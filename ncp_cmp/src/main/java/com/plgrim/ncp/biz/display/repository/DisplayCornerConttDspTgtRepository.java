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
 * @since       2015. 5. 15       
 */
package com.plgrim.ncp.biz.display.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttDspTgt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttLang;
import com.plgrim.ncp.base.repository.dsp.DspCnrConttDspTgtRepository;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DisplayCornerConttDspTgtRepository extends DspCnrConttDspTgtRepository {

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
	 * 컨텐츠 정보 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrContt [설명]
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 15
	 */
	public Integer insertDspCnrConttDspTgtInfo(DspCnrConttDspTgt dspCnrConttDspTgt) throws Exception {
		//코너컨텐츠 등록
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("REV_SN", dspCnrConttDspTgt.getRevSn());
		conditions.put("CNR_SN", dspCnrConttDspTgt.getCnrSn());
		conditions.put("CNR_SET_SN", dspCnrConttDspTgt.getCnrSetSn());
		conditions.put("CONTT_TURN", dspCnrConttDspTgt.getConttTurn());
		
		
		Integer dspTgtTurn = getIdGenService().generateDBOrder(getSession1(), "DSP_CNR_CONTT_DSP_TGT", "DSP_TGT_TURN", conditions, DatabaseType.ORACLE);
		
		dspCnrConttDspTgt.setDspTgtTurn(dspTgtTurn);
		insertDspCnrConttDspTgt(dspCnrConttDspTgt);

		return dspTgtTurn;
	}
	
	/**
	 * 컨텐츠 전시대상 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttDspTgt [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 15
	 */
	public int updateDspCnrConttDspTgrInfo(DspCnrConttDspTgt dspCnrConttDspTgt) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspCnrConttDspTgrInfo", dspCnrConttDspTgt);
	}
	
	
	/**
	 * 컨텐츠 전시대상 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrSetLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 15
	 */
	public int deleteDspCnrConttDspTgtInfo(DspCnrConttDspTgt dspCnrConttDspTgt) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrConttDspTgtInfo", dspCnrConttDspTgt);
	}
	
	
	/**
	 * 코너 컨텐츠 전시대상 삭제 - 유형별
	 *
	 * @param dspCnrConttDspTgt the dsp cnr contt dsp tgt
	 * @return the int
	 * @throws Exception the exception
	 */
	public int deleteDspCnrConttDspTgtTp(DspCnrConttDspTgt dspCnrConttDspTgt) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrConttDspTgtTp", dspCnrConttDspTgt);
	}
	
	/**
	 * 다국어 체크를 위한 컨텐츠목록.
	 *
	 * @param dspCnrConttDspTgt the dsp cnr contt dsp tgt
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCnrConttLang> selectValidConttForLang(DspCnrConttDspTgt dspCnrConttDspTgt) {
		List<DspCnrConttLang> result = getSession1().selectList(
	    		"com.plgrim.ncp.biz.display.selectValidConttForLang", dspCnrConttDspTgt);
	    return result;
    }
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
