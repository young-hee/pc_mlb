package com.plgrim.ncp.framework.alarm;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Profile({ "default", "local", "dev", "stg", "stg2" })
public class DefaultAlarmService implements AlarmService {
	@Override
	public void alarm(String message) {
		log.warn(message);
	}

}
