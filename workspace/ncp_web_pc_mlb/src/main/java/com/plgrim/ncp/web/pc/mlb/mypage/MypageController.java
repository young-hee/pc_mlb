package com.plgrim.ncp.web.pc.mlb.mypage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.enums.BskEnum.MbrSect;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.biz.display.data.DspCtgryDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.result.DxmDspFoCtgryResult;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberBenefitApiResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitResult;
import com.plgrim.ncp.cmp.display.fo.DxmDisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.OrderSelectComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.member.data.MemberMileageInfoSDO;

import lombok.extern.slf4j.Slf4j;

/**
 * mypage base controller
 * 
 * @author Seed
 *
 */
@Slf4j
@Controller
@RequestMapping("/mypage")
public class MypageController extends AbstractController {
	
	@Autowired
	private OrderSelectComponent orderSelectComponent;
	
	@Autowired
	MemberBenefitFOComponent memberBenefitFOComponent;
	
	@Autowired
	DxmDisplaySelectComponent dxmDisplaySelectComponent;
	
	@Autowired
	MemberActivityFOComponent memberActivityFOComponent;
	
	/**
	 * 마이페이지 SUB-MAIN
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/view", method = {RequestMethod.GET, RequestMethod.POST})
	public String getCouponList(@ModelAttribute  MypageFoDTO mypageFoDTO , Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		/** 화면 location 세팅 시작 **/
		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		
		mypageFoDTO.setMbr(userDetail.getMbr());
		mypageFoDTO.setHistoryYn("N");
		mypageFoDTO.setBrand("M");
		
		String mbrNm = mypageFoDTO.getMbr().getMbrNm();
		
		// 마일리지 내역 조회
		MemberMileageInfoSDO memberMileageInfoSDO = memberBenefitFOComponent.selectMileageInfo(pk, mypageFoDTO);
		//My Coupon Cnt
		int couponCnt =  memberBenefitFOComponent.getMyCouponCntWithMall(pk, mypageFoDTO);
		// P포인트 현황 조회
		MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
		mbrWebpntHist.setMbrNo(mypageFoDTO.getMbr().getMbrNo());
		mbrWebpntHist.setMallId(pk.getMall());
		MbrWebpntHistExtend mbrWebpntHistExtend = memberBenefitFOComponent.selectWebPointInfo(mbrWebpntHist);
		// 회원등급조회
		MbrGrd mbrGrd = new MbrGrd();
		mbrGrd.setMbrNo(mypageFoDTO.getMbr().getMbrNo() );
		mbrGrd.setMallId(pk.getMall());
		
		mbrGrd = memberActivityFOComponent.selectMbrGrd(mbrGrd);
		
		model.addAttribute("mbrNm", mbrNm);
		model.addAttribute("mileageInfo", memberMileageInfoSDO);
		model.addAttribute("couponCnt", couponCnt);
		model.addAttribute("purpleCoinInfo", mbrWebpntHistExtend);
		model.addAttribute("mbrGrd", mbrGrd);
		
