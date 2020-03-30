package com.niugege.spi.spring.scan.scanner;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 */
public abstract class AbstractClassCandidateScanner implements ClassCandidateScanner {
    /**
     * the ClassLoader , could be nullable
     */
    private List<ClassLoader> classLoaders;
    /**
     * the ClassTypeFilter , could be nullable
     */
    private List<ClassTypeFilter> classTypeFilters;

    /**
     * add ClassLoader
     *
     * @param classLoader the ClassLoader
     */
    public void addClassLoader(ClassLoader classLoader) {
        if (classLoaders == null) {
            classLoaders = new ArrayList<>();
        }
        if (classLoader != null) {
            classLoaders.add(classLoader);
        }
    }

    /**
     * add ClassTypeFilter
     *
     * @param classMatchFilter the ClassTypeFilter
     */
    public void addClassTypeFilter(ClassTypeFilter classMatchFilter) {
        if (classTypeFilters == null) {
            classTypeFilters = new ArrayList<>();
        }
        if (classMatchFilter != null) {
            classTypeFilters.add(classMatchFilter);
        }
    }

    /**
     * get all classLoaders
     *
     * @return the all classLoaders
     */
    public List<ClassLoader> getClassLoaders() {
        return classLoaders;
    }

    /**
     * get all classTypeFilters
     *
     * @return the all classTypeFilters
     */
    public List<ClassTypeFilter> getClassTypeFilters() {
        return classTypeFilters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Class<?>> scan(Class<?> annotation, String... scanPackages) {
        List<Class> scannedClasses = doScan(annotation, scanPackages);
        if (scannedClasses.isEmpty()) {
            return null;
        }
        Set<Class<?>> matchedClasses = new LinkedHashSet<>();
        for (Class scannedClass : scannedClasses) {
            if (filter(scannedClass)) {
                matchedClasses.add(scannedClass);
            }
        }
        return matchedClasses;
    }

    /**
     * check the class whether filter all classTypeFilters
     *
     * @param clazz the class
     * @return true if filter, otherwise false
     */
    private boolean filter(Class<?> clazz) {
        if (CollectionUtils.isEmpty(classTypeFilters)) {
            return true;
        }
        return classTypeFilters.stream().allMatch(filter -> filter.match(clazz));
    }

    protected abstract List<Class> doScan(Class<?> annotation, String... scanPackages);
}
