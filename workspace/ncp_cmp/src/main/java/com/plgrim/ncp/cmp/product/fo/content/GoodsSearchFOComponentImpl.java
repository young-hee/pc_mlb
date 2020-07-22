package com.plgrim.ncp.cmp.product.fo.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.goods.service.GoodsSearchService;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.cmp.product.fo.GoodsSearchFOComponent;

@Transactional(value = "transactionManager")
@Component
public class GoodsSearchFOComponentImpl extends AbstractComponent implements GoodsSearchFOComponent {
	
	@Autowired
	private GoodsSearchService goodsSearchService;

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsSearchFOComponent#getGoodsShopList(com.plgrim.ncp.biz.vendor.data.ShopSearchDTO)
	 */
	@Override
	public List<SysShopExtends> getGoodsShopList(ShopSearchDTO shopSearchDTO) {
		return goodsSearchService.getGoodsShopList(shopSearchDTO);
	}

	@Override
	public SysShopExtends getGoodsShop(ShopSearchDTO shopSearchDTO) {
		return goodsSearchService.getGoodsShop(shopSearchDTO);
	}

}
