package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("godGodEvlExtend")
public class GodGodEvlExtend extends GodGodEvl {
	private static final long serialVersionUID = 6475884055218275272L;

	/** 회원 아이디 */
	private String mbrId;

	/** 상품평 구매옵션. */
	private String itmNm;

	/** 상품 평점 . */
	private Integer avgQltyScore;

	/** 사이즈(작아요) 평가 점수 비율 . */
	private Integer sizeSmlAvgPercent;

	/** 사이즈(딱 맞아요) 평가 점수 비율 . */
	private Integer sizeMidAvgPercent;

	/** 사이즈(커요) 평가 점수 비율 . */
	private Integer sizeLagAvgPercent;

	/** 칼라(밝아요) 평가 점수 비율 . */
	private Integer colorBrgAvgPercent;

	/** 칼라(화면과 같아요) 평가 점수 비율 . */
	private Integer colorSamAvgPercent;

	/** 칼라(어두워요) 평가 점수 비율 . */
	private Integer colorDarAvgPercent;

	/** 포토리뷰 카운트 . */
	private Integer atchFileCnt;

	/** 총 카운트 . */
	private Integer totalCnt;

	/**
	 * 정소가
	 */
	private java.math.BigDecimal rtlPrc;
	/**
	 * 최종 판매가 할인 유형별 지정된 판매 금액
	 * 
	 * 세트할인도 균일가 처리할 수 있음
	 */
	private java.math.BigDecimal lastSalePrc;

	private Integer ordQty;

	private List<GodGodEvlAtchFile> godGodEvlAtchFileList;

	private String imgFrontUrl;

	private String detailImgFrontUrl;
	
	private String imgBackUrl;
	
	private String godUrl;
	
	private String godNm;
	
	private String  colorCd;
 
	private String  dsgnGrpCnncNo;
	
	/**
	 * 브랜드 ID
	 * ㅁ. 온라인에서 사용하는 브랜드 ID	 
	 */
	private String brndId;
}
