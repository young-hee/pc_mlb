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
public class DlivyRetrievalSDO extends AbstractSDO{
	
	private static final long serialVersionUID = 5836627492662374731L;
	
	@JsonProperty("WAYBILL_NO")
	private String dmstcWaybilNo;   // 회수 운송장번호
	@JsonProperty("INTERFACE_ACTION")
	private String interfaceAction;   // 인터페이스 액션구분
	@JsonProperty("WH_CD")
	private String whCd;   // 센터코드
	@JsonProperty("STRR_ID")
	private String strrId;   // 브랜드코드
	@JsonProperty("INB_TCD")
	private String inbTcd;   // 창고입고유형코드
	@JsonProperty("INB_ECT_DATE")
	private String inbEctDate;   // 창고입고예정일자
	@JsonProperty("REF_SEASON")
	private String refSeason;   // 시즌
	@JsonProperty("REF_NO")
	private String ordNo;   // 참조번호
	@JsonProperty("SUPPR_ID")
	private String supprId;   // 공급사ID(매입처)
	@JsonProperty("SUPPR_NM")
	private String supprNm;   // 공급사명(매입처명)
	@JsonProperty("ORDER_DATE")
	private String orderDate;   // 발주일자
	@JsonProperty("REF_DETL_NO")
	private String rtrvlDrctGodNo;   // 참조 상세 번호
	@JsonProperty("ITEM_CD")
	private String itemCd;   // 제품코드
	@JsonProperty("INB_ECT_QTY")
	private String inbEctQty;   // 입고예정수량
	@JsonProperty("RTRN_TCD")
	private String rtrnTcd;   // 반품유형
	/* 202002 추가 */
	@JsonProperty("RTRN_TCD_DESC")
	private String rtrnTcdDesc;   // 사유내용
	@JsonProperty("INVC_SCD")
	private String invcScd;   // 온라인반품 요청재고상태
	@JsonProperty("MEMO")
	private String MEMO;   // 비고
	@JsonProperty("IF_CRT_ID")
	private String ifCrtId;   // 데이터 생성자
	@JsonProperty("IF_CRT_DTM")
	private String ifCrtDtm;   // 데이터 생성일시
	@JsonProperty("INTERFACE_KEY")
	private String interfaceKey;   // 인터페이스키
	@JsonProperty("RTRN_REASON_TCD")
	private String rtrvlStatCd;   // 회수 상태
	@JsonProperty("INB_CMPT_DATE")
	private String inbCmptDate;   // 입고처리확정일자
	@JsonProperty("INB_CMPT_QTY")
	private String inbCmptQty;   // 입고완료 수량.

	@JsonProperty("RESULT_CD")
	private String resultCd;           // 결과코드
	@JsonProperty("RESULT_MSG")
	private String resultMsg;          // 결과메세지
	
	private String clmNo;
	private String dlvPcupspTurn;
	private String dlvTurn;
	private String clmTpCd;
	private String exchgDlivyDrctGodNo;
}
