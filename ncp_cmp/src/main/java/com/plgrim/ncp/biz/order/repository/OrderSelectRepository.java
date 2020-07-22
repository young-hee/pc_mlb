package com.plgrim.ncp.biz.order.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmFoExtend;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZoneNationExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtends;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspFoExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtends;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodInv;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrndExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.cart.data.ReOrderCartDTO;
import com.plgrim.ncp.biz.member.result.MypageOrderRtExchgPrmResult;
import com.plgrim.ncp.biz.order.data.ComOvseaDlvZoneChrgeDTO;
import com.plgrim.ncp.biz.order.data.CouponSearchDTO;
import com.plgrim.ncp.biz.order.data.MbrDelvSearchDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderFoEntity;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderRtExchgPrmSearchDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.data.OrderGiftDTO;
import com.plgrim.ncp.biz.order.data.OrderParamDTO;
import com.plgrim.ncp.biz.order.data.OrderStockDTO;
import com.plgrim.ncp.biz.order.data.SysExchgRtDTO;
import com.plgrim.ncp.biz.order.result.GodSvcResult;
import com.plgrim.ncp.biz.order.result.MypageClaimFoResult;
import com.plgrim.ncp.biz.order.result.MypageOrderFoResult;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmResult;
import com.plgrim.ncp.biz.order.result.OrderCompleteResult;
import com.plgrim.ncp.biz.order.result.OrderCouponResult;
import com.plgrim.ncp.biz.order.result.OrderLinkPriceResult;
import com.plgrim.ncp.commons.data.order.InfOrderSalesDTO;
import com.plgrim.ncp.commons.data.order.OrdGodReOrderDTO;
import com.plgrim.ncp.commons.data.order.sdo.IsPrHeaderSDO;
import com.plgrim.ncp.commons.data.order.sdo.ItGiftSDO;
import com.plgrim.ncp.commons.data.order.sdo.ItPrItemSDO;
import com.plgrim.ncp.commons.data.order.sdo.ItPrPaymentSDO;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.order.data.OrderUseTempMileageListSDO;
import com.plgrim.ncp.interfaces.order.data.OrderUseTempMileageSDO;

@Repository
public class OrderSelectRepository extends AbstractRepository {
	/**
	 * 회원배송지 목록 조회.
	 * @param mbrDelvSearchDTO MbrDelvSearchDTO
	 * @param pageParam PageParam
	 * @return Page<MbrDlvsp>
	 * @throws Exception
	 */
	public Page<MbrDlvsp> selectMemberDeliveryList(MbrDelvSearchDTO mbrDelvSearchDTO, PageParam pageParam) throws Exception {

		// 페이지 인덱스 셋팅
		RepositoryHelper.makePageEntityIndex(mbrDelvSearchDTO, pageParam);
		List<MbrDlvsp> results = getSession1().selectList("com.plgrim.ncp.biz.order.select.selectMemberDeliveryList", mbrDelvSearchDTO);

		// 전체 Row 수 구하기
		long totalRow = this.selectMemberDeliveryListCount(mbrDelvSearchDTO);
		return new PageImpl<MbrDlvsp>(results, pageParam.getPageable(), totalRow);
	}

    /**
     * 회원 배송지 목록 조회 카운트.
     * @param mbrDelvSearchDTO MbrDelvSearchDTO
     * @return long
     * @throws Exception
     */
	public long selectMemberDeliveryListCount(MbrDelvSearchDTO mbrDelvSearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectMemberDeliveryListCount", mbrDelvSearchDTO);
	}

