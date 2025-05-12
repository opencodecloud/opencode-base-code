package cloud.opencode.base.basecode;

import cloud.opencode.base.basecode.enums.ResultMessageEnum;
import cloud.opencode.base.basecode.enums.ResultStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://opencode.cloud">OpenCode.cloud</a>
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeResult<T>  implements Serializable {
    @Serial
    private static final long serialVersionUID = -4239977605869432973L;
    @Nullable
    private T data;
    @Nullable
    private Integer code;
    @Nullable
    private String message;
    private HttpStatus status;
    @Nullable
    private Long total;
    @Nullable
    private Long page;
    @Nullable
    private Long pageSize;
    @Nullable
    private HttpHeaders headers;
    private long timestamp ;
    @Nullable
    private MediaType mediaType;

    public CodeResult(@Nullable HttpStatus status) {
        this.status = Objects.requireNonNullElse(status, HttpStatus.OK);
        this.data = null;
        this.code = ResultStatusEnum.SUCCESS.getCode();
        this.message = ResultMessageEnum.SYSTEM_OK.getValue();
        this.mediaType = null;
        this.headers =null;
        this.total = null;
        this.page = null;
        this.pageSize = null;
        this.timestamp = System.currentTimeMillis();
    }


    public CodeResult(@Nullable HttpStatus status,@Nullable T body) {
        this.status = Objects.requireNonNullElse(status, HttpStatus.OK);
        this.data = body;
        this.code = ResultStatusEnum.SUCCESS.getCode();
        this.message = ResultMessageEnum.SYSTEM_OK.getValue();
        this.mediaType = null;
        this.headers =null;
        this.total = null;
        this.page = null;
        this.pageSize = null;
        this.timestamp = System.currentTimeMillis();
    }

    public CodeResult(@Nullable HttpStatus status, @Nullable T body, @Nullable String message, @Nullable Integer code) {
        this.status = Objects.requireNonNullElse(status, HttpStatus.OK);
        this.data = body;
        this.code = Objects.requireNonNullElseGet(code, ResultStatusEnum.SUCCESS::getCode);
        this.message = Objects.requireNonNullElseGet(message, ResultMessageEnum.SYSTEM_OK::getValue);
        this.mediaType = null;
        this.headers =null;
        this.total = null;
        this.page = null;
        this.pageSize = null;
        this.timestamp = System.currentTimeMillis();
    }

    public CodeResult(@Nullable HttpStatus status,@Nullable T body, @Nullable MediaType type, @Nullable MultiValueMap<String, String> headers ) {
        this.status = Objects.requireNonNullElse(status, HttpStatus.OK);
        this.data = body;
        this.code = ResultStatusEnum.SUCCESS.getCode();
        this.message = ResultMessageEnum.SYSTEM_OK.getValue();
        this.mediaType = type;
        this.headers = HttpHeaders.readOnlyHttpHeaders(headers != null ? headers : new HttpHeaders());;
        this.total = null;
        this.page = null;
        this.pageSize = null;
        this.timestamp = System.currentTimeMillis();
    }


    public CodeResult(@Nullable HttpStatus status, @Nullable T body, @Nullable Long total, @Nullable Long page, @Nullable Long pageSize,@Nullable String message, @Nullable Integer code,@Nullable MediaType type, @Nullable MultiValueMap<String, String> headers) {
        this.status = Objects.requireNonNullElse(status, HttpStatus.OK);
        this.data = body;
        this.code = Objects.requireNonNullElseGet(code, ResultStatusEnum.SUCCESS::getCode);
        this.message = Objects.requireNonNullElseGet(message, ResultMessageEnum.SYSTEM_OK::getValue);
        this.mediaType = type;
        this.headers = HttpHeaders.readOnlyHttpHeaders(headers != null ? headers : new HttpHeaders());;
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * OK
     * @return
     */
    public static CodeResult OK() {
        return new CodeResult(HttpStatus.OK);
    }

    /**
     * OK
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data) {
        return new CodeResult(HttpStatus.OK,data);
    }

    /**
     * OK
     * @param data data
     * @param status HttpStatus
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data, HttpStatus status) {
        return new CodeResult(status,data);
    }

    /**
     * OK
     * @param data data
     * @param message message
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data,String message) {
        return new CodeResult(HttpStatus.OK,data,message,ResultStatusEnum.SUCCESS.getCode());
    }

    /**
     * OK
     * @param data data
     * @param message message
     * @param status HttpStatus
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data,String message, HttpStatus status) {
        return new CodeResult(status,data,message,ResultStatusEnum.SUCCESS.getCode());
    }

    /**
     * OK
     * @param data data
     * @param message message
     * @param code code
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data,String message,Integer code) {
        return new CodeResult(HttpStatus.OK,data,message,code);
    }

    /**
     * OK
     * @param data data
     * @param message message
     * @param code code
     * @param status HttpStatus
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data,String message,Integer code, HttpStatus status) {
        return new CodeResult(status,data,message,code);
    }

    /**
     * OK
     * @param data data
     * @param type MediaType
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data, MediaType type) {
        return new CodeResult(null,data,type,null);
    }

    /**
     * OK
     * @param data data
     * @param type MediaType
     * @param status HttpStatus
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data, MediaType type,HttpStatus status ) {
        return new CodeResult(status,data,type,null);
    }

    /**
     * OK
     * @param data data
     * @param type type
     * @param headers HttpHeaders
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data, @Nullable MediaType type, @Nullable MultiValueMap<String, String> headers) {
        return new CodeResult(null,data,type,headers);
    }

    /**
     * OK
     * @param data data
     * @param type type
     * @param headers HttpHeaders
     * @param status HttpStatus
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data, @Nullable MediaType type, @Nullable MultiValueMap<String, String> headers, HttpStatus status) {
        return new CodeResult(status,data,type,headers);
    }

    /**
     * OK
     * @param data data
     * @param total total
     * @param page page
     * @param pageSize pageSize
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data, Long total, Long page, Long pageSize) {
        return new CodeResult(null,data,total,page,pageSize,null,null,null,null);
    }

    /**
     * OK
     * @param data data
     * @param total total
     * @param page page
     * @param pageSize pageSize
     * @param message message
     * @param code code
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data, Long total, Long page, Long pageSize,String message, Integer code) {
        return new CodeResult(null,data,total,page,pageSize,message,code,null,null);
    }

    /**
     * OK
     * @param data DATA
     * @param total total
     * @param page page
     * @param pageSize pageSize
     * @param message message
     * @param code code
     * @param type MediaType
     * @param headers HttpHeaders
     * @return
     * @param <T>
     */
    public static <T> CodeResult OK(T data ,Long total, Long page, Long pageSize,String message, Integer code, @Nullable MediaType type, @Nullable  MultiValueMap<String, String> headers) {
        return new CodeResult(null,data,total,page,pageSize,message,code,type,headers);
    }

    /**
     * OKWithMediaTypeAndHeaders
     * @param data data
     * @param type MediaType
     * @param headers HttpHeaders
     * @param status HttpStatus
     * @return
     * @param <T>
     */
    public static <T> CodeResult OKWithMediaTypeAndHeaders(T data, @Nullable MediaType type, @Nullable MultiValueMap<String, String> headers, HttpStatus status) {
        return new CodeResult(status,data,type,headers);
    }

    /**
     * OKWithMediaTypeAndHeaders
     * @param data data
     * @param type MediaType
     * @param headers HttpHeaders
     * @return
     * @param <T>
     */
    public static <T> CodeResult OKWithMediaTypeAndHeaders(T data, @Nullable MediaType type, @Nullable MultiValueMap<String, String> headers) {
        return new CodeResult(null,data,type,headers);
    }

    /**
     * OK
     * @param status HttpStatus
     * @return
     * @param <T>
     */
    public static <T> CodeResult OKOnlyStatus(HttpStatus status) {
        return new CodeResult(status);
    }

    /**
     * OK
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CodeResult OKWithoutStatus(T data) {
        CodeResult codeResult = new CodeResult(HttpStatus.OK, data);
        codeResult.setStatus(null);
        return codeResult;
    }

    /**
     * ERROR
      * @return
     */
    public static CodeResult ERROR() {
        return new CodeResult(HttpStatus.BAD_REQUEST,null, ResultMessageEnum.SYSTEM_ERROR.getValue(), ResultStatusEnum.FAIL.getCode());
    }

    /**
     * ERROR
     * @param message message
     * @return
     */
    public static CodeResult ERROR(String message) {
        return new CodeResult(HttpStatus.BAD_REQUEST,null,message,ResultStatusEnum.FAIL.getCode());
    }

    /**
     * ERROR
     * @param status HttpStatus
     * @param message message
     * @return
     */
    public static CodeResult ERROR(HttpStatus status,String message) {
        return new CodeResult(status,null, message, ResultStatusEnum.FAIL.getCode());
    }

    /**
     * ERROR
     * @param status HttpStatus
     * @param message message
     * @param code code
     * @return
     */
    public static CodeResult ERROR(HttpStatus status,String message,Integer code) {
        return new CodeResult(status,null, message, code);
    }

    /**
     * ERROR
     * @param status HttpStatus
     * @param type  MediaType
     * @param headers HttpHeaders
     * @return
     */
    public static CodeResult ERROR(HttpStatus status,@Nullable MediaType type, @Nullable MultiValueMap<String, String> headers) {
        return new CodeResult(status,null, type, headers);
    }



}
