package cloud.opencode.base.basecode;

import cloud.opencode.base.basecode.enums.ResultMessageEnum;
import cloud.opencode.base.basecode.enums.ResultStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;

import java.io.Serial;

/**
 * @author Jon So,
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/opencodecloud">project github</a>
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -9137994707264363383L;
    @Nullable
    private String message;
    private HttpStatusCode status;
    @Nullable
    private Integer code = ResultStatusEnum.FAIL.getCode();

    /**
     * CodeException
     */
    public CodeException() {
        super(ResultMessageEnum.SYSTEM_ERROR.getValue());
        this.message = ResultMessageEnum.SYSTEM_ERROR.getValue();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * CodeException
     * @param message message
     */
    public CodeException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * CodeException
     * @param message message
     * @param status status
     */
    public CodeException(String message, @Nullable HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    /**
     * CodeException
     * @param message message
     * @param e e
     */
    public CodeException(String message,@Nullable Throwable e) {
        super(message, e);
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }


    /**
     * CodeException
     * @param message message
     * @param status status
     * @param code code
     */
    public CodeException(String message, @Nullable HttpStatus status, @Nullable int code) {
        super(message);
        this.message = message;
        this.code = code;
        this.status = status;
    }

    /**
     * CodeException
     * @param message message
     * @param code code
     * @param e e
     */
    public CodeException(String message, @Nullable int code, @Nullable Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * CodeException
     * @param message message
     * @param status HttpStatus
     * @param code code
     * @param e e
     */
    public CodeException(String message, @Nullable HttpStatus status, @Nullable int code, @Nullable Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
        this.status = status;
    }

}