package com.plgrim.ncp.cmp.bi.bo.statistic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.biz.bi.data.BiStatisticMbrDTO;
import com.plgrim.ncp.biz.bi.data.BiStatisticOnlineInventoryDTO;
import com.plgrim.ncp.biz.bi.result.BiStatisticGodQtyShopResult;
import com.plgrim.ncp.biz.bi.result.BiStatisticMbrResult;
import com.plgrim.ncp.biz.bi.result.BiStatisticMvSysCdResult;
import com.plgrim.ncp.biz.bi.result.BiStatisticOnlineInventoryResult;
import com.plgrim.ncp.biz.bi.service.BiStatisticService;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.cmp.bi.bo.BiStatisticBOComponent;

@Transactional(value = "transactionManager")
@Component
public class BiStatisticBOComponentImpl extends AbstractComponent implements BiStatisticBOComponent{
	
	@Autowired
	private BiStatisticService biStatisticService; 

	@Override
	public Page<BiStatisticMbrResult> getMbrMarketingGridList(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception {
		return biStatisticService.getMbrMarketingGridList(biStatisticMbrDTO);
	}

	@Override
	public List<Map<String, Object>> getMbrMarketingListExcel(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception {		
		return biStatisticService.getMbrMarketingListExcel(biStatisticMbrDTO);
	}

	@Override
	public Page<BiStatisticMbrResult> getMbrBasketGridList(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception {
		return biStatisticService.getMbrBasketGridList(biStatisticMbrDTO);
	}

	@Override
	public List<Map<String, Object>> getMbrBasketListExcel(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception {
		return biStatisticService.getMbrBasketListExcel(biStatisticMbrDTO);
	}

	@Override
	public Page<BiStatisticMbrResult> getMbrOrderGridList(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception {
		return biStatisticService.getMbrOrderGridList(biStatisticMbrDTO);
	}

	@Override
	public List<Map<String, Object>> getMbrOrderListExcel(BiStatisticMbrDTO biStatisticMbrDTO) throws Exception {
		return biStatisticService.getMbrOrderListExcel(biStatisticMbrDTO);
	}

	@Override
	public List<BiStatisticMvSysCdResult> getSeasonGrpCd() throws Exception {
		return biStatisticService.getSeasonGrpCd();
	}

	@Override
	public List<BiStatisticMvSysCdResult> getMnfcturYear() throws Exception {
		return biStatisticService.getMnfcturYear();
	}

	@Override
	public Page<BiStatisticOnlineInventoryResult> getOnlineInventoryGridList(
			BiStatisticOnlineInventoryDTO biStatisticOnlineInventoryDTO) throws Exception {
		return biStatisticService.getOnlineInventoryGridList(biStatisticOnlineInventoryDTO);
	}

	@Override
	public List<Map<String, Object>> getOnlineInventoryListExcel(
			BiStatisticOnlineInventoryDTO biStatisticOnlineInventoryDTO) throws Exception {
		return biStatisticService.getOnlineInventoryListExcel(biStatisticOnlineInventoryDTO);
	}

	@Override
	public List<GodItm> getGodItmList(String godNo) throws Exception {
		return biStatisticService.getGodItmList(godNo);
	}

	@Override
	public Page<BiStatisticGodQtyShopResult> getGodQtyShopGridList(GoodsSearchDTO goodsSearchDTO) throws Exception {
		return biStatisticService.getGodQtyShopGridList(goodsSearchDTO);
	}

	@Override
	public List<Map<String, Object>> getGodQtyShopListExcel(GoodsSearchDTO goodsSearchDTO) throws Exception {
		return biStatisticService.getGodQtyShopListExcel(goodsSearchDTO);
	}

	@Override
	public String getInvBatchEndDt() throws Exception {		
		return biStatisticService.getInvBatchEndDt();
	}

}
