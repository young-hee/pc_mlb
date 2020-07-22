/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * 
 */
package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminLoginLog;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminUseLog;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;

/**
 * 운영자 로그 
 * @author Tam
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdmMsgLogResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    /*
     * 발송시간
     */
    java.util.Date sendtime;
    
    /**
     * 메시지 유형  : E-MAIL, SMS, LMS
     */
    String msgTp;
    
    /**
     * SMS : 메시지 , EMAIL: 제목
     */
    String msg;
    
    /**
     * 수신인 전화번호 ( EMAIL 유형 없음)
     */
    String rcpnPhone;
    
    /**
     * 발신인명 
     */
    String adsrNm;
    
    /**
     * 발신인 전화번호( EMAIL 유형은 없음.)
     */
    String adsrPhone;
    
    /**
     * 전송결과
     */
    String sendRstl;
    
    
}

