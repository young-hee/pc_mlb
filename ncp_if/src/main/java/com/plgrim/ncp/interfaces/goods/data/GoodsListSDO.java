package com.plgrim.ncp.interfaces.goods.data;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value=Include.NON_EMPTY)
@Alias("goodsListSDO")
public class GoodsListSDO extends InterfaceSDO {
	private static final long serialVersionUID = 3008848047376281820L;

	@JsonProperty("PROD_LIST")
	private List<InfGoodsItmSDO> prodList;
	
    @JsonProperty("MATERIAL_LIST")
	private List<InfGodMatrSDO> materialList;
	
    @JsonProperty("SIZE_REF_POM_LIST")
	private List<InfGodSizeLktbPomSDO> sizeRefPomList;
    
    @JsonProperty("SIZE_REF_LIST")
	private List<InfGodSizeLktbSDO> sizeRefList;
    
    @JsonProperty("PRICE_LIST")
	private List<InfGodPrcSDO> priceList;
    
    @JsonProperty("STOCK_LIST")
	private List<InfGodItmInvSDO> stockList;

    @JsonProperty("IMAGE_LIST")
	private List<GoodsImageUrlSDO> imageList;

	private String regtrId;
}