package com.plgrim.ncp.cmp.orderfulfilment.batch.claim;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.inf.InfMail;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpTrnsmis;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.PayRfd;
import com.plgrim.ncp.base.enums.EMailEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.base.enums.OrderEnum;
import com.plgrim.ncp.base.enums.WebPointEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobDetail;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefSectCd;
import com.plgrim.ncp.biz.claim.data.ClaimBatchSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClaimBatchTargetDTO;
import com.plgrim.ncp.biz.claim.data.ClmErpTrnsmis;
import com.plgrim.ncp.biz.claim.exception.ClaimAlreadyExistException;
import com.plgrim.ncp.biz.claim.exception.ClaimCompleteFailException;
import com.plgrim.ncp.biz.claim.result.ClaimRefundVirtualCdResult;
import com.plgrim.ncp.biz.claim.result.ClaimReturnResult;
import com.plgrim.ncp.biz.claim.service.ClaimBatchService;
import com.plgrim.ncp.biz.claim.service.ClaimService;
import com.plgrim.ncp.biz.delivery.service.DeliveryGlobalAffService;
import com.plgrim.ncp.biz.delivery.service.DeliveryInfErpExternalService;
import com.plgrim.ncp.biz.delivery.service.DeliveryInfErpService;
import com.plgrim.ncp.biz.delivery.service.DeliveryService;
import com.plgrim.ncp.biz.delivery.service.DeliveryStatusService;
import com.plgrim.ncp.biz.interfaces.data.InfOrdGodErpDstbClmSearchDTO;
import com.plgrim.ncp.biz.interfaces.service.InfOrdGodErpDstbService;
import com.plgrim.ncp.biz.member.service.MemberActivityCommandService;
import com.plgrim.ncp.biz.member.service.MemberActivitySelectService;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitCommandService;
import com.plgrim.ncp.biz.member.service.MemberBenefitSelectService;
import com.plgrim.ncp.biz.member.service.MemberOrderCommandService;
import com.plgrim.ncp.biz.member.service.MemberOrderSelectService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoSelectService;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.service.OrderBoSelectService;
import com.plgrim.ncp.biz.order.service.OrderBoService;
import com.plgrim.ncp.biz.order.service.OrderCommandService;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.biz.pay.service.PayBoService;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.commons.service.SysBrandService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClaimBatchComponentImpl extends AbstractComponent implements ClaimBatchComponent {
 
    @Autowired
    ClaimService claimService;
    
    @Autowired
    ClaimBatchService claimBatchService;

    @Autowired
    DeliveryStatusService deliveryStatusService;

    @Autowired
    DeliveryInfErpService deliveryInfErpService;

    @Autowired
    DeliveryInfErpExternalService deliveryInfErpExternalService;

    @Autowired
    InfOrdGodErpDstbService infOrdGodErpDstbService;

    @Autowired
    OrderBoService orderBoService;
    
    @Autowired
    OrderSelectService orderSelectService;
    
    @Autowired
    OrderCommandService orderCommandService;   

    @Autowired
    OrderBoSelectService orderBoSelectService;

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    PayBoService payBoService;

    @Autowired
    @Qualifier("sessionTemplate1")
    private SqlSession sqlSession1;
	
    @Autowired
    private PromotionService promotionService;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;
    
    @Autowired
    private DeliveryGlobalAffService deliveryGlobalAffService;
	
	@Autowired
	IdGenService idGenService;

    @Autowired
    private SysBrandService sysBrandService;

	@Autowired
	MemberPersonalInfoSelectService memberPersonalInfoSelectService;
	
	@Autowired
	MemberPersonalInfoCommandService memberPersonalInfoCommandService;
	
	@Autowired
	MemberActivitySelectService memberActivitySelectService;
	
	@Autowired
	MemberActivityCommandService memberActivityCommandService;
	
	@Autowired
	MemberBaseSelectService memberBaseSelectService;
	
	@Autowired
	MemberBaseCommandService memberBaseCommandService;
	
	@Autowired
	MemberBenefitSelectService memberBenefitSelectService;
	
	@Autowired
	MemberBenefitCommandService memberBenefitCommandService;
	
	@Autowired
	MemberOrderSelectService memberOrderSelectService;
	
	@Autowired
	MemberOrderCommandService memberOrderCommandService;

	/**
     * 반품/교환 클레임 완료배치 > 반품 환불로직
     * @param clmNo
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
    public String createPayAndRfd(String clmNo) throws Exception {
    	
    	
    	String result="한거없음 : "+clmNo;
    	
    	PayExtend checkPay = new PayExtend();
    	checkPay.setClmNo(clmNo);
    	List<PayExtend> checkPayList = payBoService.selectOrdClmPayExtend(checkPay);
    	
    	if(checkPayList!=null && checkPayList.size()!=0){//기존에 해당 클레임으로 pay 테이블에 insert 되어있다면 환불로직을 타지 않는다.
    		log.info("이미 pay 테이블에 insert 된 클레임입니다. clmNo : "+clmNo);
    	}else{
    		//클레임번호로 pay 테이블에 insert 되어있지 않다면 환불로직을 탄다.
        	boolean refundChk = false;
        	PayExtend pay = new PayExtend();
        	Clm clm = new Clm();
        	clm.setClmNo(clmNo);
        	
        	clm = claimBatchService.selectClmByClmNo(clm);
        	
        	Ord ord = orderSelectService.selectOrdEntity(clm.getOrdNo());
        	
        	pay.setOrdNo(clm.getOrdNo());//주문번호로
        	pay.setPayTpCd("ORD");//주문시 생성된
        	pay.setDealTpCd("PAY_COMPT");//결제완료된
        	List<PayExtend> ordPayList = payBoService.selectOrdClmPayExtend(pay);
        	
        	int errorCnt = 0;
        	HashMap<String, String> map = null;
        	for(PayExtend payDto : ordPayList){
        	
        		map = createPayAndRfdTransaction(payDto,ord,clm);
        	
        		errorCnt += Integer.parseInt(map.get("cnt"));
        		if("true".equals(map.get("refundChk"))){
        			refundChk = true;
        		}
        	}
        	
        	if(errorCnt!=0){
        		result= "실행실패 : "+clmNo;
        	}else{
        		
    			//클레임 완료처리
    			clm.setClmStatCd("COMPT");
    			
    			claimService.updateClmStatCd(clm);
    			result= "실행완료 : "+clmNo;		
        	}
    	}
    	return result;

    	
    }
    
    
    
    
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
    public HashMap<String, String> createPayAndRfdTransaction(PayExtend payDto ,Ord ord, Clm clm){
    	int cnt = 0;
    	boolean refundChk = false;
    	try {
        	String clmNo = clm.getClmNo();
    		String rfdStatCdOri = "";//환불상태코드 결제취소 요청 후 리턴 코드
    		String rfdStatCd = "RFD_COMPT";//환불상태코드 결제취소 요청 후 리턴 코드 (결제환불의 코드) pg 미연동 고려하여 초기값을 환불완료로 한다.
    		String rfdResn = "";//사유 결제취소 요청후 리턴 메세지
    		/*ㅁ. 거래유형 : DEAL_TP
    		   >. 결제 대기 : PAY_WAIT
    		   >. 입금 대기 : DEPST_WAIT
    		   >. 결제 완료 : PAY_COMPT
    		   >. 전체 취소 : ALL_CNCL
    		   >. 부분 취소 : PART_CNCL
    		 * */
    		String dealTpCd = "";	//거래유형코드 
    		String refundYn="";	//모빌리언 주문시 결제월체크하여 환불여부 확인
    		Date date = new Date();  

        	//HttpServletRequest request = getCurrentRequest();
        	ObjectMapper om = new ObjectMapper(); 
    		
    		if("Y".equals(payDto.getMpayMnYn())){//주결제
    			//ordMainPay = payDto;
    			
    			
    			/*DB처리*/
    	    	MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();
    	    	
    	    	// BO에서 결제한 정보를 가져온다
    	    	mypageOrderInfoDTO.setOrdNo(ord.getOrdNo());
    	    	mypageOrderInfoDTO.setMbrNo(ord.getMbrNo());
    	    	
    	    	mypageOrderInfoDTO = orderSelectService.selectPayMethodChangeClm(mypageOrderInfoDTO);//주결제 정보
    	    	
    	    	if(mypageOrderInfoDTO==null){
    	    	
    	    		throw new ClaimAlreadyExistException("환불로직 처리중 주문번호 : "+ord.getOrdNo()+" 에 메인 결제가 없습니다.");    	    		
    	    	}
    	    	
    	    	dealTpCd=mypageOrderInfoDTO.getDealTpCd();
    	    	refundYn=mypageOrderInfoDTO.getRefundYn();
    	    	
    			// 기존 원주문 payNo
    	    	// pay 취소건 취소누적금액 update
    	    	mypageOrderInfoDTO.setMpayMnYn("Y");	
    	    	mypageOrderInfoDTO.setCnclAcmtlAmt(clm.getPayExchgRtCrncySumAmt().intValue()+"");
    	    	
    	    	orderCommandService.updateCancelPay(mypageOrderInfoDTO);
    	    	
    			//  pay 취소정보 insert
    			//  payNo 번호 따와서 넣어야함    	
    	    	
    			mypageOrderInfoDTO.setNewPayNo(orderCommandService.selectPayNumber());
    			
    			mypageOrderInfoDTO.setMpayMnYn("N");	//정산 요청으로 클레임으로 들어가는 결제정보중 주결제도 MpayMnYn 을 Y 로 넣는다. 는 원복
    			mypageOrderInfoDTO.setPayTpCd("CLM");//결제유형코드 클레임
    			mypageOrderInfoDTO.setClmNo(clmNo);
    			mypageOrderInfoDTO.setRegtrId("PASSIVE");
    			mypageOrderInfoDTO.setRegDt(date);
    			
    			if("ALL_CNCL".equals(clm.getClmTpCd())){
    				mypageOrderInfoDTO.setDealTpCd("ALL_CNCL");	
    			}else{
    				mypageOrderInfoDTO.setDealTpCd("PART_CNCL");
    			}
    			
    			mypageOrderInfoDTO.setStdrCrncyPayAmt(clm.getStdrCrncySumAmt().intValue()+"");
    			mypageOrderInfoDTO.setPayCrncyPayAmt(clm.getPayExchgRtCrncySumAmt().intValue()+"");
    			if(clm.getRfdBnkAcctNo()!=null &&!"".equals(clm.getRfdBnkAcctNo()) && ord.getMbrNo() != null){

    				mypageOrderInfoDTO.setRfdBnkCd(clm.getRfdBnkCd());
    				//mypageOrderInfoDTO.setRfdBnkAcctNo(encryptedRfdAccNo);
    				mypageOrderInfoDTO.setRfdBnkAcctNo(clm.getRfdBnkAcctNo());
    				mypageOrderInfoDTO.setRfdAcnthldrNm(clm.getRfdAcnthldrNm());
    				
    				/********************
    				 * 회원 환불계좌 테이블 insert (merge into)
    				 */
    				
    				Map<String, Object> map = new HashMap<String, Object>();		
    				map.put("MBR_NO", ord.getMbrNo());		
    				
    				int rfdBnkAcctTurn = Integer.parseInt(idGenService.generateDBOrder(sqlSession1,"mbr_rfd_bnk_acct","rfd_bnk_acct_turn",map, DatabaseType.ORACLE)+"");
    				
    				MbrRfdBnkAcct mbrRfdBnkAcct = new MbrRfdBnkAcct();
    				mbrRfdBnkAcct.setMbrNo(ord.getMbrNo());
    				mbrRfdBnkAcct.setRfdBnkAcctTurn(rfdBnkAcctTurn);
    				mbrRfdBnkAcct.setRfdBnkAcctNm("클레임 환불계좌 등록");
    				mbrRfdBnkAcct.setRfdBnkAcctNo(clm.getRfdBnkAcctNo());
    				mbrRfdBnkAcct.setRfdBnkAcctBnkCd(clm.getRfdBnkCd());
    				mbrRfdBnkAcct.setRfdBnkAcctAcnthldrNm(clm.getRfdAcnthldrNm());
    				mbrRfdBnkAcct.setRprstRfdBnkAcctYn("N");
    				mbrRfdBnkAcct.setRegtrId(clm.getRegtrId());
    				mbrRfdBnkAcct.setUdterId(clm.getRegtrId());
    				
    				memberBenefitCommandService.insertMbrRfdAcc(mbrRfdBnkAcct);
    				
    			}
    			
    			//orderCommandService.insertCancelPay(mypageOrderInfoDTO);    
    			
    			orderCommandService.insertCancelPayForApi(mypageOrderInfoDTO);
    			
    			Pay searchPay =  new Pay();
    			searchPay.setPayNo(mypageOrderInfoDTO.getNewPayNo());
    			
    			Pay refundPay = payBoService.selectPayByPayNo(searchPay); //주결제 pay 정보
    			
    			searchPay =  new Pay();
    			searchPay.setPayNo(mypageOrderInfoDTO.getPayNo());
    			
    			Pay ordPay = payBoService.selectPayByPayNo(searchPay); //주결제 pay 정보
    			
    			
    			


    			
    			if("PAY_COMPT".equals(dealTpCd)){//제휴사 주문이 아니고 결제 완료이면
        	    	try {
        	    		rfdStatCd = "RFD_COMPT";
        	    	}catch (Exception e) {
        	        	log.error("",e);
        	        	log.info("aaaaaaaaaaaaaaaaaaaaaaaaaa50");
        	        	//주결제 모듈 연동실패
        	        	//주결제 환불모듈에 연결 실패하였습니다.
        				rfdStatCd = "RFD_ERR";//환불상태코드 결제취소 요청 후 리턴 코드
        				rfdResn = "PG사 연동 실패 : "+e.getMessage();//사유 결제취소 요청후 리턴 메세지
        				//throw new Exception("PG사 연동 실패");    				
        				log.info("PG사 연동 실패 : "+om.writeValueAsString(payDto));
        				cnt++;
        	        }
    			}
    			
    			
    			
    			/*	결제 환불 테이블	*/
    			String payMn = refundPay.getPayMnCd();
    			String rfdTpCd = "";
    			if("RLTM_BNK_ACCT_PAY".equals(payMn) || "NON_BNKBOK_PAY".equals(payMn)){
    				rfdTpCd = "CASH_RFD";
    			}else if("CREDT_CARD_PAY".equals(payMn)){
    				rfdTpCd = "CREDT_CARD_CNCL";
    			}else if("MOBIL_PON_PAY".equals(payMn)){
    				rfdTpCd = "MOBIL_CNCL";
    			}else if("GFCT_PAY".equals(payMn)){
    				rfdTpCd = "GFCT_RFD";
    			}else if("MBSH_PNT_PAY".equals(payMn) || "WEB_PNT_PAY".equals(payMn)){
    				rfdTpCd = "SAV_MNY_RFD";
    			}else if("VIRTL_BNK_ACCT_PAY".equals(payMn)){
    				rfdTpCd = "VIRTL_BNK_ACCT_CNCL";
    			}else if("ALIPAY".equals(payMn)){
    				rfdTpCd = "ALIPAY";
    			}else{
    				rfdTpCd = payMn;
    			}

    			/*insertPayRfd*/
    			PayRfd payRfd = new PayRfd();
    			payRfd.setPayNo(mypageOrderInfoDTO.getNewPayNo());
    			payRfd.setRfdStatCd(rfdStatCd); //환불 완료
    			 
    			payRfd.setRfdRequstDt(date);//DB시간으로 넣어야 하므로 비어있지 않다면 쿼리에서 sysdate 로 넣자
    			payRfd.setRfdRequstResn(clm.getClmTpCd());
    			if("RFD_COMPT".equals(rfdStatCd)){
    				payRfd.setRfdComptDt(date);
    			}else if("RFD_ERR".equals(rfdStatCd)){
    				payRfd.setRfdErrDt(date);
    				payRfd.setRfdErrCont(rfdResn);					
    			}
    			payRfd.setPreRfdYn("N"); //선환불 아님  
    			payRfd.setRfdOccurTpCd("ORD_CLM");
    			payRfd.setRfdTpCd(rfdTpCd);
    			payRfd.setRegDt(date);
    			payRfd.setRegtrId("PASSIVE");
    			payRfd.setUdtDt(date);
    			payRfd.setUdterId("PASSIVE");
    			payRfd.setStdrCrncyRfdAmt(refundPay.getStdrCrncyPayAmt());
    			//payRfd.setRfdAmt(refundPay.getPayCrncyPayAmt());
    			payRfd.setRfdCrncyCd(refundPay.getPayCrncyCd());
    			
    			payBoService.insertPayRfd(payRfd);
    			
    		}else{//부가결제 포인트,쿠폰등
    			
//부가결제 포인트,쿠폰등
    			
    			
    			boolean rfdCheck = false;
    			
        		/*쿠폰복원*/
        		
        		//상품쿠폰 적용된 해당 상품의 모든 수량이 반품되었다면 복원해준다.
        		//claimBatchService.selectGodCpnRepay()
        		if("GOD_CPN".equals(payDto.getPayMnCd())){
        			
        			String mbrCpnNo = claimBatchService.getRepayGodCpn(payDto);
        			
        			if(null != mbrCpnNo && !"".equals(mbrCpnNo)){
        				/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
        	        	MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
        	        	mbrIssuCpn.setMbrNo(ord.getMbrNo());    	        	
        	        	mbrIssuCpn.setMbrCpnNo(payDto.getMbrCpnNo());
        	        	mbrIssuCpn.setUdterId(clm.getUdterId());
        	        
        	        	promotionService.updateCouponRevertStatus(mbrIssuCpn);
        	        
        	        	/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
        	        	rfdCheck = true;
        			}
        		}else if("BSK_CPN".equals(payDto.getPayMnCd())){
            		//장바구니쿠폰 모든 주문상품이 반품되었다면 복원해준다. 
            		//ord_god_erp 테이블에 clm_no 가 null 인게 없으면 모두 취소된걸로 판단하고 복원해준다.
        		    InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
        		    infOrdGodErpDstbClmSearchDTO.setOrdNo(payDto.getOrdNo());
        		    infOrdGodErpDstbClmSearchDTO.setClmNoNullYn("Y");
        		    infOrdGodErpDstbClmSearchDTO.setExceptExchangeWithrawYn("Y");
        		    
        		    List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
        		    
            		if(infOrdGodErpDstbCheckList==null || infOrdGodErpDstbCheckList.size()==0){
        				/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
        	        	MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
        	        	//mbrIssuCpn.setMbrNo(ord.getMbrNo());    	        	
        	        	mbrIssuCpn.setClmNo(clmNo);
        	        	mbrIssuCpn.setMbrCpnNo(payDto.getMbrCpnNo());
        	        	mbrIssuCpn.setUdterId(clm.getUdterId());    	 
        	        	
        	        	promotionService.updateCouponRevertStatus(mbrIssuCpn);
        	        	
        	        	/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
        	        	rfdCheck = true;
            		}    			
        		}else if("DLV_CST_CPN".equals(payDto.getPayMnCd()) || "QDLV_CST_CPN".equals(payDto.getPayMnCd())){
        			//무료배송 쿠폰은 해당 배송지에 모든상품이 취소된경우 복원해준다.
        			//결제정보에서 쿠폰번호가져와서 물류배송의 배송지 순번으로 해당배송지에 모든 상품이 취소나 반품되었다면 복원해준다.
        		    InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
        		    infOrdGodErpDstbClmSearchDTO.setOrdNo(payDto.getOrdNo());
        		    infOrdGodErpDstbClmSearchDTO.setClmNoNullYn("Y");
        		    infOrdGodErpDstbClmSearchDTO.setDlvCstCpnNo(payDto.getMbrCpnNo());
        		    //해당 쿠폰이 사용된 배송지에 상품중 ord_god_erp에 클레임번호가 없는게 있는지 조회
        		    
        		    List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
        		    
        		    //해당 배송지에 클레임 안걸린(정상주문)게 없다면 (해당배송지에 모두 클레임이 걸렸다면) 무료배송쿠폰 복원
            		if(infOrdGodErpDstbCheckList==null || infOrdGodErpDstbCheckList.size()==0){
        				/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
        	        	MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
        	        	mbrIssuCpn.setMbrNo(ord.getMbrNo());    	        	
        	        	mbrIssuCpn.setMbrCpnNo(payDto.getMbrCpnNo());
        	        	mbrIssuCpn.setUdterId(clm.getUdterId());    
        	        
        	        	promotionService.updateCouponRevertStatus(mbrIssuCpn);
        	        
        	        	/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
        	        	rfdCheck = true;
            		}    			
        		}else{
        			rfdCheck = true;
        		}
        		
        		//무료배송쿠폰은 반품이라 복원할 필요 없음 (이미 받을때 사용)
    			
        		
        		if(rfdCheck){
        			int refundServAmt=0;
        			
        			if("MBSH_PNT_PAY".equals(payDto.getPayMnCd())){//1차오픈에서는 포인트 유형은 멤버쉽만 있음
        				refundServAmt = clm.getUnityPntUseSumAmt().intValue();
        			}else if("GOD_CPN".equals(payDto.getPayMnCd())){//상품쿠폰
        				refundServAmt = clm.getGodCpnDcSumAmt().intValue();    				
        			}else if("BSK_CPN".equals(payDto.getPayMnCd())){//장바구니쿠폰
        				refundServAmt = clm.getBskCpnDcSumAmt().intValue();    				
        			}else if("DLV_CST_CPN".equals(payDto.getPayMnCd())){//배송비쿠폰
        				refundServAmt = clm.getDlvCstCpnDcSumAmt().intValue();    				
        			}else if("MBSH_IMDTL_PNT_PAY".equals(payDto.getPayMnCd())){//즉시할인
        				refundServAmt = clm.getImdtlDcSumAmt().intValue();
        			}else if("QDLV_CST_CPN".equals(payDto.getPayMnCd())){//퀵배송비쿠폰
        				refundServAmt = clm.getDlvCstCpnDcSumAmt().intValue();    				
        			}
        			

        				
    				//원주문 취소 누적금액 누적
    				BigDecimal bd = new BigDecimal(refundServAmt);
    				payDto.setStdrCrncyPayAmt(bd);
    				payDto.setPayCrncyPayAmt(bd);  
    				payDto.setUdterId("PASSIVE");    	
    				
        			claimBatchService.updateOrdPayCancelAcclAmt(payDto);
        			
        			//결제취소로 결제row insert
        			
    				payDto.setNewPayNo(orderCommandService.selectPayNumber());
    				
    				payDto.setMpayMnYn("N");	//주결제가 아니므로 N 
    				payDto.setPayTpCd("CLM");//결제유형코드 클레임
    				payDto.setClmNo(clmNo);
    				payDto.setRegtrId("PASSIVE");
    				payDto.setRegDt(date);
        			if("ALL_CNCL".equals(clm.getClmTpCd())){
        				payDto.setDealTpCd("ALL_CNCL");	
        			}else{
        				payDto.setDealTpCd("PART_CNCL");
        			}    				
        			
        			claimBatchService.insertClmPay(payDto);

        			
    				/*	결제 환불 테이블	*/
    				String payMn = payDto.getPayMnCd();
    				String rfdTpCd = "";
        			if("RLTM_BNK_ACCT_PAY".equals(payMn) || "NON_BNKBOK_PAY".equals(payMn)){
        				rfdTpCd = "CASH_RFD";
        			}else if("CREDT_CARD_PAY".equals(payMn)){
        				rfdTpCd = "CREDT_CARD_CNCL";
        			}else if("MOBIL_PON_PAY".equals(payMn)){
        				rfdTpCd = "MOBIL_CNCL";
        			}else if("GFCT_PAY".equals(payMn)){
        				rfdTpCd = "GFCT_RFD";
        			}else if("UNITY_SAV_MNY_PAY".equals(payMn) || "WEB_PNT_PAY".equals(payMn)){
        				rfdTpCd = "SAV_MNY_RFD";
        			}else if("VIRTL_BNK_ACCT_PAY".equals(payMn)){
        				rfdTpCd = "VIRTL_BNK_ACCT_CNCL";
        			}else if("MBSH_PNT_PAY".equals(payMn)){
        				rfdTpCd = "UNITY_SAV_MNY_RFD";
        			}else if("EVT_PNT_PAY".equals(payMn)){
        				rfdTpCd = "EVT_PNT_RFD";
        			}else if("ZERO_PAY".equals(payMn)){
        				rfdTpCd = "ZERO_PAY";
        			}else if("MBSH_IMDTL_PNT_PAY".equals(payMn)){
        				rfdTpCd = "MBSH_IMDTL_PNT_PAY";
        			}else{
        				rfdTpCd = payMn;
        			}
        			
    				/*insertPayRfd*/
    				PayRfd payRfd = new PayRfd();
    				payRfd.setPayNo(payDto.getNewPayNo());
    				payRfd.setRfdStatCd("RFD_COMPT"); //환불 완료 (포인트 실제 erp 전송은 반품erp전송 배치에서 한다.)
    				payRfd.setRfdRequstDt(date);//DB시간으로 넣어야 하므로 비어있지 않다면 쿼리에서 sysdate 로 넣자
    				payRfd.setRfdRequstResn(clm.getClmTpCd());
    				if("RFD_COMPT".equals(rfdStatCd)){
    					payRfd.setRfdComptDt(date);
    				}else if("RFD_ERR".equals(rfdStatCd)){
    					payRfd.setRfdErrDt(date);
    					payRfd.setRfdErrCont(rfdResn);		
    					cnt++;
    					log.warn("RFD ERROR : "+payDto.getNewPayNo());
    				}
    				payRfd.setPreRfdYn("N"); //선환불 아님  
    				payRfd.setRfdOccurTpCd("ORD_CLM");
    				payRfd.setRfdTpCd(rfdTpCd);
    				payRfd.setRegDt(date);
    				payRfd.setRegtrId("PASSIVE");
    				payRfd.setUdtDt(date);
    				payRfd.setUdterId("PASSIVE");
    				payRfd.setStdrCrncyRfdAmt(payDto.getStdrCrncyPayAmt());
        			//payRfd.setRfdAmt(payDto.getPayCrncyPayAmt());
        			payRfd.setRfdCrncyCd(payDto.getPayCrncyCd());
        			if(!"".equals(rfdTpCd)){
        				
        				payBoService.insertPayRfd(payRfd);        				
        				
        			}        			
        		}
    			

    		}

    		
        }
        catch (Exception e) {
	        cnt++;
	        log.error("",e);
        }
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("cnt", cnt+"");//기타
    	map.put("refundChk", refundChk+"");//주문입력오류	
    	
		return map;
	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
    /**
     * 반품/교환 클레임 완료배치 > 반품 환불로직
     * @param clmNo
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
    public String returnCmplBatchRepayToDB(String clmNo) throws Exception {
       	log.info("clmNo :================>"+clmNo);
    	Clm clm = new Clm();
    	clm.setClmNo(clmNo);
       	clm = claimBatchService.selectClmByClmNo(clm);
    	PayExtend checkPay = new PayExtend();
    	checkPay.setClmNo(clmNo);
    	List<PayExtend> checkPayList = payBoService.selectOrdClmPayExtend(checkPay);    	
   
    	Ord ord = orderSelectService.selectOrdEntity(clm.getOrdNo());
    	

    	
    	if(checkPayList!=null && checkPayList.size()!=0 && clm.getStdrCrncySumAmt().intValue()>0){//기존에 해당 클레임으로 pay 테이블에 insert 되어있다면 환불로직을 타지 않는다.
    		log.info("이미 pay 테이블에 insert 된 클레임입니다. clmNo : "+clmNo);
    	}else{//클레임번호로 pay 테이블에 insert 되어있지 않다면 환불로직을 탄다.
        	boolean refundChk = false;
        	Pay pay = new Pay();

      
        	pay.setOrdNo(clm.getOrdNo());//주문번호로
        	pay.setPayTpCd("ORD");//주문시 생성된
        	pay.setDealTpCd("PAY_COMPT");//결제완료된
        	//List<PayExtend> ordPayList = payBoService.selectOrdClmPayExtend(pay);
        	List<PayExtend> ordPayList = payBoService.selectOrdClmRefundPayExtend(pay); //K2 환불대상 중복으로변경
   
        	int errorCnt = 0;
        	HashMap<String, String> map = null;
        	for(PayExtend payDto : ordPayList){
        		if(clm.getStdrCrncySumAmt().intValue()<0 && "Y".equals(payDto.getMpayMnYn())){
        			//클레임에 사이트기준금액이 음수이면 추가결제건이니 주결제는 환불로직을 타지않고 클레임 완료처리한다.
     
        	    	map = new HashMap<String, String>();
        	    	map.put("cnt", "0");//기타
        	    	map.put("refundChk", "false");//주문입력오류
        		}else{
        			if("KOR".equalsIgnoreCase(ord.getLangCd())){//국내면
                		map = returnCmplBatchRepayTransaction(payDto,ord,clm);
                		errorCnt += Integer.parseInt(map.get("cnt"));        				
        			}else{//글로벌이면
                		map = returnCmplBatchRepayTransactionGlobal(payDto,ord,clm);
                		errorCnt += Integer.parseInt(map.get("cnt"));
        			}        			
        		}

        		
        		
        		if("true".equals(map.get("refundChk"))){
        			refundChk = true;
        		}
        	}
        	
        	// 환불 오류가 발생하더라도 데이터는 생성된 상태이고이후 다른 액션이 있을 수 없으며 후 처리는 환불오류에 대한 재 환불시도만 하고있음.
        	// 따라서 환불오류가 발생하더라도 P포인트은 별개로 복원처리 하도록 수정함.
        	this.insertWebpoint(ord, clm);
        	
        	if(errorCnt==0){        	
        		
    			//클레임 완료처리
    			clm.setClmStatCd("COMPT");
    
    			claimService.updateClmStatCd(clm);
    
    			
    			
				/**
				 * #34162 [개발]글로벌 배송정책 변경으로 인한 클레임 처리시 주문상태 반영 기능 개선 요청의 건
				 * 2016-01-24 글로벌 배송파트에서 분리배송 적용하여
				 * 클레임에서도 클레임이 완료되는 시점에 잔여 상품이 모두 배송 완료면
				 * 주문상태를 배송완료로 변경해준다.
				 */
    			if(!"KOR".equalsIgnoreCase(ord.getLangCd())){
    				//배송완료 대상인지 여부를 select 하는 쿼리
    				boolean ordStatDlvComptYn = claimService.selectOrdStatDlvComptYn(ord.getOrdNo());
    				//주문상태를 배송완료로 update 하는 쿼리
    				if(ordStatDlvComptYn){
    					Ord statUpdateOrd = new Ord();
    					statUpdateOrd.setOrdNo(ord.getOrdNo());
    					statUpdateOrd.setOrdStatCd("DLV_COMPT");//주문테이블 주문상태 : 배송완료
    					orderBoService.updateOrdStatCd(statUpdateOrd);			
    				}	
    			}
    			
    			
    			
    			//티몰주문일경우 회수지시테이블에 사은품들을 회수완료로 강제 update 해준다.
    			if("TMALL".equals(ord.getMallId())){
    				//클레임번호로 해당데이터 사은품만 회수완료로 update
    				deliveryGlobalAffService.updateRtrvlDrctGftForTmall(clm);
    			}
        		
        		Mbr mbr = new Mbr();
        		mbr.setMbrNo(ord.getMbrNo());
     
        		mbr = memberBaseSelectService.selectMbr(mbr);
  
        		if(mbr==null){
        			mbr = new Mbr();
        		}
    
        		SystemPK systemPK = getIdGenService().generateSystemPK("", "", "KOR");
   
        		//에러가 없다면 클레임 정상적으로 완료되었다고 메일이랑 문자를 보내준다.
        		try {
					if("KOR".equalsIgnoreCase(ord.getLangCd())){
                        sendMmsEmail(ord, mbr,clm, "C03", systemPK, refundChk);
					} else if("CHI".equalsIgnoreCase(ord.getLangCd())) {
						sendMmsEmail(ord, mbr,clm, EMailEnum.CHI_CLM_RETURN_COMPT_INFO.toString(), systemPK, refundChk);
					} else {
						sendMmsEmail(ord, mbr,clm, EMailEnum.ENG_CLM_RETURN_COMPT_INFO.toString(), systemPK, refundChk);
					}
                }catch (Throwable e) {
                	log.error("",e);
                }
        		
        		
        	}
    	}
    	

    	return "";
    }
    
    
    
    //스플릿 진행하며 불필요한 트렌젝션 정리
    //@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
    private HashMap<String, String> returnCmplBatchRepayTransaction(PayExtend payDto ,Ord ord, Clm clm){
    	int cnt = 0;
    	boolean refundChk = false;
    	try {
        	String clmNo = clm.getClmNo();
    		String rfdStatCdOri = "";//환불상태코드 결제취소 요청 후 리턴 코드
    		String rfdStatCd = "RFD_COMPT";//환불상태코드 결제취소 요청 후 리턴 코드 (결제환불의 코드) pg 미연동 고려하여 초기값을 환불완료로 한다.
    		String rfdResn = "";//사유 결제취소 요청후 리턴 메세지
    		/*ㅁ. 거래유형 : DEAL_TP
    		   >. 결제 대기 : PAY_WAIT
    		   >. 입금 대기 : DEPST_WAIT
    		   >. 결제 완료 : PAY_COMPT
    		   >. 전체 취소 : ALL_CNCL
    		   >. 부분 취소 : PART_CNCL
    		 * */
    		String dealTpCd = "";	//거래유형코드 
    		String refundYn="";	//모빌리언 주문시 결제월체크하여 환불여부 확인
    		Date date = new Date();  

        	//HttpServletRequest request = getCurrentRequest();
        	ObjectMapper om = new ObjectMapper(); 
    		
    		if("Y".equals(payDto.getMpayMnYn())){//주결제
    			//ordMainPay = payDto;
    			
    			
    			/*DB처리*/
    	    	MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();
    	    	
    	    	// BO에서 결제한 정보를 가져온다
    	    	mypageOrderInfoDTO.setOrdNo(ord.getOrdNo());
    	    	mypageOrderInfoDTO.setMbrNo(ord.getMbrNo());
    	
    	    	mypageOrderInfoDTO = orderSelectService.selectPayMethodChangeClm(mypageOrderInfoDTO);//주결제 정보
    
    	    	if(mypageOrderInfoDTO==null){
    
    	    		throw new ClaimAlreadyExistException("환불로직 처리중 주문번호 : "+ord.getOrdNo()+" 에 메인 결제가 없습니다.");    	    		
    	    	}
    	    	
    	    	dealTpCd=mypageOrderInfoDTO.getDealTpCd();
    	    	refundYn=mypageOrderInfoDTO.getRefundYn();
    	    	
    			// 기존 원주문 payNo
    	    	// pay 취소건 취소누적금액 update
    	    	mypageOrderInfoDTO.setMpayMnYn("Y");	
    	    	mypageOrderInfoDTO.setCnclAcmtlAmt(clm.getPayExchgRtCrncySumAmt().intValue()+"");
    
    	    	orderCommandService.updateCancelPay(mypageOrderInfoDTO);
    
    			//  pay 취소정보 insert
    			//  payNo 번호 따와서 넣어야함    	
    
    			mypageOrderInfoDTO.setNewPayNo(orderCommandService.selectPayNumber());
    	
    			mypageOrderInfoDTO.setMpayMnYn("N");	//정산 요청으로 클레임으로 들어가는 결제정보중 주결제도 MpayMnYn 을 Y 로 넣는다. 는 원복
    			mypageOrderInfoDTO.setPayTpCd("CLM");//결제유형코드 클레임
    			mypageOrderInfoDTO.setClmNo(clmNo);
    			mypageOrderInfoDTO.setRegtrId(clm.getRegtrId());
    			mypageOrderInfoDTO.setRegDt(date);
    			
    			if("ALL_CNCL".equals(clm.getClmTpCd())){
    				mypageOrderInfoDTO.setDealTpCd("ALL_CNCL");	
    			}else{
    				mypageOrderInfoDTO.setDealTpCd("PART_CNCL");
    			}
    			
    			mypageOrderInfoDTO.setStdrCrncyPayAmt(clm.getStdrCrncySumAmt().intValue()+"");
    			mypageOrderInfoDTO.setPayCrncyPayAmt(clm.getPayExchgRtCrncySumAmt().intValue()+"");
    			if(clm.getRfdBnkAcctNo()!=null &&!"".equals(clm.getRfdBnkAcctNo()) && ord.getMbrNo() != null &&!"".equals(ord.getMbrNo())){

    				mypageOrderInfoDTO.setRfdBnkCd(clm.getRfdBnkCd());
    				mypageOrderInfoDTO.setRfdBnkAcctNo(clm.getRfdBnkAcctNo());
    				mypageOrderInfoDTO.setRfdAcnthldrNm(clm.getRfdAcnthldrNm());
    				
    				/********************
    				 * 회원 환불계좌 테이블 insert (merge into)
    				 */
    				
    				Map<String, Object> map = new HashMap<String, Object>();		
    				map.put("MBR_NO", ord.getMbrNo());		
   
    				int rfdBnkAcctTurn = Integer.parseInt(idGenService.generateDBOrder(sqlSession1,"mbr_rfd_bnk_acct","rfd_bnk_acct_turn",map, DatabaseType.ORACLE)+"");
    	
    				MbrRfdBnkAcct mbrRfdBnkAcct = new MbrRfdBnkAcct();
    				mbrRfdBnkAcct.setMbrNo(ord.getMbrNo());
    				mbrRfdBnkAcct.setRfdBnkAcctTurn(rfdBnkAcctTurn);
    				mbrRfdBnkAcct.setRfdBnkAcctNm("클레임 환불계좌 등록");
    				mbrRfdBnkAcct.setRfdBnkAcctNo(clm.getRfdBnkAcctNo());
    				mbrRfdBnkAcct.setRfdBnkAcctBnkCd(clm.getRfdBnkCd());
    				mbrRfdBnkAcct.setRfdBnkAcctAcnthldrNm(clm.getRfdAcnthldrNm());
    				mbrRfdBnkAcct.setRprstRfdBnkAcctYn("N");
    				mbrRfdBnkAcct.setRegtrId(clm.getRegtrId());
    				mbrRfdBnkAcct.setUdterId(clm.getRegtrId());
    		
    				memberBenefitCommandService.insertMbrRfdAcc(mbrRfdBnkAcct);
    		
    			}
    
    			// DTO 변경으로 주석처리 2018-07-11
//    			orderCommandService.insertCancelPay(mypageOrderInfoDTO);    
    	
    			Pay searchPay =  new Pay();
    			searchPay.setPayNo(mypageOrderInfoDTO.getNewPayNo());
    	
    			Pay refundPay = payBoService.selectPayByPayNo(searchPay); //주결제 pay 정보
    	
    			searchPay =  new Pay();
    			searchPay.setPayNo(mypageOrderInfoDTO.getPayNo());
    	
    			Pay ordPay = payBoService.selectPayByPayNo(searchPay); //주결제 pay 정보
    	
    			
    			


    			
    			if("PAY_COMPT".equals(dealTpCd)
    					&& !"N".equals(clm.getPgTrnsmisTgtYn())//pg 연동여부가 N 이 아닌거 Y는 연동대상임
    					&& !"TMALL".equals(ord .getMallId())//티몰주문도 PG 연동대상 아님 티몰도 주문유형이 제휴사임.
    					){//제휴사 주문이 아니고 결제 완료이면
        	    	try {
        	    		

        	    		rfdStatCd = "RFD_COMPT";//0원 결제면 PG사 통신하지않고 무조건 환불완료로 저장한다.
 
        	    	}catch (Throwable e) {
        	        	log.error("",e);
        	
        	        	//주결제 모듈 연동실패
        	        	//주결제 환불모듈에 연결 실패하였습니다.
        				rfdStatCd = "RFD_ERR";//환불상태코드 결제취소 요청 후 리턴 코드
        				rfdResn = "PG사 연동 실패 : "+e.getMessage();//사유 결제취소 요청후 리턴 메세지
        				//throw new Exception("PG사 연동 실패");    				
        				log.info("PG사 연동 실패 : "+om.writeValueAsString(payDto));
        				cnt++;
        	        }
    			}
    			
    			
    			
    			/*	결제 환불 테이블	*/
    			String payMn = refundPay.getPayMnCd();
    			String rfdTpCd = "";
    			if("RLTM_BNK_ACCT_PAY".equals(payMn) || "NON_BNKBOK_PAY".equals(payMn)){
    				rfdTpCd = "CASH_RFD";
    			}else if("CREDT_CARD_PAY".equals(payMn)){
    				rfdTpCd = "CREDT_CARD_CNCL";
    			}else if("MOBIL_PON_PAY".equals(payMn)){
    				rfdTpCd = "MOBIL_CNCL";
    			}else if("GFCT_PAY".equals(payMn)){
    				rfdTpCd = "GFCT_RFD";
    			}else if("MBSH_PNT_PAY".equals(payMn) || "WEB_PNT_PAY".equals(payMn)){
    				rfdTpCd = "SAV_MNY_RFD";
    			}else if("VIRTL_BNK_ACCT_PAY".equals(payMn)){
    				rfdTpCd = "VIRTL_BNK_ACCT_CNCL";
    			}else if("ZERO_PAY".equals(payMn)) {
    				rfdTpCd = "ZERO_PAY_CNCL";
    			}else if("ALIPAY".equals(payMn)){
    				rfdTpCd = "ALIPAY";
    			}else{
    				rfdTpCd = payMn;
    			}

    			/*insertPayRfd*/
    			PayRfd payRfd = new PayRfd();
    			payRfd.setPayNo(mypageOrderInfoDTO.getNewPayNo());
    			payRfd.setRfdStatCd(rfdStatCd); //환불 완료
    			 
    			payRfd.setRfdRequstDt(date);//DB시간으로 넣어야 하므로 비어있지 않다면 쿼리에서 sysdate 로 넣자
    			payRfd.setRfdRequstResn(clm.getClmTpCd());
    			if("RFD_COMPT".equals(rfdStatCd)){
    				payRfd.setRfdComptDt(date);
    			}else if("RFD_ERR".equals(rfdStatCd)){
    				payRfd.setRfdErrDt(date);
    				payRfd.setRfdErrCont(rfdResn);					
    			}
    			payRfd.setPreRfdYn("N"); //선환불 아님  
    			payRfd.setRfdOccurTpCd("ORD_CLM");
    			payRfd.setRfdTpCd(rfdTpCd);
    			payRfd.setRegDt(date);
    			payRfd.setRegtrId(clm.getRegtrId());
    			payRfd.setUdtDt(date);
    			payRfd.setUdterId(clm.getRegtrId());
    			payRfd.setStdrCrncyRfdAmt(refundPay.getStdrCrncyPayAmt());
    			
				payRfd.setExchgRtAplBegDt(ord.getExchgRtAplBegDt());
				payRfd.setPayCrncyRfdAmt(refundPay.getPayCrncyPayAmt());
    			
    			//payRfd.setRfdAmt(refundPay.getPayCrncyPayAmt());
    			payRfd.setRfdCrncyCd(refundPay.getPayCrncyCd());
    			
				if(refundChk){
					payRfd.setPreRfdYn("Y");	
				}else{
					payRfd.setPreRfdYn("N");
				}
    			
    
    			payBoService.insertPayRfd(payRfd);

    		}else{//부가결제 포인트,쿠폰등
    			
//부가결제 포인트,쿠폰등
    			
    			
    			boolean rfdCheck = false;
    			
        		/*쿠폰복원*/
        		
        		//상품쿠폰 적용된 해당 상품의 모든 수량이 반품되었다면 복원해준다.
        		//claimBatchService.selectGodCpnRepay()
        		if("GOD_CPN".equals(payDto.getPayMnCd())){
        	
        			String mbrCpnNo = claimBatchService.getRepayGodCpn(payDto);
        	
        			if(null != mbrCpnNo && !"".equals(mbrCpnNo)){
        				/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
        	        	MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
        	        	mbrIssuCpn.setMbrNo(ord.getMbrNo());    	        	
        	        	mbrIssuCpn.setMbrCpnNo(payDto.getMbrCpnNo());
        	        	mbrIssuCpn.setUdterId(clm.getUdterId());
        	
        	        	promotionService.updateCouponRevertStatus(mbrIssuCpn);
        
        	        	/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
        	        	rfdCheck = true;
        			}
        		}else if("BSK_CPN".equals(payDto.getPayMnCd())){
            		//장바구니쿠폰 모든 주문상품이 반품되었다면 복원해준다. 
            		//ord_god_erp 테이블에 clm_no 가 null 인게 없으면 모두 취소된걸로 판단하고 복원해준다.
        		    InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
        		    infOrdGodErpDstbClmSearchDTO.setOrdNo(payDto.getOrdNo());
        		    infOrdGodErpDstbClmSearchDTO.setClmNoNullYn("Y");
        		    infOrdGodErpDstbClmSearchDTO.setExceptExchangeWithrawYn("Y");
        
        		    List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
       
        		    
        		    // 사용된 쿠폰이 있는경우 추가 (다수의 반품클레임이 동시에 완료될 경우 모든 클레임에서 쿠폰 복구에 대한 로직을 처리하게 되므로 체크하도록 한다)
        		    MbrIssuCpn cpnCntParam = new MbrIssuCpn();
        		    cpnCntParam.setOrdNo(payDto.getOrdNo());
        		    cpnCntParam.setMbrCpnNo(payDto.getMbrCpnNo());
        		    int useCpnCnt = claimBatchService.getOrderUseCouponCount(cpnCntParam);
        		    
            		if((infOrdGodErpDstbCheckList==null || infOrdGodErpDstbCheckList.size()==0) && useCpnCnt > 0){
        				/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
        	        	MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
        	        	//mbrIssuCpn.setMbrNo(ord.getMbrNo());    	        	
        	        	mbrIssuCpn.setClmNo(clmNo);
        	        	mbrIssuCpn.setMbrCpnNo(payDto.getMbrCpnNo());
        	        	mbrIssuCpn.setUdterId(clm.getUdterId());    	 
        
        	        	promotionService.updateCouponRevertStatus(mbrIssuCpn);
       
        	        	/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
        	        	rfdCheck = true;
            		}    			
        		}else if("DLV_CST_CPN".equals(payDto.getPayMnCd()) || "QDLV_CST_CPN".equals(payDto.getPayMnCd())){
        			int remainCnt = orderSelectService.selectAplPrmGodDlvFeeCnt(payDto.getOrdNo());
					if(remainCnt == 0) {	        			
	        			//무료배송 쿠폰은 해당 배송지에 모든상품이 취소된경우 복원해준다.
	        			//결제정보에서 쿠폰번호가져와서 물류배송의 배송지 순번으로 해당배송지에 모든 상품이 취소나 반품되었다면 복원해준다.
	        		    InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
	        		    infOrdGodErpDstbClmSearchDTO.setOrdNo(payDto.getOrdNo());
	        		    infOrdGodErpDstbClmSearchDTO.setClmNoNullYn("Y");
	        		    infOrdGodErpDstbClmSearchDTO.setDlvCstCpnNo(payDto.getMbrCpnNo());
	        		    //해당 쿠폰이 사용된 배송지에 상품중 ord_god_erp에 클레임번호가 없는게 있는지 조회
	        
	        		    List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
	     
	        		    //해당 배송지에 클레임 안걸린(정상주문)게 없다면 (해당배송지에 모두 클레임이 걸렸다면) 무료배송쿠폰 복원
	            		if(infOrdGodErpDstbCheckList==null || infOrdGodErpDstbCheckList.size()==0){
	        				/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
	        	        	MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
	        	        	mbrIssuCpn.setMbrNo(ord.getMbrNo());    	        	
	        	        	mbrIssuCpn.setMbrCpnNo(payDto.getMbrCpnNo());
	        	        	mbrIssuCpn.setUdterId(clm.getUdterId());    
	  
	        	        	promotionService.updateCouponRevertStatus(mbrIssuCpn);
	 
	        	        	/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
	        	        	rfdCheck = true;
	            		}  
         			}
        		}else if("WEB_PNT_PAY".equals(payDto.getPayMnCd())){
					//this.insertWebpoint(ord, clm);
					rfdCheck = true;
				}else{
        			rfdCheck = true;
        		}
        		
        		//무료배송쿠폰은 반품이라 복원할 필요 없음 (이미 받을때 사용)
    			
        		
        		if(rfdCheck){
        			int refundServAmt=0;
        			
        			if("MBSH_PNT_PAY".equals(payDto.getPayMnCd())){//1차오픈에서는 포인트 유형은 멤버쉽만 있음
        				refundServAmt = clm.getUnityPntUseSumAmt().intValue();
        			}else if("GOD_CPN".equals(payDto.getPayMnCd())){//상품쿠폰
        				refundServAmt = clm.getGodCpnDcSumAmt().intValue();    				
        			}else if("BSK_CPN".equals(payDto.getPayMnCd())){//장바구니쿠폰
        				refundServAmt = clm.getBskCpnDcSumAmt().intValue();    				
        			}else if("DLV_CST_CPN".equals(payDto.getPayMnCd())){//배송비쿠폰
        				refundServAmt = clm.getDlvCstCpnDcSumAmt().intValue();    				
        			}else if("MBSH_IMDTL_PNT_PAY".equals(payDto.getPayMnCd())){//즉시할인
        				refundServAmt = clm.getImdtlDcSumAmt().intValue();
        			}else if("WEB_PNT_PAY".equals(payDto.getPayMnCd())){//P포인트
						refundServAmt = clm.getWebpntUseSumAmt().intValue();
					}else if("QDLV_CST_CPN".equals(payDto.getPayMnCd())){//퀵배송비쿠폰
        				refundServAmt = clm.getDlvCstCpnDcSumAmt().intValue();    				
        			}
        				
    				//원주문 취소 누적금액 누적
    				BigDecimal bd = new BigDecimal(refundServAmt);
    				payDto.setStdrCrncyPayAmt(bd);
    				payDto.setPayCrncyPayAmt(bd);  
    				payDto.setUdterId(clm.getRegtrId());    	
    	
        			claimBatchService.updateOrdPayCancelAcclAmt(payDto);
     
        			//결제취소로 결제row insert
  
    				payDto.setNewPayNo(orderCommandService.selectPayNumber());
    
    				payDto.setMpayMnYn("N");	//주결제가 아니므로 N 
    				payDto.setPayTpCd("CLM");//결제유형코드 클레임
    				payDto.setClmNo(clmNo);
    				payDto.setRegtrId(clm.getRegtrId());
    				payDto.setRegDt(date);
        			if("ALL_CNCL".equals(clm.getClmTpCd())){
        				payDto.setDealTpCd("ALL_CNCL");	
        			}else{
        				payDto.setDealTpCd("PART_CNCL");
        			}    				
     
        			claimBatchService.insertClmPay(payDto);
      
        			
        			//결제환불테이블 insert
        			//PayRfd payRfd = new PayRfd();
        			//claimBatchService.insertClmPayRfd(payRfd);
        			
        			
    				/*	결제 환불 테이블	*/
    				String payMn = payDto.getPayMnCd();
    				String rfdTpCd = "";
        			if("RLTM_BNK_ACCT_PAY".equals(payMn) || "NON_BNKBOK_PAY".equals(payMn)){
        				rfdTpCd = "CASH_RFD";
        			}else if("CREDT_CARD_PAY".equals(payMn)){
        				rfdTpCd = "CREDT_CARD_CNCL";
        			}else if("MOBIL_PON_PAY".equals(payMn)){
        				rfdTpCd = "MOBIL_CNCL";
        			}else if("GFCT_PAY".equals(payMn)){
        				rfdTpCd = "GFCT_RFD";
        			}else if("UNITY_SAV_MNY_PAY".equals(payMn) || "WEB_PNT_PAY".equals(payMn)){
        				rfdTpCd = "SAV_MNY_RFD";
        			}else if("VIRTL_BNK_ACCT_PAY".equals(payMn)){
        				rfdTpCd = "VIRTL_BNK_ACCT_CNCL";
        			}else if("MBSH_PNT_PAY".equals(payMn)){
        				rfdTpCd = "UNITY_SAV_MNY_RFD";
        			}else if("EVT_PNT_PAY".equals(payMn)){
        				rfdTpCd = "EVT_PNT_RFD";
        			}else if("ZERO_PAY".equals(payMn)){
        				rfdTpCd = "ZERO_PAY";
        			}else if("MBSH_IMDTL_PNT_PAY".equals(payMn)){
        				rfdTpCd = "MBSH_IMDTL_PNT_PAY";
        			}else if("WEB_PNT_PAY".equals(payMn)){
						rfdTpCd = "WEBPNT_RFD";
					}else{
        				rfdTpCd = payMn;
        			}
        			

        			
    				/*insertPayRfd*/
    				PayRfd payRfd = new PayRfd();
    				payRfd.setPayNo(payDto.getNewPayNo());
    				payRfd.setRfdStatCd("RFD_COMPT"); //환불 완료 (포인트 실제 erp 전송은 반품erp전송 배치에서 한다.)
    				payRfd.setRfdRequstDt(date);//DB시간으로 넣어야 하므로 비어있지 않다면 쿼리에서 sysdate 로 넣자
    				payRfd.setRfdRequstResn(clm.getClmTpCd());
    				if("RFD_COMPT".equals(rfdStatCd)){
    					payRfd.setRfdComptDt(date);
    				}else if("RFD_ERR".equals(rfdStatCd)){
    					payRfd.setRfdErrDt(date);
    					payRfd.setRfdErrCont(rfdResn);		
    					cnt++;
    					log.warn("RFD ERROR : "+payDto.getNewPayNo());
    				}
    				payRfd.setPreRfdYn("N"); //선환불 아님  
    				payRfd.setRfdOccurTpCd("ORD_CLM");
    				payRfd.setRfdTpCd(rfdTpCd);
    				payRfd.setRegDt(date);
    				payRfd.setRegtrId(clm.getRegtrId());
    				payRfd.setUdtDt(date);
    				payRfd.setUdterId(clm.getRegtrId());
    				payRfd.setStdrCrncyRfdAmt(payDto.getStdrCrncyPayAmt());
    				
					payRfd.setExchgRtAplBegDt(ord.getExchgRtAplBegDt());
					payRfd.setPayCrncyRfdAmt(payDto.getPayCrncyPayAmt());
    				
        			//payRfd.setRfdAmt(payDto.getPayCrncyPayAmt());
        			payRfd.setRfdCrncyCd(payDto.getPayCrncyCd());
        			if(!"".equals(rfdTpCd)){
        	
        				payBoService.insertPayRfd(payRfd);
     
        			}        			
        		}
    			

    		}
    	    		
         }catch (Throwable e){
        	 log.error("Batch_Repay_Fail" + " exception : {}", clm);
        	 log.error("Batch_Repay_Fail", e);  
             cnt++;
	    }
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("cnt", cnt+"");//기타
    	map.put("refundChk", refundChk+"");//주문입력오류	
    	
		return map;
	
    }
    
    
	private HashMap<String, String> returnCmplBatchRepayTransactionGlobal(PayExtend payDto ,Ord ord, Clm clm){
		int cnt = 0;
		boolean refundChk = false;
		try {
			String clmNo = clm.getClmNo();
			String rfdStatCdOri = "";//환불상태코드 결제취소 요청 후 리턴 코드
			String rfdStatCd = "RFD_COMPT";//환불상태코드 결제취소 요청 후 리턴 코드 (결제환불의 코드) pg 미연동 고려하여 초기값을 환불완료로 한다.
			String rfdResn = "";//사유 결제취소 요청후 리턴 메세지
    		/*ㅁ. 거래유형 : DEAL_TP
    		   >. 결제 대기 : PAY_WAIT
    		   >. 입금 대기 : DEPST_WAIT
    		   >. 결제 완료 : PAY_COMPT
    		   >. 전체 취소 : ALL_CNCL
    		   >. 부분 취소 : PART_CNCL
    		 * */
			String dealTpCd = "";	//거래유형코드
			String refundYn="";	//모빌리언 주문시 결제월체크하여 환불여부 확인
			Date date = new Date();

			//HttpServletRequest request = getCurrentRequest();
			ObjectMapper om = new ObjectMapper();

			if("Y".equals(payDto.getMpayMnYn())){//주결제
				//ordMainPay = payDto;
    			
    			
    			/*DB처리*/
				MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();

				// BO에서 결제한 정보를 가져온다
				mypageOrderInfoDTO.setOrdNo(ord.getOrdNo());
				mypageOrderInfoDTO.setMbrNo(ord.getMbrNo());
				mypageOrderInfoDTO = orderSelectService.selectPayMethodChangeClm(mypageOrderInfoDTO);//주결제 정보
				
				List<String> paramList = new ArrayList<String>();
				paramList.add(ord.getOrdNo()); // {0}
				String[] params = paramList.toArray(new String[paramList.size()]);
				
				if(mypageOrderInfoDTO==null){
					throw new ClaimCompleteFailException("Claim.pay.not.ord_no", params);
				}

				dealTpCd=mypageOrderInfoDTO.getDealTpCd();
				refundYn=mypageOrderInfoDTO.getRefundYn();

				// 기존 원주문 payNo
				// pay 취소건 취소누적금액 update
				mypageOrderInfoDTO.setMpayMnYn("Y");
				//mypageOrderInfoDTO.setCnclAcmtlAmt(clm.getPayExchgRtCrncySumAmt().intValue()+"");
				//ncp3차로 수정
				mypageOrderInfoDTO.setCnclAcmtlAmt(clm.getStdrCrncySumAmt().intValue()+"");
				orderCommandService.updateCancelPay(mypageOrderInfoDTO);

				//  pay 취소정보 insert
				//  payNo 번호 따와서 넣어야함
				mypageOrderInfoDTO.setNewPayNo(orderCommandService.selectPayNumber());
				mypageOrderInfoDTO.setMpayMnYn("N");	//클레임으로 들어가는 결제정보중 주결제도 MpayMnYn 을 Y 로 넣는다. 는 원복
				mypageOrderInfoDTO.setPayTpCd("CLM");//결제유형코드 클레임
				mypageOrderInfoDTO.setClmNo(clmNo);
				mypageOrderInfoDTO.setRegtrId(clm.getRegtrId());
				mypageOrderInfoDTO.setRegDt(date);

				if("ALL_CNCL".equals(clm.getClmTpCd())){
					mypageOrderInfoDTO.setDealTpCd("ALL_CNCL");
				}else{
					mypageOrderInfoDTO.setDealTpCd("PART_CNCL");
				}

				mypageOrderInfoDTO.setStdrCrncyPayAmt(clm.getStdrCrncySumAmt().intValue()+"");
				mypageOrderInfoDTO.setPayCrncyPayAmt(clm.getPayExchgRtCrncySumAmt().toString());
				
				// DTO 변경으로 주석처리 2018-07-11
//				orderCommandService.insertCancelPay(mypageOrderInfoDTO);


				Pay searchPay =  new Pay();
				searchPay.setPayNo(mypageOrderInfoDTO.getNewPayNo());
				Pay refundPay = payBoService.selectPayByPayNo(searchPay); //주결제 pay 정보
				searchPay =  new Pay();
				searchPay.setPayNo(mypageOrderInfoDTO.getPayNo());
				Pay ordPay = payBoService.selectPayByPayNo(searchPay); //주결제 pay 정보






				if("PAY_COMPT".equals(dealTpCd)//제휴사 주문이 아니고 결제 완료이면
						&& !"N".equals(clm.getPgTrnsmisTgtYn())//pg 연동여부가 N 이 아닌거 Y는 연동대상임
						){
					try {
						
						rfdStatCd = "RFD_COMPT";//0원 결제면 PG사 통신하지않고 무조건 환불완료로 저장한다.
						
					}catch (Throwable e) {
						e.printStackTrace();
						//주결제 모듈 연동실패
						//주결제 환불모듈에 연결 실패하였습니다.
						rfdStatCd = "RFD_ERR";//환불상태코드 결제취소 요청 후 리턴 코드
						rfdResn = "PG사 연동 실패 : "+e.getMessage();//사유 결제취소 요청후 리턴 메세지
						//throw new Exception("PG사 연동 실패");
						log.info("PG사 연동 실패 : "+om.writeValueAsString(payDto));
						cnt++;
					}
				}
    			
    			
    			
    			/*	결제 환불 테이블	*/
				String payMn = refundPay.getPayMnCd();
				String rfdTpCd = "";



				if("RLTM_BNK_ACCT_PAY".equals(payMn) || "NON_BNKBOK_PAY".equals(payMn)){
					rfdTpCd = "CASH_RFD";
				}else if("CREDT_CARD_PAY".equals(payMn)){
					rfdTpCd = "CREDT_CARD_CNCL";
				}else if("MOBIL_PON_PAY".equals(payMn)){
					rfdTpCd = "MOBIL_CNCL";
				}else if("GFCT_PAY".equals(payMn)){
					rfdTpCd = "GFCT_RFD";
				}else if("UNITY_SAV_MNY_PAY".equals(payMn) || "WEB_PNT_PAY".equals(payMn)){
					rfdTpCd = "SAV_MNY_RFD";
				}else if("VIRTL_BNK_ACCT_PAY".equals(payMn)){
					rfdTpCd = "VIRTL_BNK_ACCT_CNCL";
				}else if("MBSH_PNT_PAY".equals(payMn)){
					rfdTpCd = "UNITY_SAV_MNY_RFD";
				}else if("EVT_PNT_PAY".equals(payMn)){
					rfdTpCd = "EVT_PNT_RFD";
				}else if("PAYPAL".equals(payMn)){
					rfdTpCd = "PAYPAL_CNCL";
				}else if("UNIONPAY".equals(payMn)){
					rfdTpCd = "CUP_CNCL";
				}else if("ZERO_PAY".equals(payMn)){
					rfdTpCd = "ZERO_PAY";
				}

    			/*insertPayRfd*/
				PayRfd payRfd = new PayRfd();
				payRfd.setPayNo(mypageOrderInfoDTO.getNewPayNo());
				payRfd.setRfdStatCd(rfdStatCd); //환불 완료

				payRfd.setRfdRequstDt(date);//DB시간으로 넣어야 하므로 비어있지 않다면 쿼리에서 sysdate 로 넣자
				payRfd.setRfdRequstResn(clm.getClmTpCd());
				if("RFD_COMPT".equals(rfdStatCd)){
					payRfd.setRfdComptDt(date);
				}else if("RFD_ERR".equals(rfdStatCd)){
					payRfd.setRfdErrDt(date);
					payRfd.setRfdErrCont(rfdResn);
				}
				payRfd.setPreRfdYn("N"); //선환불 아님
				payRfd.setRfdOccurTpCd("ORD_CLM");
				payRfd.setRfdTpCd(rfdTpCd);
				payRfd.setRegDt(date);
				payRfd.setRegtrId(clm.getRegtrId());
				payRfd.setUdtDt(date);
				payRfd.setUdterId(clm.getRegtrId());
				payRfd.setStdrCrncyRfdAmt(refundPay.getStdrCrncyPayAmt());
				payRfd.setExchgRtAplBegDt(ord.getExchgRtAplBegDt());
				payRfd.setPayCrncyRfdAmt(refundPay.getPayCrncyPayAmt());
				//payRfd.setRfdAmt(refundPay.getPayCrncyPayAmt());
				payRfd.setRfdCrncyCd(refundPay.getPayCrncyCd());

				payBoService.insertPayRfd(payRfd);


			}else{//부가결제 포인트,쿠폰등


				boolean rfdCheck = false;
    			
        		/*쿠폰복원*/

				//상품쿠폰 적용된 해당 상품의 모든 수량이 반품되었다면 복원해준다.
				//claimBatchService.selectGodCpnRepay()
				if("GOD_CPN".equals(payDto.getPayMnCd())){
					String mbrCpnNo = claimBatchService.getRepayGodCpn(payDto);
					if(null != mbrCpnNo && !"".equals(mbrCpnNo)){
						/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
						MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
						mbrIssuCpn.setMbrNo(ord.getMbrNo());
						mbrIssuCpn.setMbrCpnNo(payDto.getMbrCpnNo());
						mbrIssuCpn.setUdterId(clm.getUdterId());
						promotionService.updateCouponRevertStatus(mbrIssuCpn);
						/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
						rfdCheck = true;
					}
				}else if("BSK_CPN".equals(payDto.getPayMnCd())){
					//장바구니쿠폰 모든 주문상품이 반품되었다면 복원해준다.
					//ord_god_erp 테이블에 clm_no 가 null 인게 없으면 모두 취소된걸로 판단하고 복원해준다.
					InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
					infOrdGodErpDstbClmSearchDTO.setOrdNo(payDto.getOrdNo());
					infOrdGodErpDstbClmSearchDTO.setClmNoNullYn("Y");
					infOrdGodErpDstbClmSearchDTO.setExceptExchangeWithrawYn("Y");
					
					List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
					if(infOrdGodErpDstbCheckList==null || infOrdGodErpDstbCheckList.size()==0){
						/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
						MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
						//mbrIssuCpn.setMbrNo(ord.getMbrNo());
						mbrIssuCpn.setClmNo(clmNo);
						mbrIssuCpn.setMbrCpnNo(payDto.getMbrCpnNo());
						mbrIssuCpn.setUdterId(clm.getUdterId());
						promotionService.updateCouponRevertStatus(mbrIssuCpn);
						/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
						rfdCheck = true;
					}
				}else if("DLV_CST_CPN".equals(payDto.getPayMnCd())){
					//무료배송 쿠폰은 해당 배송지에 모든상품이 취소된경우 복원해준다.
					//결제정보에서 쿠폰번호가져와서 물류배송의 배송지 순번으로 해당배송지에 모든 상품이 취소나 반품되었다면 복원해준다.
					InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
					infOrdGodErpDstbClmSearchDTO.setOrdNo(payDto.getOrdNo());
					infOrdGodErpDstbClmSearchDTO.setClmNoNullYn("Y");
					infOrdGodErpDstbClmSearchDTO.setDlvCstCpnNo(payDto.getMbrCpnNo());
					//해당 쿠폰이 사용된 배송지에 상품중 ord_god_erp에 클레임번호가 없는게 있는지 조회
					List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
					//해당 배송지에 클레임 안걸린(정상주문)게 없다면 (해당배송지에 모두 클레임이 걸렸다면) 무료배송쿠폰 복원
					if(infOrdGodErpDstbCheckList==null || infOrdGodErpDstbCheckList.size()==0){
						/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
						MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
						mbrIssuCpn.setMbrNo(ord.getMbrNo());
						mbrIssuCpn.setMbrCpnNo(payDto.getMbrCpnNo());
						mbrIssuCpn.setUdterId(clm.getUdterId());
						promotionService.updateCouponRevertStatus(mbrIssuCpn);
						/***************쿠폰복원 레오가 제공해줌 2015-05-30******************/
						rfdCheck = true;
					}
				}else if("WEB_PNT_PAY".equals(payDto.getPayMnCd())){
					//this.insertWebpoint(ord, clm);
					rfdCheck = true;
				}else{
					rfdCheck = true;
				}

				//무료배송쿠폰은 반품이라 복원할 필요 없음 (이미 받을때 사용)


				if(rfdCheck){
					int refundServAmt=0;

					if("MBSH_PNT_PAY".equals(payDto.getPayMnCd())){//1차오픈에서는 포인트 유형은 멤버쉽만 있음
						refundServAmt = clm.getUnityPntUseSumAmt().intValue();
					}else if("GOD_CPN".equals(payDto.getPayMnCd())){//상품쿠폰
						refundServAmt = clm.getGodCpnDcSumAmt().intValue();
					}else if("BSK_CPN".equals(payDto.getPayMnCd())){//장바구니쿠폰
						refundServAmt = clm.getBskCpnDcSumAmt().intValue();
					}else if("DLV_CST_CPN".equals(payDto.getPayMnCd())){//배송비쿠폰
						refundServAmt = clm.getDlvCstCpnDcSumAmt().intValue();
					}else if("MBSH_IMDTL_PNT_PAY".equals(payDto.getPayMnCd())){//즉시할인
						refundServAmt = clm.getImdtlDcSumAmt().intValue();
					}



					//원주문 취소 누적금액 누적
					BigDecimal bd = new BigDecimal(refundServAmt);
					payDto.setStdrCrncyPayAmt(bd);
					payDto.setPayCrncyPayAmt(bd);
					payDto.setUdterId(clm.getRegtrId());
					claimBatchService.updateOrdPayCancelAcclAmt(payDto);

					//결제취소로 결제row insert
					payDto.setNewPayNo(orderCommandService.selectPayNumber());
					payDto.setMpayMnYn("N");	//주결제가 아니므로 N
					payDto.setPayTpCd("CLM");//결제유형코드 클레임
					payDto.setClmNo(clmNo);
					payDto.setRegtrId(clm.getRegtrId());
					payDto.setRegDt(date);
					if("ALL_CNCL".equals(clm.getClmTpCd())){
						payDto.setDealTpCd("ALL_CNCL");
					}else{
						payDto.setDealTpCd("PART_CNCL");
					}
					claimBatchService.insertClmPay(payDto);


					//결제환불테이블 insert
					//PayRfd payRfd = new PayRfd();
					//claimBatchService.insertClmPayRfd(payRfd);
        			
        			
    				/*	결제 환불 테이블	*/
					String payMn = payDto.getPayMnCd();
					String rfdTpCd = "";
					if("RLTM_BNK_ACCT_PAY".equals(payMn) || "NON_BNKBOK_PAY".equals(payMn)){
						rfdTpCd = "CASH_RFD";
					}else if("CREDT_CARD_PAY".equals(payMn)){
						rfdTpCd = "CREDT_CARD_CNCL";
					}else if("MOBIL_PON_PAY".equals(payMn)){
						rfdTpCd = "MOBIL_CNCL";
					}else if("GFCT_PAY".equals(payMn)){
						rfdTpCd = "GFCT_RFD";
					}else if("UNITY_SAV_MNY_PAY".equals(payMn) || "WEB_PNT_PAY".equals(payMn)){
						rfdTpCd = "SAV_MNY_RFD";
					}else if("VIRTL_BNK_ACCT_PAY".equals(payMn)){
						rfdTpCd = "VIRTL_BNK_ACCT_CNCL";
					}else if("MBSH_PNT_PAY".equals(payMn)){
						rfdTpCd = "UNITY_SAV_MNY_RFD";
					}else if("EVT_PNT_PAY".equals(payMn)){
						rfdTpCd = "EVT_PNT_RFD";
					}else if("ZERO_PAY".equals(payMn)){
						rfdTpCd = "ZERO_PAY";
					}else if("MBSH_IMDTL_PNT_PAY".equals(payMn)){
						rfdTpCd = "MBSH_IMDTL_PNT_PAY";
					}else if("WEB_PNT_PAY".equals(payMn)){
						rfdTpCd = "WEBPNT_RFD";
					}else{
						rfdTpCd = payMn;
					}
        			
    				/*insertPayRfd*/
					PayRfd payRfd = new PayRfd();
					payRfd.setPayNo(payDto.getNewPayNo());
					payRfd.setRfdStatCd("RFD_COMPT"); //환불 완료 (포인트 실제 erp 전송은 반품erp전송 배치에서 한다.)
					payRfd.setRfdRequstDt(date);//DB시간으로 넣어야 하므로 비어있지 않다면 쿼리에서 sysdate 로 넣자
					payRfd.setRfdRequstResn(clm.getClmTpCd());
					if("RFD_COMPT".equals(rfdStatCd)){
						payRfd.setRfdComptDt(date);
					}else if("RFD_ERR".equals(rfdStatCd)){
						payRfd.setRfdErrDt(date);
						payRfd.setRfdErrCont(rfdResn);
						cnt++;
						log.warn("RFD ERROR : "+payDto.getNewPayNo());
					}
					payRfd.setPreRfdYn("N"); //선환불 아님
					payRfd.setRfdOccurTpCd("ORD_CLM");
					payRfd.setRfdTpCd(rfdTpCd);
					payRfd.setRegDt(date);
					payRfd.setRegtrId(clm.getRegtrId());
					payRfd.setUdtDt(date);
					payRfd.setUdterId(clm.getRegtrId());
					payRfd.setStdrCrncyRfdAmt(payDto.getStdrCrncyPayAmt());
					//payRfd.setRfdAmt(payDto.getPayCrncyPayAmt());
					payRfd.setRfdCrncyCd(payDto.getPayCrncyCd());
					payRfd.setExchgRtAplBegDt(ord.getExchgRtAplBegDt());
					payRfd.setPayCrncyRfdAmt(payDto.getPayCrncyPayAmt());
					if(!"".equals(rfdTpCd)){
						payBoService.insertPayRfd(payRfd);
					}
				}


			}

		}
		catch (Exception e) {
			log.error("Batch_Repay_Fail" + " exception : {}", clm);
       	    log.error("Batch_Repay_Fail", e);  
       		cnt++;
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cnt", cnt+"");//기타
		map.put("refundChk", refundChk+"");//주문입력오류

		return map;

	}
    
    
	
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public void insertWebpoint(Ord ord, Clm clm){

		try {

			//ncp 3차 P포인트 작업
			InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
			infOrdGodErpDstbClmSearchDTO.setOrdNo(ord.getOrdNo());
			infOrdGodErpDstbClmSearchDTO.setClmNo(clm.getClmNo());
			List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
					
			
			for(InfOrdGodErpDstb infOrdGodErpDstb : infOrdGodErpDstbCheckList){
		        if(ord.getMbrTpCd().equals(MemberEnum.MemberTpCd.UNITY_MBR.toString()) || ord.getMbrTpCd().equals(MemberEnum.MemberTpCd.ONLINE_MBR.toString())) {	            

		            MbrWebpntHist param = new MbrWebpntHist();
		            param.setWebpntTpCd(WebPointEnum.WEBPNT.toString()); // WEBPNT P포인트
		            param.setWebpntResnCd(WebPointEnum.WebPntResnCd.PCH.toString()); // PCH : 구매
		            param.setMbrNo(ord.getMbrNo());
		            param.setOrdNo(ord.getOrdNo());
		            param.setOrdGodTurn(infOrdGodErpDstb.getOrdGodTurn());
		            param.setQtyTurn(infOrdGodErpDstb.getQtyTurn());
		            param.setClmNo(infOrdGodErpDstb.getClmNo());
		            param.setClmWrhsGodTurn(infOrdGodErpDstb.getClmWrhsGodTurn());
		            param.setMallId(ord.getMallId());
		            
		            /*P포인트 사용 (즉시차감) */
		            if (infOrdGodErpDstb.getWebpntAccmlUntPrc().intValue() > 0) {
		                param.setWebpntDetailResnCd(WebPointEnum.WebPntPchDtlResnCd.PCH_CANCL_DDCT.toString()); // 취소로 인한 차감
		                param.setWebpntAmt(infOrdGodErpDstb.getWebpntAccmlUntPrc());//P포인트 적립금
		                param.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_CNCL.toString());//적립취소
		                param.setResnDscr("클레임 으로 적립금액 차감");
		                memberBenefitCommandService.insertWebPoint(param);
		            }

		            /* P포인트 적립예정*/
		            if (infOrdGodErpDstb.getWebpntUseUntPrc().intValue() > 0 ) {
		                param.setWebpntDetailResnCd(WebPointEnum.WebPntPchDtlResnCd.PCH_CANCL_ACCML.toString()); //취소로 인한 적립 
		                param.setWebpntAmt(infOrdGodErpDstb.getWebpntUseUntPrc());
		                param.setWebpntStatCd(WebPointEnum.WebPntStatCd.USE_CNCL.toString());//적립확정
		                param.setResnDscr("클레임 으로 사용금액 적립");
		                memberBenefitCommandService.insertWebPoint(param);
		            }
		        }
				
			}
		}
		catch (Exception e) {
			log.info("클레임 반품 P포인트 적용 실패 clmNo : "+clm.getClmNo());
			e.printStackTrace();
		}

	}
    
    
	private HttpServletRequest getCurrentRequest(){

		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest hsr = sra.getRequest();
	    return hsr;
	}

	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public void sendMmsEmail(Ord ord, Mbr mbr,Clm clm, String mailType, SystemPK systemPK,boolean refundYn) throws Exception{
		long time = System.currentTimeMillis();
		Date regDt = new Date(time);
		
		// 주문취소 : O05 , 반품신청 : C02 , 반품완료  : C03
		// 영문몰 주문취소 : E15 , 반품신청 : E16 , 반품완료  : E17
		// 중문몰 주문취소 : Z15 , 반품신청 : Z16 , 반품완료  : Z17
		//String mailType = "O05";

		//메일전송 셋팅
		// TODO : ses 메일로 변경
//		InfMail infMail = new InfMail();
//		infMail.setAutotype(mailType);
//		infMail.setUserId(!StringService.isEmpty(mbr.getMbrId()) ? mbr.getMbrId() : "NMBR");
//		infMail.setEmail(ord.getCstmrEmail());
//		infMail.setName(ord.getCstmrNm());
//		infMail.setInsertdate(regDt);
//		infMail.setSendyn("N");
//		infMail.setTag1(ord.getOrdNo());
//		infMail.setTag2(clm.getClmNo());//K2
//
//		if("C03".equals(mailType)
//				|| EMailEnum.CHI_CLM_RETURN_COMPT_INFO.toString().equals(mailType)
//				|| EMailEnum.ENG_CLM_RETURN_COMPT_INFO.toString().equals(mailType)){
//			infMail.setTitle("반품 완료 안내");
//		}

		String mallId = ord.getMallId();
		String ordNo = ord.getOrdNo();
		String tranId = StringService.isNotEmpty(ord.getMbrNo()) ? ord.getMbrNo() : clm.getClmNo();
		String mobileNumber = ord.getCstmrMobilAreaNo() + ord.getCstmrMobilTlofNo() + ord.getCstmrMobilTlofWthnNo();

		String msgKey = "";
		ArrayList<String> params = new ArrayList<>();
		DecimalFormat decimalFormat = new DecimalFormat("###,###");

		String mmsMsg = "";

		//SMS개선 by cannon : 2016.07.17
		String smsGodNm = clm.getGodSumry();
		smsGodNm = "[" + sysBrandService.selectBrndNm4SmsByClmNo(clm.getClmNo()) + "]" + smsGodNm;

		if ("O05".equals(mailType)
				|| "O25".equals(mailType)
				|| "O45".equals(mailType)) {
			params.add(0, ordNo);        // 주문번호
			params.add(1, smsGodNm);    // 상품명
			params.add(2, decimalFormat.format(clm.getPayExchgRtCrncySumAmt()));    // 취소 금액

			ClaimRefundVirtualCdResult result = claimBatchService.selectClaimRefundVirtualCd(clm.getClmNo(), true);

			//환불수단별 메세지
			switch (result.getPayMnCd()) {
				case "VIRTL_BNK_ACCT_PAY":
					msgKey = "DXM_CLM_CANCEL_06";
					params.remove(2);    // 가상계좌 취소의 경우 취소 금액 노출 X

					break;

				case "CREDT_CARD_PAY":
				case "RLTM_BNK_ACCT_PAY":
					params.add(3, result.getRfdBnkNm());    // 환불계좌

					msgKey = "DXM_CLM_CANCEL_02";

					break;

				case "MOBIL_PON_PAY":
					if ("Y".equals(result.getThisMonthsPayYn())) {
						//당월
						msgKey = "DXM_CLM_CANCEL_04";
					} else {
						params.add(3, result.getRfdBnkNm() + " " + result.getRfdBnkAcctNo());    // 환불계좌

						//익월
						msgKey = "DXM_CLM_CANCEL_05";
					}

					break;

				default:
					break;
			}

		} else if ("C02".equals(mailType)
				|| "C22".equals(mailType)
				|| "C42".equals(mailType)) {
			params.add(0, ordNo);        // 주문번호
			params.add(1, smsGodNm);    // 상품명
			params.add(2, claimBatchService.selectPcupsDlvCompNm(clm.getClmNo()));        // 수거택배사

			//반품신청
			msgKey = "DXM_CLM_RTGOD_01";
		} else if ("C04".equals(mailType)
				|| "C24".equals(mailType)
				|| "C44".equals(mailType)) {
			params.add(0, ordNo);        // 주문번호
			params.add(1, smsGodNm);    // 상품명
			params.add(2, claimBatchService.selectPcupsDlvCompNm(clm.getClmNo()));    // 수거 택배사

			//교환신청
			msgKey = "DXM_CLM_EXCHG_01";

		} else if ("C03".equals(mailType)
				|| "C23".equals(mailType)
				|| "C43".equals(mailType)) {
			//반품완료
			params.add(0, ordNo);        // 주문번호
			params.add(1, smsGodNm);    // 상품명
			params.add(2, decimalFormat.format(clm.getPayExchgRtCrncySumAmt()));    // 반품 금액

			ClaimRefundVirtualCdResult result = claimBatchService.selectClaimRefundVirtualCd(clm.getClmNo(), true);

			//환불수단별 메세지
			switch (result.getPayMnCd()) {
				case "VIRTL_BNK_ACCT_PAY":
					params.add(2, result.getRfdBnkNm() + " " + result.getRfdBnkAcctNo());    // 환불계좌

					msgKey = "DXM_CLM_RTGOD_02";

					break;

				case "CREDT_CARD_PAY":
				case "RLTM_BNK_ACCT_PAY":
					params.add(2, result.getRfdBnkNm());    // 환불계좌

					msgKey = "DXM_CLM_RTGOD_02";

					break;

				case "MOBIL_PON_PAY":
					if ("Y".equals(result.getThisMonthsPayYn())) {
						//당월
						msgKey = "DXM_CLM_RTGOD_04";
					} else {
						//익월
						params.add(2, result.getRfdBnkNm() + " " + result.getRfdBnkAcctNo());    // 환불계좌

						msgKey = "DXM_CLM_RTGOD_05";
					}

				default:
					break;
			}
		}

		
		boolean sendFlag = false;
        if (StringService.isNotEmpty(msgKey)) {
			String callerId = ClaimBatchComponentImpl.class.getName() + ".sendMmsEmail";

		

			sendFlag = true;
        } else {
            log.error(">>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            log.error(">>>>>>>>> ERROR !!! msgKey Empty <<<<<<<<<<<<<");
            log.error(">>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }

		try {
			if (!"AFF_ORD".equals(ord.getOrdTpCd())) {//제휴사 주문이 아니면 문자메일보낸다.
				try {
//					memberBaseCommandService.sendSmsEmail(null, null, infMail, systemPK);
				} catch(Exception e){
					log.error(">>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<");
					log.error(">>>>>>>>> ERROR !!! sending order mail <<<<<<<<<<<<<");
					log.error("", e);
					log.error(">>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				}

				if ("KOR".equalsIgnoreCase(ord.getLangCd()) && sendFlag) {
					// 알림처리
				}
			}

			//개인정보 이용 이력 저장
			insertMemberPersonalInfo(systemPK, ord);
		} catch (Exception e) {
			log.error(">>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			log.error(">>>>>>>>> ERROR !!! sending order sms <<<<<<<<<<<<<");
			log.error("", e);
			log.error(">>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		}
	}

	
	
	/**
	 * 개인정보 이용 이력 저장
	 * @param systemPK
	 * @param ord
	 * @throws Exception
	 */    
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public void insertMemberPersonalInfo(SystemPK systemPK, Ord ord) throws Exception {
		
		// step 6. 개인정보이용이력 등록 -- TODO 개인정보 이용이력의 이용 업무 상세코드에서 탈퇴 없음.
		String[][] useMmsInfo = { // 구분, 업무, 업무상세
				{ UsefSectCd.PSNL_INFO_THPR_OFFER_DETL.toString(), UsefJobCd.SND_KKO_NTCN_TAK.toString(), UsefJobDetail.CLM_CANCEL_PAY.toString()}
		};
		String[] target = { ord.getMbrNo() };
		memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK, useMmsInfo , target , null, null , ord.getMbrNo() );
		
		
		
		String[][] useEmailInfo = { // 구분, 업무, 업무상세
		//		{ UsefSectCd.PSNL_INFO_THPR_OFFER_DETL.toString(), UsefJobCd.SND_EMAIL.toString(), UsefJobDetail.CLM_CANCEL_PAY.toString()}
				{ UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_EMAIL.toString(), UsefJobDetail.CLM_CANCEL_PAY.toString()}
		};		
		memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK, useEmailInfo , target , null, null , ord.getMbrNo() );
		

	}



	/**
	 * as-is 의 claim.java 에서 최상단의 erp 전송 대상조회쿼리
	 */
	@Override
	@Transactional(value = "transactionManager")
    public List<ClaimBatchTargetDTO> selectClaimTargetList(ClaimBatchSearchDTO claimBatchSearchDTO) throws Exception {
		return claimBatchService.selectClaimTargetList(claimBatchSearchDTO);
    }

	@Override
	@Transactional(value = "transactionManager")
	public void updateClmErpTrnsmis(ClmErpTrnsmis totalCancelClmErpTrnsmis){
		claimBatchService.updateClmErpTrnsmis(totalCancelClmErpTrnsmis);
	}

	@Override	
	@Transactional(value = "transactionManager")
	public void updateOrdErpTrnsmis(ClmErpTrnsmis totalCancelClmErpTrnsmis){
		claimBatchService.updateOrdErpTrnsmis(totalCancelClmErpTrnsmis);
	}

	@Override
	@Transactional(value = "transactionManager")
    public void updateClmErpWthdraw(ClaimBatchTargetDTO dto) {
		claimBatchService.updateClmErpWthdraw(dto);
    }
	
	
    /**
     * AdapterHeader 값 설정
     * @return
     */
    /*private AdapterHeader setAdapterHeader(){
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");		
		return adapterHeader;
    }*/
	

	@Override
	@Transactional(value = "transactionManager")
	public void updateClmStatCd(Clm clm) throws Exception{
		claimService.updateClmStatCd(clm);
	}
	
	/**
	 * <pre>
	 * #23144 [주문모듈]Off-line 매장 온라인 주문 반품 BO 개발
	 * 	- '픽업프로세스' 개선 - 반품 배치프로세스 통합
	 *
	 * 	- 매장 반품처리 시 기존 BO 반품접수와는 달리 아래와 같이 데이터 등록이 필요함
	 * 		1. lgs_rtrvl_drct_god.rtrvl_stat_cd = 'RTRVL_COMPT' - 회수상태코드 - 회수완료
	 *		   lgs_rtrvl_drct_god.rtrvl_shop_id = '회수매장ID' - 기존에는 'X30004'이나 배송매장ID 로 세팅되나 접수 매장에 재고를 생성하기 위함
	 *		   lgs_rtrvl_drct_god.erp_inv_trnsmis_sect_cd = 'SHOP_INV' - 매장재고
	 * 		2. clm_erp_trnsmis.clm_erp_trnsmis_tp_cd = 'UNITY_PNT_ACCML_TMPR' - '통합 포인트 적립 임시'
	 * 		   clm_erp_trnsmis.erp_trnsmis_cd = 'X' - '해당사항없음'으로 처리
	 *		3. inf_ord_god_erp_dstb.clm_erp_intrlck_yn = 'N' 또는 'E' - 'N'전송대상이지만 미전송, 'E'전송실패
	 * </pre>
	 *
	 * @param clmNo 신규 반품 처리될 클레임번호함
	 * @throws Exception
	 */
	@Override
	@Transactional(value = "transactionManager")
	public void execShopRtnClmProc(String clmNo) throws Exception {
		/*
		 * 1-반품과 교환 회수지시대기를 회수지시상태로 변경배치
		 * 	- RtrvlDrctGodBatchReader.getRtrvlDrctGodBatchReader
		 * 	- LGS_RTRVL_DRCT_GOD 테이블의 회수상태코드('RTRVL_STAT_CD')를 회수지시('RTRVL_DRCT')로 수정하는 것으로,
		 * 	  반품접수 시 회수완료('RTRVL_COMPT')까지 처리하므로 생략
		 */

		/*
		 * '반품' 처리될 클레임 정보 조회
		 */
		ClaimReturnResult clmRtnRslt = claimBatchService.selectClaim4Return( clmNo );

		if ( log.isInfoEnabled() ) {
			log.info("Execute claim process [{}::{}::{}::{}::{}::{}::{}::{}::{}::{}::{}::{}]",
					clmRtnRslt.getClmNo(), clmRtnRslt.getClmTpCd(), clmRtnRslt.getOrdNo(),
					clmRtnRslt.getOrdTpCd(), clmRtnRslt.getRepayAmt(), clmRtnRslt.getErpTempErpInc(), clmRtnRslt.getOrdErpOrErpYn(),
					clmRtnRslt.getPgSectCd(), clmRtnRslt.getPgStoreId(), clmRtnRslt.getMallId(), clmRtnRslt.getLangCd(), clmRtnRslt.getMbrNo() );
		}

		// 결제금액과 환불금액 체크
		if ( clmRtnRslt.getRepayAmt().intValue() < 0 ) {
			if (log.isInfoEnabled()) {
				log.info("Insufficient amount of the refund.[{}::{}]",
						clmRtnRslt.getPayCrncyPayAmt(), clmRtnRslt.getPayExchgRtCrncySumAmt());
				throw new ClaimCompleteFailException("Claim.rtgod.insufficient.refund", null);
			}
		}

		/*
		 * 3-반품 회수완료후 환불처리 (상태변경없음)
		 * 	- ReturnCmplReader.returncmpl
		 * 	- ReturnReader.returnToErp의 '접수' 단계에서의 적립포인트 임시삭감, '완료' 단계에서의 적립포인트 복원을 진행 안함
		 * 	  따라서 아래 '일반주문'의 '적립금액 임시차감(Z_RFC_FSPERP_BP_CB_TEM_DEL) 아직 'N' 이면 완료 처리 안함' 조건이 의미없음
		 */
		/*
		// 제휴사 주문인 경우
		if ( StringService.equalsIgnoreCase( clmRtnRslt.getOrdTpCd(),
				OrderEnum.ORD_TP_CD_AFF_ORD.toString() ) ) {
			this.returnCmplBatchRepayToDB(clmRtnRslt.getClmNo());
		}
		else {
			*//*
			 * '일반주문' 이면서,
			 * 적립금액 임시차감(Z_RFC_FSPERP_BP_CB_TEM_DEL) 아직 'N' 이면 완료 처리 안함
			 *//*
			if ( ! StringService.equalsIgnoreCase("N", clmRtnRslt.getErpTempErpInc()) ) {
				this.returnCmplBatchRepayToDB(clmRtnRslt.getClmNo());
			}
		}*/

		this.returnCmplBatchRepayToDB(clmRtnRslt.getClmNo());

		/*
		 * 2, 4-반품 처리배치 (접수/완료)
		 * 	- ReturnReader.returnToErp
		 * 	- 클레임상태 '완료'(COMPT) 기능 적용
		 */
		ClaimBatchSearchDTO searchDTO = new ClaimBatchSearchDTO();
		searchDTO.setClmTpCd("RTGOD");
		/*
		 * 특정 클레임에대한 '반품' 처리만 대상으로 처리함으로, '클레임번호' 조건 추가
		 */
		searchDTO.setClmNo(clmRtnRslt.getClmNo());

		List<ClaimBatchTargetDTO> tgtClmList = this.selectClaimTargetList(searchDTO);

		/*
		 * 1개의 클레임 처리이지만, ReturnReader.returnToErp 의 로직을 모방해서 그대로 사용
		 * 	- '클레임상태코드'(CLM_STAT_CD) 는 위 'returnCmplBatchRepayToDB' 메소드 호출 시 '완료'(COMPT)로 처리하므로
		 * 	  추가적인 '클레임상태코드' 체크 없음
		 */
		for ( ClaimBatchTargetDTO clmDto : tgtClmList ) {

			if ( log.isInfoEnabled() ) {
				log.info("Execute claim process(return to erp) [{}::{}::{}::{}::{}::{}::{}::{}::{}::{}]",
						clmDto.getClmNo(), clmDto.getClmTpCd(), clmDto.getClmStatCd(), clmDto.getOrdNo(),
						clmDto.getOrdTpCd(), clmDto.getBpNo(), clmDto.getErpTempErpInc(),
						clmDto.getOrdErpOrErpYn(), clmDto.getErpReErpYn(), clmDto.getMbrEmplNo() );
			}

			ClmErpTrnsmis clmErpTrnsmis = new ClmErpTrnsmis();
			clmErpTrnsmis.setClmNo(clmDto.getClmNo());
			clmErpTrnsmis.setOrdNo(clmDto.getOrdNo());

		}
	}

	/**
	 * <pre>
	 * '클레임철회' 시 이후 프로세스 처리
	 * </pre>
	 *
	 * @param clmNo 클레임번호
	 * @param clmTpCd 클레임 유형 - 반품('RTGOD'), 교환('GNRL_EXCHG')
	 * @throws Exception
	 */
	@Override
	@Transactional(value = "transactionManager")
	public void execWthdrawProc(String clmNo, String clmTpCd) throws Exception {

		ClaimBatchSearchDTO searchDTO = new ClaimBatchSearchDTO();
		searchDTO.setClmTpCd(clmTpCd);
		/*
		 * 특정 클레임에 대해 처리함으로, '클레임번호' 조건 추가
		 */
		searchDTO.setClmNo(clmNo);

		List<ClaimBatchTargetDTO> tgtClmList = this.selectClaimTargetList(searchDTO);

		for ( ClaimBatchTargetDTO clmDto : tgtClmList ) {
			if ( log.isInfoEnabled() ) {
				log.info("Execute 'WTHDRAW' process [{}::{}::{}::{}::{}::{}::{}::{}::{}::{}]",
						clmDto.getClmNo(), clmDto.getClmTpCd(), clmDto.getClmStatCd(), clmDto.getOrdNo(),
						clmDto.getOrdTpCd(), clmDto.getBpNo(), clmDto.getErpTempErpInc(),
						clmDto.getOrdErpOrErpYn(), clmDto.getErpReErpYn(), clmDto.getMbrEmplNo() );
			}

			/*
			 * 적립금 임시 차감 코드가 R이면(임시차감 했으면)
			 * 	- 적립금 임시차감 원복
			 */
			if ( StringService.equalsIgnoreCase("R", clmDto.getErpTempErpInc()) ) {
				
			}

			ClmErpTrnsmis clmErpTrnsmis = new ClmErpTrnsmis();
			clmErpTrnsmis.setClmNo(clmDto.getClmNo());
			clmErpTrnsmis.setOrdNo(clmDto.getOrdNo());

			clmErpTrnsmis.setClmErpTrnsmisTpCd("ERP_RE_ERP");
			clmErpTrnsmis.setErpTrnsmisCd("X");
			this.updateClmErpTrnsmis(clmErpTrnsmis);

			clmErpTrnsmis.setClmErpTrnsmisTpCd("RTL_TRNSMIS");
			clmErpTrnsmis.setErpTrnsmisCd("X");
			this.updateClmErpTrnsmis(clmErpTrnsmis);

			clmErpTrnsmis.setClmErpTrnsmisTpCd("UNITY_PNT_ACCML_TMPR");
			clmErpTrnsmis.setErpTrnsmisCd("X");
			this.updateClmErpTrnsmis(clmErpTrnsmis);

			this.updateClmErpWthdraw(clmDto);
		}
	}
	
	/**
	 * 온오프쿠폰 임시사용 복원
	 * 
	 * @param ordNo
	 * @param orderType
	 * @param cpnStatCd
	 * @return
	 */
	public List<OrdErpTrnsmis> sendFgifOfferUseTemp(String ordNo, String orderType, String cpnStatCd) {
//		return orderSelectService.sendFgifOfferUseTemp(ordNo, orderType, cpnStatCd);
		return null;
	}

	@Override
	public int selectRcptnoNullCntByClmNo(String clmNo) throws Exception {
		return claimService.selectRcptnoNullCntByClmNo(clmNo);
	}

}
