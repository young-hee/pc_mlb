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
package com.plgrim.ncp.commons.result;

import java.math.BigInteger;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

/**
 * 메뉴 Result VO
 * @author Park
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuViewResult  {
	
	/**
	 * UID
	 */
    private static final long serialVersionUID = 5145202058661828850L;
    
    /**
     * 권한그룹코드
     */
    //private long authorGrpSn;
    
    /**
     * dhtmlx 용 id
     */
    private BigInteger id;
    
	/**
	 * 메뉴 Depth
	 */
	private BigInteger lvl;
	
	/**
	 * 메뉴 일련번호
	 */
	private BigInteger menuSn;

	/**
	 * 상위 메뉴 일련번호
	 */
	private BigInteger upperMenuSn;

	/**
	 * 메뉴 명
	 */
	private String menuNm;

	/**
	 * 메뉴 이미지 URL
	 */
	private String menuImgUrl;

	/**
	 * 메뉴 URL
	 */
	private String menuUrl;

	/**
	 * 사용 여부
	 */
	private String useYn;

	/**
	 * 정렬 순서
	 */
	private BigInteger sortSeq;

	/**
	 * BO 사이트 ID BO, PO, CS를 정의
	 */
	private String boSiteId;
	
	/**
	 * 하위 메뉴 갯수
	 */
	private BigInteger childCount;
	
	/**
	 * 메뉴 유형 코드 
	 */
	private String menuTpCd;

	/**
	 * 메뉴 최초파일 여부 (index 파일 여부)
	 */
	private String firstPgeYn;
	
	/**
	 * ㅁ. 출력 유형 : OUTPT_TP
	   >. 본창 : TH_WIN
	   >. 새창 : NEW_WIN
	   >. 팝업 : POPUP
	 */
	private String outptTpCd;
	
	/**
	 * 팝업 좌측 위치
	 */
	private BigInteger popupLeftLc; 
	
	/**
	 * 팝업 상단 위치
	 */
	private BigInteger popupUpendLc;
	
	/**
	 * 팝업 넓이
	 */
	private BigInteger popupWidth;
	
	/**
	 * 팝업 높이
	 */
	private BigInteger popupHg;
	
	/**
	 * 하위 메뉴 리스트
	 */
	private List<MenuViewResult> rows;

}
