package com.plgrim.ncp.biz.order.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtends;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPchShop;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPreferPayMn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpPntTrnsmis;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpTrnsmis;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrm;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodSvcDetailCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdNmbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayEscr;
import com.plgrim.ncp.base.entities.datasource1.pay.PayPgIntrlckLog;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.WebPointEnum;
import com.plgrim.ncp.base.repository.inf.InfOrdGodErpDstbRepository;
import com.plgrim.ncp.base.repository.lgs.LgsDlvHistRepository;
import com.plgrim.ncp.base.repository.mbr.MbrIssuCpnRepository;
import com.plgrim.ncp.base.repository.mbr.MbrPreferPayMnRepository;
import com.plgrim.ncp.base.repository.mbr.MbrRepository;
import com.plgrim.ncp.base.repository.ord.OrdErpPntTrnsmisRepository;
import com.plgrim.ncp.base.repository.ord.OrdNmbrStplatAgrRepository;
import com.plgrim.ncp.base.repository.ord.OrdRepository;
import com.plgrim.ncp.base.repository.pay.PayRepository;
import com.plgrim.ncp.biz.claim.data.RefundPayDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberBenefitApiResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitResult;
import com.plgrim.ncp.biz.member.service.MemberBenefitCommandService;
import com.plgrim.ncp.biz.member.service.MemberBenefitSelectService;
import com.plgrim.ncp.biz.order.data.LgsDlvspDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.data.OrdGodErpTrnsmisDTO;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.biz.order.data.OrderStockDTO;
import com.plgrim.ncp.biz.order.data.SmsRecptnSectDTO;
import com.plgrim.ncp.biz.order.repository.OrderCommandRepository;
import com.plgrim.ncp.biz.order.repository.OrderSelectRepository;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.exception.OrderCompleteFailException;
import com.plgrim.ncp.commons.data.order.NaverConfirmReturnDTO;
import com.plgrim.ncp.commons.data.order.sdo.ItPrItemSDO;
import com.plgrim.ncp.commons.service.NaverPayService;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.interfaces.mpush.adapter.MPushAdapter;
import com.plgrim.ncp.interfaces.mpush.data.MPushAlimTalkSDO;
import com.plgrim.ncp.interfaces.order.adapter.OrderAdapter;
import com.plgrim.ncp.interfaces.order.data.OrderUseTempMileageSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.plgrim.ncp.interfaces.util.InterfaceConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class OrderCommandService extends AbstractService {

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	@Autowired
	MbrPreferPayMnRepository mbrPreferPayMnRepository;
	
	@Autowired
	MbrIssuCpnRepository mbrIssuCpnRepository;

	// 물류배송
	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;

	// 물류배송 이력
	@Autowired
	LgsDlvHistRepository lgsDlvHistRepository;
	
	@Autowired
	MemberBenefitCommandService memberBenefitCommandService;
	
	@Autowired
	MemberBenefitSelectService memberBenefitSelectService;

	@Autowired
	OrderAdapter orderAdapter;
	
	@Autowired
	MemberBenefitFOComponent memberBenefitFOComponent;
	
	@Autowired
	OrdRepository ordRepository;
	
	@Autowired
	MbrRepository mbrRepository;
	
	@Autowired
	MPushAdapter mPushAdapter;
	
	@Autowired
	PayRepository payRepository;
	
	@Autowired
	NaverPayService naverPayService;
	
	public String selectPayNumber() throws Exception {
		return this.selectDBNumber("SQ_PAY", "ST");
	}

	public String selectDBNumber(String seqNm, String prefix) throws Exception {
		return getIdGenService().generateDBNumber(sqlSession1, seqNm, prefix, DatabaseType.ORACLE);
	}

	public Integer selectOrderSequence(String ordNo) throws Exception {
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("ord_no", ordNo);
		return getIdGenService().generateDBOrder(sqlSession1, "ord_god", "ord_god_turn", conditions, DatabaseType.ORACLE);
	}

	public Integer selectOrderSequence(String ordNo, int ordGodTurn) throws Exception {
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("ord_no", ordNo);
		conditions.put("ord_god_turn", ordGodTurn);
		return getIdGenService().generateDBOrder(sqlSession1, "inf_ord_god_erp_dstb", "qty_turn", conditions, DatabaseType.ORACLE);
	}

	public Integer selectDeliverySequence(String ordNo) throws Exception {
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("ord_no", ordNo);
		return getIdGenService().generateDBOrder(sqlSession1, "lgs_dlvsp", "dlv_pcupsp_turn", conditions, DatabaseType.ORACLE);
	}

	public Integer selectPromotionSequence(String ordNo) throws Exception {
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("ord_no", ordNo);
		return getIdGenService().generateDBOrder(sqlSession1, "ord_god_apl_prm", "apl_prm_turn", conditions, DatabaseType.ORACLE);
	}
	
	public Integer selectDeliverySequence(String ordNo, int dlvPcupspTurn) throws Exception {
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("ord_no", ordNo);
		conditions.put("dlv_pcupsp_turn", dlvPcupspTurn);
		return getIdGenService().generateDBOrder(sqlSession1, "lgs_dlv", "dlv_turn", conditions, DatabaseType.ORACLE);
	}

	@Autowired
	OrderSelectRepository orderSelectRepository;

	@Autowired
	OrderCommandRepository orderCommandRepository;

	@Autowired
	OrdErpPntTrnsmisRepository ordErpPntTrnsmisRepository;

	@Autowired
	InfOrdGodErpDstbRepository infOrdGodErpDstbRepository;

	@Autowired
	OrdNmbrStplatAgrRepository ordNmbrStplatAgrRepository;
	
	@Autowired
	OrderEntitySetService orderEntitySetService;

	/**
	 * 주문마스터 입력
	 * @param orderDTO OrderDTO
	 * @param resultMap Map<String, String>
	 * @throws Exception
	 */
	public Ord insertOrder(OrderDTO orderDTO, Map<String, String> resultMap,String lpInfo) throws Exception {
		
		Ord ord = orderEntitySetService.makeOrd(orderDTO, resultMap);
		ord.setLinkprcSesionId(lpInfo);
		orderCommandRepository.insertOrd(ord);

		return ord;
	}
	
	/**
	 * 물류배송지 입력
	 * @param orderDTO OrderDTO
	 * @param lgsDlvspDTO LgsDlvspDTO
	 * @return LgsDlvsp
	 * @throws Exception
	 */
	public LgsDlvsp insertDelivery(OrderDTO orderDTO, LgsDlvspDTO lgsDlvspDTO) throws Exception {
		LgsDlvsp lgsDlvsp = orderEntitySetService.makeLgsDlvsp(orderDTO, lgsDlvspDTO);
		orderCommandRepository.insertLgsDlvsp(lgsDlvsp);
		
		return lgsDlvsp;
	}
	
	/**
	 * 물류배송지 상품
	 * @param ord Ord
	 * @param ordGod OrdGod
	 * @return OrdGod
	 * @throws Exception
	 */
	public void insertOrdGod(OrdGod ordGod) throws Exception {
		orderCommandRepository.insertOrdGod(ordGod);
	}
	
	/**
	 *
	* <pre>
	* 픽업 상품인 경우와 픽업상품이 아닌 경우를 대비하여 처리하도록 처리
	* </pre>
	* @param lgsDlv
	* @throws Exception
	* @since 2015. 11. 3.
	 */
	public void insertLgsDlv(LgsDlv lgsDlv) throws Exception {
		if(lgsDlv.getDlvMnCd() != null && "SHOP_PKUP".equals(lgsDlv.getDlvMnCd())){
			// 매장 픽업일 경우
			orderCommandRepository.insertLgsDlvWithShopPkup(lgsDlv);
		}else{
			orderCommandRepository.insertLgsDlv(lgsDlv);
		}
	}

	/**
	 * 인터페이스 ERP 주문상품
	 * @param infOrdGodErpDstb InfOrdGodErpDstb
	 * @throws Exception
	 */
	public void insertInfOrdGodErpDstb(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		orderCommandRepository.insertInfOrdGodErpDstb(infOrdGodErpDstb);
	}
	
	public void insertOrdCpst(OrdCpstGodCnnc ordCpstGodCnnc) throws Exception {
		orderCommandRepository.insertOrdCpstGodCnnc(ordCpstGodCnnc);
	}
	
	// 3. 주문상품 출고지시
	public void insertShipping(Ord ord, String dlvMnCd) throws Exception {
		LgsDlivyDrctGod lgsDlivyDrctGod = new LgsDlivyDrctGod();
		
		lgsDlivyDrctGod.setOrdNo(ord.getOrdNo());
		lgsDlivyDrctGod.setLgsItmYn("Y");
		//lgsDlivyDrctGod.setDlvShopId(GoodsEnum.ERP_ONLINE_TEMP_WERKS);	// 임시매장코드
		lgsDlivyDrctGod.setDlivyDrctTgtYn("N");
		lgsDlivyDrctGod.setDlivyDrctYn("N");

		// 출고지시 유형 코드
		if("SHOP_PKUP".equals(dlvMnCd)){
			// 매장픽업
			lgsDlivyDrctGod.setDlivyDrctTpCd("SHOP_PKUP");
		}else{
			// 일반주문
			lgsDlivyDrctGod.setDlivyDrctTpCd("ORD");
			lgsDlivyDrctGod.setDlvStatCd("DLV_WAIT");
		}

		
		lgsDlivyDrctGod.setRegtrId(ord.getRegtrId());
		lgsDlivyDrctGod.setRegDt(ord.getRegDt());
		lgsDlivyDrctGod.setUdterId(ord.getRegtrId());
		lgsDlivyDrctGod.setUdtDt(ord.getRegDt());
		
//		lgsDlivyDrctGod.setGftYn("N");	from god
		lgsDlivyDrctGod.setShtgDcsnYn("N");
		lgsDlivyDrctGod.setResveRcptfrCreatYn("N");
		lgsDlivyDrctGod.setResveRcptfrDcsnYn("N");
		lgsDlivyDrctGod.setResveRcptfrDlivyYn("N");
		
		lgsDlivyDrctGod.setErpResveRcptfrCnclYn("N");
		lgsDlivyDrctGod.setErpResveRcptfrCreatYn("N");
		lgsDlivyDrctGod.setErpResveRcptfrRecreatYn("N");
		
		lgsDlivyDrctGod.setInvAplYn("N");
		lgsDlivyDrctGod.setCntrDlivyCnfirmYn("N");

		orderCommandRepository.insertLgsDlivyDrctGod(lgsDlivyDrctGod);
	}
	
	// 4. 결제
	public void insertPayment(OrderDTO orderDTO, Ord ord) throws Exception {
		Pay pay = orderDTO.getPay();
		pay.setOrdNo(orderDTO.getOrd().getOrdNo());
		pay.setPayTpCd("ORD");
		pay.setPayCrncyCd(ord.getCrncyCd());
		pay.setMpayMnYn("Y");
		pay.setPayMnCd(orderDTO.getPay().getPayMnCd());
		pay.setStdrCrncyPayAmt(ord.getStdrCrncySumAmt());
		pay.setPayCrncyPayAmt(ord.getStdrCrncySumAmt());
		pay.setRegtrId(orderDTO.getRegtrId());

		if (ord.getStdrCrncySumAmt().compareTo(BigDecimal.ZERO) > 0) {
			orderCommandRepository.insertMypagePay(pay);
			if ("Y".equals(orderDTO.getPay().getEscrYn())) {
				PayEscr payEscr = new PayEscr();
				payEscr.setPayNo(pay.getPayNo());
				payEscr.setEscrStatCd("ESCR_REQST");
				payEscr.setRegtrId(orderDTO.getRegtrId());
				payEscr.setRegDt(ord.getRegDt());
				payEscr.setUdterId(orderDTO.getRegtrId());
				payEscr.setUdtDt(ord.getRegDt());
				orderCommandRepository.insertPayEscr(payEscr);
			}
		}
	}

	public void insertPayment(Pay pay) throws Exception {
		orderCommandRepository.insertPay(pay);
		if ("Y".equals(pay.getEscrYn())) {
			PayEscr payEscr = new PayEscr();
			payEscr.setPayNo(pay.getPayNo());
			payEscr.setEscrStatCd("ESCR_REQST");
			payEscr.setRegtrId(pay.getRegtrId());
			payEscr.setEscrDlvRegDt(pay.getRegDt());
			payEscr.setRegDt(pay.getRegDt());
			payEscr.setUdterId(pay.getRegtrId());
			payEscr.setUdtDt(pay.getRegDt());
			orderCommandRepository.insertPayEscr(payEscr);
		}
	}

	public void insertPayLogTransaction(PayPgIntrlckLog payLog) throws Exception {
		orderCommandRepository.insertPayLog(payLog);
	}

	// 5. 프로모션
	public void insertOrdGodAplPrm(OrdGodAplPrm ordGodAplPrm) throws Exception {
		ordGodAplPrm.setAplPrmTurn(this.selectPromotionSequence(ordGodAplPrm.getOrdNo()));
		orderCommandRepository.insertOrdGodAplPrm(ordGodAplPrm);
	}
	
	public void updateMbrIssuCoupon(OrdGodAplPrm ordGodAplPrm) {
		orderCommandRepository.updateMbrIssuCoupon(ordGodAplPrm);
	}
	
	// 7-1. 회원관련
	public void insertMemberOrder(OrdNmbrStplatAgr ordNmbrStplatAgr) throws Exception {
		orderCommandRepository.insertOrdNmbrStplatAgr(ordNmbrStplatAgr);
	}
	
	// 7-3. 회원구매매장(픽업 주문 일 때)
	public void mergeMbrPchShop(MbrPchShop mbrPchShop) throws Exception {
		orderCommandRepository.mergeMbrPchShop(mbrPchShop);
	}
	
	/**
	 * 스플릿 프로젝트로 추가 2017-03-17
	 * @param ord
	 * @param mbrId
	 * @return
	 * @throws Exception
	 */
//	public PurchaseHistorySDO getBpSalesSaveSelectForSplitHub(Ord ord, String mbrId, String orderType) throws Exception {
//
//		String ordNo = ord.getOrdNo();
//
//		BpSalesSaveSDO bpSalesSaveSDO = null;
//
//		PurchaseHistorySDO purchaseHistorySDO = null;
//
//		IsPrHeaderSDO isPrHeaderSDO = orderSelectRepository.selectIsPrHeader(ordNo);
//		if (isPrHeaderSDO != null && !StringService.isEmpty(isPrHeaderSDO.getFspVbenr())) {
//
//
//			/**
//			 * 1. item
//			 */
//			List<ItPrItemSDO> itPrItemSDOList = orderSelectRepository.selectItPrItemSDOList(ordNo);
//
//			//isPrHeaderSDO.setCbBalAmt(bMemberPnt.toString());			// 최종포인트
//			isPrHeaderSDO.setCbBalAmt("0");			// 최종포인트 스플릿에서 빠지기로 함 2017-03-17
//			isPrHeaderSDO.setWerks(GoodsEnum.ERP_ONLINE_WERKS);
//
//			/**
//			 * 2. pay method
//			 */
//			List<ItPrPaymentSDO> itPrPaymentSDOList = orderSelectRepository.selectItPrPaymentSDOList(ordNo);
//
//			/**
//			 * 3. gift
//			 */
//			//List<ItGiftSDO> itGiftSDOList = new ArrayList<ItGiftSDO>();
//			//ItGiftSDO igs = new ItGiftSDO();
//			//itGiftSDOList.add(igs);
//
//			/**
//			 * 4. setting
//			 */
//			bpSalesSaveSDO = new BpSalesSaveSDO();
//			bpSalesSaveSDO.setIsPrHeaderSDO(isPrHeaderSDO);
//			bpSalesSaveSDO.setItPrItemSDOList(itPrItemSDOList);
//			bpSalesSaveSDO.setItPrPaymentSDOList(itPrPaymentSDOList);
//			//bpSalesSaveSDO.setItGiftSDOList(itGiftSDOList);
//
//
//			purchaseHistorySDO = convertBpSalesSave(bpSalesSaveSDO);
//			purchaseHistorySDO.setOrdClmSectCd(orderType);
//
//		}
//
//		return purchaseHistorySDO;
//	}



    public Ord webPointInsert(Ord ord) throws Exception {

        String ordNo = ord.getOrdNo();

        List<ItPrItemSDO> itPrItemSDOList = orderSelectRepository.selectItPrItemSDOList(ordNo);

        for (ItPrItemSDO item : itPrItemSDOList) {
            // 자사 P포인트 적립/차감
            this.webPointInsert(ord , item);
        }

        List<ItPrItemSDO> itPrItemSDOListByPartmal = orderSelectRepository.selectItPrItemSDOListForPartmal(ordNo);

        for (ItPrItemSDO item : itPrItemSDOListByPartmal) {
            // 입점사 P포인트 적립/차감
            this.webPointInsert(ord , item );
        }
        return ord;
    }

//	/**
//	 * 예약영수증 호출
//	 * @param infErpList
//	 * @param ord
//	 * @return
//	 * @throws Exception
//	 */
//	public SalesSDO modifyPreSales(InfOrdGodErpDstbExtends inf, AdapterHeader adapterHeader) throws Exception {
//
//		BigDecimal bAllDcAmt =	inf.getWebDcUntPrc().add(inf.getEmpDcUntPrc())
//								//.add(inf.getGodDcUntPrc()).add(inf.getSignlDcUntPrc()).add(inf.getB2eSpslDcUntPrc())
//								.add(inf.getBundleDcUntPrc()).add(inf.getCrsDcUntPrc())
//								.add(inf.getGodCpnDcUntPrc()).add(inf.getBskCpnDcUntPrc());
//
//		SalesSDO sdo = new SalesSDO();
//		sdo.setFpiaOr(inf.getOrdNo()); // 주문번호
//		sdo.setSeqNo(String.valueOf(inf.getQtyTurn()));	// 품목번호, 상품수량순번
//		sdo.setMatnr(inf.getSkuNo());	// 상품번호
//		sdo.setReswk(GoodsEnum.ERP_ONLINE_TEMP_WERKS);	// 재고운송오더의 경우 공급(출고)플랜트, 출고지
//		sdo.setReslo(GoodsEnum.ERP_CENTER_SHOP_ID_LOACTION_FC01);	// 재고 운송 오더에 대한 출고 저장 위치, 출고지-창고위치
//		sdo.setWerks(GoodsEnum.ERP_ONLINE_WERKS);	// 플랜트 매장코드, 브랜드별 판매매장코드
//		sdo.setLgort(GoodsEnum.ERP_CENTER_SHOP_ID_LOACTION_FC01);	// 저장위치, 'FC01'
//		sdo.setMenge("1");	// 수량
//		sdo.setMeins("PC");	//기본단위
//		sdo.setDmbtr(inf.getRtlPrc().subtract(bAllDcAmt).toString());// 금액(현지 통화) - 정소가 - 모든할인(일모,포인트제외)
//		// sdo.setWaers(String waers);// SDO에서 통화키, 일반고정값 "KRW" 세팅
//		sdo.setEindt(inf.getEindt());// 품목납품일
//		sdo.setVbetr("0");// 할인율
//		sdo.setCbAmt(inf.getUnityPntUseUntPrc().add(inf.getImdtlDcUntPrc()).toString());// 적립금사용액
//        sdo.setNamt(inf.getWebpntUseUntPrc());
//		sdo.setZlsch(inf.getZlsch());
//
//		if (!StringService.isEmpty(inf.getPickupShopId())) {
//			sdo.setPwerks(inf.getPickupShopId());
//			sdo.setPlgort(GoodsEnum.ERP_CENTER_SHOP_ID_LOACTION_FC01);
//		}
//
//		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
//		SalesSDO result = salesAdapter.modifyPreSales(sdo, adapterHeader);
//		if ("Y".equals(result.getResult())) {
//			inf.setErpResveRcptfrNo(result.getErpResveRcptfrNo());
//			this.updatePreSales(inf);
//		}
//
//		log.debug(">>>>>>>>>>>>>>> result : " + result);
//		return result;
//	}

	public void updatePreSales(InfOrdGodErpDstbExtends inf) throws Exception {
		orderCommandRepository.modifyPreSalesYnInf(inf);
		orderCommandRepository.modifyPreSalesYnLgs(inf);
	}
	
	/**
	 * 주문 erp 전송 이력
	 */
	public void insertOrdErpTrnsmis(OrdErpTrnsmis ost) throws Exception {
		orderCommandRepository.insertOrdErpTrnsmis(ost);
	}
	
	/**
	 * 주문 ERP 전송 수량단위 등록.
	 *
	 * @param ord String
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdErpTrnsmisHist(String ordNo) throws Exception {
		orderCommandRepository.insertOrdErpTrnsmisHist(ordNo);
	}
	
	// 6. ERP 관련
	public void insertERP(OrderDTO orderDTO) throws Exception {
		OrdErpPntTrnsmis ordErpPntTrnsmis = new OrdErpPntTrnsmis();
		orderCommandRepository.insertOrdErpPntTrnsmis(ordErpPntTrnsmis);
	}

	public void updateOrder(Ord ord) throws Exception {
//		orderCommandRepository.updateOrdGod(ord);
		orderCommandRepository.updateOrd(ord);
	}
	
	public void updateStock(OrderStockDTO stock, boolean isGift) throws Exception {
		if (!isGift) {
			/**
			 * 2016.07.26 의미없는 DB lock 방지를 위해 예약상품, 온라인한정상품의 경우만 update처리 하도록 한다.
			 */
			if ("Y".equals(stock.getReserveYn()) || "Y".equals(stock.getOnlineLimitYn())) {
			orderCommandRepository.updateStockGeneral(stock);
			}
			//orderCommandRepository.updateStockStore(stock);	// DB deadlock 으로 주석처리
		} else {
			orderCommandRepository.updateStockGift(stock);
		}
	}
	
	/**
	 * 입점사 주문상품재고갱신
	 *
	 * @param stock
	 * @param isGift
	 * @throws Exception
	 */
	public void updateStockPartmall(OrderStockDTO stock, boolean isGift) throws Exception {
		orderCommandRepository.updateStockPartmall(stock);
	}

	/**
	 * 물류관련 테이블 이력입력
	 * @param ordNo
	 * @throws Exception
	 */
	public void insertHistoryLgs(String ordNo) throws Exception {
		orderCommandRepository.insertLgsDlvspHist(ordNo);
		orderCommandRepository.insertLgsDlvHist(ordNo);
		orderCommandRepository.insertLgsDLivyDrctGodHist(ordNo);
	}

	// 대량주문,전화주문 ORD 업데이트(결제처리)
	public Ord updatePayMethodChangeOrd(OrderDTO orderDTO, Map<String, String> resultMap) throws Exception {
		Ord ord = orderEntitySetService.makeOrd(orderDTO, resultMap);
		if("pkupOrderChange".equals(orderDTO.getType())){
			ord.setOrdTpCd("GNRL_ORD");
			ord.setDlvCstSumAmt(orderDTO.getOrd().getDlvCstSumAmt());
		}
		
		if(StringService.isNotEmpty(orderDTO.getOrd().getAutoRfdSectCd())) {
			// 클라이언트에서 받아온 변경본을 적용한다.
			ord.setAutoRfdSectCd(orderDTO.getOrd().getAutoRfdSectCd());
		}
		
		orderCommandRepository.updatePayMethodChangeOrd(ord);
		
		return ord;
	}
	// 픽업주문 일반주문 ORD 업데이트(결제처리)
	public void updatePkupOrderChangeOrd(Ord ord) throws Exception {
		orderCommandRepository.updatePayMethodChangeOrd(ord);
	}
	// 클레임 배송비 결제처리
	public void updaeClmDlvAmtClmStatCd(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		orderCommandRepository.updaeClmDlvAmtClmStatCd(mypageOrderInfoDTO);
	}	
	
	/**
	 * 배송비 결제 후 결제정보 업데이트
	 * 
	 * @param orderDTO
	 * @param resultMap
	 * @param ord
	 * @throws Exception
	 */
	public void updatePayMethodChangePay(OrderDTO orderDTO) throws Exception {

		Pay pay = orderDTO.getPay();
		pay.setOrdNo(orderDTO.getOrd().getOrdNo());
		
		orderCommandRepository.updatePayMethodChangePay(pay);
		
		if ("Y".equals(orderDTO.getPay().getEscrYn())) {
			PayEscr payEscr = new PayEscr();
			payEscr.setPayNo(pay.getPayNo());
			payEscr.setEscrStatCd("ESCR_REQST");
			payEscr.setRegtrId(orderDTO.getRegtrId());
			payEscr.setRegDt(orderDTO.getRegDt());
			payEscr.setUdterId(orderDTO.getRegtrId());
			payEscr.setUdtDt(orderDTO.getRegDt());
			orderCommandRepository.insertPayEscr(payEscr);
		}

	}
	
	// 마이페이지용 ord 생성(ord는 업데이트)
	public Ord updateRerunPayMethodChangeOrd(OrderDTO orderDTO, Map<String, String> resultMap) throws Exception {
		Ord ord = orderEntitySetService.makeOrd(orderDTO, resultMap);
		orderCommandRepository.insertOrd(ord);

		return ord;
	}	
	// 프론트 변경시 취소 정보 insert
	public void insertCancelPay(RefundPayDTO refundPayDTO) throws Exception {
		orderCommandRepository.insertCancelPay(refundPayDTO);
	}	
	// 프론트 변경시 취소 정보 insert
	public void updateOrdGodDlvPcupspTurn(OrdGod ordGod) throws Exception {
		orderCommandRepository.updateOrdGodDlvPcupspTurn(ordGod);
	}	
	// 프론트 가상계좌 결제수단 변경시 기존에 있던 주결제 취소누적금액 추가
	public void updateCancelPay(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		orderCommandRepository.updateCancelPay(mypageOrderInfoDTO);
	}	
	//   픽업주문 일반 배송 전환시 물류배송 업데이트
	public void insertLgsDlvDlvPcupspTurn(LgsDlvFoExtend lgsDlvFoExtend) throws Exception {
		orderCommandRepository.insertLgsDlvDlvPcupspTurn(lgsDlvFoExtend);
	}	

	// PG전용 무통장(가상계좌) 입금 결과 승인 처리
	public void updateDealTpCd(Map<String, String> param) throws Exception {
		orderCommandRepository.updateDealTpCd(param);
	}
	public void updateOrdStatCd(Map<String, String> param) throws Exception {
		orderCommandRepository.updateOrdStatCd(param);
	}
    
    public void updateReceipt(Ord ord) throws Exception {
		orderCommandRepository.updateReceipt(ord);
	}

	public void insertCancelPayForApi(MypageOrderInfoDTO mypageOrderInfoDTO) {
		orderCommandRepository.insertCancelPayForApi(mypageOrderInfoDTO);
    }
    


	/**
	 *
	* <pre>
	*입점사 쿠폰 분담율에 따른 업체 할인 금액 업데이트
	* </pre>
	* @param ordNo
	* @throws Exception
	* @since 2015. 11. 12.
	 */
	public void updateInfOrdGodErpDstbComputeComBndUntPrc(String ordNo) throws Exception {
		Map<String, String> conditions = Maps.newHashMap();
		conditions.put("ordNo", ordNo);
		orderCommandRepository.updateInfOrdGodErpDstbComputeComBndUntPrc(conditions);
	}
	
	/**
	 * 물류배송 해외배송 국내 배송정보 insert
	 * @param lgsDlv
	 * @throws Exception
	 */
	public void insertLgsDlvOvseaDmst(LgsDlv lgsDlv) throws Exception {
		orderCommandRepository.insertLgsDlvOvseaDmst(lgsDlv);
	}


	/**
	 * 물류배송 해외배송 해외 배송정보 insert
	 * @param lgsDlv
	 */
	public void insertLgsDlvOvsea(LgsDlv lgsDlv) {
		orderCommandRepository.insertLgsDlvOvsea(lgsDlv);
	}




	// 3. 주문상품 출고지시
	public void insertLgsDlivyDrctGodOvsea(Ord ord,String ovseaDlvTurn) throws Exception {
		LgsDlivyDrctGod lgsDlivyDrctGod = new LgsDlivyDrctGod();

		lgsDlivyDrctGod.setOrdNo(ord.getOrdNo());
		lgsDlivyDrctGod.setLgsItmYn("Y");
		//lgsDlivyDrctGod.setDlvShopId(GoodsEnum.ERP_ONLINE_TEMP_WERKS);	// 임시매장코드
		lgsDlivyDrctGod.setDlivyDrctTgtYn("N");
		lgsDlivyDrctGod.setDlivyDrctYn("N");
		lgsDlivyDrctGod.setDlivyDrctTpCd("ORD");
		lgsDlivyDrctGod.setDlvStatCd("DLV_WAIT");
		lgsDlivyDrctGod.setRegtrId(ord.getRegtrId());
		lgsDlivyDrctGod.setRegDt(ord.getRegDt());
		lgsDlivyDrctGod.setUdterId(ord.getRegtrId());
		lgsDlivyDrctGod.setUdtDt(ord.getRegDt());

//		lgsDlivyDrctGod.setGftYn("N");	from god
		lgsDlivyDrctGod.setShtgDcsnYn("N");
		lgsDlivyDrctGod.setResveRcptfrCreatYn("N");
		lgsDlivyDrctGod.setResveRcptfrDcsnYn("N");
		lgsDlivyDrctGod.setResveRcptfrDlivyYn("N");

		lgsDlivyDrctGod.setErpResveRcptfrCnclYn("N");
		lgsDlivyDrctGod.setErpResveRcptfrCreatYn("N");
		lgsDlivyDrctGod.setErpResveRcptfrRecreatYn("N");

		lgsDlivyDrctGod.setInvAplYn("N");
		lgsDlivyDrctGod.setCntrDlivyCnfirmYn("N");

		//lgsDlivyDrctGod.setOvseaDlvTurn(ovseaDlvTurn);

		orderCommandRepository.insertLgsDlivyDrctGodOvsea(lgsDlivyDrctGod);
	}

    public void webPointInsert(Ord ord , ItPrItemSDO item)  throws Exception {

        BigDecimal bUnitSaveWebpnt = new BigDecimal(item.getWebpntAccmlUntPrc()); // P포인트 적립금액
        BigDecimal bUnitUseWebpnt = new BigDecimal(item.getWebpntUseUntPrc()); // P포인트 사용금액


        MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
		mbrWebpntHist.setMbrNo(ord.getMbrNo());
		mbrWebpntHist.setMallId(ord.getMallId());
		MbrWebpntHistExtend mbrWebpntHistExtend = memberBenefitSelectService.selectWebPointInfo(mbrWebpntHist);
        
		//BigDecimal totalPnt = ((MbrWebpntHistExtend) memberBenefitSelectService.selectWebPointInfo(ord.getMbrNo())).getUsefulAmt();
		BigDecimal totalPnt = mbrWebpntHistExtend.getUsefulAmt();
		BigDecimal usePnt = new BigDecimal("0");

        // 일반,온라인회원의 경우 P포인트 차감
        if(ord.getMbrTpCd().equals(MemberEnum.MemberTpCd.UNITY_MBR.toString()) || ord.getMbrTpCd().equals(MemberEnum.MemberTpCd.ONLINE_MBR.toString())) {


            MbrWebpntHist param = new MbrWebpntHist();
            param.setWebpntTpCd(WebPointEnum.WEBPNT.toString()); // WEBPNT P포인트
            param.setWebpntResnCd(WebPointEnum.WebPntResnCd.PCH.toString()); // PCH : 구매
            param.setMbrNo(ord.getMbrNo());
            param.setOrdNo(item.getOrdNo());
            param.setOrdGodTurn(item.getOrdGodTurn());
            param.setQtyTurn(Integer.parseInt(item.getSeqNo()));
            param.setMallId(ord.getMallId());

              /*P포인트 사용 (즉시차감) */
            if (item.getWebpntUseUntPrc() > 0) {
                param.setWebpntDetailResnCd(WebPointEnum.WebPntPchDtlResnCd.WEBPNT_USE.toString()); // WEBPNT_USE P포인트사용 WEBPNT_ACCML : P포인트적립
                param.setWebpntAmt(bUnitUseWebpnt);
                param.setWebpntStatCd(WebPointEnum.WebPntStatCd.USE.toString());
                param.setResnDscr("주문 P포인트 사용");

				// 실소가보다 포인트 사용금액이 클경우
				if ( bUnitUseWebpnt.compareTo(new BigDecimal(item.getReaPrc())) > 0) {
					log.error(CommonResponseCode.OD_40004_실소가보다_P포인트_사용금액이_큼.toMessage());
					throw new OrderCompleteFailException("ord.error.invalid_price", null);
				}
				memberBenefitCommandService.insertWebPoint(param);
				usePnt = usePnt.add(bUnitUseWebpnt);
            }

            /* P포인트 적립예정*/
            if (item.getWebpntAccmlUntPrc() > 0 ) {
                param.setWebpntDetailResnCd(WebPointEnum.WebPntPchDtlResnCd.WEBPNT_ACCML.toString()); // WEBPNT_USE P포인트사용 WEBPNT_ACCML : P포인트적립
                param.setWebpntAmt(bUnitSaveWebpnt);
                param.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_PREARNGE.toString());
                param.setResnDscr("주문 P포인트 적립");
                memberBenefitCommandService.insertWebPoint(param);
            }
        }

        // 가용포인트 마이너스인경우 0으로 강제처리
        if(totalPnt.compareTo( new BigDecimal("0")) < 0 ) {
 		   totalPnt= new BigDecimal("0");
 		}

		// 가용포인트를 초과하였을 경우
		if ( usePnt.compareTo(totalPnt) > 0) {
			log.error(CommonResponseCode.OD_40005_가용_P포인트_사용_초과.toMessage());
			throw new OrderCompleteFailException("ord.error.invalid_price", null);
		}
    }

	public long updateOrgMbrOrd(Ord ord){
		return orderCommandRepository.updateOrgMbrOrd(ord);
	}

	public List<HashMap<String, String>> selectCafeDXMOrd(String ordNo){
		return orderSelectRepository.selectCafeDXMOrd(ordNo);
	}

	public void updateOrgOrdStatCd(Ord ord){
		orderCommandRepository.updateOrgOrdStatCd(ord);
	}

	public void updateOrgOrdGod(OrdGod ordGod){
		orderCommandRepository.updateOrgOrdGod(ordGod);
	}

	//배송비즉시할인쿠폰 : by cannon (2016.06.07)
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public String genMbrIssuCpnNo() throws Exception{
		return orderCommandRepository.genMbrIssuCpnNo();
	}
	
	//배송비즉시할인쿠폰 : by cannon (2016.06.07)
	public void insertMbrIssuCpn(MbrIssuCpn mbrIssuCpn) throws Exception{
		mbrIssuCpnRepository.insertMbrIssuCpn(mbrIssuCpn);
	}

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경 기능
	 * 	- 물류배송지, 물류출고지시상품 정보 수정
	 *
	 * @param lgsDlvsp
	 * @param ordGodExtends
	 * @throws Exception
	 */
	public void updatePkup2pkupLgs(LgsDlvspExtend lgsDlvsp, List<LgsDlivyDrctGodExtend> lgsDlivyDrctGods)
			throws Exception {

		if (log.isInfoEnabled()) {
			log.info("Update the logistics related information. [{}/{}]",
					lgsDlvsp.getOrdNo(), lgsDlvsp.getDlvPcupspTurn())
					;
		}

		// 물류 배송지 정보 수정
		this.orderCommandRepository.updatePkup2pkupLgsDlvsp(lgsDlvsp);

		// 물류 배송지 이력 생성
		this.orderCommandRepository.insertLgsDlvspHist(lgsDlvsp.getOrdNo());

		/*
		 * 물류배송(이력) 정보 수정정
		 * 	- 픽업번호(DMSTC_WAYBIL_NO), 픽업번호생성일시(DMSTC_WAYBIL_NO_REG_DT), 매장픽업SMS발송여부(SHOP_PKUP_SMS_SND_YN)
		 */
		LgsDlv lgsDlv = new LgsDlv();
		lgsDlv.setOrdNo(lgsDlvsp.getOrdNo());
		lgsDlv.setDlvPcupspTurn(lgsDlvsp.getDlvPcupspTurn());
		lgsDlv.setUdterId(lgsDlvsp.getUdterId());

		this.orderCommandRepository.updatePkup2pkupLgsDlv(lgsDlv);

		LgsDlvHist lgsDlvHist = new LgsDlvHist();
		lgsDlvHist.setOrdNo(lgsDlvsp.getOrdNo());
		lgsDlvHist.setDlvPcupspTurn(lgsDlvsp.getDlvPcupspTurn());
		lgsDlvHist.setUdterId(lgsDlvsp.getUdterId());
		lgsDlvHist.setRegtrId(lgsDlvsp.getUdterId());

		this.deliveryCommandRepository.insertLgsDlvHist(lgsDlvHist);

		// 물류출고지시상품 정보 수정
		for (LgsDlivyDrctGodExtend lgsDlivyDrctGod : lgsDlivyDrctGods) {

			//스플릿 3차 2017-05-22 배송파트 요청으로 배정상태에 따라 분기처리
			//출고지시 출고매장 b031 / 배송상태 배정대기 / 매장픽업주문  일 경우 미배정으로 판단
						
			if("B031".equalsIgnoreCase(lgsDlivyDrctGod.getDlvShopId())
					&& ( "DLV_WAIT".equalsIgnoreCase(lgsDlivyDrctGod.getDlvStatCd()) 
							|| "SHTG_RCEPT".equalsIgnoreCase(lgsDlivyDrctGod.getDlvStatCd()) )
					&&"SHOP_PKUP".equalsIgnoreCase(lgsDlivyDrctGod.getDlivyDrctTpCd())){				
				//미배정일 경우 배송지만 처리해야함
			}else{
				//배정일 경우 출고지시 상품도 update 처리함
				
				// LGS_DLV의 '배송순번'(DLV_TURN)을 조회하기 위한 파라미터
				lgsDlivyDrctGod.setOrdNo(lgsDlvsp.getOrdNo());
				lgsDlivyDrctGod.setDlvPcupspTurn(lgsDlvsp.getDlvPcupspTurn());
				// 변경될 픽업매장ID
				//lgsDlivyDrctGod.setDlvShopId(lgsDlvsp.getPkupShopId4Change());
				// '배송상태코드'는 '출고지시'
				lgsDlivyDrctGod.setDlvStatCd("DLIVY_DRCT");
				lgsDlivyDrctGod.setUdterId(lgsDlvsp.getUdterId());

				// 물류출고지시상품 정보 수정
				this.orderCommandRepository.updatePkup2pkupLgsDlvDrctGod(lgsDlivyDrctGod);

				LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
				BeanUtils.copyProperties(lgsDlivyDrctGod, lgsDlivyDrctGodHist);

				// 물류출고지시상품 정보 이력 생성
				this.orderCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

				if (log.isInfoEnabled()) {
					log.info("Update the lgs_dlivy_drct_god/hist table.[{}/{}]",
							lgsDlvsp.getOrdNo(), lgsDlivyDrctGod.getDlivyDrctGodNo() );
				}				
			}
		}
	}
	
	//고객선호결제수단 : UXUI개선 //#34475 로 트랜잭션 설정 추가 
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void insertMbrPreferPayMn(MbrPreferPayMn mbrPreferPayMnDTO) throws Exception{
		mbrPreferPayMnRepository.deleteMbrPreferPayMn(mbrPreferPayMnDTO);
		if(mbrPreferPayMnDTO.getPayMnCd()!=null && !"".equals(mbrPreferPayMnDTO.getPayMnCd())){
			mbrPreferPayMnRepository.insertMbrPreferPayMn(mbrPreferPayMnDTO);
		}
	}
	
	public void setSmsRecptnSect(SmsRecptnSectDTO smsRecptnSectDTO) throws Exception{
		orderCommandRepository.setSmsRecptnSect(smsRecptnSectDTO);
	}
	
	public int updateNewPayNoByPayNo(MypageOrderInfoDTO mypageOrderInfoDto) throws Exception{
		return orderCommandRepository.updateNewPayNoByPayNo(mypageOrderInfoDto);
	}
	
	//# 36273 : god_no, itm_no 순으로 정렬 후 처리 (상품재고동기화 와 dead-lock 회피)
	public List<OrdGodExtends> getListOrdGodSortedByGodnoItmno(List<OrdGodExtends> ordGodExtendsList){
		List<OrdGodExtends> list = new ArrayList<OrdGodExtends>();
		list.addAll(ordGodExtendsList);
		Collections.sort(list, new Comparator<OrdGodExtends>(){
			@Override
			public int compare(OrdGodExtends o1, OrdGodExtends o2){
				String key1 = o1.getGodNo()+o1.getItmNo();
				String key2 = o2.getGodNo()+o2.getItmNo();
				return key1.compareTo(key2);
			}
		});
		return list;
	}

	
	
	
	
	
	
	
	//////////////////////////////스플릿으로 추가
	
