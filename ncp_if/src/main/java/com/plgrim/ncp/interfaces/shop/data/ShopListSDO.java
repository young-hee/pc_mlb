package com.plgrim.ncp.interfaces.shop.data;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceBaseSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value=Include.NON_EMPTY)
@Alias("shopListSDO")
public class ShopListSDO extends InterfaceBaseSDO{
	private static final long serialVersionUID = -8724921549948376008L;

	@JsonProperty("RESPONSE_DATA")
	private List<ShopSDO> shopList;
	
	private String regtrId;
}