	/**
	 * 회원 배송지 조회.
	 * @param mbrDlvsp MbrDlvsp
	 * @return MbrDlvsp
	 * @throws Exception
	 */
	public MbrDlvspExtend selectMemberDelivery(MbrDlvspExtend mbrDlvsp) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectMemberDelivery", mbrDlvsp);
	}
	
	/**
	 * 최근배송지 목록 조회
	 * @param mbrDelvSearchDTO MbrDelvSearchDTO
	 * @param pageParam PageParam
	 * @return Page<MbrDlvsp>
	 * @throws Exception
	 */
	public Page<MbrDlvsp> selectOrderDeliveryList(MbrDelvSearchDTO mbrDelvSearchDTO, PageParam pageParam) throws Exception {
		
		// 페이지 인덱스 셋팅
		RepositoryHelper.makePageEntityIndex(mbrDelvSearchDTO, pageParam);
		List<MbrDlvsp> results = getSession1().selectList("com.plgrim.ncp.biz.order.select.selectOrderDeliveryList", mbrDelvSearchDTO);

		// 전체 Row 수 구하기
		long totalRow = this.selectOrderDeliveryListCount(mbrDelvSearchDTO);
		return new PageImpl<MbrDlvsp>(results, pageParam.getPageable(), totalRow);
	}
	
	/**
	 * 최근배송지 목록 조회 카운트.
	 * @param mbrDelvSearchDTO MbrDelvSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectOrderDeliveryListCount(MbrDelvSearchDTO mbrDelvSearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOrderDeliveryListCount", mbrDelvSearchDTO);
	}
	
	/**
	 * 주문사은품 조회
	 * @param prmNo String
	 * @return OrderGiftDTO
	 * @throws Exception
	 */
	public OrderGiftDTO selectOrderGiftList(String prmNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOrderGift", prmNo);
	}

	/**
	 * 회원쿠폰조회
	 * @param couponSearchDTO CouponSearchDTO
	 * @return List<OrderCouponResult>
	 */
	public List<OrderCouponResult> selectOrderCouponList(CouponSearchDTO couponSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectOrderCouponList", couponSearchDTO);
	}

