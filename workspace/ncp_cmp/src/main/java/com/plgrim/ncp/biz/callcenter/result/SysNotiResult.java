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
package com.plgrim.ncp.biz.callcenter.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SysNotiResult extends AbstractResult {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 공지 일련번호
	 */
	private java.lang.Long notiSn;
	/**
	 * 공지 유형 코드
	 ㅁ. 공지 유형 : NOTI_TP
	 >. 일반 : GNRL
	 >. 시스템 : SYS
	 >. 이벤트 : EVT
	 >. 이벤트 당첨 : EVT_PRIZE
	 */
	private String notiTpCd;
	/**
	 * 공지 구분 코드
	 ㅁ. 공지 구분 : NOTI_SECT
	 >. FO 공지 : FO_NOTI
	 >. BO 공지 : BO_NOTI
	 */
	private String notiSectCd;
	/**
	 * 등록 관리자 ID
	 */
	private String regAdminId;
	/**
	 * 공지 등록 일시
	 */
	private java.util.Date notiRegDt;
	/**
	 * 사용 여부
	 */
	private String useYn;
	/**
	 * 상단 고정 여부
	 */
	private String upendFixYn;
	/**
	 * 공지 제목
	 */
	private String notiSj;
	/**
	 * 공지 컨텐츠 구분 코드
	 ㅁ. 공지 컨텐츠 구분 : NOTI_CONTT_SECT
	 >. 이미지배너 : IMG_BANNER
	 >. 이미지맵 : IMG_MAP
	 >. HTML :  HTML
	 */
	private String notiConttSectCd;
	/**
	 * 이미지 URL
	 */
	private String imgUrl;
	/**
	 * 링크 URL
	 */
	private String linkUrl;
	/**
	 * 링크 출력 대상 코드
	 ㅁ. 링크 출력 대상 : LINK_OUTPT_TGT
	 >. 본창 : TH_WIN
	 >. 새창 : NEW_WIN
	 */
	private String linkOutptTgtCd;
	/**
	 * 공지 내용
	 */
	private String notiCont;
	/**
	 * 조회 수
	 */
	private java.math.BigInteger inqireNum;
	/**
	 * 팝업 여부
	 */
	private String popupYn;
	/**
	 * 팝업 구분 코드
	 ㅁ. 팝업 구분 : POPUP_SECT
	 >. 일반 팝업 : GNRL_POPUP
	 >. 레이어 팝업 : LAYER_POPUP
	 */
	private String popupSectCd;
	/**
	 * 팝업 시작 일시
	 */
	private java.util.Date popupBegDt;
	/**
	 * 팝업 종료 일시
	 */
	private java.util.Date popupEndDt;
	/**
	 * 팝업 좌측 위치
	 */
	private java.lang.Integer popupLeftLc;
	/**
	 * 팝업 상단 위치
	 */
	private java.lang.Integer popupUpendLc;
	/**
	 * 팝업 넓이
	 */
	private java.math.BigDecimal popupWidth;
	/**
	 * 팝업 높이
	 */
	private java.lang.Integer popupHg;
	/**
	 * 이벤트 번호
	 ㅁ. EV || YYYYMMDD || 7자리 Cycle Sequence
	 */
	private String evtNo;
	/**
	 * 등록자 ID
	 등록한 관리자 번호
	 */
	private String regtrId;
	/**
	 * 등록 일시
	 */
	private java.util.Date regDt;
	/**
	 * 수정자 ID
	 수정한 관리자 번호
	 */
	private String udterId;
	/**
	 * 수정 일시
	 */
	private java.util.Date udtDt;



   
}
