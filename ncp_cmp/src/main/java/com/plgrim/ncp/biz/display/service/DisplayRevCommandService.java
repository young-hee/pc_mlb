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
 * @since       2015. 4. 7       
 */
package com.plgrim.ncp.biz.display.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetFlter;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetMod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTmplatInfoCnnc;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;
import com.plgrim.ncp.base.repository.dsp.DspAbTestRepository;
import com.plgrim.ncp.base.repository.dsp.DspAbTestRevRepository;
import com.plgrim.ncp.base.repository.dsp.DspAbTestSetFlterRepository;
import com.plgrim.ncp.base.repository.dsp.DspAbTestSetModRepository;
import com.plgrim.ncp.base.repository.dsp.DspAbTestSetRepository;
import com.plgrim.ncp.base.repository.dsp.DspCnrRepository;
import com.plgrim.ncp.base.repository.dsp.DspCnrTmplatInfoCnncRepository;
import com.plgrim.ncp.base.repository.dsp.DspCtgryRepository;
import com.plgrim.ncp.base.repository.dsp.DspRevCpstRepository;
import com.plgrim.ncp.base.repository.dsp.DspRevRepository;
import com.plgrim.ncp.base.repository.dsp.DspStrndRepository;
import com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO;
import com.plgrim.ncp.biz.display.data.DspConttAbTestDTO;
import com.plgrim.ncp.biz.display.data.DspRevBoDTO;
import com.plgrim.ncp.biz.display.repository.DisplayCategoryRepository;
import com.plgrim.ncp.biz.display.repository.DisplayRevCommandRepository;
import com.plgrim.ncp.biz.display.repository.DisplayRevSelectRepository;
import com.plgrim.ncp.biz.display.result.DspCnrTmplatInfoCnncResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;

/**
 * 전시 리비전 Select Service
 * 
 */
@Service
public class DisplayRevCommandService extends AbstractService {

	@Autowired
	DisplayRevSelectRepository displayRevSelectRepository;
	
	@Autowired
	DspRevRepository dspRevRepository;
	
	@Autowired
	DspRevCpstRepository dspRevCpstRepository;
	
	@Autowired
	DspAbTestRepository dspAbTestRepository;
	
	@Autowired
	DspAbTestSetRepository dspAbTestSetRepository;
	
	@Autowired
	DspAbTestSetFlterRepository dspAbTestSetFlterRepository;
	
	@Autowired
	DspAbTestSetModRepository dspAbTestSetModRepository;
	
	@Autowired
	DspAbTestRevRepository dspAbTestRevRepository;
	
	
	@Autowired
	DisplayRevCommandRepository displayRevCommandRepository;
	
	@Autowired
	DspCnrRepository dspCnrRepository;
	
	@Autowired
	DspCnrTmplatInfoCnncRepository dspCnrTmplatInfoCnncRepository;

	@Autowired
	DisplayCategoryRepository displayCategoryRepository;

	@Autowired
	DspStrndRepository dspStrndRepository;

	@Autowired
	DspCtgryRepository dspCtgryRepository;


	/**
	 * 전시개정 등록
	 *
	 * @param dspRevDTO
	 * @return
	 * @
	 */
	public long insertRevInfo(DspRevBoDTO dspRevDTO)  {
		return displayRevCommandRepository.insertRevInfo(dspRevDTO);
	}
	
	
	/**
	 * 전시 개정 수정
	 *
	 * @param dspRevDTO the dsp rev dto
	 * @return the int
	 * @ the exception
	 */
	public int updateRevInfo(DspRevBoDTO dspRevDTO)  {
		//return dspRevRepository.updateDspRev(dspRevDTO.getDspRev());
		return 0;
	}
	

