package cloud.opencode.base.basecode.handler;

import cloud.opencode.base.basecode.CodeException;
import cloud.opencode.base.basecode.CodeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author Jon So,
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/opencodecloud">project github</a>
 */
@Slf4j
@RestControllerAdvice
public class CodeExceptionHandler {

    /**
     * handler exception
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public CodeResult handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Error is:{}, Error Info:{}", "Params error.", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Params error,"+ e.getLocalizedMessage());
    }

    /**
     * handler exception
     * @param e
     * @return
     */
    @ExceptionHandler(SecurityException.class)
    public CodeResult handleSecurityException(SecurityException e) {
        log.error("Error is:{}, Error Info:{}", "No access allowed.", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.UNAUTHORIZED, "No access allowed,"+ e.getLocalizedMessage());
    }

    /**
     * handler exception
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public CodeResult handleNullPointerException(NullPointerException e) {
        log.error("Error is:{}, Error Info:{}", "Null pointer exception.", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.SERVICE_UNAVAILABLE, "Null pointer exception,"+ e.getLocalizedMessage());
    }

    /**
     * handler exceptioin
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public CodeResult handlerNoFoundException(NoHandlerFoundException e) {
        log.error("Error is:{}，Error Info:{}", "NOT FOUND.", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.NOT_FOUND, "NOT FOUND,"+ e.getLocalizedMessage());
    }

    /**
     * handler exception
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public CodeResult handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("Error is:{}, Error Info:{}", "Request type is not supported.", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported media type,"+ e.getLocalizedMessage());
    }

    /**
     * handler exception
     * @param e
     * @return
     */
    @ExceptionHandler(CodeException.class)
    public CodeResult handleCodeException(CodeException e) {
        log.error("Error is:{}, Error Info:{}", e.getLocalizedMessage(), e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.INTERNAL_SERVER_ERROR,  e.getLocalizedMessage());
    }

    /**
     * handler Exception
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public CodeResult handleRuntimeException(RuntimeException e) {
        log.error("Error is:{}, Error Info:{}", "RuntimeException.", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.INTERNAL_SERVER_ERROR,  e.getLocalizedMessage());
    }

    /**
     * handler Exception
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CodeResult handleException(Exception e) {
        log.error("Error is:{}, Error Info:{}", "Error.", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.INTERNAL_SERVER_ERROR,  e.getLocalizedMessage());
    }
}