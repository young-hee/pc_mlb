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
public class ClaimBatchTargetDTO extends AbstractDTO {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    static final long serialVersionUID = 4403992496214821087L;


	private String clmNo;
	private String ordNo;
	private String clmTpCd;
	private String clmStatCd;
	private String erpOrErpYn;
	private String erpReErpYn;
	private String erpTempErpUse;
	private String erpTempErpInc;
	private String ordErpTempErpUse;
	private String ordErpTempErpInc;
	private String ordErpOrErpYn;
	private String erpRetYn;
	private String bpNo;
	private String partmalSectCd;
	private String reasonCb;
	private String menge;
	private String mbrEmplNo;
	private String pkupClmYn;
	private String payMnCd;
	private String ordTpCd;
	private String dealTpCd;
	
	/* 온오프쿠폰 임시사용 컬럼*/
	private String erpTempCpnErpUse;
	private String ordErpTempCpnErpUse;
	
	/**
	 * 'execRtnClmProc' 메소드에서 호출여부
	 * 	- 반품 이후 재고 이동 시 '반품접수매장'으로 이동시키기 위한 구분
	 */
	private boolean isExecShopRtnClmProc;

	/**
	 * 회원번호
	 * 	- 포인트 연동 이력 등록 시 사용
	 */
	private String mbrNo;
	
	private String virtlDlvComptYn;
}