	/**
	 * 전시 개정 삭제
	 *
	 * @param dspRevDTO the dsp rev dto
	 * @return the int
	 * @ the exception
	 */
	public int deleteRevInfo(DspRevBoDTO dspRevDTO)  {
		//return dspRevRepository.deleteDspRev(dspRevDTO.getDspRev());
		return 0;
	}
	
	
	/**
	 * 리비전 복사.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @param srcRevSn 복사대상 리비전 번호
	 * @param desRevSn 등록되는 리비전 번호
	 * @ the exception
	 */
	public void copyRevCnrConttInfo(DspCnrConttScBoDTO dspCnrConttScBoDTO, Long srcRevSn, Long desRevSn)  {
		
		dspCnrConttScBoDTO.setScSrcRevSn(srcRevSn);
		dspCnrConttScBoDTO.setScDesRevSn(desRevSn);
		
		if(StringService.isEmpty(dspCnrConttScBoDTO.getCnrCopyYn()) || "N".equals(dspCnrConttScBoDTO.getCnrCopyYn())) {
			// dsp_cnr : 코너는 공유 되기 때문에 존재하는지 체크하여 insert한다.
			List<Long> cnrSnList = displayRevSelectRepository.getDspCnrRevList(dspCnrConttScBoDTO);
			for(Long revCnrSn: cnrSnList) {
				dspCnrConttScBoDTO.setScCpRevCnrSn(revCnrSn.toString());
				Integer cnrCnt = displayRevSelectRepository.checkDspCnrRev(dspCnrConttScBoDTO);
				if(cnrCnt == 0) {
					displayRevCommandRepository.insertDspCnrRev(dspCnrConttScBoDTO);
				}
				
				Integer cnrGrpCnt = displayRevSelectRepository.checkDspCnrTpGrpRev(dspCnrConttScBoDTO);
				if(cnrGrpCnt == 0) {
					// dsp_cnr_tp_grp - 그룹유형
					dspCnrConttScBoDTO.setTpGrpUpperYn("Y");
					displayRevCommandRepository.insertDspCnrTpGrpRev(dspCnrConttScBoDTO);

					// dsp_cnr_tp_grp - 컨텐츠유형
					dspCnrConttScBoDTO.setTpGrpUpperYn("N");
					displayRevCommandRepository.insertDspCnrTpGrpRev(dspCnrConttScBoDTO);
				}
			}
			dspCnrConttScBoDTO.setScCpRevCnrSn(null);
			
			// dsp_cnr_tmplat_info_cnnc 
			displayRevCommandRepository.insertDspCnrTmplatInfoCnncRev(dspCnrConttScBoDTO);
			
			// dsp_rev_cpst
			if(StringService.isEmpty(dspCnrConttScBoDTO.getRevCpstCopyYn()) || "N".equals(dspCnrConttScBoDTO.getRevCpstCopyYn())) {
				displayRevCommandRepository.insertDspRevCpstRev(dspCnrConttScBoDTO);
			}

			
			
			// dsp_cnr_set
			displayRevCommandRepository.insertDspCnrSetRev(dspCnrConttScBoDTO);
			
			// dsp_cnr_set_lang
			displayRevCommandRepository.insertDspCnrSetLangRev(dspCnrConttScBoDTO);
		}
		
		// dsp_cnr_contt
		displayRevCommandRepository.insertDspCnrConttRev(dspCnrConttScBoDTO);
		
		// dsp_cnr_contt_lang
		displayRevCommandRepository.insertDspCnrConttLangRev(dspCnrConttScBoDTO);
		
		// dsp_cnr_contt_dsp_tgt
		displayRevCommandRepository.insertDspCnrConttDspTgtRev(dspCnrConttScBoDTO);

	}
	
	
	/**
	 * 특정 페이지의 리비전 삭제.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @param srcRevSn 복사대상 리비전 번호
	 * @param desRevSn 삭제할 리비전 번호
	 * @ the exception
	 */
	public void deleteRevPageContt(DspCnrConttScBoDTO dspCnrConttScBoDTO, Long srcRevSn, Long desRevSn)  {
		
		dspCnrConttScBoDTO.setScSrcRevSn(srcRevSn);
		dspCnrConttScBoDTO.setScDesRevSn(desRevSn);
		
		// dsp_cnr_contt_dsp_tgt
		displayRevCommandRepository.deleteDspCnrConttDspTgtRev(dspCnrConttScBoDTO);
		
		// dsp_cnr_contt_lang
		displayRevCommandRepository.deleteDspCnrConttLangRev(dspCnrConttScBoDTO);
				
		// dsp_cnr_contt
		displayRevCommandRepository.deleteDspCnrConttRev(dspCnrConttScBoDTO);
		
		if(StringService.isEmpty(dspCnrConttScBoDTO.getCnrCopyYn()) || "N".equals(dspCnrConttScBoDTO.getCnrCopyYn())) {
			// dsp_cnr_set_lang
			displayRevCommandRepository.deleteDspCnrSetLangRev(dspCnrConttScBoDTO);
	
			// dsp_cnr_set
			displayRevCommandRepository.deleteDspCnrSetRev(dspCnrConttScBoDTO);	
		
	
			// dsp_rev_cpst
			displayRevCommandRepository.deleteDspRevCpstRev(dspCnrConttScBoDTO);
						
			// dsp_cnr_tmplat_info_cnnc
			displayRevCommandRepository.deleteDspCnrTmplatInfoCnncRev(dspCnrConttScBoDTO);
			
			// dsp_cnr
			//displayRevCommandRepository.deleteDspCnrRev(dspCnrConttScBoDTO);
		}		
				
	}
	
