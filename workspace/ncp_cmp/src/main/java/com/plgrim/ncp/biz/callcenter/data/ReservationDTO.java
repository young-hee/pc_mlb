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

/**
 * 샘플 DTO.
 *
 * @author shhenry.choi
 * @since 2015. 3. 18
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ReservationDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	static final long serialVersionUID = 4403992496214821087L;

	

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
	
	private String cnsltSn;
	
	private String resveTelTurn;
	
	private String resveTelConfigYn;
	
	private String resveTelProgrsDt;
	
	private String resveTelConfigResnCd;
	
	private String resveTelProgrsStatCd;
	
	private String regtrId;
	
	private String regDate;
	
	private String udterId;
	
	private String udtDate;
	
}
