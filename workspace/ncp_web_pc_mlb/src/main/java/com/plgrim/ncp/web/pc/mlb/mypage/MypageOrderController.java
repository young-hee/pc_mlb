package com.plgrim.ncp.web.pc.mlb.mypage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.plgrim.ncp.base.entities.datasource1.clm.ClmFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspFoExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodFoExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.helpdesk.data.HistoryInfoFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.order.data.MbrDelvSearchDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.data.NaverPayOpenDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.biz.order.result.MypageOrderFoResult;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.OrderCommandComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.OrderSelectComponent;
import com.plgrim.ncp.commons.data.order.KcpParamDTO;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;
import com.plgrim.ncp.interfaces.order.data.OrderFormerlyPurchaseSDO;
import com.plgrim.ncp.interfaces.order.data.OrderOfflinePurchaseSDO;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

@Slf4j
@Controller
@RequestMapping("/mypage/order")
public class MypageOrderController extends MypageController {
	
	@Autowired
	private MemberActivityFOComponent memberActivityFOComponent;
	
	@Autowired
	private OrderSelectComponent orderSelectComponent;
	
	@Autowired
	private OrderCommandComponent orderCommandComponent;
	
	@Autowired
	OrderSelectService orderSelectService;
	
	@Autowired
	DeliverySelectRepository deliverySelectRepository;
	/**
	 * 회원 주문 리스트 조회
	 * 
	 * @param mypageOrderFoResult
	 * @param model
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_GUEST')")
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String getMypageOrderList(@ModelAttribute MypageOrderFoResult mypageOrderFoResult, Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) {
		
		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageOrderLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", "/mypage/order/list");
        location.put("msgKey", "mypage.order.list.ttl");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageOrderFoResult.setMbrNo(userDetail.getMbr().getMbrNo());

		}

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/order/order.list";
	}

	/**
	 * 회원 주문 리스트 조회(Include)
	 * 
	 * @param mypageOrderInfoDTO
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_GUEST')")
	@RequestMapping(value = "/include/list.ajax", method = {RequestMethod.GET, RequestMethod.POST})
	public String getMypageOrderListInclude(@ModelAttribute  MypageOrderInfoDTO mypageOrderInfoDTO
			, Model model, HttpServletRequest request
			, @RequestParam(value="currentPage" , required = false ) String currentPage
			, @RequestParam(value="mypageMainYn" , required = false ) String mypageMainYn) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageOrderInfoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
		}else{
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageOrderInfoDTO.setCstmrNm(userDetail.getParameterMap().get("cstmrNm"));
			mypageOrderInfoDTO.setCstmrMobilNo(userDetail.getParameterMap().get("cstmrMobilNo"));
			mypageOrderInfoDTO.setOrdNo(userDetail.getParameterMap().get("ordNo"));

		}

		PageParam pageParam = getPageService().buildPageParam(StringUtils.defaultIfEmpty(currentPage, "1"), 10);
		
		Page<MypageOrderFoResult> result =  memberActivityFOComponent.selectFoOrderList(pk, mypageOrderInfoDTO, pageParam);
		PageService.makePageResult(result , model);
		
		List<MypageOrderFoResult> orderList = result.getContent();
		
		for(MypageOrderFoResult order : orderList){
			int clmCnt = 0;
			for(OrdGodFoExtend orderGod : order.getOrdGodList()){
				if("Y".equals(orderGod.getClmYn()) && orderGod.getRealOrdQty() > 0){
					clmCnt++;
				}
				
				// 교환상품을 반품시 교환클레임 완료아닌경우 버튼 비노출
				OrderBoDTO orderDTO = new OrderBoDTO();
				orderDTO.setOrdNo(order.getOrdNo());
				
				if (order.getDlvPcupspTurn() == null || order.getOrdGodTurn() == null) {
					continue;
				}
					
				orderDTO.setDlvPcupspTurn(Integer.parseInt(order.getDlvPcupspTurn()));
				int nOrdGodTurn = Integer.parseInt(order.getOrdGodTurn());
				
				List<OrdGodExtend> ordGodExtends = orderSelectComponent.selectCoOrdGodList(orderDTO);

				// 교환 진행중인 경우
				for (OrdGodExtend ordGodExtend : ordGodExtends) {
					
					if (nOrdGodTurn != ordGodExtend.getOrdGodTurn()) {
						continue;
					}
					
					if ("GNRL_EXCHG".equals(ordGodExtend.getClmTpCd()) == false) {
						continue;
					}
					
					if ("COMPT".equals(ordGodExtend.getClmStatCd()) || "WTHDRAW".equals(ordGodExtend.getClmStatCd())) {
						continue;
					}
					
					log.debug("getClmTpCd==> "+ordGodExtend.getClmTpCd()+"getClmStatCd=> "+ordGodExtend.getClmStatCd());
             	    log.debug(">> EXCHANGE_CLM_STATUS : {} >> ORD_NO : {} ", ordGodExtend.getClmStatCd(),order.getOrdNo());
               		orderGod.setClmYn("N");
               		
               		break;
				}
			}
			order.setClmCnt(clmCnt);
		}

		model.addAttribute("orderList", orderList);
		model.addAttribute("myPage", mypageOrderInfoDTO);
		model.addAttribute("mypageMainYn", mypageMainYn);

		return "tiles:mypage/order/include/order.list.include";
	}
	
	/**
	 * 회원 주문 상세 조회
	 * 
	 * @param mypageOrderInfoDTO
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/{ordNo}/view", method = {RequestMethod.GET, RequestMethod.POST})
	public String viewMypageOrderDetailInfo(@ModelAttribute  MypageOrderInfoDTO mypageOrderInfoDTO , Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		this.validOrderAuth(mypageOrderInfoDTO.getOrdNo());
		
		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageOrderLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
		location.put("url", "/mypage/order/" + mypageOrderInfoDTO.getOrdNo() + "/view");
		
		String ctgryNo = "";
		if ("Y".equals(mypageOrderInfoDTO.getClmYn())) {
			ctgryNo = "DMPA01A01A02";
	        location.put("msgKey", "mypage.claim.detail.ttl");
	        model.addAttribute("clmYn", "Y");
		} else {
			ctgryNo = "DMPA01A01A01";
	        location.put("msgKey", "mypage.order.detail.ttl");
		}
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		String vipYn = "N";
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageOrderInfoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
			model.addAttribute("nmbrYn", "N");//회원
			this.setOrderListDspCtgryScFrDTO(model, ctgryNo);
			
			// VIP회원 여부
			String vipList = deliverySelectRepository.getVipList();
			if(userDetail.getMbr() != null && userDetail.getMbr().getErpCstmrNo() != null
					&& vipList.indexOf(userDetail.getMbr().getErpCstmrNo()) != -1) {
				vipYn = "Y";
			}
		}else{
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			if(!"".equals(userDetail.getParameterMap().get("cstmrNm"))
					&&!"".equals(userDetail.getParameterMap().get("cstmrMobilNo"))
					&&!"".equals(userDetail.getParameterMap().get("ordNo"))){
				// 비회원 정보
				mypageOrderInfoDTO.setCstmrNm(userDetail.getParameterMap().get("cstmrNm"));
				mypageOrderInfoDTO.setCstmrMobilNo(userDetail.getParameterMap().get("cstmrMobilNo"));
				mypageOrderInfoDTO.setOrdNo(userDetail.getParameterMap().get("ordNo"));
				model.addAttribute("nmbrYn", "Y"); //비회원
			}
		}
		
		model.addAttribute("vipYn", vipYn);

		MypageOrderInfoDTO orderInfo = memberActivityFOComponent.selectFoOrderInfo(pk, mypageOrderInfoDTO);
		
		// 배송지별 동일 출고지 여부 체크
		for(LgsDlvspFoExtend lgsDlvspFoExtend: orderInfo.getLgsDlvspFoExtend()) {
			
			List<String> shopList = new ArrayList<String>();
			
			for(LgsDlvFoExtend lgsDlv : lgsDlvspFoExtend.getLgsDlvFoExtendList()) {
				for(OrdGodFoExtend ordGod : lgsDlv.getOrdGodList()) {
					if (ordGod.getDlvShopId() == null) {
						continue;
					}
					
					if (shopList.contains(ordGod.getDlvShopId())) {
						continue;
					}
					
					shopList.add(ordGod.getDlvShopId());
				}
			}
			
			if (shopList.size() > 1) {
				lgsDlvspFoExtend.setPlaceDiffYN("Y");
			} else {
				lgsDlvspFoExtend.setPlaceDiffYN("N");
			}
			
			log.debug("lgsDlvspFoExtend.getPlaceDiffYN : " + lgsDlvspFoExtend.getPlaceDiffYN());
		}

		int clmCnt = 0;
		for(LgsDlvspFoExtend lgsDlvsp : orderInfo.getLgsDlvspFoExtend()){
			for(LgsDlvFoExtend lgsDlv : lgsDlvsp.getLgsDlvFoExtendList()){
				for(OrdGodFoExtend ordGod : lgsDlv.getOrdGodList()){
					if("Y".equals(ordGod.getClmYn()) 
							&& ordGod.getRealOrdQty() > 0 
							&& "EXCHG".equals(ordGod.getDlivyDrctTpCd()) == false){
						clmCnt++;
					}
				}
			}
		}

		orderInfo.setClmCnt(clmCnt);

		// 주문 상세 조회
		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("myPage", mypageOrderInfoDTO);
		
		// 취소 정보
		String clmCancels[] = {"PART_CNCL","ALL_CNCL"};
		mypageOrderInfoDTO.setClmTpCd(clmCancels);
		List<ClmFoExtend> resultClmCancels = memberActivityFOComponent.selectClmFoList(pk, mypageOrderInfoDTO);
		List<ClmFoExtend> listClmCancels = new ArrayList<ClmFoExtend>();
		
		for(ClmFoExtend  list  : resultClmCancels){
			if(list.getRfdBnkAcctNo()!=null && !"".equals(list.getRfdBnkAcctNo())){
//				 TODO : 환불계좌..
//				String decryptedAccNo = rau.getDecryptRefundAccount(list.getRfdBnkAcctNo(), false, list.getClmNo());
//				list.setRfdBnkAcctNo(decryptedAccNo);
			}

			listClmCancels.add(list);
		}
		
		model.addAttribute("clmCancels", listClmCancels);
		// 반품 정보
		String clmReturns[] = {"RTGOD"};
		mypageOrderInfoDTO.setClmTpCd(clmReturns);
		List<ClmFoExtend> resultClmReturns = memberActivityFOComponent.selectClmFoList(pk, mypageOrderInfoDTO);
		List<ClmFoExtend> listClmReturns = new ArrayList<ClmFoExtend>();
		for(ClmFoExtend  list  : resultClmReturns){
			if(list.getRfdBnkAcctNo()!=null && !"".equals(list.getRfdBnkAcctNo())){
//				TODO : 환불계좌
//				String decryptedAccNo = rau.getDecryptRefundAccount(list.getRfdBnkAcctNo(), false, list.getClmNo());
//				list.setRfdBnkAcctNo(decryptedAccNo);
			}

			listClmReturns.add(list);
		}

		model.addAttribute("clmReturns", listClmReturns);
		// 교환 정보
		String ClmExchanges[] = {"GNRL_EXCHG"};
		mypageOrderInfoDTO.setClmTpCd(ClmExchanges);
		model.addAttribute("clmExchanges", memberActivityFOComponent.selectClmFoList(pk, mypageOrderInfoDTO));

		model.addAttribute("dlvYn", memberActivityFOComponent.selectDlvYn(pk , mypageOrderInfoDTO));
		
		return "tiles:mypage/order/order.view";
	}
	
	/**
	 * 구매확정 처리 - 상품단위
	 * 
	 * @param deliverySearchDTO
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/decision/update.json", method = {RequestMethod.GET, RequestMethod.POST})
	public void updateOrderDecision(MypageOrderInfoDTO mypageOrderInfoDTO, Model model, HttpServletRequest request) throws Exception {
		
		this.validOrderAuth(mypageOrderInfoDTO.getOrdNo());
		
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		
		if(ContextService.hasRole()){ // 회원
			mypageOrderInfoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
		}else{ // 비회원
			mypageOrderInfoDTO.setCstmrNm(userDetail.getParameterMap().get("cstmrNm"));
			mypageOrderInfoDTO.setCstmrMobilNo(userDetail.getParameterMap().get("cstmrMobilNo"));
			mypageOrderInfoDTO.setOrdNo(userDetail.getParameterMap().get("ordNo"));
		}
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		orderCommandComponent.updateOrderDecision(mypageOrderInfoDTO, pk);
	}
	
	/**
	 * 주문 배송지 변경 레이어
	 * 
	 * @param lgsDlvspFoExtend
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/delivery/edit", method = RequestMethod.POST)
	public String editOrderDeliveryLocationPop(@ModelAttribute LgsDlvspFoExtend lgsDlvspFoExtend, Model model,HttpServletRequest request) throws Exception {
		
		this.validOrderAuth(lgsDlvspFoExtend.getOrdNo());
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		List<MemberBoResult> memberBoResult = new ArrayList<MemberBoResult>();
		
		MbrDlvsp mbrDlvsp = new MbrDlvsp();
		LgsDlvsp lgsDlvsp =  new LgsDlvsp();

		boolean isMember = false;

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		if(ContextService.hasRole()){
			// 배송지 엔티티
			mbrDlvsp.setMbrNo(userDetail.getMbr().getMbrNo());
			memberBoResult = memberActivityFOComponent.getDeliveryLocationList(pk, mbrDlvsp,"");
			model.addAttribute("deliveryLocationList", memberBoResult);
			model.addAttribute("userDetail", userDetail.getMbr());
			lgsDlvspFoExtend.setMbrNo(userDetail.getMbr().getMbrNo());
			System.out.println(userDetail.getMbr().toString());
			isMember = true;
		}

		if ("pkupOrderChange".equals(lgsDlvspFoExtend.getType()) 
				|| "pkupReturnChange".equals(lgsDlvspFoExtend.getType()) 
				|| "pkupExchange".equals(lgsDlvspFoExtend.getType()) 
				|| "returnChange".equals(lgsDlvspFoExtend.getType())) {
			
			//비회원 주문 처리 추가
			if(isMember){
				lgsDlvsp =  memberActivityFOComponent.selectPkupOrderDeliveryLocationPop(pk, mbrDlvsp);
				lgsDlvsp.setOrdNo(lgsDlvspFoExtend.getOrdNo());
				model.addAttribute("orderDeliveryLocation", lgsDlvsp);

			}else{
				//비회원은 mbr_dlvsp 테이블에서 조회할 데이터가 없어서 배송지변경팝업이 가지고 있어야 하는 nation_cd 가 없어서 에러 발생
				//비회원에 대해 고려가 없었던 부분이므로 비회원일 경우 별도 프로세스가 필요할수도 있다고 판단되어 하단부를 복사하여 분기처리합니다
				//lgs_dlvsp 가 아닌쪽에서 조회하도록 변경시 nation_cd 누락되면 안됩니다
				if(lgsDlvspFoExtend.getOrdTpCd().equals("SHOP_PKUP_ORD")){
					lgsDlvsp.setOrdNo(lgsDlvspFoExtend.getOrdNo());
					model.addAttribute("orderDeliveryLocation", lgsDlvsp);
				}else{
					model.addAttribute("orderDeliveryLocation", memberActivityFOComponent.selectOrderDeliveryLocationPop(pk, lgsDlvspFoExtend));
				}
				
			}

		}else{
			model.addAttribute("orderDeliveryLocation", memberActivityFOComponent.selectOrderDeliveryLocationPop(pk, lgsDlvspFoExtend));
		}

		// 픽업 주문 일반 배송 변경시 사용
		model.addAttribute("type", lgsDlvspFoExtend.getType());
		model.addAttribute("ordTpCd", lgsDlvspFoExtend.getOrdTpCd());
		
		if (isMember) {
			Ord ord = new Ord();
			ord.setMbrNo(userDetail.getMbr().getMbrNo());
			model.addAttribute("dlvMemoList", orderSelectService.selectDeliveryMemoList(ord));
		}
		
		model.addAttribute("isMember", isMember);

		return "tiles:mypage/order/include/order.delivery.edit.include";

	}

	/**
	 * 배송지 목록 조회 레이어
	 * 
	 * @param mbrDelvSearchDTO
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/delivery/list", method = RequestMethod.POST)
	public String getOrderDeliveryLocationList(@ModelAttribute MbrDelvSearchDTO mbrDelvSearchDTO, Model model, HttpServletRequest request) throws Exception {
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		mbrDelvSearchDTO.setLang(systemPK.getLang());
		mbrDelvSearchDTO.setMallId(systemPK.getMall());
		mbrDelvSearchDTO.setDevice(systemPK.getDevice());

		mbrDelvSearchDTO.setDelvFlag("member");
		PageParam pageParam = getPageService().buildPageParam(mbrDelvSearchDTO.getPageNo(), 10000);
		Page<MbrDlvsp> mbrDlvspList = orderSelectComponent.selectOrderDeliveryList(mbrDelvSearchDTO, pageParam);

		model.addAttribute("mbrDlvspList", mbrDlvspList.getContent());
		
		mbrDelvSearchDTO.setDelvFlag("delv");
		pageParam = getPageService().buildPageParam(mbrDelvSearchDTO.getPageNo(), 5);
		Page<MbrDlvsp> ordDlvspList = orderSelectComponent.selectOrderDeliveryList(mbrDelvSearchDTO, pageParam);
		
		model.addAttribute("ordDlvspList", ordDlvspList.getContent());

		model.addAttribute("delvSeq", request.getParameter("delvSeq"));
		model.addAttribute("delvFlag", mbrDelvSearchDTO.getDelvFlag());
		PageService.makePageResult(mbrDlvspList, model);

		return "tiles:mypage/order/include/order.delivery.list.include";
	}

	
	/**
	 * 주문시 배송지 수정
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/delivery/update.json", method = RequestMethod.POST)
	public void updateOrderDeliveryLocation(@ModelAttribute LgsDlvsp lgsDlvsp, Model model,HttpServletRequest request,String defaultDelv,String addMbrDlvspCheck) throws Exception {
		
		this.validOrderAuth(lgsDlvsp.getOrdNo());
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		MypageOrderInfoDTO mypageOrderInfoDTO  =  new MypageOrderInfoDTO();
		MypageOrderInfoDTO result = new MypageOrderInfoDTO();
		
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageOrderInfoDTO.setOrdNo(lgsDlvsp.getOrdNo());
			mypageOrderInfoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
		}else{
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			if(!"".equals(userDetail.getParameterMap().get("cstmrNm"))
					&&!"".equals(userDetail.getParameterMap().get("cstmrMobilNo"))
					&&!"".equals(userDetail.getParameterMap().get("ordNo"))){
				// 비회원 정보
				mypageOrderInfoDTO.setCstmrNm(userDetail.getParameterMap().get("cstmrNm"));
				mypageOrderInfoDTO.setCstmrMobilNo(userDetail.getParameterMap().get("cstmrMobilNo"));
				mypageOrderInfoDTO.setOrdNo(userDetail.getParameterMap().get("ordNo"));
			}
		}
		
		result  = memberActivityFOComponent.selectFoOrderInfo(pk, mypageOrderInfoDTO);
		
		if( result == null){
			throw new Exception("Fail Order Info");
		}

		memberActivityFOComponent.updateDeliveryLocationChange(pk,lgsDlvsp);
		//회원배송지 추가
		if(ContextService.hasRole()) {

			if( "Y".equalsIgnoreCase(defaultDelv) || "Y".equalsIgnoreCase(addMbrDlvspCheck)) {
				memberActivityFOComponent.updateDeliveryLocationMbrDlvsp(pk, lgsDlvsp, defaultDelv, addMbrDlvspCheck);
			}
		}
	}
	
    /**
     * 결제수단 변경 레이어
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_GUEST')")
    @RequestMapping(value = "/payment/edit", method = RequestMethod.POST)
    public String selectFoOrderInfoPop(@ModelAttribute MypageOrderInfoDTO mypageOrderInfoDTO, Model model, HttpServletRequest request) throws Exception {
    	
    	this.validOrderAuth(mypageOrderInfoDTO.getOrdNo());
    	
        SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
        String mbrId = "비회원";
        String mbrEmail = "";
        if (ContextService.hasRole()) {
            SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
            mypageOrderInfoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
            mbrId = userDetail.getMbr().getMbrId();
            mbrEmail = userDetail.getMbr().getMbrEmail();
        }


        MypageOrderInfoDTO orderInfoDTO = memberActivityFOComponent.selectPayMethodChange(systemPK, mypageOrderInfoDTO);

        if ("pkupOrderChange".equals(mypageOrderInfoDTO.getType())) {
            List<MypageOrderInfoDTO> orderInfo = new ArrayList<MypageOrderInfoDTO>();
            orderInfo = memberActivityFOComponent.selectDlvCstSumAmt(systemPK, mypageOrderInfoDTO);
            // 결제 금액에 배송비 추가
            java.math.BigDecimal payExchgRtCrncySumAmt = orderInfoDTO.getPayExchgRtCrncySumAmt();
            java.math.BigDecimal dlvCstSumAmt = BigDecimal.ZERO;

            for (int i = 0; i < orderInfo.size(); i++) {
                dlvCstSumAmt = dlvCstSumAmt.add(orderInfo.get(i).getDlvCstSumAmt());
            }
            orderInfoDTO.setPayExchgRtCrncySumAmt(payExchgRtCrncySumAmt.add(dlvCstSumAmt));


            List<LgsDlvspFoExtend> dlvsp = mypageOrderInfoDTO.getLgsDlvspFoExtend();

            List<LgsDlvspFoExtend> orderInfoDlvsp = orderInfoDTO.getLgsDlvspFoExtend();


            orderInfoDlvsp.set(0, dlvsp.get(0));

            orderInfoDTO.setLgsDlvspFoExtend(orderInfoDlvsp);


        } else if ("clmDlvAmtPay".equals(mypageOrderInfoDTO.getType())) {

            orderInfoDTO.setPayExchgRtCrncySumAmt(new BigDecimal(orderInfoDTO.getPayCrncyPayAmt()));
        }
        orderInfoDTO.setType(mypageOrderInfoDTO.getType());
        model.addAttribute("orderInfo", orderInfoDTO);
        model.addAttribute("mbrEmail", mbrEmail);
        model.addAttribute("mbrId", mbrId);
        model.addAttribute("type", mypageOrderInfoDTO.getType());

        //회원배송지 추가체크 여부
        model.addAttribute("defaultDelv", mypageOrderInfoDTO.getDefaultDelv());
        model.addAttribute("addMbrDlvspCheck", mypageOrderInfoDTO.getAddMbrDlvspCheck());

        return "tiles:mypage/order/include/order.payment.edit.include";
    }
    
	/**
	 * 결제 PG 정보 조회
	 * 
	 * @param mypageOrderInfoDTO
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/payment/pginfo.json", method = RequestMethod.POST)
	public void getPaymentPGInfo(@ModelAttribute MypageOrderInfoDTO mypageOrderInfoDTO ,Model model , HttpServletRequest request) throws Exception {

		this.validOrderAuth(mypageOrderInfoDTO.getOrdNo());

		String payNo = orderCommandComponent.getNewPayNoForMypagePayChange(mypageOrderInfoDTO, request);
		// naver pay install
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
            SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
            mbrNo = userDetail.getMbr().getMbrNo();
        }
		 
		NaverPayOpenDTO npayDTO = new NaverPayOpenDTO();
		npayDTO.setMerchantPayKey(payNo); // 결제번호
		npayDTO.setMerchantUserKey(mbrNo); // 회원번호 (비회원 NMBR)
		npayDTO.setReturnUrl(getConfigService().getProperty("ncp_base.order.naverPay.returnUrl"));
		npayDTO.setClientId(getConfigService().getProperty("ncp_base.order.naverPay.mlb.clientId"));
		npayDTO.setMode(getConfigService().getProperty("ncp_base.order.naverPay.mode"));
		
		model.addAttribute("npayInfo", npayDTO);
		
		KcpParamDTO kcpDTO = new KcpParamDTO();
		kcpDTO.setOrdr_idxx(payNo);
		kcpDTO.setReq_tx(getConfigService().getProperty("ncp_base.order.kcp.rex.tx"));	    
		kcpDTO.setSite_cd(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.cd"));
		
		if (ContextService.hasRole()) {
	        SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
            String  erpCstmrNo = userDetail.getMbr().getErpCstmrNo();
            String cstrno = deliverySelectRepository.getVipList();
            if(StringService.isNotEmpty(erpCstmrNo)&&cstrno.indexOf(erpCstmrNo)!=-1){
                kcpDTO.setSite_cd(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.vip.cd"));
            }
        }
 
		kcpDTO.setSite_name(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.name"));
		kcpDTO.setQuota(getConfigService().getProperty("ncp_base.order.kcp.quota.month"));
		kcpDTO.setCurrency(getConfigService().getProperty("ncp_base.order.kcp.currency"));
		kcpDTO.setModule_type(getConfigService().getProperty("ncp_base.order.kcp.module.type"));
		kcpDTO.setEscw_used("Y");
		kcpDTO.setPay_mod("Y");
		kcpDTO.setDeli_term(getConfigService().getProperty("ncp_base.order.kcp.delivery.term"));
		//<!-- 2012년 8월 18일 정자상거래법 개정 관련 설정 부분 -->
		//<!-- 제공 기간 설정 0:일회성 1:기간설정(ex 1:2012010120120131)  -->
		kcpDTO.setGood_expr("0");
		kcpDTO.setVcnt_expire_term(getConfigService().getProperty("ncp_base.order.kcp.vcnt.expire.term"));
		
		
		model.addAttribute("kcpInfo",kcpDTO);
		
		model.addAttribute("payNo",payNo);
	}
    
	/**
	 * 가상계좌 결제수단 변경
	 * 
	 * @param orderDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_GUEST')")
    @RequestMapping(value = "/payment/changepay", method = RequestMethod.POST)
    public String rerunPayMethodChange(@ModelAttribute("orderDTO") OrderDTO orderDTO, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
        
    	this.validOrderAuth(orderDTO.getOrd().getOrdNo());
    	
    	SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
        
    	orderDTO.setMallId(systemPK.getMall());
        orderDTO.setLang(systemPK.getLang());
        orderDTO.setDevice(systemPK.getDevice());
        Mbr mbr = new Mbr();
        
        if (ContextService.hasRole()) {
            SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
            mbr = userDetail.getMbr();
        }
        orderDTO.setMbr(mbr);
        orderCommandComponent.rerunPayMethodChange(orderDTO, request);
        
        String redirectUrl = "redirect:/mypage/order/" + orderDTO.getOrd().getOrdNo() + "/view";
        
		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);
        
        return redirectUrl;
    }
    
    /**
     * 대량주문 결제 
     * @param orderDTO
     * @param request
     * @param dspCtgryScFrDTO
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_GUEST')")
    @RequestMapping(value = "/payment/paylagqtyord", method = RequestMethod.POST)
    public String updaePayMethodChange(@ModelAttribute("orderDTO") OrderDTO orderDTO, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
        
    	this.validOrderAuth(orderDTO.getOrd().getOrdNo());
    	
    	SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
        
        orderDTO.setMallId(systemPK.getMall());
        orderDTO.setLang(systemPK.getLang());
        orderDTO.setDevice(systemPK.getDevice());
        Mbr mbr = new Mbr();
        
        if (ContextService.hasRole()) {
            SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
            mbr = userDetail.getMbr();
        }
        
        orderDTO.setMbr(mbr);
        orderCommandComponent.updaePayMethodChange(orderDTO, request);
        
        String redirectUrl = "redirect:/mypage/order/" + orderDTO.getOrd().getOrdNo() + "/view";
        
        FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);
		
        return redirectUrl;
    }
    
	/**
	 * 배송완료 처리(고객).
	 * [#51486] FO 배송완료 버튼 생성요청
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @param model [설명]
	 * @return the delivery list json
	 * @throws Exception the exception
	 * @since 2017. 9. 28
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/deliveryStatus/update.json", method = {RequestMethod.GET, RequestMethod.POST})
	public void updateDeliveryStatus(DeliverySearchDTO deliverySearchDTO, Model model, HttpServletRequest request) throws Exception {
		
		this.validOrderAuth(deliverySearchDTO.getOrdNo());
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		
		if(ContextService.hasRole()){ // 회원
			deliverySearchDTO.setMbrNo(userDetail.getMbr().getMbrNo());
		}else{ // 비회원
			deliverySearchDTO.setCstmrNm(userDetail.getParameterMap().get("cstmrNm"));
			deliverySearchDTO.setCstmrMobilNo(userDetail.getParameterMap().get("cstmrMobilNo"));
			deliverySearchDTO.setOrdNo(userDetail.getParameterMap().get("ordNo"));
		}
		
		List<DeliveryOrderGoodResult> list = orderSelectComponent.getDeliveryListFO(pk, deliverySearchDTO); // 해당 주문건에 대한 배송 조회
		orderCommandComponent.updateDeliveryStatusFO(pk, list); // 해당 주문건에 대한 배송 상태 변경
	}
	
	/**
	 * 회원 영수증 리스트 조회
	 * 
	 * @param model
	 * @param pageNo
	 * @param request
	 * @param mypageOrderInfoDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/receipt/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String getReceiptList(Model model, @RequestParam(value = "pageNo", required = false) String pageNo, HttpServletRequest request , MypageOrderInfoDTO mypageOrderInfoDTO, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageOrderLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", "/mypage/order/receipt/list");
        location.put("msgKey", "mypage.order.receipt.ttl");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		PageParam pageParam = getPageService().buildPageParam(pageNo,20);

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		mypageOrderInfoDTO.setMbrNo(userDetail.getMbr().getMbrNo());

		// 회원 영수증 리스트 조회
		Page<MypageOrderFoResult> receiptList = memberActivityFOComponent.selectFoReceiptList(pk, mypageOrderInfoDTO, pageParam);
		List<MypageOrderFoResult> lists = null;
		if (receiptList != null) {
            lists = receiptList.getContent();
			for (int i = 0; i < lists.size(); i++) {
				MypageOrderFoResult orderInfo = lists.get(i);

				String clmNo = orderInfo.getClmNo();
				PayExtend payExtend = null;
				if (StringService.isNotEmpty(clmNo)) {
					Pay pay = new Pay();
					pay.setOrdNo(orderInfo.getOrdNo());
					pay.setClmNo(clmNo);
					payExtend = orderSelectComponent.selectLastClmOfOrd(pay);
				}
				// TODO : PG사별 SID등.. 설정 필요

				if (payExtend != null && "ALL".equals(payExtend.getLastClm())) {
					orderInfo.setDealTpCd("ALL_CNCL");
				}
			}
		}
		
		PageService.makePageResult(receiptList, model);

		model.addAttribute("receiptList", lists);
		model.addAttribute("totalRow", receiptList.getTotalElements());
		model.addAttribute("myPage", mypageOrderInfoDTO);

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/order/order.receipt.list";
	}
	
	/**
	 * 회원 영수증 리스트 조회
	 * 
	 * @param model
	 * @param pageNo
	 * @param request
	 * @param mypageOrderInfoDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/receipt/include/list.ajax", method = {RequestMethod.GET, RequestMethod.POST})
	public String getReceiptListInclude(Model model
			, @RequestParam(value = "pageNo", required = false) String pageNo
			, HttpServletRequest request
			, MypageOrderInfoDTO mypageOrderInfoDTO, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
        
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		PageParam pageParam = getPageService().buildPageParam(pageNo,20);

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		mypageOrderInfoDTO.setMbrNo(userDetail.getMbr().getMbrNo());

		// 회원 영수증 리스트 조회
		Page<MypageOrderFoResult> receiptList = memberActivityFOComponent.selectFoReceiptList(pk, mypageOrderInfoDTO, pageParam);
		List<MypageOrderFoResult> lists = null;
		if (receiptList != null) {
            lists = receiptList.getContent();
			for (int i = 0; i < lists.size(); i++) {
				MypageOrderFoResult orderInfo = lists.get(i);

				String clmNo = orderInfo.getClmNo();
				PayExtend payExtend = null;
				if (StringService.isNotEmpty(clmNo)) {
					Pay pay = new Pay();
					pay.setOrdNo(orderInfo.getOrdNo());
					pay.setClmNo(clmNo);
					payExtend = orderSelectComponent.selectLastClmOfOrd(pay);
				}

				if (payExtend != null && "ALL".equals(payExtend.getLastClm())) {
					orderInfo.setDealTpCd("ALL_CNCL");
				}
			}
		}
		
		PageService.makePageResult(receiptList, model);

		model.addAttribute("receiptList", lists);
		model.addAttribute("totalRow", receiptList.getTotalElements());
		model.addAttribute("myPage", mypageOrderInfoDTO);

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/order/include/order.receipt.list.include";
	}
	
	/**
	 * 회원 영수증 리스트 조회
	 * 
	 * @param model
	 * @param pageNo
	 * @param request
	 * @param mypageOrderInfoDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/include/specification/view", method = {RequestMethod.GET, RequestMethod.POST})
	public String viewSpecification(Model model, @RequestParam(value = "pageNo", required = false) String pageNo, HttpServletRequest request , MypageOrderInfoDTO mypageOrderInfoDTO ) throws Exception {

		return "tiles:mypage/order/include/order.specification.view.include";
	}
	
	/**
	 * 이전 주문보기
	 * 
	 * @param model
	 * @param request
	 * @param historyInfoFoDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value ="/formerly/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String getFormerlyPurchaseList(Model model, HttpServletRequest request, @ModelAttribute HistoryInfoFoDTO historyInfoFoDTO, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageOrderLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", "/mypage/order/list");
        location.put("msgKey", "mypage.order.list.ttl");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        
        /*String dateStart = historyInfoFoDTO.getDateStart();
		String dateEnd = historyInfoFoDTO.getDateEnd();
		
		if(StringService.isEmpty(dateStart) && StringService.isEmpty(dateEnd)) {
			dateStart = DateService.getNextMonth(new Date(), "yyyy-MM-dd", -3);
			dateEnd = DateService.getNextMonth(new Date(), "yyyy-MM-dd", 0);
		}*/
        
