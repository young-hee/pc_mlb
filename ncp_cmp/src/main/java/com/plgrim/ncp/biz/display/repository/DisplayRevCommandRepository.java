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
 * @since       2015. 4. 9       
 */
package com.plgrim.ncp.biz.display.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetFlter;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetMod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;
import com.plgrim.ncp.base.repository.dsp.DspAbTestRepository;
import com.plgrim.ncp.base.repository.dsp.DspAbTestSetRepository;
import com.plgrim.ncp.base.repository.dsp.DspRevCpstRepository;
import com.plgrim.ncp.base.repository.dsp.DspRevRepository;
import com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO;
import com.plgrim.ncp.biz.display.data.DspRevBoDTO;
import com.plgrim.ncp.framework.enums.DatabaseType;


/**
 * 전시 개정 Command Repository
 */
@Repository
public class DisplayRevCommandRepository extends AbstractRepository {
	
	@Autowired
	DspRevRepository dspRevRepository;

	@Autowired
	DspRevCpstRepository dspRevCpstRepository;

	@Autowired
	DspAbTestRepository dspAbTestRepository;
	
	@Autowired
	DspAbTestSetRepository dspAbTestSetRepository;
	
	/**
	 * 전시 개정 등록
	 *
	 * @param dspRev the dsp rev
	 * @return the long
	 * @ the exception
	 */
	public long insertRevInfo(DspRevBoDTO dspRevDTO)  {
		//개정 일련번호 SEQ생성
		long revSn = getIdGenService().generateDBSequence(getSession1(), "SQ_DSP_REV",  DatabaseType.ORACLE).longValue();
		
		DspRev dspRev = dspRevDTO.getDspRev();
		dspRev.setRevSn(revSn);
		 dspRevRepository.insertDspRev(dspRev);

		return revSn;
	}


