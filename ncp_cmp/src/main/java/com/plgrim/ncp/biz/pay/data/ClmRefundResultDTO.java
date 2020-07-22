package com.plgrim.ncp.biz.pay.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmRefundResultDTO extends AbstractDTO {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = -7345704659382282741L;

	private String paymncd;	//결제수단

	private String paymncdNm;	//결제수단명
	
	private String paycrncypayamt;//최초결제금액	
	
	private String remainpayamt;//잔여결제금액
	private String remainpayamtEx;//잔여결제금액

	private String beforePayamt;//이전 취소금액
	private String beforePayamtEx;//이전 취소금액

	private String prc;//환불금액
	
	private String prcEx;//환불금액
	
	private String paycrncypayamtEx;//최초결제금액		
			
	
	private String upperpayno;//상위결제번호
	
	private String refundStatus;// 대기 : W / 완료 : S / 실패 : F 
	
	private String rfdStatCdOri;//환불상태코드 결제취소 요청 후 리턴 코드
	private String rfdStatCd;//환불상태코드 결제취소 요청 후 리턴 코드 (결제환불의 코드)
	private String rfdStatCdNm;//환불상태코드 결제취소 요청 후 리턴 코드 (결제환불의 코드)
	private String rfdResn;//사유 결제취소 요청후 리턴 메세지
	
	private String rfdErrCont;

	private String mpayMnYn; //대표결제수단여부
}
