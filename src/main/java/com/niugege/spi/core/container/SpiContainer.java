package com.niugege.spi.core.container;

import com.niugege.spi.api.IBaseSpi;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * the SpiContainer interface definition
 */
public interface SpiContainer {

    SpiContainer DEFAULT_INSTANCE = DefaultSpiContainer.getInstance();

    /**
     * get all interface implementations
     *
     * @return the all spi implementations
     */
    Set<Map.Entry<Class<? extends IBaseSpi>, List<IBaseSpi>>> getAllSpiImpl();

    /**
     * look up spi interface implementations
     *
     * @param spiClass the spi interface class
     * @return the spi interface implementations
     */
    List<? extends IBaseSpi> lookup(Class<? extends IBaseSpi> spiClass);

    /**
     * add spi implementation into container
     *
     * @param spiClass the spi interface class
     * @param spiImpl  the spi interface implementation
     */
    void addToContainer(Class<? extends IBaseSpi> spiClass, IBaseSpi spiImpl);
}
