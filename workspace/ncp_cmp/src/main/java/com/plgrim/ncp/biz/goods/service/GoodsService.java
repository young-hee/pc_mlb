package com.plgrim.ncp.biz.goods.service;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.com.ComGodOpt;
import com.plgrim.ncp.base.entities.datasource1.com.ComGodOptVal;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodColor;
import com.plgrim.ncp.base.entities.datasource1.god.GodColorLang;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodNtfcDetail;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMall;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.repository.com.ComGodOptRepository;
import com.plgrim.ncp.base.repository.com.ComGodOptValRepository;
import com.plgrim.ncp.base.repository.com.ComRepository;
import com.plgrim.ncp.base.repository.dsp.DspCtgryCnncGodRepository;
import com.plgrim.ncp.base.repository.dsp.DspGodPrcRepository;
import com.plgrim.ncp.base.repository.god.GodAditDetailRepository;
import com.plgrim.ncp.base.repository.god.GodColorLangRepository;
import com.plgrim.ncp.base.repository.god.GodColorRepository;
import com.plgrim.ncp.base.repository.god.GodCpstGodCnncRepository;
import com.plgrim.ncp.base.repository.god.GodDsgnGrpRepository;
import com.plgrim.ncp.base.repository.god.GodHistRepository;
import com.plgrim.ncp.base.repository.god.GodIconCnncRepository;
import com.plgrim.ncp.base.repository.god.GodImgRepository;
import com.plgrim.ncp.base.repository.god.GodItmHistRepository;
import com.plgrim.ncp.base.repository.god.GodItmOptRepository;
import com.plgrim.ncp.base.repository.god.GodItmRepository;
import com.plgrim.ncp.base.repository.god.GodLangbyGodNmHistRepository;
import com.plgrim.ncp.base.repository.god.GodLangbyGodNmRepository;
import com.plgrim.ncp.base.repository.god.GodLndrDscrRepository;
import com.plgrim.ncp.base.repository.god.GodLndrImgRepository;
import com.plgrim.ncp.base.repository.god.GodLndrRepository;
import com.plgrim.ncp.base.repository.god.GodModelImgCnncRepository;
import com.plgrim.ncp.base.repository.god.GodModelRepository;
import com.plgrim.ncp.base.repository.god.GodModelSizeRepository;
import com.plgrim.ncp.base.repository.god.GodMoviRepository;
import com.plgrim.ncp.base.repository.god.GodNotiRepository;
import com.plgrim.ncp.base.repository.god.GodNtfcDetailRepository;
import com.plgrim.ncp.base.repository.god.GodOptRepository;
import com.plgrim.ncp.base.repository.god.GodOptValRepository;
import com.plgrim.ncp.base.repository.god.GodRelateRepository;
import com.plgrim.ncp.base.repository.god.GodRepository;
import com.plgrim.ncp.base.repository.god.GodSaleMallRepository;
import com.plgrim.ncp.base.repository.god.GodShopItmInvRepository;
import com.plgrim.ncp.base.repository.sys.SysAdminBrndRepository;
import com.plgrim.ncp.base.repository.sys.SysBrndPrdlstRepository;
import com.plgrim.ncp.base.repository.sys.SysBrndRepository;
import com.plgrim.ncp.base.repository.sys.SysMallRepository;
import com.plgrim.ncp.biz.goods.data.GoodsExcelDTO;
import com.plgrim.ncp.biz.vendor.repository.ComChargerRepository;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.interfaces.goods.adapter.GoodsAdapter;

import lombok.extern.slf4j.Slf4j;

/** Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	:	상품 Service
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Slf4j
@Service
public class GoodsService extends AbstractService{
	  
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;	
	
	@Autowired
	IdGenService idGenService;
	
	@Autowired
	GodDsgnGrpRepository godDsgnGrpRepository;
	
	@Autowired
	GodRepository godRepository;
	
	@Autowired
	GodHistRepository godHistRepository;
	
	@Autowired
	GodLangbyGodNmRepository godLangbyGodNmRepository;
	
	@Autowired
	GodLangbyGodNmHistRepository godLangbyGodNmHistRepository;
	
	@Autowired
	GodOptRepository godOtpRepository;
	
	@Autowired
	GodOptValRepository godOptValRepository;
	
	@Autowired
	GodItmRepository godItmRepository;
	
	@Autowired
	GodItmOptRepository godItmOptRepository;
	
	@Autowired
	GodItmHistRepository godItmHistRepository;
	
	@Autowired
	GodShopItmInvRepository godShopItmInvRepository;
	
	@Autowired
	GodNtfcDetailRepository godNtfcDetailRepository;
	
	@Autowired
	DspCtgryCnncGodRepository dspCtgryCnncGodRepository;
	
	@Autowired
	DspGodPrcRepository dspGodPrcRepository;
	
	@Autowired
	GodCpstGodCnncRepository godCpstGodCnncRepository;
	
	@Autowired
	GodSaleMallRepository godSaleMallRepository;
	
	@Autowired
	GodIconCnncRepository godIconCnncRepository;
	
	@Autowired
	GodImgRepository godImgRepository;
	
	@Autowired
	GodRelateRepository godRelateRepository;
	
	@Autowired
	GodAditDetailRepository godAditDetailRepository;
	
	@Autowired
	GodModelImgCnncRepository godModelImgCnncRepository;
	
	@Autowired
	GodLndrRepository godLndrRepository;
	
	@Autowired
	GodLndrDscrRepository godLndrDscrRepository;
	
	@Autowired
	GodLndrImgRepository godLndrImgRepository;	
	
	@Autowired
	GodColorRepository godColorRepository;
	
	@Autowired
	GodColorLangRepository godColorLangRepository;
	
	@Autowired
	GodNotiRepository godNotiRepository;
  	
	@Autowired
	GodModelRepository godModelRepository;
	
	@Autowired
	GodModelSizeRepository godModelSizeRepository;	
	
	@Autowired
	GodMoviRepository godMoviRepository;
	
	@Autowired
	ComRepository comRepository;
	
	@Autowired
	ComChargerRepository comChargerRepository; 
	
	@Autowired
	ComGodOptRepository comGodOptRepository;
	
	@Autowired
	ComGodOptValRepository comGodOptValRepository;
	
	@Autowired
	SysBrndRepository sysBrndRepository;

	@Autowired
	SysBrndPrdlstRepository sysBrndPrdlstRepository;
	
	@Autowired
	SysAdminBrndRepository sysAdminBrndRepository;
	
	@Autowired
	SysMallRepository sysMallRepository;
	
	@Autowired
	GoodsAdapter goodsAdapter;
	
	String[] langs = {
			String.valueOf(GoodsEnum.KOR)
		,	String.valueOf(GoodsEnum.ENG)
		//,	String.valueOf(GoodsEnum.CHI)
	};
	
	
	protected void stackTrace(Exception e) {
		StackTraceElement[] ste = e.getStackTrace();		
		String className = ste[0].getClassName();
		String methodName = ste[0].getMethodName();
		int lineNumber = ste[0].getLineNumber();
		String fileName = ste[0].getFileName();
		
		log.debug("Exception : ", e.getMessage());
		log.debug("Class name {} ", className , "Method name {} ", methodName, "Line number {} ", lineNumber, "File name {} ", fileName);		
	}
	
	/**
	 * 상품 정보 조회
	 * - GOD table select by primary key 
	 * 
	 * @param god
	 * @return
	 */
	public God getGoods(String godNo){
		God god = new God();
		god.setGodNo(godNo);
		return godRepository.selectGod(god);
	}
	
	/**
	 * 상품 언어별 상품 정보 조회
	 * - GOD_LANGBY_GOD_NM table select by primary key
	 * 
	 * @param godLangbyGodNm
	 * @return
	 */
	public GodLangbyGodNm getGoodsLangByGoodsName(String godNo, String langCd) {
		GodLangbyGodNm godLangbyGodNm = new GodLangbyGodNm();
		godLangbyGodNm.setGodNo(godNo);
		godLangbyGodNm.setLangCd(langCd);
		return godLangbyGodNmRepository.selectGodLangbyGodNm(godLangbyGodNm);
	}
	
	/**
	 * 상품 단품 정보 조회
	 * - GOD_ITM table select by primary key
	 * 
	 * @param itmNo
	 * @return
	 */
	public GodItm getGoodsItem(String itmNo){
		GodItm godItm = new GodItm();
		godItm.setItmNo(itmNo);
		return godItmRepository.selectGodItm(godItm);
	}
	
	/**
	 * 상품 고시 상세 조회
	 * - GOD_NTFC_DETAIL table select by primary key
	 * 
	 * @param godNo
	 * @param langCd
	 * @return
	 */
	public GodNtfcDetail getGoodsNotificationDetail(String godNo, String langCd) {
		GodNtfcDetail godNtfcDetail = new GodNtfcDetail();
		godNtfcDetail.setGodNo(godNo);
		godNtfcDetail.setLangCd(langCd);		
		return godNtfcDetailRepository.selectGodNtfcDetail(godNtfcDetail);
	}
	
	/**
	 * 시스템 브랜드 정보 조회
	 * - SYS_BRND table select by primary key
	 * 
	 * @param brndId
	 * @return
	 * @throws Exception
	 */
	public SysBrnd getSystemBrand(String brndId){
		SysBrnd sysBrnd = new SysBrnd();
		sysBrnd.setBrndId(brndId);			
		return sysBrndRepository.selectSysBrnd(sysBrnd);
	}
	
	/**
	 * 업체 정보 조회
	 * - COM table select by primary key
	 * 
	 * @param comId
	 * @return
	 */
	public Com getCom(String comId) {
		Com com = new Com();
		com.setComId(comId);
		return comRepository.selectCom(com);
	}

	/**
	 * 업체 상품 옵션 조회
	 * - COM_GOD_OPT table select by primary key
	 * 
	 * 
	 * @param comId
	 * @param optCd
	 * @return
	 */
	public ComGodOpt getComGoodsOption(String comId, String optCd) {
		ComGodOpt comGodOpt = new ComGodOpt();
		comGodOpt.setComId(comId);
		comGodOpt.setOptCd(optCd);
		return comGodOptRepository.selectComGodOpt(comGodOpt);
	}
	
	/**
	 * 업체 옵션 값 조회
	 * - COM_GOD_OPT_VAL table select by primary key
	 * 
	 * @param comId
	 * @param optCd
	 * @param optValCd
	 * @return
	 */
	public ComGodOptVal getComGoodsOptionValue(String comId, String optCd, String optValCd) {
		ComGodOptVal comGodOptVal = new ComGodOptVal();
		comGodOptVal.setComId(comId);
		comGodOptVal.setOptCd(optCd);
		comGodOptVal.setOptValCd(optValCd);
		return comGodOptValRepository.selectComGodOptVal(comGodOptVal);
	}
	
	/**
	 * 업체 담당자 조회
	 * - COM_CHRGER table select by primary key
	 * 
	 * @param comId
	 * @param chrgerJobCd
	 * @return
	 */
	public ComChrger getComChrger(String comId, String chrgerJobCd) {
		ComChrger chrger = new ComChrger();
		chrger.setComId(comId);
		chrger.setChrgerJobCd(chrgerJobCd);
		chrger.setChrgerTurn(Integer.parseInt(String.valueOf(GoodsEnum.Number.ONE)));
		return comChargerRepository.selectCompanyCharger(chrger);
	}	
	
	/**
	 * 상품 컬럴 조회
	 * 
	 * @param colorCd
	 * @param brndGrpId
	 * @return
	 */
	public GodColor getGoodsColor(String colorCd, String brndGrpId) {
		GodColor godColor = new GodColor();
		godColor.setColorCd(colorCd);
		godColor.setBrndGrpId(brndGrpId);
		return godColorRepository.selectGodColor(godColor);
	}
	
	/**
	 * 상품 컬러 언어별 조회
	 * 
	 * @param colorCd
	 * @param brndGrpId
	 * @return
	 */
	public GodColorLang getGoodsColorLang(String colorCd, String langCd, String brndGrpId) {
		GodColorLang godColorLang = new GodColorLang();
		godColorLang.setColorCd(colorCd);
		godColorLang.setLangCd(langCd);
		godColorLang.setBrndGrpId(brndGrpId);
		return godColorLangRepository.selectGodColorLang(godColorLang);
	}
	
	/**
	 * 시스템 몰 조회 
	 * 
	 * @param mallId
	 * @return
	 */
	public SysMall getSystemMall(String mallId) {
		SysMall sysMall = new SysMall();
		sysMall.setMallId(mallId);
		return sysMallRepository.selectSysMall(sysMall);
	}
	
	/**
	 * 시스템 관리자 브랜드 조회
	 * 
	 * @param adminId
	 * @param brndId
	 * @return
	 */
	public SysAdminBrnd getSysAdminBrnd(String adminId, String brndId) {
		SysAdminBrnd sysAdminBrnd = new SysAdminBrnd();
		sysAdminBrnd.setAdminId(adminId);
		sysAdminBrnd.setBrndId(brndId);
		return sysAdminBrndRepository.selectSysAdminBrnd(sysAdminBrnd);
	}
	
	/**
	 * Excel 항목 기본 유효성 체크
	 * 	Field vaildation
	 * 
	 * @param target
	 * @param cls
	 * @return
	 */
	public GoodsExcelDTO excelFormatValidation(GoodsExcelDTO target, Class<?> cls) {
		//	validation
		target.setValidCode(String.valueOf(GoodsEnum.SUCCESS_CODE));
		target.setValidText("");
		
		// Object Validation
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<GoodsExcelDTO>> constraintViolations = validator.validate(target, cls);
		
		// Validation 에러메세지를 처리한다
		if(constraintViolations.size()>0) {
			Set<String> violationMessages = new HashSet<String>();
		    for (ConstraintViolation<GoodsExcelDTO> constraintViolation : constraintViolations) {
		        violationMessages.add("[" + constraintViolation.getPropertyPath() + "] " + constraintViolation.getMessage());
		    }
		    if(log.isInfoEnabled()){
		    	log.info("데이터 유효성 오류 : " + StringService.join(violationMessages, "\n"));
			}
		    target.setValidCode("error");
		    target.setValidText("데이터 유효성 오류 : " + StringService.join(violationMessages, "\n"));
		}		
		
		return target;		
	}
	
}
