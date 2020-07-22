package com.plgrim.ncp.commons.data.order;

import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class KcpCommonReceiveDTO extends AbstractOrderDTO {
	

	
	String site_cd;  // 사이트 코드
    String tno;// KCP 거래번호
    String order_no;// 주문번호 - 내부는 결제번호
    String tx_cd;// 업무처리 구분 코드
    String tx_tm;// 업무처리 완료 시간
    String ipgm_name;// 주문자명
    String remitter;// 입금자명
    String ipgm_mnyx;// 입금 금액
    String bank_code;// 은행코드
    String account;// 가상계좌 입금계좌번호
    String op_cd;// 처리구분 코드
    String noti_id;// 통보 아이디
    String cash_a_no;// 현금영수증 승인번호
    String cash_a_dt;// 현금영수증 승인시간
	String cash_no;// 현금영수증 거래번호
	String st_cd;// 구매 확인 코드
	
}
