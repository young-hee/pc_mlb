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
public class ClaimInfoDetailResult extends AbstractResult {

	
    /**
	 * 
	 */
    private static final long serialVersionUID = 9201754039142189327L;

    //주문유형
    private String ordTp;
    
    //주문유형코드
    private String ordTpCd;
    
    //몰 이름
    private String mallNm;
    
    //언어
    private String langCdNm;
    
    //클레임신청일
    private String clmDt;
    
    //클레임 접수일
    private String rceptDt;
    
    //클레임 완료일
    private String comptDt;
    
    //주문번호
    private String ordNo;
    
    //클레임번호
    private String clmNo;
    
    //클레임상태
    private String clmStatCd;
    
    //클레임상태 이름
    private String clmStatCdNm;
    
    //택배사 수거지시 여부
    private String hdryYn;
    
    //가상주문번호
    private String virtlOrdNo;        
    
    private String rfdBnkNm;
    
    private String rfdBnkCd;
    
    private String rfdBnkAcctNo;
    
    private String rfdAcnthldrNm;
    
    private String pgYn;
    
    // 선진행 구분 코드
    private String clmPreprogrsSectCd;
}
