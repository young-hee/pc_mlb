package com.plgrim.ncp.web.pc.mlb.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberBenefitApiResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitResult;
import com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimCommandComponent;
import com.plgrim.ncp.commons.repository.SysExchgRepository;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderFormController extends OrderController {

    @Autowired
    DisplaySelectComponent displaySelectCommant;

    @Autowired
    ClaimCommandComponent claimCommandComponent;

    @Autowired
    SysExchgRepository sysExchgRepository;

    @Autowired
    DisplaySelectComponent displaySelectComponent;

    @Autowired
    MemberBenefitFOComponent memberBenefitFOComponent;
    
    @Value("${ncp_web_pc_sf.order.success.no}")
    private String orderSuccessNo;

    @RequestMapping(value = "/orderform/new", method = RequestMethod.GET)
    public String newOrder(Model model, HttpServletRequest request,@RequestParam(value = "cartNationCd", required = false) String cartNationCd) throws Exception {

    	log.info(CommonResponseCode.OD_00019_주문서_진입_시작.toMessage());

    	/** 화면 제목 세팅 시작 **/
		model.addAttribute("titleSetKey", "order.js.txt.title");
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
        Map<String, String> location = null;
        List<Map<String, String>> locationSet = new ArrayList<Map<String, String>>();
        
        location = new HashMap<String, String>();
        location.put("url", "/");
        location.put("msgKey", "common.location.home");
        locationSet.add(location);

        location = new HashMap<String, String>();
        location.put("url", "/cart/goods/list");
        location.put("msgKey", "order.js.txt.title");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        if (ContextService.hasRole()) {
        	SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
        	model.addAttribute("mbr",userDetail.getMbr());
        }
        SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
    	// 구매이용
 		SysStplat buyerInform = displaySelectComponent.selectSysStplatCont(systemPK, MemberEnum.StplatCd.ONLNE_SITE_USEF_STPLAT.toString());
 		model.addAttribute("buyerInform", buyerInform);

 		// 인정보수집 및 이용에 대한 안내 (
 		SysStplat personalInform = displaySelectComponent.selectSysStplatCont(systemPK, MemberEnum.StplatCd.NMBR_PSNL_INFO_COLCT_USEF_AGR_ORD.toString());
 		model.addAttribute("personalInform", personalInform);
        
 		if (ContextService.hasRole()) {
	        // 혜택API - 장바구니 쿠폰 혜택
	        MemberBenefitApiResult apiResult = memberBenefitFOComponent.callMemberBenefit(systemPK, MemberBenefitEnum.BnefSectCd.BSK_CPN_BNEF, null);
	        
	        log.info("MEMBER_BENEFIT_API : ResultCd({}),ResultMsg({})", apiResult.getResultCd(), apiResult.getResultMsg());
	        
	        if(apiResult != null && apiResult.getMemberBenefitResultList() != null) {
	            List<MemberBenefitResult> resultList = apiResult.getMemberBenefitResultList();
	
	            for(int i= 0; i < resultList.size(); i++) {
	                MemberBenefitResult memberBenefitResult = resultList.get(i);
	                log.info("MEMBER_BENEFIT_API_DETAIL : ResultCd({}),ResultMsg({})", memberBenefitResult.getResultCd(), memberBenefitResult.getResultMsg());
	            }
	        }
 		}
 		
        return "tiles:order/orderform/order.new";

    }
    
    

    
    

    @RequestMapping(value = "/{ordNo}/view", method = RequestMethod.GET)
    public String viewOrderComplete(@PathVariable String ordNo, Model model, HttpServletRequest request) throws Exception {
    	
    	/** 화면 제목 세팅 시작 **/
		model.addAttribute("titleSetKey", "order.js.txt.order.complete");
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
        Map<String, String> location = null;
        List<Map<String, String>> locationSet = new ArrayList<Map<String, String>>();
        
        location = new HashMap<String, String>();
        location.put("url", "/");
        location.put("msgKey", "common.location.home");
        locationSet.add(location);

        location = new HashMap<String, String>();
        location.put("url", "/cart/goods/list");
        location.put("msgKey", "cart.js.txt.title");
        locationSet.add(location);
        
        
      
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
        
    	model.addAttribute("ordNo", ordNo);
    	
        return "tiles:order/orderform/order.complete";
    }
    
}
