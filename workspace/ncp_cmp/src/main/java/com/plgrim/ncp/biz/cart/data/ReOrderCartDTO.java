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

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Instantiates a new cart dto.
 *
 * @author mc009.park
 * @since 2015. 4. 2
 */

/**
 * Instantiates a new cart dto.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReOrderCartDTO extends AbstractDTO {

	private String ordNo;
	private String clmNo;
	private List<CartDTO> cartList;

}
