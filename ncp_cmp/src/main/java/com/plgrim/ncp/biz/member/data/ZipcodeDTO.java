package com.plgrim.ncp.biz.member.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class ZipcodeDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -6391073696096288840L;

	private String srchKeyword;   //검색항목
	 
	 private String srchType;      //검색구분
	 
	 private String keyword1;      
	
	 private String keyword2;
	 
	 private String keyword3;

	 private String keyword4;

	 private String zipcodeId;
	 
	 private String baseAddrId;
	 
	 private String detailAddrId;
	 
	 private String srchZipcode;   
	
	 private String srchDetailAddr;
	 
	 private String srchMalotnm;

	/**
	 * 퀵 배송 여부
	 */
	 private String qdlvYn;


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
