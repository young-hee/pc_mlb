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
package com.plgrim.ncp.cmp.common.bo.system;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfigDetail;
import com.plgrim.ncp.commons.data.SysConfigDTO;
import com.plgrim.ncp.commons.result.SysConfigResult;

public interface SystemConfigureBOComponent {

    /**
     * 설정등록 처리
     *
     * @param paramData
     * @throws Exception
     */
    public void addSysConfig(SysConfigDTO paramData) throws Exception;

    /**
     * 설정값 일괄 등록/수정 처리
     *
     * @param updateList
     * @throws Exception
     */
    public void updateSysDynmcConfigDetailList(List<SysConfigDTO> updateList) throws Exception;

    /**
     * 설정상세 삭제 처리
     *
     * @param paramData
     * @throws Exception
     */
    public void deleteSysDynmcConfigDetail(SysDynmcConfigDetail paramData) throws Exception;

    /**
     * 설정 수정 처리
     *
     * @param paramData
     * @throws Exception
     */
    public void updateSysDynmcConfig(SysConfigDTO paramData) throws Exception;


    /**
     * 설정조회 리스트
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<SysConfigResult> selectListSysConfig(SysConfigDTO paramData) throws Exception;

    /**
     * 설정조회 리스트 총 갯수 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public long selectListCountSysConfig(SysConfigDTO paramData) throws Exception;

    /**
     * 설정조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public SysConfigResult selectSysConfig(SysConfigDTO paramData) throws Exception;

    /**
     * 설정조회 목록 엑셀 다운로드
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> selectListSysConfigExcel(SysConfigDTO paramData) throws Exception;

    /**
     * 설정상세 리스트 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<SysConfigResult> selectSysConfigDetailList(SysConfigDTO paramData) throws Exception;

    /**
     * 서킷브래이커 스케줄 대상 설정조회 팝업 리스트
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<SysConfigResult> selectListSysConfigPop(SysConfigDTO paramData) throws Exception;

    /**
     * 서킷브래이커 스케줄 대상 설정조회 팝업 리스트 총 갯수 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public long selectListCountSysConfigPop(SysConfigDTO paramData) throws Exception;
}