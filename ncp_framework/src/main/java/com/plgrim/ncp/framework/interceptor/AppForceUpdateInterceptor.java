package com.plgrim.ncp.framework.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plgrim.ncp.framework.commons.StringService;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.plgrim.ncp.framework.systems.Stage;
import com.plgrim.ncp.framework.systems.SystemContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 강제 업데이트 대상 앱에서 요청이 오면, 업데이트 사이트로 이동시킨다.
 * 
 * @author narusas
 */
@Slf4j
@Component
public class AppForceUpdateInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	SystemContext systemContext;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			if (isNotRequireUpgrade(request)) {
				return true;
			}

			sendToAppForceUpdate(response);
			return false;
		} catch (Exception e) {
			// 버전 체크 로직에서 발생한 오류가 어플리케이션 진입을 막으면 안됨. 로그만 기록하고 진행시킴
			log.warn("", e);
			return true;
		}
	}

	boolean isNotRequireUpgrade(HttpServletRequest request) {
		return checkStage() || checkURI(request) || checkIsApp(request) || checkAppSpec(request);
	}

	void sendToAppForceUpdate(HttpServletResponse response) throws IOException {
		log.info("APP_UPDATE_REQUIRED");
		response.sendRedirect("/appForceUpdate");
	}

	boolean checkStage() {
		return Stage.LOCAL.equals(systemContext.getStage());
	}

	boolean checkURI(HttpServletRequest request) {
		return request.getRequestURI() != null && request.getRequestURI().contains("appForceUpdate");
	}

	boolean checkIsApp(HttpServletRequest request) {
		return StringUtils.isEmpty(request.getHeader("User-Agent"))
				|| request.getHeader("User-Agent").contains("Ver.") == false;
	}

	boolean checkAppSpec(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		String appVersion = getAppVersion(userAgent);

		if (isMatch(userAgent, appVersion)){
			return false;
		}

		return true;
	}

	String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

	String getAppVersion(String userAgent) {
		int appVersionIndex = userAgent.indexOf("Ver.");
		return userAgent.substring(appVersionIndex + 4, appVersionIndex + 7);
	}

	/**
	 * DynamicConfiguration 설정으로 인한 수정
	 *
	 * @param userAgent
	 * @param appVersion
	 * @return
	 */
	private boolean isMatch(String userAgent, String appVersion) {
		DynamicStringProperty appType = DynamicPropertyFactory.getInstance().getStringProperty("app_force_update_app_types", "");

		String appTypeValue = appType.getValue();
		if (StringService.isNotEmpty(appTypeValue)) {
			String[] appTypes = appTypeValue.split("\\,");

			for (String appName : appTypes) {
				if (userAgent.contains(appName) == false) {
					/* 앱정보가 없을 경우 */
					continue;
				}

				/* 설정된 appTypes 에 따라 Version 정보 조회 */
				DynamicStringProperty compareAppVersion = DynamicPropertyFactory.getInstance().getStringProperty("app_force_update_" + appName + "_version", "");
				String appVersionValue = compareAppVersion.getValue();

				if (StringService.isNotEmpty(appVersionValue)) {
					String[] arrVersion = appVersionValue.split("\\^");

					/* Version 정보(version,device 구조) 를 통해 userAgent 와 비교*/
					for (String version : arrVersion) {
						String[] requiredVersion = version.split("\\/");

						double parseVersion = Double.parseDouble(requiredVersion[0]);
						if (requiredVersion.length == 1 && Double.parseDouble(appVersion) == parseVersion) {
							return true;
						} else if (requiredVersion.length > 1) {
							String[] devices = requiredVersion[1].split(",");
							for (int i = 0; i < devices.length; i++) {
								if (userAgent.contains(devices[i]) && Double.parseDouble(appVersion) == parseVersion) {
									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

}
