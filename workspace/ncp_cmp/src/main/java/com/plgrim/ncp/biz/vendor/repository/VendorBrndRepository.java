/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.biz.vendor.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlst;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlstHist;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrndFeeHist;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopEvt;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopHoldy;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopHoldyDow;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopPhoto;
import com.plgrim.ncp.base.enums.SysInfoEnum.OwnBrandEnum;
import com.plgrim.ncp.biz.brand.data.BrandResultDTO;
import com.plgrim.ncp.biz.goods.data.GoodsValidDTO;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.biz.vendor.data.VendorBrndHoldyDTO;
import com.plgrim.ncp.biz.vendor.data.VendorBrndSearchDTO;
import com.plgrim.ncp.biz.vendor.result.VendorBrndEvtResult;
import com.plgrim.ncp.biz.vendor.result.VendorBrndHoldyResult;
import com.plgrim.ncp.biz.vendor.result.VendorBrndListResult;

/**
 * @author Mark
 *
 */
@Repository
public class VendorBrndRepository extends AbstractRepository {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 브랜드 매장내역 조회 건수
	 * @param vendorBrndSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectVendorBrndListCount(VendorBrndSearchDTO vendorBrndSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectBrndShopCount", vendorBrndSearchDTO);
	}
	
	/**
	 * 브랜드 매장내역 조회
	 * @param vendorBrndSearchDTO
	 * @return List<VendorBrndListResult>
	 * @throws Exception
	 */
	public List<VendorBrndListResult> selectVendorBrndList(VendorBrndSearchDTO vendorBrndSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectBrndShop", vendorBrndSearchDTO);
	}
	
	/**
	 * 브랜드 목록 조회(콤보박스)
	 * @return List<SysBrnd>
	 * @throws Exception
	 */
	public List<SysBrnd> selectSysBrndCombo() {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectSysBrndCombo");
	}
	
