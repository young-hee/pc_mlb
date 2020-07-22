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
package com.plgrim.ncp.commons.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.sys.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.commons.data.FormSysBrndDTO;
import com.plgrim.ncp.commons.data.SysBrandDTO;
import com.plgrim.ncp.commons.result.BrndTagCdResult;
import com.plgrim.ncp.commons.result.ErpBrndIdResult;
import com.plgrim.ncp.commons.result.SysBrandResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

/**
 * Sys Code Repository.
 *
 * @author Dennis
 */
@Slf4j
@Repository
public class SysBrandRepository extends AbstractRepository { 

	/**
	 * 해당 브랜드의 임직원가 할인율 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @return Big decimal [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 4
	 */
	public BigDecimal selectEmpPrcDcRt(SysBrnd sysBrnd) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.brand.selectEmpPrcDcRt", sysBrnd);
	}
	
	/**
	 * 임직원 할인율 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 8
	 */
	public void updateSysBrndEmpDcRt ( SysBrnd sysBrnd) throws Exception {
		sysBrnd.setRegtrId(BOSecurityUtil.getLoginId());
		getSession1().update("com.plgrim.ncp.commons.brand.updateSysBrndEmpDcRt", sysBrnd);
	}
	
	/**
	 * 브랜드별 포인트적립률 목록 조회
	 * 
	 * @param sysBrandDTO
	 * @return
	 * @throws Exception
	 */
	public List<SysBrandResult> selectSysBrandPntAccmlList(SysBrandDTO sysBrandDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.brand.selectSysBrandPntAccmlList", sysBrandDTO);
	}

	/**
	 * 브랜드별 포인트 적립률 목록 TotalCount
	 * 
	 * @param sysBrandDTO
	 * @return
	 * @throws Exception
	 */
	public int selectSysBrandPntAccmlTotalCount(SysBrandDTO sysBrandDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.brand.selectSysBrandPntAccmlTotalCount", sysBrandDTO);
	}
	
	/**
	 * 브랜드별 포인트적립률 목록 엑셀다운로드.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param sysBrandDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 8
	 */
	public List<Map<String, Object>> selectSysBrandPntAccmlListByAll(SysBrandDTO sysBrandDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.brand.selectSysBrandPntAccmlListByAll", sysBrandDTO);
	}

	/**
	 * 모든 포인트 적립률 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 8
	 */
	public void updateSysBrndPntAccml ( SysBrnd sysBrnd) throws Exception {
		sysBrnd.setRegtrId(BOSecurityUtil.getLoginId());
		getSession1().update("com.plgrim.ncp.commons.brand.updateSysBrndPntAccml", sysBrnd);
	}
	
	/**
	 * 포인트 적립률 수정 시 상품정보 update.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 24
	 */
	public void updatePntAccmlGod ( SysBrnd sysBrnd) throws Exception {
		sysBrnd.setRegtrId(BOSecurityUtil.getLoginId());
		getSession1().update("com.plgrim.ncp.commons.brand.updatePntAccmlGod", sysBrnd);
	}
	
	/**
	 * 브랜드코드관리 > 브랜드목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysBrandResult> selectListSysBrnd(FormSysBrndDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.brand.selectListSysBrnd", paramData);
	}
	
	/**
	 * 브랜드코드관리 > 자식 브랜드 목록 조회
	 * @param paramData (selBrndId 필수)
	 * @return
	 * @throws Exception
	 */
	public List<SysBrandResult> selectListChildSysBrnd(FormSysBrndDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.brand.selectListChildSysBrnd", paramData);
	}
	
	/**
	 * 브랜드코드관리 > 자식 브랜드 정보 조회
	 * @param paramData (selBrndId 필수)
	 * @return
	 * @throws Exception
	 */
	public SysBrandResult selectRowSysBrnd(FormSysBrndDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.brand.selectRowSysBrnd", paramData);
	}


	/**
	 * 브랜드코드관리 > 자식 브랜드 정보 조회
	 * @param brndId (selBrndId 필수)
	 * @return
	 * @throws Exception
	 */
	public List<SysBrndImg> selectRowSysBrndImg(String brndId) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.brand.selectSysBrndImgList", brndId);
	}



	/**
	 * 브랜드코드관리 > 브랜드코드 사용 횟수 조회 
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public int checkUseCntFromBrndId(FormSysBrndDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.brand.checkUseCntFromBrndId", paramData);
	}
	
	
	/**
	 * 브랜드명 다국어 인덱스 ID를 조회한다.
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public String getBrndNmMlangIdxId(String langCd) throws Exception {
		/*
		SysMlangIdx paramData = new SysMlangIdx();
		paramData.setTableNm("SYS_BRND");
		paramData.setColNm("BRND_NM");
		paramData.setLangCd(langCd);
		
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.brand.getMlangIdxId", paramData);
		*/
		return null;				
	}
	
	/**
	 * 인터페이스 브랜드ID 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<ErpBrndIdResult> selectListErpBrndId(FormSysBrndDTO paramData) throws Exception {
		log.info(paramData.toString());
		//return getSession1().selectList("com.plgrim.ncp.commons.brand.selectListErpBrndId", paramData);
		return getSession1().selectList("com.plgrim.ncp.commons.brand.selectListErpBrndIdSplit", paramData); // Split
	}
	
	/**
	 * beaker brand 체크 ( 1이면 beaker 브랜드 그룹 브랜드)
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public int checkBeakerBrndCount(FormSysBrndDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.brand.checkBeakerBrndCount", paramData);
	}
	
	/*################################################################################################
	 * Command
	##################################################################################################*/
	
	/**
	 * 시스템 브랜드 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertSysBrnd( SysBrnd paramData ) throws Exception {
		log.info(paramData.toString());
		getSession1().insert( "com.plgrim.ncp.commons.brand.insertSysBrnd", paramData);
	}
	
	/**
	 * 시스템 브랜드 히스토리 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertSysBrndHist ( SysBrnd paramData) throws Exception {
		
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().insert("com.plgrim.ncp.commons.brand.insertSysBrndHist", paramData);
	}

	/**
	 * 시스템 브랜드 수정
	 * @param paramData
	 * @throws Exception
	 */
	public void updateSysBrnd ( SysBrnd paramData) throws Exception {
		
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.brand.updateSysBrnd", paramData);
	}
	
	/**
	 * 시스템 브랜드 다국어 등록/수정
	 * @param paramData
	 * @throws Exception
	 */
	public void mergeBlangBrndNm ( SysBrndLang paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.brand.mergeBlangBrndNm", paramData);
	}


	/**
	 * 포인트종류별 적립률 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 8
	 */
	public void updateSysBrndPntAccmlByType ( SysBrnd sysBrnd ) throws Exception {
		sysBrnd.setRegtrId(BOSecurityUtil.getLoginId());
		getSession1().update("com.plgrim.ncp.commons.brand.updateSysBrndPntAccmlByType", sysBrnd);
	}

	/**
	 * 포인트종류별 적립률 수정 시 상품정보 update.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 24
	 */
	public void updatePntAccmlGodByType ( SysBrnd sysBrnd ) throws Exception {
		sysBrnd.setRegtrId(BOSecurityUtil.getLoginId());
		getSession1().update("com.plgrim.ncp.commons.brand.updatePntAccmlGodByType", sysBrnd);
	}
	
	/**
	 * SMS 발송용 브랜드명 구하기 (BEAKER 최상위, 이외 2레벨)
	 * @param brndId
	 * @return
	 * @throws Exception
	 */
	public String selectBrndNm4Sms(String brndId) throws Exception {
		// SMS개선 by cannon : 2016.07.17
		return getSession1().selectOne("com.plgrim.ncp.commons.brand.selectBrndNm4Sms", brndId);
	}
	
	public String selectBrndNm4SmsByOrdNo(String ordNo) throws Exception {
		// SMS개선 by cannon : 2016.07.17
		return getSession1().selectOne("com.plgrim.ncp.commons.brand.selectBrndNm4SmsByOrdNo", ordNo);
	}

	public String selectBrndNm4SmsByClmNo(String clmNo) throws Exception {
		// SMS개선 by cannon : 2016.07.17
		return getSession1().selectOne("com.plgrim.ncp.commons.brand.selectBrndNm4SmsByClmNo", clmNo);
	}


	/**
	 * 시스템 브랜드 이미지 등록/수정
	 * @param paramData
	 * @throws Exception
	 */
	public void mergeSysBrndImg(SysBrndImg paramData) throws Exception {
		getSession1().update("com.plgrim.ncp.commons.brand.mergeSysBrndImg", paramData);
	}

	/**
	 * 시스템 브랜드 조회
	 * 
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public SysBrnd selectSysBrnd(SysBrndExtend paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.brand.selectSysBrnd", paramData);
	}
	
	/**
	 * 시스템 브랜드 '사용여부' 수정 시 플래그 처리
	 * 
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public int mergeSysBrndModifyFlag(SysBrnd paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.brand.mergeSysBrndModifyFlag", paramData);
	}

}
