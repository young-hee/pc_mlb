package com.plgrim.ncp.biz.delivery.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryHistoryResult extends AbstractResult {

    private static final long serialVersionUID = -7582629838121191678L;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 물류출고지시상품이력 엔터티. */
	private LgsDlivyDrctGodHist lgsDdgh;
	
	private String dlvStatNm;	//배송상태명.
	private String dlvShopNm;	//배송매장명.
	private String adminNm;		//수정자명.
	private String chngDt;		//수정일시.
	private String rejectResnNm; // 거절사유
	private String dlvShopTelNo; // 배송매장전화번호

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
