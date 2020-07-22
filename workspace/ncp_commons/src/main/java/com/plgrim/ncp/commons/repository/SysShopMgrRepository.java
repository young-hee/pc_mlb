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
 * @since       2015. 6. 3       
 */
package com.plgrim.ncp.commons.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopEvt;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopHoldy;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopHoldyDow;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopPhoto;
import com.plgrim.ncp.commons.data.SysShopDTO;
import com.plgrim.ncp.commons.data.SysShopHoldyDTO;
import com.plgrim.ncp.commons.result.SysShopEvtResult;
import com.plgrim.ncp.commons.result.SysShopHoldyResult;
import com.plgrim.ncp.commons.result.SysShopResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 매장관리 Repository
 * @author ed
 *
 */
@Slf4j
@Repository
public class SysShopMgrRepository extends AbstractRepository {
	
	/**
	 * 매장 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysShopResult> selectSysShopList( SysShopDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.sysshop.selectSysShopList", paramData);
	}
	
	/**
	 * 매장 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysShopListCount( SysShopDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysshop.selectSysShopListCount", paramData);
    }
	
	/**
	 * 매장 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysShopListExcel( SysShopDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.sysshop.selectSysShopListExcel", paramData);
	}
	
	/**
	 * 매장 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysShopResult selectSysShopDetail( SysShopDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysshop.selectSysShopDetail", paramData);
	}
	
	/**
	 * 매장 등록.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int insertSysShop(SysShop paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.base.insertSysShop", paramData);
	}
	
	/**
	 * 매장 수정.
	 *
	 * @param paramData [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public int updateSysShop(SysShop paramData) throws Exception {
		return getSession1().delete("com.plgrim.ncp.commons.sysshop.updateSysShop", paramData);
	}
	
	/**
	 * 매장 이미지 관리 페이지 수정.
	 *
	 * @param paramData [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 10
	 */
	public int updateSysShopImgPage(SysShop paramData) throws Exception {
		return getSession1().delete("com.plgrim.ncp.commons.sysshop.updateSysShopImgPage", paramData);
	}
	
	/**
	 * 매장 그리드 수정.
	 *
	 * @param paramData [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public int updateSysShopGrid(SysShop paramData) throws Exception {
		return getSession1().delete("com.plgrim.ncp.commons.sysshop.updateSysShopGrid", paramData);
	}
	
	/**
	 * 매장 중복 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysShopDupCount( SysShop paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysshop.selectSysShopDupCount", paramData);
    }
	
	/**
	 * 매장 이미지 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result
	 * @throws Exception the exception
	 * @since 2015. 8. 10
	 */
	public List<SysShopPhoto> selectSysShopPhotoDetail(SysShopPhoto paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.sysshop.commSelectSysShopPhoto", paramData);
	}
	
	
	/**
	 * 매장 이미지 등록
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 8
	 */
	public int insertSysShopPhoto(SysShopPhoto sysShopPhoto){
		return getSession1().insert("com.plgrim.ncp.commons.sysshop.commInsertSysShopPhoto", sysShopPhoto);
	}
	
	/**
	 * 매장 이미지 수정
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 8
	 */
	public int updateSysShopPhoto(SysShopPhoto sysShopPhoto) {
		return getSession1().update("com.plgrim.ncp.commons.sysshop.commUpdateSysShopPhoto", sysShopPhoto);
	}
	
	/**
	 * 브랜드 매장 이벤트 상세조회
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public SysShopEvtResult selectSysShopEvt(SysShopEvt sysShopEvt) {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysshop.selectSysShopEvt", sysShopEvt);
	}
	
	/**
	 * 브랜드 매장 이벤트 수정
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public int updateSysShopEvt(SysShopEvt sysShopEvt) {
		return getSession1().update("com.plgrim.ncp.commons.sysshop.updateSysShopEvt", sysShopEvt);
	}
	
	/**
	 * 브랜드 매장 이벤트 등록
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public String insertSysShopEvt(SysShopEvt sysShopEvt){
		getSession1().insert("com.plgrim.ncp.commons.sysshop.insertSysShopEvt", sysShopEvt);
		return Long.toString(sysShopEvt.getShopEvtSn());
	}
	
	/**
	 * 브랜드 매장 이벤트 체크
	 * @param vendorBrndSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public int selectSysShopEvtCheck(SysShopEvt sysShopEvt) {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysshop.selectSysShopEvtCheck", sysShopEvt);
	}
	
	/**
	 * 브랜드 매장 이벤트 목록 조회 건수
	 * @param vendorBrndSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectSysShopEvtListCount(SysShopDTO sysShopDTO) {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysshop.selectSysShopEvtListCount", sysShopDTO);
	}
	
	/**
	 * 브랜드 매장 이벤트 목록 조회
	 * @param vendorBrndSearchDTO
	 * @return List<VendorBrndListResult>
	 * @throws Exception
	 */
	public List<SysShopResult> selectSysShopEvtList(SysShopDTO sysShopDTO) {
		return getSession1().selectList("com.plgrim.ncp.commons.sysshop.selectSysShopEvtList", sysShopDTO);
	}
	