	/**
	 * 컨텐츠의 특정 리비전 모두 삭제할 경우
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @param scRevSn the sc rev sn
	 * @ the exception
	 */
	public void deleteRevContt(DspCnrConttScBoDTO dspCnrConttScBoDTO, Long scRevSn)  {
		
		dspCnrConttScBoDTO.setScDesRevSn(scRevSn);
		
		// dsp_cnr_contt_dsp_tgt
		displayRevCommandRepository.deleteDspCnrConttDspTgtRev(dspCnrConttScBoDTO);
		
		// dsp_cnr_contt_lang
		displayRevCommandRepository.deleteDspCnrConttLangRev(dspCnrConttScBoDTO);
				
		// dsp_cnr_contt
		displayRevCommandRepository.deleteDspCnrConttRev(dspCnrConttScBoDTO);
		
		if(StringService.isEmpty(dspCnrConttScBoDTO.getCnrCopyYn()) || "N".equals(dspCnrConttScBoDTO.getCnrCopyYn())) {
			// dsp_cnr_set_lang
			displayRevCommandRepository.deleteDspCnrSetLangRev(dspCnrConttScBoDTO);
	
			// dsp_cnr_set
			displayRevCommandRepository.deleteDspCnrSetRev(dspCnrConttScBoDTO);
			
			// dsp_cnr_tp_grp - 컨텐츠유형
			dspCnrConttScBoDTO.setTpGrpUpperYn("N");
			displayRevCommandRepository.deleteDspCnrTpGrpRev(dspCnrConttScBoDTO);
			
			// dsp_cnr_tp_grp - 그룹유형
			dspCnrConttScBoDTO.setTpGrpUpperYn("Y");
			displayRevCommandRepository.deleteDspCnrTpGrpRev(dspCnrConttScBoDTO);
	
			// dsp_rev_cpst
			if(StringService.isEmpty(dspCnrConttScBoDTO.getRevCpstCopyYn()) || "N".equals(dspCnrConttScBoDTO.getRevCpstCopyYn())) {
				displayRevCommandRepository.deleteDspRevCpstRev(dspCnrConttScBoDTO);
			}
						
			// dsp_cnr_tmplat_info_cnnc
			displayRevCommandRepository.deleteDspCnrTmplatInfoCnncRev(dspCnrConttScBoDTO);
			
			// dsp_cnr
			displayRevCommandRepository.deleteDspCnrRev(dspCnrConttScBoDTO);
		}		
				
	}
	
	/**
	 * 페이지별 리비전 저장
	 *
	 * @param dspRevCpst
	 * @
	 */
	public void saveDspRevCpst(DspRevCpst dspRevCpst)  {
		String loginId = BOSecurityUtil.getLoginId();
		dspRevCpst.setRevSn(1L);
		Integer cnt = displayRevSelectRepository.selectDspRevCpstOne(dspRevCpst);
		if(cnt == 0) {
			dspRevCpst.setRegtrId(loginId);
			dspRevCpst.setUdterId(loginId);
			dspRevCpst.setUseYn("Y");
			displayRevCommandRepository.insertDspRevCpstOne(dspRevCpst);
		}
		else {
			//템플릿번호가 변경되었을 경우 데이터 처리 확인
			dspRevCpst.setUdterId(loginId);
			displayRevCommandRepository.updateDspRevCpstOne(dspRevCpst);
		}
	}
	
