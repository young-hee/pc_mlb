package com.plgrim.ncp.biz.helpdesk.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelpdeskRepairResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	Mbr mbr;
	
	private String mobilNo;
	
	private String mbrNm;
	
	private String rdGroup01;
	
	private String erpCstmrNo;
	
	private String iKunnr;		// ERP 고객 아이디
	
	private String iQmnum;		// 접수번호
	
	private String qmnum; 		// 통지 번호
	
    private String fenum; 		// 통지인쇄 품목번호
    
    private String kunum; 		// 고객 번호
    
    private String name2; 		// 이름 2
    
    private String telf1; 		// 첫번째 전화번호
    
    private String astxt; 		// 사용자 상태 조회 필드
    
    private String astxtNm; 	// 길이 40의 문자필드
    
    private String bautl; 		// 조립품
    
    private String anzfehler; 	// 발견된 결함 수
    
    private String fegrp; 		// 코드그룹 - 문제
    
    private String fecod; 		// 문제 또는 손상 코드
    
    private String kurztext1; 	// 코드그룹의 간단한 내역
    
    private String kurztext2; 	// 코드에 대한 내역 (40문자까지)
    
    private String customwr; 	// 문서통화상의 비용
    
    private String waers; 		// 통화 키
    
    private String qmdat; 		// 통지일
    
    private String pster; 		// 계획된 시작일
    
    private String ausbs; 		// 오작동 종료 (일자)
    
    private String qmnam; 		// 통지보고인이름
    
    private String faNme; 		// FA 성명
    
    private String pKbetr; 		// 비율(조건 금액 또는 백분율)
    
    private String mngrp; 		// 코드그룹 - 액티비티
    
    private String kurztext3; 	// 코드그룹의 간단한 내역
    
    private String peter; 		// 계획 종료일
    
    private String parnr; 		// 공급업체 또는 채권자의 계정 번호
    
    private String bpName; 		// 성
    
    private String delivD; 		// 매장발송일
    
    private String deliveD; 	// 상담실완료일
    
    private String mncod; 		// 액티비티 코드
    
    private String retAppPrc; 	// 반품인정가
    
    private String reciptD; 	// 상담실접수일
    
    private String shpTelNo; 	// 매장전화
    
    private String fordat; 		// 상담실예상일
    
    private String parnr2; 		// 직무책임자 (파트너번호)
    
    private String adrnr; 		// 주소
    
    private String uname; 		// 성
    
	
    private String result;	        //처리 결과.
	
	private String callerId;        //호출자 ID.
	
	private String type;            //메시지 유형
	
	private String id;              //ID
	
	private String number;          //ERROR NUMBER	
	
	private String message;         //메시지 텍스트
	
	private String logNo;          	//어플리케이션 로그: 로그 번호
	
	private String logMsgNo;      	//어플리케이션 로그: 내부 메시지 일련 번호
	
	private String messageV1;      	//내부 메시지1
	
	private String messageV2;      	//내부 메시지2
	
	private String messageV3;      	//내부 메시지3	
	
	private String messageV4;      	//내부 메시지4
	
	private String parameter;       //매개변수 이름
	
	private String row;             //매개변수라인
	
	private String field;           //매개변수 필드
	
	private String system;          //메시지를 발생시킨 논리 시스템
	
	private String mobilNationNo;
	
	
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
