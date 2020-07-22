/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 17
 */
package com.plgrim.ncp.cmp.orderfulfilment.bo.claim;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmExtend;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGod;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodAplPrm;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInvExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdClmGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrm;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.enums.DeliveryEnum;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.GoodsEnum.GoodsType;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.base.enums.WebPointEnum;
import com.plgrim.ncp.biz.claim.data.ClaimBoDTO;
import com.plgrim.ncp.biz.claim.data.ClaimOrdGftSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClaimReceiptDTO;
import com.plgrim.ncp.biz.claim.data.ClaimSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClmDlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.claim.data.ClmOrdGodAplPrm;
import com.plgrim.ncp.biz.claim.data.RefundPayDTO;
import com.plgrim.ncp.biz.claim.exception.ClaimCompleteFailException;
import com.plgrim.ncp.biz.claim.repository.ClaimRepository;
import com.plgrim.ncp.biz.claim.service.ClaimBatchService;
import com.plgrim.ncp.biz.claim.service.ClaimService;
import com.plgrim.ncp.biz.delivery.data.DeliveryInfErpDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryPayClaimDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.data.LgsDlvspBoDTO;
import com.plgrim.ncp.biz.delivery.data.LgsDlvspPkupDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.service.DeliveryInfErpExternalService;
import com.plgrim.ncp.biz.delivery.service.DeliveryRuleService;
import com.plgrim.ncp.biz.delivery.service.DeliveryService;
import com.plgrim.ncp.biz.delivery.service.DeliveryStatusService;
import com.plgrim.ncp.biz.goods.data.GoodsInventoryDTO;
import com.plgrim.ncp.biz.goods.service.GoodsService;
import com.plgrim.ncp.biz.interfaces.data.InfOrdGodErpDstbClmSearchDTO;
import com.plgrim.ncp.biz.interfaces.data.InfOrdGodErpDstbUpdateDTO;
import com.plgrim.ncp.biz.interfaces.data.OrdGodErpDTO;
import com.plgrim.ncp.biz.interfaces.service.InfOrdGodErpDstbService;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitCommandService;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.biz.order.data.OrderParamDTO;
import com.plgrim.ncp.biz.order.repository.OrderSelectRepository;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmDetailResult;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmResult;
import com.plgrim.ncp.biz.order.service.OrderBoSelectService;
import com.plgrim.ncp.biz.order.service.OrderBoService;
import com.plgrim.ncp.biz.order.service.OrderCommandService;
import com.plgrim.ncp.biz.order.service.OrderEntitySetService;
import com.plgrim.ncp.biz.order.service.OrderInterfaceService;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.biz.pay.service.PayBoService;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.OrderCommandComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.OrderSelectComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.exception.OrderCompleteFailException;
import com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent;
import com.plgrim.ncp.commons.data.order.DeliveryClaimDTO;
import com.plgrim.ncp.commons.service.MailHtmlReaderService;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.interfaces.delivery.adapter.DeliveryAdapter;
import com.plgrim.ncp.interfaces.delivery.data.DlivyDrctSDO;
import com.plgrim.ncp.interfaces.delivery.data.DlivyWmsRetrievalSDO;
import com.plgrim.ncp.interfaces.delivery.data.DlivyWmsSDO;
import com.plgrim.ncp.interfaces.email.adapter.EmailAdapter;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Transactional(value = "transactionManager")
@Component
public class ClaimCommandComponentImpl extends AbstractComponent implements ClaimCommandComponent {
 
	@Autowired
	private ClaimService claimService;

	@Autowired
	private ClaimBatchService claimBatchService;

	@Autowired
	private DeliveryStatusService deliveryStatusService;

	@Autowired
	private DeliveryInfErpExternalService deliveryInfErpExternalService;

	@Autowired
	private InfOrdGodErpDstbService infOrdGodErpDstbService;

	@Autowired
	private OrderBoService orderBoService;

	@Autowired
	private OrderSelectService orderSelectService;

	@Autowired
	private OrderCommandService orderCommandService;

	@Autowired
	private OrderBoSelectService orderBoSelectService;

	@Autowired
	private DeliveryService deliveryService;

	@Autowired
	private PayBoService payBoService;

	@Autowired
	private PromotionService promotionService;

	@Autowired
	@Qualifier("sessionTemplate1")
	private SqlSession sqlSession1;

	@Autowired
	private InterfaceApiCommon interfaceApiCommon;

	@Autowired
	private DeliveryRuleService deliveryRuleService;

	@Autowired
	private OrderEntitySetService orderEntitySetService;

	@Autowired
	private ClaimRepository claimRepository;
	
	@Autowired
	private MemberBaseSelectService memberBaseSelectService;
	
	@Autowired
	private MemberBenefitCommandService memberBenefitCommandService;
	
	@Autowired
    private GoodsInventoryFOComponent goodsInventoryFOComponent;
	
	@Autowired
	private DeliveryCommandRepository deliveryCommandRepository;
	
	@Autowired
	private DeliveryAdapter deliveryAdapter;
	
	@Autowired
	private OrderCommandComponent orderCommandComponent;
	
	@Autowired
	private RefundPayComponent refundPayComponent;
	
	@Autowired
	MailHtmlReaderService mailHtmlReaderService;
	
	@Autowired
	EmailAdapter emailAdapter;
	
	@Autowired
	OrderSelectRepository orderSelectRepository;
	
	@Autowired
	OrderInterfaceService orderInterfaceService;
	
	@Autowired
	OrderSelectComponent orderSelectComponent;
	
	@Autowired
	ClaimSelectComponent claimSelectComponent;
	
	@Autowired
	GoodsService goodsService;
	
	/*
	 * 클레임 주문 전체취소 접수/완료.
	 * 
	 * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimCommandComponent#updateClaimTotalCancel(javax.servlet.http.HttpServletRequest, com.plgrim.ncp.biz.claim.data.ClaimSearchDTO, com.plgrim.ncp.framework.data.SystemPK, java.lang.String)
	 */
	@Override
	public String updateClaimTotalCancel(ClaimSearchDTO claimSearchDTO,SystemPK systemPK) throws Exception{

		try {
			
			/****************************************************************************************************************
			 * 중복 접수 방지를 위한 테이블 lock처리 (nowait)
			 *  - 주문번호 단위로 erp 분배 테이블에 lock을 잡는다.
			 ****************************************************************************************************************/
			try {
				claimRepository.selectOrdGodErpForUpdate(claimSearchDTO.getOrdNo());
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ClaimCompleteFailException("Claim.already.apply", null);
			}
			
			String ordNo = claimSearchDTO.getOrdNo();
			Ord ord = orderSelectService.selectOrdEntity(ordNo);
			
			String ordStatCd = ord.getOrdStatCd();
			String ordTpCd = ord.getOrdTpCd();
			boolean hasTempMileuse = false;
			boolean isDepstWait = false;
			
			if ("DEPST_WAIT".equals(ordStatCd)) {
				isDepstWait = true;
			}
					
			if ("DEPST_WAIT".equals(ordStatCd) && ord.getUnityPntUseSumAmt().compareTo(BigDecimal.ZERO) > 0) {
				hasTempMileuse = true;
			}
			
			/****************************************************************************************************************
			 * 클레임 검증
			 * 	1. 출고완료 및 배송완료 상품이 있는지 체크하여, 있을경우 예외처리
			 *  2. 기 접수된 클레임이 있을 경우 예외처리
			 ****************************************************************************************************************/
			List<DlvOrdGodInfoDTO> infoList =  new ArrayList<DlvOrdGodInfoDTO>();
			DlvOrdGodInfoDTO dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();
			dlvOrdGodInfoDTO.setOrdNo(ordNo);
			infoList.add(dlvOrdGodInfoDTO);
			
			String deliveryYn = deliveryStatusService.checkDeliveryYnAboutOrder(infoList);
			
			if("Y".equals(deliveryYn)){
				throw new ClaimCompleteFailException("Claim.complete.product.delivery_order", null);
			}

			// 2.클레임 접수건이 있는지 체크 - 철회되지 않은 클레임이 있을 경우 예외처리 
			int clmCnt = claimService.checkExistClmRceptCompt(claimSearchDTO);
			if(clmCnt!=0){
				throw new ClaimCompleteFailException("Claim.already.apply", null);
			}

			ClaimReceiptDTO claimReceiptDTO = new ClaimReceiptDTO();
			claimReceiptDTO.setAdminId(claimSearchDTO.getAdminId());
			claimReceiptDTO.setUdterId(claimSearchDTO.getUdterId());
			claimReceiptDTO.setOrdNo(ordNo);
			claimReceiptDTO.setClmResnCd(claimSearchDTO.getClmResnCd());
			claimReceiptDTO.setClmResnCont(claimSearchDTO.getClmResnCont());

			if("SHOP_PKUP_ORD".equals(ord.getOrdTpCd())){
				claimReceiptDTO.setPkupClmYn("Y");
			}else{
				claimReceiptDTO.setPkupClmYn("N");
			}

			claimReceiptDTO.setPgTrnsmisTgtYn("Y");

			// 환불계좌 암호화
			String clmNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_CLM", "CL", DatabaseType.ORACLE);
			if(claimSearchDTO.getRfdBnkAcctNo()!=null && !"".equals(claimSearchDTO.getRfdBnkAcctNo())){

				claimReceiptDTO.setRfdBnkCd(claimSearchDTO.getRfdBnkCd());
				claimReceiptDTO.setRfdAcnthldrNm(claimSearchDTO.getRfdAcnthldrNm());
				claimReceiptDTO.setRfdBnkAcctNo(claimSearchDTO.getRfdBnkAcctNo());
			}
			
			claimReceiptDTO.setClmStatCd("RCEPT"); //접수 RCEPT
			claimReceiptDTO.setClmTpCd("ALL_CNCL"); //전체취소 ALL_CNCL
	        /*
	         * 취소사유는 as-is에서 단순변심 SIMPL_CHGMIND
	         * 	- 미결제 시 '자동주문취소' 배치에서는 'PAY_DELAY'(결제지연) 으로 입력
	         */
			claimReceiptDTO.setClmResnCd( StringService.isNotEmpty( claimReceiptDTO.getClmResnCd() )
					? claimReceiptDTO.getClmResnCd() : "CSTMR_CHGMIND_CNCL");
			
			claimReceiptDTO.setClmNo(clmNo);
			claimService.insertClmByOrdGenClmNo(claimReceiptDTO); //클레임 테이블 insert

			//4.클레임취소상품 테이블에 등록 & 주문 클레임 상품 연결 테이블에 등록
			List<OrdGod> clmOrdGodList = claimService.selectOrdGodListForAllCancel(ordNo);
			for(OrdGod ordGod : clmOrdGodList){
				claimService.createClmWrhsGodForCancelClaim(ordNo, clmNo, ordGod.getOrdGodTurn(), ordGod.getOrdQty(), claimSearchDTO.getClmResnCd(), claimSearchDTO.getClmResnCont());
			}

			// 클레임 god_sumry 등록
			this.clmSumryUpdate(clmNo);

			//클레임 입고상품 적용 프로모션 테이블 insert 필요 (주문할 당시의 row 그대로 select insert)
			claimService.insertClmWrhsGodAplPrmByOrd(claimReceiptDTO);

			/**
			 * 클레임메모 저장
			 * MemoTpCd("CLM")
			 * ExpsrYn("N")
			 */
			
			CsoCnsltMemo csoCnsltMemo = new CsoCnsltMemo();
			csoCnsltMemo.setRegtrId(claimSearchDTO.getUdterId());
			csoCnsltMemo.setMemoTpCd("CLM");
			csoCnsltMemo.setExpsrYn("Y");
			csoCnsltMemo.setClmNo(clmNo);
			csoCnsltMemo.setMemoCont(claimSearchDTO.getMemoCont());

			if("B".equals(claimSearchDTO.getRole())){
				claimService.updateCsoCnsltMemo(csoCnsltMemo);
			}

			//to-be 인터페이스 주문상품erp 분배 테이블 update 해당 주문번호의 모든 주문상품단위 로  clmNo, clmWrhsGodTurn 를 update
			InfOrdGodErpDstbUpdateDTO infOrdGodErpDstbDTO = new InfOrdGodErpDstbUpdateDTO();
			infOrdGodErpDstbDTO.setOrdNo(ordNo);
			infOrdGodErpDstbDTO.setClmNo(clmNo);
			infOrdGodErpDstbService.updateInfOrdGodErpDstbByClm(infOrdGodErpDstbDTO);

			//6.클레임 취소 배송비 등록 update
			DeliveryPayClaimDTO deliveryPayClaimDTO = new DeliveryPayClaimDTO();
			deliveryPayClaimDTO.setOrdNo(ordNo);//전체취소는 주문번호만 보낸다.
			deliveryPayClaimDTO.setClmNo(clmNo);//update 나 insert 할때 클레임번호를 넣어준다.
			deliveryPayClaimDTO.setAdminId(claimReceiptDTO.getAdminId());//클레임접수자 id
			
			//주문배송지 테이블 update 이력테이블 insert
			deliveryService.updateClmDlvspCancelPay(deliveryPayClaimDTO);
			deliveryService.insertClmDlvspCancelPayHist(deliveryPayClaimDTO);
			
			//주문배송 테이블 update 이력테이블 insert
			deliveryService.updateClmDlvCancelPay(deliveryPayClaimDTO);//주문배송 > 배송비 0원으로 update
			deliveryService.insertClmDlvCancelPayHist(deliveryPayClaimDTO);//주문배송 > 배송비 0원으로 update

			InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
			infOrdGodErpDstbClmSearchDTO.setOrdNo(ordNo);
			infOrdGodErpDstbClmSearchDTO.setClmNo(clmNo);
			List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
		
			// 취소로 인한 웹포인트 처리
			for(InfOrdGodErpDstb infOrdGodErpDstb : infOrdGodErpDstbCheckList){
				
				if(ord.getMbrTpCd().equals(MemberEnum.MemberTpCd.UNITY_MBR.toString())) {	            
					
		            MbrWebpntHist param = new MbrWebpntHist();
		            param.setWebpntTpCd(WebPointEnum.WEBPNT.toString()); // 웹포인트
		            param.setWebpntResnCd(WebPointEnum.WebPntResnCd.PCH.toString()); // PCH : 구매
		            param.setMbrNo(ord.getMbrNo());
		            param.setOrdNo(ordNo);
		            param.setOrdGodTurn(infOrdGodErpDstb.getOrdGodTurn());
		            param.setQtyTurn(infOrdGodErpDstb.getQtyTurn());
		            param.setClmNo(clmNo);
		            param.setClmWrhsGodTurn(infOrdGodErpDstb.getClmWrhsGodTurn());
		            param.setMallId(ord.getMallId());
	
		            /*웹포인트 사용 (즉시차감) */
		            if(null!=infOrdGodErpDstb.getWebpntAccmlUntPrc()){
			            if (infOrdGodErpDstb.getWebpntAccmlUntPrc().intValue() > 0) {
			                param.setWebpntDetailResnCd(WebPointEnum.WebPntPchDtlResnCd.PCH_CANCL_DDCT.toString()); // 취소로 인한 차감
			                param.setWebpntAmt(infOrdGodErpDstb.getWebpntAccmlUntPrc());//웹포인트 적립금
			                param.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_CNCL.toString());//적립 취소
			                param.setResnDscr("클레임 으로 적립금액 차감");
			                memberBenefitCommandService.insertWebPoint(param);
			            }	            	
		            }
	
		            /* 웹포인트 적립예정*/
		            if(null!=infOrdGodErpDstb.getWebpntUseUntPrc()){
			            if (infOrdGodErpDstb.getWebpntUseUntPrc().intValue() > 0 ) {
			                param.setWebpntDetailResnCd(WebPointEnum.WebPntPchDtlResnCd.PCH_CANCL_ACCML.toString()); //취소로 인한 적립 
			                param.setWebpntAmt(infOrdGodErpDstb.getWebpntUseUntPrc());
			                param.setWebpntStatCd(WebPointEnum.WebPntStatCd.USE_CNCL.toString());//사용취소
			                param.setResnDscr("클레임 으로 사용금액 적립");
			                memberBenefitCommandService.insertWebPoint(param);
			            }
		            }
		        }
			}
			
			Ord statUpdateOrd = new Ord();
			statUpdateOrd.setOrdNo(ordNo);
			statUpdateOrd.setOrdStatCd("ALL_CNCL");//주문테이블 주문상태 : 전체취소

			// 자동취소아닌 취소도 미결제대기 상태이면 해당값 업데이트
			if("DEPST_WAIT".equals(ord.getOrdStatCd())){ 
				statUpdateOrd.setDepstWaitCnclYn("Y");
			}
	 		
			// 주문 마스터상태 및 입금대기취소여부 업데이트
			orderBoService.updateOrdStatCd(statUpdateOrd);

			/****************************************************************************************************************
			 * 온라인 재고 처리
			 * 1. 예약 주문 -> 예약재고 차감
			 * 2. WMS 배송 -> 판매예정수량 차감
			 * 3. 매장 배송, 매장수령주문 -> RT처리로 ERP내 실시간 재고 차감 처리
			 * 
			 *  미결제의 경우 주문시점에 재고 차감을 하지 않았으므로 스킵처리
			 ****************************************************************************************************************/
			if("DEPST_WAIT".equals(ordStatCd) == false && OrderClaimEnum.ORD_TP_CD_LAG_QTY_ORD.toString().equals(ordTpCd) == false){ 
				List<GodShopItmInvExtend> godShopItmInvExtendList =  deliveryService.selectOrdGodShopItmQty(dlvOrdGodInfoDTO);
				this.updateCancelGoodsInventory(ord, godShopItmInvExtendList);
			}

			

			/****************************************************************************************************************
			 * 출고지시 취소 처리
			 * 1. 취소 처리 조건 : WMS배송, 출고지시상태, 출고지시취소상태가 없어야함(BO에서 출고지시취소처리를 이미 하지 않은 경우만) 
			 ****************************************************************************************************************/
			
			DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
			deliverySearchDTO.setClmNo(clmNo);
			deliverySearchDTO.setOrdNo(ordNo);
			
			// 출고상품 리스트 조회
			List<DeliveryInfErpDTO> dlist = deliveryInfErpExternalService.selectDlivyDrctInfoListByClm(deliverySearchDTO);

			List<DlivyDrctSDO> dlivyCnclList = new ArrayList<DlivyDrctSDO>();
			
			for (DeliveryInfErpDTO dto : dlist) {
				
				DeliverySearchDTO dlivyCnclDTO = new DeliverySearchDTO();
				
				dlivyCnclDTO.setCancelYn("Y");
				dlivyCnclDTO.setOrdNo(ordNo);
				dlivyCnclDTO.setDlvPcupspTurn(dto.getDlvPcupspTurn());
				dlivyCnclDTO.setDlvTurn(dto.getDlvTurn());
				dlivyCnclDTO.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());

				DlivyDrctSDO dlivyCncl = deliveryCommandRepository.selectWmsDlivyDrctTargetList(dlivyCnclDTO);
				dlivyCnclList.add(dlivyCncl);
			}
			
			if (dlivyCnclList.isEmpty() == false) {
				try{
					
					DlivyWmsSDO dlivyWmsSDO = new DlivyWmsSDO();
					dlivyWmsSDO.setReleaseList(dlivyCnclList);
					
					AdapterHeader header = new AdapterHeader();
					header.setRequestId(interfaceApiCommon.getRequestId());
					header.setMallId(systemPK.getMall());
					header.setLangCd("KOR");
					header.setDvcCd(systemPK.getDevice());
					
					dlivyWmsSDO.setCallerId(this.getClass().getSimpleName() + this.getClass().getDeclaredMethods()[0].getName());
					
					String returnData = deliveryAdapter.sendWMSDlivyDrct(dlivyWmsSDO, header);
					
					DlivyWmsSDO dlivyWmsSDOReturn = (DlivyWmsSDO) JsonService.unmarshalling(returnData, DlivyWmsSDO.class);
					
					if("200".equals(dlivyWmsSDOReturn.getResponseCd()) == false) {
						throw new ClaimCompleteFailException("Claim.fail.dlivydrctcncl", null);
					}
						
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					throw e;
				}
			}
			
			//DB 에도 출고지시 취소처리 해준다.
			deliveryService.updateClmDlvDrctGodCancel(deliveryPayClaimDTO);
			
				    	
	    	
	    	Clm clm = new Clm();
	    	clm.setClmNo(clmNo);
	    	clm = claimBatchService.selectClmByClmNo(clm);
	    	
	    	this.refundPayForClaim(clm, ord, isDepstWait, hasTempMileuse, false);
		
