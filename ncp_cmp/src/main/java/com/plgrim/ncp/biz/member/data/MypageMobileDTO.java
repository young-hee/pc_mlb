package com.plgrim.ncp.biz.member.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageMobileDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

	private MypageFoDTO mypageFoDTO;
	
	private String mobileFlag;                                      // 최근 본 상품 3개만 가져오기 위한 플래그
	
	private String mbrNo;
	
	//private GoodsSearchFoDTO goodsSearchFoDTO;
	
	private MypageMtmFoDTO mypageMtmFoDTO;
	
	private MypageQnaFoDTO mypageQnaFoDTO;
	
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
