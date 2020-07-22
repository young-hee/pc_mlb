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
 * @since       2015. 7. 9       
 */
package com.plgrim.ncp.commons.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormSysCmmnNotiDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private String notiSj; // 공지제목
	private List<String> adminTpCd; // 노출타겟
	private List<String> notiTpCds; // 공지유형
	private List<String> boSiteIds; // 노출타겟 BO
	private String dspYn; // 게시여부
	private List<String> popupYns; // 팝업여부
	private String popupBegDt; // 공지기간시작
	private String popupEndDt; // 공지기간끝
	private String regBegDt; // 등록일시작
	private String regEndDt; // 등록일끝
	private String calType; // 기간검색 종류
	private String regtrIdNm; // 작성자 
	private String notiCont;

	private Long notiSn; // 공지일련번호
	private Long notiFileSn; // 선택첨부파일순번
	private String notiSectCd; // 공지구분코드
	
	private List<String> mallIds; // 몰 ID
	private List<String> dvcCds; // 디바이스 유형
	private List<String> dspYns; // 전시여부
	private String dspBegDt; // 전시시작일
	private String dspEndDt; // 전시종료일

	/**
	 * 전시유형
	 * - begDt : 등록일
	 * - dspBegDt : 전시시작일
	 * - dspEndDt : 전시종료일
	 */
	private String dspTpCd;  // 전시유형 
	
	private Long popupNotiSn; // 팝업공지일련번호
	
	private String grpcoNm; // 그룹사명
	
	private String boSiteId; // BO 사이트ID
    private String langCd;
}
