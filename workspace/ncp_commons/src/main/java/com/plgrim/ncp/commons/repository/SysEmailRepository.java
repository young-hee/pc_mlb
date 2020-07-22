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
 * @since       2015. 6. 3       
 */
package com.plgrim.ncp.commons.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysSmsEmailTxt;
import com.plgrim.ncp.commons.data.SysEmailDTO;
import com.plgrim.ncp.commons.result.SysEmailResult;

/**
 * 이메일 발송 Repository
 * @author ed
 *
 */
//@Slf4j
@Repository
public class SysEmailRepository extends AbstractRepository {
	
	/**
	 * 이메일 자주 사용하는 문구 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysEmailResult> selectSysEmailTxtList( SysEmailDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.sysemail.selectSysEmailTxtList", paramData);
	}
	
	/**
	 * 이메일 자주 사용하는 문구 등록.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int insertSysEmailTxt(SysSmsEmailTxt paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.sysemail.insertSysEmailTxt", paramData);
	}
	
	/**
	 * 이메일 자주 사용하는 문구 수정.
	 *
	 * @param paramData [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public int updateSysEmailTxt(SysSmsEmailTxt paramData) throws Exception {
		return getSession1().delete("com.plgrim.ncp.commons.sysemail.updateSysEmailTxt", paramData);
	}
}





