package com.niugege.spi.core.proxy;


import com.niugege.spi.api.IBaseSpi;
import com.niugege.spi.core.container.SpiContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * spi proxy
 */
public class SpiProxy<T extends IBaseSpi> implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpiProxy.class);

    private Class<T> spiClass;

    SpiProxy(Class<T> spiClass) {
        this.spiClass = spiClass;
    }

    Class<T> getSpiClass() {
        return spiClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /*
         * get spi interface implementations
         */
        List<? extends IBaseSpi> spiImplList = SpiContainer.DEFAULT_INSTANCE.lookup(spiClass);
        /*
         * null or empty
         */
        if (spiImplList == null || spiImplList.isEmpty()) {
            LOGGER.warn("biz-spi-engine:can not spi implementations for class:{}", spiClass.getName());
            return new ArrayList<>();
        }
        /*
         * support multi spi implementation result combine
         */
        List compositeResult = new ArrayList<>();

        for (IBaseSpi spi : spiImplList) {
            /*
             * if current spi implementation match condition
             */
            if (null != args && spi.condition(args[0])) {
                List<?> result;
                try {
                    /*
                     * invoke method -> "invoke"
                     */
                    result = (List<?>) method.invoke(spi, args);
                } catch (InvocationTargetException e) {
                    /*
                     * do not swallow the real exception,
                     * the real exception target wrapped by InvocationTargetException
                     */
                    throw e.getTargetException();
                }
                /*
                 * if current spi implementation return not empty,
                 * add into compositeResult
                 */
                if (result != null && !result.isEmpty()) {
                    compositeResult.addAll(result);
                }
                /*
                 * if spi interface implementations is mutex,execute one of witch match condition
                 */
                if (spi.config(args[0]).isMutex()) {
                    break;
                }
            }
        }
        return compositeResult;
    }
}
