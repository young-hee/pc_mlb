package com.plgrim.ncp.cmp.member.fo.auth;

import com.plgrim.ncp.base.abstracts.AbstractBean;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class CustomPasswordEncoder extends AbstractBean implements PasswordEncoder{

	 /**
     * 회원 서비스.
     */
    @Autowired
    MemberBaseSelectService memberBaseSelectService;
	
	@Autowired
	@Qualifier("commonSelectComponentImpl")
	CommonSelectComponent commonSelectComponent;
    
	@Override
    public String encode(CharSequence rawPassword) {
		String str = (String)rawPassword;
	    return str;
    }

	@Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
		boolean isMatches = false;
		String password = rawPassword.toString();
		try {
	        if(StringService.isNotEmpty(password)
					&& StringService.isNotEmpty(encodedPassword)
					&& (password.equals(encodedPassword)
						|| IdGenService.generateMD5(password).toLowerCase().equals(encodedPassword.toLowerCase())
						|| IdGenService.generateSHA256(IdGenService.generateMD5(password)).toLowerCase().equals(encodedPassword.toLowerCase())
						|| IdGenService.generateSHA256(password).toLowerCase().equals(encodedPassword.toLowerCase())) ){
	        	isMatches = true;
	        }

			/**
			 * AuthenticationMemberService 를 통해 인증된 사용자가 있을 경우
			 * 저장 된 사용자 정보를 반환하여
			 * 로그인 유지를 위해 true 설정
			 * */
			if(ContextService.hasRole()){
				isMatches = true;
			}
        }
        catch (Exception e) {
	        // TODO Auto-generated catch block
	        log.error("",e);
         }
	    return isMatches;
    }
	

}
