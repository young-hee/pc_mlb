package com.plgrim.ncp.biz.member.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageOnlineMemberGradeInfoResult extends AbstractResult {/**

	/**
	 * 
	 */
	private static final long serialVersionUID = 7073844769299562604L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 온라인 등급
	 */
	private String onlneGrdNm;


	/**
	 * 온라인 등급 코드
	 */
	private String onlneGrdCd;


	/**
	 * 온라인 등급 적용 시작년월일
	 */
	private String grdAplBegDt;


	/**
	 * 온라인 등급 적용년도
	 */
	private String grdAplEndDt;

	/**
	 * 실적집계 시작일시(년도)
	 */
	private String acmsltSmonBegYear;

	/**
	 * 실적집계 시작일시(월)
	 */
	private String acmsltSmonBegMonth;

	/**
	 * 실적집계 종료일시(년도)
	 */
	private String acmsltSmonEndYear;

	/**
	 * 실적집계 종료일시(월)
	 */
	private String acmsltSmonEndMonth;

	/**
	 * 구매횟수
	 */
	private java.lang.Integer ordCnt;

	/**
	 * 구매금액
	 */
	private java.lang.Integer ordAmount;

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
