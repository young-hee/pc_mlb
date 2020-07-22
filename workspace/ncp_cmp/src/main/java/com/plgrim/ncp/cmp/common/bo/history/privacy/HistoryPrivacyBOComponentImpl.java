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
package com.plgrim.ncp.cmp.common.bo.history.privacy;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.cmp.common.bo.history.HistoryPrivacyBOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.commons.data.MbrPsnlInfoLogDTO;
import com.plgrim.ncp.commons.result.MbrPsnlInfoLogResult;
import com.plgrim.ncp.commons.service.MbrPsnlInfoLogService;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.transaction.annotation.Transactional;

// @Slf4j
@Component
@Transactional(value = "transactionManager")
public class HistoryPrivacyBOComponentImpl extends AbstractComponent implements HistoryPrivacyBOComponent {

	@Autowired
	MbrPsnlInfoLogService mbrPsnlInfoLogService; // 개인정보제공 로그 Service
	
	/**
	 * 개인정보제공 로그 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<MbrPsnlInfoLogResult> selectMbrPsnlInfoLogList( SystemPK systemPK, MbrPsnlInfoLogDTO paramData) throws Exception {
		return mbrPsnlInfoLogService.selectMbrPsnlInfoLogList(paramData); 
	}
	
	/**
	 * 개인정보제공 로그 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectMbrPsnlInfoLogListCount( SystemPK systemPK, MbrPsnlInfoLogDTO paramData) throws Exception {
		return mbrPsnlInfoLogService.selectMbrPsnlInfoLogListCount(paramData); 
	}
	
	/**
	 * 개인정보제공 로그 목록 조회 엑셀.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectMbrPsnlInfoLogListExcel( SystemPK systemPK, MbrPsnlInfoLogDTO paramData) throws Exception {
		return mbrPsnlInfoLogService.selectMbrPsnlInfoLogListExcel(paramData); 
	}
}
