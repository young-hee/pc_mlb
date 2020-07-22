package com.plgrim.ncp.cmp.vendor.bo.affiliate;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.biz.vendor.data.VendorGridDTO;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.result.ComBrndHistResult;
import com.plgrim.ncp.biz.vendor.result.VendorListResult;
import com.plgrim.ncp.biz.vendor.service.ComBrndHistService;
import com.plgrim.ncp.biz.vendor.service.VendorService;
import com.plgrim.ncp.cmp.vendor.bo.VendorAffiliateBOComponent;
import com.plgrim.ncp.cmp.vendor.bo.common.VendorBOComponentImpl;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
public class VendorAffiliateBOComponentImpl extends VendorBOComponentImpl implements VendorAffiliateBOComponent {

	@Autowired
	private VendorService vendorService;

	@Autowired
	private ComBrndHistService comBrndHistService;


	@Override
	public void mergeBrndCommissionGridData(List<VendorGridDTO> dataList) throws Exception {

		vendorService.mergeBrndCommissionGridData(dataList);
	}

	@Override
	public void deleteBrndCommissionGridData(List<VendorGridDTO> dataList) throws Exception {
		vendorService.deleteBrndCommissionGridData(dataList);

	}

	@Override
	public void mergeComAffVrscComCnnc(List<VendorGridDTO> dataList) throws Exception{

		vendorService.mergeComAffVrscComCnnc(dataList);
	}

	@Override
	public void deleteComAffVrscComCnnc(List<VendorGridDTO> dataList) throws Exception {
		// TODO Auto-generated method stub
		vendorService.deleteComAffVrscComCnnc(dataList);
	}

	/**
	 * 판매제휴사 등록
	 *
	 * @param com the com
	 * @return the string
	 * @throws Exception the exception
	 */
	public int insertAffCom(Com com) throws Exception{
		return vendorService.insertAffCom(com);
	}

	@Override
	public Page<VendorListResult> getBrandCommissionList(SystemPK systemPK,
														 VendorSearchDTO vendorSearchDTO) throws Exception {

		return vendorService.selectBrandCommissionList(vendorSearchDTO);
	}

	/**
	 * 수수료율 이력 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param vendorSearchDTO [설명]
	 * @return the vendor list
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	@Override
	public Page<ComBrndHistResult> getComBrndHistList(SystemPK systemPK,
													  VendorSearchDTO vendorSearchDTO) throws Exception {
		return comBrndHistService.selectBrndHistList(vendorSearchDTO);
	}

	@Override
	public List<Map<String, Object>> getBrandCommissionListExcel(
			SystemPK systemPK, VendorSearchDTO vendorSearchDTO)
			throws Exception {
		// TODO Auto-generated method stub
		return vendorService.selectBrandCommissionListExcel(vendorSearchDTO);
	}

	@Override
	public List<Map<String, Object>> getComBrndHistListExcel(SystemPK systemPK,
															 VendorSearchDTO vendorSearchDTO) throws Exception {
		// TODO Auto-generated method stub
		return comBrndHistService.selectBrndHistListExcel(vendorSearchDTO);
	}

	/**
	 * 판매 제휴사 상세 조회
	 *
	 * @param com the com
	 * @return the com
	 * @throws Exception the exception
	 */
	@Override
	public List<Com> selectAffCom(Com com) throws Exception{
		return vendorService.selectAffCom(com);
	}

	/**
	 * 제휴사별 상품속성관리 조회.
	 *
	 * @param searchDTO
	 * @return the ComDmstcDlvCstPlcResult
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Page<VendorListResult> selectComAffVrscComCnncList(VendorSearchDTO searchDTO) throws Exception{

		return vendorService.selectComAffVrscComCnncList(searchDTO);
	}

	@Override
	public List<Map<String, Object>> selectComAffVrscComCnncListExcel(
			VendorSearchDTO searchDTO) throws Exception {
		// TODO Auto-generated method stub
		return vendorService.selectComAffVrscComCnncListExcel(searchDTO);
	}

}
