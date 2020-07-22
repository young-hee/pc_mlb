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
 * @since       2015. 3. 17       
 */
package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.sample.Dept;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;

/**
 * 클레임 조회 DTO.
 *
 * @author shhenry.choi
 * @since 2015. 3. 18
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimOrdGftSearchDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	static final long serialVersionUID = 4403992496214821087L;

    private String clmNo;                                   /* 클레임번호                    */
    private String ordNo;                                   /* 주문번호                      */
    private List<OrdGod> ordGodList;


    private String partmalSectCd;                           /* 입점 구분 코드  >. 자사 : MCOM   >. 입점 : PARTMAL              */

}
