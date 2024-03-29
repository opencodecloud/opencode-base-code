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
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://opencode.cloud">OpenCode.cloud</a>
 */
@RestControllerAdvice
public class CodeResponseHandler implements ResponseBodyAdvice<Object> {
    @Autowired(required = false)
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> clazz) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> clazz, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        if(o instanceof String){
            return objectMapper.writeValueAsString(CodeResult.OKWithoutStatus(o));
        }
        if(o instanceof CodeResult){
            serverHttpResponse.setStatusCode(((CodeResult<?>) o).getStatus());
            ((CodeResult<?>) o).setStatus(null);
            HttpHeaders headers = serverHttpResponse.getHeaders();
            if(((CodeResult<?>) o).getHeaders()!=null){
                for(String key: ((CodeResult<?>) o).getHeaders().keySet()){
                    List<String> list = ((CodeResult<?>) o).getHeaders().get(key);
                    if(list!=null){
                        for (String s : list) {
                            headers.set(key,s);
                        }
                    }
                }
                ((CodeResult<?>) o).setHeaders(null);
            }
            MediaType mediaType1 = ((CodeResult<?>) o).getMediaType();

            if(mediaType1 !=null){
                headers.setContentType(mediaType1);
                if(mediaType1 ==MediaType.APPLICATION_PDF || mediaType1 ==MediaType.IMAGE_JPEG ||
                        mediaType1 ==MediaType.IMAGE_PNG || mediaType1 ==MediaType.IMAGE_GIF ||
                        mediaType1 ==MediaType.APPLICATION_OCTET_STREAM){
                    OutputStream outputStream = serverHttpResponse.getBody();
                    outputStream.write((byte[]) ((CodeResult<?>) o).getData());
                    outputStream.flush();
                    outputStream.close();
                }
                ((CodeResult<?>) o).setMediaType(null);
            }
            return o;
        }
        if(o instanceof CodeException){
            serverHttpResponse.setStatusCode(((CodeException) o).getStatus());
            ((CodeException) o).setStatus(null);
            return o;
        }
        return CodeResult.OK(o);
    }
}