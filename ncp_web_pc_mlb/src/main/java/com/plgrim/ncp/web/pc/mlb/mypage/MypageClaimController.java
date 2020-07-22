package com.plgrim.ncp.web.pc.mlb.mypage;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspFoExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodFoExtend;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.biz.claim.data.ClaimBoDTO;
import com.plgrim.ncp.biz.claim.data.ClaimSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClmDlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.data.LgsDlvspPkupDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.biz.order.result.MypageClaimFoResult;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmDetailResult;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmResult;
import com.plgrim.ncp.biz.pay.data.ClmRefundDTO;
import com.plgrim.ncp.biz.pay.data.ClmRefundSearchDTO;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimCommandComponent;
import com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimNoticeComponent;
import com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimSelectComponent;
import com.plgrim.ncp.cmp.orderfulfilment.bo.order.OrderBoSelectComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.OrderCommandComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.OrderSelectComponent;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.commons.util.CodeUtil.Code;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

@Slf4j
@Controller
@RequestMapping("/mypage/claim")
public class MypageClaimController extends MypageController {
	
	@Autowired
	private MemberActivityFOComponent memberActivityFOComponent;
	
	@Autowired
	private OrderSelectComponent orderSelectComponent;
	
	@Autowired
	private OrderCommandComponent orderCommandComponent;
	
	@Autowired
	private ClaimSelectComponent claimSelectComponent;
	
	@Autowired
	private ClaimCommandComponent claimCommandComponent;
	
	@Autowired
	private ClaimNoticeComponent claimNoticeComponent;
	
	/**
	 * TODO : 패키지 정리
	 */
	@Autowired
	private OrderBoSelectComponent orderBoSelectComponent;
	
	/**
	 * 클레임목록 조회화면
	 * 
	 * @param mypageOrderInfoDTO
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_GUEST')")
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String selectFoClaimList(@ModelAttribute  MypageOrderInfoDTO mypageOrderInfoDTO , Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageOrderLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", "/mypage/claim/list");
        location.put("msgKey", "mypage.order.claimlist.ttl");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        
		model.addAttribute("myPage",mypageOrderInfoDTO);
		
		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/claim/claim.list";		
	}
	
	/**
	 * 클레임목록 조회(Include)
	 * 
	 * @param mypageOrderInfoDTO
	 * @param model
	 * @param request
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_GUEST')")
	@RequestMapping(value = "/include/list.ajax", method = {RequestMethod.GET, RequestMethod.POST})
	public String getMypageOrderListInclude(@ModelAttribute  MypageOrderInfoDTO mypageOrderInfoDTO
			, Model model, HttpServletRequest request
			, @RequestParam(value="currentPage" , required = false ) String currentPage) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageOrderInfoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
		}else{
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageOrderInfoDTO.setOrdNo(userDetail.getParameterMap().get("ordNo"));
			mypageOrderInfoDTO.setCstmrNm(userDetail.getParameterMap().get("cstmrNm"));
			mypageOrderInfoDTO.setNonMember("Y");
		}
		
		PageParam pageParam = getPageService().buildPageParam(StringUtils.defaultIfEmpty(currentPage, "1"), 10);

		Page<MypageClaimFoResult> result = memberActivityFOComponent.selectFoClaimList(pk, mypageOrderInfoDTO, pageParam);
		
		PageService.makePageResult(result , model);
		List<MypageClaimFoResult> claimList = result.getContent();
		
		model.addAttribute("claimList", claimList);
		model.addAttribute("myPage", mypageOrderInfoDTO);

		return "tiles:mypage/claim/include/claim.list.include";
	}
	
	/**
	 * 부분취소 접수화면
	 * 
	 * @param model
	 * @param request
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/cancel/part/new", method = {RequestMethod.GET, RequestMethod.POST})
	public String partCancelAccept(Model model, HttpServletRequest request ,OrderBoDTO orderDTO, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		String ordNo = orderDTO.getOrdNo();
		this.validOrderAuth(ordNo);

		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageOrderLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", "/mypage/claim/cancel/part/new");
        location.put("msgKey", "mypage.order.cancel.ttl");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        
        SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
        
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			model.addAttribute("mbrNo"			, userDetail.getMbr().getMbrNo());
			model.addAttribute("nmbrYn", "N"); //회원
			this.setOrderListDspCtgryScFrDTO(model, "DMPA01A01A02");
		}else{
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			if(!"".equals(userDetail.getParameterMap().get("cstmrNm"))
					&&!"".equals(userDetail.getParameterMap().get("cstmrMobilNo"))
					&&!"".equals(userDetail.getParameterMap().get("ordNo"))){
				// 비회원 정보
				model.addAttribute("nmbrYn", "Y"); //비회원
			}
		}

		/*배송지별 주문상품 내역 조회*/
		orderDTO.setClmTpCd("PART_CNCL");
		orderDTO.setRole("F");
		orderDTO.setLang(systemPK.getLang());
		
		List<OrdGodForRtnClmResult> ordGodForRtnClmResult = orderSelectComponent.selectOrdGodPartCancelClmWithGft(systemPK, orderDTO);

		model.addAttribute("ordGodForRtnClmResult", ordGodForRtnClmResult);
		LgsDlvspExtend lgsDlvsp = new LgsDlvspExtend();

		List<LgsDlvspPkupDTO> dlvspList = new ArrayList<LgsDlvspPkupDTO>();
		lgsDlvsp.setOrdNo(ordNo);
		for(OrdGodForRtnClmResult dto : ordGodForRtnClmResult){
			lgsDlvsp.setDlvPcupspTurn(Integer.parseInt(dto.getDlvPcupspTurn()));
			dlvspList.add(claimSelectComponent.selectLgsDlvspPkup(lgsDlvsp));
		}

		MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();
		mypageOrderInfoDTO.setOrdNo(ordNo);
		
		MypageOrderInfoDTO orderInfo = memberActivityFOComponent.selectFoOrderInfo(systemPK, mypageOrderInfoDTO);
		
