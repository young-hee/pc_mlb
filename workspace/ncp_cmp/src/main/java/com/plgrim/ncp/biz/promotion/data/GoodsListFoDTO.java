package com.plgrim.ncp.biz.promotion.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
@Data
@EqualsAndHashCode(callSuper=false)
public class GoodsListFoDTO extends AbstractResult{

	private String godNo;

	private String godNm;

	private String tagNm;

	private String dcIcon;
	private String cpnIcon;
	private String lastSalePrc;
	
	private String imgUrl1;

	private String imgUrl2;

	private String brndNm;

	private String sprtrTurn;
	
	private List<String> iconNmList;
	
	private String rtlPrc;	// 정소가 추가 2015.09.18

	private String colorStyleCd;

	private String[] colorStyleCds;
	

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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

	public String[] getColorStyleCds() {

		if (getColorStyleCd() != null) {
			return getColorStyleCd().split(",");
		}
		else {
			return colorStyleCds;
		}
	}	

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
