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
 * @since       2015. 6. 20       
 */
package com.plgrim.ncp.biz.cart.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

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
 * @since 2015. 6. 20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ShopSearchDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4302710795480010155L;

	/** The sido cd. */
	private String sidoCd;
	
	/** The brnd id. */
	private String brndId;
	
	/** The shop id. */
	private String shopId;
	
	private List<String> brndIdList;

	private List<String> godNoList;
 
	private List<String> godItmList;
	
	private int brndCnt;
	
	private String godNo;
	
    private String itmNo;
	
	
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
