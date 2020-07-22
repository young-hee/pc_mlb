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

import com.plgrim.ncp.base.entities.datasource1.com.ComBrndHist;
import com.plgrim.ncp.base.repository.com.ComRepository;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.result.ComBrndHistResult;


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
public class VendorBrndHistRepository extends ComRepository  {

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
	 *  업체 브랜드 이력 건수 조회.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
    public long selectBrndHistListCount(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.brndhist.selectBrndHistListCount", vendorSearchDTO);
	}
	

	/**
	 * 업체 브랜드 이력 조회.
	 *
	 * @param comBrndHist the ComBrndHist
	 * @return the ComBrndHist
	 * @throws SQLException the SQL exception
	 */
	public List<ComBrndHistResult> selectBrndHistList(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brndhist.selectBrndHistList", vendorSearchDTO);
	}    
	
	/**
	 * 업체 브랜드 이력 조회 엑셀.
	 *
	 * @param comBrndHist the ComBrndHist
	 * @return the ComBrndHist
	 * @throws SQLException the SQL exception
	 */
	public List<Map<String, Object>> selectBrndHistListExcel(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.brndhist.selectBrndHistListExcel", vendorSearchDTO);
	} 	
    
	/**
	 * 업체 브랜드 이력 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 9
	 */
	public void updateBrndHist(ComBrndHist comBrndHist) {
		getSession1().update("com.plgrim.ncp.biz.vendor.brndhist.updateComBrndHist", comBrndHist);
	}


		
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
