package com.plgrim.ncp.cmp.orderfulfilment.bo.claim;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayEscr;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.PayPgIntrlckLog;
import com.plgrim.ncp.base.entities.datasource1.pay.PayRfd;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.base.enums.OrderEnum;
import com.plgrim.ncp.biz.claim.data.RefundPayDTO;
import com.plgrim.ncp.biz.claim.exception.ClaimCompleteFailException;
import com.plgrim.ncp.biz.interfaces.data.InfOrdGodErpDstbClmSearchDTO;
import com.plgrim.ncp.biz.interfaces.service.InfOrdGodErpDstbService;
import com.plgrim.ncp.biz.order.service.OrderCommandService;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.biz.pay.service.PayBoService;
import com.plgrim.ncp.biz.pay.service.RefundPayService;
import com.plgrim.ncp.biz.promotion.data.MbrIssuCpnExtend;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.commons.data.order.KcpCancelParamDTO;
import com.plgrim.ncp.commons.data.order.KcpReturnDTO;
import com.plgrim.ncp.commons.data.order.NaverCancelReturnDTO;
import com.plgrim.ncp.commons.service.KcpPayService;
import com.plgrim.ncp.commons.service.NaverPayService;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;
import com.plgrim.ncp.interfaces.promotion.adapter.PromotionAdapter;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponUseSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class RefundPayComponentImpl extends AbstractComponent implements RefundPayComponent {
	
	@Autowired
	@Qualifier("sessionTemplate1")
	private SqlSession sqlSession1;
	
	@Autowired
	private OrderCommandService orderCommandService;
	
	@Autowired
	private OrderSelectService orderSelectService;
	
	@Autowired
	private KcpPayService kcpPayService;
	
	@Autowired
	private NaverPayService naverPayService;
	
	@Autowired
	private InfOrdGodErpDstbService infOrdGodErpDstbService;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private PayBoService payBoService;
	
	@Autowired
	private RefundPayService refundPayService;
	
	@Autowired
	private PromotionAdapter promotionAdapter;
	
	@Autowired
	private InterfaceApiCommon interfaceApiCommon;
	
	/**
	 * 클레임 결제 환불 처리
	 * 
	 * @param refundPayDTO
	 * @param ordEntity
	 * @param clm
	 * @param request
	 * @return
	 */
	@Override
	public boolean refundPayTransaction(RefundPayDTO refundPayDTO, Clm clm, boolean isRefundReprocess){
		
		boolean isRfdCompt = true;
		
		String payMnCd = refundPayDTO.getPayMnCd();
		
		try {
			String clmNo = clm.getClmNo();
			
			String rstlMsg = "";//사유 결제취소 요청후 리턴 메세지

			ObjectMapper om = new ObjectMapper();

			if("Y".equals(refundPayDTO.getMpayMnYn())){	// 주결제(PG) 환불의 경우

				if (clm.getPayExchgRtCrncySumAmt().compareTo(BigDecimal.ZERO) < 0) { // 주결제인데 환불금액이 없는경우 (추가결제의 경우) 환불로직 제외처리
					return true;
				}

				if (isRefundReprocess == false) {
					/**
					 * 원결제 데이터의 환불 누적금액 업데이트
					 */
					refundPayService.updateAddPayByPartCncl(refundPayDTO);
				}
				
				/**
				 * 환불계좌 암호화
				 */
				if(clm.getRfdBnkAcctNo()!=null && !"".equals(clm.getRfdBnkAcctNo())){

					refundPayDTO.setRfdBnkCd(clm.getRfdBnkCd());
					refundPayDTO.setRfdBnkAcctNo(clm.getRfdBnkAcctNo());
					refundPayDTO.setRfdAcnthldrNm(clm.getRfdAcnthldrNm());

					if (isRefundReprocess == false && refundPayDTO.getMbrNo()!=null && !"".equals(refundPayDTO.getMbrNo())) {
						/********************
						 * 회원 환불계좌 테이블 insert (merge into)
						 */
						this.insertMbrRfdAcc(clm, refundPayDTO.getMbrNo());
					}
				}
				
				/**
				 * 휴대폰 재결제에 의한 최종 재결제 승인번호와, 해당 재승인건의 금액을 계산하여 세팅한다.
				 *  - PG승인번호 : 최종 취소 데이터의 승인번호
				 *  - 원거래금액 : 원결제 금액 - 휴대폰 취소금액 합계(재결제로 인한 데이터)
				 *  - 원거래취소금액 : 휴대폰 환불금액 합계
				 */
				if("MOBIL_PON_PAY".equals(refundPayDTO.getPayMnCd())){

					RefundPayDTO repayInfo = refundPayService.getMobileRePayInfo(refundPayDTO.getPayNo());
					
					refundPayDTO.setPgAprvNo(repayInfo.getPgAprvNo());
					refundPayDTO.setOriStdrCrncySumAmt(repayInfo.getOriStdrCrncySumAmt());
					refundPayDTO.setOriCnclAcmtlAmt(repayInfo.getOriCnclAcmtlAmt());
				}
				
				if ("Y".equals(refundPayDTO.getEscrYn())) {
					
					PayEscr payEscr = refundPayService.selectPayEscr(refundPayDTO.getPayNo());
					refundPayDTO.setEscrStatCd(payEscr.getEscrStatCd());
					
				} else {
					// 환불 여부 확인
					refundPayDTO.setRefundYn(refundPayService.getRefundPayYn(refundPayDTO.getPayNo()));
				}
				
				if (isRefundReprocess == false) {
					/**
					 * 결제 환불 데이터 생성
					 */
					refundPayDTO.setNewPayNo(orderCommandService.selectPayNumber());
					refundPayDTO.setMpayMnYn("N"); 	// 주결제는 최초 주문시 원 결제건에 대해서만 Y로 처리한다.
					refundPayDTO.setPayTpCd("CLM");	// 결제유형코드 클레임
	
					// 거래유형코드를 클레임유형에 따라 처리.
					String dealTpCd = refundPayDTO.getDealTpCd();
					
					if("ALL_CNCL".equals(clm.getClmTpCd())){
						refundPayDTO.setDealTpCd("ALL_CNCL");
					}else{
						refundPayDTO.setDealTpCd("PART_CNCL");
					}
					
					orderCommandService.insertCancelPay(refundPayDTO);
	
					// 이후 결제 환불을 위해 원복한다.
					refundPayDTO.setDealTpCd(dealTpCd);
				}
				
				/**
				 * PG사별 환불 처리
				 */
				if(("PAY_COMPT".equals(refundPayDTO.getDealTpCd()) || "DEPST_WAIT".equals(refundPayDTO.getDealTpCd()))
						&& "N".equals(clm.getPgTrnsmisTgtYn()) == false){ //pg 연동여부가 N 이 아닌거 Y는 연동대상임
					
					try {
						
						if(Integer.parseInt(refundPayDTO.getPayCrncyPayAmt()) > 0) {
							
							// PG사에 보낼 환불사유
							try {
								refundPayDTO.setRfdResn(CodeUtil.getCode("KOR", clm.getClmResnCd()).getCdNm());
							} catch (Exception e) {	// 코드가 없을경우나 잘못된 코드가 들어왔을경우 등 오류 발생시 상세사유로 넘긴다.
								refundPayDTO.setRfdResn(clm.getClmResnCont());
							}
							
							this.refundPG(refundPayDTO);
							
							this.insertPayLogTransaction(refundPayDTO);
							
							rstlMsg = refundPayDTO.getRfdRsltmsg();
							
							/**
							 * 재승인 번호가 있는 경우 결제(취소) 데이터에 승인번호를 업데이트 처리한다. - 휴대폰 재승인 처리
							 */
							if (StringService.isNotEmpty(refundPayDTO.getTradeReauthNo())) {
								refundPayService.updateTradeReauthNo(refundPayDTO);
							}
							
							if (refundPayDTO.isRfdSuccess() == false) {
								isRfdCompt = false;
							}
							
//							if(payType.equals("ABANK") || payType.equals("VBANK")){
//								/*****************환불계좌 복호화********************/
//
//								String decryptedAccNo = rau.getDecryptRefundAccount(refundPay.getRfdBnkAcctNo(), false, clmNo);
//								/*****************환불계좌 복호화 끝********************/
//								rfdStatCd = "RFD_REQUST";
//							}
							
						}
					}catch (Throwable e) {
						log.error("",e);
						rstlMsg = "PG사 연동 실패 : "+e.getMessage();
						
						log.info("PG사 연동 실패 : "+om.writeValueAsString(refundPayDTO));
						isRfdCompt = false;
					}
				}
				
				// 환불 유형 코드를 조회
				String rfdTpCd = this.getRfdTpCd(payMnCd);
				
				if ("Y".equals(refundPayDTO.getRefundYn())) {	// PG 환불 처리시 현금 환불로 세팅한다.
    				rfdTpCd = "CASH_RFD";
    			}
				
				Date date = new Date();
				
				/*	결제 환불 테이블	*/
    			/*insertPayRfd*/
				PayRfd payRfd = new PayRfd();
				payRfd.setPayNo(refundPayDTO.getNewPayNo());

				payRfd.setRfdRequstDt(date);//DB시간으로 넣어야 하므로 비어있지 않다면 쿼리에서 sysdate 로 넣자
				payRfd.setRfdRequstResn(clm.getClmTpCd());
				if(isRfdCompt) {
					payRfd.setRfdStatCd("RFD_COMPT"); //환불 완료
					payRfd.setRfdComptDt(date);
				}else {
					payRfd.setRfdStatCd("RFD_ERR");
					payRfd.setRfdErrDt(date);
					payRfd.setRfdErrCont(rstlMsg);
				}
				payRfd.setPreRfdYn("N"); //선환불 아님
				payRfd.setRfdOccurTpCd("ORD_CLM");
				payRfd.setRfdTpCd(rfdTpCd);
				payRfd.setRegDt(date);
				payRfd.setRegtrId(clm.getRegtrId());
				payRfd.setUdtDt(date);
				payRfd.setUdterId(clm.getRegtrId());
				payRfd.setStdrCrncyRfdAmt(new BigDecimal(refundPayDTO.getStdrCrncyPayAmt()));
				
				payRfd.setExchgRtAplBegDt(refundPayDTO.getExchgRtAplBegDt());
				payRfd.setPayCrncyRfdAmt(new BigDecimal(refundPayDTO.getPayCrncyPayAmt()));
								
				payRfd.setRfdCrncyCd(refundPayDTO.getPayCrncyCd());
				
				if (isRefundReprocess == false) {
					payBoService.insertPayRfd(payRfd);
				} else {
					refundPayService.updatePayRfdStat(payRfd);
				}

				// 에스크로 상태변경 성공 시 
				if ("Y".equals(refundPayDTO.getEscrYn()) && refundPayDTO.isRfdSuccess()) {
					
					// 취소된 경우 에스크로 상태를 변경
					if (OrderClaimEnum.EscrStatCd.DLV_BF_CNCL.toString().equals(refundPayDTO.getEscrStatCd())
							|| OrderClaimEnum.EscrStatCd.DLV_AF_CNCL.toString().equals(refundPayDTO.getEscrStatCd())) {
						refundPayService.updateEscrStatCd(refundPayDTO);
					}
				}
				
			}else{//부가결제 포인트,쿠폰등
    			
        		/*쿠폰복원*/

				//상품쿠폰 적용된 해당 상품의 모든 수량이 반품되었다면 복원해준다.
				if("GOD_CPN".equals(payMnCd)){
					
					PayExtend pay = new PayExtend();
					
					pay.setOrdNo(refundPayDTO.getOrdNo());
					pay.setPayNo(refundPayDTO.getPayNo());
					pay.setMbrCpnNo(refundPayDTO.getMbrCpnNo());
					
					String mbrCpnNo = refundPayService.getRepayGodCpn(pay);
					if(null != mbrCpnNo && !"".equals(mbrCpnNo)){
						MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
						mbrIssuCpn.setMbrNo(refundPayDTO.getMbrNo());
						mbrIssuCpn.setMbrCpnNo(refundPayDTO.getMbrCpnNo());
						mbrIssuCpn.setUdterId(clm.getUdterId());
						promotionService.updateCouponRevertStatus(mbrIssuCpn);
					}
				}else if("BSK_CPN".equals(payMnCd)){
					//장바구니쿠폰 모든 주문상품이 반품되었다면 복원해준다.
					//ord_god_erp 테이블에 clm_no 가 null 인게 없으면 모두 취소된걸로 판단하고 복원해준다.
					InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
					infOrdGodErpDstbClmSearchDTO.setOrdNo(refundPayDTO.getOrdNo());
					infOrdGodErpDstbClmSearchDTO.setClmNoNullYn("Y");
					infOrdGodErpDstbClmSearchDTO.setExceptExchangeWithrawYn("Y");
					List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
					if(infOrdGodErpDstbCheckList==null || infOrdGodErpDstbCheckList.size()==0){
						
						MbrIssuCpnExtend onOffCpnInfo = refundPayService.selectRestoreOnoffCpnInfo(refundPayDTO.getMbrCpnNo());
						
						if (onOffCpnInfo == null || StringService.isEmpty(onOffCpnInfo.getCpnCrtfcCd())) {
							
							MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
							mbrIssuCpn.setClmNo(clmNo);
							mbrIssuCpn.setMbrCpnNo(refundPayDTO.getMbrCpnNo());
							mbrIssuCpn.setUdterId(clm.getUdterId());
							promotionService.updateCouponRevertStatus(mbrIssuCpn);
							
						} else {
							
							InterfaceSDO result = restoreOnOffCoupon(refundPayDTO, clmNo, onOffCpnInfo);
							
							if("200".equals(result.getResponseCd())) {
								
								MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
								mbrIssuCpn.setClmNo(clmNo);
								mbrIssuCpn.setMbrCpnNo(refundPayDTO.getMbrCpnNo());
								mbrIssuCpn.setUdterId(clm.getUdterId());
								promotionService.updateCouponRevertStatus(mbrIssuCpn);
								
							} else {
								isRfdCompt = false;
							}
						}
					}
				}else if("DLV_CST_CPN".equals(payMnCd)){
					int remainCnt = orderSelectService.selectAplPrmGodDlvFeeCnt(refundPayDTO.getOrdNo());
					if(remainCnt == 0) {
						//상품					
						//무료배송 쿠폰은 해당 배송지에 모든상품이 취소된경우 복원해준다.
						//결제정보에서 쿠폰번호가져와서 물류배송의 배송지 순번으로 해당배송지에 모든 상품이 취소나 반품되었다면 복원해준다.
						InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
						infOrdGodErpDstbClmSearchDTO.setOrdNo(refundPayDTO.getOrdNo());
						infOrdGodErpDstbClmSearchDTO.setClmNoNullYn("Y");
						infOrdGodErpDstbClmSearchDTO.setDlvCstCpnNo(refundPayDTO.getMbrCpnNo());
						//해당 쿠폰이 사용된 배송지에 상품중 ord_god_erp에 클레임번호가 없는게 있는지 조회
						List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
						//해당 배송지에 클레임 안걸린(정상주문)게 없다면 (해당배송지에 모두 클레임이 걸렸다면) 무료배송쿠폰 복원
						if(infOrdGodErpDstbCheckList==null || infOrdGodErpDstbCheckList.size()==0){
							MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
							mbrIssuCpn.setMbrNo(refundPayDTO.getMbrNo());
							mbrIssuCpn.setMbrCpnNo(refundPayDTO.getMbrCpnNo());
							mbrIssuCpn.setUdterId(clm.getUdterId());
							promotionService.updateCouponRevertStatus(mbrIssuCpn);
						}
				   }
				}
		
				if (isRefundReprocess == false) {
					/**
					 * 원결제 데이터의 환불 누적금액 업데이트
					 */
					refundPayService.updateAddPayByPartCncl(refundPayDTO);
	
					/**
					 * 결제 환불 데이터 생성
					 */
					refundPayDTO.setNewPayNo(orderCommandService.selectPayNumber());
					refundPayDTO.setMpayMnYn("N");	//주결제가 아니므로 N
					refundPayDTO.setPayTpCd("CLM");//결제유형코드 클레임
					
					if("ALL_CNCL".equals(clm.getClmTpCd())){
						refundPayDTO.setDealTpCd("ALL_CNCL");
					}else{
						refundPayDTO.setDealTpCd("PART_CNCL");
					}
	
					orderCommandService.insertCancelPay(refundPayDTO);
				}
				
				/*	결제 환불 테이블	*/
				String rfdTpCd = this.getRfdTpCd(payMnCd);
    			
				Date date = new Date();
				
				/*insertPayRfd*/
				PayRfd payRfd = new PayRfd();
				payRfd.setPayNo(refundPayDTO.getNewPayNo());
				
				payRfd.setRfdRequstDt(date);	//DB시간으로 넣어야 하므로 비어있지 않다면 쿼리에서 sysdate 로 넣자
				payRfd.setRfdRequstResn(clm.getClmTpCd());
				if(isRfdCompt) {
					payRfd.setRfdStatCd("RFD_COMPT"); //환불 완료
					payRfd.setRfdComptDt(date);
				}else {
					payRfd.setRfdStatCd("RFD_ERR");
					payRfd.setRfdErrDt(date);
					payRfd.setRfdErrCont(rstlMsg);
					log.warn("RFD ERROR : "+refundPayDTO.getNewPayNo());
				}
				payRfd.setPreRfdYn("N"); //선환불 아님
				payRfd.setRfdOccurTpCd("ORD_CLM");
				payRfd.setRfdTpCd(rfdTpCd);
				payRfd.setRegDt(date);
				payRfd.setRegtrId(clm.getRegtrId());
				payRfd.setUdtDt(date);
				payRfd.setUdterId(clm.getRegtrId());
				payRfd.setStdrCrncyRfdAmt(new BigDecimal(refundPayDTO.getStdrCrncyPayAmt()));
				
				payRfd.setRfdCrncyCd(refundPayDTO.getPayCrncyCd());
				
				payRfd.setExchgRtAplBegDt(refundPayDTO.getExchgRtAplBegDt());
				payRfd.setPayCrncyRfdAmt(new BigDecimal(refundPayDTO.getPayCrncyPayAmt()));
				
				if(!"".equals(rfdTpCd)){
					if (isRefundReprocess == false) {
						payBoService.insertPayRfd(payRfd);
					} else {
						refundPayService.updatePayRfdStat(payRfd);
					}
				}
			}
		}
		catch (ClaimCompleteFailException e1) {
			log.error("Cancel_Repay Fail" + " exception : {}", e1);
			log.error(CommonResponseCode.CL_40002_취소_환불_실패.toMessage() + " / "+e1.getDirectMessage());
			isRfdCompt = false; 
		}
		catch (Exception e2) {
			 log.error("Cancel Repay_Fail", e2);
         	 log.error(CommonResponseCode.CL_40002_취소_환불_실패.toMessage() + " exception : {}", e2.getMessage());
         	isRfdCompt = false;
		}

		return isRfdCompt;
	}

	private InterfaceSDO restoreOnOffCoupon(RefundPayDTO refundPayDTO, String clmNo, MbrIssuCpnExtend onOffCpnInfo) {
		
		// 온오프라인 복원
		AdapterHeader header = new AdapterHeader();
		
		header.setRequestId(this.interfaceApiCommon.getRequestId());
		
		try {
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(ContextService.getCurrentRequest());
			
			header.setMallId(systemPK.getMall());
			header.setLangCd("KOR");
			header.setDvcCd(systemPK.getDevice());
		} catch(Exception e) {
			header.setMallId("DXM");
			header.setDvcCd("PC");
			header.setLangCd("KOR");
		}
		
		
		OnOffCouponUseSDO sdo = new OnOffCouponUseSDO();
				
		String shopCode = "";			
		if("DXM".equals(onOffCpnInfo.getMallId())){
			shopCode = "30004";
		}else if("MBM".equals(onOffCpnInfo.getMallId())){
			shopCode = "510";
		}else if("SAM".equals(onOffCpnInfo.getMallId())){
			shopCode = "30003";
		}else{
			shopCode = "30004";
		}		
		
    	sdo.setCid(onOffCpnInfo.getErpCstmrNo());
    	sdo.setIssueNo(onOffCpnInfo.getCpnCrtfcCd());
    	sdo.setOrdNo(refundPayDTO.getOrdNo());
    	sdo.setClmNo(clmNo);    	
    	sdo.setShopcode(shopCode);
    				
		InterfaceSDO result = promotionAdapter.restoreOnOffCoupon(sdo, header);

		return result;
	}

	/**
	 * PG사별 환불처리
	 * 
	 * @param refundPayDTO
	 * @param clm
	 * @param payMnCd
	 * @param dealTpCd
	 */
	@Override
	public void refundPG(RefundPayDTO refundPayDTO) {
		if ("KCP_PAY".equals(refundPayDTO.getPgSectCd())) {

			this.refundKCP(refundPayDTO);
			
		} else if (OrderEnum.PAY_MN_CD.NAVER_PAY.toString().equals(refundPayDTO.getPayMnCd())) {
			
			this.refundNaverPay(refundPayDTO);
		}
	}

	/**
	 * 에스크로 정산 보류 처리
	 * 
	 * @param refundPayDTO
	 */
	@Override
	public void reserveCalculate(RefundPayDTO refundPayDTO) {
		
		KcpCancelParamDTO kcpCancelDTO = new KcpCancelParamDTO();
		
		kcpCancelDTO.setMod_desc(refundPayDTO.getRfdResn());
		kcpCancelDTO.setTno(refundPayDTO.getPgAprvNo());
		kcpCancelDTO.setOrdr_idxx(refundPayDTO.getPayNo());
		
		kcpCancelDTO.setMod_type(OrderClaimEnum.KcpCancel.ESCROW_DEFER_CALCULATE.getModType());
		
		this.setPgStoreInfo(refundPayDTO.getPayNo(), kcpCancelDTO);
		
		KcpReturnDTO returnDTO = kcpPayService.approveCancel(kcpCancelDTO);
		
		refundPayDTO.setRfdRsltmsg(returnDTO.getResMsg());
		
		if ("0000".equals(returnDTO.getResCd())) {
			refundPayDTO.setRfdSuccess(true);
		} else {
			refundPayDTO.setRfdSuccess(false);
		}
		
	}

	private void setPgStoreInfo(String payNo, KcpCancelParamDTO kcpCancelDTO) {
		Pay pay = new Pay();
		
		pay.setPayNo(payNo);
		pay = payBoService.selectPayByPayNo(pay); //주결제 pay 정보
		
		kcpCancelDTO.setSiteCd(pay.getPgStoreId());
		kcpCancelDTO.setSiteKey(pay.getPgCrsKey());
	}
	
	/**
	 * 네이버페이 환불처리
	 * 
	 * @param refundPayDTO
	 */
	private void refundNaverPay(RefundPayDTO refundPayDTO) {
		
		Pay pay = new Pay();
		
		pay.setPayNo(refundPayDTO.getPayNo());
		pay = payBoService.selectPayByPayNo(pay); //주결제 pay 정보
		
		NaverCancelReturnDTO naverCancelReturnDTO = naverPayService.nPayCancel(
				refundPayDTO.getPgAprvNo()
				, Long.parseLong(refundPayDTO.getPayCrncyPayAmt())
				, refundPayDTO.getRfdResn()
				, pay.getPgStoreId()
				, pay.getPgCrsKey());
		
		refundPayDTO.setNaverCancelReturnDTO(naverCancelReturnDTO);
		refundPayDTO.setRfdRsltmsg(naverCancelReturnDTO.getCode());
		
		if ("Success".equals(naverCancelReturnDTO.getCode())) {
			refundPayDTO.setRfdSuccess(true);
		} else {
			refundPayDTO.setRfdSuccess(false);
		}
	}

	/**
	 * KCP 환불처리
	 * 
	 * @param refundPayDTO
	 */
	private void refundKCP(RefundPayDTO refundPayDTO) {
		
		KcpCancelParamDTO kcpCancelDTO = new KcpCancelParamDTO();
		
		this.setPgStoreInfo(refundPayDTO.getPayNo(), kcpCancelDTO);
		
		kcpCancelDTO.setMod_desc(refundPayDTO.getRfdResn());
		kcpCancelDTO.setTno(refundPayDTO.getPgAprvNo());
		kcpCancelDTO.setOrdr_idxx(refundPayDTO.getPayNo());
		
		/****************************************************************************************************************
		 * 취소(환불) 유형별 파라메터 세팅
		 ****************************************************************************************************************/
		if ("Y".equals(refundPayDTO.getEscrYn())) {

			// 가상계좌 결제 완료된 케이스는 환불 처리를 한다.
			if ("PAY_COMPT".equals(refundPayDTO.getDealTpCd()) && "VIRTL_BNK_ACCT_PAY".equals(refundPayDTO.getPayMnCd())) {
				kcpCancelDTO.setRefund_yn("Y");
		
				kcpCancelDTO.setMod_bankcode(refundPayService.getKcpRefundBnkCd(refundPayDTO.getRfdBnkCd()));
				kcpCancelDTO.setMod_account(refundPayDTO.getRfdBnkAcctNo());
				kcpCancelDTO.setMod_depositor(refundPayDTO.getRfdAcnthldrNm());
			}
			
			if (OrderClaimEnum.EscrStatCd.ESCR_REQST.toString().equals(refundPayDTO.getEscrStatCd())) {	// 신청
				
				if ("DEPST_WAIT".equals(refundPayDTO.getDealTpCd()) && "VIRTL_BNK_ACCT_PAY".equals(refundPayDTO.getPayMnCd())) {
					
					kcpCancelDTO.setMod_type(OrderClaimEnum.KcpCancel.ESCROW_CANCEL_VIRTLBNKACCT.getModType());
					
				} else {
					
					kcpCancelDTO.setMod_type(OrderClaimEnum.KcpCancel.ESCROW_CANCEL_BEFORE_DLV.getModType());
					
				}
				
				refundPayDTO.setEscrStatCd(OrderClaimEnum.EscrStatCd.DLV_BF_CNCL.toString());
				
			} else if (OrderClaimEnum.EscrStatCd.ESCR_PCH_REJECT.toString().equals(refundPayDTO.getEscrStatCd())
					|| OrderClaimEnum.EscrStatCd.ESCR_CAL_RESRVE.toString().equals(refundPayDTO.getEscrStatCd())) { // 구매거부이거나 정산보류
				
				kcpCancelDTO.setMod_type(OrderClaimEnum.KcpCancel.ESCROW_CANCEL_AFTER_DLV.getModType());
				
				refundPayDTO.setEscrStatCd(OrderClaimEnum.EscrStatCd.DLV_AF_CNCL.toString());
				
			} else if (OrderClaimEnum.EscrStatCd.ESCR_PCH_DCSN.toString().equals(refundPayDTO.getEscrStatCd())
					|| OrderClaimEnum.EscrStatCd.ESCR_AUTO_PCH_DCSN.toString().equals(refundPayDTO.getEscrStatCd())) { // 구매확정
				
				kcpCancelDTO.setMod_type(OrderClaimEnum.KcpCancel.ESCROW_CANCEL_AFTER_CONFIRM.getModType());
				
				if ("RLTM_BNK_ACCT_PAY".equals(refundPayDTO.getPayMnCd())) {
					
					if (refundPayDTO.getOriStdrCrncySumAmt().compareTo(new BigDecimal(refundPayDTO.getPayCrncyPayAmt())) == 0) {
						
						kcpCancelDTO.setEscr_cncl_cd(OrderClaimEnum.KcpCancel.ESCROW_CANCEL_RLTMBNKACCT_AFTER_CONFIRM.getModType());
						
					} else {
						
						kcpCancelDTO.setEscr_cncl_cd(OrderClaimEnum.KcpCancel.ESCROW_PARTCANCEL_RLTMBNKACCT_AFTER_CONFIRM.getModType());
						
						// 원 결제 잔여 금액
						BigDecimal rem = refundPayDTO.getOriStdrCrncySumAmt().subtract(refundPayDTO.getOriCnclAcmtlAmt());
						
						kcpCancelDTO.setMod_mny(refundPayDTO.getPayCrncyPayAmt());
						kcpCancelDTO.setRem_mny(rem.toString());
					}
					
				} else if ("VIRTL_BNK_ACCT_PAY".equals(refundPayDTO.getPayMnCd())) {
					
					if (refundPayDTO.getOriStdrCrncySumAmt().compareTo(new BigDecimal(refundPayDTO.getPayCrncyPayAmt())) == 0) {
						
						kcpCancelDTO.setEscr_cncl_cd(OrderClaimEnum.KcpCancel.ESCROW_CANCEL_VIRTLBNKACCT_AFTER_CONFIRM.getModType());
						
						kcpCancelDTO.setMod_mny(refundPayDTO.getPayCrncyPayAmt());
						
					} else {
						
						kcpCancelDTO.setEscr_cncl_cd(OrderClaimEnum.KcpCancel.ESCROW_PARTCANCEL_VIRTLBNKACCT_AFTER_CONFIRM.getModType());
						
						// 원 결제 잔여 금액
						BigDecimal rem = refundPayDTO.getOriStdrCrncySumAmt().subtract(refundPayDTO.getOriCnclAcmtlAmt());
						
						kcpCancelDTO.setMod_mny(refundPayDTO.getPayCrncyPayAmt());
						kcpCancelDTO.setRem_mny(rem.toString());
						
					}

				}
			}
			
		} else {
			
			if ("Y".equals(refundPayDTO.getRefundYn())) {	// 환불요청 (가상계좌 입금건 + 휴대폰 이월환불건)
				
				kcpCancelDTO.setMod_bankcode(refundPayService.getKcpRefundBnkCd(refundPayDTO.getRfdBnkCd()));
				kcpCancelDTO.setMod_account(refundPayDTO.getRfdBnkAcctNo());
				kcpCancelDTO.setMod_depositor(refundPayDTO.getRfdAcnthldrNm());
				
				// 전체환불
				if (refundPayDTO.getOriStdrCrncySumAmt().compareTo(new BigDecimal(refundPayDTO.getPayCrncyPayAmt())) == 0) {
					
					kcpCancelDTO.setMod_type(OrderClaimEnum.KcpCancel.STANDARD_REFUND_PAY.getModType());
					
				// 부분환불
				} else {
					
					kcpCancelDTO.setMod_type(OrderClaimEnum.KcpCancel.STANDARD_PARTREFUND_PAY.getModType());
					
					// 원 결제 잔여 금액
					BigDecimal rem = refundPayDTO.getOriStdrCrncySumAmt().subtract(refundPayDTO.getOriCnclAcmtlAmt());
					
					kcpCancelDTO.setMod_mny(refundPayDTO.getPayCrncyPayAmt());
					kcpCancelDTO.setRem_mny(rem.toString());
				}
				
			} else {
				
				// 전체취소일 경우
				if (refundPayDTO.getOriStdrCrncySumAmt().compareTo(new BigDecimal(refundPayDTO.getPayCrncyPayAmt())) == 0) {
					
					kcpCancelDTO.setMod_type(OrderClaimEnum.KcpCancel.STANDARD_CANCEL_PAY.getModType());
					
				} else {
					// 모바일 재승인
					if ("MOBIL_PON_PAY".equals(refundPayDTO.getPayMnCd())){
						
						kcpCancelDTO.setMod_type(OrderClaimEnum.KcpCancel.MOBILPON_REPAY.getModType());	// 재승인
						
						// 취소 금액
						BigDecimal cancel = new BigDecimal(refundPayDTO.getPayCrncyPayAmt());
						// 재결제 금액 = 잔여금액 - 취소금액
						BigDecimal rePay = refundPayDTO.getOriStdrCrncySumAmt().subtract(cancel);
						
						kcpCancelDTO.setMod_mny(rePay.toString());
					// 타 결제수단 부분취소
					} else {
						
						kcpCancelDTO.setMod_type(OrderClaimEnum.KcpCancel.STANDARD_PARTCANCEL_PAY.getModType());
						
						// 원 결제 잔여 금액
						BigDecimal rem = refundPayDTO.getOriStdrCrncySumAmt().subtract(refundPayDTO.getOriCnclAcmtlAmt());
						
						kcpCancelDTO.setMod_mny(refundPayDTO.getPayCrncyPayAmt());
						kcpCancelDTO.setRem_mny(rem.toString());
						
					}
				}
			}
		}
		
		KcpReturnDTO returnDTO = kcpPayService.approveCancel(kcpCancelDTO);
		
		refundPayDTO.setKcpReturnDTO(returnDTO);
		refundPayDTO.setRfdRsltmsg(returnDTO.getResMsg());
		refundPayDTO.setTradeReauthNo(returnDTO.getTradeReauthNo());
		
		if ("0000".equals(returnDTO.getResCd())) {
			refundPayDTO.setRfdSuccess(true);
		} else {
			refundPayDTO.setRfdSuccess(false);
		}
	}
	
	/**
	 * PG 로그 등록
	 */
	private void insertPayLogTransaction(RefundPayDTO refundPayDTO) {
		try {
			PayPgIntrlckLog payLog = new PayPgIntrlckLog();
			payLog.setLogDt(new Date());
			payLog.setLogTpCd("CANCEL");
			
			if ("KCP_PAY".equals(refundPayDTO.getPgSectCd())) {
				payLog.setLogCont(refundPayDTO.getKcpReturnDTO().toJSON());
			} else if (OrderEnum.PAY_MN_CD.NAVER_PAY.toString().equals(refundPayDTO.getPgSectCd())) {
				payLog.setLogCont(refundPayDTO.getNaverCancelReturnDTO().toJSON());
			}
			
			payLog.setPayNo(refundPayDTO.getNewPayNo());
			payLog.setOrdNo(refundPayDTO.getOrdNo());
			payLog.setClmNo(refundPayDTO.getClmNo());
			payLog.setMbrNo(refundPayDTO.getMbrNo());
			payLog.setPgAprvNo(refundPayDTO.getPgAprvNo());
		
			orderCommandService.insertPayLogTransaction(payLog);
			
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
		}
	}
	
	/**
	 * 회원 환불계좌 등록
	 * 
	 * @param clm
	 * @param mbrNo
	 */
	private void insertMbrRfdAcc(Clm clm, String mbrNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("MBR_NO", mbrNo);
//		int rfdBnkAcctTurn = Integer.parseInt(idGenService.generateDBOrder(sqlSession1,"mbr_rfd_bnk_acct","rfd_bnk_acct_turn",map, DatabaseType.ORACLE)+"");
		// TODO : 회원번호가 숫자로만 되어있으면 숫자로 파싱하여 오류 발생. 명시하여 수정하자. 또한 환불계좌 사용하는지 확인하자. 필요없으면 제거..

		MbrRfdBnkAcct mbrRfdBnkAcct = new MbrRfdBnkAcct();
		mbrRfdBnkAcct.setMbrNo(mbrNo);
//		mbrRfdBnkAcct.setRfdBnkAcctTurn(rfdBnkAcctTurn);
		mbrRfdBnkAcct.setRfdBnkAcctNm("클레임 환불계좌 등록");
		mbrRfdBnkAcct.setRfdBnkAcctNo(clm.getRfdBnkAcctNo());
		mbrRfdBnkAcct.setRfdBnkAcctBnkCd(clm.getRfdBnkCd());
		mbrRfdBnkAcct.setRfdBnkAcctAcnthldrNm(clm.getRfdAcnthldrNm());
		mbrRfdBnkAcct.setRprstRfdBnkAcctYn("N");
		mbrRfdBnkAcct.setRegtrId(clm.getRegtrId());
		mbrRfdBnkAcct.setUdterId(clm.getRegtrId());

//		memberBenefitCommandService.insertMbrRfdAcc(mbrRfdBnkAcct);
	}
	
	/**
	 * 환불유형코드 조회
	 * 결제수단에 매핑하여 가져온다.
	 * 
	 * @param payMnCd
	 * @return
	 */
	private String getRfdTpCd(String payMnCd) {
		String rfdTpCd;
		if("RLTM_BNK_ACCT_PAY".equals(payMnCd) || "NON_BNKBOK_PAY".equals(payMnCd)){
			rfdTpCd = "CASH_RFD";
		}else if("CREDT_CARD_PAY".equals(payMnCd)){
			rfdTpCd = "CREDT_CARD_CNCL";
		}else if("MOBIL_PON_PAY".equals(payMnCd)){
			rfdTpCd = "MOBIL_CNCL";
		}else if("GFCT_PAY".equals(payMnCd)){
			rfdTpCd = "GFCT_RFD";
		}else if("UNITY_SAV_MNY_PAY".equals(payMnCd) || "WEB_PNT_PAY".equals(payMnCd)){
			rfdTpCd = "SAV_MNY_RFD";
		}else if("VIRTL_BNK_ACCT_PAY".equals(payMnCd)){
			rfdTpCd = "VIRTL_BNK_ACCT_CNCL";
		}else if("MBSH_PNT_PAY".equals(payMnCd)){
			rfdTpCd = "UNITY_SAV_MNY_RFD";
		}else if("EVT_PNT_PAY".equals(payMnCd)){
			rfdTpCd = "EVT_PNT_RFD";
		}else if("ZERO_PAY".equals(payMnCd)){
			rfdTpCd = "ZERO_PAY";
		}else if("WEB_PNT_PAY".equals(payMnCd)){
			rfdTpCd = "WEBPNT_RFD";
		}else{
			rfdTpCd = payMnCd;
		}
		return rfdTpCd;
	}
}
