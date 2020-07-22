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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSetLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTpGrp;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrnd;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndLang;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndLang;
import com.plgrim.ncp.biz.display.data.DspCnrBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrSearchFoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrTpGrpBoDTO;
import com.plgrim.ncp.biz.display.exception.NotDeletedCnrTpException;
import com.plgrim.ncp.biz.display.repository.DisplayCornerRepository;
import com.plgrim.ncp.biz.display.repository.DisplayCornerRepository.SelectDspCnrConttList2Dto;
import com.plgrim.ncp.biz.display.repository.DisplayCornerTypeRepository;
import com.plgrim.ncp.biz.display.result.DspCnrContt2FoResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttGodFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttPromtFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttStrndFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrSetFoResult;
import com.plgrim.ncp.biz.display.result.GodColorFoResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;

/**
 * 코너 설정 관리 service class
 * 
 * <p>
 * 
 * <ul>
 *   <li> 코너 기본정보 등록/수정/삭제
 *   <li> 코너 컨텐츠 유형 그룹 등록/수정/삭제
 * </ul>.
 *
 * @author shsunhee.kim
 * @since 2015. 3. 16
 */
@Slf4j
@Service
public class DisplayCornerService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DisplayCornerRepository cornerRepository;
	
	@Autowired
	DisplayCornerTypeRepository cornerTypeRepository;
	
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
	 * 코너 등록.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCnrDTO 코너관리 DTO
	 * @return Long 생성된 코너일련번호
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public long insertCorner(DspCnrBoDTO dspCnrDTO) throws Exception {
		return cornerRepository.insertCornerInfo(dspCnrDTO.getDspCnr());
	}
	
	/**
	 * 코너 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCnrDTO 코너관리 DTO
	 * @return Int result
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int updateCorner(DspCnrBoDTO dspCnrDTO) throws Exception {
		return cornerRepository.updateDspCnr(dspCnrDTO.getDspCnr());
	}
	
	/**
	 * 코너 삭제.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCnrDTO 코너관리 DTO
	 * @return Int result
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public int deleteCorner(DspCnrBoDTO dspCnrDTO) throws Exception {
		return cornerRepository.deleteDspCnr(dspCnrDTO.getDspCnr());
	}
	
	
	/**
	 * 코너 목록 수정 (코너사용여부 수정)
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list 코너목록 grid list
	 * @throws Exception the exception
	 * @since 2015. 4. 7
	 */
	public void updateCornerList(List<DspCnrBoDTO> list) throws Exception {
		DspCnr dspCnr = null;
		String loginId = BOSecurityUtil.getLoginId();
		
		if (list != null) {
			for(DspCnrBoDTO dspCnrDTO: list) {
				dspCnr = dspCnrDTO.getDspCnr();
				dspCnr.setDspCnrDscr(null);
				dspCnr.setDspCnrNm(null);
				dspCnr.setUdterId(loginId);
				
				cornerRepository.updateDspCnrInfo(dspCnr);				
			}
		}
	}
	
	/**
	 * 코너 목록 삭제
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list 코너목록 grid list
	 * @throws Exception the exception
	 * @since 2015. 4. 7
	 */
	public void deleteCornerList(List<DspCnrBoDTO> list) throws Exception {
		DspCnr dspCnr = null;
		if (list != null) {
			for(DspCnrBoDTO dspCnrDTO: list) {
				dspCnr = dspCnrDTO.getDspCnr();
				//코너컨텐츠 유형 그룹 삭제
				DspCnrTpGrp dspCnrTpGrp = new DspCnrTpGrp();
				dspCnrTpGrp.setRevSn(dspCnr.getRevSn());
				dspCnrTpGrp.setCnrSn(dspCnr.getCnrSn());
				dspCnrTpGrp.setUpperCnrTpGrpSn(Long.valueOf(1));				
				cornerRepository.deleteDspCnrTpGrpByCnrSn(dspCnrTpGrp);
				
				dspCnrTpGrp.setUpperCnrTpGrpSn(Long.valueOf(0));				
				cornerRepository.deleteDspCnrTpGrpByCnrSn(dspCnrTpGrp);
				
				//코너 삭제
				cornerRepository.deleteDspCnr(dspCnr);				
			}
		}
	}
	
	/**
	 * 코너 컨텐츠 유형 그룹 등록.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list DspCnrTpGrpDTO 목록
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public void insertCornerType (Long cnrSn, Long upperCnrTpGrpSn, List<DspCnrTpGrpBoDTO> list) throws Exception {
		
		DspCnrTpGrp dspCnrTpGrp = null;
		
		if (list != null) {
			for(DspCnrTpGrpBoDTO dspCnrTpGrpDTO: list) {
				dspCnrTpGrp = dspCnrTpGrpDTO.getDspCnrTpGrp();
				dspCnrTpGrp.setCnrSn(cnrSn);
				if(upperCnrTpGrpSn != 0) dspCnrTpGrp.setUpperCnrTpGrpSn(upperCnrTpGrpSn);
				
				cornerTypeRepository.insertCornerType(dspCnrTpGrp);				
			}
		}
	}
	
	/**
	 * 코너 컨텐츠 유형 그룹 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list DspCnrTpGrpDTO 목록
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public void updateCornerType(Long cnrSn, Long upperCnrTpGrpSn,List<DspCnrTpGrpBoDTO> list) throws Exception {
		DspCnrTpGrp dspCnrTpGrp = null;
		
		if (list != null) {
			for(DspCnrTpGrpBoDTO dspCnrTpGrpDTO: list) {
				dspCnrTpGrp = dspCnrTpGrpDTO.getDspCnrTpGrp();
				dspCnrTpGrp.setCnrSn(cnrSn);
				if(upperCnrTpGrpSn != 0) dspCnrTpGrp.setUpperCnrTpGrpSn(upperCnrTpGrpSn);
				
				cornerTypeRepository.updateDspCnrTpGrp(dspCnrTpGrp);	
			}
		}		
	}
	
	/**
	 * 코너 컨텐츠 유형 그룹 삭제.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list DspCnrTpGrpDTO 목록
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public void deleteCornerType(Long cnrSn, Long upperCnrTpGrpSn,List<DspCnrTpGrpBoDTO> list) {
		DspCnrTpGrp dspCnrTpGrp = null;
		
		if (list != null) {
			for(DspCnrTpGrpBoDTO dspCnrTpGrpDTO: list) {
				dspCnrTpGrp = dspCnrTpGrpDTO.getDspCnrTpGrp();
				dspCnrTpGrp.setCnrSn(cnrSn);
				
				if(upperCnrTpGrpSn != 0) {
					//유형삭제
					
					//코너셋트에 연결되어 있으면 삭제불가
					int cnt = cornerTypeRepository.selectUseCnrTpCnt(dspCnrTpGrp);
					
					if (cnt > 0) {
						throw new NotDeletedCnrTpException(null);
					}
					
					 cornerTypeRepository.deleteDspCnrTpGrp(dspCnrTpGrp);
				}
				else {
					//그룹삭제 1) 유형삭제, 그룹삭제
					dspCnrTpGrp.setUpperCnrTpGrpSn(dspCnrTpGrp.getCnrTpGrpSn());
					
					//코너셋트에 연결되어 있으면 삭제불가
					int cnt = cornerTypeRepository.selectUseCnrTpCnt(dspCnrTpGrp);
					
					if (cnt > 0) {
						throw new NotDeletedCnrTpException(null);
					}
					
					cornerTypeRepository.deleteDspCnrTpGrp(dspCnrTpGrp);
										
					//cornerTypeRepository.deleteDspCnrTpGrp(dspCnrTpGrp);
				}
				
			}
		}	
	}
	
	/**
	 * 코너 컨텐츠 유형 그룹 등록.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list DspCnrTpGrpDTO 목록
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public void insertCornerType (List<DspCnrTpGrpBoDTO> list) throws Exception {
		
		DspCnrTpGrp dspCnrTpGrp = null;
		
		if (list != null) {
			for(DspCnrTpGrpBoDTO dspCnrTpGrpDTO: list) {
				dspCnrTpGrp = dspCnrTpGrpDTO.getDspCnrTpGrp();
				
				cornerTypeRepository.insertCornerType(dspCnrTpGrp);				
			}
		}
	}
	
	/**
	 * 코너 컨텐츠 유형 그룹 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list DspCnrTpGrpDTO 목록
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public void updateCornerType(List<DspCnrTpGrpBoDTO> list) throws Exception {
		DspCnrTpGrp dspCnrTpGrp = null;
		
		if (list != null) {
			for(DspCnrTpGrpBoDTO dspCnrTpGrpDTO: list) {
				dspCnrTpGrp = dspCnrTpGrpDTO.getDspCnrTpGrp();
				cornerTypeRepository.updateDspCnrTpGrp(dspCnrTpGrp);	
			}
		}		
	}
	
	
	/**
	 * 전시코너 기본 정보 조회
	 * @param pk
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public DspCnrFoResult selectDspCnrDefaultInfo(SystemPK pk, DspCnrSearchFoDTO search) throws Exception{
		DspCnrFoResult result = cornerRepository.selectDspCnrDefaultInfo(pk, search);
		if(result != null) {
			
			List<DspCnrSetFoResult> dspCnrSetList = result.getDspCnrSetList();
			// 전시코너세트가 존재하면 ... 
			// 전시코너컨텐츠 정보를 조회해온다.
			if(dspCnrSetList != null && dspCnrSetList.size() > 0){
				for(DspCnrSetFoResult dspCnrSet:dspCnrSetList){
					
					// LANGUAGE CONTROL
					String lang = pk.getLang();
					if(!lang.equals("KOR")){
						DspCnrSet dcSet = dspCnrSet.getDspCnrSet();
						DspCnrSetLang dcSetLang = dspCnrSet.getDspCnrSetLang();
						changeDspCnrLnag(dcSet, dcSetLang);
					}
					
					search.setDspCnrSet(dspCnrSet.getDspCnrSet());
					List<DspCnrConttFoResult> contentList = cornerRepository.selectDspCnrConttList(pk, search);
					
					Map<String,List<DspCnrConttFoResult>> dspCnrConttMap = separateDspCnrConttTpGrpList( pk, search, contentList);
					
					dspCnrSet.setDspCnrConttMap(dspCnrConttMap);
				}
			}
			
		}
		
		return result;
	}
	
	public void selectDspCnrConttList(List<DspCnrFoResult> cnrList, Map<String, Object> map) throws Exception {
		SelectDspCnrConttList2Dto search = SelectDspCnrConttList2Dto.fromMap(map);


		List<DspCnrContt2FoResult> resultList = cornerRepository.selectDspCnrConttList2(search);
		
		List<String> godNoList = new ArrayList<String>();
		List<Long> promtSnList = new ArrayList<Long>();
		List<Long> strndSnList = new ArrayList<Long>();
		List<String> stkGodNoList = new ArrayList<String>();

		boolean isExist = false;
		String godNo = "";
		Long promtSn = new Long(0L);
		Long strndSn = new Long(0L);
		String stkGodNo = "";
		
		int resultCnt = 0;
		if(resultList != null){
			resultCnt = resultList.size();
		}
		
		if(resultList != null && resultCnt > 0){
			for(DspCnrContt2FoResult dspCnrContt:resultList){
				
				DspCnrContt contt = dspCnrContt.getDspCnrContt();
				String conttTpCd = contt.getConttTpCd();
				
				if(conttTpCd.equals("GOD")){
					godNo = contt.getGodNo();
					int cnt = godNoList.size();
					if(cnt == 0){
						godNoList.add(godNo);
					}
					else{
						isExist = false;
						for(String gNo:godNoList){
							if(gNo.equals(godNo)){
								isExist = true;
								break;
							}
						}
						if(!isExist){
							godNoList.add(contt.getGodNo());
						}
					}
				}else if(conttTpCd.equals("PROMT")){
					promtSn = contt.getPromtSn();
					int cnt = promtSnList.size();
					if(cnt == 0){
						promtSnList.add(promtSn);
					}else{
						isExist = false;
						for(Long pNo:promtSnList){
							if(pNo.equals(promtSn)){
								isExist = true;
								break;
							}
						}
						if(!isExist){
							promtSnList.add(promtSn);
						}
					}
				}
				else if(conttTpCd.equals("STRND")){
					strndSn = contt.getStrndSn();
					int cnt = strndSnList.size();
					if(cnt == 0){
						strndSnList.add(strndSn);
					}else{
						isExist = false;
						for(Long sNo:strndSnList){
							if(sNo.equals(strndSn)){
								isExist = true;
								break;
							}
						}
						if(!isExist){
							strndSnList.add(strndSn);
						}
					}
				}else if(conttTpCd.equals("STK_GOD")){
					stkGodNo = contt.getGodNo();
					int cnt = stkGodNoList.size();
					if(cnt == 0){
						stkGodNoList.add(stkGodNo);
					}
					else{
						isExist = false;
						for(String sGodNo:stkGodNoList){
							if(sGodNo.equals(stkGodNo)){
								isExist = true;
								break;
							}
						}
						if(!isExist){
							stkGodNoList.add(contt.getGodNo());
						}
					}
				}
			}
		}
		// 전시컨텐츠 중 상품,기획전,STRAND가 있을 경우 각각 한번에 정보를 가져온다.
		int godNoListCnt = godNoList.size();
		int promtCdListCnt = promtSnList.size();
		int strndCdListCnt = strndSnList.size();
		int stkGodNoListCnt = stkGodNoList.size();
		
		List<DspCnrConttGodFoResult> godResultList = null;
		List<DspCnrConttPromtFoResult> promtResultList = null;
		List<DspCnrConttStrndFoResult> strndResultList = null;
		List<DspCnrConttGodFoResult> stkGodResultList = null;
		// 상품정보로딩
		if(godNoListCnt > 0){
			map.put("godNoList", godNoList);
			godResultList = cornerRepository.selectDspCnrConttGod2(map);
			if(godResultList != null){
				for(DspCnrConttGodFoResult godResult:godResultList){
					// 이미지 체크
					String fImg = godResult.getImgFrontUrl();
					String bImg = godResult.getImgBackUrl();

					boolean isExistFront = false;
					boolean isExistBack = false;
					
					if(fImg != null && fImg.length() > 0){
						isExistFront = true;
					}
					if(bImg != null && bImg.length() > 0){
						isExistBack = true;
					}
					
					if(isExistFront == true && isExistBack == false){
						godResult.setImgBackUrl(fImg);
					}
					if(isExistFront == false && isExistBack == true){
						godResult.setImgFrontUrl(bImg);
					}
				}
			}
		}
		// 기획전정보로딩
		if(promtCdListCnt > 0){
			map.put("promtSnList", promtSnList);
			promtResultList = cornerRepository.selectDspCnrConttPromt2(map);
		}
		// STRAND정보로딩
		if(strndCdListCnt > 0){
			map.put("strndSnList", strndSnList);
			strndResultList = cornerRepository.selectDspCnrConttStnd2(map);
		}
		//스티커상품 정보로딩
		if(stkGodNoListCnt > 0){
			//상품정보로딩 시 사용한 [key:godNoList]를 엎어써서 사용함 for 같은 쿼리사용을 위해.
			map.put("godNoList", stkGodNoList);
			stkGodResultList = cornerRepository.selectDspCnrConttGod2(map);
			if(stkGodResultList != null){
				for(DspCnrConttGodFoResult stkGodResult:stkGodResultList){
					// 이미지 체크
					String sfImg = stkGodResult.getImgFrontUrl();
					String sbImg = stkGodResult.getImgBackUrl();

					boolean isExistFront = false;
					boolean isExistBack = false;
					
					if(sfImg != null && sfImg.length() > 0){
						isExistFront = true;
					}
					if(sbImg != null && sbImg.length() > 0){
						isExistBack = true;
					}
					
					if(isExistFront == true && isExistBack == false){
						stkGodResult.setImgBackUrl(sfImg);
					}
					if(isExistFront == false && isExistBack == true){
						stkGodResult.setImgFrontUrl(sbImg);
					}
				}
			}
		}
		
		String lang = (String)map.get("lang");
		
		if(resultList != null && resultCnt > 0){
			for(DspCnrContt2FoResult dspCnrContt:resultList){
				//1. 전시코너
				Long cnrCd = dspCnrContt.getDspCnrContt().getCnrSn();
				Long cnrSetCd = dspCnrContt.getDspCnrContt().getCnrSetSn();
				DspCnrContt contt = dspCnrContt.getDspCnrContt();
				String conttTpCd = contt.getConttTpCd();
				
				for(DspCnrFoResult dspCnrResult:cnrList){
					DspCnr dspCnr = dspCnrResult.getDspCnr();
					if(cnrCd.equals(dspCnr.getCnrSn())){
						List<DspCnrSetFoResult> dcsList = dspCnrResult.getDspCnrSetList();
						if( dcsList == null){
							dcsList = new ArrayList<DspCnrSetFoResult>();
							dspCnrResult.setDspCnrSetList(dcsList);
						}
						
						isExist = false;
						DspCnrSetFoResult cnrSetResult = null;
						for(DspCnrSetFoResult dcsResult:dcsList){
							Long csSn = dcsResult.getDspCnrSet().getCnrSetSn();
							if(cnrSetCd.equals(csSn)){
								cnrSetResult = dcsResult;
								isExist = true;
								break;
							}
						}
						if(isExist){
							Map<String,List<DspCnrConttFoResult>> dspCnrConttMap = cnrSetResult.getDspCnrConttMap();
							DspCnrConttLang dspCnrConttLang = dspCnrContt.getDspCnrConttLang();
							// LANGUAGE CONTROL
							if(!lang.equals("KOR")){
								changeDspCnrConttLang(contt, dspCnrConttLang);
							}
							List<DspCnrConttFoResult> l = dspCnrConttMap.get(conttTpCd);
							if(l == null){
								l = new ArrayList<DspCnrConttFoResult> ();
								dspCnrConttMap.put(conttTpCd, l);
							}
							DspCnrConttFoResult item = new DspCnrConttFoResult();
							item.setDspCnrContt(contt);
							item.setDspCnrConttLang(dspCnrConttLang);

							boolean isExistItem = false;
							if(conttTpCd.equals("GOD")){
								if(godResultList != null){
									for(DspCnrConttGodFoResult dspCnrConttGod:godResultList){
										God god = dspCnrConttGod.getGod();

										if(contt.getGodNo().equals(god.getGodNo())){
											if(!lang.equals("KOR")){
												GodLangbyGodNm godLang = dspCnrConttGod.getGodLangbyGodNm();
												changeDspCtgryCnncGodLang(god, godLang);
											}
											item.setDspCnrGod(dspCnrConttGod);
											item.setStkCdNm(dspCnrContt.getStkCdNm());
											isExistItem = true;
											break;
										}
										
										
									}
								}
							}else if(conttTpCd.equals("PROMT")){
								if(promtResultList != null){
									for(DspCnrConttPromtFoResult dspCnrConttPromt:promtResultList){
										DspPromt promt = dspCnrConttPromt.getDspPromt();
										if(contt.getPromtSn().equals(promt.getPromtSn())){
											if(!lang.equals("KOR")){
												DspPromtLang promtLang = dspCnrConttPromt.getDspPromtLang();
												changeDspPromtLang(promt, promtLang);
											}
											item.setDspCnrPromt(dspCnrConttPromt);
											isExistItem = true;
											break;
										}
									}
								}
							}else if(conttTpCd.equals("STRND")){
								if(strndResultList != null){
									for(DspCnrConttStrndFoResult dspCnrConttStrnd:strndResultList){
										DspStrnd strnd = dspCnrConttStrnd.getDspStrnd();
										SysBrnd brnd = dspCnrConttStrnd.getSysBrnd();
										if(contt.getStrndSn().equals(strnd.getStrndSn())){
											if(!lang.equals("KOR")){
												DspStrndLang strndLang = dspCnrConttStrnd.getDspStrndLang();
												SysBrndLang brndLang = dspCnrConttStrnd.getSysBrndLang();
												changeDspStrndBrndLang(strnd, strndLang, brnd, brndLang);
											}
											item.setDspCnrStrnd(dspCnrConttStrnd);
											isExistItem = true;
											break;
										}
									}
								}
							}else if(conttTpCd.equals("STK_GOD")){
								if(stkGodResultList != null){
									for(DspCnrConttGodFoResult dspCnrConttGod:stkGodResultList){
										God god = dspCnrConttGod.getGod();

										if(contt.getGodNo().equals(god.getGodNo())){
											if(!lang.equals("KOR")){
												GodLangbyGodNm godLang = dspCnrConttGod.getGodLangbyGodNm();
												changeDspCtgryCnncGodLang(god, godLang);
											}
											item.setDspCnrGod(dspCnrConttGod);
											item.setStkCdNm(dspCnrContt.getStkCdNm());
											isExistItem = true;
											break;
										}
										
										
									}
								}
							}else{
								isExistItem = true;
							}
								
							if(isExistItem){
								l.add(item);
							}
						}else{
							// 전시코너세트 신규 등록
							DspCnrSetFoResult dcsResult = new DspCnrSetFoResult();
							DspCnrSet dcSet = dspCnrContt.getDspCnrSet();
							DspCnrSetLang dcSetLang = dspCnrContt.getDspCnrSetLang();
							// LANGUAGE CONTROL
							if(!lang.equals("KOR")){
								changeDspCnrLnag(dcSet, dcSetLang);
							}
							dcsResult.setDspCnrSet(dcSet);
							dcsResult.setDspCnrSetLang(dcSetLang);
							// 신규 전시코너세트에 신규전시컨텐츠등록
							Map<String,List<DspCnrConttFoResult>> dspCnrConttMap = new HashMap<String,List<DspCnrConttFoResult>>();
							dcsResult.setDspCnrConttMap(dspCnrConttMap);
							dcsList.add(dcsResult);
								
							DspCnrConttLang dspCnrConttLang = dspCnrContt.getDspCnrConttLang();
							List<DspCnrConttFoResult> l = new ArrayList<DspCnrConttFoResult>();
							DspCnrConttFoResult item = new DspCnrConttFoResult();
							item.setDspCnrContt(contt);
							item.setDspCnrConttLang(dspCnrConttLang);
							// LANGUAGE CONTROL
							if(!lang.equals("KOR")){
								changeDspCnrConttLang(contt, dspCnrConttLang);		//changeDspStrndLang 확장함수 (for brnd_nm from sys_brnd & sys_brnd_lang)
							}

							boolean isExistItem = false;
							if(conttTpCd.equals("GOD")){
								if(godResultList != null){
									for(DspCnrConttGodFoResult dspCnrConttGod:godResultList){
										God god = dspCnrConttGod.getGod();
										if(contt.getGodNo().equals(god.getGodNo())){
											if(!lang.equals("KOR")){
												GodLangbyGodNm godLang = dspCnrConttGod.getGodLangbyGodNm();
												changeDspCtgryCnncGodLang(god, godLang);
											}
											item.setDspCnrGod(dspCnrConttGod);
											item.setStkCdNm(dspCnrContt.getStkCdNm());
											isExistItem = true;
											break;
										}
									}
								}
							}else if(conttTpCd.equals("PROMT")){
								if(promtResultList != null){
									for(DspCnrConttPromtFoResult dspCnrConttPromt:promtResultList){
										DspPromt promt = dspCnrConttPromt.getDspPromt();
										if(contt.getPromtSn().equals(promt.getPromtSn())){
											if(!lang.equals("KOR")){
												DspPromtLang promtLang = dspCnrConttPromt.getDspPromtLang();
												changeDspPromtLang(promt, promtLang);
											}
											item.setDspCnrPromt(dspCnrConttPromt);
											isExistItem = true;
											break;
										}
									}
								}
							}else if(conttTpCd.equals("STRND")){
								if(strndResultList != null){
									for(DspCnrConttStrndFoResult dspCnrConttStrnd:strndResultList){
										DspStrnd strnd = dspCnrConttStrnd.getDspStrnd();
										SysBrnd brnd = dspCnrConttStrnd.getSysBrnd();
										if(contt.getStrndSn().equals(strnd.getStrndSn())){
											if(!lang.equals("KOR")){
												DspStrndLang strndLang = dspCnrConttStrnd.getDspStrndLang();
												SysBrndLang brndLang = dspCnrConttStrnd.getSysBrndLang();
												changeDspStrndBrndLang(strnd, strndLang, brnd, brndLang);		//changeDspStrndLang 확장함수 (for brnd_nm from sys_brnd & sys_brnd_lang)
											}
											item.setDspCnrStrnd(dspCnrConttStrnd);
											isExistItem = true;
											break;
										}
									}
								}
							}else if(conttTpCd.equals("STK_GOD")){
								if(stkGodResultList != null){
									for(DspCnrConttGodFoResult dspCnrConttGod:stkGodResultList){
										God god = dspCnrConttGod.getGod();
										if(contt.getGodNo().equals(god.getGodNo())){
											if(!lang.equals("KOR")){
												GodLangbyGodNm godLang = dspCnrConttGod.getGodLangbyGodNm();
												changeDspCtgryCnncGodLang(god, godLang);
											}
											item.setDspCnrGod(dspCnrConttGod);
											item.setStkCdNm(dspCnrContt.getStkCdNm());
											isExistItem = true;
											break;
										}
									}
								}
							}else{
								isExistItem = true;
							}
								
							if(isExistItem){
								l.add(item);
							}
							dspCnrConttMap.put(conttTpCd, l);
						}

						break;
					}
				}
			}
		}
	}
	
	public List<DspCnrFoResult> selectDspCnrSetByCnr(Map<String,Object> map) throws Exception{
		return cornerRepository.selectDspCnrSetByCnr(map);
	}
	
	public List<String> selectTopSellerGodNoList1(Map<String,Object> map) throws Exception{
		return cornerRepository.selectTopSellerGodNoList1(map);
	}
	
	public List<String> selectBrandTopSellerGodNoList8s(Map<String,Object> map) throws Exception{
		return cornerRepository.selectBrandTopSellerGodNoList8s(map);
	}
	
	public List<String> selectBrandTopSellerGodNoListOthers(Map<String,Object> map) throws Exception{
		return cornerRepository.selectBrandTopSellerGodNoListOthers(map);
	}
	
	public List<String> selectNewArrivalGodNoList1(Map<String,Object> map) throws Exception{
		return cornerRepository.selectNewArrivalGodNoList1(map);
	}
	
	public List<String> selectBrandNewArrivalGodNoList8s(Map<String,Object> map) throws Exception{
		return cornerRepository.selectBrandNewArrivalGodNoList8s(map);
	}
	
	public List<String> selectBrandNewArrivalGodNoListOthers(Map<String,Object> map) throws Exception{
		return cornerRepository.selectBrandNewArrivalGodNoListOthers(map);
	}
	
	public List<DspCnrConttGodFoResult> selectDspCnrConttGod2 (Map<String,Object> map) throws Exception{
		List<DspCnrConttGodFoResult> list = cornerRepository.selectDspCnrConttGod2(map);
		String lang = (String) map.get("lang");
		if(!lang.equals("KOR")){
			if(list != null && list.size() > 0){
				for(DspCnrConttGodFoResult gResult:list){
					God god = gResult.getGod();
					GodLangbyGodNm godLang = gResult.getGodLangbyGodNm();
					changeDspCtgryCnncGodLang(god, godLang);
				}
			}
		}
		return list;
	}
	
	public List<DspCnrConttStrndFoResult> selectDspCnrConttStndList(Map<String,String> map) throws Exception{
		return cornerRepository.selectDspCnrConttStnd3(map);
	}
	
	public List<DspCnrConttStrndFoResult> selectBeanpoleDspCnrConttStndList(Map<String,String> map) throws Exception{
		return cornerRepository.selectDspCnrConttStnd4(map);
	}
	
	public List<GodColorFoResult> getGoodsColorList(Map<String,String> map) throws Exception {
		return cornerRepository.getGoodsColorList(map);
	}
	
	public Integer getGoodsOrdCnt(Map<String,Object> map) throws Exception {
		return cornerRepository.getGoodsOrdCnt(map);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 전시코너세트의 컨텐츠 목록을 타입유형별로 목록을 나누어준다.
	 * 
	 * @param pk 시스템 PK
	 * @param search	검색조건
	 * @param list		타입유형전체 전시코너컨텐츠 목록
	 * @return			타입유형별로 전시코너컨텐츠 목록으로 분리한 MAP
	 * @throws Exception
	 */
	private Map<String,List<DspCnrConttFoResult>> separateDspCnrConttTpGrpList(
			SystemPK pk, DspCnrSearchFoDTO search, List<DspCnrConttFoResult> list) throws Exception{
		Map<String,List<DspCnrConttFoResult>> map = new HashMap<String,List<DspCnrConttFoResult>>();

		for(DspCnrConttFoResult contt:list){
			
			// LANGUAGE CONTROL
			String lang = pk.getLang();
			if(!lang.equals("KOR")){
				DspCnrContt dcContt = contt.getDspCnrContt();
				DspCnrConttLang dcConttLang = contt.getDspCnrConttLang();
				changeDspCnrConttLang(dcContt, dcConttLang);
			}
			String conttTp = contt.getDspCnrContt().getConttTpCd(); 
			search.setDspCnrContt(contt.getDspCnrContt());
			if(conttTp.equals("GOD")){
				// Contents Type is "GOD"
				DspCnrConttGodFoResult godResult = cornerRepository.selectDspCnrConttGod(pk, search);
				if(godResult == null){
					continue;
				}
				// LANGUAGE CONTROL
				if(!lang.equals("KOR")){
					God god = godResult.getGod();
					GodLangbyGodNm godLang = godResult.getGodLangbyGodNm();
					changeDspCtgryCnncGodLang(god, godLang);
				}
				contt.setDspCnrGod(godResult);
			}else if(conttTp.equals("PROMT")){
				// Contents Type is DSP_PRONT
				DspCnrConttPromtFoResult promtResult = cornerRepository.selectDspCnrConttPromt(pk, search);
				if(promtResult == null){
					continue;
				}
				// LANGUAGE CONTROL
				if(!lang.equals("KOR")){
					DspPromt promt = promtResult.getDspPromt();
					DspPromtLang promtLang = promtResult.getDspPromtLang();
					changeDspPromtLang(promt, promtLang);
				}
				contt.setDspCnrPromt(promtResult);
			}else if(conttTp.equals("STRND")){
				// Contents Type is S_TRND
				DspCnrConttStrndFoResult strndResult = cornerRepository.selectDspCnrConttStnd(pk, search);
				if(strndResult == null){
					continue;
				}
				if(!lang.equals("KOR")){
					DspStrnd strnd = strndResult.getDspStrnd();
					DspStrndLang strndLang = strndResult.getDspStrndLang();
					changeDspStrndLang(strnd, strndLang);
				}
				contt.setDspCnrStrnd(strndResult);
			}
			
//			String key = contt.getDspCnrTpGrp().getCnrConttTpGrpNm() + "(" + contt.getDspCnrContt().getCnrTpGrpSn() + ")";
			String key = contt.getDspCnrContt().getConttTpCd();
			if(map.containsKey(key)){
				List<DspCnrConttFoResult> tpList = map.get(key);
				tpList.add(contt);
			}
			else{
				List<DspCnrConttFoResult> tpList = new ArrayList<DspCnrConttFoResult>();
				tpList.add(contt);
				map.put(key, tpList);
			}
		}
		
		return map;
	}
	
	private void changeDspCnrLnag(DspCnrSet dspCnrSet, DspCnrSetLang dspCnrSetLang){
//		if(dspCnrSetLang != null && dspCnrSetLang.getSetNm() != null){	// 대체변환 안함.
		if(dspCnrSetLang != null){	// 대체변환 안함.
			dspCnrSet.setSetNm(dspCnrSetLang.getSetNm());
			dspCnrSet.setSetDscr(dspCnrSetLang.getSetDscr());
		}else{
			dspCnrSet.setSetNm("");
			dspCnrSet.setSetDscr("");
		}
	}
	
	private void changeDspCnrConttLang(DspCnrContt dcContt, DspCnrConttLang dcConttLang){
		// 대체변환 안함.
		if(dcConttLang != null){
			dcContt.setConttNm(dcConttLang.getConttNm());
			dcContt.setImgFileUrl(dcConttLang.getImgFileUrl());
			dcContt.setImgFileNm(dcConttLang.getImgFileNm());
			dcContt.setImgAltrtvCont(dcConttLang.getImgAltrtvCont());
			dcContt.setMoviImgUrl(dcConttLang.getMoviImgUrl());
			dcContt.setMoviImgAltrtvCont(dcConttLang.getMoviImgAltrtvCont());
			dcContt.setHtmlCont(dcConttLang.getHtmlCont());
			dcContt.setImgDscr(dcConttLang.getImgDscr());
			dcContt.setImgMapCont(dcConttLang.getImgMapCont());
		}else{
			dcContt.setConttNm("");
			dcContt.setImgFileUrl("");
			dcContt.setImgFileNm("");
			dcContt.setImgAltrtvCont("");
			dcContt.setMoviImgUrl("");
			dcContt.setMoviImgAltrtvCont("");
			dcContt.setHtmlCont("");
			dcContt.setImgDscr("");
			dcContt.setImgMapCont("");
		}
	}
	
	private void changeDspCtgryCnncGodLang(God god, GodLangbyGodNm godLang){
		// 대체변환 안함.
		if(godLang != null ){
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
	
	private void changeDspPromtLang(DspPromt promt, DspPromtLang promtLang){
		// 대체변환 안함.
		if(promtLang != null){
			promt.setPromtNm(promtLang.getPromtNm());
//			promt.setPcImgFileNm(promtLang.getPcImgFileNm());
//			promt.setPcImgFileUrl(promtLang.getPcImgFileUrl());
//			promt.setPcImgAltrtvCont(promtLang.getPcImgAltrtvCont());
//			promt.setMobileImgFileNm(promtLang.getMobileImgFileNm());
//			promt.setMobileImgFileUrl(promtLang.getMobileImgFileUrl());
//			promt.setMobileImgAltrtvCont(promtLang.getMobileImgAltrtvCont());
		}else{
			promt.setPromtNm("");
//			promt.setPcImgFileNm("");
//			promt.setPcImgFileUrl("");
//			promt.setPcImgAltrtvCont("");
//			promt.setMobileImgFileNm("");
//			promt.setMobileImgFileUrl("");
//			promt.setMobileImgAltrtvCont("");
		}
	}
	
	private void changeDspStrndLang(DspStrnd strnd, DspStrndLang strndLang){
		// 대체변환 안함.
		if(strndLang != null){
			strnd.setStrndNm(strndLang.getStrndNm());
			strnd.setStrndDscr(strndLang.getStrndDscr());
			strnd.setRprstImgFileUrl(strndLang.getRprstImgFileUrl());
			strnd.setRprstImgFileNm(strndLang.getRprstImgFileNm());
			strnd.setRprstImgAltrtvCont(strndLang.getRprstImgAltrtvCont());
		}else{
			strnd.setStrndNm("");
			strnd.setStrndDscr("");
			strnd.setRprstImgFileUrl("");
			strnd.setRprstImgFileNm("");
			strnd.setRprstImgAltrtvCont("");
		}
	}
	
	//changeDspStrndLang 확장함수 (for brnd_nm from sys_brnd & sys_brnd_lang)
	private void changeDspStrndBrndLang(DspStrnd strnd, DspStrndLang strndLang, SysBrnd brnd, SysBrndLang brndLang){
		// changeDspStrndLang 의 DspStrnd & DspStrndLang 테이블에 브랜드명 추가 조회시, 따로 sysBrnd & sysBrndLang 빈을 이용한 추가 정보 언어변환 
		changeDspStrndLang(strnd, strndLang);
		if(brndLang != null){
			brnd.setBrndNm(brndLang.getBrndNm());
		}else{
			brnd.setBrndNm("");
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
