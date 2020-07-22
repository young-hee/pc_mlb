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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.MemberEnum.MemberTpCd;
import com.plgrim.ncp.base.enums.MemberEnum.UnityMbrCrtfcSectCd;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.member.data.MemberChildDTO;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.Pcc;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.service.MemberInterfaceService;
import com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberAuthFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberJoinFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/join")
public class MemberJoinController extends MemberBaseController {

	@Autowired
	CommonSelectComponent commonSelectComponent;
	
	@Autowired
	private MemberJoinFOComponent memberJoinFOComponent;
	
	@Autowired
	private MemberAuthFOComponent memberAuthFOComponent;
	
	@Autowired
	private DisplaySelectComponent displaySelectComponent;
	
	@Autowired
	MemberInterfaceService memberInterfaceService;
	
	/**
	 * 회원 가입 약관 동의 화면
	 * 
	 * <p/>
	 * 
	 * @param request [설명]
	 * @param response [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2018. 4. 30
	 */
//	@RequestMapping(value = {"/viewTerms"}, method = {RequestMethod.GET,RequestMethod.POST})
//    public String viewTerms(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//    	log.debug("{}",  request.getRequestURI());
//    
//		/** 화면 제목 세팅 시작 **/
//		model.addAttribute("titleSetKey", "member.title.join");
//		/** 화면 제목 세팅 종료 **/
//		
//		/** 화면 location 세팅 시작 **/
//		List<Map<String, String>> locationSet = this.makeHomeLocationSet();
//		
//		Map<String, String> location = new HashMap<String, String>();
//
//        location.put("url", "/member/join/viewTerms");
//        location.put("msgKey", "member.title.join");
//        locationSet.add(location);
//        
//        model.addAttribute("locationSet", locationSet);
//        /** 화면 location 세팅 종료 **/
//    	
//		// 로그인이 되어있는 상태이면 메인 호출
//		if(ContextService.hasRole()){
//			return "redirect:/";
//		}
//		// 본인인증이 되어 있지 않으면 본인인증 화면 호출
//		else if(request.getSession().getAttribute(SessionEnum.PCC.toString()) == null
//				&& request.getSession().getAttribute(SessionEnum.IPIN.toString()) == null
//				) {
//			return "redirect:/member/certification/view";
//		}
//    	
//		request.getSession().removeAttribute(SessionEnum.MEMBER_FO_DTO.toString());
//		
//		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
//
//		// 온라인 사이트 이용약관
//		SysStplat onlineSiteUsefStplat = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.ONLNE_SITE_USEF_STPLAT.toString());
//		model.addAttribute("onlineSiteUsefStplat", onlineSiteUsefStplat);
//
//		// 개인정보 취급 방침
//		SysStplat psnlInfoColctUsefAgr = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.PSNL_INFO_TRTMNT_POLCY.toString());
//		model.addAttribute("psnlInfoColctUsefAgr", psnlInfoColctUsefAgr);
//		
//    	String localeLang = LocaleService.getCurrentLang(request);
//    	
//    	SysStplatUse sysStplatUse = new SysStplatUse();
//		sysStplatUse.setStplatUseTpCd("ONLNE_MBR_JOIN");
//		sysStplatUse.setLang(localeLang);
//
//		List<SysStplat> sysStplatList = memberJoinFOComponent.selectSysStplat(sysStplatUse);
//		model.addAttribute("sysStplatList", sysStplatList);
//
//        return "tiles:member/terms.view";
//    }
	
	@RequestMapping(value = "/getTerms.json", method = RequestMethod.POST, produces = { "application/json" })
	public void getTerms(HttpServletRequest request, Model model ) throws Exception {
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		// 온라인 사이트 이용약관
		SysStplat onlineSiteUsefStplat = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.ONLNE_SITE_USEF_STPLAT.toString());
		model.addAttribute("onlineSiteUsefStplat", onlineSiteUsefStplat);

