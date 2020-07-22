package com.plgrim.ncp.biz.interfaces.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbRepair;
import com.plgrim.ncp.base.repository.inf.InfOrdGodErpDstbRepairRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InfOrdGodErpDstbRepairService extends AbstractService {

	@Autowired
	InfOrdGodErpDstbRepairRepository infOrdGodErpDstbRepairRepository;// 주문 상품 ERP

	public InfOrdGodErpDstbRepair selectInfOrdGodErpDstbRepair(InfOrdGodErpDstbRepair infOrdGodErpDstbRepair) throws Exception {
		return infOrdGodErpDstbRepairRepository.selectInfOrdGodErpDstbRepair(infOrdGodErpDstbRepair);
	}

}
