package com.plgrim.ncp.commons.data.order;

import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class KcpCancelParamDTO  extends AbstractOrderDTO {
	
	/** 망취소여부 */
	boolean net_cancel;
	
	private String siteCd;
	
	private String siteKey;
	
	
	
	/** KCP 거래 고유 번호 */
	private String tno;
	
	/** 변경 요청 종류 */
	private String mod_type;
	
	
	
	/** 쇼핑몰 주문번호 */
	private String ordr_idxx;
	
	/** 변경 사유 */
	private String mod_desc;
	
	/** 취소요청 금액 */
	private String mod_mny;
	
	/** 부분취소 이전에 남은 금액 */
	private String rem_mny;
	
	
	
	/** 환불여부 */
	private String refund_yn;
	
	/** 은행 코드 */
	private String mod_bankcode;
	
	/** 발급 계좌 */
	private String mod_account;
	
	/** 예금주 */
	private String mod_depositor;
	
	
	
	/** 에스크로 보조코드 - 구매확인 후 취소시 구분값 */
	private String escr_cncl_cd;

}
