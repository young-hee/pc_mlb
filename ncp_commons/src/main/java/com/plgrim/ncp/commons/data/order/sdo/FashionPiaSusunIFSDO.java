package com.plgrim.ncp.commons.data.order.sdo;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class FashionPiaSusunIFSDO {

	private String r3nameR;	//sapJcoMode	//호출시 필수
	
	private String callerId;		//호출자 ID	//호출시 필수
	
	private String result;		//결과 코드
	
	
	private String	iWerks; //	매장	//호출시 필수
	
	private String	iSalfr; //	판매구분	//호출시 필수
	
	private String	iBpName; //	고객명	//호출시 필수
	
	private String	iTelNo; //	전화번호	//호출시 필수
	
	private String	iResNo; //	주민번호
	
	private String	iCardNo; //	카드번호
	
	private String	iDate; //	생년월일	//호출시 필수
	
	private String	iGender; //	남녀구분(M:남자, F:여자)	//호출시 필수
	
	private String	iMatnr; //	품번	//호출시 필수
	
	private String	iChasu; //	차수	//호출시 필수
	
	private String	iAnzfehler; //	수량	//호출시 필수
	
	private String	iRetAppPrc; //	실판매가액	//호출시 필수
	
	private String	iOtgrp; //	매장요청	//호출시 필수
	
	private String	iOtgrpt; //	매장요청사항	//호출시 필수
	
	private String	iAusbs; //	완료희망일
	
	private String	bdcmode; //	TRANSACTION USING...에 대한 처리모드
	
	private String	bdcupd; //	갱신세션
	
	private String	iBpNo; //	고객번호
	
	private String	iRctno; //	영수증번호
	
	private String	iSeqno; //	영수증일련번호
	
	private String	eQmnum; //	접수번호
	
	private String	eMsg; //	메세지
	
	private String	status; //	처리결과 : S, E
	
	private String	message; //	결과메시지
	
	private String	iYearGb; //	구입시기구분
}
