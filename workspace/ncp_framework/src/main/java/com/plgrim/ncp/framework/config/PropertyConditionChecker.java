package com.plgrim.ncp.framework.config;

import java.util.Map;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import lombok.extern.slf4j.Slf4j;

/**
 * Decide whether System.property is enable or disable
 * 
 * @author charles <charles@plgrim.com>
 *
 */
@Slf4j
public class PropertyConditionChecker implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		if (log.isDebugEnabled()) {
			log.debug("context: {}, metadata: {}", context, metadata);
		}
		
		Map<String, Object> attributes = metadata.getAnnotationAttributes(PropertyCondition.class.getName());
        Boolean val = (Boolean)attributes.get("expectCondition");
        
        boolean returnValue = check(context, attributes);
        
        return val ? returnValue : !returnValue;
	}
	
	private boolean check(ConditionContext context, Map<String, Object> attributes) {
        String propName = (String)attributes.get("value");
                
        if (propName == null || propName.length() == 0) {
            log.warn("propName is empty");
            return false;
        }
        
        String value = System.getProperty(propName);
		if (value == null) {
			log.info("Value doesn't exist in System.getProperty for propName[{}]", propName);
			value = context.getEnvironment().getProperty(propName);
			if (value == null) {
				log.info("Value doesn't exist in Environment for propName[{}]. propName's check return false", propName);
				return false;
			}
		}
		
		value = value.trim().toLowerCase();
		
		if (0 == value.length() || "false".equals(value)) {
			return false;
		}
		
		return true;
	}
}
