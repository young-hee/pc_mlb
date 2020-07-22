package com.plgrim.ncp.commons.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysAdAffiliateResult extends AbstractResult {

	/**
	 * UID
	 */

    private static final long serialVersionUID = -6655325365564354058L;
    
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    /**
     * 몰 명
     */
	String mallIdNm;

    /**
     * 유입 채널 유형 코드 명
     */
	String inflowChnnlTpCdNm;

    /**
     * 유입 구분 코드 명
     */
	String inflowSectCdNm;

	/**
	 * 광고 제휴 업체 명
	 */
	private String advtAffComNm;
	
	/**
	 * 시작 일시 String	 
	 */
	String begDtStr;

	/**
	 * 종료 일시 String	 
	 */
	String endDtStr;
	
	/**
	 * 사용 여부 String
	 */
	String useYnNm;

	/**
	 * 수수료 사용 여부 String
	 */
	String feeUseYnNm;

	/**
	 * URL복사
	 */
	String urlCp;
	
    /**
     * 시스템 유입 entity
     */
    SysInflow sysInflow;


}
