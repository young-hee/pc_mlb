/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 18       
 */
package com.plgrim.ncp.biz.member.result;

import java.util.Date;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPntIntrlckHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Instantiates a new member bo result.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberBoResult extends AbstractResult {

    /** UID. */
    private static final long serialVersionUID = 2097822591560688187L;
    
    /**  회원 Entity. */
    private MbrExtend mbr;
    
    /**  회원 인증 Entity. */
    private MbrCrtfc mbrCrtfc;   
    
    /**  회원 배송지 Entity. */
    private MbrDlvspExtend mbrDlvsp;
    
    /**  회원 개인정보 변경 이력 Entity. */
    private MbrPsnlInfoModHist mbrPsnlInfoModHist; 
    
    /**  회원 발급 쿠폰 Entity. */
    private MbrIssuCpn mbrIssuCpn;
    
    /**  회원 포인트 연동 이력. */
    private MbrPntIntrlckHist mbrPntIntrlckHist;

    /** 회원 P포인트 Entity */
    private MbrWebpntHistExtend mbrWebpntHist;
    
    /**  회원 ID 연계 Entity. */
    private MbrIdCntc mbrIdCntc;       

    /**  휴대폰 번호. */
    private String mobilNo;               

    /**  회사전화 번호. */
    private String otelNo;                

    /**  전화 번호. */
    private String telNo;                 

    /**  법인전화 번호. */
    private String cprtelNo;              

    /**  관리자 명. */
    private String adminNm;               

    /**  등록자 명. */
    private String regtrNm;               

    /**  등록자 정보. */
    private String regtrInfo;             
    
    /**  총 주문건. */
    private String pchCount;              

    /**  총 주문금액. */
    private String pchAmt;                  

    /**  회원 상태 코드명. */
    private String mbrStatNm;             

    /**  회원 유형 코드명. */
    private String mbrTpNm;               

    /**  회원 속성 코드명. */
    private String mbrAtrbNm;             

    /**  가입 언어 코드명. */
    private String joinLangNm;            

    /**  가입 디바이스 코드명. */
    private String joinDvcNm;             

    /**  가입 몰 명. */
    private String joinMallNm;              

    /**  탈퇴 구분 코드명. */
    private String secsnSectNm;              

    /**  탈퇴 사유 코드명. */
    private String secsnResnNm;              

    /**  유입 경로. */
    private String inflowNm;
    
    /**  소속 그룹사 명. */
    private String psitnGrpcoNm;

    /**  로그 발생 일자. */
    private Date logOccurDate;             

    /**  회원 인증 일자(통합회원 가입일). */
    private Date mbrCrtfcDate;             

    /**  강제탈퇴 처리자 명. */
    private String enfrcsecsnDspsNm;           

    /**  회원 메모. */
    private String memoCont;                
    
    /**  개인정보 수정 구분 명. */
    private String psnlInfoUdtSectNm;          

    /**  프로모션 명. */
    private String prmNm;                  

    /**  쿠폰 유형 명. */
    private String cpnTpNm;                

    /**  쿠폰 상태 명. */
    private String cpnStatNm;              

    /**  쿠폰 유효기간. */
    private String validDate;              

    /**  할인유형. */
    private String dcSectNm;               

    /**  쿠폰 종류 코드. */
    private String cpnKndCd;
    
    /**  쿠폰 종류 코드명. */
    private String cpnKndNm;

    /**  적립금 사유 코드. */
    private String savMnyResnNm;

    /**  로그인횟수. */
    private long loginCnt;

    /**  상품평 개수. */
    private long godEvlCnt;

    /**  SNS 개수. */
    private long snsCnt;
    
    /**  기존 대표 배송지 여부. */
    private String orgBaseDlvspYn;
    
    /**  회원 속성 코드명.  ERP멤버십 등급 */
    private String erpGrdNm;

    /**  고객국가. */
    private String mbrNationNm;
    
    /**  회원 ID 연계 Entity. */
    private MbrIdCntc faceBukMbrIdCntc;       

    /**  회원 ID 연계 Entity. */
    private MbrIdCntc spassMbrIdCntc;

    /**  회원 등급. */
    private String onlneGrdNm;
}

