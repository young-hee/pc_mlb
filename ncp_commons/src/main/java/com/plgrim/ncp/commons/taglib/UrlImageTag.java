/* Copyright (c) 2013 plgrim, Inc.
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
 *                       
 */
package com.plgrim.ncp.commons.taglib;

import com.plgrim.ncp.framework.commons.ConfigService;
import com.plgrim.ncp.framework.commons.StringService;

/**
 * JSP Page Option Custom Tag Lib
 * @author Park
 */

public class UrlImageTag extends UrlTag {
	private String mallId;
	/**
	 * UID
	 */
    private static final long serialVersionUID = 6909370419303809488L;

    public void setMallId(String mallId) {
    	this.mallId = mallId;
    }

	@Override
	String getDefaultRootURI() {
		if (StringService.isEmpty(this.mallId)) {
			return CodeBean.urlImage;
		} else {
			ConfigService configService = getRequestContext().getWebApplicationContext().getBean(ConfigService.class);
			return configService.getProperty("ncp_web_bo.cdn." + mallId + ".image.url");
		}
	}

}
