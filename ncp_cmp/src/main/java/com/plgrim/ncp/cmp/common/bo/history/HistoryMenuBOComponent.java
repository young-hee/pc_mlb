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


public interface HistoryMenuBOComponent {

    /**
     * 운영자메뉴내역 로그조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<AdmLogResult> selectListMenuLog(FormSysAdmlogDTO paramData) throws Exception;

    /**
     * 운영자메뉴내역 총로그수
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public long selectCountMenuLog(FormSysAdmlogDTO paramData) throws Exception;

    /**
     * 운영자메뉴->File이용 내역
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<AdmLogResult> selectListFileLog(FormSysAdmlogDTO paramData) throws Exception;

    /**
     * 운영자 메뉴(FILE) 이용내역 엑셀다운로드
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> selectListFileLogExcel(FormSysAdmlogDTO paramData) throws Exception;

    /**
     * 운영자메뉴 File 이용 총 로그 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public long selectCountFileLog(FormSysAdmlogDTO paramData) throws Exception;

}