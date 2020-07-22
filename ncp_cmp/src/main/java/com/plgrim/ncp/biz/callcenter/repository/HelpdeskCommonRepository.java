package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.callcenter.data.SendMailDTO;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskEmailResult;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.framework.page.PageParam;


@Repository
public class HelpdeskCommonRepository extends AbstractRepository{

	/**
	 * 매장 목록 조회 페이징
	 * 
	 * @param shopSearchDTO
	 * @return
	 * @throws Exception 
	 */
	public Page<SysShopExtends> getHelpdeskShopList(ShopSearchDTO shopSearchDTO,PageParam pageParam) throws Exception {
		
		RepositoryHelper.makePageEntityIndex(shopSearchDTO, pageParam);
		List<SysShopExtends> results= getSession1().selectList("com.plgrim.ncp.biz.helpdesk.search.getHelpdeskShopList", shopSearchDTO);
		long totalRow = getHelpdeskShopListCount(shopSearchDTO);
		return new PageImpl<SysShopExtends>(results, pageParam.getPageable(), totalRow);
	}
	
	/**	매방 리스트 카운트 */
	public long getHelpdeskShopListCount(ShopSearchDTO shopSearchDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.helpdesk.search.getHelpdeskShopListCount", shopSearchDTO);
	}
	
	/**	1:1문의 메일 템플릿 입력정보 */
	public HelpdeskEmailResult getSendEmailTemplateInfo(SendMailDTO sendMailDTO) throws Exception{
		
		return getSession1().selectOne("com.plgrim.ncp.biz.helpdesk.search.getSendEmailTemplateInfo", sendMailDTO);
	}
	
	/**	단체 제휴 메일 템플릿 입력정보 */
	public HelpdeskEmailResult getSendAffOrdEmailTemplateInfo(SendMailDTO sendMailDTO) throws Exception{
		
		return getSession1().selectOne("com.plgrim.ncp.biz.helpdesk.search.getSendAffOrdEmailTemplateInfo", sendMailDTO);
	}
	
	
	
}