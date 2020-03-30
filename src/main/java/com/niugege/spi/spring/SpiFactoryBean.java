package com.niugege.spi.spring;

import com.niugege.spi.api.IBaseSpi;
import com.niugege.spi.core.proxy.SpiProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * spring spi bean creation,delegate to SpiProxy
 */
public class SpiFactoryBean<T extends IBaseSpi> implements FactoryBean<T>, InitializingBean {

    @SuppressWarnings("unchecked")
    public static final String FIELD_SPI_FUNCTION_POINT_CLASS = "spiFunctionPointClass";

    private Class<T> spiFunctionPointClass;

    public SpiFactoryBean() {

    }

    public SpiFactoryBean(Class<T> spiFunctionPointClass) {
        this.spiFunctionPointClass = spiFunctionPointClass;
    }

    public void setSpiFunctionPointClass(Class<T> spiFunctionPointClass) {
        this.spiFunctionPointClass = spiFunctionPointClass;
    }


    @Override
    public T getObject() {
        return SpiProxyFactory.newInstance(spiFunctionPointClass);
    }

    @Override
    public Class<T> getObjectType() {
        return spiFunctionPointClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
