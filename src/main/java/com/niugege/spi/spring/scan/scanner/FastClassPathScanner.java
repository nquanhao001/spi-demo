package com.niugege.spi.spring.scan.scanner;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * fast-class-path-scanner implementations
 */
public class FastClassPathScanner extends AbstractClassCandidateScanner {

    @Override
    protected List<Class> doScan(Class<?> annotation, String... scanPackages) {
        FastClasspathScanner fastClasspathScanner = new FastClasspathScanner(scanPackages);

        List<ClassLoader> classLoaders = getClassLoaders();

        List<Class> matchClassList = new ArrayList<>();
        /*
         * if specify ClassLoaders use the specific ClassLoaders.
         *
         * otherwise,Bridging classloading across multiple different classloaders
         *
         * link: https://github.com/lukehutch/fast-classpath-scanner/wiki#alternatives
         */
        if (!CollectionUtils.isEmpty(classLoaders)) {
            classLoaders.forEach(fastClasspathScanner::addClassLoader);
        } else {
            fastClasspathScanner.createClassLoaderForMatchingClasses();
        }

        fastClasspathScanner.matchClassesWithAnnotation(annotation, matchClassList::add).scan();

        return matchClassList;

    }
}