	/**
	 * 리비전 복사(select insert) - dspCnr 
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void insertDspCnrRev(DspCnrConttScBoDTO dto)  {
		getSession1().insert("com.plgrim.ncp.biz.display.insertDspCnrRev", dto);
	}
	
	/**
	 * 리비전 복사(select insert) - dspCnrTmplatInfoCnnc
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void insertDspCnrTmplatInfoCnncRev(DspCnrConttScBoDTO dto)  {
		getSession1().insert("com.plgrim.ncp.biz.display.insertDspCnrTmplatInfoCnncRev", dto);
	}
	
	/**
	 * 리비전 복사(select insert) - dspRevCpst
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void insertDspRevCpstRev(DspCnrConttScBoDTO dto)  {
		getSession1().insert("com.plgrim.ncp.biz.display.insertDspRevCpstRev", dto);
	}
	
	/**
	 * 리비전 복사(select insert) - dspCnrTpGrp
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void insertDspCnrTpGrpRev(DspCnrConttScBoDTO dto)  {
		getSession1().insert("com.plgrim.ncp.biz.display.insertDspCnrTpGrpRev", dto);
	}
	
	/**
	 * 리비전 복사(select insert) - dspCnrSet
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void insertDspCnrSetRev(DspCnrConttScBoDTO dto)  {
		getSession1().insert("com.plgrim.ncp.biz.display.insertDspCnrSetRev", dto);
	}
	
	/**
	 * 리비전 복사(select insert) - dspCnrSetLang
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void insertDspCnrSetLangRev(DspCnrConttScBoDTO dto)  {
		getSession1().insert("com.plgrim.ncp.biz.display.insertDspCnrSetLangRev", dto);
	}
	
	/**
	 * 리비전 복사(select insert) - dspCnrContt
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void insertDspCnrConttRev(DspCnrConttScBoDTO dto)  {
		getSession1().insert("com.plgrim.ncp.biz.display.insertDspCnrConttRev", dto);
	}
	
	/**
	 * 리비전 복사(select insert) - dspCnrConttLang
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void insertDspCnrConttLangRev(DspCnrConttScBoDTO dto)  {
		getSession1().insert("com.plgrim.ncp.biz.display.insertDspCnrConttLangRev", dto);
	}
	
	/**
	 * 리비전 복사(select insert) - dspCnrConttDspTgt
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void insertDspCnrConttDspTgtRev(DspCnrConttScBoDTO dto)  {
		getSession1().insert("com.plgrim.ncp.biz.display.insertDspCnrConttDspTgtRev", dto);
	}
	
	/* 삭제 */
	/**
	 * 리비전 삭제 - dspCnr 
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void deleteDspCnrRev(DspCnrConttScBoDTO dto)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrRev", dto);
	}
	
	/**
	 * 리비전 삭제 - dspCnrTmplatInfoCnnc
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void deleteDspCnrTmplatInfoCnncRev(DspCnrConttScBoDTO dto)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrTmplatInfoCnncRev", dto);
	}
	
	/**
	 * 리비전 삭제 - dspRevCpst
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void deleteDspRevCpstRev(DspCnrConttScBoDTO dto)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspRevCpstRev", dto);
	}
	/**
	 * 리비전 삭제 - dspCnrTpGrp
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void deleteDspCnrTpGrpRev(DspCnrConttScBoDTO dto)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrTpGrpRev", dto);
	}
	
	/**
	 * 리비전 삭제 - dspCnrSet
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void deleteDspCnrSetRev(DspCnrConttScBoDTO dto)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrSetRev", dto);
	}
	
	/**
	 * 리비전 삭제 - dspCnrSetLang
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void deleteDspCnrSetLangRev(DspCnrConttScBoDTO dto)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrSetLangRev", dto);
	}
	
	/**
	 * 리비전 삭제 - dspCnrContt
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void deleteDspCnrConttRev(DspCnrConttScBoDTO dto)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrConttRev", dto);
	}
	
	/**
	 * 리비전 삭제 - dspCnrConttLang
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void deleteDspCnrConttLangRev(DspCnrConttScBoDTO dto)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrConttLangRev", dto);
	}
	
	/**
	 * 리비전 삭제 - dspCnrConttDspTgt
	 *
	 * @param dto the dto
	 * @ the exception
	 */
	public void deleteDspCnrConttDspTgtRev(DspCnrConttScBoDTO dto)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrConttDspTgtRev", dto);
	}
	
	
	/**
	 * 전시개정구성 
	 *
	 * @param dspRevCpst the dsp rev cpst
	 * @ the exception
	 */
	public void insertDspRevCpstOne(DspRevCpst dspRevCpst)  {
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("REV_SN", 1);
		
		
		Integer revCpstTurn = getIdGenService().generateDBOrder(getSession1(), "DSP_REV_CPST", "REV_CPST_TURN", conditions, DatabaseType.ORACLE);
		
		dspRevCpst.setRevCpstTurn(revCpstTurn);
		dspRevCpstRepository.insertDspRevCpst(dspRevCpst);
	}
	
	/**
	 * Insert dsp rev cpst info.
	 *
	 * @param dspRevCpst the dsp rev cpst
	 * @ the exception
	 */
	public void insertDspRevCpstInfo(DspRevCpst dspRevCpst)  {
		//dspRevCpstRepository.insertDspRevCpst(dspRevCpst);
	}
	
	/**
	 * 전시개정구성 템플릿 수정
	 *
	 * @param dspRevCpst the dsp rev cpst
	 * @ the exception
	 */
	public void updateDspRevCpstOne(DspRevCpst dspRevCpst)  {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspRevCpstOne", dspRevCpst);
	}
	
	/**
	 * 전시개정구성 수정
	 *
	 * @param dspRevCpst the dsp rev cpst
	 * @ the exception
	 */
	public void updateDspRevCpstInfo(DspRevCpst dspRevCpst)  {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspRevCpstInfo", dspRevCpst);
	}
	
	
	/**
	 * A/B 테스트 등록
	 *
	 * @param dspAbTest the dsp ab test
	 * @return the long
	 * @ the exception
	 */
	public long insertDspAbTestOne(DspAbTest dspAbTest)  {
		long abTestSn = getIdGenService().generateDBSequence(getSession1(), "SQ_DSP_AB_TEST",  DatabaseType.ORACLE).longValue();
		
		dspAbTest.setAbTestSn(abTestSn);
		//dspAbTestRepository.insertDspAbTest(dspAbTest);
		
		return abTestSn;
	}
	
	/**
	 * A/B 테스트 세트 등록
	 *
	 * @param dspAbTestSet the dsp ab test set
	 * @return the integer
	 * @ the exception
	 */
	public Integer insertDspAbTestSetOne(DspAbTestSet dspAbTestSet)  {
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("AB_TEST_SN", dspAbTestSet.getAbTestSn());
		
		Integer setTurn = getIdGenService().generateDBOrder(getSession1(), "DSP_AB_TEST_SET", "SET_TURN", conditions, DatabaseType.ORACLE);
		
		dspAbTestSet.setSetTurn(setTurn);
		//dspAbTestSetRepository.insertDspAbTestSet(dspAbTestSet);
		
		return setTurn;
	}
	
	
	/**
	 * Delete dsp ab test set flter info.
	 *
	 * @param dspAbTestSetFlter the dsp ab test set flter
	 * @ the exception
	 */
	public void deleteDspAbTestSetFlterInfo(DspAbTestSetFlter dspAbTestSetFlter)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspAbTestSetFlterInfo", dspAbTestSetFlter);
	}
	
	/**
	 * Update dsp ab test set mod info.
	 *
	 * @param dspAbTestSetMod the dsp ab test set mod
	 * @ the exception
	 */
	public void updateDspAbTestSetModInfo(DspAbTestSetMod dspAbTestSetMod)  {
		getSession1().delete("com.plgrim.ncp.biz.display.updateDspAbTestSetModInfo", dspAbTestSetMod);
	}
	
	/**
	 * A/B 테스트 시작일자 수정
	 *
	 * @param dspAbTest the dsp ab test
	 */
	public void updateAbTestModBegDt(DspAbTest dspAbTest)  {
		getSession1().delete("com.plgrim.ncp.biz.display.updateAbTestModBegDt", dspAbTest);
	}
	
	/**
	 * A/B 테스트 종료일자 수정
	 *
	 * @param dspAbTest the dsp ab test
	 */
	public void updateAbTestModEndDt(DspAbTest dspAbTest)  {
		getSession1().delete("com.plgrim.ncp.biz.display.updateAbTestModEndDt", dspAbTest);
	}
	
	/**
	 * Delete dsp ab test set flter info.
	 *
	 * @param dspAbTestSetMod the dsp ab test set mod
	 * @ the exception
	 */
	public void deleteDspAbTestSetModInfo(DspAbTestSetMod dspAbTestSetMod)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspAbTestSetModInfo", dspAbTestSetMod);
	}
	
	/**
	 * Delete dsp ab test set info.
	 *
	 * @param dspAbTestSet the dsp ab test set
	 * @ the exception
	 */
	public void deleteDspAbTestSetInfo(DspAbTestSet dspAbTestSet)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspAbTestSetInfo", dspAbTestSet);
	}
	
	/**
	 * Delete dsp ab test rev info.
	 *
	 * @param dspAbTestRev the dsp ab test rev
	 * @ the exception
	 */
	public void deleteDspAbTestRevInfo(DspAbTestRev dspAbTestRev)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspAbTestRevInfo", dspAbTestRev);
	}
	
	/**
	 * Update dsp ab test rev info.
	 *
	 * @param dspAbTestRev the dsp ab test rev
	 * @return the int
	 * @ the exception
	 */
	public int updateDspAbTestRevInfo(DspAbTestRev dspAbTestRev)  {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspAbTestRevInfo", dspAbTestRev);
	}
	
	/**
	 * Update dsp ab test rev expsr rt.
	 *
	 * @param dspAbTestRev the dsp ab test rev
	 * @return the int
	 * @ the exception
	 */
	public int updateDspAbTestRevExpsrRt(DspAbTestRev dspAbTestRev)  {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspAbTestRevExpsrRt", dspAbTestRev);
	}
	
	/**
	 * Update dsp ab test set expsr rt.
	 *
	 * @param dspAbTestSet the dsp ab test set
	 * @return the int
	 * @ the exception
	 */
	public int updateDspAbTestSetExpsrRt(DspAbTestSet dspAbTestSet)  {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspAbTestSetExpsrRt", dspAbTestSet);
	}
	
	/**
	 * Insert dsp cnr ab test rev.
	 *
	 * @param dspCnr the dsp cnr
	 * @return the int
	 * @ the exception
	 */
	public int insertDspCnrAbTestRev(DspCnr dspCnr)  {
		return getSession1().insert("com.plgrim.ncp.biz.display.insertDspCnrAbTestRev", dspCnr);
	}
	
	/**
	 * Update dsp ab test info.
	 *
	 * @param dspAbTest the dsp ab test
	 * @return the int
	 * @ the exception
	 */
	public int updateDspAbTestInfo(DspAbTest dspAbTest)  {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspAbTestInfo", dspAbTest);
	}
	
	
	
	/**
	 * Update dsp ab test set flter info.
	 *
	 * @param dspAbTestSetFlter the dsp ab test set flter
	 * @return the int
	 * @ the exception
	 */
	public int updateDspAbTestSetFlterInfo(DspAbTestSetFlter dspAbTestSetFlter)  {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspAbTestSetFlterInfo", dspAbTestSetFlter);
	}
	
	/**
	 * Update dsp rev.
	 *
	 * @param dspRev the dsp rev
	 * @ the exception
	 */
	public void updateDspRev(DspRev dspRev)  {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspRev", dspRev);
	}

	public void updateBaseRevUseYn(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		getSession1().update("com.plgrim.ncp.biz.display.updateBaseRevUseYn", dspCnrConttScBoDTO);
	}

	public void updateDspRevSectCd(DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspRevSectCd",dspCnrConttScBoDTO);
	}

	public void updateBaseRevTmplatSn(DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		getSession1().update("com.plgrim.ncp.biz.display.updateBaseRevTmplatSn", dspCnrConttScBoDTO);
	}
}
	
