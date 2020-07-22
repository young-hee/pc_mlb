package com.plgrim.ncp.cmp.callcenter.fo.helpdesk.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.callcenter.data.SendMailDTO;
import com.plgrim.ncp.biz.callcenter.service.HelpdeskCommonSearvice;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskEmailResult;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.cmp.callcenter.fo.helpdesk.HelpdeskCommonFOComponent;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(value="transactionManager")
@Component
public class HelpdeskCommonFOComponentImpl extends AbstractComponent implements HelpdeskCommonFOComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	HelpdeskCommonSearvice helpdeskCommonSearvice;

	public Page<SysShopExtends> getHelpdeskShopList(ShopSearchDTO shopSearchDTO,PageParam pageParam)throws Exception  {
		return helpdeskCommonSearvice.getHelpdeskShopList(shopSearchDTO,pageParam);
	}
	
	/**	1:1문의 메일 템플릿 입력정보 */
	public HelpdeskEmailResult getSendEmailTemplateInfo(SendMailDTO sendMailDTO) throws Exception{
		
		return helpdeskCommonSearvice.getSendEmailTemplateInfo(sendMailDTO);
	}
	
	/**	단체 제휴 메일 템플릿 입력정보 */
	public HelpdeskEmailResult getSendAffOrdEmailTemplateInfo(SendMailDTO sendMailDTO) throws Exception{
		
		return helpdeskCommonSearvice.getSendAffOrdEmailTemplateInfo(sendMailDTO);
	}
	
}
