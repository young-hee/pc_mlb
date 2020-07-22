package com.plgrim.ncp.commons.data.order.sdo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * T_TRANS_DLV
 * @author user
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TransDlvSDO {


	private String	vbeln; //	납품문서 번호
	
	private String	fpiaOr; //	패션피아주문번호
	
	private String	seqno; //	품목 번호
	
	private String	wadatIst; //	처리일자
	
	private String	matnr; //	상품 번호
	
	private String	menge; //	수량
	
	private String	meins; //	기본 단위
	
	private String	ztype; //	직배송여부
	
	private String	cbAmt; //	적립금마진액(+)
	
	private String	returnCd; //	처리결과
	
	private String	msg; //	메시지
	
	
	//일모카드로 추가(규격서에 없지만 as-is 소스에 있음 ㅡㅜ)
	private String waers;
	
	private String cardNo;	// 일모카드 카드번호
	
	private String empNo;	//일모카드 사원번호
	
	private String ilmoAmt;	//일모카드 할인금액
	
	private String zsto;	// 매장으로 직접 반품 X
	
	
	////////////SAP회수완료날짜 //////////////////
		
	private String exchangeDt ;
				
    private String namt; 	//퍼플코인(온라인포인트)


    private String zreDate;	//회수완료일

    private String zreId;	//회수처리자

    private String zreGr;	//센터반품즉시처리여부

    private String zflag;	//가반품Flag
}
