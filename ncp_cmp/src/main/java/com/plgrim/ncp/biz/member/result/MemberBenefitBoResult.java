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

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefAplTgt;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Instantiates a new memberBenefit bo result. 
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberBenefitBoResult extends AbstractResult {

    /** UID. */ 
	private static final long serialVersionUID = 1L;

    /** 목록. */
    private List<MemberBenefitBoResult> lists;
    
    private List<String> langList;
    
    private List<String> mallList;
    
    private String bnefLangCd;
    
    private String bnefLangNm;
    
    private List<String> deviceList;

    private List<String> memberTypeList;
    
    private List<String> memberAttributeList;
    
    private List<String> mobileAppList;
    
    private List<String> chnnlRecptnAgrSectList;

    /** 목록 갯수. */
    private long listCount;
    
	/**
	 * 혜택 일련번호
	 */
	private java.lang.Long bnefSn;    
	
	/**
	 * 혜택 구분 코드
	 */
	private String bnefSectCd;
	
	/**
	 * 혜택 구분 코드명
	 */
	private String bnefSectCdNm;
	
	/**
	 * 혜택 상세 구분 코드	 
	 */
	private String bnefDetailSectCd;
	
	/**
	 * 혜택 상세 구분 코드명	 
	 */
	private String bnefDetailSectCdNm;
	
	/**
	 * 혜택 명	 
	 */
	private String bnefNm;
	/**
	 * 혜택 시작 일자 
	 */
	private java.util.Date bnefBegDt;
	/**
	 * 혜택 시작 시각
	 */
	private String bnefBegHour;	
	/**
	 * 혜택 시작 분
	 */
	private String bnefBegMinute;		
	/**
	 * 혜택 종료 일자
	 */
	private java.util.Date bnefEndDt;
	/**
	 * 혜택 종료 시각
	 */
	private String bnefEndHour;	
	/**
	 * 혜택 종료 분
	 */
	private String bnefEndMinute;
	/**
	 * 승인 구분 코드	 
	 */
	private String aprvSectCd;
	
	/**
	 * 승인 구분 코드명	 
	 */
	private String aprvSectCdNm;	
	
	/**
	 * 승인 관리자 ID	 
	 */
	private String aprvAdminId;
	/**
	 * 승인 관리자 이름	 
	 */
	private String aprvAdminNm;	
	/**
	 * 승인 일시	 
	 */
	private java.util.Date aprvDt;
	/**
	 * 중지 관리자 ID	 
	 */
	private String stpgeAdminId;
	/**
	 * 중지 관리자 이름
	 */
	private String stpgeAdminNm;	
	/**
	 * 중지 일시	 
	 */
	private java.util.Date stpgeDt;	
	/**
	 * 중지 사유 내용	 
	 */
	private String stpgeResnCont;
	/**
	 * 등록자 ID
	 */
	private String regtrId;
	/**
	 * 등록자 이름
	 */
	private String regtrNm;	
	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;
	/**
	 * 수정자 ID
	 */
	private String udterId;
	/**
	 * 수정자 이름
	 */
	private String udterNm;	
	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;
	
    /**
     * 프로모션
     */
    private Prm prm;

    /**
     * 프로모션 확장
     */
    private PrmExtend prmEx;
    
    /**
     * 회원혜택 적용대상
     */
    private List<MbrBnefAplTgt> mbrBnefAplTgtList;
    
    private String mallId;
    private String mallNm;
    private String langNm;	 
    
    /**
     * 회원혜택 상세 지급혜택
     */
    
	private String dtlBnefSn;

	private String dtlPrmNo;
	
	private String dtlPrmNm;

	private java.util.Date dtlStrBnefBegDt;

	private java.util.Date dtlStrBnefEndDt;

	private String dtlAprvSectCd;
	
	private String dtlAprvSectCdNm;	
	
	private String dtlAprvAdminId;
	
	private String dtlAprvAdminNm;

	private java.util.Date dtlAprvDt;

	private String dtlStpgeAdminId;
	
	private String dtlStpgeAdminNm;

	private java.util.Date dtlStpgeDt;	

	private String dtlStpgeResnCont;

	private String dtlRegtrId;
	
	private String dtlRegtrNm;	

	private java.util.Date dtlRegDt;
	
	private java.util.Date dtlStrRegDt;

	private String dtlUdterId;
	
	private String dtlUdterNm;

	private java.util.Date dtlUdtDt;
	
	private java.util.Date dtlStrUdtDt;
	
	private java.math.BigDecimal dtlPntAmt;
	
	private String dtlPntAplResnCd;
	
	private java.math.BigDecimal dtlWebpntAmt;	
	
	private String dtlPymntResnDscr;
	
	private String dtlWebpntValidDaycnt;
	
	private String dtlBnefDetailTurn;	
	
	private String cpnIssuMthdCd;
    
	private String prmStatCd;

	private String dtlBnefTpCd;

	private String dtlBnefTpNm;
}

