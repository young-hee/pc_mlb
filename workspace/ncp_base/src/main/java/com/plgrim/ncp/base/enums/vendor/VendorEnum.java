/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 5. 8       
 */
package com.plgrim.ncp.base.enums.vendor;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author alcyone
 */
public class VendorEnum {

	/**
	 * 업체 담당자 업무구분
	 */
	public enum ComChrgerJob{
		  BSN	     // 영업
		, MD	     // MD
		, ORD	     // 주문 
		, CSV	     // 고객서비스
		, RTGOD	     // 반품 
		, CAL	     // 정산
	}

	/**
	 * 입점 업체 담당자 업무구분
	 */
	public enum PartmalComChrgerJob{
		  BSN	     // 영업
		, MD	     // MD
		, CSV	     // 고객서비스
	}
}
