/* Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      id
 * @since       2018.04.30   
 */
package com.plgrim.ncp.web.pc.mlb.member;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.member.data.Ipin;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.Pcc;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberBenefitApiResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.cmp.member.fo.MemberAuthFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberJoinFOComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.utils.CookieUtil;
import com.plgrim.ncp.interfaces.member.data.MemberInformationSDO;
import com.plgrim.ncp.interfaces.member.data.MembershipSDO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/login")
public class MemberLoginController extends MemberBaseController {

	@Autowired
	CommonSelectComponent commonSelectComponent;
	
	@Autowired
	private MemberJoinFOComponent memberJoinFOComponent;
	
	@Autowired
	private MemberBenefitFOComponent memberBenefitFOComponent;
	
	@Autowired
	private MemberAuthFOComponent memberAuthFOComponent;
	
	@Autowired
	private CartCommandComponent cartCommandComponent;
	
	@Autowired
	private MemberBaseCommandService memberBaseCommandService;
	
	/**
	 * 로그인 페이지
	 *
	 * @param request [설명]
	 * @return String [설명]
	 * @since 2018. 4. 20
	 */
	@RequestMapping(value = "/view", method = {RequestMethod.POST, RequestMethod.GET} )
	public String view(@RequestParam(value="loginTarget", required = false) String loginTarget, HttpServletRequest request, HttpServletResponse response, Model model) {
		log.debug("{}",  request.getRequestURI());
		/** 화면 제목 세팅 시작 **/
		model.addAttribute("titleSetKey", "member.title.login");
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeHomeLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
		location.put("url", "/member/login/view");
        location.put("msgKey", "member.title.login");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
		
		String sessionUrl = "";
		HttpSession session = request.getSession();
		SavedRequest savedRequest = null;
		if(session != null) {
			savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
			if(savedRequest != null) {
				sessionUrl = savedRequest.getRedirectUrl();
			}
		}
		
		// 로그인이 되어있는 상태이면 메인 호출
		if(ContextService.hasRole()){
			return "redirect:/";
		}
		
		try {
			
			if(!StringService.isEmpty(sessionUrl)&&(sessionUrl.indexOf("order") > -1 || sessionUrl.indexOf("cart") > -1 )){
				model.addAttribute("nonOrderFlag", true);
			}else{
				String refererUrl = request.getHeader("REFERER");
                if(!StringService.isEmpty(loginTarget)){
                	model.addAttribute("loginTarget", loginTarget);
                	
                }else{
                	if(refererUrl != null && (refererUrl.indexOf("/errors") > -1 || refererUrl.indexOf("/member") > -1)){
                		
                	}else{
                		session.setAttribute("REFERER_URL",refererUrl);
                	}
                }
			}
			
			String uid = (CookieUtil.getCookieValue(request, "UID")) == null ? "" : CookieUtil.getCookieValue(request, "UID");
			
			/*
			Cookie[] cookies = request.getCookies(); // 요청정보로부터 쿠키를 가져온다.			
			
			if ( cookies != null ) {
				for (int i = 0; i < cookies.length; i++) {

					if (cookies[i].getName().equals("UID")) {
						if (cookies[i].getValue() != null
								&& !cookies[i].getValue().equals("")) {
							uid = cookies[i].getValue();
						}
					}

				}
			}
			*/
			
			model.addAttribute("UID", URLDecoder.decode(uid, "UTF-8") );

		}
		catch (Exception e) {
			log.error("",e);
		}

		model.addAttribute("seo_title", "로그인 | MLB");
		model.addAttribute("seo_desc", "로그인 | MLB");
		
		return "tiles:member/login.view";
	}
	
