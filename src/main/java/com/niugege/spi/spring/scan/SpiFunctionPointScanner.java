package com.niugege.spi.spring.scan;

import com.niugege.spi.annotation.SpiFunctionPoint;
import com.niugege.spi.spring.BeanRegisterUtil;
import com.niugege.spi.spring.SpiFactoryBean;
import com.niugege.spi.spring.scan.scanner.AbstractClassCandidateScanner;
import com.niugege.spi.spring.scan.scanner.FastClassPathScanner;
import com.niugege.spi.spring.scan.scanner.IBaseSpiClassTypeFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * scan annotated with {@link SpiFunctionPoint} interfaces and register SpiFactoryBean
 *
 */
public class SpiFunctionPointScanner {

    private Logger LOGGER = LoggerFactory.getLogger(SpiFunctionPointScanner.class);
    /**
     * the ClassLoader , could be nullable
     */
    private ClassLoader classLoader;
    /**
     * the BeanDefinitionRegistry
     */
    private BeanDefinitionRegistry registry;

    SpiFunctionPointScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * scan packages and register {@linkplain SpiFactoryBean}
     *
     * @param scanPackages the scan packages
     */
    void scan(String... scanPackages) {
        /*
         * scan spi class and generate "SpiFactoryBean" bean definition holder
         * which annotated with @SpiFunctionPoint interfaces
         */
        Set<BeanDefinitionHolder> beanDefinitions = doScan(scanPackages);
        /*
         * register bean definition to the given registry
         */
        BeanRegisterUtil.registerBeanDefinitions(beanDefinitions, registry);
        /*
         * extension method if after modification
         */
        this.processBeanDefinitions(beanDefinitions);
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        //noop
    }

    private Set<BeanDefinitionHolder> doScan(String... scanPackages) {
        if (scanPackages == null || scanPackages.length <= 0) {
            LOGGER.warn("biz-spi-scan,Scan Packages,scanPackages is empty");
            return null;
        }
        LOGGER.info("biz-spi-scan,Scan Packages:{}", Arrays.toString(scanPackages));
        Set<Class<?>> spiClasses = scanSpiFunctionPointInterfaces(scanPackages);
        if (spiClasses == null || spiClasses.isEmpty()) {
            logScannedSpiInterfacesEmpty(scanPackages);
            return null;
        }
        logScannedSpiInterfaces(spiClasses);
        Set<BeanDefinitionHolder> beanDefHolders = new HashSet<>(spiClasses.size());
        for (Class<?> spiClass : spiClasses) {
            beanDefHolders.add(BeanRegisterUtil.createSpiFactoryBeanBeanDefinitionHolder(spiClass));
        }
        return beanDefHolders;
    }

    private Set<Class<?>> scanSpiFunctionPointInterfaces(String... scanPackages) {
        AbstractClassCandidateScanner fastClassPathScanner = new FastClassPathScanner();
        fastClassPathScanner.addClassTypeFilter(new IBaseSpiClassTypeFilter());
        fastClassPathScanner.addClassLoader(this.classLoader);
        return fastClassPathScanner.scan(SpiFunctionPoint.class, scanPackages);
    }

    private void logScannedSpiInterfaces(Set<Class<?>> spiClasses) {
        for (Class spiClass : spiClasses) {
            LOGGER.info("biz-spi-scan,Scanned annotated with @SpiFunctionPoint {}", spiClass);
        }
    }

    private void logScannedSpiInterfacesEmpty(String[] scanPackages) {
        LOGGER.warn("biz-spi-scan,Scanned packages {} not any interfaces which annotated with @SpiFunctionPoint present", Arrays.toString(scanPackages));
    }
}
