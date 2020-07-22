/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      ystam.kwon
 * @since       2015. 4. 17
 */
package com.plgrim.ncp.cmp.common.bo.history;

import com.plgrim.ncp.commons.data.FormSysAdmlogDTO;
import com.plgrim.ncp.commons.result.AdmLogResult;

import java.util.List;
import java.util.Map;


public interface HistoryAccessBOComponent {


    /**
     * 운영자로그인로그 목록조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<AdmLogResult> selectListLoginLog(FormSysAdmlogDTO paramData) throws Exception;

    /**
     * 운영자로그인로그 목록 엑셀다운로드
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> selectListLoginLogExcel(FormSysAdmlogDTO paramData) throws Exception;


    /**
     * 운영자로그인로그 총로그수
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public long selectCountLoginLog(FormSysAdmlogDTO paramData) throws Exception;

}