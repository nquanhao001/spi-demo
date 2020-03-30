package com.niugege.spi.core.proxy;


import com.niugege.spi.api.IBaseSpi;

import java.lang.reflect.Proxy;

/**
 * simple spi proxy factory
 */
public class SpiProxyFactory {

    /**
     * create a new proxy instance with SpiProxy
     *
     * @param spiProxy the spi proxy instance
     * @param <T>      the type of spi interface class
     * @return the proxied object
     */
    @SuppressWarnings("unchecked")
    private static <T extends IBaseSpi> T newInstance(SpiProxy<T> spiProxy) {
        final Class<T> bizSpiClass = spiProxy.getSpiClass();
        return (T) Proxy.newProxyInstance(bizSpiClass.getClassLoader(), new Class[]{bizSpiClass}, spiProxy);
    }

    /**
     * create a new proxy instance with spi class
     *
     * @param spiClass the spi interface class
     * @param <T>      the type of spi interface class
     * @return the proxied object
     */
    public static <T extends IBaseSpi> T newInstance(Class<T> spiClass) {
        final SpiProxy<T> spiProxy = new SpiProxy<>(spiClass);
        return newInstance(spiProxy);
    }
}
