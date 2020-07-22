package com.plgrim.ncp.biz.pay.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.PayPgIntrlckLog;
import com.plgrim.ncp.base.entities.datasource1.pay.PayRfd;
import com.plgrim.ncp.base.enums.OrderEnum;
import com.plgrim.ncp.base.repository.pay.PayRepository;
import com.plgrim.ncp.base.repository.pay.PayRfdRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.order.service.OrderCommandService;
import com.plgrim.ncp.biz.pay.data.ClmRefundResultDTO;
import com.plgrim.ncp.biz.pay.data.ClmRefundSearchDTO;
import com.plgrim.ncp.biz.pay.data.PayBoDTO;
import com.plgrim.ncp.biz.pay.repository.PayOrderRepository;
import com.plgrim.ncp.commons.data.order.KcpParamDTO;
import com.plgrim.ncp.commons.data.order.KcpReturnDTO;
import com.plgrim.ncp.commons.exception.OrderPGFailException;
import com.plgrim.ncp.commons.service.KcpPayService;
import com.plgrim.ncp.framework.enums.DatabaseType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PayBoService extends AbstractService {

	@Autowired
	PayRepository payRepository;// 결제

	@Autowired
	PayRfdRepository payRfdRepository;// 결제환불

	@Autowired
	PayOrderRepository payOrdRepository;

	@Autowired
	private KcpPayService kcpPayService;

	@Autowired
	OrderCommandService orderCommandService;
	@Autowired
	DeliverySelectRepository deliverySelectRepository;
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	public void payProcessor(PayBoDTO payBoDTO, String ordNo, String regtrId) throws Exception {

		log.info("payProcessor");

		String mallId = payBoDTO.getMallId();

		for (Pay pay : payBoDTO.getPayList()) {

			String payNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_PAY", "ST", DatabaseType.ORACLE);
			pay.setPayCrncyCd(mallId.equals("TMALL") ? "CNY" : "KRW");
			pay.setPayTpCd("ORD");
			pay.setPayNo(payNo);
			pay.setOrdNo(ordNo);
			pay.setEscrYn("N");
			pay.setMpayMnYn("Y");
			pay.setPayDt(new Date());
			pay.setPartCnclPsbYn("Y");
			pay.setCardSvDealYn("N");
			pay.setRegtrId(regtrId);
			pay.setUdterId(regtrId);
			payRepository.insertPay(pay);
		}
	}

	public void payKcpProcessor(PayBoDTO payBoDTO, KcpParamDTO kcpDTO, Long payAmt, String ordNo, String regtrId)
			throws Exception {

		log.info("payProcessor");

		String mallId = payBoDTO.getMallId();
		if (!"MBM".equals(mallId)) {
			throw new OrderPGFailException("MLB 전용 결제입니다");
		}

		Mbr mbr = payBoDTO.getMbr();
        boolean vip = false;  
		String cstrno = deliverySelectRepository.getVipList();
		if (mbr != null && mbr.getErpCstmrNo() != null && cstrno.indexOf(mbr.getErpCstmrNo()) != -1) {
			vip = true;
		}

		KcpReturnDTO rkcpDTO = kcpPayService.approve(kcpDTO, payAmt, mallId, vip);
		Date payDt = new Date();

		// PG LOG
		try {
			PayPgIntrlckLog payLog = new PayPgIntrlckLog();
			payLog.setLogDt(payDt);
			payLog.setLogTpCd("APPROVAL");
			payLog.setLogCont(rkcpDTO.toJSON());
			payLog.setPayNo(kcpDTO.getOrdr_idxx());
			payLog.setOrdNo(ordNo);
			// payLog.setClmNo(String clmNo);
			payLog.setMbrNo(regtrId);
			payLog.setPgAprvNo(rkcpDTO.getTno());

			orderCommandService.insertPayLogTransaction(payLog);
		} catch (Exception e) {
			log.warn(">>>>>>>>>>>>>>>>>>> PAY LOG ERROR <<<<<<<<<<<<<<<<<<<<<<");
			log.warn(">>>>>>>>>>>>>>>>>>> ORD_NO : " + ordNo);
			log.warn(">>>>>>>>>>>>>>>>>>> PAY_NO : " + kcpDTO.getOrdr_idxx());
		}
		if (!"0000".equals(rkcpDTO.getResCd())) {
			log.warn(rkcpDTO.getResMsg());
			throw new OrderPGFailException(rkcpDTO.getResMsg());
		}

		for (Pay pay : payBoDTO.getPayList()) {

			String payNo = kcpDTO.getOrdr_idxx();
			pay.setPayCrncyCd("KRW");
			pay.setPayTpCd("ORD");
			pay.setPayNo(payNo);
			pay.setOrdNo(ordNo);
			pay.setEscrYn("N");
			pay.setMpayMnYn("Y");
			pay.setPayDt(payDt);
			pay.setPartCnclPsbYn("Y");
			pay.setCardSvDealYn("N");
			pay.setRegtrId(regtrId);
			pay.setUdterId(regtrId);

			pay.setPgAprvNo(rkcpDTO.getTno());

			pay.setDealTpCd(OrderEnum.ORD_STAT_DEPST_WAIT.toString());
			pay.setPayMnCd(OrderEnum.kcpPayComp.VIRTL_BNK_ACCT_PAY.getLocalValue());

			pay.setBnkCd(rkcpDTO.getBankCode());
			pay.setBnkNm(rkcpDTO.getBankName());

			pay.setAcnthldrNm(rkcpDTO.getDepositor());
			pay.setBnkAcctNo(rkcpDTO.getAccount());

			if (rkcpDTO.getVaDate() != null && rkcpDTO.getVaDate().length() > 0) {
				SimpleDateFormat kcpTimeSdf = new SimpleDateFormat("yyyyMMddHHmmss");
				pay.setPayTmlmtDt(kcpTimeSdf.parse(rkcpDTO.getVaDate()));
			}
			pay.setPgSectCd("KCP_PAY");

			pay.setPgStoreId(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.vip.cd"));
			pay.setPgCrsKey(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.vip.key"));

			pay.setCardSvDealYn("N"); // 카드세이브거래

			pay.setEscrYn("N");

			payRepository.insertPay(pay);
		}
	}

	/**
	 * 주문번호로 해당 주문의 결제정보를 가져온다.
	 * 
	 * @param ordNo
	 * @return
	 */
	public Pay getOrdPayInfo(String ordNo) throws Exception {
		return payOrdRepository.getOrdPayInfo(ordNo);
	}

	/**
	 * 결제정보의 거래유형 코드를 변경한다.
	 * 
	 * @param updatePay
	 * @throws Exception
	 */
	public void updatePayStatCd(Pay updatePay) throws Exception {
		payRepository.updatePay(updatePay);
	}

	public void insertPayRfd(PayRfd payRfd) throws Exception {
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("PAY_NO", payRfd.getPayNo());
		int maxTurn = getIdGenService().generateDBOrder(sqlSession1, "PAY_RFD", "RFD_TURN", conditions,
				DatabaseType.ORACLE);
		payRfd.setRfdTurn(maxTurn);
		payRfdRepository.insertPayRfd(payRfd);
	}

	/** 입금확정완료 */
	public int confirmDepositPay(Pay pay) throws Exception {
		return payOrdRepository.confirmDepositPay(pay);
	}

	/**
	 * 클레임 환불금액 계산
	 * 
	 * @return
	 */
	public List<ClmRefundResultDTO> selectInfOrdGodErpDstbForClmRefund(ClmRefundSearchDTO clmRefundSearchDTO) {
		// TODO Auto-generated method stub
		return payOrdRepository.selectInfOrdGodErpDstbForClmRefund(clmRefundSearchDTO);
	}

	public List<Pay> selectOrdClmPay(Pay pay) {
		return payOrdRepository.selectOrdClmPay(pay);
	}

	public List<PayExtend> selectOrdClmPayForErp(Pay pay) {
		return payOrdRepository.selectOrdClmPayForErp(pay);
	}

	public List<PayExtend> selectOrdClmPayExtend(PayExtend payExtend) {
		return payOrdRepository.selectOrdClmPayExtend(payExtend);
	}

	public List<PayExtend> selectOrdClmRefundPayExtend(Pay pay) {
		return payOrdRepository.selectOrdClmRefundPayExtend(pay);
	}

	public Pay selectPayByPayNo(Pay pay) {
		return payRepository.selectPay(pay);
	}

	/**
	 * 주문 - 교환접수시 발생하는 결제정보 조회.
	 * 
	 * @param ordNo
	 * @return
	 */
	public String selectUpperPayNo(Pay pay) throws Exception {
		return payOrdRepository.selectUpperPayNo(pay);
	}

	/**
	 * 주문 - 교환접수시 발생하는 결제정보를 저장.
	 * 
	 * @param ordNo
	 * @return
	 */
	public void payProcessorForClm(Pay pay) throws Exception {
		payRepository.insertPay(pay);
	}

	public List<ClmRefundResultDTO> getClmRefundInfoList(String clmNo) {
		return payOrdRepository.getClmRefundInfoList(clmNo);
	}

	public void updatePayForCancel(Pay updateCnclPay) {
		payOrdRepository.updatePayForCancel(updateCnclPay);
	}

	public void updatePayForClmWthdrawDlvFee(Pay searchPay) {
		payOrdRepository.updatePayForClmWthdrawDlvFee(searchPay);
	}

	public void insertPayForClmWthdrawDlvFee(Pay searchPay) {
		payOrdRepository.insertPayForClmWthdrawDlvFee(searchPay);
	}

	public int selectbeforePartCancelCnt(Pay beforeSearchPay) {
		return payOrdRepository.selectbeforePartCancelCnt(beforeSearchPay);
	}

	public int selectbeforePartCancelCnt2(Pay beforeSearchPay) {
		return payOrdRepository.selectbeforePartCancelCnt2(beforeSearchPay);
	}

}
