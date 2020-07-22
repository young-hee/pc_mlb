package com.plgrim.ncp.biz.goods.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.plgrim.ncp.biz.goods.data.GoodsListMbDTO;
import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsListMbResultDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    private static final long serialVersionUID = 4302710795480010155L;

    private int productTotCnt;
    private List<GoodsListMbDTO> products;
   
	
	
	
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
