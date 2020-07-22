package com.plgrim.ncp.biz.claim.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGod;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;


@Data
@EqualsAndHashCode(callSuper=false)
public class ClaimUserInfoDetailResult extends AbstractResult {

	/**
	 * 
	 */
    private static final long serialVersionUID = -9176153274720026593L;

    private String cstmrMobile;	//전화번호
    
    private String cstmrTel;	//휴대전화번호
        
    private String mbrTpCdNm;	//회원유형코드명
    
    private String mbrTpCd;
    
    private String mbrStrbCdNm;	//회원속성코드명
    
    private String mbrNo;	//회원번호
    
    private String erpNo;	//회원번호
    
    private String mbrId;	//회원ID
    
    private String cstmrNm;	//구매자명
    
    private String cstmrEmail;	//구매자 이메일
    
    private String cpnCnt;		//쿠폰카운트
    
    private String membershipPoint;	//맴버쉽포인트
    
    private String eventPoint;	//이벤트 포인트
    
    private String webPntAmt;	//P포인트
    

    
    
    
	/**
     * 클레임 테이블 엔티티
     */
    Clm clm;
    
    /**
     * 클레임 입고상품 엔티티
     */
    ClmWrhsGod clmWrhsGod;
    
    /**
     * 회원 테이블 엔티티
     */
    Mbr mbr;
    
    /**
     * 주문테이블
     */
    Ord ord;

}
