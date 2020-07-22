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
 * @since       2015. 11. 23       
 */
package com.plgrim.ncp.biz.display.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.biz.display.result.DspCnrConttFrResult;
import com.plgrim.ncp.biz.display.result.DspTmplatFrResult;

/**
 * Instantiates a new dsp cnr sc fr dto.
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class DspCnrScFrDTO extends AbstractDTO {
	/**
	 * 
	 */
    private static final long serialVersionUID = -9149203599362383736L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    Long tmplatSn;
    
    String tmplatTp;
    
    String tmplatScKey;
    
    String tmplatCnncUrl;
    
    String tmplatDscr;
    
    List<DspTmplatFrResult> tmplatCnrList;
    
    Long revSn;	           //개정번호
    Integer revCpstTurn;   //개정구성순번
    Long abTestSn;         //AB 테스트 순번
    String isPrevYn;

	/** 전시대상 설정 */
	/** 적용회원유형 
	 * (APL_MBR_TP: NMBR 비회원, 
	 * ONLINE_MBR 온라인회원, UNTY_MBR 통합회원) 
	 */
	String aplMbrTp;
	/** 적용회원속성
	 * (APL_MBR_ATRB: GNRL_MBR 일반회원, PLGRIM_FSHN 플그림패션,
	 * plgrim 플그림, GRPCO_ALL 그룹사전체, GRPCO_INDVDLZ 그룹사개별)
	 */
	String aplMbrAtrb;
	/** 전시상품 가격 - 가격구분코드 */
	String prcSectCd;
	
	/** 일모카드 사용 유무 */
	String empYn;
	
	String grpcoId;
	
	String soldDspYn;
	
	/** 대체로직적용 상품 파라미터 */
	Long cnrSn;
	Long cnrSetSn;
	
	String altScNm;
	int lastIndex;
	List<DspCnrConttFrResult> notIncludeGodNos;
		
	/** The brand ids. */
	List<String> brandIds;
	
	/** The seson list. */
	List<String> sesonList;
	
	String thmCtgryNo;
	
	
	/** 해당 브랜드의 strnd 상세정보를 조회하기 위한  param */
	String strndBrndId;
	
	String strndTpCd;
	
	String prev;
	
	
	/** 브랜드몰 카테고리 템플릿 조회를 위한 Key 브랜드ID */
	String tmplatKeyBrndId;
	
	
	List<String> conttTpList;
	
	String imgSize;
	
	/** 이미지배너 브랜드필터 파라미터 */
	String[] filterBrndCd;
}
