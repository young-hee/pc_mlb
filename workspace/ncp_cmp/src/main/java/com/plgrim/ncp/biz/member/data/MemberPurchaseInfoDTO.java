package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Jieun on 2016-09-22.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberPurchaseInfoDTO  extends AbstractDTO {

	private static final long serialVersionUID = -5104713041258152373L;
	
	private String mbrNo;		  // 회원번호
	private String someNaelImg;   // 썸네일 이미지
	private String brndNm;	  // 브랜드명
	private String godNm; // 상품명
	private String purchaseDate;	// 구매일자
    
}
