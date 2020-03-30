package com.niugege.spi.core.container;

import com.niugege.spi.api.IBaseSpi;
import org.springframework.aop.support.AopUtils;

import java.util.Collection;

/**
 * Spi Register
 */
public class SpiRegister {

    private SpiContainer container;

    private Collection<Object> bizSpiBeans;

    public void setContainer(SpiContainer container) {
        this.container = container;
    }

    public void setBizSpiBeans(Collection<Object> bizSpiBeans) {
        this.bizSpiBeans = bizSpiBeans;
    }

    public void register() {
        if (container == null) {
            return;
        }
        if (bizSpiBeans == null) {
            return;
        }
        bizSpiBeans.forEach(this::addToContainer);
    }

    @SuppressWarnings("unchecked")
    private void addToContainer(Object bean) {
        if (!(bean instanceof IBaseSpi)) {
            return;
        }
        IBaseSpi bizSpiBean = (IBaseSpi) bean;
        /*
         * get target bean real supper interface class type (maybe spring aop proxy wrapped)
         */
        Class<? extends IBaseSpi> spiClass = (Class<? extends IBaseSpi>) AopUtils.getTargetClass(bizSpiBean).getInterfaces()[0];
        /*
         * add into spi container
         */
        container.addToContainer(spiClass, bizSpiBean);
    }
}
