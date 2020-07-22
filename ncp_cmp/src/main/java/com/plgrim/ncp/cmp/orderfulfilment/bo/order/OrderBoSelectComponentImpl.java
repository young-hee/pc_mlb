package com.plgrim.ncp.cmp.orderfulfilment.bo.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZoneNationExtend;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.biz.callcenter.data.CsoCnsltMemoExtendDTO;
import com.plgrim.ncp.biz.callcenter.service.MemoService;
import com.plgrim.ncp.biz.claim.data.ClmCouponSearchDTO;
import com.plgrim.ncp.biz.claim.result.ClmCouponResult;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.service.DeliveryStatusService;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.service.MemberBenefitSelectService;
import com.plgrim.ncp.biz.member.service.MemberOrderSelectService;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.repository.OrderBoSelectRepository;
import com.plgrim.ncp.biz.order.result.ClmWrhsGodResult;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmDetailResult;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmResult;
import com.plgrim.ncp.biz.order.result.OrderBoResult;
import com.plgrim.ncp.biz.order.result.OrderRepairResult;
import com.plgrim.ncp.biz.order.service.OrderBoSelectService;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.biz.vendor.service.ComDmstcDlvCstPlcService;
import com.plgrim.ncp.commons.result.SysShopResult;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

@Component
public class OrderBoSelectComponentImpl extends AbstractComponent implements OrderBoSelectComponent{

	@Autowired
	OrderBoSelectService orderBoSelectService;

	@Autowired
	OrderSelectService orderSelectService;
	
	@Autowired
	MemberBenefitSelectService memberBenefitSelectService;

	@Autowired
	MemoService memoService;

	@Autowired
	DeliveryStatusService deliveryStatusService;

	@Autowired
	ComDmstcDlvCstPlcService comDmstcDlvCstPlcService;
	
	@Autowired
	MemberOrderSelectService memberOrderSelectService;
	
	@Autowired
	OrderBoSelectRepository orderBoSelectRepository;

	@Override
	public Ord selectOrdEntity(String ordNo) throws Exception {

		return orderSelectService.selectOrdEntity(ordNo);
	}

	@Override
	public Page<OrderBoResult> selectBoOrderList(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		return orderBoSelectService.selectBoOrderList(orderDTO, pageParam);
	}

	@Override
	public Page<OrderBoResult> selectBoPayList(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		return orderBoSelectService.selectBoPayList(orderDTO, pageParam);
	}
	
	@Override
	public Page<OrderBoResult> selectBoOrdGodList(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		return orderBoSelectService.selectBoOrdGodList(orderDTO, pageParam);
	}

	@Override
	public Page<OrderBoResult> selectAffOrdList(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		return orderBoSelectService.selectAffOrdList(orderDTO, pageParam);
	}
	
	@Override
	public Page<OrderBoResult> selectAffOrdErrList(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		return orderBoSelectService.selectAffOrdErrList(orderDTO, pageParam);
	}

	@Override
	public List<OrderBoResult> selectBOGodList(OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectService.selectBOGodList(orderDTO);
	}

