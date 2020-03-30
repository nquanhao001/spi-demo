package com.niugege.spi.spring;

import com.niugege.spi.annotation.SpiFunctionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.util.Set;

/**
 * bean register util
 */
public final class BeanRegisterUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanRegisterUtil.class);

    /**
     * register SpiSpringApplicationListener bean
     *
     * @param registry the registry to register with
     */
    public static void registerSpiApplicationListenerBeanDefinition(BeanDefinitionRegistry registry) {
        String beanName = SpiSpringApplicationListener.BEAN_NAME;
        GenericBeanDefinition beanDef = new GenericBeanDefinition();
        beanDef.setBeanClass(SpiSpringApplicationListener.class);
        registry.registerBeanDefinition(beanName, beanDef);
        LOGGER.info("biz-spi-bean registered :Bean '{}' of type [{}]", beanName, SpiSpringApplicationListener.class.getName());
    }

    /**
     * register beans into the given registry(bean factory).
     *
     * @param beanDefinitions the bean definitions
     * @param registry        the registry to register with
     */
    public static void registerBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions, BeanDefinitionRegistry registry) {
        if (null == beanDefinitions || beanDefinitions.isEmpty()) {
            return;
        }
        for (BeanDefinitionHolder beanDefinition : beanDefinitions) {
            BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinition, registry);
            LOGGER.info("biz-spi-bean registered :Bean '{}' of type [{}] ,spi interface:[{}]",
                    beanDefinition.getBeanName(),
                    beanDefinition.getBeanDefinition().getBeanClassName(),
                    beanDefinition.getBeanDefinition().getConstructorArgumentValues().getGenericArgumentValues().get(0).getValue());
        }
    }

    /**
     * create a bean which bean property:beanClass is "SpiFactoryBean.class"
     * and property:spiFunctionPointClass is "spiClass"
     *
     * @param spiClass the spi interface class
     * @return the bean definition holder
     */
    public static BeanDefinitionHolder createSpiFactoryBeanBeanDefinitionHolder(Class<?> spiClass) {
        GenericBeanDefinition beanDef = new GenericBeanDefinition();
        beanDef.setBeanClass(SpiFactoryBean.class);
        beanDef.getConstructorArgumentValues()
                .addGenericArgumentValue(spiClass);
        String beanName = generateSpiFactoryBeanName(spiClass);
        LOGGER.info("biz-spi-bean created :Bean '{}' of type [{}] for spi interface:[{}]",
                beanName, SpiFactoryBean.class.getName(), spiClass.getName());
        return new BeanDefinitionHolder(beanDef, beanName);
    }

    /**
     * generate SpiFactory bean name .
     * if {@link SpiFunctionPoint#spiBeanName()} present and use it,
     * otherwise generate bean de capitalize the spi interface class name
     *
     * @param spiClass the spi interface class
     * @return the bean name
     */
    private static String generateSpiFactoryBeanName(Class<?> spiClass) {
        SpiFunctionPoint sfpAnn = spiClass.getAnnotation(SpiFunctionPoint.class);
        if (sfpAnn != null && StringUtils.hasText(sfpAnn.spiBeanName())) {
            return sfpAnn.spiBeanName();
        }
        return BeanRegisterUtil.generateDefaultBeanName(spiClass);
    }

    /**
     * de capitalize interface class as bean name
     *
     * @param clazz the spi interface class
     * @return the bean name
     */
    private static String generateDefaultBeanName(Class<?> clazz) {
        final String shortClassName = ClassUtils.getShortName(clazz);
        return Introspector.decapitalize(shortClassName);
    }

}
