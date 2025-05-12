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
 * Global exception handler
 * 全局异常处理器
 *
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://opencode.cloud">OpenCode.cloud</a>
 */
@Slf4j
@RestControllerAdvice
public class CodeExceptionHandler {

    /**
     * Handle IllegalArgumentException
     * 处理参数异常
     *
     * @param e IllegalArgumentException instance / 参数异常实例
     * @return CodeResult instance / CodeResult实例
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public CodeResult handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Error is: {}, Error Info: {}", "Params error", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.BAD_REQUEST, "Params error: " + e.getLocalizedMessage());
    }

    /**
     * Handle SecurityException
     * 处理安全异常
     *
     * @param e SecurityException instance / 安全异常实例
     * @return CodeResult instance / CodeResult实例
     */
    @ExceptionHandler(SecurityException.class)
    public CodeResult handleSecurityException(SecurityException e) {
        log.error("Error is: {}, Error Info: {}", "No access allowed", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.UNAUTHORIZED, "No access allowed: " + e.getLocalizedMessage());
    }

    /**
     * Handle NullPointerException
     * 处理空指针异常
     *
     * @param e NullPointerException instance / 空指针异常实例
     * @return CodeResult instance / CodeResult实例
     */
    @ExceptionHandler(NullPointerException.class)
    public CodeResult handleNullPointerException(NullPointerException e) {
        log.error("Error is: {}, Error Info: {}", "Null pointer exception", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Null pointer exception: " + e.getLocalizedMessage());
    }

    /**
     * Handle NoHandlerFoundException
     * 处理未找到处理器异常
     *
     * @param e NoHandlerFoundException instance / 未找到处理器异常实例
     * @return CodeResult instance / CodeResult实例
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public CodeResult handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("Error is: {}, Error Info: {}", "Not found", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.NOT_FOUND, "Not found: " + e.getLocalizedMessage());
    }

    /**
     * Handle HttpMediaTypeNotSupportedException
     * 处理不支持的媒体类型异常
     *
     * @param e HttpMediaTypeNotSupportedException instance / 不支持的媒体类型异常实例
     * @return CodeResult instance / CodeResult实例
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public CodeResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("Error is: {}, Error Info: {}", "Request type is not supported", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported media type: " + e.getLocalizedMessage());
    }

    /**
     * Handle CodeException
     * 处理自定义异常
     *
     * @param e CodeException instance / 自定义异常实例
     * @return CodeResult instance / CodeResult实例
     */
    @ExceptionHandler(CodeException.class)
    public CodeResult handleCodeException(CodeException e) {
        log.error("Error is: {}, Error Info: {}", e.getMessage(), e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.valueOf(e.getStatus().value()), e.getMessage());
    }

    /**
     * Handle RuntimeException
     * 处理运行时异常
     *
     * @param e RuntimeException instance / 运行时异常实例
     * @return CodeResult instance / CodeResult实例
     */
    @ExceptionHandler(RuntimeException.class)
    public CodeResult handleRuntimeException(RuntimeException e) {
        log.error("Error is: {}, Error Info: {}", "Runtime exception", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    }

    /**
     * Handle Exception
     * 处理通用异常
     *
     * @param e Exception instance / 异常实例
     * @return CodeResult instance / CodeResult实例
     */
    @ExceptionHandler(Exception.class)
    public CodeResult handleException(Exception e) {
        log.error("Error is: {}, Error Info: {}", "System error", e.getLocalizedMessage());
        return CodeResult.ERROR(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    }
}