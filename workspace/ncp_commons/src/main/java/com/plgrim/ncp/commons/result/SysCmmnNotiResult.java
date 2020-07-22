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
 * @since       2015. 6. 5       
 */
package com.plgrim.ncp.commons.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpco;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNoti;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNotiAtchFile;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNoti;

/**
 * @author ed
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysCmmnNotiResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    private SysNoti sysNoti; // 시스템공지
    List<SysNotiAtchFile> sysNotiAtchFileList; // 첨부파일
    
    private String mallNm; // FO노출타겟명
    private String siteNm; // BO노출타겟명
    private String adminTpCdNm; // 세부타겟명
    private String notiTpCdNm; // 공지유형명
    private String popupDtNm; // 공지기간
    private String popupBegDtNm; // 공지시작일
    private String popupEndDtNm; // 공지종료일    
    private String popupBegDtHour; 	// 공지기간 시작 시간
	private String popupBegDtMinute; 	// 공지기간 시작 분
	private String popupEndDtHour; 	// 공지기간 끝 시간	
	private String popupEndDtMinute; 	// 공지기간 끝 분	
    private String dspYnNm; // 게시여부명
    private String popupYnNm; // 팝업여부명
    private String regtrIdNm; // 등록자명
    private String regDtNm; // 등록일
    private String udterIdNm; // 수정자명
    private String udtDtNm; // 수정일
    
    // 전시카테고리
    private String dspCtgryNo; // 전시카테고리 번호
    private String dspCtgryNm; // 전시카테고리명
    private String type; // 타입(MAIN, Category Main, Brand Main)
    
    // 팝업공지 데이터
    private SysPopupNoti sysPopupNoti; // 팝업공지
    private String mall; // 몰
	private String dvc; // 디바이스
	private String lang; // 언어
	private String dsp; // 전시영역
	private String mbrTpCd; // 대상회원유형
	private String mbrAtrbCd; // 대상회원속성
	private String mbr; // 전시대상
	private String dspRegDtNm; // 전시시작일
	private String dspEndDtNm; // 전시종료일
	private SysGrpco sysGrpco; // 그룹사
	private String aplTurn; // 적용순번

	private String newYn; //최근 공지 여부

	//PO공지 리스트 내용
	private String noticeLineHtml; //공지사항 line html
	private String day7InYn; // 등록일 기준 top5

    private String langCd;
}

