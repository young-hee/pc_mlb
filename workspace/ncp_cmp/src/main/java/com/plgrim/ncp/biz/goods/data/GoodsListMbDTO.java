package com.plgrim.ncp.biz.goods.data;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;


@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsListMbDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    private static final long serialVersionUID = 4302710795480010155L;

    private String title;
    private String godNo;
    private String image;
    private String url;
    private String price;
    private BigDecimal discountRate;
    private BigDecimal couponRate;
    private String godSaleSectCd;
    private String brndNm;
    private boolean best = false;
    private boolean brandNew = false;
    private boolean hot = false;
    private boolean sale = false;
    private boolean seasonoff = false;
    private boolean md = false;
    private boolean special = false;
    private boolean restock = false;
    private String seasonInfo;
    private String tagNm;
    private String textIcon;
    
    private String prePrice;	// 정소가 추가 2015.09.18
	
    String colorStyleCd;

    String[] colorStyleCds;
    
	public String[] getColorStyleCds() {
		
		if(getColorStyleCd() != null){
			
			return getColorStyleCd().split(",");
			
		}else{
		
			return colorStyleCds;
		}			
	}
	
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
