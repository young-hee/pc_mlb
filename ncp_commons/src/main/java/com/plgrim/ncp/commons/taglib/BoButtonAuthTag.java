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

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.plgrim.ncp.commons.auth.BOAuthenticationProvider;
import com.plgrim.ncp.commons.util.BOSecurityUtil;


/**
 * 운영자화면(Back office) 버튼를 권한별로 보여줄지 안보여줄지를 체크하는 커스텀 태그이다. 
 * 참고 : tutorial/sample/boAuthGuide 
 * @author Tam
 */
@Slf4j
public class BoButtonAuthTag extends RequestContextAwareTag { 

	/**
	 * UID
	 */
    private static final long serialVersionUID = 1;
	
    
	private String url;
	
	private String auth;
	
	private String display;

	private String menuSn;
	
	private RequestContext requestContext;
	
	public int doStartTagInternal() throws Exception{

		//String lang = LocaleService.getCurrentLang((HttpServletRequest)pageContext.getRequest()).toString();
		
		boolean result =  false;
		
		if( url != null && !"".equals(url)){
			result = checkAuthUrl(url , auth);
		}else if ( menuSn != null && !"".equals(menuSn)){
			result = checkAuthMenuSn(menuSn,auth);
		}else{
			this.requestContext = (RequestContext) this.pageContext.getAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE);
			url = this.requestContext.getRequestUri();
			result = checkAuthUrl(url , auth);
		}
		
		log.debug("{}||{}||{}||{}||결과={}", url, menuSn, auth, display, result);
		
		outPrint( result );
		
		return EVAL_PAGE;
	}
	
	/**
	 * 화면 출력
	 * @param isCheck
	 * @throws Exception
	 */
	public void outPrint( boolean isCheck) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		
		if( display != null && "false".equals(display) ){
			if( isCheck ){
				sb.append("true");
			}else{
				sb.append("false");
			}
		}else{
			if( isCheck ){
				sb.append(" style='display:;'");
			}else{
				sb.append(" style='display:none;'");
			}
		}
		
		pageContext.getOut().print(sb.toString());
		
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 *C (등록)  or V (보기) or U (수정) or D (삭제) or M  (마스킹) or E (excel downlaod)
	 * @param auth
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	/**
	 * display용이면 true, 아니면 false
	 * @param display
	 */
	public void setDisplay(String display) {
		this.display = display;
	}
	
	/**
	 * 메뉴코드로 권한(기능) 체크시 사용
	 * @param menuSn
	 */
	public void setMenuSn(String menuSn) {
		this.menuSn = menuSn;
	}
	
	
	/**
	 * 접근 URL로 권한체크
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private boolean checkAuthUrl( String url , String auth) throws Exception{
		
		return BOSecurityUtil.hasAccessUrl(url, auth);
	}
	
	/**
	 * 접근 프로그램URL 코드(메뉴코드) 권한체크
	 * @param menuSn
	 * @param auth
	 * @return
	 * @throws Exception
	 */
	private boolean checkAuthMenuSn( String menuSn , String auth) throws Exception{
		
		return BOSecurityUtil.hasAccessMenu(menuSn, auth);
	}

}
