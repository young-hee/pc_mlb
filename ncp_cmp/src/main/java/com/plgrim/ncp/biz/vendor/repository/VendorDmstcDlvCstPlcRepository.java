/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 17       
 */
package com.plgrim.ncp.biz.vendor.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcExcp;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcHist;
import com.plgrim.ncp.base.repository.com.ComRepository;
import com.plgrim.ncp.biz.vendor.data.VendorGridDTO;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.result.ComDmstcDlvCstPlcResult;


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
 * @author sy59.gim
 * @since 2015. 3. 24
 */
/**
 * @author user
 *
 */
@Repository
public class VendorDmstcDlvCstPlcRepository extends ComRepository  {

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
	
	/**
	 * 배송비 정책 조회 콤보.
	 *
	 * @param vendorSearchDTO
	 * @return the ComDmstcDlvCstPlcResult
	 * @throws SQLException the SQL exception
	 */
	public List<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcCombo(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectComDmstcDlvCstPlcCombo", vendorSearchDTO);
	} 
	
	/**
	 * 배송비 정책 리스트 건수 조회
	 * @param vendorSearchDTO
	 * @return
	 */
    public long selectComDmstcDlvCstPlcListCount(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComDmstcDlvCstPlcListCount", vendorSearchDTO);
	}
	

	/**
	 * 배송비 정책 리스트 조회
	 * @param vendorSearchDTO
	 * @return
	 */
	public List<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcList(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectComDmstcDlvCstPlcList", vendorSearchDTO);
	}
	
	/**
	 *  배송비 정책 건수 조회.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
//    public long selectComDmstcDlvCstPlcCount(VendorSearchDTO vendorSearchDTO) {
//		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComDmstcDlvCstPlcCount", vendorSearchDTO);
//	}
//	

	/**
	 * 배송비 정책 상세 조회.
	 *
	 * @param vendorSearchDTO
	 * @return the ComDmstcDlvCstPlcResult
	 * @throws SQLException the SQL exception
	 */
	public ComDmstcDlvCstPlcResult selectComDmstcDlvCstPlc(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComDmstcDlvCstPlc", vendorSearchDTO);
	}

	//배송비 정책 상세 조회. (CS 에서 업체 기본 배송 정책 조회 용)
	public List<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcFromCs(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectComDmstcDlvCstPlcFromCs", vendorSearchDTO);
	}

	/**
	 * 배송비 정책 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 9
	 */
	public int updateComDmstcDlvCstPlc(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.updateComDmstcDlvCstPlc", comDmstcDlvCstPlc);
	}
	
	/**
	 * 배송비정책 기본배송지 수정
	 * @param comDmstcDlvCstPlc
	 */
	public void updateComDmstcDlvCstPlcBaseDlv(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		getSession1().update("com.plgrim.ncp.biz.vendor.updateComDmstcDlvCstPlcBaseDlv", comDmstcDlvCstPlc);
	}
	
	/**
	 * 상품 배송정책번호 수정
	 * @param comDmstcDlvCstPlc
	 */
	public void updateGodDlvCstPlcSn(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		getSession1().update("com.plgrim.ncp.biz.vendor.updateGodDlvCstPlcSn", comDmstcDlvCstPlc);
	}

	/**
	 * 배송비 정책 저장.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 9
	 */
	public void insertComDmstcDlvCstPlc(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		getSession1().update("com.plgrim.ncp.biz.vendor.insertComDmstcDlvCstPlc", comDmstcDlvCstPlc);
	}	
	/**
	 * 배송비 정책 이력저장.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 9
	 */
	public void insertComDmstcDlvCstPlcHist(ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist) {
		getSession1().update("com.plgrim.ncp.biz.vendor.insertComDmstcDlvCstPlcHist", comDmstcDlvCstPlcHist);
	}
	
