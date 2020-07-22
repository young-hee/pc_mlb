/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim..
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.biz.member.data;

import java.util.ArrayList;
import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.*;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPntIntrlckHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPntIntrlckHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 회원 BackOffice DTO.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberBoDTO extends AbstractDTO {

    /** UID. */
    private static final long serialVersionUID = 1L;

    /* === Entity START ===================================================  */
    /** 회원. */
    private Mbr mbr;
    
    private String[] mbrNos;

    /** 회원 인증. */
    private MbrCrtfc mbrCrtfc;

    /** 회원 배송지. */
    private MbrDlvspExtend mbrDlvsp;

    /** 회원 개인정보 변경 이력. */
    private MbrPsnlInfoModHist mbrPsnlInfoModHist;

    /** 회원 발급 쿠폰. */
    private MbrIssuCpn mbrIssuCpn;

    /** 회원 포인트 연동 이력. */
    private MbrPntIntrlckHist  mpih;

    /** 회원 환불 계좌. */
    private MbrRfdBnkAcct mrba;
    /* === Entity ENT   ===================================================  */
    
    
    /* === 공통 START =====================================================  */
    /** 휴대전화 번호. */
    private String mobilNo;

    /** 전화번호. */
    private String telNo;

    /** 법인 전화번호. */
    private String cprtelNo;
    /* === 공통 END   =====================================================  */

    /* === 회원 전체 목록 START ===========================================  */
    /** 가입 몰 ID. */
    private List<String> searchJoinMallId;

    /** 가입경로1. */
    private String joinChnnlCd;

    /** 가입경로2. */
    private String joinLangCd;

    /** 가입 유형 - 1:온라인, 2:통합. */
    private String searchDateType;

    /** 가입 일시. */
    private String searchStartDate;

    /** 가입 일시. */
    private String searchEndDate;
    /* === 회원 전체 목록 END   ===========================================  */
   
    /* === 회원 상세 START ================================================  */
    /** 기존 회원명. */
    private String orgMbrNm;

    /** 회원 인증 유형 - 통합 회원 인증:UNITY, 임직원 인증:EMP. */
    private String searchCrtfcTp;

    /** 임시 비밀번호 발송 방법 - e-mail:email, 휴대전화:sms. */
    private String sendMethod;

    /** 회원 메모. */
    private String memoCont;

    /** 회원 상태. */
    private String mbrStat;
    
    /** 회원멤버십 등급 */
    private String erpGrdCd ;
    
    
    /* === 회원 상세 END   ================================================  */
  
    /* === 탈퇴 회원 관리 START ===========================================  */
    /** 탈퇴유형. */
    private List<String> searchSecsnSectCd;
    /* === 탈퇴 회원 관리 END   ===========================================  */
    
    /* === 온라인클럽 회원 관리 START =====================================  */
    /** 조회구분(ALL : 온라인클럽회원전체조회, LACK : 분기미충족회원조회) . */
    private String searchType;

    /** 활동기간(-1 : 지난분기, 0 : 이번분기). */
    private String searchQuarter;

    /** 로그인 개수. */
    private int onlineClubLoginCnt;

    /** 상품평 개수. */
    private int onlineClubGodEvlCnt;

    /** SNS 개수. */
    private int onlineClubSnsCnt;
    /* === 온라인클럽회원 회원 관리 END   =================================  */
    
    /** 포인트 유형. */
    private String cbType;

    /** 발송 방법. */
    private String sendType;

    /** sms 콜백번호    . */
    private String smsCallback;

    /** sms 메시지. */
    private String smsMessage;

    /** 메일 제목. */
    private String emailTitle;

    /** 메일 유형. */
    private String emailAutoType;

    /** 보낸사람 이름. */
    private String emailSendNm;

    /** 보낸사람 메일주소. */
    private String emailSendEmail;
    
    /** 회원주소정보 변경 여부 */
    private String mbrBaseAddrYn;

    /** 대표배송지 변경 여부 */
    private String baseDlvspYn;
    
    /**
     * 마케팅동의여부
     */
    private String colctAgr;
    
    /**
     * 제3자 제공 및 국외 이전 동의여부 
     */
    private String thprOfferAgr;

    private List<String> searchListGubun = new ArrayList<String>();

    /** 회원혜택이력 혜택 지급 구분 코드 */
    private String mbrBnefPymntSectCd;

    /**  회원 등급. */
    private String onlneGrdCd;
   
}
