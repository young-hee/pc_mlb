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

import com.plgrim.ncp.base.entities.datasource1.sys.*;
import com.plgrim.ncp.commons.data.FormSysAdmDTO;
import com.plgrim.ncp.commons.data.SysCmmnNotiDTO;
import com.plgrim.ncp.commons.result.*;
import com.plgrim.ncp.framework.data.SystemPK;


public interface OperationUserBOComponent {

    /**
     * 운영자정보를 등록/수정한다.
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public void insertAdminInfo(FormSysAdmDTO paramData) throws Exception;

    /**
     * 운영자정보를 수정한다.
     *
     * @param paramData
     * @throws Exception
     */
    public void updateAdminInfo(FormSysAdmDTO paramData) throws Exception;

    /**
     * 운영자 임시비밀번호 발급
     *
     * @param paramData
     * @throws Exception
     */
    public void updateAdminTempPassword(FormSysAdmDTO paramData) throws Exception;

    /**
     * PO 비밀번호 분실 - 회원 리스트 조회 (존재여부)
     *
     * @param sysAdmin
     * @throws Exception
     */
    public List<SysAdmin> poSelectLostPasswordList(SysAdmin sysAdmin) throws Exception;

    public List<SysAdmin> poSelectLostInfo(SysAdmin sysAdmin) throws Exception;

    /**
     * PO 비밀번호 분실, 임시비밀번호 발급 (로그인 정보가 없는 사용자 요청임)
     *
     * @param sysAdmin
     * @throws Exception
     */
    public String poRequestAdminLostPassword(SysAdmin sysAdmin) throws Exception;

    /**
     * 나의정보 수정 처리
     *
     * @param paramData
     * @throws Exception
     */
    public void updateMyInfo(FormSysAdmDTO paramData) throws Exception;

    /**
     * 운영자 비밀번호 변경
     *
     * @param paramData
     * @throws Exception
     */
    public void updateAdminChangePassword(SysAdmin paramData) throws Exception;

    /**
     * 운영자정보 비밀번호 초기화 날짜 변경
     *
     * @param paramData
     * @throws Exception
     */
    public void updateAdminPwIntzDt(SysAdmin paramData) throws Exception;

    /**
     * 즐겨찾기 등록.
     *
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void insertSysAdminBukmk(SysAdminBukmk paramData) throws Exception;

    /**
     * 즐겨찾기 삭제.
     *
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void deleteSysAdminBukmk(SysAdminBukmk paramData) throws Exception;

    /**
     * 즐겨찾기 목록를 조회한다.
     *
     * @return
     * @throws Exception
     */
    public List<SysAdminBukmkResult> selectAdminBookmarkList() throws Exception;


    /**
     * 운영관리 운영자 목록 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<AdmResult> selectListAdm(FormSysAdmDTO paramData) throws Exception;


    /**
     * 운영관리 운영자목록 엑셀 다운로드
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> selectListAdmExcel(FormSysAdmDTO paramData) throws Exception;

    /**
     * 운영자목록 총 수
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public long selectCountAdmList(FormSysAdmDTO paramData) throws Exception;

    /**
     * 권한그룹 목록 조회
     *
     * @return
     * @throws Exception
     */
    public List<SysAuthorGrp> selectListAuthorGrp() throws Exception;

    /**
     * 운영자 ID 중복 체크
     *
     * @param adminId
     * @return
     * @throws Exception
     */
    public boolean checkAdminId(String adminId) throws Exception;


    /**
     * 운영자 정보 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public AdmResult selectRowAdminInfo(FormSysAdmDTO paramData) throws Exception;

    /**
     * 권한그룹 메뉴 목록 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<AuthResult> selectListAuthMenu(FormSysAdmDTO paramData) throws Exception;

    /**
     * 권한그룹 FILE 목록 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<AuthResult> selectListAuthFile(FormSysAdmDTO paramData) throws Exception;

    /**
     * 브랜드 선택 목록 조회
     *
     * @param paramData * @return
     * @throws Exception
     */
    public List<SysBrndExtend> getComBrndList(SysAdmin paramData) throws Exception;

    public List<SysBrndExtend> getUserComBrndList(FormSysAdmDTO paramData) throws Exception;

    /**
     * PO Dashboard 현황 정보
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public DashboardSumResult selectDashboardSumInfo(FormSysAdmDTO paramData) throws Exception;

    /**
     * PO Main Notice list
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<SysCmmnNotiResult> selectPoMainNoticeList(SystemPK systemPK, SysCmmnNotiDTO paramData) throws Exception;

    /**
     * #33927 BO 배송매장 전용 DashBoard 생성
     * - 배송매장 공지사항
     *
     * @return
     * @throws Exception
     */
    public List<SysCmmnNotiResult> selectDlvShopMainNoticeList() throws Exception;

    /**
     * 운영자 담당 몰 조회
     *
     * @param paramData
     * @throws Exception
     */
    public List<SysAdminMallExtend> selectAdminMall(FormSysAdmDTO paramData) throws Exception;

}