	/**
	 * Adds the contt ab test.
	 *
	 * @param DspConttAbTestDTO the dsp contt ab test DTO
	 * @return the long
	 * @throws ParseException 
	 * @ the exception
	 */
	public long addConttAbTest(DspConttAbTestDTO dspConttAbTestDTO) throws ParseException  {
		
		String loginId = BOSecurityUtil.getLoginId();
		
		//dsp_ab_test
		DspAbTest dspAbTest = dspConttAbTestDTO.getDspAbTest();
		dspAbTest.setRegtrId(loginId);
		dspAbTest.setUdterId(loginId);
		dspAbTest.setProgrsPsbYn("Y");
		dspAbTest.setSetUseYn("N");
		
		String inBegDt = dspConttAbTestDTO.getInBegDt();
		String inEndDt = dspConttAbTestDTO.getInEndDt();
		SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		dspAbTest.setBegDt(simplDateFormat.parse(inBegDt));
		dspAbTest.setEndDt(simplDateFormat.parse(inEndDt));	
		
		long abTestSn = displayRevCommandRepository.insertDspAbTestOne(dspConttAbTestDTO.getDspAbTest());
		
		//dsp_ab_test_set
		DspAbTestSet dspAbTestSet = new DspAbTestSet();
		dspAbTestSet.setAbTestSn(abTestSn);
		dspAbTestSet.setExpsrRt(new BigDecimal(0)); //A/B테스트 등록 당시는 0으로 저장
		dspAbTestSet.setRegtrId(loginId);
		dspAbTestSet.setUdterId(loginId);
		Integer setTurn = displayRevCommandRepository.insertDspAbTestSetOne(dspAbTestSet);
		
		//dsp_ab_test_set_mod
		DspAbTestSetMod dspAbTestSetMod = new DspAbTestSetMod();
		dspAbTestSetMod.setAbTestSn(abTestSn);
		dspAbTestSetMod.setSetTurn(setTurn);
		dspAbTestSetMod.setModTurn(1);
		dspAbTestSetMod.setModResnCont("-");
		dspAbTestSetMod.setBegDt(simplDateFormat.parse(inBegDt));
		dspAbTestSetMod.setEndDt(simplDateFormat.parse(inEndDt));	
		dspAbTestSetMod.setRegtrId(loginId);
		dspAbTestSetMod.setUdterId(loginId);		
		//dspAbTestSetModRepository.insertDspAbTestSetMod(dspAbTestSetMod);
		
		//dsp_ab_test_rev
		DspAbTestRev dspAbTestRev = new DspAbTestRev();
		dspAbTestRev.setAbTestSn(abTestSn);
		dspAbTestRev.setRevSn(dspConttAbTestDTO.getScRevSn());
		dspAbTestRev.setRevCpstTurn(dspConttAbTestDTO.getScRevCpstTurn());
		dspAbTestRev.setDvcSectCd(dspConttAbTestDTO.getScDvcSectCd());
		dspAbTestRev.setSetTurn(setTurn);
		dspAbTestRev.setModTurn(1);
		dspAbTestRev.setAbTestGrpCd("EXCLS");
		dspAbTestRev.setRegtrId(loginId);
		dspAbTestRev.setUdterId(loginId);	
		//dspAbTestRevRepository.insertDspAbTestRev(dspAbTestRev);
		
		return abTestSn;
	}
	
	/**
	 * A/B 테스트 세트 등록 
	 *
	 * @param dspAbTestSet the dsp ab test set
	 * @ the exception
	 */
	public void insertDspAbTestSetInfo(DspAbTestSet dspAbTestSet)  {
		//dspAbTestSetRepository.insertDspAbTestSet(dspAbTestSet);
	}
	
	/**
	 * Insert dsp ab test set one. - set turn 생성
	 *
	 * @param dspAbTestSet the dsp ab test set
	 * @return the int
	 */
	public int insertDspAbTestSetOne(DspAbTestSet dspAbTestSet)  {
		//return displayRevCommandRepository.insertDspAbTestSetOne(dspAbTestSet);
		return 0;
	}
	
	/**
	 * A/B 테스트 세트 변경 등록
	 *
	 * @param dspAbTestSetMod the dsp ab test set mod
	 * @ the exception
	 */
	public void insertDspAbTestSetModInfo(DspAbTestSetMod dspAbTestSetMod)  {
		//dspAbTestSetModRepository.insertDspAbTestSetMod(dspAbTestSetMod);
	}
	
	/**
	 * A/B 테스트 세트 변경 수정.
	 *
	 * @param dspAbTestSetMod the dsp ab test set mod
	 * @ the exception
	 */
	public void updateDspAbTestSetModInfo(DspAbTestSetMod dspAbTestSetMod)  {
		displayRevCommandRepository.updateDspAbTestSetModInfo(dspAbTestSetMod);
	}
	
	/**
	 * A/B 테스트 세트 변경 삭제
	 *
	 * @param dspAbTestSetMod the dsp ab test set mod
	 * @ the exception
	 */
	public void deleteDspAbTestSetMod(DspAbTestSetMod dspAbTestSetMod)  {
		//dspAbTestSetModRepository.deleteDspAbTestSetMod(dspAbTestSetMod);
	}
	
