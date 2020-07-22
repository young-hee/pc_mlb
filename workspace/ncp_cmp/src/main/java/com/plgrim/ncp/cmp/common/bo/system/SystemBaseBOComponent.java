/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 8. 4
 */
package com.plgrim.ncp.cmp.common.bo.system;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.sys.*;
import com.plgrim.ncp.commons.data.*;
import com.plgrim.ncp.commons.result.*;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.data.domain.Page;


public interface SystemBaseBOComponent {

    /**
     * 시스템 브랜드 등록
     *
     * @param paramData
     * @throws Exception
     */
    public void insertSysBrnd(FormSysBrndDTO paramData) throws Exception;

    /**
     * 시스템 브랜드 이미지 등록
     *
     * @param paramList
     * @throws Exception
     */
    public void insertSysBrndImg(List<SysBrndImg> paramList) throws Exception;


    /**
     * 시스템 브랜드 수정
     *
     * @param paramData
     * @throws Exception
     */
    public void updateSysBrnd(FormSysBrndDTO paramData) throws Exception;

    /**
     * 시스템 브랜드 이미지 수정
     *
     * @param paramList
     * @throws Exception
     */
    public void updateSysBrndImg(List<SysBrndImg> paramList) throws Exception;

    /**
     * 시스템 브랜드 이미지 삭제
     *
     * @param paramList
     * @throws Exception
     */
    public void deleteSysBrndImg(List<SysBrndImg> paramList) throws Exception;


    /**
     * 일괄 시스템 브랜드 정보 수정
     *
     * @param paramList
     * @throws Exception
     */
    public void updateSysBrndList(List<SysBrandDTO> paramList) throws Exception;

    /**
     * 임직원 할인율 수정
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param list [설명]
     * @throws Exception the exception
     * @since 2015. 8. 4
     */
    public void updateSysBrandEmpDcRtList(List<SysBrandDTO> list) throws Exception;

    /**
     * 포인트적립률 수정.
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param list [설명]
     * @throws Exception the exception
     * @since 2015. 7. 13
     */
    public void updateSysBrandPntAccmlList(List<SysBrandDTO> list) throws Exception;

    /**
     * 포인트종류별 적립률 수정.
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param list [설명]
     * @throws Exception the exception
     * @since 2015. 7. 13
     */
    public void updateSysBrandPntAccmlListByType(List<SysBrandDTO> list) throws Exception;

    /**
     * 브랜드별 일모 할인률 목록 조회
     *
     * @param sysBrandDTO
     * @return
     * @throws Exception
     */
    public List<SysBrandResult> selectSysBrandPntAccmlList(SysBrandDTO sysBrandDTO) throws Exception;

    /**
     * 브랜드별 포인트 적립률 목록 TotalCount
     *
     * @param sysBrandDTO
     * @return
     * @throws Exception
     */
    public int selectSysBrandPntAccmlTotalCount(SysBrandDTO sysBrandDTO) throws Exception;

    /**
     * 브랜드별 포인트 적립률 목록 엑셀다운로드.
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param sysBrandDTO [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 7. 8
     */
    public List<Map<String, Object>> selectSysBrandPntAccmlListByAll(SysBrandDTO sysBrandDTO) throws Exception;

    /**
     * 브랜드코드관리 > 브랜드목록 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<SysBrandResult> selectListSysBrnd(FormSysBrndDTO paramData) throws Exception;

    /**
     * 브랜드코드관리 > 자식 브랜드 목록 조회
     *
     * @param paramData (selBrndId 필수)
     * @return
     * @throws Exception
     */
    public List<SysBrandResult> selectListChildSysBrnd(FormSysBrndDTO paramData) throws Exception;

    /**
     * 브랜드코드관리 > 자식 브랜드 정보 조회
     *
     * @param paramData (selBrndId 필수)
     * @return
     * @throws Exception
     */
    public SysBrandResult selectRowSysBrnd(FormSysBrndDTO paramData) throws Exception;


    /**
     * 브랜드코드관리 > 브랜드코드 사용가능 여부
     *
     * @param paramData
     * @return 사용가능(true)
     * @throws Exception
     */
    public boolean isUseBrndId(FormSysBrndDTO paramData) throws Exception;


