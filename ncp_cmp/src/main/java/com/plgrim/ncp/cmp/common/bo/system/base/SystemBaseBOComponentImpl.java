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
package com.plgrim.ncp.cmp.common.bo.system.base;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sys.*;
import com.plgrim.ncp.cmp.common.bo.system.SystemBaseBOComponent;
import com.plgrim.ncp.commons.data.*;
import com.plgrim.ncp.commons.result.*;
import com.plgrim.ncp.commons.service.*;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.data.SystemPK;

@Transactional(value = "transactionManager")
@Component
public class SystemBaseBOComponentImpl extends AbstractComponent implements SystemBaseBOComponent {

    @Autowired
    SysBrandService sysBrandService;

    @Autowired
    SysExcpCodeService sysExcpCodeService;

    @Autowired
    SysPrudService sysPrudService;

    @Autowired
    SysPrudWtService sysPrudWtService;

    @Autowired
    SysCodeService sysCodeService;

    @Autowired
    SysErpSaleShopService sysErpSaleShopService;

    @Autowired
    SysShopMgrService sysShopMgrService; // 매장관리 Service


    /**
     * 시스템 브랜드 등록
     *
     * @param paramData
     * @throws Exception
     */
    public void insertSysBrnd(FormSysBrndDTO paramData) throws Exception {
        sysBrandService.insertSysBrnd(paramData.getSysBrnd());
    }

    /**
     * 시스템 브랜드 등록
     *
     * @param paramList
     * @throws Exception
     */
    public void insertSysBrndImg(List<SysBrndImg> paramList) throws Exception {
        sysBrandService.insertSysBrndImg(paramList);

    }


    /**
     * 시스템 브랜드 수정
     *
     * @param paramData
     * @throws Exception
     */
    public void updateSysBrnd(FormSysBrndDTO paramData) throws Exception {
        sysBrandService.updateSysBrnd(paramData.getSysBrnd());
    }

    /**
     * 시스템 브랜드 이미지 수정
     *
     * @param paramList
     * @throws Exception
     */
    public void updateSysBrndImg(List<SysBrndImg> paramList) throws Exception {
        sysBrandService.updateSysBrndImg(paramList);
    }

    /**
     * 시스템 브랜드 이미지 삭제
     *
     * @param paramList
     * @throws Exception
     */
    public void deleteSysBrndImg(List<SysBrndImg> paramList) throws Exception {
        sysBrandService.deleteSysBrndImg(paramList);
    }

