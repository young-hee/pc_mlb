package com.plgrim.ncp.biz.member.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodIcon;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageTdGodFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */


	/**
	 *
	 */
	private static final long serialVersionUID = -6946042687916443054L;

	private String todayGodSn;	   // 오늘볺상품 일련번호

	private String godNo;		   // 상품번호

	private String brndId;		   // Brand Id

	private String brndNm;		   // Brand NM

	private String colorNm;		   // 색상명

	private String godNm;          // 상품명

	private String imgUrl;         // 상품IMG

	private String godSaleSectCd;  // 상품 Sale 상태

	private String lastSalePrc;   // 상품가격

	private String rtlPrc;   // 상품 정소가	2015.09.18

	private String csmrPrc;   // 상품 실소가	2019.02.12

	private String godDcRt;	//	상품 할인	2015.09.18

	private String cpnDcRt;	//	쿠폰 할인	2015.09.18

	private String godUrl; // 상품url
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
