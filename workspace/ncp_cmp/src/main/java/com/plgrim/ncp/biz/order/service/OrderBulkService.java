package com.plgrim.ncp.biz.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.enums.DeliveryEnum;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.biz.goods.data.GoodsInventoryDTO;
import com.plgrim.ncp.biz.goods.service.GoodsService;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent;


@Service
public class OrderBulkService extends OrderBoAbstractService {

	@Autowired
	GoodsInventoryFOComponent goodsInventoryFOComponent;
	
	@Autowired
	GoodsService goodsService;
	
	public void insertOrder(OrderBoDTO orderDTO) throws Exception {
		creatOrder(orderDTO);

	}

	@Override
	void setOrder(Ord ord) {
		ord.setOrdStatCd(OrderClaimEnum.ORD_STAT_DEPST_WAIT.toString());
		ord.setOrdTpCd(OrderClaimEnum.ORD_TP_CD_LAG_QTY_ORD.toString());
	}

	@Override
	boolean mbrChecK() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	boolean pointYn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean invManageYn() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	boolean godChecK() {
		//return true; // 대량주문 재고 처리 하지 않음  2019.2.13
		return false;
	}
	@Override
	void invProcessor(OrdGodExtend ordGodEntity) throws Exception {
		
		GoodsInventoryDTO gDTO = new GoodsInventoryDTO();
		
		//mall 분리
		God tempGod = goodsService.getGoods(ordGodEntity.getGodNo());
		String shopId = DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString();
		if("M".equals(tempGod.getBrndId())) {
			shopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString();
		} else if("I".equals(tempGod.getBrndId())) {
			shopId = DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString();
		} else if("A".equals(tempGod.getBrndId())) {
			shopId = DeliveryEnum.DLV_ONLINE_SHOP_SA.toString();
		}
		
		gDTO.setShopId(shopId);
		gDTO.setGodNo(ordGodEntity.getGodNo());
		gDTO.setItmNo(ordGodEntity.getItmNo());
		gDTO.setSyncReqType(GoodsEnum.InvSyncReqTp.ORD.toString());
		gDTO.setSyncType(GoodsEnum.InvSyncTp.IRDS.toString());
		gDTO.setSalePrearngeQty(ordGodEntity.getOrdQty());
		
		goodsInventoryFOComponent.updateGoodsSalePrearrangementInventoryVariation(gDTO);
		
	}

    @Override
    void pckageshapeChk(OrdGodExtend ordGodEntity) throws Exception {
        // TODO Auto-generated method stub
        
    }
}
