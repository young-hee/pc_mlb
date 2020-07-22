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
 * @since       2015. 6. 22       
 */
package com.plgrim.ncp.interfaces.member.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

/**
 * 회원 탈퇴 결과
 * 
 * @author Id
 * @since 2018. 7. 4
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrSecsnResult")
public class MbrSecsnResult extends Mbr {

	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = 266556246105397073L;
    
	/**
	 * 회원 번호
	 * ㅁ. 회원 가입시 부여되는 고유한 관리 번호
	 *    >. MB || YYYYMMDD || 7자리 Cycle	 
	 */
	private String mbrNo;

	/**
	 * ERP 고객번호
	 * ㅁ. ERP에서 관리되는 고객 번호	 
	 */
	private String erpCstmrNo;

    /** 주문 개수. */
    private long ordCnt;

    /** 클레임 개수. */
    private long clmCnt;

}
