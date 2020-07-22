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
package com.plgrim.ncp.cmp.orderfulfilment.fo.sale;

import java.util.HashMap;
import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.biz.cart.data.CartDTO;
import com.plgrim.ncp.biz.cart.data.CartGodOptionDTO;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.data.GodReWhsgNtcnDTO;
import com.plgrim.ncp.biz.cart.result.CartSimpleListResult;
import com.plgrim.ncp.commons.data.order.KcpCommonReceiveDTO;
import com.plgrim.ncp.framework.data.SystemPK;

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
 */
public interface SaleComponent {


	/**
	 * 위시리스트 삭제
	 * 
	 * @param systemPK
	 * @param cartSearchDTO
	 * @throws Exception
	 */
	String saleKcpConfirmReceive(KcpCommonReceiveDTO kcpCommonReceiveDTO) throws Exception;

}
