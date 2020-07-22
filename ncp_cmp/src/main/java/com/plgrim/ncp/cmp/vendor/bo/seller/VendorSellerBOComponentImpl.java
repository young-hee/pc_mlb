package com.plgrim.ncp.cmp.vendor.bo.seller;

import com.plgrim.ncp.biz.vendor.data.VendorGoodsOptionDTO;
import com.plgrim.ncp.biz.vendor.data.VendorReqstDTO;
import com.plgrim.ncp.biz.vendor.result.VendorGoodsOptionResult;
import com.plgrim.ncp.biz.vendor.result.VendorReqstResult;
import com.plgrim.ncp.biz.vendor.service.VendorRequestService;
import com.plgrim.ncp.biz.vendor.service.VendorService;
import com.plgrim.ncp.cmp.vendor.bo.common.VendorBOComponentImpl;
import com.plgrim.ncp.cmp.vendor.bo.VendorSellerBOComponent;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [클래스 설명]
 *
 * <p>
 *
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author yoon.eun
 * @since 2015. 11. 10
 */
@Component
@Transactional(value = "transactionManager")
public class VendorSellerBOComponentImpl extends VendorBOComponentImpl implements VendorSellerBOComponent {

	@Autowired
	private VendorService vendorService;

	@Autowired
	private VendorRequestService vendorRequestService;

	/**
	 * 업체 상품 옵션 등록
	 * @param savList
	 * @throws Exception
	 */
	@Override
	public void insertComGodOptList(List<VendorGoodsOptionDTO> savList) throws Exception {
		vendorService.insertComGodOptList(savList);
	}

	/**
	 * 입점 신청 정보 수정
	 * @param vendorReqstList
	 * @throws Exception
	 */
	@Override
	public void updateVendorRequest(List<VendorReqstDTO> vendorReqstList) throws Exception {
		vendorRequestService.updateVendorRequest(vendorReqstList);
	}

	/**
	 * 업체 상품 옵션 조회
	 */
	@Override
	public List<VendorGoodsOptionResult> getComGodOpt(VendorGoodsOptionDTO vendorGoodsOptionDTO) throws Exception{
		return vendorService.selectComGodOpt(vendorGoodsOptionDTO);
	}


	/**
	 * 입점 신청 목록 조회
	 * @param vendorReqstDTO
	 * @return Page<VendorReqstResult>
	 * @throws Exception
	 */
	@Override
	public Page<VendorReqstResult> selectVendorRequestList(VendorReqstDTO vendorReqstDTO, PageParam pageParam) throws Exception{
		return vendorRequestService.selectVendorRequestList(vendorReqstDTO, pageParam);
	}

	/**
	 * 입점 신청 정보 상세 조회
	 * @Param vendorReqstDTO
	 * @return VendorRequestResult
	 * @throws Exception
	 */
	@Override
	public VendorReqstResult getVendorRequest(VendorReqstDTO vendorReqstDTO) throws Exception {
		return vendorRequestService.getVendorRequest(vendorReqstDTO);
	}


}