//
//	public PointTempReductionTableSDO getBpCbTemDelTableForSplitHub(List<InfOrdGodErpDstbExtends> infErpList, String erpNo, String pntType, String orderType) throws Exception {
//		List<BpCbTemDelListSDO> list = getBpCbTemDelTable(infErpList, erpNo, pntType, orderType);
//
//		PointTempReductionTableSDO pointTempReductionTableSDO = new PointTempReductionTableSDO();
//
//		//컨버트
//		List<PointTempReductionSDO> pointTempReductionSDOList = convertBpCbTemDelList(list);
//		if("use".equalsIgnoreCase(pntType)){
//			pointTempReductionTableSDO.setCntcSectCd("PNT_USE_TMPR_REDCT");//사용포인트
//		}else{
//			pointTempReductionTableSDO.setCntcSectCd("PNT_ACCML_TMPR_REDCT");//적립포인트
//		}
//
//		pointTempReductionTableSDO.setOrdClmSectCd(orderType);
//
//		pointTempReductionTableSDO.setPointTempReductionList(pointTempReductionSDOList);
//
//
//		return pointTempReductionTableSDO;
//	}
	
	
	
	
	//////////////////////////////////////////////////////////////////////
	//스플릿으로 추가
	//////////////////////////////////////////////////////////////////////
	
	/**
	 * 임시삭감 SDO를 스플릿 SDO로 컨버트
	 * @param list
	 * @return
	 */