	/**
	 * A/B 테스트 수정
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @return the int
	 * @throws ParseException 
	 * @ the exception
	 */
	public int updateAbTestInfo(DspConttAbTestDTO dspConttAbTestDTO) throws ParseException  {
		String loginId = BOSecurityUtil.getLoginId();
		
		DspAbTest dspAbTest = dspConttAbTestDTO.getDspAbTest();
		dspAbTest.setUdterId(loginId);
		
		String inBegDt = dspConttAbTestDTO.getInBegDt();
		String inEndDt = dspConttAbTestDTO.getInEndDt();
		SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		if(StringService.isNotEmpty(inBegDt)) {
			dspAbTest.setBegDt(simplDateFormat.parse(inBegDt));
		}
		if(StringService.isNotEmpty(inEndDt)) {
			dspAbTest.setEndDt(simplDateFormat.parse(inEndDt));
		}
		
		//int result = dspAbTestRepository.updateDspAbTest(dspAbTest);
		int result = 0;
		
		//dsp_ab_test_set_mod 테이블의 날짜 수정
		if(dspAbTest.getBegDt() != null) {
			displayRevCommandRepository.updateAbTestModBegDt(dspAbTest);
		}
		if(dspAbTest.getEndDt() != null) {
			displayRevCommandRepository.updateAbTestModEndDt(dspAbTest);
		}

		return result;
	}
	
	/**
	 * A/B 테스트 기간수정
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @return the int
	 * @throws ParseException 
	 * @ the exception
	 */
	public int updateAbTestDate(DspConttAbTestDTO dspConttAbTestDTO) throws ParseException  {
		String loginId = BOSecurityUtil.getLoginId();
		
		DspAbTest dspAbTest = dspConttAbTestDTO.getDspAbTest();
		dspAbTest.setUdterId(loginId);
		
		String inEndDt = dspConttAbTestDTO.getInEndDt();
		SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		if(StringService.isNotEmpty(inEndDt)) {
			dspAbTest.setEndDt(simplDateFormat.parse(inEndDt));
		}
		
		int result = displayRevCommandRepository.updateDspAbTestInfo(dspAbTest);
		
		if(dspAbTest.getEndDt() != null) {
			displayRevCommandRepository.updateAbTestModEndDt(dspAbTest);
		}
		return result;
	}
	
	
	/**
	 * A/B 테스트 시작일자 수정
	 *
	 * @param dspAbTest the dsp ab test
	 */
	public void updateAbTestModBegDt(DspAbTest dspAbTest)  {
		displayRevCommandRepository.updateAbTestModBegDt(dspAbTest);
	}
	
	/**
	 * A/B 테스트 종료일자 수정
	 *
	 * @param dspAbTest the dsp ab test
	 */
	public void updateAbTestModEndDt(DspAbTest dspAbTest)  {
		displayRevCommandRepository.updateAbTestModEndDt(dspAbTest);
	}
	
	/**
	 * A/B 테스트 삭제
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @return the int
	 * @ the exception
	 */
	public void deleteConttAbTest(DspConttAbTestDTO dspConttAbTestDTO)  {
		DspAbTest dspAbTest = dspConttAbTestDTO.getDspAbTest();
		
		//dsp_ab_test_set_flter 삭제  (abtestsn), base
//		DspAbTestSetFlter dspAbTestSetFlter = new DspAbTestSetFlter();
//		dspAbTestSetFlter.setAbTestSn(dspAbTest.getAbTestSn());
//		displayRevCommandRepository.deleteDspAbTestSetFlterInfo(dspAbTestSetFlter);
		
		//dsp_ab_test_rev 삭제          (abtestsn), base
		DspAbTestRev dspAbTestRev = new DspAbTestRev();
		dspAbTestRev.setAbTestSn(dspAbTest.getAbTestSn());
		displayRevCommandRepository.deleteDspAbTestRevInfo(dspAbTestRev);
		
		//dsp_ab_test_set_mod 삭제    (abtestsn,setturn)
		DspAbTestSetMod dspAbTestSetMod = new DspAbTestSetMod();
		dspAbTestSetMod.setAbTestSn(dspAbTest.getAbTestSn());
		displayRevCommandRepository.deleteDspAbTestSetModInfo(dspAbTestSetMod);
		
		//dsp_ab_test_set 삭제          (abtestsn,setturn), base
		DspAbTestSet dspAbTestSet = new DspAbTestSet();
		dspAbTestSet.setAbTestSn(dspAbTest.getAbTestSn());
		displayRevCommandRepository.deleteDspAbTestSetInfo(dspAbTestSet);
		
		//dsp_ab_test 삭제                base
		//dspAbTestRepository.deleteDspAbTest(dspAbTest);
	}
	
	/**
	 * Delete dsp ab test set.
	 *
	 * @param dspAbTestSet the dsp ab test set
	 * @ the exception
	 */
	public void deleteDspAbTestSet(DspAbTestSet dspAbTestSet)  {
		//dspAbTestSetRepository.deleteDspAbTestSet(dspAbTestSet);
	}
	/**
	 * A/B 테스트 개정 삭제
	 *
	 * @param dspAbTestRev the dsp ab test rev
	 * @ the exception
	 */
	public void deleteDspAbTestRevInfo(DspAbTestRev dspAbTestRev)  {
		displayRevCommandRepository.deleteDspAbTestRevInfo(dspAbTestRev);
	}
	
	
	
