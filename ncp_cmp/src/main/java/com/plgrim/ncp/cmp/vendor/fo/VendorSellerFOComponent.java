package com.plgrim.ncp.cmp.vendor.fo;

import com.plgrim.ncp.biz.vendor.data.VendorReqstRegDTO;

public interface VendorSellerFOComponent {

    /**
     * Helpdesk 입점 신청
     *
     * @param vendorReqstRegDTO
     * @throws Exception
     */
    public void insertVendorRequest(VendorReqstRegDTO vendorReqstRegDTO) throws Exception;

}