//	private List<PointTempReductionSDO> convertBpCbTemDelList(List<BpCbTemDelListSDO> list) {
//		List<PointTempReductionSDO> resultList = new ArrayList<PointTempReductionSDO>();
//
//		PointTempReductionSDO pointTempReductionSDO = null;
//		for(BpCbTemDelListSDO sdo : list){
//			pointTempReductionSDO = new PointTempReductionSDO();
//            pointTempReductionSDO.setErpCstmrNo(sdo.getPartner());
//            pointTempReductionSDO.setOrdNo(sdo.getFspVbenr());
//            pointTempReductionSDO.setOrdClmSectCd(sdo.getOrderType());
//            pointTempReductionSDO.setTmprRedctTurn(sdo.getFspSeqno());
//            pointTempReductionSDO.setRcptfrCshbBlce(sdo.getCbBalAmt());
//            pointTempReductionSDO.setCrncyCd(sdo.getWaers());
//
//            resultList.add(pointTempReductionSDO);
//		}
//
//		return resultList;
//	}
//
//	/**
//	 * 구매이력SDO를 스플릿 SDO로 컨버트
//	 * @param bpSalesSaveSDO
//	 * @return
//	 */
//	private PurchaseHistorySDO convertBpSalesSave(BpSalesSaveSDO bpSalesSaveSDO) {
//		PurchaseHistorySDO purchaseHistorySDO = new PurchaseHistorySDO();
//
//		// HEADER
//		purchaseHistorySDO.setRcptfrNo(bpSalesSaveSDO.getIsPrHeaderSDO().getRctNo());
//		purchaseHistorySDO.setOrdNo(bpSalesSaveSDO.getIsPrHeaderSDO().getFspVbenr());
//		purchaseHistorySDO.setShopId(bpSalesSaveSDO.getIsPrHeaderSDO().getWerks());
//		purchaseHistorySDO.setErpCstmrNo(bpSalesSaveSDO.getIsPrHeaderSDO().getBpNo());
//		purchaseHistorySDO.setPchDate(bpSalesSaveSDO.getIsPrHeaderSDO().getPurDat());
//		purchaseHistorySDO.setPchHour(bpSalesSaveSDO.getIsPrHeaderSDO().getPurTime());
//		purchaseHistorySDO.setCsmrPrc(bpSalesSaveSDO.getIsPrHeaderSDO().getReaPrc());
//		purchaseHistorySDO.setDcAmt(bpSalesSaveSDO.getIsPrHeaderSDO().getDisAmt());
//		purchaseHistorySDO.setRcptfrCshbBlce(bpSalesSaveSDO.getIsPrHeaderSDO().getCbBalAmt());
//		purchaseHistorySDO.setCrncyCd(bpSalesSaveSDO.getIsPrHeaderSDO().getWaers());
//		purchaseHistorySDO.setErpGrdCd(bpSalesSaveSDO.getIsPrHeaderSDO().getClassBp());
//		purchaseHistorySDO.setOrdClmSectCd(bpSalesSaveSDO.getIsPrHeaderSDO().getOrderType());
//
//		//IT_PR_ITEM
//		List<ItPrItemSDO> itPrItemSDOList = bpSalesSaveSDO.getItPrItemSDOList();
//		List<PurchaseHistoryItemSDO> itemSDOList = new ArrayList<PurchaseHistoryItemSDO>();
//		PurchaseHistoryItemSDO itemSDO = null;
//		for (ItPrItemSDO sdo : itPrItemSDOList) {
//			itemSDO = new PurchaseHistoryItemSDO();
//            itemSDO.setRcptfrNo(sdo.getRctNo());
//            itemSDO.setQtyTurn(sdo.getSeqNo());
//            itemSDO.setSkuNo(sdo.getMatNo());
//            itemSDO.setItmQty(sdo.getMatQty());
//            itemSDO.setChrgFaNm(sdo.getResFa());
//            itemSDO.setRtlPrc(sdo.getStdPrc());
//            itemSDO.setCsmrPrc(sdo.getReaPrcPr());
//            itemSDO.setCsmrPrcSumAmt(sdo.getReaPrc());
//            itemSDO.setDcAmt(sdo.getDisAmt());
//            itemSDO.setDcRt(sdo.getDisRate());
//            itemSDO.setAccmlRt(sdo.getAddRate());
//            itemSDO.setAccmlAmt(sdo.getCbSaveAmt());
//            itemSDO.setCybrMnyAccmlAmt(sdo.getFspCbA());
//            itemSDO.setCybrMnyUseAmt(sdo.getFspCbU());
//            itemSDO.setQtyUnitCd(sdo.getMeins());
//            itemSDO.setCrncyCd(sdo.getWaers());
//            itemSDO.setPntSectCd(sdo.getGb());
//            //itemSDO.setTagSn();
//            //itemSDO.setPayMnCd();
//            //itemSDO.setImdtlDcYn();
//
//            // #54953 : 구매이력개선
//            itemSDO.setPrdlstCd(sdo.getPrdlstCd());
//            itemSDO.setErpBrndGrpId(sdo.getErpBrndGrpId());
//            itemSDO.setColorCd(sdo.getColorCd());
//            itemSDO.setOptCd1(sdo.getOptCd1());
//
//
//            itemSDOList.add(itemSDO);
//		}
//		purchaseHistorySDO.setItemSDOList(itemSDOList);
//
//
//		//IT_PR_PAYMENT
//		List<ItPrPaymentSDO> itPrPaymentSDOList = bpSalesSaveSDO.getItPrPaymentSDOList();
//		List<PurchaseHistoryPaymentSDO> paymentSDOList = new ArrayList<PurchaseHistoryPaymentSDO>();
//		PurchaseHistoryPaymentSDO paymentSDO = null;
//		for (ItPrPaymentSDO sdo : itPrPaymentSDOList) {
//			paymentSDO = new PurchaseHistoryPaymentSDO();
//			paymentSDO.setRcptfrNo(sdo.getRctNo());
//			paymentSDO.setPayTurn(sdo.getSeqNo());
//			paymentSDO.setPayMnCd(sdo.getZlsch());
//			paymentSDO.setCpnCrtfcCd(sdo.getRecNo());
//			paymentSDO.setPayCardNm(sdo.getCrdCmp());
//			paymentSDO.setAcPlCrncyAmt(sdo.getDmbtr());
//			paymentSDO.setCrncyCd(sdo.getWaers());
//			paymentSDO.setQtyTurn(sdo.getSeqnoItem());
//			paymentSDO.setErpCmpgId(sdo.getCampaignId());
//
//			paymentSDOList.add(paymentSDO);
//		}
//		purchaseHistorySDO.setPaymentSDOList(paymentSDOList);
//
//		//IT_GIFT
//		List<ItGiftSDO> itGiftSDOList = bpSalesSaveSDO.getItGiftSDOList();
//		List<PurchaseHistoryGiftSDO> giftSDOList = new ArrayList<PurchaseHistoryGiftSDO>();
//		PurchaseHistoryGiftSDO giftSDO = null;
//		if(itGiftSDOList != null){
//			for (ItGiftSDO sdo : itGiftSDOList) {
//				giftSDO = new PurchaseHistoryGiftSDO();
//				giftSDO.setClntId(sdo.getMandt());
//				giftSDO.setRcptfrNo(sdo.getRctNo());
//				giftSDO.setGftNo(sdo.getGiftNo());
//				giftSDO.setGftQty(sdo.getFspGiftQty());
//				giftSDO.setGftPrdlstQty(sdo.getFspItemQty());
//				giftSDO.setGftRtlPrcSumAmt(sdo.getFspGiftAmt());
//				giftSDO.setQtyUnitCd(sdo.getMeins());
//				giftSDO.setCrncyCd(sdo.getWaers());
//				giftSDO.setRegtrNm(sdo.getCrnam());
//				giftSDO.setRegDate(sdo.getCrdat());
//				giftSDO.setRegHour(sdo.getCrtim());
//				giftSDO.setUdterNm(sdo.getUpnam());
//				giftSDO.setUdtDate(sdo.getUpdat());
//				giftSDO.setUdtHour(sdo.getUptim());
//
//				giftSDOList.add(giftSDO);
//			}
//			purchaseHistorySDO.setGiftSDOList(giftSDOList);
//		}
//
//
//		return purchaseHistorySDO;
//	}
//
//
//	/**
//	 * 포인트적립[OR (임시삭감), RE(삭감환원)]
//	 * @param infErpList
//	 * @param erpNo
//	 * @param pntType
//	 * @param orderType
//	 * @return List<BpCbTemDelListSDO>
//	 * @throws Exception
//	 */
//	private List<BpCbTemDelListSDO> getBpCbTemDelTable(List<InfOrdGodErpDstbExtends> infErpList, String erpNo, String pntType, String orderType)
//	        throws Exception {
//		List<BpCbTemDelListSDO> list = new ArrayList<BpCbTemDelListSDO>();
//
//		for (InfOrdGodErpDstbExtends inf : infErpList) {
//			BigDecimal bCbBalAmt = inf.getUnityPntAccmlUntPrc();
//			if ("use".equals(pntType)) {
//				bCbBalAmt = inf.getUnityPntUseUntPrc();
//			}
//
//			if (bCbBalAmt.compareTo(BigDecimal.ZERO) > 0) {
//				BpCbTemDelListSDO bctd = new BpCbTemDelListSDO();
//				bctd.setFspVbenr(inf.getOrdNo());
//				bctd.setWaers(inf.getCrncyCd());
//				bctd.setFspSeqno(String.valueOf(inf.getQtyTurn()));
//				bctd.setCbBalAmt(bCbBalAmt.toString());
//				bctd.setOrderType(orderType);	// OR, RE
//				bctd.setPartner(erpNo); // erp user id
//
//				list.add(bctd);
//			}
//		}
//
//		return list;
//	}
//
//	/**
//	 * 온오프 쿠폰 데이터 셋팅  스플릿프로젝트로 추가 2017-03-17
//	 * @param ordNo
//	 * @return
//	 */
//	public List<CouponTempUseSDO> getCouponTempUseSDOListForSplitHub(String ordNo, String orderType) {
//
//		List<CouponTempUseSDO> couponTempUseSDOList = new ArrayList<CouponTempUseSDO>();
//		MbrIssuCpn cpnSearch = new MbrIssuCpn();
//		cpnSearch.setOrdNo(ordNo);
//		cpnSearch.setCpnStatCd("USE");
//
//		List<MbrIssuCpn> mCouponList = orderSelectRepository.selectUsedOnOffCouponSplit(cpnSearch);
//
//		for(MbrIssuCpn mbrIssuCpn: mCouponList){
//
//    		CouponTempUseSDO couponTempUseSDO = new CouponTempUseSDO();
//    		String offerNum = "";
///*
//    	    if(mbrIssuCpn.getChkNum() == null){
//    	    	offerNum = mbrIssuCpn.getCpnCrtfcCd();
//    	    }
//    	    else{
//    	    	offerNum = mbrIssuCpn.getCpnCrtfcCd() + mbrIssuCpn.getChkNum();
//    	    }
//*/
//    	    //컨버트
//			couponTempUseSDO.setOfrNo(offerNum);
//			couponTempUseSDO.setOrdNo(ordNo);
//			couponTempUseSDO.setOrdClmSectCd(orderType);
//			couponTempUseSDOList.add(couponTempUseSDO);
//
//		}
//		return couponTempUseSDOList;
//	}

	public void setOrdErpTrnsmisListData(String ordNo, List<OrdErpTrnsmis> listErpTrans, String resultCd, String erpTpCd, String orderType) {
		
/*		   >. 사용포인트 임시삭감 : UNITY_PNT_USE_TMPR
		   >. 적립포인트 임시삭감 : UNITY_PNT_ACCML_TMPR
		   >. 구매 이력 증가 전송 : ERP_OR_ERP
		   >. 구매 이력 감소 전송 : ERP_RE_ERP
		   >. 리테일 전송 : RTL_TRNSMIS
		   >. 통합 포인트 사용 확정 : UNITY_PNT_USE_DCSN
		   >. 통합 포인트 적립 확정 : UNITY_PNT_ACCML_DCSN';
*/		

		
		String erpResultCd = "";
		
		if("UNITY_PNT_USE_TMPR".equals(erpTpCd)
				|| "UNITY_PNT_ACCML_TMPR".equals(erpTpCd)
				|| "ONOFLNE_CPN_USE_TMPR".equals(erpTpCd)){
/*			Y: RE 성공
			RH: RE HUB 전송
			F: RE 실패.
			R: OR 성공
			OH: OR 허브 전송
			E: OR 실패.
			X: 해당없음.
			N: 대상이지만 미전송*/	
			if("OR".equals(orderType)){ //OR
				if("Y".equals(resultCd)){//hub 성공 erp 성공
					erpResultCd = "R";
				}else if("N".equals(resultCd)){//hub 성공 erp 실패나 미전송
					erpResultCd = "OH";
				}
			}else{ //RE
				if("Y".equals(resultCd)){//hub 성공 erp 성공
					erpResultCd = "Y";
				}else if("N".equals(resultCd)){//hub 성공 erp 실패나 미전송
					erpResultCd = "RH";
				}
			}
			
		}else{
/*			N: 대상이지만 미전송.
			H: HUB 전송
			X: 해당없음
			E: 전송실패.
			Y: 전송 성공*/
			if("Y".equals(resultCd)){//hub 성공 erp 성공
				erpResultCd = "Y";
			}else if("N".equals(resultCd)){//hub 성공 erp 실패나 미전송
				erpResultCd = "H";
			}
		}
		
		
		
		// 주문 Erp 전송
		OrdErpTrnsmis erpTrans = new OrdErpTrnsmis();
		erpTrans.setOrdNo(ordNo);
		erpTrans.setOrdErpTrnsmisTpCd(erpTpCd);
		erpTrans.setErpTrnsmisCd(erpResultCd);
		
		listErpTrans.add(erpTrans);
		
		
		
	}
	
	public List<Ord> selectOrdForUpdate(String ordNo) throws Exception {
		return orderSelectRepository.selectOrdForUpdate(ordNo);
	}

	/**
	 * #43357 시리얼 검수 초기화 관련 수정 요청 로 추가 
	 * 클레임에서 사용시 클레임 번호만 셋팅해야함
	 * 주문에서 사용시 주문번호만 셋팅해야함
	 * @param clm
	 */
	public void checkInfoInit(Clm clm) {		
		orderCommandRepository.checkInfoInit(clm);
	}

	public void insertOrdGodSvcDetailCnnc(OrdGodSvcDetailCnncExtend ordGodSvcDetailCnncExtend) {
		orderCommandRepository.insertOrdGodSvcDetailCnnc(ordGodSvcDetailCnncExtend);
	}
	
	/**
	 * 구매확정 처리 - 상품단위
	 * 
	 * @param mypageOrderInfoDTO
	 */
	public void updateOrderDecision(List<OrdGod> ordGodList, SystemPK pk) {

		Ord ordParam = new Ord();
		ordParam.setOrdNo(ordGodList.get(0).getOrdNo());
		Ord ord = ordRepository.selectOrd(ordParam);
		int checkCnt = orderSelectRepository.selectOrdGodSaleComptFirst(ord);
		
		for (OrdGod ordGod : ordGodList) {
			
			MypageOrderInfoDTO dto = new MypageOrderInfoDTO();
			
			dto.setOrdNo(ordGod.getOrdNo());
			dto.setOrdGodTurn(String.valueOf(ordGod.getOrdGodTurn()));
			
			orderCommandRepository.updateOrderDecision(dto);
		}
		
		// 네이버 페이 구매완료할 결제번호 조회
		String tradeComptPayNo = orderSelectRepository.getNaverPayTradeComptPayNo(ordGodList.get(0).getOrdNo());
		
		if (StringService.isNotEmpty(tradeComptPayNo)) {	// 구매완료 처리
			Pay pay = new Pay();
			pay.setPayNo(tradeComptPayNo);
			pay = payRepository.selectPay(pay);
			
			NaverConfirmReturnDTO retunDTO = naverPayService.nPayPurchaseConfirm(pay.getPgAprvNo(), pay.getPgStoreId(), pay.getPgCrsKey());
			
			try {
				PayPgIntrlckLog payLog = new PayPgIntrlckLog();
				
				payLog.setLogDt(new Date());
				payLog.setLogTpCd("TRADE_COMPT");
				payLog.setLogCont(retunDTO.toJSON());
				payLog.setPayNo(pay.getPayNo());
				payLog.setOrdNo(pay.getOrdNo());
				payLog.setMbrNo(ord.getMbrNo());
				payLog.setPgAprvNo(pay.getPgAprvNo());
				
				this.insertPayLogTransaction(payLog);
				
			} catch (Exception e) {
				log.error("PG log insert fail : " + e.getMessage(), e);
			}
			
			if ("Success".equals(retunDTO.getCode()) == false
					&& "AlreadyConfirm".equals(retunDTO.getCode()) == false) {
				throw new RuntimeException("네이버페이 구매완료 실패");
			}
		}
		
		AdapterHeader header = new AdapterHeader();
		
		if (pk == null) {
			
			header.setLangCd("KOR");
			//mallId
			//header.setMallId("DXM");
			header.setMallId(ordGodList.get(0).getMallId());
			header.setDvcCd("BATCH");
		} else {
			
			header.setLangCd(pk.getLang());
			header.setMallId(pk.getMall());
			header.setDvcCd(pk.getDevice());
		}
		
		// 클레임 주문건 관련 마일리지보다 선처리.
		if(checkCnt == 0 && ord.getMbrNo() != null)
		{
			Mbr mbr = new Mbr();
			mbr.setMbrNo(ord.getMbrNo());
			mbr = mbrRepository.selectMbr(mbr);
			
			//TODO : 몰 늘어나면 7일 자동 확정 배치 수정 필요함.
			SystemPK systemPK = new SystemPK();
			if (pk == null) {
				//mallId
				//systemPK.setMall("DXM");
				systemPK.setMall(ord.getMallId());
				systemPK.setLang("KOR");
				systemPK.setDevice("PC");
			} else {
				systemPK.setMall(pk.getMall());
				systemPK.setLang(pk.getLang());
				systemPK.setDevice(pk.getDevice());
				systemPK.setApp(pk.getApp());
			}
			
	        // 혜택API 회원 첫구매 혜택 - 회원 첫구매 혜택
	        MemberBenefitApiResult apiResult = memberBenefitFOComponent.callMemberBenefit(systemPK, MemberBenefitEnum.BnefSectCd.MBR_BEG_PCH_BNEF, null, mbr, null, ord.getMallId());
	        
	        log.info("MEMBER_BENEFIT_API : ResultCd({}),ResultMsg({})", apiResult.getResultCd(), apiResult.getResultMsg());
	        
	        int memberBenefitSuccessCnt = 0;
	        if(apiResult != null && apiResult.getMemberBenefitResultList() != null) {
	            List<MemberBenefitResult> resultList = apiResult.getMemberBenefitResultList();

	            for(int i= 0; i < resultList.size(); i++) {
	                MemberBenefitResult memberBenefitResult = resultList.get(i);
	                if(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage().equals(memberBenefitResult.getResultCd())) {
	                	memberBenefitSuccessCnt = memberBenefitSuccessCnt + 1;
	                }
	                log.info("MEMBER_BENEFIT_API_DETAIL : ResultCd({}),ResultMsg({})", memberBenefitResult.getResultCd(), memberBenefitResult.getResultMsg());
	            }
	        }
	        
	        if ((CommonResponseCode.PM_00012_회원_혜택_API_성공.toMessage().equals(apiResult.getResultCd()) && memberBenefitSuccessCnt > 0)
	        		&& StringService.isNotEmpty(mbr.getMobilAreaNo())) {
	        	try {
		        	ArrayList<String> params = new ArrayList<>();
					
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, 30);
					Date dt = cal.getTime();
					SimpleDateFormat sdf = new SimpleDateFormat("~Y/M/d");

					MPushAlimTalkSDO mPushAlimTalkSDO = new MPushAlimTalkSDO();
					
					if("MBM".equals(systemPK.getMall())) {
						//mPushAlimTalkSDO.setSendDirectFlag(true);
						
						params.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
						params.add(1, mbr.getMbrNm());
						params.add(2, "첫구매 15% 할인");
						params.add(3, sdf.format(dt));
						params.add(4, getConfigService().getProperty("ncp.url.mb_MLB.root")+ "/mypage/benefit/listCoupon");
					} else if("SAM".equals(systemPK.getMall())) {
						//mPushAlimTalkSDO.setSendDirectFlag(true);
						
						params.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
						params.add(1, mbr.getMbrNm());
						params.add(2, "첫구매 10% 할인");
						params.add(3, sdf.format(dt));
						params.add(4, getConfigService().getProperty("ncp.url.mb_SA.root")+ "/mypage/benefit/listCoupon");
					} else {
						params.add(0, mbr.getMbrNm());
						params.add(1, sdf.format(dt));
						params.add(2, getConfigService().getProperty("ncp.url.mb_DX.root")+ "/mypage/benefit/listCoupon");
					}
					
					
					mPushAlimTalkSDO.setMbrNo(mbr.getMbrNo());
					mPushAlimTalkSDO.setReceiveMobileNo(mbr.getMobilAreaNo() + mbr.getMobilTlofNo() + mbr.getMobilTlofWthnNo());
					mPushAlimTalkSDO.setCallerId(this.getClass().getName() + "updateOrderDecision");
					
					//mallId
					//mPushAlimTalkSDO.setMallId("DXM");
					mPushAlimTalkSDO.setMallId(systemPK.getMall());
					mPushAlimTalkSDO.setMsgKey(systemPK.getMall() + "_ORD_FIRSTPCH_01");
					mPushAlimTalkSDO.setParams(params);
					
					header.setRequestId(this.interfaceApiCommon.getRequestId());
					
					mPushAdapter.sendAlimTalk(mPushAlimTalkSDO, header);
					
	        	} catch (Exception e) {
	        		log.error("쿠폰 발급 알림 전송 실패.");
	        		log.error(e.getMessage(), e);
	        	}
	        }
		}
		
		OrderUseTempMileageSDO sdo = orderSelectRepository.selectOrderDecisionAccmlMileList(ordGodList);
		
		if (sdo.getOrdList() == null || sdo.getOrdList().isEmpty()) {
			return;
		}
        
		try {
			
			header.setRequestId(this.interfaceApiCommon.getRequestId());
			
			String result = orderAdapter.useTempMemberMileage(sdo, header);
			OrderUseTempMileageSDO resultObj = JsonService.unmarshalling(result, OrderUseTempMileageSDO.class);
			
			if (InterfaceConstants.IF_RESULT_CD_SUCESS.equals(resultObj.getResponseCd()) == false) {
				throw new RuntimeException("마일리지 임시처리 실패");
			}
			
			for (OrdGod ordGod : ordGodList) {
				OrdGodErpTrnsmisDTO godDTO = new OrdGodErpTrnsmisDTO();
				godDTO.setOrdNo(ordGod.getOrdNo());
				godDTO.setOrdGodTurn(ordGod.getOrdGodTurn());
				godDTO.setOrdGodErpTrnsmisTpCd("MILE_ACCML");
				godDTO.setErpTrnsmisCd("SUCCES");
				godDTO.setRegtrId(ordGod.getRegtrId());
				orderCommandRepository.insertOrdGodMileAccmlErpTrnsmis(godDTO);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			throw new RuntimeException("마일리지 임시처리 오류");
		}
	}
	
	public String sendRtOrderGoods(String ordNo,String clmNo){
		String rtCode = "000";
		
		return rtCode;
	}
	
	public String cancelRtOrderGoods(String ordNo,String clmNo){
		String rtCode = "000";
		
		return rtCode;
	}
	
	public void checkBlackMember(){
		
//		if (ContextService.hasRole()) {
//			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
//			Mbr mbr = userDetail.getMbr();
//			
//			if(MemberEnum.MemberStatCd.BLCLST_A.toString().equals( mbr.getMbrStatCd())){
//				throw new OrderCompleteFailException("ord.error.not.black.order", null);
//			}
//		}
	}
	
	
}
