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
package com.plgrim.ncp.biz.display.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTmplatInfoCnnc;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspTmplat;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.base.repository.dsp.DspCnrTmplatInfoCnncRepository;
import com.plgrim.ncp.biz.display.data.DspCnrTmplatInfoBoDTO;
import com.plgrim.ncp.biz.display.data.DspTmplatBoDTO;
import com.plgrim.ncp.biz.display.exception.NotDeletedCnrTmplatException;
import com.plgrim.ncp.biz.display.repository.DisplayTemplateRepository;
import com.plgrim.ncp.biz.display.result.DspCnrTmplatInfoCnncResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;

/**
 * 템플릿 관리 service class
 * 
 * <p>
 * 
 * <ul>
 *   <li> 템플릿정보 등록/수정/삭제
 *   <li> 템플릿연결 코너 등록/수정/삭제
 * </ul>.
 *
 * @author tktaeki.kim
 * @since 2015. 3. 30
 */
@Service
public class DisplayTemplateService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** The display template repository. */
	@Autowired
	DisplayTemplateRepository displayTemplateRepository;
	
	/** The display template corner repository. */
	@Autowired
	DspCnrTmplatInfoCnncRepository dspCnrTmplatInfoCnncRepository;

	@Autowired
	DisplayRevCommandService displayRevCommandService;


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
	 * 템플릿 등록.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspTmplatDTO 템플릿 관리를 위한 DTO
	 * @return Big integer 등록한 템플릿 일련번호
	 * @throws Exception the exception
	 * @since 2015. 3. 19
	 */
	public long insertTemplate (DspTmplatBoDTO dspTmplatDTO) throws Exception {		

		return displayTemplateRepository.insertDspTmplatInfo(dspTmplatDTO.getDspTmplat());
	}
	
	
	
	/**
	 * 템플릿 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspTmplatDTO [설명]
	 * @return Int
	 * @throws Exception the exception
	 * @since 2015. 3. 19
	 */
	public int updateTemplate (DspTmplatBoDTO dspTmplatDTO) throws Exception {
		return displayTemplateRepository.updateDspTmplat(dspTmplatDTO.getDspTmplat());
	}
	
	/**
	 * 템플릿 삭제.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspTmplatDTO [설명]
	 * @return Int
	 * @throws Exception the exception
	 * @since 2015. 3. 19
	 */
	public int deleteTemplate (DspTmplatBoDTO dspTmplatDTO) throws Exception {
		return displayTemplateRepository.deleteDspTmplat(dspTmplatDTO.getDspTmplat());
	}
	
	/**
	 * 템플릿 목록 삭제.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list DspTmplatBoDTO 목록
	 * @return Int 
	 * @throws Exception the exception
	 * @since 2015. 4. 9
	 */
	public int deleteTemplateList (List<DspTmplatBoDTO> list) {
		
		int res = 0;
		
		DspTmplat dspTmplat = null;
		
		for(DspTmplatBoDTO dspTmplatBoDTO: list) {
			dspTmplat = dspTmplatBoDTO.getDspTmplat();
			
			int cnt = displayTemplateRepository.selectCnrTmplatCnt(dspTmplat);
			if(cnt > 0) {
				throw new NotDeletedCnrTmplatException(null);
			}
			res = displayTemplateRepository.deleteDspTmplat(dspTmplat);
		}
		
		return res;
	}
	
	
	/**
	 * 템플릿 연결 코너등록.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param addList 등록할 목록
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 19
	 */
	public int insertTemplateCorner (Long tmplatSn, List<DspCnrTmplatInfoBoDTO> addList) throws Exception {
		int res = 0;
		
		if (addList != null) {
			DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc = null;
			
			for(DspCnrTmplatInfoBoDTO dspCnrTmplatInfoDTO: addList) {
				dspCnrTmplatInfoCnnc = dspCnrTmplatInfoDTO.getDspCnrTmplatInfoCnnc();
				dspCnrTmplatInfoCnnc.setTmplatSn(tmplatSn);
				dspCnrTmplatInfoCnncRepository.insertDspCnrTmplatInfoCnnc(dspCnrTmplatInfoCnnc);
			}
		}
		
		return res;
	}
	
	/**
	 * 템플릿 연결 코너 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param updList 수정할 목록
	 * @return Int
	 * @throws Exception the exception
	 * @since 2015. 3. 19
	 */
	public int updateTemplateCorner (Long tmplatSn, List<DspCnrTmplatInfoBoDTO> updList) throws Exception {
		int res = 0;
		
		if (updList != null) {
			DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc = null;
			
			for(DspCnrTmplatInfoBoDTO dspCnrTmplatInfoDTO: updList) {
				dspCnrTmplatInfoCnnc = dspCnrTmplatInfoDTO.getDspCnrTmplatInfoCnnc();
				dspCnrTmplatInfoCnnc.setTmplatSn(tmplatSn);
				res = dspCnrTmplatInfoCnncRepository.updateDspCnrTmplatInfoCnnc(dspCnrTmplatInfoCnnc);
			}
		}
		
		return res;
	}
	
	/**
	 * 템플릿연결 코너 등록 삭제.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list 삭제할 템플릿연결 코너목록
	 * @return Int
	 * @throws Exception the exception
	 * @since 2015. 3. 19
	 */
	public int deleteTemplateCorner (Long tmplatSn, List<DspCnrTmplatInfoBoDTO> list) throws Exception {
		
		int res = 0;
		
		DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc = null;
		
		for(DspCnrTmplatInfoBoDTO dspCnrTmplatInfoDTO: list) {
			dspCnrTmplatInfoCnnc = dspCnrTmplatInfoDTO.getDspCnrTmplatInfoCnnc();
			dspCnrTmplatInfoCnnc.setTmplatSn(tmplatSn);
			res = displayTemplateRepository.deleteTmplatInfoCnnc(dspCnrTmplatInfoCnnc);
		}
		
		return res;
	}
	
	/**
	 * 템플릿 연결 코너등록.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param addList 등록할 목록
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 19
	 */
	public int insertTemplateCorner (List<DspCnrTmplatInfoBoDTO> addList) throws Exception {
		int res = 0;
		
		String loginId = BOSecurityUtil.getLoginId();
		
		if (addList != null) {
			DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc = null;
			
			for(DspCnrTmplatInfoBoDTO dspCnrTmplatInfoDTO: addList) {
				dspCnrTmplatInfoCnnc = dspCnrTmplatInfoDTO.getDspCnrTmplatInfoCnnc();
				if(dspCnrTmplatInfoCnnc.getDspCnrTmplatTurn() == null || dspCnrTmplatInfoCnnc.getDspCnrTmplatTurn() == 0) {
					dspCnrTmplatInfoCnnc.setDspCnrTmplatTurn(1);
				}
				dspCnrTmplatInfoCnnc.setRegtrId(loginId);
				dspCnrTmplatInfoCnnc.setUdterId(loginId);
				dspCnrTmplatInfoCnncRepository.insertDspCnrTmplatInfoCnnc(dspCnrTmplatInfoCnnc);
			}
		}
		
		return res;
	}
	
	/**
	 * 템플릿 연결 코너 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param updList 수정할 목록
	 * @return Int
	 * @throws Exception the exception
	 * @since 2015. 3. 19
	 */
	public int updateTemplateCorner (List<DspCnrTmplatInfoBoDTO> updList) throws Exception {
		int res = 0;
		
		if (updList != null) {
			String loginId = BOSecurityUtil.getLoginId();
			
			DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc = null;
			
			for(DspCnrTmplatInfoBoDTO dspCnrTmplatInfoDTO: updList) {
				dspCnrTmplatInfoCnnc = dspCnrTmplatInfoDTO.getDspCnrTmplatInfoCnnc();
				dspCnrTmplatInfoCnnc.setUdterId(loginId);
				res = dspCnrTmplatInfoCnncRepository.updateDspCnrTmplatInfoCnnc(dspCnrTmplatInfoCnnc);
			}
		}
		
		return res;
	}
	
	
	/**
	 * 템플릿 연결 시 
	 *
	 * @param key String
	 * @param key2 String
	 * @param tmplatTp String
	 * @param tmplatSn Long
	 * @param revSn Long
	 * @throws Exception the exception
	 */
	public void saveCnrTmplatInfoCnnc(String key, String key2, String tmplatTp, Long tmplatSn, Long revSn) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		
		//선택한 템플릿 번호의 코너템플릿정보연결목록 조회
		DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc = new DspCnrTmplatInfoCnnc();
		dspCnrTmplatInfoCnnc.setRevSn(1L);
		dspCnrTmplatInfoCnnc.setTmplatSn(tmplatSn);
		List<DspCnrTmplatInfoCnncResult> list = displayTemplateRepository.selectCnrTmplatInfoCnncList(dspCnrTmplatInfoCnnc);
		
		String cnncKey = "";
		
		for (DspCnrTmplatInfoCnncResult result: list) {
			DspCnrTmplatInfoCnnc cnnc = result.getDspCnrTmplatInfoCnnc();
			cnnc.setRevSn(revSn);

			cnncKey = StringService.nvl(cnnc.getDspCtgryNo(),"") 
					+ StringService.nvl(cnnc.getDspBrndCtgryNo(),"") + StringService.nvl(cnnc.getDspBrndId(),"")
					+ (cnnc.getPromtSn()==null?"":cnnc.getPromtSn()) + StringService.nvl(cnnc.getEvtNo(),"") 
					+ (cnnc.getStrndSn()==null?"":cnnc.getStrndSn());
			
			if(tmplatTp != null
					&& (tmplatTp.equals(DisplayEnum.TMPLAT_TP.DSP_CTGRY.toString()) 
							|| tmplatTp.equals(DisplayEnum.TMPLAT_TP.THEMA_PGE.toString()) 
							|| tmplatTp.equals(DisplayEnum.TMPLAT_TP.BRND.toString()))) {
				if(StringService.isNotEmpty(key2)) {
					cnnc.setDspBrndCtgryNo(key);
					cnnc.setDspBrndId(key2);
					
					cnnc.setDspCtgryNo(null);
				}
				else {
					cnnc.setDspCtgryNo(key);
					
					cnnc.setDspBrndCtgryNo(null);
					cnnc.setDspBrndId(null);
				}
				cnnc.setPromtSn(null);
				cnnc.setStrndSn(null);
				cnnc.setEvtNo(null);
			}
			else if(tmplatTp != null && tmplatTp.equals(DisplayEnum.TMPLAT_TP.PROMT.toString())) {
				cnnc.setPromtSn(Long.parseLong(key));
				
				cnnc.setDspCtgryNo(null);
				cnnc.setDspBrndCtgryNo(null);
				cnnc.setDspBrndId(null);
				cnnc.setStrndSn(null);
				cnnc.setEvtNo(null);
			}
			else if(tmplatTp != null && tmplatTp.equals(DisplayEnum.TMPLAT_TP.STRND.toString())) {
				cnnc.setStrndSn(Long.parseLong(key));
				
				cnnc.setDspCtgryNo(null);
				cnnc.setDspBrndCtgryNo(null);
				cnnc.setDspBrndId(null);
				cnnc.setPromtSn(null);
				cnnc.setEvtNo(null);
			}
			else if(tmplatTp != null && tmplatTp.equals(DisplayEnum.TMPLAT_TP.EVT.toString())) {
				cnnc.setEvtNo(key);
				
				cnnc.setDspCtgryNo(null);
				cnnc.setDspBrndCtgryNo(null);
				cnnc.setDspBrndId(null);
				cnnc.setPromtSn(null);
				cnnc.setStrndSn(null);
			}
			
			
			DspCnrTmplatInfoCnncResult one = displayTemplateRepository.selectCnrTmplatInfoCnncOne(cnnc);
			if(one == null || one.getDspCnrTmplatInfoCnnc() == null) {
				if(tmplatTp != null && tmplatTp.equals(DisplayEnum.TMPLAT_TP.PROMT.toString())) {
					cnnc.setDspCnrUseYn("N");
				}
				if(result.getCnt() == 1 && StringService.isEmpty(cnncKey)) {
					//update
					cnnc.setUdterId(loginId);
					dspCnrTmplatInfoCnncRepository.updateDspCnrTmplatInfoCnnc(cnnc);
				}
				else {
					//insert
					cnnc.setRegtrId(loginId);
					cnnc.setUdterId(loginId);
					cnnc.setDspCnrTmplatTurn(cnnc.getDspCnrTmplatTurn() + 1);
					dspCnrTmplatInfoCnncRepository.insertDspCnrTmplatInfoCnnc(cnnc);
				}
			}
			
		}
		//loop st  ->조회한 코너템플릿정보를 해당 카테고리번호로 저장
		//해당 카테고리번호로 템플릿+코너 조회 1)
		// cnt == 1 : 1)이 있으면 X, 없으면 update 
		// cnt > 1  : 1)이 있으면 X, 없으면 insert 
		//end loop

	}
	
	/**
	 * Select cnr tmplat info cnnc one.
	 *
	 * @param dspCnrTmplatInfoCnnc the dsp cnr tmplat info cnnc
	 * @return the dsp cnr tmplat info cnnc result
	 */
	public DspCnrTmplatInfoCnncResult selectCnrTmplatInfoCnncOne(DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc) {
		return displayTemplateRepository.selectCnrTmplatInfoCnncOne(dspCnrTmplatInfoCnnc);
	}
	
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
