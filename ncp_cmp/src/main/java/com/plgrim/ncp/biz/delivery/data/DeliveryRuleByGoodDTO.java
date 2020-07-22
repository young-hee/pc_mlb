/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 4. 1       
 */
package com.plgrim.ncp.biz.delivery.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

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
 * @author 
 * @since 2015. 4. 1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryRuleByGoodDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1592371884513682321L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
    /**
	 * 
	 */
    
	private List<String> godList;	// 상품리스트
	    /**##############  기타[E]  ##############**/

	/**
	 * 주문 번호
	 */
	private String ordNo;
	
	/**
	 * 상품 연결 유형 코드
	 *	ㅁ. 상품 연결 유형 : GOD_CNNC_TP
	 *	   >. 출고 상품 연결 : DLIVY_GOD_CNNC
	 *	   >. 입고 상품 연결 : WRHS_GOD_CNNC
	 *	
	 *	ㅁ. 출고 상품 연결 : 교환시 입고 상품과 출고 상품을 연결
	 *	   >. 교환 출고 상품과 연결 되기 때문에 연결된 상품이 다를 수있다.
	 *	
	 *	ㅁ. 입고 상품 연결 : 교환/반품시 주문 상품과 입고 상품을 연결
	 *	   >. 주문 상품과 입고 상품을 연결 하기 때문에 연결된 상품은 무조건 같다.
	 */
	private String notExistsGodCnncTpCd;
	
	/**
	 * 주문 상품 순번 List	 
	 */
	private List<Integer> ordGodTurnList;
	
	/**
	 * 주문 상품 순번
	 */
	private Integer ordGodTurn;
	
	/**
	 * 클레임 유형 코드 List
	 * 
	 * ㅁ. 클레임 유형 : CLM_TP
	 *	   >. 부분취소 : PART_CNCL
	 *	   >. 전체취소 : ALL_CNCL
	 *	   >. 일반교환 : GNRL_EXCHG
	 *	   >. 맞교환 : DRT_EXCHG
	 *	   >. 반품 : RTGOD
	 *	   >. 옵션변경 : OPT_MOD
	 */
	private String clmTpCd;
	
	/**
	 * 교환후 반품에 대한 조회 여부
	 */
	private boolean isSelectExchgToRt = false;
	
	/**
	 * 반품요청 상품에 해당하는 배송지 - 배송정책 번호별 원 주문 상품 조회 여부
	 */
	private boolean isSelectReqRtByLgsDlv = false;
	
	/**
	 * 배송 수거지 순번	List
	 */
	private List<Integer> dlvPcupspTurnList;
	
	/**
	 * 배송정책조회기준 : 주문 / 클레임
	 */
	private boolean isUseOrd = false;
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
