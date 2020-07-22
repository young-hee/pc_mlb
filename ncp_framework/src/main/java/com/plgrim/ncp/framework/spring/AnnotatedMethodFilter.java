package com.plgrim.ncp.framework.spring;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * TypeFilter to find classes based on annotations on
 * {@link java.lang.reflect.Method}s.
 */
public class AnnotatedMethodFilter implements TypeFilter {

	private final Class<? extends Annotation> annotatedClass;

	/**
	 * Create filter for classes with {@link java.lang.reflect.Method}s
	 * annotated with specified annotation.
	 *
	 * @param annotatedClass
	 *            The annotated Class
	 */
	public AnnotatedMethodFilter(Class<? extends Annotation> annotatedClass) {
		this.annotatedClass = annotatedClass;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		Set<MethodMetadata> annotatedMethods = annotationMetadata
				.getAnnotatedMethods(annotatedClass.getCanonicalName());
		return !annotatedMethods.isEmpty();
	}

}
