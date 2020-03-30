package com.niugege.spi.core.container;

import com.niugege.spi.api.IBaseSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * default SpiContainer implement
 */
public class DefaultSpiContainer implements SpiContainer {

    private final Logger logger = LoggerFactory.getLogger(DefaultSpiContainer.class);

    private static final Map<Class<? extends IBaseSpi>, List<IBaseSpi>> container = new HashMap<>();

    public static class DefaultSpiContainerInstance {
        static final DefaultSpiContainer INSTANCE = new DefaultSpiContainer();
    }

    public static DefaultSpiContainer getInstance() {
        return DefaultSpiContainerInstance.INSTANCE;
    }

    @Override
    public Set<Map.Entry<Class<? extends IBaseSpi>, List<IBaseSpi>>> getAllSpiImpl() {
        return Collections.unmodifiableSet(container.entrySet());
    }

    @Override
    public List<? extends IBaseSpi> lookup(Class<? extends IBaseSpi> spiClass) {
        return container.get(spiClass);
    }

    @Override
    public void addToContainer(Class<? extends IBaseSpi> spiClass, IBaseSpi spiImpl) {
        synchronized (container) {
            List<IBaseSpi> spiImplInstances = getRegisteredSpiImplInstances(spiClass);

            if (spiImplInstances.contains(spiImpl)) {
                return;
            }
            spiImplInstances.add(spiImpl);
            /*
             * sort spi interface implementations,sort ascending
             */
            spiImplInstances.sort(Comparator.comparingInt(o -> o.config(null).getPriority()));
            /*
             * put into spi map container
             */
            List<IBaseSpi> unmodifiableInstances = Collections.unmodifiableList(spiImplInstances);
            /*
             * log spi register info
             */
            loggerSpiRegister(spiClass, unmodifiableInstances);
            /*
             * put into container
             */
            container.put(spiClass, unmodifiableInstances);
        }
    }

    private List<IBaseSpi> getRegisteredSpiImplInstances(Class<? extends IBaseSpi> spiClass) {
        if (container.get(spiClass) == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(container.get(spiClass));
    }

    private void loggerSpiRegister(Class spiClass, List<IBaseSpi> spiImplList) {
        for (IBaseSpi spiImpl : spiImplList) {
            logger.info("biz-spi-impl-registered :spi interface:[{}] ,spi implement:[{}] ,{}",
                    spiClass.getName(),
                    spiImpl.getClass().getName(),
                    spiImpl.config(null).toString());
        }
    }
}
