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
package com.plgrim.ncp.cmp.common.bo.operation;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.commons.data.*;
import com.plgrim.ncp.commons.result.AuthGrpResult;
import com.plgrim.ncp.commons.result.AuthResult;
import com.plgrim.ncp.commons.result.MenuResult;
import com.plgrim.ncp.framework.data.SystemPK;


public interface OperationAuthBOComponent {


    /**
     * 권한그룹 저장 처리
     *
     * @param paramData
     */
    public void mergeAuthGrp(SysAuthGrpDTO paramData) throws Exception;

    /**
     * 일괄 권한그룹 저장처리
     *
     * @param paramData
     * @throws Exception
     */
    public void updateAuthGrp(List<SysAuthGrpDTO> paramData) throws Exception;


    /**
     * 권한그룹 복사
     *
     * @param paramData
     */
    public void copyAuthGrp(SysAuthGrpDTO paramData) throws Exception;


    /**
     * 권한(기능/메뉴)정보 수정
     *
     * @param paramData
     */
    public void updateAuth(long authorGrpSn, long menuSn, List<SysAuthDTO> paramData) throws Exception;

    /**
     * 버튼권한 정보 수정
     *
     * @param authorGrpSn
     * @param menuSn
     * @param paramList
     * @throws Exception
     */
    public void updateAuthButton(long authorGrpSn, long menuSn, List<SysAuthDTO> paramList) throws Exception;

    /**
     * 권한그룹정보 삭제
     *
     * @param paramList
     */
    public void deleteAdmAuthGrp(List<SysAuthGrpDTO> paramList) throws Exception;

    /**
     * 권한메뉴정보 삭제
     *
     * @param paramList
     */
    public void deleteAdmAuthMenu(List<SysAuthDTO> paramList) throws Exception;

    /**
     * 권한 메뉴정보를 등록한다.
     *
     * @param authorGrpSn
     * @param paramList
     * @throws Exception
     */
    public void updateAuthMenu(long authorGrpSn, List<SysMenuDTO> paramList) throws Exception;


    /**
     * 권한그룹 목록 조회
     *
     * @param systemPK
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<AuthGrpResult> selectListAuthGrp(SystemPK systemPK, FormSysAuthGrpDTO paramData) throws Exception;

    /**
     * 권한그룹 excel download
     *
     * @param systemPK
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> selectListAuthGrpDownload(SystemPK systemPK, FormSysAuthGrpDTO paramData) throws Exception;

    /**
     * 권한그룹사용 횟수 조회
     *
     * @param systemPK
     * @param paramData
     * @return
     * @throws Exception
     */
    public long selectUseCntAuthGrp(SystemPK systemPK, SysAuthGrpDTO paramData) throws Exception;


    /**
     * 권한그룹 사용 유/무 ( true/false)
     *
     * @param systemPK
     * @param paramData
     * @return
     * @throws Exception
     */
    public boolean isAuthGrpUse(SystemPK systemPK, SysAuthGrpDTO paramData) throws Exception;


    /**
     * 권한 메뉴 목록 조회
     *
     * @param paramData
     * @return
     */
    /**
     * @param systemPK
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<AuthResult> selectListAuthMenu(SystemPK systemPK, FormSysAuthGrpDTO paramData) throws Exception;

    /**
     * 권한(메뉴/기능) 사용 횟수 조회
     *
     * @param systemPK
     * @param paramData
     * @return
     * @throws Exception
     */
    public long selectUseCntAuth(SystemPK systemPK, SysAuthDTO paramData) throws Exception;


    /**
     * 권한 기능 목록 조회
     *
     * @param systemPK
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<AuthResult> selectListAuth(SystemPK systemPK, FormSysAuthGrpDTO paramData) throws Exception;


    /**
     * 메뉴 목록
     *
     * @param systemPK
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<MenuResult> selectListMenu(SystemPK systemPK, FormSysAuthGrpDTO paramData) throws Exception;

    /**
     * 메뉴 목록(로그인한 사용자의 권한 그룹별 메뉴 목록)
     *
     * @param systemPK
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<MenuResult> selectListRoleMenuAll(SystemPK systemPK, SysAuthMenuDTO paramData) throws Exception;
}