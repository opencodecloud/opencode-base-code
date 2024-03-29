package cloud.opencode.base.basecode.handler;

import cloud.opencode.base.basecode.CodeException;
import cloud.opencode.base.basecode.CodeResult;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

/**
 * @author Jon So,
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/opencodecloud">project github</a>
 */
@Component
public class CodeExceptionHandlerResolver implements HandlerExceptionResolver {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * resolveException
     *
     * @param httpServletRequest  HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @param o                   object
     * @param e                   exception
     * @return ModelAndView
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String message = e.getLocalizedMessage();
        if (e instanceof CodeException ce) {
            message = ce.getMessage();
        }

        if (o instanceof HandlerMethod) {
            if (e instanceof IllegalArgumentException) {
                message = "Params error.";
            } else if (e instanceof SecurityException) {
                message = "No access allowed.";
            } else if (e instanceof NullPointerException) {
                message = "Null pointer exception.";
            }
        } else if (e instanceof NoHandlerFoundException) {
            message = "Corresponding resource not found.";
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            message = "Request type is not supported.";
        }
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        try {
            httpServletResponse.getWriter()
                    .write(
                            OBJECT_MAPPER.writeValueAsString(
                                    CodeResult.ERROR(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, message)
                            )
                    );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ModelAndView();
    }
}