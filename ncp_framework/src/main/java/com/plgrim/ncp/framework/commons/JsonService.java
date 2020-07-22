/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 3       
 */
package com.plgrim.ncp.framework.commons;

import com.plgrim.ncp.framework.utils.JsonUtil;

/**
 * JSON 데이터를 처리하는 전용 서비스
 * 
 * <p>
 * 
 * <ul>
 * <li>Object to Json 마샬링
 * <li>Json to Object 언마샬링
 * </ul>.
 *
 * @author tktaeki.kim
 * @since 2015. 2. 25
 */
public class JsonService {

	/**
	 * Object를 Json 형태의 Text로 변환 한다.
	 * <p>
	 * 프레임웍에서 사용할 경우
	 * <p>
	 * User user = new User(); <br/>
	 * user.setId("001");<br/>
	 * 
	 * log.debug(getJsonService().marshalling(user));<br/>
	 * <p>
	 * {id:"001"}<br/>
	 *
	 * @param object 대상 오브젝트
	 * @return String - 변환된 Json 텍스트
	 * @since 2015. 2. 25
	 */
	public static String marshalling(final Object object) {

		return JsonUtil.marshallingJson(object);
	}

	/**
	 * Object를 Json 형태의 Text로 변환 하고 보기 편하게 변환 한다.
	 * 
	 * <p/>
	 * 
     * User user = new User(); <br/>
	 * user.setId("001");<br/>
	 * 
	 * log.debug(getJsonService().marshalling(user));<br/>
	 * <p>
	 * {<br/>
	 *    id:"001"<br/>
	 * }<br/>
	 *
     * @param object 대상 오브젝트
	 * @return String - 변환된 Json 텍스트
	 * @since 2015. 3. 3
	 */
	public static String marshallingJsonWithPretty(final Object object) {

		return JsonUtil.marshallingJsonWithPretty(object);
	}

	// marshallingJsonWithPretty

	/**
	 * Json 텍스트를 파싱 후 대상 오브젝트에 데이터 바인딩 후 생성.
	 * <p>
	 * 프레임웍에서 사용할 경우
	 * <p>
	 * String value = "{id:"001"}"; <br/>
	 * User user = getJsonService().unmarshalling(value, User.class) <br/>
	 * @param <T> the generic type
	 * @param jsonText Json 텍스트
	 * @param valueType 생성되는 오브젝트의 클래스 타입
	 * @return T [설명]
	 * @throws Exception the exception
	 * @since 2015. 2. 25
	 */
	public static <T> T unmarshalling(final String jsonText, final Class<T> valueType) {
		return JsonUtil.unmarshallingJson(jsonText, valueType); 
	}

}
