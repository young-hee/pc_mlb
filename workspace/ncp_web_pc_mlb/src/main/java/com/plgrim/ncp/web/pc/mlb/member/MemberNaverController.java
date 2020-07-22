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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntcExtend;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.commons.auth.naver.NaverAccessTokenValidator;
import com.plgrim.ncp.commons.auth.naver.NaverUserService;
import com.plgrim.ncp.commons.auth.naver.NaverValidationResult;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/naver")
public class MemberNaverController extends MemberBaseController {

	@Autowired
	CommonSelectComponent commonSelectComponent;
	
	@Autowired
	NaverUserService<MemberFoResult> naverUserService;
	
	@Autowired
	NaverAccessTokenValidator naverValidator;
	
	@Autowired
    IdGenService idGenService;
	
	/**
	 * 네이버 아이디 로그인 연결 팝업
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/linkNaverLogin.popup", method = {RequestMethod.POST,RequestMethod.GET}  )
	public String linkNaverLoginPopup(HttpServletRequest request, Model model){
		return "member/naver/popup/link.naver.login.popup";	
		
	}
	
	/**
	 * 네이버 아이디 연결 팝업
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/linkNaver.popup", method = {RequestMethod.POST,RequestMethod.GET}  )
	public String linkNaverPopup(HttpServletRequest request, Model model){
		return "member/naver/popup/link.naver.popup";
	}
	
	/**
	 * 네이버 연결 및 해제
	 * 
	 * @param request
	 * @param model
	 * @param dto
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateNaverLinkInfo.json", method = {RequestMethod.POST}  )
	public void updateNaverLinkInfo(HttpServletRequest request, Model model, MemberFoDTO dto) throws Exception{
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		log.info("updateNaverLinkInfo========"+dto);
		
		/*String idCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER.toString();
		if(pk.getMall().equals("MBM")){
			idCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER_MBM.toString();
		}else if(pk.getMall().equals("SAM")){
			idCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER_SAM.toString();
		}*/
		
		String idCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER_MBM.toString();
		
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();	
		
		Mbr mbr = userDetail.getMbr();
		MbrIdCntc mbrIdCntc= dto.getMbrIdCntc();
		
		String deleteYn = dto.getMbrIdCntc().getDeleteYn();
		if( "N".equals(deleteYn) && ("".equals(mbr.getMbrNo()) || "".equals(mbrIdCntc.getToknId()) || "".equals(mbrIdCntc.getLoginId())) ) {
			throw new UsernameNotFoundException("Bad credentials");
		}
		
		log.info("mbr=="+mbr);
		log.info("mbrIdCntc=="+mbrIdCntc);
		log.info("userDetail.getMbr().getMbrNo()=="+userDetail.getMbr().getMbrNo());
		log.info("userDetail.getMbr().getUdterId()=="+userDetail.getMbr().getUdterId());
		log.info("deleteYn====="+deleteYn);
		log.info("idCntcTpCd====="+idCntcTpCd);
		MbrIdCntc upterIdInfo = naverUserService.selectNaverUdterIdInfo(userDetail.getMbr().getMbrNo(), idCntcTpCd);
		
		log.info("upterIdInfo========"+upterIdInfo);
		
		String upterId = userDetail.getMbr().getMbrNo();
		if(upterIdInfo != null && !"".equals(upterIdInfo)){
			upterId = upterIdInfo.getUdterId();
		}
		
		log.info("upterId========"+upterId);
		
