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
 * @since       2015. 7. 8       
 */
package com.plgrim.ncp.biz.display.repository;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSetLang;
import com.plgrim.ncp.base.repository.dsp.DspCnrSetRepository;
import com.plgrim.ncp.framework.enums.DatabaseType;

import lombok.extern.slf4j.Slf4j;


/**
 * 코너관리를 DB처리하는 Repository
 * 
 * <p>
 * 
 * <ul>
 *   <li> 코너목록 조회
 *   <li> 코너등록/수정/삭제
 *   <li> 코너 컨텐츠 유형 그룹 등록/수정/삭제
 * </ul>.
 *
 * @author shsunhee.kim
 * @since 2015. 3. 13
 */
@Repository
@Slf4j
public class DisplayCornerSetRepository extends DspCnrSetRepository {

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
	 * 코너 세트 정보 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrSet [설명]
	 * @return Long 생성된 코너 일련번호
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public long insertCornerSetInfo(DspCnrSet dspCnrSet) throws Exception {
		//코너 일련번호 SEQ생성
		long cnrSetSn = getIdGenService().generateDBSequence(getSession1(), "SQ_DSP_CNR_SET",
                DatabaseType.ORACLE).longValue();
		
		dspCnrSet.setCnrSetSn(cnrSetSn);
		insertDspCnrSet(dspCnrSet);

		return cnrSetSn;
	}
	
	/**
	 * 코너세트정보 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCnr 코너 정보 Entity
	 * @return Int 처리결과수
	 * @throws Exception the exception
	 * @since 2015. 4. 7
	 */
	public int updateDspCnrSetInfo(DspCnrSet dspCnrSet) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspCnrSetInfo", dspCnrSet);
	}
	
	
	/**
	 * 코너 세트명 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCnrSetLang [설명]
	 * @return Int 처리결과수
	 * @throws Exception the exception
	 * @since 2015. 4. 7
	 */
	public int updateDspCnrSetLangNm(DspCnrSetLang dspCnrSetLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspCnrSetLangNm", dspCnrSetLang);
	}
	

	/**
	 * 코너 세트설명 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCnrSetLang [설명]
	 * @return Int 처리결과수
	 * @throws Exception the exception
	 * @since 2015. 4. 7
	 */
	public int updateDspCnrSetLangDscr(DspCnrSetLang dspCnrSetLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspCnrSetLangDscr", dspCnrSetLang);
	}
	
	/**
	 * 코너 세트(언어) 수정(merge).
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrSetLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 25
	 */
	public int saveDspCnrSetLangInfo(DspCnrSetLang dspCnrSetLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.saveDspCnrSetLangInfo", dspCnrSetLang);
	}
	
	
	/**
	 * 코너 세트(언어) 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrSetLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 13
	 */
	public int deleteDspCnrSetLangInfo(DspCnrSetLang dspCnrSetLang) {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrSetLangInfo", dspCnrSetLang);
	}
	
	/**
	 * 컨텐츠 수 체크.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrSetLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 8
	 */
	public int selectDspCnrConttCnt (DspCnrSet dspCnrSet)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCnrConttCnt", dspCnrSet);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	
}
