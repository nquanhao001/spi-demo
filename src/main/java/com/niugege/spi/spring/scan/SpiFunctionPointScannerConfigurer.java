package com.niugege.spi.spring.scan;

import com.niugege.spi.annotation.SpiFunctionPoint;
import com.niugege.spi.spring.BeanRegisterUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 扫描{@linkplain SpiFunctionPoint}的bean方式配置，"basePackages"为扫描包路径，多个包路径支持",;"分割
 * </p>
 *
 * <b>配置使用示例：</b>
 *
 * <pre class="code">
 * {@code
 *  <!-- xml文件配置方式 -->
 *  <bean class="com.ggj.ecommerce.bizspi.spring.scan.SpiFunctionPointScannerConfigurer">
 *      <property name="basePackages" value="com.ggj.ecommerce.bizspi.sample"/>
 *  </bean>
 * }
 * 或者代码配置方式
 *
 * &#064;Bean
 * public SpiFunctionPointScannerConfigurer spiFunctionPointScannerConfigurer() {
 *      SpiFunctionPointScannerConfigurer configurer = new SpiFunctionPointScannerConfigurer();
 *      configurer.setBasePackages("com.ggj.ecommerce.bizspi.sample");
 *      return configurer;
 * }
 * </pre>
 *
 */
public class SpiFunctionPointScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

    private static final String SCAN_PACKAGES_PATH_DELIMITERS = ",;";

    /**
     * 扫描包路径，多个包路径支持",;"分割
     */
    private String basePackages;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // left intentionally blank
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanRegisterUtil.registerSpiApplicationListenerBeanDefinition(registry);
        SpiFunctionPointScanner functionPointScanner = new SpiFunctionPointScanner(registry);
        functionPointScanner.scan(tokenizeBasePackages());
    }

    private String[] tokenizeBasePackages() {
        return StringUtils.tokenizeToStringArray(basePackages, SCAN_PACKAGES_PATH_DELIMITERS);
    }

    public void setBasePackages(String basePackages) {
        this.basePackages = basePackages;
    }

}