	/**
	 *  도서산간배송비 건수 조회.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
    public long selectComDmstcDlvCstPlcExcpCount(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComDmstcDlvCstPlcExcpCount", vendorSearchDTO);
	}
	

	/**
	 * 도서산간배송비 조회.
	 *
	 * @param vendorSearchDTO
	 * @return the ComDmstcDlvCstPlcResult
	 * @throws SQLException the SQL exception
	 */
	public List<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcExcp(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectComDmstcDlvCstPlcExcp", vendorSearchDTO);
	} 	
	
	/**
	 * 도서산간배송비 조회 엑셀.
	 *
	 * @param vendorSearchDTO
	 * @return the ComDmstcDlvCstPlcResult
	 * @throws SQLException the SQL exception
	 */
	public List<Map<String, Object>> selectComDmstcDlvCstPlcExcpExcel(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectComDmstcDlvCstPlcExcpExcel", vendorSearchDTO);
	} 	
		
	
	/**
	 * 도서산간배송비 저장.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 17
	 */
	public void mergeDlvCstPlcExcp(VendorGridDTO vendorListResult) {
		//Com com = vendorListResult.getCom();
		//getSession1().update("com.plgrim.ncp.biz.vendor.mergeVendorGridData", com);
		getSession1().insert("com.plgrim.ncp.biz.vendor.mergeDlvCstPlcExcp", vendorListResult);
	}  
	
	/**
	 * 도서산간배송비 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 17
	 */
	public void deleteDlvCstPlcExcp(ComDmstcDlvCstPlcExcp comDmstcDlvCstPlcExcp) {
		//Com com = vendorListResult.getCom();
		//getSession1().update("com.plgrim.ncp.biz.vendor.mergeVendorGridData", com);
		getSession1().delete("com.plgrim.ncp.base.deleteComDmstcDlvCstPlcExcp", comDmstcDlvCstPlcExcp);
	}
	
	/**
	 * 업체 통화 국내 배송비 이력 삭제
	 * @param comCrncyDmstcDlvCstHist
	 */
	/*
	public void deleteComCrncyDmstcDlvCstHist(ComCrncyDmstcDlvCstHist comCrncyDmstcDlvCstHist) {
		getSession1().delete("com.plgrim.ncp.biz.vendor.deleteComCrncyDmstcDlvCstHist", comCrncyDmstcDlvCstHist);
	}
	*/
	/**
	 * 업체 통화 국내 배송비 삭제
	 * @param comCrncyDmstcDlvCst
	 */
	/*
	public void deleteComCrncyDmstcDlvCst(ComCrncyDmstcDlvCst comCrncyDmstcDlvCst) {
		getSession1().delete("com.plgrim.ncp.biz.vendor.deleteComCrncyDmstcDlvCst", comCrncyDmstcDlvCst);
	}
	*/
	/**
	 * 업체 국내 배송비 정책 이력 삭제
	 * @param comDmstcDlvCstPlcHist
	 */
	public void deleteComDmstcDlvCstPlcHist(ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist) {
		getSession1().delete("com.plgrim.ncp.biz.vendor.deleteComDmstcDlvCstPlcHist", comDmstcDlvCstPlcHist);
	}
	
	/**
	 * 업체 국내 배송비 정책 예외 삭제
	 * @param comDmstcDlvCstPlcExcp
	 */
	public void deleteComDmstcDlvCstPlcExcp(ComDmstcDlvCstPlcExcp comDmstcDlvCstPlcExcp) {
		getSession1().delete("com.plgrim.ncp.biz.vendor.deleteComDmstcDlvCstPlcExcp", comDmstcDlvCstPlcExcp);
	}
	
	/**
	 * 업체 국내 배송비 정책 삭제
	 * @param comDmstcDlvCstPlc
	 */
	public void deleteComDmstcDlvCstPlc(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		getSession1().delete("com.plgrim.ncp.biz.vendor.deleteComDmstcDlvCstPlc", comDmstcDlvCstPlc);
	}

	/**
	 * 기본 배송 기간 안내 조회
     */
	public String selectBaseDlvMthdDscr() {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectBaseDlvMthdDscr");
	}
		
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