//	/**
//	 * 회원쿠폰수량조회
//	 * @param couponSearchDTO CouponSearchDTO
//	 * @return Integer
//	 * @throws Exception
//	 */
//	public Integer selectOrderCouponListCount(CouponSearchDTO couponSearchDTO) throws Exception {
//		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOrderCouponListCount", couponSearchDTO);
//	}
	
	/**
	 * 매장정보조회
	 * @param sysShopBrnd SysShopBrndExtend
	 * @return SysShopExtends
	 * @throws Exception
	 */
	public SysShopExtends selectSysShop(SysShopBrndExtend sysShopBrnd) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectSysShop", sysShopBrnd);
	}
	
	/**
	 * 상품의 유효성체크
	 * @param stock OrderStockDTO
	 * @return String
	 * @throws Exception
	 */
	public String selectGoodsStatus(OrderStockDTO stock) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectGoodsStatus", stock);
	}
	
	/**
	 * 사은품 재고체크
	 * @param godNo
	 * @return OrdGodExtends
	 * @throws Exception
	 */
	public OrdGodExtends selectGiftStock(String godNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectGiftStock", godNo);
	}	

	public int selectFoOrderListCount(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectFoOrderListCount", mypageOrderInfoDTO);
	}
	
	public Page<MypageOrderFoResult> selectFoOrderList(MypageOrderInfoDTO mypageOrderInfoDTO, PageParam pageParam) throws Exception {
		RepositoryHelper.makePageEntityIndex(mypageOrderInfoDTO, pageParam);
		
		List<MypageOrderFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.order.select.selectFoOrderList", mypageOrderInfoDTO);
		int totalRow = getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectFoOrderListCount", mypageOrderInfoDTO);
		
		return new PageImpl<MypageOrderFoResult>(results, pageParam.getPageable(), totalRow);
	}

	public Page<MypageClaimFoResult> selectFoClaimList(MypageOrderInfoDTO mypageOrderInfoDTO, PageParam pageParam) throws Exception {
		RepositoryHelper.makePageEntityIndex(mypageOrderInfoDTO, pageParam);
		
		List<MypageClaimFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.order.select.selectFoClaimList", mypageOrderInfoDTO);
		int totalRow = getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectFoClaimListCount", mypageOrderInfoDTO);
		
		return new PageImpl<MypageClaimFoResult>(results, pageParam.getPageable(), totalRow);
	}

	/* 모빌리언즈 승인번호 조회 */
	public String selectMobilPonPayAprvNoList(MypageOrderFoResult dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectMobilPonPayAprvNoList", dto);
	}

	public Page<MypageOrderFoResult> selectFoReceiptList(MypageOrderInfoDTO dto, PageParam pageParam) throws Exception {
		RepositoryHelper.makePageEntityIndex(dto, pageParam);

		List<MypageOrderFoResult> results =  getSession1().selectList("com.plgrim.ncp.biz.order.select.selectFoReceiptList", dto);
		int totalCnt = getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectFoReceiptListTotalCount", dto);

		return new PageImpl<MypageOrderFoResult>(results, pageParam.getPageable(), totalCnt);
	}


	public List<MypageOrderFoResult> selectFoReceiptList(MypageOrderInfoDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectFoReceiptList2", dto);
	}

	public MypageOrderInfoDTO selectFoOrderInfo(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectFoOrderInfo", mypageOrderInfoDTO);
	}
	
	public List<MypageOrderRtExchgPrmResult> getOrderGoodsRtExchgPromotionInfoList(MypageOrderRtExchgPrmSearchDTO search) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.getOrderGoodsRtExchgPromotionInfoList", search);
	}

	public MypageOrderInfoDTO selectPayMethodChange(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectPayMethodChange", mypageOrderInfoDTO);
	}
	public MypageOrderInfoDTO selectPayMethodChangeClm(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectPayMethodChangeClm", mypageOrderInfoDTO);
	}
	public MypageOrderInfoDTO selectDlvYn(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectDlvYn", mypageOrderInfoDTO);
	}
	public MypageOrderInfoDTO selectPayFoMobilPonPayRfd(String ordNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectPayFoMobilPonPayRfd", ordNo);
	}

	public List<ClmFoExtend> selectClmFoList(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectClmFoList", mypageOrderInfoDTO);
	}	
	//ncp 3차 ********************************************************************************************
	public List<ClmFoExtend> selectClmFoListGlobal(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.orderGlobal.select.selectClmFoListGlobal", mypageOrderInfoDTO);
	}
	//ncp 3차 ********************************************************************************************

	public List<OrdGodExtend> selectFoGodInfo(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectFoGodInfo", mypageOrderInfoDTO);
	}	

	public LgsDlvsp selectOrderDeliveryLocationPop(LgsDlvspFoExtend lgsDlvspFoExtend) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOrderDeliveryLocationPop", lgsDlvspFoExtend);
	}	
	//ncp 3차 주문배송지 조회 ********************************************************************************************
	public LgsDlvspFoExtend selectOrderDeliveryLocationPopGlobal(LgsDlvspFoExtend lgsDlvspFoExtend) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.orderGlobal.select.selectOrderDeliveryLocationPopGlobal", lgsDlvspFoExtend);
	}
	//ncp 3차 주문배송지 조회 ********************************************************************************************



	public IsPrHeaderSDO selectIsPrHeader(String ordNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectIsPrHeader", ordNo);
	}

	public List<ItPrItemSDO> selectItPrItemSDOList(String ordNo) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectItPrItemSDOList", ordNo);
	}

    public List<ItPrItemSDO> selectItPrItemSDOListForPartmal(String ordNo) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectItPrItemSDOListForPartmal", ordNo);
	}

	public List<ItPrPaymentSDO> selectItPrPaymentSDOList(String ordNo) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectItPrPaymentSDOList", ordNo);
	}
	
	public List<ItGiftSDO> selectItGiftSDOList(String ordNo) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectItGiftSDOList", ordNo);
	}
	
	public List<MypageOrderInfoDTO> selectDlvCstSumAmt(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectDlvCstSumAmt", mypageOrderInfoDTO);
	}
	public List<MypageOrderInfoDTO> selectDlvCstSumAmtList(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectDlvCstSumAmtList", mypageOrderInfoDTO);
	}
	
	public SysShopBrnd selectDlvShopYn(SysShopBrnd sysShopBrnd) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectDlvShopYn", sysShopBrnd);
	}
	
	public SysShopBrnd selectPkupDlvShopYn(SysShopBrndExtend sysShopBrndExtend) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectPkupDlvShopYn", sysShopBrndExtend);
	}
	/**
	 * 주문상품 옵션 조회
	 * @param mypageOrderInfoDTO mypageOrderInfoDTO
	 * @return List<MypageOrderFoResult>
	 * @throws Exception
	 */
	public List<MypageOrderFoResult> selectOrdGoodOption(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectOrdGoodOption", mypageOrderInfoDTO);
	}
	
	/**
	 * 주문세트상품 옵션 조회
	 * @param mypageOrderInfoDTO mypageOrderInfoDTO
	 * @return List<MypageOrderFoResult>
	 * @throws Exception
	 */
	public List<MypageOrderFoResult> selectOrdSetOption(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectOrdSetOption", mypageOrderInfoDTO);
	}
	
	/**
	 * 주문완료
	 * */
	public OrderCompleteResult selectOrderCompleteResult(OrderParamDTO ord) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOrderCompleteResult", ord);
	}
	
	public OrderCompleteResult selectVirtualConfirm(String payNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectVirtualConfirm", payNo);
	}
	
	public List<InfOrdGodErpDstbExtends> selectInfOrdGodErpDstbList(String ordNo) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectInfOrdGodErpDstbList", ordNo);
	}
	
	public String selectMultiLgsDlvYn(String ordNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectMultiLgsDlvYn", ordNo);
	}
	
	
	public List<OrderLinkPriceResult> selectOrderLinkPriceInfo(String ordNo) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectOrderLinkPriceInfo", ordNo);
	}
	////////// FRONT END /////////////////////////////////////////////	

