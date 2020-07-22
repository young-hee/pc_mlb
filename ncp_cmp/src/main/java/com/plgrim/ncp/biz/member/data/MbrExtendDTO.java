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
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.biz.member.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

/**
 * [클래스 설명]
 * 
 * @author sy59.gim
 * @since 2015. 5. 20
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrExtendDTO")
public class MbrExtendDTO extends Mbr {
	
    /** UID. */
    private static final long serialVersionUID = 266556246105397073L;
    
    /** 전화번호. */
    private String telNo;

}
