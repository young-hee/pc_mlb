package com.plgrim.ncp.interfaces.delivery.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.base.abstracts.AbstractSDO;
import com.plgrim.ncp.interfaces.abstracts.InterfaceBaseSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value=Include.NON_EMPTY)
public class DlivyDrctSDO extends AbstractSDO{
	
	private static final long serialVersionUID = 5836627492662374731L;

	@JsonProperty("REF_NO")
	private String ordNo;               // 주문번호
	@JsonProperty("WH_CD")
	private String whCd;                // 센터코드(센터코드)
	@JsonProperty("STRR_ID")
	private String strrId;              // 브랜드코드
	@JsonProperty("OUTB_TCD")
	private String outbTcd;             // 창고출고유형코드
	@JsonProperty("OUTB_ECT_DATE")
	private String outbEctDate;         // 창고출고예정일자
	@JsonProperty("REF_SEASON")
	private String refSeason;           // 시즌코드
	@JsonProperty("ONLINE_YN")
	private String onlineYn;            // 온라인몰 여부
	@JsonProperty("SHIPTO_ZIP")
	private String shiptoZip;           // 우편번호
	@JsonProperty("SHIPTO_ADDR_1")
	private String shiptoAddr1;         // 주소
	@JsonProperty("SHIPTO_ADDR_2")
	private String shiptoAddr2;         // 주소
	@JsonProperty("SHIPTO_TEL_NO")
	private String shiptoTelNo;         // 전화번호
	@JsonProperty("CUST_ID")
	private String custId;              // 주문자ID
	@JsonProperty("CUST_NM")
	private String custNm;              // 주문자명
	@JsonProperty("PAYMENT_AMT")
	private String paymentAmt;          // 결제금액
	@JsonProperty("CANCEL_YN")
	private String cancelYn;            // 출고취소여부
	@JsonProperty("REF_DETL_NO")
	private String dlivyDrctGodNo;           // ERP 오더라인번호
	@JsonProperty("ITEM_CD")
	private String itemCd;              // 품목코드
	@JsonProperty("OUTB_ECT_QTY")
	private String outbEctQty;          // 출고지시수량
	@JsonProperty("DLV_MEMO")
	private String dlvMemo;             // 배송메세지
	@JsonProperty("IF_CRT_ID")
	private String ifCrtId;             // 데이터 생성자
	@JsonProperty("IF_CRT_DTM")
	private String ifCrtDtm;            // 데이터 생성일시
	@JsonProperty("SALE_PRICE")
	private String salePrice;            // 상품 UNIT 판매가
	@JsonProperty("INTERFACE_KEY")
	private String interfaceKey;            // 인터페이스 키
	@JsonProperty("OUTB_CMPT_CANCEL_YN")
	private String outbCmptCancelYn;            // 출고지시 취소 확정여부.
	@JsonProperty("OUTB_CMPT_DATE")
	private String outbCmptDate;            // 출고완료일자.
	@JsonProperty("OUTB_CMPT_QTY")
	private String outbCmptQty;            // 출고완료수량
	@JsonProperty("QTY")
	private String invQty;            // 실재고수량
	@JsonProperty("ERP_QTY")
	private String erpQty;            // 본사 물류재고
	
    /**
     * 처리결과코드
     */
    @JsonProperty("RESULT_CD")
    private String resultCd;
    /**
     * 처리결과메시지
     */
    @JsonProperty("RESULT_MSG")
    private String resultMsg;
    @JsonProperty("WAYBILL_NO")
    private String dmstcWaybilNo;       // 송장번호

    private String dlvStatCd;          	// 상태값을 하려 헀으나 ERP에서 들은바론 리턴 값이 없음. 
	
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int dlvCnt;          	// 송장 분리를 체크하기 위한 같은 주문에 생성 필요 카운트 
	private String dlvPcupspTurn;
	private String dlvTurn;
	private String params;
	private String dlivyDrctCnclStatCd;
}
