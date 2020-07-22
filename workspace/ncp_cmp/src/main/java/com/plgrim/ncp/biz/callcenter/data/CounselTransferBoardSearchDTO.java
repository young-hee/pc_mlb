/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shhenry.choi
 * @since       2015. 3. 18       
 */
package com.plgrim.ncp.biz.callcenter.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class CounselTransferBoardSearchDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	private String searchDateType;
	
	private String transferBoardStartDt;
	
	private String transferBoardEndDt;
	
	private String transStatCd;
	
	private String chrgJobTpCd;
	
	private String transRequstTpCd;
	
	private String elapseDay;

	private String regAdminId;

	private String doiCd;
	
	private String ordNo;
    
    private String godNo;
    
    private String cnsltSn;
    
    private Long requstSn;

    private String comId;

    private String tabSect; /* po main dashboard 업무게시판 구분 */

    private String loginId;

    private String mallId;

    private String langCd;


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
