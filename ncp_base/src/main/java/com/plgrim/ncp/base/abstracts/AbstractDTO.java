/* Copyright (c) 2015 plgrim, Inc.
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
 * beyondj2ee			2015.02.09      
 */
package com.plgrim.ncp.base.abstracts;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.framework.page.PageParam;

/**
 * 최상위 추상 DTO 클래스.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractDTO implements Serializable {

	// ~ Instance fields. ~~~~~~~~~~~~~~
	/** 페이지 시작 row 번호. */
	int beginIndex = 0;
	
	/** 페이지 마지막 row 번호. */
	int endIndex = 0;

	/* 예외 처리를 위한 파라미터 */
	String[] exceptionParams;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 922331944241812404L;
	
	/* 몰 아이디 */
	String mallId;
	
	/* 언어코드 */
	String lang;
	
	/* 접속 채널 */
	String channel;
	
	/* 접속 디바이스 */
	String device;
	
	/**
	 * 그리드 상태 플래그
	 * 추가 : C
	 * 수정 : U
	 */
	String gCudTag;
	
	String gridCudTag;
	
	/**
	 * 그리드 페이지 번호
	 */
	int gRowNum = 1;
	
	/**
	 * 그리드 페이지 번호
	 */
	String gPageNo = "1"; 

	String gridPageNo = "1";  //object mapping

	/**
	 * 그리드 페이지 사이즈
	 */
	int gPageSize = 50;
	
	int gridPageSize = 50; //object mapping
	
	/**
	 * 그리드 페이지 구조체
	 */
	PageParam pageParam;
	
	/* 마스킹 여부 */
	String maskingYn;
	
	/* 메뉴 일련번호 */
	Long accessMenuSn;
	
	/*로그인 ID*/
	String loginId;
	
	/*로그인된 업체ID*/
	String comId;
	
	/* FO - 로그인 회원 유형 코드 */
	String absMbrTpCd;

	/* FO - 로그인 회원 속성 코드 */
	String absMbrAtrbCd;

	/* FO - 로그인 회원 그룹사 ID */
	String absGepcoId;
	
	/* B2E, SIGNL */
	private String spcPrmTp;

	/* GNRL, EMP */
	private String prcSectCd;
	
	//담당브랜드 목록 - 운영자유형별 담당브랜드가 존재시 사용되는 권한 필드
	List<String> authBrndList;
	
	//DB 마스킹처리시 마스킹여부를 설정한다. Y (마스킹) or N(마스킹해제)
	String dbMarkingYn;
	
}
