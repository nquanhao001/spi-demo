package com.niugege.spi.annotation;


import com.niugege.spi.api.IBaseSpi;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;


/**
 * 业务spi注解，标记一个类为spi接口(功能节点接口)实现
 */
@Service
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BizSpi {

    /**
     * @return 描述信息
     */
    String desc() default IBaseSpi.DEFAULT_SPI_DESCRIPTION;
}
