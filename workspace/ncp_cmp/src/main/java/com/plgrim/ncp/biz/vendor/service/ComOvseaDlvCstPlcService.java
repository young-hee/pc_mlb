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
public class ComOvseaDlvCstPlcService  extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 업체 Repository
	 */
	@Autowired
	private VendorOvseaDlvCstPlcRepository vendorOvseaDlvCstPlcRepository;
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
	 * 글로벌 해외배송비 정책 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlc [설명]
	 * @throws Exception the exception
	 * @since 2017. 10. 26
	 */
	public int updateComOvseaDlvCstPlcEng(ComDmstcDlvCstPlc comDmstcDlvCstPlc) throws Exception{
		comDmstcDlvCstPlc.setUdterId(BOSecurityUtil.getLoginId());
		return vendorOvseaDlvCstPlcRepository.updateComOvseaDlvCstPlcEng(comDmstcDlvCstPlc);
	}

	/**
	 * 글로벌 해외배송비 정책 이력 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlc [설명]
	 * @throws Exception the exception
	 * @since 2017. 10. 31
	 */
	public int insertComOvseaDlvCstPlcEngHist(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		ComOvseaDlvCstPlcLangHis comOvseaDlvCstPlcLangHis = new ComOvseaDlvCstPlcLangHis();
		comOvseaDlvCstPlcLangHis.setOvseaDlvCstPlcSn(1L);
		comOvseaDlvCstPlcLangHis.setLangCd("ENG");
		//comOvseaDlvCstPlcLangHis.setDlvMthdDscr(comDmstcDlvCstPlc.getDlvMthdDscrEng());
		comOvseaDlvCstPlcLangHis.setRegtrId(BOSecurityUtil.getLoginId());
		comOvseaDlvCstPlcLangHis.setUdterId(BOSecurityUtil.getLoginId());
		return vendorOvseaDlvCstPlcRepository.insertComOvseaDlvCstPlcLangHist(comOvseaDlvCstPlcLangHis);
	}

	/**
	 * 중문 해외배송비 정책 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlc [설명]
	 * @throws Exception the exception
	 * @since 2017. 10. 26
	 */
	public int updateComOvseaDlvCstPlcChi(ComDmstcDlvCstPlc comDmstcDlvCstPlc) throws Exception{
		comDmstcDlvCstPlc.setUdterId(BOSecurityUtil.getLoginId());
		return vendorOvseaDlvCstPlcRepository.updateComOvseaDlvCstPlcChi(comDmstcDlvCstPlc);
	}

	/**
	 * 중문 해외배송비 정책 이력 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlc [설명]
	 * @throws Exception the exception
	 * @since 2017. 10. 31
	 */
	public int insertComOvseaDlvCstPlcChiHist(ComDmstcDlvCstPlc comDmstcDlvCstPlc) {
		ComOvseaDlvCstPlcLangHis comOvseaDlvCstPlcLangHis = new ComOvseaDlvCstPlcLangHis();
		comOvseaDlvCstPlcLangHis.setOvseaDlvCstPlcSn(1L);
		comOvseaDlvCstPlcLangHis.setLangCd("CHI");
		//comOvseaDlvCstPlcLangHis.setDlvMthdDscr(comDmstcDlvCstPlc.getDlvMthdDscrChi());
		comOvseaDlvCstPlcLangHis.setRegtrId(BOSecurityUtil.getLoginId());
		comOvseaDlvCstPlcLangHis.setUdterId(BOSecurityUtil.getLoginId());
		return vendorOvseaDlvCstPlcRepository.insertComOvseaDlvCstPlcLangHist(comOvseaDlvCstPlcLangHis);
	}

}
