package com.niugege.spi.spring;

import com.niugege.spi.annotation.BizSpi;
import com.niugege.spi.core.container.SpiContainer;
import com.niugege.spi.core.container.SpiRegister;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * load annotated with @BizSpi beans and register it
 */
public class SpiSpringApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    static final String BEAN_NAME = "bizSpiSpringApplicationListener";

    private SpiContainer container = SpiContainer.DEFAULT_INSTANCE;

    @Override
    @SuppressWarnings("unchecked")
    public void onApplicationEvent(ContextRefreshedEvent event) {
        /*
         * get beans with annotation @BizSpi
         */
        Map<String, Object> spiBeanMap = event.getApplicationContext().getBeansWithAnnotation(BizSpi.class);
        /*
         * not any of spi beans found
         */
        if (CollectionUtils.isEmpty(spiBeanMap)) {
            return;
        }
        /*
         * register spi implementations
         */
        registerBizSpiBeans(spiBeanMap);
    }

    private void registerBizSpiBeans(Map<String, Object> spiBeanMap) {
        SpiRegister spiRegister = new SpiRegister();
        spiRegister.setContainer(container);
        spiRegister.setBizSpiBeans(spiBeanMap.values());
        spiRegister.register();
    }
}
