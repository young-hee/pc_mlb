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
 * Henry				2015.04.09         
 */
package com.plgrim.ncp.biz.callcenter.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class CounselTransferlResult extends AbstractResult {/**
	 * 
	 */
    private static final long serialVersionUID = 9113553783478319970L;
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
   
    private String cnsltSn;					// 상담 이력 번호 
    private String cnsltDetailTurn;			// 상담 상세 순번
    private String transRequstDt;			// 이관 요청 일시
    private String transStatNm;				// 이관 처리 상태
    private String transTgtNm;				// 이관 대상
    private String transTgtAdminNm;				// 이관 대상:담당자이름
    private String transRequstTpNm;			// 이관 요청 유형
    private String doiNm;					// 중요도
    private String transDelay;				// 경과일
    private String transComptDt;			// 이관처리 완료일시
    private String inqTpNm ;				// 문의 유형
    private String cnsltReqstMbrNo;			// 회원명
    private String cnsltReqstMbrIdAndNo;    // 회원 번호
    private String ordNo;					// 주문 번호
    private String godNo;					// 상품 번호
    private String partmalSectCd; 			// 상품 구분 자사, 입점사
    private String cnsltStatNm;
    private String promsclDt;				// 약속 콜 일시
    private String transRqesterNm;
    private String cnsltDt;
    private String cstmrClmNm;
    private String cnsltKndNm;
    private String chrgJobTpNm;
    private String cnsltTgtNm;  
    private String dvcNm;					
    private String langNm;					
    private String mallNm;					
    
    private String chrgJobTpCd;             
    private String transRequstCont;         
    private String transAtchmnflTurn;       
    private String transAtchFile_nm;        
    private String transAtchfileUrl;        
    
}