	/**
	 * 로그인 팝업 페이지
	 *
	 * @param request [설명]
	 * @return String [설명]
	 * @since 2018. 4. 20
	 */
	@RequestMapping(value = "/view.popup", method = {RequestMethod.POST, RequestMethod.GET} )
	public String viewPopup(@RequestParam(value="loginTarget", required = false) String loginTarget, HttpServletRequest request, Model model) {
		String refererUrl = request.getHeader("REFERER");
				
		try {
	        if(!StringService.isEmpty(refererUrl)) {
	        	model.addAttribute("loginTarget", refererUrl);	        	
	        }
	        else {
	        	model.addAttribute("loginTarget", loginTarget);	        	
	        }
	        
	        Cookie[] cookies = request.getCookies(); // 요청정보로부터 쿠키를 가져온다.
	     	String uid = "";

	     	for (int i = 0; i < cookies.length; i++) { 

	     		if (cookies[i].getName().equals("UID")) {
	     			if (cookies[i].getValue() != null
	     			        && !cookies[i].getValue().equals("")) {
	     				uid = cookies[i].getValue();
	     			}
	     		}

	     	}
	     	model.addAttribute("UID", uid);
        }
        catch (Exception e) {
	        log.error("",e);
        }
		
		return "tiles:member/popup/login.popup";
	}

	/**
	 * 로그인 후 처리
	 *
	 * @param request [설명]
	 * @param dto [설명]
	 * @param model [설명]
	 * @throws Exception the exception
	 * @since 2018. 4. 30
	 */
	@RequestMapping(value = "/loginAction.json", method = RequestMethod.GET )
	public void loginAction(HttpServletRequest request, MemberFoDTO dto, Model model ) {

		try{
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
			if(ContextService.hasRole()){
				
				if(dto.getLoginActionParam() == null || dto.getLoginActionParam().equals("")){
					// 장바구니 병합
					cartCommandComponent.mergeCart(systemPK);
				}

				// 혜택API 로그인 - 로그인
				MemberBenefitApiResult apiResult = memberBenefitFOComponent.callMemberBenefit(systemPK, MemberBenefitEnum.BnefSectCd.LOGIN, null);
				
				log.info("MEMBER_BENEFIT_API : ResultCd({}),ResultMsg({})", apiResult.getResultCd(), apiResult.getResultMsg());

				if(apiResult != null && apiResult.getMemberBenefitResultList() != null) {
				    List<MemberBenefitResult> resultList = apiResult.getMemberBenefitResultList();

				    for(int i= 0; i < resultList.size(); i++) {
				        MemberBenefitResult memberBenefitResult = resultList.get(i);
				        log.info("MEMBER_BENEFIT_API_DETAIL : ResultCd({}),ResultMsg({})", memberBenefitResult.getResultCd(), memberBenefitResult.getResultMsg());
				    }
				}
			}
		}
		catch(Exception e) {
			log.error("",e);
		}
	}
	