	@Override
	public OrderBoResult selectBOOrderDt(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {

		OrderBoResult orderBoResult = orderBoSelectService.selectBOOrderDt(orderDTO);

		if (!orderBoResult.getMbrTpCd().equals("NMBR")) {
			MemberBoDTO dto = new MemberBoDTO();

			Mbr mbr = new Mbr();
			mbr.setMbrNo(orderBoResult.getMbrNo());
			dto.setMbr(mbr);
			orderBoResult.setCpnCount(memberBenefitSelectService.selectMyCouponCount(dto));
		}
		if (orderBoResult.getMbrTpCd().equals("UNITY_MBR") && StringService.isNotEmpty(orderBoResult.getErpCstmrNo())) {

			try {
				/**
				 * PO에서 조회시에는 회원의 잔여포인트 조회가 필요 없기 때문에
				 * 업체 코드가 넘어올 경우 호출하지 않도록 적용
				 */
				if(orderDTO.getComId() == null || "".equals(orderDTO.getComId())){

					long point = memberBenefitSelectService.getMemberReserveRemainAmount(orderBoResult.getErpCstmrNo());

					orderBoResult.setMemPnt(String.valueOf(point));
				}

			}
			catch (Exception e) {

				String msg = "";
				if (e.getCause() != null) {
					msg = e.getCause().getMessage();
				}
				else {
					msg = e.getMessage();
				}
				orderBoResult.setMemPnt(msg);
			}

		}
		return orderBoResult;
	}

	@Override
	public List<CsoCnsltMemoExtendDTO> selectCsoCnsltMemoList(CsoCnsltMemo csoCnsltMemo) throws Exception {
		return memoService.selectCsoCnsltMemoList(csoCnsltMemo);
	}

	@Override
	public CsoCnsltMemo getCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception {
		return memoService.getCsoCnsltMemo(csoCnsltMemo);
	}
	
	@Override
	public List<OrderBoResult> selectBOOrdClmList(OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectService.selectBOOrdClmList(orderDTO);
	}

	@Override
	public GodExtend selectBoOrdGodItmHist(OrdGodExtend ordGodExtend) throws Exception {

		return orderBoSelectService.selectBoOrdGodItmHist(ordGodExtend);

	}

	@Override
	public List<LgsDlivyDrctGodExtend> selectOrdDlivyDrct(OrderBoDTO orderDTO) throws Exception {

		DlvOrdGodInfoDTO dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();
		dlvOrdGodInfoDTO.setOrdNo(orderDTO.getOrdNo());
		dlvOrdGodInfoDTO.setOrdGodTurn(String.valueOf(orderDTO.getOrdGodTurn()));
		dlvOrdGodInfoDTO.setAdminId("select");
		return deliveryStatusService.selectOrdDlivyDrct(dlvOrdGodInfoDTO);

	}

	@Override
	public List<GodExtend> selectBoItmHist(OrderBoDTO orderDTO, long qty) throws Exception {

		List<GodExtend> list = orderBoSelectService.selectBoItmHist(orderDTO);
		List<GodExtend> rtList = new ArrayList<GodExtend>();
		boolean flag = true;
		long stockQty = 0;
		for (GodExtend godExtend : list) {
			String resveSaleGodYn = godExtend.getResveSaleGodYn();

			if (orderDTO.getOrdTp().equals("RESVE_ORD") && resveSaleGodYn != null && resveSaleGodYn.equals("Y")) {

				if (godExtend.getResveInvQty() < qty) {

					flag = false;
				}
				else {
					stockQty = godExtend.getResveInvQty();
				}

			}//한정 재고가 Y 인경우
			else if (godExtend.getLmttInvYn() != null && godExtend.getLmttInvYn().equals("Y")) {

				if (orderDTO.getOrdTp() != null && orderDTO.getOrdTp().equals("AFF_ORD")) {

					//재휴사 한정 재고 수량
					if (godExtend.getAffComLmttInvQty() < qty) {

						flag = false;
					}
					else {
						stockQty = godExtend.getAffComLmttInvQty();
					}

				}
				else {
					//온라인 한정 재고 수량
					if (godExtend.getOnlneLmttInvQty() < qty) {

						flag = false;
					}
					else {
						stockQty = godExtend.getOnlneLmttInvQty();
					}

				}
			}
			else {

				long safeInvQty = 0;

				if (godExtend.getSafeInvUseYn().equals("Y")) {

					safeInvQty = godExtend.getSafeInvQty();
				}
				// 실재고 = 총 가용 재고 수량 - 판매 예정 수 - IF(안전 재고 사용여부=Y,안전 재고 수,0)

				long realQty = godExtend.getTotUsefulInvQty() - godExtend.getSalePrearngeQty() - safeInvQty;

				if (realQty < qty) {
					flag = false;
				}
				else {
					stockQty = realQty;
				}

			}

			if (flag) {
				godExtend.setQty(stockQty);
				rtList.add(godExtend);
			}
		}

		return rtList;

	}

	@Override
	public HashMap<String, Object> ordValidation(OrderBoDTO orderDTO) throws Exception {

		HashMap<String, Object> map = Maps.newHashMap();
		map.put("RT", "F");

		DlvOrdGodInfoDTO dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();
		dlvOrdGodInfoDTO.setOrdNo(orderDTO.getOrdNo());
		dlvOrdGodInfoDTO.setAdminId("validation");
		if (orderDTO.getOrdGodTurn() > 0) {
			dlvOrdGodInfoDTO.setOrdGodTurn(String.valueOf(orderDTO.getOrdGodTurn()));
		}
		if (orderDTO.getDlvPcupspTurn() > 0) {
			dlvOrdGodInfoDTO.setDlvPcupspTurn(String.valueOf(orderDTO.getDlvPcupspTurn()));
		}

		/*
         * 전체 '배송상태코드'(DLV_STAT_CD) 체크
         */

		List<LgsDlivyDrctGodExtend> lgsDlivyDrctGods = deliveryStatusService.selectOrdDlivyDrct(dlvOrdGodInfoDTO);

		int count = lgsDlivyDrctGods.size();
		int temp =1;
		long qty =0;

		if (orderDTO.getValidationType().equals("ITM") && count==0) {

			map.put("MSG", "해당 상품은 수량 전체가 취소가 되어서 변경할수가 없습니다.");
			return map;

		}
		
		
		for (LgsDlivyDrctGodExtend lgsDlivyDrctGod : lgsDlivyDrctGods) {

			qty += lgsDlivyDrctGod.getDlivyDrctQty() - lgsDlivyDrctGod.getDlivyDrctCnclQty();
			
			if(temp == count){
				
				if(qty <=0 ){
                   if (orderDTO.getValidationType().equals("DLV") ||
						   orderDTO.getValidationType().equals("PKUP_DLV")) {
						map.put("MSG", "해당 배송지에 출고할수 있는 상품이 없습니다.");
						return map;

					}
				}
				 
			}
			
			if (orderDTO.getOrdTp().equals("SHOP_PKUP_ORD")) {
				if (orderDTO.getValidationType().equals("DLV")) {
					String cd = lgsDlivyDrctGod.getDlvStatCd();
					if (!(cd.equals("DLIVY_DRCT_WAIT") || cd.equals("DLIVY_DRCT") || cd.equals("SHTG_RCEPT") || cd.equals("DLV_WAIT") || cd.equals("DLIVY_DRCT_CNCL") || cd.equals("SHOP_PRPARE_COMPT"))) {
						map.put("MSG", "출고완료 이후 상품이 존재 하여 배송지 변경이 불가합니다.");
						return map;
					}
				}
			}
			else {
				String cd = lgsDlivyDrctGod.getDlvStatCd();
				String dm = lgsDlivyDrctGod.getDmstcWaybilNo();
				 

				if (!(cd.equals("DLIVY_DRCT_WAIT") || cd.equals("DLIVY_DRCT") || cd.equals("SHTG_RCEPT") || cd.equals("DLV_WAIT") || cd.equals("DLIVY_DRCT_CNCL")
						|| cd.equals("EXCHG_WRHS_WAIT"))) {
					if (orderDTO.getValidationType().equals("ITM")) {
						map.put("MSG", "배송중인 상품 옵션 변경 불가");
						return map;
					}
					else if (orderDTO.getValidationType().equals("DLV")) {
						map.put("MSG", "출고완료 이후 상품이 존재 하여 배송지 변경이 불가합니다.");
						return map;
					}
				}
				/* 
				 * BO/CSO 배송지 변경 불가 시 얼럿 문구 변경
				 */
				if (cd.equals("DLIVY_DRCT") && StringService.isNotEmpty(dm)) {
					map.put("MSG", "운송장 생성이 완료되어 배송지 변경이 불가합니다.");
					return map;
				}

			}
			temp++;
		}

		map.put("RT", "S");
		map.put("lgsDlivyDrctGods", lgsDlivyDrctGods);
		return map;
	}

	@Override
	public List<OrdGodAplPrmExtend> selectOrdPrmDtlList(OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectService.selectOrdPrmDtlList(orderDTO);

	}
	
	@Override
	public OrderBoResult selectEmailOrderCompt(String ordNo) throws Exception {
		/*
		OrderBoDTO orderDTO = new OrderBoDTO();
		orderDTO.setOrdNo(ordNo);
		return orderBoSelectService.selectEmailOrderCompt(orderDTO);
		*/
		/*
		 * 1. 수정일자   : 2016-01-25
		 * 2. 수정자     : 김재성 (jskim27)
		 * 3. 요청 SR NO : #17112
		 * 4. 수정내용   : [배송] '발송완료' 메일 발송 시 배송처/주문상품 정보 노출안됨
		 * 					- '입금지연안내', '자동주문취소안내', '상품발송안내', '발송지연안내' 메일 발송에서 공통적으로 사용하기 때문에
		 * 						'상품발송안내' 메일 이외에는 송장번호 파라미터를 "" 로 설정해서 호출
		 */
		return this.selectEmailOrderCompt( ordNo, "");
	}

	@Override
	public OrderBoResult selectEmailOrderCompt(String ordNo, String dmstcWaybilNo) throws Exception {
		OrderBoDTO orderDTO = new OrderBoDTO();
		orderDTO.setOrdNo(ordNo);
		orderDTO.setDmstcWaybilNo( dmstcWaybilNo );
		return orderBoSelectService.selectEmailOrderCompt(orderDTO);
	}
	
	@Override
	public OrderBoResult selectEmailOrderComptGlobal(String ordNo, String dmstcWaybilNo) throws Exception {
		OrderBoDTO orderDTO = new OrderBoDTO();
		orderDTO.setOrdNo(ordNo);
		orderDTO.setDmstcWaybilNo( dmstcWaybilNo );
		return orderBoSelectService.selectEmailOrderComptGlobal(orderDTO);
	}

	/*
	 * Claim
	 * ********************************************************************
	 * ****************************
	 */

	/**
	 * 주문 - 클레임접수기본정보조회[주문상품별](교환)
	 */
	@Override
	public List<OrdGodForRtnClmDetailResult> selectOrdGodInfo(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectService.selectOrdGodInfo(orderDTO);
	}

	/**
	 * 주문 - 클레임접수기본정보조회[주문상품별](부분취소/반품)
	 */
	@Override
	public List<OrdGodForRtnClmDetailResult> selectOrdGodInfoWithGft(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectService.selectOrdGodInfoWithGft(orderDTO);
	}
	
	
	
	/**
	 * 주문 - 반품접수 클레임접수기본정보조회[반품접수-물류배송지별]
	 */
	@Override
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmWithGft(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {
		
		return orderBoSelectService.selectOrdGodForRtnClmWithGft(orderDTO);
	}
	
	/**
	 * 주문 - 반품접수 클레임접수기본정보조회[반품접수-물류배송지별]FO에서 주문건 제외
	 */
	@Override
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmWithGftFo(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {
		
		return orderBoSelectService.selectOrdGodForRtnClmWithGftFo(orderDTO);
	}
	
	/**
	 * 주문 - 반품접수 클레임접수기본정보조회[반품접수-물류배송지별]
	 */
	@Override
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClm(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectService.selectOrdGodForRtnClm(orderDTO);
	}

	/**
	 * 주문 - 반품접수 클레임접수기본정보조회[반품접수-물류배송지별] - FO에서 수선수량 제거
	 */
	@Override
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmFo(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectService.selectOrdGodForRtnClmFo(orderDTO);
	}
	
	/**
	 * 주문 - 반품접수 옵션변경 가능 상품정보 조회
	 */
	@Override
	public List<GodExtend> selectBoItmHistForClm(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectService.selectBoItmHistForClm(orderDTO);
	}

	/**
	 * 주문 - 반품접수 클레임접수기본정보조회[반품접수-물류배송지별]
	 */
	@Override
	public List<LgsDlvspExtend> selectCoDlvspList(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectService.selectCoDlvspList(orderDTO);
	}

	/**
	 * 클레임관리 - 반품 수정후 팝업 클레임기본정보조회[반품 - 물류배송지별]
	 */
	@Override
	public List<ClmWrhsGodResult> selectClmWrhsGodForRtnClmWithGft(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectService.selectClmWrhsGodForRtnClmWithGft(orderDTO);
	}
	
	@Override
	public List<ClmWrhsGodResult> selectClmWrhsGodForRtnClm(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectService.selectClmWrhsGodForRtnClm(orderDTO);		
	}

	@Override
	public List<LgsDlvspExtend> selectDlvspListForOrd(OrderBoDTO orderDTO) throws Exception {				
	    return orderBoSelectService.selectDlvspListForOrd(orderDTO);
    }

	@Override
	public String selectOrdTpCd(SystemPK systemPK, String ordNo) {
		return orderBoSelectService.selectOrdTpCd(ordNo);
	}
	
	/*
	 * Claim
	 * ********************************************************************
	 * ****************************
	 */

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * 	- 픽업가능 매장 리스트 조회
	 *
	 * @param lgsDlivyDrctGodExtend 주문번호, 최대출고지시수량, 단품개수, 기존 픽업매장ID 등
	 * @return List<SysShopResult> 픽업매장 리스트
	 * @throws Exception
	 */
	@Override
	public List<SysShopResult> selectPkupShop(LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend) throws Exception {
		return this.orderBoSelectService.selectPkupShop(lgsDlivyDrctGodExtend);
	}

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * 	- 해당 픽업주문의 단품갯수, 최대 출고지시 수량 조회
	 *
	 * @param ordNo 주문번호
	 * @return
	 * @throws Exception
	 */
	@Override
	public LgsDlivyDrctGodExtend getMaxDlivyCount4Pkup(String ordNo) throws Exception {
		return this.orderBoSelectService.getMaxDlivyCount4Pkup(ordNo);
	}
	
	/**
	 * #31984 [글로벌] BO 주문조회 페이지 개선 요청
	 * - 배송국가 리스트 조회
	 *
	 * @param comOvseaDlvZoneNationExtend
	 * @return List<ComOvseaDlvZoneNationExtend>
	 * @throws Exception
	 */
	@Override
	public List<ComOvseaDlvZoneNationExtend> selectComOvseaDlvZoneNation(SystemPK systemPK, ComOvseaDlvZoneNationExtend comOvseaDlvZoneNationExtend) throws Exception {
		return this.orderBoSelectService.selectComOvseaDlvZoneNation(comOvseaDlvZoneNationExtend);
	}

	/**
	 * 무료수선가능대상상품 조회
	 */
	@Override
	public List<OrderRepairResult> selectOrdGodForRepair(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectService.selectOrdGodForRepair(orderDTO);
	}
	
	/**
	 * 무료수선가능대상상품 수 조회
	 */
	@Override
	public Integer selectOrdGodForRepairCount(OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectService.selectOrdGodForRepairCount(orderDTO);
	}
	
	/**
	 * 클레임 - 반품/교환 무료 배송비쿠폰 조회
	 */
	@Override
	public List<ClmCouponResult> selectClmCouponList(SystemPK systemPK, ClmCouponSearchDTO clmCouponSearchDTO) throws Exception {
		
		return orderBoSelectService.selectClmCouponList(clmCouponSearchDTO);
	}
	
	@Override
	public GodExtend selectBoBulkOrdGodItmInfo(OrdGodExtend ordGodExtend) throws Exception {
		return orderBoSelectService.selectBoBulkOrdGodItmInfo(ordGodExtend);
	}
	
	@Override
	public int selectBoOrderListItemCount(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectBoOrderListItemCount(orderDTO);
	}
	
	
}
