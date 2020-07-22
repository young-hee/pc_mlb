package com.plgrim.ncp.commons.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExcpCd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExcpCdExtend;
import com.plgrim.ncp.commons.repository.SysExcpCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysExcpCodeService extends AbstractService {

    @Autowired
    SysExcpCodeRepository sysExcpCodeRepository;

    public List<SysExcpCdExtend> selectAllSysExcpGrpCd(SysExcpCdExtend sysExcpCdExtend){
        return sysExcpCodeRepository.selectAllSysExcpGrpCd(sysExcpCdExtend);
    }
    
	public SysExcpCd selectSysExcpCdForOrd(SysExcpCd sysExcpCd){
		return sysExcpCodeRepository.selectSysExcpCdForOrd(sysExcpCd);
	}
}
