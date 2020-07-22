/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * beyondj2ee			2015.02.09        
 */
package com.plgrim.ncp.base.abstracts;

import java.util.Map;

import com.plgrim.ncp.framework.commons.JsonService;

	/**
	 * The Class AbstractService.
	 */
	public abstract class AbstractService extends AbstractBean {
	// ~ Instance fields. ~~~~~~~~~~~~~~
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	// ~ Implementation Method. ~~~~~~~~
	// ~ Public Methods. ~~~~~~~~~~~~~~~
	// ~ Private Methods. ~~~~~~~~~~~~~~
	// ~ Getter and Setter. ~~~~~~~~~~~~
	 /**
     * Object(DTO, Entity) 객체를 Map으로 변경.
     *
     * @param obj [설명]
     * @return Map [설명]
     * @throws Exception the exception
     * @since 2015. 6. 9
     */
    @SuppressWarnings("unchecked")
	public Map<String, Object> makeAfterMap(Object obj) throws Exception {
        String str = JsonService.marshallingJsonWithPretty(obj);
        Map<String, Object> returnMap = JsonService.unmarshalling(str, Map.class);

        return returnMap;
    }
}
