package com.niugege.spi.api;

import java.util.List;

/**
 * spi接口定义
 *
 */
public interface IBaseSpi<In, Out> {
    /**
     * spi默认描述
     */
    String DEFAULT_SPI_DESCRIPTION = "";

    /**
     * spi执行条件
     *
     * @param context spi上下文
     * @return true:执行spi，false:跳过执行
     */
    boolean condition(In context);

    /**
     * spi执行内容
     *
     * @param context spi上下文
     * @return spi执行结果组合
     */
    List<Out> invoke(In context);

    /**
     * spi配置信息
     *
     * @param context spi上下文
     * @return spi配置
     */
    default SpiConfig config(In context) {
        return SpiConfig.create();
    }
}
