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

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.commons.util.CodeUtil.Code;
import com.plgrim.ncp.framework.commons.LocaleService;

/**
 * JSP Page Option Custom Tag Lib
 * @author Park
 */

public class CodeTag extends RequestContextAwareTag {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 1;
	/**
	 * Code Group Key
	 */
	private String code;
	private String var;

	public int doStartTagInternal() throws Exception{
		String lang = LocaleService.getCurrentLang((HttpServletRequest)pageContext.getRequest()).toString();
		Code cd = CodeUtil.getCode(lang, code);
		if(var != null && var.trim().length() > 0){
			pageContext.setAttribute(var, cd);
		}
		else{
			pageContext.getOut().println(cd==null?"":cd.getCdNm());
		}
		return EVAL_PAGE;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	public void setVar(String var){
		this.var = var;
	}
}
