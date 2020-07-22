package com.plgrim.ncp.biz.order.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmFoExtend;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZoneNationExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtends;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspFoExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPreferPayMn;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnCrtfcCd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrndExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.base.repository.mbr.MbrPreferPayMnRepository;
import com.plgrim.ncp.base.repository.ord.OrdRepository;
import com.plgrim.ncp.biz.member.result.MypageOrderRtExchgPrmResult;
import com.plgrim.ncp.biz.order.data.ComOvseaDlvZoneChrgeDTO;
import com.plgrim.ncp.biz.order.data.CouponSearchDTO;
import com.plgrim.ncp.biz.order.data.MbrDelvSearchDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderRtExchgPrmSearchDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.data.OrderGiftDTO;
import com.plgrim.ncp.biz.order.data.OrderParamDTO;
import com.plgrim.ncp.biz.order.data.OrderStockDTO;
import com.plgrim.ncp.biz.order.data.SysExchgRtDTO;
import com.plgrim.ncp.biz.order.repository.OrderSelectRepository;
import com.plgrim.ncp.biz.order.result.GodSvcResult;
import com.plgrim.ncp.biz.order.result.GoodsCouponResult;
import com.plgrim.ncp.biz.order.result.MypageClaimFoResult;
import com.plgrim.ncp.biz.order.result.MypageOrderFoResult;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmResult;
import com.plgrim.ncp.biz.order.result.OrderCompleteResult;
import com.plgrim.ncp.biz.order.result.OrderCouponResult;
import com.plgrim.ncp.biz.order.result.OrderLinkPriceResult;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.commons.data.order.InfOrderSalesDTO;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.promotion.adapter.PromotionAdapter;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponUseSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderSelectService extends AbstractService {

	@Autowired
	OrderSelectRepository orderSelectRepository;
	
	@Autowired
	MbrPreferPayMnRepository mbrPreferPayMnRepository;
	
	@Autowired
	PromotionService promotionService;
	
	@Autowired
	OrdRepository ordRepository;
	
	@Autowired
	PromotionAdapter promotionAdapter;
	
	/**
	 * 회원배송지 목록 조회.
	 * @param mbrDelvSearchDTO MbrDelvSearchDTO
	 * @param pageParam PageParam
	 * @return Page<MbrDlvsp>
	 * @throws Exception
	 */
	public Page<MbrDlvsp> selectMemberDeliveryList(MbrDelvSearchDTO mbrDelvSearchDTO, PageParam pageParam) throws Exception {
		return orderSelectRepository.selectMemberDeliveryList(mbrDelvSearchDTO, pageParam);
	}
	
    /**
     * 회원 배송지 목록 조회 카운트.
     * @param mbrDelvSearchDTO MbrDelvSearchDTO
     * @return long
     * @throws Exception
     */
	public long selectMemberDeliveryListCount(MbrDelvSearchDTO mbrDelvSearchDTO) throws Exception {
		return orderSelectRepository.selectMemberDeliveryListCount(mbrDelvSearchDTO);
	}

	/**
	 * 회원 배송지 조회.
	 * @param mbrDlvsp MbrDlvsp
	 * @return MbrDlvsp
	 * @throws Exception
	 */
	public MbrDlvspExtend selectMemberDelivery(MbrDlvspExtend mbrDlvsp) throws Exception {
		return orderSelectRepository.selectMemberDelivery(mbrDlvsp);
	}

	/**
	 * 최근배송지 목록 조회
	 * @param mbrDelvSearchDTO MbrDelvSearchDTO
	 * @param pageParam PageParam
	 * @return Page<MbrDlvsp>
	 * @throws Exception
	 */
	public Page<MbrDlvsp> selectOrderDeliveryList(MbrDelvSearchDTO mbrDelvSearchDTO, PageParam pageParam) throws Exception {
		return orderSelectRepository.selectOrderDeliveryList(mbrDelvSearchDTO, pageParam);
	}
	
	/**
	 * 최근배송지 목록 조회 카운트.
	 * @param mbrDelvSearchDTO MbrDelvSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectOrderDeliveryListCount(MbrDelvSearchDTO mbrDelvSearchDTO) throws Exception {
		return orderSelectRepository.selectOrderDeliveryListCount(mbrDelvSearchDTO);
	}
	
	/**
	 * 주문사은품 조회
	 * @param prmNo String
	 * @return OrderGiftDTO
	 * @throws Exception
	 */
	public OrderGiftDTO selectOrderGiftList(String prmNo) throws Exception {
		return orderSelectRepository.selectOrderGiftList(prmNo);
	}
	
	/**
	 * 회원쿠폰조회
	 * @param couponSearchDTO CouponSearchDTO
	 * @return List<OrderCouponResult>
	 */
	public List<OrderCouponResult> selectOrderCouponList(CouponSearchDTO couponSearchDTO) throws Exception {
		return orderSelectRepository.selectOrderCouponList(couponSearchDTO);
	}

//	/**
//	 * 회원쿠폰수량조회
//	 * @param couponSearchDTO CouponSearchDTO
//	 * @return Integer
//	 * @throws Exception
//	 */
//	public Integer selectOrderCouponListCount(CouponSearchDTO couponSearchDTO) throws Exception {
//		return orderSelectRepository.selectOrderCouponListCount(couponSearchDTO);
//	}

	/**
	 * 매장정보조회
	 * @param sysShopBrnd SysShopBrndExtend
	 * @return SysShopExtends
	 * @throws Exception
	 */
	public SysShopExtends selectSysShop(SysShopBrndExtend sysShopBrnd) throws Exception {
		return orderSelectRepository.selectSysShop(sysShopBrnd);
	}

	/**
	 * 상품의 유효성체크
	 * @param stock OrderStockDTO
	 * @return String
	 * @throws Exception
	 */
	public String selectGoodsStatus(OrderStockDTO stock) throws Exception {
		return orderSelectRepository.selectGoodsStatus(stock);
	}
	
	/**
	 * 사은품 재고체크
	 * @param godNo
	 * @return OrdGodExtends
	 * @throws Exception
	 */
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public OrdGodExtends selectGiftStock(String godNo) throws Exception {
		return orderSelectRepository.selectGiftStock(godNo);
	}
	
	public int selectFoOrderListCount(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectFoOrderListCount(mypageOrderInfoDTO);
	}
	
	public Page<MypageOrderFoResult> selectFoOrderList(MypageOrderInfoDTO mypageOrderInfoDTO, PageParam pageParam) throws Exception {
		
		return orderSelectRepository.selectFoOrderList(mypageOrderInfoDTO, pageParam);
	}

	public Page<MypageClaimFoResult> selectFoClaimList(MypageOrderInfoDTO dto, PageParam pageParam) throws Exception {
		return orderSelectRepository.selectFoClaimList(dto, pageParam);
	}	
	
	public Page<MypageOrderFoResult> selectFoReceiptList(MypageOrderInfoDTO dto, PageParam pageParam) throws Exception {
		return orderSelectRepository.selectFoReceiptList(dto, pageParam);
	}

	public List<MypageOrderFoResult> selectFoReceiptList(MypageOrderInfoDTO dto) throws Exception {
		return orderSelectRepository.selectFoReceiptList(dto);
	}

	public MypageOrderInfoDTO selectFoOrderInfo(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectFoOrderInfo( mypageOrderInfoDTO);
	}
	public List<MypageOrderRtExchgPrmResult> getOrderGoodsRtExchgPromotionInfoList(MypageOrderRtExchgPrmSearchDTO search) throws Exception {
		return orderSelectRepository.getOrderGoodsRtExchgPromotionInfoList(search);
	}

	public MypageOrderInfoDTO selectPayMethodChange(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectPayMethodChange( mypageOrderInfoDTO);
	}
	public MypageOrderInfoDTO selectDlvYn(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectDlvYn( mypageOrderInfoDTO);
	}
	public MypageOrderInfoDTO selectPayFoMobilPonPayRfd(String ordNo) throws Exception {
		return orderSelectRepository.selectPayFoMobilPonPayRfd(ordNo);
	}
	public MypageOrderInfoDTO selectPayMethodChangeClm(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectPayMethodChangeClm( mypageOrderInfoDTO);
	}
	
	public List<ClmFoExtend> selectClmFoList(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectClmFoList( mypageOrderInfoDTO);
	}	
	//ncp 3차 ********************************************************************************************
	public List<ClmFoExtend> selectClmFoListGlobal(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectClmFoListGlobal( mypageOrderInfoDTO);
	}
	//ncp 3차 ********************************************************************************************

	public List<OrdGodExtend> selectFoGodInfo(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectFoGodInfo( mypageOrderInfoDTO);
	}	

	public LgsDlvsp selectOrderDeliveryLocationPop(LgsDlvspFoExtend lgsDlvspFoExtend) throws Exception {
		return orderSelectRepository.selectOrderDeliveryLocationPop( lgsDlvspFoExtend);
	}	
	//ncp 3차 주문배송지 조회 ********************************************************************************************
	public LgsDlvspFoExtend selectOrderDeliveryLocationPopGlobal(LgsDlvspFoExtend lgsDlvspFoExtend) throws Exception {
		return orderSelectRepository.selectOrderDeliveryLocationPopGlobal( lgsDlvspFoExtend);
	}
	//ncp 3차 주문배송지 조회 ********************************************************************************************




	public List<MypageOrderInfoDTO> selectDlvCstSumAmt(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectDlvCstSumAmt( mypageOrderInfoDTO);
	}	
	public List<MypageOrderInfoDTO> selectDlvCstSumAmtList(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectDlvCstSumAmtList( mypageOrderInfoDTO);
	}	
	public SysShopBrnd selectDlvShopYn(SysShopBrnd sysShopBrnd) throws Exception {
		return orderSelectRepository.selectDlvShopYn( sysShopBrnd);
	}	
	
	public SysShopBrnd selectPkupDlvShopYn(SysShopBrndExtend sysShopBrndExtend) throws Exception {
		return orderSelectRepository.selectPkupDlvShopYn( sysShopBrndExtend);
	}
	
	
	/**
	 * 주문상품 옵션 조회
	 * @param mypageOrderInfoDTO
	 * @return List<MypageOrderFoResult>
	 * @throws Exception
	 */
	public List<MypageOrderFoResult> selectOrdGoodOption(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectOrdGoodOption(mypageOrderInfoDTO);
	}
	
	/**MypageFoDTO dto
	 * 주문세트상품 옵션 조회
	 * @param mypageOrderInfoDTO
	 * @return List<MypageOrderFoResult>
	 * @throws Exception
	 */
	public List<MypageOrderFoResult> selectOrdSetOption(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		return orderSelectRepository.selectOrdSetOption(mypageOrderInfoDTO);
	}
	
	/**
	 * 주문완료
	 * */
	public OrderCompleteResult selectOrderCompleteResult(OrderParamDTO ord) throws Exception {
		
		return orderSelectRepository.selectOrderCompleteResult(ord);
	}
	
	public OrderCompleteResult selectVirtualConfirm(String payNo) throws Exception {
		return orderSelectRepository.selectVirtualConfirm(payNo);
	}
	
	public List<InfOrdGodErpDstbExtends> selectInfOrdGodErpDstbList(String ordNo) throws Exception {
		return orderSelectRepository.selectInfOrdGodErpDstbList(ordNo);
	}
	
	
	public String selectMultiLgsDlvYn(String ordNo) throws Exception {
		return orderSelectRepository.selectMultiLgsDlvYn( ordNo);
	}
	
	public List<OrderLinkPriceResult> selectOrderLinkPriceInfo(String ordNo) throws Exception{
		return orderSelectRepository.selectOrderLinkPriceInfo(ordNo);
    }
	////////// FRONT END /////////////////////////////////////////////	

	public SysExchgRtDTO selectExchangeRt(SystemPK systemPK) {

		SysExchgRtDTO sysExchgRtDTO = new SysExchgRtDTO();
		String crncyCd="KRW";//default
//		if("KOR".equalsIgnoreCase(systemPK.getLang())){
//			crncyCd="KRW";
//		}else{
//			crncyCd="USD";
//		}
		sysExchgRtDTO.setCrncyCd(crncyCd);


		return orderSelectRepository.selectExchangeRt(sysExchgRtDTO);
	}

	public SysExchgRtDTO selectExchangeRt(String crncyCd) {
		SysExchgRtDTO sysExchgRtDTO = new SysExchgRtDTO();
		sysExchgRtDTO.setCrncyCd(crncyCd);
		return orderSelectRepository.selectExchangeRt(sysExchgRtDTO);
	}

	/**
	 * selectExchangeRt(String crncyCd) 이거 오버로딩
	 * 환율적용시작일 키값으로 주문서에서 적용되었던 환율정보를 구해온다.
	 * @param crncyCd
	 * @param exchgRtAplBegDtStr
	 * @return
	 */
	public SysExchgRtDTO selectExchangeRt(String crncyCd,String exchgRtAplBegDtStr) {
		SysExchgRtDTO sysExchgRtDTO = new SysExchgRtDTO();
		sysExchgRtDTO.setCrncyCd(crncyCd);
		sysExchgRtDTO.setExchgRtAplBegDtStr(exchgRtAplBegDtStr);
		return orderSelectRepository.selectExchangeRtByAplBegDtStr(sysExchgRtDTO);
	}

	public SysExchgRtDTO selectExchangeRt(String crncyCd,Date exchgRtAplBegDt) {
		SysExchgRtDTO sysExchgRtDTO = new SysExchgRtDTO();
		sysExchgRtDTO.setCrncyCd(crncyCd);
		sysExchgRtDTO.setExchgRtAplBegDt(exchgRtAplBegDt);
		return orderSelectRepository.selectExchangeRtByAplBegDtStr(sysExchgRtDTO);
	}





	public ComOvseaDlvZoneChrgeDTO selectOvseaDlvFee(ComOvseaDlvZoneChrgeDTO comOvseaDlvZoneChrge) {
		return orderSelectRepository.selectOvseaDlvFee(comOvseaDlvZoneChrge);
	}
	
	public ComOvseaDlvZoneChrgeDTO selectOvseaDlvFeeHist(ComOvseaDlvZoneChrgeDTO comOvseaDlvZoneChrge) {
		return orderSelectRepository.selectOvseaDlvFeeHist(comOvseaDlvZoneChrge);
	}

	
	
	public List<ComOvseaDlvZoneChrgeDTO> selectOvseaDlvZoneChrgeList(ComOvseaDlvZoneChrgeDTO comOvseaDlvZoneChrge) {
		return orderSelectRepository.selectOvseaDlvZoneChrgeList(comOvseaDlvZoneChrge);
	}


	public List<ComOvseaDlvZoneNationExtend> selectNationInfo(ComOvseaDlvZoneNationExtend comOvseaDlvZoneNationExtend) {
		return orderSelectRepository.selectNationInfo(comOvseaDlvZoneNationExtend);
	}
	public String selectDmstcDlvFee(String ordNo) {
		return orderSelectRepository.selectDmstcDlvFee(ordNo);
	}
	public String getInterfaceOrder(String payNo) {
		return orderSelectRepository.getInterfaceOrder(payNo);
	}

	/**
	 * 받은 환율 정보로 적용대상금액을 환율적용해준다.
	 */
	public String applyExchangeRt(SysExchgRtDTO sysExchgRtDTO)  throws Exception{


		//2016-01-28 회의에서 정해짐.
		//반올림할 자리수 소수점 2자리에서 내림 한다. 가 정책임(상품의 최종계산된 금액에 내림한다.)
		double dPoint = 100;
		//환율 외화금액
		double exAmt = Double.parseDouble(sysExchgRtDTO.getExchgRtCrncyAmt().toString());
		//환율 한화금액
		double stAmt=Double.parseDouble(sysExchgRtDTO.getStdrCrncyAmt().toString());

		//적용 대상금액
		double target = Double.parseDouble(sysExchgRtDTO.getOriAmt());

		//환율 적용 후 금액.
		double result = Math.floor((target/stAmt*exAmt)*dPoint)/dPoint;


		return result+"";
	}

	/**
	 * 받은 환율 정보로 적용대상금액을 환율적용해준다.
	 */
	public String applyExchangeRt(SysExchgRtDTO sysExchgRtDTO, boolean truncFlag) throws Exception {

		// 2016-01-28 회의에서 정해짐.
		// 반올림할 자리수 소수점 2자리에서 올림 한다. (상품 할인, 쿠폰할인 등 최종계산된 금액에 내림한다.)
		double dPoint = 100;
		// 환율 외화금액
		double exAmt = Double.parseDouble(sysExchgRtDTO.getExchgRtCrncyAmt().toString());
		// 환율 한화금액
		double stAmt = Double.parseDouble(sysExchgRtDTO.getStdrCrncyAmt().toString());

		// 적용 대상금액
		double target = Double.parseDouble(sysExchgRtDTO.getOriAmt());

		double result = 0;

		if (truncFlag) {
			// 환율 적용 후 금액. 1.005, 1.001, 1.009는 모두 1.00로 처리함.
			result = Math.floor((target / stAmt * exAmt) * dPoint) / dPoint;
		} else {
			// 환율 적용 후 금액. 1.005, 1.001, 1.009 모두 1.01로 처리함.
			 result = Math.ceil((target / stAmt * exAmt) * dPoint) / dPoint;
		}

		return result + "";
	}

	/**
	 * 카페 24 회원 주문 select
	 * @param orgMbrId
	 * @return
	 * @throws Exception
	 */
	public List<Ord> selectCafeMbrOrd(String orgMbrId) throws IOException {
		return orderSelectRepository.selectCafeMbrOrd(orgMbrId);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	public String selectGodWt(String godNo) {
		return orderSelectRepository.selectGodWt(godNo);
	}
	
	public String selectGodWtForCpst(String godNo) {
		return orderSelectRepository.selectGodWtForCpst(godNo);
	}

	public Pay selectPayByPayNo(String result) {
		return orderSelectRepository.selectPayByPayNo(result);
	}
	
	public int selectAplPrmGodDlvFeeCnt(String ordNo) {
		return orderSelectRepository.selectAplPrmGodDlvFeeCnt(ordNo);
	}

	public MbrPreferPayMn selectMbrPreferPayMn(MbrPreferPayMn mbrPreferPayMn) throws Exception{
		return mbrPreferPayMnRepository.selectMbrPreferPayMn(mbrPreferPayMn);
	}

	public String getMaxDlvChrgeHistTurn(ComOvseaDlvZoneChrgeDTO param) {		
		return orderSelectRepository.getMaxDlvChrgeHistTurn(param);
	}

	public void checkAvailableOnOffCoupon(String erpCstmrNo, List<OrderCouponResult> memberCouponList, AdapterHeader adapterHeader) {
		//1.대상추출
		List<String> couponList = new ArrayList<String>();
		List<PrmCpnCrtfcCd> prmCpnCrtfcCdList = new ArrayList<PrmCpnCrtfcCd>();
		for (OrderCouponResult memberCoupon : memberCouponList) {
			for (GoodsCouponResult mCoupon : memberCoupon.getGoodsCouponResultList()) {
				if("ONOFLNE_CPN".equals(mCoupon.getPrmCpn().getCpnKndCd())){
					mCoupon.setAvailble(false);
					mCoupon.setOnoffCheckedErp(false);
					mCoupon.setOnoffCheckedAvailble(false);
					
					String cpnCrtfcCd = mCoupon.getCpnCrtfcCd();
					if(cpnCrtfcCd != null 
						&& !cpnCrtfcCd.isEmpty() 
						&& !couponList.contains(cpnCrtfcCd)){
						
						PrmCpnCrtfcCd prmCpnCrtfcCd = new PrmCpnCrtfcCd();
						prmCpnCrtfcCd.setPrmNo(mCoupon.getPrm().getPrmNo());
						prmCpnCrtfcCd.setCpnCrtfcCd(cpnCrtfcCd);
						
						prmCpnCrtfcCdList.add(prmCpnCrtfcCd);
						couponList.add(cpnCrtfcCd);
					}
				}
			}
		}
		
		//2.API 호출
		if(prmCpnCrtfcCdList.size()>0){
			try{
				for(PrmCpnCrtfcCd item : prmCpnCrtfcCdList){
					
					OnOffCouponUseSDO result = promotionAdapter.validOnOffCoupon(erpCstmrNo, item.getCpnCrtfcCd(), adapterHeader);
					
					if("N".equals(result.getUseYn())){
						for (OrderCouponResult memberCoupon : memberCouponList) {
							for (GoodsCouponResult mCoupon : memberCoupon.getGoodsCouponResultList()) {
								if(item.getPrmNo().equals(mCoupon.getPrm().getPrmNo())){
									if("ONOFLNE_CPN".equals(mCoupon.getPrmCpn().getCpnKndCd())){
										if(mCoupon.getCpnCrtfcCd() != null){
											mCoupon.setOnoffCheckedErp(true);
											mCoupon.setAvailble(true);
											mCoupon.setOnoffCheckedAvailble(true);
										}
									}
								}
							}
						}
					}
				}

			}catch(Exception e){
				log.error("온오프라인 쿠폰 체크 오류 ::: {}", e);
			}
		}

	}

	//#34425 로 추가 2017-01-16
	//글로벌은 해외판매여부와 해당언어별 몰 전시여부 체크하여 하나라도 N인 경우 판매불가 처리
	public String selectGoodsGlobalChkStatus(OrderStockDTO stock) throws Exception {
		return orderSelectRepository.selectGoodsGlobalChkStatus(stock);		
	}

    public List<GodSvcResult> selectGoodsWrapServiceList(String godNo) {
		return orderSelectRepository.selectGoodWrapServiceList(godNo);
    }



	public LgsDlvsp selectLgsDlvspOfQdlvOrder(String ordNo) {
		return orderSelectRepository.selectLgsDlvspOfQdlvOrder(ordNo);
	}

	public int selectQdlvComArea(String postNo) {
		return orderSelectRepository.selectQdlvComArea(postNo);
	}

	public int selectComQdlvOldArea(String splitPostNo) {
		return orderSelectRepository.selectComQdlvOldArea(splitPostNo);
	}
	
	//주문번호로 주문 엔티티를 반환한다.
	public Ord selectOrdEntity(String ordNo) {
		Ord ord = new Ord();
		ord.setOrdNo(ordNo);
		return ordRepository.selectOrd(ord);
	}
	
	public List<OrdGodExtend> selectCoOrdGodList(OrderBoDTO orderDTO) {
		return orderSelectRepository.selectCoOrdGodList(orderDTO);
	}
	
	/**
	 * 주문의 마지막 클레임 정보 조회
	 * 
	 * @param pay
	 * @return
	 */
	public PayExtend selectLastClmOfOrd(Pay pay){
		return orderSelectRepository.selectLastClmOfOrd(pay);
	}
	
	/**
	 * 주문 - 부분취소 접수
	 * 클레임접수기본정보조회[물류배송지별]
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodPartCancelClmWithGft(OrderBoDTO orderDTO) throws Exception {
		return orderSelectRepository.selectOrdGodPartCancelClmWithGft(orderDTO);
	}
	
	public List<LgsDlvsp> selectDeliveryMemoList(Ord ord){
		return orderSelectRepository.selectDeliveryMemoList(ord);
	}
	
	public List<InfOrderSalesDTO> selectOrderSalesList(OrderParamDTO dto){
		return orderSelectRepository.selectOrderSalesList(dto);
	}
	
	public List<InfOrderSalesDTO> selectMileageTempDataList(OrderParamDTO dto){
		return orderSelectRepository.selectMileageTempDataList(dto);
	}
	
	
	public String selectOrdMallId(String ordNo) {
		return orderSelectRepository.selectOrdMallId(ordNo);
	}
}