//	@Cacheable(
//			value="OrderSelectRepository.selectExchangeRt",
//			key="{"
//					+ "#a0.crncyCd"
//					+ "}")
	public SysExchgRtDTO selectExchangeRt(SysExchgRtDTO sysExchgRtDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectExchangeRt", sysExchgRtDTO);
	}

	public SysExchgRtDTO selectExchangeRtByAplBegDtStr(SysExchgRtDTO sysExchgRtDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectExchangeRtByAplBegDtStr", sysExchgRtDTO);
	}

	public List<ComOvseaDlvZoneNationExtend> selectNationInfo(ComOvseaDlvZoneNationExtend comOvseaDlvZoneNationExtend) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectNationInfo", comOvseaDlvZoneNationExtend);
	}

	public ComOvseaDlvZoneChrgeDTO selectOvseaDlvFee(ComOvseaDlvZoneChrgeDTO comOvseaDlvZoneChrge) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOvseaDlvFee", comOvseaDlvZoneChrge);
	}
	
	//#31461 으로 추가2016-11-17
	public ComOvseaDlvZoneChrgeDTO selectOvseaDlvFeeHist(ComOvseaDlvZoneChrgeDTO comOvseaDlvZoneChrge) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOvseaDlvFeeHist", comOvseaDlvZoneChrge);
	}


	public List<ComOvseaDlvZoneChrgeDTO> selectOvseaDlvZoneChrgeList(ComOvseaDlvZoneChrgeDTO comOvseaDlvZoneChrge) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectOvseaDlvZoneChrgeList", comOvseaDlvZoneChrge);
	}

	/**
	 * 카페 24 회원 주문 select - CAFE
	 * @param orgMbrId
	 * @return
	 * @throws Exception
	 */
	public List<Ord> selectCafeMbrOrd(String orgMbrId) throws IOException {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectCafeMbrOrd", orgMbrId);
	}


	/**
	 * 카페 24 회원 주문 정보 조회 - NCP
	 * @param ordNo
	 */
	public List<HashMap<String, String>> selectCafeDXMOrd(String ordNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectCafeDXMOrd", ordNo);
	}

	public String selectGodWt(String godNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectGodWt", godNo);
	}
	
	public String selectGodWtForCpst(String godNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectGodWtForCpst", godNo);
	}
	
	public int selectOrderCount(MypageOrderInfoDTO mypageOrderInfoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOrderCount", mypageOrderInfoDTO);
	}

	public String selectDmstcDlvFee(String godNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectDmstcDlvFee", godNo);
	}
	public String getInterfaceOrder(String payNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.getInterfaceOrder", payNo);
	}

	public Pay selectPayByPayNo(String result) {
		Pay pay = new Pay();
		pay.setPayNo(result);
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectPayByPayNo", pay);
	}
	
	public int selectCouponDlvFlag(OrdGod ordGod) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectCouponDlvFlag", ordGod);
	}
	
	public int selectCouponRtlDlvFlag(OrdGod ordGod) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectCouponRtlDlvFlag", ordGod);
	}

	public List<OrdGod> selectRemainOrdGodAplPrmList(String ordNo,int dlvPcupspTurn) {	
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("ordNo", ordNo);
		map.put("dlvPcupspTurn", dlvPcupspTurn);
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectRemainOrdGodAplPrmList", map);
	}

	public int selectAplPrmGodDlvFeeCnt(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectAplPrmGodDlvFeeCnt", ordNo);
	}

	public MypageOrderInfoDTO getOrderSaleAmountInfo(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.getOrderSaleAmountInfo", ordNo);
	}
	
	public String getMaxDlvChrgeHistTurn(ComOvseaDlvZoneChrgeDTO param) {		
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.getMaxDlvChrgeHistTurn", param);
	}
	
	public List<MbrIssuCpn> selectUsedOnOffCoupon(MbrIssuCpn mbrIssuCpn) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectUsedOnOffCoupon", mbrIssuCpn);
	}

	//#34425 로 추가 2017-01-16
	//글로벌은 해외판매여부와 해당언어별 몰 전시여부 체크하여 하나라도 N인 경우 판매불가 처리
	public String selectGoodsGlobalChkStatus(OrderStockDTO stock) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectGoodsGlobalChkStatus", stock);
	}

	//스플릿 프로젝트로 추가 2017-03-17
	public List<MbrIssuCpn> selectUsedOnOffCouponSplit(MbrIssuCpn cpnSearch) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectUsedOnOffCouponSplit", cpnSearch);
	}
	
	public List<Ord> selectOrdForUpdate(String ordNo) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectOrdForUpdate", ordNo);
	}

	/**
	 * 주문의 마지막 클레임 정보 조회
	 * 
	 * @param pay
	 * @return
	 */
	public PayExtend selectLastClmOfOrd(Pay pay) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectLastClmOfOrd", pay);
	}

	/**
	 * 포장서비스 타입 조회(브랜드박스, 쇼핑백)
	 * @param godNo
	 * @return List<GodSvcResult>
	 */
	public List<GodSvcResult> selectGoodWrapServiceList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectGoodWrapServiceList", godNo);
	}

	/**
	 * 주문서 내 퀵배송 가능지역 검색
	 * @param ordNo
	 * @return ComQdlvArea
	 */
	public LgsDlvsp selectLgsDlvspOfQdlvOrder(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectLgsDlvspOfQdlvOrder", ordNo);
	}

    /**
     * 퀵배송 가능지역 검색
     * @param postNo
     * @return ComQdlvArea
     */
    public int selectQdlvComArea(String postNo) {
        return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectQdlvComArea", postNo);
    }

	public int selectComQdlvOldArea(String splitPostNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectComQdlvOldArea", splitPostNo);
	}
	
	public List<OrdGodExtend> selectCoOrdGodList(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectCoOrdGodList", orderDTO);
	}
	
	/*
	 * 주문 - 부분취소 클레임 접수화면 하단의 배송지별 리스트 (상품사은품 포함)
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodPartCancelClmWithGft(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodPartCancelClmWithGft", orderDTO);
	}
	
	public List<LgsDlvsp> selectDeliveryMemoList(Ord ord){
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectDeliveryMemoList", ord);
	}
	
	public List<OrdGodInv> selectOrderGoodsWmsItemQuantityList(Ord ord){
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectOrderGoodsWmsItemQuantityList", ord);
	}
	
	/**
	 * 구매확정 마일리지 적립 목록(수량단위)
	 * 
	 * @param ordGodList
	 * @return
	 */
	public OrderUseTempMileageSDO selectOrderDecisionAccmlMileList(List<OrdGod> ordGodList) {
		
		MypageOrderFoEntity dto = new MypageOrderFoEntity();
		dto.setOrdGodList(ordGodList);
		
		List<OrderUseTempMileageListSDO> list = getSession1().selectList("com.plgrim.ncp.biz.order.select.selectOrderDecisionAccmlMileList", dto);
		
		OrderUseTempMileageSDO sdo = new OrderUseTempMileageSDO();
		sdo.setOrdList(list);
		
		return sdo;
	}
	
	public List<LgsDlivyDrctGod> selectLgsDlivyDrctGod(OrderParamDTO dto){
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectLgsDlivyDrctGod", dto);
	}
	
	public List<InfOrderSalesDTO> selectOrderSalesList(OrderParamDTO dto){
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectOrderSalesList", dto);
	}
	
	public List<InfOrderSalesDTO> selectMileageTempDataList(OrderParamDTO dto){
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectMileageTempDataList", dto);
	}
	
	public Ord selectOrderInfo(String ordNo){
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOrderInfo", ordNo);
	}
	
	/**
	 * 구매확정 회원 발급 쿠폰 여부 확인
	 * @param mbrNo
	 * @return
	 */
	public int selectOrdGodSaleComptFirst(Ord ord) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOrdGodSaleComptFirst", ord);
	}
	
	public List<OrdGodReOrderDTO> selectReOrderGodList(ReOrderCartDTO reOrderCartDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.order.select.selectReOrderGodList",reOrderCartDTO);
	}
	
	public String getNaverPayTradeComptPayNo(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.getNaverPayTradeComptPayNo", ordNo);
	}

	public String selectOrdMallId(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOrdMallId", ordNo);
	}
		
	/**
	 * 주문에 적용된 온/오프라인 쿠폰 금액 조회
	 * 
	 * @param mbrIssuCpn
	 * @return
	 */
	public OrdGodAplPrmExtends selectAppliedyOnOffCouponAmount(MbrIssuCpn mbrIssuCpn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectAppliedyOnOffCouponAmount", mbrIssuCpn);
	}	
}
