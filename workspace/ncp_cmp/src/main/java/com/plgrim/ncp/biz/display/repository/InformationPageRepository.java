package com.plgrim.ncp.biz.display.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatHistExtends;
import com.plgrim.ncp.base.repository.sys.SysStplatRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class InformationPageRepository extends SysStplatRepository{

	
	/**
	 * 시스템 약관 상세 영문 조회.
	 *
	 * @param sysStplat the SysStplat
	 * @return the SysStplat
	 * @throws SQLException the SQL exception
	 */
	public SysStplat selectSysStplatByLang(SysStplat sysStplat) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectSysStplatByLang", sysStplat);
	}

	/**
	 * 시스템 약관 history 조회.
	 *
	 * @param sysStplat the SysStplat
	 * @return the List<SysStplatHistExtends>
	 * @throws SQLException the SQL exception
	 */
	public List<SysStplatHistExtends> selectSysStplatHist(SysStplat sysStplat) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectSysStplatHist", sysStplat);
	}	
	
	/**
	 * 시스템 약관 history 영문 조회.
	 *
	 * @param sysStplat the SysStplat
	 * @return the List<SysStplatHistExtends>
	 * @throws SQLException the SQL exception
	 */
	public List<SysStplatHistExtends> selectSysStplatHistByLang(SysStplat sysStplat) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectSysStplatHistByLang", sysStplat);
	}

	/**
	 * 시스템 약관 제목 영문 조회.
	 *
	 * @param 
	 * @return 
	 * @throws SQLException the SQL exception
	 */
	public String selectSysStplatTitleByLang(String stplatCd) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectSysStplatTitleByLang", stplatCd);
	}
	
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

}