        //mlb는 2018년 1월1일부터 오픈(2019년 2월25일)때 까지 조회
        String dateStart = "20180101";
		String dateEnd = "20190225";
		
		dateStart = dateStart + " 00:00:00";
		dateEnd = dateEnd + " 23:59:59";
		
		OrderFormerlyPurchaseSDO purchaseInfo = new OrderFormerlyPurchaseSDO();
        
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		
		int purchaseListSize = 0;
		
		if(userDetail.getMbr().getErpCstmrNo() != "" && "UNITY_MBR".equals(userDetail.getMbr().getMbrTpCd())){
			
			OrderFormerlyPurchaseSDO sdo = new OrderFormerlyPurchaseSDO();
			sdo.setStartDt(dateStart);
			sdo.setEndDt(dateEnd);
			sdo.setCid(userDetail.getMbr().getErpCstmrNo());
			sdo.setBrand("M");
			
			purchaseInfo = memberActivityFOComponent.getFormerlyPurchaseList(pk, sdo);
			
			if (purchaseInfo.getResponseData() != null && purchaseInfo.getResponseData().isEmpty() == false) {
				purchaseListSize = purchaseInfo.getResponseData().size();
			}
		}
		
		model.addAttribute("mbrNm", userDetail.getMbr().getMbrNm());
		model.addAttribute("mbrTp", userDetail.getMbr().getMbrTpCd());
		model.addAttribute("searchDTO", historyInfoFoDTO);
		model.addAttribute("purchaseList", purchaseInfo.getResponseData());
		model.addAttribute("purchaseListSize", purchaseListSize);
		model.addAttribute("dspCtgryScFrDTO", dspCtgryScFrDTO);

