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
 * @since       2015. 4. 28
 */
package com.plgrim.ncp.biz.delivery.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodHist;
import com.plgrim.ncp.base.enums.DeliveryEnum;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliveryExternalRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliveryGlobalAffExternalRepository;
import com.plgrim.ncp.framework.enums.DatabaseType;

import lombok.extern.slf4j.Slf4j;

/**
 * [클래스 설명]
 *
 * <p>
 *
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author jwcale.kim
 * @since 2015. 4. 25
 */
@Slf4j
@Service
public class DeliveryGlobalAffService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryGlobalAffExternalRepository deliveryGlobalAffExternalRepository;
	
	@Autowired
	DeliveryExternalRepository deliveryExternalRepository;
	
	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;
	
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	
	/**
	 * 
	* <pre>
	*	출고지시 단위의 DlvOrdGodInfoDTO 리스트를 구해온다
	* </pre>
	* @param dlvOrdGodInfoDTO
	* @return
	* @since 2015. 11. 17.
	 */
	public List<DlvOrdGodInfoDTO> selectLgsDlivyDrctGodInfoList(DlvOrdGodInfoDTO dlvOrdGodInfoDTO) {
	    return deliveryGlobalAffExternalRepository.selectLgsDlivyDrctGodInfoList(dlvOrdGodInfoDTO);
    }

	/**
	 * 
	* <pre>
	*	회수지시 테이블에 클레임 정보를 적재한다.
	* </pre>
	* @param dlvOrdGodInfoDTO
	 * @throws Exception 
	* @since 2015. 11. 19.
	 */
	public void insertLgsRtrvlDrctGod(DlvOrdGodInfoDTO dlvOrdGodInfoDTO) throws Exception {
	    // TODO Auto-generated method stub

		/* 물류회수지시상품순번 */
		String rtrvlDrctGodNo = getIdGenService().generateDBNumber(sqlSession1, "sq_lgs_rtrvl_drct_god", "R", DatabaseType.ORACLE);
	
		LgsRtrvlDrctGodExtend lgsRtrvlDrctGod = new LgsRtrvlDrctGodExtend();

		lgsRtrvlDrctGod.setRtrvlDrctGodNo(rtrvlDrctGodNo);                          /* 회수지시 상품 번호       */
		lgsRtrvlDrctGod.setOrdNo(dlvOrdGodInfoDTO.getOrdNo());						/* 주문 번호OD || YYYYMMDD || 7자리 Cycle Sequence */
		lgsRtrvlDrctGod.setClmNo(dlvOrdGodInfoDTO.getClmNo());						/* 클레임 번호CL || YYYYMMDD || 7자리 Cycle Sequence */
		lgsRtrvlDrctGod.setDlivyDrctGodNo(dlvOrdGodInfoDTO.getDlivyDrctGodNo());	/* 출고지시 상품 번호		*/
		lgsRtrvlDrctGod.setClmWrhsGodTurn(dlvOrdGodInfoDTO.getClmWrhsGodTurn());    /* 클레임 입고 상품 순번    */
		lgsRtrvlDrctGod.setDlvPcupspTurn(Integer.parseInt(dlvOrdGodInfoDTO.getDlvPcupspTurn()));      /* 배송 수거지 순번         */
		lgsRtrvlDrctGod.setDlvTurn(Integer.parseInt(dlvOrdGodInfoDTO.getDlvTurn()));
		lgsRtrvlDrctGod.setRtrvlDrctTgtYn("N");                                     /* 회수지시 대상 여부       */
		lgsRtrvlDrctGod.setRtrvlDrctYn("N");                                        /* 회수지시 여부            */
		/* 회수지시 유형 코드 회수지시 유형 코드ㅁ. 회수지시유형 : RTRVL_DRCT_SECT   >. 반품 : RTGOD   >. 교환 : EXCHG   >. 맞교환 : DRT_EXCHG   >. 매장 반품 : SHOP_RTGOD */
		lgsRtrvlDrctGod.setRtrvlDrctTpCd("RTGOD");               			/* 회수지시 유형 코드 회수지시 유형 코드ㅁ. 회수지시유형 : RTRVL_DRCT_SECT   >. 반품 : RTGOD   >. 교환 : EXCHG   >. 맞교환 : DRT_EXCHG   >. 매장 반품 : SHOP_RTGOD */		
		//클레임입고상품 등록시 생성한 수량-물류출고지시상품 기준
		lgsRtrvlDrctGod.setRtrvlDrctQty(Long.parseLong(dlvOrdGodInfoDTO.getQty()));        /* 회수지시 수량            */		
		lgsRtrvlDrctGod.setRtrvlGodPrcsComptYn("N");								/* 회수 상품 처리 완료 여부 */
		lgsRtrvlDrctGod.setWrhsComptDt(null);										/* 입고 완료 일시           */
		lgsRtrvlDrctGod.setRtrvlComptDt(null);										/* 회수 완료 일시           */										
		lgsRtrvlDrctGod.setHdryComTrnsmisYn("N");                                   /* 택배사 전송 여부         */
		lgsRtrvlDrctGod.setErpTrnsmisYn("N");                                       /* ERP 전송 여부            */
		lgsRtrvlDrctGod.setErpCnfirmYn("N");                                        /* ERP 확인 여부            */
		lgsRtrvlDrctGod.setRtgodcstCalAmt(new BigDecimal("0"));						/* 반품비 정산 금액         */
		lgsRtrvlDrctGod.setRegtrId("TMALL");                  /* 등록자 ID                */			
		lgsRtrvlDrctGod.setHdryComTrnsmisTgtYn("N");             				/* 택배사 전송 대상 여부    */
		//반품 완료배치에서 사은품은 회수완료되었는지 체크하지 않는다.
		//lgsRtrvlDrctGod.setRtrvlStatCd("RTRVL_DRCT_WAIT");                      	/* 회수 상태 코드ㅁ. 회수 상태 : RTRVL_STAT   >. 회수지시 대기 : RTRVL_DRCT_WAIT   >. 회수지시 : RTRVL_DRCT   >. 입고 완료 : WRHS_COMPT   >. 회수 완료 : RTRVL_COMPT   >. 회수 철회 : RTRVL_WTHDRAW */	
		lgsRtrvlDrctGod.setRtrvlStatCd("RTRVL_DRCT");                      	/* 회수 상태 코드ㅁ. 회수 상태 : RTRVL_STAT   >. 회수지시 대기 : RTRVL_DRCT_WAIT   >. 회수지시 : RTRVL_DRCT   >. 입고 완료 : WRHS_COMPT   >. 회수 완료 : RTRVL_COMPT   >. 회수 철회 : RTRVL_WTHDRAW */

		//로그인된 매장ID 와 출고지시배송매장ID 가 같다면
		lgsRtrvlDrctGod.setErpInvTrnsmisSectCd("CNTR_INV");					/* ERP 재고 전송 구분 : 센터 재고 : CNTR_INV */
		lgsRtrvlDrctGod.setRtrvlShopId(DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString());    							/* 회수매장코드 > CDC  TMALL 매장 코드 > A346*/ 
		//lgsRtrvlDrctGod.setRtrvlShopId("A346");    							/* 회수매장코드 > CDC  TMALL 매장 코드 > A346*/
		
		
		LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();

		//패키지, 세트, 사은품, 상품권 인 경우
		//일반상품 또는 구성품
		//2015-01-04 2차개발 요건으로 쿼리 변경되어 티몰전용 repo 추가
		//deliveryExternalRepository.insertLgsRtrvlDrctGodForClm(lgsRtrvlDrctGod);
		deliveryExternalRepository.insertLgsRtrvlDrctGodForClmTmall(lgsRtrvlDrctGod);
		

		BeanUtils.copyProperties(lgsRtrvlDrctGod, lgsRtrvlDrctGodHist);
		lgsRtrvlDrctGodHist.setUdterId("TMALL");
		deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);

    }

	/**
	 * 
	* <pre>
	*	티몰 반품 완료시 사은품 회수상태 update
	* </pre>
	* @param clm
	* @since 2015. 11. 23.
	 */
	public void updateRtrvlDrctGftForTmall(Clm clm) {
		deliveryGlobalAffExternalRepository.updateRtrvlDrctGftForTmall(clm);
    }



}

