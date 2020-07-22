package com.plgrim.ncp.framework.spring;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

public class AnnotatedMethodScanner {

	private final ClassLoader classLoader;
	private final ClassPathScanningCandidateComponentProvider provider;

	public AnnotatedMethodScanner() {
		classLoader = AnnotatedMethodScanner.class.getClassLoader();
		provider = new ClassPathScanningCandidateComponentProvider(false);
	}

	// package private for testing only
	AnnotatedMethodScanner(ClassLoader classLoader, ClassPathScanningCandidateComponentProvider provider) {
		this.classLoader = classLoader;
		this.provider = provider;
	}

	/**
	 * Find all methods on classes under scanBase that are annotated with
	 * annotationClass.
	 *
	 * @param scanBase
	 *            Package to scan recursively, in dot notation (ie:
	 *            org.jrugged...)
	 * @param annotationClass
	 *            Class of the annotation to search for
	 * @return Set<Method> The set of all @{java.lang.reflect.Method}s having
	 *         the annotation
	 */
	public Set<Method> findAnnotatedMethods(String scanBase, Class<? extends Annotation> annotationClass) {
		Set<BeanDefinition> filteredComponents = findCandidateBeans(scanBase, annotationClass);
		return extractAnnotatedMethods(filteredComponents, annotationClass);
	}

	Set<Method> extractAnnotatedMethods(Set<BeanDefinition> filteredComponents, Class<? extends Annotation> annoClass) {
		Set<Method> annotatedMethods = new HashSet<Method>();
		for (BeanDefinition bd : filteredComponents) {
			try {
				String className = bd.getBeanClassName();
				Class<?> beanClass = classLoader.loadClass(className);
				for (Method m : beanClass.getMethods()) {
					if (m.getAnnotation(annoClass) != null) {
						annotatedMethods.add(m);
					}
				}
			} catch (ClassNotFoundException cnfe) {
				// no-op
			}
		}

		return annotatedMethods;
	}

	synchronized Set<BeanDefinition> findCandidateBeans(String scanBase, Class<? extends Annotation> annotatedClass) {
		provider.resetFilters(false);
		provider.addIncludeFilter(new AnnotatedMethodFilter(annotatedClass));

		String basePackage = scanBase.replace('.', '/');
		return provider.findCandidateComponents(basePackage);
	}
}
