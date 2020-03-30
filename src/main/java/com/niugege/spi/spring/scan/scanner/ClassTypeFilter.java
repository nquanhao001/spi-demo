package com.niugege.spi.spring.scan.scanner;

/**
 * the ClassTypeFilter interface definition
 */
public interface ClassTypeFilter {

    /**
     * whether the class match filter.
     *
     * @param clazz the class.
     * @return true if match,otherwise false.
     */
    boolean match(Class<?> clazz);
}
