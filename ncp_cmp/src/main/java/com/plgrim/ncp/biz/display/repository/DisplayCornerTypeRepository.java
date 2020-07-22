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
 * @since       2015. 7. 9       
 */
package com.plgrim.ncp.biz.display.repository;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTpGrp;
import com.plgrim.ncp.base.repository.dsp.DspCnrTpGrpRepository;
import com.plgrim.ncp.framework.enums.DatabaseType;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DisplayCornerTypeRepository extends DspCnrTpGrpRepository {

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
	 * 전시 코너 유형 그룹 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dspCnrTpGrp [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public long insertCornerType(DspCnrTpGrp dspCnrTpGrp) throws Exception {
		//코너타입 일련번호 SEQ생성
		long cnrTpGrpSn = getIdGenService().generateDBSequence(getSession1(), "SQ_DSP_CNR_TP_GRP",
                DatabaseType.ORACLE).longValue();
		
		dspCnrTpGrp.setCnrTpGrpSn(cnrTpGrpSn);
		insertDspCnrTpGrp (dspCnrTpGrp);

		return cnrTpGrpSn;
	}
	
	
	public int deleteDspCnrTpGrpByUpper(DspCnrTpGrp dspCnrTpGrp)  {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrTpGrpByUpper", dspCnrTpGrp);
	}
	
	/**
	 * 코너컨텐츠유형그룹을 사용중인 세트 수 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrTpGrp [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 9
	 */
	public int selectUseCnrTpCnt(DspCnrTpGrp dspCnrTpGrp)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectUseCnrTpCnt", dspCnrTpGrp);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
