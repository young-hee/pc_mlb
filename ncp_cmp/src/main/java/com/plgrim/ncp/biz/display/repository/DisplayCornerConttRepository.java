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
 * @since       2015. 7. 15       
 */
package com.plgrim.ncp.biz.display.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttLang;
import com.plgrim.ncp.base.repository.dsp.DspCnrConttRepository;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspImgUploadDTO;
import com.plgrim.ncp.biz.display.result.DspImgUploadResult;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

/** The Constant log. */
@Slf4j
@Repository
public class DisplayCornerConttRepository extends DspCnrConttRepository {

	/** The id gen service. */
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
	 * 신규 컨텐츠 순번 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrContt [설명]
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 20
	 */
	public Integer getConttTurn(DspCnrContt dspCnrContt) throws Exception {
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("CNR_SN", dspCnrContt.getCnrSn());
		conditions.put("CNR_SET_SN", dspCnrContt.getCnrSetSn());
		
		Integer conttTurn = getIdGenService().generateDBOrder(getSession1(), "DSP_CNR_CONTT", "CONTT_TURN", conditions, DatabaseType.ORACLE);
		
		dspCnrContt.setConttTurn(conttTurn);
		
		return conttTurn;
	}
	
	/**
	 * 컨텐츠 정보 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrContt [설명]
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 15
	 */
	public void insertCornerContent(DspCnrContt dspCnrContt) throws Exception {
		//코너컨텐츠 등록

		// 20160517_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 컬럼 스티커 상품 처리]
		if (StringService.isEmpty(dspCnrContt.getStkExpsrYn())) {
			// Null 처리
			dspCnrContt.setStkExpsrYn("N");
		}

		insertDspCnrContt(dspCnrContt);
	}
	
	/**
	 * 컨텐츠 정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrContt [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 15
	 */
	public int updateCornerContent(DspCnrContt dspCnrContt) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspCnrConttInfo", dspCnrContt);
	}
	
	/**
	 * 컨텐츠 정보 수정 - Grid.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrContt [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 15
	 */
	public int updateCornerContentForGrid(DspCnrContt dspCnrContt) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspCnrConttForGrid", dspCnrContt);
	}
	
	/**
	 * 컨텐츠 언어 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 15
	 */
	public int updateDspCnrConttLangInfo(DspCnrConttLang dspCnrConttLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspCnrConttLangInfo", dspCnrConttLang);
	}
	
	/**
	 * 컨텐츠 언어 수정 - Grid.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 15
	 */
	public int updateDspCnrConttLangForGrid(DspCnrConttLang dspCnrConttLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspCnrConttLangForGrid", dspCnrConttLang);
	}
	
	/**
	 * 컨텐츠 언어 저장.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 19
	 */
	public int saveDspCnrConttLangInfo(DspCnrConttLang dspCnrConttLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.saveDspCnrConttLangInfo", dspCnrConttLang);
	}
	
	/**
	 * 컨텐츠 언어 저장 - 이미지.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 15
	 */
	public int saveConttLangImg(DspCnrConttLang dspCnrConttLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.saveConttLangImg", dspCnrConttLang);
	}
	
	/**
	 * 컨텐츠 언어 저장 - 오버 이미지.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 15
	 */
	public int saveConttLangOvImg(DspCnrConttLang dspCnrConttLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.saveConttLangOvImg", dspCnrConttLang);
	}
	
	/**
	 * 컨텐츠 언어 저장 - 기타 텍스트형 다국어 컨텐츠
	 * 김병철 sr#19452
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2016. 05. 13
	 */
	public int saveConttLangContents(DspCnrConttLang dspCnrConttLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.saveConttLangContents", dspCnrConttLang);
	}
	
	
	/**
	 * 컨텐츠 언어 삭제
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 15
	 */
	public int deleteDspCnrConttLangInfo(DspCnrConttLang dspCnrConttLang) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrConttLangInfo", dspCnrConttLang);
	}
	
	
	/**
	 * 코너 세트에 기등록된 컨텐츠 목록조회. - 상품, 기획전, S-Trend
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrContt [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 20
	 */
	public List<Map<String,Object>> selectCnrConttCnncedList (DspCnrConttExt dspCnrContt) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectCnrConttCnncedList", dspCnrContt);
	}
	
	
	/**
	 * 전시카테고리에 연결되어 있는 상품인지 체크
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param godNo [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 30
	 */
	public int selectCtgryCnncGodCnt (String godNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectCtgryCnncGodCnt", godNo);
	}
	
	
	/**
	 * 전시 컨텐츠 이미지 리스트 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspImgUploadDTO [설명]
	 * @param pageParam [설명]
	 * @return Page [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 7
	 */
	public Page<DspImgUploadResult> selectImageUploadList(DspImgUploadDTO dspImgUploadDTO, PageParam pageParam) throws Exception {
        // 페이지 설정
        RepositoryHelper.makePageEntityIndex(dspImgUploadDTO, pageParam);
        
        // 전시 컨텐츠 이미지 리스트 조회
        List<DspImgUploadResult> resultList = getSession1().selectList("com.plgrim.ncp.biz.display.selectImageUploadList", dspImgUploadDTO);
        
        // 전시 컨텐츠 이미지 리스트 갯수 조회
        long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.display.selectImageUploadListTotal", dspImgUploadDTO);
        return new PageImpl<DspImgUploadResult>(resultList, pageParam.getPageable(), totalRow);
    }
	
	/**
	 * 컨텐츠 이미지 정보 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspImgUploadDTO [설명]
	 * @return Dsp img upload result [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspImgUploadResult detailImageUpload(DspImgUploadDTO dspImgUploadDTO) throws Exception {
	    return getSession1().selectOne("com.plgrim.ncp.biz.display.detailImageUpload", dspImgUploadDTO);
	}
	
	
	/**
	 * 업로드 된 이미지 목록 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspImgUploadDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<DspImgUploadResult> selectImageList(DspImgUploadDTO dspImgUploadDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.biz.display.selectImageList", dspImgUploadDTO);
	}
	
	public int selectImageCnt(DspImgUploadDTO dspImgUploadDTO) throws Exception {
	    return getSession1().selectOne("com.plgrim.ncp.biz.display.selectImageCnt", dspImgUploadDTO);
	}
	
	public int updateImageUpload(DspImgUploadDTO dspImgUploadDTO) throws Exception {
	    return getSession1().update("com.plgrim.ncp.biz.display.updateImageUpload", dspImgUploadDTO);
	}
	
	/**
	 * 전시코너 연결 상품 전시순서 업로드
	 */
	public int updateSortSeqCnrConttGod(DspCnrContt dspCnrContt) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateSortSeqCnrConttGod", dspCnrContt);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	
}