		// 개인정보 취급 방침
		SysStplat psnlInfoColctUsefAgr = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.PSNL_INFO_TRTMNT_POLCY.toString());
		model.addAttribute("psnlInfoColctUsefAgr", psnlInfoColctUsefAgr);
	}
	
	/**
	 * 회원 가입 정보 입력 화면
	 * 
	 * <p/>
	 * 
	 * @param request [설명]
	 * @param response [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2018. 4. 30
	 */
	@RequestMapping(value = {"/view"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String view(HttpServletRequest request, Model model) throws Exception {
    	log.debug("{}",  request.getRequestURI());
    	
    	// 본인인증 관련 정보를 세션에서 제거
		request.getSession().removeAttribute(SessionEnum.PCC.toString());
		//request.getSession().removeAttribute(SessionEnum.IPIN.toString());

		/** 화면 제목 세팅 시작 **/
		model.addAttribute("titleSetKey", "member.title.join");
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeHomeLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();

        location.put("url", "/member/join/view");
        location.put("msgKey", "member.title.join");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
    	
		// 로그인이 되어있는 상태이면 메인 호출
		if(ContextService.hasRole()){
			return "redirect:/";
		}

		model.addAttribute("seo_title", "회원가입 | MLB");
		model.addAttribute("seo_desc", "회원가입 | MLB");
		
        return "tiles:member/join.view";
    }
	
	@RequestMapping(value = {"/viewTest"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String viewTest(HttpServletRequest request, Model model) throws Exception {
    	log.debug("{}",  request.getRequestURI());
    	
    	// 본인인증 관련 정보를 세션에서 제거
		request.getSession().removeAttribute(SessionEnum.PCC.toString());
		//request.getSession().removeAttribute(SessionEnum.IPIN.toString());

		/** 화면 제목 세팅 시작 **/
		model.addAttribute("titleSetKey", "member.title.join");
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeHomeLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();

        location.put("url", "/member/join/view");
        location.put("msgKey", "member.title.join");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
    	
		// 로그인이 되어있는 상태이면 메인 호출
		if(ContextService.hasRole()){
			return "redirect:/";
		}
    	    	
        return "tiles:member/join.view.test";
    }
	
	
//	@RequestMapping(value = {"/view"}, method = {RequestMethod.GET,RequestMethod.POST})
//    public String view(HttpServletRequest request, MemberFoDTO dto, Model model) throws Exception {
//    	log.debug("{}",  request.getRequestURI());
//
//		/** 화면 제목 세팅 시작 **/
//		model.addAttribute("titleSetKey", "member.title.join");
//		/** 화면 제목 세팅 종료 **/
//		
//		/** 화면 location 세팅 시작 **/
//		List<Map<String, String>> locationSet = this.makeHomeLocationSet();
//		
//		Map<String, String> location = new HashMap<String, String>();
//
//        location.put("url", "/member/join/view");
//        location.put("msgKey", "member.title.join");
//        locationSet.add(location);
//        
//        model.addAttribute("locationSet", locationSet);
//        /** 화면 location 세팅 종료 **/
//    	
//		// 로그인이 되어있는 상태이면 메인 호출
//		if(ContextService.hasRole()){
//			return "redirect:/";
//		}
//		// 본인인증이 되어 있지 않으면 본인인증 화면 호출
//		// 만 14세 이상이 아닌 경우 본인인증 화면 호출
//		else if((request.getSession().getAttribute(SessionEnum.PCC.toString()) == null
//				&& request.getSession().getAttribute(SessionEnum.IPIN.toString()) == null)
//				|| StringUtils.isEmpty(dto.getAgeChk()) || !"Y".equals(dto.getAgeChk())
//				) {
//			return "redirect:/member/certification/view";
//		}
//    	
//		boolean stplatInvalid = false;
//		String onlineSiteUsefStplat = dto.getOnlineSiteUsefStplat();
//		String psnlInfoColctUsefAgr = dto.getPsnlInfoColctUsefAgr();
//
//        // 약관 동의가 없는 경우 약관 동의 화면 호출
//		if (!StringUtils.isEmpty(onlineSiteUsefStplat) && !StringUtils.isEmpty(psnlInfoColctUsefAgr)) {
//			if ( !"Y".equals(onlineSiteUsefStplat) || !"Y".equals(psnlInfoColctUsefAgr)) {
//				stplatInvalid = true;
//			}
//		} else {
//			stplatInvalid = true;
//		}
//
//		if (stplatInvalid) {
//			return "redirect:/member/join/viewTerms";
//		}
//
//    	request.getSession().setAttribute(SessionEnum.MEMBER_FO_DTO.toString(), dto);
//    	
//		if(request.getSession().getAttribute(SessionEnum.PCC.toString()) != null) {
//        	Pcc pcc = (Pcc)request.getSession().getAttribute(SessionEnum.PCC.toString());
//        	model.addAttribute("certifyName", pcc.getName().trim());
//			model.addAttribute("certifyCellNo", pcc.getCellNo().trim());
//			model.addAttribute("certifyBirth", pcc.getBirYMD().trim());
//			model.addAttribute("certifyType", "pcc");
//			model.addAttribute("certifySuccess", true);
//        }
//		else if(request.getSession().getAttribute(SessionEnum.IPIN.toString()) != null) {
//        	Ipin ipin = (Ipin)request.getSession().getAttribute(SessionEnum.IPIN.toString());
//        	model.addAttribute("certifyName", ipin.getName().trim());
//        	model.addAttribute("certifyBirth", ipin.getBirth().trim());
//        	model.addAttribute("certifyType", "ipin");
//        	model.addAttribute("certifySuccess", true);
//        }
//        else {
//        	model.addAttribute("certifySuccess", false);
//        }
//    	
//        return "tiles:member/join.view";
//    }

	/**
	* 중복 이메일이 있는지 확인
	* 중복 DI가 있는지 확인
	* 
	* @param request
	* @param dto
	* @param model
	* @throws Exception
	* @since 2018. 4. 30
	 */
	@RequestMapping(value = "/joinMemberCheck.json", method = RequestMethod.POST)
	public void joinMemberCheck(HttpServletRequest request, MemberFoDTO dto, Model model ) throws Exception {
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		dto.setMallId(pk.getMall());
		
		boolean duplication = false;
		
		// 중복 이메일 검사
		boolean isCheckEmail = memberJoinFOComponent.isCheckEmail(dto);
		
		// 중복 DI 검사
		MbrCrtfc crtfc = new MbrCrtfc();

		String di = "";
		// 본인인증, 아이핀인증 정보를 세션에서 조회
		if(request.getSession().getAttribute(SessionEnum.PCC.toString()) != null) {
        	Pcc pcc = (Pcc)request.getSession().getAttribute(SessionEnum.PCC.toString());

        	di = pcc.getDi();
        }
//		else if(request.getSession().getAttribute(SessionEnum.IPIN.toString()) != null) {
//        	Ipin ipin = (Ipin)request.getSession().getAttribute(SessionEnum.IPIN.toString());
//        	
//        	di = ipin.getDiscrhash();
//        }
		
		crtfc.setRlnmCrtfcDi(di);
		
		// 멤버십 회원 중복 체크
		MemberFoResult checkCertMbr = memberAuthFOComponent.checkMemberCertify(crtfc.getRlnmCrtfcDi(), null, null);
		
		if(checkCertMbr != null && checkCertMbr.getMbr() != null) {
			if(checkCertMbr.getMbr().getMbrId() != null && !"".equals(checkCertMbr.getMbr().getMbrId())) {
				duplication = true;
			}
		}
		
		model.addAttribute("duplication", duplication);
		model.addAttribute("isCheckEmail", isCheckEmail);
	}
	
	/**
	 * 아이디 입력시 ajax로 중복된 아이디가 있는지 확인
	 *
	 * @param request [설명]
	 * @param dto [설명]
	 * @param model [설명]
	 * @throws Exception the exception
	 * @since 2018. 4. 30
	 */
	@RequestMapping(value = "/isCheckId.json", method = RequestMethod.POST, produces = { "application/json" })
	public void checkId(HttpServletRequest request, MemberFoDTO dto, Model model ) throws Exception {
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
				
		// 온라인에서 ID 확인
		boolean isCheckId = memberJoinFOComponent.isCheckId(dto);
		
		// ERP에서 ID 확인
		if(!isCheckId) {
			isCheckId = memberJoinFOComponent.isCheckIdByErp(pk, dto);
		}
		
		model.addAttribute("isCheckId", isCheckId);
	}

	/**
	 * 이메일 입력시 ajax 로 중복된 이메일이 있는지 확인
	 * 
	 * @param request [설명]
	 * @param response [설명]
	 * @param dto [설명]
	 * @param model [설명]
	 * @throws Exception the exception
	 * @since 2018. 4. 30
	 */
	@RequestMapping(value = "/isCheckEmail.json", method = RequestMethod.POST, produces = { "application/json" })
	public Model checkEmail(HttpServletRequest request,HttpServletResponse response, MemberFoDTO dto, Model model ) throws Exception {
		boolean isCheckEmail = memberJoinFOComponent.isCheckEmail(dto);
		model.addAttribute("isCheckEmail", isCheckEmail);
		return model;
	}
	
	/**
	 * 이메일 입력시 ajax 로 중복된 이메일이 있는지 확인
	 * (본인 회원번호를 제외한 회원 중에서 체크)
	 * 
	 * @param request [설명]
	 * @param response [설명]
	 * @param dto [설명]
	 * @param model [설명]
	 * @throws Exception the exception
	 * @since 2018. 6. 15
	 */
	@RequestMapping(value = "/isCheckEmailOthers.json", method = RequestMethod.POST, produces = { "application/json" })
	public Model checkEmailOthers(HttpServletRequest request,HttpServletResponse response, MemberFoDTO dto, Model model ) throws Exception {
		boolean isCheckEmail = true;
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			if(dto.getMbr() != null && dto.getMbr().getMbrEmail() != null){
				userDetail.getMbr().setMbrEmail(dto.getMbr().getMbrEmail());
			}
			dto.setMbr(userDetail.getMbr());
			isCheckEmail = memberJoinFOComponent.isCheckEmailOthers(dto);
		}
		
		model.addAttribute("isCheckEmail", isCheckEmail);
		return model;
	}
	
	/**
	 * 회원 가입 처리 와 동시에 자동 로그인 처리
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param request [설명]
	 * @param response [설명]
	 * @param dto [설명]
	 * @param model [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2018. 5. 03
	 */
	@PreAuthorize("hasAnyRole('ROLE_ANONYMOUS','ROLE_GUEST')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, MemberFoDTO dto
						,@RequestParam(value="childrenName", required=false) List<String> childrenName
						,@RequestParam(value="childrenBirthDate", required=false) List<String> childrenBirthDate
						,@RequestParam(value="childrenBirthYear", required=false) List<String> childrenBirthYear
						,@RequestParam(value="childrenBirthMonth", required=false) List<String> childrenBirthMonth
						,@RequestParam(value="childrenBirthDay", required=false) List<String> childrenBirthDay
						,Model model ) throws Exception {
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
//		MemberFoDTO dtoFromSession = null;
//		boolean stplatInvalid = false;
//		String onlineSiteUsefStplat = "";
//		String psnlInfoColctUsefAgr = "";
//		if(request.getSession().getAttribute(SessionEnum.MEMBER_FO_DTO.toString()) != null) {
//			dtoFromSession = (MemberFoDTO) request.getSession().getAttribute(SessionEnum.MEMBER_FO_DTO.toString());
//		
//			onlineSiteUsefStplat = dtoFromSession.getOnlineSiteUsefStplat();
//			psnlInfoColctUsefAgr = dtoFromSession.getPsnlInfoColctUsefAgr();
//	        // 약관 동의가 없는 경우 약관 동의 화면 호출
//			if (!StringUtils.isEmpty(onlineSiteUsefStplat) && !StringUtils.isEmpty(psnlInfoColctUsefAgr)) {
//				if ( !"Y".equals(onlineSiteUsefStplat) || !"Y".equals(psnlInfoColctUsefAgr)) {
//					stplatInvalid = true;
//				}
//			} else {
//				stplatInvalid = true;
//			}
//		}
//		
//		if (stplatInvalid) {
//			return "redirect:/member/join/viewTerms";
//		}
		
		// 로그인이 되어있는 상태이면 메인 호출
		if(ContextService.hasRole()){
			return "redirect:/";
		}
		// 본인인증이 되어 있지 않으면 본인인증 화면 호출
		else if(request.getSession().getAttribute(SessionEnum.PCC.toString()) == null) {
			return "redirect:/member/join/view";
		}
		
		Pcc pcc = (Pcc)request.getSession().getAttribute(SessionEnum.PCC.toString());   
		//HttpSession session = request.getSession();
		
		//인증정보값 세팅		
		dto.setMobileNumber(pcc.getCellNo());

		//자동 로그인 위해 암호화 되기전에 담는다.
		String userId = dto.getMbr().getMbrId();
		String password = dto.getMbr().getMbrPw();
		request.setAttribute("userId", userId);
		request.setAttribute("password", password);

		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();

		/////////////////////////////////////////////////////////////////////////
		// start : hdiv 상에 공백을 허용하게 변경함으로 인해 공백 허용불가 처리 추가
		String regEx = ".*\\s.*";
		Pattern ptrnCh = Pattern.compile(regEx);

		String mbrNm = dto.getMbr().getMbrNm();
		String mbrEmail = dto.getMbr().getMbrEmail();
		String mobileNumber = dto.getMobileNumber();

		// userId, e-mail, 휴대폰번호 공백 불가
		if ((StringService.isNotEmpty(userId) && ptrnCh.matcher(userId).matches())
				|| (StringService.isNotEmpty(mbrEmail) && ptrnCh.matcher(mbrEmail).matches())
				|| (StringService.isNotEmpty(mobileNumber) && ptrnCh.matcher(mobileNumber).matches())) {

			// 특수문자 입력할 수 없습니다. 페이지 전달.
			return "redirect:/errors/vulnerability";
		}

		/**
		 * DEXC3-86 지라 요청으로 2018.09.14 주석 처리.
		 * 
		// 국문몰인 경우엔 회원명 공백 불가
		if (MemberEnum.KOR.toString().equals(pk.getLang())) {
			if (StringService.isNotEmpty(mbrNm) && ptrnCh.matcher(mbrNm).matches()) {
				// 특수문자 입력할 수 없습니다. 페이지 전달.
				return "redirect:/errors/vulnerability";
			}
		}
		 */
		// end : hdiv 상에 공백을 허용하게 변경함으로 인해 공백 허용불가 처리 추가
		/////////////////////////////////////////////////////////////////////////

		// 이메일 수신동의여부		
		if(!StringUtils.isEmpty(dto.getMbr().getEmailRecptnAgrYn()) && dto.getMbr().getEmailRecptnAgrYn().equals(MemberEnum.YES.toString()) ){
			dto.getMbr().setEmailRecptnAgrYn(MemberEnum.YES.toString());
		}else{
			dto.getMbr().setEmailRecptnAgrYn(MemberEnum.NO.toString());
		}
		// SMS 수신동의여부
		if(!StringUtils.isEmpty(dto.getMbr().getSmsRecptnAgrYn()) && dto.getMbr().getSmsRecptnAgrYn().equals(MemberEnum.YES.toString()) ){
			dto.getMbr().setSmsRecptnAgrYn(MemberEnum.YES.toString());
		}else{
			dto.getMbr().setSmsRecptnAgrYn(MemberEnum.NO.toString());
		}
		// 우편 수신동의여부
//		if(!StringUtils.isEmpty(dto.getMbr().getDmRecptnAgrYn()) && dto.getMbr().getDmRecptnAgrYn().equals(MemberEnum.YES.toString()) ){
//			dto.getMbr().setDmRecptnAgrYn(MemberEnum.YES.toString());
//		}else{
//			dto.getMbr().setDmRecptnAgrYn(MemberEnum.NO.toString());
//		}
		dto.getMbr().setDmRecptnAgrYn(MemberEnum.NO.toString());
		// 전화 수신동의여부
//		if(!StringUtils.isEmpty(dto.getMbr().getTmRecptnAgrYn()) && dto.getMbr().getTmRecptnAgrYn().equals(MemberEnum.YES.toString()) ){
//			dto.getMbr().setTmRecptnAgrYn(MemberEnum.YES.toString());
//		}else{
//			dto.getMbr().setTmRecptnAgrYn(MemberEnum.NO.toString());
//		}
		dto.getMbr().setTmRecptnAgrYn(MemberEnum.NO.toString());
		
		dto.getMbr().setLastEmailRecptnAgrDt(today);
		dto.getMbr().setLastSmsRecptnAgrDt(today);
		dto.getMbr().setLastDmRecptnAgrDt(today);
		dto.getMbr().setLastTmRecptnAgrDt(today);

		if(MemberEnum.NO.toString().equals(dto.getMbr().getEmailRecptnAgrYn()) && MemberEnum.NO.toString().equals(dto.getMbr().getSmsRecptnAgrYn())
				&& MemberEnum.NO.toString().equals(dto.getMbr().getDmRecptnAgrYn()) && MemberEnum.NO.toString().equals(dto.getMbr().getTmRecptnAgrYn())){
			dto.setMarktPsnlInfoColctUsefAgrYn(MemberEnum.NO.toString());
		}else{
			dto.setMarktPsnlInfoColctUsefAgrYn(MemberEnum.YES.toString());
		}

		// 약관 동의 정보 세팅
		List<String> stplatList = new ArrayList<String>();
		stplatList.add(MemberEnum.StplatCd.ONLNE_SITE_USEF_STPLAT.toString());
		stplatList.add(MemberEnum.StplatCd.PSNL_INFO_TRTMNT_POLCY.toString());

		List<String> stplatYnList = new ArrayList<String>();
		stplatYnList.add(dto.getOnlineSiteUsefStplat());
		stplatYnList.add(dto.getPsnlInfoColctUsefAgr());
		
		dto.setStplatCd(stplatList);
		dto.setStplatIemAgrYn(stplatYnList);
		
		//휴대폰 본인인증
		dto.getMbr().setUnityMbrCrtfcSectCd(UnityMbrCrtfcSectCd.SLF_CRTFC.toString());

		// 가입 회원 IP
	    String ipAddress = request.getRemoteAddr();
		dto.getMbr().setJoinMbrIp(ipAddress);

		// 회원가입시 가입국가코드에 들어가는 국가코드가 코드테이블에 있는 국가코드와 상이
		Locale locale = LocaleService.getCurrentLocale(request);
		String joinNationCd = locale.getCountry();

		if(StringService.isNotEmpty(joinNationCd)){
			dto.getMbr().setJoinNationCd(joinNationCd.toLowerCase());
		} else {
			dto.getMbr().setJoinNationCd("kr");
		}

		// 국문용 자동세팅
		dto.getMbr().setMobilNationCd("kr");
		dto.getMbr().setMobilNationNo("82");
		if(dto.getMbr().getMbrAddrSectCd() != null) {
			dto.getMbr().setMbrAddrNationCd("kr");
			dto.getMbr().setMbrAddrTpCd("OWNHOM");
		}
		if(dto.getMbr().getTelAreaNo() != null) {
			dto.getMbr().setTelNationNo("82");
		}
		
		// 본인인증, 아이핀인증 정보를 세션에서 조회하여 dto에 세팅
		dto = this.setMemberFoDtoFromCert(request, dto);
		
		if(dto.isSignupType() == false) {
			throw new RuntimeException();
		}
		
		dto.getMbr().setMbrBrthdySlrcldYn(MemberEnum.YES.toString());		// 회원 생년월일 양력 여부
		
		if(request.getSession().getAttribute(SessionEnum.INFLOW_SN.toString()) != null){
			dto.getMbr().setInflowSn(Long.parseLong((String)request.getSession().getAttribute(SessionEnum.INFLOW_SN.toString())));
		}
		else {
			// 유입 일련번호
			Long inflowSn = null;
			String requestUrl = memberJoinFOComponent.getRequestUrlForAddMember(request, pk);
			
			SysInflow sysInflow = new SysInflow();
			sysInflow.setMallId(pk.getMall());
			sysInflow.setExcutRemNm(requestUrl);
			sysInflow = memberJoinFOComponent.selectMbrSysInflow(sysInflow);
			
			if(sysInflow != null) {
				if(sysInflow.getInflowSn() > 0) {
					inflowSn = sysInflow.getInflowSn();
				}
			}
			dto.getMbr().setInflowSn(inflowSn);
		}
		
		//자녀정보
		List<MemberChildDTO> cDto = new ArrayList<MemberChildDTO>();		
		if(childrenName != null && !childrenName.isEmpty()){
			for(int k = 0 ; k < childrenName.size() ; k++){
				try{
					String cName = childrenName.get(k);
					if(cName != null && !"".equals(cName)){
						String cBirthY = childrenBirthYear.get(k);
						String cBirthM = childrenBirthMonth.get(k);
						String cBirthD = childrenBirthDay.get(k);
												
						if(!StringUtils.isEmpty(cBirthY) && !StringUtils.isEmpty(cBirthM) && !StringUtils.isEmpty(cBirthD)){
							MemberChildDTO m = new MemberChildDTO();
							m.setChildrenName(cName);
							m.setChildrenBirthYear(cBirthY);
							m.setChildrenBirthMonth(cBirthM);
							m.setChildrenBirthDay(cBirthD);
							
							cDto.add(m);
						}
					}
				}catch(Exception e){
					log.debug("자녀정보 오류");
				}				
			}
			
			if(!cDto.isEmpty()){
				dto.setChildrenList(cDto);
			}
		}
				
		
		// 장바구니 병합을 위한 이전 SESSION_ID 저장
        String prvSessionId = (String) request.getSession().getAttribute(SessionEnum.SESSION_ID.toString());
        if (StringUtils.isEmpty(prvSessionId)) {
        	request.getSession().setAttribute(SessionEnum.SESSION_ID.toString(), request.getSession().getId());
        }
		
		// 회원 가입 처리
		memberJoinFOComponent.addMember(pk, dto);

		// 회원 가입 메일/SMS 발송
		memberJoinFOComponent.sendJoinMailSms(pk, dto);

		// security 설정 자동 로그인
		memberAuthFOComponent.mbrAuthentication();
		
		// 로그인 로그, ERP 로그인 성공 전송, 장바구니 병합, 로그인 혜택 처리
		memberAuthFOComponent.processAfterAddAndLogin(request);
		
		//request.getSession().removeAttribute(SessionEnum.MEMBER_FO_DTO.toString());

		// 회원가입 완료 후 본인인증 관련 정보를 세션에서 제거
		request.getSession().removeAttribute(SessionEnum.PCC.toString());
		//request.getSession().removeAttribute(SessionEnum.IPIN.toString());
		
		return "redirect:/member/join/viewJoinSuccess";
	}
	
	/**
	 * 회원 가입 완료 화면
	 * 
	 * <p/>
	 * 
	 * @param request [설명]
	 * @param response [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2018. 5. 03
	 */
	@RequestMapping(value = {"/viewJoinSuccess"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String viewJoinSucces(HttpServletRequest request, Model model) throws Exception {
    	log.debug("{}",  request.getRequestURI());
    
		/** 화면 제목 세팅 시작 **/
		model.addAttribute("titleSetKey", "member.title.join");
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeHomeLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();

        location.put("url", "/member/join/viewJoinSuccess");
        location.put("msgKey", "member.title.join");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
    	
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		Mbr mbr = userDetail.getMbr();
    	String mbrId = mbr.getMbrId();
    	mbrId = mbrId.substring(0, mbrId.length()-2) + "**";
		model.addAttribute("mbrId", mbrId);
		model.addAttribute("mbrNm", mbr.getMbrNm());
		
        return "tiles:member/join.success.view";
    }
	
	/**
	 * 본인인증 정보를 조회하여 MemberFoDto에 데이터 세팅
	 * 
	 * @param dto [설명]
	 * @param pcc [설명]
	 * @param ipin [설명]
	 * @return MemberFoDTO [설명]
	 * @throws Exception the exception
	 * @since 2018. 5. 04
	 */
	private MemberFoDTO setMemberFoDtoFromCert(HttpServletRequest request, MemberFoDTO dto) {
		long time = System.currentTimeMillis();
		Date regDt = new Date(time);
		
		if(request.getSession().getAttribute(SessionEnum.PCC.toString()) != null) {
        	Pcc pcc = (Pcc)request.getSession().getAttribute(SessionEnum.PCC.toString());

			dto.getMbr().setMbrTpCd(MemberTpCd.UNITY_MBR.toString());
			dto.getMbr().setUnityMbrCrtfcDt(regDt);
			dto.setSignupType(true);
			
			String frgnrYn = pcc.getFgnGbn();
			String mbrSexSectCd = pcc.getSex();
			String cellno = pcc.getCellNo();	//휴대폰번호
			
			// 외국인 여부
//			if("N".equals(frgnrYn)) {
//				frgnrYn = MemberEnum.NO.toString();
//			}
//			else {
//				frgnrYn = MemberEnum.YES.toString();
//			}
			
			// 성별
			if("9".equals(mbrSexSectCd) || "5".equals(mbrSexSectCd) || "7".equals(mbrSexSectCd) || "1".equals(mbrSexSectCd) || "3".equals(mbrSexSectCd)) {
				mbrSexSectCd = "MALE";
			}else {
				mbrSexSectCd = "FEMALE";
			}
			
			dto.getMbr().setFrgnrYn(frgnrYn);
			dto.getMbr().setMbrSexSectCd(mbrSexSectCd);
			dto.getMbr().setMbrNm(pcc.getName());
			dto.getMbr().setMbrBrthdy(pcc.getBirYMD());			
			if(cellno.length() > 10){
				dto.getMbr().setMobilAreaNo(cellno.substring(0, 3));
				dto.getMbr().setMobilTlofNo(cellno.substring(3, 7));
				dto.getMbr().setMobilTlofWthnNo(cellno.substring(7, 11));
			}else{
				dto.getMbr().setMobilAreaNo(cellno.substring(0, 3));
				dto.getMbr().setMobilTlofNo(cellno.substring(3, 6));
				dto.getMbr().setMobilTlofWthnNo(cellno.substring(6,10));
			}
        }
		/*
		else if(request.getSession().getAttribute(SessionEnum.IPIN.toString()) != null) {
        	Ipin ipin = (Ipin)request.getSession().getAttribute(SessionEnum.IPIN.toString());
        	
			dto.getMbr().setMbrTpCd(MemberTpCd.UNITY_MBR.toString());
			dto.getMbr().setUnityMbrCrtfcDt(regDt);
			dto.setSignupType(true);
        	
			String frgnrYn = ipin.getFgn();
			String mbrSexSectCd = ipin.getSex();
			
			// 외국인 여부
			if("0".equals(frgnrYn)) {
				frgnrYn = MemberEnum.NO.toString();
			}
			else {
				frgnrYn = MemberEnum.YES.toString();
			}
			
			// 성별
			if("0".equals(mbrSexSectCd)) {
				mbrSexSectCd = "FEMALE";
			}
			else {
				mbrSexSectCd = "MALE";
			}
			
			dto.getMbr().setFrgnrYn(frgnrYn);
			dto.getMbr().setMbrSexSectCd(mbrSexSectCd);
        }
        */
        else {
			dto.setSignupType(false);
        }
		
		return dto;
	}
	
	/**
	 * 본인인증 관련 정보를 세션에서 제거
	 *
	 * @param request [설명]
	 * @param model [설명]
	 * @throws Exception the exception
	 * @since 2018. 6. 30
	 */
	@RequestMapping(value = "/removeCert.json", method = RequestMethod.POST, produces = { "application/json" })
	public void removeCert(HttpServletRequest request, Model model) throws Exception {
		// 본인인증 관련 정보를 세션에서 제거
		request.getSession().removeAttribute(SessionEnum.PCC.toString());
		request.getSession().removeAttribute(SessionEnum.IPIN.toString());
	}	
	
}