	/**
	 * 브랜드 매장내역 엑셀
	 * @param vendorBrndSearchDTO
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectVendorBrndListExcel(VendorBrndSearchDTO vendorBrndSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectBrndShopExcel", vendorBrndSearchDTO);
	}
	
	/**
	 * 브랜드 매장내역 리스트 수정
	 * @param sysShopBrnd
	 * @return int
	 * @throws Exception
	 */
	public int updateSysShopBrndList(SysShopBrnd sysShopBrnd) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.brnd.updateSysShopBrndList", sysShopBrnd);
	}
	
	/**
	 * 브랜드 매장 이력 수정
	 * @param sysShopBrnd
	 * @return int
	 * @throws Exception
	 */
	public int updateSysShopBrndFeeHist(SysShopBrndFeeHist sysShopBrndFeeHist) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.brnd.updateSysShopBrndFeeHist", sysShopBrndFeeHist);
	}
	
	/**
	 * 브랜드 매장 이력 등록
	 * @param sysShopBrnd
	 * @return int
	 * @throws Exception
	 */
	public int insertSysShopBrndFeeHist(SysShopBrndFeeHist sysShopBrndFeeHist) {
		return getSession1().insert("com.plgrim.ncp.biz.vendor.brnd.insertSysShopBrndFeeHist", sysShopBrndFeeHist);
	}	
	
	/**
	 * 브랜드 매장 등록
	 * @param sysShopBrnd
	 * @return int
	 * @throws Exception
	 */
	public int insertSysShopBrnd(SysShopBrnd sysShopBrnd) {
		return getSession1().insert("com.plgrim.ncp.biz.vendor.brnd.insertSysShopBrnd", sysShopBrnd);
	}
	
	/**
	 * 브랜드 매장 이미지 등록
	 * @param sysShopPhoto
	 * @return int
	 * @throws Exception
	 */
	public int insertSysShopPhoto(SysShopPhoto sysShopPhoto){
		return getSession1().insert("com.plgrim.ncp.biz.vendor.brnd.insertSysShopPhoto", sysShopPhoto);
	}
	
	/**
	 * 브랜드 매장 상세조회
	 * @param sysShopBrnd
	 * @return
	 * @throws Exception
	 */
	public VendorBrndListResult selectSysShopBrand(SysShopBrnd sysShopBrnd){
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectSysShopBrand", sysShopBrnd);
	}
	
	/**
	 * 브랜드 매장 이미지 조회
	 * @param sysShopPhoto
	 * @return
	 * @throws Exception
	 */
	public List<SysShopPhoto> selectSysShopPhoto(SysShopPhoto sysShopPhoto){
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectSysShopPhoto", sysShopPhoto);
	}
	
	/**
	 * 브랜드 매장 수정
	 * @param sysShopBrnd
	 * @return
	 * @throws Exception
	 */
	public int updateSysShopBrnd(SysShopBrnd sysShopBrnd) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.brnd.updateSysShopBrnd", sysShopBrnd);
	}
	
	/**
	 * 브랜드 매장 이미지 수정
	 * @param sysShopPhoto
	 * @return
	 * @throws Exception
	 */
	public int updateSysShopPhoto(SysShopPhoto sysShopPhoto) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.brnd.updateSysShopPhoto", sysShopPhoto);
	}
	
	/**
	 * 브랜드 매장 이벤트 상세조회
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public VendorBrndEvtResult selectSysShopEvt(SysShopEvt sysShopEvt) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectSysShopEvt", sysShopEvt);
	}
	
	/**
	 * 브랜드 매장 이벤트 수정
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public int updateSysShopEvt(SysShopEvt sysShopEvt) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.brnd.updateSysShopEvt", sysShopEvt);
	}
	
	/**
	 * 브랜드 매장 이벤트 등록
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public String insertSysShopEvt(SysShopEvt sysShopEvt){
		getSession1().insert("com.plgrim.ncp.biz.vendor.brnd.insertSysShopEvt", sysShopEvt);
		return Long.toString(sysShopEvt.getShopEvtSn());
	}
	
	/**
	 * 브랜드 매장 이벤트 체크
	 * @param vendorBrndSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public int selectSysShopEvtCheck(SysShopEvt sysShopEvt) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectSysShopEvtCheck", sysShopEvt);
	}
	
	/**
	 * 브랜드 매장 이벤트 목록 조회 건수
	 * @param vendorBrndSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectSysShopEvtListCount(VendorBrndSearchDTO vendorBrndSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectSysShopEvtListCount", vendorBrndSearchDTO);
	}
	
	/**
	 * 브랜드 매장 이벤트 목록 조회
	 * @param vendorBrndSearchDTO
	 * @return List<VendorBrndListResult>
	 * @throws Exception
	 */
	public List<VendorBrndListResult> selectSysShopEvtList(VendorBrndSearchDTO vendorBrndSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectSysShopEvtList", vendorBrndSearchDTO);
	}
	
	/**
	 * 브랜드 매장 이벤트 목록 엑셀
	 * @param vendorBrndSearchDTO
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSysShopEvtListExcel(VendorBrndSearchDTO vendorBrndSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectSysShopEvtListExcel", vendorBrndSearchDTO);
	}
	
	/**
	 * 브랜드 매장 휴일 목록 조회 건수
	 * @param vendorBrndSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectSysShopHoldyListCount(VendorBrndSearchDTO vendorBrndSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectSysShopHoldyListCount", vendorBrndSearchDTO);
	}
	
	/**
	 * 브랜드 매장 휴일 목록 조회
	 * @param vendorBrndSearchDTO
	 * @return List<VendorBrndListResult>
	 * @throws Exception
	 */
	public List<VendorBrndHoldyResult> selectSysShopHoldyList(VendorBrndSearchDTO vendorBrndSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectSysShopHoldyList", vendorBrndSearchDTO);
	}
	
	/**
	 * 브랜드 매장 휴일 목록 엑셀
	 * @param vendorBrndSearchDTO
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSysShopHoldyListExcel(VendorBrndSearchDTO vendorBrndSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectSysShopHoldyListExcel", vendorBrndSearchDTO);
	}
	
	/**
	 * 브랜드 매장 휴일 등록 체크
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public int selectSysShopHoldyDayCheck(SysShopHoldy sysShopHoldy) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectSysShopHoldyDayCheck", sysShopHoldy);
	}
	
	/**
	 * 브랜드 매장 휴일 상세조회
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public VendorBrndHoldyResult selectSysShopHoldyDay(SysShopHoldy sysShopHoldy) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectSysShopHoldyDay", sysShopHoldy);
	}
	
	/**
	 * 브랜드 매장 휴일 일자별 등록
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public int insertSysShopHoldyDay(SysShopHoldy sysShopHoldy) {
		return getSession1().insert("com.plgrim.ncp.biz.vendor.brnd.insertSysShopHoldyDay", sysShopHoldy);
	}
	
	/**
	 * 브랜드 매장 휴일 일자별 수정
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public int updateSysShopHoldyDay(VendorBrndHoldyDTO vendorBrndHoldyDTO) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.brnd.updateSysShopHoldyDay", vendorBrndHoldyDTO);
	}
	
	/**
	 * 브랜드 매장 휴일 일자별 삭제
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public int deleteSysShopHoldyDay(SysShopHoldy sysShopHoldy) {
		return getSession1().delete("com.plgrim.ncp.biz.vendor.brnd.deleteSysShopHoldyDay", sysShopHoldy);
	}
	
	/**
	 * 브랜드 매장 휴일 요일별 상세조회
	 * @param sysShopHoldyDow
	 * @return
	 * @throws Exception
	 */
	public List<VendorBrndHoldyResult> selectSysShopHoldyWeek(SysShopHoldyDow sysShopHoldyDow) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectSysShopHoldyWeek", sysShopHoldyDow);
	}
	
	/**
	 * 브랜드 매장 휴일 요일별 등록
	 * @param sysShopHoldyDow
	 * @return
	 * @throws Exception
	 */
	public int insertSysShopHoldyWeek(SysShopHoldyDow sysShopHoldyDow) {
		return getSession1().insert("com.plgrim.ncp.biz.vendor.brnd.insertSysShopHoldyWeek", sysShopHoldyDow);
	}
	
	/**
	 * 브랜드 매장 휴일 요일별 삭제
	 * @param sysShopHoldyDow
	 * @return
	 * @throws Exception
	 */
	public int deleteSysShopHoldyWeek(SysShopHoldyDow sysShopHoldyDow) {
		return getSession1().delete("com.plgrim.ncp.biz.vendor.brnd.deleteSysShopHoldyWeek", sysShopHoldyDow);
	}
	
	/**
	 * 브랜드 매장 이벤트 단건조회
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public VendorBrndEvtResult selectOneSysShopEvt(SysShopEvt sysShopEvt) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectOneSysShopEvt", sysShopEvt);
	}
	
	/**
	 * 브랜드 매장 이벤트 리스트 조회
	 * @param shopSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<VendorBrndEvtResult> selectBrndShopEvtList(ShopSearchDTO shopSearchDTO)throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectBrndShopEvtList", shopSearchDTO);
	}
	
	/**
	 *  브랜드명 조회
	 * @param brndIds
	 * @return
	 * @throws Exception
	 */
	public List<BrandResultDTO> selectSysBrndNm(List<String> brndIds)throws Exception{
		HashMap<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("brndIds", brndIds);
		
		String ownBrndIds = getConfigService().getProperty(OwnBrandEnum.OwnBrandId.toString());
		pMap.put("ownBrndIds", ownBrndIds);
		
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectSysBrndNm", pMap);
	}
	
	/**
	 * 브랜드 품목별 수선정보 조회 건수
	 * @param vendorBrndSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectVendorBrndPrdListCount(VendorBrndSearchDTO vendorBrndSearchDTO)throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectBrndPrdCount", vendorBrndSearchDTO);
	}

	/**
	 * 브랜드 품목별 수선정보 조회
	 * @param vendorBrndSearchDTO
	 * @return List<VendorBrndListResult>
	 * @throws Exception
	 */
	public List<VendorBrndListResult> selectVendorBrndPrdList(VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectSysBrndPrdlst", vendorBrndSearchDTO);
	}

	/**
	 * 브랜드 품목별 수선정보 등록
	 * @param vendorBrndSearchDTO
	 * @return List<VendorBrndListResult>
	 * @throws Exception
	 */
	public int insertBrndPrdList(SysBrndPrdlst sysBrndPrdlst) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.vendor.brnd.insertSysBrndPrdlst", sysBrndPrdlst);
	}

	/**
	 * 브랜드 품목별 수선정보 등록시 품목코드 및 브랜드ID 유효성 체크
	 * @param goodsValidDTO
	 * @return List<VendorBrndListResult>
	 * @throws Exception
	 */
	public Map<String,Object> selectBrndPrdKeyValid(GoodsValidDTO goodsValidDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectBrndPrdKeyValid", goodsValidDTO);
	}

	/**
	 * 브랜드별 품목별 수선정보 등록를 위한 품목코드+브랜드ID 조합 존재 여부 유효성 조회
	 * @param goodsValidDTO
	 * @return List<VendorBrndListResult>
	 * @throws Exception
	 */
	public long selectBrndPrdExistCount(GoodsValidDTO goodsValidDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectBrndPrdExistCount", goodsValidDTO);
	}

	/**
	 * 상품수선정보 리스트 수정
	 * @param sysBrndPrdlst
	 * @return int
	 * @throws Exception
	 */
	public int updateSysBrndPrdlst(SysBrndPrdlst sysBrndPrdlst)throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.vendor.brnd.updateSysBrndPrdlst", sysBrndPrdlst);
	}

	/**
	 * 상품수선정보 내역을 위한 조회
	 * @param paramSysBrndPrdlst
	 * @return List<SysBrndPrdlst>
	 * @throws Exception
	 */
	public List<SysBrndPrdlst> selectSysBrndPrdUpdateLst(SysBrndPrdlst paramSysBrndPrdlst) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectSysBrndPrdUpdateLst", paramSysBrndPrdlst);
	}

	/**
	 * 상품수선정보 내역 등록 및 수정
	 * @param paramSysBrndPrdlst
	 * @return List<SysBrndPrdlst>
	 * @throws Exception
	 */
	public int updateSysBrndPrdlstHist(SysBrndPrdlstHist sysBrndPrdlstHist) throws Exception{
		return getSession1().insert("com.plgrim.ncp.biz.vendor.brnd.updateSysBrndPrdlstHist", sysBrndPrdlstHist);
	}

	/**
	 * 브랜드 품목별 수선정보 내역 조회 건수
	 * @param vendorBrndSearchDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectVendorBrndPrdListHistCount(VendorBrndSearchDTO vendorBrndSearchDTO)throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brnd.selectBrndPrdHistCount", vendorBrndSearchDTO);
	}

	/**
	 * 브랜드 품목별 수선정보 내역 조회
	 * @param vendorBrndSearchDTO
	 * @return List<VendorBrndListResult>
	 * @throws Exception
	 */
	public List<VendorBrndListResult> selectVendorBrndPrdListHist(VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brnd.selectSysBrndPrdlstHist", vendorBrndSearchDTO);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
