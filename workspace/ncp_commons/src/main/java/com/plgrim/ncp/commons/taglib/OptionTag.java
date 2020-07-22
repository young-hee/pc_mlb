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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.plgrim.ncp.base.enums.SysInfoEnum;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.commons.result.CodeViewResult;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;

/**
 * JSP Page Option Custom Tag Lib
 * @author Park
 */

@Slf4j
public class OptionTag extends RequestContextAwareTag {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 6909370419303809488L;
    
	/**
	 * Code Group Key
	 */
	private String optionDataKey;

	/**
	 * Default Value
	 */
	private String defaultValue;
	
	public int doStartTagInternal() throws Exception{
    		IdGenService idGenService = new IdGenService();
    		CommonSelectComponent commonSelectComponent = ContextService.getBean(this.getRequestContext(), CommonSelectComponent.class);
    		SystemPK systemPK = idGenService.generateSystemPK(SysInfoEnum.MallIdEnum.DXM_MALL_ID.toString(), "PC", (HttpServletRequest) this.pageContext.getRequest());
    		List<CodeViewResult> list = commonSelectComponent.getCodeList(systemPK, optionDataKey);
    		String cd = null;
    		String cdNm = null;
    		
	        for(int i =0; list != null && i < list.size(); i++) {
	        	cd =  StringService.trimToEmpty(list.get(i).getCd());
	        	cdNm = StringService.trimToEmpty(list.get(i).getCdNm());
	        	
	        	if (defaultValue != null && defaultValue.equals(list.get(i).getCd())) {
		        	pageContext.getOut().println("<option value='"+ cd +"' selected>" + cdNm + "</option>");
	        	}
	        	else {
		        	pageContext.getOut().println("<option value='" + cd + "'>" + cdNm + "</option>");
	        	}
	        }

		return EVAL_PAGE;
	}
	
	
	/**
	 * @return the optionDataKey
	 */
	public String getOptionDataKey() {
		return optionDataKey;
	}

	/**
	 * @param optionDataKey the optionDataKey to set
	 */
	public void setOptionDataKey(String optionDataKey) {
		this.optionDataKey = optionDataKey;
	}


	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}


	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	

}
