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
package com.plgrim.ncp.biz.claim.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGod;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.sys.SysLang;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMall;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClaimPayRefundResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 8569202923397222025L;

    /**
     * ㅁ. 결제구분 : PAY_MN
		   >. 신용카드결제 : CREDT_CARD_PAY
		   >. 실시간계좌결제 : RLTM_BNK_ACCT_PAY
		   >. 가상계좌결제 : VIRTL_BNK_ACCT_PAY
		   >. 무통장결제 : NON_BNKBOK_PAY
		   >. 상품권결제 : GFCT_PAY
		   >. 통합적립금결제 : UNITY_SAV_MNY_PAY
		   >. 장바구니쿠폰 : BSK_CPN
		   >. 배송비쿠폰 : DLV_CST_CPN
		   >. 웹포인트결제 : WEB_PNT_PAY
		   >. 휴대폰결제 : MOBIL_PON_PAY
		   >. 상품쿠폰 : GOD_CPN
		   >. 카카오페이 : KKO_PAY
     */
    //결제 수단 코드
	private String payMnCd;	
	
	//결제 수단 코드 명
	private String payMnCdNm;	

	//결제통화 결제 금액
	private String payCrncyPayAmt;
	
	//잔여결재금액
	private String remainPayAmt;
	
	//환불금액
	private String rfdAmt;
	
	//비고 환불결과
	private String rfdResultCd;
	
	//비고 환불결과
	private String rfdResult;
	
	

    
}
