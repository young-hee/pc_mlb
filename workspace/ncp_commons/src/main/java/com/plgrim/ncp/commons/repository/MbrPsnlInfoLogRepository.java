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
 * @since       2015. 7. 13       
 */
package com.plgrim.ncp.commons.repository;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.commons.data.MbrPsnlInfoLogDTO;
import com.plgrim.ncp.commons.result.MbrPsnlInfoLogResult;

/**
 * 개인정보제공 로그 Repository
 * @author ed
 *
 */
@Slf4j
@Repository
public class MbrPsnlInfoLogRepository extends AbstractRepository {
	
	/**
	 * 개인정보제공 로그 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<MbrPsnlInfoLogResult> selectMbrPsnlInfoLogList( MbrPsnlInfoLogDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.mbrpsnlinfolog.selectMbrPsnlInfoLogList", paramData);
	}
	
	/**
	 * 개인정보제공 로그 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectMbrPsnlInfoLogListCount( MbrPsnlInfoLogDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.mbrpsnlinfolog.selectMbrPsnlInfoLogListCount", paramData);
    }
	
	/**
	 * 개인정보제공 로그 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectMbrPsnlInfoLogListExcel( MbrPsnlInfoLogDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.mbrpsnlinfolog.selectMbrPsnlInfoLogListExcel", paramData);
	}
}





