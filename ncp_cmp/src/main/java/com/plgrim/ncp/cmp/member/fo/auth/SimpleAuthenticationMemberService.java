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

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.base.enums.SysInfoEnum;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
public class SimpleAuthenticationMemberService extends AbstractComponent implements UserDetailsService {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    /**
     * 회원 서비스.
     */
	
	@Autowired
	MemberBaseSelectService memberBaseSelectService;

    @Autowired
	MessageSourceAccessor messageSourceCmpAccessor;
    
    @Autowired
    IdGenService idGenService;


    /**
     * spring security 인증
     */
    @Override
    public UserDetails loadUserByUsername(String userId) {
    	
    	

        HttpServletRequest request = ContextService.getCurrentRequest();
        SystemPK pk = idGenService.getAutoGeneratorSystemPK(request);
        //    	CsrfToken token = (CsrfToken)request.getAttribute("_csrf");
        //		String tokenStr = (String)token.getToken();
        HttpSession session = request.getSession();
        session.setAttribute(SessionEnum.SESSION_ID.toString(), session.getId());
        //        session.setAttribute("token", tokenStr);

        String serverName = request.getServerName();
        Locale locale = null ;

        if ( serverName.contains("en.") ) {
        	locale = Locale.ENGLISH;
        } else if ( serverName.contains("cn.") ) {
        	locale = Locale.CHINA;
        } else {
        	locale = Locale.KOREA;
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        SecurityUserDetail userDetail = null;

        MemberFoDTO dto = new MemberFoDTO();
        Mbr mbr = new Mbr();
        mbr.setMbrId(userId);
        dto.setMbr(mbr);

        try {

            String mallId = getIdGenService().getEnvValue(SysInfoEnum.MALL_ID_SYSTEM_VARIABLE_ID.toString());
            dto.setMallId(mallId);
            
            userDetail = memberBaseSelectService.selectSecuredMember(dto);

            if (userDetail != null) {

                userDetail.getMbr();
                if (userDetail.getMbr().getMbrLoginLastFailrCount() != null && userDetail.getMbr().getMbrLoginLastFailrCount() >= 5) {
                    //session.setAttribute(SessionEnum.FAIL_MSG.toString(), messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg", locale.getLanguage()));
                	if("DXM".equals(pk.getMall())){
                		session.setAttribute(SessionEnum.FAIL_MSG.toString(), messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg", locale.getLanguage()));
                	}else{
                		session.setAttribute(SessionEnum.FAIL_MSG.toString(), messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg2", locale.getLanguage()));
                	}
                    return null;
                }

                userDetail.setGrantedAuths(grantedAuths);
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return userDetail;
    }
}