		model.addAttribute("orderInfo"  , orderInfo);
		model.addAttribute("dlvspList"  , dlvspList);
		model.addAttribute("clmRsn", getClmRsnCodeList(OrderClaimEnum.ClmTpEnum.PART_CNCL, systemPK.getLang()));
		model.addAttribute("mobilPonPayRfd", memberActivityFOComponent.selectPayFoMobilPonPayRfd(systemPK, ordNo));

		return "tiles:mypage/claim/cancel.part.new";
	}
	
	/**
	 * 클레임 예상 환불금액 계산
	 * 
	 * @param model
	 * @param request
	 * @param claimBoDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value="/include/refundInfo", method=RequestMethod.POST)
	public ModelAndView getClaimRefundInfo(ModelAndView model, HttpServletRequest request,ClaimBoDTO claimBoDTO, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		this.validOrderAuth(claimBoDTO.getOrdNo());
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		ObjectMapper om = new ObjectMapper();
		log.info("om.writeValueAsString(claimBoDTO)  ::  "+om.writeValueAsString(claimBoDTO));

		//환불정보 리스트
		ClmRefundSearchDTO clmRefundSearchDTO = new ClmRefundSearchDTO();
		List<OrdGodExtend> ordGodList = new ArrayList<OrdGodExtend>();
		clmRefundSearchDTO.setOrdNo(claimBoDTO.getOrdNo());

		clmRefundSearchDTO.setLang(pk.getLang());

		List<LgsDlvspExtend> lgsDlvspList = claimBoDTO.getLgsDlvspList();

		for(int i=0; i < lgsDlvspList.size(); i++){
			List<ClmWrhsGodExtend> clmWrhsGodList = lgsDlvspList.get(i).getClmWrhsGodList();
			List<ClmWrhsGodExtend> newClmWrhsGodList = new ArrayList<ClmWrhsGodExtend>();
			if(clmWrhsGodList != null){
				for(int j=0; j < clmWrhsGodList.size(); j++){
					if(clmWrhsGodList.get(j).getDlivyDrctGodNo() != null && !"".equals(clmWrhsGodList.get(j).getDlivyDrctGodNo())){
						
						newClmWrhsGodList.add(clmWrhsGodList.get(j));

						OrdGodExtend ordGodExtend = new OrdGodExtend();
						ordGodExtend.setOrdNo(claimBoDTO.getOrdNo());
						ordGodExtend.setOrdGodTurn(clmWrhsGodList.get(j).getOrdGodTurn());
						ordGodExtend.setOrdQty(clmWrhsGodList.get(j).getClmQty());
						ordGodExtend.setDlivyDrctGodNo(clmWrhsGodList.get(j).getDlivyDrctGodNo());
						ordGodExtend.setDlvPcupspTurn(lgsDlvspList.get(i).getDlvPcupspTurn());
						ordGodExtend.setDlvTurn(clmWrhsGodList.get(j).getDlvTurn());

						ordGodList.add(ordGodExtend);

					}
				}
			}
			lgsDlvspList.get(i).setClmWrhsGodList(newClmWrhsGodList);
		}

		/**
		 * 수거 상품이 없을 경우 list에서 수거지 정보 remove
		 */
		for(int i=0 ; i < lgsDlvspList.size() ; i++){
			if(lgsDlvspList.get(i).getClmWrhsGodList() == null || lgsDlvspList.get(i).getClmWrhsGodList().size() == 0){
				lgsDlvspList.remove(i);
				i--;
			}
		}

		/* BO 처리을 위한 값 세팅(FO 사용 안함) */
		claimBoDTO.setCallTp("");
		claimBoDTO.setOrdTp("");
		claimBoDTO.setAdminTpCd("");

		/**
		 * 주문사은품 취소(반품) 대상 조회
		 */
		List<ClmDlvOrdGodInfoDTO> clmOrdGodList = new ArrayList<ClmDlvOrdGodInfoDTO>();
		
		if("PART_CNCL".equals(claimBoDTO.getClmTpCd()) || "RTGOD".equals(claimBoDTO.getClmTpCd())){
			for(LgsDlvspExtend dlvsp : claimBoDTO.getLgsDlvspList()){
				for(ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()){
					ClmDlvOrdGodInfoDTO clmDlvOrdGodInfoDTO = new ClmDlvOrdGodInfoDTO();
					clmDlvOrdGodInfoDTO.setDlvPcupspTurn(dlvsp.getDlvPcupspTurn()+"");
					clmDlvOrdGodInfoDTO.setOrdGodTurn(clmGod.getOrdGodTurn()+"");
					clmDlvOrdGodInfoDTO.setDlivyDrctGodNo(clmGod.getDlivyDrctGodNo()+"");
					clmDlvOrdGodInfoDTO.setDlvTurn(clmGod.getDlvTurn()+"");
					clmDlvOrdGodInfoDTO.setQty(clmGod.getClmQty().intValue()+"");
					clmDlvOrdGodInfoDTO.setClmResnCd(clmGod.getClmResnCd());
					clmDlvOrdGodInfoDTO.setClmResnCont(clmGod.getClmResnCont());
					clmDlvOrdGodInfoDTO.setGodTpCd(clmGod.getGodTpCd());
					
					clmOrdGodList.add(clmDlvOrdGodInfoDTO);
				}
			}
			
			ClaimSearchDTO claimSearchDTO = new ClaimSearchDTO();
			claimSearchDTO.setDlvspClmOrdGod(clmOrdGodList);
			clmOrdGodList = claimCommandComponent.getOrderGiftCancelGoodsList(claimSearchDTO, claimBoDTO.getOrdNo());
		}
		
		/* 부분취소 일 경우 */
		if("PART_CNCL".equals(claimBoDTO.getClmTpCd())){

			clmRefundSearchDTO.setReturnDlvAmt(claimBoDTO.getReturnDlvAmt());
			clmRefundSearchDTO.setClmTpCd(claimBoDTO.getClmTpCd());
			clmRefundSearchDTO.setOrdGodList(ordGodList);
			clmRefundSearchDTO.setLgsDlvspList(claimBoDTO.getLgsDlvspList());

			clmRefundSearchDTO.setRole("F");
			
			ClmRefundDTO clmRefundDTO = claimSelectComponent.selectOrdGodErpForClmRefund(clmRefundSearchDTO);
			
			clmRefundDTO.setOrdGftList(clmOrdGodList);
			
			model.addObject("clmRefundDTO", clmRefundDTO);

		} else if("RTGOD".equals(claimBoDTO.getClmTpCd())){

			/* 반품 일 경우 */
			List<LgsDlvspExtend> lgsDlvspList2 = claimSelectComponent.selectRtgodDlvCstCal(claimBoDTO, pk);

			java.math.BigDecimal realityDlvCst = BigDecimal.valueOf(0);
			
			java.math.BigDecimal orderDlvAmt = BigDecimal.valueOf(0);	// 초도배송비
			java.math.BigDecimal pickupDlvAmt = BigDecimal.valueOf(0);	// 반품수거비

			// 계산 된 업체별 반품 배송비를 통해 환불 값을 가져오기 위한 세팅
			for(LgsDlvspExtend lgsDlvsp : lgsDlvspList2){
				for(LgsDlvFoExtend ld : lgsDlvsp.getLgsDlvList()) {
					if(ld.getCnclDlvCst() == null)
						ld.setCnclDlvCst(BigDecimal.valueOf(0));
					if(ld.getRtgodDlvCst() == null)
						ld.setRtgodDlvCst(BigDecimal.valueOf(0));
					// 반품 배송비 - 취소 배송비
					realityDlvCst = realityDlvCst.add(ld.getRtgodDlvCst().subtract(ld.getCnclDlvCst()));
					
					pickupDlvAmt = pickupDlvAmt.add(ld.getPlcDlvCst());	// 반품 수거비
					orderDlvAmt = orderDlvAmt.add(ld.getCnclDlvCst().multiply(new BigDecimal("-1")));	// 초도배송비 환불금액은 - 처리하여 더해준다.
					
					if (ld.getRtgodDlvCst().compareTo(ld.getPlcDlvCst()) > 0) {	// 초도배송비가 부과되는 경우
						orderDlvAmt = orderDlvAmt.add(ld.getRtgodDlvCst().subtract(ld.getPlcDlvCst()));
					}
				}
			}
			
			claimBoDTO.setReturnDlvAmt(String.valueOf(realityDlvCst));
			clmRefundSearchDTO.setReturnDlvAmt(claimBoDTO.getReturnDlvAmt());

			clmRefundSearchDTO.setClmTpCd(claimBoDTO.getClmTpCd());
			clmRefundSearchDTO.setOrdGodList(ordGodList);
			clmRefundSearchDTO.setLgsDlvspList(claimBoDTO.getLgsDlvspList());


			clmRefundSearchDTO.setRole("F");

			ClmRefundDTO clmRefundDTO = claimSelectComponent.selectOrdGodErpForClmRefund(clmRefundSearchDTO);

			clmRefundDTO.setOrderDlvAmt(orderDlvAmt.toString());
			clmRefundDTO.setPickupDlvAmt(pickupDlvAmt.toString());
			
			clmRefundDTO.setOrdGftList(clmOrdGodList);
			
			model.addObject("clmRefundDTO", clmRefundDTO);
			model.addObject("lgsDlvspList", lgsDlvspList2);

		} else {

			/* 교환 일 경우 */
			List<LgsDlvspExtend> lgsDlvspList2 = claimSelectComponent.selectExchgDlvCstCal(claimBoDTO, pk);
			model.addObject("lgsDlvspList", lgsDlvspList2);
		}

		// 택배사 정책으로 택배사 이름 조회
		Map<String, String> dlvCom = claimSelectComponent.selectBaseDlvCom(pk.getLang(), pk.getMall());
		
		model.addObject("dlvCom", dlvCom);
		model.addObject("clmTpCd",  claimBoDTO.getClmTpCd());
		
		model.setViewName("tiles:mypage/claim/include/refund.info.include");
		
		return model;
	}
	
	/**
	 * 부부취소 접수처리
	 *  - 취소 클레임은 접수 시점에 완료 처리한다.
	 *  
	 * @param model
	 * @param request
	 * @param claimBoDTO
	 * @param clmRefundDTO
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/cancle/part/request.json", produces = "application/json", method = RequestMethod.POST)
	public void updateClaimUnitCancelJson(Model model, HttpServletRequest request, ClaimBoDTO claimBoDTO, ClmRefundDTO clmRefundDTO) throws Exception {

		log.info("PartCancel_Start");

		this.validOrderAuth(claimBoDTO.getOrdNo());
		
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);

		ObjectMapper om = new ObjectMapper();
		log.info("om.writeValueAsString(clmRefundDTO)  ::  "+om.writeValueAsString(clmRefundDTO));

		List<LgsDlvspExtend> lgsDlvspList = claimBoDTO.getLgsDlvspList();

		for(int i=0; i < lgsDlvspList.size(); i++){
			List<ClmWrhsGodExtend> clmWrhsGodList = lgsDlvspList.get(i).getClmWrhsGodList();
			List<ClmWrhsGodExtend> newClmWrhsGodList = new ArrayList<ClmWrhsGodExtend>();
			for(int j=0; j < clmWrhsGodList.size(); j++) {
				if("SET_GOD".equals(clmWrhsGodList.get(j).getGodTpCd()) || "PCKAGE_GOD".equals(clmWrhsGodList.get(j).getGodTpCd())){
					/* 세트 || 패키지 마스터 상품 추가 */
					newClmWrhsGodList.add(clmWrhsGodList.get(j));
				} else {
					if(clmWrhsGodList.get(j).getDlivyDrctGodNo() != null && !"".equals(clmWrhsGodList.get(j).getDlivyDrctGodNo())){
						newClmWrhsGodList.add(clmWrhsGodList.get(j));
					}
				}
			}
			lgsDlvspList.get(i).setClmWrhsGodList(newClmWrhsGodList);
		}
		
		ClaimSearchDTO claimSearchDTO = new ClaimSearchDTO();
		
		claimSearchDTO.setOrdNo(claimBoDTO.getOrdNo());
		claimSearchDTO.setPgExcept("N");

		claimSearchDTO.setRfdBnkCd(claimBoDTO.getRfdBnkCd());
		claimSearchDTO.setRfdAcnthldrNm(claimBoDTO.getRfdAcnthldrNm());
		claimSearchDTO.setRfdBnkAcctNo(claimBoDTO.getRfdBnkAcctNo());

		List<ClmDlvOrdGodInfoDTO> clmOrdGodList = new ArrayList<ClmDlvOrdGodInfoDTO>();
		ClmDlvOrdGodInfoDTO clmDlvOrdGodInfoDTO = null;
		
		for(LgsDlvspExtend dlvsp : claimBoDTO.getLgsDlvspList()){
			for(ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()){
				clmDlvOrdGodInfoDTO = new ClmDlvOrdGodInfoDTO();
				clmDlvOrdGodInfoDTO.setDlvPcupspTurn(dlvsp.getDlvPcupspTurn()+"");
				clmDlvOrdGodInfoDTO.setOrdGodTurn(clmGod.getOrdGodTurn()+"");
				clmDlvOrdGodInfoDTO.setDlivyDrctGodNo(clmGod.getDlivyDrctGodNo()+"");
				clmDlvOrdGodInfoDTO.setDlvTurn(clmGod.getDlvTurn()+"");
				clmDlvOrdGodInfoDTO.setQty(clmGod.getClmQty().intValue()+"");
				clmDlvOrdGodInfoDTO.setClmResnCd(clmGod.getClmResnCd());
				clmDlvOrdGodInfoDTO.setClmResnCont(clmGod.getClmResnCont());
				clmDlvOrdGodInfoDTO.setGodTpCd(clmGod.getGodTpCd());
				
				clmOrdGodList.add(clmDlvOrdGodInfoDTO);
			}
		}
		
		claimSearchDTO.setDlvspClmOrdGod(clmOrdGodList);
		claimSearchDTO.setLgsDlvspList(lgsDlvspList);

		/**
		 * 클레임 대표사유코드 설정
		 */
		String masterClmResnCd = getClmMasterResn(clmOrdGodList);
		claimSearchDTO.setClmResnCd(masterClmResnCd);
		
		for (ClmDlvOrdGodInfoDTO ordGod : clmOrdGodList) {
			if (masterClmResnCd.equals(ordGod.getClmResnCd())) {
				claimSearchDTO.setClmResnCont(ordGod.getClmResnCont());
				break;
			}
		}
	
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			claimSearchDTO.setUdterId(userDetail.getMbr().getMbrNo());
		}else{
			claimSearchDTO.setUdterId("NMBR");
		}
		
		String clmNo = claimCommandComponent.updateClaimUnitCancel(request, claimSearchDTO, systemPK);

		try {
			claimNoticeComponent.sendOrderPartCancelNotice(claimSearchDTO.getOrdNo(), clmNo, systemPK, true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		model.addAttribute("result", clmNo);
		model.addAttribute("acceptResult", "Y");

		log.info("PartCancel_Complete");

	}

	
	/**
	 * 전체취소 접수 화면 (레이어)
	 * 
	 * @param model
	 * @param request
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/cancel/total/new", method = {RequestMethod.GET, RequestMethod.POST})
	public String totalCancelAccept(@ModelAttribute MypageOrderInfoDTO mypageOrderInfoDTO, Model model,HttpServletRequest request) throws Exception {
		
		this.validOrderAuth(mypageOrderInfoDTO.getOrdNo());
		
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		
		MypageOrderInfoDTO orderInfo = memberActivityFOComponent.selectFoOrderInfo(systemPK, mypageOrderInfoDTO);
		
		model.addAttribute("clmRsn", getClmRsnCodeList(OrderClaimEnum.ClmTpEnum.ALL_CNCL, systemPK.getLang()));
		model.addAttribute("totalCancelInfo", orderInfo);
		model.addAttribute("mobilPonPayRfd", memberActivityFOComponent.selectPayFoMobilPonPayRfd(systemPK, mypageOrderInfoDTO.getOrdNo()));

		return "tiles:mypage/claim/include/cancel.total.new.include";
	}
	
	/**
	 * 전체취소 접수처리
	 * 
	 * @param claimSearchDTO
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/cancle/total/request.json", method = RequestMethod.POST)
	public void updateClaimTotalCancelJson(@ModelAttribute ClaimSearchDTO claimSearchDTO, Model model,HttpServletRequest request) throws Exception {
		
		log.info("TotalCancel_Start");
//		log.info(CommonResponseCode.CL_00001_취소_접수_시도.toMessage());

		this.validOrderAuth(claimSearchDTO.getOrdNo());
		
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);

		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			claimSearchDTO.setUdterId(userDetail.getMbr().getMbrNo());
		}else{
			claimSearchDTO.setUdterId("NMBR");
		}
		
		String result = claimCommandComponent.updateClaimTotalCancel(claimSearchDTO, systemPK);

		model.addAttribute("result", result);
		model.addAttribute("acceptResult", "Y");

		try {
			claimNoticeComponent.sendOrderAllCancelNotice(claimSearchDTO.getOrdNo(), systemPK, true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		log.info("TotalCancel_Complete");
//		log.info(CommonResponseCode.CL_00002_취소_접수_성공.toMessage());

	}

	/**
	 * 교환 접수화면
	 * 
	 * @param model
	 * @param request
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
    @RequestMapping(value = "/exchange/new", method = RequestMethod.POST)
    public String newExchangeClaim(Model model, HttpServletRequest request, OrderBoDTO orderDTO, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
        
		this.validOrderAuth(orderDTO.getOrdNo());
		
		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageOrderLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", "/mypage/claim/exchange/new");
        location.put("msgKey", "mypage.order.exchange.ttl");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
        
        String ordNo = orderDTO.getOrdNo();
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			model.addAttribute("mbrNo"			, userDetail.getMbr().getMbrNo());
			model.addAttribute("mbrTp"		    , userDetail.getMbr().getMbrTpCd());
			orderDTO.setMbrNo(userDetail.getMbr().getMbrNo());
			model.addAttribute("nmbrYn", "N"); //회원
			model.addAttribute("userDetail", userDetail.getMbr());
			this.setOrderListDspCtgryScFrDTO(model, "DMPA01A01A02");
		}else{
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			if(!"".equals(userDetail.getParameterMap().get("cstmrNm"))
					&&!"".equals(userDetail.getParameterMap().get("cstmrMobilNo"))
							&&!"".equals(userDetail.getParameterMap().get("ordNo"))){
			// 비회원 정보
				model.addAttribute("nmbrYn", "Y"); //비회원
				model.addAttribute("mbrTp"		    , "NMBR");
			}
		}		
		model.addAttribute("ordNo", ordNo);	//주문번호
		
		MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();
		mypageOrderInfoDTO.setOrdNo(ordNo);
		
		MypageOrderInfoDTO orderInfo = memberActivityFOComponent.selectFoOrderInfo(systemPK, mypageOrderInfoDTO);
		model.addAttribute("orderInfo", orderInfo);
		
        /* 배송지별 주문상품 내역 조회 */
		orderDTO.setRole("F");
		orderDTO.setLang(systemPK.getLang());
        List<OrdGodForRtnClmResult> ordGodForRtnClmResult = orderBoSelectComponent.selectOrdGodForRtnClm(systemPK, orderDTO);        
        /* 
         * 옵션변경콤보 생성
         * 수거지정보
         */
		for (OrdGodForRtnClmResult ordGodForRtnClm : ordGodForRtnClmResult) 
		{			
            /* 교환상품정보 */
			for (OrdGodForRtnClmDetailResult ordGodForRtnClmDetailResult : ordGodForRtnClm.getOrdGodForRtnClmDetailResultList()) 
			{
				orderDTO.setGodNo(ordGodForRtnClmDetailResult.getGodNo());	//해당상품번호
				orderDTO.setItmNo(ordGodForRtnClmDetailResult.getItmNo());	//해당단품번호
				ordGodForRtnClmDetailResult.setGodExtendChangeList(orderBoSelectComponent.selectBoItmHistForClm(orderDTO));
				                                                                          
			}
		}
		model.addAttribute("ordGodForRtnClmResult", ordGodForRtnClmResult);
        
		LgsDlvspExtend lgsDlvsp = new LgsDlvspExtend();
		
		List<LgsDlvspPkupDTO> dlvspList = new ArrayList<LgsDlvspPkupDTO>();
		lgsDlvsp.setOrdNo(ordNo);
		for(OrdGodForRtnClmResult dto : ordGodForRtnClmResult){
			lgsDlvsp.setDlvPcupspTurn(Integer.parseInt(dto.getDlvPcupspTurn()));
			dlvspList.add(claimSelectComponent.selectLgsDlvspPkup(lgsDlvsp));			
		}
		model.addAttribute("dlvspList"  , dlvspList);
		model.addAttribute("clmRsn", getClmRsnCodeList(OrderClaimEnum.ClmTpEnum.GNRL_EXCHG, systemPK.getLang()));
		
        //주문 교환접수 호출
        return "tiles:mypage/claim/exchange.new";
    }
	
	/**
	 * 교환 접수처리
	 * 
	 * @param model
	 * @param request
	 * @param claimBoDTO
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/exchange/request.json", produces = { "application/json"})
	public void addExchangeClaim(Model model, HttpServletRequest request, ClaimBoDTO claimBoDTO) throws Exception {

		this.validOrderAuth(claimBoDTO.getOrdNo());
		
		log.info("ExchangeClaim_Start");

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			claimBoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
			claimBoDTO.setRegtrId(userDetail.getMbr().getMbrId());       	// 로그인 세션정보
			claimBoDTO.setRegtrShopId("");		// 로그인된 매장ID
		}

		claimBoDTO.setMallId(StringUtils.defaultString(pk.getMall(), "DXM"));

		List<LgsDlvspExtend> lgsDlvspList = claimBoDTO.getLgsDlvspList();

		for(int i=0; i < lgsDlvspList.size(); i++){
			List<ClmWrhsGodExtend> clmWrhsGodList = lgsDlvspList.get(i).getClmWrhsGodList();
			List<ClmWrhsGodExtend> newClmWrhsGodList = new ArrayList<ClmWrhsGodExtend>();

			for(int j=0; j < clmWrhsGodList.size(); j++){
				if(clmWrhsGodList.get(j).getGodNo() != null && !"".equals(clmWrhsGodList.get(j).getGodNo())){
					newClmWrhsGodList.add(clmWrhsGodList.get(j));
				}
			}
			lgsDlvspList.get(i).setClmWrhsGodList(newClmWrhsGodList);
		}
		
		claimBoDTO.setLgsDlvspList(lgsDlvspList);
		
		List<ClmDlvOrdGodInfoDTO> dlvspClmOrdGod = new ArrayList<ClmDlvOrdGodInfoDTO>();
		ClmDlvOrdGodInfoDTO clmDlvOrdGodInfoDTO = null;

		for(LgsDlvspExtend dlvsp : claimBoDTO.getLgsDlvspList()){
			for(ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()){
				clmDlvOrdGodInfoDTO = new ClmDlvOrdGodInfoDTO();
				clmDlvOrdGodInfoDTO.setClmResnCd(clmGod.getClmResnCd());
				dlvspClmOrdGod.add(clmDlvOrdGodInfoDTO);
			}
		}

		String masterClmResnCd = getClmMasterResn(dlvspClmOrdGod);
		claimBoDTO.setClmResnCd(masterClmResnCd);
		claimBoDTO.getClmExtend().setPgTrnsmisTgtYn("Y");//프론트 PG 전송여부 강제가능
		String clmNo = claimCommandComponent.addExchangeClaim(claimBoDTO , pk);

		String defaultDelv = claimBoDTO.getDefaultDelv();
		String addMbrDlvspCheck = claimBoDTO.getAddMbrDlvspCheck();

		if ("Y".equalsIgnoreCase(defaultDelv) ||
				"Y".equalsIgnoreCase(addMbrDlvspCheck)) {

			LgsDlvsp mbrLgsDlvsp = new LgsDlvsp() ;

			for(int i = 0 ; i < lgsDlvspList.size() ; i++){
				if(i == 0){
					Functions.copyProperties2(mbrLgsDlvsp, lgsDlvspList.get(i));
				}
			}
			memberActivityFOComponent.updateDeliveryLocationMbrDlvsp(pk, mbrLgsDlvsp, defaultDelv, addMbrDlvspCheck);
		}

		try {
			claimNoticeComponent.sendAcceptExchangeNotice(claimBoDTO.getOrdNo(), clmNo, pk, true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		model.addAttribute("clmNo", clmNo);

		log.info("ExchangeClaim_Complete");

	}
	


	/**
	 * 반품 접수화면
	 * @param model
	 * @param request
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping("/return/new")
	public String requestForReturn(Model model, HttpServletRequest request ,OrderBoDTO orderDTO, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageOrderLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", "/mypage/claim/return/new");
        location.put("msgKey", "mypage.order.return.ttl");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
	
		String ordNo = orderDTO.getOrdNo();

		MypageOrderInfoDTO  mypageOrderInfoDTO = new MypageOrderInfoDTO();
		mypageOrderInfoDTO.setOrdNo(ordNo);

		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			model.addAttribute("mbrNo"			, userDetail.getMbr().getMbrNo());
			model.addAttribute("mbrTp"		    , userDetail.getMbr().getMbrTpCd());
			model.addAttribute("nmbrYn", "N"); //회원

			mypageOrderInfoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
			this.setOrderListDspCtgryScFrDTO(model, "DMPA01A01A02");
		}else{
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			if(!"".equals(userDetail.getParameterMap().get("cstmrNm"))
					&&!"".equals(userDetail.getParameterMap().get("cstmrMobilNo"))
					&&!"".equals(userDetail.getParameterMap().get("ordNo"))){
				// 비회원 정보
				model.addAttribute("mbrTp"		    , "NMBR");
				model.addAttribute("nmbrYn", "Y"); //비회원

				mypageOrderInfoDTO.setCstmrNm(userDetail.getParameterMap().get("cstmrNm"));
				mypageOrderInfoDTO.setCstmrMobilNo(userDetail.getParameterMap().get("cstmrMobilNo"));
				mypageOrderInfoDTO.setOrdNo(userDetail.getParameterMap().get("ordNo"));

			}
		}

		MypageOrderInfoDTO orderInfo = memberActivityFOComponent.selectFoOrderInfo(systemPK, mypageOrderInfoDTO);
		model.addAttribute("orderInfo", orderInfo);
		
		List<String> ordGodTurnList = new ArrayList<String>();

		/*
		* 반품 가능 주문 상품 번호 세팅
		* */
		for(LgsDlvspFoExtend lgsDlvsp : orderInfo.getLgsDlvspFoExtend()){
			if(orderDTO.getDlvPcupspTurn() == lgsDlvsp.getDlvPcupspTurn()){
				for(LgsDlvFoExtend lgsDlv : lgsDlvsp.getLgsDlvFoExtendList()){
					for(OrdGodFoExtend ordGod : lgsDlv.getOrdGodList()){
						if(ordGod.getRealOrdQty() > 0){
							// xml 에서 null 여부 체크만 함으로 존재할 경우 값 세팅
							orderDTO.setOrdGodTurnStr(ordGod.getOrdGodTurn().toString());
							ordGodTurnList.add(ordGod.getOrdGodTurn().toString());
							if(ordGod.getGodTpCd() != null && ("SET_GOD".equals(ordGod.getGodTpCd()) || "PCKAGE_GOD".equals(ordGod.getGodTpCd())) ){
								for(OrdGodFoExtend ordGodOpt : ordGod.getGodOptList()){
									if(ordGodOpt.getRealOrdQty() > 0) {
										ordGodTurnList.add(ordGodOpt.getOrdGodTurn().toString());
									}
								}
							}
						}
					}
				}
			} else {
				/* 다중 반품 일 경우 수거지 순번을 던지지 않아 해당 주문건의 전체 상품 번호를 세팅 */
				for(LgsDlvFoExtend lgsDlv : lgsDlvsp.getLgsDlvFoExtendList()){
					for(OrdGodFoExtend ordGod : lgsDlv.getOrdGodList()){
						if(ordGod.getRealOrdQty() > 0){
							// xml 에서 null 여부 체크만 함으로 존재할 경우 값 세팅
							orderDTO.setOrdGodTurnStr(ordGod.getOrdGodTurn().toString());
							ordGodTurnList.add(ordGod.getOrdGodTurn().toString());
							if(ordGod.getGodTpCd() != null && ("SET_GOD".equals(ordGod.getGodTpCd()) || "PCKAGE_GOD".equals(ordGod.getGodTpCd())) ){
								for(OrdGodFoExtend ordGodOpt : ordGod.getGodOptList()){
									if(ordGodOpt.getRealOrdQty() > 0) {
										ordGodTurnList.add(ordGodOpt.getOrdGodTurn().toString());
									}
								}
							}
						}
					}
				}
			}
		}
		orderDTO.setOrdGodTurnArr(ordGodTurnList);

		model.addAttribute("ordNo", ordNo);    //주문번호

		String ordTpCd = orderBoSelectComponent.selectOrdTpCd(systemPK, ordNo);
		model.addAttribute("ordTpCd", ordTpCd);

		orderDTO.setRole("F");
		model.addAttribute("prmTpCd"  , orderDTO.getPrmTpCd());

		Ord ord = orderSelectComponent.selectOrdEntity(ordNo);
		orderDTO.setLang(systemPK.getLang());

		List<OrdGodForRtnClmResult> ordGodForRtnClmResult = orderBoSelectComponent.selectOrdGodForRtnClmWithGftFo(systemPK, orderDTO);

		LgsDlvspExtend lgsDlvsp = new LgsDlvspExtend();
		
		List<LgsDlvspPkupDTO> dlvspList = new ArrayList<LgsDlvspPkupDTO>();
		lgsDlvsp.setOrdNo(ordNo);

		lgsDlvsp.setOrdTpCd(ordTpCd);

		int i = 0;
		for(OrdGodForRtnClmResult dto : ordGodForRtnClmResult){
			int clmCnt = 0;
			for(OrdGodForRtnClmDetailResult dto2 : dto.getOrdGodForRtnClmDetailResultList()){
				if("Y".equals(dto2.getClmYn()) && dto2.getWrkQty() > 0){
					if("SET_GOD".equals(dto2.getGodTpCd()) || "PCKAGE_GOD".equals(dto2.getGodTpCd())){
						dto2.setDlvStatCd("DLV_COMPT");
					}

					clmCnt++;
				}

			}
			lgsDlvsp.setDlvPcupspTurn(Integer.parseInt(dto.getDlvPcupspTurn()));
			dlvspList.add(claimSelectComponent.selectLgsDlvspPkup(lgsDlvsp));
			dlvspList.get(i).setDlvPcupspTurn(dto.getDlvPcupspTurn());
			dlvspList.get(i).setClmCnt(clmCnt);
			i++;
		}

		model.addAttribute("ordGodForRtnClmResult", ordGodForRtnClmResult);
		model.addAttribute("dlvspList"  , dlvspList);

		// 휴대폰소액결제인 경우 반품접수시 당월 상관없어 무조건 환불계좌입력 노출, 무통장/가상계좌도 쿼리에서 체크하고 있으므로 무조건 'Y'로 넘기면 안됨.
		MypageOrderInfoDTO mobilPonPayRfd = memberActivityFOComponent.selectPayFoMobilPonPayRfd(systemPK, ordNo);
		if("MOBIL_PON_PAY".equals(mobilPonPayRfd.getPayMnCd())){
			mobilPonPayRfd.setRfdYn("Y");
		}
		
		model.addAttribute("mobilPonPayRfd", mobilPonPayRfd);
		model.addAttribute("multiDlv", memberActivityFOComponent.selectMultiLgsDlvYn(systemPK, ordNo));
		model.addAttribute("clmRsn", getClmRsnCodeList(OrderClaimEnum.ClmTpEnum.RTGOD, systemPK.getLang()));
		
		return "tiles:mypage/claim/return.new";
	}
	
	/**
	 * 반품 접수처리
	 * 
	 * @param model
	 * @param request
	 * @param claimBoDTO
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER' , 'ROLE_GUEST')")
	@RequestMapping(value = "/return/request.json", produces = { "application/json"})
	public void addReturnClaim(Model model, HttpServletRequest request, ClaimBoDTO claimBoDTO) throws Exception {

		log.info("ReturnClaim_Start");

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		List<LgsDlvspExtend> lgsDlvspList = claimBoDTO.getLgsDlvspList();


		for(int i=0; i < lgsDlvspList.size(); i++){
			List<ClmWrhsGodExtend> clmWrhsGodList = lgsDlvspList.get(i).getClmWrhsGodList();
			List<ClmWrhsGodExtend> newClmWrhsGodList = new ArrayList<ClmWrhsGodExtend>();

			if(clmWrhsGodList != null){
				for(int j=0; j < clmWrhsGodList.size(); j++){
					if(clmWrhsGodList.get(j).getDlivyDrctGodNo() != null && !"".equals(clmWrhsGodList.get(j).getDlivyDrctGodNo())){
						newClmWrhsGodList.add(clmWrhsGodList.get(j));
					} else if (clmWrhsGodList.get(j).getGodTpCd() != null && ("SET_GOD".equals(clmWrhsGodList.get(j).getGodTpCd()) || "PCKAGE_GOD".equals(clmWrhsGodList.get(j).getGodTpCd())) ){
						newClmWrhsGodList.add(clmWrhsGodList.get(j));
					}
				}
			}
			lgsDlvspList.get(i).setClmWrhsGodList(newClmWrhsGodList);
		}
		claimBoDTO.setLgsDlvspList(lgsDlvspList);

		for(int i=0 ; i < claimBoDTO.getLgsDlvspList().size() ; i++){
			if(claimBoDTO.getLgsDlvspList().get(i).getClmWrhsGodList() == null || claimBoDTO.getLgsDlvspList().get(i).getClmWrhsGodList().size() == 0){
				claimBoDTO.getLgsDlvspList().remove(i);
				i -= 1;
			}
		}

		List<ClmDlvOrdGodInfoDTO> dlvspClmOrdGod = new ArrayList<ClmDlvOrdGodInfoDTO>();
		ClmDlvOrdGodInfoDTO clmDlvOrdGodInfoDTO = null;

		for(LgsDlvspExtend dlvsp : claimBoDTO.getLgsDlvspList()){
			for(ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()){
				clmDlvOrdGodInfoDTO = new ClmDlvOrdGodInfoDTO();
				clmDlvOrdGodInfoDTO.setClmResnCd(clmGod.getClmResnCd());
				dlvspClmOrdGod.add(clmDlvOrdGodInfoDTO);
			}
		}

		String masterClmResnCd = getClmMasterResn(dlvspClmOrdGod);
		claimBoDTO.setClmResnCd(masterClmResnCd);

		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			claimBoDTO.setMbrNo(userDetail.getMbr().getMbrNo());

			claimBoDTO.setRegtrId(userDetail.getMbr().getMbrId());       	// 로그인 세션정보
			claimBoDTO.setRegtrShopId("");		// 로그인된 매장ID
		}

		claimBoDTO.getClmExtend().setPgTrnsmisTgtYn("Y");//프론트 PG 전송여부 강제가능

		String clmNo = claimCommandComponent.addReturnClaim(claimBoDTO , pk);
		model.addAttribute("clmNo", clmNo);

		LgsDlvsp lgsDlvsp = new LgsDlvsp();
		for(LgsDlvsp lgsDlvsp2 : lgsDlvspList ){

			lgsDlvsp.setAddrSectCd(lgsDlvsp2.getAddrSectCd());

		}

		String defaultDelv = claimBoDTO.getDefaultDelv();
		String addMbrDlvspCheck = claimBoDTO.getAddMbrDlvspCheck();


		if ("Y".equalsIgnoreCase(defaultDelv) ||
				"Y".equalsIgnoreCase(addMbrDlvspCheck)) {

			LgsDlvsp mbrLgsDlvsp = new LgsDlvsp() ;
			for(LgsDlvsp mbrLgsDlvsp2 : lgsDlvspList ){
				Functions.copyProperties2(mbrLgsDlvsp, mbrLgsDlvsp2);
			}


			memberActivityFOComponent.updateDeliveryLocationMbrDlvsp(pk,mbrLgsDlvsp,defaultDelv,addMbrDlvspCheck);
		}

		try {
			claimNoticeComponent.sendAcceptReturnNotice(claimBoDTO.getOrdNo(), clmNo, pk, true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		log.info("ReturnClaim_Complete");
	}

    /**
     * 클레임 배송비 결제
     */
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_GUEST')")
    @RequestMapping(value = "/payment/addpay.json", method = RequestMethod.POST)
    public void addClaimDeliveryPayment(@ModelAttribute("orderDTO") OrderDTO orderDTO, Model model, HttpServletRequest request) throws Exception {
        SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);

        String addPayClmTpCd = request.getParameter("addPayClmTpCd");

        orderDTO.setMallId(systemPK.getMall());
        orderDTO.setLang(systemPK.getLang());
        orderDTO.setDevice(systemPK.getDevice());
        Mbr mbr = new Mbr();
        if (ContextService.hasRole()) {
            SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
            mbr = userDetail.getMbr();
        }
        orderDTO.setMbr(mbr);
        if (OrderClaimEnum.ClmTpEnum.PART_CNCL.toString().equals(addPayClmTpCd)) {
            claimCommandComponent.updateCancelClmDlvAmtPayMethod(orderDTO, request);
        } else {
        	claimCommandComponent.updateClmDlvAmtPayMethod(orderDTO, request);
        }

        model.addAttribute("result", "Y");
    }	
	
	/**
	 * 클레임 마스터 대표사유 추출
	 * 
	 * @param dlvspClmOrdGod
	 * @return
	 */
	private String getClmMasterResn(List<ClmDlvOrdGodInfoDTO> dlvspClmOrdGod){
		//CodeUtil.getCode(refundPayDTO.getLang(), clm.getClmResnCd()
		String masterClmResnCd = "";
		int masterClmResnNo = 0;
		// TODO : 클레임 대표사유 우선순위 선정 필요
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ETC", "1"); //기타 
    	map.put("WN_DLV", "2"); //오배송
    	map.put("COLOR_INFO_DFFRNC", "3"); //색상 정보 상이
    	map.put("SIZE_INFO_DFFRNC", "4"); //사이즈 정보 상이
    	map.put("ETC_GOD_INFO_GAP", "5"); //기타 상품정보상이
    	map.put("GOD_STAN", "6"); //상품 오염
    	map.put("GOD_DMG", "7"); //상품파손
    	map.put("GOD_BADN", "8"); //상품불량
    	map.put("OPT_CHG", "9"); //옵션변경
    	map.put("HDRY_LOST", "10"); //택배분실
    	map.put("OVSITE_DLV", "11"); //누락배송 
    	map.put("SYS_ERR_CNCL", "12"); //시스템오류취소
    	map.put("DPLC_ORD_CNCL", "13"); //이중주문취소
    	map.put("DLV_DELAY_CNCL", "14"); //배송지연취소
    	map.put("SHTG_CNCL", "15"); //결품취소
    	map.put("SIZE_EXCHG", "16"); //사이즈교환
    	map.put("PRC_GAP", "17"); //가격차이
    	map.put("CSTMR_CHGMIND_CNCL", "18"); //고객변심취소
    	map.put("AUTO_SHTG_CNCL", "19"); //자동결품취소
    	map.put("SHOP_NON_RECPT", "20"); //매장 미수령
    	map.put("CSTMR_CHGMIND", "21"); //단순변심
    	map.put("", "0");//null

		for(ClmDlvOrdGodInfoDTO dto : dlvspClmOrdGod){
			if(dto != null && dto.getClmResnCd() != null){
				if(masterClmResnNo < Integer.parseInt(map.get(dto.getClmResnCd()))){	//기존꺼보다 순위가 높다면
					masterClmResnNo = Integer.parseInt(map.get(dto.getClmResnCd()));	//순위가 높은 hashmap 키 등록
					masterClmResnCd = dto.getClmResnCd();	//순위가 높은 사유 등록
				}
			}
		}
		return masterClmResnCd;
	}
	
	/**
	 * 클레임 사유코드 클레임유형별 조회
	 * 
	 * @param clmTpCd
	 * @param lang
	 * @return
	 */
	private List<Code> getClmRsnCodeList(OrderClaimEnum.ClmTpEnum clmTpCd, String lang) {
		
		List<Code> rsnCodeList = new ArrayList<Code>();
		
		for (Code code : CodeUtil.getCodes("CLM_RSN", lang)) {
			
			OrderClaimEnum.ClmRsnEnum clmRsnEnum = OrderClaimEnum.ClmRsnEnum.valueOf(code.getCd());
			
			if (clmTpCd == OrderClaimEnum.ClmTpEnum.ALL_CNCL || clmTpCd == OrderClaimEnum.ClmTpEnum.PART_CNCL) {
				if (clmRsnEnum.isCancelRsn()) {
					rsnCodeList.add(code);
				}
			} else if (clmTpCd == OrderClaimEnum.ClmTpEnum.RTGOD){
				if (clmRsnEnum.isReturnRsn()) {
					rsnCodeList.add(code);
				}
			} else if (clmTpCd == OrderClaimEnum.ClmTpEnum.GNRL_EXCHG){
				if (clmRsnEnum.isExchangeRsn()) {
					rsnCodeList.add(code);
				}
			}
		}
		
		return rsnCodeList;
	}
}
