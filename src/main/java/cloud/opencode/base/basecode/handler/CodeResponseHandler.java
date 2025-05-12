package cloud.opencode.base.basecode.handler;

import cloud.opencode.base.basecode.CodeException;
import cloud.opencode.base.basecode.CodeResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.OutputStream;
import java.util.List;

/**
 * Response body handler for unified response format
 * 统一响应格式的响应体处理器
 *
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://opencode.cloud">OpenCode.cloud</a>
 */
@RestControllerAdvice
public class CodeResponseHandler implements ResponseBodyAdvice<Object> {
    
    @Autowired(required = false)
    private ObjectMapper objectMapper;

    /**
     * Check if the response should be handled
     * 检查是否应该处理响应
     *
     * @param methodParameter Method parameter / 方法参数
     * @param clazz Converter class / 转换器类
     * @return true if should handle / 如果需要处理则返回true
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> clazz) {
        return true;
    }

    /**
     * Handle response body before writing
     * 在写入响应体之前处理
     *
     * @param body Response body / 响应体
     * @param methodParameter Method parameter / 方法参数
     * @param mediaType Media type / 媒体类型
     * @param clazz Converter class / 转换器类
     * @param request Server request / 服务器请求
     * @param response Server response / 服务器响应
     * @return Processed response body / 处理后的响应体
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                Class<? extends HttpMessageConverter<?>> clazz, ServerHttpRequest request,
                                ServerHttpResponse response) {
        // Handle String response
        if (body instanceof String) {
            return objectMapper.writeValueAsString(CodeResult.OK(body));
        }

        // Handle CodeResult response
        if (body instanceof CodeResult) {
            CodeResult<?> result = (CodeResult<?>) body;
            response.setStatusCode(result.getStatus());
            result.setStatus(null);

            // Handle headers
            HttpHeaders headers = response.getHeaders();
            if (result.getHeaders() != null) {
                for (String key : result.getHeaders().keySet()) {
                    List<String> values = result.getHeaders().get(key);
                    if (values != null) {
                        for (String value : values) {
                            headers.set(key, value);
                        }
                    }
                }
                result.setHeaders(null);
            }

            // Handle media type and binary data
            MediaType responseMediaType = result.getMediaType();
            if (responseMediaType != null) {
                headers.setContentType(responseMediaType);
                if (isBinaryMediaType(responseMediaType)) {
                    OutputStream outputStream = response.getBody();
                    outputStream.write((byte[]) result.getData());
                    outputStream.flush();
                    outputStream.close();
                }
                result.setMediaType(null);
            }
            return result;
        }

        // Handle CodeException response
        if (body instanceof CodeException) {
            CodeException exception = (CodeException) body;
            response.setStatusCode(exception.getStatus());
            return exception;
        }

        // Handle other responses
        return CodeResult.OK(body);
    }

    /**
     * Check if the media type is binary
     * 检查媒体类型是否为二进制
     *
     * @param mediaType Media type to check / 要检查的媒体类型
     * @return true if binary / 如果是二进制则返回true
     */
    private boolean isBinaryMediaType(MediaType mediaType) {
        return mediaType == MediaType.APPLICATION_PDF
                || mediaType == MediaType.IMAGE_JPEG
                || mediaType == MediaType.IMAGE_PNG
                || mediaType == MediaType.IMAGE_GIF
                || mediaType == MediaType.APPLICATION_OCTET_STREAM;
    }
}