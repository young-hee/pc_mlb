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
package com.plgrim.ncp.biz.cart.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;

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
public class CartGodOptionDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7747026340368521768L;

	/** The bsk. */
	Bsk bsk = new Bsk();
	
	/** The bsk no. */
	private String bskNo;
	
	/** The god turn. */
	private int godTurn;
	
	/** The itm qty. */
	private int itmQty;
	
	/** The bsk god list. */
	List<BskGod> bskGodList;
	
	/** 수정자 Id */
	private String udterId;
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
