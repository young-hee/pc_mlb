package com.plgrim.ncp.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation for fields requiring masking
 *
 * Masking Annotation Sample
 *  Bank(Card Number or  : @MaskingFormat(regexPattern = "^(.*)(\\d{3})(\\d{3})$", replacePattern = "$1***$3")
 *  Birthday : @MaskingFormat(regexPattern = "^(\\d{2,4})(-?)(\\d{2})(-?)(\\d{2})$", replacePattern = "$1$2**$4$5")
 *  Phone : @MaskingFormat(regexPattern = "^(.*)", replacePattern = "****")
 *  Phone : @MaskingFormat(regexPattern = "(\d{3})-(\d{3}||\d{4})-(\d{4})", replacePattern = "$1-****-$3")
 *  Address : @MaskingFormat(regexPattern = "^(.*)", replacePattern = "****************")
 * 
 * @since 1.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MaskingFormat {
    String regexPattern() default "^(.*)";
    String replacePattern() default "$1";
}