package com.plgrim.ncp.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysErpSaleShop;
import com.plgrim.ncp.commons.repository.SysErpSaleRepository;

@Service
public class SysErpSaleShopService extends AbstractService {

	
	@Autowired
	SysErpSaleRepository sysErpSaleRepository;
	
	public List<SysErpSaleShop> selectSysErpSaleShopList(SysErpSaleShop sysErpSaleShop) throws Exception {
		return sysErpSaleRepository.selectSysErpSaleShopList(sysErpSaleShop);
	}


}
