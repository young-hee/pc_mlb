package com.plgrim.ncp.cmp.vendor.bo;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.biz.vendor.data.VendorGoodsOptionDTO;
import com.plgrim.ncp.biz.vendor.result.VendorGoodsOptionResult;
import com.plgrim.ncp.biz.vendor.result.VendorListResult;
import com.plgrim.ncp.framework.data.SystemPK;

import java.util.List;

public interface VendorCompanyBOComponent {

    /**
     * 업체 등록
     *
     * @param systemPK
     * @param com
     * @throws Exception
     */
    public String insertVendor(SystemPK systemPK, Com com) throws Exception;

    /**
     * 업체 리스트 수정
     *
     * @param updateList
     * @throws Exception
     */
    public void updateVendorList(List<VendorListResult> updateList) throws Exception;

    /**
     * 업체 상세 조회
     * @param systemPK
     * @param com
     * @return
     * @throws Exception
     */
    public Com selectVendor(SystemPK systemPK, Com com) throws Exception;


}
