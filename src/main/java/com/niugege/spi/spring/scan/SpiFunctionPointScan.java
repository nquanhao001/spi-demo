package com.niugege.spi.spring.scan;

import com.niugege.spi.annotation.SpiFunctionPoint;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>
 * <b>扫描{@linkplain SpiFunctionPoint}的注解方式配置，配置使用示例：</b>
 * </p>
 * <pre class="code">
 *
 *     &#064;SpringBootApplication
 *     &#064;SpiFunctionPointScan("com.ggj.ecommerce.bizspi.sample")
 *     public class Application extends SpringBootServletInitializer {
 *
 *     &#064;Override
 *     protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
 *         return application.sources(Application.class);
 *     }
 *
 *     public static void main(String[] args) {
 *         SpringApplication.run(Application.class, args);
 *     }
 *  }
 * </pre>
 *
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SpiFunctionPointScannerRegistrar.class)
public @interface SpiFunctionPointScan {

    /**
     * SPI功能点扫描包路径
     */
    @AliasFor("basePackages")
    String[] value() default {};

    /**
     * SPI功能点扫描包路径
     */
    @AliasFor("value")
    String[] basePackages() default {};

}
