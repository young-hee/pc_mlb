package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("repairDTO")
public class RepairDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */


	private String mbrNo;

	private String mbrNm;

	private String clmNo;

	/**
	 * 휴대전화 번호
	 */
	private String mobilNo;

	private String godSumry;

	/**
	 * 클레임 접수 방법 코드
	 ㅁ. 클레임 접수 방법 : CLM_RCEPT_MTHD
	 >. 매장 방문 : SHOP_VISIT    Y
	 >. 택배 접수 : HDRY_RCEPT    Y
	 >. 기타 : ETC    N
	 ㅁ. 보조코드1 : 수선 사용 여부 Y/N
	 */
	private String dlvSectCd;

	private String ordNo;

	private String dlvPcupspTurn;

	private String repairTotCnt;
	private String repairComptCnt;
	private String wrhsAcptComptCnt;


	private String dlvPcupspTurnWthdraw;			// 클레임 회수지
	private String dlvPcupspTurnDlivyWthdraw;		// 클레임 수거지

	private String dlvTurnWthdraw;                  // 클레임 회수지
	private String dlvTurnDlivyWthdraw;             // 클레임 수거지

	private String waybilNoDlivyWthdraw;            // 클레임 수거지 송장번호
	private String dlivyDrctGodNo;             	    // 클레임 수거지 출고지시 상품 번호
	private String rtrvlDrctGodNo;                  // 클레임 회수지시 상품 번호

	private String clmWrhsGodTurn;       // 클레임 입고상품 순번
	private String ordGodTurn;           // 주문 상품 순번
	private String dlivyQty;             // 출고수량

	private String qtyTurn;
	private String wrhsAcptYn;           // 입고검수여부
	private String repairComptCd;        // 수선 상태 코드

	private String pkupShopId;           // 수선 SHOP_ID

	/** 검색 일자 */
	private String searchDate;
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
