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

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

/**
 * [클래스 설명]
 * 
 * @author sy59.gim
 * @since 2015. 6. 19
 */
@Data
@Alias("memberGridDTO")
@EqualsAndHashCode(callSuper=false)
public class MemberGridDTO extends AbstractDTO {

	
	/** UID. */
    private static final long serialVersionUID = -6935489922628022974L;
    
	/** 그리드 DTO 리스트 */
	private List<MemberBoDTO> memberGridList;
	

}
