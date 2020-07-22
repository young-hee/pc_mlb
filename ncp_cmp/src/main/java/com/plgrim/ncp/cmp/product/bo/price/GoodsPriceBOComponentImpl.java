package com.plgrim.ncp.cmp.product.bo.price;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodPrcResveRegExtend;
import com.plgrim.ncp.biz.goods.data.GoodsDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;
import com.plgrim.ncp.cmp.product.bo.GoodsPriceBOComponent;
import com.plgrim.ncp.framework.data.SystemPK;


@Transactional(value = "transactionManager")
@Component
public class GoodsPriceBOComponentImpl extends AbstractComponent implements GoodsPriceBOComponent {@Override
	public void insertPrcReserveGoods(SystemPK systemPK, GodPrcResveRegExtend godPrcResveReg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePrcReserveAprv(SystemPK systemPK, GodPrcResveRegExtend godPrcResveReg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePartmalComFeeRt(God god) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePrcReserveAprv(SystemPK systemPK, GodPrcResveRegExtend godPrcResveReg) {
		// TODO Auto-generated method stub
		
	}
/*
	@Override
	public void updateInfSalesPriceAprv(SystemPK systemPK, InfZd5sdm0011Extend infSalesPrice) {
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public void updateInfSalesPriceAprv(SystemPK systemPK, List<GoodsDTO> goodsGridList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGoodsPriceProcedureVari(Map<String, String> params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateGoodsPriceProcedureApply(String godNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Page<GoodsInfoResult> selectUpdatePriceList(SystemPK systemPK, GoodsSearchDTO goodsSearchDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<GoodsInfoResult> selectReservePriceList(SystemPK systemPK, GoodsSearchDTO goodsSearchDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> selectResvePriceApplyList(SystemPK systemPK) {
		// TODO Auto-generated method stub
		return null;
	}
			 

}
