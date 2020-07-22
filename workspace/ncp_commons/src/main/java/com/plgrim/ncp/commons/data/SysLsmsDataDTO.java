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
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysSmsEmailTxt;


@Data
@EqualsAndHashCode(callSuper = false)
public class SysLsmsDataDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private SysSmsEmailTxt sysSmsEmailTxt; // SMS문구
	private String name; // 이름
	private String tranPhone; // 수신번호
	private String tranCallback; // 발신번호
	private String tranMsg; // 메시지
	private String tranId; // 회원ID
	
}
