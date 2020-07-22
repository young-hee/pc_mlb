package com.plgrim.ncp.biz.goods.result;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInvExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsInventoryResult")
public class GoodsInventoryResult extends GoodsResult{
	private static final long serialVersionUID = -6723935440132410049L;

    /** 상품 엔티티. */
    private GodExtend godEx;
    
    /** 상품 단품 엔티티. */
    private GodItmExtend godItmEx;
    
    /** 상품 매장 단품 엔티티. */
    private GodShopItmInvExtend godShopItmInvEx;
    
    /** 시스템브랜드 엔티티.*/
    private SysBrnd sysBrnd;
	
}
