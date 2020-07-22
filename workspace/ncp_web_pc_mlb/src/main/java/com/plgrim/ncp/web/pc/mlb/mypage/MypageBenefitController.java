package com.plgrim.ncp.web.pc.mlb.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.goods.data.GoodsPriceSearchDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberBenefitApiResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.result.MypageCpnFoResult;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent;
import com.plgrim.ncp.cmp.display.fo.DxmDisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsPriceFOComponent;
import com.plgrim.ncp.cmp.promotion.fo.PromotionCouponFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.commons.CommonSelectComponentImpl;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;
import com.plgrim.ncp.interfaces.member.data.MemberMileageInfoSDO;
import com.plgrim.ncp.interfaces.member.data.MembershipCardSDO;
import com.plgrim.ncp.interfaces.member.data.MileageHistInofSDO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage/benefit")
public class MypageBenefitController extends MypageController{

	@Autowired
	CommonSelectComponent commonSelectComponent;

	@Autowired
	CommonSelectComponentImpl commonSelectComponentImpl;

	@Autowired
	DisplaySelectComponent displaySelectComponent;

	@Autowired
	DxmDisplaySelectComponent dxmDisplaySelectComponent;

	@Autowired
	MemberActivityFOComponent memberActivityFOComponent;

	@Autowired
	MemberBenefitFOComponent memberBenefitFOComponent;

	@Autowired
	GoodsPriceFOComponent goodsPriceFOComponent;

	@Autowired
	PromotionCouponFOComponent promotionCouponFOComponent;

	@Autowired
	IdGenService idGenService;

