package com.plgrim.ncp.cmp.vendor.fo.shop;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.sys.SysShopEvt;
import com.plgrim.ncp.biz.vendor.result.VendorBrndEvtResult;
import com.plgrim.ncp.biz.vendor.service.VendorBrndService;
import com.plgrim.ncp.cmp.vendor.fo.VendorShopFOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.biz.vendor.result.SearchShopResult;
import com.plgrim.ncp.biz.vendor.service.ShopSearchService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

@Component
@Transactional(value="transactionManager")
public class VendorShopFOComponentImpl extends AbstractComponent implements VendorShopFOComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	ShopSearchService  shopSearchService;

	@Autowired
	private VendorBrndService vendorBrndService;
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

	/** 매장찾기 브랜드명조회 */
	@Override
	public List<SearchShopResult> getShopSrchBrndList(SystemPK pk,ShopSearchDTO shopSearchDTO) throws Exception{
		return shopSearchService.getShopSrchBrndList(shopSearchDTO);
	}

	/** 매장찾기 목록 */
	@Override
	public List<SearchShopResult> getShopSearchList(SystemPK pk,ShopSearchDTO shopSearchDTO) throws Exception{
		return shopSearchService.getShopSearchList(shopSearchDTO);
	}

	/** 구매가능 오프라인 매장찾기 목록 */
	@Override
	public List<SearchShopResult> getShopSearchForAvailableList(SystemPK pk,ShopSearchDTO shopSearchDTO) throws Exception{
		return shopSearchService.getShopSearchForAvailableList(shopSearchDTO);
	}

	/** 매장명 조회 */
	@Override
	public SearchShopResult selectSearchShopInfo(SystemPK pk,ShopSearchDTO shopSearchDTO) throws Exception{
		return shopSearchService.selectSearchShopInfo(shopSearchDTO);
	}

	/** 모바일 매장찾기 목록 */
	@Override
	public List<SearchShopResult> getMobileShopSearchList(SystemPK pk,ShopSearchDTO shopSearchDTO, PageParam pageParam) throws Exception{
		return shopSearchService.getMobileShopSearchList(shopSearchDTO, pageParam);
	}

	/** 매장찾기 건수 */
	@Override
	public SearchShopResult getShopSearchCount(SystemPK pk,ShopSearchDTO shopSearchDTO) throws Exception{
		return shopSearchService.getShopSearchCount(shopSearchDTO);
	}

	/** 매장찾기 건수 */
	@Override
	public SearchShopResult getShopSearchForAvailCount(SystemPK pk,ShopSearchDTO shopSearchDTO) throws Exception{
		return shopSearchService.getShopSearchForAvailCount(shopSearchDTO);
	}

	/** 매장찾기 이벤트 */
	@Override
	public List<SearchShopResult> getShopSearchEvent(SystemPK pk,ShopSearchDTO shopSearchDTO) throws Exception{
		return shopSearchService.getShopSearchEvent(shopSearchDTO);
	}


	/** 가까운 매장 목록 */
	@Override
	public List<SearchShopResult> getNearShopSearchList(SystemPK pk, ShopSearchDTO shopSearchDTO) throws Exception {
		return shopSearchService.getNearShopSearchList(shopSearchDTO);
	}

	/** 가까운 매장 목록 구매가능매장*/
	@Override
	public List<SearchShopResult> getNearShopSearchForAvailList(SystemPK pk, ShopSearchDTO shopSearchDTO) throws Exception {
		return shopSearchService.getNearShopSearchForAvailList(shopSearchDTO);
	}

	/**
	 * 브랜드 매장 이벤트 리스트 조회
	 * @param shopSearchDTO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<VendorBrndEvtResult> selectBrndShopEvtList(ShopSearchDTO shopSearchDTO)throws Exception{
		return vendorBrndService.selectBrndShopEvtList(shopSearchDTO);
	}

	/**
	 * 브랜드 매장 이벤트 단건 조회
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	@Override
	public VendorBrndEvtResult getOneSysShopEvt(SysShopEvt sysShopEvt)throws Exception{
		return vendorBrndService.selectOneSysShopEvt(sysShopEvt);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */





}
