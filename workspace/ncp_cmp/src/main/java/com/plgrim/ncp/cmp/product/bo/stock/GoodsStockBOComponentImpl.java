package com.plgrim.ncp.cmp.product.bo.stock;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.cmp.product.bo.GoodsStockBOComponent;
@Transactional(value = "transactionManager")
@Component
public class GoodsStockBOComponentImpl extends AbstractComponent implements GoodsStockBOComponent {
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsStockBOComponent#insertGodItmInvHistByOrder(com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends, java.lang.String)
	 */
	@Deprecated
	@Override
	public void insertGodItmInvHistByOrder(OrdGodExtends ordGodExtends, String shopId) {
		// TODO Auto-generated method stub
		try {
		
		}catch(Exception e) {
			throw new RuntimeException();
		}
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsStockBOComponent#selectGodItmShopStockHistList(java.lang.String, java.lang.String)
	 */
	@Deprecated
	@Override
	public List<GodExtend> selectGodItmShopStockHistList(String godNo, String shopId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
