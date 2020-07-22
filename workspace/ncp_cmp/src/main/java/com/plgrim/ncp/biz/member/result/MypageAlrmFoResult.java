package com.plgrim.ncp.biz.member.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageAlrmFoResult extends AbstractResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2925196561961792776L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private String reWhsgNtcnSn;   // 재입고알람 일련번호
	
	private String godTurn;		   // 상품순번
	
	private String godNo;		   // 상품번호
	
	private String setGodNo;	   // 세트상품번호
	
	private String brndId;		   // Brand Id
	
	private String brndNm;		   // Brand NM
	
	private String colorNm;		   // 색상명
	
	private String godNm;          // 상품명
	
	private String imgUrl;         // 상품IMG
	
	private String regDt;          // 등록일자
	
	private String godSaleSectCd;  // 상품 Sale 상태
	
	private String itmNm; 		   // 옵션명
	
	private String ntcnComptYn; 	   // 옴션상태
	
	private String colorStyleCd;   // 색상코드
	
	
	private String mobilAreaNo;     //모바일 번호  
	
	private String mobilTlofNo;     
	
	private String mobilTlofWthnNo; 
	
	private String clorChipImgUrl;		//색상URL

	private String stockStatus; // 입고상태

	private String linkGodNo; // 상품상세 링크를 위한 상품번호

	private MypageAlrmFoResult reord; // 리오더상품정보

	
	
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
