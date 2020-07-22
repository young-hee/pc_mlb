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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.newsletter.data.NewsLetterDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberJoinFOComponent;
import com.plgrim.ncp.cmp.newsletter.fo.NewsLetterFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.SecurityParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/guest")
public class GuestController extends MemberBaseController {

	@Autowired
	CommonSelectComponent commonSelectComponent;
	
	@Autowired
	private MemberActivityFOComponent memberActivityFOComponent;
	
	@Autowired
	private MemberJoinFOComponent memberJoinFOComponent;
	
	@Autowired
	private NewsLetterFOComponent newsLetterFOComponent;
	
	/**
	 * 비회원 주문 클릭시 진입
	 * 비회원 권한 가져오기
	 * 
	 * @param request [설명]
	 * @param model [설명]
	 * @since 2018. 5. 25
	 */
	@RequestMapping(value = "/authGuestRole.json", method = {RequestMethod.POST, RequestMethod.GET} )
	public void nonMbrOrder(HttpServletRequest request, Model model){
		try{
			memberJoinFOComponent.nonMbrAuthentication();
		}
		catch (Exception e) {
	        log.error("",e);
        }
	}
	
	/**
	 * 회원 주문 존재 여부 조회
	 *
	 * @param mypageOrderInfoDTO
	 * @param model
	 * @param request
	 * @since 2018. 5. 25
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/isExistOrder.json", method = {RequestMethod.GET, RequestMethod.POST})
	public Model selectFoExistOrder(@ModelAttribute  MypageOrderInfoDTO mypageOrderInfoDTO , Model model, HttpServletRequest request) throws Exception {
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		mypageOrderInfoDTO.setMallId(pk.getMall());
		int orderCount = memberActivityFOComponent.selectFoOrderListCount(pk, mypageOrderInfoDTO);
		
		if(orderCount > 0) {
			SecurityUserDetail userDetail = new SecurityUserDetail();
			Mbr mbr = new Mbr(); 
			userDetail.setMbr(mbr);
			
			Map<String,String> parametetMap = new HashMap<String,String>();
			parametetMap.put("cstmrNm", mypageOrderInfoDTO.getCstmrNm());
			parametetMap.put("cstmrMobilNo", mypageOrderInfoDTO.getCstmrMobilNo());
			parametetMap.put("ordNo",  mypageOrderInfoDTO.getOrdNo());
			userDetail.setParameterMap(parametetMap);
	
	        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
	        //spring 4.0 변경
	          
	        grantedAuthorities.add(new SimpleGrantedAuthority(SecurityParam.GUEST_ROLE.toString()));
	        Authentication authenticatedUser = new UsernamePasswordAuthenticationToken(userDetail, null, grantedAuthorities);
	        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		}
		model.addAttribute("orderCount", orderCount);
		return model;
	}
	
	
	@RequestMapping(value = "/newsLetterEmail.json", method = {RequestMethod.POST} )	
	public void newsLetterEmail(HttpServletRequest request, NewsLetterDTO dto, Model model){
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		dto.setMallId(pk.getMall());
		newsLetterFOComponent.insertNewsLetter(dto);
		
		model.addAttribute("SUCCESS", "SUCCESS");
	}
	
	
}
