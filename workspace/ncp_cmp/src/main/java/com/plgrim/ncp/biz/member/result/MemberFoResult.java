package com.plgrim.ncp.biz.member.result;

import java.util.List;
import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;

@Data
@EqualsAndHashCode(callSuper=false)
public class MemberFoResult extends AbstractResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 204090408348213371L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	Mbr mbr;
	
	MbrCrtfc mbrCrtfc;
	
	MbrGrd mbrGrd;
	
	List<MbrStplatAgr> mbrStplatAgrs;
	
	
	/**
	 * 외국인 회원 인증 여부 확인
	 * */
    int mbrCrtfcCnt;

	/**
	 * 회원 탈퇴 관련
	 */
	int ordCnt;
	
	int ordCntKor;

	int ordCntEng;

	int ordCntChi;

	int cpnCnt;
	
	int wishCnt;
	
	int claimCnt;
	
	int claimCntCancel;
	
	int claimCntExchange;
	
	int claimCntReturn;
	
	long reserve;
	
	long eventReserve;
	
	long milage;
	
	boolean isPointEarned;
	
	String mobilNo;
	
	String telNo;
	
	String otelNo;
	
	String maskMobilNo;
	
	String maskEmail;

	/** 회원 P포인트 Entity */
	private MbrWebpntHistExtend mbrWebpntHistExt;

    /** 회원 P포인트 Entity */
    private MbrWebpntHistExtend mbrWebpntHist;
    
    private String stplatCd;
    
    private String stplatIemAgrYn;

    String joinDtStr;
    
    String secsnDtStr;
    
    String erpConnectFlag;
    
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