	/**
	 * Insert ab test rev info.
	 *
	 * @param dspAbTestRev the dsp ab test rev
	 * @ the exception
	 */
	public void insertAbTestRevInfo(DspAbTestRev dspAbTestRev)  {
		//dspAbTestRevRepository.insertDspAbTestRev(dspAbTestRev);		
	}
	
	/**
	 * Update ab test rev info.
	 *
	 * @param dspAbTestRev the dsp ab test rev
	 * @return the int
	 * @ the exception
	 */
	public int updateAbTestRevInfo(DspAbTestRev dspAbTestRev)  {
		//int result =  dspAbTestRevRepository.updateDspAbTestRev(dspAbTestRev);
		return 0;
	}
	
	/**
	 * 전시 AB 테스트 개정 랜덤 노출율 수정
	 *
	 * @param dspAbTestRev the dsp ab test rev
	 * @return the int
	 * @ the exception
	 */
	public int updateDspAbTestRevExpsrRt(DspAbTestRev dspAbTestRev)  {
		int result =  displayRevCommandRepository.updateDspAbTestRevExpsrRt(dspAbTestRev);
		return result;
	}
	
	/**
	 * 전시 AB 테스트 세트 고객세그먼트 노출율 수정
	 *
	 * @param dspAbTestRev the dsp ab test rev
	 * @return the int
	 * @ the exception
	 */
	public int updateDspAbTestSetExpsrRt(DspAbTestSet dspAbTestSet)  {
		int result =  displayRevCommandRepository.updateDspAbTestSetExpsrRt(dspAbTestSet);
		return result;
	}
	
	/**
	 * Insert dsp rev cpst info.
	 *
	 * @param dspRevCpst the dsp rev cpst
	 * @return the int
	 * @ the exception
	 */
	public void insertDspRevCpstInfo(DspRevCpst dspRevCpst)  {
		displayRevCommandRepository.insertDspRevCpstInfo(dspRevCpst);
	}
	
	/**
	 * Update dsp rev cpst info.
	 *
	 * @param dspRevCpst the dsp rev cpst
	 * @ the exception
	 */
	public void updateDspRevCpstOne(DspRevCpst dspRevCpst)  {
		displayRevCommandRepository.updateDspRevCpstOne(dspRevCpst);
	}
	