		return "tiles:mypage/order/order.formerly.list";
	}
	
	/**
	 * 매장 구매내역 조회
	 * 
	 * @param model
	 * @param request
	 * @param historyInfoFoDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value ="/offline/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String getPurchasedListInStroe(Model model, HttpServletRequest request, @ModelAttribute HistoryInfoFoDTO historyInfoFoDTO, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageOrderLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", "/mypage/order/offline/list");
        location.put("msgKey", "mypage.order.offlinelist.ttl");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        
        String dateStart = historyInfoFoDTO.getDateStart();
		String dateEnd = historyInfoFoDTO.getDateEnd();
		
		if(StringService.isEmpty(dateStart) && StringService.isEmpty(dateEnd)) {
			dateStart = DateService.getNextMonth(new Date(), "yyyy-MM-dd", -3);
			dateEnd = DateService.getNextMonth(new Date(), "yyyy-MM-dd", 0);
		}
		
        OrderOfflinePurchaseSDO purchaseInfo = new OrderOfflinePurchaseSDO();
        
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		
		int purchaseListSize = 0;
		
		if(userDetail.getMbr().getErpCstmrNo() != "" && "UNITY_MBR".equals(userDetail.getMbr().getMbrTpCd())){
			
			OrderOfflinePurchaseSDO sdo = new OrderOfflinePurchaseSDO();
			sdo.setStartDt(dateStart);
			sdo.setEndDt(dateEnd);
			sdo.setCid(userDetail.getMbr().getErpCstmrNo());
			sdo.setBrand("X");
			
			purchaseInfo = memberActivityFOComponent.getOfflinePurchaseList(pk, sdo);
			
			if (purchaseInfo.getResponseData() != null && purchaseInfo.getResponseData().isEmpty() == false) {
				purchaseListSize = purchaseInfo.getResponseData().size();
			}
		}
		
		model.addAttribute("mbrNm", userDetail.getMbr().getMbrNm());
		model.addAttribute("mbrTp", userDetail.getMbr().getMbrTpCd());
		model.addAttribute("searchDTO", historyInfoFoDTO);
		model.addAttribute("purchaseList", purchaseInfo.getResponseData());
		model.addAttribute("purchaseListSize", purchaseListSize);

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/order/order.offline.list";
	}
	
	/**
	 * 거래명세서 조회(팝업)
	 * 
	 * @param mypageOrderInfoDTO
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/popup/dealingsDetailedStatement.popup", method = {RequestMethod.GET, RequestMethod.POST})
	public String popupDealingsDetailedStatement(@RequestParam(value="ordNo", required = true) String ordNo, Model model, HttpServletRequest request) throws Exception {
		
		MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();
		mypageOrderInfoDTO.setOrdNo(ordNo);
		
		this.validOrderAuth(mypageOrderInfoDTO.getOrdNo());
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageOrderInfoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
			model.addAttribute("nmbrYn", "N");//회원
		}else{
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			if(!"".equals(userDetail.getParameterMap().get("cstmrNm"))
					&&!"".equals(userDetail.getParameterMap().get("cstmrMobilNo"))
					&&!"".equals(userDetail.getParameterMap().get("ordNo"))){
				// 비회원 정보
				mypageOrderInfoDTO.setCstmrNm(userDetail.getParameterMap().get("cstmrNm"));
				mypageOrderInfoDTO.setCstmrMobilNo(userDetail.getParameterMap().get("cstmrMobilNo"));
				mypageOrderInfoDTO.setOrdNo(userDetail.getParameterMap().get("ordNo"));
				model.addAttribute("nmbrYn", "Y"); //비회원
			}
		}
		
		MypageOrderInfoDTO orderInfo = memberActivityFOComponent.selectFoOrderInfo(pk, mypageOrderInfoDTO);
		
		// 주문 상세 조회
		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("myPage", mypageOrderInfoDTO);
		
		model.addAttribute("currentDateString", DateService.getCurrentDateString("yyyy-MM-dd HH:mm:ss"));
		
		return "tiles:mypage/order/popup/dealings.detailed.statement.popup";
	}
}
