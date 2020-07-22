package com.plgrim.ncp.cmp.vendor.fo.seller;

import com.plgrim.ncp.biz.vendor.data.VendorReqstRegDTO;
import com.plgrim.ncp.biz.vendor.service.VendorRequestService;
import com.plgrim.ncp.cmp.vendor.fo.VendorSellerFOComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VendorSellerFOComponentImpl implements VendorSellerFOComponent {

    @Autowired
    private VendorRequestService vendorRequestService;

    @Override
    public void insertVendorRequest(VendorReqstRegDTO vendorReqstRegDTO) throws Exception {
        vendorRequestService.insertVendorRequest(vendorReqstRegDTO);
    }
}
