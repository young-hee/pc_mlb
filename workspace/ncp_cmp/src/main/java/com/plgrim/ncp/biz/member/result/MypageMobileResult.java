package com.plgrim.ncp.biz.member.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageMobileResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

/**
	 * 
	 */
	private static final long serialVersionUID = 3120695999537165023L;

private MbrDlvsp mbrDlvsp = new MbrDlvsp();						// 회원 배송지
    
    private String member;                                          // 회원 기본정보 변경여부
    
    private MbrIssuCpn mbrIssuCpn;                    				// 회원 발급 쿠폰
    
    private Mbr mbr;                    							// 회원 발급 쿠폰
    
    private String prmNo;                    						//  프로모션 번호
    
    private String aplGodSecCd; 									// 쿠폰 상품 Type
    
    private String srchType; 										// Search Type
    
    private String srchKeyword; 								    // Search Keyword
    
	private String dateStart;    							    	//  시작일
	
	private String dateEnd;    										//  종료일
	
	private String srchMonth;    									//  Search Month
    
    private String waybillNumber;       							// 국내 송장번호
    private String dlvComCd;       									// 택배사 코드
    
    private String ordNo;                    						//  주분 번호
    
    private String erpSeq;                    						//  ERP 순번
    private String mobilNo;         								// 휴대폰번호
    private String telNo;           								// 전화번호
    
    private String wishlstSn;	   									// 위시리스트 일련번호
	
    private String godTurn;		   									// 상품순번
	
	private String prcSectCd;		   								// 가격구분코드
	
	private String ivCbAmt;		   									// 마일리지 포인트
	
	private String todayGodSn;                                       // 최근본상품 일련번호
	
	private String mbrCpnNo;									    // 쿠폰번호
	
	private String language;									    // 언어
	
	private List<String> godNoList;									// 상품번호 리스트
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
