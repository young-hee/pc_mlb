package com.plgrim.ncp.biz.goods.data;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsOptionDTO")
public class GoodsOptionDTO extends AbstractDTO{
	private static final long serialVersionUID = -1384375112481357497L;

	/** 상품번호 */
	private String godNo;
	
	/** 매장ID */
	private String shopId;
	
}
