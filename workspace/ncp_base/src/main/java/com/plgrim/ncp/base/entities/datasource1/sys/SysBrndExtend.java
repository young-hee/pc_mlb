/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : jackie(jackie)
 * @date : 20150501
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 시스템 브랜드 확장
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("SysBrndExtend")
public class SysBrndExtend extends SysBrnd{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 브랜드 경로 
	 */
	private String naBrndNm;
	
	/**
	 * 영어 브랜드 코드명
	 */
	private String engBrndNm;
	
	/**
	 * 중국 브랜드 코드명
	 */
	private String chiBrndNm;
	
	/**
	 * 영어 브랜드명 다국어일련번호
	 */
//	private java.lang.Long engMlangSn;
	
	/**
	 * 중국 브랜드명 다국어일련번호
	 */
//	private java.lang.Long chiMlangSn;
	
	/**
	 * 영어 브랜드명 다국어 IDX ID
	 */
//	private String engMlangIdxId;
	
	/**
	 * 중국 브랜드명 다국어 IDX ID
	 */
//	private String chiMlangIdxId;
	
	/**
	 * 등록자명
	 */
	private String regtrNm;
	
	/**
	 * 수정자명
	 */
	private String udterNm;
	
	private int maxHistTurn;
	
	private int maxSortSeq;

	/**
	 * 등록일자 str
	 */
	private String regDtStr;

	/**
	 * 수정일자 str
	 */
	private String udtDtStr;
	
	private String comChecked;
	
	/** 영문 브랜드스토리 소개내용 */
	private String engBrndStoryIntrcnCont;
	
	/** 중문 브랜드스토리 소개내용 */
	private String chiBrndStoryIntrcnCont;
	
	/** 사용여부 변경 확인용 */
	private String orgUseYn;
}
