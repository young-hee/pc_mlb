/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 7. 15       
 */
package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormMbrPsnlInfoLogDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private String usefBegDt; // 조회시작일
	private String usefEndDt; // 조회종료일
	private String psnlInfoUsefComCd; // 수탁업체명
	private String mbrId; // 회원ID
}