		// 연결하기의 경우
		if ("N".equals(deleteYn)) {
			String accessToken = mbrIdCntc.getToknId();
			log.info("accessToken========"+accessToken);	
			NaverValidationResult validationResult = naverValidator.validate(pk, accessToken);

			// 1. MBR_NO 중복 확인
			MbrIdCntcExtend mbrIdCntcExtend = new MbrIdCntcExtend();
			mbrIdCntcExtend.setMbrNo(userDetail.getMbr().getMbrNo());
			mbrIdCntcExtend.setMallId(pk.getMall());
			mbrIdCntcExtend.setIdCntcTpCd(idCntcTpCd);
			mbrIdCntcExtend.setDeleteYn("N");

			MbrIdCntc mbrIdCntcForValid = naverUserService.selectNaverUserInfo(mbrIdCntcExtend);
			
//			if(mbrIdCntcForValid != null && !"".equals(mbrIdCntcForValid.getToknId()) && !mbrIdCntc.getLoginId().equals(mbrIdCntcForValid.getToknId())) {
//				model.addAttribute("beforeConnect", "true");	//기존 연결된 계정이 있음
//			}else{
//				model.addAttribute("beforeConnect", "false");	//기존 연결된 계정이 없음
//			}
			
			if(mbrIdCntcForValid != null && !"".equals(mbrIdCntcForValid.getToknId()) && !mbrIdCntc.getLoginId().equals(mbrIdCntcForValid.getToknId())) {
				model.addAttribute("beforeConnect", "true");	//기존 연결된 계정이 있음
			}else{
				model.addAttribute("beforeConnect", "false");	//신규연결
			}
			
			// 2. 네이버 tokenid 중복 확인
			MbrIdCntcExtend mbrIdCntcExtendForDupl = new MbrIdCntcExtend();
			mbrIdCntcExtendForDupl.setToknId(validationResult.getUserId());
			mbrIdCntcExtendForDupl.setMallId(pk.getMall());
			mbrIdCntcExtendForDupl.setIdCntcTpCd(idCntcTpCd);
			mbrIdCntcExtendForDupl.setDeleteYn("N");

			MbrIdCntc mbrIdCntcForValidForDupl = naverUserService.selectNaverUserInfo(mbrIdCntcExtendForDupl);
			
			// 3. 기존 다른 온라인ID 연결 해제
			if(mbrIdCntcForValidForDupl != null && !"".equals(mbrIdCntcForValidForDupl.getToknId()) && mbrIdCntc.getLoginId().equals(mbrIdCntcForValidForDupl.getToknId())) {
				// 연결 해제
				naverUserService.updateMemberNaverUserId(mbrIdCntcForValidForDupl.getMbrNo(), null, upterIdInfo.getLoginId());
			}
			
			// 4. 현재 온라인ID 연결
			naverUserService.updateMemberNaverUserId(userDetail.getMbr().getMbrNo(), validationResult.getUserId(), validationResult.getEmail());
			model.addAttribute("connectFlag", "true");
			
		}
		else {
			// 연결 해제
			naverUserService.updateMemberNaverUserId(userDetail.getMbr().getMbrNo(), null, upterIdInfo.getLoginId());
		}
	}
	
	/**
	 * 네이버 연동 상태 조회
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/linkNaverStatus.json", method = {RequestMethod.POST, RequestMethod.GET} )
	public void linkNaverStatus(HttpServletRequest request, Model model) {
		log.debug("{}",  request.getRequestURI());
		
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
				
		String idCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER.toString();
		if(systemPK.getMall().equals("MBM")){
			idCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER_MBM.toString();
		}else if(systemPK.getMall().equals("SAM")){
			idCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER_SAM.toString();
		}
		
		String linkNaverFlag = "false";
		
		if(ContextService.hasRole()) {
			//String idCntcTpCd = "NAVER";
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			Mbr mbr = userDetail.getMbr();
			
			MbrIdCntcExtend mbrIdCntcExtend = new MbrIdCntcExtend();
			mbrIdCntcExtend.setMbrNo(mbr.getMbrNo());
			mbrIdCntcExtend.setIdCntcTpCd(idCntcTpCd);
			mbrIdCntcExtend.setDeleteYn("N");
			MbrIdCntc mbrIdCntc = naverUserService.selectNaverUserInfo(mbrIdCntcExtend);
			
			if(mbrIdCntc != null) {
				linkNaverFlag = "true";
			}
		}
		
		model.addAttribute("linkNaverFlag", linkNaverFlag);
	}
	
	
}
