package com.plgrim.ncp.biz.delivery.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryAffDTO extends AbstractDTO{

    private static final long serialVersionUID = 1L;
    
    /** 주문번호 */
	private String ordNo;
	/** 주문 일시 */
	private String ordDt;
	/** 단/복품 */
	private String lgsItmYn;
	/** ERP 상품 번호 */
	private String erpGodNo;
	/** 상품 번호 */
	private String godNm;
	/** 주문 수량*/
	private String ordQty;
	/** 정소가 */
	private Long rtlPrc;
	/** 실소가*/
	private Long saleAmt;
	/** 해외 운송장 번호*/
	private String waybilNo;
	/** 배송추적 */
	private String deliveryTrace;
	/** 판토스 주문번호 */
	private String ordPantosNo;
	/** 제휴사 주문 번호*/
	private String affComOrdNo;
	/** 제휴사 상품 번호*/
	private String affComOrdGodNo;
	/** 옵션 */
	private String options;
	/** 해외배송 URL */
	private String cdPantosDscr;
	

}
