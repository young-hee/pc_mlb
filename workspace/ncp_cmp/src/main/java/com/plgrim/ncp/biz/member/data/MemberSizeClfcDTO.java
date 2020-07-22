package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Jieun on 2016-09-22.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberSizeClfcDTO  extends AbstractDTO {

	private static final long serialVersionUID = -5104713041258152373L;

	private String mbrNo;           // 회원번호
	private String mbrSizeTurn;		// 사이즈 순번

	private String mbrSizeNm;       // 사이즈 이름
	private String mbrSizeClfcCd;   // 회원사이즈 분류

	private String height;        	// 키
	private String weight;        	// 몸무게
	private String waist;           // 허리
	private String body;         	// 체형
    
    
	/**
	 * 등록자 ID
등록한 관리자 번호	 
	 */
	private String regtrId;
	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;
	/**
	 * 수정자 ID
수정한 관리자 번호	 
	 */
	private String udterId;
	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;    
    
}