    /**
     * 인터페이스 브랜드ID 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<ErpBrndIdResult> selectListErpBrndId(FormSysBrndDTO paramData) throws Exception;

    /**
     * 인터페이스 브랜드 tag 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<BrndTagCdResult> selectListBrndTagCd(FormSysBrndDTO paramData, SystemPK systemPk) throws Exception;

    /**
     * beaker brand 체크 ( 1이면 beaker 브랜드 그룹 브랜드)
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public int checkBeakerBrndCount(FormSysBrndDTO paramData) throws Exception;

    /**
     * 공통그룹코드 수정처리
     *
     * @param paramData
     * @throws Exception
     */
    public void updateSysGrpCd(List<SysCdDTO> paramData) throws Exception;

    /**
     * 공통그룹코드 등록처리
     *
     * @param paramList
     * @throws Exception
     */
    public void insertSysGrpCd(List<SysCdDTO> paramList) throws Exception;

    /**
     * 공통그룹 상세코드 수정처리
     *
     * @param paramList
     * @throws Exception
     */
    public void updateSysGrpCdDetail(List<SysCdDTO> paramList) throws Exception;

    /**
     * 공통그룹 상세코드 등록처리
     *
     * @param paramList
     * @throws Exception
     */
    public void insertSysGrpCdDetail(String grpCd, List<SysCdDTO> paramList) throws Exception;

    /**
     * 공통코드그룹 목록 조회
     *
     * @param
     * @return
     */
    public List<SysCodeResult> selectListCdGrp(FormSysCodeDTO paramData) throws Exception;

    /**
     * 공통코드그룹 상세코드목록 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<SysCodeResult> selectListCdGrpDetail(FormSysCodeDTO paramData) throws Exception;

    /**
     * 사용하고 있는 코드인지 체크한다.
     *
     * @param cd
     * @return true:사용
     * @throws Exception
     */
    public boolean isUseFromSysCd(String cd) throws Exception;

    /**
     * 시스템예외그룹코드 List 조회
     *
     * @param sysExcpCdExtend
     * @return
     * @throws Exception
     */
    public List<SysExcpCdExtend> selectAllSysExcpGrpCd(SysExcpCdExtend sysExcpCdExtend) throws Exception;


    /* 일괄 PrudCd 저장처리
     * @param paramList
     * @throws Exception
     */
    public void updatePrudCd(List<SysPrudDTO> paramList) throws Exception;

    /**
     * 목록 조회
     *
     * @param
     * @return
     */
    public List<SysPrudResult> selectSysPrudCdList(SystemPK systemPK, FormSysPrudDTO paramData) throws Exception;

    /**
     * 목록 총 횟수
     *
     * @param systemPK
     * @param paramData
     * @return
     * @throws Exception
     */
    public long selectCountSysPrudCd(SystemPK systemPK, FormSysPrudDTO paramData) throws Exception;

    /**
     * 품목코드 목록 엑셀다운로드
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> selectSysPrudCdListExcel(FormSysPrudDTO paramData) throws Exception;

    /**
     * 일괄 PrudWtCd 업데이트
     *
     * @param paramList
     * @throws Exception
     */
    public void updatePrudWtCd(List<SysPrudWtDTO> paramList) throws Exception;

    /**
     * PrudWtCd 저장
     *
     * @param paramData
     * @throws Exception
     */
    public int insertPrudWtCd(SysPrudWtDTO paramData) throws Exception;

    /**
     * 목록 조회
     *
     * @param
     * @return
     */
    public List<SysPrudWtResult> selectSysPrudWtCdList(SystemPK systemPK, SysPrudWtDTO paramData) throws Exception;

    /**
     * 목록 총 횟수
     *
     * @param systemPK
     * @param paramData
     * @return
     * @throws Exception
     */
    public long selectCountSysPrudWtCd(SystemPK systemPK, SysPrudWtDTO paramData) throws Exception;

    /**
     * 품목코드 목록 엑셀다운로드
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> selectSysPrudWtCdListExcel(FormSysPrudDTO paramData) throws Exception;

    /**
     * 품목코드 등록 페이지 품목 select box
     *
     * @return
     * @throws Exception
     */
    public List<SysPrudResult> selectSysPrdlstCd() throws Exception;
    /**
     * 판매점코드조회(페이징처리되지않은)
     *
     * @param sysErpSaleShop
     * @return
     * @throws Exception
     */
    public List<SysErpSaleShop> selectSysErpSaleShopList(SysErpSaleShop sysErpSaleShop) throws Exception ;
    /**
     * 매장 등록.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void insertSysShop( SystemPK systemPK, SysShopDTO paramData) throws Exception ;

    /**
     * 매장 수정.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void updateSysShop( SystemPK systemPK, SysShopDTO paramData) throws Exception ;

    /**
     * 매장 이미지관리 페이지 수정.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 8. 10
     */
    public void updateSysShopImgPage( SystemPK systemPK, SysShopDTO paramData) throws Exception ;


