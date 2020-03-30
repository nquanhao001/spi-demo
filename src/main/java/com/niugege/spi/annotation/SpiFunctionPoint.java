package com.niugege.spi.annotation;


import com.niugege.spi.api.IBaseSpi;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>
 * <b>业务SPI功能点标记注解，说明：</b>
 * </p>
 * <ul>
 * <li>该注解表明这是一个业务功能点：业务功能点，就是整个业务流程中抽离出来的一个功能节点</li>
 * <li>该注解只用在，<b>实现了IBase接口</b>且<b>定义为接口</b>的类定义上。</li>
 * <li>该注解是生效只有有两种方式，1、配置SpiFunctionPointScannerConfigurer bean。2、配置@SpiFunctionPointScan注解方式</li>
 * </ul>
 * <b>使用配置示例:</b>
 * <pre class="code">
 *  &#064;SpiFunctionPoint("计价：本地计价、远程优惠计价、不需要计价等多种不同计价方式")
 *  public interface CalcPrice extends IBaseSpi< GroupOrderSDTO, Integer> {
 *  }
 * </pre>
 *
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpiFunctionPoint {

    /**
     * 业务功能节点描述
     */
    @AliasFor("desc")
    String value() default IBaseSpi.DEFAULT_SPI_DESCRIPTION;

    /**
     * 业务功能节点描述
     */
    @AliasFor("value")
    String desc() default IBaseSpi.DEFAULT_SPI_DESCRIPTION;

    /**
     * custom SpiFactoryBean bean name
     * <p>
     * 注入时使用 @Resource(name = "${custom definition spiBeanName}")
     */
    String spiBeanName() default "";
}

