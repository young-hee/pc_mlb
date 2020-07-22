package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspFinderBoResult extends AbstractResult {

    private static final long serialVersionUID = -2752248806043804337L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	private String finderNo;
	private String finderNm;
	private String finderNmRgbColorCdRgb; //파인더명 색상코드

	private String finderAsstnNm; //파인더 보조명
	private String finderAsstnRgbColorCd; //파인더 보조명 색상코드

	private String finderUrl; //파인더 URL

	private String upendRgbColorCd; //상단색상코드

	private String bannerImgUrl; //배너 이미지 url
	private String bannerCont; //배너 내용

	private String useYn;
	private int sortSeq;

	private String moreHtmlBtnNm;

	private String finderGodSortStdrCd;

	/*파인더 질문*/
	private int qestnTurn; //질문 순번
	private String qestnCont; //질문 내용
	private String qestnLblNm; //질문 라벨명
	private String pcQestnLblCont; //pc 라벨 내용
	private String mobileQestnLblCont; //모바일 라벨
	private String qestnLblUseYn;//QESTN_LBL_USE_YN 더알아보기 사용여부


	/*파인더 예문*/
	private int smpleSntnTurn; //예문 순번
	private String smpleSntnCont; //예문 내용

	/*파인더 연결*/
	private String godNo;
	private String erpGodNo;
	private String godNm;

	private String regtrId;
	private Date regDt;
	private String udterId;
	private Date udtDt;

	private int beforeQestnTurn;
	private int beforeSmpleSntnTurn;

	private String checkedYn;

	/*엑셀업로드관련*/
	private String existYn;
	private String duplicationYn;
	private String validText;

	private String gridCudTag; //그리드 상태 플러그

}