    /**
     * 일괄 시스템 브랜드 정보 수정
     *
     * @param paramList
     * @throws Exception
     */
    public void updateSysBrndList(List<SysBrandDTO> paramList) throws Exception {

        Iterator<SysBrandDTO> iterator = paramList.iterator();

        while (iterator.hasNext()) {
            SysBrandDTO paramData = iterator.next();
            sysBrandService.updateSysBrnd(paramData.getSysBrnd());
        }

    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.common.bo.system.SystemBaseBOComponent#updateSysBrandEmpDcRtList(java.util.List)
     */
    public void updateSysBrandEmpDcRtList(List<SysBrandDTO> list) throws Exception {

        Iterator<SysBrandDTO> iterator = list.iterator();

        while (iterator.hasNext()) {
            SysBrandDTO paramData = iterator.next();
            SysBrnd sysBrnd = paramData.getSysBrnd();

            BigDecimal empPrcDcRt = sysBrnd.getEmpPrcDcRt();
            BigDecimal curEmpPrcDcRt = sysBrandService.selectEmpPrcDcRt(sysBrnd);
            if (!empPrcDcRt.equals(curEmpPrcDcRt)) {
                //시스템 브랜드 임직원할인율 update
                sysBrandService.updateSysBrndEmpDcRt(paramData.getSysBrnd());

                //상품의 임직원가 update
            }

        }

    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.commons.SystemBaseBOComponent#updateSysBrandPntAccmlList(java.util.List)
     */
    @Override
    public void updateSysBrandPntAccmlList(List<SysBrandDTO> list)
            throws Exception {
        Iterator<SysBrandDTO> iterator = list.iterator();

        while (iterator.hasNext()) {
            SysBrandDTO paramData = iterator.next();
            sysBrandService.updateSysBrndPntAccml(paramData.getSysBrnd());

            //상품정보 update
            sysBrandService.updatePntAccmlGod(paramData.getSysBrnd());
        }

    }

    /**
     * 포인트종류별 적립률 수정
     *
     * @param list
     * @throws Exception
     */
    @Override
    public void updateSysBrandPntAccmlListByType(List<SysBrandDTO> list)
            throws Exception {
        Iterator<SysBrandDTO> iterator = list.iterator();

        while (iterator.hasNext()) {
            SysBrandDTO paramData = iterator.next();
            sysBrandService.updateSysBrndPntAccmlByType(paramData.getSysBrnd());

            //상품정보 update
            sysBrandService.updatePntAccmlGodByType(paramData.getSysBrnd());
        }
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.commons.SystemBaseBOComponent#selectSysBrandPntAccmlList(com.plgrim.ncp.commons.data.SysBrandDTO)
     */
    @Override
    public List<SysBrandResult> selectSysBrandPntAccmlList(
            SysBrandDTO sysBrandDTO) throws Exception {
        List<SysBrandResult> list = sysBrandService.selectSysBrandPntAccmlList(sysBrandDTO);

        for (SysBrandResult result : list) {
            result.getSysBrnd().setRegDtStr(DateService.parseString(result.getSysBrnd().getRegDt(), "yyyy-MM-dd"));
            result.getSysBrnd().setUdtDtStr(DateService.parseString(result.getSysBrnd().getUdtDt(), "yyyy-MM-dd"));
        }

        return list;
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.commons.SystemBaseBOComponent#selectSysBrandPntAccmlTotalCount(com.plgrim.ncp.commons.data.SysBrandDTO)
     */
    @Override
    public int selectSysBrandPntAccmlTotalCount(SysBrandDTO sysBrandDTO)
            throws Exception {
        return sysBrandService.selectSysBrandPntAccmlTotalCount(sysBrandDTO);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.commons.SystemBaseBOComponent#selectSysBrandPntAccmlListByAll(com.plgrim.ncp.commons.data.SysBrandDTO)
     */
    @Override
    public List<Map<String, Object>> selectSysBrandPntAccmlListByAll(
            SysBrandDTO sysBrandDTO) throws Exception {
        return sysBrandService.selectSysBrandPntAccmlListByAll(sysBrandDTO);
    }

    /**
     * 브랜드코드관리 > 브랜드목록 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<SysBrandResult> selectListSysBrnd(FormSysBrndDTO paramData) throws Exception {
        return sysBrandService.selectListSysBrnd(paramData);
    }

    /**
     * 브랜드코드관리 > 자식 브랜드 목록 조회
     *
     * @param paramData (selBrndId 필수)
     * @return
     * @throws Exception
     */
    public List<SysBrandResult> selectListChildSysBrnd(FormSysBrndDTO paramData) throws Exception {
        return sysBrandService.selectListChildSysBrnd(paramData);
    }

    /**
     * 브랜드코드관리 > 자식 브랜드 정보 조회
     *
     * @param paramData (selBrndId 필수)
     * @return
     * @throws Exception
     */
    public SysBrandResult selectRowSysBrnd(FormSysBrndDTO paramData) throws Exception {
        return sysBrandService.selectRowSysBrnd(paramData);
    }


    /**
     * 브랜드코드관리 > 브랜드코드 사용 횟수 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public boolean isUseBrndId(FormSysBrndDTO paramData) throws Exception {
        return (sysBrandService.checkUseCntFromBrndId(paramData) > 0) ? false : true;
    }

    /**
     * 인터페이스 브랜드ID 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<ErpBrndIdResult> selectListErpBrndId(FormSysBrndDTO paramData) throws Exception {
        return sysBrandService.selectListErpBrndId(paramData);
    }

    /**
     * 인터페이스 브랜드 tag 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<BrndTagCdResult> selectListBrndTagCd(FormSysBrndDTO paramData, SystemPK systemPk) throws Exception {
        return sysBrandService.selectListBrndTagCd(paramData, systemPk);
    }

    /**
     * beaker brand 체크 ( 1이면 beaker 브랜드 그룹 브랜드)
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public int checkBeakerBrndCount(FormSysBrndDTO paramData) throws Exception {
        return sysBrandService.checkBeakerBrndCount(paramData);
    }

    /**
     * 공통그룹코드 수정처리
     */
    public void updateSysGrpCd(List<SysCdDTO> paramList) throws Exception {

        Iterator<SysCdDTO> iterator = paramList.iterator();

        while (iterator.hasNext()) {
            SysCdDTO rowData = iterator.next();
            sysCodeService.updateSysGrpCd(rowData.getSysGrpCd());
        }

    }

    /**
     * 공통그룹코드 등록처리
     *
     * @param paramList
     * @throws Exception
     */
    public void insertSysGrpCd(List<SysCdDTO> paramList) throws Exception {

        Iterator<SysCdDTO> iterator = paramList.iterator();

        while (iterator.hasNext()) {
            SysCdDTO rowData = iterator.next();
            sysCodeService.insertSysGrpCd(rowData.getSysGrpCd());
        }
    }

    /**
     * 공통그룹 상세코드 수정처리
     *
     * @param paramList
     * @throws Exception
     */
    public void updateSysGrpCdDetail(List<SysCdDTO> paramList) throws Exception {

        Iterator<SysCdDTO> iterator = paramList.iterator();

        while (iterator.hasNext()) {
            SysCdDTO rowData = iterator.next();
            sysCodeService.updateSysGrpCdDetail(rowData.getSysGrpCdCdCnnc());
        }

    }

    /**
     * 공통그룹 상세코드 등록처리
     *
     * @param paramList
     * @throws Exception
     */
    public void insertSysGrpCdDetail(String grpCd, List<SysCdDTO> paramList) throws Exception {

        Iterator<SysCdDTO> iterator = paramList.iterator();

        while (iterator.hasNext()) {
            SysCdDTO rowData = iterator.next();
            rowData.getSysGrpCdCdCnnc().setGrpCd(grpCd);
            sysCodeService.insertSysGrpCdDetail(rowData.getSysGrpCdCdCnnc());
        }
    }

    /**
     * 공통코드그룹 목록 조회
     *
     * @param
     * @return
     */
    public List<SysCodeResult> selectListCdGrp(FormSysCodeDTO paramData) throws Exception {

        return sysCodeService.selectListCdGrp(paramData);
    }

    /**
     * 공통코드그룹 상세코드목록 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<SysCodeResult> selectListCdGrpDetail(FormSysCodeDTO paramData) throws Exception {
        return sysCodeService.selectListCdGrpDetail(paramData);
    }

    /**
     * 사용하고 있는 코드인지 체크한다.
     *
     * @param cd
     * @return true:사용
     * @throws Exception
     */
    public boolean isUseFromSysCd(String cd) throws Exception {

        return sysCodeService.isUseFromSysCd(cd);
    }

    @Override
    public List<SysExcpCdExtend> selectAllSysExcpGrpCd(SysExcpCdExtend sysExcpCdExtend) throws Exception {
        return sysExcpCodeService.selectAllSysExcpGrpCd(sysExcpCdExtend);
    }

    @Override
    public void updatePrudCd(List<SysPrudDTO> paramList) throws Exception {
        sysPrudService.mergePrudCd(paramList);
    }

    /**
     * 품목코드 목록 조회
     */
    public List<SysPrudResult> selectSysPrudCdList(SystemPK systemPK, FormSysPrudDTO paramData) throws Exception {

        return sysPrudService.selectSysPrudList(paramData);
    }

    /**
     * 품목코드 총 횟수 조회
     */
    public long selectCountSysPrudCd(SystemPK systemPK, FormSysPrudDTO paramData) throws Exception {
        return sysPrudService.selectCountSysPrudCd(paramData);
    }

    /**
     * 품목코드 목록 엑셀다운로드
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> selectSysPrudCdListExcel(FormSysPrudDTO paramData) throws Exception {
        return sysPrudService.selectSysPrudCdListExcel(paramData);
    }


    @Override
    public void updatePrudWtCd(List<SysPrudWtDTO> paramList) throws Exception {
        sysPrudWtService.updatePrudWtCd(paramList);
    }

    @Override
    public int insertPrudWtCd(SysPrudWtDTO paramData) throws Exception {
        int ret = 0;
        ret = sysPrudWtService.insertPrudWtCd(paramData);
        return ret;
    }

    /**
     * 품목코드 목록 조회
     */
    @Override
    public List<SysPrudWtResult> selectSysPrudWtCdList(SystemPK systemPK, SysPrudWtDTO paramData) throws Exception {

        return sysPrudWtService.selectSysPrudWtList(paramData);
    }

    /**
     * 품목코드 총 횟수 조회
     */
    @Override
    public long selectCountSysPrudWtCd(SystemPK systemPK, SysPrudWtDTO paramData) throws Exception {
        return sysPrudWtService.selectCountSysPrudWtCd(paramData);
    }

    /**
     * 품목코드 목록 엑셀다운로드
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, String>> selectSysPrudWtCdListExcel(FormSysPrudDTO paramData) throws Exception {
        return sysPrudWtService.selectSysPrudWtCdListExcel(paramData);
    }

    /**
     * 품목코드 등록 페이지 품목 select box
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<SysPrudResult> selectSysPrdlstCd() throws Exception {
        return sysPrudWtService.selectSysPrdlstCd();
    }

    @Override
    public List<SysErpSaleShop> selectSysErpSaleShopList(SysErpSaleShop sysErpSaleShop) throws Exception {
        return sysErpSaleShopService.selectSysErpSaleShopList(sysErpSaleShop);
    }

    /**
     * 매장 등록.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void insertSysShop( SystemPK systemPK, SysShopDTO paramData) throws Exception {
        sysShopMgrService.insertSysShop(paramData);
    }

    /**
     * 매장 수정.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void updateSysShop( SystemPK systemPK, SysShopDTO paramData) throws Exception {
        sysShopMgrService.updateSysShop(paramData);
    }

    /**
     * 매장 이미지관리 페이지 수정.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 8. 10
     */
    public void updateSysShopImgPage(SystemPK systemPK, SysShopDTO paramData)
            throws Exception {
        sysShopMgrService.updateSysShopImgPage(paramData);
    }

    /**
     * 매장 그리드 수정.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void updateSysShopGrid(List<SysShopDataDTO> paramData) throws Exception {
        sysShopMgrService.updateSysShopGrid(paramData);
    }

    /**
     * 매장 이미지 수정
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 8. 8
     */
    public void updateSysShopImg( SystemPK systemPK, SysShopDTO paramData) throws Exception {
        sysShopMgrService.updateSysShopImg(paramData);
    }


    /**
     * 매장 이벤트 등록
     * @param sysShopEvt
     * @return
     * @throws Exception
     */
    @Override
    public String insertSysShopEvt(SysShopEvt sysShopEvt)throws Exception {
        return sysShopMgrService.insertSysShopEvt(sysShopEvt);
    }

    /**
     * 매장 이벤트 수정
     * @param sysShopEvt
     * @return
     * @throws Exception
     */
    @Override
    public String updateSysShopEvt(SysShopEvt sysShopEvt)throws Exception {
        return sysShopMgrService.updateSysShopEvt(sysShopEvt);
    }

    /**
     * 브랜드 매장 휴일 일자별 등록
     * @param sysShopHoldy
     * @return
     * @throws Exception
     */
    @Override
    public String insertSysShopHoldyDay(SysShopHoldy sysShopHoldy) throws Exception {
        return sysShopMgrService.insertSysShopHoldyDay(sysShopHoldy);
    }

    /**
     * 브랜드 매장 휴일 일자별 수정
     * @param sysShopHoldyDTO
     * @return
     * @throws Exception
     */
    @Override
    public String updateSysShopHoldyDay(SysShopHoldyDTO sysShopHoldyDTO) throws Exception {
        return sysShopMgrService.updateSysShopHoldyDay(sysShopHoldyDTO);
    }

    /**
     * 브랜드 매장 휴일 일자별 삭제
     * @param sysShopHoldy
     * @return
     * @throws Exception
     */
    @Override
    public int deleteSysShopHoldyDay(SysShopHoldy sysShopHoldy) throws Exception {
        return sysShopMgrService.deleteSysShopHoldyDay(sysShopHoldy);
    }

    /**
     * 브랜드 매장 휴일 요일별 등록
     * @param sysShopHoldyDTO
     * @return
     * @throws Exception
     */
    @Override
    public void insertSysShopHoldyWeek(SysShopHoldyDTO sysShopHoldyDTO) throws Exception {
        sysShopMgrService.insertSysShopHoldyWeek(sysShopHoldyDTO);
    }


    /**
     * 매장 목록 조회.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public List<SysShopResult> selectSysShopList( SystemPK systemPK, SysShopDTO paramData) throws Exception {
        return sysShopMgrService.selectSysShopList(paramData);
    }

    /**
     * 매장 목록 조회 개수.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @return Long [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public long selectSysShopListCount( SystemPK systemPK, SysShopDTO paramData) throws Exception {
        return sysShopMgrService.selectSysShopListCount(paramData);
    }

    /**
     * 매장 목록 조회 엑셀.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public List<Map<String, Object>> selectSysShopListExcel( SystemPK systemPK, SysShopDTO paramData) throws Exception {
        return sysShopMgrService.selectSysShopListExcel(paramData);
    }

    /**
     * 매장 상세 조회.
     *
     * @param systemPK [설명]
     * @param paramData [설명]
     * @return Sys cmmn noti result [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public SysShopResult selectSysShopDetail( SystemPK systemPK, SysShopDTO paramData) throws Exception {
        return sysShopMgrService.selectSysShopDetail(paramData);
    }

    /**
     * 브랜드 매장 이벤트 상세 조회
     * @param sysShopEvt
     * @return
     * @throws Exception
     */
    @Override
    public SysShopEvtResult selectSysShopEvt(SysShopEvt sysShopEvt)throws Exception{
        return sysShopMgrService.selectSysShopEvt(sysShopEvt);
    }

    /**
     * 브랜드 매장 이벤트 조회
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     */
    @Override
    public Page<SysShopResult> selectSysShopEvtList(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception {
        return sysShopMgrService.selectSysShopEvtList(sysShopDTO);
    }

    /**
     * 브랜드 매장 이벤트 조회 엑셀
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> selectSysShopEvtListExcel(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception{
        return sysShopMgrService.selectSysShopEvtListExcel(sysShopDTO);
    }

    /**
     * 브랜드 매장 휴일 조회
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     */
    @Override
    public Page<SysShopHoldyResult> selectSysShopHoldyList(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception {
        return sysShopMgrService.selectSysShopHoldyList(sysShopDTO);
    }

    /**
     * 브랜드 매장 휴일  조회 엑셀
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> selectSysShopHoldyListExcel(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception{
        return sysShopMgrService.selectSysShopHoldyListExcel(sysShopDTO);
    }

    /**
     * 상담 휴일 조회
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     */
    @Override
    public Page<SysShopHoldyResult> selectCounselHoldyList(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception {
        return sysShopMgrService.selectCounselHoldyList(sysShopDTO);
    }

    /**
     * 상담 휴일  조회 엑셀
     * @param systemPK
     * @param sysShopDTO
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> selectCounselHoldyListExcel(SystemPK systemPK, SysShopDTO sysShopDTO) throws Exception{
        return sysShopMgrService.selectCounselHoldyListExcel(sysShopDTO);
    }

    /**
     * 브랜드 매장 휴일 상세조회
     * @param sysShopHoldy
     * @return
     * @throws Exception
     */
    @Override
    public SysShopHoldyResult getSysShopHoldyDay(SysShopHoldy sysShopHoldy) throws Exception {
        return sysShopMgrService.selectSysShopHoldyDay(sysShopHoldy);
    }

    /**
     * 브랜드 매장 휴일 요일별 상세조회
     * @param sysShopHoldyDow
     * @return
     * @throws Exception
     */
    @Override
    public List<SysShopHoldyResult> getSysShopHoldyWeek(SysShopHoldyDow sysShopHoldyDow) throws Exception {
        return sysShopMgrService.selectSysShopHoldyWeek(sysShopHoldyDow);
    }

	@Override
	public String selectSysShopErpBrandId(String shopId) throws Exception { 
		return sysShopMgrService.selectSysShopErpBrandId(shopId);
	}

}
