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
 * @since       2015. 4. 8
 */
package com.plgrim.ncp.biz.display.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * [클래스 설명]
 *
 * <p>
 *
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author shsunhee.kim
 * @since 2015. 3. 13
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class DspFinderBoDTO extends AbstractDTO {

	private String finderNo;
	private List<String> arFinderNo;

	private String finderNm;
	private String finderNmRgbColorCdRgb; //파인더명 색상코드

	private String finderAsstnNm; //파인더 보조명
	private String finderAsstnRgbColorCd; //파인더 보조명 색상코드

	private String finderUrl; //파인더 URL

	private String upendRgbColorCd; //상단색상코드

	private String bannerImgUrl; //배너 이미지 url
	private String bannerCont; //배너 내용


	private String useYn;
	List<String> arUseYn;

	private String qestnLblUseYn;//QESTN_LBL_USE_YN 더알아보기 사용여부

	private int sortSeq;

	private String finderGodSortStdrCd;

	private String startDate;
	private String endDate;

	private String regtrId;
	private String regDt;
	private String udterId;
	private String udtDt;

	private String scDateType; //날짜검색유형

	/*파인더 질문*/
	private int qestnTurn; //질문 순번


	private String qestnCont; //질문 내용
	private String qestnLblNm; //질문 라벨명
	private String pcQestnLblCont; //pc 라벨 내용
	private String mobileQestnLblCont; //모바일 라벨 내용

	/*파인더 예문*/
	private int smpleSntnTurn; //예문 순번

	private String smpleSntnCont; //예문 내용


	private String godNo; //연결상품 번호
	private String erpGodNo; //연결상품 erp번호

	private List<String> arErpGodNo; //연결상품 erp번호


	private String rowId;

	private int beforeQestnTurn; //변경전 질문순번
	private int beforeSmpleSntnTurn;//변경전 예문순번

	private String checkedYn;

	/*엑셀관련*/
	private String validText;
	private String queryCase;


}
