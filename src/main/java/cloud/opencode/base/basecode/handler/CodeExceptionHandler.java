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
        return CodeResult.ERROR("Params error,"+ e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * handler exception
     * @param e
     * @return
     */
    @ExceptionHandler(SecurityException.class)
    public CodeResult handleSecurityException(SecurityException e) {
        log.error("Error is:{}, Error Info:{}", "No access allowed.", e.getLocalizedMessage());
        return CodeResult.ERROR("No access allowed."+ e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * handler exception
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public CodeResult handleNullPointerException(NullPointerException e) {
        log.error("Error is:{}, Error Info:{}", "Null pointer exception.", e.getLocalizedMessage());
        return CodeResult.ERROR("Null pointer exception."+ e.getLocalizedMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * handler exceptioin
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public CodeResult handlerNoFoundException(NoHandlerFoundException e) {
        log.error("Error is:{}，Error Info:{}", "NOT FOUND.", e.getLocalizedMessage());
        return CodeResult.ERROR("NOT FOUND."+ e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * handler exception
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public CodeResult handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("Error is:{}, Error Info:{}", "Request type is not supported.", e.getLocalizedMessage());
        return CodeResult.ERROR("Unsupported media type,"+ e.getLocalizedMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * handler exception
     * @param e
     * @return
     */
    @ExceptionHandler(CodeException.class)
    public CodeResult handleCodeException(CodeException e) {
        log.error("Error is:{}, Error Info:{}", e.getLocalizedMessage(), e.getErrorInfo());
        return CodeResult.ERROR( e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * handler Exception
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public CodeResult handleRuntimeException(RuntimeException e) {
        log.error("Error is:{}, Error Info:{}", "RuntimeException.", e.getLocalizedMessage());
        return CodeResult.ERROR( e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * handler Exception
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CodeResult handleException(Exception e) {
        log.error("Error is:{}, Error Info:{}", "Error.", e.getLocalizedMessage());
        return CodeResult.ERROR( e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}