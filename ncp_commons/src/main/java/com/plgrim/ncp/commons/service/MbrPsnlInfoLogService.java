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
 * @since       2015. 6. 10       
 */
package com.plgrim.ncp.commons.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.commons.data.MbrPsnlInfoLogDTO;
import com.plgrim.ncp.commons.repository.MbrPsnlInfoLogRepository;
import com.plgrim.ncp.commons.result.MbrPsnlInfoLogResult;

/**
 * 개인정보제공 로그 Service
 * @author ed
 *
 */
@Service
public class MbrPsnlInfoLogService extends AbstractService {

	@Autowired
    MbrPsnlInfoLogRepository mbrPsnlInfoLogRepository; // 개인정보제공 로그 DAO
	
	/**
	 * 단체주문 / 제휴문의 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<MbrPsnlInfoLogResult> selectMbrPsnlInfoLogList( MbrPsnlInfoLogDTO paramData) throws Exception {
		return mbrPsnlInfoLogRepository.selectMbrPsnlInfoLogList(paramData); 
	}
	
	/**
	 * 단체주문 / 제휴문의 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectMbrPsnlInfoLogListCount( MbrPsnlInfoLogDTO paramData) throws Exception {
		return mbrPsnlInfoLogRepository.selectMbrPsnlInfoLogListCount(paramData); 
	}
	
	/**
	 * 단체주문 / 제휴문의 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectMbrPsnlInfoLogListExcel( MbrPsnlInfoLogDTO paramData) throws Exception {
		return mbrPsnlInfoLogRepository.selectMbrPsnlInfoLogListExcel(paramData); 
	}
}
