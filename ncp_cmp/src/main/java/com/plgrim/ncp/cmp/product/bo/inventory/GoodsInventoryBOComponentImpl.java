package com.plgrim.ncp.cmp.product.bo.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.biz.goods.data.GoodsInventorySearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsOptionDTO;
import com.plgrim.ncp.biz.goods.result.GoodsInventoryResult;
import com.plgrim.ncp.biz.goods.result.GoodsOptionResult;
import com.plgrim.ncp.biz.goods.service.GoodsInfoService;
import com.plgrim.ncp.biz.goods.service.GoodsInventoryService;
import com.plgrim.ncp.cmp.product.bo.GoodsInventoryBOComponent;
import com.plgrim.ncp.framework.utils.Utils;

@Component
public class GoodsInventoryBOComponentImpl extends AbstractComponent implements GoodsInventoryBOComponent {

	@Autowired
	private GoodsInfoService goodsInfoService;
	
	@Autowired
	private GoodsInventoryService goodsInventoryService;
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsInventoryBOComponent#getChangeOption(com.plgrim.ncp.biz.goods.data.GoodsOptionDTO)
	 */
	@Override
	public GoodsOptionResult getChangeOption(GoodsOptionDTO goodsOptionDTO) {
		GoodsOptionResult result = new GoodsOptionResult();		
		try {
			String godNo = goodsOptionDTO.getGodNo();
			String shopId = goodsOptionDTO.getShopId();
			
			//	상품 조회
			God god = goodsInfoService.getGoods(godNo);
			
			if(Utils.empty(god)) {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));				
				return result;				
			}
			result.setGod(god);
			
			//	색상조회
			List<GodExtend> designColorList = goodsInfoService.getDesignColorList(godNo);
			result.setDesignColorList(designColorList);									
			
			//	단품 조회
			if(!String.valueOf(GoodsEnum.GoodsType.SET_GOD).equals(god.getGodTpCd())) {
				List<GodItmExtend> itemList = goodsInfoService.getGoodsItemListByShop(godNo, shopId);
				result.setItemList(itemList);				
			} else {
				//	구성정보 조회
				List<GodCpstGodCnncExtend> cpstList = goodsInfoService.getGoodsCompositionGoodsConnectionList(godNo);
				result.setCpstList(cpstList);
				
				if(Utils.empty(cpstList)) {
					result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
					return result;					
				}
				
				//	구성정보 단품 조회
				List<GodItmExtend> cpstGodItmList = goodsInfoService.getGoodsCompositionGoodsConnectionItemListByShop(godNo, shopId);
				result.setItemList(cpstGodItmList);
				
			}
			
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			throw new RuntimeException();
		}		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsInventoryBOComponent#searchShopInventoryList(com.plgrim.ncp.biz.goods.data.GoodsInventorySearchDTO)
	 */
	@Override
	public Page<GoodsInventoryResult> searchShopInventoryList(GoodsInventorySearchDTO inventorySearchDTO) {
		return goodsInventoryService.searchShopInventoryList(inventorySearchDTO);
	}

}
