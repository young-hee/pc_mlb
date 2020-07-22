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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.member.data.BizNice;
import com.plgrim.ncp.biz.member.data.Ipin;
import com.plgrim.ncp.biz.member.data.Pcc;
import com.plgrim.ncp.cmp.member.fo.MemberAuthFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberJoinFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.data.SystemPK;

import CheckPlus.nice.MCheckPlus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/certification")
public class MemberCertificationController extends MemberBaseController {

	@Autowired
	CommonSelectComponent commonSelectComponent;
	
	@Autowired
	private MemberAuthFOComponent memberAuthFOComponent;
	
	@Autowired
	private MemberJoinFOComponent memberJoinFOComponent;

	/**
	 * 본인 인증 화면
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
    public String view(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    	log.debug("{}",  request.getRequestURI());
   
		/** 화면 제목 세팅 시작 **/
		model.addAttribute("titleSetKey", "member.title.join");
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeHomeLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
        
        location.put("url", "/member/certification/view");
        location.put("msgKey", "member.title.join");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
    	
        // 본인인증 관련 정보를 세션에서 제거
     	request.getSession().removeAttribute(SessionEnum.PCC.toString());
     	request.getSession().removeAttribute(SessionEnum.IPIN.toString());
        
        return "tiles:member/certification.view";
    }
	
	/**
	 * 아이핀 인증시 ajax로 ipin에서 필요한 데이터 세팅
	 * 
	 *
	 * @param request [설명]
	 * @param response [설명]
	 * @return Ipin [설명]
	 * @since 2018. 4. 30
	 */
	@ResponseBody
	@RequestMapping(value = "/setIpin.json", method = RequestMethod.POST )
	public Ipin setIpin(HttpServletRequest request, HttpServletResponse response, @RequestParam("srv") String srv){
		try {
			request.getSession().setAttribute("srv", srv);
			int numIpinLength = 6;
			String randomStr = "";
			Random ran = new Random();
			for (int i = 0; i < numIpinLength; i++) {
				// 0 ~ 9 랜덤 숫자 생성
				randomStr += ran.nextInt(10);
			}
			Calendar today = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String day = sdf.format(today.getTime());
			String reqIpinNum = day + randomStr;
		 
			request.getSession().setAttribute("reqIpinNum", reqIpinNum);
			Ipin ipin = memberAuthFOComponent.setIPIN(reqIpinNum, srv);
	        return ipin;
        }
        catch (Exception e) {
	        log.error("",e);
        }
		return null;

	}
	
	/**
	 * 휴대폰 인증시 ajax로 PCC에서 필요한 데이터 세팅
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param request [설명]
	 * @param response [설명]
	 * @return Pcc [설명]
	 * @since 2018. 4. 30
	 */
	@ResponseBody
	@RequestMapping(value = "/setPcc.json", method = RequestMethod.POST )
	public Pcc setPcc(HttpServletRequest request, HttpServletResponse response, @RequestParam("srv") String srv){
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		String device = pk.getDevice();
		try {
			request.getSession().setAttribute("srv", srv);
			int numLengthPhone = 6;
			String randomStrPhone = "";
			Random ranPcc = new Random();
			for (int i = 0; i < numLengthPhone; i++) {
				// 0 ~ 9 랜덤 숫자 생성
				randomStrPhone += ranPcc.nextInt(10);
			}
			Calendar today = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String day = sdf.format(today.getTime());
			String reqPccNum = day + randomStrPhone;
			
			request.getSession().setAttribute("reqPccNum", reqPccNum);
	        Pcc pcc = memberAuthFOComponent.setPCC(reqPccNum, day, srv, device);
	        return pcc;
        }
        catch (Exception e) {
	        log.error("",e);
        }
		return null;
	}
	
	/**
	 * 본인인증 gate 페이지 
	 * 
	 * <p/>
	 * 
	 */
	@RequestMapping(value="/nice", method = {RequestMethod.GET, RequestMethod.POST})
	public String certifySiren(Model model, HttpServletRequest request, BizNice bizNice) throws Exception{
		log.info("bizNice : {}", bizNice);
		model.addAttribute("bizNice", bizNice);
		return "tiles:member/popup/certification.nice";
	}
	
	/**
	 * 아이핀 인증 완료 후 데이터 받아주는 페이지
	 *
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2018. 4. 30
	 */
	@RequestMapping(value = "/ipinNiceSuccess.popup", method = {RequestMethod.POST, RequestMethod.GET })
	public String ipinNiceComplete(HttpServletRequest request, Model model) {
		try {
			
			SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
			
			String reqIpinNum = "";
			if(!StringUtils.isEmpty(request.getSession().getAttribute("reqIpinNum"))) {
				reqIpinNum = (String) request.getSession().getAttribute("reqIpinNum");
			}
			
			log.info("reqIpinNum ::=============================>"+reqIpinNum);

			if("".equals(reqIpinNum)) {
				model.addAttribute("certificationResult", "N");
				throw new Exception("인증 실패");
			}
			
			String retInfo = "";
			if(request.getParameter("enc_data") != null) {
				retInfo = request.getParameter("enc_data").trim();
			}
			log.info("retInfo ::=============================>"+retInfo);

			if("".equals(retInfo)) {
				model.addAttribute("certificationResult", "N");
				throw new Exception("인증 실패");
			}

	        Ipin ipin = memberAuthFOComponent.getIPIN(reqIpinNum, retInfo);

	        model.addAttribute(SessionEnum.IPIN.toString(), JsonService.marshalling(ipin));

			if(!"Y".equals(ipin.getResult()) || StringUtils.isEmpty(ipin.getDiscrhash())){
				model.addAttribute("certificationResult", "N");
				throw new Exception("인증 실패");
			} else {
				model.addAttribute("certificationResult", "Y");
				request.getSession().setAttribute(SessionEnum.IPIN.toString(),ipin);
			}

	        DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
	        Date tempDate = sdFormat.parse(ipin.getBirth());
	        
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.YEAR, -14);
	        Date before14 = calendar.getTime();
	        
	        boolean is14 = false;
	        
	        if(before14.compareTo(tempDate) == 0 ){
	        	is14 = true;
	        }else is14 = before14.compareTo(tempDate) > 0;
	        
	        model.addAttribute("is14", JsonService.marshalling(is14));
	        String srv = (String)request.getSession().getAttribute("srv");
	        model.addAttribute("srv", JsonService.marshalling(srv));

	        // 본인인증 후 세팅
	        memberAuthFOComponent.setModelForCertificationAfter(model, pk, srv, ipin.getDiscrhash(), ipin.getCiscrhash());
	        
        }
        catch (Exception e) {
	        log.error("",e);
        }
		return "tiles:member/popup/certification.nice.complete";
	}
	
	/**
	 * 휴대폰 인증후 데이터 받아주는 페이지
	 * 
	 *
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2018. 4. 30
	 */
	@RequestMapping(value = {"/pccNiceSuccess.popup", "/pccNiceFail.popup"}, method = {RequestMethod.POST, RequestMethod.GET } )
	public String pccNiceComplete(HttpServletRequest request, Model model) {
		try {
			
			SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
			
			String reqPccNum = "";
			if(!StringUtils.isEmpty(request.getSession().getAttribute("reqPccNum"))) {
				reqPccNum = (String) request.getSession().getAttribute("reqPccNum");
			}

			log.info("reqPccNum ::=============================>"+reqPccNum);

			if("".equals(reqPccNum)) {
				model.addAttribute("certificationResult", "N");
				throw new Exception("인증 실패");
			}
			
			String retInfo = "";
			if(request.getParameter("EncodeData") != null) {
				retInfo = request.getParameter("EncodeData").trim();
			}
			log.info("retInfo ::=============================>"+retInfo);

			if("".equals(retInfo)) {
				model.addAttribute("certificationResult", "N");
				throw new Exception("인증 실패");
			}
			
			log.info("request.getRequestURI() ::=============================>"+request.getRequestURI());
			boolean successFlag = false;
			if(request.getRequestURI().indexOf("Success") > 0) {
				successFlag = true;	
			}
			Pcc pcc = memberAuthFOComponent.getPCC(reqPccNum, retInfo, successFlag);
	        
	        //이미 멤버십 가입되어 있으면 다시 안됨
	        
	        model.addAttribute(SessionEnum.PCC.toString(), JsonService.marshalling(pcc));

			if(!"Y".equals(pcc.getResult()) || StringUtils.isEmpty(pcc.getDi()) || StringUtils.isEmpty(pcc.getCi())){
				model.addAttribute("certificationResult", "N");
				throw new Exception("인증 실패");
			} else {
				model.addAttribute("certificationResult", "Y");
				request.getSession().setAttribute(SessionEnum.PCC.toString(),pcc);
			}

	        DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
	        Date tempDate = sdFormat.parse(pcc.getBirYMD());
	        
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.YEAR, -14);
	        Date before14 = calendar.getTime();
	        
	        boolean is14 = false;
	        
	        if(before14.compareTo(tempDate) == 0 ) {
	        	is14 = true;
	        }
	        else {
	        	is14 = before14.compareTo(tempDate) > 0;
	        }
	        
	        model.addAttribute("is14", JsonService.marshalling(is14));
	        String srv = (String)request.getSession().getAttribute("srv");
	        model.addAttribute("srv", JsonService.marshalling(srv));

	        // 본인인증 후 세팅
	        memberAuthFOComponent.setModelForCertificationAfter(model, pk, srv, pcc.getDi(), pcc.getCi());
	        
        }
        catch (Exception e) {
	        log.error("",e);
        }
		return "tiles:member/popup/certification.nice.complete";
	}
	
	
	
	/**
	 * MLB 회원가입시 본인인증 SMS
	 * @param request
	 * @param birthday
	 * @param gender
	 * @param mbrNm
	 * @param mobileCo
	 * @param phone
	 * @param model
	 */
	@RequestMapping(value = "/sendMobileCertSms.json", method = RequestMethod.POST )
	public void sendMobileCertSms(HttpServletRequest request
								,@RequestParam(value="birthday", required=true) String birthday
								,@RequestParam(value="gender", required=true) String gender
								,@RequestParam(value="mbrNm", required=true) String mbrNm
								,@RequestParam(value="mobileCo", required=true) String mobileCo
								,@RequestParam(value="mobileNumber", required=true) String mobileNumber
								, Model model){
		
		String sSiteCode 	= getConfigService().getProperty("ncp_base.cert.sitecode");		// NICE로부터 부여받은 사이트 코드
		String sSitePw 		= getConfigService().getProperty("ncp_base.cert.sitepassword");		// NICE로부터 부여받은 사이트 패스워드
		
		String sJumin		= birthday.substring(2) + gender;		// 주민등록번호 앞 7자리
		String sName		= mbrNm;					// 성명
		String sMobileCo	= mobileCo;					// 이통사 구분 (SKT : 1 / KT : 2 / LG : 3 / SKT알뜰폰 : 5 / KT알뜰폰 : 6 / LGU+알뜰폰 : 7)
		String sMobileNo	= mobileNumber;					// 휴대폰 번호
		
		String errMsg = "입력하신 정보가 일치하지 않습니다. <br>[성명] , [휴대폰번호], [생년월일], [성별], [통신사] 정보를<br> 확인 후 정확하게 입력하여 주시기 바랍니다.";
		/*
		log.debug("===sSiteCode=" + sSiteCode);
		log.debug("===sSitePw=" + sSitePw);
		log.debug("===sJumin=" + sJumin);
		log.debug("===sName=" + sName);
		log.debug("===sMobileCo=" + sMobileCo);
		log.debug("===sMobileNo=" + sMobileNo);
		*/
		
		request.getSession().removeAttribute(SessionEnum.PCC.toString());
		
		/*
		┌ sCPRequest 변수에 대한 설명  ─────────────────────────────────────────────────────
			[CP 요청번호]로 귀사에서 데이타를 임의로 정의하시면 됩니다.
			
			CP 요청번호는 인증 완료 후, 결과 데이타에 함께 제공되며
			데이타 위변조 방지 및 특정 사용자가 요청한 것임을 확인하기 위한 목적으로 이용하실 수 있습니다.
			
			따라서 귀사의 프로세스에 응용하여 이용할 수 있는 데이타이기에, 필수값은 아닙니다.
		└────────────────────────────────────────────────────────────────────
		*/
		int numLengthPhone = 6;
		String randomStrPhone = "";
		Random ranPcc = new Random();
		for (int i = 0; i < numLengthPhone; i++) {
			// 0 ~ 9 랜덤 숫자 생성
			randomStrPhone += ranPcc.nextInt(10);
		}
		Calendar today = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String day = sdf.format(today.getTime());
		String reqPccNum = day + randomStrPhone;
		
		request.getSession().setAttribute("reqPccNum", reqPccNum);
		String sCPRequest		= reqPccNum;		
		
		
		
		// 객체 생성
		MCheckPlus cpMobile = new MCheckPlus();
		
		int iReturn = -1;
		// Method 결과값(iReturn)에 따라, 프로세스 진행여부를 파악합니다.
		
		iReturn = cpMobile.fnRequestSafeAuth(sSiteCode, sSitePw, sJumin, sName, sMobileCo, sMobileNo, sCPRequest);
		log.info("===SMS iReturn : " + iReturn);
		model.addAttribute("iReturn", iReturn);
		// Method 결과값에 따른 처리사항
		if (iReturn == 0){
			/*
				- 응답코드에 따라 SMS 인증 여부를 판단합니다.
				- 응답코드 정의 : 첨부해드린 xls 파일을 참고하세요.
			*/
			log.debug("RETURN_CODE=" + cpMobile.getReturnCode());              // 인증결과코드
			log.debug("REQ_SEQ=" + cpMobile.getRequestSEQ());                  // 요청 고유번호
			log.debug("RES_SEQ=" + cpMobile.getResponseSEQ());                 // 응답 고유번호
			
			if(cpMobile.getReturnCode().equals("0000")){
				model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
				model.addAttribute("REQ_SEQ", cpMobile.getRequestSEQ());
				model.addAttribute("RES_SEQ", cpMobile.getResponseSEQ());
			}else if(cpMobile.getReturnCode().equals("0001")){
				model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
				//model.addAttribute("RETURN_MESSAGE", "요청 고유번호가 다릅니다. 다시 시도해 주세요.");
				model.addAttribute("RETURN_MESSAGE", "입력하신 정보가 일치하지 않습니다.\n[성명],[휴대폰번호],[생년월일],[성별],[통신사] 정보를 확인 후\n정확하게 입력하여 주시기 바랍니다.");				
			}else{
				model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
				//model.addAttribute("RETURN_MESSAGE", "요청 고유번호가 다릅니다. 다시 시도해 주세요.");
				model.addAttribute("RETURN_MESSAGE", errMsg);				
			}
			
			
      
		}else if (iReturn == -7 || iReturn == -8){
			log.debug("AUTH_ERROR=" + iReturn);
			log.debug("서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
			log.debug("IP : 121.131.196.200 / Port : 3700 ~ 3715");
			
			model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
			model.addAttribute("RETURN_MESSAGE", "서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
		}else if (iReturn == -9 || iReturn == -10 || iReturn == 12){
			log.debug("AUTH_ERROR=" + iReturn);
			log.debug("입력값 오류 : fnRequest 함수 처리시, 필요한 6개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");
			
			model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
			model.addAttribute("RETURN_MESSAGE", "입력값 오류 : fnRequest 함수 처리시, 필요한 6개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");
		}else{
			log.debug("AUTH_ERROR=" + iReturn);
			log.debug("iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해 주세요.");
			
			model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
			model.addAttribute("RETURN_MESSAGE", "iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해 주세요.");
		}	
	}
	
	
	@RequestMapping(value = "/sendMobileCertSmsCheck.json", method = RequestMethod.POST )
	public void sendMobileCertSmsCheck(HttpServletRequest request
								,@RequestParam(value="REQ_SEQ", required=true) String REQ_SEQ
								,@RequestParam(value="RES_SEQ", required=true) String RES_SEQ	
								,@RequestParam(value="sAuthNo", required=true) String sAuthNo	
								,@RequestParam(value="birthday", required=true) String birthday
								,@RequestParam(value="gender", required=true) String gender
								,@RequestParam(value="mbrNm", required=true) String mbrNm
								,@RequestParam(value="mobileCo", required=true) String mobileCo
								,@RequestParam(value="mobileNumber", required=true) String mobileNumber
								,@RequestParam(value="frgnrYn", required=true) String frgnrYn
								, Model model){
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		String errMsg = "입력하신 정보가 일치하지 않습니다. <br>[성명],[휴대폰번호],[생년월일],[성별],[통신사] 정보를<br>확인 후 정확하게 입력하여 주시기 바랍니다.";
			
		
		String sSiteCode 	= getConfigService().getProperty("ncp_base.cert.sitecode");		// NICE로부터 부여받은 사이트 코드
		String sSitePw 		= getConfigService().getProperty("ncp_base.cert.sitepassword");		// NICE로부터 부여받은 사이트 패스워드
	
		String sResSeq		= RES_SEQ;			// 응답 고유번호 (CPMobileStep1 에서 확인된 cpMobile.getResponseSEQ() 데이타)
		//String sAuthNo		= requestSEQ;			// SMS 인증번호    	
		String sCPRequest	= REQ_SEQ;					// 요청 고유번호 (CPMobileStep1 에서 정의한 cpMobile.getRequestSEQ() 데이타)
		
		log.debug("sCPRequest=" + sCPRequest);                  // 요청 고유번호
		
		
		// 객체 생성
		MCheckPlus cpMobile = new MCheckPlus();
		
		int iReturn = -1;
		// Method 결과값(iReturn)에 따라, 프로세스 진행여부를 파악합니다.
		iReturn = cpMobile.fnRequestConfirm(sSiteCode, sSitePw, sResSeq, sAuthNo, sCPRequest);
		
		if (iReturn == 0){
			/*
				- 응답코드에 따라 귀사의 기획의도에 맞게 진행하시면 됩니다.
				- 응답코드 정의 : 첨부해드린 xls 파일을 참고하세요.
			*/
			log.debug("RETURN_CODE=" + cpMobile.getReturnCode());              // 응답코드
			log.debug("CONFIRM_DATETIME=" + cpMobile.getConfirmDateTime());    // 인증 완료시간
			log.debug("REQ_SEQ=" + cpMobile.getRequestSEQ());                  // 요청 고유번호
			log.debug("RES_SEQ=" + cpMobile.getResponseSEQ());                 // 응답 고유번호
			log.debug("RES_CI=" + cpMobile.getResponseCI());                   // 아이핀 연결정보(CI)
			log.debug("RES_DI=" + cpMobile.getResponseDI());                   // 아이핀 중복가입확인정보(DI)
			
			if(!REQ_SEQ.equals(cpMobile.getRequestSEQ())){
				model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
				model.addAttribute("RETURN_MESSAGE", "요청 고유번호가 다릅니다. 다시 시도해 주세요.");
				//model.addAttribute("RETURN_MESSAGE", errMsg);
				model.addAttribute("certificationResult", "N");
			}else{
				
				if(cpMobile.getReturnCode().equals("0000")){
				
					try{
						DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
				        Date tempDate = sdFormat.parse(birthday);
				        
				        Calendar calendar = Calendar.getInstance();
				        calendar.add(Calendar.YEAR, -14);
				        Date before14 = calendar.getTime();
				        
				        boolean is14 = false;
				        
				        if(before14.compareTo(tempDate) == 0 ) {
				        	is14 = true;
				        }
				        else {
				        	is14 = before14.compareTo(tempDate) > 0;
				        }
				        
				        model.addAttribute("is14", JsonService.marshalling(is14));
					}catch(Exception e){
						log.debug("===is14 에러 : " + e.getMessage());
					}
					
					
					Pcc pcc= new Pcc();		        
					pcc.setName(mbrNm);
					pcc.setBirYMD(birthday);
					pcc.setSex(gender);
					pcc.setFgnGbn(frgnrYn);
					pcc.setDi(cpMobile.getResponseDI());
					pcc.setCi(cpMobile.getResponseCI());
					pcc.setCellNo(mobileNumber);
					pcc.setCellCorp(mobileCo);
					pcc.setCertDate(cpMobile.getConfirmDateTime());
					pcc.setResult("Y");
					//세션저장
					request.getSession().setAttribute(SessionEnum.PCC.toString(),pcc);
					
					// 본인인증 후 세팅
			        memberAuthFOComponent.setModelForCertificationAfter(model, pk, "newjoin", cpMobile.getResponseDI(), cpMobile.getResponseCI());			
					
					model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
					model.addAttribute("CONFIRM_DATETIME", cpMobile.getConfirmDateTime());
					model.addAttribute("REQ_SEQ", cpMobile.getRequestSEQ());
					model.addAttribute("RES_SEQ", cpMobile.getResponseSEQ());
					model.addAttribute("RES_CI", cpMobile.getResponseCI());
					model.addAttribute("RES_DI", cpMobile.getResponseDI());
					model.addAttribute("certificationResult", "Y");
					model.addAttribute("pcc", pcc);
				}else if(cpMobile.getReturnCode().equals("0001")){
					model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
					//model.addAttribute("RETURN_MESSAGE", "요청 고유번호가 다릅니다. 다시 시도해 주세요.");
					model.addAttribute("RETURN_MESSAGE", "인증 불일치<br>받은 문자를 정확히 입력해 주세요.");
					model.addAttribute("certificationResult", "N");
				}else{
					model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
					//model.addAttribute("RETURN_MESSAGE", "요청 고유번호가 다릅니다. 다시 시도해 주세요.");
					model.addAttribute("RETURN_MESSAGE", errMsg);
					model.addAttribute("certificationResult", "N");
				}
			}
		}else if (iReturn == -7 || iReturn == -8){
			log.debug("AUTH_ERROR=" + iReturn);
			log.debug("서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
			log.debug("IP : 121.131.196.200 / Port : 3700 ~ 3715");
			
			model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
			model.addAttribute("RETURN_MESSAGE", "서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
			model.addAttribute("certificationResult", "N");
			
		}else if (iReturn == -9 || iReturn == -10 || iReturn == 12){
			log.debug("AUTH_ERROR=" + iReturn);
			log.debug("입력값 오류 : fnRequest 함수 처리시, 필요한 5개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");
			
			model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
			model.addAttribute("RETURN_MESSAGE", "입력값 오류 : fnRequest 함수 처리시, 필요한 5개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");
			model.addAttribute("certificationResult", "N");
		}else{
			log.debug("AUTH_ERROR=" + iReturn);
			log.debug("iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해 주세요.");
			
			model.addAttribute("RETURN_CODE", cpMobile.getReturnCode());
			model.addAttribute("RETURN_MESSAGE", "iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해 주세요.");
			model.addAttribute("certificationResult", "N");
		}	
	}
	
	
	
	
	/**
	 * MLB 회원가입시 본인인증 SMS
	 * @param request
	 * @param birthday
	 * @param gender
	 * @param mbrNm
	 * @param mobileCo
	 * @param phone
	 * @param model
	 */
	@RequestMapping(value = "/sendMobileCertSmsTest.json", method = RequestMethod.POST )	
	public void sendMobileCertSmsTest(HttpServletRequest request
								,@RequestParam(value="birthday", required=true) String birthday
								,@RequestParam(value="gender", required=true) String gender
								,@RequestParam(value="mbrNm", required=true) String mbrNm
								,@RequestParam(value="mobileCo", required=true) String mobileCo
								,@RequestParam(value="mobileNumber", required=true) String mobileNumber
								, Model model){
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		model.addAttribute("RETURN", memberJoinFOComponent.sendCertSms(pk, birthday, gender, mbrNm, mobileCo, mobileNumber));
	}

}
