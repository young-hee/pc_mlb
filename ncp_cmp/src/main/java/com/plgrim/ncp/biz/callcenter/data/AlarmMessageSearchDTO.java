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
package com.plgrim.ncp.biz.callcenter.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;


@Data
@EqualsAndHashCode(callSuper = false)
public class AlarmMessageSearchDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    private static final long serialVersionUID = 7062215063112244201L;

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */
	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	private String startDate;
	private String endDate;
	private String promsclStatCd;
	private String promsTgtNm;
	private String regtrId;
	private String chrgerCnfirmYn;
	
	private String counselTransferAlarmSearchStartDate;
	private String counselTransferAlarmSearchEndDate;
	private String transStatCd;
	private String transStatYn;
	private String transTgtCd;
	
	private String transRecrId;
	private String inputComId;
	private String selectChrgJobTpCd;
	private String selectTransRequstTpCd;
	private String inputRequstTpCd;

	private String chrgerConfirmYn;

	private String userId;

}
