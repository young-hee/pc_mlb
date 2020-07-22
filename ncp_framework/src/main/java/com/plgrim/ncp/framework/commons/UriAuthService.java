/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 31       
 */
package com.plgrim.ncp.framework.commons;

import com.plgrim.ncp.framework.data.UriAuthData;

import lombok.Data;

import org.apache.ibatis.session.SqlSession;

/**
 * 메뉴 접속 권한 체크 관련
 *
 * <p>
 *
 * <ul>
 * <li>권한 체크
 * <li>uri
 * </ul>.
 *
 * @author by peter
 * @since 2016. 11. 17
 */

@Data
public class UriAuthService {

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
    public void insertUriAuth(SqlSession session, UriAuthData menuAuth) {
        session.selectOne("insertMenuAuthLog", menuAuth);
    }
    
    public Integer getConnectIpCount(SqlSession session, UriAuthData menuAuth) {
        return session.selectOne("getConnectIpCount", menuAuth);
    }
}
