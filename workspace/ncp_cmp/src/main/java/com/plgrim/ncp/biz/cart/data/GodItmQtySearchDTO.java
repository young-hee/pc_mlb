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

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.biz.cart.result.CartPrmResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 [장바구니 조회용 DTO]
 *
 * <p>
 *
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author jewellig.lee
 * @since 2017. 7. 26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GodItmQtySearchDTO extends AbstractDTO {

	/**
	 * UUID
	 */

	private static final long serialVersionUID = -8689987723236746536L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	private String[] itmNoArr;
	private String dlvSectCd;
	private String shopId;
	private String bskNo;



	/*옵션변경으로 추가*/
	private String godNo;

}

