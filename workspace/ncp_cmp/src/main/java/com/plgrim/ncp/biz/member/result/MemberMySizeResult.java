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
 * @since       2015. 6. 18       
 */
package com.plgrim.ncp.biz.member.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefAplTgt;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Instantiates a new memberBenefit bo result. 
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberMySizeResult extends AbstractResult {

    /** UID. */
	private static final long serialVersionUID = 1L;

	/** 회원번호 */
	private String mbrNo;

	/** 나의 사이즈 그룹 명 */
	private String mbrSizeClfcCdNm;

	/** 나의사이즈 이름 */
	private String mbrSizeNm;

	/** 키 */
	private String height;

	/** 몸무게 */
	private String weight;

	/** 허리둘레 */
	private String waist;

	/** 체형 */
	private String body;

}

