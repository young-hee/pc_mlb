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
 * @since       2015. 6. 17       
 */
package com.plgrim.ncp.biz.member.data;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

/**
 * [발송 정보 조회]
 * 
 * @author sy59.gim
 * @since 2015. 5. 29
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberWebSelectResult extends AbstractResult {

	/** serialVersionUID. */
    private static final long serialVersionUID = -7267766815267082753L;
    
    /** 보낸날짜. */
    private Date sendDt;
    
    /** 상태. */
    private String sendStatus;
    
    /** 내용. */
    private String sendInfo;
    
    /** 수신자. */
    private String receiver;
	
}
