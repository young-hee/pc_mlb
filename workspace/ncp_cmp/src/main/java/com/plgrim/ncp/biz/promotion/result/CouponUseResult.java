package com.plgrim.ncp.biz.promotion.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractResult;

/**
 * 쿠폰 사용 Result
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CouponUseResult extends AbstractResult {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	
	/**
	 * 오프라인 쿠폰번호
	 */
	private String cpnCrtfcCd;
	
	/**
	 * 프로모션 번호
	 */
	private String prmNo;
	
    
    /**
     * 쿠폰상태
     */
    private String cpnStatCd;                           
    
	
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