	/**
	 * 브랜드 매장 이벤트 목록 엑셀
	 * @param vendorBrndSearchDTO
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSysShopEvtListExcel(SysShopDTO sysShopDTO) {
		return getSession1().selectList("com.plgrim.ncp.commons.sysshop.selectSysShopEvtListExcel", sysShopDTO);
	}
	
	// TODO 휴일 목록 조회
	/**
	 * 브랜드 매장 휴일 목록 조회 건수
	 * @param vendorBrndSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectSysShopHoldyListCount(SysShopDTO sysShopDTO) {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysshop.selectSysShopHoldyListCount", sysShopDTO);
	}
	
	/**
	 * 브랜드 매장 휴일 목록 조회
	 * @param vendorBrndSearchDTO
	 * @return List<VendorBrndListResult>
	 * @throws Exception
	 */
	public List<SysShopHoldyResult> selectSysShopHoldyList(SysShopDTO sysShopDTO) {
		return getSession1().selectList("com.plgrim.ncp.commons.sysshop.selectSysShopHoldyList", sysShopDTO);
	}
	
	/**
	 * 브랜드 매장 휴일 목록 엑셀
	 * @param vendorBrndSearchDTO
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSysShopHoldyListExcel(SysShopDTO sysShopDTO) {
		return getSession1().selectList("com.plgrim.ncp.commons.sysshop.selectSysShopHoldyListExcel", sysShopDTO);
	}
	
	/**
	 * 상담 휴일 목록 조회 건수
	 * @param sysShopDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectCounselHoldyListCount(SysShopDTO sysShopDTO) {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysshop.selectCounselHoldyListCount", sysShopDTO);
	}
	
	/**
	 * 상담 휴일 목록 조회
	 * @param sysShopDTO
	 * @return List<SysShopHoldyResult>
	 * @throws Exception
	 */
	public List<SysShopHoldyResult> selectCounselHoldyList(SysShopDTO sysShopDTO) {
		return getSession1().selectList("com.plgrim.ncp.commons.sysshop.selectCounselHoldyList", sysShopDTO);
	}
	
	/**
	 * 상담 휴일 목록 엑셀
	 * @param sysShopDTO
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectCounselHoldyListExcel(SysShopDTO sysShopDTO) {
		return getSession1().selectList("com.plgrim.ncp.commons.sysshop.selectCounselHoldyListExcel", sysShopDTO);
	}
	
	/**
	 * 브랜드 매장 휴일 등록 체크
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public int selectSysShopHoldyDayCheck(SysShopHoldy sysShopHoldy) {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysshop.selectSysShopHoldyDayCheck", sysShopHoldy);
	}
	
	/**
	 * 브랜드 매장 휴일 상세조회
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public SysShopHoldyResult selectSysShopHoldyDay(SysShopHoldy sysShopHoldy) {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysshop.selectSysShopHoldyDay", sysShopHoldy);
	}
	
	/**
	 * 브랜드 매장 휴일 일자별 등록
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public int insertSysShopHoldyDay(SysShopHoldy sysShopHoldy) {
		return getSession1().insert("com.plgrim.ncp.commons.sysshop.insertSysShopHoldyDay", sysShopHoldy);
	}
	
	/**
	 * 브랜드 매장 휴일 일자별 수정
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public int updateSysShopHoldyDay(SysShopHoldyDTO sysShopHoldyDTO) {
		return getSession1().update("com.plgrim.ncp.commons.sysshop.updateSysShopHoldyDay", sysShopHoldyDTO);
	}
	
	/**
	 * 브랜드 매장 휴일 일자별 삭제
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public int deleteSysShopHoldyDay(SysShopHoldy sysShopHoldy) {
		return getSession1().delete("com.plgrim.ncp.commons.sysshop.deleteSysShopHoldyDay", sysShopHoldy);
	}
	
	/**
	 * 브랜드 매장 휴일 요일별 상세조회
	 * @param sysShopHoldyDow
	 * @return
	 * @throws Exception
	 */
	public List<SysShopHoldyResult> selectSysShopHoldyWeek(SysShopHoldyDow sysShopHoldyDow) {
		return getSession1().selectList("com.plgrim.ncp.commons.sysshop.selectSysShopHoldyWeek", sysShopHoldyDow);
	}
	
	/**
	 * 브랜드 매장 휴일 요일별 등록
	 * @param sysShopHoldyDow
	 * @return
	 * @throws Exception
	 */
	public int insertSysShopHoldyWeek(SysShopHoldyDow sysShopHoldyDow) {
		return getSession1().insert("com.plgrim.ncp.commons.sysshop.insertSysShopHoldyWeek", sysShopHoldyDow);
	}
	
	/**
	 * 브랜드 매장 휴일 요일별 삭제
	 * @param sysShopHoldyDow
	 * @return
	 * @throws Exception
	 */
	public int deleteSysShopHoldyWeek(SysShopHoldyDow sysShopHoldyDow) {
		return getSession1().delete("com.plgrim.ncp.commons.sysshop.deleteSysShopHoldyWeek", sysShopHoldyDow);
	}
	
	/**
	 * 시스템 매장 '사용여부' 수정 시 플래그 처리
	 * 
	 * @param sysShop
	 * @return
	 */
	public int mergeSysShopModifyFlag(SysShop sysShop) {
		return getSession1().update("com.plgrim.ncp.commons.sysshop.mergeSysShopModifyFlag", sysShop);
	}
	
	/**
	 * 매장 브랜드 ID 조회
	 * 
	 * @param shopId
	 * @return
	 */
	public String selectSysShopErpBrandId(String shopId) {
		return getSession1().selectOne("com.plgrim.ncp.commons.sysshop.selectSysShopErpBrandId", shopId);
	}
	
}
