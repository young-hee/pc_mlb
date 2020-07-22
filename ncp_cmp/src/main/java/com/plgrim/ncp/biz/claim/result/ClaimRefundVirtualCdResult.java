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
package com.plgrim.ncp.biz.claim.result;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 클레임 배치 조회 DTO.
 *
 * @author shhenry.choi
 * @since 2015. 3. 18
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimRefundVirtualCdResult extends AbstractDTO {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    static final long serialVersionUID = 1L;

	private String rfdBnkNm;
	private String rfdBnkAcctNo;
	private String stdrCrncyPayAmt;
	private String payMnCd;

	//SMS개선 by cannon : 2016.07.17
	private String thisMonthsPayYn;	//당월결제여부
	private String oriPayDealTpCd;	//원주문결제상태

}
