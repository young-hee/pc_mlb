/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * 수트 파인더 Result Entity
 * @author      munsik.jeong
 * @since       2017. 8. 15
 */
package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class GoodsFinderFoResult extends AbstractResult {
    private static final long serialVersionUID = -2065602554280086792L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 습득자 번호. 번호를 사용하나 자주 생성 되는 번호가 아닌 관계로 따로 포멧이나 Sequence 사용 하지 않음. MAX값+1로 처리 */
    String finderNo;
    /** 습득자 명 */
    String finderNm;
    /** 습득자 보조명 */
    String finderAsstnNm;
    /** 파인더명 색상코드 */
    String finderNmRgbColorCdRgb;
    /** 파인더명 보조명 색상코드 */
    String finderAsstnRgbColorCd;
    /** 파인더명 보조명 상단색상코드 */
    String upendRgbColorCd;
    /** 파인더명 배너 이미지 url*/
    String bannerImgUrl;
    /** 파인더명  배너 내용 */
    String bannerCont;
    /** 습득자 상품 정렬 기준 코드. 습득자 상품 정렬 기준 : FINDERGODSORTSTDR. 신상품순 : NEWGODSEQ. 높은가격순 : BESTPRCSEQ. 낮은가격순 : LWETPRCSEQ */
    String finderGodSortStdrCd;
     /** 사용 여부 */
    String useYn;
    /** 등록자 ID 등록한 관리자 번호 */
    String regtrId;
    /** 등록 일시 */
    String regDt;
    /** 수정자 ID 수정한 관리자 번호 */
    String udterId;
    /** 수정 일시 */
    String udtDt;
    /** 상품 번호. 상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence */
    String godNo;
    /** 질문 순번 */
    String qestnTurn;
    /** 예문 순번 */
    String smpleSntnTurn;
    /** 질문 내용 */
    String qestnCont;
    /** 질문 라벨 명 */
    String qestnLblNm;
    /** 질문 라벨 사용여부 */
    String qestnLblUseYn;
    /** 질문 라벨 내용 PC */
    String pcQestnLblCont;
    /** 질문 라벨 내용 MOBILE */
    String mobileQestnLblCont;
    /** 예문 내용 */
    String smpleSntnCont;
    /** 대상 순번 */
    String tgtTurn;
    /** 습득자 대상 코드. 습득자 대상 : FINDER_TGT. 사업부 브랜드 : ENDP_BRND. 품목 : PRDLST" */
    String finderTgtCd;
    /** 사업부 브랜드 ID */
    String endpBrndId;
    /** 품목 코드. ERP과 연동하여 처리 된건은 2자리 */
    String prdlstCd;
    /** 예문 리스트 */
    List<GoodsFinderFoResult> smpleSntnList;

    //질문 노출순서
    String qestnSortSeq;
    //예문 노출순서
    String sntnSortSeq;

}
