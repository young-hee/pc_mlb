package com.plgrim.ncp.commons.data.order.sdo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ItGiftSDO {

	private String	mandt;//	클라이언트
	
	private String	rctNo;//	영수증번호
	
	private String	giftNo;//	패션피아사은품번호
	
	private String	fspGiftQty;//	패션피아 사은품지급수량
	
	private String	fspItemQty;//	패션피아 사은품지급품목수량
	
	private String	fspGiftAmt;//	패션피아 사은품지급정상소매금액
	
	private String	meins;//	기본 단위
	
	private String	waers;//	통화 키
	
	private String	crnam;//	생성인
	
	private String	crdat;//	생성일
	
	private String	crtim;//	생성시간
	
	private String	upnam;//	최종변경인
	
	private String	updat;//	최종 변경일
	
	private String	uptim;//	최종변경시간 

		
}