	/**
	 * 아이디 찾기 페이지
	 *
	 * @param request [설명]
	 * @return String [설명]
	 * @since 2018. 5. 18
	 */
	@RequestMapping(value = "/viewFind", method = {RequestMethod.POST, RequestMethod.GET} )
	public String viewFind(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value="type", required = false) String type) {
		log.debug("{}",  request.getRequestURI());
		
		String title = "member.title.find.id";
		
		if(StringUtils.isEmpty(type)){
			type = "ID";
		}
		
		if(type != null && "ID".equals(type)) {
			title = "member.title.find.id";
		}
		else if(type != null && "PASSWORD".equals(type)) {
			title = "member.title.find.pw";
		}
		
		/** 화면 제목 세팅 시작 **/
		model.addAttribute("titleSetKey", title);
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeHomeLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();

        location.put("url", "/member/login/viewFind");
        location.put("msgKey", title);
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
		
		// 로그인이 되어있는 상태이면 메인 호출
		if(ContextService.hasRole()){
			return "redirect:/";
		}
		else {
			model.addAttribute("type", type);
			
			return "tiles:member/find.view";
		}
	}
	
	/**
	 * 비밀번호 변경 처리
	 *
	 * @param request [설명]
	 * @param dto [설명]
	 * @param model [설명]
	 * @throws Exception the exception
	 * @since 2018. 6. 11
	 */
	@RequestMapping(value = "/modifyPassword.json", method = RequestMethod.POST )
	public void modifyPassword(HttpServletRequest request, MemberFoDTO dto, Model model ) {
		try{
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
			boolean isFlag = true;				// 비밀번호 변경 성공 여부
			boolean onlyErpUpdateFlag = false;		// 온라인 비밀번호가 아닌 ERP 비밀번호 변경 여부(휴면인 경우 혹은 ERP만 회원이 있는 경우 ERP의 비밀번호만 변경)
			
			// 비밀번호 찾기에서 들어온 경우
			if(!ContextService.hasRole()) {
				// 중복 DI 검사
				MbrCrtfc crtfc = new MbrCrtfc();
	
				String di = "";
				String ci = "";
				// 본인인증, 아이핀인증 정보를 세션에서 조회
				if(request.getSession().getAttribute(SessionEnum.PCC.toString()) != null) {
		        	Pcc pcc = (Pcc)request.getSession().getAttribute(SessionEnum.PCC.toString());
	
		        	di = pcc.getDi();
		        	ci = pcc.getCi();
		        }
				else if(request.getSession().getAttribute(SessionEnum.IPIN.toString()) != null) {
		        	Ipin ipin = (Ipin)request.getSession().getAttribute(SessionEnum.IPIN.toString());
		        	
		        	di = ipin.getDiscrhash();
		        	ci = ipin.getCiscrhash();
		        }
				else {
					isFlag = false;
				}
	
				MemberFoResult checkCertMbr = null;
				if(isFlag) {
					crtfc.setRlnmCrtfcDi(di);
					
					// 온라인에서 회원 조회
					checkCertMbr = memberAuthFOComponent.checkMemberCertify(crtfc.getRlnmCrtfcDi(), null, null);
					
					if(checkCertMbr == null || checkCertMbr.getMbr() == null) {
						// ERP 인터페이스 조회하여 회원 조회
						MbrCrtfc mbrCrtfc = new MbrCrtfc();
						mbrCrtfc.setRlnmCrtfcDi(di);
						mbrCrtfc.setRlnmCrtfcCi(ci);
						
						MembershipSDO membershipSDO = memberAuthFOComponent.checkMemberJoinErp(systemPK, mbrCrtfc);
						if(membershipSDO == null || membershipSDO.getResult() == null || "".equals(membershipSDO.getResult())) {
							isFlag = false;
						}
						else {
							List<Mbr> mbrList = memberAuthFOComponent.selectMemberByErpCstmrNo(membershipSDO.getCid());
							
							if(mbrList != null && !mbrList.isEmpty()) {
								for(Mbr mbr : mbrList) {
									dto.setMbr(mbr);
								}
							}
							else {
								Mbr mbr = new Mbr();
								mbr.setErpCstmrNo(membershipSDO.getCid());
								try {
									MemberInformationSDO memberInformationSDO = memberJoinFOComponent.getMemberInformation(systemPK, mbr);
									mbr.setMbrId(memberInformationSDO.getId());
									memberBaseCommandService.setMbrStatCd(memberInformationSDO.getStatus(), mbr);
									dto.setMbr(mbr);
								} catch (Exception e) {
									log.warn(">> modifyPassword", e);
									onlyErpUpdateFlag = false;
								}
							}
							
							onlyErpUpdateFlag = true;
						}
					}
					else {
						dto.setMbr(checkCertMbr.getMbr());
					}
				}
			
			}
			// 마이페이지 회원정보수정 비밀번호 변경에서 들어온 경우
			else {
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		        dto.setMbr(userDetail.getMbr());
			}
			
			if(isFlag) {
				isFlag = memberJoinFOComponent.updatePassword(systemPK, dto, onlyErpUpdateFlag);
			}
			
			model.addAttribute("isFlag", isFlag);
		}
		catch(Exception e){
			log.error("",e);
		}
		finally {
			// 회원가입 완료 후 본인인증 관련 정보를 세션에서 제거
			request.getSession().removeAttribute(SessionEnum.PCC.toString());
			request.getSession().removeAttribute(SessionEnum.IPIN.toString());
		}
	}
	
	@RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET} )
	public String logout(HttpServletRequest request, HttpSession session, Model model) {
		log.debug("{}",  request.getRequestURI());
		
		SecurityContextHolder.clearContext();

		if(session != null)
		    session.invalidate();
		
		return "redirect:/";
	}
	
}
