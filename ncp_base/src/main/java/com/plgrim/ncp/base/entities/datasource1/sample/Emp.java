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

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 사원 엔티티.
 */

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("emp")
public class Emp extends AbstractEntity {

	// ~ Instance fields. ~~~~~~~~~~~~~~
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9176650206580189557L;

	/** 사원 번호. */
	Integer empno;

	/** 사원 이름. */
	String ename;

	/** 담당업무. */
	String job;

	/** 급여. */
	Integer sal;

	/** 부서 번호. */
	Integer deptNo;
	
	Date retireDate;

	// ~ Public Methods. ~~~~~~~~~~~~~~~
	// ~ Getter and Setter. ~~~~~~~~~~~~

}
