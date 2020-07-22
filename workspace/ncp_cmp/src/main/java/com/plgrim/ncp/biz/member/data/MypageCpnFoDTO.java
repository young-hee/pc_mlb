package com.plgrim.ncp.biz.member.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageCpnFoDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6086216934852979873L;

	private String dcNm;		//할인명
	
	private String cpnNm;		//쿠폰제목
	
	private String validDay;	//유효기간(일)
	
	private String validHour;	//유효기간(시간)
	
	private String godNm;		//상품명
	
	private String prmNm;       // 프로모션 명
    
	private String cpnTpCd;     // 쿠폰 유형 명
    
	private String cpnStatCd;   // 쿠폰 상태 명
    
	private String validDate;   // 쿠폰 유효기간
	
	private String validTime;   // 쿠폰 남은시간
    
	private String dcSectNm;    // 할인유형
    
	private String cpnPubliMbdNm; // 쿠폰 발행 주체
	
	private String mbrCpnNo;	// 쿠폰번호
	
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
