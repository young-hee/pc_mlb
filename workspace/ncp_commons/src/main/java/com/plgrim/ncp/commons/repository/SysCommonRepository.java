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

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.commons.result.RefundAccountResult;

/**
 * 시스템 공통 Repository
 * @author Park
 *
 */
@Repository
public class SysCommonRepository extends AbstractRepository {

	public RefundAccountResult getPrivateKeyForCryption(Map<String,String> paramMap) {
	    
	    return getSession3().selectOne("com.plgrim.ncp.commons.syscommon.selectPrivateKeyCryption", paramMap);
    }

	public void setPrivateKeyForCryption(Map<String,String> paramMap) {

		getSession3().insert("com.plgrim.ncp.commons.syscommon.insertPrivateKeyCryption", paramMap);
    }
}
