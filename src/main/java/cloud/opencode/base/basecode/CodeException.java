package cloud.opencode.base.basecode;

import cloud.opencode.base.basecode.enums.ResultMessageEnum;
import cloud.opencode.base.basecode.enums.ResultStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.util.Objects;

/**
 * Unified exception class for API responses
 * 统一的API响应异常类
 *
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://opencode.cloud">OpenCode.cloud</a>
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -9137994707264363383L;

    /**
     * Error message / 错误消息
     */
    @Nullable
    private final String message;

    /**
     * HTTP status code / HTTP状态码
     */
    private final HttpStatusCode status;

    /**
     * Error code / 错误代码
     */
    @Nullable
    private final Integer code;

    /**
     * Default constructor
     * 默认构造方法
     */
    public CodeException() {
        super(ResultMessageEnum.SYSTEM_ERROR.getValue());
        this.message = ResultMessageEnum.SYSTEM_ERROR.getValue();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = ResultStatusEnum.FAIL.getCode();
    }

    /**
     * Constructor with message
     * 带消息的构造方法
     *
     * @param message Error message / 错误消息
     */
    public CodeException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = ResultStatusEnum.FAIL.getCode();
    }

    /**
     * Constructor with message and status
     * 带消息和状态的构造方法
     *
     * @param message Error message / 错误消息
     * @param status HTTP status / HTTP状态
     */
    public CodeException(String message, @Nullable HttpStatus status) {
        super(message);
        this.message = message;
        this.status = Objects.requireNonNullElse(status, HttpStatus.INTERNAL_SERVER_ERROR);
        this.code = ResultStatusEnum.FAIL.getCode();
    }

    /**
     * Constructor with message and cause
     * 带消息和原因的构造方法
     *
     * @param message Error message / 错误消息
     * @param cause Original exception / 原始异常
     */
    public CodeException(String message, @Nullable Throwable cause) {
        super(message, cause);
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = ResultStatusEnum.FAIL.getCode();
    }

    /**
     * Constructor with message, status and code
     * 带消息、状态和代码的构造方法
     *
     * @param message Error message / 错误消息
     * @param status HTTP status / HTTP状态
     * @param code Error code / 错误代码
     */
    public CodeException(String message, @Nullable HttpStatus status, @Nullable Integer code) {
        super(message);
        this.message = message;
        this.status = Objects.requireNonNullElse(status, HttpStatus.INTERNAL_SERVER_ERROR);
        this.code = Objects.requireNonNullElse(code, ResultStatusEnum.FAIL.getCode());
    }

    /**
     * Constructor with message, code and cause
     * 带消息、代码和原因的构造方法
     *
     * @param message Error message / 错误消息
     * @param code Error code / 错误代码
     * @param cause Original exception / 原始异常
     */
    public CodeException(String message, @Nullable Integer code, @Nullable Throwable cause) {
        super(message, cause);
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = Objects.requireNonNullElse(code, ResultStatusEnum.FAIL.getCode());
    }

    /**
     * Constructor with message, status, code and cause
     * 带消息、状态、代码和原因的构造方法
     *
     * @param message Error message / 错误消息
     * @param status HTTP status / HTTP状态
     * @param code Error code / 错误代码
     * @param cause Original exception / 原始异常
     */
    public CodeException(String message, @Nullable HttpStatus status, @Nullable Integer code, @Nullable Throwable cause) {
        super(message, cause);
        this.message = message;
        this.status = Objects.requireNonNullElse(status, HttpStatus.INTERNAL_SERVER_ERROR);
        this.code = Objects.requireNonNullElse(code, ResultStatusEnum.FAIL.getCode());
    }
}