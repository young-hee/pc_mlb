package com.plgrim.ncp.framework.responsecode.common;

import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * enum은 생성시기가 다르기 때문에 enum 생성자 안에서 static 필드에 접근할수가 없다. 따라서 해당 클래스 내부에서 중복 여부를 체크할수 없다 
 * @author narusas
 *
 */
@Component
public class CommonResponseCodeValidator implements InitializingBean {
	@Override
	public void afterPropertiesSet() {
		HashSet<String> messageCodes = new HashSet<>();
		CommonResponseCode[] codes = CommonResponseCode.values();
		for (CommonResponseCode bizErrorCode : codes) {
			if (messageCodes.contains(bizErrorCode.getCode())) {
				throw new IllegalStateException("중복된  ResponseCode 가 존재합니다." + bizErrorCode.getCode());
			}
			if (StringUtils.isEmpty(bizErrorCode.getDefaultMessage())) {
				throw new IllegalStateException(" 기본 메시지를 가지지 않은 ResponseCode 가 존재합니다." + bizErrorCode.name());
			}
			messageCodes.add(bizErrorCode.getCode());
		}

	}
}
