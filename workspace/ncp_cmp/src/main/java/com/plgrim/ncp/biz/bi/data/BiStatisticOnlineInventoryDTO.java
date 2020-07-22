package com.plgrim.ncp.biz.bi.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BiStatisticOnlineInventoryDTO extends AbstractDTO{
	
	private static final long serialVersionUID = -1851547754983196467L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */	
	
	/** 색상코드 */
	private String colorCd;
	
	/** 사이즈 코드 */
	private String sizeCd;
	
	/** 재고 수량 - 최소 수 */
	private String invMinQty;
	
	/** 재고 수량 - 최고 수 */
	private String invMaxQty; 

	/** 상품번호 구분 */
	private String godNosGbn;

	/** 재고 구분 */
	private String invGbn;
	
	/** 시즌 코드 */
	private String[] seasonGrpCds; 
		
    /** 상품번호 멀티 */
    private String[] godNos;
    
    /** 브랜드ID 멀티. */
    private String[] brndIds;
	
}
