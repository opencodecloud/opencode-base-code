package cloud.opencode.base.basecode.annotation;

import cloud.opencode.base.basecode.config.CodeExceptionAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Jon So,
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/opencodecloud">project github</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CodeExceptionAutoConfiguration.class)
@Documented
@Inherited
public @interface EnableCodeException {
}