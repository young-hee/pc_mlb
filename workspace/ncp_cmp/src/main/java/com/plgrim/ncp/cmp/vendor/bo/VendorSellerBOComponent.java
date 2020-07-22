package com.plgrim.ncp.cmp.vendor.bo;

import com.plgrim.ncp.biz.vendor.data.VendorGoodsOptionDTO;
import com.plgrim.ncp.biz.vendor.data.VendorReqstDTO;
import com.plgrim.ncp.biz.vendor.result.VendorGoodsOptionResult;
import com.plgrim.ncp.biz.vendor.result.VendorReqstResult;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VendorSellerBOComponent {


    /**
     * 업체 상품 옵션 등록
     *
     * @param savList
     * @throws Exception
     */
    public void insertComGodOptList(List<VendorGoodsOptionDTO> savList) throws Exception;

    /**
     * 입점 신청 정보 수정
     *
     * @param vendorReqstList
     * @throws Exception
     * @since 2015. 11. 10.
     */
    public void updateVendorRequest(List<VendorReqstDTO> vendorReqstList) throws Exception;

    /**
     * 업체 상품 옵션 조회
     *
     * @param vendorGoodsOptionDTO
     * @return
     * @throws Exception
     */
    public List<VendorGoodsOptionResult> getComGodOpt(VendorGoodsOptionDTO vendorGoodsOptionDTO) throws Exception;

    /**
     * 입점 신청 목록 조회
     *
     * @param vendorReqstDTO
     * @param pageParam
     * @return
     * @throws Exception
     * @since 2015. 11. 10.
     */
    public Page<VendorReqstResult> selectVendorRequestList(VendorReqstDTO vendorReqstDTO, PageParam pageParam) throws Exception;

    /**
     * 입점 신청 정보 상세 조회
     *
     * @param vendorReqstDTO
     * @return
     * @throws Exception
     * @since 2015. 11. 10.
     */
    public VendorReqstResult getVendorRequest(VendorReqstDTO vendorReqstDTO) throws Exception;


}
