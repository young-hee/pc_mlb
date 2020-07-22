package com.plgrim.ncp.biz.device.repository;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.device.data.AppVerMbDTO;

@Repository
public class InterfaceMbSfRepository extends AbstractRepository {

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
	 *  앱 최신 버전 정보 조회
	 *  
	 * @param appVerMbDTO
	 * @return
	 * @throws Exception
	 */
	public AppVerMbDTO selectAppVersionInfo(AppVerMbDTO appVerMbDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.web.mb.dx.interface.selectAppVersionInfo", appVerMbDTO);
	}
	

	 /* ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */


	

}
