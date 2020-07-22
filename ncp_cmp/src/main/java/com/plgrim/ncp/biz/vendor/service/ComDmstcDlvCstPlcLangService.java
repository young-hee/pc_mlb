/* Copyright (c) 2017 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      siga77.lee
 * @since       2017. 10. 26       
 */
package com.plgrim.ncp.biz.vendor.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcLang;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcLangHis;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlcLangHis;
import com.plgrim.ncp.base.repository.com.ComDmstcDlvCstPlcLangHisRepository;
import com.plgrim.ncp.base.repository.com.ComDmstcDlvCstPlcLangRepository;
import com.plgrim.ncp.biz.vendor.repository.VendorOvseaDlvCstPlcRepository;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

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
 * @author siga77.lee
 * @since 2017. 10. 26
 */
@Service
public class ComDmstcDlvCstPlcLangService  extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 배송비정책언어 Repository
	 */
	@Autowired
	private ComDmstcDlvCstPlcLangRepository comDmstcDlvCstPlcLangRepository;
	/**
	 * 배송비정책언어이력 Repository
	 */
	@Autowired
	private ComDmstcDlvCstPlcLangHisRepository comDmstcDlvCstPlcLangHisRepository;
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;	
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
	 * 글로벌 언어별 배송안내 문구수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlc [설명]
	 * @throws Exception the exception
	 * @since 2017. 10. 26
	 */
	public int updateComDmstcDlvCstPlcLang(ComDmstcDlvCstPlcLang comDmstcDlvCstPlcLang) throws Exception{
		comDmstcDlvCstPlcLang.setUdterId(BOSecurityUtil.getLoginId());
		comDmstcDlvCstPlcLang.setUdterId(BOSecurityUtil.getLoginId());
		
		if(null==comDmstcDlvCstPlcLangRepository.selectComDmstcDlvCstPlcLang(comDmstcDlvCstPlcLang)){
			comDmstcDlvCstPlcLangRepository.insertComDmstcDlvCstPlcLang(comDmstcDlvCstPlcLang);
			return 1;
		}else{
			return comDmstcDlvCstPlcLangRepository.updateComDmstcDlvCstPlcLang(comDmstcDlvCstPlcLang);
			
		}
	}	
	
	/**
	 * 글로벌 언어별 배송안내 문구수정 이력등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlc [설명]
	 * @throws Exception the exception
	 * @since 2017. 10. 26
	 */
	public void insertComDmstcDlvCstPlcLangHis(ComDmstcDlvCstPlcLangHis comDmstcDlvCstPlcLangHis) throws Exception{
		comDmstcDlvCstPlcLangHis.setUdterId(BOSecurityUtil.getLoginId());
		comDmstcDlvCstPlcLangHis.setUdterId(BOSecurityUtil.getLoginId());
		comDmstcDlvCstPlcLangHisRepository.insertComDmstcDlvCstPlcLangHis(comDmstcDlvCstPlcLangHis);
	}	
}
