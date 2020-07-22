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

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExcpCd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExcpCdExtend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysExcpCodeRepository extends AbstractRepository {

	/**
	 * 예외 코드 설명 조회
	 */
	public List<SysExcpCdExtend> selectAllSysExcpGrpCd(SysExcpCdExtend sysExcpCdExtend){
		return getSession1().selectList("com.plgrim.ncp.commons.sysexcpcd.selectAllSysExcpGrpCd", sysExcpCdExtend);
	}
	
	public SysExcpCd selectSysExcpCdForOrd(SysExcpCd sysExcpCd){
		return getSession1().selectOne("com.plgrim.ncp.commons.sysexcpcd.selectSysExcpCdForOrd", sysExcpCd);
	}
	
}
