package com.plgrim.ncp.biz.member.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReviewCountResult extends AbstractResult{
	/**
	 * 
	 */
    private static final long serialVersionUID = -4733176959078640346L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    private int noReviewCnt;
    
    private int reviewCnt;

	private String pckageGodTpCd; /*패키지형 상품 유형코드*/

	private String dlvStatCd;  /* 배송 상태 코드 */
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
