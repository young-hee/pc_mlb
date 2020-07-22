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


/**
 * JSP Page Option Custom Tag Lib
 * @author Park
 */

public class UrlCpsSecureRootTag extends UrlTag {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 6909370419303809488L;

	@Override
    String getDefaultRootURI() {
		boolean isWeb = false;
        String rtnPc = null;
        String rtnMb = null;

		if(device == null){
			isWeb = "PC".equals(CodeBean.currentDevice);
		}
		else{
			isWeb = "PC".equals(device);
		}
        String test = getRequestContext().getLocale().getLanguage();
        switch(getRequestContext().getLocale().getLanguage()) {
//            case "zh" : rtnPc = CodeBean.urlCnSRootPc; rtnMb = CodeBean.urlCnSRootMb; break;
//            case "en" : rtnPc = CodeBean.urlEnSRootPc; rtnMb = CodeBean.urlEnSRootMb; break;
            default : rtnPc = CodeBean.urlSRootPc; rtnMb = CodeBean.urlSRootMb; break;
        }
        return isWeb? rtnPc : rtnMb;
    }
}
