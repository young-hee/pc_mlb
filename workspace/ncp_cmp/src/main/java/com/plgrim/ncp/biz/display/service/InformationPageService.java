package com.plgrim.ncp.biz.display.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatHistExtends;
import com.plgrim.ncp.biz.display.repository.DisplayOldPolicyRepository;
import com.plgrim.ncp.biz.display.repository.InformationPageRepository;

@Service
public class InformationPageService extends AbstractService {
	
	@Autowired
	InformationPageRepository informationPageRepository;
	
	@Autowired
	DisplayOldPolicyRepository displayOldPolicyRepository;

	public SysStplat selectSysStplat(SysStplat search) throws Exception{
	    // TODO Auto-generated method stub
	    return informationPageRepository.selectSysStplat(search);
    }
	
	public SysStplat selectSysStplatByLang(SysStplat search) throws Exception{
		// TODO Auto-generated method stub
		SysStplat sysStplat = new SysStplat();
		String stplatDscr = "";
		sysStplat = informationPageRepository.selectSysStplatByLang(search);
		stplatDscr = informationPageRepository.selectSysStplatTitleByLang(search.getStplatCd());
		sysStplat.setStplatDscr(stplatDscr);
		return sysStplat;
	}
	
	public List<SysStplatHistExtends> selectSysStplatHist(SysStplat search) throws Exception{
		// TODO Auto-generated method stub
		return informationPageRepository.selectSysStplatHist(search);
	}
	
	public List<SysStplatHistExtends> selectSysStplatHistByLang(SysStplat search) throws Exception{
		// TODO Auto-generated method stub
		return informationPageRepository.selectSysStplatHistByLang(search);
	}	
	
	public List<SysStplatHistExtends> selectPrivacyPolicyHistDt(SysStplat search) throws Exception{
		//return informationPageRepository.selectPrivacyPolicyHistDt(search);
		return null;
	}

	public SysStplat selectOldSysStplat(SysStplatHistExtends search) throws Exception{
	    // TODO Auto-generated method stub
	    return displayOldPolicyRepository.selectOldSysStplat(search);
    }

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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
