package com.plgrim.ncp.framework.systems;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 어플리케이션의 구성에 관련된 정보를 보관한다.
 *
 */
@Component
@Getter
@Setter
@Slf4j
public class SystemContext implements InitializingBean {

	@Autowired
	Environment env;

	@Autowired
	SystemClock clock;

	static SystemContext instance;

	/**
	 * 현재 실행 되고 있는 어플리케이션이 PC, MB 등 어떤것인가?
	 */
	ApplicationType appType = ApplicationType.UNKNOWN;

	/**
	 * 현재 실행되고 있는 서버의 스테이지는 운영인가? 개발서버인가?
	 */
	Stage stage = Stage.LOCAL;

	/**
	 * 현재 실행되고 있는 WAS의 인스턴스는 어떤 것인가?
	 */
	WasInstance wasInstance = WasInstance.M1;

	AtomicLong seq = new AtomicLong(0);

	Lane lane = Lane.LANE1;

	static ThreadLocal<Language> currentRequestlanguageLocal = new ThreadLocal<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		appType = ApplicationType.decideApplicationType(env);
		stage = Stage.decideStage(env);
		wasInstance = WasInstance.decideWasInstance(env);
		seq = new AtomicLong(0);
		instance = this;
		lane = Lane.decideLane(stage, wasInstance);

		log.info("System Context appType:{} stage:{} wasInstance:{}", appType, stage, wasInstance);
	}

	public String nextRequestId() {
		return appType.getSymbol() + wasInstance.getSymbol() + nextUid();
	}

	public String nextUid() {
		return (new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date())) + (Math.abs(seq.getAndIncrement() % 10));
	}

	public static String generateRequestId() {
		return instance.nextRequestId();
	}

	public void setCurrentRequestLanguage(Language language) {
		currentRequestlanguageLocal.set(language);
	}

	public Language getCurrentRequestLanguage() {
		return currentRequestlanguageLocal.get();
	}
}
