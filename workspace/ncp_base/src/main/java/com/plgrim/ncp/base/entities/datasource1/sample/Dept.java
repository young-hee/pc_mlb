/* Copyright (c) 2015 plgrim, Inc.
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
 * beyondj2ee			2015.02.09     
 */
package com.plgrim.ncp.base.entities.datasource1.sample;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 부서 엔티티.
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("dept")
public class Dept extends AbstractEntity {
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6613541548464940805L;

	/** 부서 번호. */
	Integer deptNo;
	
	/** 부서 명. */
	String dName;
	
	String deptName;
	
}
