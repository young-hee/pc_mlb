package com.plgrim.ncp.web.pc.mlb.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatHistExtends;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.promotion.adapter.PromotionAdapter;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponIssueSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

@Controller
@Slf4j
@RequestMapping("/common")
public class InformationPageController extends AbstractController{

	@Autowired
	private DisplaySelectComponent displaySelectComponent;
	
	@Autowired
	private PromotionAdapter promotionAdapter;
	
	/**
	 * Gets the terms and conditions.
	 *
	 * @param model the model
	 * @param request the request
	 * @return the terms and conditions
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/information/termsAndConditions", method = RequestMethod.GET)
	public String getTermsAndConditions(Model model, @RequestParam(value = "agreementNo" ,required = false) String agreementNo, HttpServletRequest request) throws Exception {
		
		
		/** 화면 제목 세팅 시작 **/
		model.addAttribute("titleSetKey", "common.terms.ttl1");
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeHomeLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
        location.put("url", "/helpdesk");
        location.put("msgKey", "common.terms.ttl2");
        locationSet.add(location);
		
		Map<String, String> location2 = new HashMap<String, String>();
        location2.put("url", "/common/information/termsAndConditions?agreementNo=1");
        location2.put("msgKey", "common.terms.ttl1");
        locationSet.add(location2);
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/		
		
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		// 온라인 사이트 이용약관
		SysStplat onlineSiteUsefStplat = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.ONLNE_SITE_USEF_STPLAT.toString());
		model.addAttribute("onlineSiteUsefStplat", onlineSiteUsefStplat);

		// 개인정보 수집 및 이용동의
		SysStplat psnlInfoTrtmntPolicy = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.PSNL_INFO_TRTMNT_POLCY.toString());
		model.addAttribute("psnlInfoTrtmntPolicy", psnlInfoTrtmntPolicy);

		// 개인정보 수집 및 이용동의 history
		List<SysStplatHistExtends> sysStplatHistExtendsList = displaySelectComponent.selectSysStplatHist(pk, MemberEnum.StplatCd.PSNL_INFO_TRTMNT_POLCY.toString());
		model.addAttribute("sysStplatHistExtendsList", sysStplatHistExtendsList);
		
		model.addAttribute("agreementNo", StringUtils.defaultIfEmpty(agreementNo, "1"));
		return "tiles:common/terms.conditions";
		
	}
	
	/**
	 * 이메일 무단 수집 거부  조회
	 * 
	 * @param shopSearchDTO
	 * @return
	 */

	@RequestMapping(value = "/information/getEmailWonticeColctReject.json", method = RequestMethod.POST, produces = { "application/json" })
	public void getEmailWonticeColctReject(Model model, HttpServletRequest request) throws Exception {
		try{
			SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);			
			SysStplat emailWonticeColctReject = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.EMAIL_WONTICE_COLCT_REJECT.toString());
			model.addAttribute("emailWonticeColctReject", emailWonticeColctReject);
		}
		catch (Exception e) {
	        log.error("",e);
        }				
	}	
	
	private List<Map<String, String>> makeHomeLocationSet() {
		
        Map<String, String> location = null;
        
        List<Map<String, String>> locationSet = new ArrayList<Map<String, String>>();
        
        location = new HashMap<String, String>();
        location.put("url", "/main/mall/view");
        location.put("msgKey", "common.location.home");
        locationSet.add(location);

        return locationSet;
	}
	
	
	
	@RequestMapping(value = "/inf/erpCoupon.json", method = RequestMethod.GET, produces = { "application/json" })
	public void erpCoupon(HttpServletRequest request
							,@RequestParam(value="stdDt", required=true) String stdDt
							,@RequestParam(value="brandId", required=true) String brandId
							,@RequestParam(value="cid", required=false) String cid
							,Model model
			) throws Exception {
		
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setDvcCd("PC");
		adapterHeader.setLangCd(String.valueOf(GoodsEnum.KOR));
		
		OnOffCouponIssueSDO onOffCouponIssueSDO = promotionAdapter.getOnOffCouponIssueList(stdDt, brandId, cid, adapterHeader);	
		
		model.addAttribute("res", onOffCouponIssueSDO);
	}	
	
	public String getRequestId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
