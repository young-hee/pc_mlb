package com.plgrim.ncp.commons.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysAdAffiliateDTO  extends AbstractDTO {

	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;

    /**
     * 서비스번호 Array
     */
	private List<String> arrInflowSn;

    /**
     * 유입 서비스 유형 코드 Array
     */
	private List<String> arrInflowChnnlTpCd;

    /**
     * 유입 구분 코드 Array
     */
	private List<String> arrInflowSectCd;

    /**
     * 사용여부 Array
     */
	private List<String> arrUseYn;

    /**
     * 수수료사용여부 Array
     */
	private List<String> arrFeeUseYn;

	/**
	 * 조회 기간 유형
	 */
	private String searchTermType;

	/**
	 * 업체ID
	 */
	private String companyId;
	
	/**
	 * 광고 제휴 업체 명
	 */
	private String advtAffComNm;
	
	/**
	 * 조회 시작 일시 String	 
	 */
	private String begDtStr;

	/**
	 * 조회 종료 일시 String	 
	 */
	private String endDtStr;

	/**
	 * 조회 시작 일시 Date	 
	 */
	private java.util.Date searchBegDt;

	/**
	 * 조회 종료 일시 String	 
	 */
	private java.util.Date searchEndDt;
	
    /**
     * 시스템 유입 entity
     */
	private SysInflow sysInflow;
	
	/**
     * 몰 구분
     */
	private String mallTpCd;

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
