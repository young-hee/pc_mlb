package com.plgrim.ncp.biz.order.repository;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtends;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPchShop;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpPntTrnsmis;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpTrnsmis;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrm;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodInv;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodSvcDetailCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdNmbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayEscr;
import com.plgrim.ncp.base.entities.datasource1.pay.PayPgIntrlckLog;
import com.plgrim.ncp.biz.claim.data.RefundPayDTO;
import com.plgrim.ncp.biz.order.data.ChangeOrderDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.data.OrdGodErpTrnsmisDTO;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.biz.order.data.OrderStockDTO;
import com.plgrim.ncp.biz.order.data.SmsRecptnSectDTO;

@Repository
public class OrderCommandRepository extends AbstractRepository {

	/**
	 * 주문 등록.
	 *
	 * @param ord the Ord
	 * @throws SQLException the SQL exception
	 */
	public void insertOrd(Ord ord) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertOrd", ord);
	}

	/**
	 * 주문 상품 등록.
	 *
	 * @param ordGod the OrdGod
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdGod(OrdGod ordGod) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertOrdGod", ordGod);
	}

	/**
	 * 주문 구성 상품 연결 등록.
	 *
	 * @param ordCpstGodCnnc the OrdCpstGodCnnc
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdCpstGodCnnc(OrdCpstGodCnnc ordCpstGodCnnc) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertOrdCpstGodCnnc", ordCpstGodCnnc);
	}

	/**
	 * 주문 상품 서비스 연결 등록.
	 *
	 * @param ordGodSvcCnnc the OrdGodSvcCnnc
	 * @throws SQLException the SQL exception
	 */
	/*
	public void insertOrdGodSvcCnnc(OrdGodSvcCnnc ordGodSvcCnnc) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertOrdGodSvcCnnc", ordGodSvcCnnc);
	}
	*/
	/**
	 * 주문 상품 적용 프로모션 등록.
	 *
	 * @param ordGodAplPrm the OrdGodAplPrm
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdGodAplPrm(OrdGodAplPrm ordGodAplPrm) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertOrdGodAplPrm", ordGodAplPrm);
	}

	/**
	 * 주문 클레임 적용 프로모션 등록.
	 *
	 * @param ordClmAplPrm the OrdClmAplPrm
	 * @throws SQLException the SQL exception
	 */
	/*
	public void insertOrdClmAplPrm(OrdClmAplPrm ordClmAplPrm) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertOrdClmAplPrm", ordClmAplPrm);
	}
	*/
	
	/**
	 * 물류 배송지 등록.
	 *
	 * @param lgsDlvsp the LgsDlvsp
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlvsp(LgsDlvsp lgsDlvsp) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertLgsDlvsp", lgsDlvsp);
	}

	/**
	 * 물류 배송 등록.
	 *
	 * @param lgsDlv the LgsDlv
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlv(LgsDlv lgsDlv) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertLgsDlv", lgsDlv);
	}


	/**
	 * 물류 배송 등록.
	 *
	 * @param lgsDlv the LgsDlv
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlvWithShopPkup(LgsDlv lgsDlv) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertLgsDlvWithShopPkup", lgsDlv);
	}


	/**
	 * 물류 출고지시 상품 등록.
	 *
	 * @param lgsDlivyDrctGod the LgsDlivyDrctGod
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertLgsDlivyDrctGod", lgsDlivyDrctGod);
	}

	/**
	 * 결제 등록.
	 *
	 * @param pay the Pay
	 * @throws SQLException the SQL exception
	 */
	public void insertPay(Pay pay) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertPay", pay);
	}

	/**
	 * 결제 에스크로 등록.
	 *
	 * @param payEscr the PayEscr
	 * @throws SQLException the SQL exception
	 */
	public void insertPayEscr(PayEscr payEscr) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertPayEscr", payEscr);
	}
	
	/**
	 * 결제 로그 등록.
	 * @param payLog
	 * @throws SQLException the SQL exception
	 */
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public void insertPayLog(PayPgIntrlckLog payLog) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertPayLog", payLog);
	}

	/**
	 * 주문 ERP 포인트 전송 등록.
	 *
	 * @param ordErpPntTrnsmis the OrdErpPntTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdErpPntTrnsmis(OrdErpPntTrnsmis ordErpPntTrnsmis) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertOrdErpPntTrnsmis", ordErpPntTrnsmis);
	}

	/**
	 * 인터페이스 주문 상품 ERP 분배 등록.
	 *
	 * @param infOrdGodErpDstb the InfOrdGodErpDstb
	 * @throws SQLException the SQL exception
	 */
	public void insertInfOrdGodErpDstb(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertInfOrdGodErpDstb", infOrdGodErpDstb);
	}
	
	/**
	 * 회원쿠폰갱신
	 * @param ordGodAplPrm
	 */
	public void updateMbrIssuCoupon(OrdGodAplPrm ordGodAplPrm) {
		getSession1().insert("com.plgrim.ncp.biz.order.command.updateMbrIssuCoupon", ordGodAplPrm);
	}

	/**
	 * 주문 비회원 약관 동의 등록.
	 *
	 * @param ordNmbrStplatAgr the OrdNmbrStplatAgr
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdNmbrStplatAgr(OrdNmbrStplatAgr ordNmbrStplatAgr) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertOrdNmbrStplatAgr", ordNmbrStplatAgr);
	}

	/**
	 * 주문마스터 갱신
	 * @param ord Ord
	 * @throws Exception
	 */
	public void updateOrd(Ord ord) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateOrd", ord);
	}
	
	@Deprecated
	public void updateOrdGod(Ord ord) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateOrdGod", ord);
	}
	
	public void updateStockGeneral(OrderStockDTO stock) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateStockGeneral", stock);
	}
	
	public void updateStockStore(OrderStockDTO stock) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateStockStore", stock);
	}
	
	public void updateStockGift(OrderStockDTO stock) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateStockGift", stock);
	}

	public void modifyPreSalesYnInf(InfOrdGodErpDstbExtends inf) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.modifyPreSalesYnInf", inf);
	}

	public void modifyPreSalesYnLgs(InfOrdGodErpDstbExtends inf) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.modifyPreSalesYnLgs", inf);
	}
	
	public void insertLgsDlvspHist(String ordNo) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.insertLgsDlvspHist", ordNo);
	}
	
	public void insertLgsDlvHist(String ordNo) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.insertLgsDlvHist", ordNo);
	}
	
	public void insertLgsDLivyDrctGodHist(String ordNo) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.insertLgsDLivyDrctGodHist", ordNo);
	}

	/**
	 * 대량주문,전화주문 ORD 업데이트(결제처리)
	 *
	 * @param ord the Ord
	 * @throws SQLException the SQL exception
	 */
	public void updatePayMethodChangeOrd(Ord ord) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updatePayMethodChangeOrd", ord);
	}	
	/**
	 * 대량주문,전화주문 ORD 업데이트(결제처리)
	 *
	 * @param ord the Ord
	 * @throws SQLException the SQL exception
	 */
	public void updatePayMethodChangePay(Pay pay) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updatePayMethodChangePay", pay);
	}	
	/**
	 * 클레임 배송비 결제처리
	 *
	 * @param ord the Ord
	 * @throws SQLException the SQL exception
	 */
	public void updaeClmDlvAmtClmStatCd(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updaeClmDlvAmtClmStatCd", mypageOrderInfoDTO);
	}	

	/**
	 * REPAIR : 클레임 배송비 결제처리 무료수선일 경우
	 *
	 * @param mypageOrderInfoDTO the mypage order info dto
	 * @throws Exception the exception
	 */
	public void updaeClmDlvAmtClmStatCdForRepair(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updaeClmDlvAmtClmStatCdForRepair", mypageOrderInfoDTO);
	}

	/**
	 * 프론트 변경시 취소 정보 insert
	 *
	 * @param ord the Ord
	 * @throws SQLException the SQL exception
	 */
	public void insertCancelPay(RefundPayDTO refundPayDTO) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertCancelPay", refundPayDTO);
	}	
	/**
	 * 프론트 가상계좌 결제수단 변경시 기존에 있던 주결제 취소누적금액 추가
	 *
	 * @param ord the Ord
	 * @throws SQLException the SQL exception
	 */
	public void updateCancelPay(MypageOrderInfoDTO mypageOrderInfoDTO) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateCancelPay", mypageOrderInfoDTO);
	}
	
	/**
	 * 픽업주문 일반 배송 전환시 신배송지로 업데이트
	 *
	 * @param ord the Ord
	 * @throws SQLException the SQL exception
	 */
	public void insertLgsDlvDlvPcupspTurn(LgsDlvFoExtend lgsDlvFoExtend) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertLgsDlvDlvPcupspTurn", lgsDlvFoExtend);
	}
	/**
	 * 픽업주문 일반 배송 전환시 신배송지로 업데이트
	 *
	 * @param ord the Ord
	 * @throws SQLException the SQL exception
	 */
	public void updateOrdGodDlvPcupspTurn(OrdGod ordGod) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateOrdGodDlvPcupspTurn", ordGod);
	}

	/**
	 * PG 전용 무통장(가상계좌) 입금 승인 처리	-결재(deal_tp_cd),주문(ord_stat_cd)
	 *
	 * @param map
	 * @throws SQLException the SQL exception
	 */
	public void updateDealTpCd(Map<String, String> param) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateDealTpCd", param);
	}
	public void updateOrdStatCd(Map<String, String> param) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateOrdStatCd", param);
	}

	/**
	 * 회원구매매장(픽업주문시)
	 * */
	public void mergeMbrPchShop(MbrPchShop mbrPchShop) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.mergeMbrPchShop", mbrPchShop);
	}
	
	/**
	 * 현금영수증 재신청 
	 *
	 * @param ord the Ord
	 * @throws SQLException the SQL exception
	 */
	public void updateReceipt(Ord ord) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateReceipt", ord);
	}
	
	/**
	 * 주문 ERP 전송 등록.
	 *
	 * @param ordErpTrnsmis the OrdErpTrnsmis
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdErpTrnsmis(OrdErpTrnsmis ordErpTrnsmis) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertOrdErpTrnsmis", ordErpTrnsmis);
	}
	
	/**
	 * 주문 ERP 전송 수량단위 등록.
	 *
	 * @param ord String
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdErpTrnsmisHist(String ordNo) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertOrdErpTrnsmisHist", ordNo);
	}
	

	/**
	 * 결제 등록.
	 *
	 * @param pay the Pay
	 * @throws SQLException the SQL exception
	 */
	public void insertMypagePay(Pay pay) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertMypagePay", pay);
	}

	public void insertCancelPayForApi(MypageOrderInfoDTO mypageOrderInfoDTO) {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertCancelPayForApi", mypageOrderInfoDTO);
    }

	/**
	 * 입점사 쿠폰 분담율에 따른 업체 할인 금액 업데이트
	 *
	 * @param map
	 * @throws SQLException the SQL exception
	 */
	public void updateInfOrdGodErpDstbComputeComBndUntPrc(Map<String, String> param) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateInfOrdGodErpDstbComputeComBndUntPrc", param);
	}

	/**
	 * 입점사 주문상품 재고갱신
	 *
	 * @param stock
	 * @throws Exception
	 */
	public void updateStockPartmall(OrderStockDTO stock) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateStockPartmall", stock);
	}

	public void insertLgsDlvOvseaDmst(LgsDlv lgsDlv) {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertLgsDlvOvseaDmst", lgsDlv);
	}

	public void insertLgsDlvOvsea(LgsDlv lgsDlv) {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertLgsDlvOvsea", lgsDlv);
	}

	public void insertLgsDlivyDrctGodOvsea(LgsDlivyDrctGod lgsDlivyDrctGod) {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertLgsDlivyDrctGodOvsea", lgsDlivyDrctGod);
	}

	/**
	 * 카페 24 회원 주문 정보 수정
	 * @param ord
	 */
	public long updateOrgMbrOrd(Ord ord) {
		return getSession1().update("com.plgrim.ncp.biz.order.command.updateOrgMbrOrd", ord);
	}


	/**
	 * 카페 24 회원 주문 정보 수정 (주문 상태 배송완료 처리)
	 * @param ord
	 */
	public void updateOrgOrdStatCd(Ord ord) {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateOrgOrdStatCd", ord);
	}

	/**
	 * 카페 24 회원 주문 정보 수정
	 * @param ordGod
	 */
	public void updateOrgOrdGod(OrdGod ordGod) {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateOrgOrdGod", ordGod);
	}

	public int deleteTemporaryOrder(OrderDTO orderDTO) {
		return getSession1().update("com.plgrim.ncp.biz.order.command.deleteTemporaryOrder", orderDTO.getPay().getPayNo());
	}

	/**
	 * 회원발급쿠폰번호 생성 (배송비즉시할인쿠폰 : by cannon (2016.06.07))
	 * 
	 * @return
	 * @throws Exception
	 */
	public String genMbrIssuCpnNo() throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.command.genMbrIssuCpnNo");
	}
	
	public void setSmsRecptnSect(SmsRecptnSectDTO smsRecptnSectDTO) {
		getSession1().update("com.plgrim.ncp.biz.order.command.setSmsRecptnSect", smsRecptnSectDTO);
	}

	/**
	 * 픽업주문 배송처 변경 시 신규 픽업매장 정보로 물류배송지 정보 수정
	 *
	 * @param lgsDlvsp - 주문번호, 변경될 픽업매장ID
	 * @return
	 * @throws Exception
	 */
	public int updatePkup2pkupLgsDlvsp(LgsDlvsp lgsDlvsp) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.order.updatePkup2pkupLgsDlvsp", lgsDlvsp);
	}

	/**
	 * 픽업주문 배송처 변경 시 신규 픽업매장 정보로 물류출고지시 정보 수정
	 *
	 * @param lgsDlivyDrctGod
	 * @return
	 * @throws Exception
	 */
	public int updatePkup2pkupLgsDlvDrctGod(LgsDlivyDrctGodExtend lgsDlivyDrctGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.order.updatePkup2pkupLgsDlvDrctGod", lgsDlivyDrctGod);
	}

	/**
	 * 물류 출고지시 상품 이력 등록.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param lgsDlivyDrctGodHist [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public int insertLgsDlivyDrctGodHist(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.order.insertLgsDlivyDrctGodHist", lgsDlivyDrctGodHist);
	}

	/**
	 * 픽업주문 배송처 변경 시 '픽업번호' 등 수정
	 *
	 * @param lgsDlivyDrctGod
	 * @return
	 * @throws Exception
	 */
	public int updatePkup2pkupLgsDlv(LgsDlv lgsDlv) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.order.updatePkup2pkupLgsDlv", lgsDlv);
	}
	
	/**
	 * PAY.pay_no 를 신규 pay_no 로 교체. 결제이전 데이터만 가능(child data 생성이전)
	 * @param mypageOrderInfoDto
	 * @return
	 */
	public int updateNewPayNoByPayNo(MypageOrderInfoDTO mypageOrderInfoDto){
		return getSession1().update("com.plgrim.ncp.biz.order.command.updateNewPayNoByPayNo", mypageOrderInfoDto);
	}

	public void checkInfoInit(Clm clm) {
		getSession1().update("com.plgrim.ncp.biz.order.command.checkInfoInit", clm);
	}

	/**
	 * 주문 상품 포장서비스 등록
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param ordGodSvcDetailCnncExtend [설명]
	 * @since 2017. 8. 30
	 */
	public void insertOrdGodSvcDetailCnnc(OrdGodSvcDetailCnncExtend ordGodSvcDetailCnncExtend) {
		getSession1().insert("com.plgrim.ncp.biz.order.command.inesrtOrdGodSvcDetailCnnc", ordGodSvcDetailCnncExtend);
	}
	
	/**
	 * 구매확정 처리 - 상품단위
	 * 
	 * @param mypageOrderInfoDTO
	 */
	public void updateOrderDecision(MypageOrderInfoDTO mypageOrderInfoDTO) {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateOrderDecision", mypageOrderInfoDTO);
	}
	
	/**
	 * 본사재고처리
	 * 
	 * @param mypageOrderInfoDTO
	 */
	public void updateHoffInventoryOrderQuantity(OrdGodInv ordGodInv) {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateHoffInventoryOrderQuantity", ordGodInv);
	}
	
	/**
	 * 결품처리
	 * 
	 * @param mypageOrderInfoDTO
	 */
	public void updateShortageOrderGod(OrdGodInv ordGodInv) {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateShortageOrderGod", ordGodInv);
	}
	
	/**
	 * 주문상품 마일리지 적립 ERP전송데이터 생성
	 * @param dto
	 */
	public void insertOrdGodMileAccmlErpTrnsmis(OrdGodErpTrnsmisDTO dto) {
		getSession1().insert("com.plgrim.ncp.biz.order.command.insertOrdGodMileAccmlErpTrnsmis", dto);
	}
	
	public int updateChangeOrderClaimOrderNumber(ChangeOrderDTO changeOrderDTO) {
		return getSession1().update("com.plgrim.ncp.biz.order.command.updateChangeOrderClaimOrderNumber", changeOrderDTO);
	}
	
	public void updateChangeOrderDeliveryComplete(String ordNo) {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateChangeOrderDeliveryComplete", ordNo);
	}
	
	public void updateChangeOrderLgsDeliveryComplete(String ordNo) {
		getSession1().update("com.plgrim.ncp.biz.order.command.updateChangeOrderLgsDeliveryComplete", ordNo);
	}
	
	public void changeOrderClaimRtrvlComplete(String ordNo) {
		getSession1().update("com.plgrim.ncp.biz.order.command.changeOrderClaimRtrvlComplete", ordNo);
	}
	

	
	
}
