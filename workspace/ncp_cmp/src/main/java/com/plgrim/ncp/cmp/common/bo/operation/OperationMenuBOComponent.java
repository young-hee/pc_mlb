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

import org.springframework.data.domain.Page;

import com.plgrim.ncp.commons.data.FormSysMenuDTO;
import com.plgrim.ncp.commons.data.SysMenuDTO;
import com.plgrim.ncp.commons.result.MenuResult;
import com.plgrim.ncp.commons.result.SysMenuPopupResult;
import com.plgrim.ncp.framework.data.SystemPK;


public interface OperationMenuBOComponent {


    //메뉴리스트 조회

    //메뉴정보 일괄수정 저장처리

    //메뉴등록 처리

    //메뉴수정 저장처리


    /**
     * 메뉴관리 목록 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<MenuResult> selectListMenu(FormSysMenuDTO paramData) throws Exception;

    /**
     * 메뉴 총 횟수 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public long selectCountMenu(FormSysMenuDTO paramData) throws Exception;


    /**
     * 메뉴 팝업 조회
     *
     * @param systemPK
     * @param formSysMenuDTO
     * @return
     * @throws Exception
     */
    public Page<SysMenuPopupResult> selectListMenuPopupList(SystemPK systemPK, FormSysMenuDTO formSysMenuDTO) throws Exception;


    /**
     * 메뉴관리 목록 excel download
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> selectListMenuExcel(FormSysMenuDTO paramData) throws Exception;

    /**
     * 메뉴관리 메뉴정보 조회
     *
     * @param menuSn
     * @return
     * @throws Exception
     */
    public MenuResult selectRowMenu(long menuSn) throws Exception;

    /**
     * 메뉴관리 메뉴파일목록 조회
     *
     * @param menuSn
     * @return
     * @throws Exception
     */
    public List<MenuResult> selectMenuFileList(long menuSn) throws Exception;

    /**
     * 멀티메뉴 사용 횟수 조회
     *
     * @param paramDataList
     * @return
     * @throws Exception
     */
    public long selectUseCntMenuList(List<SysMenuDTO> paramDataList) throws Exception;

    /**
     * 메뉴정보 일괄 저장처리
     *
     * @param paramList
     * @throws Exception
     */
    public void updateMenuList(List<SysMenuDTO> paramList) throws Exception;


    /**
     * 메뉴정보 수정처리
     *
     * @param paramData
     * @throws Exception
     */
    public void updateMenu(SysMenuDTO paramData) throws Exception;


    /**
     * 메뉴정보 등록처리
     *
     * @param paramData
     */
    public void addMenu(SysMenuDTO paramData) throws Exception;

    /**
     * 메뉴정보 일괄삭제
     *
     * @param paramList
     * @return
     * @throws Exception
     */
    public long deleteMenuList(List<SysMenuDTO> paramList) throws Exception;

}