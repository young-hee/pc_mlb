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
 * @since       2015. 6. 4       
 */
package com.plgrim.ncp.cmp.common.fo;

import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;


public interface CommonInflowFOComponent {

	/**
	 * 시스템 Inflow 조회
	 * @param sysInflow
	 * @return
	 * @throws Exception
	 */
	public SysInflow selectSysInflow(SysInflow sysInflow) throws Exception;
}