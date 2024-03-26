package cloud.opencode.base.basecode;

import cloud.opencode.base.basecode.enums.ResultMessageEnum;
import cloud.opencode.base.basecode.enums.ResultStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://opencode.cloud">OpenCode.cloud</a>
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeResult<T> extends ResponseEntity<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -877545005334587795L;
    private T data;
    private Integer code;
    private String message;
    private Long total;
    private Long page;
    private Long pageSize;

    public CodeResult(HttpStatusCode status) {
        super(status);
        this.data = null;
        this.code = ResultStatusEnum.SUCCESS.getCode();
        this.message = ResultMessageEnum.SYSTEM_OK.getValue();
        this.total = null;
        this.page = null;
        this.pageSize = null;
    }

    public CodeResult(@Nullable T body, HttpStatusCode status) {
        super(body, status);
        this.data = body;
        this.code = null;
        this.message = null;
        this.total = null;
        this.page = null;
        this.pageSize = null;
    }

    public CodeResult(@Nullable T body, HttpStatusCode status, @Nullable Integer code) {
        super(body, status);
        this.data = body;
        this.code = code;
        this.message = null;
        this.total = null;
        this.page = null;
        this.pageSize = null;
    }

    public CodeResult(@Nullable MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(headers, status);
        this.data = null;
        this.code = null;
        this.message = null;
        this.total = null;
        this.page = null;
        this.pageSize = null;
    }

    public CodeResult(@Nullable T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
        this.data = body;
        this.code = null;
        this.message = null;
        this.total = null;
        this.page = null;
        this.pageSize = null;
    }

    public CodeResult(@Nullable T body, MultiValueMap<String, String> headers, HttpStatusCode statusCode) {
        super(body, headers, statusCode);
        this.data = body;
        this.code = null;
        this.message = null;
        this.total = null;
        this.page = null;
        this.pageSize = null;
    }

    public CodeResult(@Nullable T body, HttpStatusCode status, @Nullable String message) {
        super(body, status);
        this.data = body;
        this.message = message;
        this.code = null;
        this.total = null;
        this.page = null;
        this.pageSize = null;
    }

    public CodeResult(@Nullable T body, HttpStatusCode status, @Nullable String message, @Nullable Integer code) {
        super(body, status);
        this.data = body;
        this.message = message;
        this.code = code;
        this.total = null;
        this.page = null;
        this.pageSize = null;
    }

    public CodeResult(@Nullable T body, HttpStatusCode status, @Nullable String message, @Nullable Long total, @Nullable Long page, @Nullable Long pageSize) {
        super(body, status);
        this.data = body;
        this.message = message;
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
    }

    public CodeResult(@Nullable T body, HttpStatusCode status, @Nullable String message, @Nullable Long total, @Nullable Long page, @Nullable Long pageSize, @Nullable Integer code) {
        super(body, status);
        this.data = body;
        this.message = message;
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.code = code;
    }

    /**
     * OK
     * @return
     */
    public static CodeResult OK() {
        return new CodeResult(HttpStatusCode.valueOf(200));
    }

    /**
     * OK
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data) {
        return new CodeResult(data,HttpStatusCode.valueOf(200),ResultMessageEnum.SYSTEM_SUCCESS.getValue(),ResultStatusEnum.SUCCESS.getCode());
    }

    /**
     * OK
     * @param data
     * @param message
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data, String message) {
        return new CodeResult(data, HttpStatusCode.valueOf(200), message,ResultStatusEnum.SUCCESS.getCode());
    }

    /**
     * OK
     * @param data
     * @param message
     * @param code
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data, String message, Integer code) {
        return new CodeResult(data, HttpStatusCode.valueOf(200), message, code);
    }

    /**
     * OK
     * @param data
     * @param message
     * @param total
     * @param page
     * @param pageSize
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data, String message, Long total, Long page, Long pageSize) {
        return new CodeResult(data, HttpStatusCode.valueOf(200), message, total, page, pageSize);
    }

    /**
     * OK
     * @param data
     * @param message
     * @param total
     * @param page
     * @param pageSize
     * @param code
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data, String message, Long total, Long page, Long pageSize, Integer code) {
        return new CodeResult(data, HttpStatusCode.valueOf(200), message, total, page, pageSize, code);
    }

    /**
     * OK
     * @param message
     * @param code
     * @return
     */
    public static CodeResult OK(String message, Integer code) {
        return new CodeResult(null, HttpStatusCode.valueOf(200), message, code);
    }

    /**
     * OK
     * @param body
     * @param headers
     * @param rawStatus
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(@Nullable T body, MultiValueMap<String, String> headers, int rawStatus) {
        return new CodeResult(body, headers, rawStatus);
    }

    /**
     * OK
     * @param headers
     * @param status
     * @return
     */
    public static CodeResult OK(@Nullable MultiValueMap<String, String> headers, HttpStatusCode status) {
        return new CodeResult(headers, HttpStatusCode.valueOf(status.value()));
    }

    /**
     * OK
     * @param body
     * @param headers
     * @param statusCode
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(@Nullable T body, MultiValueMap<String, String> headers, HttpStatusCode statusCode) {
        return new CodeResult(body, headers, statusCode);
    }


    /**
     * ERROR
     * @param message
     * @param status
     * @return
     */
    public static CodeResult ERROR(String message, HttpStatus status) {
        return new CodeResult(null, HttpStatusCode.valueOf(status.value()), ResultStatusEnum.FAIL.getCode());
    }

    /**
     * ERROR
     * @param message
     * @return
     */
    public static CodeResult ERROR(String message) {
        return new CodeResult(null, HttpStatusCode.valueOf(400), message, ResultStatusEnum.FAIL.getCode());
    }

}
