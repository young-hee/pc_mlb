package com.plgrim.ncp.biz.goods.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsSmsSendResult extends AbstractResult{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = -10L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    private String godNm;

    private String mobilNo;

    private String itmNm;

    private String reWhsgNtcnSn;

    private String loginId;
    
    private String brndNm;	// SMS개선 by cannon : 2016.07.17 - BEAKER최상위, 그외 2레벨

    private String mbrNo;

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
