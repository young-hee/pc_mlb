package com.plgrim.ncp.cmp.member.fo.auth;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginLog;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SecurityJsonResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.SecurityParam;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 인증이 실패 된 후 처리를 담당 한다.
 */
@Data
@Slf4j
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /* 로그인 실패시 이동하는 URL */
    String failURL;

    /* 로그인 실패시 이동하는 팝업 URL */
    String failPopupURL;
    
    String failMessage;
    
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
	@Qualifier("commonSelectComponentImpl")
	CommonSelectComponent commonSelectComponent;

	@Autowired
    IdGenService idGenService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        // Request 객체의 Attribute에 사용자가 실패시 입력했던 로그인 ID와 비밀번호를 저장해두어 로그인 페이지에서 이를 접근하도록 한다.
        String userId = StringService.trimToEmpty(request.getParameter(SecurityParam.USER_ID_INPUT_NAME.toString()));
        String password = StringService.trimToEmpty(request.getParameter(SecurityParam.PASSWORD_INPUT_NAME.toString()));
        String loginRedirect = StringService.trimToEmpty(request.getParameter(SecurityParam.TARGET_PARAM_NAME.toString()));

        request.setAttribute(SecurityParam.USER_ID_INPUT_NAME.toString(), userId);
        request.setAttribute(SecurityParam.TARGET_PARAM_NAME.toString(), loginRedirect);

        Mbr failMbr = new Mbr();
        failMbr.setMbrId(userId);
        
        SystemPK pk = idGenService.getAutoGeneratorSystemPK(request);
        
        Locale locale = LocaleService.getCurrentLocale(request);

        failMessage = messageSourceCmpAccessor.getMessage("member.login.info.notmatched.msg", locale);

        // ERP 연동에 실패하여 로그인이 실패하였을 경우 메세지 변경
        if(e.getCause() != null && e.getCause().getClass().getName().contains("MemberFailERPIFException")) {
        	failMessage = messageSourceCmpAccessor.getMessage("MemberFailERPIFException", locale);
        }
        // 네이버 로그인 시 Access Token Validator 실패하였을 경우 메세지 변경
        else if(e.getCause() != null && e.getCause().getClass().getName().contains("BadCredentialsException")) {
        	failMessage = "BadCredentials";
        }
        
        log.debug("locale {} ", locale);

        MemberFoDTO dto = new MemberFoDTO();
        dto.setMbr(failMbr);
        try {
			dto.setMallId(pk.getMall());
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
        Mbr mbr = memberBaseSelectService.login(dto);
        
        // 조회된 아이디가 있는 경우
        if (mbr != null){
        	// 회원의 상태가 휴면이 아닌 경우만 실패횟수 업데이트(추후 휴면인 경우의 처리도 필요할 것으로 보임.)
        	if(!MemberEnum.MemberStatCd.DRMNCY.toString().equals(mbr.getMbrStatCd())) {
	            if (mbr.getMbrLoginLastFailrCount() != null && mbr.getMbrLoginLastFailrCount() >= 5) {
	            	// 5회 이상 로그인에 실패했습니다.<br>비밀번호 찾기에서 새로운 비밀번호를 설정해주세요.
	                //failMessage = messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg", locale);
	            	if("DXM".equals(pk.getMall())){
	            		failMessage = messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg", locale);
                	}else{
                		failMessage = messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg2", locale);
                	}
	            }
	            else if(e.getCause() == null || (e.getCause() != null && !e.getCause().getClass().getName().contains("MemberFailERPIFException"))) {
	                // ERP 연동에 실패하여 로그인이 실패하였을 경우는 로그인 실패횟수를 업데이트 하지 않음.
	                // 로그인 실패횟수 +1 업데이트
	            	if(mbr.getMbrLoginLastFailrCount() != null) { 
	            		mbr.setMbrLoginLastFailrCount(mbr.getMbrLoginLastFailrCount() + 1);
	            	}
	            	else {
	            		mbr.setMbrLoginLastFailrCount(1);
	            	}
	                memberBaseCommandService.updateLoginFailCount(mbr);
	                if (mbr.getMbrLoginLastFailrCount() == 5) {
	                	// 5회 이상 로그인에 실패했습니다.<br>비밀번호 찾기에서 새로운 비밀번호를 설정해주세요.
	                	//failMessage = messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg", locale);
	                	if("DXM".equals(pk.getMall())){
		            		failMessage = messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg", locale);
	                	}else{
	                		failMessage = messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg2", locale);
	                	}
	                }
	            }
        	}

        	try {
         		String  ip = request.getRemoteAddr();
         		MbrLoginLog mbrLoginLog = new MbrLoginLog();
         		mbrLoginLog.setMbrNo(mbr.getMbrNo());
         		mbrLoginLog.setMbrLoginCd("LOGIN_FAILR");
         		mbrLoginLog.setLoginIp(ip);

         		String loginNationCd = locale.getCountry();

         		if(StringService.isNotEmpty(loginNationCd)){
         			mbrLoginLog.setLoginNationCd(loginNationCd.toLowerCase());
         		}else {
         			mbrLoginLog.setLoginNationCd("kr");
         		}

         		mbrLoginLog.setLangCd(pk.getLang());
         		mbrLoginLog.setDvcCd(pk.getDevice());
         		mbrLoginLog.setMallId(pk.getMall());

         		mbrLoginLog.setLoginFailrPw(IdGenService.generateSHA256(password));

                 //로그인 히스토리 추가
         		log.info(">>>>>>>>>>>>>>>>>>>>>>>> failId : " + userId + ", : " + password + ", : " + mbr.getMbrNo());
         		log.info("mbrLoginLog {}",mbrLoginLog);
                 memberBaseCommandService.addMbrLoginLog(pk, mbrLoginLog);
            } catch(Exception ex){
             	log.error("", ex);
            }
        } else {
            // 조회된 아이디가 없는 경우
            failMessage = messageSourceCmpAccessor.getMessage("member.login.info.notmatched.msg", locale);
        }

        String pageMode = request.getParameter(SecurityParam.LOGIN_POPUP_ENALBED.toString());
        String targetURL = failURL;

        //pageMode값 true일 경우
        if(StringService.isNotEmpty(pageMode) && BooleanUtils.toBoolean(pageMode)) {
            targetURL = failPopupURL;
        }

        request.getSession().removeAttribute(SessionEnum.MEMBER_INFORMATION_SDO.toString());
        request.getSession().removeAttribute(SessionEnum.ONLINE_INSERT_FLAG.toString());
        
        renderView(request, response, targetURL+ "?authenticationNok=1");
    }


    private void renderView (HttpServletRequest request, HttpServletResponse response, String targetURL) throws IOException, ServletException {

        boolean ajaxAvailable = BooleanUtils.toBoolean(request.getHeader(SecurityParam.HEADER_AJAX_LOGIN.toString()));

        //요청이 ajax 면
        if (ajaxAvailable) {

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            SecurityJsonResult resultData = new SecurityJsonResult();
            String jsonData = "";
            try {
                log.info("{} {}", this.getClass().getName(), failMessage);
            	resultData.setFailMessage(failMessage);
                jsonData = JsonService.marshalling(resultData);
            }catch (Exception e) {
                throw new IOException(e);
            }
            InputStream is =
                    new ByteArrayInputStream(jsonData.getBytes());
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } else {
            request.getRequestDispatcher(targetURL).forward(request, response);
        }
    }
}
