package com.plgrim.ncp.biz.member.result;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mypageMysizeResult")
public class MypageMysizeResult extends AbstractResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2925196561963982776L;
	
	private String mbrNo;		  // 회원번호
	private String mbrSizeTurn;   // 사이즈 순번
	
	private String mbrSizeNm;	  // 사이즈 이름
	private String mbrSizeClfcCd; // 회원사이즈 분류
	
	private String height;       // 키
    private String weight;       // 몸무게
    private String waist;        // 허리    
    private String body;         // 체형
	
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
