package com.plgrim.ncp.biz.member.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodIcon;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageWishFoResult extends AbstractResult{

	/**
	 *
	 */
	private static final long serialVersionUID = 7631707281423187057L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private String wishlstSn;	   // 위시리스트 일련번호

	private String godTurn;		   // 상품순번

	private String godNo;		   // 상품번호

	private String ovseaDspStatCd; // 해외 전시 승인 여부

	private String ovseaDlvPsbYn;  // 해외 판매 여부(해외배송여부)

	private String brndId;		   // Brand Id

	private String brndNm;		   // Brand NM

	private String colorNm;		   // 색상명

	private String csmrPrc;        // 실소가

	private String rtlPrc;			// 정소가

	private String godNm;          // 상품명

	private String imgUrl;         // 상품IMG

	private String godSaleSectCd;  // 상품 Sale 상태

	private String lastSalePrc;   // 상품가격

	private String itmQty;         // 상품수량

	private String colorStyleCd;   // 색상코드

	private List<GodIcon> godIcon; // 상품Icon

	private String clorChipImgUrl; // 컬러칩url

	private String godUrl; // 상품url

	private int  wishListCount;

	private String  wishListYn;
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
