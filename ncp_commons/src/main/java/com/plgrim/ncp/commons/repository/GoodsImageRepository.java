package com.plgrim.ncp.commons.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;

@Repository
public class GoodsImageRepository extends AbstractRepository{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	
//	@Cacheable(value="goodsImageRepository.selectRprstGodImagePath")
	public String selectRprstGodImagePath(String godNo,String size,String imgTurn){
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("godNo", godNo);
		paramData.put("size", size);
		
		if(imgTurn == null || "".equals(imgTurn)){
			paramData.put("imgTurn", "0");
		}else{
			paramData.put("imgTurn", imgTurn);
		}
		
		return getSession1().selectOne("com.plgrim.ncp.commons.god.selectListShopId", paramData);
		
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
