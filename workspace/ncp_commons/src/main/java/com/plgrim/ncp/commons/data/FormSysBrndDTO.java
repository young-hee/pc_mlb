package com.plgrim.ncp.commons.data;

import java.util.ArrayList;
import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndImg;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndExtend;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormSysBrndDTO extends AbstractDTO {

     private static final long serialVersionUID = 1L;
		/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
 	//검색조건
 	String searchField; // 조회필드 1:브랜드명, 2:브랜드코드
 	String searchText; //조회 조건
 	
 	/**
 	 * 선택된 브랜드 코드
 	 */
 	String selBrndId;
 	
 	/**
 	 * 선택된 erp brnd id
 	 */
 	String selErpBrndId;
 	/**
 	 * 선택된 브랜드코드 정보
 	 */
 	SysBrndExtend sysBrnd;

	/**
	 * 선택된 브랜드코드 이미지
	 * */
	List<SysBrndImg> sysBrndImgList;

	/**
	 * 선택된 브랜드코드 이미지
	 * */
	List<SysBrndImg> deleteSysBrndImgList;

 	/**
 	 * 자식브랜드 목록 정보
 	 */
 	List<SysBrandDTO> childSysBrndList;
 	
 	/**
 	 * 최상위브랜드 정보
 	 * */
 	String topBrndId;
 	
 	List<String> fileNameList = new ArrayList<String>();
}
