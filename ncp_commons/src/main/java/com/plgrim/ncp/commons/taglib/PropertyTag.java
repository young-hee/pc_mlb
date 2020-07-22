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

import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * JSP Page Option Custom Tag Lib
 * 
 * @author Park
 */

public class PropertyTag extends RequestContextAwareTag {
	private String key;
	private String var;
	private static Environment env;

	/**
	 * UID
	 */
	private static final long serialVersionUID = 6909370419303809488L;

	public void setKey(String key) {
		this.key = key;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		if (env == null) {
			WebApplicationContext appContext = WebApplicationContextUtils
					.getWebApplicationContext(this.pageContext.getServletContext());
			env = appContext.getEnvironment();
		}
		String val = env.getProperty(key);
		if (var != null) {
			pageContext.setAttribute(var, val);
		} else {
			pageContext.getOut().print(val);
		}
		return EVAL_PAGE;
	}
}
