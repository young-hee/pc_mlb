/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 8. 27       
 */
package com.plgrim.ncp.cmp.display.bo.abtest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.plgrim.ncp.cmp.display.bo.DisplayRevCommandComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetFlter;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetMod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfig;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfigDetail;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.biz.display.data.DspAbTestExtDTO;
import com.plgrim.ncp.biz.display.data.DspAbTestSetExtend;
import com.plgrim.ncp.biz.display.data.DspAbTestSetFlterExtend;
import com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO;
import com.plgrim.ncp.biz.display.data.DspConttAbTestDTO;
import com.plgrim.ncp.biz.display.data.DspRevBoDTO;
import com.plgrim.ncp.biz.display.result.DspAbTestRevResult;
import com.plgrim.ncp.biz.display.result.DspRevCpstResult;
import com.plgrim.ncp.biz.display.service.DisplayRevCommandService;
import com.plgrim.ncp.biz.display.service.DisplayRevSelectService;
import com.plgrim.ncp.biz.display.service.DisplayTemplateService;
import com.plgrim.ncp.commons.data.SysConfigDTO;
import com.plgrim.ncp.commons.result.SysConfigResult;
import com.plgrim.ncp.commons.service.SysConfigService;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.commons.util.CodeUtil.Code;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;

import lombok.extern.slf4j.Slf4j;

@Component
@Transactional(value = "transactionManager")
@Slf4j
public class DisplayRevCommandComponentImpl extends AbstractComponent implements DisplayRevCommandComponent {
	
	@Autowired
	DisplayRevSelectService displayRevSelectService;
	
	@Autowired
	DisplayRevCommandService displayRevCommandService;
	
	@Autowired
	DisplayTemplateService displayTemplateService;
   
	@Autowired
	SysConfigService sysConfigService;

	@Value("${ncp_base.display.revision.base}")
	Long baseRevSn;
	
	String DC_FEATURES = "features";
	
	String DC_STR_ENABLED = "enabled";
	String DC_STR_ENABLED_TRUE = "true";
	String DC_STR_ENABLED_FALSE = "false";
	
	String DC_STR_STRATEGYID = "strategyId";
	String DC_STR_COMPOSITE = "composite";
	String DC_STR_PROBABILITY = "probability";
	String DC_STR_PURCHASETOTALAMT = "purchaseTotalAmount";
	String DC_STR_PURCHASETOTALCOUNT = "purchaseTotalCount";
	
	String DC_STR_PARAMETERS = "parameters";
	
	String DC_STR_CONDITION = "condition";
	String DC_STR_CONDITION_AND = "and";
	String DC_STR_CONDITION_OR = "or";
	String DC_STR_CONDITION_NOTAND = "not and";
	String DC_STR_CONDITION_NOTOR = "not or";
	
