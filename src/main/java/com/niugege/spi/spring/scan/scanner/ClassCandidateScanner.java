package com.niugege.spi.spring.scan.scanner;

import java.util.Set;

/**
 * the ClassCandidateScanner interface definition
 */
public interface ClassCandidateScanner {

    /**
     * scan the specified packages classes which match annotation
     *
     * @param annotation   the class annotation to match.
     * @param scanPackages the packages
     * @return the matched class
     */
    Set<Class<?>> scan(Class<?> annotation, String... scanPackages);
}
