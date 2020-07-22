package com.plgrim.ncp.interfaces.goods.data;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsInventorySDO")
public class GoodsInventorySDO extends InterfaceSDO{
	private static final long serialVersionUID = -3816862468283367913L;

	/**
	 * 상품번호 
	 */
	private String godNo;

	/**
	 * 단품번호 
	 */
	private String itmNo;
	
	/**
	 * 매장ID 
	 */
	private String shopId;
	
	/**
	 * 재고 수량	 
	 */
	private java.lang.Long invQty;	
	
	/**
	 * 예약 판매 상품 여부	 
	 */
	private String resveSaleGodYn;	
	
	/**
	 * 예약 재고 수량
	 * ㅁ. 예약후 판매된 수량을 제외한 수량	 
	 */
	private java.lang.Long resveInvQty;	
	
	/**
	 * 위치1 재고 수량
	 * ㅁ. ERP_QTY	 
	 */
	private java.lang.Long lc1InvQty;	
	
	/**
	 * 판매 예정 수량
	 * ㅁ. 재고 수는 5분에 한번씩 연동 되기 때문에 ERP에 예약 영수증을 보내기전에까지는 판매 예정 재고에서 재고를 차감 해야 한다.
	 * 
	 * ㅁ. 실재고 계산 방법
	 *    >. 총 가용 재고 수량 - 판매 예정 수 - IF(안전 재고 사용여부=Y, 안전 재고 수,0)
	 * 
	 * ㅁ. (+)양수만 사용
	 */
	private java.lang.Long salePrearngeQty;	
	
	/**
	 * 재고 동기화 유형
     * IRDS : 증감
     * DDCT : 차감
	 */
	private String syncType;
	
	/**
	 * 재고 동기화 요청 파트
	 * ORD : 주문
	 * CLM : 클레임
	 * DLV : 배송
	 * 
	 */
	private String syncReqType;
}
