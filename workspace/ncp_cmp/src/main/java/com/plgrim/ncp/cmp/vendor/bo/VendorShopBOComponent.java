package com.plgrim.ncp.cmp.vendor.bo;

import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.biz.vendor.data.VendorBrndRegDTO;
import com.plgrim.ncp.biz.vendor.data.VendorBrndSearchDTO;
import com.plgrim.ncp.biz.vendor.data.VendorGridDTO;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.result.SysShopResult;
import com.plgrim.ncp.biz.vendor.result.VendorBrndListResult;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface VendorShopBOComponent {


    /**
     * 브랜드 매장 등록
     *
     * @param vendorBrndRegDTO
     * @throws Exception
     */
    public void insertSysShopBrnd(VendorBrndRegDTO vendorBrndRegDTO) throws Exception;

    /**
     * 브랜드 매장 수정
     *
     * @param vendorBrndRegDTO
     * @throws Exception
     */
    public void updateSysShopBrnd(VendorBrndRegDTO vendorBrndRegDTO) throws Exception;


    /**
     * 매장 브랜드별 수수료율 저장
     *
     * @param lists
     * @param loginId
     * @throws Exception
     */
    public void updateSysShopBrndFee(List<VendorGridDTO> lists, String loginId) throws Exception;

    /**
     * 브랜드 매장내역 리스트 수정
     *
     * @param updateList
     * @throws Exception
     */
    public void updateVendorShopBrndList(List<VendorBrndListResult> updateList) throws Exception;

    /**
     * 매장 브랜드 수수료율 조회
     *
     * @param systemPK
     * @param searchDTO
     * @return
     * @throws Exception
     */
    public Page<SysShopResult> getShopBrandListForFee(SystemPK systemPK, VendorSearchDTO searchDTO) throws Exception;

    /**
     * 브랜드 매장내역 조회
     *
     * @param systemPK
     * @param vendorBrndSearchDTO
     * @return
     * @throws Exception
     */
    public Page<VendorBrndListResult> getVendorBrndList(SystemPK systemPK, VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception;

    /**
     * 브랜드 목록 조회(콤보박스)
     *
     * @return List<SysBrnd>
     * @throws Exception
     */
    public List<SysBrnd> getSysBrndCombo() throws Exception;

    /**
     * 브랜드 매장내역 엑셀
     *
     * @param systemPK
     * @param vendorBrndSearchDTO
     * @return List<Map               <               String               ,                               Object>>
     * @throws Exception
     */
    public List<Map<String, Object>> getVendorBrndListExcel(SystemPK systemPK, VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception;

    /**
     * 매장 브랜드 수수료율 엑셀 조회
     *
     * @param systemPK
     * @param searchDTO
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getShopBrandExcelForFee(SystemPK systemPK, VendorSearchDTO searchDTO) throws Exception;

    /**
     * 매장조회
     *
     * @param systemPK
     * @param vendorBrndSearchDTO
     * @return
     * @throws Exception
     */
    public List<VendorBrndListResult> getSysShop(SystemPK systemPK, VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception;

    /**
     * 브랜드 매장 상세조회
     *
     * @param sysShopBrnd
     * @return
     * @throws Exception
     */
    public VendorBrndListResult getSysShopBrand(SysShopBrnd sysShopBrnd) throws Exception;


}
