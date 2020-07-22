/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 5. 29       
 */
package com.plgrim.ncp.commons;

import com.plgrim.ncp.commons.data.GodEvlDTO;
import com.plgrim.ncp.framework.data.SystemPK;


public interface GodEvlCmdComponent {
	
	/**
	 * 상품평관리 등록.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateGodEvl( SystemPK systemPK, GodEvlDTO paramData) throws Exception ;
}