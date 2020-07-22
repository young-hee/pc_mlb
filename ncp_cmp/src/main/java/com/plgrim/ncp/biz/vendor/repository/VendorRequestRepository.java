package com.plgrim.ncp.biz.vendor.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.com.ComReqstAtchmnfl;
import com.plgrim.ncp.biz.vendor.data.VendorReqstDTO;
import com.plgrim.ncp.biz.vendor.result.VendorReqstResult;
import com.plgrim.ncp.commons.result.CodeViewResult;
import com.plgrim.ncp.framework.page.PageParam;

@Repository
public class VendorRequestRepository extends AbstractRepository{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	public Page<VendorReqstResult> selectVendorRequestList(VendorReqstDTO vendorReqstDTO, PageParam pageParam) throws Exception {
		// 페이지 설정
        RepositoryHelper.makePageEntityIndex(vendorReqstDTO, pageParam);

        // 공지사항 리스트 조회
        List<VendorReqstResult> resultList = getSession1().selectList("com.plgrim.ncp.biz.vendor.request.selectVendorRequestList", vendorReqstDTO);

        // 공지사항 게시글 갯수 조회
        long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.vendor.request.selectVendorRequestTotal", vendorReqstDTO);
        return new PageImpl<VendorReqstResult>(resultList, pageParam.getPageable(), totalRow);
	}
	
	public int updateVendorRequest(VendorReqstDTO vendorReqstDTO) throws Exception {
		int result = getSession1().update("com.plgrim.ncp.biz.vendor.request.updateVendorRequest", vendorReqstDTO.getComReqst());
		return result;
	}
	
	public int updateVendorPrcsCompt(VendorReqstDTO vendorReqstDTO) throws Exception {
		int result = getSession1().update("com.plgrim.ncp.biz.vendor.request.updateVendorPrcsCompt", vendorReqstDTO.getComReqst());
		return result;
	}
	public VendorReqstResult getVendorRequest(VendorReqstDTO vendorReqstDTO) throws Exception {
		VendorReqstResult vendorReqstResult = getSession1().selectOne("com.plgrim.ncp.biz.vendor.request.getVendorRequest", vendorReqstDTO);
		return vendorReqstResult;
	}
	
	public List<ComReqstAtchmnfl> selectVendorRequestAtchmnfl(VendorReqstDTO vendorReqstDTO) throws Exception {
		List<ComReqstAtchmnfl> resultList = getSession1().selectList("com.plgrim.ncp.biz.vendor.request.selectVendorRequestAtchmnfl", vendorReqstDTO);
		return resultList;
	}
	
	public List<CodeViewResult> selectNatioinCdList() throws Exception {
		List<CodeViewResult> resultList = getSession1().selectList("com.plgrim.ncp.biz.vendor.request.selectNationCdList");
		return resultList;
	}
	
}
