package com.plgrim.ncp.commons.data.order.sdo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ItPrItemSDO {

	private String	rctNo;//	영수증번호
	
	private String	seqNo;//	순번
	
	private String	matNo;//	품번
	
	private String	matQty;//	수량
	
	private String	resFa;//	담당 FA
	
	private String	stdPrc;//	정상소매가
	
	private String	reaPrcPr;//	실소매가
	
	private String	reaPrc;//	실소매금액
	
	private String	disAmt;//	할인금액
	
	private String	disRate;//	품번DC율
	
	private String	addRate;//	적립율/마일리지
	
	private String	cbSaveAmt;//	적립대상금액
	
	private String	fspCbA;//	사이버머니 적립액
	
	private String	fspCbU;//	사이버머니 사용액
	
	private String	meins;//	기본 단위
	
	private String	waers;//	통화 키
	
	private String	gb;//	P:Point M:Mileage
	
	private String	mlgAmt;//	적립마일리지

	
	
	

    private long webpntUseUntPrc; //퍼플코인 사용 단가
    private long webpntAccmlUntPrc; // 퍼플코인 적립 단가
    private String ordNo;
    private String godNo;
    private int ordGodTurn;
    private String partmalSectCd;
    
    // #54953 : 구매이력개선
    private String prdlstCd;		//품목 : item_cd
    private String sapBrndGrpId;	//품목브랜드그룹: brand
    private String colorCd;			//품번색상: color
    private String optCd1;			//품번사이즈: size
    
    private String erpBrndGrpId;

}
