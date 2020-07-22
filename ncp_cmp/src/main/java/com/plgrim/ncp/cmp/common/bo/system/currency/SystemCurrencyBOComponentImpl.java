package com.plgrim.ncp.cmp.common.bo.system.currency;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.cmp.common.bo.system.SystemCurrencyBOComponent;
import com.plgrim.ncp.commons.data.FormSysExchgDTO;
import com.plgrim.ncp.commons.result.SysExchgResult;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.commons.data.SysExchgDTO;
import com.plgrim.ncp.commons.service.SysExchgService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class SystemCurrencyBOComponentImpl extends AbstractComponent implements SystemCurrencyBOComponent {
	
	@Autowired
	SysExchgService sysExchgService;
	
	@Override
	public void updateExchg(List<SysExchgDTO> paramList) throws Exception {
	    // TODO Auto-generated method stub
		Iterator<SysExchgDTO> iterator = paramList.iterator();
		while(iterator.hasNext()) {
			sysExchgService.mergeExchg( iterator.next());
		}

    }
	
	/**
	 * 환율 등록.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	@Override
    public void insertExchg(SysExchgDTO paramData) throws Exception {
		sysExchgService.insertExchg(paramData);
    }


	@Override
	public List<SysExchgResult> selectSysExchgList(SystemPK systemPk, FormSysExchgDTO paramData) throws Exception {
		// TODO Auto-generated method stub
		return sysExchgService.selectSysExchgList(paramData);
	}

	/**
	 * 환율 목록 엑셀.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	@Override
	public List<Map<String, Object>> selectSysExchgListExcel(SystemPK systemPK, FormSysExchgDTO paramData) throws Exception {
		return sysExchgService.selectSysExchgListExcel(paramData);
	}

	/**
	 * 환율 목록 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	@Override
	public long selectSysExchgListCount(SystemPK systemPK, FormSysExchgDTO paramData) throws Exception {
		return sysExchgService.selectSysExchgListCount(paramData);
	}

	/**
	 * 환율 중복 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	@Override
	public long selectSysExchgDupCount(SystemPK systemPK, SysExchgDTO paramData) throws Exception {
		return sysExchgService.selectSysExchgDupCount(paramData);
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
