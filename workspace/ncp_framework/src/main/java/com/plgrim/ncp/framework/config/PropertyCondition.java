package com.plgrim.ncp.framework.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

/**
 * bean load controller
 * <pre>
 * interface for Spring @Config Conditional check 
 * enable @Bean when true of property value in config 
 * </pre>
 * 
 * @author charles <charles@plgrim.com>
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Conditional(PropertyConditionChecker.class)
public @interface PropertyCondition {
    /**
     * prperty name of base.properties
     * 
     * @return return property name
     */
    public String value();
    
    /**
     * If set expectCondition to false, then enable @Bean when false of property value in base.properties
     * 
     * @retrun return given condition when define this attribute 
     */
    public boolean expectCondition() default true;

    /**
     * check existence of given property in base.properties
     * 
     * @return return true if exist
     */
    public boolean exists() default true;
}
