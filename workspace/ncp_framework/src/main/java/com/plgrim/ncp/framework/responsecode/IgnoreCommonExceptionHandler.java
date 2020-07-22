package com.plgrim.ncp.framework.responsecode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 프레임워크에서 제공되는 예외 처리 메커니즘( CommonExceptionHandler )을 건너 뛰고 싶을때 컨트롤러 메소드에 마킹할 어노테이션
 * 
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreCommonExceptionHandler { 

}
