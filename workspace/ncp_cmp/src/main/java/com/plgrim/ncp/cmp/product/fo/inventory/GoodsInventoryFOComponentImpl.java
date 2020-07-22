package com.plgrim.ncp.cmp.product.fo.inventory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInv;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.biz.goods.data.GoodsInventoryDTO;
import com.plgrim.ncp.biz.goods.data.GoodsInventorySearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsOptionDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsOptionResult;
import com.plgrim.ncp.biz.goods.service.GoodsContentService;
import com.plgrim.ncp.biz.goods.service.GoodsInfoService;
import com.plgrim.ncp.biz.goods.service.GoodsInventoryService;
import com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.utils.Utils;
import com.plgrim.ncp.interfaces.goods.data.InfGodTargetSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GoodsInventoryFOComponentImpl extends AbstractComponent implements GoodsInventoryFOComponent {
	
	@Autowired
	private InterfaceApiCommon interfaceApiCommon;
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	@Autowired
	private GoodsContentService goodsContentService;

	@Autowired
	private GoodsInventoryService goodsInventoryService;

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent#getChangeOption(com.plgrim.ncp.biz.goods.data.GoodsOptionDTO)
	 */
	@Override
	public GoodsOptionResult getChangeOption(GoodsOptionDTO goodsOptionDTO) {
		GoodsOptionResult result = new GoodsOptionResult();
		
		try {
			String godNo = goodsOptionDTO.getGodNo();
			String langCd = goodsOptionDTO.getLang();
			String shopId = goodsOptionDTO.getShopId();
			
			//	상품 조회
			God god = goodsInfoService.getGoods(godNo);
 
			if(Utils.empty(god)) {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));				
				return result;				
			}
			
			//	언어 기본 처리
			if(StringService.isEmpty(langCd)) {
				langCd = String.valueOf(GoodsEnum.KOR);
			}
			god.setRecomendSexCd(CodeUtil.getCode(langCd, god.getRecomendSexCd()).getCdNm());
			//	상품 언어별 상품명 조회
			GodLangbyGodNm langByGodNm = goodsInfoService.getGoodsLangByGoodsName(godNo, langCd);
			if(!Utils.empty(langByGodNm)) {
				god.setGodNm(langByGodNm.getGodNm());
				god.setMobileGodNm(langByGodNm.getMobileGodNm());
				god.setGodSrchSnm(langByGodNm.getGodSrchSnm());
			}			
			result.setGod(god);
			//	색상조회
			List<GodExtend> designColorList = goodsInfoService.getDesignColorList(godNo);
			result.setDesignColorList(designColorList);						
			
			// 이미지 조회
			List<GodImg> imageList = goodsContentService.getGoodsImageList(godNo);
			if(!Utils.empty(imageList)) {
				List<GodImg> detailImageList = new ArrayList<GodImg>();
				for(GodImg img : imageList) {
					if(String.valueOf(GoodsEnum.GoodsImageType.THNAIL).equals(img.getImgTpCd())) {
						//	대표컷
						result.setIamge(img);
					}else {
						// 상세컷
						detailImageList.add(img);
					}
				}
				result.setImageList(detailImageList);
			}			
			
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
				
				for(GodCpstGodCnncExtend cpst : cpstList) {
					String cpstGodNo = cpst.getCpstGodNo();
					
					//	언어별 구성 상품 상품명 조회
					GodLangbyGodNm cpstLangByGodNm = goodsInfoService.getGoodsLangByGoodsName(cpstGodNo, langCd);					
					if(!Utils.empty(cpstLangByGodNm) && StringService.isNotEmpty(cpstLangByGodNm.getGodNm())) {
						cpst.setCpstGodNm(cpstLangByGodNm.getGodNm());
						cpst.setCpstMobileGodNm(cpstLangByGodNm.getMobileGodNm());
					}
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
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent#updateGoodsSalePrearrangementInventoryVariation(com.plgrim.ncp.biz.goods.data.GoodsInventoryDTO)
	 */
	@Override
	public void updateGoodsSalePrearrangementInventoryVariation(GoodsInventoryDTO invDTO) {
		String shopId = invDTO.getShopId();
		String godNo = invDTO.getGodNo();
		String itmNo = invDTO.getItmNo();
		String udterId = invDTO.getSyncReqType();
		Long salePrearngeQty = invDTO.getSalePrearngeQty();
		
		//	매장 단품 재고 수정
		GodShopItmInv itmInv = new GodShopItmInv();
		itmInv.setShopId(shopId);
		itmInv.setItmNo(itmNo);		
		
		if(String.valueOf(GoodsEnum.InvSyncTp.IRDS).equals(invDTO.getSyncType())) {}
		else if(String.valueOf(GoodsEnum.InvSyncTp.DDCT).equals(invDTO.getSyncType())) {
			salePrearngeQty = (salePrearngeQty * -1);
		} else {
			throw new RuntimeException();
		}
		itmInv.setSalePrearngeQty(salePrearngeQty);
		
		if(String.valueOf(GoodsEnum.InvSyncReqTp.ORD).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.CLM).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.DLV).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.SHOP_DLV).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.WMS_DLV).equals(udterId)) {
			itmInv.setUdterId(invDTO.getSyncReqType());
		} else {
			throw new RuntimeException();
		}
		//	상품 매장 단품 재고(판매예정수량) 조정
		goodsInventoryService.updateGoodsShopItemInventorySalePrearrangementQuantityVariation(itmInv);		
		
		//	상품 단품 판매예정수량 조정 및 상태 변경
		GodItm godItm = new GodItm();
		godItm.setItmNo(itmNo);
		godItm.setSalePrearngeQty(salePrearngeQty);
		godItm.setUsefulInvQtyShopId(shopId);
		godItm.setUdterId(udterId);
		goodsInventoryService.updateGoodsItemSalePrearrangementQuantityVariation(godItm);
		
		//	단품 상태에 따른 상품 상태 변경
		God god = new God();
		god.setGodNo(godNo);
		god.setUdterId(udterId);
		goodsInventoryService.updateGoodsItemStatusAccordingToGoodsStatus(god);
		
		//	세트상품 상태 싱크
		goodsInfoService.updateSetStatus(godNo);
	}


	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent#updateGoodsInventoryVariation(com.plgrim.ncp.biz.goods.data.GoodsInventoryDTO)
	 */
	@Override
	public void updateGoodsInventoryVariation(GoodsInventoryDTO invDTO) {
		String shopId = invDTO.getShopId();
		String godNo = invDTO.getGodNo();
		String itmNo = invDTO.getItmNo();
		String udterId = invDTO.getSyncReqType();
		Long invQty = invDTO.getInvQty();

		//	매장 단품 재고 수정
		GodShopItmInv itmInv = new GodShopItmInv();
		itmInv.setShopId(shopId);
		itmInv.setItmNo(itmNo);
		
		if(String.valueOf(GoodsEnum.InvSyncTp.IRDS).equals(invDTO.getSyncType())) {}
		else if(String.valueOf(GoodsEnum.InvSyncTp.DDCT).equals(invDTO.getSyncType())) {
			invQty = (invQty * -1);
		} else {
			throw new RuntimeException();
		}		
		itmInv.setInvQty(invQty);
		
		if(String.valueOf(GoodsEnum.InvSyncReqTp.ORD).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.CLM).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.DLV).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.SHOP_DLV).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.WMS_DLV).equals(udterId)) {
			itmInv.setUdterId(invDTO.getSyncReqType());
		} else {
			throw new RuntimeException();
		}			
		//	상품 매장 단품 재고 수정
		goodsInventoryService.updateGoodsShopItemInventoryQuantityVariation(itmInv);
		
		//	상품 단품 재고수정 및 상태 변경
		GodItm godItm = new GodItm();
		godItm.setItmNo(itmNo);
		godItm.setTotUsefulInvQty(invQty);
		godItm.setUsefulInvQtyShopId(shopId);		
		godItm.setUdterId(udterId);
		goodsInventoryService.updateGoodsItemInventoryQuantityVariation(godItm);		
		
		//	단품 상태에 따른 상품 상태 변경
		God god = new God();
		god.setGodNo(godNo);
		god.setUdterId(udterId);
		goodsInventoryService.updateGoodsItemStatusAccordingToGoodsStatus(god);		
		
		//	세트상품 상태 싱크
		goodsInfoService.updateSetStatus(godNo);
	}


	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent#updateGoodsReserveInventoryVariation(com.plgrim.ncp.biz.goods.data.GoodsInventoryDTO)
	 */
	@Override
	public void updateGoodsReserveInventoryVariation(GoodsInventoryDTO invDTO) {
		String godNo = invDTO.getGodNo();
		String itmNo = invDTO.getItmNo();
		String udterId = invDTO.getSyncReqType();
		Long resveInvQty = invDTO.getResveInvQty();
		
		
		//	상품 단품 예약재고 수정 및 상태 변경
		GodItm godItm = new GodItm();
		godItm.setItmNo(itmNo);		

		if(String.valueOf(GoodsEnum.InvSyncTp.IRDS).equals(invDTO.getSyncType())) {}
		else if(String.valueOf(GoodsEnum.InvSyncTp.DDCT).equals(invDTO.getSyncType())) {
			resveInvQty = (resveInvQty * -1);
		} else {
			throw new RuntimeException();
		}		
		godItm.setResveInvQty(resveInvQty);		
		
		if(String.valueOf(GoodsEnum.InvSyncReqTp.ORD).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.CLM).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.DLV).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.SHOP_DLV).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.WMS_DLV).equals(udterId)) {
			godItm.setUdterId(invDTO.getSyncReqType());
		} else {
			throw new RuntimeException();
		}
		goodsInventoryService.updateGoodsItemReserveInventoryQuantityVariation(godItm);
		
		//	단품 상태에 따른 상품 상태 변경
		God god = new God();
		god.setGodNo(godNo);
		god.setUdterId(udterId);
		goodsInventoryService.updateGoodsItemStatusAccordingToGoodsStatus(god);	
		
		//	세트상품 상태 싱크
		goodsInfoService.updateSetStatus(godNo);
		
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent#updateGoodsInventory(com.plgrim.ncp.biz.goods.data.GoodsInventoryDTO)
	 */
	@Override
	public void updateGoodsInventory(GoodsInventoryDTO invDTO) {
		String shopId = invDTO.getShopId();
		String godNo = invDTO.getGodNo();
		String itmNo = invDTO.getItmNo();
		String udterId = invDTO.getSyncReqType();
		Long invQty = invDTO.getInvQty();
		Long lc1InvQty = invDTO.getLc1InvQty();
		
		//	매장 단품 재고 수정
		GodShopItmInv itmInv = new GodShopItmInv();
		itmInv.setShopId(shopId);
		itmInv.setItmNo(itmNo);		
		itmInv.setInvQty(invQty);		
		itmInv.setLc1InvQty(lc1InvQty);
		if(String.valueOf(GoodsEnum.InvSyncReqTp.ORD).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.CLM).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.DLV).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.SHOP_DLV).equals(udterId) 
				|| String.valueOf(GoodsEnum.InvSyncReqTp.WMS_DLV).equals(udterId)) {
			itmInv.setUdterId(invDTO.getSyncReqType());
		} else {
			throw new RuntimeException();
		}
		//	상품 매장 단품 재고 수정
		goodsInventoryService.updateGoodsShopItemInventoryQuantity(itmInv);		
				
		//	상품 단품 재고 수정 및 상태 변경
		GodItm godItm = new GodItm();
		godItm.setItmNo(itmNo);
		godItm.setTotUsefulInvQty(invQty);
		godItm.setUsefulInvQtyShopId(shopId);		
		godItm.setUdterId(udterId);
		goodsInventoryService.updateGoodsItemInventoryQuantity(godItm);		
		
		//	단품 상태에 따른 상품 상태 변경
		God god = new God();
		god.setGodNo(godNo);
		god.setUdterId(udterId);
		goodsInventoryService.updateGoodsItemStatusAccordingToGoodsStatus(god);		
		
		//	세트상품 상태 싱크
		goodsInfoService.updateSetStatus(godNo);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent#updateGoodsErpInventory(com.plgrim.ncp.biz.goods.data.GoodsInventorySearchDTO)
	 */
	@Override
	public void updateGoodsErpInventory(GoodsInventorySearchDTO inventoryDTO) {		
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setMallId(inventoryDTO.getMallId());
		adapterHeader.setLangCd(inventoryDTO.getLang());
		adapterHeader.setDvcCd(inventoryDTO.getDevice());
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());		
		
		try {					
			GoodsSearchDTO goodsSearchDTO = new GoodsSearchDTO();
			goodsSearchDTO.setErpGodNo(inventoryDTO.getErpGodNo());
			goodsSearchDTO.setGodNo(inventoryDTO.getGodNo());
			
			//	상품 조회
			God god = goodsInfoService.getGoods(goodsSearchDTO);		
			String godNo = god.getGodNo();
			
			InfGodTargetSDO targetSDO;
			if(String.valueOf(GoodsEnum.GoodsType.GNRL_GOD).equals(god.getGodTpCd())) {			
				if(String.valueOf(GoodsEnum.YES).equals(god.getErpInvIntrlckYn()) && String.valueOf(GoodsEnum.NO).equals(god.getResveSaleGodYn())) {
					targetSDO = new InfGodTargetSDO();
					
					targetSDO.setBrand(god.getBrndId());
					targetSDO.setDsgnGrpNo(god.getDsgnGrpNo());
					targetSDO.setColorCd(god.getColorCd());
					targetSDO.setSeason(god.getSesonCd());
					goodsInventoryService.getRealtimeGoodsInventoryAndUpdate(adapterHeader, targetSDO);
				}			
			}
			
			if(String.valueOf(GoodsEnum.GoodsType.SET_GOD).equals(god.getGodTpCd())) {
				//	구성정보 조회
				List<GodCpstGodCnncExtend> cpstList = goodsInfoService.getGoodsCompositionGoodsConnectionList(godNo);			
				if(!Utils.empty(cpstList)) {				
					targetSDO = new InfGodTargetSDO();
					
					for(GodCpstGodCnncExtend cpst : cpstList) {
						String cpstGodNo = cpst.getCpstGodNo();					
						God cpstGod = goodsInfoService.getGoods(cpstGodNo);
						if(String.valueOf(GoodsEnum.YES).equals(cpstGod.getErpInvIntrlckYn()) && String.valueOf(GoodsEnum.NO).equals(god.getResveSaleGodYn())) {
							targetSDO.setBrand(cpstGod.getBrndId());
							targetSDO.setDsgnGrpNo(cpstGod.getDsgnGrpNo());
							targetSDO.setColorCd(cpstGod.getColorCd());
							targetSDO.setSeason(cpstGod.getSesonCd());
							goodsInventoryService.getRealtimeGoodsInventoryAndUpdate(adapterHeader, targetSDO);
						}					
					}
				}
			}			
			//	세트상품 상태 싱크
			goodsInfoService.updateSetStatus(godNo);
		} catch (Exception e) {
			log.error("updateGoodsErpInventory error : ", e.getMessage());
		}		
	}
	
}
