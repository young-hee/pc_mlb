/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim..
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.biz.newsletter.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * newsletter BackOffice DTO.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class NewsLetterDTO extends AbstractDTO {

    /** UID. */
    private static final long serialVersionUID = 1L;

    /* === Entity START ===================================================  */
    private String mallId;
    private String applcntEmail;	//신청자 이메일
    private java.util.Date reqstDt;	//신청일시     
    
    private String regtrId;
    private java.util.Date regDt;
    private String udterId;
    private java.util.Date udtDt; 
    
    private String searchStartDate;
    private String searchEndDate;
    private String searchMallId;
    private String searchEmailAddr;
    
    private String[] deleteEmailAddr;
       
}
