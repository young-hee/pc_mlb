package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseStockTargetDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = -3646307071252104764L;

    private String ebeln;
    
    private String ebelp;
    
    private String ordNo;
    
    private String itemNo;
    
    private String qtySeq;
    
    private String clmNo;

	private String intrlckTgtYn; // ERP 연동 대상여부
}
