/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      mc009.park
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.biz.cart.result;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmBundleDcCnd;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author mc009.park
 * @since 2015. 6. 19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CartPrmResult extends AbstractResult{

	
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2428061740182702517L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
     /** The grp seq. */
	private int grpSeq;
    
     /** The god turn. */
     private int godTurn;
    
     /** The prm no. */
     private String prmNo;
     
     /** The god no. */
     private String godNo;
     
     /** The csmr prc. */
     private BigDecimal csmrPrc;
     
     /** The emp prc. */
     private BigDecimal empPrc;
     
     /** The itm qty. */
     private Integer itmQty = 0;
     
     /** The prm tp cd. */
     private String prmTpCd;
     
     /** The prm dtl tp cd. */
     private String prmDtlTpCd;
     
     /** The god gft sect cd. */
     private String godGftSectCd;
     
     private String accmlSectCd;
     
     private BigDecimal accmlAmt;
     
     private BigDecimal accmlRt;
     
     
     /** The prm bundle dc cnd list. */
     List<PrmBundleDcCnd> prmBundleDcCndList;

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