		    return clmNo;
		
		} catch(ClaimCompleteFailException e1) {
	    	 log.error("TotalCancel Fail" + " exception : {}", claimSearchDTO);
	      	 log.error("TotalCancel Fail" + " exception : {}", e1);
	  	     log.error(CommonResponseCode.CL_40001_취소_접수_실패.toMessage() + " / "+e1.getDirectMessage());
	    	 throw e1;
		} catch (Exception e2) {
	      log.error("TotalCancel Fail" + " exception : {}", claimSearchDTO);
	   	  log.error("TotalCancel Fail" + " exception : {}", e2);
	   	  log.error(CommonResponseCode.CL_40001_취소_접수_실패.toMessage() + " / "+e2.getMessage());
	   	  throw e2;		    
		}       	
	}

	/**
	 * 클레임 결제 환불 처리
	 * 
	 * 1. 결제 목룍을 조회하여 수단별 환불처리
	 * 2. 매출취소 및 임시마일리지 전송
	 * 
	 * @param ordNo
	 * @param ord
	 * @param ordStatCd
	 * @param hasTempMileuse
	 * @param clmNo
	 * @param pay
	 * @param clm
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean refundPayForClaim(Clm clm, Ord ord, boolean isDepstWait, boolean hasTempMileuse, boolean isRefundReprocess) throws Exception {
    	
		/**
		 * 환불 처리
		 */
    	PayExtend pay = new PayExtend();
    	
    	pay.setOrdNo(clm.getOrdNo());//주문번호로
    	pay.setPayTpCd("ORD");//주문시 생성된
    	
		// 결제 리스트 조회
    	List<PayExtend> ordPayList = new ArrayList<PayExtend>();
    	
    	if (isRefundReprocess) {	// 환불 실패건 재처리일 경우
    		
    		// 클레임 정보 중 환불계좌 문제로 수정한 경우가 있어서, 환불데이터로 계좌정보를 이관해준다.
    		claimRepository.updatePayRfdBnkInfo(clm);
    		
    		ordPayList = claimRepository.selectOrdClmRefundPayReprocess(clm);
    	} else {
    		ordPayList = payBoService.selectOrdClmRefundPayExtend(pay);
    	}
    	
		int errorCnt = 0;
		
		boolean isClmCompt = false;
		
		for(PayExtend payDto : ordPayList){
			
			boolean isRfdCompt = true;
			
			// 이미 기환불 된 경우는 skip. 
			// 환불 대상 데이터는 주문/클레임 구분이 주문인 결제 데이터를 전부 가져오는데 일반배송전환 재결제 및 결제수단 변경 등 최초 주문 결제가 전체 환불된 경우 의미없는 0원환불 데이터가 생성된다. 이를 방지하기 위해 추가함
			if (payDto.getStdrCrncyPayAmt().compareTo(payDto.getCnclAcmtlAmt()) == 0) {
				continue;
			}
			
			RefundPayDTO refundPayDTO = this.makeDTOForRefundPay(ord, clm, payDto, true);
			
			if (isRefundReprocess) {	// 환불 재처리시는 기 생성한 데이터의 결제번호를 신규결제번호로 세팅
				refundPayDTO.setNewPayNo(payDto.getNewPayNo());
			}
			
			if("PAY_COMPT".equals(payDto.getDealTpCd())){
				
				isRfdCompt = refundPayComponent.refundPayTransaction(refundPayDTO, clm, isRefundReprocess);
				
			}else if("VIRTL_BNK_ACCT_PAY".equals(payDto.getPayMnCd())&&"DEPST_WAIT".equals(payDto.getDealTpCd())){
				if("Y".equals(payDto.getMpayMnYn())){
					
					isRfdCompt = refundPayComponent.refundPayTransaction(refundPayDTO, clm, isRefundReprocess);
					
				} else{
				    continue;
				}
		    		        			
			}else if("NON_BNKBOK_PAY".equals(payDto.getPayMnCd())&&"PAY_WAIT".equals(payDto.getDealTpCd())){
				isDepstWait = true;
			}
			
			if (isRfdCompt == false) {
				errorCnt++;
			}
		}
		
		// 환불 완료인 경우 클레임 완료처리
		if(errorCnt==0){
			
			Clm statDTO = new Clm();
			statDTO.setClmNo(clm.getClmNo());
			statDTO.setClmStatCd("COMPT");
			claimService.updateClmStatCd(statDTO);	   
			
			isClmCompt = true;
			/**
			 * 매출취소, 마일리지 임시사용 취소 전송
			 * TODO : 교환반품전환시에는 교환 출고품에 대한 매출취소처리를 보내도록 변경하자.
			 */
			this.sendSalesInfoToErp(clm.getOrdNo(), clm.getClmNo(), ord.getMallId(), isDepstWait, hasTempMileuse);
		}
		
		
		return isClmCompt;
	}

	/**
	 * 클레임 ERP 전송처리
	 * 
	 * @param ordNo
	 * @param ordStatCd
	 * @param hasTempMileuse
	 * @param clmNo
	 */
	@Override
	public void sendSalesInfoToErp(String ordNo, String clmNo, String mallId, boolean isDepstWait, boolean hasTempMileuse) {
		
		AdapterHeader adapterHeader = new AdapterHeader();
		
		try {
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(ContextService.getCurrentRequest());
			
			adapterHeader.setMallId(systemPK.getMall());
			adapterHeader.setLangCd("KOR");
			adapterHeader.setDvcCd(systemPK.getDevice());
		} catch (Exception e) {	// systemPK생성 실패시 (batch서버)
			//mallId
			//adapterHeader.setMallId("DXM");
			adapterHeader.setMallId(mallId);
			adapterHeader.setLangCd("KOR");
			adapterHeader.setDvcCd("BATCH");
		}
		
		/****************************************************************************************************************
		 * 매출 취소 처리
		 * 1. 가상계좌 미입금 건은 매출 취소에서 제외
		 * 2. 배정되지 않은 주문상품의 경우 매출 취소에서 제외
		 ****************************************************************************************************************/
		if (isDepstWait == false) {
			try {
				
				OrderParamDTO salesDTO = new OrderParamDTO();
				
				salesDTO.setOrdNo(ordNo);
				salesDTO.setClmNo(clmNo);
				
				adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
				
				orderInterfaceService.sendSalesData(salesDTO, adapterHeader);
				
				// 매배정 마일리지 사용 주문 임시마일리지 취소
				salesDTO.setUnAssignedOrderYn("Y");
				orderInterfaceService.sendMileageTempData(salesDTO, adapterHeader);
				
				claimRepository.insertErpTrnsmisForClaim(ordNo, clmNo, OrderClaimEnum.ErpTrnsmisTpEnum.SALES_CNCL.toString(), "SUCCES");
				
			} catch(Exception e) {
				log.error(e.getMessage(), e);
				claimRepository.insertErpTrnsmisForClaim(ordNo, clmNo, OrderClaimEnum.ErpTrnsmisTpEnum.SALES_CNCL.toString(), "FAILR");
				
			}
		}
		
		/****************************************************************************************************************
		 * 임시마일리지 사용취소 처리
		 * 1. 가상계좌 주문이면서 마일리지를 사용한 경우만 전송
		 ****************************************************************************************************************/
		if (hasTempMileuse) {
			try {
				
				OrderParamDTO salesDTO = new OrderParamDTO();
				
				salesDTO.setOrdNo(ordNo);
				salesDTO.setClmNo(clmNo);
				
				adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
				
				orderInterfaceService.sendMileageTempData(salesDTO, adapterHeader);
				
				claimRepository.insertErpTrnsmisForClaim(ordNo, clmNo, OrderClaimEnum.ErpTrnsmisTpEnum.MILE_USE_CNCL.toString(), "SUCCES");
				
			} catch(Exception e) {
				log.error(e.getMessage(), e);
				claimRepository.insertErpTrnsmisForClaim(ordNo, clmNo, OrderClaimEnum.ErpTrnsmisTpEnum.MILE_USE_CNCL.toString(), "FAILR");
				
			}
		}
	}

	private void updateCancelGoodsInventory(Ord ord, List<GodShopItmInvExtend> godShopItmInvExtendList) {
		for(GodShopItmInvExtend godShopItmInvExtendHist : godShopItmInvExtendList){
			
			GoodsInventoryDTO gDTO = new GoodsInventoryDTO();
			gDTO.setShopId(godShopItmInvExtendHist.getShopId());
			gDTO.setGodNo(godShopItmInvExtendHist.getGodNo());
			gDTO.setItmNo(godShopItmInvExtendHist.getItmNo());
			gDTO.setSyncReqType(GoodsEnum.InvSyncReqTp.CLM.toString());
			
			if("RESVE_ORD".equals(ord.getOrdTpCd())){	// 예약 주문
				
				gDTO.setSyncType(GoodsEnum.InvSyncTp.IRDS.toString());
				gDTO.setResveInvQty((long)godShopItmInvExtendHist.getSalePrearngeQtyInt());
				goodsInventoryFOComponent.updateGoodsReserveInventoryVariation(gDTO);
				
			} 
				
			if (DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals(godShopItmInvExtendHist.getShopId()) == false
					&& DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals(godShopItmInvExtendHist.getShopId()) == false
					&& DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals(godShopItmInvExtendHist.getShopId()) == false
					&& DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals(godShopItmInvExtendHist.getShopId()) == false) {	// 매장배송의 경우 재고처리 안하며 RT처리로 실재고를 증가처리 시킨다.
				continue;
			}
			
			gDTO.setSyncType(GoodsEnum.InvSyncTp.DDCT.toString());
			gDTO.setSalePrearngeQty((long)godShopItmInvExtendHist.getSalePrearngeQtyInt());
			goodsInventoryFOComponent.updateGoodsSalePrearrangementInventoryVariation(gDTO);
		}
	}

	/*
	 * 클레임 주문 부분취소 접수.
	 * 
	 * - 결제대기 상태인 주문 or 세트상품 or 묶음할인 인 경우 정책 상 부분취소를 하지 않는다
	 * 
	 * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimCommandComponent#updateClaimUnitCancel(javax.servlet.http.HttpServletRequest, com.plgrim.ncp.biz.claim.data.ClaimSearchDTO, com.plgrim.ncp.framework.data.SystemPK, java.lang.String)
	 */
	@Override
    public String updateClaimUnitCancel(HttpServletRequest request, ClaimSearchDTO claimSearchDTO,SystemPK systemPK) throws Exception{
		
		try {
			
			try {
				// 주문번호 단위로 erp 분배 테이블에 lock 처리.. (nowait)
				claimRepository.selectOrdGodErpForUpdate(claimSearchDTO.getOrdNo());
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ClaimCompleteFailException("Claim.already.apply", null);
			}
		
			/**
			 * 결제대기 상태의 부분취소 건이 있을 경우 예외 처리함.
			 * 만약 그냥 진행할 경우 배송비 환불에 대한 이슈가 발생함.
			 */
			if(claimRepository.selectPayWaitPartCnclCount(claimSearchDTO.getOrdNo()) > 0) {
				throw new ClaimCompleteFailException("Claim.exist.partcncl.addpay", null);
			}
			
			List<LgsDlvFoExtend> cnclLgsDlvspList = deliveryRuleService.getCnclClmLgsDlvList(claimSearchDTO.getLgsDlvspList().get(0).getClmWrhsGodList());
		
		
			ClaimBoDTO claimBoDTO = new ClaimBoDTO();
			claimBoDTO.setOrdNo(claimSearchDTO.getOrdNo());
		
			/**
			 * 상품사은품 취소 대상 추가
			 */
			List<ClmDlvOrdGodInfoDTO> godGftList = this.getGoodsGiftCancelList(claimSearchDTO);
			claimSearchDTO.getDlvspClmOrdGod().addAll(godGftList);
			
			/**
			 * 주문사은품 취소 대상 추가
			 */
			List<ClmDlvOrdGodInfoDTO> ordGftList = this.getOrderGiftCancelGoodsList(claimSearchDTO, claimSearchDTO.getOrdNo());		
			claimSearchDTO.getDlvspClmOrdGod().addAll(ordGftList);
		
			String ordNo = claimSearchDTO.getOrdNo();
			Ord ord = orderSelectService.selectOrdEntity(ordNo);
		
			String ordStatCd = ord.getOrdStatCd();
			String ordTpCd = ord.getOrdTpCd();
			
			ClaimReceiptDTO claimReceiptDTO = new ClaimReceiptDTO();
			claimReceiptDTO.setAdminId(claimSearchDTO.getAdminId());
			claimReceiptDTO.setUdterId(claimSearchDTO.getUdterId());
			claimReceiptDTO.setOrdNo(ordNo);
			claimReceiptDTO.setClmResnCd(claimSearchDTO.getClmResnCd());
			claimReceiptDTO.setClmResnCont(claimSearchDTO.getClmResnCont());

			List<DlvOrdGodInfoDTO> infoList =  new ArrayList<DlvOrdGodInfoDTO>();
			DlvOrdGodInfoDTO dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();
			List<String> ordGodTurnList = new ArrayList<String>();
			List<InfOrdGodErpDstb> cnclGodList = new ArrayList<InfOrdGodErpDstb>();

			for(ClmDlvOrdGodInfoDTO dto : claimSearchDTO.getDlvspClmOrdGod()){
				dlvOrdGodInfoDTO.setDlvPcupspTurn(dto.getDlvPcupspTurn());
				dlvOrdGodInfoDTO.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
				dlvOrdGodInfoDTO.setDlvTurn(dto.getDlvTurn());
				dlvOrdGodInfoDTO.setOrdGodTurn(dto.getOrdGodTurn());
				dlvOrdGodInfoDTO.setQty(dto.getQty());
				dlvOrdGodInfoDTO.setOrdNo(ordNo);
				dlvOrdGodInfoDTO.setAdminId(claimSearchDTO.getUdterId());	    	
				infoList.add(dlvOrdGodInfoDTO);
				dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();	    	
				ordGodTurnList.add(dto.getOrdGodTurn());
	    	
				//접수가능수량 넘어가면 에러나면서 팅기도록한다.	    	
				List<InfOrdGodErpDstbExtend> ordGodBaseList = new ArrayList<InfOrdGodErpDstbExtend>();
				InfOrdGodErpDstbExtend searchInfOrdGodErpDstb = new InfOrdGodErpDstbExtend();
				searchInfOrdGodErpDstb.setOrdNo(ordNo);
				searchInfOrdGodErpDstb.setOrdGodTurn(Integer.parseInt(dto.getOrdGodTurn()));
				searchInfOrdGodErpDstb.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
				searchInfOrdGodErpDstb.setQty(dto.getQty()+"");
				
				// 수량단위 리스트를 조회
				ordGodBaseList =  infOrdGodErpDstbService.selectInfOrdGodErpDstbForRefund(searchInfOrdGodErpDstb);
				cnclGodList.addAll(ordGodBaseList);

				//세트 껍데기 구분하기위해 추가
				//껍데기 상품순번으로 분배테이블을 조회하고 있으나 분배테이블에는 껍데기 상품이 없으므로 exception 발생
				if (dto.getGodTpCd().equals(GoodsType.SET_GOD.toString()) == false 
						&& dto.getGodTpCd().equals(GoodsType.PCKAGE_GOD.toString()) == false) {
				
					if(ordGodBaseList!=null){
						if(ordGodBaseList.size() < Integer.parseInt(dto.getQty())){//접수 가능수량보다 접수 하려는 수량이 더 크면
							throw new ClaimCompleteFailException("Claim.exceed.available_requisition", null);
						}
					}else{
						throw new ClaimCompleteFailException("Claim.exceed.available_requisition", null);
					}
				}
			}
		
			//부분취소는 결제테이블의 결제상태가 결제완료여야 가능 
			Pay payCheck = new Pay();    	
			payCheck.setOrdNo(ord.getOrdNo());//주문번호로
			payCheck.setPayTpCd("ORD");//주문시 생성된
			payCheck.setDealTpCd("PAY_COMPT");//결제완료된
			payCheck.setMpayMnYn("Y");//주결제
			
			// 결제환불대상 리스트 조회
			List<PayExtend> ordPayListCheck = payBoService.selectOrdClmRefundPayExtend(payCheck); //환불대상 중복으로변경
			
			if(ordPayListCheck==null || ordPayListCheck.size()==0){
				throw new ClaimCompleteFailException("Claim.partial_cancellation.complete_order", null);
			}		
		
			// 출고완료인 상품이 있는지 확인
			String deliveryYn = deliveryStatusService.checkDeliveryYnAboutOrder(infoList);		
			if("Y".equals(deliveryYn)){
				throw new ClaimCompleteFailException("Claim.exist.shipping_product", null);
			}
    
			/*
			 * 2.클레임테이블 insert 
			(주문테이블 기준 select insert 상태는 접수로)
			 */

			if("SHOP_PKUP_ORD".equals(ord.getOrdTpCd())){
				claimReceiptDTO.setPkupClmYn("Y");
			}else{
				claimReceiptDTO.setPkupClmYn("N");
			}

			claimReceiptDTO.setPgTrnsmisTgtYn("Y");
		
			// 환불계좌...
			String clmNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_CLM", "CL", DatabaseType.ORACLE);
			if(claimSearchDTO.getRfdBnkAcctNo()!=null && !"".equals(claimSearchDTO.getRfdBnkAcctNo())){
				Mbr mbrAcc = new Mbr();
				mbrAcc.setMbrNo(ord.getMbrNo());
				mbrAcc = memberBaseSelectService.selectMbr(mbrAcc);

				claimReceiptDTO.setRfdBnkCd(claimSearchDTO.getRfdBnkCd());
				claimReceiptDTO.setRfdAcnthldrNm(claimSearchDTO.getRfdAcnthldrNm());
				claimReceiptDTO.setRfdBnkAcctNo(claimSearchDTO.getRfdBnkAcctNo());
			}

			claimReceiptDTO.setClmStatCd("RCEPT"); //접수 RCEPT
			claimReceiptDTO.setClmTpCd("PART_CNCL");    //부분취소 PART_CNCL

			claimReceiptDTO.setClmNo(clmNo);
			claimService.insertClmByOrdGenClmNo(claimReceiptDTO); //클레임 테이블 insert


			/*
			 * 3.클레임입고상품테이블 insert 
			(화면에서 넘어온 파라메터로 주문상품 테이블 select insert)
			 */
		
			List<ClmDlvOrdGodInfoDTO> clmWrhsGodDelList = new ArrayList<ClmDlvOrdGodInfoDTO>();
		
			for(ClmDlvOrdGodInfoDTO dto : claimSearchDTO.getDlvspClmOrdGod()){
			
				if(dto.getQty()!=null && Integer.parseInt(dto.getQty()) != 0){
					/**
					 * 클레임입고상품 insert & 주문클레임 상품연결 insert
					 */
					claimService.createClmWrhsGodForCancelClaim(claimSearchDTO.getOrdNo()
							, clmNo
							, Integer.parseInt(dto.getOrdGodTurn())
							, Long.parseLong(dto.getQty())
							, dto.getClmResnCd()
							, dto.getClmResnCont());
					
					if("SET_GOD".equals(dto.getGodTpCd()) || "PCKAGE_GOD".equals(dto.getGodTpCd())){
						clmWrhsGodDelList.add(dto);
					}
				}
			}
	    		
			// 부분취소시 클레임 입고상품에 껍대기 추가 (추가후 삭제)
			for(ClmDlvOrdGodInfoDTO dto : clmWrhsGodDelList){
				claimSearchDTO.getDlvspClmOrdGod().remove(dto);	
			}

			// 클레임 god_sumry 등록
			this.clmSumryUpdate(clmNo);

			//클레임 메모저장
			if("B".equals(claimSearchDTO.getRole())){
				CsoCnsltMemo csoCnsltMemo = new CsoCnsltMemo();
				csoCnsltMemo.setRegtrId(claimSearchDTO.getUdterId());
				csoCnsltMemo.setMemoTpCd("CLM");
				csoCnsltMemo.setExpsrYn("Y");
				csoCnsltMemo.setClmNo(clmNo);
				csoCnsltMemo.setMemoCont(claimSearchDTO.getMemoCont());
				
				claimService.updateCsoCnsltMemo(csoCnsltMemo);
			}

			/**
			 * 7.인터페이스 주문상품 erp 분배 테이블 update 부분취소 고려하여 수정 완료
			 (화면에서 받은 주문번호,주문상품순번,수량(rownum)으로 select update inf_ord_god_erp_dstb set clm_no where clm_no is null)
			 */
			//to-be 인터페이스 주문상품erp 분배 테이블 update 해당 주문번호의 모든 주문상품단위 로  clmNo, clmWrhsGodTurn 를 update
			InfOrdGodErpDstbUpdateDTO infOrdGodErpDstbDTO = new InfOrdGodErpDstbUpdateDTO();
			infOrdGodErpDstbDTO.setOrdNo(ordNo);
			OrdGodErpDTO ordGodErpDTO = new OrdGodErpDTO();
			List<OrdGodErpDTO> ordGodTurnListForErp = new ArrayList<OrdGodErpDTO>();
			for(ClmDlvOrdGodInfoDTO dto : claimSearchDTO.getDlvspClmOrdGod()){
				ordGodErpDTO = new OrdGodErpDTO();
				ordGodErpDTO.setClmNo(clmNo);
				ordGodErpDTO.setOrdGodTurn(dto.getOrdGodTurn());
				ordGodErpDTO.setQty(dto.getQty());
				ordGodErpDTO.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
				ordGodTurnListForErp.add(ordGodErpDTO);
			}
			infOrdGodErpDstbDTO.setOrdGodTurnList(ordGodTurnListForErp);
			infOrdGodErpDstbService.updateInfOrdGodErpDstbByClmUnit(infOrdGodErpDstbDTO);

			boolean isAddPay = false; // 추가결제 필요여부

			String partYn = claimRepository.getPartClaimPossibleYn(ordNo, "PART_CNCL", claimSearchDTO.getRole());
			// 부분 클레임 처리가 불가할경우 전체 처리 되었는지 확인 (주문할인 및 에스크로 주문) 
			if ("N".equals(partYn)) {
				if (claimRepository.getNoClaimGodCount(ordNo) > 0) {
					throw new ClaimCompleteFailException("Claim.impossible.part.accept", null);
				}
			}
			
			/**
			 * 클레임으로 인한 배송비 변경 금액 계산
			 * 추가결제 여부 확인 및 CLM 테이블 배송비 합계 금액에서 사용
			 */
			java.math.BigDecimal realityDlvCst = BigDecimal.valueOf(0);
			for(LgsDlvFoExtend lgsDlv : cnclLgsDlvspList) {
				if(lgsDlv.getOriRealityDlvCst().intValue() > 0){
					// 유료배송 -> 전체취소 이거나 유료배송 일 경우
					realityDlvCst = realityDlvCst.add(lgsDlv.getCnclDlvCst());
					// 클레임 으로 인해 발생한 배송비 환불인지 확인할것.
					// 클레임으로 인한 배송비면... 해당 결제 취소하도록 추가로직 구성하고... 원 결제에서 그 금액을 환불하지 않도록 변경
				} else {
					// 무료배송 -> 유료배송 일 경우
					realityDlvCst = realityDlvCst.subtract(lgsDlv.getRealityDlvCst());
				}
			}

			BigDecimal addPayAmount = new BigDecimal(0);

			if (realityDlvCst.compareTo(BigDecimal.ZERO) < 0) { // 배송비 추가결제 발생 시

				BigDecimal reverseSign = new BigDecimal(-1);
				addPayAmount = addPayAmount.add(realityDlvCst.multiply(reverseSign));

				BigDecimal cnclStdrCrncyAmt = new BigDecimal(0);

				for(InfOrdGodErpDstb chkInfOrdGodErpDstb : cnclGodList){
					cnclStdrCrncyAmt = cnclStdrCrncyAmt.add(chkInfOrdGodErpDstb.getStdrCrncyUntPrc());//앞으로 취소될 가격 수량단위
				}
				
				addPayAmount = addPayAmount.subtract(cnclStdrCrncyAmt);
				
				// 환불금액보다 배송비 부과 금액이 많은 경우
				if (addPayAmount.compareTo(BigDecimal.ZERO) > 0) {
					isAddPay = true;
				}
			}

			/**
			 * 6.클레임입고상품적용프로모션insert
			 (주문클레임상품연결테이블 join 주문상품적용프로모션테이블 로 클레임입고상품적용프로모션 select insert)
			 */
			claimService.insertClmWrhsGodAplPrmByOrd(claimReceiptDTO);

			if (isAddPay == false) {	// 추가결제 필요시 결제 후 웹포인트 원복
				this.updateWebPointForCancelClaim(ordNo, ord, clmNo);
			}

			/****************************************************************************************************************
			 * 온라인 재고 처리
			 * 1. 예약 주문 -> 예약재고 차감
			 * 2. WMS 배송 -> 판매예정수량 차감
			 * 3. 매장 배송, 매장수령주문 -> RT처리로 ERP내 실시간 재고 차감 처리
			 * 
			 *  입금대기의 경우 주문시 재고차감을 안하므로 스킵처리
			 ****************************************************************************************************************/
			if("DEPST_WAIT".equals(ordStatCd) == false && OrderClaimEnum.ORD_TP_CD_LAG_QTY_ORD.toString().equals(ordTpCd) == false){
				List<GodShopItmInvExtend> godShopItmInvExtendList = new ArrayList<GodShopItmInvExtend>();
				for(DlvOrdGodInfoDTO dto : infoList){
					godShopItmInvExtendList.addAll(deliveryService.selectOrdGodShopItmQty(dto));
				}
				this.updateCancelGoodsInventory(ord, godShopItmInvExtendList);
			}
			
			/****************************************************************************************************************
			 * 출고지시 취소 처리
			 * 1. 취소 처리 조건 : WMS배송, 출고지시상태, 출고지시취소상태가 없어야함(BO에서 출고지시취소처리를 이미 하지 않은 경우만) 
			 ****************************************************************************************************************/
			List<DlivyDrctSDO> dlivyCnclList = new ArrayList<DlivyDrctSDO>();
			
			DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
			deliverySearchDTO.setClmDlvOrdGodInfoList(infoList);
			
			List<DeliveryInfErpDTO> dlist = deliveryInfErpExternalService.selectDlivyDrctInfoListByClm(deliverySearchDTO);
			
			for (DeliveryInfErpDTO dto : dlist) {
				
				DeliverySearchDTO dlivyCnclDTO = new DeliverySearchDTO();
				
				dlivyCnclDTO.setCancelYn("Y");
				dlivyCnclDTO.setOrdNo(ordNo);
				dlivyCnclDTO.setDlvPcupspTurn(dto.getDlvPcupspTurn());
				dlivyCnclDTO.setDlvTurn(dto.getDlvTurn());
				dlivyCnclDTO.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());

				DlivyDrctSDO dlivyCncl = deliveryCommandRepository.selectWmsDlivyDrctTargetList(dlivyCnclDTO);
				
				// 부분취소의 경우는 취소되는 수량을 세팅하여 부분 출고지시 취소 처리를 한다.
				for (DlvOrdGodInfoDTO clmGod : infoList) {
					if (dto.getDlivyDrctGodNo().equals(clmGod.getDlivyDrctGodNo()) == false) {
						continue;
					}
					
					dlivyCncl.setOutbEctQty(clmGod.getQty());
				}
				
				dlivyCnclList.add(dlivyCncl);
			}
			
			
			if (dlivyCnclList.isEmpty() == false) {
				try{
					
					DlivyWmsSDO dlivyWmsSDO = new DlivyWmsSDO();
					dlivyWmsSDO.setReleaseList(dlivyCnclList);
					
					AdapterHeader header = new AdapterHeader();
					
					header.setRequestId(interfaceApiCommon.getRequestId());
					header.setMallId(systemPK.getMall());
					header.setLangCd("KOR");
					header.setDvcCd(systemPK.getDevice());
					
					dlivyWmsSDO.setCallerId(this.getClass().getSimpleName() + this.getClass().getDeclaredMethods()[0].getName());
					
					String returnData = deliveryAdapter.sendWMSDlivyDrct(dlivyWmsSDO, header);
					
					DlivyWmsSDO dlivyWmsSDOReturn = (DlivyWmsSDO) JsonService.unmarshalling(returnData, DlivyWmsSDO.class);
					
					if("200".equals(dlivyWmsSDOReturn.getResponseCd()) == false) {
						throw new ClaimCompleteFailException("Claim.fail.dlivydrctcncl", null);
					}
						
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					throw e;
				}
			}
			
			deliveryService.updateClmDlvDrctGodUnitCancel(deliverySearchDTO);	// 출고지시테이블 취소수량 등록 / 출고지시 취소로 상태변경

			/*
			 * 	'물류출고지시상품' 데이터 전체가 '배송완료', '출고지시취소' 인 경우에
			 * 	해당 '주문'(ORD테이블)에 대해서 '배송완료' 로 상태 수정
			 */
			this.deliveryStatusService.modifyOrdDlvCompt( claimSearchDTO.getOrdNo() );
			if ( log.isDebugEnabled() ) {
				log.debug("ClaimCommandComponentImpl.updateClaimUnitCancel->modifyOrdDlvCompt [{}]", claimSearchDTO.getOrdNo());
			}

			// 물류배송정보 변경
			LgsDlv lgsDlvUpd = null;

			int remainCnt = orderSelectService.selectAplPrmGodDlvFeeCnt(ordNo);
			for(LgsDlvFoExtend lgsDlv : cnclLgsDlvspList) {
				if(0!=remainCnt){//상품 무료배송비 쿠폰을 사용한 주문이면
					lgsDlvUpd = new LgsDlv();
					lgsDlvUpd.setOrdNo(lgsDlv.getOrdNo());
					lgsDlvUpd.setDlvPcupspTurn(lgsDlv.getDlvPcupspTurn());
					lgsDlvUpd.setDlvTurn(lgsDlv.getDlvTurn());
					lgsDlvUpd.setClmNo(clmNo);
					lgsDlvUpd.setRegtrId(claimSearchDTO.getUdterId());
					lgsDlvUpd.setUdterId(claimSearchDTO.getUdterId());
					lgsDlvUpd.setStdrCrncyAmt(lgsDlv.getRealityDlvCst());		//기준 통화 금액
					lgsDlvUpd.setPayExchgRtCrncyAmt(lgsDlv.getRealityDlvCst());	//결제 환율 통화 금액
					lgsDlvUpd.setRealityDlvCst(lgsDlv.getRealityDlvCst());		//실배송비
					lgsDlvUpd.setPlcDlvCst(lgsDlv.getRealityDlvCst());			//정책배송비			
					if(lgsDlv.getOriRealityDlvCst().intValue() > 0){
						lgsDlvUpd.setCnclDlvCst(lgsDlv.getRealityDlvCst());	
					}else{
						lgsDlvUpd.setCnclDlvCst(lgsDlv.getRealityDlvCst().multiply(new BigDecimal("-1")));
					}
					
					
					claimService.updateLgsDlvCst(lgsDlvUpd);
					deliveryService.insertLgsDlvForCnclClmHist(lgsDlv);//물류배송 hist 테이블에 등록
				}else{
					for(ClmDlvOrdGodInfoDTO dto : claimSearchDTO.getDlvspClmOrdGod()){
						if(Integer.valueOf(dto.getDlvPcupspTurn()).intValue() == lgsDlv.getDlvPcupspTurn().intValue() &&
								Integer.valueOf(dto.getDlvTurn()).intValue() == lgsDlv.getDlvTurn().intValue()){
							//update 용 DTO
							lgsDlvUpd = new LgsDlv();
							lgsDlvUpd.setOrdNo(lgsDlv.getOrdNo());
							lgsDlvUpd.setDlvPcupspTurn(lgsDlv.getDlvPcupspTurn());
							lgsDlvUpd.setDlvTurn(lgsDlv.getDlvTurn());
							lgsDlvUpd.setClmNo(clmNo);
							lgsDlvUpd.setRegtrId(claimSearchDTO.getUdterId());
							lgsDlvUpd.setUdterId(claimSearchDTO.getUdterId());
	
							if(lgsDlv.getCnclDlvCst() == null) {
								lgsDlv.setCnclDlvCst(BigDecimal.valueOf(0));
							}
							if(lgsDlv.getRtgodDlvCst() == null) {
								lgsDlv.setRtgodDlvCst(BigDecimal.valueOf(0));
							}
							
							if(lgsDlv.getOriRealityDlvCst().intValue() > 0){
								//유료주문
	
								//전체반품이어서 취소배송비가 발생한 경우
								if(lgsDlv.getCnclDlvCst().intValue() > 0)
								{
									lgsDlvUpd.setCnclDlvCst(lgsDlv.getCnclDlvCst());			//취소배송비
	
									claimService.updateLgsDlvCst(lgsDlvUpd);
									deliveryService.insertLgsDlvForCnclClmHist(lgsDlv);//물류배송 hist 테이블에 등록
	
								}  else {
									
									claimService.updateLgsDlvCst(lgsDlvUpd);
									deliveryService.insertLgsDlvForCnclClmHist(lgsDlv);//물류배송 hist 테이블에 등록
								}
	
							} else if(lgsDlv.getRealityDlvCst().intValue() > 0  &&  ord.getDlvCstCpnDcSumAmt().intValue() > 0) { //해당주문번호로 주문상품적용프로모션에 상품배송비쿠폰이 없으면 조건 추가필요
								//배송비쿠폰인사용인경우강제 배송비0원처리
								
								log.debug("부분취소강제배송비처리============>");
								
								lgsDlvUpd.setStdrCrncyAmt(BigDecimal.valueOf(0));		//기준 통화 금액
								lgsDlvUpd.setPayExchgRtCrncyAmt(BigDecimal.valueOf(0));	//결제 환율 통화 금액
								lgsDlvUpd.setRealityDlvCst(BigDecimal.valueOf(0));	    	//실배송비
								lgsDlvUpd.setCnclDlvCst(BigDecimal.valueOf(0));             //취소배송비
								lgsDlv.setRealityDlvCst(BigDecimal.valueOf(0));   // 하단고객회수발생배송비 0원처리
	
								claimService.updateLgsDlvCst(lgsDlvUpd);
								deliveryService.insertLgsDlvForCnclClmHist(lgsDlv);//물류배송 hist 테이블에 등록
							
							} else {
								//무료주문
	
								//정책이 깨져서 주문배송비를 청구해야 하는 경우
								if(lgsDlv.getRealityDlvCst().intValue() > 0) {
									lgsDlvUpd.setStdrCrncyAmt(lgsDlv.getRealityDlvCst());		//기준 통화 금액
									lgsDlvUpd.setPayExchgRtCrncyAmt(lgsDlv.getRealityDlvCst());	//결제 환율 통화 금액
									lgsDlvUpd.setRealityDlvCst(lgsDlv.getRealityDlvCst());		//실배송비
									lgsDlvUpd.setPlcDlvCst(lgsDlv.getRealityDlvCst());			//정책배송비
									
									claimService.updateLgsDlvCst(lgsDlvUpd);
									deliveryService.insertLgsDlvForCnclClmHist(lgsDlv);//물류배송 hist 테이블에 등록
									
								} else {
									lgsDlvUpd.setStdrCrncyAmt(BigDecimal.valueOf(0));		//기준 통화 금액
									lgsDlvUpd.setPayExchgRtCrncyAmt(BigDecimal.valueOf(0));	//결제 환율 통화 금액
									lgsDlvUpd.setRealityDlvCst(BigDecimal.valueOf(0));		//실배송비
									lgsDlvUpd.setPlcDlvCst(BigDecimal.valueOf(0));			//정책배송비
									
									claimService.updateLgsDlvCst(lgsDlvUpd);
									deliveryService.insertLgsDlvForCnclClmHist(lgsDlv);//물류배송 hist 테이블에 등록
								}
							}
						}
					}
				}
			}

			/**
			 * 9.클레임입고상품 가격정보 update
			 (erp 분배 테이블 join 하여 수량별로 가격을 sum 해서 update)
			 */
			claimService.updateClmWrhsGodToPrcByErp(claimReceiptDTO);

			/**
			 * 10.클레임 가격정보 update
			 (가격정보 클레임입고상품테이블 join 하여 sum 해서 update)
	
			 */
			claimReceiptDTO.setClmTpCd("PART_CNCL");
			claimReceiptDTO.setRealityDlvCst(realityDlvCst);
			claimService.updateClmToPrcByClmWrhsGod(claimReceiptDTO);
			
			//한 주문에 모든 상품이 취소되었다면 주문의 상태를 전체취소로 변경해준다.
			InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
			infOrdGodErpDstbClmSearchDTO.setOrdNo(ordNo);
			infOrdGodErpDstbClmSearchDTO.setClmNoNullYn("Y");
			List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
			if(infOrdGodErpDstbCheckList == null || infOrdGodErpDstbCheckList.size()==0){//ordGodErp에 클레임번호가 비어있는게 없다면 모두 취소된거임
				Ord statUpdateOrd = new Ord();
				statUpdateOrd.setOrdNo(ordNo);
				statUpdateOrd.setOrdStatCd("ALL_CNCL");//주문테이블 주문상태 : 전체취소
				orderBoService.updateOrdStatCd(statUpdateOrd);
			}
			
			// 본 클레임으로 인해 모든 상품수량이 취소된 경우 클레임 유형을 전체취소로 변경한다. 
			ClaimSearchDTO cnclTpDTO = new ClaimSearchDTO();
			cnclTpDTO.setOrdNo(ordNo);
			cnclTpDTO.setClmNo(clmNo);
			cnclTpDTO.setUdterId(claimSearchDTO.getUdterId());
			claimRepository.updateCancelClaimTpCd(cnclTpDTO);
			
		    Clm checkInfoInitDTO = new Clm();
		    checkInfoInitDTO.setClmNo(clmNo);
		    orderCommandService.checkInfoInit(checkInfoInitDTO);
		    
			/**
			 * 20.환불완료처리(클레임 상태 완료 처리) : 주결제수단이 [가상계좌,무통장,휴대폰]이 아니면 가능
			 */
			boolean refundComplCheck = true;

			/**
		     * 결제 환불 처리
		     * 1. 주 결제 수단을 체크하여 가상계좌일 경우 포인트사용임시삭감에 대해 취소할 수 있도록 한다.
		     *
		     */
			Pay pay = new Pay();
			pay.setOrdNo(claimSearchDTO.getOrdNo());//주문번호로
			pay.setPayTpCd("ORD");//주문시 생성된

			List<PayExtend> ordPayList = payBoService.selectOrdClmRefundPayExtend(pay); //환불대상 중복으로변경

			if(refundComplCheck && isAddPay == false){//결제 완료처리 해야할때

				this.refundPayForPartCncl(request, clmNo, ordPayList);

			} else if(isAddPay == true) {

				// 추가결제 데이터 생성
				this.insertAddPayWait(claimSearchDTO.getUdterId(), ordNo, clmNo, addPayAmount);
	
				// 클레임 결제 대기 상태로 변경
				Clm clm = new Clm();
				clm.setClmNo(clmNo);
				clm.setClmStatCd("PAY_WAIT");
				claimService.updateClmStatCd(clm);
			}

		    return clmNo;
		} catch(ClaimCompleteFailException e1) {
			log.error("PartCancel Fail" + " exception : {}", claimSearchDTO);
			log.error("PartCancel Fail" + " exception : {}", e1);
			log.error(CommonResponseCode.CL_40001_취소_접수_실패.toMessage() + " / "+e1.getDirectMessage());
			throw e1;   	 
		} catch (Exception e2) {
			log.error("PartCancel Fail" + " exception : {}", claimSearchDTO); 	 
			log.error("PartCancel Fail" + " exception : {}", e2);
			log.error(CommonResponseCode.CL_40001_취소_접수_실패.toMessage() + " / "+e2.getMessage());
			throw e2;		    
		}
	}

	/**
	 * 상품사은품 취소 대상 조회
	 * 
	 * @param claimSearchDTO
	 * @return
	 */
	private List<ClmDlvOrdGodInfoDTO> getGoodsGiftCancelList(ClaimSearchDTO claimSearchDTO) {
		List<ClmDlvOrdGodInfoDTO> godGftList = new ArrayList<ClmDlvOrdGodInfoDTO>();
		// 상품사은품 조회
		for(ClmDlvOrdGodInfoDTO dto : claimSearchDTO.getDlvspClmOrdGod()){
			
			dto.setOrdNo(claimSearchDTO.getOrdNo());
			List<ClmDlvOrdGodInfoDTO> tgtGodGftList = claimService.selectCnclTgtGodGftPrm(dto);
			
			if(tgtGodGftList == null || tgtGodGftList.isEmpty()) {
				continue;
			}
			
			// 취소정보는 본 상품과 동일하세 처리
			for (ClmDlvOrdGodInfoDTO godGft : tgtGodGftList) {
				godGft.setQty(dto.getQty());
				godGft.setClmResnCd(dto.getClmResnCd());
				godGft.setClmResnCont(dto.getClmResnCont());
			}
			
			
			godGftList.addAll(tgtGodGftList);
			
		}
		return godGftList;
	}

	/**
	 * 주문사은품 취소 대상 조회
	 * 
	 * @param claimSearchDTO
	 * @param ordNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ClmDlvOrdGodInfoDTO> getOrderGiftCancelGoodsList(ClaimSearchDTO claimSearchDTO, String ordNo) throws Exception {
		
		List<ClmOrdGodAplPrm> ogapResultList = getOrderGiftCancelPromotionList(claimSearchDTO, ordNo);

		List<ClmDlvOrdGodInfoDTO> clmDlvOrdGodInfoDTOList  = new ArrayList<ClmDlvOrdGodInfoDTO>();
		
		/**
		 * 클레임 대상 리스트에 주문사은품 추가
		 */
		for(ClmOrdGodAplPrm ordGft : ogapResultList){
			InfOrdGodErpDstbExtend searchOrdGodErpGft = new InfOrdGodErpDstbExtend();
		    searchOrdGodErpGft.setOrdNo(ordGft.getOrdNo());
		    searchOrdGodErpGft.setOrdGodTurn(Integer.parseInt(ordGft.getOrdGodTurn()));
		    searchOrdGodErpGft.setQty(ordGft.getAplQty()+"");
		    List<InfOrdGodErpDstbExtend> ordGodErpGftList = infOrdGodErpDstbService.selectInfOrdGodErpDstbForRefund(searchOrdGodErpGft);
		    for(InfOrdGodErpDstbExtend ordGodErpGft : ordGodErpGftList){
		    	ClmDlvOrdGodInfoDTO clmDlvOrdGodInfoDTOOrdGft = new ClmDlvOrdGodInfoDTO(); 
				clmDlvOrdGodInfoDTOOrdGft.setDlvPcupspTurn(ordGodErpGft.getDlvPcupspTurn()+"");
				clmDlvOrdGodInfoDTOOrdGft.setDlivyDrctGodNo(ordGodErpGft.getDlivyDrctGodNo());
				LgsDlivyDrctGod lgsDlivyDrctGod = new LgsDlivyDrctGod();
				lgsDlivyDrctGod.setDlivyDrctGodNo(ordGodErpGft.getDlivyDrctGodNo());
				clmDlvOrdGodInfoDTOOrdGft.setDlvTurn(deliveryService.selectLgsDlivyDrctGod(lgsDlivyDrctGod).getDlvTurn()+"");    			
				clmDlvOrdGodInfoDTOOrdGft.setOrdGodTurn(ordGft.getOrdGodTurn());
				clmDlvOrdGodInfoDTOOrdGft.setQty("1");
				//ui/ux 프로젝트 기존 버그 수정 god_tp_cd 셋팅추가 aether
				clmDlvOrdGodInfoDTOOrdGft.setGodTpCd(ordGft.getGodTpCd());
				clmDlvOrdGodInfoDTOList.add(clmDlvOrdGodInfoDTOOrdGft);
		    }			
		}
		return clmDlvOrdGodInfoDTOList;
	}

	/**
	 * 주문사은품 취소 목록 조회
	 * 
	 * @param claimSearchDTO
	 * @param ordNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ClmOrdGodAplPrm> getOrderGiftCancelPromotionList(ClaimSearchDTO claimSearchDTO, String ordNo) throws Exception {
		
		List<ClmOrdGodAplPrm> ogapResultList = new ArrayList<ClmOrdGodAplPrm>();
		
		int resultQty = 0;
		int resultAmt = 0;
		
		ClaimBoDTO claimBoDTO = new ClaimBoDTO();
		claimBoDTO.setOrdNo(ordNo);
		
		// 주문사은품 조회
		List<OrdGodAplPrm> aplPrmList =  claimService.selectOrdGodAplPrm(claimBoDTO);
		
		//접수하려는 상품리스트 조회 (본상품)
		List<InfOrdGodErpDstb> reqClmList = new ArrayList<InfOrdGodErpDstb>();
		
		for(ClmDlvOrdGodInfoDTO dto : claimSearchDTO.getDlvspClmOrdGod()){
			InfOrdGodErpDstbExtend searchInfOrdGodErpDstb = new InfOrdGodErpDstbExtend();
			searchInfOrdGodErpDstb.setOrdNo(claimBoDTO.getOrdNo());
			searchInfOrdGodErpDstb.setOrdGodTurn(Integer.parseInt(dto.getOrdGodTurn()));
			searchInfOrdGodErpDstb.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
			searchInfOrdGodErpDstb.setQty(dto.getQty()+"");

			//주문사은품 대상은 자사만 해당
			searchInfOrdGodErpDstb.setErpIntrlckTgtYn("Y");

			// ERP 분배 테이블 및 출고상품 테이블을 이용하여 수량단위 상품을 조회한다.
			reqClmList.addAll( infOrdGodErpDstbService.selectInfOrdGodErpDstbForRefund(searchInfOrdGodErpDstb));

		}

		//2. 1리스트만큼for 돌면서 prm 테이블 조회하여 ORD_GFT_PCH_STDR_CD 컬럼이 QTY_STDR 인지 AMT_STDR 인지 체크
		
		for(OrdGodAplPrm ordGodAplPrmDto : aplPrmList){
			
			Prm prm = new Prm();
			prm.setPrmNo(ordGodAplPrmDto.getPrmNo());
			prm = promotionService.selectPrm(prm);
			
			if(prm != null){
			
				//3. ord_god_apl_prm 의 프로모션번호로 ord_god_turn 으로 주문상품 리스트 조회 (본상품) - 주문상품 단위로 조회
				List<OrdGod> ordGodList = claimService.selectOrdGodByOrdGft(ordGodAplPrmDto); 
			
				//4. 주문번호로 clm_wrhs_god 테이블 조회하여 이전 취소내역 조회 (본상품)
				ClaimOrdGftSearchDTO claimOrdGftSearchDTO = new ClaimOrdGftSearchDTO();
				claimOrdGftSearchDTO.setOrdNo(claimBoDTO.getOrdNo());
				claimOrdGftSearchDTO.setOrdGodList(ordGodList);

				//주문사은품 대상은 자사만 해당
				claimOrdGftSearchDTO.setPartmalSectCd("MCOM");
				//주문사은품 대상은 자사만 해당

				// 이전 클레임 체크 
				List<ClmWrhsGod> beforeClmWrhsGodList = claimService.selectClmWrhsGodForOrdGft(claimOrdGftSearchDTO);

				// 3의 주문리스트 - 4의 이전 취소내역 - 5의 접수하려는 내역 = 최종 결제 금액 or 수량
				if("AMT_STDR".equals(prm.getOrdGftPchStdrCd())){//금액기준
					
					//7. 프로모션 정책이 금액으로 깨지는지 체크 깨지면 ogapResultList 에 해당 주문상품순번이 없을경우 add 한다.
					for(OrdGod chkOrdGod : ordGodList){//대상상품 주문가격
						resultAmt += chkOrdGod.getSaleAmt().intValue();	//아이템단위
					}
					
					for(ClmWrhsGod chkClmWrhsGod : beforeClmWrhsGodList){//대상상품 이전취소가격 아이템단위
						resultAmt -= chkClmWrhsGod.getSaleAmt().intValue();
					}
					
					for(InfOrdGodErpDstb chkInfOrdGodErpDstb : reqClmList){
						resultAmt -= chkInfOrdGodErpDstb.getSaleUntPrc().intValue();//앞으로 취소될 가격 수량단위
					}
					
					if(prm.getOrdGftAplCndAmt().intValue() > resultAmt){//프로모션 정책 가격보다 최종 결제금액이 작으면 정책이 깨졌으니 클레임 목록에 포함시킨다.						
						
						boolean chkInsert = true;
						for(ClmOrdGodAplPrm chkResultDto : ogapResultList){
							if(chkResultDto.getOrdGodTurn().equals(ordGodAplPrmDto.getOrdGodGftTurn())){								
								chkInsert = false;
							}
						}
					
						List<OrdGod> chkOrdGodList = new ArrayList<OrdGod>(); 
						OrdGod chkOrdGod = new OrdGod();
						chkOrdGod.setOrdGodTurn(ordGodAplPrmDto.getOrdGodGftTurn());
						chkOrdGodList.add(chkOrdGod);
						
						ClaimOrdGftSearchDTO chkClmWrhsGod = new ClaimOrdGftSearchDTO();
						chkClmWrhsGod.setOrdNo(claimBoDTO.getOrdNo());
						chkClmWrhsGod.setOrdGodList(chkOrdGodList);

						//주문사은품 대상은 자사만 해당
						claimOrdGftSearchDTO.setPartmalSectCd("MCOM");
						//주문사은품 대상은 자사만 해당

						List<ClmWrhsGod> chkClmWrhsGodList = claimService.selectClmWrhsGodForOrdGft(chkClmWrhsGod);
						
						int chkOrdGodErpCnt = 0;
						
						if(chkClmWrhsGodList!=null){
							chkOrdGodErpCnt = chkClmWrhsGodList.size();
						}
						
						if(chkOrdGodErpCnt!=0){//해당상품이 이전 클레임으로 취소되었다면 다시 취소되면 안된다.
							chkInsert = false;
						}
						
						if(chkInsert){
							OrdGod searchOrdGod = new OrdGod();
							searchOrdGod.setOrdNo(claimBoDTO.getOrdNo());
							searchOrdGod.setOrdGodTurn(ordGodAplPrmDto.getOrdGodGftTurn());						
							OrdGod resultOrdGod = orderBoSelectService.selectOrdGodEntity(searchOrdGod);
							ClmOrdGodAplPrm resultDto = new ClmOrdGodAplPrm();
							resultDto.setOrdGftPchStdrCd(prm.getOrdGftPchStdrCd());
							resultDto.setOrdGftAplCndAmt(prm.getOrdGftAplCndAmt().intValue()+"");
							resultDto.setGodNm(resultOrdGod.getGodNm());
							resultDto.setItmNm(resultOrdGod.getItmNm());
							resultDto.setAplQty(resultOrdGod.getOrdQty()+"");
							resultDto.setOrdGodTurn(ordGodAplPrmDto.getOrdGodGftTurn()+"");
							resultDto.setOrdNo(claimBoDTO.getOrdNo()+"");
							//ui/ux 프로젝트 기존 버그 수정 god_tp_cd 셋팅추가 aether
							resultDto.setGodTpCd(resultOrdGod.getGodTpCd());
							ogapResultList.add(resultDto);
						}						
					}
					resultAmt = 0;//초기화
				}else if("QTY_STDR".equals(prm.getOrdGftPchStdrCd())){//수량기준
					//8. 프로모션 정책이 금액으로 깨지는지 체크 깨지면 ogapResultList 에 해당 주문상품순번이 없을경우 add 한다.
					for(OrdGod chkOrdGod : ordGodList){//대상상품 주문가격
						resultQty += chkOrdGod.getOrdQty();	//아이템단위
					}
					for(ClmWrhsGod chkClmWrhsGod : beforeClmWrhsGodList){//대상상품 이전취소가격 아이템단위
						resultQty -= chkClmWrhsGod.getClmQty();
					}
					if(reqClmList!=null){
						resultQty -= reqClmList.size();//수량단위이므로 size 가 수량이다
					}											
					if(prm.getOrdGftAplCndQty().intValue() > resultQty){//프로모션 정책 가격보다 최종 결제금액이 작으면 정책이 깨졌으니 클레임 목록에 포함시킨다.						
						boolean chkInsert = true;
						for(ClmOrdGodAplPrm chkResultDto : ogapResultList){
							if(chkResultDto.getOrdGodTurn().equals(ordGodAplPrmDto.getOrdGodGftTurn())){								
								chkInsert = false;
							}
						}	
						List<OrdGod> chkOrdGodList = new ArrayList<OrdGod>(); 
						OrdGod chkOrdGod = new OrdGod();
						chkOrdGod.setOrdGodTurn(ordGodAplPrmDto.getOrdGodGftTurn());
						chkOrdGodList.add(chkOrdGod);
						ClaimOrdGftSearchDTO chkClmWrhsGod = new ClaimOrdGftSearchDTO();
						chkClmWrhsGod.setOrdNo(claimBoDTO.getOrdNo());
						chkClmWrhsGod.setOrdGodList(chkOrdGodList);
						
						//주문사은품 대상은 자사만 해당
						claimOrdGftSearchDTO.setPartmalSectCd("MCOM");
						//주문사은품 대상은 자사만 해당

						List<ClmWrhsGod> chkClmWrhsGodList = claimService.selectClmWrhsGodForOrdGft(chkClmWrhsGod);
						int chkOrdGodErpCnt = 0;
						if(chkClmWrhsGodList!=null){
							chkOrdGodErpCnt = chkClmWrhsGodList.size();
						}
						if(chkOrdGodErpCnt!=0){//해당상품이 이전 클레임으로 취소되었다면 다시 취소되면 안된다.
							chkInsert = false;
						}
						if(chkInsert){
							OrdGod searchOrdGod = new OrdGod();
							searchOrdGod.setOrdNo(claimBoDTO.getOrdNo());
							searchOrdGod.setOrdGodTurn(ordGodAplPrmDto.getOrdGodGftTurn());						
							OrdGod resultOrdGod = orderBoSelectService.selectOrdGodEntity(searchOrdGod);
							ClmOrdGodAplPrm resultDto = new ClmOrdGodAplPrm();
							resultDto.setOrdGftPchStdrCd(prm.getOrdGftPchStdrCd());
							resultDto.setOrdGftAplCndQty(prm.getOrdGftAplCndQty().intValue()+"");
							resultDto.setGodNm(resultOrdGod.getGodNm());
							resultDto.setItmNm(resultOrdGod.getItmNm());
							resultDto.setAplQty(resultOrdGod.getOrdQty()+"");
							resultDto.setOrdGodTurn(ordGodAplPrmDto.getOrdGodGftTurn()+"");
							resultDto.setOrdNo(claimBoDTO.getOrdNo()+"");
							//ui/ux 프로젝트 기존 버그 수정 god_tp_cd 셋팅추가 aether
							resultDto.setGodTpCd(resultOrdGod.getGodTpCd());
							ogapResultList.add(resultDto);
						}						
					}
					resultQty = 0;//초기화
				}				
			}			
		}
		return ogapResultList;
	}

	/**
	 * 추가결제 필요시 결제대기 데이터 생성
	 * 
	 * @param udterId
	 * @param ordNo
	 * @param clmNo
	 * @param addPayAmount
	 * @throws Exception
	 */
	private void insertAddPayWait(String udterId, String ordNo, String clmNo, BigDecimal addPayAmount) throws Exception {
		// 추가결제 데이터 생성
		Pay addPay = new Pay();
		addPay.setOrdNo(ordNo);							//주문 번호

		String upperPayNo = payBoService.selectUpperPayNo(addPay);
		addPay.setUpperPayNo(upperPayNo);					//상위 결제 번호

		String payNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_PAY", "ST", DatabaseType.ORACLE);
		addPay.setPayNo(payNo);							//결제번호

		addPay.setPayMnCd("NON_BNKBOK_PAY");				//결제 수단 코드 - 무통장결제
		addPay.setDealTpCd("PAY_WAIT");					//거래 유형 코드 - 결제 대기
		addPay.setPayCrncyCd("KRW");						//결제 통화 코드 - 대한민국 원
		addPay.setStdrCrncyPayAmt(addPayAmount);			//기준 통화 결제 금액
		addPay.setPayCrncyPayAmt(addPayAmount);			//결제 통화 결제 금액
		addPay.setPayTpCd("CLM");							//결제 유형 코드 - 클레임
		addPay.setClmNo(clmNo);							//클레임 번호
		addPay.setRegtrId(udterId);
		addPay.setUdterId(udterId);
		Functions.variAbleSetN(addPay);
		payBoService.payProcessorForClm(addPay);
	}

	private boolean refundPayForPartCncl(HttpServletRequest request, String ordNo, String clmNo) throws Exception{
		/**
	     * 결제 환불 처리
	     * 1. 주 결제 수단을 체크하여 가상계좌일 경우 포인트사용임시삭감에 대해 취소할 수 있도록 한다.
	     *
	     */
		Pay pay = new Pay();
		pay.setOrdNo(ordNo);//주문번호로
		pay.setPayTpCd("ORD");//주문시 생성된

		List<PayExtend> ordPayList = payBoService.selectOrdClmRefundPayExtend(pay); //환불대상 중복으로변경
		return this.refundPayForPartCncl(request, clmNo, ordPayList);
	}

	private boolean refundPayForPartCncl(HttpServletRequest request, String clmNo, List<PayExtend> ordPayList) throws Exception {
		boolean refundChk = false;
		Clm clm = new Clm();
		clm.setClmNo(clmNo);
		clm = claimBatchService.selectClmByClmNo(clm);
		Ord ord = orderSelectService.selectOrdEntity(clm.getOrdNo());

		String ordStatCd = ord.getOrdStatCd();
		
		boolean isDepstWait = false;
		boolean hasTempMileuse = false;
		
		if ("DEPST_WAIT".equals(ordStatCd)) {
			isDepstWait = true;
		}
				
		if ("DEPST_WAIT".equals(ordStatCd) && ord.getUnityPntUseSumAmt().compareTo(BigDecimal.ZERO) > 0) {
			hasTempMileuse = true;
		}
		
		/**
		 * 배송비 환불 시 클레임으로 인한 초도 배송비 추가결제 조회
		 * 조회한 내역이 있으면 추가결제 이므로 해당 금액을 clm에서 빼도록 하며, 해당 결제건들도 별도로 환불 처리를 해준다.
		 */
		BigDecimal remainRefundAmount = clm.getDlvCstSumAmt();
		BigDecimal totalAddPayAmount = BigDecimal.ZERO;
		List<PayExtend> addPayList = null;
		if (clm.getDlvCstSumAmt().compareTo(BigDecimal.ZERO) > 0) {
			addPayList = claimRepository.selectAddPayDlvAmtByPartCncl(clm);

			if (addPayList != null && addPayList.isEmpty() ==false) {
				for (PayExtend addPay : addPayList) {

					if (addPay.getPayCrncyPayAmt().compareTo(remainRefundAmount) > 0) {
						addPay.setPayCrncyPayAmt(remainRefundAmount);
						remainRefundAmount = BigDecimal.ZERO;
					} else {
						remainRefundAmount = remainRefundAmount.subtract(addPay.getPayCrncyPayAmt());
					}

					totalAddPayAmount = totalAddPayAmount.add(addPay.getPayCrncyPayAmt());
				}
			}

			if (totalAddPayAmount.compareTo(BigDecimal.ZERO) > 0) {	// 원 결제에서 부과된 금액이 아니므로 차감해주고 따로 처리하도록 한다.
				clm.setPayExchgRtCrncySumAmt(clm.getPayExchgRtCrncySumAmt().subtract(totalAddPayAmount));
				clm.setStdrCrncySumAmt(clm.getStdrCrncySumAmt().subtract(totalAddPayAmount));
			}
		}

		int errorCnt = 0;
		
		for(PayExtend payDto : ordPayList){

			// 이미 기환불 된 경우는 skip. 
			// 환불 대상 데이터는 주문/클레임 구분이 주문인 결제 데이터를 전부 가져오는데 일반배송전환 재결제 및 결제수단 변경 등 최초 주문 결제가 전체 환불된 경우 의미없는 0원환불 데이터가 생성된다. 이를 방지하기 위해 추가함 
			if (payDto.getStdrCrncyPayAmt().compareTo(payDto.getCnclAcmtlAmt()) == 0) {
				continue;
			}
			
			RefundPayDTO refundPayDTO = this.makeDTOForRefundPay(ord, clm, payDto, true);
			
			boolean isRfdCompt = true;
			/**
			 * PG 환불 처리
			 */
			if("PAY_COMPT".equals(payDto.getDealTpCd())){
				isRfdCompt = refundPayComponent.refundPayTransaction(refundPayDTO, clm, false);
			}else if("VIRTL_BNK_ACCT_PAY".equals(payDto.getPayMnCd())&&"DEPST_WAIT".equals(payDto.getDealTpCd())){
				if("Y".equals(payDto.getMpayMnYn())){
					isRfdCompt = refundPayComponent.refundPayTransaction(refundPayDTO, clm, false);
				} else{
				    continue;
				}
			}else if("NON_BNKBOK_PAY".equals(payDto.getPayMnCd())&&"PAY_WAIT".equals(payDto.getDealTpCd())){
				isDepstWait = true;
			}


			if(isRfdCompt == false){
				errorCnt++;
			}
		}

		if (addPayList != null && addPayList.isEmpty() == false) {
			for (PayExtend addPay : addPayList) {
				
				boolean isRfdCompt = true;
				
				RefundPayDTO refundPayDTO = this.makeDTOForRefundPay(ord, clm, addPay, false);
				if (addPay.getPayCrncyPayAmt().compareTo(BigDecimal.ZERO) > 0) {
					isRfdCompt = refundPayComponent.refundPayTransaction(refundPayDTO, clm, false);
				}
				
				if(isRfdCompt == false){
					errorCnt++;
				}
			}
		}

		/************************포인트 재적립 및 삭감  start***********************************************/

		//클레임상태완료처리
		//클레임상품상태완료처리(전체취소,취소인경우:접수상태이므로 완료처리) ALL_CNCL_COMPT
		//클레임상태완료처리
		//클레임상품상태완료처리(전체취소,취소인경우:접수상태이므로 완료처리) ALL_CNCL_COMPT
		if(errorCnt==0){
			clm = new Clm();
			clm.setClmNo(clmNo);
			clm.setClmStatCd("COMPT");
			claimService.updateClmStatCd(clm);
			
			this.sendSalesInfoToErp(ord.getOrdNo(), clmNo, ord.getMallId(), isDepstWait, hasTempMileuse);
		}
		return refundChk;
	}

	/**
	 * 주문 취소로 인한 웹포인트 복원 처리
	 *
	 * @param ordNo
	 * @param clmNo
	 * @throws Exception
	 */
	private void updateWebPointForCancelClaim(String ordNo, String clmNo) throws Exception {
		Ord ord = orderSelectService.selectOrdEntity(ordNo);
		this.updateWebPointForCancelClaim(ordNo, ord, clmNo);
	}

	/**
	 * 주문 취소로 인한 웹포인트 복원 처리
	 *
	 * @param ordNo
	 * @param ord
	 * @param clmNo
	 * @throws Exception
	 */
	private void updateWebPointForCancelClaim(String ordNo, Ord ord, String clmNo) throws Exception {
		InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO;
		List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList;
		//ncp 3차 웹포인트 작업
		infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
		infOrdGodErpDstbClmSearchDTO.setOrdNo(ordNo);
		infOrdGodErpDstbClmSearchDTO.setClmNo(clmNo);
		infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);

		for(InfOrdGodErpDstb infOrdGodErpDstb : infOrdGodErpDstbCheckList){
		    if(ord.getMbrTpCd().equals(MemberEnum.MemberTpCd.UNITY_MBR.toString()) || ord.getMbrTpCd().equals(MemberEnum.MemberTpCd.ONLINE_MBR.toString())) {

		        MbrWebpntHist param = new MbrWebpntHist();
		        param.setWebpntTpCd(WebPointEnum.WEBPNT.toString()); // WEBPNT 웹포인트
		        param.setWebpntResnCd(WebPointEnum.WebPntResnCd.PCH.toString()); // PCH : 구매
		        param.setMbrNo(ord.getMbrNo());
		        param.setOrdNo(ordNo);
		        param.setOrdGodTurn(infOrdGodErpDstb.getOrdGodTurn());
		        param.setQtyTurn(infOrdGodErpDstb.getQtyTurn());
		        param.setClmNo(clmNo);
		        param.setClmWrhsGodTurn(infOrdGodErpDstb.getClmWrhsGodTurn());
		        param.setMallId(ord.getMallId());

		        /*웹포인트 사용 (즉시차감) */
		        if (infOrdGodErpDstb.getWebpntAccmlUntPrc().intValue() > 0) {
		            param.setWebpntDetailResnCd(WebPointEnum.WebPntPchDtlResnCd.PCH_CANCL_DDCT.toString()); // 취소로 인한 차감
		            param.setWebpntAmt(infOrdGodErpDstb.getWebpntAccmlUntPrc());//웹포인트 적립금
		            param.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_CNCL.toString());//사용
		            param.setResnDscr("클레임 으로 적립금액 차감");
		            memberBenefitCommandService.insertWebPoint(param);
		        }

		        /* 웹포인트 적립예정*/
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


	/*
	 * 반품 신청, 접수
	 * 
	 * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimCommandComponent#addReturnClaim(com.plgrim.ncp.biz.claim.data.ClaimBoDTO, com.plgrim.ncp.framework.data.SystemPK)
	 */
	@Override
	public String addReturnClaim(ClaimBoDTO claimDTO, SystemPK systemPK) throws Exception {

		String clmNo 				= claimDTO.getClmNo();							//클레임번호
		String virtlRtgodYn  		= claimDTO.getClmExtend().getVirtlRtgodYn();    //가상반품여부
		String virtlOrdNo     		= claimDTO.getClmExtend().getVirtlOrdNo();      //가상주문번호

		try {
			
//			// 가반품의 경우
//			if("Y".equals(virtlRtgodYn)) {
//				OrderBoDTO orderDTO = new OrderBoDTO();
//				orderDTO.setOrdNo(virtlOrdNo);
//				String virtlDlvCompYn  = orderBoSelectService.getVirtlDlvComptYn(orderDTO);
//				
//				if(!StringService.equalsIgnoreCase(virtlDlvCompYn, "Y") ){
//					throw new ClaimCompleteFailException("Claim.virtual_order.unhandle", null);
//				}
//				
//				List<ClmWrhsGodExtend> extClmWrhsGodList = new ArrayList<ClmWrhsGodExtend>();
//			
//				for(LgsDlvspExtend dlvsp : claimDTO.getLgsDlvspList()){
//					extClmWrhsGodList.addAll(dlvsp.getClmWrhsGodList());
//				}
//			
//				claimDTO.setClmWrhsGodList(extClmWrhsGodList);
//				int godCompCount = orderBoSelectService.selectDlivyDrctGodCompCount(claimDTO);
//				
//				if(godCompCount != 0){
//					throw new ClaimCompleteFailException("Claim.virtual_order.god_error", null);
//				}
//				
//			}

			/**
			 * 접수 수량체크 방어로직 추가
			 */
			for(LgsDlvspExtend dlvsp : claimDTO.getLgsDlvspList()){
				for(ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()){
					//접수가능수량 넘어가면 에러나면서 팅기도록한다.
					
					if (clmGod.getGodTpCd().equals(GoodsType.SET_GOD.toString()) == false 
							&& clmGod.getGodTpCd().equals(GoodsType.PCKAGE_GOD.toString()) == false) {

						List<InfOrdGodErpDstbExtend> ordGodBaseList = new ArrayList<InfOrdGodErpDstbExtend>();
						InfOrdGodErpDstbExtend searchInfOrdGodErpDstb = new InfOrdGodErpDstbExtend();
						searchInfOrdGodErpDstb.setOrdNo(claimDTO.getOrdNo());

						//클레임번호 - 수정후 접수시 환불정보조회를 위해 추가
						//환불대송쿼리 조건에 t.clm_no IS NULL 걸려 있어서 '신청' 인 건은 조회가 않되고 있음.
						searchInfOrdGodErpDstb.setClmNo(claimDTO.getClmNo());

						searchInfOrdGodErpDstb.setOrdGodTurn(clmGod.getOrdGodTurn());
						searchInfOrdGodErpDstb.setDlivyDrctGodNo(clmGod.getDlivyDrctGodNo());
						searchInfOrdGodErpDstb.setQty(clmGod.getClmQty()+"");
						//클레임번호가 비어있는만큼의 row 를 리스트로 가져온다.
						ordGodBaseList =  infOrdGodErpDstbService.selectInfOrdGodErpDstbForRefund(searchInfOrdGodErpDstb);
						
						if(ordGodBaseList!=null){
							if(ordGodBaseList.size() < clmGod.getClmQty().intValue()){//접수 가능수량보다 접수 하려는 수량이 더 크면
								throw new ClaimCompleteFailException("Claim.exceed.available_requisition", null);
							}
						}else{
							throw new ClaimCompleteFailException("Claim.exceed.available_requisition", null);
						}
					}
				}
			}

			if(clmNo == null || "".equals(clmNo))
			{
				//클레임번호 채번
				clmNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_CLM", "CL", DatabaseType.ORACLE);
				claimDTO.setClmNo(clmNo);

	            /* 클레임 접수 데이터 등록 */
				addReturnProcessor(claimDTO, systemPK);

				/**
				 * 13-2-4.접수완료 메세지
				 */
			}
			else
			{
	            
	        	/* 클레임 신청 데이터 초기화 */
				deleteClaimProcessor(claimDTO);
	            	
	        	/* 클레임 신청 데이터 등록 */
				addReturnProcessor(claimDTO, systemPK);

				/**
				 * 13-2-4.접수완료 메세지
				 */

			}
			
			return claimDTO.getClmNo();
		} catch(ClaimCompleteFailException e1) {
			log.error("ReturnComplet Fail" + " exception : {}", claimDTO);
			log.error("ReturnComplet Fail" + " exception : {}", e1);
			log.error(CommonResponseCode.CL_40005_반품_접수_실패.toMessage() + " / "+e1.getDirectMessage());
			throw e1;
	   	 
	    } catch (Exception e2) {
	    	log.error("ReturnComplet Fail" + " exception : {}", claimDTO);
	    	log.error("ReturnComplet Fail" + " exception : {}", e2);
	    	log.error(CommonResponseCode.CL_40005_반품_접수_실패.toMessage() + " / "+e2.getMessage());
	    	throw e2;		    
	    }       		
	}

	/*
     * 반품 신청/접수 등록
     */
	public void addReturnProcessor(ClaimBoDTO claimDTO, SystemPK systemPK) throws Exception {
		
		String adminTpCd 			= claimDTO.getAdminTpCd();						//운영자 유형 코드
		String regtrShopId 			= claimDTO.getRegtrShopId();					//로그인된 매장ID
		String dlivyDrctTpCd   		= claimDTO.getDlivyDrctTpCd();         			//출고지시유형코드

		String ordTp 				= claimDTO.getOrdTp();							//주문유형
		String callTp 				= claimDTO.getCallTp();							//호출유형
		String ordNo 				= claimDTO.getOrdNo();							//주문번호
		String clmNo 				= claimDTO.getClmNo();							//클레임번호
		String clmTpCd       		= claimDTO.getClmTpCd();            			//클레임유형코드
		String clmStatCd       		= claimDTO.getClmStatCd();            			//클레임코드

		String mbrTp 				= claimDTO.getMbrTp();							//회원유형
		String role 				= claimDTO.getRole();							//FO/BO 구분

		String regtrId 				= claimDTO.getRegtrId();

		String dlvComTrnsmisTgtYn 	= "Y";											//택배사수거지시 여부
		if(OrderClaimEnum.ClmRsnEnum.PRC_MOD.toString().equals(claimDTO.getClmResnCd())){
			claimDTO.getClmExtend().setVirtlRtgodYn("Y");
			dlvComTrnsmisTgtYn = "N";
        }
		if(OrderClaimEnum.ClmRsnEnum.SND_CSTMR.toString().equals(claimDTO.getClmResnCd())){
			dlvComTrnsmisTgtYn = "N";
        }
		String virtlRtgodYn  		= claimDTO.getClmExtend().getVirtlRtgodYn();    //가상반품여부
		String repairRtgodYn 		= claimDTO.getRepairRtgodYn();					//수거반품여부
		String cstmrNm 				= claimDTO.getCstmrNm();						//고객명
		String cstmrMobilNo 		= claimDTO.getCstmrMobilNo();					//전화번호
		String rtrvlDrctGrpDgre 	= claimDTO.getRtrvlDrctGrpDgre();				//차수
		String badnReqestCont 		= claimDTO.getBadnReqestCont();					//의뢰내용

		Boolean dlvGbn 				= true;	//회수지시 인경우 true, 출고지시인 경우 false
		Boolean isMbrCheck 			= true;	//통합회원-온라인회원 인 경우 true, 비회원인 경우 false

		BigDecimal refundPrdDlvFee  = claimDTO.getRefundPrdDlvFee();	//총 결제환불금액

		String pgTrnsmisTgtYn		= claimDTO.getClmExtend().getPgTrnsmisTgtYn();
		String mallId               = claimDTO.getMallId();
		String lang                 = claimDTO.getLang();

		/**
		 * 클레임 insert
		 *
		 * ㅁ. 클레임 유형 : CLM_TP
		 * >. 부분취소 : PART_CNCL
		 * >. 전체취소 : ALL_CNCL
		 * >. 일반교환 : GNRL_EXCHG
		 * >. 맞교환 : DRT_EXCHG
		 * >. 반품 : RTGOD
		 *
		 * ㅁ. 클레임상태 : CLM_STAT
		 * >. 신청 : REQST
		 * >. 결제 대기 : PAY_WAIT
		 * >. 접수 : RCEPT
		 * >. 완료 : COMPT
		 * >. 철회 : WTHDRAW
		 *
		 * ㅁ. 클레임 사유 : CLM_RSN
			   >. 고객변심취소 : CSTMR_CHGMIND_CNCL
			   >. 사이즈교환 : SIZE_EXCHG
			   >. 품절취소 : SLDOUT_CNCL
			   >. 배송지연취소 : DLV_DELAY_CNCL
			   >. 이중주문취소 : DPLC_ORD_CNCL
			   >. 시스템오류취소 : SYS_ERR_CNCL
			   >. 누락배송 : OVSITE_DLV
			   >. 택배분실 : HDRY_LOST
			   >. 옵션변경 : OPT_CHG
			   >. 상품불량 : GOD_BADN
			   >. 상품파손 : GOD_DMG
			   >. 상품 오염 : GOD_STAN
			   >. 기타 상품정보상이 : ETC_GOD_INFO_GAP
			   >. 사이즈 정보 상이 : SIZE_INFO_DFFRNC
			   >. 색상 정보 상이 : COLOR_INFO_DFFRNC
			   >. 오배송 : WN_DLV
			   >. 기타 : ETC
		 */
		claimDTO.getClmExtend().setClmNo(       claimDTO.getClmNo()     	);  /* 클레임 번호 		*/
		claimDTO.getClmExtend().setClmStatCd(   clmStatCd					);  /* 클레임 상태 코드ㅁ. 클레임상태 : CLM_STAT   >. 신청 : REQST   >. 결제 대기 : PAY_WAIT   >. 접수 : RCEPT   >. 완료 : COMPT   >. 철회 : WTHDRAW */
		claimDTO.getClmExtend().setClmTpCd(     clmTpCd   					);  /* 클레임 유형 코드ㅁ. 클레임 유형 : CLM_TP   >. 부분취소 : PART_CNCL   >. 전체취소 : ALL_CNCL   >. 일반교환 : GNRL_EXCHG   >. 맞교환 : DRT_EXCHG   >. 반품 : RTGOD */
		claimDTO.getClmExtend().setClmResnCd(   claimDTO.getClmResnCd() 	);  /* 클레임 사유 코드 */
		claimDTO.getClmExtend().setClmResnCont( ""                       	);  /* 클레임 사유 내용 */
		claimDTO.getClmExtend().setRceptAdminId(claimDTO.getRegtrId()   	);  /* 접수 관리자 ID 	*/
		claimDTO.getClmExtend().setRegtrId(     claimDTO.getRegtrId()   	);  /* 등록자 ID 		*/
		claimDTO.getClmExtend().setOrdNo(       claimDTO.getOrdNo()     	);  /* 주문번호 		*/

		if(!"Y".equals(pgTrnsmisTgtYn)) {
			claimDTO.getClmExtend().setPgTrnsmisTgtYn(	"N"   	);  /* 등록자 ID 		*/
		}

		// 마스터에 상세사유 세팅
		loop : for(LgsDlvspExtend dlvsp : claimDTO.getLgsDlvspList()){
			for(ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()){
				if (claimDTO.getClmResnCd().equals(clmGod.getClmResnCd()) && StringService.isNotEmpty(clmGod.getClmResnCont())) {
					claimDTO.getClmExtend().setClmResnCont(clmGod.getClmResnCont());
					break loop;
				}
			}
		}
		
        /* 
         * 클레임 반품/교환시 수량체크
         * 접수화면을 호출해 놓은 상태에서 여러명이 접수를 할 경우 주문수량 보다 많은 클레임수량이 접수 될 수 있다.
         * 접수 시에 가능 수량 한번 더 체크
         */
		List<Map<String, String>> chkClmQtyList = claimService.selectCheckClmQty(claimDTO);

		for (Map<String, String> chkClmQty : chkClmQtyList) {
			String clmQty = chkClmQty.get("clmQty");

			if(StringService.equalsIgnoreCase(clmQty, "0")){

				if("F".equals(claimDTO.getRole())){
				
				}else{
					throw new ClaimCompleteFailException("Claim.impossible_amt_check", null);
				}
			}
		}

		/**
		 * 클레임상태값 설정 < 'CLM_STAT_CD'
		 * 배송비의 결제여부에 따라 접수'RCEPT' or 결제대기'PAY_WAIT'
		 */
		BigDecimal zero = new BigDecimal("0");
		if(refundPrdDlvFee.compareTo(zero) == -1)	//compareTo :::: -1은 작다, 0은 같다, 1은 크다
		{
			claimDTO.getClmExtend().setClmStatCd("PAY_WAIT");//결제대기
		} else {
			if("F".equals(claimDTO.getRole())){
				// REQST, RCEPT 판단 여기서하자..
				claimDTO.getClmExtend().setClmStatCd(clmStatCd);//신청
			}else{
				claimDTO.getClmExtend().setClmStatCd("RCEPT");//접수
			}
		}
		
		claimService.insertClmForRtrvl(claimDTO); //클레임등록

		/**
		 * 클레임메모 저장
		 * MemoTpCd("CLM")
		 * ExpsrYn("N")
		 */
		claimDTO.setMemoTpCd("CLM");
		claimDTO.setExpsrYn("Y");

		if("B".equals(claimDTO.getRole())){
			CsoCnsltMemo csoCnsltMemo = new CsoCnsltMemo();
			csoCnsltMemo.setRegtrId(claimDTO.getRegtrId());
			csoCnsltMemo.setMemoTpCd("CLM");
			csoCnsltMemo.setExpsrYn("Y");
			csoCnsltMemo.setClmNo(clmNo);
			csoCnsltMemo.setMemoCont(claimDTO.getMemoCont());
			
			claimService.updateCsoCnsltMemo(csoCnsltMemo);
		}

		/**
		 * 물류배송지 insert - 화면에서 입력받은 정보
		 * 회원번호, 클레임번호, 배송수거지구분, 배송수단코드 추가해야 함.
		 * 물류배송지 수거지 insert
		 * 물류배송지이력 insert
		 * 회수방식이 편의점택배의 경우 CvnStoreAdapter 를 호출하여 받은 승인번호를 물류배송 승인번호(LGS_DLV.CVNSTOR_HDRY_APRV_NO)에 insert 해야 함 - to be
		 */
		LgsDlvspBoDTO lgsDlvspBoDTO = new LgsDlvspBoDTO();
		lgsDlvspBoDTO.setLgsDlvspList(claimDTO.getLgsDlvspList());
		lgsDlvspBoDTO.setClmTpCd(clmTpCd);
		lgsDlvspBoDTO.setClmResnCd(claimDTO.getClmResnCd());

		//ncp2 - 계산된 물류배송 정보를 담기 위한 TmpDTO
		lgsDlvspBoDTO.setLgsDlvspTmpList(claimDTO.getLgsDlvspTmpList());

		if("NMBR".equals(mbrTp)) {
			//비회원인 경우
			isMbrCheck = false;
		}

		//클레임수거지 등록
		//자사,입점사인 경우 물류배송 추가건
		deliveryService.lgsDlvspProcessorForRtrvl(lgsDlvspBoDTO
				, ordNo					//주문번호
				, clmNo					//클레임번호
				, dlvComTrnsmisTgtYn	//택배사수거지시여부
				, isMbrCheck			//통합회원,온라인회원 - true, 비회원 - false
				, role					//FO/BO 구분
				, regtrId				//로그인ID
				, ordTp					//주문유형
				, callTp				//호출유형
				, adminTpCd				//운영자유형코드
				, regtrShopId			//로그인된 매장ID
		);

		/****************************************************************************************************************
		 * 상품사은품 반품 대상 처리
		 ****************************************************************************************************************/
		
		List<ClmWrhsGodExtend> clmGodGftList = new ArrayList<ClmWrhsGodExtend>();
		
		for(LgsDlvspExtend dlvsp : claimDTO.getLgsDlvspList()){
			for(ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()){
				
				List<ClmWrhsGodExtend> godGftList = claimService.selectRtnTgtGodGftPrm(clmGod);
				
				if(godGftList == null || godGftList.isEmpty()) {
					continue;
				}
				
				// 반품정보는 본 상품과 동일하세 처리
				for (ClmWrhsGodExtend godGft : godGftList) {
					godGft.setClmQty(clmGod.getClmQty());
					godGft.setClmResnCd(clmGod.getClmResnCd());
					godGft.setClmResnCont(clmGod.getClmResnCont());
					godGft.setGodCnncTpCd("WRHS_GOD_CNNC");
				}
				
				clmGodGftList.addAll(godGftList);
			}
		}
		
		claimDTO.getLgsDlvspList().get(0).getClmWrhsGodList().addAll(clmGodGftList);
		
		/****************************************************************************************************************
		 * 주문사은품 체크
		 * 주문사은품을 체크 하여 프로모션 정책이 깨지는 경우 사은품을 반품접수 한다.
		 ****************************************************************************************************************/

		//체크변수
		int resultQty = 0;
		int resultAmt = 0;

		//결과
		List<ClmOrdGodAplPrm> ogapResultList = new ArrayList<ClmOrdGodAplPrm>();


		//1. ord_god_apl_prm 테이블 주문 사은품(ORD_GFT) 조회
		List<OrdGodAplPrm> aplPrmList =  claimService.selectOrdGodAplPrm(claimDTO);


		//2. 1리스트만큼for 돌면서 prm 테이블 조회하여 ORD_GFT_PCH_STDR_CD 컬럼이 QTY_STDR 인지 AMT_STDR 인지 체크
		for(OrdGodAplPrm ordGodAplPrmDto : aplPrmList){//한개 주문에 여러개의 주문사은품 프로모션이 걸릴 수 있음.
			Prm prm = new Prm();
			prm.setPrmNo(ordGodAplPrmDto.getPrmNo());
			prm = promotionService.selectPrm(prm);
			if(prm != null){

				//3. ord_god_apl_prm 의 프로모션번호로 ord_god_turn 으로 주문상품 리스트 조회 (본상품)
				List<OrdGod> ordGodList = claimService.selectOrdGodByOrdGft(ordGodAplPrmDto);

				//4. 주문번호로 clm_wrhs_god 테이블 조회하여 이전 취소내역 조회 (본상품)
				ClaimOrdGftSearchDTO claimOrdGftSearchDTO = new ClaimOrdGftSearchDTO();
				claimOrdGftSearchDTO.setOrdNo(claimDTO.getOrdNo());
				claimOrdGftSearchDTO.setOrdGodList(ordGodList);

				//ncp2 - 주문사은품 대상은 자사만 해당
				claimOrdGftSearchDTO.setPartmalSectCd("MCOM");
				//ncp2 - 주문사은품 대상은 자사만 해당

				List<ClmWrhsGod> beforeClmWrhsGodList = claimService.selectClmWrhsGodForOrdGft(claimOrdGftSearchDTO);

				//5. 접수하려는 상품리스트 조회 (본상품)
				List<InfOrdGodErpDstb> reqClmList = new ArrayList<InfOrdGodErpDstb>();
				for(LgsDlvspExtend dlvsp : claimDTO.getLgsDlvspList()){
					for(ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()){
						InfOrdGodErpDstbExtend searchInfOrdGodErpDstb = new InfOrdGodErpDstbExtend();
						searchInfOrdGodErpDstb.setOrdNo(claimDTO.getOrdNo());
						searchInfOrdGodErpDstb.setOrdGodTurn(clmGod.getOrdGodTurn());
						searchInfOrdGodErpDstb.setDlivyDrctGodNo(clmGod.getDlivyDrctGodNo());
						searchInfOrdGodErpDstb.setQty(clmGod.getClmQty()+"");

						//ncp2 - 주문사은품 대상은 자사만 해당
						searchInfOrdGodErpDstb.setErpIntrlckTgtYn("Y");
						//ncp2 - 주문사은품 대상은 자사만 해당

						reqClmList.addAll( infOrdGodErpDstbService.selectInfOrdGodErpDstbForRefund(searchInfOrdGodErpDstb));
					}
				}

				//6. QTY_STDR 일땐 수량 AMT_STDR 일땐 가격기준으로 계산
				// 3의 주문리스트 - 4의 이전 취소내역 - 5의 접수하려는 내역 = 최종 결제 금액 or 수량
				if("AMT_STDR".equals(prm.getOrdGftPchStdrCd())){//금액기준
					//7. 프로모션 정책이 금액으로 깨지는지 체크 깨지면 ogapResultList 에 해당 주문상품순번이 없을경우 add 한다.
					for(OrdGod chkOrdGod : ordGodList){//대상상품 주문가격
						resultAmt += chkOrdGod.getSaleAmt().intValue();    //아이템단위
					}
					for(ClmWrhsGod chkClmWrhsGod : beforeClmWrhsGodList){//대상상품 이전취소가격 아이템단위
						resultAmt -= chkClmWrhsGod.getSaleAmt().intValue();
					}
					for(InfOrdGodErpDstb chkInfOrdGodErpDstb : reqClmList){
						resultAmt -= chkInfOrdGodErpDstb.getSaleUntPrc().intValue();//앞으로 취소될 가격 수량단위
					}
					if(prm.getOrdGftAplCndAmt().intValue() > resultAmt){//프로모션 정책 가격보다 최종 결제금액이 작으면 정책이 깨졌으니 클레임 목록에 포함시킨다.
						boolean chkInsert = true;
						for(ClmOrdGodAplPrm chkResultDto : ogapResultList){

							//주문상품번호 가 중복인 경우 중복등록
							if(chkResultDto.getOrdGodTurn().equals(ordGodAplPrmDto.getOrdGodGftTurn())){
								chkInsert = false;
							}
						}

						List<OrdGod> chkOrdGodList = new ArrayList<OrdGod>();
						OrdGod chkOrdGod = new OrdGod();
						chkOrdGod.setOrdGodTurn(ordGodAplPrmDto.getOrdGodGftTurn());
						chkOrdGodList.add(chkOrdGod);
						ClaimOrdGftSearchDTO chkClmWrhsGod = new ClaimOrdGftSearchDTO();
						chkClmWrhsGod.setOrdNo(claimDTO.getOrdNo());
						chkClmWrhsGod.setOrdGodList(chkOrdGodList);

						//주문사은품 대상은 자사만 해당
						claimOrdGftSearchDTO.setPartmalSectCd("MCOM");
						//주문사은품 대상은 자사만 해당

						List<ClmWrhsGod> chkClmWrhsGodList = claimService.selectClmWrhsGodForOrdGft(chkClmWrhsGod);
						int chkOrdGodErpCnt = 0;
						if(chkClmWrhsGodList!=null){
							chkOrdGodErpCnt = chkClmWrhsGodList.size();
						}
						if(chkOrdGodErpCnt!=0){//해당상품이 이전 클레임으로 취소되었다면 다시 취소되면 안된다.
							chkInsert = false;
						}
						if(chkInsert){

							OrdGod searchOrdGod = new OrdGod();
							searchOrdGod.setOrdNo(claimDTO.getOrdNo());
							searchOrdGod.setOrdGodTurn(ordGodAplPrmDto.getOrdGodGftTurn());

							//출고상품순번, 배송매장ID, 배송순번 추가 - 부분취소 사은품 생성과 다름
							OrdGodExtend resultOrdGod = orderBoSelectService.selectOrdGodGftDtl(searchOrdGod);

							ClmOrdGodAplPrm resultDto = new ClmOrdGodAplPrm();
							resultDto.setGodNm(resultOrdGod.getGodNm());
							resultDto.setItmNm(resultOrdGod.getItmNm());
							resultDto.setAplQty(resultOrdGod.getOrdQty()+"");
							resultDto.setOrdGodTurn(resultOrdGod.getOrdGodTurn()+"");
							resultDto.setOrdNo(resultOrdGod.getOrdNo()+"");

							resultDto.setGodCnncTpCd("WRHS_GOD_CNNC");	//주문클레임상품연결
							resultDto.setGodTpCd(resultOrdGod.getGodTpCd());
							resultDto.setDlivyDrctGodNo(resultOrdGod.getDlivyDrctGodNo());
							resultDto.setDlvPcupspTurn(resultOrdGod.getDlvPcupspTurn());
							resultDto.setDmstcDlvCstPlcSn(resultOrdGod.getDmstcDlvCstPlcSn());
							resultDto.setDlvTurn(resultOrdGod.getDlvTurn());
							resultDto.setDlvShopId(resultOrdGod.getDlvShopId());
							resultDto.setGodNo(resultOrdGod.getGodNo());
							resultDto.setItmNo(resultOrdGod.getItmNo());

							ogapResultList.add(resultDto);
						}
					}
					resultAmt = 0;//초기화
				}else if("QTY_STDR".equals(prm.getOrdGftPchStdrCd())){//수량기준
					//8. 프로모션 정책이 금액으로 깨지는지 체크 깨지면 ogapResultList 에 해당 주문상품순번이 없을경우 add 한다.
					for(OrdGod chkOrdGod : ordGodList){//대상상품 주문가격
						resultQty += chkOrdGod.getOrdQty(); //아이템단위
					}
					for(ClmWrhsGod chkClmWrhsGod : beforeClmWrhsGodList){//대상상품 이전취소가격 아이템단위
						resultQty -= chkClmWrhsGod.getClmQty();
					}
					if(reqClmList!=null){
						resultQty -= reqClmList.size();//수량단위이므로 size 가 수량이다
					}
					if(prm.getOrdGftAplCndQty().intValue() > resultQty){//프로모션 정책 가격보다 최종 결제금액이 작으면 정책이 깨졌으니 클레임 목록에 포함시킨다.
						boolean chkInsert = true;
						for(ClmOrdGodAplPrm chkResultDto : ogapResultList){
							if(chkResultDto.getOrdGodTurn().equals(ordGodAplPrmDto.getOrdGodGftTurn())){
								chkInsert = false;
							}
						}
						List<OrdGod> chkOrdGodList = new ArrayList<OrdGod>();
						OrdGod chkOrdGod = new OrdGod();
						chkOrdGod.setOrdGodTurn(ordGodAplPrmDto.getOrdGodGftTurn());
						chkOrdGodList.add(chkOrdGod);
						ClaimOrdGftSearchDTO chkClmWrhsGod = new ClaimOrdGftSearchDTO();
						chkClmWrhsGod.setOrdNo(claimDTO.getOrdNo());
						chkClmWrhsGod.setOrdGodList(chkOrdGodList);

						// 주문사은품 대상은 자사만 해당
						claimOrdGftSearchDTO.setPartmalSectCd("MCOM");
						// 주문사은품 대상은 자사만 해당

						List<ClmWrhsGod> chkClmWrhsGodList = claimService.selectClmWrhsGodForOrdGft(chkClmWrhsGod);
						int chkOrdGodErpCnt = 0;
						if(chkClmWrhsGodList!=null){
							chkOrdGodErpCnt = chkClmWrhsGodList.size();
						}
						if(chkOrdGodErpCnt!=0){//해당상품이 이전 클레임으로 취소되었다면 다시 취소되면 안된다.
							chkInsert = false;
						}
						if(chkInsert){

							OrdGod searchOrdGod = new OrdGod();
							searchOrdGod.setOrdNo(claimDTO.getOrdNo());
							searchOrdGod.setOrdGodTurn(ordGodAplPrmDto.getOrdGodGftTurn());

							//출고상품순번, 배송매장ID, 배송순번 추가 - 부분취소 사은품 생성과 다름
							OrdGodExtend resultOrdGod = orderBoSelectService.selectOrdGodGftDtl(searchOrdGod);

							ClmOrdGodAplPrm resultDto = new ClmOrdGodAplPrm();
							resultDto.setGodNm(resultOrdGod.getGodNm());
							resultDto.setItmNm(resultOrdGod.getItmNm());
							resultDto.setAplQty(resultOrdGod.getOrdQty()+"");
							resultDto.setOrdGodTurn(resultOrdGod.getOrdGodTurn()+"");
							resultDto.setOrdNo(resultOrdGod.getOrdNo()+"");

							resultDto.setGodCnncTpCd("WRHS_GOD_CNNC");	//주문클레임상품연결
							resultDto.setGodTpCd(resultOrdGod.getGodTpCd());
							resultDto.setDlivyDrctGodNo(resultOrdGod.getDlivyDrctGodNo());
							resultDto.setDlvPcupspTurn(resultOrdGod.getDlvPcupspTurn());
							resultDto.setDmstcDlvCstPlcSn(resultOrdGod.getDmstcDlvCstPlcSn());
							resultDto.setDlvTurn(resultOrdGod.getDlvTurn());
							resultDto.setDlvShopId(resultOrdGod.getDlvShopId());
							resultDto.setGodNo(resultOrdGod.getGodNo());
							resultDto.setItmNo(resultOrdGod.getItmNo());

							ogapResultList.add(resultDto);

						}
					}
					resultQty = 0;//초기화
				}
			}
		}

		List<ClmWrhsGodExtend> clmOrdGftList  = new ArrayList<ClmWrhsGodExtend>();	//클레임입고상품에 등록될 주문사은품 리스트
		

		//추출 된 주문사은품을 위한 클레임입고상품 정보 생성.
		for(ClmOrdGodAplPrm ordGft : ogapResultList) {
			ClmWrhsGodExtend clmOrdGftDto = new ClmWrhsGodExtend();						//주문사은품을 담을 클레임입고상품
			
			clmOrdGftDto.setOrdNo(ordGft.getOrdNo());
			clmOrdGftDto.setOrdGodTurn(Integer.parseInt(ordGft.getOrdGodTurn()));
			clmOrdGftDto.setGodTpCd(ordGft.getGodTpCd());
			clmOrdGftDto.setDlivyDrctGodNo(ordGft.getDlivyDrctGodNo());
			clmOrdGftDto.setGodCnncTpCd(ordGft.getGodCnncTpCd());

			clmOrdGftDto.setGodNo(ordGft.getGodNo());
			clmOrdGftDto.setGodNm(ordGft.getGodNm());
			clmOrdGftDto.setItmNo(ordGft.getItmNo());
			clmOrdGftDto.setDlvPcupspTurn(ordGft.getDlvPcupspTurn());
			clmOrdGftDto.setDmstcDlvCstPlcSn(ordGft.getDmstcDlvCstPlcSn());
			clmOrdGftDto.setDlvTurn(ordGft.getDlvTurn());
			clmOrdGftDto.setDlvShopId(ordGft.getDlvShopId());

			clmOrdGftDto.setOrdGodTurnAnce(null);
			clmOrdGftDto.setOrdCpstGodTurn(null);
			clmOrdGftDto.setPckageGodTpCd(null);
			clmOrdGftDto.setSortSeq(null);

			clmOrdGftDto.setClmQty(Long.parseLong(ordGft.getAplQty()));
			clmOrdGftDto.setClmResnCd(claimDTO.getLgsDlvspList().get(0).getClmWrhsGodList().get(0).getClmResnCd());

			clmOrdGftList.add(clmOrdGftDto);
		}
        
        /*
         * 주문사은품 정보를 담기위한 클레임수거지정보 - 기본적으로 첫 수거지에 주문사은품 생성하기로 함.
         * 클레임입고상품 정보 Setting.
         */
		claimDTO.getLgsDlvspList().get(0).getClmWrhsGodList().addAll(clmOrdGftList);

		/*****************************************************************************************************************/


		/**
		 * 클레임입고상품 insert
		 * 입고될 교환상품에 대한 정보를 등록
		 */
		claimService.clmWrhsGodProcessor(claimDTO);

		// 클레임 god_sumry 등록
		this.clmSumryUpdate(clmNo);

		/**
		 * 주문클레임상품연결 insert
		 * 물류회수지시상품 insert
		 */
		claimService.insertOrdClmGodCnncForRtrvl(claimDTO
				, dlvGbn				//회수지시 인경우 true, 출고지시인 경우 false
		);

		/**
		 * 클레임입고상품적용프로모션insert
		 * (주문클레임상품연결테이블 join 주문상품적용프로모션테이블 로 클레임입고상품적용프로모션 select insert)
		 */
		ClaimReceiptDTO claimReceiptDTO = new ClaimReceiptDTO();
		claimReceiptDTO.setOrdNo(ordNo);
		claimReceiptDTO.setClmNo(clmNo);
		claimReceiptDTO.setAdminId(regtrId);
		claimReceiptDTO.setUdterId(regtrId);
		claimService.insertClmWrhsGodAplPrmByOrd(claimReceiptDTO);

		/**
		 * 물류회수지시상품 insert
		 */
		deliveryService.lgsRtrvlDrctGodProcessor(lgsDlvspBoDTO
				, clmTpCd				//클레임유형코드
				, virtlRtgodYn			//가상반품여부
				, dlvComTrnsmisTgtYn		//택배사수거지시여부
				, repairRtgodYn			//수거반품여부
				, cstmrNm         		//고객명
				, cstmrMobilNo     		//전화번호
				, rtrvlDrctGrpDgre 		//차수
				, badnReqestCont 		//의뢰내용
				, role					//FO/BO 구분
				, ordTp					//주문유형
				, callTp					//호출유형
				, regtrShopId			//로그인된 매장ID
				, dlivyDrctTpCd			//회수지시유형 != 클레임유형코드 이므로 따로 세팅
				, adminTpCd				//운영자유형코드
		);

		/**
		 * 9.인터페이스주문상품erp분배 update < 클레임번호 update
		 * claimSearchDTO.getDlvspClmOrdGod() 관련하여 수정 필요.
		 */
		InfOrdGodErpDstbUpdateDTO infOrdGodErpDstbDTO = new InfOrdGodErpDstbUpdateDTO();
		infOrdGodErpDstbDTO.setOrdNo(ordNo);
		OrdGodErpDTO ordGodErpDTO = new OrdGodErpDTO();
		List<OrdGodErpDTO> ordGodTurnListForErp = new ArrayList<OrdGodErpDTO>();

		//물류배송지 정보 추출
		for (LgsDlvspExtend lgsDlvspExtend : claimDTO.getLgsDlvspList())
		{
			//클레임입고상품 정보 추출
			for (ClmWrhsGodExtend clmWrhsGodEntity : lgsDlvspExtend.getClmWrhsGodList())
			{

				if (clmWrhsGodEntity.getDlivyDrctGodNo() == null || clmWrhsGodEntity.getDlivyDrctGodNo().equals(""))
				{
					//세트상품의 경우 껍데기는 제외 시켜야 함.

				} else {

					ordGodErpDTO = new OrdGodErpDTO();
					//ordGodErpDTO.setOrdNo(ordNo);
					ordGodErpDTO.setClmNo(clmNo);
					ordGodErpDTO.setOrdGodTurn(clmWrhsGodEntity.getOrdGodTurn()+"");//자료형 주의
					ordGodErpDTO.setQty(clmWrhsGodEntity.getClmQty()+"");//자료형 주의
					ordGodErpDTO.setDlivyDrctGodNo(clmWrhsGodEntity.getDlivyDrctGodNo());
					ordGodErpDTO.setErpGodSnArry(clmWrhsGodEntity.getErpGodSnArry());
					ordGodErpDTO.setClmResnCont(clmWrhsGodEntity.getClmResnCont());
					ordGodTurnListForErp.add(ordGodErpDTO);
				}
			}
		}

		infOrdGodErpDstbDTO.setOrdGodTurnList(ordGodTurnListForErp);
		infOrdGodErpDstbService.updateInfOrdGodErpDstbByClmUnit(infOrdGodErpDstbDTO);
		
		String partYn = claimRepository.getPartClaimPossibleYn(ordNo, "RTGOD");
		// 부분 클레임 처리가 불가할경우 전체 처리 되었는지 확인 (주문할인 및 에스크로 주문) 
		if ("N".equals(partYn)) {
			if (claimRepository.getNoClaimGodCount(ordNo) > 0) {
				throw new ClaimCompleteFailException("Claim.impossible.part.accept", null);
			}
		}

		/**
		 * 7.클레임입고상품 update < 금액
		 */
		claimReceiptDTO.setOrdNo(claimDTO.getOrdNo());
		claimReceiptDTO.setClmNo(claimDTO.getClmNo());
		claimService.updateClmWrhsGodToPrcByErp(claimReceiptDTO);

		/**
		 * 8.클레임 update < 클레임상태, 금액
		 (가격정보 클레임입고상품테이블 join 하여 sum 해서 update)
		 */
		if("DXM".equals(mallId) || "MBM".equals(mallId) || "SAM".equals(mallId)){
			BigDecimal totalDlvCstCpnDcAmt= new BigDecimal(0);
			
			for (LgsDlvspExtend lgsDlvspTmp2 : lgsDlvspBoDTO.getLgsDlvspTmpList())
			{
				if(StringService.equalsIgnoreCase(lgsDlvspTmp2.getDlvPcupspSectCd(), "CLM_PCUPSP"))
				{
					//화면에서 넘겨받은 물류배송 리스트 만큼 생성
					for (LgsDlv lgsDlvTmp : lgsDlvspTmp2.getLgsDlvList()) {
						if(StringService.isNotEmpty(lgsDlvTmp.getDlvCstCpnNo())){
							totalDlvCstCpnDcAmt = totalDlvCstCpnDcAmt.add(lgsDlvTmp.getDlvCstCpnDcAmt());
							claimReceiptDTO.setDlvCstCpnNo(lgsDlvTmp.getDlvCstCpnNo());
						}
					}
				}
			}
			claimReceiptDTO.setDlvCstDlvAmt(totalDlvCstCpnDcAmt);
			claimReceiptDTO.setLang(lang);
			claimReceiptDTO.setClmTpCd("RTGOD");
		}

		claimService.updateClmToPrcByClmWrhsGod(claimReceiptDTO);

		Clm clm = new Clm();
		clm.setClmNo(claimDTO.getClmNo());
		clm = claimBatchService.selectClmByClmNo(clm);
		
		// 에스크로 결제이면서 회수로 인한 배송비가 발생할 경우 배송비추가결제 처리를 하기위한 플래그
		boolean hasEscrAddPay = false;
		if ("N".equals(partYn) && clm.getDlvCstSumAmt().compareTo(BigDecimal.ZERO) < 0) {
			hasEscrAddPay = true;
		}
		
		/**
		 * 클레임상태값 설정 < 'CLM_STAT_CD'
		 * 배송비의 결제여부에 따라 접수'RCEPT' or 결제대기'PAY_WAIT'
		 */
		
		if(hasEscrAddPay || clm.getStdrCrncySumAmt().compareTo(BigDecimal.ZERO) < 0)
		{
			claimDTO.getClmExtend().setClmStatCd("PAY_WAIT");//결제대기
		} else {
			if("F".equals(claimDTO.getRole())){
				/** 
				 * FO에서 접수한 경우 클레임 귀책여부에 따른 클레임 상태를 구해온다.
				 * - 자사 귀책사유가 1개라도 존재할 경우 : 신청(REQST)
				 * - 전체가 고객 귀책사유일 경우 경우 : 접수(RCEPT) 
				 */
				claimDTO.getClmExtend().setClmStatCd(claimService.getClmStatCdByClmRsn(claimDTO));
			}else{
				claimDTO.getClmExtend().setClmStatCd("RCEPT");//접수
			}
		}
		Clm clmForUpdateStat = new Clm();
		clmForUpdateStat.setClmNo(clmNo);
		clmForUpdateStat.setClmStatCd(claimDTO.getClmExtend().getClmStatCd());
		claimService.updateClmStatCd(clmForUpdateStat);


		/**
		 * 반품시 추가배송비 발생하여 환불금액이 마이너스로 들어올경우 PAY 테이블에 결제대기로 insert
		 * @param ordNo
		 * @return
		 */
		if(clm.getStdrCrncySumAmt().compareTo(BigDecimal.ZERO) < 0) {
			BigDecimal addPayAmt = clm.getStdrCrncySumAmt().multiply(new BigDecimal("-1"));//추가결제금액 마이너스금액에 -1을 곱해서 양수로만든다
			insertAddPayWait(regtrId, ordNo, clmNo, addPayAmt);
		} else if (hasEscrAddPay) {
			BigDecimal addPayAmt = clm.getDlvCstSumAmt().multiply(new BigDecimal("-1"));//추가결제금액 마이너스금액에 -1을 곱해서 양수로만든다
			insertAddPayWait(regtrId, ordNo, clmNo, addPayAmt);
		}

		clm = new Clm();
		clm.setClmNo(clmNo);
		clm = claimBatchService.selectClmByClmNo(clm);

		Ord ord = orderSelectService.selectOrdEntity(ordNo);

		// 에스크로 배송이 시작되었을 경우 차후 에스크로 취소를 위한 정산보류처리를 한다.
		RefundPayDTO refundPayDTO = claimRepository.selectPayInfoForCalculateReserve(ordNo);
		
		if ("Y".equals(refundPayDTO.getEscrYn()) && OrderClaimEnum.EscrStatCd.ESCR_DLV_REG.toString().equals(refundPayDTO.getEscrStatCd())) {
			refundPayDTO.setRfdResn(CodeUtil.getCode(systemPK.getLang(), clm.getClmResnCd()).getCdNm());
			
			refundPayComponent.reserveCalculate(refundPayDTO);
			
			if (refundPayDTO.isRfdSuccess() == false) {
				throw new RuntimeException(refundPayDTO.getRfdRsltmsg());
			}
			
			refundPayDTO.setEscrStatCd(OrderClaimEnum.EscrStatCd.ESCR_CAL_RESRVE.toString());
			refundPayDTO.setRegtrId(claimDTO.getRegtrId());
			
			claimRepository.updateEscrStatCd(refundPayDTO);
		}
		
		// 매장 반품의 경우 환불처리
		if("OFF_SHOP".equals(claimDTO.getCallTp())){
			this.refundPayForClaim(clm, ord, false, false, false);
		}

	}


	/*
	 * 교환접수
	 * 
	 * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimCommandComponent#addExchangeClaim(com.plgrim.ncp.biz.claim.data.ClaimBoDTO, com.plgrim.ncp.framework.data.SystemPK)
	 */
	@Override
	public String addExchangeClaim(ClaimBoDTO claimDTO, SystemPK systemPK) throws Exception {

		try {
			
			for(LgsDlvspExtend dlvsp : claimDTO.getLgsDlvspList()){
				for(ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()){
					if (clmGod.getGodTpCd().equals(GoodsType.SET_GOD.toString()) == false 
							&& clmGod.getGodTpCd().equals(GoodsType.PCKAGE_GOD.toString()) == false) {

						List<InfOrdGodErpDstbExtend> ordGodBaseList = new ArrayList<InfOrdGodErpDstbExtend>();
						InfOrdGodErpDstbExtend searchInfOrdGodErpDstb = new InfOrdGodErpDstbExtend();
						searchInfOrdGodErpDstb.setOrdNo(claimDTO.getOrdNo());

						//클레임번호
						//- 수정후 접수시 환불정보조회를 위해 추가
						//환불대송쿼리 조건에 t.clm_no IS NULL 걸려 있어서 '신청' 인 건은 조회가 않되고 있음.
						searchInfOrdGodErpDstb.setClmNo(claimDTO.getClmNo());

						searchInfOrdGodErpDstb.setOrdGodTurn(clmGod.getOrdGodTurn());
						searchInfOrdGodErpDstb.setDlivyDrctGodNo(clmGod.getDlivyDrctGodNo());
						if(clmGod.getClmQty()!=null){//출고되는상품은 수량이없다.
							searchInfOrdGodErpDstb.setQty(clmGod.getClmQty()+"");
						}
						//클레임번호가 비어있는만큼의 row 를 리스트로 가져온다.
						ordGodBaseList =  infOrdGodErpDstbService.selectInfOrdGodErpDstbForRefund(searchInfOrdGodErpDstb);
						if(ordGodBaseList!=null){
							if(clmGod.getClmQty()!=null){
								if(ordGodBaseList.size() < clmGod.getClmQty().intValue()){//접수 가능수량보다 접수 하려는 수량이 더 크면
									throw new ClaimCompleteFailException("Claim.exceed.available_requisition", null);
								}
							}
						}else{
							throw new ClaimCompleteFailException("Claim.exceed.available_requisition", null);
						}
					}
				}
			}
			
			String clmNo = claimDTO.getClmNo();	//클레임번호

			if (clmNo == null || clmNo.equals(""))
			{
	        	/* 클레임번호 채번 */
				clmNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_CLM", "CL", DatabaseType.ORACLE);
				claimDTO.setClmNo(clmNo);
	           
	            /* 클레임 신청 데이터 등록 */
				addExchangeProcessor(claimDTO, systemPK, null);

			}
			else
			{
	        	/* 클레임 신청 데이터 초기화 */
				deleteClaimProcessor(claimDTO);
	        	
	        	/* 클레임 신청 데이터 등록 */
				addExchangeProcessor(claimDTO, systemPK, null);

			}
			
			return clmNo; 
		} catch(ClaimCompleteFailException e1) {
			log.error("ExchangeComplete Fail" + " exception : {}", claimDTO);
			log.error("ExchangeComplete Fail" + " exception : {}", e1);
			log.error(CommonResponseCode.CL_40006_교환_접수_실패.toMessage() + " / "+e1.getDirectMessage());
			throw e1;	   	 
		} catch (Exception e2) {
			log.error("ExchangeComplete Fail" + " exception : {}", claimDTO);
			log.error("ExchangeComplete Fail" + " exception : {}", e2);
			log.error(CommonResponseCode.CL_40006_교환_접수_실패.toMessage() + " / "+e2.getMessage());
			throw e2;		    
		}       				
	}

	/**
	 * 교환 신청/접수 등록
	 * 
	 * @param claimDTO
	 * @param systemPK
	 * @param request
	 * @throws Exception
	 */
	public void addExchangeProcessor(ClaimBoDTO claimDTO, SystemPK systemPK, HttpServletRequest request) throws Exception {

		String adminTpCd 			= claimDTO.getAdminTpCd();						//운영자 유형 코드
		String regtrShopId 			= claimDTO.getRegtrShopId();					//로그인된 매장ID

		String ordTp 				= claimDTO.getOrdTp();							//주문유형
		String callTp 				= claimDTO.getCallTp();							//호출유형
		String ordNo 				= claimDTO.getOrdNo();							//주문번호
		String clmNo 				= claimDTO.getClmNo();							//클레임번호
		String clmTpCd       		= claimDTO.getClmTpCd();            			//클레임유형코드
		String clmStatCd       		= claimDTO.getClmStatCd();            			//클레임상태코드
		String dlivyDrctTpCd   		= claimDTO.getDlivyDrctTpCd();         			//출고지시유형코드

		String role 				= claimDTO.getRole();							//FO/BO 구분

		String regtrId 				= claimDTO.getRegtrId();

		String dlvComTrnsmisTgtYn 	= "Y";											//택배사수거지시 여부
		String virtlRtgodYn  		= "N";    										//가상반품여부
		String repairRtgodYn 		= claimDTO.getRepairRtgodYn();					//수거반품여부
		String cstmrNm 				= claimDTO.getCstmrNm();						//고객명
		String cstmrMobilNo 		= claimDTO.getCstmrMobilNo();					//전화번호 
		String rtrvlDrctGrpDgre 	= claimDTO.getRtrvlDrctGrpDgre();				//차수
		String badnReqestCont 		= "";											//의뢰내용

		if(OrderClaimEnum.ClmRsnEnum.SND_CSTMR.toString().equals(claimDTO.getClmResnCd())){
			dlvComTrnsmisTgtYn = "N";
        }
		
		BigDecimal payCrncyPayAmt   = claimDTO.getPayCrncyPayAmt();					//총배송비

		Boolean dlvGbn 				= true;	//회수지시 인경우 true, 출고지시인 경우 false
		Boolean isMbrCheck 			= true;	//통합회원-온라인회원 인 경우 true, 비회원인 경우 false
		String mallId               = claimDTO.getMallId();
		String lang                 = claimDTO.getLang();

		/**
		 * 클레임 insert
		 * >. 일반교환 : GNRL_EXCHG
		 * ㅁ. 클레임상태 : CLM_STAT
		 * >. 접수 : RCEPT
		 */
		claimDTO.getClmExtend().setClmNo(       claimDTO.getClmNo()     );  /* 클레임 번호CL || YYYYMMDD || 7자리 Cycle Sequence */
		claimDTO.getClmExtend().setClmStatCd(   clmStatCd				);  /* 클레임 상태 코드ㅁ. 클레임상태 : CLM_STAT   >. 신청 : REQST   >. 결제 대기 : PAY_WAIT   >. 접수 : RCEPT   >. 완료 : COMPT   >. 철회 : WTHDRAW */
		claimDTO.getClmExtend().setClmTpCd(     clmTpCd   				);  /* 클레임 유형 코드ㅁ. 클레임 유형 : CLM_TP   >. 부분취소 : PART_CNCL   >. 전체취소 : ALL_CNCL   >. 일반교환 : GNRL_EXCHG   >. 맞교환 : DRT_EXCHG   >. 반품 : RTGOD */
		claimDTO.getClmExtend().setClmResnCd(   claimDTO.getClmResnCd() );  /* 클레임 사유 코드 */
		claimDTO.getClmExtend().setClmResnCont( ""                      );  /* 클레임 사유 내용 */
		claimDTO.getClmExtend().setRceptAdminId(claimDTO.getRegtrId()   );  /* 접수 관리자 ID */
		claimDTO.getClmExtend().setVirtlRtgodYn(virtlRtgodYn            );  /* 가상 반품 여부 */
		claimDTO.getClmExtend().setRegtrId(     claimDTO.getRegtrId()   );  /* 등록자 ID등록한 관리자 번호 */
		claimDTO.getClmExtend().setOrdNo(       claimDTO.getOrdNo()     );  /* 주문번호 */

		// 마스터에 상세사유 세팅
		loop : for(LgsDlvspExtend dlvsp : claimDTO.getLgsDlvspList()){
			for(ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()){
				if (claimDTO.getClmResnCd().equals(clmGod.getClmResnCd()) && StringService.isNotEmpty(clmGod.getClmResnCont())) {
					claimDTO.getClmExtend().setClmResnCont(clmGod.getClmResnCont());
					break loop;
				}
			}
		}
		
        /* 
         * 클레임 반품/교환시 수량체크
         * 접수화면을 호출해 놓은 상태에서 여러명이 접수를 할 경우 주문수량 보다 많은 클레임수량이 접수 될 수 있다.
         * 접수 시에 가능 수량 한번 더 체크
         */
		List<Map<String, String>> chkClmQtyList = claimService.selectCheckClmQty(claimDTO);

		for (Map<String, String> chkClmQty : chkClmQtyList) {
			
			String clmQty = chkClmQty.get("clmQty");

			if(StringService.equalsIgnoreCase(clmQty, "0")) {

				if("F".equals(claimDTO.getRole())){
				}else{
					throw new ClaimCompleteFailException("Claim.impossible_amt_check", null);
				}

			}
		}

		/**
		 * 클레임상태값 설정 < 'CLM_STAT_CD'
		 * 배송비의 결제여부에 따라 접수'RCEPT' or 결제대기'PAY_WAIT'
		 */
		BigDecimal zero = new BigDecimal("0");
		if(payCrncyPayAmt.compareTo(zero) == 1) {	//compareTo :::: -1은 작다, 0은 같다, 1은 크다
			claimDTO.getClmExtend().setClmStatCd("PAY_WAIT");//결제대기
		} else {
			if("F".equals(claimDTO.getRole())){
				claimDTO.getClmExtend().setClmStatCd(clmStatCd);//신청
			}else{
				claimDTO.getClmExtend().setClmStatCd("RCEPT");//접수
			}
		}
		
		claimService.insertClmForRtrvl(claimDTO); //클레임등록

		/**
		 * 클레임메모 저장
		 * MemoTpCd("CLM")
		 * ExpsrYn("N")
		 */
		claimDTO.setMemoTpCd("CLM");
		claimDTO.setExpsrYn("Y");

		CsoCnsltMemo csoCnsltMemo = new CsoCnsltMemo();
		csoCnsltMemo.setRegtrId(claimDTO.getRegtrId());
		csoCnsltMemo.setMemoTpCd("CLM");
		csoCnsltMemo.setExpsrYn("Y");
		csoCnsltMemo.setClmNo(clmNo);
		csoCnsltMemo.setMemoCont(claimDTO.getMemoCont());

		if("B".equals(claimDTO.getRole())){
			claimService.updateCsoCnsltMemo(csoCnsltMemo);
		}


		/**
		 * 물류배송지 insert - 화면에서 입력받은 정보
		 * 회원번호, 클레임번호, 배송수거지구분, 배송수단코드 추가해야 함.
		 * 물류배송지 수거지 insert
		 * 물류배송지이력 insert
		 * 회수방식이 편의점택배의 경우 CvnStoreAdapter 를 호출하여 받은 승인번호를 물류배송 승인번호(LGS_DLV.CVNSTOR_HDRY_APRV_NO)에 insert 해야 함 - to be
		 */
		LgsDlvspBoDTO lgsDlvspBoDTO = new LgsDlvspBoDTO();
		lgsDlvspBoDTO.setLgsDlvspList(claimDTO.getLgsDlvspList());
		lgsDlvspBoDTO.setClmTpCd(clmTpCd);
		lgsDlvspBoDTO.setClmResnCd(claimDTO.getClmResnCd());
		
		//ncp2 - 계산된 물류배송 정보를 담기 위한 TmpDTO
		lgsDlvspBoDTO.setLgsDlvspTmpList(claimDTO.getLgsDlvspTmpList());

		//클레임수거지 등록
		//자사,입점사인 경우 물류배송 추가건
		deliveryService.lgsDlvspProcessorForRtrvl(lgsDlvspBoDTO
				, ordNo					//주문번호
				, clmNo					//클레임번호
				, dlvComTrnsmisTgtYn	//택배사수거지시여부
				, isMbrCheck			//통합회원,온라인회원 - true, 비회원 - false
				, role					//FO/BO 구분
				, regtrId				//로그인ID
				, ordTp					//주문유형
				, callTp				//호출유형
				, adminTpCd				//운영자유형코드
				, regtrShopId			//로그인된 매장ID
		);

		/**
		 * 클레임입고상품 insert
		 * 입고될 교환상품에 대한 정보를 등록
		 */
		claimService.clmWrhsGodProcessor(claimDTO);
	
		this.clmSumryUpdate(clmNo);		

		/**
		 * 주문클레임상품연결 insert
		 * 물류회수지시상품 insert
		 */
		claimService.insertOrdClmGodCnncForRtrvl(claimDTO, dlvGbn);				//회수지시 인경우 true, 출고지시인 경우 false

		/**
		 * 클레임입고상품적용프로모션insert
		 * (주문클레임상품연결테이블 join 주문상품적용프로모션테이블 로 클레임입고상품적용프로모션 select insert)
		 */
		ClaimReceiptDTO claimReceiptDTO = new ClaimReceiptDTO();
		claimReceiptDTO.setOrdNo(ordNo);
		claimReceiptDTO.setClmNo(clmNo);
		claimReceiptDTO.setAdminId(regtrId);
		claimReceiptDTO.setUdterId(regtrId);
		claimService.insertClmWrhsGodAplPrmByOrd(claimReceiptDTO);

		/**
		 * 물류회수지시상품 insert
		 */
		deliveryService.lgsRtrvlDrctGodProcessor(lgsDlvspBoDTO
				, clmTpCd				//클레임유형코드
				, virtlRtgodYn			//가상반품여부
				, dlvComTrnsmisTgtYn		//택배사수거지시여부
				, repairRtgodYn			//수거반품여부
				, cstmrNm         		//고객명
				, cstmrMobilNo     		//전화번호
				, rtrvlDrctGrpDgre 		//차수
				, badnReqestCont 		//의뢰내용
				, role					//FO/BO 구분
				, ordTp					//주문유형
				, callTp					//호출유형
				, regtrShopId			//로그인된 매장ID
				, dlivyDrctTpCd			//회수지시유형
				, adminTpCd				//운영자유형코드
		);

		/**
		 * 10.인터페이스주문상품erp분배 update < 클레임번호 update
		 * claimSearchDTO.getDlvspClmOrdGod() 관련하여 수정 필요.
		 */
		InfOrdGodErpDstbUpdateDTO infOrdGodErpDstbDTO = new InfOrdGodErpDstbUpdateDTO();
		infOrdGodErpDstbDTO.setOrdNo(ordNo);
		OrdGodErpDTO ordGodErpDTO = new OrdGodErpDTO();
		List<OrdGodErpDTO> ordGodTurnListForErp = new ArrayList<OrdGodErpDTO>();
		
		//물류배송지 정보 추출
		for (LgsDlvspExtend lgsDlvspExtend : claimDTO.getLgsDlvspList()) {
			
			//클레임 수거지인 경우만
			if(StringService.equalsIgnoreCase(lgsDlvspExtend.getDlvPcupspSectCd(), "CLM_DLVSP")) {
				continue;
			}
			
			//클레임입고상품 정보 추출
			for (ClmWrhsGodExtend clmWrhsGodEntity : lgsDlvspExtend.getClmWrhsGodList()) {
				ordGodErpDTO = new OrdGodErpDTO();
				ordGodErpDTO.setClmNo(clmNo);
				ordGodErpDTO.setOrdGodTurn(clmWrhsGodEntity.getOrdGodTurn()+"");//자료형 주의
				ordGodErpDTO.setQty(clmWrhsGodEntity.getClmQty()+"");//자료형 주의
				ordGodErpDTO.setDlivyDrctGodNo(clmWrhsGodEntity.getDlivyDrctGodNo());
				ordGodTurnListForErp.add(ordGodErpDTO);
			}
			
		}
		
		infOrdGodErpDstbDTO.setOrdGodTurnList(ordGodTurnListForErp);
		infOrdGodErpDstbService.updateInfOrdGodErpDstbByClmUnit(infOrdGodErpDstbDTO);

		/**
		 * 7.클레임입고상품 update < 금액
		 */
		claimReceiptDTO.setOrdNo(claimDTO.getOrdNo());
		claimReceiptDTO.setClmNo(claimDTO.getClmNo());
		claimService.updateClmWrhsGodToPrcByErp(claimReceiptDTO);


		/**
		 * 9.클레임 update < 클레임상태, 금액
		 (가격정보 클레임입고상품테이블 join 하여 sum 해서 update)
		 */
		if("DXM".equals(mallId) || "MBM".equals(mallId) || "SAM".equals(mallId)){
			BigDecimal totalDlvCstCpnDcAmt= new BigDecimal(0);
			
			for (LgsDlvspExtend lgsDlvspTmp2 : lgsDlvspBoDTO.getLgsDlvspTmpList()) {
				
				//클레임 수거지인경우만
				if(StringService.equalsIgnoreCase(lgsDlvspTmp2.getDlvPcupspSectCd(), "CLM_DLVSP")) {
					continue;
				}
				
				//화면에서 넘겨받은 물류배송 리스트 만큼 생성
				for (LgsDlv lgsDlvTmp : lgsDlvspTmp2.getLgsDlvList()) {
					if(StringService.isNotEmpty(lgsDlvTmp.getDlvCstCpnNo())){
						totalDlvCstCpnDcAmt = totalDlvCstCpnDcAmt.add(lgsDlvTmp.getDlvCstCpnDcAmt());
						claimReceiptDTO.setDlvCstCpnNo(lgsDlvTmp.getDlvCstCpnNo());
					}
				}
			
			}
			claimReceiptDTO.setDlvCstDlvAmt(totalDlvCstCpnDcAmt);
			claimReceiptDTO.setLang(lang);
			claimReceiptDTO.setClmTpCd("GNRL_EXCHG");
		}
		
		claimService.updateClmToPrcByClmWrhsGod(claimReceiptDTO);
		
        /* 
         * ************************************************************************************************************
         * 교환주문상품생성 Start
         * orderBoCommandComponent.addProcessor(OrderBoDTO orderDTO) 참조
         * ************************************************************************************************************
         */
		ObjectMapper logMapper = new ObjectMapper();

		log.info("## logMapper #################################### " + logMapper.writeValueAsString(lgsDlvspBoDTO));

		//클레임배송지 등록
		//자사,입점사인 경우 물류배송 추가건
		deliveryService.lgsDlvspProcessorForDlivy(lgsDlvspBoDTO
				, ordNo					//주문번호
				, clmNo					//클레임번호
				, dlvComTrnsmisTgtYn	//택배사수거지시여부
				, isMbrCheck			//통합회원,온라인회원 - true, 비회원 - false
				, role					//FO/BO 구분
				, regtrId				//로그인ID
				, ordTp					//주문유형
				, callTp				//호출유형
		);

        /* 
         * 주문상품 등록
         * 
         * 1. 기 등록된 주문상품의 수량만큼 인터페이스주문상품ERP분배 조회 - INF_ORD_GOD_ERP_DSTB
         * 2. 교환선택한 단품정보 조회 - god_itm
         * 3. 기 등록된 주문상품을 조회하여 해당칼럼 수정 후 insert - ord_god
         * 
         * 주문상품적용프로모션 등록
         * 1. 기 등록된 주문상품번호의 적용유형[APL_TP] 을 취소[CNCL] 로 등록
         * 2. 교환으로 생성된 주문상품번호의 적용유형[APL_TP] 을 적용[APL] 로 등록
         * 
         * 물류출고지시상품 등록 - LGS_DLIVY_DRCT_GOD
         * 물류출고지시상품이력 등록 - LGS_DLIVY_DRCT_GOD_HIST
         * 
         * 인터페이스주문상품ERP분배 등록 - INF_ORD_GOD_ERP_DSTB
         * 1. 교환선택한 수량만큼 insert
         * 2. 클레임번호 update
         * 
         */
		List<InfOrdGodErpDstb> ordGodErpList 		= null;	//인터페이스주문상품ERP분배 등록용

		OrdGodExtend ordGod 						= null;		//주문상품 등록용
		ClmWrhsGodExtend ordClmGodCnnc				= null;		//주문클레임상품연결 등록용
		List<LgsDlivyDrctGod> lgsDlivyDrctGodList 	= new ArrayList<LgsDlivyDrctGod>();//실시간 예약영수 발행용
		LgsDlivyDrctGod lgsDlivyDrctGod 			= null;		//물류출고지시상품 등록용
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist 	= null;		//물류출고지시상품 이력 등록용

		InfOrdGodErpDstb ordGodErp					= null;		//인터페이스주문상품ERP분배 등록용


		Map<String, Object> conditions = null;
		
		/* 주문상품순번채번 채번용 */
		int newOrdGodTurn = 0;
		
		/* 클레임입고상품순번 조회용 */
		int newClmWrhsGodTurn = 0;

		/* SKU번호 조회용 */
		String skuNo = "";

		/* 물류출고지시상품순번 채번용 */
		String newDlivyDrctGodNo = "";
		
		/* 물류배송 배송지 배송순번 조회용 */
		int dlvTurn = 0;
		
		/* 인터페이스주문상품ERP분배 수량순번 채번용 */
		int newQtyTurn = 0;
		int newOrdGodQtyTurn = 0;
		int newOrdGodQtyTurnAdd = 0;	//ncp2 수량순번, 주문상품수량순번 증가용

		log.info("## logMapper #################################### " + logMapper.writeValueAsString(claimDTO));

		//주문상품, 주문상품적용프로모션, 물류출고지시상품, 인터페이스주문상품ERP분배 등록시 계산될 항목

		//Sum
		BigDecimal stdrCrncyUntSumPrc 			= new BigDecimal("0");  /* Sum 기준 통화 단가 */
		BigDecimal payExchgRtCrncyUntSumPrc 	= new BigDecimal("0");  /* Sum 결제 환율 통화 단가 */
		BigDecimal rtlSumPrc 					= new BigDecimal("0");  /* Sum 정소가 */
		BigDecimal saleUntSumPrc            	= new BigDecimal("0");  /* Sum 판매 단가 */
		BigDecimal allDcSumAmt        			= new BigDecimal("0");  /* Sum 전체 할인 금액 */
		BigDecimal webDcUntSumPrc           	= new BigDecimal("0");  /* Sum 웹 할인 단가 */
		BigDecimal empDcUntSumPrc           	= new BigDecimal("0");  /* Sum 임직원 할인 단가 */
		BigDecimal godDcUntSumPrc           	= new BigDecimal("0");  /* Sum 상품 할인 단가 */
		BigDecimal imdtlDcUntSumPrc     		= new BigDecimal("0");  /* Sum 즉시 할인 단가 */
		BigDecimal bundleDcUntSumPrc        	= new BigDecimal("0");  /* Sum 묶음 할인 단가 */
		BigDecimal crsDcUntSumPrc           	= new BigDecimal("0");  /* Sum 교차 할인 단가 */
		BigDecimal godCpnDcUntSumPrc        	= new BigDecimal("0");  /* Sum 상품 쿠폰 할인 단가 */
		BigDecimal bskCpnDcUntSumPrc        	= new BigDecimal("0");  /* Sum 장바구니 쿠폰 할인 단가 */
		BigDecimal unityPntUseUntSumPrc     	= new BigDecimal("0");  /* Sum 통합 포인트 사용 단가 */
		BigDecimal evtPntUseUntSumPrc       	= new BigDecimal("0");  /* Sum 이벤트 포인트 사용 단가 */
		BigDecimal webpntUseUntSumPrc       	= new BigDecimal("0");  /* Sum 웹포인트 사용 단가 */
		BigDecimal unityPntAccmlUntSumPrc  		= new BigDecimal("0");  /* Sum 통합 포인트 적립 단가 */
		BigDecimal evtPntAccmlUntSumPrc     	= new BigDecimal("0");  /* Sum 이벤트 포인트 적립 단가 */
		BigDecimal webpntAccmlUntSumPrc     	= new BigDecimal("0");  /* Sum 웹포인트 적립 단가 */

		//Unit
		BigDecimal stdrCrncyUntPrc    			= null;  /* 기준 통화 단가 */
		BigDecimal payExchgRtCrncyUntPrc   		= null;  /* 결제 환율 통화 단가 */
		BigDecimal rtlPrc    					= null;  /* 정소가 */
		BigDecimal saleUntPrc               	= null;  /* 판매 단가 */
		BigDecimal webDcUntPrc              	= null;  /* 웹 할인 단가 */
		BigDecimal empDcUntPrc              	= null;  /* 임직원 할인 단가 */
		BigDecimal godDcUntPrc              	= null;  /* 상품 할인 단가 */
		BigDecimal imdtlDcUntPrc	        	= null;  /* 즉시 할인 단가 */
		BigDecimal bundleDcUntPrc           	= null;  /* 묶음 할인 단가 */
		BigDecimal crsDcUntPrc              	= null;  /* 교차 할인 단가 */
		BigDecimal godCpnDcUntPrc           	= null;  /* 상품 쿠폰 할인 단가 */
		BigDecimal bskCpnDcUntPrc           	= null;  /* 장바구니 쿠폰 할인 단가 */
		BigDecimal unityPntUseUntPrc        	= null;  /* 통합 포인트 사용 단가 */
		BigDecimal evtPntUseUntPrc          	= null;  /* 이벤트 포인트 사용 단가 */
		BigDecimal webpntUseUntPrc          	= null;  /* 웹포인트 사용 단가 */
		BigDecimal unityPntAccmlUntPrc      	= null;  /* 통합 포인트 적립 단가 */
		BigDecimal evtPntAccmlUntPrc        	= null;  /* 이벤트 포인트 적립 단가 */
		BigDecimal webpntAccmlUntPrc        	= null;  /* 웹포인트 적립 단가 */

		BigDecimal allDcUntAmt        			= new BigDecimal("0");  /* 전체 할인 금액 단가 */

		for (LgsDlvspExtend lgsDlvspExtend : claimDTO.getLgsDlvspList()) {
			//클레임배송지 인 경우만
			if(StringService.equalsIgnoreCase(lgsDlvspExtend.getDlvPcupspSectCd(), "CLM_PCUPSP")) {
				continue;
			}
			
			//배송지순번 추출 - 이미 등록된 배송지
			int newDlvPcupspTurn = lgsDlvspExtend.getDlvPcupspTurn();
			
			/*
			 * 교환시 세트대표상품 인서트 하기 위해 교환상품순번 저장하는 변수 
			 */
			List<Integer> extOrdGodTurnList= new ArrayList<Integer>();
			
			//교환접수시 교환해야 할 상품정보
			for (ClmWrhsGodExtend clmWrhsGodExtend : lgsDlvspExtend.getClmWrhsGodList()) {

				//교환배송비 계산시 생성된 배송지 DTO
				for (LgsDlvspExtend lgsDlvspTmpExtend : claimDTO.getLgsDlvspTmpList()) {
					
					//클레임배송지만
					if(StringService.equalsIgnoreCase(lgsDlvspTmpExtend.getDlvPcupspSectCd(), "CLM_PCUPSP")) {
						continue;
					}
					
					for (LgsDlv lgsDlv : lgsDlvspTmpExtend.getLgsDlvList()) {
						//주문 - 교환접수 배송지 등록시 주문번호,배송지순번으로 배송순번 조회
						//ncp2 입점사의 경우 주문번호, 배송지순번, 클레임번호, 국내배송정책 일련번호 의 배송순번 조회.
						//클레임입고상품 for 안으로 이동.

						if((lgsDlv.getOrdNo() == ordNo) &&
								(lgsDlv.getDlvPcupspTurn() == newDlvPcupspTurn) &&
								(lgsDlv.getClmNo() == clmNo) &&
								(lgsDlv.getDmstcDlvCstPlcSn().longValue() == clmWrhsGodExtend.getDmstcDlvCstPlcSn().longValue())
								) {
							dlvTurn = lgsDlv.getDlvTurn();
						}
					}
					
				}

				/* 
                 * 주문상품 등록
                 * 1. 교환선택한 단품정보 조회 - god_itm
                 * 2. 기 등록된 주문상품을 조회하여 해당칼럼 수정 후 insert - ord_god
                 */
				ordGod = new OrdGodExtend();	//주문상품 등록용

				conditions = Maps.newHashMap();
				conditions.put("ord_no", ordNo);

                /* 주문상품순번채번 */
				newOrdGodTurn = getIdGenService().generateDBOrder(sqlSession1, "ord_god", "ord_god_turn", conditions, DatabaseType.ORACLE);
				
		        /* 
		         * 회수시 생성된 클레임입고상품 정보 추출
		         */
				for (LgsDlvspExtend lgsDlvspTmp : claimDTO.getLgsDlvspList()){
					
					//수거지만
					if(StringService.equalsIgnoreCase(lgsDlvspTmp.getDlvPcupspSectCd(), "CLM_DLVSP")){
						continue;
					}
					
					//수거지 등록 후 등록된 클레임입고상품 정보 추출
					for (ClmWrhsGodExtend clmWrhsGodTmp : lgsDlvspTmp.getClmWrhsGodList()){
						//클레임입고상품 등록시 생성된 클레임입고상품 선택
						if(clmWrhsGodTmp.getOrdGodTurn() == clmWrhsGodExtend.getOrdGodTurn()){
							newClmWrhsGodTurn = clmWrhsGodTmp.getClmWrhsGodTurn();
						}
					}
					
				}
        		
		        /*
		         * 출고지시상품번호
		         * 인터페이스주문상품ERP분배 등록시 지정하기위해 미리생성
		         */
				newDlivyDrctGodNo = getIdGenService().generateDBNumber(sqlSession1, "sq_lgs_dlivy_drct_god", "RO", DatabaseType.ORACLE);

		        /*
				 * 교환상품의 주문번호에 해당하는 수량만큼 ERP 분배테이블에서 조회
				 */
				InfOrdGodErpDstbExtend infOrdGodErpDstbExtend = new InfOrdGodErpDstbExtend();
				infOrdGodErpDstbExtend.setOrdNo(clmWrhsGodExtend.getOrdNo());					//교환접수시 주문번호
				infOrdGodErpDstbExtend.setOrdGodTurn(clmWrhsGodExtend.getOrdGodTurn());			//교환접수시 주문상품순번
				infOrdGodErpDstbExtend.setClmNo(clmNo);											//교환접수시 생성된 클레임번호
				infOrdGodErpDstbExtend.setOrdQty(clmWrhsGodExtend.getOrdQty());					//교환접수시 교환수량
				List<InfOrdGodErpDstb> ordGodErpTmpList = infOrdGodErpDstbService.selectInfOrdGodErpDstbForClm(infOrdGodErpDstbExtend);

				/** 회수상품 기준으로 조회해온 수량이 교환 출고수량보다 적을경우 출고수량을 입고수량에 맞춘다. - 비정상적인 클라이언트 데이터로 입고, 출고수량이 안맞을경우 */
				if (clmWrhsGodExtend.getOrdQty() > ordGodErpTmpList.size()) {
					clmWrhsGodExtend.setOrdQty((long)ordGodErpTmpList.size());
				}
				
				/* 인터페이스주문상품ERP분배 수량순번 채번용 */
				newQtyTurn = getIdGenService().generateDBOrder(sqlSession1, "inf_ord_god_erp_dstb", "qty_turn", conditions, DatabaseType.ORACLE);

				//주문 - 교환접수시 교환할 단품번호의 sku_no 조회.
				GodExtend godExtend= orderBoSelectService.selectGodItmInfo(clmWrhsGodExtend);
				skuNo = godExtend.getSkuNo();

				//리스트가 초기화 되면서 마지막 Row만 담김 >> for 밖으로 이동.
				ordGodErpList = new ArrayList<InfOrdGodErpDstb>();
				
				//수량순번, 주문상품수량순번 증가용 초기화.
				newOrdGodQtyTurnAdd = 0;

				//해당교환상품의 수량만큼 인터페이스주문상품ERP분배 내역 조회
				for (InfOrdGodErpDstb infOrdGodErpDstb : ordGodErpTmpList) {

					stdrCrncyUntPrc   		= infOrdGodErpDstb.getStdrCrncyUntPrc();			/* 기준 통화 단가 */
					payExchgRtCrncyUntPrc   = infOrdGodErpDstb.getPayExchgRtCrncyUntPrc();		/* 결제 환율 통화 단가 */
					rtlPrc   				= infOrdGodErpDstb.getRtlPrc();						/* 정소가 */
					saleUntPrc              = infOrdGodErpDstb.getSaleUntPrc();					/* 판매 단가 */
					webDcUntPrc             = infOrdGodErpDstb.getWebDcUntPrc();              	/* 웹 할인 단가 */
					empDcUntPrc             = infOrdGodErpDstb.getEmpDcUntPrc();              	/* 임직원 할인 단가 */
					godDcUntPrc             = infOrdGodErpDstb.getGodDcUntPrc();              	/* 상품 할인 단가 */
					imdtlDcUntPrc       	= infOrdGodErpDstb.getImdtlDcUntPrc();        		/* 즉시 할인 단가 */
					bundleDcUntPrc          = infOrdGodErpDstb.getBundleDcUntPrc();           	/* 묶음 할인 단가 */
					crsDcUntPrc             = infOrdGodErpDstb.getCrsDcUntPrc();              	/* 교차 할인 단가 */
					godCpnDcUntPrc          = infOrdGodErpDstb.getGodCpnDcUntPrc();           	/* 상품 쿠폰 할인 단가 */
					bskCpnDcUntPrc          = infOrdGodErpDstb.getBskCpnDcUntPrc();           	/* 장바구니 쿠폰 할인 단가 */
					unityPntUseUntPrc       = infOrdGodErpDstb.getUnityPntUseUntPrc();        	/* 통합 포인트 사용 단가 */
					evtPntUseUntPrc         = infOrdGodErpDstb.getEvtPntUseUntPrc();          	/* 이벤트 포인트 사용 단가 */
					webpntUseUntPrc         = infOrdGodErpDstb.getWebpntUseUntPrc();          	/* 웹포인트 사용 단가 */
					unityPntAccmlUntPrc     = infOrdGodErpDstb.getUnityPntAccmlUntPrc();      	/* 통합 포인트 적립 단가 */
					evtPntAccmlUntPrc       = infOrdGodErpDstb.getEvtPntAccmlUntPrc();        	/* 이벤트 포인트 적립 단가 */
					webpntAccmlUntPrc       = infOrdGodErpDstb.getWebpntAccmlUntPrc();        	/* 웹포인트 적립 단가 */

					if(stdrCrncyUntPrc   		== null){stdrCrncyUntPrc       	= new BigDecimal("0");}  /* 기준 통화 단가          */
					if(payExchgRtCrncyUntPrc   	== null){payExchgRtCrncyUntPrc	= new BigDecimal("0");}  /* 결제 환율 통화 단가     */
					if(rtlPrc   				== null){rtlPrc               	= new BigDecimal("0");}  /* 정소가                  */
					if(saleUntPrc              	== null){saleUntPrc            	= new BigDecimal("0");}  /* 판매 단가               */
					if(webDcUntPrc             	== null){webDcUntPrc           	= new BigDecimal("0");}  /* 웹 할인 단가            */
					if(empDcUntPrc             	== null){empDcUntPrc           	= new BigDecimal("0");}  /* 임직원 할인 단가        */
					if(godDcUntPrc             	== null){godDcUntPrc           	= new BigDecimal("0");}  /* 상품 할인 단가          */
					if(imdtlDcUntPrc       		== null){imdtlDcUntPrc         	= new BigDecimal("0");}  /* 온라인 클럽 할인 단가   */
					if(bundleDcUntPrc          	== null){bundleDcUntPrc       	= new BigDecimal("0");}  /* 묶음 할인 단가          */
					if(crsDcUntPrc             	== null){crsDcUntPrc           	= new BigDecimal("0");}  /* 교차 할인 단가          */
					if(godCpnDcUntPrc          	== null){godCpnDcUntPrc        	= new BigDecimal("0");}  /* 상품 쿠폰 할인 단가     */
					if(bskCpnDcUntPrc          	== null){bskCpnDcUntPrc        	= new BigDecimal("0");}  /* 장바구니 쿠폰 할인 단가 */
					if(unityPntUseUntPrc       	== null){unityPntUseUntPrc    	= new BigDecimal("0");}  /* 통합 포인트 사용 단가   */
					if(evtPntUseUntPrc         	== null){evtPntUseUntPrc      	= new BigDecimal("0");}  /* 이벤트 포인트 사용 단가 */
					if(webpntUseUntPrc         	== null){webpntUseUntPrc      	= new BigDecimal("0");}  /* 웹포인트 사용 단가      */
					if(unityPntAccmlUntPrc     	== null){unityPntAccmlUntPrc  	= new BigDecimal("0");}  /* 통합 포인트 적립 단가   */
					if(evtPntAccmlUntPrc       	== null){evtPntAccmlUntPrc     	= new BigDecimal("0");}  /* 이벤트 포인트 적립 단가 */
					if(webpntAccmlUntPrc       	== null){webpntAccmlUntPrc     	= new BigDecimal("0");}  /* 웹포인트 적립 단가      */

					allDcUntAmt    			= new BigDecimal("0");  						 /* 전체 할인 금액 단가  초기화 */
					allDcUntAmt 			= allDcUntAmt.add(godDcUntPrc       		);   /* 상품 할인 단가 */
					allDcUntAmt 			= allDcUntAmt.add(bundleDcUntPrc    		);   /* 묶음 할인 단가 */
					allDcUntAmt 			= allDcUntAmt.add(crsDcUntPrc       		);   /* 교차 할인 단가 */
					allDcUntAmt 			= allDcUntAmt.add(godCpnDcUntPrc    		);   /* 상품 쿠폰 할인 단가 */
					allDcUntAmt 			= allDcUntAmt.add(bskCpnDcUntPrc    		);   /* 장바구니 쿠폰 할인 단가 */

					//인터페이스주문상품ERP분배 의 수량1개 금액을 교환수량만큼 Sum.
					stdrCrncyUntSumPrc		= stdrCrncyUntSumPrc.add( 		stdrCrncyUntPrc   		);	/* 기준 통화 단가 */
					payExchgRtCrncyUntSumPrc= payExchgRtCrncyUntSumPrc.add( payExchgRtCrncyUntPrc   );	/* 결제 환율 통화 단가 */
					//정소가는 단위금액으로 표시 되어야 함.
					rtlSumPrc				= rtlPrc;												/* 정소가 */
					saleUntSumPrc          	= saleUntSumPrc.add(         	saleUntPrc          	);	/* 판매 단가 */
					webDcUntSumPrc         	= webDcUntSumPrc.add(        	webDcUntPrc         	); 	/* 웹 할인 단가 */
					empDcUntSumPrc         	= empDcUntSumPrc.add(        	empDcUntPrc         	); 	/* 임직원 할인 단가 */
					godDcUntSumPrc         	= godDcUntSumPrc.add(        	godDcUntPrc         	); 	/* 상품 할인 단가 */
					imdtlDcUntSumPrc   		= imdtlDcUntSumPrc.add(  		imdtlDcUntPrc   		); 	/* 즉시 할인 단가 */  
					bundleDcUntSumPrc      	= bundleDcUntSumPrc.add(     	bundleDcUntPrc      	); 	/* 묶음 할인 단가 */
					crsDcUntSumPrc         	= crsDcUntSumPrc.add(        	crsDcUntPrc         	); 	/* 교차 할인 단가 */
					godCpnDcUntSumPrc      	= godCpnDcUntSumPrc.add(     	godCpnDcUntPrc      	); 	/* 상품 쿠폰 할인 단가 */
					bskCpnDcUntSumPrc      	= bskCpnDcUntSumPrc.add(     	bskCpnDcUntPrc      	); 	/* 장바구니 쿠폰 할인 단가 */
					unityPntUseUntSumPrc   	= unityPntUseUntSumPrc.add(  	unityPntUseUntPrc   	); 	/* 통합 포인트 사용 단가 */
					evtPntUseUntSumPrc     	= evtPntUseUntSumPrc.add(    	evtPntUseUntPrc     	); 	/* 이벤트 포인트 사용 단가 */
					webpntUseUntSumPrc     	= webpntUseUntSumPrc.add(    	webpntUseUntPrc     	); 	/* 웹포인트 사용 단가 */
					unityPntAccmlUntSumPrc 	= unityPntAccmlUntSumPrc.add(	unityPntAccmlUntPrc 	); 	/* 통합 포인트 적립 단가 */
					evtPntAccmlUntSumPrc   	= evtPntAccmlUntSumPrc.add(  	evtPntAccmlUntPrc   	); 	/* 이벤트 포인트 적립 단가 */
					webpntAccmlUntSumPrc   	= webpntAccmlUntSumPrc.add(  	webpntAccmlUntPrc   	); 	/* 웹포인트 적립 단가 */

					//전체할인금액
					allDcSumAmt				= allDcSumAmt.add(allDcUntAmt        );	/* 기준 통화 단가 */;

					ordGodErp = new InfOrdGodErpDstb();
					ordGodErp.setOrdNo(					clmWrhsGodExtend.getOrdNo()					);	/* 주문번호                */
					ordGodErp.setOrdGodTurn(			newOrdGodTurn								);	/* 주문상품순번            */


					//조회수량 만큼 루프를 돌면서 수량순번을 채번해햐 하나 현재 채번 못함. >>>

   					/* 인터페이스주문상품ERP분배 수량순번 채번용 */
					InfOrdGodErpDstb newTurn = infOrdGodErpDstbService.selectInfOrdGodErpDstbQtyTurn(ordGodErp);

                    /* 조회수량 만큼 루프를 돌면서 수량순번을 채번해야 하나 현재 채번 않됨 >>> DB 제약사항 R_1062 적용 이후
            		 * 인터페이스주문상품ERP분배 등록 - INF_ORD_GOD_ERP_DSTB
                     * 1. 교환선택한 수량만큼 insert
                     * 2. 클레임번호 update
                     * 
                     * 생성된 인터페이스주문상품ERP분배 정보를 수량별로 저장하지 않고 리스트에 담고 있기 때문에 수량순번, 주문상품수량순번이 같아지는 현상 발생.
                     * 로직에서 1씩 증가시켜줘야 함. >> newOrdGodQtyTurnAdd++
                     */

					newQtyTurn = newTurn.getQtyTurn() + newOrdGodQtyTurnAdd;
					newOrdGodQtyTurn = newTurn.getOrdGodQtyTurn() + newOrdGodQtyTurnAdd;

					ordGodErp.setQtyTurn(				newQtyTurn									);	/* 수량순번                */
					ordGodErp.setOrdGodQtyTurn(			newOrdGodQtyTurn							);	/* 주문상품수량순번        */

					ordGodErp.setRegtrId(regtrId);
					ordGodErp.setUdterId(regtrId);
					
   					//주문 - 교환접수시 교환할 단품번호의 sku_no 조회.
					ordGodErp.setSkuNo(					skuNo       								);	/* SKU번호                 */

					//조회한 인터페이스주문상품ERP분배 정보를 새로 등록할 ERP분배로 복사
					ordGodErp.setCrncyCd(				OrderClaimEnum.KRW.toString()				);	/* 통화코드                */
					ordGodErp.setStdrCrncyUntPrc(       infOrdGodErpDstb.getStdrCrncyUntPrc()       );	/* 기준 통화 단가          */
					ordGodErp.setPayExchgRtCrncyUntPrc( infOrdGodErpDstb.getPayExchgRtCrncyUntPrc() );  /* 결제 환율 통화 단가     */
					ordGodErp.setRtlPrc(                infOrdGodErpDstb.getRtlPrc()                );  /* 정소가                  */
					ordGodErp.setSaleUntPrc(            infOrdGodErpDstb.getSaleUntPrc()            );  /* 판매 단가               */
					ordGodErp.setWebDcUntPrc(           infOrdGodErpDstb.getWebDcUntPrc()           );  /* 웹 할인 단가            */
					ordGodErp.setEmpDcUntPrc(           infOrdGodErpDstb.getEmpDcUntPrc()           );  /* 임직원 할인 단가        */
					ordGodErp.setGodDcUntPrc(           infOrdGodErpDstb.getGodDcUntPrc()           );  /* 상품 할인 단가          */
					ordGodErp.setImdtlDcUntPrc(     	infOrdGodErpDstb.getImdtlDcUntPrc()     	);  /* 즉시 할인 단가   	   */
					ordGodErp.setBundleDcUntPrc(        infOrdGodErpDstb.getBundleDcUntPrc()        );  /* 묶음 할인 단가          */
					ordGodErp.setCrsDcUntPrc(           infOrdGodErpDstb.getCrsDcUntPrc()           );  /* 교차 할인 단가          */
					ordGodErp.setGodCpnDcUntPrc(        infOrdGodErpDstb.getGodCpnDcUntPrc()        );  /* 상품 쿠폰 할인 단가     */
					ordGodErp.setBskCpnDcUntPrc(        infOrdGodErpDstb.getBskCpnDcUntPrc()        );  /* 장바구니 쿠폰 할인 단가 */
					ordGodErp.setUnityPntUseUntPrc(     infOrdGodErpDstb.getUnityPntUseUntPrc()     );  /* 통합 포인트 사용 단가   */
					ordGodErp.setEvtPntUseUntPrc(       infOrdGodErpDstb.getEvtPntUseUntPrc()       );  /* 이벤트 포인트 사용 단가 */
					ordGodErp.setWebpntUseUntPrc(       infOrdGodErpDstb.getWebpntUseUntPrc()       );  /* 웹포인트 사용 단가      */
					ordGodErp.setUnityPntAccmlUntPrc(   infOrdGodErpDstb.getUnityPntAccmlUntPrc()   );  /* 통합 포인트 적립 단가   */
					ordGodErp.setEvtPntAccmlUntPrc(     infOrdGodErpDstb.getEvtPntAccmlUntPrc()     );  /* 이벤트 포인트 적립 단가 */
					ordGodErp.setWebpntAccmlUntPrc(     infOrdGodErpDstb.getWebpntAccmlUntPrc()     );  /* 웹포인트 적립 단가      */
					//출고지시상품번호
					ordGodErp.setDlivyDrctGodNo(		newDlivyDrctGodNo							);	/* 출고지시상품번호			*/

					Functions.variAbleSetN(ordGodErp);

					//ncp2 ERP연동 대상여부가 무조건 'N' 으로 세팅되는 현상으로 Functions.variAbleSetN(ordGodErp) 이후로 위치이동.
					ordGodErp.setErpIntrlckTgtYn(     	infOrdGodErpDstb.getErpIntrlckTgtYn()     	);  /* ERP 연동 대상 여부   */


					ordGodErpList.add(ordGodErp);
                    
            		/* 인터페이스주문상품ERP분배 등록 - INF_ORD_GOD_ERP_DSTB
                     * 1. 교환선택한 수량만큼 insert
                     * 2. 클레임번호 update
                     */

					//로직변경으로 인한 추가 >>> 강제로 수량순번, 주문상품수량순번을 1씩 증가시킴.
					newOrdGodQtyTurnAdd++;

				}//해당교환상품의 수량만큼 인터페이스주문상품ERP분배 내역 조회

				//주문상품 정보 생성
				ordGod.setNewOrdGodTurn(		newOrdGodTurn						);	//주문상품순번
				ordGod.setNewDlvPcupspTurn(		newDlvPcupspTurn					);	//배송수거지순번
				ordGod.setOrdQty(				clmWrhsGodExtend.getOrdQty()		);	//주문수량 - 교환수량

				ordGod.setStdrCrncyAmt(         stdrCrncyUntSumPrc         			);  /* 기준 통화 단가 */
				ordGod.setPayExchgRtCrncyAmt(   payExchgRtCrncyUntSumPrc   			);  /* 결제 환율 통화 단가 */
				ordGod.setRtlPrc(               rtlSumPrc                  			);  /* 정소가 */
				ordGod.setSaleAmt(              saleUntSumPrc              			);  /* 판매 단가 */
				ordGod.setWebDcAmt(             webDcUntSumPrc             			);  /* 웹 할인 단가 */
				ordGod.setEmpDcAmt(             empDcUntSumPrc             			);  /* 임직원 할인 단가 */
				ordGod.setAllDcAmt(             allDcSumAmt                			);  /* 전체 할인 금액 */
				ordGod.setGodDcAmt(             godDcUntSumPrc             			);  /* 상품 할인 단가 */
				ordGod.setImdtlDcAmt(       	imdtlDcUntSumPrc       				);  /* 즉시 할인 단가 */
				ordGod.setBundleDcAmt(          bundleDcUntSumPrc          			);  /* 묶음 할인 단가 */
				ordGod.setCrsDcAmt(             crsDcUntSumPrc             			);  /* 교차 할인 단가 */
				ordGod.setGodCpnDcAmt(          godCpnDcUntSumPrc          			);  /* 상품 쿠폰 할인 단가 */
				ordGod.setBskCpnDcAmt(          bskCpnDcUntSumPrc          			);  /* 장바구니 쿠폰 할인 단가 */
				ordGod.setUnityPntUseAmt(       unityPntUseUntSumPrc       			);  /* 통합 포인트 사용 단가 */
				ordGod.setEvtPntUseAmt(         evtPntUseUntSumPrc         			);  /* 이벤트 포인트 사용 단가 */
				ordGod.setWebpntUseAmt(         webpntUseUntSumPrc         			);  /* 웹포인트 사용 단가 */
				ordGod.setUnityPntAccmlAmt(     unityPntAccmlUntSumPrc     			);  /* 통합 포인트 적립 단가 */
				ordGod.setWebpntAccmlAmt(       webpntAccmlUntSumPrc       			);  /* 웹포인트 적립 단가 */
				ordGod.setRegtrId(				regtrId								);	//작업자

				ordGod.setOrdNo(				clmWrhsGodExtend.getOrdNo()			);	//주문번호
				ordGod.setOrdGodTurn(			clmWrhsGodExtend.getOrdGodTurn()	);	//주문상품순번
				ordGod.setDlvPcupspTurn(		clmWrhsGodExtend.getDlvPcupspTurn()	);	//배송수거지순번
				ordGod.setGodNo(				clmWrhsGodExtend.getGodNo()			);	//주문상품번호
				ordGod.setItmNo(				clmWrhsGodExtend.getItmNo()			);	//주문단품번호 - 교환출고용

				orderBoService.insertOrdGodForClm(ordGod);

        		/* 인터페이스주문상품ERP분배 등록 - INF_ORD_GOD_ERP_DSTB
                 * 1. 교환선택한 수량만큼 insert
                 * 2. 클레임번호 update
                 */

				if(ordGodErpList.size() > 0) {
					for (InfOrdGodErpDstb infOrdGodErpDstb : ordGodErpList) {
						infOrdGodErpDstbService.insertInfOrdGodErpDstbForClm(infOrdGodErpDstb);
					}
				}

				stdrCrncyUntSumPrc 			= new BigDecimal("0");  /* Sum 기준 통화 단가 */
				payExchgRtCrncyUntSumPrc 	= new BigDecimal("0");  /* Sum 결제 환율 통화 단가 */
				rtlSumPrc 					= new BigDecimal("0");  /* Sum 정소가 */
				saleUntSumPrc            	= new BigDecimal("0");  /* Sum 판매 단가 */
				allDcSumAmt        			= new BigDecimal("0");  /* Sum 전체 할인 금액 */
				webDcUntSumPrc           	= new BigDecimal("0");  /* Sum 웹 할인 단가 */
				empDcUntSumPrc           	= new BigDecimal("0");  /* Sum 임직원 할인 단가 */
				godDcUntSumPrc           	= new BigDecimal("0");  /* Sum 상품 할인 단가 */
				imdtlDcUntSumPrc     		= new BigDecimal("0");  /* Sum 즉시 할인 단가 */
				bundleDcUntSumPrc        	= new BigDecimal("0");  /* Sum 묶음 할인 단가 */
				crsDcUntSumPrc           	= new BigDecimal("0");  /* Sum 교차 할인 단가 */
				godCpnDcUntSumPrc        	= new BigDecimal("0");  /* Sum 상품 쿠폰 할인 단가 */
				bskCpnDcUntSumPrc        	= new BigDecimal("0");  /* Sum 장바구니 쿠폰 할인 단가 */
				unityPntUseUntSumPrc     	= new BigDecimal("0");  /* Sum 통합 포인트 사용 단가 */
				evtPntUseUntSumPrc       	= new BigDecimal("0");  /* Sum 이벤트 포인트 사용 단가 */
				webpntUseUntSumPrc       	= new BigDecimal("0");  /* Sum 웹포인트 사용 단가 */
				unityPntAccmlUntSumPrc  	= new BigDecimal("0");  /* Sum 통합 포인트 적립 단가 */
				evtPntAccmlUntSumPrc     	= new BigDecimal("0");  /* Sum 이벤트 포인트 적립 단가 */
				webpntAccmlUntSumPrc     	= new BigDecimal("0");  /* Sum 웹포인트 적립 단가 */

				orderBoService.insertOrdGodAplPrmForClmList(ordGod, conditions, regtrId);

                /* 
                 * 주문 구성상품연결 등록
                 * 1. 일반교환/맞교환 패키지/세트 상품 인 경우 ORD_CPST_GOD_CNNC 등록
                 */
				String godTp = clmWrhsGodExtend.getGodTpCd();

				if (godTp.equals(GoodsType.SET_GOD.toString()) || godTp.equals(GoodsType.PCKAGE_GOD.toString()))
				{
					OrdCpstGodCnnc ordCpstGodCnnc = new OrdCpstGodCnnc();
					ordCpstGodCnnc.setOrdNo(			clmWrhsGodExtend.getOrdNo()			);	// 주문 번호             		
					ordCpstGodCnnc.setOrdGodTurn(		clmWrhsGodExtend.getOrdGodTurn()	);  // 주문 상품 순번        		
					ordCpstGodCnnc.setOrdCpstGodTurn(	newOrdGodTurn						);  // 주문 구성 상품 순번   		
					ordCpstGodCnnc.setPckageGodTpCd(	clmWrhsGodExtend.getGodTpCd()		);  // 패키지형상품 유형 코드		
					ordCpstGodCnnc.setCpstGodQty(		clmWrhsGodExtend.getOrdQty()		);  // 구성 상품 수량        		         		                  
					ordCpstGodCnnc.setRegtrId(			regtrId								);  // 등록자 ID             		
					ordCpstGodCnnc.setUdterId(			regtrId								);  // 수정자 ID             		
					orderBoService.insertOrdCpstGodCnncForClm(ordCpstGodCnnc);
				}                                                                               

                
                /* 
                 * 주문클레임상품연결 등록 - ORD_CLM_GOD_CNNC
                 * 상품연결유형 > 출고상품연결 : DLIVY_GOD_CNNC
                 */
				ordClmGodCnnc = new ClmWrhsGodExtend();	//주문클레임상품연결 등록용

				ordClmGodCnnc.setOrdNo(			ordNo							);	//주문번호
				ordClmGodCnnc.setOrdGodTurn(	newOrdGodTurn					);	//주문상품순번
				ordClmGodCnnc.setGodCnncTpCd(	clmWrhsGodExtend.getGodCnncTpCd());	//상품연결유형코드 - DLIVY_GOD_CNNC
				ordClmGodCnnc.setOrdClmQty(		clmWrhsGodExtend.getOrdQty()	);	//주문 클레임 수량
				ordClmGodCnnc.setRegtrId(		regtrId							);	//등록자
				ordClmGodCnnc.setClmNo(			clmNo							);	//클레임번호
				ordClmGodCnnc.setClmWrhsGodTurn(newClmWrhsGodTurn				);	//클레임입고상품번호

				claimService.insertOrdClmGodCnncForRtrvl(ordClmGodCnnc);
                
                
                /* 
                 * 물류출고지시상품 등록 - LGS_DLIVY_DRCT_GOD
                 */
				lgsDlivyDrctGod = new LgsDlivyDrctGod();	//물류출고지시상품 등록용

				lgsDlivyDrctGod.setDlivyDrctGodNo(	newDlivyDrctGodNo					);	//출고지시상품번호
				lgsDlivyDrctGod.setOrdNo(			clmWrhsGodExtend.getOrdNo()			);	//주문번호
				lgsDlivyDrctGod.setOrdGodTurn(		newOrdGodTurn						);	//주문상품순번

				if(clmWrhsGodExtend.getPckageGodTurn() == null || clmWrhsGodExtend.getPckageGodTurn() == 0) {
					lgsDlivyDrctGod.setPckageGodTurn(	null								);	//패키지형상품번호
				} else {
					lgsDlivyDrctGod.setPckageGodTurn(	clmWrhsGodExtend.getPckageGodTurn()	);	//패키지형상품번호
				}

				if (clmWrhsGodExtend.getGodTpCd().equals(GoodsType.PCHS_GFT.toString()) || clmWrhsGodExtend.getGodTpCd().equals(GoodsType.CNVRS_GFT.toString())) {
					lgsDlivyDrctGod.setGftYn(		"Y"									);	//사은품여부
				} else {
					lgsDlivyDrctGod.setGftYn(		"N"									);	//사은품여부
				}
				
				if(true){ // 교환시 출고는 wms로 고정
					//mall 분리
					God tempGod = goodsService.getGoods(clmWrhsGodExtend.getGodNo());
					String shopId = DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString();
					if("M".equals(tempGod.getBrndId())) {
						shopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString();
					} else if("I".equals(tempGod.getBrndId())) {
						shopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString();
					} else if("A".equals(tempGod.getBrndId())) {
						shopId = DeliveryEnum.DLV_ONLINE_SHOP_SA.toString();
					}
					
					lgsDlivyDrctGod.setDlvShopId(shopId);
				}				
				
				lgsDlivyDrctGod.setDlvStatCd(OrderClaimEnum.DLV_STAT_EXCHG_WRHS_WAIT.toString());

				lgsDlivyDrctGod.setDlvPcupspTurn(	newDlvPcupspTurn					);	//배송수거지순번
				lgsDlivyDrctGod.setDlvTurn(			dlvTurn								);	//배송순번
				lgsDlivyDrctGod.setDlivyDrctQty(	clmWrhsGodExtend.getOrdQty()		);	//출고지시수량

				Functions.variAbleSetN(lgsDlivyDrctGod);

				lgsDlivyDrctGod.setLgsItmYn(		clmWrhsGodExtend.getLgsItmYn()		);	//물류단품여부

        		/* 매장픽업인 경우 */
				if(StringService.equalsIgnoreCase(callTp, "DLV") || StringService.equalsIgnoreCase(callTp, "OFF_SHOP")) {
					lgsDlivyDrctGod.setDlivyDrctTpCd(	"SHOP_PKUP"						);	//출고지시유형코드
					lgsDlivyDrctGod.setDlivyDrctTgtYn(	"N"								);	//출고지시 대상 여부											
				} else {
					lgsDlivyDrctGod.setDlivyDrctTpCd(	dlivyDrctTpCd					);	//출고지시유형코드
					lgsDlivyDrctGod.setDlivyDrctTgtYn(	"N"								);	//출고지시 대상 여부
				}

				lgsDlivyDrctGod.setRegtrId(			regtrId								);
				lgsDlivyDrctGod.setUdterId(			regtrId								);


				if ("MCOM".equals(clmWrhsGodExtend.getPartmalSectCd())) {
					lgsDlivyDrctGodList.add(lgsDlivyDrctGod);
				}

				deliveryService.insertLgsDlivyDrctGodForClm(lgsDlivyDrctGod);

				/**********************************************************************************************************
				 * 교환 출고 품 재고 차감 
				 **********************************************************************************************************/
				GoodsInventoryDTO gDTO = new GoodsInventoryDTO();
				
				gDTO.setShopId(lgsDlivyDrctGod.getDlvShopId());
				gDTO.setGodNo(clmWrhsGodExtend.getGodNo());
				gDTO.setItmNo(clmWrhsGodExtend.getItmNo());
				gDTO.setSyncReqType(GoodsEnum.InvSyncReqTp.CLM.toString());
				gDTO.setSyncType(GoodsEnum.InvSyncTp.IRDS.toString());
				gDTO.setSalePrearngeQty(clmWrhsGodExtend.getOrdQty());
				
				goodsInventoryFOComponent.updateGoodsSalePrearrangementInventoryVariation(gDTO);
				
				if (claimRepository.getWMSAvailableInvQty(gDTO) < 0) {
					throw new ClaimCompleteFailException("Claim.exchange.no.inventory", null);
				}
				
				// TODO : 재고체크. 온라인재고 + 본사재고 - 판매예정수량 < 0 -> 오류
        		/* 
        		 * 물류출고지시상품이력 등록 - LGS_DLIVY_DRCT_GOD_HIST
        		 */
				lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
				BeanUtils.copyProperties(lgsDlivyDrctGod, lgsDlivyDrctGodHist);

				conditions = Maps.newHashMap();	//초기화
				conditions.put("dlivy_drct_god_no", newDlivyDrctGodNo);

				int histTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlivy_drct_god_hist", "hist_turn", conditions, DatabaseType.ORACLE);
				lgsDlivyDrctGodHist.setHistTurn(histTurn);

				deliveryService.insertLgsDlivyDrctGodHistForClm(lgsDlivyDrctGodHist);

				/*
				 * 세트대표상품 인서트를 위한 교환상품순번 저장
				 * */
				extOrdGodTurnList.add(clmWrhsGodExtend.getOrdGodTurn());
			}//교환접수시 교환해야 할 상품정보	
			
			/*
			 * 세트대표상품 인서트 처리
			 * */
			Map<String, Object> extOrdGod = new HashMap<String, Object>();
		    
		    extOrdGod.put("extOrdGodTurnList", extOrdGodTurnList);
		    extOrdGod.put("ordNo", lgsDlvspExtend.getClmWrhsGodList().get(0).getOrdNo());
		    extOrdGod.put("clmNo", clmNo);
		    
		    /*
			 * 교환상품이 세트상품인지 체크
			 * */
		    List<OrdGod> ordGodExtendList= claimService.selectOrdCpstGodCnncList(extOrdGod);
		    
		    // 세트상품이 아닐경우 다음 상품으로..
		    if(!ordGodExtendList.isEmpty()) {
		    	continue;
		    }
		    
		    conditions = Maps.newHashMap();
			conditions.put("ord_no", ordNo);
			
			OrdGodExtend ordGod2                = null;
			OrdCpstGodCnnc ordCpstGodCnnc2      = null;
			Map<String, Object> extDlivyDrctGod = null;
			
	    	for(OrdGod og : ordGodExtendList){
    			/* 주문상품순번채번 */
    			newOrdGodTurn = getIdGenService().generateDBOrder(sqlSession1, "ord_god", "ord_god_turn", conditions, DatabaseType.ORACLE);

    			ordGod2 = new OrdGodExtend();
        	    ordGod2.setOrdNo(og.getOrdNo());               //주문번호
			    ordGod2.setGodNo(og.getGodNo());               //온라인품번
			    ordGod2.setItmNo(og.getItmNo());               //아이템품번
			    ordGod2.setOrdGodTurn(og.getOrdGodTurn());     //교환전상품순번
			    ordGod2.setNewDlvPcupspTurn(newDlvPcupspTurn); //교환배송순번
			    ordGod2.setNewOrdGodTurn(newOrdGodTurn);       //교환상품순번
			    ordGod2.setOrdQty(og.getOrdQty());             //교환수량
			    ordGod2.setRegtrId(regtrId);				   //등록자 ID

			    orderBoService.insertSetExchgGodForClm(ordGod2);
			    
			    ordCpstGodCnnc2 = new OrdCpstGodCnnc();
			    ordCpstGodCnnc2.setOrdNo(og.getOrdNo());	        //주문번호            		    		
			    ordCpstGodCnnc2.setOrdGodTurn(og.getOrdGodTurn());  //교환전상품순번        		
			    ordCpstGodCnnc2.setRegtrId(regtrId);                //등록자 ID             		
			    ordCpstGodCnnc2.setUdterId(regtrId);                //수정자 ID
				
				orderBoService.insertSetExchgCpstGodCnnc(ordCpstGodCnnc2);
				
				extDlivyDrctGod = new HashMap<String, Object>();
			    extDlivyDrctGod.put("ordNo", og.getOrdNo());
				extDlivyDrctGod.put("clmNo", clmNo);
				extDlivyDrctGod.put("pckageGodTurn", new java.lang.Integer(newOrdGodTurn));
				
				claimService.updateSetPckageGodTurn(extDlivyDrctGod);
	    	}
            
		}

		/**
		 * 주문 - 교환접수시 발생하는 결제정보를 저장.
		 * @param ordNo
		 * @return
		 */		
		if(payCrncyPayAmt.compareTo(zero) == 1) {
			insertAddPayWait(regtrId, ordNo, clmNo, payCrncyPayAmt);

		}
		
		/* 
         * 교환주문상품생성 end
         * ************************************************************************************************************
         */
	}

	/*
     * 메모저장
     * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimCommandComponent#updateCsoCnsltMemo(com.plgrim.ncp.biz.claim.data.ClaimReturnDTO)
     */
	public void updateCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception{
		claimService.updateCsoCnsltMemo(csoCnsltMemo); //클레임 테이블 insert
	}

	/**
	 * 환불을 위한 DTO 생성
	 * 
	 * @param ord
	 * @param clm
	 * @param oriPay
	 * @param isGoodsAmountRefund
	 * @return
	 */
	private RefundPayDTO makeDTOForRefundPay(Ord ord, Clm clm, PayExtend oriPay, boolean isGoodsAmountRefund) {
		
		RefundPayDTO refundPayDTO = new RefundPayDTO();
		
		String payMnCd = oriPay.getPayMnCd();
		
		refundPayDTO.setOrdNo(ord.getOrdNo());
		refundPayDTO.setMbrNo(ord.getMbrNo());
		refundPayDTO.setLang(ord.getLangCd());
		
		refundPayDTO.setPayNo(oriPay.getPayNo());
		refundPayDTO.setDealTpCd(oriPay.getDealTpCd());
		refundPayDTO.setPayMnCd(payMnCd);
		refundPayDTO.setPgAprvNo(oriPay.getPgAprvNo());
		refundPayDTO.setPgSectCd(oriPay.getPgSectCd());
		refundPayDTO.setEscrYn(oriPay.getEscrYn());
		refundPayDTO.setPayCrncyCd(oriPay.getPayCrncyCd());
		refundPayDTO.setMpayMnYn(oriPay.getMpayMnYn());
		refundPayDTO.setMbrCpnNo(oriPay.getMbrCpnNo());
		
		refundPayDTO.setRfdBnkCd(oriPay.getRfdBnkCd());	
		refundPayDTO.setRfdBnkAcctNo(oriPay.getRfdBnkAcctNo());
		refundPayDTO.setRfdAcnthldrNm(oriPay.getRfdAcnthldrNm());
		
		refundPayDTO.setOriStdrCrncySumAmt(oriPay.getPayCrncyPayAmt()); // 원 결제금액 세팅. 전체환불여부 확인용
		refundPayDTO.setOriCnclAcmtlAmt(oriPay.getCnclAcmtlAmt());
		
		refundPayDTO.setClmNo(clm.getClmNo());
		refundPayDTO.setRegtrId(clm.getRegtrId());
		
		refundPayDTO.setExchgRtAplBegDt(oriPay.getExchgRtAplBegDt());
		
		if ("Y".equals(oriPay.getMpayMnYn())) {
			if (isGoodsAmountRefund) {
				refundPayDTO.setCnclAcmtlAmt(clm.getPayExchgRtCrncySumAmt().intValue()+"");
				refundPayDTO.setStdrCrncyPayAmt(clm.getStdrCrncySumAmt().intValue()+"");
				refundPayDTO.setPayCrncyPayAmt(clm.getPayExchgRtCrncySumAmt().intValue()+"");
			} else {	// 배송비에 대한 환불로 전체금액을 환불한다.
				refundPayDTO.setCnclAcmtlAmt(oriPay.getPayCrncyPayAmt().intValue()+"");
				refundPayDTO.setStdrCrncyPayAmt(oriPay.getPayCrncyPayAmt().intValue()+"");
				refundPayDTO.setPayCrncyPayAmt(oriPay.getPayCrncyPayAmt().intValue()+"");
			}
		} else {
			int refundAmt = 0;
			if("MBSH_PNT_PAY".equals(payMnCd)){//마일리지
				refundAmt = clm.getUnityPntUseSumAmt().intValue();
			}else if("GOD_CPN".equals(payMnCd)){//상품쿠폰
				refundAmt = clm.getGodCpnDcSumAmt().intValue();
			}else if("BSK_CPN".equals(payMnCd)){//장바구니쿠폰
				refundAmt = clm.getBskCpnDcSumAmt().intValue();
			}else if("DLV_CST_CPN".equals(payMnCd)){//배송비쿠폰
				refundAmt = clm.getDlvCstCpnDcSumAmt().intValue();
			}else if("WEB_PNT_PAY".equals(payMnCd)){//웹포인트
				refundAmt = clm.getWebpntUseSumAmt().intValue();
			}

			//원주문 취소 누적금액 누적
			refundPayDTO.setCnclAcmtlAmt(String.valueOf(refundAmt));
			refundPayDTO.setStdrCrncyPayAmt(String.valueOf(refundAmt));
			refundPayDTO.setPayCrncyPayAmt(String.valueOf(refundAmt));
			
		}
		
		return refundPayDTO;
	}

	/**
	 * 반품이나 교환 철회시 배송비 추가결제가 존재할 경우 환불해주는 모듈
	 * 
	 * @param clmNo
	 * @param regtrId
	 * @return
	 * @throws Exception
	 */
	private void repayWthdrawDlvFee(String clmNo, String regtrId) throws Exception {

		Clm clm = new Clm();
		clm.setClmNo(clmNo);
		clm = claimBatchService.selectClmByClmNo(clm);
		
		Ord ord = orderSelectService.selectOrdEntity(clm.getOrdNo());

		Pay clmSearchPay = new Pay();
		clmSearchPay.setClmNo(clmNo);

		PayExtend pay = new PayExtend();
    	pay.setClmNo(clmNo);
    	
		// 결제 리스트 조회
		List<PayExtend> addPayList = payBoService.selectOrdClmRefundPayExtend(pay);
		
		if(addPayList == null || addPayList.size() != 1){
			return;
		}

		for (PayExtend addPay : addPayList) {
			
			addPay.setMpayMnYn("Y"); // 추가결제지만 PG 환불처리를 위해 Y로 세팅
			RefundPayDTO refundPayDTO = this.makeDTOForRefundPay(ord, clm, addPay, false);
			
			if (addPay.getPayCrncyPayAmt().compareTo(BigDecimal.ZERO) > 0) {
				refundPayComponent.refundPayTransaction(refundPayDTO, clm, false);
			}
		}
	}


	/**
	 * [클레임관리 - 입고완료/사은품가입고]
	 */
	public HashMap<String, Object> updateRtrvlStat(ClaimBoDTO claimDTO) throws Exception{
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("RT", "F");
		String udterId = claimDTO.getUdterId();

		ClmWrhsGodExtend clmWrhsGodExtend = new ClmWrhsGodExtend();

		//클레임상세 반품정보 추출
		for (LgsRtrvlDrctGodExtend lgsRtrvlDrctGodExtend : claimDTO.getLgsRtrvlDrctGodExtendList())
		{
			clmWrhsGodExtend.setClmNo(lgsRtrvlDrctGodExtend.getClmNo());
			clmWrhsGodExtend.setClmWrhsGodTurn(lgsRtrvlDrctGodExtend.getClmWrhsGodTurn());
			int count = claimService.selectClmRtrvDrctWthdraw(clmWrhsGodExtend);

			if(count >0){
				map.put("MSG", "입고 불가한 상품이 존재 하여  입고처리를 할수 없습니다. ");
				return map;
			}


			lgsRtrvlDrctGodExtend.setUdterId(udterId);
			claimService.updateRtrvlStat(lgsRtrvlDrctGodExtend); //클레임 테이블 insert
		}
		map.put("RT", "S");
		return map;
	}

	/**
	 * [클레임관리 - 반품/교환상세 반품/교환접수]
	 */
	public HashMap<String, Object> updateClmRcept(ClaimBoDTO claimBoDTO) throws Exception{
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("RT", "F");
		String udterId = claimBoDTO.getUdterId();
		String clmGubunMsg = "교환/반품";

		Clm clm = new Clm();
		clm.setClmNo(claimBoDTO.getClmNo());
		clm.setClmStatCd("RCEPT");
		clm.setUdterId(udterId);
		claimService.updateClmRcept(clm); 		//클레임 테이블 update

		map.put("RT", "S");

		clm.setOrdNo(claimBoDTO.getOrdNo());
		clm.setMallId(claimBoDTO.getMallId());

		try {
			/**
			 * 클레임 접수시 배송담당자에게 문자발송
			 * 
			 */
			HttpServletRequest request = ContextService.getCurrentRequest();
			SystemPK systemPK = getIdGenService().generateSystemPK("", "", "KOR");

			if("GNRL_EXCHG".equals(claimBoDTO.getClmTpCd())){ //교환
				clmGubunMsg = "교환";
			}else{
				clmGubunMsg = "반품";
			}
    	}
	    catch (Throwable e) {
	    	log.error(">>[sms error]",e);
        }

		return map;
	}
	
	/**
	 * [클레임관리 - 반품/교환상세 반품/교환접수]
	 * SystemPK 추가 2016-12-06
	 */
	public HashMap<String, Object> updateClmRcept(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception{
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("RT", "F");
		String udterId = claimBoDTO.getUdterId();
		String clmGubunMsg = "교환/반품";

		Clm clm = new Clm();
		clm.setClmNo(claimBoDTO.getClmNo());
		clm.setClmStatCd("RCEPT");
		clm.setUdterId(udterId);
		claimService.updateClmRcept(clm); 		//클레임 테이블 update
		
		/**
		 * [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청
		 * 선진행 구분코드 update
		 */
		if (claimBoDTO.getClmPreprogrsSectCd() != null && !claimBoDTO.getClmPreprogrsSectCd().equals("")
				&& claimBoDTO.getClmPreprogrsSectCd().equals("PREPROGRS")) {
			claimService.updateClmPreprogrsSectCd(claimBoDTO);
		}
		
		map.put("RT", "S");
		clm.setOrdNo(claimBoDTO.getOrdNo());
		clm.setMallId(claimBoDTO.getMallId());

		try {
			/**
			 * 클레임 접수시 배송담당자에게 문자발송
			 * 
			 */
			if("GNRL_EXCHG".equals(claimBoDTO.getClmTpCd())){ //교환
				clmGubunMsg = "교환";
			}else{
				clmGubunMsg = "반품";
			}
    	}
	    catch (Throwable e) {
	    	log.error(">>[sms error]",e);
        }
		return map;
	}

	/**
	 * [클레임관리 - 반품/교환상세 부분철회]
	 */
	@Override
	public HashMap<String, Object> updateClaimWthdraw(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception {

		HashMap<String, Object> map = Maps.newHashMap();
		map.put("RT", "F");
//		String udterId = claimBoDTO.getUdterId();

		String ordNo   = claimBoDTO.getOrdNo();
		String clmNo   = claimBoDTO.getClmNo();
		String udterId = claimBoDTO.getUdterId();
		String clmTpCd = claimBoDTO.getClmTpCd();
	
		// 수선부분 수량 철회로 인하여 조건 수정
		boolean isAllWthraw = false;
		if (claimBoDTO.getClmWrhsGodList().size() == claimService.selectClmWrhsGodCount(clmNo)) {
			isAllWthraw = true;
		}

		for (int i = 0; i < claimBoDTO.getClmWrhsGodList().size(); i++) {
			ClmWrhsGodExtend clmWrhsGodExtend = claimBoDTO.getClmWrhsGodList().get(i);
			clmWrhsGodExtend.setUdterId(udterId);
			clmWrhsGodExtend.setClmNo(clmNo);

			
			// 수거지시 상태가 철회로 오면
			// wms에 입고지시 철회를 호출하고 수고지시 철회 상태로 변경 이후에 프로세스 진행
			int clmRtrvDrctCnt = claimService.selectClmRtrvDrct(clmWrhsGodExtend);
			// 회수지시건수가 있고 부분 철회면 진행안함
			
			if(clmRtrvDrctCnt>0){
				
				
				if(clmRtrvDrctCnt!=claimBoDTO.getClmWrhsGodList().size()){
					map.put("MSG", "회수지시중에 부분 철회를 할수 없습니다. ");
					return map;
				}
				
				if(claimService.selectClmRtrvDrctWthdrawSendErp(clmWrhsGodExtend)>0){
					map.put("MSG", "클레임 철회는 회수송장이 발급되야 가능합니다. 회수송장이 발급되면 다시 철회 신청해주세요 ");
					return map;
				}
				
				AdapterHeader adapterHeader = new AdapterHeader();
				adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
				adapterHeader.setMallId("DX");
				adapterHeader.setLangCd("KOR");
				adapterHeader.setDvcCd("PC");
				
				DeliveryClaimDTO dcDTO = new DeliveryClaimDTO();
				dcDTO.setClmNo(clmNo);
				String resultCode = deliveryAdapter.sendWMSRetrievalWithdraw(adapterHeader,dcDTO.toJSON());
				DlivyWmsRetrievalSDO dwrSDO = JsonService.unmarshalling(resultCode, DlivyWmsRetrievalSDO.class);

				if (dwrSDO==null||!"200".equals(dwrSDO.getResponseCd())) {
					map.put("MSG", "회수지시 철회 실패 ");
					return map;
				}
			}
			
			int count;
			count = claimService.selectClmRtrvDrctWthdraw(clmWrhsGodExtend);

			if (count > 0) {
				map.put("MSG", "상품중에 철회가 불가한 상품이 존재 하여  철회를 할수 없습니다. ");
				return map;
			}

			count = claimService.selectClmDrctWthdraw(clmWrhsGodExtend);
			if (count > 0) {
				map.put("MSG", "교환상품 배송상태가 출고지시 이전까지만 철회를 할수 있습니다. ");
				return map;
			}
			
			count = claimRepository.getEscrCalResrveCnt(ordNo);
			if (count > 0) {
				map.put("MSG", "반품으로 인한 에스크로 정산보류 상태로 클레임 철회가 불가합니다.");
				return map;
			}

			if (isAllWthraw) {
				clmWrhsGodExtend.setClmWthdrawSectCd("ALL_WTHDRAW");
			} else {
				clmWrhsGodExtend.setClmWthdrawSectCd("PART_WTHDRAW");
			}

			claimService.updateClmWrhsGodWthdraw(clmWrhsGodExtend);    //클레임입고상품 update
			claimService.updateRtrvlStatWthdraw(clmWrhsGodExtend);    //물류회수지시상품 update


			// 회수지시 상품 HIST 등록
			LgsRtrvlDrctGod lgsRtrvlDrctGod = new LgsRtrvlDrctGod();
			lgsRtrvlDrctGod.setClmNo(clmNo);
			lgsRtrvlDrctGod.setClmWrhsGodTurn(clmWrhsGodExtend.getClmWrhsGodTurn());
			lgsRtrvlDrctGod.setUdterId(udterId);
			claimService.insertRepairLgsRdgHist(lgsRtrvlDrctGod);

		}


		if ("GNRL_EXCHG".equals(clmTpCd)) {

			List<DlvOrdGodInfoDTO> infoList = new ArrayList<DlvOrdGodInfoDTO>();
			DlvOrdGodInfoDTO dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();

			for (ClmWrhsGodExtend clmWrhsGodExtend : claimBoDTO.getClmWrhsGodList()) {
				dlvOrdGodInfoDTO.setDlvPcupspTurn(clmWrhsGodExtend.getDlvPcupspTurnDlivy() + "");
				dlvOrdGodInfoDTO.setDlivyDrctGodNo(clmWrhsGodExtend.getDlivyDrctGodNo());
				dlvOrdGodInfoDTO.setOrdGodTurn(clmWrhsGodExtend.getOrdGodTurn() + "");
				dlvOrdGodInfoDTO.setQty(clmWrhsGodExtend.getDlivyQty() + "");
				dlvOrdGodInfoDTO.setOrdNo(ordNo);
//	            dlvOrdGodInfoDTO.setDlvTurn(clmWrhsGodExtend.getDlvTurn()+"");
				//ncp2 - 교환철회 시 상세쿼리에서 배송순번을 가져오고 있으나 부분철회를 할 경우 문제가 발생하고 있음
				//ex) 상품순번이 1, 2 인 경우 1번에 대한 철회만 제대로 이루어 지고 있음.
				//철회시 해당 출고지시 상품 번호의 배송순번을 조회하여 세팅하도록 수정. Test 필요.
				dlvOrdGodInfoDTO.setClmNo(clmNo);
				dlvOrdGodInfoDTO.setClmWrhsGodTurn(clmWrhsGodExtend.getClmWrhsGodTurn());
				dlvOrdGodInfoDTO.setDlvTurn(deliveryService.selectDlvTurn(dlvOrdGodInfoDTO) + "");

				dlvOrdGodInfoDTO.setAdminId(udterId);
				infoList.add(dlvOrdGodInfoDTO);

				dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();
			}

			
			List<GodShopItmInvExtend> godShopItmInvExtendList = new ArrayList<GodShopItmInvExtend>();

			// 교환 건에 대해서만 재고 복원 진행
			// 수선의 경우 기존 상품의 수선을 통해 재 출고 됨으로 재고 차감(복원)이 진행되지 않음
			if ("GNRL_EXCHG".equals(clmTpCd)) {
				for (DlvOrdGodInfoDTO dto : infoList) {
					godShopItmInvExtendList.addAll(deliveryService.selectOrdGodShopItmQty(dto));
				}


				for (GodShopItmInvExtend godShopItmInvExtendHist : godShopItmInvExtendList) {
					GoodsInventoryDTO gDTO = new GoodsInventoryDTO();
					gDTO.setShopId(godShopItmInvExtendHist.getShopId());
					gDTO.setGodNo(godShopItmInvExtendHist.getGodNo());
					gDTO.setItmNo(godShopItmInvExtendHist.getItmNo());
					gDTO.setSyncReqType(GoodsEnum.InvSyncReqTp.CLM.toString());
					gDTO.setSyncType(GoodsEnum.InvSyncTp.DDCT.toString());
					
					gDTO.setSalePrearngeQty((long)godShopItmInvExtendHist.getSalePrearngeQtyInt());
					goodsInventoryFOComponent.updateGoodsSalePrearrangementInventoryVariation(gDTO);
				}
			}

			/**
			 * 18.포인트 재적립 및 차감 부분취소 고려하여 수정필요
			 (erp 분배 테이블 참조해서 사용한 포인트 수량단위로 재발급/구매시 적립된 포인트를 n/1로 나눠서 포인트 차감?? 루터가 확인중)
			 (확인결과 주문시 적립해주는 포인트는 구매자가 구매 확정 후 7일 뒤에 적립되므로
			 주문 전체취소나 부분취소에서는 포인트 차감할 케이스가 없음.)
			 (erp 분배 테이블 참조해서 사용한 포인트 수량단위로 재발급 해줘야함 회원팀)
			 (erp 전송 필요)
			 */
			/************************포인트 재적립 및 삭감 end***********************************************/


			DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
			dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();

			deliverySearchDTO.setClmDlvOrdGodInfoList(infoList);
			deliverySearchDTO.setClmNo(clmNo);
			deliverySearchDTO.setClmStatCd("WTHDRAW");

			
			deliveryService.updateClmDlvDrctGodUnitCancel(deliverySearchDTO);   // 출고지시테이블 취소수량 등록 / 출고지시 취소로 상태변경

			/*
			 * 1. 수정일자   : 2016-02-18
			 * 2. 수정자     : 김재성 (jskim27)
			 * 3. 요청 SR NO : #14052
			 * 4. 수정내용   : <긴급>[고객센터] 탈퇴가능하도록 배송상태 변경요청
			 *		- 복수구매 주문의 경우, 일부 상품이 정상적으로 '배송완료' 처리된 이후에
			 *			이외의 상품이'출고지시취소' 되는 경우, 해당 주문은 '배송중'으로 남을 수 밖에 없음
			 *		- 이러한 '출고지시취소' 처리 시 해당 주문에 대해서 검토 후 '배송완료' 처리
			 */
			if("BT".equals(claimBoDTO.getRole())){
				this.deliveryStatusService.modifyRepairOrdDlvCompt(ordNo);
			} else {
				this.deliveryStatusService.modifyOrdDlvCompt(ordNo);
			}

			if ( log.isDebugEnabled() ) {
				log.debug("ClaimCommandComponentImpl.updateClaimWthdraw->modifyOrdDlvCompt [{}]", ordNo );
			}
		}

		ClmWrhsGodExtend clmWrhsGodExtend = new ClmWrhsGodExtend();
		clmWrhsGodExtend.setClmNo(claimBoDTO.getClmNo());

		int count = claimService.selectClmWrhsGodWthdraw(clmWrhsGodExtend);

		if (count == 0) {

			//세트상품 껍데기 수량 update 추가
			clmWrhsGodExtend.setUdterId(udterId);
			claimService.updateClmWrhsGodWthdraw(clmWrhsGodExtend);

			/* 클레임철회상태가 정상인 건이 없으면 */
			Clm clm = new Clm();
			clm.setClmNo(claimBoDTO.getClmNo());
			clm.setClmStatCd("WTHDRAW");
			claimService.updateClmStatCd(clm);
			
			/* 
			 * 철회된 상품에 대한 클레임마스터 update
             */
			claimService.updateClmForAllWthdraw(clm);
		} else {

			/* 반품/교환 상품이 남아 있는 경우
			 * 철회된 상품에 대한 클레임마스터 update
			 */
			ClaimReceiptDTO claimReceiptDTO = new ClaimReceiptDTO();
			claimReceiptDTO.setClmNo(claimBoDTO.getClmNo());
			claimService.updateClmToPrcByClmWrhsGod(claimReceiptDTO);
		}

		// 부분철회 수선 상품의 경우 INF_ORD_GOD_ERP_DSTB 의 상품 정보를 참조하여야 하므로 로직 이동
		for (ClmWrhsGodExtend clmWrhsGod : claimBoDTO.getClmWrhsGodList()) {
			clmWrhsGod.setUdterId(udterId);
			clmWrhsGod.setClmNo(clmNo);
			claimService.updateClmErpWthdraw(clmWrhsGod);        //ERP분배 update
		}
		
		// 반품이나 교환 철회시 배송비 추가결제가 존재할 경우 환불해주는 모듈
		// 온라인 프로세스 완료 후 환불 로직 실행
		if (count == 0) {
			repayWthdrawDlvFee(clmNo, udterId);		
		}

		map.put("RT", "S");
		return map;

	}

	/* 
	 * 환불계좌저장
	 */
	@Override
	public HashMap<String, Object> updateRfdBnk(ClaimBoDTO claimBoDTO) throws Exception {
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("RT", "F");

		String udterId = claimBoDTO.getUdterId();

		ClmExtend clmExtend = new ClmExtend();
		clmExtend.setRfdBnkAcctNo(claimBoDTO.getRfdBnkAcctNo());
		clmExtend.setRfdBnkCd(claimBoDTO.getRfdBnkCd());
		clmExtend.setRfdAcnthldrNm(claimBoDTO.getRfdAcnthldrNm());
		clmExtend.setUdterId(udterId);
		clmExtend.setClmNo(claimBoDTO.getClmNo());

		claimService.updateRfdBnk(clmExtend);

		map.put("RT", "S");
		return map;

	}

	/**
	 * PG 미연동 클레임 완료처리 버튼
	 * @throws Exception
	 */
	@Override
    public void updateClmStatCmpl(String clmNo) throws Exception {
    	Clm clm = new Clm();
    	clm.setClmNo(clmNo);
    	clm.setClmStatCd("COMPT");
    	claimService.updateClmStatCd(clm);
    	    	
	    //한 주문에 모든 상품이 취소되었다면 주문의 상태를 전체취소로 변경해준다.
    	clm = new Clm();
    	clm.setClmNo(clmNo);
    	clm = claimBatchService.selectClmByClmNo(clm);
	    InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO = new InfOrdGodErpDstbClmSearchDTO();
	    infOrdGodErpDstbClmSearchDTO.setOrdNo(clm.getOrdNo());
	    infOrdGodErpDstbClmSearchDTO.setClmNoNullYn("Y");
	    List<InfOrdGodErpDstb> infOrdGodErpDstbCheckList  = infOrdGodErpDstbService.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
	    if(infOrdGodErpDstbCheckList == null || infOrdGodErpDstbCheckList.size()==0){//ordGodErp에 클레임번호가 비어있는게 없다면 모두 취소된거임
		    Ord statUpdateOrd = new Ord();
		    statUpdateOrd.setOrdNo(clm.getOrdNo());
		    statUpdateOrd.setOrdStatCd("ALL_CNCL");//주문테이블 주문상태 : 전체취소
		    orderBoService.updateOrdStatCd(statUpdateOrd);	    	
	    }
    }

    /**
     * [클레임관리 - 교환을 반품으로 전환]
     */
    @Override
    public HashMap<String, Object> switchReturn(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception {

        HashMap<String, Object> map = Maps.newHashMap();
        map.put("RT", "F");
        String ordNo   = claimBoDTO.getOrdNo();
        String clmNo   = claimBoDTO.getClmNo();
        String udterId = claimBoDTO.getUdterId();
        
        for (ClmWrhsGodExtend clmWrhsGodExtend : claimBoDTO.getClmWrhsGodList()) {

            clmWrhsGodExtend.setClmNo(claimBoDTO.getClmNo());

            int count = claimService.selectClmRtrvDrctWthdrawSwitch(clmWrhsGodExtend);

            if(count >0){
                map.put("MSG", "회수 완료된 상품만 반품으로 전환 할수 있습니다.");
                return map;
            }
            count = claimService.selectSwitchClmDrctWthdraw(clmWrhsGodExtend);

            if(count >0){
                map.put("MSG", "교환상품 배송상태가 출고지시 이전까지만 반품으로 전환 할수 있습니다.");
                return map;
            }
            
        }

       
       String rtClmNo =  returnInsert(claimBoDTO, systemPK);
       map.put("clmNo", rtClmNo);
       
       for (ClmWrhsGodExtend clmWrhsGodExtend : claimBoDTO.getClmWrhsGodList()) {

    	   clmWrhsGodExtend.setUdterId(udterId);
    	   clmWrhsGodExtend.setClmNo(claimBoDTO.getClmNo());

    	   claimService.updateClmWrhsGodWthdraw(clmWrhsGodExtend); //클레임입고상품 update
    	   claimService.updateRtrvlStatWthdraw(clmWrhsGodExtend);  //물류회수지시상품 update  
               
    	   int cntErpOR=0;
    	   cntErpOR=claimService.selectOrdErpErpCnt(clmWrhsGodExtend);  //erp고객번호추출
             
    	   if(cntErpOR > 0) { //교환출고가 ERP전송됬으면
    		   clmWrhsGodExtend.setWthDrawClmNo(rtClmNo);  
    		   claimService.updateRtrvlDstbWthdraw(clmWrhsGodExtend);  //철회할출고지시테이블 클레임업데이트  
    	   }
		
       }

       List<DlvOrdGodInfoDTO> infoList =  new ArrayList<DlvOrdGodInfoDTO>();
       DlvOrdGodInfoDTO dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();
       List<String> ordGodTurnList = new ArrayList<String>();

       for (ClmWrhsGodExtend clmWrhsGodExtend : claimBoDTO.getClmWrhsGodList()){

    	   dlvOrdGodInfoDTO.setDlvPcupspTurn(clmWrhsGodExtend.getDlvPcupspTurnDlivy()+"");
    	   dlvOrdGodInfoDTO.setDlivyDrctGodNo(clmWrhsGodExtend.getDlivyDrctGodNo());
    	   dlvOrdGodInfoDTO.setOrdGodTurn(clmWrhsGodExtend.getOrdGodTurn()+"");
    	   dlvOrdGodInfoDTO.setQty(clmWrhsGodExtend.getDlivyQty()+"");
    	   dlvOrdGodInfoDTO.setOrdNo(ordNo);
    	   //교환철회 시 상세쿼리에서 배송순번을 가져오고 있으나 부분철회를 할 경우 문제가 발생하고 있음
    	   //ex) 상품순번이 1, 2 인 경우 1번에 대한 철회만 제대로 이루어 지고 있음.
    	   //철회시 해당 출고지시 상품 번호의 배송순번을 조회하여 세팅하도록 수정. Test 필요.
    	   dlvOrdGodInfoDTO.setClmNo(clmNo);
    	   dlvOrdGodInfoDTO.setClmWrhsGodTurn(clmWrhsGodExtend.getClmWrhsGodTurn());
    	   dlvOrdGodInfoDTO.setDlvTurn(deliveryService.selectDlvTurn(dlvOrdGodInfoDTO)+"");

    	   dlvOrdGodInfoDTO.setAdminId(udterId);
    	   infoList.add(dlvOrdGodInfoDTO);

    	   dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();
    	   ordGodTurnList.add(clmWrhsGodExtend.getOrdGodTurn()+"");
       }

       // 교환 출고 예정 상품 수량 변경
       List<GodShopItmInvExtend> godShopItmInvExtendList = new ArrayList<GodShopItmInvExtend>();
       for(DlvOrdGodInfoDTO dto : infoList){
    	   godShopItmInvExtendList.addAll(deliveryService.selectOrdGodShopItmQty(dto));
       }
       
       // 교환 출고품 재고 차감
       for (GodShopItmInvExtend godShopItmInvExtendHist : godShopItmInvExtendList) {
			GoodsInventoryDTO gDTO = new GoodsInventoryDTO();
			gDTO.setShopId(godShopItmInvExtendHist.getShopId());
			gDTO.setGodNo(godShopItmInvExtendHist.getGodNo());
			gDTO.setItmNo(godShopItmInvExtendHist.getItmNo());
			gDTO.setSyncReqType(GoodsEnum.InvSyncReqTp.CLM.toString());
			gDTO.setSyncType(GoodsEnum.InvSyncTp.DDCT.toString());
			
			gDTO.setSalePrearngeQty((long)godShopItmInvExtendHist.getSalePrearngeQtyInt());
			goodsInventoryFOComponent.updateGoodsSalePrearrangementInventoryVariation(gDTO);
		}

       DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
       dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();

       deliverySearchDTO.setClmDlvOrdGodInfoList(infoList);
       deliverySearchDTO.setClmNo(clmNo);
       deliverySearchDTO.setClmStatCd("WTHDRAW");

       deliveryService.updateClmDlvDrctGodUnitCancel(deliverySearchDTO);   // 출고지시테이블 취소수량 등록 / 출고지시 취소로 상태변경

       /*
        * [고객센터] 탈퇴가능하도록 배송상태 변경요청
        *		- 복수구매 주문의 경우, 일부 상품이 정상적으로 '배송완료' 처리된 이후에
        *			이외의 상품이'출고지시취소' 되는 경우, 해당 주문은 '배송중'으로 남을 수 밖에 없음
        *		- 이러한 '출고지시취소' 처리 시 해당 주문에 대해서 검토 후 '배송완료' 처리
        */
       this.deliveryStatusService.modifyOrdDlvCompt( ordNo );
       if ( log.isDebugEnabled() ) {
    	   log.debug("ClaimCommandComponentImpl.switchReturn->modifyOrdDlvCompt [{}]", ordNo);
       }

       for (ClmWrhsGodExtend clmWrhsGodExtend : claimBoDTO.getClmWrhsGodList()) {

    	   clmWrhsGodExtend.setUdterId(udterId);
    	   clmWrhsGodExtend.setClmNo(clmNo);
    	   claimService.updateClmErpWthdraw(clmWrhsGodExtend);     //ERP분배 update
       }
       ClmWrhsGodExtend clmWrhsGodExtend = new ClmWrhsGodExtend();
       clmWrhsGodExtend.setClmNo(claimBoDTO.getClmNo());
       int count = claimService.selectClmWrhsGodWthdraw(clmWrhsGodExtend);

       if(count ==0){
    	   /* 클레임철회상태가 정상인 건이 없으면 */
    	   Clm clm = new Clm();
    	   clm.setClmNo(claimBoDTO.getClmNo());
    	   clm.setClmStatCd("WTHDRAW");
    	   claimService.updateClmStatCd(clm);

    	   claimService.updateClmForAllWthdraw(clm);

    	   updateRfdBnk(claimBoDTO);
    	   repayWthdrawDlvFee(clmNo, udterId);

       } else {
    	   /* 반품/교환 상품이 남아 있는 경우
    	    * 철회된 상품에 대한 클레임마스터 update 
    	    */
    	   ClaimReceiptDTO claimReceiptDTO = new ClaimReceiptDTO();
    	   claimReceiptDTO.setClmNo(claimBoDTO.getClmNo());
    	   claimService.updateClmToPrcByClmWrhsGod(claimReceiptDTO);
       }
		
       map.put("RT", "S");
       return map;

    }

	/*
     * 반품 신청/접수 등록
     */
	public String returnInsert(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception {


		String clmNo = claimService.returnInsert(claimBoDTO);

		Clm clm = new Clm();
		clm.setClmNo(clmNo);
		clm = claimBatchService.selectClmByClmNo(clm);

		return clmNo;
	}

	/*
     * 클레임 god_sumry 
     */
	public void clmSumryUpdate(String clmNo) throws Exception {

		claimService.updateClmSumry(clmNo);

	}


	/**
	 * 취소 클레임 초도 배송비 결제
	 */
	@Override
	public void updateCancelClmDlvAmtPayMethod(OrderDTO orderDTO, HttpServletRequest request) throws Exception {
		MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();

		// BO에서 결제한 정보를 가져온다
		mypageOrderInfoDTO.setOrdNo(orderDTO.getOrd().getOrdNo());
		mypageOrderInfoDTO.setMbrNo(orderDTO.getMbr().getMbrNo());
		mypageOrderInfoDTO.setType("clmDlvAmtPay");
		mypageOrderInfoDTO.setClmNo(orderDTO.getClmNo());
		mypageOrderInfoDTO = orderSelectService.selectPayMethodChange(mypageOrderInfoDTO);

		// 기본 업데이트 정보 세팅
		long time = System.currentTimeMillis();
		Date regDt = new Date(time);
		orderDTO.setRegDt(regDt);
		orderDTO.setRegtrId(orderDTO.getMbr().getMbrNo());

		// 클레임 배송비 일치 여부 확인
		BigDecimal ordTotAmt = orderDTO.getOrd().getPayExchgRtCrncySumAmt();
		BigDecimal og_ordTotAmt = new BigDecimal(mypageOrderInfoDTO.getPayCrncyPayAmt());

		String godTpCd = mypageOrderInfoDTO.getGodTpCd();
		Map<String, String> resultMap = new HashMap<String, String>();
		if (ordTotAmt.compareTo(og_ordTotAmt) != 0) {
			throw new RuntimeException("updaePayMethodChange ordTotAmt Error");
		}

//		resultMap = this.approvePG(orderDTO, request, godTpCd);
		orderCommandComponent.approvePG(orderDTO, ordTotAmt, orderDTO.getOrd().getOrdNo());
		try {
			
			// ord
			Ord ord = orderEntitySetService.makeOrd(orderDTO, resultMap);

			// 웹포인트 복원 처리
			this.updateWebPointForCancelClaim(ord.getOrdNo(), orderDTO.getClmNo());

			// pay 테이블 결제 처리
			orderCommandService.updatePayMethodChangePay(orderDTO);

			// clm 테이블 결제 완료 처리
			orderCommandService.updaeClmDlvAmtClmStatCd(mypageOrderInfoDTO);

			this.refundPayForPartCncl(request, orderDTO.getOrd().getOrdNo(), orderDTO.getClmNo());

		}
		catch (Exception e) {
//			this.cancelPG(orderDTO, request, godTpCd, resultMap);
			// TODO 추가결제 PG 취소..
			throw e;
		}
	}
	
	/**
	 * 클레임 교환 배송비 결제
	 */
	@Override
	public void updateClmDlvAmtPayMethod(OrderDTO orderDTO, HttpServletRequest request) throws Exception {
		MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();

		// BO에서 결제한 정보를 가져온다
		mypageOrderInfoDTO.setOrdNo(orderDTO.getOrd().getOrdNo());
		mypageOrderInfoDTO.setMbrNo(orderDTO.getMbr().getMbrNo());
		mypageOrderInfoDTO.setType("clmDlvAmtPay");
		mypageOrderInfoDTO.setClmNo(orderDTO.getClmNo());
		mypageOrderInfoDTO = orderSelectService.selectPayMethodChange(mypageOrderInfoDTO);

		orderDTO.setRegtrId(orderDTO.getMbr().getMbrNo());

		// 클레임 배송비 일치 여부 확인
		BigDecimal ordTotAmt = orderDTO.getOrd().getPayExchgRtCrncySumAmt();
		BigDecimal og_ordTotAmt = new BigDecimal(mypageOrderInfoDTO.getPayCrncyPayAmt());

		if (ordTotAmt.compareTo(og_ordTotAmt) != 0) {
			throw new Exception("updaePayMethodChange ordTotAmt Error");
		}
		
		orderCommandComponent.approvePG(orderDTO, orderDTO.getOrd().getPayExchgRtCrncySumAmt(), orderDTO.getOrd().getOrdNo());
		
		try {

			// pay 테이블 결제 처리
			orderCommandService.updatePayMethodChangePay(orderDTO);

			// clm 테이블 결제 완료 처리
			orderCommandService.updaeClmDlvAmtClmStatCd(mypageOrderInfoDTO);

		}
		catch (Exception error) {
			orderCommandComponent.cancelApprovePG(orderDTO);
			throw new OrderCompleteFailException("orderError");
		}
	}

	/**
	 * [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청
	 * 선진행 변경
	 */
	public HashMap<String, Object> updateClmChangeRcept(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception{
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("RT", "F");

		if (claimBoDTO.getClmPreprogrsSectCd() != null && !claimBoDTO.getClmPreprogrsSectCd().equals("")
				&& claimBoDTO.getClmPreprogrsSectCd().equals("PREPROGRS")) {
			claimService.updateClmPreprogrsSectCd(claimBoDTO);
		}
		
		map.put("RT", "S");
		return map;
	}

	/*
     * 반품 신청 삭제
     */
	public void deleteClaimProcessor(ClaimBoDTO claimDTO) throws Exception {

		String ordNo = claimDTO.getOrdNo();		//주문번호
		String clmNo = claimDTO.getClmNo();		//클레임번호
		String clmTpCd = claimDTO.getClmTpCd();	//클레임유형
    	
    	/* 인터페이스 주문상품 SAP 분배 수정 */
		claimService.updateOrdGodErpForClmWthdraw(clmNo);

		ClmWrhsGodExtend delClmWrhsGod = new ClmWrhsGodExtend();
		delClmWrhsGod.setOrdNo(ordNo);
		delClmWrhsGod.setClmNo(clmNo);

        /* 반품이 아니면 >> 교환신청인 경우 */
		if(!StringService.equalsIgnoreCase(clmTpCd, "RTGOD"))
		{

			/* 인터페이스주문상품ERP분배 삭제 */
			claimService.deleteInfOrdGodErpDstb(delClmWrhsGod);

        	/* 물류 출고지시상품 이력 삭제 */
			claimService.deleteLgsDlivyDrctGodHist(delClmWrhsGod);

        	/* 물류 출고지시상품 삭제 */
			claimService.deleteLgsDlivyDrctGod(delClmWrhsGod);

        	/* 주문상품적용프로모션 삭제 */
			claimService.deleteOrdGodAplPrm(delClmWrhsGod);

        	/* 교환 수정후 접수 관련 삭제대상상품 조회 */
			List<ClmWrhsGodExtend> delClmWrhsGodList = claimService.selectOrdGodListForClm(delClmWrhsGod);
			Integer pckageGodTurn = null;
			
			for (ClmWrhsGodExtend clmWrhsGodExtend : delClmWrhsGodList)
			{
				pckageGodTurn = clmWrhsGodExtend.getPckageGodTurn();
				/* 주문구성상품연결 삭제 */
				delClmWrhsGod.setOrdGodTurn(clmWrhsGodExtend.getOrdGodTurn());
				if(pckageGodTurn != null){
					delClmWrhsGod.setPckageGodTurn(pckageGodTurn);
				}
				claimService.deleteOrdCpstGodCnnc(delClmWrhsGod);
				
	        	/* 주문 클레임 상품연결 삭제 - 출고 상품 연결 */
				OrdClmGodCnnc delOrdClmGodCnncDlivy = new OrdClmGodCnnc();
				delOrdClmGodCnncDlivy.setOrdNo(ordNo);
				delOrdClmGodCnncDlivy.setClmNo(clmNo);
				delOrdClmGodCnncDlivy.setOrdGodTurn(clmWrhsGodExtend.getOrdGodTurn());
				claimService.deleteOrdClmGodCnncDlivy(delOrdClmGodCnncDlivy);

    	    	/* 주문상품 삭제 */
				claimService.deleteOrdGod(delClmWrhsGod);
				
				if(pckageGodTurn != null){
					delClmWrhsGod.setOrdGodTurn(pckageGodTurn);
					claimService.deleteOrdGod(delClmWrhsGod);
				}
			}
		}

    	/* 물류 회수지시상품 이력 삭제 */
		LgsRtrvlDrctGodHist  delLgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();
		delLgsRtrvlDrctGodHist.setOrdNo(ordNo);
		delLgsRtrvlDrctGodHist.setClmNo(clmNo);
		claimService.deleteLgsRtrvlDrctGodHist(delLgsRtrvlDrctGodHist);
    	
    	/* 물류 회수지시상품 삭제 */
		LgsRtrvlDrctGod delLgsRtrvlDrctGod = new LgsRtrvlDrctGod();
		delLgsRtrvlDrctGod.setOrdNo(ordNo);
		delLgsRtrvlDrctGod.setClmNo(clmNo);
		claimService.deleteLgsRtrvlDrctGod(delLgsRtrvlDrctGod);
        
    	/* 클레임 입고상품 적용 프로모션 삭제 */
		ClmWrhsGodAplPrm delClmWrhsGodAplPrm = new ClmWrhsGodAplPrm();
		delClmWrhsGodAplPrm.setOrdNo(ordNo);
		delClmWrhsGodAplPrm.setClmNo(clmNo);
		claimService.deleteClmWrhsGodAplPrm(delClmWrhsGodAplPrm);
                 
    	/* 주문 클레임 상품연결 삭제 - 입고 상품 연결 */
		OrdClmGodCnnc delOrdClmGodCnncWrhs = new OrdClmGodCnnc();
		delOrdClmGodCnncWrhs.setOrdNo(ordNo);
		delOrdClmGodCnncWrhs.setClmNo(clmNo);
		claimService.deleteOrdClmGodCnncWrhs(delOrdClmGodCnncWrhs);


        /* 클레임 입고상품 삭제 */
		claimService.deleteClmWrhsCpstGodCnnc(delClmWrhsGod);
        
        
        /* 클레임 입고상품 삭제 */
		claimService.deleteClmWrhsGod(delClmWrhsGod);

        /* 물류 배송 이력 삭제 */
		LgsDlvHist delLgsDlvHist = new LgsDlvHist();
		delLgsDlvHist.setOrdNo(ordNo);
		delLgsDlvHist.setClmNo(clmNo);
		claimService.deleteLgsDlvHist(delLgsDlvHist);
        
        /* 물류 배송 삭제 */
		LgsDlv delLgsDlv = new LgsDlv();
		delLgsDlv.setOrdNo(ordNo);
		delLgsDlv.setClmNo(clmNo);
		claimService.deleteLgsDlv(delLgsDlv);
        
        /* 물류 배송지 이력 삭제 */
		LgsDlvspHist delLgsDlvspHist = new LgsDlvspHist();
		delLgsDlvspHist.setOrdNo(ordNo);
		delLgsDlvspHist.setClmNo(clmNo);
		claimService.deleteLgsDlvspHist(delLgsDlvspHist);
        
        /* 물류 배송지 삭제 */
		System.out.println("ordNo==>"+ordNo);
		System.out.println("clmNo==>"+clmNo);
		LgsDlvsp delLgsDlvsp = new LgsDlvsp();
		delLgsDlvsp.setOrdNo(ordNo);
		delLgsDlvsp.setClmNo(clmNo);
		claimService.deleteLgsDlvsp(delLgsDlvsp);

    	/* 클레임 SAP 전송 삭제 */
		Clm delClm = new Clm();
		delClm.setOrdNo(ordNo);
		delClm.setClmNo(clmNo);
		claimService.deleteClmErpTrnsmis(delClm);

		claimService.deleteClm(delClm);

	}
	
	@Override
	@Transactional(value = "transactionManager", propagation=Propagation.REQUIRES_NEW)
	public String updateClaimPartCancel(ClaimBoDTO claimBoDTO, OrderBoDTO orderBoDTO, SystemPK systemPK)
			throws Exception {

		orderBoDTO.setClmTpCd("PART_CNCL");
		orderBoDTO.setRole("F");

		List<OrdGodForRtnClmResult> ordGodForRtnClmResult = orderSelectComponent
				.selectOrdGodPartCancelClmWithGft(systemPK, orderBoDTO);

		LgsDlvspExtend lgsDlvsp = new LgsDlvspExtend();

		List<LgsDlvspPkupDTO> dlvspList = new ArrayList<LgsDlvspPkupDTO>();
		lgsDlvsp.setOrdNo(orderBoDTO.getOrdNo());
		for (OrdGodForRtnClmResult dto : ordGodForRtnClmResult) {
			lgsDlvsp.setDlvPcupspTurn(Integer.parseInt(dto.getDlvPcupspTurn()));
			dlvspList.add(claimSelectComponent.selectLgsDlvspPkup(lgsDlvsp));
		}

		MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();
		mypageOrderInfoDTO.setOrdNo(orderBoDTO.getOrdNo());

		claimBoDTO.setOrdNo(orderBoDTO.getOrdNo());
		claimBoDTO.setClmTpCd("PART_CNCL");
		claimBoDTO.setPgIfYn("N");
		claimBoDTO.setRfdBnkCd(orderBoDTO.getRfdBnkCd());
		claimBoDTO.setRfdAcnthldrNm(orderBoDTO.getRfdAcnthldrNm());
		claimBoDTO.setRfdBnkAcctNo(orderBoDTO.getRfdBnkAcctNo());

		List<LgsDlvspExtend> lgsDlvspList = new ArrayList<>();
		for (OrdGodForRtnClmResult rs : ordGodForRtnClmResult) {
			LgsDlvspExtend lgsDlvspExtend = new LgsDlvspExtend();
			lgsDlvspExtend.setOrdNo(orderBoDTO.getOrdNo());
			lgsDlvspExtend.setDlvPcupspTurn(Integer.valueOf(rs.getDlvPcupspTurn()));
			List<ClmWrhsGodExtend> clmWrhsGodList = new ArrayList<>();
			for (OrdGodForRtnClmDetailResult ordGod : rs.getOrdGodForRtnClmDetailResultList()) {
				if (ordGod.getWrkQty() > 0) {
					ClmWrhsGodExtend clmWrhsGodExtend = new ClmWrhsGodExtend();
					// 상품 사은품
					clmWrhsGodExtend.setOrdNo(orderBoDTO.getOrdNo());
					clmWrhsGodExtend.setPartmalSectCd(ordGod.getPartmalSectCd());
					clmWrhsGodExtend.setDmstcDlvCstPlcSn(ordGod.getDmstcDlvCstPlcSn());
					clmWrhsGodExtend.setClmResnCd(claimBoDTO.getClmResnCd());
					clmWrhsGodExtend.setGodTpCd(ordGod.getGodTpCd());
					clmWrhsGodExtend.setOrdGodTurn(ordGod.getOrdGodTurn());
					clmWrhsGodExtend.setDlivyDrctGodNo(ordGod.getDlivyDrctGodNo());
					clmWrhsGodExtend.setGodNo(ordGod.getGodNo());
					clmWrhsGodExtend.setDlvTurn(Integer.valueOf(ordGod.getDlvTurn()));
					clmWrhsGodExtend.setClmQty(ordGod.getWrkQty());

					clmWrhsGodList.add(clmWrhsGodExtend);
				}

			}
			lgsDlvspExtend.setClmWrhsGodList(clmWrhsGodList);
			lgsDlvspList.add(lgsDlvspExtend);
		}

		claimBoDTO.setLgsDlvspList(lgsDlvspList);

		return this.updateClaimUnitCancel(claimBoDTO, systemPK);
	}

	private String updateClaimUnitCancel(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception {
		List<LgsDlvspExtend> lgsDlvspList = claimBoDTO.getLgsDlvspList();

		for (int i = 0; i < lgsDlvspList.size(); i++) {
			List<ClmWrhsGodExtend> clmWrhsGodList = lgsDlvspList.get(i).getClmWrhsGodList();
			List<ClmWrhsGodExtend> newClmWrhsGodList = new ArrayList<ClmWrhsGodExtend>();
			for (int j = 0; j < clmWrhsGodList.size(); j++) {
				if ("SET_GOD".equals(clmWrhsGodList.get(j).getGodTpCd())
						|| "PCKAGE_GOD".equals(clmWrhsGodList.get(j).getGodTpCd())) {
					/* 세트 || 패키지 마스터 상품 추가 */
					newClmWrhsGodList.add(clmWrhsGodList.get(j));
				} else {
					if (clmWrhsGodList.get(j).getDlivyDrctGodNo() != null
							&& !"".equals(clmWrhsGodList.get(j).getDlivyDrctGodNo())) {
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

		for (LgsDlvspExtend dlvsp : claimBoDTO.getLgsDlvspList()) {
			for (ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()) {
				clmDlvOrdGodInfoDTO = new ClmDlvOrdGodInfoDTO();
				clmDlvOrdGodInfoDTO.setDlvPcupspTurn(dlvsp.getDlvPcupspTurn() + "");
				clmDlvOrdGodInfoDTO.setOrdGodTurn(clmGod.getOrdGodTurn() + "");
				clmDlvOrdGodInfoDTO.setDlivyDrctGodNo(clmGod.getDlivyDrctGodNo() + "");
				clmDlvOrdGodInfoDTO.setDlvTurn(clmGod.getDlvTurn() + "");
				clmDlvOrdGodInfoDTO.setQty(clmGod.getClmQty().intValue() + "");
				clmDlvOrdGodInfoDTO.setClmResnCd(clmGod.getClmResnCd());
				clmDlvOrdGodInfoDTO.setClmResnCont(clmGod.getClmResnCont());
				clmDlvOrdGodInfoDTO.setGodTpCd(clmGod.getGodTpCd());

				clmOrdGodList.add(clmDlvOrdGodInfoDTO);
			}
		}

		claimSearchDTO.setDlvspClmOrdGod(clmOrdGodList);
		claimSearchDTO.setLgsDlvspList(lgsDlvspList);

		claimSearchDTO.setClmResnCd(claimBoDTO.getClmResnCd());
		HttpServletRequest request = ContextService.getCurrentRequest();
		claimSearchDTO.setUdterId(claimBoDTO.getRegtrId());
		
		return this.updateClaimUnitCancel(request, claimSearchDTO, systemPK);
	}

	@Override
	public HashMap<String, Object> updateClmRfdErrPgTrnsmisTgtYn(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception {
		HashMap<String, Object> map = Maps.newHashMap();		
		if(claimService.updateClmRfdErrPgTrnsmisTgtYn(claimBoDTO) > 0){			
			map.put("RT", "S");
		}else{
			map.put("RT", "F");
		}
		return map;
	}
}