	String DC_PROBABILITY = "PROBABILITY";
	String DC_PURCHASETOTALAMT = "PURCHASETOTALAMT";
	String DC_PURCHASETOTALCOUNT = "PURCHASETOTALCOUNT";
	
	
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevCommandComponent#deleteConttAbTest(com.plgrim.ncp.biz.display.data.DspConttAbTestDTO)
	 */
	@Override
	public void deleteConttAbTest(DspConttAbTestDTO dspConttAbTestDTO)  {
		try {
			String loginId = BOSecurityUtil.getLoginId();
			
			//진행가능 여부 가 null 이고 노출방식 코드가 null이면
			DspAbTest dspAbTest = dspConttAbTestDTO.getDspAbTest();
			if(dspAbTest != null) {
				if(StringService.isEmpty(dspAbTest.getSetExpsrMenmthdCd()) && StringService.isEmpty(dspAbTest.getProgrsPsbYn())) {
					displayRevCommandService.deleteConttAbTest(dspConttAbTestDTO);
				}
				else {
					//진행가능 여부를 'N'으로 저장
					dspAbTest.setProgrsPsbYn("N");
					dspAbTest.setUdterId(loginId);
					displayRevCommandService.updateDspAbTestInfo(dspAbTest);
					
					//피처 목록 사용여수를 n으로 update
					this.updateAbTestUseYnSysConfig(dspConttAbTestDTO);
				}
			}
			log.info(CommonResponseCode.DP_00016_ABTEST_기본정보삭제_성공.toMessage());
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40010_ABTEST_기본정보삭제_실패.toMessage() +" dspConttAbTestDTO: {}" , dspConttAbTestDTO);
			throw new RuntimeException(e);
		}	
	}
	
	private void updateAbTestUseYnSysConfig(DspConttAbTestDTO dspConttAbTestDTO)  {
		try {
			String loginId = BOSecurityUtil.getLoginId();
			
			DspAbTest dspAbTest = dspConttAbTestDTO.getDspAbTest();
			String key[] = this.getPageKeyData(dspConttAbTestDTO);
			String abTestKey = "AB_" + dspAbTest.getAbTestSn() + "_" + key[0] + "_" + key[1] ;
			String abTestConfigNm = DC_FEATURES + "." + abTestKey;
			
			if(dspConttAbTestDTO.getScRevSn() != null && dspConttAbTestDTO.getScRevSn() != 0) {
				abTestConfigNm += "_" + dspConttAbTestDTO.getScRevSn();
			}
			
			SysDynmcConfig param = new SysDynmcConfig();
			//param.setConfigNm(abTestConfigNm);
			param.setConfigNm(dspAbTest.getAbTestSn() + "_" + key[0] + "_" + key[1]);
			param.setUseYn("Y");
			List<SysDynmcConfig> list = displayRevSelectService.selectAbTestSysConfigList(param);
			
			for(SysDynmcConfig resSysDynmcConfig: list) {
				SysConfigDTO paramData = new SysConfigDTO();
				
				//이전 설정 데이터
				SysDynmcConfig agoSysDynmcConfig = new SysDynmcConfig();
				agoSysDynmcConfig.setMallId(resSysDynmcConfig.getMallId());
				agoSysDynmcConfig.setConfigNm(resSysDynmcConfig.getConfigNm());
				agoSysDynmcConfig.setConfigBegDt(resSysDynmcConfig.getConfigBegDt());
				paramData.setAgoSysDynmcConfig(agoSysDynmcConfig);
				
				SysDynmcConfig sysDynmcConfig = new SysDynmcConfig();
				sysDynmcConfig.setUseYn("N");
				sysDynmcConfig.setUdterId(loginId);
				if(StringService.isNotEmpty(dspConttAbTestDTO.getInEndDt())) {
					SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					sysDynmcConfig.setConfigEndDt(simplDateFormat.parse(dspConttAbTestDTO.getInEndDt()));
				}
				paramData.setSysDynmcConfig(sysDynmcConfig);
				
				sysConfigService.updateSysDynmcConfig(paramData);				
				
				SysDynmcConfigDetail detailConfig = new SysDynmcConfigDetail();
				detailConfig.setUdterId(loginId);
				detailConfig.setUseYn("N");
				detailConfig.setMallId(resSysDynmcConfig.getMallId());
				detailConfig.setConfigNm(resSysDynmcConfig.getConfigNm());
				detailConfig.setConfigBegDt(resSysDynmcConfig.getConfigBegDt());
				
				sysConfigService.updateSysDynmcConfigDetail(detailConfig);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevCommandComponent#modifyAbTest(com.plgrim.ncp.biz.display.data.DspConttAbTestDTO)
	 */
	@Override
	public int modifyAbTest(DspConttAbTestDTO dspConttAbTestDTO)  {
		try {
			int result = displayRevCommandService.updateAbTestInfo(dspConttAbTestDTO);
			
			this.updateAbTestSysConfig(dspConttAbTestDTO);
			
			log.info(CommonResponseCode.DP_00014_ABTEST_기본정보수정_성공.toMessage()); 
			return result;
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40008_ABTEST_기본정보수정_실패.toMessage() +" dspConttAbTestDTO: {}" , dspConttAbTestDTO);
			throw new RuntimeException();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevCommandComponent#modifyAbTestDate(com.plgrim.ncp.biz.display.data.DspConttAbTestDTO)
	 */
	@Override
	public int modifyAbTestDate(DspConttAbTestDTO dspConttAbTestDTO)  {
		try {
			int result = displayRevCommandService.updateAbTestDate(dspConttAbTestDTO);
			
			this.updateAbTestSysConfig(dspConttAbTestDTO);
			log.info(CommonResponseCode.DP_00015_ABTEST_기간수정_성공.toMessage()); 
			return result;
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40009_ABTEST_기간수정_실패.toMessage() +" dspConttAbTestDTO: {}" , dspConttAbTestDTO);
			throw new RuntimeException();
		}
	}
	
	/**
	 * 특정 페이지 KEY에 대해 사용중인 피쳐목록을 조회하여 날짜를 업데이트 한다. 
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 */
	private void updateAbTestSysConfig(DspConttAbTestDTO dspConttAbTestDTO)  {
		try {
			String loginId = BOSecurityUtil.getLoginId();
			
			String inBegDt = dspConttAbTestDTO.getInBegDt();
			String inEndDt = dspConttAbTestDTO.getInEndDt();
			SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			
			DspAbTest dspAbTest = dspConttAbTestDTO.getDspAbTest();
			String key[] = this.getPageKeyData(dspConttAbTestDTO);
			String abTestKey = "AB_" + dspAbTest.getAbTestSn() + "_" + key[0] + "_" + key[1] ;
			String abTestConfigNm = DC_FEATURES + "." + abTestKey;
			
			SysDynmcConfig param = new SysDynmcConfig();
			//param.setConfigNm(abTestConfigNm);
			param.setConfigNm(dspAbTest.getAbTestSn() + "_" + key[0] + "_" + key[1]);
			param.setUseYn("Y");
			List<SysDynmcConfig> list = displayRevSelectService.selectAbTestSysConfigList(param);
			
			for(SysDynmcConfig resSysDynmcConfig: list) {
				SysConfigDTO paramData = new SysConfigDTO();
				Date agoBegDt = resSysDynmcConfig.getConfigBegDt();
				
				//이전 설정 데이터
				SysDynmcConfig agoSysDynmcConfig = new SysDynmcConfig();
				agoSysDynmcConfig.setMallId(resSysDynmcConfig.getMallId());
				agoSysDynmcConfig.setConfigNm(resSysDynmcConfig.getConfigNm());
				agoSysDynmcConfig.setConfigBegDt(agoBegDt);
				paramData.setAgoSysDynmcConfig(agoSysDynmcConfig);
				
				if(StringService.isNotEmpty(inBegDt)) {
					resSysDynmcConfig.setConfigBegDt(simplDateFormat.parse(inBegDt));
				}
				if(StringService.isNotEmpty(inEndDt)) {
					resSysDynmcConfig.setConfigEndDt(simplDateFormat.parse(inEndDt));
				}
				resSysDynmcConfig.setUdterId(loginId);
				paramData.setSysDynmcConfig(resSysDynmcConfig);
				
				//시작일자가 PK에 포함되어 있으므로 시작일자가 변경되었을 경우에는 이전시작일자 데이터를 select하여 신규로 insert 하고,
				//시작일자가 변경되지 않았을 경우에는 update한다.
				if(StringService.isEmpty(inBegDt) || agoBegDt.equals(simplDateFormat.parse(inBegDt))) {
					sysConfigService.updateSysDynmcConfig(paramData);
				}
				else {
					sysConfigService.updateAgoSysDynmcConfig(paramData);
				}
				
				SysConfigDTO paramDetailData = new SysConfigDTO();	
				SysDynmcConfig detailConfig = new SysDynmcConfig();
				detailConfig.setUdterId(loginId);
				if(StringService.isNotEmpty(inBegDt)) {
					detailConfig.setConfigBegDt(simplDateFormat.parse(inBegDt));
				}
				else {
					detailConfig.setConfigBegDt(agoBegDt);
				}
				paramDetailData.setSysDynmcConfig(detailConfig);
				paramDetailData.setAgoSysDynmcConfig(agoSysDynmcConfig);
				sysConfigService.updateAgoSysDynmcConfigDetail(paramDetailData);
				
				if(StringService.isNotEmpty(inBegDt) && !agoBegDt.equals(simplDateFormat.parse(inBegDt))) {
					sysConfigService.deleteAgoSysDynmcConfig(paramDetailData);
				}
			
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevCommandComponent#addConttAbTest(com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public long addConttAbTest(DspConttAbTestDTO dspConttAbTestDTO)  {
		try {
			log.info(CommonResponseCode.DP_00022_ABTEST_기본정보등록_시작.toMessage()); 
			//dsp_ab_test
			//dsp_ab_test_set
			//dsp_ab_test_set_mod
			//dsp_ab_test_rev
			long abTestSn = displayRevCommandService.addConttAbTest(dspConttAbTestDTO);
			
			if(abTestSn > 0) {
				log.info(CommonResponseCode.DP_00002_ABTEST_기본정보등록_성공.toMessage());
			} else {
				log.info(CommonResponseCode.DP_40001_ABTEST_기본정보등록_실패.toMessage() +" dspConttAbTestDTO: {}" , dspConttAbTestDTO);
			}
			
			return abTestSn;
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40001_ABTEST_기본정보등록_실패.toMessage() +" dspConttAbTestDTO: {}" , dspConttAbTestDTO);
			throw new RuntimeException();
		}
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevCommandComponent#addConttAbTestRev(com.plgrim.ncp.biz.display.data.DspConttAbTestDTO)
	 */
	@Override
	public void addConttAbTestRev(DspConttAbTestDTO dspConttAbTestDTO)  {
		try {
			String loginId = BOSecurityUtil.getLoginId();
			
			Long baseRevSn = null;
			Long baseTmplatSn = null;
			String scDvcSectCd = dspConttAbTestDTO.getScDvcSectCd();
					
			//DSP_AB_TEST_SET, DSP_AB_TEST_SET_MOD
			List<DspAbTestRev> dspAbTestRevLst = dspConttAbTestDTO.getDspAbTestRevList();
			List<DspRev> dspRevList = dspConttAbTestDTO.getDspRevList();
			List<DspRevCpst> dspRevCpstList = dspConttAbTestDTO.getDspRevCpstList();
			List<DspAbTestExtDTO> abTestExtList = dspConttAbTestDTO.getAbTestExtList();
			DspAbTest dspAbTest = dspConttAbTestDTO.getDspAbTest();
			
			String inBegDt = dspConttAbTestDTO.getInBegDt();
			String inEndDt = dspConttAbTestDTO.getInEndDt();
			SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date begDt = simplDateFormat.parse(inBegDt);
			Date endDt = simplDateFormat.parse(inEndDt);
			
			//1) dsp_ab_test 수정 : 세트사용여부 수정
			dspAbTest.setUdterId(loginId);
			displayRevCommandService.updateDspAbTestInfo(dspAbTest);
			
			//2.1) 등록
			// 2.1.1) set 신규생성 mod 신규생성 :  dsp_ab_test_set, dsp_ab_test_set_mod => 랜덤일경우 set사용여부가 항상 = 'N', 고객세그먼트일 경우 set사용여부가 항상 'Y' 
			// 2.1.2) rev 신규생성 : dsp_rev
			// 2.1.3) dsp_rev_cpst 등록 및 컨텐츠 복사
			//  2.1.3.1) dsp_rev_cpst등록 
			//  2.1.3.2) dvcSectCd에 해당하는 컨텐츠 복사 - base와 같으면 base의 컨텐츠까지 복사, 다르면 코너(dsp_cnr) 코너템플릿연결(dsp_cnr_tmplat_info_cnnc)까지 생성
			// 2.1.4) dsp_ab_test_rev 등록
			//2.2) 수정
			// 2.2.1) 세트사용 = 'Y'이고, 2번 이상일 경우 dsp_ab_test_set, dsp_ab_test_set_mod 세트 추가 -- 삭제
			// 2.2.1) dsp_ab_test_rev 수정 
			// 2.2.2) 템플릿 변경 시 
			//  2.2.1) 기존등록되어있는 템플릿 조회
			//  2.2.2) 신규 템플릿으로 dsp_rev_cpst template 수정 
			//  2.2.3) 컨텐츠 복사
			//   2.2.3.1) 이전 템플릿과 신규 템플릿이 다를 경우 템플릿 컨텐츠 복사 (dvcSectCd에 해당하는 컨텐츠 복사 - )
			//              - base와 같으면 base의 컨텐츠까지 복사, 다르면 코너(dsp_cnr) 코너템플릿연결(dsp_cnr_tmplat_info_cnnc)까지 생성
			//2.3) 삭제 : DelRowYn
			// 2.3.1) dsp_ab_test_rev 삭제
			// 2.3.2) dsp_rev_cpst 포함 해당 revsn의 컨텐츠 삭제
			// 2.3.3) dsp_rev 삭제
			// 2.3.4) dsp_ab_test_set, dsp_ab_test_set_mod 삭제 : dsp_ab_test_rev에 set, mod 없을 경우 		
			
			//BASE 조회
			DspCnrConttScBoDTO baseParamDTO = this.setConttPageKeyData(dspConttAbTestDTO);
			DspRevCpstResult baseRev = displayRevSelectService.selectBaseRev(baseParamDTO);
			if(baseRev != null) {
				baseRevSn = baseRev.getDspRev().getRevSn();
				//base tmplatSn
				if(StringService.isNotEmpty(scDvcSectCd)&&"MOBILE".equals(scDvcSectCd)) {
					baseTmplatSn = baseRev.getDspRevCpst().getMobileTmplatSn();
				}
				else {
					baseTmplatSn = baseRev.getDspRevCpst().getPcTmplatSn();
				}	
			}
					
			for(int i=0; i<dspAbTestRevLst.size(); i++) {
				DspAbTestRev dspAbTestRev = dspAbTestRevLst.get(i);
				dspAbTestRev.setDvcSectCd(scDvcSectCd);
				dspAbTestRev.setRegtrId(loginId);
				dspAbTestRev.setUdterId(loginId);
				
				if(dspAbTestRev.getAbTestSn() != null && dspAbTestRev.getAbTestSn() != 0) {
					DspRev dspRev = dspRevList.get(i);
					DspAbTestExtDTO dspAbTestExtDTO = abTestExtList.get(i);
					DspRevCpst dspRevCpst = dspRevCpstList.get(i);
					dspRevCpst.setRegtrId(loginId);
					dspRevCpst.setUdterId(loginId);				
					
					if (!"BASE".equals(dspRev.getRevSectCd())) {
						if ("AB_TEST".equals(dspRev.getRevSectCd()) && (dspRev.getRevSn() != null && dspRev.getRevSn() != 0)
								&& (StringService.isNotEmpty(dspAbTestExtDTO.getDelRowYn()) && !"Y".equals(dspAbTestExtDTO.getDelRowYn())) ) {
							//수정
							
							//dsp_ab_test_rev
							displayRevCommandService.updateAbTestRevInfo(dspAbTestRev);
							
							//템플릿을 변경했을 경우 코너/컨텐츠 복사					
							//기존등록되어있는 템플릿 조회 
							DspRevCpst agoCpst = displayRevSelectService.selectDspRevCpst(dspRevCpst);
							
							//수정할 템플릿
							Long newTmplatSn = null;
							//기존등록되어있는 템플릿
							Long oldTmplatSn = null;
							if(StringService.isNotEmpty(scDvcSectCd)&&"MOBILE".equals(scDvcSectCd)) {
								newTmplatSn = dspRevCpst.getMobileTmplatSn();
								oldTmplatSn = agoCpst.getMobileTmplatSn();
							}
							else {
								newTmplatSn = dspRevCpst.getPcTmplatSn();
								oldTmplatSn = agoCpst.getPcTmplatSn();
							}
												
							//신규 템플릿으로 dsp_rev_cpst template 수정 
							displayRevCommandService.updateDspRevCpstOne(dspRevCpst);
							
							DspCnrConttScBoDTO dspCnrConttScBoDTO = this.setConttPageKeyData(dspConttAbTestDTO);
							dspCnrConttScBoDTO.setScAdminId(loginId);
							dspCnrConttScBoDTO.setRevCpstCopyYn("Y");
							if(!oldTmplatSn.equals(newTmplatSn)) {
								//old tmplat 삭제
								dspCnrConttScBoDTO.setRevOnlyYn("Y");
								dspCnrConttScBoDTO.setRevCpstCopyYn("Y");
								displayRevCommandService.deleteRevContt(dspCnrConttScBoDTO, dspRevCpst.getRevSn());
								//tmplat 복사
								if(baseTmplatSn != null && !baseTmplatSn.equals(new Long(0))) {
									if(baseTmplatSn.equals(newTmplatSn)) {
										//컨텐츠 복사 - 기본 리비전 템플릿과 동일할 경우 : 기본리비전의 컨텐츠 모두 복사
										dspCnrConttScBoDTO.setScTmplatSn(newTmplatSn.toString());
										displayRevCommandService.copyRevCnrConttInfo(dspCnrConttScBoDTO, baseRevSn, dspRevCpst.getRevSn());
									}
									else {
										//컨텐츠 복사 - 기본 리비전 템플릿과 다를 경우 : 코너(dsp_cnr) 코너템플릿연결(dsp_cnr_tmplat_info_cnnc)까지 생성	
										displayRevCommandService.insertDspCnrTmplatInfoCnnc(dspRevCpst.getRevSn(), newTmplatSn, dspCnrConttScBoDTO);
									}
								}
							}	
							log.info(CommonResponseCode.DP_00003_ABTEST_대조군실험군_REV_수정.toMessage() +" abTestSn: {}" , dspAbTest.getAbTestSn());
						}
						else if ("AB_TEST".equals(dspRev.getRevSectCd()) && (dspRev.getRevSn() != null && dspRev.getRevSn() != 0)
								&& (StringService.isEmpty(dspAbTestExtDTO.getDelRowYn()) || "Y".equals(dspAbTestExtDTO.getDelRowYn())) ) {
							//삭제
							Integer setTurn = dspAbTestRev.getSetTurn();
							Integer modTurn = dspAbTestRev.getModTurn();
							
							//dsp_ab_test_rev
							displayRevCommandService.deleteDspAbTestRevInfo(dspAbTestRev);
							
							//컨텐츠 전체 - dsp_rev_cpst 포함
							DspCnrConttScBoDTO dspCnrConttScBoDTO = new DspCnrConttScBoDTO();
							if(StringService.isNotEmpty(dspConttAbTestDTO.getScDspBrndId())) {
								dspCnrConttScBoDTO.setScDspCtgryNo(dspConttAbTestDTO.getScDspCtgryNo());
								dspCnrConttScBoDTO.setScDspBrndId(dspConttAbTestDTO.getScDspBrndId());
							}else {
								dspCnrConttScBoDTO.setScDspCtgryNo(dspConttAbTestDTO.getScDspCtgryNo());
							}
							dspCnrConttScBoDTO.setScEvtNo(dspConttAbTestDTO.getScEvtNo());
							dspCnrConttScBoDTO.setScPromtSn(dspConttAbTestDTO.getScPromtSn());
							dspCnrConttScBoDTO.setScStrndSn(dspConttAbTestDTO.getScStrndSn());
							dspCnrConttScBoDTO.setScAdminId(loginId);
							dspCnrConttScBoDTO.setRevOnlyYn("Y");
							displayRevCommandService.deleteRevContt(dspCnrConttScBoDTO, dspRev.getRevSn());
							
							//dsp_rev
							DspRevBoDTO dspRevDTO = new DspRevBoDTO();
							DspRev prDspRev = new DspRev();
							prDspRev.setRevSn(dspRev.getRevSn());
							dspRevDTO.setDspRev(prDspRev);
							displayRevCommandService.deleteRevInfo(dspRevDTO);
							
							//dsp_rev_cpst_set, dsp_rev_cpst_mod
							if(modTurn > 1) {
								DspAbTestRev param = new DspAbTestRev();
								param.setAbTestSn(dspAbTestRev.getAbTestSn());
								param.setSetTurn(setTurn);
								param.setModTurn(modTurn);
								int modCnt = displayRevSelectService.selectDspAbTestRevCount(param);
								if(modCnt == 0) {
									DspAbTestSetMod dspAbTestSetMod = new DspAbTestSetMod();
									dspAbTestSetMod.setAbTestSn(dspAbTestRev.getAbTestSn());
									dspAbTestSetMod.setSetTurn(setTurn);
									dspAbTestSetMod.setModTurn(modTurn);
									displayRevCommandService.deleteDspAbTestSetMod(dspAbTestSetMod);
								}
							}
							if(setTurn > 1) {
								DspAbTestRev param = new DspAbTestRev();
								param.setAbTestSn(dspAbTestRev.getAbTestSn());
								param.setSetTurn(setTurn);
								int setCnt = displayRevSelectService.selectDspAbTestRevCount(param);
								if(setCnt == 0) {
									DspAbTestSetMod dspAbTestSetMod = new DspAbTestSetMod();
									dspAbTestSetMod.setAbTestSn(dspAbTestRev.getAbTestSn());
									dspAbTestSetMod.setSetTurn(setTurn);
									dspAbTestSetMod.setModTurn(modTurn);
									displayRevCommandService.deleteDspAbTestSetMod(dspAbTestSetMod);
									
									DspAbTestSet dspAbTestSet = new DspAbTestSet();
									dspAbTestSet.setAbTestSn(dspAbTestRev.getAbTestSn());
									dspAbTestSet.setSetTurn(setTurn);
									displayRevCommandService.deleteDspAbTestSet(dspAbTestSet);
								}
							}
							
							log.info(CommonResponseCode.DP_00004_ABTEST_대조군실험군_REV_삭제.toMessage() +" abTestSn: {}" , dspAbTest.getAbTestSn());
						}
						else {
							//등록
							//set_use_yn이 'Y'일 경우  set가 없으면 dsp_ab_test_rev_set, dsp_ab_test_rev_mod 추가
							if(StringService.isNotEmpty(dspAbTest.getSetUseYn()) && "Y".equals(dspAbTest.getSetUseYn())) {
								DspAbTestSet dspAbTestSet = new DspAbTestSet();
								dspAbTestSet.setAbTestSn(dspAbTestRev.getAbTestSn());
								
								//dsp_ab_test_rev_set 등록
								dspAbTestSet.setRegtrId(loginId);
								dspAbTestSet.setUdterId(loginId);
								dspAbTestSet.setExpsrRt(new BigDecimal(0));
								Integer setTurn = displayRevCommandService.insertDspAbTestSetOne(dspAbTestSet);
								dspAbTestRev.setSetTurn(setTurn);
								
								//dsp_ab_test_rev_set_mod 등록
								DspAbTestSetMod dspAbTestSetMod = new DspAbTestSetMod();
								dspAbTestSetMod.setAbTestSn(dspAbTestRev.getAbTestSn());
								dspAbTestSetMod.setSetTurn(dspAbTestRev.getSetTurn());
								dspAbTestSetMod.setModTurn(dspAbTestRev.getModTurn());
								dspAbTestSetMod.setBegDt(begDt);
								dspAbTestSetMod.setEndDt(endDt);
								dspAbTestSetMod.setModResnCont("-");
								dspAbTestSetMod.setRegtrId(loginId);
								dspAbTestSetMod.setUdterId(loginId);
								displayRevCommandService.insertDspAbTestSetModInfo(dspAbTestSetMod);
								
							}
		
							//리비전 생성
							DspRevBoDTO dspRevDTO = new DspRevBoDTO();
							dspRev.setRegtrId(loginId);
							dspRev.setUdterId(loginId);
							dspRevDTO.setDspRev(dspRev);
							long abTestRevSn = displayRevCommandService.insertRevInfo(dspRevDTO);
							
							
							DspCnrConttScBoDTO dspCnrConttScBoDTO = this.setConttPageKeyData(dspConttAbTestDTO);
							dspCnrConttScBoDTO.setScAdminId(loginId);
							dspCnrConttScBoDTO.setRevCpstCopyYn("Y");
							
							Long newTmplatSn = null;
							if(StringService.isNotEmpty(scDvcSectCd)&&"MOBILE".equals(scDvcSectCd)) {
								newTmplatSn = dspRevCpst.getMobileTmplatSn();
							}
							else if(StringService.isNotEmpty(scDvcSectCd)&&"PC".equals(scDvcSectCd)) {
								newTmplatSn = dspRevCpst.getPcTmplatSn();
							}
							else {
								newTmplatSn = dspRevCpst.getPcTmplatSn();
							}
							
							dspRevCpst.setRevSn(abTestRevSn);
							dspRevCpst.setRegtrId(loginId);
							dspRevCpst.setUdterId(loginId);
							
							//전시개정구성 등록
							if(StringService.isNotEmpty(scDvcSectCd)&&"MOBILE".equals(scDvcSectCd)) {
								dspRevCpst.setPcTmplatSn(null);
							}
							else if(StringService.isNotEmpty(scDvcSectCd)&&"PC".equals(scDvcSectCd)) {
								dspRevCpst.setMobileTmplatSn(null);
							}
							displayRevCommandService.insertDspRevCpstInfo(dspRevCpst);
											
													
							if(baseTmplatSn.equals(newTmplatSn)) {
								//컨텐츠 복사 - 기본 리비전 템플릿과 동일할 경우 : 기본리비전의 컨텐츠 모두 복사
								dspCnrConttScBoDTO.setScTmplatSn(newTmplatSn.toString());
								displayRevCommandService.copyRevCnrConttInfo(dspCnrConttScBoDTO, baseRevSn, abTestRevSn);
							}
							else {
								//컨텐츠 복사 - 기본 리비전 템플릿과 다를 경우 : 코너(dsp_cnr),코너유형, 코너템플릿연결(dsp_cnr_tmplat_info_cnnc)까지 생성	
								displayRevCommandService.insertDspCnrTmplatInfoCnnc(abTestRevSn, newTmplatSn, dspCnrConttScBoDTO);
							}	
							
							//대조군 실험군 생성 dsp_ab_test_rev
							dspAbTestRev.setRevSn(abTestRevSn);
							displayRevCommandService.insertAbTestRevInfo(dspAbTestRev);
							
							log.info(CommonResponseCode.DP_00005_ABTEST_대조군실험군_REV_등록.toMessage() +" abTestSn: {}" , dspAbTest.getAbTestSn());
						}
					}
				}
			}
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40002_ABTEST_대조군실험군_REV_저장_실패.toMessage() +" dspConttAbTestDTO: {}" , dspConttAbTestDTO);
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevCommandComponent#saveConttAbTestExpsr(com.plgrim.ncp.biz.display.data.DspConttAbTestDTO)
	 */
	@Override
	public void saveConttAbTestExpsr(DspConttAbTestDTO dspConttAbTestDTO)  {
		try {
			String loginId = BOSecurityUtil.getLoginId();
			SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyy-MM-ddHHmmss");
			
			dspConttAbTestDTO.setLoginId(loginId);
			
			DspAbTest dspAbTest = dspConttAbTestDTO.getDspAbTest();
			List<DspAbTestRev> dspAbTestRevList = dspConttAbTestDTO.getDspAbTestRevList();
			List<DspAbTestSetFlterExtend> dspAbTestSetFlterList = dspConttAbTestDTO.getDspAbTestSetFlterList();
			List<DspAbTestSetExtend> dspAbTestSetList = dspConttAbTestDTO.getDspAbTestSetList();		
			
			if(dspAbTest != null ) {
				log.info(CommonResponseCode.DP_00006_ABTEST_노출설정_저장_시작.toMessage() +" abtestSn: {}, setExpsrMenmthdCd: {}" , dspAbTest.getAbTestSn(), dspAbTest.getSetExpsrMenmthdCd());
				String setExpsrMenmthdCd = dspAbTest.getSetExpsrMenmthdCd();
		
				//노출방법이 랜덤일 경우 
				if(StringService.isNotEmpty(setExpsrMenmthdCd) && "RND".equals(setExpsrMenmthdCd)) {
					//변경했을 경우 dsp_ab_test_set_flter 삭제, dsp_ab_test_set 노출율 null로 update
					
					if(dspAbTestRevList != null && dspAbTestRevList.size() > 0) {
						//dsp_ab_test_rev 노출율 update
						for(DspAbTestRev dspAbTestRev: dspAbTestRevList) {
							dspAbTestRev.setUdterId(loginId);
							displayRevCommandService.updateDspAbTestRevInfo(dspAbTestRev);
						}
					}
					
				}
				//노출방법이 고객세그먼트일 경우
				else if ("CSTMR_SGMT".equals(setExpsrMenmthdCd)) {
					
					//dsp_ab_test_set_flter 삭제 후 추가
					DspAbTestSetFlter paramFlter = new DspAbTestSetFlter();
					paramFlter.setAbTestSn(dspAbTest.getAbTestSn());
					displayRevCommandService.deleteDspAbTestSetFlterInfo(paramFlter);
					
					if(dspAbTestSetFlterList != null && dspAbTestSetFlterList.size() > 0) {
						for(DspAbTestSetFlterExtend dspAbTestSetFlterExtend: dspAbTestSetFlterList) {
							dspAbTestSetFlterExtend.setRegtrId(loginId);
							dspAbTestSetFlterExtend.setUdterId(loginId);
							
							String inOrdBegDt = dspAbTestSetFlterExtend.getInOrdBegDt();
							if(StringService.isNotEmpty(inOrdBegDt)) dspAbTestSetFlterExtend.setOrdBegDt(simplDateFormat.parse(inOrdBegDt+"000000"));
							String inOrdEndDt = dspAbTestSetFlterExtend.getInOrdEndDt();
							if(StringService.isNotEmpty(inOrdBegDt)) dspAbTestSetFlterExtend.setOrdEndDt(simplDateFormat.parse(inOrdEndDt+"235959"));
							
							displayRevCommandService.updateDspAbTestSetFlterInfo(dspAbTestSetFlterExtend);
						}
					}
					
					//세트 노출율 수정
					if(dspAbTestSetList != null && dspAbTestSetList.size() > 0) {
						for(DspAbTestSetExtend dspAbTestSet: dspAbTestSetList) {
							if(dspAbTestSet.getExpsrRt() != null) {
								dspAbTestSet.setRegtrId(loginId);
								dspAbTestSet.setUdterId(loginId);
								
								displayRevCommandService.updateDspRevSet(dspAbTestSet);
							}
						}
					}
				}
				
				////dsp_ab_test : 노출방법 update , 진행가능여부 'Y' 
				dspAbTest.setUdterId(loginId);
				dspAbTest.setProgrsPsbYn("Y");
				displayRevCommandService.updateDspAbTestInfo(dspAbTest);
				
				//dynamic config feature 확성화
				addSysConfigForAbTest(dspConttAbTestDTO);
				
				log.info(CommonResponseCode.DP_00007_ABTEST_노출설정_저장_성공.toMessage() +" abtestSn: {}, setExpsrMenmthdCd: {}" , dspAbTest.getAbTestSn(), dspAbTest.getSetExpsrMenmthdCd());
				
			}
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40003_ABTEST_노출설정_저장_실패.toMessage() +" dspConttAbTestDTO: {}" , dspConttAbTestDTO);
			throw new RuntimeException(e);
		}	
	}
	
	
	/**
	 * dynamic config feature 확성화 
	 * 	
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @throws Exception the exception
	 */
	public void addSysConfigForAbTest(DspConttAbTestDTO dspConttAbTestDTO)  {		
		try{ 
			DspAbTest dspAbTest = dspConttAbTestDTO.getDspAbTest();
			String key[] = this.getPageKeyData(dspConttAbTestDTO);
			
			String setExpsrMenmthdCd = dspAbTest.getSetExpsrMenmthdCd();		
			String curSetExpsrMenmthdCd = dspConttAbTestDTO.getCurSetExpsrMenmthdCd(); //변경전 노출방법			
			
			//노출방법이 랜덤일 경우
			if(StringService.isNotEmpty(setExpsrMenmthdCd) && "RND".equals(setExpsrMenmthdCd)) {
				List<DspAbTestRev> dspAbTestRevLst = dspConttAbTestDTO.getDspAbTestRevList();
				int revListSize = dspAbTestRevLst.size();
				int fromVal = 0;
				int toVal = 0;
				for(int i=0; i<revListSize; i++) {
					DspAbTestRev dspAbTestRev = dspAbTestRevLst.get(i);
					
					//key "AB_" + abTestSn + key1 + key2 + revSn
					String abTestKey = "AB_" + dspAbTestRev.getAbTestSn() + "_" + key[0] + "_" + key[1] + "_" + dspAbTestRev.getRevSn();
					String abTestConfigNm = DC_FEATURES + "." + abTestKey;
					
					//노출방법 변경일 경우 설정상세 삭제후 저장 
					if(StringService.isNotEmpty(curSetExpsrMenmthdCd) && !curSetExpsrMenmthdCd.equals(setExpsrMenmthdCd)) {
						SysDynmcConfigDetail delParam = new SysDynmcConfigDetail();
						delParam.setMallId(dspConttAbTestDTO.getMallId());
						delParam.setConfigNm(abTestConfigNm);
						sysConfigService.deleteSysDynmcConfigDetail(delParam);
					}
						
					BigDecimal exp = dspAbTestRev.getExpsrRt();
					toVal = toVal + exp.intValue();
					
					//ab_test features 존재하는 지 확인
					this.saveAbTestFeatureConfig(abTestConfigNm, dspConttAbTestDTO, null);					
					
					//설정 상세 enabled
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_ENABLED, DC_STR_ENABLED_TRUE, dspConttAbTestDTO, null);
					
					//설정 상세 strategyId
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_STRATEGYID, DC_STR_PROBABILITY, dspConttAbTestDTO, null);
					
					//설정 상세 parameters.probabilityFrom
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_PARAMETERS + "." + DC_STR_PROBABILITY + "From", String.valueOf(fromVal), dspConttAbTestDTO, null);
					
					//설정 상세 parameters.probabilityTo
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_PARAMETERS + "." + DC_STR_PROBABILITY + "To", String.valueOf(toVal), dspConttAbTestDTO, null);
					
					fromVal = toVal + 1;
				}
			}
			
			//노출방법이 고객세그먼트일 경우 
			/* 예) 고객세그먼트 '여성', '10대' 로 설정 1개 리비전만 저장
			     features.AB_8_DXMA32__28.parameters.features GENDER_FEMALE,AGE_FROM_10_TO_19
			*/
			else if ("CSTMR_SGMT".equals(setExpsrMenmthdCd)) {
				List<DspAbTestSetExtend> dspAbTestSetLst = dspConttAbTestDTO.getDspAbTestSetList();
				List<DspAbTestSetFlterExtend> dspAbTestSetFlterList = dspConttAbTestDTO.getDspAbTestSetFlterList();
				
				String baseRevFeature = "";
				DspAbTestRev baseDspAbTestRev = null; 
				
				for(int i=0; i<dspAbTestSetLst.size(); i++) {
					DspAbTestSetExtend dspAbTestSet = dspAbTestSetLst.get(i);
					List<DspAbTestRev> dspAbTestRevLst = dspAbTestSet.getDspAbTestRevList();
				
					int revListSize = dspAbTestRevLst.size();
					
					//고객세그먼트설정일 경우 config목록 생성
					String configList = this.getCstmrSgmtConfigList(dspAbTestSetFlterList, dspAbTestSet.getSetTurn(), dspConttAbTestDTO);
					
//					int fromConfigValue = 0;
//					int toConfigValue = 0;
//					int perValue = Math.round(100/revListSize);
					
					for(int j=0; j<revListSize; j++) {
						
						DspAbTestRev dspAbTestRev = dspAbTestRevLst.get(j);
						
						if(StringService.isNotEmpty(dspAbTestRev.getAbTestGrpCd()) && "EXCLS".equals(dspAbTestRev.getAbTestGrpCd())) {
							baseDspAbTestRev = dspAbTestRev;
						}
						else {
							
							//key "AB_" + abTestSn + key1 + key2 + revSn
							String abTestKey = "AB_" + dspAbTestRev.getAbTestSn() + "_" + key[0] + "_" + key[1] + "_" + dspAbTestRev.getRevSn();
							String abTestConfigNm = DC_FEATURES + "." + abTestKey;	
							
							if(StringService.isEmpty(baseRevFeature)) {
								baseRevFeature = abTestKey;
							}
							else {
								baseRevFeature += "," + abTestKey;
							}
								
							//ab_test features 존재하는 지 확인
							this.saveAbTestFeatureConfig(abTestConfigNm, dspConttAbTestDTO, null);				
							
							//노출방법 변경일 경우 설정상세 삭제후 저장 
							if(StringService.isNotEmpty(curSetExpsrMenmthdCd) && !curSetExpsrMenmthdCd.equals(setExpsrMenmthdCd)) {
								SysDynmcConfigDetail delParam = new SysDynmcConfigDetail();
								delParam.setMallId(dspConttAbTestDTO.getMallId());
								delParam.setConfigNm(abTestConfigNm);
								sysConfigService.deleteSysDynmcConfigDetail(delParam);
							}
							
							//설정 상세 enabled
							this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_ENABLED, DC_STR_ENABLED_TRUE, dspConttAbTestDTO, null);
							
							//설정 상세 strategyId
							this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_STRATEGYID, DC_STR_COMPOSITE, dspConttAbTestDTO, null);
							
							//설정 상세 condition
							this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_PARAMETERS + "." + DC_STR_CONDITION, DC_STR_CONDITION_AND, dspConttAbTestDTO, null);
							
							/*
							toConfigValue = toConfigValue + perValue;
							if((j+1) == revListSize) {
								if(toConfigValue < 100) {
									toConfigValue = 100;
								}
							}
							
							//percent value 없으면 등록
							String probabilityName = DC_PROBABILITY+ "_" + fromConfigValue + "_" + toConfigValue;
							this.setConfigFeature(fromConfigValue, toConfigValue, probabilityName, DC_STR_PROBABILITY, dspConttAbTestDTO);
							
							this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_PARAMETERS + "." + DC_FEATURES, configList + probabilityName, dspConttAbTestDTO, null);
							
							fromConfigValue = toConfigValue + 1;
							*/
							this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_PARAMETERS + "." + DC_FEATURES, configList, dspConttAbTestDTO, null);
						}	
					}
				}
				
				if(baseDspAbTestRev != null) {
					//key "AB_" + abTestSn + key1 + key2 + revSn
					String abTestKey = "AB_" + baseDspAbTestRev.getAbTestSn() + "_" + key[0] + "_" + key[1] + "_" + baseDspAbTestRev.getRevSn();
					String abTestConfigNm = DC_FEATURES + "." + abTestKey;					
						
					//ab_test features 존재하는 지 확인
					this.saveAbTestFeatureConfig(abTestConfigNm, dspConttAbTestDTO, null);				
										
					//설정 상세 enabled
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_ENABLED, DC_STR_ENABLED_TRUE, dspConttAbTestDTO, null);
					
					//설정 상세 strategyId
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_STRATEGYID, DC_STR_COMPOSITE, dspConttAbTestDTO, null);
					
					//설정 상세 condition
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_PARAMETERS + "." + DC_STR_CONDITION, DC_STR_CONDITION_NOTOR, dspConttAbTestDTO, null);
					
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_PARAMETERS + "." + DC_FEATURES, baseRevFeature, dspConttAbTestDTO, null);
				}
				
			}
			log.info(CommonResponseCode.DP_00008_ABTEST_feature_저장_성공.toMessage() +" abtestSn: {}, setExpsrMenmthdCd: {}" , dspAbTest.getAbTestSn(), dspAbTest.getSetExpsrMenmthdCd());
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40004_ABTEST_feature_저장_실패.toMessage() +" dspConttAbTestDTO: {}" ,dspConttAbTestDTO);
			throw new RuntimeException(e);
		}	
	}
	
	
	/**
	 * Sets the config probability.
	 * 
	 * features.PURCHASETOTALAMOUNT_250000.enabled	true
				features.PURCHASETOTALAMOUNT_250000.parameters.purchaseTotalAmount	250000
				features.PURCHASETOTALAMOUNT_250000.strategyId	purchaseTotalAmount
				
				features.PROBABILITY_15.enabled	true
				features.PROBABILITY_15.parameters.probability	15
				features.PROBABILITY_15.strategyId	probability
	 *
	 * @param exp the exp
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @throws Exception the exception
	 */
	private void setConfigFeature (Integer fromConfigValue, Integer toConfigValue, String configNm, String Configkey, DspConttAbTestDTO dspConttAbTestDTO)  {
		try {
			String abTestConfigNm = DC_FEATURES + "." + configNm;
			
			SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date begDt = new Date();
			Date endDt = simplDateFormat.parse("29991231115959");
	
			String loginId = dspConttAbTestDTO.getLoginId();
			String mallId = dspConttAbTestDTO.getMallId();
			
			SysConfigDTO paramData = new SysConfigDTO();
			SysDynmcConfig sysDynmcConfig = new SysDynmcConfig();
			sysDynmcConfig.setConfigNm(abTestConfigNm);
			sysDynmcConfig.setMallId(mallId);
			paramData.setSysDynmcConfig(sysDynmcConfig);
			SysConfigResult configResultAb = sysConfigService.selectSysConfig(paramData);
			
			//없으면 등록
			if(configResultAb == null) {
				sysDynmcConfig.setConfigBegDt(begDt);
				sysDynmcConfig.setConfigEndDt(endDt);
				sysDynmcConfig.setConfigSectCd("ETC");
				sysDynmcConfig.setUseYn("Y");
				sysDynmcConfig.setConfigDscr("AB테스트_" + abTestConfigNm);
				sysDynmcConfig.setRegtrId(loginId);
				sysDynmcConfig.setUdterId(loginId);
				//설정 등록 처리
				sysConfigService.insertSysDynmcConfig(sysDynmcConfig);
				
				SysDynmcConfigDetail sysDynmcConfigDetail = new SysDynmcConfigDetail();
				sysDynmcConfigDetail.setMallId(mallId);
				sysDynmcConfigDetail.setConfigNm(abTestConfigNm);
				sysDynmcConfigDetail.setConfigBegDt(begDt);
				sysDynmcConfigDetail.setRegtrId(loginId);
				sysDynmcConfigDetail.setUdterId(loginId);
				sysDynmcConfigDetail.setUseYn("Y");
				
				//설정 상세 enabled
				sysDynmcConfigDetail.setConfigKey(abTestConfigNm + "." + DC_STR_ENABLED);
				sysDynmcConfigDetail.setConfigVal(DC_STR_ENABLED_TRUE);
				sysConfigService.insertSysDynmcConfigDetail(sysDynmcConfigDetail);
				
				//설정 상세 strategyId
				sysDynmcConfigDetail.setConfigKey(abTestConfigNm + "." + DC_STR_STRATEGYID);
				//sysDynmcConfigDetail.setConfigVal(DC_VALUE_STRATEGYID_PROBABILITY);
				sysDynmcConfigDetail.setConfigVal(Configkey);
				sysConfigService.insertSysDynmcConfigDetail(sysDynmcConfigDetail);
				
				//설정 상세
				sysDynmcConfigDetail.setConfigKey(abTestConfigNm + "." + DC_STR_PARAMETERS + "." + Configkey+"From");
				//sysDynmcConfigDetail.setConfigVal(exp.toString());
				sysDynmcConfigDetail.setConfigVal(fromConfigValue.toString());
				sysConfigService.insertSysDynmcConfigDetail(sysDynmcConfigDetail);
				
				sysDynmcConfigDetail.setConfigKey(abTestConfigNm + "." + DC_STR_PARAMETERS + "." + Configkey+"To");
				//sysDynmcConfigDetail.setConfigVal(exp.toString());
				sysDynmcConfigDetail.setConfigVal(toConfigValue.toString());
				sysConfigService.insertSysDynmcConfigDetail(sysDynmcConfigDetail);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//this.setConfigFeatureForPch(amtFrom, amtTo, DC_PURCHASETOTALAMT + "_" + flterCd, DC_STR_PURCHASETOTALAMT, dspConttAbTestDTO);
	private void setConfigFeatureForPch (Integer valueFrom, Integer valueTo, String qyFrom, String qyTo, String configNm, String Configkey, DspConttAbTestDTO dspConttAbTestDTO)  {
		try {
			String abTestConfigNm = DC_FEATURES + "." + configNm;
			
			SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			//Date begDt = new Date();
			//Date endDt = simplDateFormat.parse("29991231115959");
			Date begDt = simplDateFormat.parse(qyFrom.replaceAll("-", "") + "000000");
			Date endDt = simplDateFormat.parse(qyTo.replaceAll("-", "") + "235959");
			
			String loginId = dspConttAbTestDTO.getLoginId();
			String mallId = dspConttAbTestDTO.getMallId();
			
			SysConfigDTO paramData = new SysConfigDTO();
			SysDynmcConfig sysDynmcConfig = new SysDynmcConfig();
			sysDynmcConfig.setConfigNm(abTestConfigNm);
			sysDynmcConfig.setMallId(mallId);
			paramData.setSysDynmcConfig(sysDynmcConfig);
			SysConfigResult configResultAb = sysConfigService.selectSysConfig(paramData);
			
			//없으면 등록
			Date detailBegDt = begDt;
			if(configResultAb == null) {
				sysDynmcConfig.setConfigBegDt(begDt);
				sysDynmcConfig.setConfigEndDt(endDt);
				sysDynmcConfig.setConfigSectCd("ETC");
				sysDynmcConfig.setUseYn("Y");
				sysDynmcConfig.setConfigDscr("AB테스트_" + abTestConfigNm);
				sysDynmcConfig.setRegtrId(loginId);
				sysDynmcConfig.setUdterId(loginId);
				//설정 등록 처리
				sysConfigService.insertSysDynmcConfig(sysDynmcConfig);
			} else {
				detailBegDt = configResultAb.getSysDynmcConfigDetail().getConfigBegDt();
			}
				
			SysDynmcConfigDetail sysDynmcConfigDetail = new SysDynmcConfigDetail();
			sysDynmcConfigDetail.setMallId(mallId);
			sysDynmcConfigDetail.setConfigNm(abTestConfigNm);
			sysDynmcConfigDetail.setConfigBegDt(detailBegDt);
			sysDynmcConfigDetail.setRegtrId(loginId);
			sysDynmcConfigDetail.setUdterId(loginId);
			sysDynmcConfigDetail.setUseYn("Y");
			
			//설정 상세 enabled
			sysDynmcConfigDetail.setConfigKey(abTestConfigNm + "." + DC_STR_ENABLED);
			sysDynmcConfigDetail.setConfigVal(DC_STR_ENABLED_TRUE);
			sysConfigService.insertSysDynmcConfigDetail(sysDynmcConfigDetail);
			
			//설정 상세 Strategy ID
			sysDynmcConfigDetail.setConfigKey(abTestConfigNm + "." + DC_STR_STRATEGYID);
			//sysDynmcConfigDetail.setConfigVal(DC_VALUE_STRATEGYID_PROBABILITY);
			sysDynmcConfigDetail.setConfigVal(Configkey);
			sysConfigService.insertSysDynmcConfigDetail(sysDynmcConfigDetail);
			
			//설정 상세 From
			//if(valueFrom > 0) {
				sysDynmcConfigDetail.setConfigKey(abTestConfigNm + "." + DC_STR_PARAMETERS + "." + Configkey + "From");
				sysDynmcConfigDetail.setConfigVal(valueFrom.toString());
				sysConfigService.insertSysDynmcConfigDetail(sysDynmcConfigDetail);
			//}
			
			//설정 상세 To
			//if(valueTo > 0) {
				sysDynmcConfigDetail.setConfigKey(abTestConfigNm + "." + DC_STR_PARAMETERS + "." + Configkey + "To");
				sysDynmcConfigDetail.setConfigVal(valueTo.toString());
				sysConfigService.insertSysDynmcConfigDetail(sysDynmcConfigDetail);
			//}
			
			//설정 상세 QueryFrom
			if(StringService.isNotEmpty(qyFrom)) {
				sysDynmcConfigDetail.setConfigKey(abTestConfigNm + "." + DC_STR_PARAMETERS + "." + Configkey + "QueryFrom");
				sysDynmcConfigDetail.setConfigVal(qyFrom);
				sysConfigService.insertSysDynmcConfigDetail(sysDynmcConfigDetail);
			}
			
			//설정 상세 QueryTo
			if(StringService.isNotEmpty(qyTo)) {
				sysDynmcConfigDetail.setConfigKey(abTestConfigNm + "." + DC_STR_PARAMETERS + "." + Configkey + "QueryTo");
				sysDynmcConfigDetail.setConfigVal(qyTo);
				sysConfigService.insertSysDynmcConfigDetail(sysDynmcConfigDetail);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	
	/**
	 * Save ab test feature config.
	 *
	 * @param abTestConfigNm the ab test config nm
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @throws Exception the exception
	 */
	private void saveAbTestFeatureConfig(String abTestConfigNm, DspConttAbTestDTO dspConttAbTestDTO, Date prBegDt)  {
		try {
			String loginId = dspConttAbTestDTO.getLoginId();
			String mallId = dspConttAbTestDTO.getMallId();
			
			SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date begDt = null;
			Date endDt = null;	
			
			if(prBegDt != null) {
				begDt = prBegDt;
				endDt = simplDateFormat.parse("29991231115959");
			} else {
				String inBegDt = dspConttAbTestDTO.getInBegDt();
				String inEndDt = dspConttAbTestDTO.getInEndDt();
				begDt = simplDateFormat.parse(inBegDt);
				endDt = simplDateFormat.parse(inEndDt);
			}
			
			SysConfigDTO paramData = new SysConfigDTO();
			SysDynmcConfig sysDynmcConfig = new SysDynmcConfig();
			sysDynmcConfig.setConfigNm(abTestConfigNm);
			sysDynmcConfig.setMallId(mallId);
			paramData.setSysDynmcConfig(sysDynmcConfig);
			SysConfigResult configResultAb = sysConfigService.selectSysConfig(paramData);
			
			//없으면 등록
			if(configResultAb == null) {
				sysDynmcConfig.setConfigBegDt(begDt);
				sysDynmcConfig.setConfigEndDt(endDt);
				sysDynmcConfig.setConfigSectCd("ETC");
				sysDynmcConfig.setUseYn("Y");
				sysDynmcConfig.setConfigDscr("AB테스트_" + abTestConfigNm);
				sysDynmcConfig.setRegtrId(loginId);
				sysDynmcConfig.setUdterId(loginId);
				//설정 등록 처리
				sysConfigService.insertSysDynmcConfig(sysDynmcConfig);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	
	
	/**
	 * Save ab test feture 설정 상세 저장
	 *
	 * @param abTestConfigNm the ab test config nm
	 * @param configKey the config key
	 * @param configVal the config val
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @throws Exception the exception
	 */
	private void saveAbTestFetureConfigDetail(String abTestConfigNm, String configKey, String configVal, DspConttAbTestDTO dspConttAbTestDTO, Date prBegDt)  {
		try {
			String loginId = dspConttAbTestDTO.getLoginId();
			String mallId = dspConttAbTestDTO.getMallId();
			
			SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			
			Date begDt = null;		
			if(prBegDt != null) {
				begDt = prBegDt;
			} else {
				String inBegDt = dspConttAbTestDTO.getInBegDt();
				begDt = simplDateFormat.parse(inBegDt);
			}
			
			SysDynmcConfigDetail sysDynmcConfigDetail = new SysDynmcConfigDetail();
			sysDynmcConfigDetail.setMallId(mallId);
			sysDynmcConfigDetail.setConfigNm(abTestConfigNm);
			sysDynmcConfigDetail.setConfigBegDt(begDt);
			sysDynmcConfigDetail.setRegtrId(loginId);
			sysDynmcConfigDetail.setUdterId(loginId);
			sysDynmcConfigDetail.setUseYn("Y");
			
			sysDynmcConfigDetail.setConfigKey(abTestConfigNm + "." + configKey);
			sysDynmcConfigDetail.setConfigVal(configVal);
			sysConfigService.insertSysDynmcConfigDetail(sysDynmcConfigDetail);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Gets the cstmr sgmt config list.
	 * 
	 * ㅁ. 필터 그룹 : FLTER_GRP
   >. 회원 성별 구분 : MBR_SEX
      >. 남성 : MALE
      >. 여성 : FEMALE
   >. 회원 연령 : MBR_AGE
      >. 10대 : 10AGE
      >. 20대 : 20AGE
      >. 30대 : 30AGE
      >. 40대이상 : 40AGE_OVER
   >. 시도 : SIDO
      >. 서울특별시 : 11000
      >. 부산광역시 : 26000
      >. 대구광역시 : 27000
      >. 인천광역시 : 28000
      >. 광주광역시 : 29000
      >. 대전광역시 : 30000
      >. 울산광역시 : 31000
      >. 경기도 : 41000
      >. 강원도 : 42000
      >. 충청북도 : 43000
      >. 충청남도 : 44000
      >. 전라북도 : 45000
      >. 전라남도 : 46000
      >. 경상북도 : 47000
      >. 경상남도 : 48000
      >. 제주특별자치도 : 50000
   >. 구매금액 : PCH_AMT
      >. 10만원 미만 : 10_TTW_BELO
      >. 10~30만원 : 10_30_TTW
      >. 30~50만원 : 30_50_TTW
      >. 50~70만원 : 50_70_TTW
      >. 70~100만원 : 70_100_TTW
      >. 100만원 이상 : 100_TTW_OVER
   >. 구매횟수 : PCH_COUNT
      >. 5회 미만 : 5_BT_BELO
      >. 5~7회 : 5_7_BT
      >. 8~10회 : 8_10_BT
      >. 10회 이상 : 10_BT_OVER
	 *
	 * @param dspAbTestSetFlterList the dsp ab test set flter list
	 * @return the cstmr sgmt config list
	 * @throws Exception the exception
	 */
	private String getCstmrSgmtConfigList(List<DspAbTestSetFlterExtend> dspAbTestSetFlterList, Integer setTurn, DspConttAbTestDTO dspConttAbTestDTO)  {
		String resConfigList = "";
		
		if(setTurn == 1) {
			return resConfigList;
		}
		
		List<Code> flterGrpCdList = CodeUtil.getCodes("FLTER_GRP", dspConttAbTestDTO.getLang());
		for(Code code: flterGrpCdList) {
			String configList = "";
			
			List<DspAbTestSetFlterExtend> listByFlter = new ArrayList<DspAbTestSetFlterExtend>();
			for(DspAbTestSetFlterExtend dspAbTestSetFlterExt: dspAbTestSetFlterList) {
				if(dspAbTestSetFlterExt.getSetTurn().equals(setTurn)) {
					if((dspAbTestSetFlterExt.getFlterGrpCd()).equals(code.getCd())) {
						listByFlter.add(dspAbTestSetFlterExt);
					}
				}
			}
			
			if(listByFlter.size() > 0) {
				
				String key[] = this.getPageKeyData(dspConttAbTestDTO);
				List<DspAbTestSetExtend> dspAbTestSetLst = dspConttAbTestDTO.getDspAbTestSetList();
				
				String abTestKey = "";
				for(DspAbTestSetExtend set: dspAbTestSetLst) {
					if(set.getSetTurn().equals(setTurn)) {
						List<DspAbTestRev> revList = set.getDspAbTestRevList();
						//(set 하나에 rev하나)
						for(DspAbTestRev rev: revList) {
							//key  + abTestSn + key1 + key2 + revSn
							abTestKey = rev.getAbTestSn() + "_" + key[0] + "_" + key[1] + "_" + rev.getRevSn();
						}
					}
				}
				
				for(DspAbTestSetFlterExtend dspAbTestSetFlterExt: listByFlter) {
					if(dspAbTestSetFlterExt.getSetTurn().equals(setTurn)) {
						String flterGrpCd = "", flterCd = "";
						
						flterGrpCd = dspAbTestSetFlterExt.getFlterGrpCd();	
						flterCd = dspAbTestSetFlterExt.getFlterCd();
						//필터그룹별 value 셋팅 로직 추가
						if(flterGrpCd.equals("MBR_SEX")) {
							configList += "GENDER_"+flterCd + ",";
						}
						else if(flterGrpCd.equals("MBR_AGE")) {
							if(flterCd.indexOf("OVER") > -1) {
								configList += "AGE_OVER_" + flterCd.substring(0,2) + ",";
							}
							else if(flterCd.indexOf("BELO") > -1) {
								configList += "AGE_UNDER_" + flterCd.substring(0,2) + ",";
							}
							else {
								configList += "AGE_FROM_" + Integer.parseInt(flterCd.substring(0,2)) + "_TO_" + (Integer.parseInt(flterCd.substring(0,2)) + 9) + ",";
							}				 
						}
						else if(flterGrpCd.equals("SIDO")) {
							configList += "RESIDENCE_" + dspAbTestSetFlterExt.getFlterCdNm() + ",";
						}
						else if(flterGrpCd.equals("PCH_AMT")) {
							String[] arAmt = flterCd.split("_");
							int amtFrom = 0;
							int amtTo = 0;
							if(flterCd.equals("10_TTW_BELO")) {
								amtTo = Integer.parseInt(arAmt[0] + "0000"); 
							} else if (flterCd.equals("100_TTW_OVER")) {
								amtFrom = Integer.parseInt(arAmt[0] + "0000");
								amtTo = 999999999;
							} else {
								amtFrom = Integer.parseInt(arAmt[0] + "0000");
								amtTo = Integer.parseInt(arAmt[1] + "0000");
							}
							
							String qyFrom = dspAbTestSetFlterExt.getInOrdBegDt();
							String qyTo = dspAbTestSetFlterExt.getInOrdEndDt();
							
							this.setConfigFeatureForPch(amtFrom, amtTo, qyFrom, qyTo, DC_PURCHASETOTALAMT + "_" + abTestKey + "_" + flterCd , DC_STR_PURCHASETOTALAMT, dspConttAbTestDTO);
							
							configList += DC_PURCHASETOTALAMT + "_" + abTestKey + "_" + flterCd + ",";
						}
						else if(flterGrpCd.equals("PCH_COUNT")) {
							String[] arCnt = flterCd.split("_");
							int cntFrom = 0;
							int cntTo = 0;
							if(flterCd.equals("5_BT_BELO")) {
								cntTo = Integer.parseInt(arCnt[0] ); 
							} else if (flterCd.equals("10_BT_OVER")) {
								cntFrom = Integer.parseInt(arCnt[0]);
								cntTo = 999;
							} else {
								cntFrom = Integer.parseInt(arCnt[0]);
								cntTo = Integer.parseInt(arCnt[1]);
							}
							
							String qyFrom = dspAbTestSetFlterExt.getInOrdBegDt();
							String qyTo = dspAbTestSetFlterExt.getInOrdEndDt();
							
							this.setConfigFeatureForPch(cntFrom, cntTo, qyFrom, qyTo, DC_PURCHASETOTALCOUNT + "_" + abTestKey + "_" + flterCd, DC_STR_PURCHASETOTALCOUNT, dspConttAbTestDTO);
							
							configList += DC_PURCHASETOTALCOUNT + "_" + abTestKey + "_" + flterCd + ",";
						}
					}
				}
				
				if(StringService.isNotEmpty(configList) && configList.endsWith(",")) {
					configList = configList.substring(0, configList.length() - 1);
				}
				
				if(listByFlter.size() == 1) {
					if(StringService.isEmpty(resConfigList)) {
						resConfigList = configList;
					}
					else {
						resConfigList += "," + configList;
					}
					
				}
				//1개이상일 경우 각각 생성 후 or 목록 추가 생성
				else if(listByFlter.size() > 1) {
					
					String keyPrefix = "";
					if(code.getCd().equals("MBR_SEX")) {
						keyPrefix = "GENDER_";
					}
					else if(code.getCd().equals("MBR_AGE")) {
						keyPrefix = "AGE_";
					}
					else if(code.getCd().equals("SIDO")) {
						keyPrefix = "RESIDENCE_" ;
					}
					else if(code.getCd().equals("PCH_AMT")) {
						keyPrefix = "PURCHASETOTALAMT_";
					}
					else if(code.getCd().equals("PCH_COUNT")) {
						keyPrefix = "PURCHASETOTALCOUNT_";
					}
					

					abTestKey = keyPrefix + abTestKey;
					String abTestConfigNm = DC_FEATURES + "." + abTestKey;	
					
					if(StringService.isEmpty(resConfigList)) {
						resConfigList = abTestKey;
					}
					else {
						resConfigList += "," + abTestKey;
					}
					
					//ab_test features 존재하는 지 확인
					this.saveAbTestFeatureConfig(abTestConfigNm, dspConttAbTestDTO, null);				
					
					//설정 상세 enabled
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_ENABLED, DC_STR_ENABLED_TRUE, dspConttAbTestDTO, null);
					
					//설정 상세 strategyId
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_STRATEGYID, DC_STR_COMPOSITE, dspConttAbTestDTO, null);
					
					//설정 상세 condition
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_PARAMETERS + "." + DC_STR_CONDITION, DC_STR_CONDITION_OR, dspConttAbTestDTO, null);
					
					this.saveAbTestFetureConfigDetail(abTestConfigNm, DC_STR_PARAMETERS + "." + DC_FEATURES, configList, dspConttAbTestDTO, null);
					
				}
				
			}
		}
		
		return resConfigList;
	}

	
	/**
	 * 코너컨텐츠 페이지 KEY 설정
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @return the dsp cnr contt sc bo DTO
	 * @throws Exception the exception
	 */
	private DspCnrConttScBoDTO setConttPageKeyData(DspConttAbTestDTO dspConttAbTestDTO)  {
		DspCnrConttScBoDTO dspCnrConttScBoDTO = new DspCnrConttScBoDTO();
		
		if(StringService.isNotEmpty(dspConttAbTestDTO.getScDspBrndId())) {
			dspCnrConttScBoDTO.setScDspCtgryNo(dspConttAbTestDTO.getScDspCtgryNo());
			dspCnrConttScBoDTO.setScDspBrndId(dspConttAbTestDTO.getScDspBrndId());
		}
		else if(StringService.isNotEmpty(dspConttAbTestDTO.getScDspCtgryNo())) {
			dspCnrConttScBoDTO.setScDspCtgryNo( dspConttAbTestDTO.getScDspCtgryNo());
		}
		else if(StringService.isNotEmpty(dspConttAbTestDTO.getScEvtNo())) {
			dspCnrConttScBoDTO.setScEvtNo(dspConttAbTestDTO.getScEvtNo());
		}
		else if(StringService.isNotEmpty(dspConttAbTestDTO.getScPromtSn())) {
			dspCnrConttScBoDTO.setScPromtSn(dspConttAbTestDTO.getScPromtSn());
		}
		else if(StringService.isNotEmpty(dspConttAbTestDTO.getScStrndSn())) {
			dspCnrConttScBoDTO.setScStrndSn(dspConttAbTestDTO.getScStrndSn());
		}
		
		return dspCnrConttScBoDTO;
	}
	
	/**
	 * Sets the ab test page key data.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the dsp contt ab test DTO
	 */
	private DspConttAbTestDTO setAbTestPageKeyData(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		DspConttAbTestDTO dspConttAbTestDTO = new DspConttAbTestDTO();
		
		if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScDspBrndId())) {
			dspConttAbTestDTO.setScDspCtgryNo(dspCnrConttScBoDTO.getScDspCtgryNo());
			dspConttAbTestDTO.setScDspBrndId(dspCnrConttScBoDTO.getScDspBrndId());
		}
		else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScDspCtgryNo())) {
			dspConttAbTestDTO.setScDspCtgryNo( dspCnrConttScBoDTO.getScDspCtgryNo());
		}
		else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScEvtNo())) {
			dspConttAbTestDTO.setScEvtNo(dspCnrConttScBoDTO.getScEvtNo());
		}
		else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScPromtSn())) {
			dspConttAbTestDTO.setScPromtSn(dspCnrConttScBoDTO.getScPromtSn());
		}
		else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScStrndSn())) {
			dspConttAbTestDTO.setScStrndSn(dspCnrConttScBoDTO.getScStrndSn());
		}
		
		return dspConttAbTestDTO;
	}
	
	
	/**
	 * DTO에서 코너컨텐츠 페이지 KEY 가져오기
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @return the page key data 
	 * @throws Exception the exception
	 */
	private String[] getPageKeyData(DspConttAbTestDTO dspConttAbTestDTO)  {
		String[] key = new String[2];
		
		if(StringService.isNotEmpty(dspConttAbTestDTO.getScDspBrndId())) {
			key[0] = dspConttAbTestDTO.getScDspCtgryNo();
			key[1] = dspConttAbTestDTO.getScDspBrndId();
		}
		else if(StringService.isNotEmpty(dspConttAbTestDTO.getScDspCtgryNo())) {
			key[0] = dspConttAbTestDTO.getScDspCtgryNo();
			key[1] = "";
		}
		else if(StringService.isNotEmpty(dspConttAbTestDTO.getScEvtNo())) {
			key[0] = dspConttAbTestDTO.getScEvtNo();
			key[1] = "";
		}
		else if(StringService.isNotEmpty(dspConttAbTestDTO.getScPromtSn())) {
			key[0] = dspConttAbTestDTO.getScPromtSn();
			key[1] = "";
		}
		else if(StringService.isNotEmpty(dspConttAbTestDTO.getScStrndSn())) {
			key[0] = dspConttAbTestDTO.getScStrndSn();
			key[1] = "";
		}
		
		return key;
	}
	
	private String[] getPageKeyData(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		String[] key = new String[2];
		
		if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScDspBrndId())) {
			key[0] = dspCnrConttScBoDTO.getScDspCtgryNo();
			key[1] = dspCnrConttScBoDTO.getScDspBrndId();
		}
		else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScDspCtgryNo())) {
			key[0] = dspCnrConttScBoDTO.getScDspCtgryNo();
			key[1] = "";
		}
		else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScEvtNo())) {
			key[0] = dspCnrConttScBoDTO.getScEvtNo();
			key[1] = "";
		}
		else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScPromtSn())) {
			key[0] = dspCnrConttScBoDTO.getScPromtSn();
			key[1] = "";
		}
		else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScStrndSn())) {
			key[0] = dspCnrConttScBoDTO.getScStrndSn();
			key[1] = "";
		}
		
		return key;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevCommandComponent#modCompleteAbTest(com.plgrim.ncp.biz.display.data.DspConttAbTestDTO)
	 */
	@Override
	public void modCompleteAbTest(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		try {
			String loginId = BOSecurityUtil.getLoginId();
			String scAbTestModTurn = dspCnrConttScBoDTO.getScAbTestModTurn();
			
			Integer modTurn = 0;
			if(StringService.isNotEmpty(scAbTestModTurn)) {
				modTurn = Integer.parseInt(scAbTestModTurn);
			}
			
			Integer newModTurn = modTurn;
			Integer oldModTurn = modTurn - 1;
					
			//modTurn - 1 은 모두 'N'으로 update
			dspCnrConttScBoDTO.setScAbTestModTurn(String.valueOf(oldModTurn));
			dspCnrConttScBoDTO.setScRevSectCd(DisplayEnum.BO_CONTT_VIEW_TYPE.AB_TEST.toString());
			List<DspAbTestRevResult> befRevList = displayRevSelectService.selectDspAbTestRevListByMod(dspCnrConttScBoDTO);
			for(DspAbTestRevResult dspAbTestRevResult: befRevList) {
				DspRevCpst dspRevCpst = dspAbTestRevResult.getDspRevCpst();
				
				DspRevCpst paramCpst = new DspRevCpst();
				paramCpst.setUseYn("N");
				paramCpst.setUdterId(loginId);
				paramCpst.setRevSn(dspRevCpst.getRevSn());
				paramCpst.setRevCpstTurn(dspRevCpst.getRevCpstTurn());
				displayRevCommandService.updateDspRevCpstInfo(paramCpst);
				
				if(!dspRevCpst.getRevSn().equals(Long.valueOf("1"))) {
					DspRev paramRev = new DspRev();
					paramRev.setUseYn("N");
					paramRev.setUdterId(loginId);
					paramRev.setRevSn(dspRevCpst.getRevSn());
					displayRevCommandService.updateDspRev(paramRev);
				}
				
				//feature 사용여부를 'N'으로 저장
				DspConttAbTestDTO dspConttAbTestDTO = this.setAbTestPageKeyData(dspCnrConttScBoDTO);
				DspAbTest dspAbTest = new DspAbTest();
				dspAbTest.setAbTestSn(Long.parseLong(dspCnrConttScBoDTO.getScAbTestSn()));
				dspConttAbTestDTO.setDspAbTest(dspAbTest);
				dspConttAbTestDTO.setScRevSn(dspRevCpst.getRevSn());
				this.updateAbTestUseYnSysConfig(dspConttAbTestDTO);
			}
			
			
			//신규 mod 사용여부를 'Y'로 하고 feature 신규 등록
			//dsp_rev_cpst use_yn ==> 'Y'로 update
			//dsp_rev use_yn = 'Y'로 update
			dspCnrConttScBoDTO.setScAbTestModTurn(String.valueOf(newModTurn));
			dspCnrConttScBoDTO.setScRevSectCd(DisplayEnum.BO_CONTT_VIEW_TYPE.AB_TEST.toString());
			List<DspAbTestRevResult> curRevList = displayRevSelectService.selectDspAbTestRevListByMod(dspCnrConttScBoDTO);
			for(int i=0; i<curRevList.size(); i++)  {
				DspAbTestRevResult dspAbTestRevResult = curRevList.get(i);
				DspRevCpst dspRevCpst = dspAbTestRevResult.getDspRevCpst();
				
				DspRevCpst paramCpst = new DspRevCpst();
				paramCpst.setUseYn("Y");
				paramCpst.setUdterId(loginId);
				paramCpst.setRevSn(dspRevCpst.getRevSn());
				paramCpst.setRevCpstTurn(dspRevCpst.getRevCpstTurn());
				displayRevCommandService.updateDspRevCpstInfo(paramCpst);
				
				DspRev paramRev = new DspRev();
				paramRev.setUseYn("Y");
				paramRev.setUdterId(loginId);
				paramRev.setRevSn(dspRevCpst.getRevSn());
				displayRevCommandService.updateDspRev(paramRev);
				
				//feature 등록
				//select insert
				DspAbTestRev dspAbTestRev = dspAbTestRevResult.getDspAbTestRev();
				String key[] = this.getPageKeyData(dspCnrConttScBoDTO);
				
				String abTestKey = "AB_" + dspAbTestRev.getAbTestSn() + "_" + key[0] + "_" + key[1] ;
				
				DspAbTestRevResult bef = befRevList.get(i);
				DspAbTestRev befRev = bef.getDspAbTestRev();
				String bfConfigNm = DC_FEATURES + "." + abTestKey + "_" + befRev.getRevSn();
				Date bfConfigBegDt = bef.getDspAbTestSetMod().getBegDt();
				
				String curConfigNm = DC_FEATURES + "." + abTestKey + "_" + dspAbTestRev.getRevSn();
				
				
				SysConfigDTO paramData = new SysConfigDTO();
				//이전 설정 데이터
				SysDynmcConfig agoSysDynmcConfig = new SysDynmcConfig();
				agoSysDynmcConfig.setMallId("DXM");
				agoSysDynmcConfig.setConfigNm(bfConfigNm);
				agoSysDynmcConfig.setConfigBegDt(bfConfigBegDt);
				paramData.setAgoSysDynmcConfig(agoSysDynmcConfig);
				
				SysDynmcConfig curSysDynmcConfig = new SysDynmcConfig();
				curSysDynmcConfig.setConfigNm(curConfigNm);
				curSysDynmcConfig.setConfigBegDt(dspAbTestRevResult.getDspAbTestSetMod().getBegDt());
				curSysDynmcConfig.setUseYn("Y");
				curSysDynmcConfig.setRegtrId(loginId);
				curSysDynmcConfig.setUdterId(loginId);
				paramData.setSysDynmcConfig(curSysDynmcConfig);
					
				sysConfigService.insertSysDynmcConfigBySelect(paramData);
				
				sysConfigService.insertSysDynmcConfigDetailBySelect(paramData);
			}
			log.info(CommonResponseCode.DP_00012_ABTEST_수정완료_성공.toMessage() +" abtestSn: {}" , dspCnrConttScBoDTO.getScAbTestSn());
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40006_ABTEST_수정완료_실패.toMessage() +" abtestSn: {}" , dspCnrConttScBoDTO.getScAbTestSn());
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevCommandComponent#modCancelAbTest(com.plgrim.ncp.biz.display.data.DspConttAbTestDTO)
	 */
	@Override
	public void modCancelAbTest(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		try{
			//modTurn 에 해당하는 내용 모두 삭제
			dspCnrConttScBoDTO.setScRevSectCd(DisplayEnum.BO_CONTT_VIEW_TYPE.AB_TEST.toString());
			List<DspAbTestRevResult> curRevList = displayRevSelectService.selectDspAbTestRevListByMod(dspCnrConttScBoDTO);
			for(DspAbTestRevResult dspAbTestRevResult: curRevList) {
				DspRevCpst dspRevCpst = dspAbTestRevResult.getDspRevCpst();
				DspAbTestRev dspAbTestRev = dspAbTestRevResult.getDspAbTestRev();
				
				//dsp_ab_test_rev 삭제
				displayRevCommandService.deleteDspAbTestRevInfo(dspAbTestRev);
				
				//해당 개정의 컨텐츠 삭제
				//컨텐츠 전체 - dsp_rev_cpst 포함
				dspCnrConttScBoDTO.setRevOnlyYn("Y");
				displayRevCommandService.deleteRevContt(dspCnrConttScBoDTO, dspRevCpst.getRevSn());
				
				//dsp_rev 삭제
				DspRevBoDTO dspRevDTO = new DspRevBoDTO();
				dspRevDTO.setDspRev(dspAbTestRevResult.getDspRev());
				displayRevCommandService.deleteRevInfo(dspRevDTO);
			}
			
			//dsp_ab_test_set_mod 삭제
			DspAbTestSetMod dspAbTestSetMod = new DspAbTestSetMod();
			dspAbTestSetMod.setAbTestSn(Long.parseLong(dspCnrConttScBoDTO.getScAbTestSn()));
			dspAbTestSetMod.setSetTurn(Integer.parseInt(dspCnrConttScBoDTO.getScAbTestSetTurn()));
			dspAbTestSetMod.setModTurn(Integer.parseInt(dspCnrConttScBoDTO.getScAbTestModTurn()));
			displayRevCommandService.deleteDspAbTestSetMod(dspAbTestSetMod);
			
			log.info(CommonResponseCode.DP_00013_ABTEST_수정취소_성공.toMessage() +" abtestSn: {}" , dspCnrConttScBoDTO.getScAbTestSn());
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40007_ABTEST_수정취소_실패.toMessage() +" abtestSn: {}" , dspCnrConttScBoDTO.getScAbTestSn());
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void foApply(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		try {
			log.info(CommonResponseCode.DP_00010_ABTEST_FO적용_시작.toMessage() +" abtestSn: {}" , dspCnrConttScBoDTO.getScAbTestSn());
			String loginId = BOSecurityUtil.getLoginId();
			dspCnrConttScBoDTO.setScAdminId(loginId);
			dspCnrConttScBoDTO.setCnrCopyYn("Y");

			//특정 화면 BASE 리비전의 템플릿 번호 조회
			String baseTmplatSn = displayRevSelectService.selectBaseRevTmplatSn(dspCnrConttScBoDTO);

			// base 리비전의 템플릿과 FO 적용 할 리비전의 템플릿이 다를 경우 전시 코너 템플릿 정보 연결의 데이터를 삭제&복사 하기 위함
			String scTmplatSn = dspCnrConttScBoDTO.getScTmplatSn();
			if(StringService.isNotEmpty(scTmplatSn)&& !scTmplatSn.equals(baseTmplatSn)){
				dspCnrConttScBoDTO.setCnrCopyYn("N");
				dspCnrConttScBoDTO.setScTmplatSn(baseTmplatSn); //삭제 대상 tmplatSn으로 변경
			}

			//base 리비전의 해당 코너의 모든 컨텐츠 삭제
			displayRevCommandService.deleteRevPageConttForAbTest(dspCnrConttScBoDTO, dspCnrConttScBoDTO.getScRevSn(), baseRevSn);

			//abtest 위너 리비전의 해당 코너 모든 컨텐츠를 조회하여 insert
			dspCnrConttScBoDTO.setScTmplatSn(scTmplatSn); //등록대상 tamplatSn으로 재변경
			displayRevCommandService.copyRevCnrConttInfoForAbTest(dspCnrConttScBoDTO, dspCnrConttScBoDTO.getScRevSn(), baseRevSn);
			
			//AB-TEST 종료상태가 아닐 경우만 실행
			// FO 적용을 눌렀을 경우 AB TEST 종료
			DspAbTest dspAbTest = new DspAbTest();
			dspAbTest.setAbTestSn(Long.parseLong(dspCnrConttScBoDTO.getScAbTestSn()));

			dspAbTest = displayRevSelectService.selectAbTestConttDetail(dspAbTest);
			if(dspAbTest != null && !"N".equals(dspAbTest.getProgrsPsbYn())) {
				DspConttAbTestDTO dspConttAbTestDTO = new DspConttAbTestDTO();
				Date nowDt = new Date();
				dspConttAbTestDTO.setInEndDt(DateService.parseString(nowDt,"yyyyMMddHHmmss"));
				dspAbTest.setProgrsPsbYn("N");
				dspConttAbTestDTO.setDspAbTest(dspAbTest);
	
				displayRevCommandService.updateAbTestInfo(dspConttAbTestDTO);
	
	
				if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScDspBrndId())) {
					dspConttAbTestDTO.setScDspCtgryNo(dspCnrConttScBoDTO.getScDspCtgryNo());
					dspConttAbTestDTO.setScDspBrndId(dspCnrConttScBoDTO.getScDspBrndId());
				}
				else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScDspCtgryNo())) {
					dspConttAbTestDTO.setScDspCtgryNo( dspCnrConttScBoDTO.getScDspCtgryNo());
				}
				else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScEvtNo())) {
					dspConttAbTestDTO.setScEvtNo(dspCnrConttScBoDTO.getScEvtNo());
				}
				else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScPromtSn())) {
					dspConttAbTestDTO.setScPromtSn(dspCnrConttScBoDTO.getScPromtSn());
				}
				else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScStrndSn())) {
					dspConttAbTestDTO.setScStrndSn(dspCnrConttScBoDTO.getScStrndSn());
				}
	
				this.updateAbTestUseYnSysConfig(dspConttAbTestDTO);
			}
			
			log.info(CommonResponseCode.DP_00011_ABTEST_FO적용_성공.toMessage() +" abtestSn: {}, abtestRevSn: {}, baseRevSn: {}", dspCnrConttScBoDTO.getScAbTestSn(),dspCnrConttScBoDTO.getScRevSn(),baseRevSn);
			
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40005_ABTEST_FO적용_실패.toMessage() +" abtestSn: {}, abtestRevSn: {}, baseRevSn: {}", dspCnrConttScBoDTO.getScAbTestSn(),dspCnrConttScBoDTO.getScRevSn(),baseRevSn);
			throw new RuntimeException(e);
		}
	}
}
