package com.plgrim.ncp.interfaces.goods.data;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("monolabsSDO")
public class MonolabsSDO extends InterfaceSDO {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = -2878581071439042824L;
 
	@JsonProperty("skey")
	private String skey;

	@JsonProperty("token")
	private String token;

	@JsonProperty("pw")
	private String pw;

	@JsonProperty("api_key")
	private String api_key;

	private String result;
	
	private String validSkey;
	
	private String checkCnt;
	
    /** ERP상품번호. */
    private String erpGodNo;
}
