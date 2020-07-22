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
 * @since       2015. 7. 16       
 */
package com.plgrim.ncp.commons.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormGodEvlDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private String regBegDt; // 조회시작일
	private String regEndDt; // 조회종료일
	private List<String> mallIds; // 몰구분
	private List<String> ntceYns; // 게시여부
	private String brndId; // 브랜드ID
	private String upperBrndId; // 브랜드 그룹 ID
	private String godNo; // 상품번호
	private String godNm; // 상품명
	private List<String> godEvlTps; // 상품평구분
	private List<String> bstGodEvlYns; // 베스트여부
	private String scoreBeg; // 시작점수
	private String scoreEnd; // 종료점수
	private String dlvEvlScore; // 배송점수
	private String qltyEvlScore; // 품질점수
	private String pkgEvlScore; // 포장점수
	private String sizeEvlCd; // 사이즈의견
	private String colorEvlCd; // 컬러의견
	private List<String> snsYns; // SNS공유여부
	private String mbrType; // 회원조회타입
	private String mbrValue; // 회원조회값
	private Integer godEvlTurn; // 상품평번호
	private Integer no; // 일련번호
	private List<String> brndIds;	// 브랜드ID`s
	private String godNoGbnCd;        //품번구분
    private List<String> langCd;
	private String bstGodEvlYnForCnt;	//베스트상품평개수 조회 조건
	private String ordNo;               //주문번호                20160526_주동민_sr#20343 [상품평 관리 화면 조회값 및 컬럼값 추가 요청]
	private String bstGodEvlCndcyYn;    //베스트 상품평 후보 여부 20160526_주동민_sr#20343 [상품평 관리 화면 조회값 및 컬럼값 추가 요청]
	private Integer bstGodEvlCnt;       //베스트상품평수          20160526_주동민_sr#20343 [상품평 관리 화면 조회값 및 컬럼값 추가 요청]
}
