/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 20
 */
package com.plgrim.ncp.cmp.member.fo.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.exception.MemberFailERPIFException;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberInterfaceService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.security.SecuritySessionInitHandler;
import com.plgrim.ncp.framework.systems.Stage;
import com.plgrim.ncp.framework.systems.SystemContext;
import com.plgrim.ncp.interfaces.member.data.MemberInformationSDO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationMemberService extends AbstractComponent implements UserDetailsService, SecuritySessionInitHandler {



    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    /**
     * 프런트 (PC, MOBILE) 회원들에게 부여되는 권한.
     */
    final static String FRONT_ROLE_NAME = "ROLE_USER";

    @Autowired
    IdGenService idGenService;

    /**
     * 회원 서비스.
     */
    @Autowired
    MemberBaseSelectService memberBaseSelectService;

    @Autowired
    MemberBaseCommandService memberBaseCommandService;
    
    @Autowired
    MessageSourceAccessor messageSourceCmpAccessor;
    
    @Autowired
    MemberInterfaceService memberInterfaceService;

    @Autowired
    SystemContext systemContext;
    
    /**
     * spring security 인증
     */
    @Override
    public UserDetails loadUserByUsername(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new UsernameNotFoundException("Given user id is empty");
        }
        HttpServletRequest request = ContextService.getCurrentRequest();

        SystemPK pk = idGenService.getAutoGeneratorSystemPK(request);
        
        Locale locale = LocaleService.getCurrentLocale(request);

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> USERID :::::::::::::: " + userId);
        HttpSession session = request.getSession();
        String prvSessionId = (String) session.getAttribute(SessionEnum.SESSION_ID.toString());
        if (StringUtils.isEmpty(prvSessionId)) {
            session.setAttribute(SessionEnum.SESSION_ID.toString(), session.getId());
            log.info("AuthenticationMemberService sessionId " + session.getId());
        }

        SecurityUserDetail userDetail = null;

        MemberFoDTO dto = new MemberFoDTO();
        Mbr mbr = new Mbr();

        /**
         * 로그인 인증 되어진 상태의 경우
         * 저장된 userDetail 반환
         * 로그인 유지.
         * */
        try {
            if (ContextService.hasRole()) {
                userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
                
                if(userDetail.getMbr() != null && userDetail.getMbr().getMbrId() != null) {
                	mbr.setMbrId(userDetail.getMbr().getMbrId());
                }
                else {
                	mbr.setMbrId(userId);
                }
                
                dto.setMallId(pk.getMall());
                dto.setMbr(mbr);
                // 온라인 회원정보 조회
                userDetail = memberBaseSelectService.selectSecuredMember(dto);

                return userDetail;
            }
        } catch (Exception e) {
            // ignore
        }
        mbr.setMbrId(userId);
        dto.setMbr(mbr);
        try {
            if (StringService.isNotEmpty(userId)) {
                dto.setMallId(pk.getMall());
                
                /**
                 * 이벤트 테스트를 위해 운영이 아닌 서버에서 evttest로 시작하는 id는 ERP를 조회하지 않고 처리.
                 */
                if(!userId.startsWith("evttest")) {//systemContext.getStage() == Stage.PRODUCTION && 
	                // ERP 통합회원정보 조회
	                MemberInformationSDO memberInformationSDO = memberInterfaceService.getMemberInformation(pk, mbr);
	                
	                // 온라인 회원정보 조회
	                userDetail = memberBaseSelectService.selectSecuredMember(dto);
	                
	                // ERP에서 조회한 회원정보가 있으면
	                /*
	                 * ERP에 CI값이 없는 회원이 존재하여 CI값은 보지 않도록 처리.
	                 */
	//                if(memberInformationSDO.getCid() != null && memberInformationSDO.getIpinDi() != null && memberInformationSDO.getIpinCi() != null) {
	                if(memberInformationSDO.getCid() != null && memberInformationSDO.getIpinDi() != null && !"D".equals(memberInformationSDO.getStatus())) {
	                	if(userDetail.getMbr() == null) {
	                		userDetail.setMbr(mbr);
	                	}
	                	mbr = this.setMbrErpPw(userDetail.getMbr(), memberInformationSDO);
	                	// ERP에는 회원정보가 있지만 온라인에는 없는 경우 온라인에 회원정보를 등록하기 위해 세팅
	                    if(userDetail.getMbr().getMbrNo() == null || "".equals(userDetail.getMbr().getMbrNo())) {
	                    	// 로그인 성공 및 실패 후 삭제
	                    	session.setAttribute(SessionEnum.ONLINE_INSERT_FLAG.toString(), JsonService.marshalling(true));
	                    }
	                    
	                    userDetail.setMbr(mbr);
	                }
	                else {
	                	throw new UsernameNotFoundException("user not founded");
	                }
                
	                if (userDetail.getMbr() != null) {
	                	// 로그인 성공시 ERP에서 받아온 회원정보를 온라인으로 update하기 위해 세팅
	                	// 로그인 성공 및 실패 후 삭제
	                	session.setAttribute(SessionEnum.MEMBER_INFORMATION_SDO.toString(), memberInformationSDO);
	                	
	                    if (userDetail.getMbr().getMbrLoginLastFailrCount() != null && userDetail.getMbr().getMbrLoginLastFailrCount() >= 5) {	                    	
	                    	if("DXM".equals(pk.getMall())){
	                    		session.setAttribute(SessionEnum.FAIL_MSG.toString(), messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg", locale.getLanguage()));
	                    		userDetail.setUnLock(false);
	                    	}else{
	                    		session.setAttribute(SessionEnum.FAIL_MSG.toString(), messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg2", locale.getLanguage()));
	                    	}
	                        
	                        return userDetail;
	                    }
	                }
                }
                else {
	                // 온라인 회원정보 조회                	
	                userDetail = memberBaseSelectService.selectSecuredMember(dto);
                }
            }
        } catch (Exception e) {
        	if(e.getMessage()!= null && e.getMessage().contains("user not founded")) {
            	log.warn("{}", e.getMessage());            	
            }
            else {
            	log.error("{}", e);
            }
            if(e.getClass().getName().contains("MemberFailERPIFException")) {
            	String[] str = {};
            	throw new MemberFailERPIFException(str);
            }
            else {
            	throw new UsernameNotFoundException("user not founded");
            }
        }
        if (userDetail == null) {
            throw new UsernameNotFoundException("user not founded");
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(FRONT_ROLE_NAME));
        userDetail.setGrantedAuths(grantedAuths);

        if (userDetail.getMbr() != null) {
            return userDetail;
        } else {
            session.setAttribute(SessionEnum.FAIL_MSG.toString(), messageSourceCmpAccessor.getMessage("member.login.info.notmatched.msg", locale.getLanguage()));
            return new SecurityUserDetail();
        }

    }

    private Mbr setMbrErpPw(Mbr mbr, MemberInformationSDO memberInformationSDO) {
    	if(mbr != null && memberInformationSDO != null && memberInformationSDO.getCid() != null) {
    		// 온라인의 ERP패스워드가 없고 ERP의 패스워드가 있거나
    		// 온라인의 ERP패스워드와 ERP의 패스워드가 다르면
    		// ERP의 패스워드를 온라인의 ERP패스워드에 세팅한다.
    		if(memberInformationSDO.getPasswd() != null
    				&& (mbr.getMbrErpPw() == null || !mbr.getMbrErpPw().equals(memberInformationSDO.getPasswd()))
    				) {
    			mbr.setMbrErpPw(memberInformationSDO.getPasswd());
    		}
    	}
    	
    	return mbr;
    }

    @Override
    public void initSecuritySession(HttpServletRequest request, HttpServletResponse response, Authentication authentication, SessionRegistry sessionRegistry) {

        SecurityUserDetail detail = (SecurityUserDetail) authentication.getPrincipal();
        String currentUserId = StringService.trimToEmpty(detail.getMbr().getMbrId());
        String chkSaveId = (String) request.getParameter("chkSaveId");
        
        if (chkSaveId != null && chkSaveId.equals("on")) {
            Cookie cookie;
            try {
                cookie = new Cookie("UID", java.net.URLEncoder.encode(currentUserId, "UTF-8"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            cookie.setMaxAge(60 * 60 * 24 * 30);//한달
            cookie.setSecure(true);
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("UID", "");
            response.addCookie(cookie);
            cookie.setSecure(true);
        }

        Cookie cacheCookie = new Cookie("LOGIN_FLAG", "Y");
        cacheCookie.setMaxAge(-1);//브라우저 유효시간만
        cacheCookie.setSecure(true);
        response.addCookie(cacheCookie);
    }


}