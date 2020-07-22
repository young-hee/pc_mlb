package com.plgrim.ncp.cmp.callcenter.fo.helpdesk;


import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.callcenter.data.SendMailDTO;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskEmailResult;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.framework.page.PageParam;

public interface HelpdeskCommonFOComponent {

	
	/**
	 * 매장 목록 조회 리스트
	 * 
	 * @param shopSearchDTO
	 * @return
	 */
	public Page<SysShopExtends> getHelpdeskShopList(ShopSearchDTO shopSearchDTO,PageParam pageParam)throws Exception ;
	
	
	/**	1:1문의 메일 템플릿 입력정보 */
	public HelpdeskEmailResult getSendEmailTemplateInfo(SendMailDTO sendMailDTO) throws Exception;
	
	/**	단체 제휴 메일 템플릿 입력정보 */
	public HelpdeskEmailResult getSendAffOrdEmailTemplateInfo(SendMailDTO sendMailDTO) throws Exception;
	
}