    /**
     * 매장 그리드 수정.
     *
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void updateSysShopGrid(List<SysShopDataDTO> paramData) throws Exception ;


    /**
     * 매장 이미지 수정
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 8. 8
     */
    public void updateSysShopImg( SystemPK systemPK, SysShopDTO paramData) throws Exception ;


    /**
     * 매장 이벤트 등록
     * @param sysShopEvt
     * @return
     * @throws Exception
     */
    public String insertSysShopEvt(SysShopEvt sysShopEvt)throws Exception;

    /**
     * 매장 이벤트 수정
     * @param sysShopEvt
     * @return
     * @throws Exception
     */
    public String updateSysShopEvt(SysShopEvt sysShopEvt)throws Exception;

    /**
     * 브랜드 매장 휴일 일자별 등록
     * @param sysShopHoldy
     * @return
     * @throws Exception
     */
    public String insertSysShopHoldyDay(SysShopHoldy sysShopHoldy) throws Exception;

    /**
     * 브랜드 매장 휴일 일자별 수정
     * @param sysShopHoldyDTO
     * @return
     * @throws Exception
     */
    public String updateSysShopHoldyDay(SysShopHoldyDTO sysShopHoldyDTO) throws Exception;

    /**
     * 브랜드 매장 휴일 일자별 삭제
     * @param sysShopHoldy
     * @return
     * @throws Exception
     */
    public int deleteSysShopHoldyDay(SysShopHoldy sysShopHoldy) throws Exception;

    /**
     * 브랜드 매장 휴일 요일별 등록
     * @param sysShopHoldyDTO
     * @return
     * @throws Exception
     */
    public void insertSysShopHoldyWeek(SysShopHoldyDTO sysShopHoldyDTO) throws Exception;


    /**
     * 매장 목록 조회.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public List<SysShopResult> selectSysShopList( SystemPK systemPK, SysShopDTO paramData) throws Exception ;

    /**
     * 매장 목록 조회 개수.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @return Long [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public long selectSysShopListCount( SystemPK systemPK, SysShopDTO paramData) throws Exception ;

    /**
     * 매장 목록 조회 엑셀.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public List<Map<String, Object>> selectSysShopListExcel( SystemPK systemPK, SysShopDTO paramData) throws Exception ;

    /**
     * 매장 상세 조회.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @return Sys cmmn noti result [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public SysShopResult selectSysShopDetail( SystemPK systemPK, SysShopDTO paramData) throws Exception ;

    /**
     * 브랜드 매장 이벤트 상세조회
     * @param sysShopEvt
     * @return
     * @throws Exception
     * @since 2015. 8. 10
     */
    public SysShopEvtResult selectSysShopEvt(SysShopEvt sysShopEvt)throws Exception;

    /**
     * 브랜드 매장 이벤트 목록
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     * @since 2015. 8. 10
     */
    public Page<SysShopResult> selectSysShopEvtList(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception;

    /**
     * 브랜드 매장 이벤트 목록 엑셀
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     * @since 2015. 8. 10
     */
    public List<Map<String, Object>> selectSysShopEvtListExcel(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception;


    /**
     * 브랜드 매장 휴일 조회
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     */
    public Page<SysShopHoldyResult> selectSysShopHoldyList(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception ;

    /**
     * 브랜드 매장 휴일  조회 엑셀
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> selectSysShopHoldyListExcel(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception ;

    /**
     * 상담 휴일 조회
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     */
    public Page<SysShopHoldyResult> selectCounselHoldyList(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception ;

    /**
     * 상담 휴일  조회 엑셀
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> selectCounselHoldyListExcel(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception ;

    /**
     * 브랜드 매장 휴일 상세조회
     * @param sysShopHoldy
     * @return
     * @throws Exception
     */
    public SysShopHoldyResult getSysShopHoldyDay(SysShopHoldy sysShopHoldy) throws Exception ;

    /**
     * 브랜드 매장 휴일 요일별 상세조회
     * @param sysShopHoldyDow
     * @return
     * @throws Exception
     */
    public List<SysShopHoldyResult> getSysShopHoldyWeek(SysShopHoldyDow sysShopHoldyDow) throws Exception ;

    /**
     * ERP 브랜드 ID
     * @param shopId
     * @return
     * @throws Exception
     */
    public String selectSysShopErpBrandId(String shopId) throws Exception ;

}