	/**
	 * P포인트 조회
	 *
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value="/listPoint" ,method = {RequestMethod.GET, RequestMethod.POST})
	public String getMyPurpleCoinList(Model model, MypageFoDTO mypageFoDTO , HttpServletRequest request ,
                                      @RequestParam(value="currentPage" , required = false ) String currentPage, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();

		Map<String, String> location = new HashMap<String, String>();

		location.put("url", null);
        location.put("msgKey", "mypage.submain.benefitinfo.ttl");
        locationSet.add(location);

        location = new HashMap<String, String>();

        location.put("url", "/mypage/benefit/listPoint");
        location.put("msgKey", "mypage.point.ttl1");
        locationSet.add(location);

        model.addAttribute("locationSet", locationSet);
        model.addAttribute("myPage",mypageFoDTO);

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		mypageFoDTO.setMbr(userDetail.getMbr());

		// P포인트 현황 조회
		MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
		mbrWebpntHist.setMbrNo(mypageFoDTO.getMbr().getMbrNo());
		mbrWebpntHist.setMallId("MBM");
		MbrWebpntHistExtend mbrWebpntHistExtend = memberBenefitFOComponent.selectWebPointInfo(mbrWebpntHist);
		//MbrWebpntHistExtend mbrWebpntHistExtend = memberBenefitFOComponent.selectWebPointInfo(mypageFoDTO.getMbr().getMbrNo());

        model.addAttribute("purpleCoinInfo", mbrWebpntHistExtend);

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/benefit/point.list";

	}

	/**
	 * P포인트 내역 조회
	 *
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value="/include/listPoint.ajax" ,method = {RequestMethod.GET, RequestMethod.POST})
	public String includePurpleCoinList(Model model, MypageFoDTO mypageFoDTO , HttpServletRequest request ,
									  @RequestParam(value="currentPage" , required = false ) String currentPage ) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		mypageFoDTO.setMbr(userDetail.getMbr());

		PageParam pageParam = getPageService().buildPageParam(StringUtils.defaultIfEmpty(currentPage , "1"), 10);

		MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
		mbrWebpntHist.setMbrNo(mypageFoDTO.getMbr().getMbrNo());
		mbrWebpntHist.setDateStart(mypageFoDTO.getDateStart().replaceAll("-", ""));
		mbrWebpntHist.setDateEnd(mypageFoDTO.getDateEnd().replaceAll("-", ""));

		// P포인트내역 목록 조회
		Page<MemberFoResult> result = memberBenefitFOComponent.getMyPurpleCoinListForMall(pk, mbrWebpntHist, mypageFoDTO.getLoginId(), pageParam);
		model.addAttribute("purpleCoinList", result.getContent() );
		PageService.makePageResult(result , model);

		return "tiles:mypage/benefit/include/point.list.include";

	}


	/**
	 * 회원등급소개
	 */
	@RequestMapping(value ="/membershipRateInfo")
	public String membershipRateInfo(Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{

		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();

		Map<String, String> location = new HashMap<String, String>();

		location.put("url", null);
        location.put("msgKey", "mypage.submain.benefitinfo.ttl");
        locationSet.add(location);

        location = new HashMap<String, String>();

        location.put("url", "/mypage/benefit/membership.info");
        location.put("msgKey", "mypage.membership.info.ttl1");
        locationSet.add(location);

        model.addAttribute("locationSet", locationSet);

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/benefit/membership.info";

	}

	/**
	 * 마일리지 조회
	 *
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value="/listMileage" ,method = {RequestMethod.GET, RequestMethod.POST})
	public String getMileageList(Model model, MypageFoDTO mypageFoDTO , HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

        this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();

		Map<String, String> location = new HashMap<String, String>();

		location.put("url", null);
        location.put("msgKey", "mypage.submain.benefitinfo.ttl");
        locationSet.add(location);

        location = new HashMap<String, String>();

        location.put("url", "/mypage/benefit/listMileage");
        location.put("msgKey", "mypage.mileage.ttl1");
        locationSet.add(location);

        model.addAttribute("locationSet", locationSet);

		model.addAttribute("myPage",mypageFoDTO);

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/benefit/mileage.list";

	}

	/**
	 * 마일리지 내역 조회
	 *
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value="/include/listMileage.ajax" ,method = {RequestMethod.GET, RequestMethod.POST})
	public String includeMileageList(Model model, MypageFoDTO mypageFoDTO , HttpServletRequest request ,
									  @RequestParam(value="currentPage" , required = false ) String currentPage ) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		if (StringService.isEmpty(currentPage)) {
			currentPage = "1";
		}

		PageParam pageParam = getPageService().buildPageParam(currentPage, 10);

		mypageFoDTO.setMbr(userDetail.getMbr());
		mypageFoDTO.setHistoryYn("Y");
		mypageFoDTO.setBrand("X");
		mypageFoDTO.setPageSize("10");
		mypageFoDTO.setPageNum(currentPage);

		// 마일리지 내역 조회
		MemberMileageInfoSDO memberMileageInfoSDO = memberBenefitFOComponent.selectMileageInfo(pk, mypageFoDTO);

		int mileageCnt = 0;
		if(memberMileageInfoSDO.getMileageHistList() != null && !memberMileageInfoSDO.getMileageHistList().equals(null)) {
			mileageCnt = memberMileageInfoSDO.getMileageHistList().size() ;
		}

		// 가상계좌 결재대기 포인트
		int unityPntUseSumAmt = memberBenefitFOComponent.selectOrdPntUseAmtSum(mypageFoDTO.getMbr().getMbrNo());

		if(memberMileageInfoSDO.getMileageHistList() != null && !memberMileageInfoSDO.getMileageHistList().equals(null)) {
			long totalRow = memberMileageInfoSDO.getTotalRow();

			List<MileageHistInofSDO> results = memberMileageInfoSDO.getMileageHistList();

			PageImpl<MileageHistInofSDO> pageResults = new PageImpl<MileageHistInofSDO>(results, pageParam.getPageable(), totalRow);

	        model.addAttribute("mileageInfo"       , memberMileageInfoSDO);
	        model.addAttribute("mileageList"       , memberMileageInfoSDO.getMileageHistList() );
	        model.addAttribute("mileageCnt"        , pageResults.getTotalElements());
	        model.addAttribute("unityPntUseSumAmt" , unityPntUseSumAmt);

	        PageService.makePageResult(pageResults, model);

		}else {

			model.addAttribute("mileageInfo"       , memberMileageInfoSDO);
	        model.addAttribute("mileageList"       , memberMileageInfoSDO.getMileageHistList() );
	        model.addAttribute("mileageCnt"        , mileageCnt);
	        model.addAttribute("unityPntUseSumAmt" , unityPntUseSumAmt);
		}


		return "tiles:mypage/benefit/include/mileage.list.include";
	}

	/**
	 * 멤버십 카드등록
	 *
	 * @param mypageFoDTO
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/memberCard.json",method = RequestMethod.POST, produces = { "application/json" })
	public void addMembershipCard(@ModelAttribute MypageFoDTO mypageFoDTO, HttpServletRequest request , Model model ) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		String membershipCardNo = request.getParameter("membershipCardNo");
		String certifyNo = request.getParameter("certifyNo");

		mypageFoDTO.setMbr(userDetail.getMbr());
		mypageFoDTO.setMembershipCardNo(membershipCardNo);
		mypageFoDTO.setCertifyNo(certifyNo);

		// 마일리지 내역 조회
		MembershipCardSDO membershipCardSDO = memberBenefitFOComponent.addMembershipCardOnCertify(pk, mypageFoDTO);

        model.addAttribute("resultCd", membershipCardSDO.getResultCd());
	}


	/**
	 * 쿠폰리스트
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/listCoupon", method = {RequestMethod.GET, RequestMethod.POST})
	public String getCouponList(@ModelAttribute  MypageFoDTO mypageFoDTO , Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();

		Map<String, String> location = new HashMap<String, String>();

		location.put("url", null);
        location.put("msgKey", "mypage.submain.benefitinfo.ttl");
        locationSet.add(location);

        location = new HashMap<String, String>();

        location.put("url", "/mypage/benefit/listCoupon");
        location.put("msgKey", "mypage.coupon.ttl1");
        locationSet.add(location);

        model.addAttribute("locationSet", locationSet);

		model.addAttribute("myPage",mypageFoDTO);

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
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

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/benefit/coupon.list";
	}


	/**
	 * 사용가능 쿠폰리스트
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value ="/include/availableCoupon.ajax")
	public String getMyCouponList(HttpServletRequest request, MypageFoDTO mypageFoDTO ,
			  @RequestParam(value="currentPage" , required = false ) String currentPage ,
			  @RequestParam(value="mypageMainYn", required = false ) String mypageMainYn, Model model ) throws Exception {

		if (StringService.isEmpty(currentPage)) {
			currentPage = "1";
		}

		PageParam pageParam = null;
		// 노출 갯수 조정
		if( "Y".equals(mypageMainYn) ) {
			pageParam = getPageService().buildPageParam(currentPage, 5);
		}else {
			pageParam = getPageService().buildPageParam(currentPage,10);
		}

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		mypageFoDTO.setMbr(userDetail.getMbr());

		// 쿠폰내역 목록 조회
		//List<MypageCpnFoResult> myCouponList = memberBenefitFOComponent.getMyCouponList(pk, mypageFoDTO);

		// My Coupon 이력조회
		Page<MypageCpnFoResult> myCouponList = memberBenefitFOComponent.getMyCouponListWithMall(pk, mypageFoDTO, pageParam);

		model.addAttribute("myCouponList", myCouponList.getContent());
		model.addAttribute("mypageMainYn", mypageMainYn);
		PageService.makePageResult(myCouponList, model);

		return "tiles:mypage/benefit/include/coupon.list.include";
	}

	/**
	 * 사용완료/기간만료 쿠폰리스트
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value ="/include/doneUsingCoupon.ajax")
	public String getMyCouponHistList(HttpServletRequest request, MypageFoDTO mypageFoDTO ,
			  @RequestParam(value="currentPage" , required = false ) String currentPage, Model model ) throws Exception {

		if (StringService.isEmpty(currentPage)) {
			currentPage = "1";
		}
		PageParam pageParam = getPageService().buildPageParam(StringUtils.defaultIfEmpty(currentPage , "1"), 10);

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		mypageFoDTO.setMbr(userDetail.getMbr());


		// My Coupon 이력조회
		Page<MypageCpnFoResult> myCouponHistList = memberBenefitFOComponent.getMyCouponHistListWithMall(pk, mypageFoDTO , pageParam);

	 	model.addAttribute("myCouponHistList", myCouponHistList.getContent());
		PageService.makePageResult(myCouponHistList, model);

		return "tiles:mypage/benefit/include/couponHist.list.include";
	}


	/**
	 * 회원 쿠폰 발행
	 *
	 * @param mypageFoDTO
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/issueCoupon.json",method = RequestMethod.POST, produces = { "application/json" })
	public void addMemberCouponIssue(@ModelAttribute MypageFoDTO mypageFoDTO, HttpServletRequest request , Model model ) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		String cpnCrtfcCd = request.getParameter("cpnCrtfcCd");

		mypageFoDTO.setMbr(userDetail.getMbr());

		//MbrIssuCpn issue = new MbrIssuCpn();

		log.info("MbrNo      ========> " + mypageFoDTO.getMbr().getMbrNo());
		log.info("MbrId      ========> " + mypageFoDTO.getMbr().getMbrId());
		log.info("CpnCrtfcCd ========> " + cpnCrtfcCd );

		mypageFoDTO.setMbrIssuCpn(new MbrIssuCpn());

		mypageFoDTO.getMbrIssuCpn().setMbrNo(mypageFoDTO.getMbr().getMbrNo());
		mypageFoDTO.getMbrIssuCpn().setRegtrId(mypageFoDTO.getMbr().getMbrNo());
		mypageFoDTO.getMbrIssuCpn().setUdterId(mypageFoDTO.getMbr().getMbrNo());
		mypageFoDTO.getMbrIssuCpn().setCpnCrtfcCd(cpnCrtfcCd);

		PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
		promotionBoDTO.setMbr(userDetail.getMbr());
		promotionBoDTO.setMbrIssuCpn(mypageFoDTO.getMbrIssuCpn());
		promotionBoDTO.setMallId(pk.getMall());
		promotionBoDTO.setLang(pk.getLang());
		promotionBoDTO.setDevice(pk.getDevice());
		promotionBoDTO.setAbsMbrTpCd(userDetail.getMbr().getMbrTpCd());
		promotionBoDTO.setAbsMbrAtrbCd(userDetail.getMbr().getMbrAtrbCd());
		promotionBoDTO.setAbsGepcoId(userDetail.getMbr().getPsitnGrpcoId());

		String resultMsg = promotionCouponFOComponent.addCouponCrtfcCdMemberIssue(promotionBoDTO);
		model.addAttribute("resultMsg", resultMsg);
	}

	/**
	 * 회원 쿠폰 적용상품조회
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/include/myCouponGoodList", method = {RequestMethod.GET, RequestMethod.POST})
	public String myCouponGood(@ModelAttribute  MypageFoDTO mypageFoDTO , Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		model.addAttribute("titleSetKey", "mypage.coupon.layer.ttl");

		String currentPage = "1";

		PageParam pageParam = getPageService().buildPageParam(currentPage, 10);

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		// 회원의 상품 쿠폰 목록 조회
		MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
		mbrIssuCpn.setCpnTpCd("GOD_CPN");

		mypageFoDTO.setMbrIssuCpn(mbrIssuCpn);
		mypageFoDTO.setPagingFlagYn("N");

		mypageFoDTO.setMbr(userDetail.getMbr());

		// My Coupon 이력조회
		Page<MypageCpnFoResult> myCouponList = memberBenefitFOComponent.getMyCouponListWithMall(pk, mypageFoDTO, pageParam);

		model.addAttribute("myCouponListForCouponName", myCouponList.getContent());
		model.addAttribute("myPage",mypageFoDTO);

		return "tiles:mypage/benefit/include/coupon.good.list";
	}

	/**
	 * 회원 쿠폰 적용상품조회 목록 조회
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/include/myCouponGoodListInclude", method = {RequestMethod.GET, RequestMethod.POST})
	public String myCouponGoodList(HttpServletRequest request, MypageFoDTO mypageFoDTO ,
			  @RequestParam(value="currentPage", required = false ) String currentPage, Model model ) throws Exception {

		if (StringService.isEmpty(currentPage)) {
			currentPage = "1";
		}
		// 전체 상품의 경우 검색어 제거 (2019-01-17)
		if (StringUtils.equals("ALL", mypageFoDTO.getAplGodSecCd())) {
			mypageFoDTO.setSrchKeyword(StringUtils.EMPTY);
		}

		PageParam pageParam = getPageService().buildPageParam(currentPage, 10);

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		mypageFoDTO.setMbr(userDetail.getMbr());

		GoodsPriceSearchDTO priceDTO = goodsPriceFOComponent.getMemberTypeForPriceForPC();
		mypageFoDTO.setSpcPrmTp(priceDTO.getSpcPrmTp());
		mypageFoDTO.setPrcSectCd(priceDTO.getPrcSectCd());

		// 회원 쿠폰 적용상품조회 목록 조회
		Page<MypageCpnFoResult> myCouponGoodList = memberBenefitFOComponent.getMyCouponGoodList(pk, mypageFoDTO, pageParam);

		model.addAttribute("myCouponGoodList", myCouponGoodList.getContent());

		PageService.makePageResult(myCouponGoodList, model);

		return "tiles:mypage/benefit/include/coupon.good.list.include";
	}

}
