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

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.tags.EscapeBodyTag;

/**
 * 프런트 전용 커스텀 태그
 * <ncp:auth url="/secured/temp?foo=xxxx&bar=yyyyy">인증이 필요한 페이지 </ncp:auth>
 * 선언 하면 인증이 될 경우 원래 url로 <a href>를 생성한다.
 * 그렇지 않은 경우 팝업 전용 로그인 페이지로 이동 한다.
 * BO에서는 절대로 사용해서는 안된다.
 * @author Park
 */
public class AuthTag extends EscapeBodyTag {

	static final String POPUP_LOGIN_URL = "/public/member/login.popup";
	
	/**
	 * UID
	 */
    private static final long serialVersionUID = 1;
	private String url;
	private String className;

	@Override
	public int doAfterBody() throws JspException {
		try {

			RequestContext context = getRequestContext();
			logger.debug(CodeBean.urlSRootPc);
			
			String linkURL = context.getContextUrl(url);
			String popupURL = context.getContextUrl(POPUP_LOGIN_URL);

			String body = this.readBodyContent();
			StringBuffer content = new StringBuffer();
			String classNameStr = "";
			if(StringUtils.isNotEmpty(className)){
				classNameStr = "class='"+className+"'";
			}
			content.append("<a href='" + linkURL + "' "+classNameStr+">");
			content.append(body);
			content.append("</a>");

			this.writeBodyContent(content.toString());
			return 0;
		} catch (IOException var2) {
			throw new JspException("Could not write escaped body", var2);
		}
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
