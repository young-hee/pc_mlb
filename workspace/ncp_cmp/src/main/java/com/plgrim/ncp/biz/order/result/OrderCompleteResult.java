package com.plgrim.ncp.biz.order.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
//import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;

@Data
@EqualsAndHashCode(callSuper = false)
// public class OrderCompleteResult extends AbstractResult {
public class OrderCompleteResult extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3293221895910909960L;

	private String ordNo;
	/**
	 * 주문정보
	 */
	private Ord ord;

	private String ordDate; // 주문일시 (날짜 변환용)

	private String resveOrdDlivyPrearngeDate; // 예약 배송일

	private String resveOrdPayClosDate; // 예약 잔액 결제 기한

	/**
	 * 무통장 입금 안내
	 */
	private Pay pay;
	
	private List<Pay> payList;

	private String payTmlmtDate; // 결제기한일

	private String remainAmt; // 예약 1차 결제후 남은 잔액

	private SysShopExtends sysShop;

	private String mbrId; // 회원아이디

	private String mbrNo; // 회원번호

	private String erpCstmrNo; // ERP 고객번호
	
	private List<OrdGod> ordGodList;
	
	private List<OrdGodExtend> ordGodExtendList;
	
	private String smsSendChoiceYn;	//UXUI개선 : SMS발송선택
	
	private String shopDlvYn;
	
	private String holdyYn;
	private String shopEndHhmm;
	private String shopBegHhmm;

}
