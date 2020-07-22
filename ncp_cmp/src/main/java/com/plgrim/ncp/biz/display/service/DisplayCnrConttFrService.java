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
 * @since       2015. 11. 23       
 */
package com.plgrim.ncp.biz.display.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSetLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrnd;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndLang;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyTagResve;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResve;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.biz.display.data.DspCnrScFrDTO;
import com.plgrim.ncp.biz.display.repository.DisplayCnrConttFrRepository;
import com.plgrim.ncp.biz.display.result.DspCnrConttFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrSetFrResult;
import com.plgrim.ncp.biz.display.result.DspTmplatFrResult;
import com.plgrim.ncp.commons.data.FormSysBrndDTO;
import com.plgrim.ncp.commons.repository.SysBrandRepository;
import com.plgrim.ncp.framework.commons.StringService;

@Service
public class DisplayCnrConttFrService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DisplayCnrConttFrRepository displayCnrConttFrRepository;
	
	@Autowired
	SysBrandRepository sysBrandRepository;

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
	 * 코너 컨텐츠 목록 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dspCnrScFrDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 13
	 */
	public List<DspCnrFrResult> selectDspCnrList(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		List<DspCnrFrResult> cnrList = null;
		List<DspTmplatFrResult> tmplatCnrList = null;
		
		//컨텐츠 Full Data 조회
		cnrList = displayCnrConttFrRepository.selectDspCnrConttList(dspCnrScFrDTO);
		
		//템플릿 정보 및 코너목록
		tmplatCnrList = dspCnrScFrDTO.getTmplatCnrList();
		
		//컨텐츠 유형별 컨텐츠목록 분리
		getDspCnrConttListByConttTp(dspCnrScFrDTO.getLang(), cnrList, tmplatCnrList);
		
		
		return cnrList;		
	}
	
	/**
	 * 컨텐츠 유형별 컨텐츠목록 분리.
	 *  . set,컨텐츠 언어적용 (세트명,컨텐츠명,이미지등 접속언어에 해당하는 데이터 적용. 접속언어에 해당하는 데이터가 없을 경우 DF언어(KOR) 적용)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param cnrList [설명]
	 * @param tmplatCnrList [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 13
	 */
	public void getDspCnrConttListByConttTp(String pkLang, List<DspCnrFrResult> cnrList, List<DspTmplatFrResult> tmplatCnrList) throws Exception {
		String lang = pkLang;
		
		for(DspCnrFrResult dspCnrfrResult: cnrList) {
			
			List<DspCnrSetFrResult> setList = dspCnrfrResult.getDspCnrSetList();
			for(DspCnrSetFrResult dspCnrSetFrResult: setList) {
				//코너세트 언어적용
				if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
					changeDspCnrLnag(dspCnrSetFrResult.getDspCnrSet(),dspCnrSetFrResult.getDspCnrSetLang());
				}
				
				List<DspCnrConttFrResult> conttList = dspCnrSetFrResult.getDspCnrConttList();
				for(DspCnrConttFrResult dspCnrConttFrResult: conttList) {
					DspCnrContt contt = dspCnrConttFrResult.getDspCnrContt();
					String conttTpCd = contt.getConttTpCd(); 
					if(DisplayEnum.CONTT_TP.HTML.toString().equals(conttTpCd)) {
						if(dspCnrSetFrResult.getDspCnrConttHtmlList() == null) {
							dspCnrSetFrResult.setDspCnrConttHtmlList(new ArrayList<DspCnrConttFrResult>());
						}
						dspCnrSetFrResult.getDspCnrConttHtmlList().add(dspCnrConttFrResult);
					} 
					else if(DisplayEnum.CONTT_TP.IMG_BANNER.toString().equals(conttTpCd)) {
						if(dspCnrSetFrResult.getDspCnrConttImgList() == null) {
							dspCnrSetFrResult.setDspCnrConttImgList(new ArrayList<DspCnrConttFrResult>());
						}
						
						//컨텐츠 언어적용
						if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
							changeDspCnrConttLang(dspCnrConttFrResult.getDspCnrContt(), dspCnrConttFrResult.getDspCnrConttLang());
						}
						dspCnrSetFrResult.getDspCnrConttImgList().add(dspCnrConttFrResult);
					}
					else if(DisplayEnum.CONTT_TP.MOVI.toString().equals(conttTpCd)) {
						if(dspCnrSetFrResult.getDspCnrConttMoviList() == null) {
							dspCnrSetFrResult.setDspCnrConttMoviList(new ArrayList<DspCnrConttFrResult>());
						}
						
						//컨텐츠 언어적용
						if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
							changeDspCnrConttLang(dspCnrConttFrResult.getDspCnrContt(), dspCnrConttFrResult.getDspCnrConttLang());
						}
						dspCnrSetFrResult.getDspCnrConttMoviList().add(dspCnrConttFrResult);
					}
					else if(DisplayEnum.CONTT_TP.TEXT.toString().equals(conttTpCd)) {
						if(dspCnrSetFrResult.getDspCnrConttTextList() == null) {
							dspCnrSetFrResult.setDspCnrConttTextList(new ArrayList<DspCnrConttFrResult>());
						}
						
						//컨텐츠 언어적용
						if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
							changeDspCnrConttLang(dspCnrConttFrResult.getDspCnrContt(), dspCnrConttFrResult.getDspCnrConttLang());
						}
						dspCnrSetFrResult.getDspCnrConttTextList().add(dspCnrConttFrResult);
					}
					// // 20160516_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 스티커 상품 처리]
					else if(DisplayEnum.CONTT_TP.GOD.toString().equals(conttTpCd) || DisplayEnum.CONTT_TP.STK_GOD.toString().equals(conttTpCd)) {
						if(dspCnrSetFrResult.getDspCnrConttGodList() == null) {
							dspCnrSetFrResult.setDspCnrConttGodList(new ArrayList<DspCnrConttFrResult>());
						}
						
						//컨텐츠상품 언어적용
						if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
							changeConttGodLang(dspCnrConttFrResult.getDspCnrGod().getGod(), dspCnrConttFrResult.getDspCnrGod().getGodLangbyGodNm());
						}
						
						//예약태그
						changeConttGodTagResveLang(dspCnrConttFrResult.getDspCnrGod().getGod(), 
								dspCnrConttFrResult.getDspCnrGod().getGodTagResve(), dspCnrConttFrResult.getDspCnrGod().getGodLangbyTagResve());
						
						dspCnrSetFrResult.getDspCnrConttGodList().add(dspCnrConttFrResult);
					}
					else if(DisplayEnum.CONTT_TP.PROMT.toString().equals(conttTpCd)) {
						if(dspCnrSetFrResult.getDspCnrConttPromtList() == null) {
							dspCnrSetFrResult.setDspCnrConttPromtList(new ArrayList<DspCnrConttFrResult>());
						}
						
						//컨텐츠기획전 언어적용
						if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
							changeDspPromtLang(dspCnrConttFrResult.getDspCnrPromt().getDspPromt(), dspCnrConttFrResult.getDspCnrPromt().getDspPromtLang());
						}
						dspCnrSetFrResult.getDspCnrConttPromtList().add(dspCnrConttFrResult);
					}
					else if(DisplayEnum.CONTT_TP.STRND.toString().equals(conttTpCd)) {
						if(dspCnrSetFrResult.getDspCnrConttStrndList() == null) {
							dspCnrSetFrResult.setDspCnrConttStrndList(new ArrayList<DspCnrConttFrResult>());
						}
						
						//컨텐츠S-Trnd 언어적용
						if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
							changeDspStrndLang(dspCnrConttFrResult.getDspCnrStrnd().getDspStrnd(), dspCnrConttFrResult.getDspCnrStrnd().getDspStrndLang());
						}
						dspCnrSetFrResult.getDspCnrConttStrndList().add(dspCnrConttFrResult);
					}
					
				}
			}
		}
		
		//set,contents 없는 코너셋팅
		if(tmplatCnrList != null) {
			for(int i=0; i<tmplatCnrList.size(); i++) {
				Long tCnrSn = tmplatCnrList.get(i).getDspCnrTmplatInfoCnnc().getCnrSn();
				Integer tSortSeq = tmplatCnrList.get(i).getDspCnrTmplatInfoCnnc().getSortSeq();
				Long cCnrSn = null;
				boolean bFlag = false;
				
				for(int j=0; j < cnrList.size(); j++) {
					cCnrSn = cnrList.get(j).getCnrSn();
					if(tCnrSn.equals(cCnrSn)) {
						bFlag = true;
						break;
					}
				}
						
				if(!bFlag) {
					DspCnrFrResult emptyCnr = new DspCnrFrResult();
					emptyCnr.setCnrSn(tCnrSn);
					cnrList.add(i,emptyCnr);
				}
			}
		}
	}
	
	
	/**
	 * 템플릿 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dspCnrScFrDTO [설명]
	 * @return Dsp tmplat fr result [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 15
	 */
	public List<DspTmplatFrResult> selectTmplatPageInfo(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		return displayCnrConttFrRepository.selectTmplatPageInfo(dspCnrScFrDTO);		
	}
	
	/**
	 * 해당 브랜드에 등록된 최신 S-Trnd조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 23
	 */
	public Long selectStrndSnForBrnd(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		return displayCnrConttFrRepository.selectStrndSnForBrnd(dspCnrScFrDTO);		
	}

	
	/**
	 * 카테고리 베스트 상품.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectCtgryBstGodList(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		return displayCnrConttFrRepository.selectCtgryBstGodList(dspCnrScFrDTO);
	}

	/**
	 * 카테고리 신상품.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectCtgryNewGodList(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		return displayCnrConttFrRepository.selectCtgryNewGodList(dspCnrScFrDTO);
	}
	
	/**
	 * 브랜드 시즌 베스트.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectBrndSesonBstGodList(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		return displayCnrConttFrRepository.selectBrndSesonBstGodList(dspCnrScFrDTO);
	}
	
	/**
	 * 브랜드 시즌 신상품.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectBrndSesonNewGodList(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		return displayCnrConttFrRepository.selectBrndSesonNewGodList(dspCnrScFrDTO);
	}
	
	/**
	 * 브랜드 베스트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectBrndBstGodList(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		return displayCnrConttFrRepository.selectBrndBstGodList(dspCnrScFrDTO);
	}
	
	/**
	 * 브랜드 신상품.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectBrndNewGodList(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		return displayCnrConttFrRepository.selectBrndNewGodList(dspCnrScFrDTO);
	}
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	// 2016/07 (UX/UI) 
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 코너 컨텐츠 목록 조회 V2.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dspCnrScFrDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 13
	 */
	public List<DspCnrFrResult> selectDspCnrListV2(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		List<DspCnrFrResult> cnrList = null;
		List<DspTmplatFrResult> tmplatCnrList = null;
		
		//브랜드검색필터존재할 경우 상위브랜드 체크
		String arBrndId[] = dspCnrScFrDTO.getFilterBrndCd();
		if(arBrndId != null) {
			ArrayList<String> brndList = new ArrayList<String>();
			boolean beaker = false;
			for(String brnd: arBrndId) {
				FormSysBrndDTO dto = new FormSysBrndDTO();
				dto.setSelBrndId(brnd);
				Integer cnt = sysBrandRepository.checkBeakerBrndCount(dto);
				if(cnt > 0) {
					if(beaker == false) {
						brndList.add("MCBR");
						beaker = true;
					}
				} else {
					brndList.add(brnd);
				}
			}
			dspCnrScFrDTO.setFilterBrndCd(brndList.toArray(new String[brndList.size()]));
		}
		
		//컨텐츠 Full Data 조회
		cnrList = displayCnrConttFrRepository.selectDspCnrConttListV2(dspCnrScFrDTO);
		
		//템플릿 정보 및 코너목록
		tmplatCnrList = dspCnrScFrDTO.getTmplatCnrList();
		
		//컨텐츠 유형별 컨텐츠목록 분리
		//getDspCnrConttListByConttTpV2(dspCnrScFrDTO.getLang(), cnrList, tmplatCnrList);
		
		List<DspCnrFrResult> cnrListV2 = getDspCnrConttListByConttTpV2(dspCnrScFrDTO.getLang(), cnrList, tmplatCnrList);
		
		
		return cnrListV2;		
	}
	
	/**
	 * 컨텐츠 유형별 컨텐츠목록 분리. V2
	 *  . set,컨텐츠 언어적용 (세트명,컨텐츠명,이미지등 접속언어에 해당하는 데이터 적용. 접속언어에 해당하는 데이터가 없을 경우 DF언어(KOR) 적용)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param cnrList [설명]
	 * @param tmplatCnrList [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 13
	 */
	public List<DspCnrFrResult> getDspCnrConttListByConttTpV2(String pkLang, List<DspCnrFrResult> cnrList, List<DspTmplatFrResult> tmplatCnrList) throws Exception {
		String lang = pkLang;
		List<DspCnrFrResult> cnrListV2 = new ArrayList<DspCnrFrResult>();
		
		for(DspCnrFrResult dspCnrfrResult: cnrList) {
			DspCnrFrResult dspCnrfrResultV2 = new DspCnrFrResult();
			BeanUtils.copyProperties(dspCnrfrResult, dspCnrfrResultV2);
			
			List<DspCnrSetFrResult> setList = dspCnrfrResult.getDspCnrSetList();
			List<DspCnrSetFrResult> setListV2 = new  ArrayList<DspCnrSetFrResult>();			
			
			if(setList != null) {
				for(DspCnrSetFrResult dspCnrSetFrResult: setList) {
					DspCnrSetFrResult dspCnrSetFrResultV2 = new DspCnrSetFrResult();
					BeanUtils.copyProperties(dspCnrSetFrResult, dspCnrSetFrResultV2);
					
					//코너세트 언어적용
					if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
						changeDspCnrLnag(dspCnrSetFrResultV2.getDspCnrSet(),dspCnrSetFrResultV2.getDspCnrSetLang());
					}
					
					List<DspCnrConttFrResult> conttList = dspCnrSetFrResultV2.getDspCnrConttList();
					for(DspCnrConttFrResult dspCnrConttFrResult: conttList) {
						DspCnrContt contt = dspCnrConttFrResult.getDspCnrContt();
						String conttTpCd = contt.getConttTpCd(); 
						if(DisplayEnum.CONTT_TP.HTML.toString().equals(conttTpCd)) {
							if(dspCnrSetFrResultV2.getDspCnrConttHtmlList() == null) {
								dspCnrSetFrResultV2.setDspCnrConttHtmlList(new ArrayList<DspCnrConttFrResult>());
							}
							
							//컨텐츠 언어적용
							if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
								changeDspCnrConttLang(dspCnrConttFrResult.getDspCnrContt(), dspCnrConttFrResult.getDspCnrConttLang());
							}
							dspCnrSetFrResultV2.getDspCnrConttHtmlList().add(dspCnrConttFrResult);
						} 
						else if(DisplayEnum.CONTT_TP.IMG_BANNER.toString().equals(conttTpCd)) {
							if(dspCnrSetFrResultV2.getDspCnrConttImgList() == null) {
								dspCnrSetFrResultV2.setDspCnrConttImgList(new ArrayList<DspCnrConttFrResult>());
							}
							
							//컨텐츠 언어적용
							if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
								changeDspCnrConttLang(dspCnrConttFrResult.getDspCnrContt(), dspCnrConttFrResult.getDspCnrConttLang());
							}
							dspCnrSetFrResultV2.getDspCnrConttImgList().add(dspCnrConttFrResult);
						}
						else if(DisplayEnum.CONTT_TP.MOVI.toString().equals(conttTpCd)) {
							if(dspCnrSetFrResultV2.getDspCnrConttMoviList() == null) {
								dspCnrSetFrResultV2.setDspCnrConttMoviList(new ArrayList<DspCnrConttFrResult>());
							}
							
							//컨텐츠 언어적용
							if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
								changeDspCnrConttLang(dspCnrConttFrResult.getDspCnrContt(), dspCnrConttFrResult.getDspCnrConttLang());
							}
							dspCnrSetFrResultV2.getDspCnrConttMoviList().add(dspCnrConttFrResult);
						}
						else if(DisplayEnum.CONTT_TP.TEXT.toString().equals(conttTpCd)) {
							if(dspCnrSetFrResultV2.getDspCnrConttTextList() == null) {
								dspCnrSetFrResultV2.setDspCnrConttTextList(new ArrayList<DspCnrConttFrResult>());
							}
							
							//컨텐츠 언어적용
							if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
								changeDspCnrConttLang(dspCnrConttFrResult.getDspCnrContt(), dspCnrConttFrResult.getDspCnrConttLang());
							}
							dspCnrSetFrResultV2.getDspCnrConttTextList().add(dspCnrConttFrResult);
						}
						// // 20160516_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 스티커 상품 처리]
						else if(DisplayEnum.CONTT_TP.GOD.toString().equals(conttTpCd) || DisplayEnum.CONTT_TP.STK_GOD.toString().equals(conttTpCd)) {
							if(dspCnrSetFrResultV2.getDspCnrConttGodList() == null) {
								dspCnrSetFrResultV2.setDspCnrConttGodList(new ArrayList<DspCnrConttFrResult>());
							}
							
							//컨텐츠상품 언어적용
							if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
								changeConttGodLang(dspCnrConttFrResult.getDspCnrGod().getGod(), dspCnrConttFrResult.getDspCnrGod().getGodLangbyGodNm());
							}
							
							//예약태그
							changeConttGodTagResveLang(dspCnrConttFrResult.getDspCnrGod().getGod(), 
									dspCnrConttFrResult.getDspCnrGod().getGodTagResve(), dspCnrConttFrResult.getDspCnrGod().getGodLangbyTagResve());
							
							dspCnrSetFrResultV2.getDspCnrConttGodList().add(dspCnrConttFrResult);
						}
						else if(DisplayEnum.CONTT_TP.PROMT.toString().equals(conttTpCd)) {
							if(dspCnrSetFrResultV2.getDspCnrConttPromtList() == null) {
								dspCnrSetFrResultV2.setDspCnrConttPromtList(new ArrayList<DspCnrConttFrResult>());
							}
							
							//컨텐츠기획전 언어적용
							if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
								changeDspPromtLang(dspCnrConttFrResult.getDspCnrPromt().getDspPromt(), dspCnrConttFrResult.getDspCnrPromt().getDspPromtLang());
							}
							dspCnrSetFrResultV2.getDspCnrConttPromtList().add(dspCnrConttFrResult);
						}
						else if(DisplayEnum.CONTT_TP.STRND.toString().equals(conttTpCd)) {
							if(dspCnrSetFrResultV2.getDspCnrConttStrndList() == null) {
								dspCnrSetFrResultV2.setDspCnrConttStrndList(new ArrayList<DspCnrConttFrResult>());
							}
							
							//컨텐츠S-Trnd 언어적용
							if(!lang.equals(DisplayEnum.DF_LANG.toString())) {
								changeDspStrndLang(dspCnrConttFrResult.getDspCnrStrnd().getDspStrnd(), dspCnrConttFrResult.getDspCnrStrnd().getDspStrndLang());
							}
							dspCnrSetFrResultV2.getDspCnrConttStrndList().add(dspCnrConttFrResult);
						}
						
					}
					
					setListV2.add(dspCnrSetFrResultV2);
					dspCnrfrResultV2.setDspCnrSetList(setListV2);
				}
				cnrListV2.add(dspCnrfrResultV2);
			}
			
		}
		
		//set,contents 없는 코너셋팅
		if(tmplatCnrList != null) {
			for(int i=0; i<tmplatCnrList.size(); i++) {
				Long tCnrSn = tmplatCnrList.get(i).getDspCnrTmplatInfoCnnc().getCnrSn();
				Integer tSortSeq = tmplatCnrList.get(i).getDspCnrTmplatInfoCnnc().getSortSeq();
				Long cCnrSn = null;
				boolean bFlag = false;
				
				for(int j=0; j < cnrListV2.size(); j++) {
					cCnrSn = cnrListV2.get(j).getCnrSn();
					if(tCnrSn.equals(cCnrSn)) {
						bFlag = true;
						break;
					}
				}
						
				if(!bFlag) {
					DspCnrFrResult emptyCnr = new DspCnrFrResult();
					emptyCnr.setCnrSn(tCnrSn);
					cnrListV2.add(i,emptyCnr);
				}
			}
		}
		
		return cnrListV2;
	}
	
	
	/**
	 * 템플릿 정보 조회 V2.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dspCnrScFrDTO [설명]
	 * @return Dsp tmplat fr result [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 15
	 */
	public List<DspTmplatFrResult> selectTmplatPageInfoV2(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		return displayCnrConttFrRepository.selectTmplatPageInfoV2(dspCnrScFrDTO);		
	}
	
	/**
	 * 브랜드카테고리의 전시브랜드ID조회
	 *
	 * @param dspCtgryNo the dsp ctgry no
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectBrndCtgryBrndId(String dspCtgryNo) throws Exception {
		return displayCnrConttFrRepository.selectBrndCtgryBrndId(dspCtgryNo);		
	}
	
	/**
	 * 컨텐츠유형목록.
	 *
	 * @param dspCnrScFrDTO the dsp cnr sc fr dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<String> selectDspCnrConttTp(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		List<String> conttTpList = null;
		
		conttTpList = displayCnrConttFrRepository.selectDspCnrConttTp(dspCnrScFrDTO);
		
		return conttTpList;		
	}
	
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 코너세트 언어적용.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrSet [설명]
	 * @param dspCnrSetLang [설명]
	 * @since 2015. 10. 20
	 */
	private void changeDspCnrLnag(DspCnrSet dspCnrSet, DspCnrSetLang dspCnrSetLang){
		// 대체변환 안함.
		if(dspCnrSet != null && dspCnrSetLang != null){
			if(StringService.isNotEmpty(dspCnrSetLang.getSetNm())) {
				dspCnrSet.setSetNm(dspCnrSetLang.getSetNm());
			}
			if(StringService.isNotEmpty(dspCnrSetLang.getSetDscr())) {
				dspCnrSet.setSetDscr(dspCnrSetLang.getSetDscr());
			}
		}
	}
	
	/**
	 * 컨텐츠 언어 적용
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dcContt [설명]
	 * @param dcConttLang [설명]
	 * @since 2015. 10. 20
	 */
	private void changeDspCnrConttLang(DspCnrContt dcContt, DspCnrConttLang dcConttLang){
		// 대체변환 안함.
		if(dcContt != null && dcConttLang != null){
			if(StringService.isNotEmpty(dcConttLang.getConttNm())) {
				dcContt.setConttNm(dcConttLang.getConttNm());
			}else{
				dcContt.setConttNm(dcConttLang.getConttNm());
			}
			if(StringService.isNotEmpty(dcConttLang.getImgFileNm())) {
				dcContt.setImgFileNm(dcConttLang.getImgFileNm());
				dcContt.setImgFileUrl(dcConttLang.getImgFileUrl());
				dcContt.setImgAltrtvCont(dcConttLang.getImgAltrtvCont());
				dcContt.setImgDscr(dcConttLang.getImgDscr());
				dcContt.setImgMapCont(dcConttLang.getImgMapCont());				
			}else{
				dcContt.setImgFileNm("");
				dcContt.setImgFileUrl("");
				dcContt.setImgAltrtvCont("");
				dcContt.setImgDscr("");
				dcContt.setImgMapCont("");		
			}
			if(StringService.isNotEmpty(dcConttLang.getImgOvFileNm())) {
				dcContt.setImgOvFileNm(dcConttLang.getImgOvFileNm());
				dcContt.setImgOvFileUrl(dcConttLang.getImgOvFileUrl());
				dcContt.setImgOvAltrtvCont(dcConttLang.getImgOvAltrtvCont());
			}else{
				dcContt.setImgOvFileNm("");
				dcContt.setImgOvFileUrl("");
				dcContt.setImgOvAltrtvCont("");
			}
			if(StringService.isNotEmpty(dcConttLang.getMoviImgFileNm())) {
				dcContt.setMoviImgFileNm(dcConttLang.getMoviImgFileNm());
				dcContt.setMoviImgUrl(dcConttLang.getMoviImgUrl());
				dcContt.setMoviImgAltrtvCont(dcConttLang.getMoviImgAltrtvCont());
				dcContt.setMoviImgDscr(dcConttLang.getMoviImgDscr());
			}else{
				dcContt.setMoviImgFileNm("");
				dcContt.setMoviImgUrl("");
				dcContt.setMoviImgAltrtvCont("");
				dcContt.setMoviImgDscr("");
			}
			if(StringService.isNotEmpty(dcConttLang.getHtmlSj())) {
				dcContt.setHtmlCont(dcConttLang.getHtmlCont());
			}
			else {
				dcContt.setHtmlCont("");
			}
			
		}
	}
	
	/**
	 * 컨텐츠 상품 언어적용
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param god [설명]
	 * @param godLang [설명]
	 * @since 2015. 10. 20
	 */
	private void changeConttGodLang(God god, GodLangbyGodNm godLang){
		// 대체변환 안함.
//		if(godLang != null && godLang.getGodNm() != null){
		if(godLang != null){
			god.setGodNm(godLang.getGodNm());
			god.setMobileGodNm(godLang.getMobileGodNm());
			god.setTagNm(godLang.getTagNm());
			god.setColorTagNm(godLang.getColorTagNm());
			god.setThemaTagNm(godLang.getThemaTagNm());
			god.setColorNm(godLang.getColorNm());
		}else{
			god.setGodNm("");
			god.setMobileGodNm("");
			god.setTagNm("");
			god.setColorTagNm("");
			god.setThemaTagNm("");
			god.setColorNm("");
		}
	}
	
	/**
	 * 기획전 언어적용.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param promt [설명]
	 * @param promtLang [설명]
	 * @since 2015. 10. 20
	 */
	private void changeDspPromtLang(DspPromt promt, DspPromtLang promtLang){
		// 대체변환 안함.
		if(promt != null && promtLang != null){
			if(StringService.isNotEmpty(promtLang.getPromtNm())) {
				promt.setPromtNm(promtLang.getPromtNm());
			}else{
				promt.setPromtNm("");
			}
//			if(StringService.isNotEmpty(promtLang.getPcImgFileNm())) {
//				promt.setPcImgFileNm(promtLang.getPcImgFileNm());
//				promt.setPcImgFileUrl(promtLang.getPcImgFileUrl());
//				promt.setPcImgAltrtvCont(promtLang.getPcImgAltrtvCont());
//			}else{
//				promt.setPcImgFileNm("");
//				promt.setPcImgFileUrl("");
//				promt.setPcImgAltrtvCont("");
//			}
//			if(StringService.isNotEmpty(promtLang.getMobileImgFileNm())) {
//				promt.setMobileImgFileNm(promtLang.getMobileImgFileNm());
//				promt.setMobileImgFileUrl(promtLang.getMobileImgFileUrl());
//				promt.setMobileImgAltrtvCont(promtLang.getMobileImgAltrtvCont());
//			}else{
//				promt.setMobileImgFileNm("");
//				promt.setMobileImgFileUrl("");
//				promt.setMobileImgAltrtvCont("");
//			}
//			if(StringService.isNotEmpty(promtLang.getPcGodImgFileNm())) {
//				promt.setPcGodImgFileNm(promtLang.getPcGodImgFileNm());
//				promt.setPcGodImgFileUrl(promtLang.getPcGodImgFileUrl());
//				promt.setPcGodImgAltrtvCont(promtLang.getPcGodImgAltrtvCont());
//			}else{
//				promt.setPcGodImgFileNm("");
//				promt.setPcGodImgFileUrl("");
//				promt.setPcGodImgAltrtvCont("");
//			}
//			if(StringService.isNotEmpty(promtLang.getMobileGodImgFileNm())) {
//				promt.setMobileGodImgFileNm(promtLang.getMobileGodImgFileNm());
//				promt.setMobileGodImgFileUrl(promtLang.getMobileGodImgFileUrl());
//				promt.setMobileGodImgAltrtvCont(promtLang.getMobileGodImgAltrtvCont());
//			}else{
//				promt.setMobileGodImgFileNm("");
//				promt.setMobileGodImgFileUrl("");
//				promt.setMobileGodImgAltrtvCont("");
//			}
		}
	}
	
	/**
	 * S-Trnd 언어 적용.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param strnd [설명]
	 * @param strndLang [설명]
	 * @since 2015. 10. 20
	 */
	private void changeDspStrndLang(DspStrnd strnd, DspStrndLang strndLang){
		// 대체변환 안함.
		if(strnd != null && strndLang != null){
			if(StringService.isNotEmpty(strndLang.getStrndNm())) {
				strnd.setStrndNm(strndLang.getStrndNm());
				strnd.setStrndDscr(strndLang.getStrndDscr());
			}else{
				strnd.setStrndNm("");
				strnd.setStrndDscr("");
			}
			if(StringService.isNotEmpty(strndLang.getRprstImgFileNm())) {
				strnd.setRprstImgFileNm(strndLang.getRprstImgFileNm());
				strnd.setRprstImgFileUrl(strndLang.getRprstImgFileUrl());
				strnd.setRprstImgAltrtvCont(strndLang.getRprstImgAltrtvCont());
			}else{
				strnd.setRprstImgFileNm("");
				strnd.setRprstImgFileUrl("");
				strnd.setRprstImgAltrtvCont("");
			}
		}
	}
	
	
	/**
	 * 상품예약태그 언어적용.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param god [설명]
	 * @param godTagResve [설명]
	 * @param godLangbyTagResve [설명]
	 * @since 2015. 11. 5
	 */
	private void changeConttGodTagResveLang(God god,  GodTagResve godTagResve,GodLangbyTagResve godLangbyTagResve){
		// 대체변환 안함.
		if(godTagResve != null){
			if(godLangbyTagResve != null && godLangbyTagResve.getTagNm() != null){
				god.setTagNm(godLangbyTagResve.getTagNm());
			}else if(godTagResve != null && godTagResve.getTagNm() != null){
				god.setTagNm(godTagResve.getTagNm());
			}else{
				god.setTagNm("");
			}
		}
	}
}