	/**
	 * Update dsp rev cpst.
	 *
	 * @param dspRevCpst the dsp rev cpst
	 * @ the exception
	 */
	public void updateDspRevCpstInfo(DspRevCpst dspRevCpst)  {
		displayRevCommandRepository.updateDspRevCpstInfo(dspRevCpst);
	}
	
	
	/**
	 * Insert dsp cnr tmplat info cnnc.
	 *
	 * @param revSn the rev sn
	 * @param list the list
	 * @return the int
	 * @ the exception
	 */
	public int insertDspCnrTmplatInfoCnnc (Long revSn, Long tmplatSn, DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		try {
			int res = 0;
			
			//컨텐츠 복사 - 기본 리비전 템플릿과 다를 경우 : 코너(dsp_cnr) 코너템플릿연결(dsp_cnr_tmplat_info_cnnc)까지 생성	
			DspCnrTmplatInfoCnnc param = new DspCnrTmplatInfoCnnc();
			param.setTmplatSn(tmplatSn);
			param.setRevSn(new Long(1));
			
			if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScDspBrndId())) {
				param.setDspBrndCtgryNo(dspCnrConttScBoDTO.getScDspCtgryNo());
				param.setDspBrndId(dspCnrConttScBoDTO.getScDspBrndId());
			}
			else {
				param.setDspCtgryNo(dspCnrConttScBoDTO.getScDspCtgryNo());
			}
			if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScPromtSn())) {
				param.setPromtSn(Long.parseLong(dspCnrConttScBoDTO.getScPromtSn()));
			}
			if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScStrndSn())) {
				param.setStrndSn(Long.parseLong(dspCnrConttScBoDTO.getScStrndSn()));
			}
			param.setEvtNo(dspCnrConttScBoDTO.getScEvtNo());
			
			List<DspCnrTmplatInfoCnncResult> list = displayRevSelectRepository.selectCnrTmplatInfoCnncRevList(param);
			if (list != null) {
				DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc = null;
				
				for(DspCnrTmplatInfoCnncResult dspCnrTmplatInfoCnncResult: list) {
					dspCnrTmplatInfoCnnc = dspCnrTmplatInfoCnncResult.getDspCnrTmplatInfoCnnc();
					
					dspCnrTmplatInfoCnnc.setRevSn(revSn);
					
					DspCnr dspCnr = dspCnrTmplatInfoCnncResult.getDspCnr();
					dspCnr.setRevSn(revSn);
					dspCnr.setCnrSn(dspCnrTmplatInfoCnnc.getCnrSn());
					dspCnr.setDspCnrNm(dspCnr.getDspCnrNm());
					dspCnr.setDspCnrDscr(dspCnr.getDspCnrDscr());
					dspCnr.setUseYn(dspCnr.getUseYn());
					dspCnr.setRegtrId(param.getRegtrId());
					dspCnr.setUdterId(param.getUdterId());
					
					dspCnrRepository.insertDspCnr(dspCnr);
					
					dspCnrConttScBoDTO.setScSrcRevSn(param.getRevSn());
					dspCnrConttScBoDTO.setScDesRevSn(revSn);
					dspCnrConttScBoDTO.setScCpRevCnrSn(String.valueOf(dspCnrTmplatInfoCnnc.getCnrSn()));
					Integer cnrGrpCnt = displayRevSelectRepository.checkDspCnrTpGrpRev(dspCnrConttScBoDTO);
					if(cnrGrpCnt == 0) {
						// dsp_cnr_tp_grp - 그룹유형
						dspCnrConttScBoDTO.setTpGrpUpperYn("Y");
						displayRevCommandRepository.insertDspCnrTpGrpRev(dspCnrConttScBoDTO);
	
						// dsp_cnr_tp_grp - 컨텐츠유형
						dspCnrConttScBoDTO.setTpGrpUpperYn("N");
						displayRevCommandRepository.insertDspCnrTpGrpRev(dspCnrConttScBoDTO);
					}
					
					dspCnrTmplatInfoCnnc.setDspCtgryNo(param.getDspCtgryNo());
					dspCnrTmplatInfoCnnc.setDspBrndCtgryNo(param.getDspBrndCtgryNo());
					dspCnrTmplatInfoCnnc.setDspBrndId(param.getDspBrndId());
					dspCnrTmplatInfoCnnc.setPromtSn(param.getPromtSn());
					dspCnrTmplatInfoCnnc.setStrndSn(param.getStrndSn());
					dspCnrTmplatInfoCnnc.setEvtNo(param.getEvtNo());
					
					dspCnrTmplatInfoCnnc.setDspCnrTmplatTurn(1);				
					dspCnrTmplatInfoCnncRepository.insertDspCnrTmplatInfoCnnc(dspCnrTmplatInfoCnnc);
				}
			}
			
			return res;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * AB 테스트 정보 수정
	 *
	 * @param dspAbTest the dsp ab test
	 * @return the int
	 * @ the exception
	 */
	public int updateDspAbTestInfo (DspAbTest dspAbTest)  {
		return displayRevCommandRepository.updateDspAbTestInfo(dspAbTest);
	}
	
	
	/**
	 * AB 테스트 개정 수정
	 *
	 * @param dspAbTestRev the dsp ab test rev
	 * @return the int
	 * @ the exception
	 */
	public int updateDspAbTestRevInfo(DspAbTestRev dspAbTestRev)  {
		int result =  displayRevCommandRepository.updateDspAbTestRevInfo(dspAbTestRev);
		return result;
	}
	
	/**
	 * AB 테스트 세트 필터 저장 (merge)
	 *
	 * @param dspAbTestSetFlter the dsp ab test set flter
	 * @return the int
	 * @ the exception
	 */
	public int updateDspAbTestSetFlterInfo(DspAbTestSetFlter dspAbTestSetFlter)  {
		int result =  displayRevCommandRepository.updateDspAbTestSetFlterInfo(dspAbTestSetFlter);
		return result;
	}
	
	/**
	 * 개정 수정
	 *
	 * @param dspRev the dsp rev
	 * @ the exception
	 */
	public void updateDspRev(DspRev dspRev)  {
		displayRevCommandRepository.updateDspRev(dspRev);
	}
	
	/**
	 * 개정 세트 수정
	 *
	 * @param dspAbTestSet the dsp ab test set
	 * @ the exception
	 */
	public void updateDspRevSet(DspAbTestSet dspAbTestSet)  {
		//dspAbTestSetRepository.updateDspAbTestSet(dspAbTestSet);
	}
	
	/**
	 * Delete dsp ab test set flter.
	 *
	 * @param dspAbTestSetFlter the dsp ab test set flter
	 * @ the exception
	 */
	public void deleteDspAbTestSetFlter(DspAbTestSetFlter dspAbTestSetFlter)  {
		//dspAbTestSetFlterRepository.deleteDspAbTestSetFlter(dspAbTestSetFlter);
	}
	
	/**
	 * Delete dsp ab test set flter info.
	 *
	 * @param dspAbTestSetFlter the dsp ab test set flter
	 * @ the exception
	 */
	public void deleteDspAbTestSetFlterInfo(DspAbTestSetFlter dspAbTestSetFlter)  {
		displayRevCommandRepository.deleteDspAbTestSetFlterInfo(dspAbTestSetFlter);
	}

	public void updateBaseRevUseYn(DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		displayRevCommandRepository.updateBaseRevUseYn(dspCnrConttScBoDTO);
	}

	public void updateDspRevSectCd(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		displayRevCommandRepository.updateDspRevSectCd(dspCnrConttScBoDTO);
		if(StringService.isNotEmpty(dspCnrConttScBoDTO.getUseYn())){
			displayRevCommandRepository.updateBaseRevUseYn(dspCnrConttScBoDTO);
		}

	}

	/**
	 * 특정 페이지의 리비전 삭제.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @param srcRevSn 복사대상 리비전 번호
	 * @param desRevSn 삭제할 리비전 번호
	 * @ the exception
	 */
	public void deleteRevPageConttForAbTest(DspCnrConttScBoDTO dspCnrConttScBoDTO, Long srcRevSn, Long desRevSn)  {

		dspCnrConttScBoDTO.setScSrcRevSn(srcRevSn);
		dspCnrConttScBoDTO.setScDesRevSn(desRevSn);

		// dsp_cnr_contt_dsp_tgt
		displayRevCommandRepository.deleteDspCnrConttDspTgtRev(dspCnrConttScBoDTO);

		// dsp_cnr_contt_lang
		displayRevCommandRepository.deleteDspCnrConttLangRev(dspCnrConttScBoDTO);

		// dsp_cnr_contt
		displayRevCommandRepository.deleteDspCnrConttRev(dspCnrConttScBoDTO);

		// dsp_cnr_set_lang
		displayRevCommandRepository.deleteDspCnrSetLangRev(dspCnrConttScBoDTO);

		// dsp_cnr_set
		displayRevCommandRepository.deleteDspCnrSetRev(dspCnrConttScBoDTO);

		if(StringService.isEmpty(dspCnrConttScBoDTO.getCnrCopyYn()) || "N".equals(dspCnrConttScBoDTO.getCnrCopyYn())) {

			// dsp_cnr_tmplat_info_cnnc
			displayRevCommandRepository.deleteDspCnrTmplatInfoCnncRev(dspCnrConttScBoDTO);

		}

	}

	/**
	 * 리비전 컨텐츠 복사.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @param srcRevSn 복사대상 리비전 번호
	 * @param desRevSn 등록되는 리비전 번호
	 * @ the exception
	 */
	public void copyRevCnrConttInfoForAbTest(DspCnrConttScBoDTO dspCnrConttScBoDTO, Long srcRevSn, Long desRevSn)  {

		dspCnrConttScBoDTO.setScSrcRevSn(srcRevSn);
		dspCnrConttScBoDTO.setScDesRevSn(desRevSn);

		if(StringService.isEmpty(dspCnrConttScBoDTO.getCnrCopyYn()) || "N".equals(dspCnrConttScBoDTO.getCnrCopyYn())) {
			// dsp_cnr_tmplat_info_cnnc
			displayRevCommandRepository.insertDspCnrTmplatInfoCnncRev(dspCnrConttScBoDTO);

			// BASE 리비전의 템플릿과 FO 적용한 템플릿이 다를 경우 BASE 리비전의 템플릿 번호 변경
			displayRevCommandRepository.updateBaseRevTmplatSn(dspCnrConttScBoDTO);
		}
		// dsp_cnr_set
		displayRevCommandRepository.insertDspCnrSetRev(dspCnrConttScBoDTO);

		// dsp_cnr_set_lang
		displayRevCommandRepository.insertDspCnrSetLangRev(dspCnrConttScBoDTO);

		// dsp_cnr_contt
		displayRevCommandRepository.insertDspCnrConttRev(dspCnrConttScBoDTO);

		// dsp_cnr_contt_lang
		displayRevCommandRepository.insertDspCnrConttLangRev(dspCnrConttScBoDTO);

		// dsp_cnr_contt_dsp_tgt
		displayRevCommandRepository.insertDspCnrConttDspTgtRev(dspCnrConttScBoDTO);
	}
}
