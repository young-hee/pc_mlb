package com.plgrim.ncp.commons.auth;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import com.plgrim.ncp.commons.AuthenticationComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.ConfigService;
import com.plgrim.ncp.framework.commons.IdGenService;

/**
 * 매번 REQUEST 마다 검증 작업을 한다.
 */
public class BOAccessDecisionVoter extends WebExpressionVoter {

	@Autowired
	ConfigService configService;
	
	@Autowired
	IdGenService idGenService;
	
	@Autowired
    AuthenticationComponent authenticationComponent;
	
    @Override
    public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> attributes) {

    	 //System.out.println("***검증(vote)시작***");
    	 String requestURL = fi.getRequest().getRequestURI();
          
        //만약 해당이 되지 않을 경우 실제 접근 제어를 한다.
        //intercept-url 정의된 권한 검증을 한다. 만약 AccessDecisionVoter.ACCESS_DENIED 경우 바로 리턴 한다.
        if (super.vote(authentication, fi, attributes) == AccessDecisionVoter.ACCESS_DENIED) {
        	//System.out.println("-> ACCESS_DENIED");
            return AccessDecisionVoter.ACCESS_DENIED;
        }
        
        
        //웹정적인 URL
        if( requestURL.indexOf("/js") == 0 || requestURL.indexOf("/images") ==0 
        		|| requestURL.indexOf("/font") ==0 || requestURL.indexOf("/plugin") ==0
        		|| requestURL.indexOf("/css") ==0 ||  requestURL.indexOf("favicon.ico") ==0 
        		|| requestURL.indexOf("/cp/login") ==0 ||  requestURL.equals("/") 
        ){
        	
        }else if (  //F/W 사용하는 공통 URL  및  application에서 공통으로 사용하는 URL 는 검증에서 제외한다. 
    		requestURL.indexOf("/excel") ==0
    		|| requestURL.indexOf("/common") ==0
    		|| requestURL.indexOf("/sc/popup/") ==0	
    		|| requestURL.indexOf("/login") ==0
    		|| requestURL.indexOf("/epsso") ==0
    		|| requestURL.indexOf("/dashboard") ==0
    		|| requestURL.indexOf("/grid") ==0
    		|| requestURL.indexOf("/access_denied") ==0
    		|| requestURL.indexOf("/not_founded") ==0
    		|| requestURL.indexOf("/errors/invalidSession") ==0
    		|| requestURL.indexOf("/errors/expiredSession") ==0
    		|| requestURL.indexOf("/errors/accessDeny") ==0
    		|| requestURL.indexOf("/errors/vulnerability") ==0
    		|| requestURL.indexOf("/errors/vulnerabilitySessionExpire") ==0
    		|| requestURL.indexOf("/errors/vulnerabilitySessionExpire") ==0
    		|| requestURL.indexOf("/menu/gnb") ==0
    		|| requestURL.indexOf("/sc/zipcode") ==0
    		|| requestURL.indexOf("/sc/bukmk") ==0   /*북마크*/
        ){
        }else{
	       /* 
	        * CS/BO REST URL 방식 존재 및 프로그램 URL 미입력 이슈로 개발편의상 주석처리함 ( URL access 권한 체크) 제외
	     
        	
	       for (Iterator iterator = authentication.getAuthorities().iterator(); iterator.hasNext();) {
	
	        	// UsernamePasswordAuthenticationToken
	            GrantedAuthority authority = (GrantedAuthority) iterator.next();
	            String role = authority.getAuthority();
	            if (role.equals("ROLE_ADM")) { //관리자만 접근 가능
	
	                //실제 비즈니스 권한 체크를 한다.
	            	if( !BOSecurityUtil.hasAccessUrl(requestURL) ){
	            		return AccessDecisionVoter.ACCESS_DENIED;
	            	}
	
	                return AccessDecisionVoter.ACCESS_GRANTED;
	            }
	        }
        	
	       */
    		try{
    			//프로글매 URL 접근 로그 생성
        		authenticationComponent.insertMenuUseLog(requestURL);
        	}catch(Exception e){
        		
        	}
	        
        }
        //System.out.println("***검증(vote)끝*** -> ACCESS_GRANTED");
        return AccessDecisionVoter.ACCESS_GRANTED;
        
    }
  
}