 		if (ContextService.hasRole()) {
	        // 혜택API - 장바구니 쿠폰 혜택
	        MemberBenefitApiResult apiResult = memberBenefitFOComponent.callMemberBenefit(pk, MemberBenefitEnum.BnefSectCd.BSK_CPN_BNEF, null);
	        
	        log.info("MEMBER_BENEFIT_API : ResultCd({}),ResultMsg({})", apiResult.getResultCd(), apiResult.getResultMsg());
	        
	        if(apiResult != null && apiResult.getMemberBenefitResultList() != null) {
	            List<MemberBenefitResult> resultList = apiResult.getMemberBenefitResultList();
	
	            for(int i= 0; i < resultList.size(); i++) {
	                MemberBenefitResult memberBenefitResult = resultList.get(i);
	                log.info("MEMBER_BENEFIT_API_DETAIL : ResultCd({}),ResultMsg({})", memberBenefitResult.getResultCd(), memberBenefitResult.getResultMsg());
	            }
	        }
 		}
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null) {  
			model.addAttribute("dspCtgryScFrDTO", (DspCtgryScFrDTO) flashMap.get("dspCtgryScFrDTO"));
		}
		else {
			model.addAttribute("dspCtgryScFrDTO", dspCtgryScFrDTO);
		}

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/mypage.view";
	}
	
	/**
	 * 마이페이지 LNB 조회를 위한 현재 URI에 대한 마이페이지 LNB 정보 조회
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param dspCtgryScFrDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mypageLnbCurrentCtgryList.json", produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> mypageLnbCurrentCtgryList(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="currentUri", required = true) String currentUri) throws Exception {

		DspCtgryScFrDTO dspCtgryScFrDTO = dxmDisplaySelectComponent.mypageLnbCurrentCtgryList(currentUri);
		Map<String, Object> map = new HashMap<>();
		map.put("currentDspCtgry", dspCtgryScFrDTO);
		return map;
	}
	
	/**
	 * 마이페이지 LNB 조회
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param dspCtgryScFrDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mypageLnbCtgryList.json", produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> lnbCtgryList(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

    	SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
    	DspCtgryDTO dspCtgryDTO = new DspCtgryDTO();
    	dspCtgryDTO.setMallId(pk.getMall());
    	dspCtgryDTO.setDevice(pk.getDevice());
    	dspCtgryDTO.setLang(pk.getLang());
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			dspCtgryDTO.setTgtMbrTpCd(userDetail.getMbr().getMbrTpCd());
			dspCtgryDTO.setAbsMbrAtrbCd(userDetail.getMbr().getMbrAtrbCd());  
        }else {
        	dspCtgryDTO.setTgtMbrTpCd(MbrSect.NMBR.toString());
        }
 
    	BeanUtils.copyProperties(dspCtgryDTO, dspCtgryScFrDTO);
    	
		dspCtgryScFrDTO.setUpperCategory("DMP");
		DxmDspFoCtgryResult dxmDspFoCtgryResult = dxmDisplaySelectComponent.lnbCtgryList(dspCtgryScFrDTO);
		Map<String, Object> map = new HashMap<>();
		map.put("lnbCategorySet", dxmDspFoCtgryResult.getDxmDspFoCtgryResults());
		map.put("ctgryNoDpth1", dspCtgryScFrDTO.getCtgryNoDpth1());
		map.put("ctgryNoDpth2", dspCtgryScFrDTO.getCtgryNoDpth2());
		map.put("ctgryNoDpth3", dspCtgryScFrDTO.getCtgryNoDpth3());
		return map;
	}
	
	/**
	 * 주문번호 검증
	 * (주문관련 페이지에서 인증정보로 주문번호를 검증하여 타인의 개인정보 조회 및 수정을 방지하는 용도로 사용한다.)
	 * 
	 * @param ordNo
	 * @throws RuntimeException
	 */
	protected void validOrderAuth(String ordNo) throws RuntimeException {
		
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		if(ContextService.hasRole()){
			//회원일 경우 mbrNo 로 주문번호 파라메터 변조에 대해 방어 (세션회원번호 / 파라메터로 넘어온 주문번호의 회원번호 체크)
			Ord ord = orderSelectComponent.selectOrdEntity(ordNo);
			if(!userDetail.getMbr().getMbrNo().equals(ord.getMbrNo())){
				// TODO : 익셉션 메시지 정의 필요
				throw new RuntimeException("invalid ordNo");
			}

		}else{
			//비회원일 경우 주문번호로 주문번호 파라메터 변조에 대해 방어 (세션주문번효 / 파라메터 주문번호 체크)
			if(!userDetail.getParameterMap().get("ordNo").equals(ordNo)){
				// TODO : 익셉션 메시지 정의 필요
				throw new RuntimeException("invalid ordNo");
			}
		}
	}
	
	/**
	 * 마이페이지 타이틀셋 설정
	 * 	- 프로퍼티명을 공통 관리하기 위해 메서드로 추가함.
	 * 
	 * @param model
	 */
	protected void setMypageTitleSetKey(Model model) {
		model.addAttribute("titleSetKey", "mypage.submain.ttl");
	}
	
	/**
	 * 마이페이지 location set 생성
	 * 	Home > 마이페이지
	 * 
	 * @return
	 */
	protected List<Map<String, String>> makeMypageLocationSet() {
		
        Map<String, String> location = null;
        
        List<Map<String, String>> locationSet = new ArrayList<Map<String, String>>();
        
        location = new HashMap<String, String>();
        location.put("url", "/main/mall/view");
        location.put("msgKey", "common.location.home");
        locationSet.add(location);

        location = new HashMap<String, String>();
        location.put("url", "/mypage/view");	// TODO : 마이페이지 메인 URI 확정 후 수정
        location.put("msgKey", "mypage.submain.ttl");
        locationSet.add(location);
        
        return locationSet;
	}
	
	protected List<Map<String, String>> makeMypageOrderLocationSet() {
        
        List<Map<String, String>> locationSet = this.makeMypageLocationSet();
        
        Map<String, String> location = new HashMap<String, String>();
        location.put("url", null);
        location.put("msgKey", "mypage.order.ttl");
        locationSet.add(location);
        
        return locationSet;
        
	}
	
	protected void setOrderListDspCtgryScFrDTO(Model model, String ctgryNo) {
		
		DspCtgryScFrDTO dspCtgryScFrDTO = new DspCtgryScFrDTO();
		
		dspCtgryScFrDTO.setDspCtgryNo(ctgryNo);
		dspCtgryScFrDTO.setCurrentCtgryDpthCd("3");
		dspCtgryScFrDTO.setCtgrySectCd("GNRL_CTGRY");
		dspCtgryScFrDTO.setCtgryNoDpth1(ctgryNo.substring(0, 6));
		dspCtgryScFrDTO.setCtgryNoDpth2(ctgryNo.substring(0, 9));
		dspCtgryScFrDTO.setCtgryNoDpth3(ctgryNo.substring(0, 12));
		
		model.addAttribute("dspCtgryScFrDTO", dspCtgryScFrDTO);
	}
}
