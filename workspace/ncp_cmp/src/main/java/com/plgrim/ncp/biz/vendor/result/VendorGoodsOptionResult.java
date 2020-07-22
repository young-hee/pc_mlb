package com.plgrim.ncp.biz.vendor.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

/**
 * VendorGoodsOptionResult
 * @author lss
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VendorGoodsOptionResult extends AbstractResult {

	/**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    /**
     * 업체ID
     */
    private String comId;
    
    /**
     * 옵션 코드
     */
    private String optCd;
    
    /**
     * 옵션명
     */
    private String optNm;
    
    /**
     * 옵션 값 코드
     */
    private String optValCd;
    
    /**
     * 옵션값명
     */
    private String optValNm;
    
    /**
     * 사용여부
     */
    private String useYn;
    
    /**
     * 등록자아아디
     */
    private String regtrId;
    
    /**
     * 등록일
     */
    private String regDt;

    /**
     * 표준사이즈 옵션코드
     */
    private String stdOptCd;

    /**
     * 표쥰사이즈 옵션값코드
     */
    private String stdOptValCd;
    
	/**
	 * 정렬 순서	 
	 */
	private java.lang.Integer sortSeq;    
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
