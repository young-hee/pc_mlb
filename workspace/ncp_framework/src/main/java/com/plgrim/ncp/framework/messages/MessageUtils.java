package com.plgrim.ncp.framework.messages;

import java.util.Locale;

import lombok.Data;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Data
@Component
public class MessageUtils implements InitializingBean {
	@Autowired
	MessageSource messageSource;

	Locale locale = Locale.KOREAN;

	static MessageUtils instance;

	public static String getMessage(String messageCode, String defaultMessage) {
		return instance == null? defaultMessage: instance.messageSource.getMessage(messageCode, null, defaultMessage, instance.locale);
	}

	public static String getMessage(String messageCode, String defaultMessage, Object... args) {
		return instance == null? defaultMessage: instance.messageSource.getMessage(messageCode, args, defaultMessage, instance.locale);
	}

	@SuppressFBWarnings(value = "ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD", justification = "싱글턴 객체를 초기화 하여 사용하기 위함")
	@Override
	public void afterPropertiesSet() throws Exception {
		MessageUtils.instance = this; 
	}

}
