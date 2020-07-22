package com.plgrim.ncp.cmp.vendor.fo;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.sys.SysShopEvt;
import com.plgrim.ncp.biz.vendor.result.VendorBrndEvtResult;

import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.biz.vendor.result.SearchShopResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

public interface VendorShopFOComponent {

    /**
     * 매장찾기 브랜드명조회
     *
     * @param pk
     * @param shopSearchDTO
     * @return
     * @throws Exception
     */
    public List<SearchShopResult> getShopSrchBrndList(SystemPK pk, ShopSearchDTO shopSearchDTO) throws Exception;

    /**
     * 매장찾기 목록
     *
     * @param pk
     * @param shopSearchDTO
     * @return
     * @throws Exception
     */
    public List<SearchShopResult> getShopSearchList(SystemPK pk, ShopSearchDTO shopSearchDTO) throws Exception;

    /**
     * 매장찾기 목록
     *
     * @param pk
     * @param shopSearchDTO
     * @return
     * @throws Exception
     */
    public List<SearchShopResult> getShopSearchForAvailableList(SystemPK pk, ShopSearchDTO shopSearchDTO) throws Exception;

    /**
     * 매장명 조회
     *
     * @param pk
     * @param shopSearchDTO
     * @return
     * @throws Exception
     */
    public SearchShopResult selectSearchShopInfo(SystemPK pk, ShopSearchDTO shopSearchDTO) throws Exception;

    /**
     * 모바일 매장찾기 목록
     *
     * @param pk
     * @param shopSearchDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public List<SearchShopResult> getMobileShopSearchList(SystemPK pk, ShopSearchDTO shopSearchDTO, PageParam pageParam) throws Exception;

    /**
     * 매장찾기 건수
     *
     * @param pk
     * @param shopSearchDTO
     * @return
     * @throws Exception
     */
    public SearchShopResult getShopSearchCount(SystemPK pk, ShopSearchDTO shopSearchDTO) throws Exception;

    /**
     * 매장찾기 건수
     *
     * @param pk
     * @param shopSearchDTO
     * @return
     * @throws Exception
     */
    public SearchShopResult getShopSearchForAvailCount(SystemPK pk, ShopSearchDTO shopSearchDTO) throws Exception;

    /**
     * 매장찾기 이벤트
     *
     * @param pk
     * @param shopSearchDTO
     * @return
     * @throws Exception
     */
    public List<SearchShopResult> getShopSearchEvent(SystemPK pk, ShopSearchDTO shopSearchDTO) throws Exception;

    /**
     * 가까운 매장 목록
     *
     * @param pk
     * @param shopSearchDTO
     * @return
     * @throws Exception
     */
    public List<SearchShopResult> getNearShopSearchList(SystemPK pk, ShopSearchDTO shopSearchDTO) throws Exception;

    /**
     * 가까운 매장 목록 구매가능매장
     *
     * @param pk
     * @param shopSearchDTO
     * @return
     * @throws Exception
     */
    public List<SearchShopResult> getNearShopSearchForAvailList(SystemPK pk, ShopSearchDTO shopSearchDTO) throws Exception;


    /**
     * 브랜드 매장 이벤트 단건 조회
     *
     * @param sysShopEvt
     * @return
     * @throws Exception
     */
    public VendorBrndEvtResult getOneSysShopEvt(SysShopEvt sysShopEvt) throws Exception;

    /**
     * 브랜드 매장 이벤트 목록 조회
     *
     * @param shopSearchDTO
     * @return
     * @throws Exception
     */
    public List<VendorBrndEvtResult> selectBrndShopEvtList(ShopSearchDTO shopSearchDTO) throws Exception;


}
