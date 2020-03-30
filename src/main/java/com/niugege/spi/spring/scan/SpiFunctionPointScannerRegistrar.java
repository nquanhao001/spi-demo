package com.niugege.spi.spring.scan;

import com.niugege.spi.spring.BeanRegisterUtil;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpiFunctionPointScannerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        BeanRegisterUtil.registerSpiApplicationListenerBeanDefinition(registry);
        AnnotationAttributes attrs = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(SpiFunctionPointScan.class.getName()));
        SpiFunctionPointScanner spiFunctionPointScanner = new SpiFunctionPointScanner(registry);
        spiFunctionPointScanner.scan(getScanPackages(metadata, attrs));
    }

    private String[] getScanPackages(AnnotationMetadata importingClassMetadata, AnnotationAttributes attrs) {
        Set<String> basePackages = new LinkedHashSet<>();
        for (String pkg : attrs.getStringArray("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg.trim());
            }
        }
        for (String pkg : attrs.getStringArray("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg.trim());
            }
        }
        if (CollectionUtils.isEmpty(basePackages)) {
            basePackages.add(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }
        return StringUtils.toStringArray(basePackages);
    }
}
