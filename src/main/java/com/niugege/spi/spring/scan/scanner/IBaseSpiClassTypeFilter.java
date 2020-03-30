package com.niugege.spi.spring.scan.scanner;

import com.niugege.spi.api.IBaseSpi;

/**
 */
public class IBaseSpiClassTypeFilter implements ClassTypeFilter {

    @Override
    public boolean match(Class<?> clazz) {
        return IBaseSpi.class.isAssignableFrom(clazz);
    }
}
