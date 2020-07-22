/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.commons.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;

/**
 * 메뉴 Repository
 * @author Park
 *
 */
@Repository
public class SysInflowViewRepository extends AbstractRepository {


	public List<SysInflow> selectSysInflowAll(SysInflow inflow) {

		return getSession1().selectList("com.plgrim.ncp.commons.sysinflow.selectSysInflowAll",inflow);
	}
	public SysInflow selectSysInflow(SysInflow inflow) {
		
		return getSession1().selectOne("com.plgrim.ncp.commons.sysinflow.selectSysInflow",inflow);
	}
}
