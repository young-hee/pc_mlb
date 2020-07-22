/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : jackie(jackie)
 * @date : 20150511
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 시스템 매장 브랜드
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("SysShopBrndExtend")
public class SysShopBrndExtend extends SysShopBrnd {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = -6289967097483357287L;

    /**
	 * 업체ID	 
	 */
	private String comId;
	
	/**
	 * 브랜드ID	 
	 */
	private String brndId;

	/**
	 * 매장명	 
	 */
	private String shopNm;
	
	/**
	 * 브랜드명	 
	 */
	private String brndNm;
	
	/**
	 * 안전재고 사용여부 변경전	 
	 */
	private String safeInvUseYnBf;
    
	/**
	 * 안전 재고 비율	 
	 */
	private int safeInvRtInt;
	
	/**
	 * 안전 재고 비율 변경전	 
	 */
	private int safeInvRtBf;

	/**
	 * 안전 재고 정량 
	 */
	private int safeInvQtyInt;
	
	/**
	 * 배송예정수량
	 */
	private int salePrearngeQty;
	
	/**
	 * 안전 재고 정량 변경전	 
	 */
	private int safeInvQtyBf;
	
	/**재고수량 
	 */
	private int invQtyInt;
	
	/**
	 * 이력관련 : 이력구분
	 */
	private String histSect;
	
	/**
	 * 이력관련 : 변경항목
	 */
	private String modIem;
	
	/**
	 * 웹 판매 가능 여부	 
	 */
	private String webSalePsbYn;
	
	/**
	 * 웹 판매 가능 여부 : 변경전	 
	 */
	private String webSalePsbYnBf;
	
	/**
	 * 등록 일시	 
	 */
	private String regDtStr;

	/**
	 * 수정 일시	 
	 */
	private String udtDtStr;
	
	/**
	 * 주문번호	 
	 */
	private String ordNo;
	
	/**
	 * 퀵배송여부
	 */
	private String qdlvShopYn;
	
}
