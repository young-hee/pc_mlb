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
package com.plgrim.ncp.biz.callcenter.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 1:1 문의 게시판 리스트 조회 dto
 *
 * @author 
 * @since 2015. 3. 25
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MtmOrdGodResult extends AbstractResult {


    private static final long serialVersionUID = 1L;

    private java.lang.Long mtmInqSn;
    private java.lang.Integer mtmInqOrdGodTurn;
    private String godNm;
    private String ordNo;
    private java.lang.Integer ordGodTurn;
    private String regtrId;
    private java.util.Date regDt;
    private String udterId;
    private java.util.Date udtDt;
    private String godNo;

}
