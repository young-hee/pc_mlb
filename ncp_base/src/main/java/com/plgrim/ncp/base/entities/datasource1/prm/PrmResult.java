package com.plgrim.ncp.base.entities.datasource1.prm;

import lombok.Data;

/** Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	:	상품 Interface target Result
 *
 * @Author 	:	neps
 * @Date   	:	2018. 6. 28.
 * @Version	:	
 *
 */
@Data
public class PrmResult {
 
	private java.lang.Long webpntSn;
	
 
	private String mbrNo;
	
	private java.math.BigDecimal usefulAmt;	// 가용포인트
	private java.math.BigDecimal extshPrearngeAmt;	// 소멸예정포인트(1달이내)
	
	private String expirationDate;
	private String expirationDateYear;
	private String expirationDateMonth;
	private String expirationDateDay;
}
