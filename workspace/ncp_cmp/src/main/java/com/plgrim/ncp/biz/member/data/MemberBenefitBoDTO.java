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
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefAplTgt;
import com.plgrim.ncp.base.enums.MemberBenefitEnum.MemberBenefitStat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 회원 BackOffice DTO. 
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberBenefitBoDTO extends AbstractDTO {

    /** UID. */
    private static final long serialVersionUID = 1L;

	String defaultMemberBenefitStatCd = MemberBenefitStat.APRV_WAIT.toString();   
	
	/**
	 * 혜택 일련번호
	 */
	private java.lang.Long bnefSn;	

	/**
	 * 혜택 구분 코드
ㅁ. BNEF_SECT : 혜택 구분
   >. LOGIN : 로그인
   >. ONLNE_NEW_JOIN : 온라인 신규 가입
   >. UNITY_NEW_JOIN : 멤버십 신규 가입
   >. MBR_INFO_UDT : 회원 정보 변경
   >. PCH_COMPT : 구매 완료
   >. SNS_CNRS : SNS공유
   >. PCH_PS_WRITNG : 구매 후기 작성	 
	 */
	private String bnefSectCd;
	
	/**
	 * 혜택 일련번호 배열
	 */	
	private java.lang.Long[] rowBnefSn;
	/**
	 * 혜택 상세 구분 코드
ㅁ. BNEF_SECT : 혜택 구분
   >. LOGIN : 로그인
      >>. MOBILE_APP_DWLD_BNEF : 모바일 앱 다운로드 혜택
   >. ONLNE_NEW_JOIN : 온라인 신규 가입
      >>. NEW_MBR_JOIN : 신규 회원 가입
      >>. MARKT_RECPTN_AGR : 마케팅 수신 동의
   >. UNITY_NEW_JOIN : 멤버십 신규 가입
      >>. NEW_UNITY_MBR_JOIN : 신규 멤버십 회원 가입
   >. MBR_INFO_UDT : 회원 정보 변경
   >. PCH_COMPT : 구매 완료
   >. SNS_CNRS : SNS공유
   >. PCH_PS_WRITNG : 구매 후기 작성	 
	 */
	private String bnefDetailSectCd;
	
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
	 * 승인 관리자 ID	 
	 */
	private String aprvAdminId;
	/**
	 * 승인 일시	 
	 */
	private java.util.Date aprvDt;
	/**
	 * 중지 관리자 ID	 
	 */
	private String stpgeAdminId;
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
	 * 등록 일시	 
	 */
	private java.util.Date regDt;
	/**
	 * 수정자 ID
	 */
	private String udterId;
	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;
	
	
    /**
     * 회원혜택 목록 검색
     */
	private String[] srchBnefSectCd;
	
	private String[] srchAprvSectCd;	

	private String srchBnefTypeCd;

	private String srchLangCd;

	private String srchDateType;
	
	private String srchMallId;
	
	private String srchBegDate;
	
	private String srchEndDate;	
	
	private String srchBnefNm;
	
	private String srchMbrNo;
	
	private String[] vcMbrNo;
	
	private String srchMbrNm;
	
	private String srchBnefPymntSuccesYn;
	
    /**
     * 회원혜택 적용대상
     */
    private MbrBnefAplTgt mbrBnefAplTgt = new MbrBnefAplTgt();
    
    /**
     * 회원혜택 지급혜택 상세
     */
	private java.lang.Long dtlBnefSn;
	
	private java.lang.Integer dtlBnefDetailTurn;	
	
	private java.lang.Long[] dtlRowBnefSn;
	
	private java.lang.Integer[] dtlRowBnefDetailTurn;		

	private String dtlPrmNo;

	private java.util.Date dtlBnefBegDt;

	private java.util.Date dtlBnefEndDt;

	private String dtlAprvSectCd;	

	private String dtlAprvAdminId;

	private java.util.Date dtlAprvDt;

	private String dtlStpgeAdminId;

	private java.util.Date dtlStpgeDt;		

	private String dtlStpgeResnCont;

	private String dtlRegtrId;

	private java.util.Date dtlRegDt;
	
	private String dtlStrRegDt;

	private String dtlUdterId;

	private java.util.Date dtlUdtDt;
	
	private String dtlStrUdtDt;
	
	private String dtlStrBnefBegDt;

	private String dtlStrBnefEndDt;
	
	private java.math.BigDecimal dtlPntAmt;
	
	private String dtlPntAplResnCd;
	
	private java.math.BigDecimal dtlWebpntAmt;
	
	private String dtlWebpntResnCd;
	
	private String dtlWebpntDetailResnCd;
	
	private String dtlPymntResnDscr;
	
	private String dtlBnefDetailSectCd;
	   
	/**
	 * 혜택 종류 코드
ㅁ. 혜택 종류 : BNEF_TP_CD
   >. 멤버십포인트(MBSH_PNT), 
   >. P포인트(WEBPNT),
   >. 온라인쿠폰(ONLNE_CPN), 
   >. 온오프라인 쿠폰(ONOFLNE_CPN) 
	 */
	private String dtlBnefTpCd;
	/**
	 * 혜택 명	 
	 */
	private String dtlBnefTpNm;  
	
	/**
	 * P포인트 유효기간	 
	 */
	private String dtlWebpntValidDaycnt;
	
	
	public String[] getVcMbrNo() {
		
		if(srchMbrNo==null||"".equals(srchMbrNo)) {
			this.vcMbrNo = null;
		}
		else {
			this.vcMbrNo = srchMbrNo.split("\r\n");			
		}
		return this.vcMbrNo;
	}	
    
}
