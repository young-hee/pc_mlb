package com.plgrim.ncp.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.commons.repository.SysInflowViewRepository;

@Service
public class SysInflowService extends AbstractService {


	@Autowired
	SysInflowViewRepository sysInflowRepository;

	public List<SysInflow> selectSysInflowAll(SysInflow inflow) {

		return sysInflowRepository.selectSysInflowAll(inflow);
	}
	public SysInflow selectSysInflow(SysInflow inflow) {
		
		return sysInflowRepository.selectSysInflow(inflow);
	}
}
