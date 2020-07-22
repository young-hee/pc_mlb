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
 * @since       2015. 7. 27       
 */
package com.plgrim.ncp.biz.display.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTmplatInfoCnnc;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspTmplat;
import com.plgrim.ncp.base.repository.dsp.DspTmplatRepository;
import com.plgrim.ncp.biz.display.result.DspCnrTmplatInfoCnncResult;
import com.plgrim.ncp.framework.enums.DatabaseType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DisplayTemplateRepository extends DspTmplatRepository  {

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
	 * 전시 템플릿 등록 확장.
	 *
	 * @param dspTmplat the DspTmplat
	 * @return Big integer 등록한 템플릿일련번호
	 * @throws Exception the exception
	 * @since 2015. 3. 24
	 */
	public long insertDspTmplatInfo (DspTmplat dspTmplat) throws Exception {
		//템플릿 일련번호 SEQ생성
		long tmplatSn = getIdGenService().generateDBSequence(getSession1(), "SQ_DSP_TMPLAT",
                DatabaseType.ORACLE).longValue();
		
		dspTmplat.setTmplatSn(tmplatSn);
		insertDspTmplat(dspTmplat);
		
		return tmplatSn;
	}
	
	/**
	 * 템플릿 연결 코너 수 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspTmplat [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 27
	 */
	public int selectCnrTmplatCnt (DspTmplat dspTmplat) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectCnrTmplatCnt", dspTmplat);
	}
	
	/**
	 * 해당템플릿에 연결된 코너정보 목록 조회
	 *
	 * @param dspCnrTmplatInfoCnnc the dsp cnr tmplat info cnnc
	 * @return the list
	 */
	public List<DspCnrTmplatInfoCnncResult> selectCnrTmplatInfoCnncList(DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectCnrTmplatInfoCnncList", dspCnrTmplatInfoCnnc);
	}
	
	/**
	 * 해당 카테고리(기획전,Strend,Evt)의 템플릿 코너 연결정보 조회
	 *
	 * @param dspCnrTmplatInfoCnnc the dsp cnr tmplat info cnnc
	 * @return the dsp cnr tmplat info cnnc result
	 */
	public DspCnrTmplatInfoCnncResult selectCnrTmplatInfoCnncOne(DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectCnrTmplatInfoCnncOne", dspCnrTmplatInfoCnnc);
	}
	
	/**
	 * 템플릿 코너 연결정보 삭제
	 *
	 * @param dspCnrTmplatInfoCnnc the dsp cnr tmplat info cnnc
	 * @return the int
	 * @throws Exception the exception
	 */
	public int deleteTmplatInfoCnnc(DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteTmplatInfoCnnc", dspCnrTmplatInfoCnnc);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
