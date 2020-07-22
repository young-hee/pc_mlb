package com.plgrim.ncp.biz.newsletter.result;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("newsLetterResult")
public class NewsLetterResult extends AbstractResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	
	private String mallNm;
	private String mallId;
    private String applcntEmail;	//신청자 이메일
    private java.util.Date reqstDt;	//신청일시     
    
    private String regtrId;
    private java.util.Date regDt;
    private String udterId;
    private java.util.Date udtDt; 
	
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
