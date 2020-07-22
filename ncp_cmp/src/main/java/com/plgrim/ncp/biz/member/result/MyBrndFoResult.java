package com.plgrim.ncp.biz.member.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBukmkBrnd;

@Data
@EqualsAndHashCode(callSuper=false)
public class MyBrndFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 *
	 */
	private static final long serialVersionUID = -7893147324413144491L;

	private MbrBukmkBrnd mbrBukmkBrnd;
	
	private String dspCtgryNo;
	
	private String brndId;
	
	private String brndNm;
	
	/* 브랜드 이미지 */
	String pcBrndImgFileNm;
    String pcBrndImgFileUrl;
    String pcBrndImgAltrtvCont;
    String mobileBrndImgFileNm;
    String mobileBrndImgFileUrl;
    String mobileBrndImgAltrtvCont;
    String pcixeBrndImgFileNm;
    String pcixeBrndImgFileUrl;
    String pcixeBrndImgAltrtvCont;
    String mbixeBrndImgFileNm;
    String mbixeBrndImgFileUrl;
    String mbixeBrndImgAltrtvCont;

    /* 카테고리 출력 유형 코드 */
    private String ctgryOutptTpCd;

    /* 출력 구분 코드 */
    private String outptSectCd;

    /* 출력 링크 URL */
    private String outptLinkUrl;

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
