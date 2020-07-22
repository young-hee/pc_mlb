package com.plgrim.ncp.biz.settlement.data;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class PrmFeeEventExcelDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    
    private String feeEventNm;
    
    private Date eventBegDt;
    
    private Date eventEndDt;
    
    private String feeEventTpCd;
    
    private String affVrscComId;
    
    private String aditFeeRt;
    
    private String useYn;
    
    private String comId;
    
    private String eventAplTgtCd;
    
    private String brndId;
    
    private String aplErpNo;
    
    private String exclsErpNo;
    
    private String errMsg;
    
	/**
	 * 등록자 ID 등록한 관리자 번호	 
	 */
	private String regtrId;
	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;
	/**
	 * 수정자 ID 수정한 관리자 번호	 
	 */
	private String udterId;
	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;
    
	/** The apl god no. */
	private String aplGodNo;
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
