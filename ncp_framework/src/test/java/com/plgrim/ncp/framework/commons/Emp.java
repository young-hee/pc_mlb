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
package com.plgrim.ncp.framework.commons;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 사원 엔티티.
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class Emp  {

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

	// ~ Public Methods. ~~~~~~~~~~~~~~~
	// ~ Getter and Setter. ~~~~~~~~~~~~

}
