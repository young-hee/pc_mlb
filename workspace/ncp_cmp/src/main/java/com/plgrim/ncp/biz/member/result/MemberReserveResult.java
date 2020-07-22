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
 * @since       2015. 6. 15       
 */
package com.plgrim.ncp.biz.member.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 포인트 RESULT
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("memberReserveResult")
public class MemberReserveResult {
    
    /** ERP 번호. */
    private String erpNo;

    /** 멤버십포인트. */
    private long reserve;

    /** 이벤트포인트. */
    private long eventReserve;

    /** 포인트 최저단위. */
    private int reserveMinUnit;

    /** 마일리지 전환시 1마일리지 당 포인트 비율. */
    private int convertReserveRate;

    /** 전환될 포인트. */
    private long convertReserve;
}
