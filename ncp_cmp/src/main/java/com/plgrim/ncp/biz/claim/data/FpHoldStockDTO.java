/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shhenry.choi
 * @since       2015. 3. 17
 */
package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

/**
 * 클레임 DTO.
 *
 * @author shhenry.choi
 * @since 2015. 3. 18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FpHoldStockDTO extends AbstractDTO {/**
	 * 
	 */
    private static final long serialVersionUID = -6570437008172857389L;
    

	/**
	 * 주문 번호	 
	 */
	private String ordNo;
	/**
	 * 주문 상품 순번	 
	 */
	private java.lang.Integer ordGodTurn;
	/**
	 * 수량 순번
ㅁ. 주문별 상품 및 수량에 대한 순번
ㅁ. 주문 번호에 유일성을 가짐	 
	 */
	private java.lang.Integer qtyTurn;
	/**
	 * 주문 상품 수량 순번
ㅁ. 주문별 상품별 수량에 대한 순번
ㅁ. 주문별 상품별 유일성을 가짐	 
	 */
	private java.lang.Integer ordGodQtyTurn;
	/**
	 * SKU 번호
ㅁ. Stock Keeping Unit
   >. 자사의 경우 ERP 상품 번호에 사이즈 옵션 값 코드를 붙인것을 SKU로 정의
   >. 입점사는 입력된 ERP 상품 번호에 모든 옵션 값 코드를 붙여 사용
	 */
	private String skuNo;
    
    
	private String r3nameR;	//erpJcoMode
	
	private String callerId;		//호출자 ID

	private String result;		//결과 코드
	

	private String	werks; //	물류센터		//호출시 필수
	
	private String	material; //	품번		//호출시 필수
	
	private String	quantity; //	수량		//호출시 필수
	
	private String	reason; //	사유		//호출시 필수
	
	private String	fpkey; //	패션피아 접수번호		//호출시 필수
	
	private String	status; //	처리결과 : S, E
	
	private String	message; //	결과메시지
	
	private String	ebeln; //	STO번호
	
	private String	ebelp; //	일련번호
    




}
