package com.plgrim.ncp.commons.data.order.sdo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * YRSD_FP_RETURN_STORE_CHECK
 * 패션피아에서 매장반품건 입고여부 체크
 * @author user
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TOrderSDO {

	private String	fpiaOr; //	패션피아주문번호 /호출시 필수
	
	private String	seqno; //	SD 문서의 품목 번호 /호출시 필수
	
	private String	returnCd; //	결과
	
	private String	store; //	매장
	
	private String	ebeln; //	STO문서
	
	private String	vbeln; //	납품문서
	
	private String	zcmYn; //	협조여부
	
	private String	zgrYn; //	센터입고여부

}
