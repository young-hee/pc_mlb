
/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://www.plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              			Date                         	Description
 * ------------------   		--------------                  ------------------
 * Generator(Generator)		2018-07-02                      
 */
package com.plgrim.ncp.base.repository.sys;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlstPom;

/**
 * The Class SysBrndPrdlstPomRepository.
 */
@Repository
public class SysBrndPrdlstPomRepository extends AbstractRepository {
	

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
	 * 시스템 브랜드 품목 POM 상세 조회.
	 *
	 * @param sysBrndPrdlstPom the SysBrndPrdlstPom
	 * @return the SysBrndPrdlstPom
	 * @throws SQLException the SQL exception
	 */
	public SysBrndPrdlstPom selectSysBrndPrdlstPom(SysBrndPrdlstPom sysBrndPrdlstPom) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectSysBrndPrdlstPom", sysBrndPrdlstPom);
	}
	
	/**
	 * 시스템 브랜드 품목 POM 등록.
	 *
	 * @param sysBrndPrdlstPom the SysBrndPrdlstPom
	 * @throws SQLException the SQL exception
	 */
	public void insertSysBrndPrdlstPom(SysBrndPrdlstPom sysBrndPrdlstPom) {
		getSession1().insert("com.plgrim.ncp.base.insertSysBrndPrdlstPom", sysBrndPrdlstPom);
	}
	
	/**
	 * 시스템 브랜드 품목 POM 수정.
	 *
	 * @param sysBrndPrdlstPom the SysBrndPrdlstPom
	 * @throws SQLException the SQL exception
	 */
	public int updateSysBrndPrdlstPom(SysBrndPrdlstPom sysBrndPrdlstPom) {
		return getSession1().update("com.plgrim.ncp.base.updateSysBrndPrdlstPom", sysBrndPrdlstPom);
	}
	
	/**
	 * 시스템 브랜드 품목 POM 삭제.
	 *
	 * @param sysBrndPrdlstPom the SysBrndPrdlstPom
	 * @return the SysBrndPrdlstPom
	 * @throws SQLException the SQL exception
	 */
	public int deleteSysBrndPrdlstPom(SysBrndPrdlstPom sysBrndPrdlstPom) {
		return getSession1().delete("com.plgrim.ncp.base.deleteSysBrndPrdlstPom", sysBrndPrdlstPom);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

