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
 * @since       2015. 7. 24       
 */
package com.plgrim.ncp.biz.display.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspBrndCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryRelate;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyTagResve;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResve;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNoti;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.base.repository.dsp.DspBrndCtgryRepository;
import com.plgrim.ncp.base.repository.dsp.DspCnrTmplatInfoCnncRepository;
import com.plgrim.ncp.base.repository.dsp.DspCtgryLangRepository;
import com.plgrim.ncp.base.repository.dsp.DspCtgryRelateRepository;
import com.plgrim.ncp.base.repository.dsp.DspCtgryRepository;
import com.plgrim.ncp.biz.display.data.DspCtgryBoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryCnncGodBoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryCnncGodExt;
import com.plgrim.ncp.biz.display.data.DspCtgryResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryResultMbExt;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.DspCtgrySearchFoDTO;
import com.plgrim.ncp.biz.display.exception.NotUpdatedDspYnException;
import com.plgrim.ncp.biz.display.repository.DisplayCategoryCnncGodRepository;
import com.plgrim.ncp.biz.display.repository.DisplayCategoryRepository;
import com.plgrim.ncp.biz.display.repository.DisplayTemplateRepository;
import com.plgrim.ncp.biz.display.result.CategoryChackResult;
import com.plgrim.ncp.biz.display.result.DspCtgryBoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryCnncGodExcelResult;
import com.plgrim.ncp.biz.display.result.DspCtgryFoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryLNBResult;
import com.plgrim.ncp.biz.display.result.GodIconFoResult;
import com.plgrim.ncp.biz.display.result.GodTagResveFoResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DisplayCategoryService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DisplayCategoryRepository displayRepository;
	
	@Autowired
	DspCtgryRepository dspCtgryRepository;
	
	@Autowired
	DspCtgryLangRepository dspCtgryLangRepository;
	
	@Autowired
	DisplayCategoryCnncGodRepository displayCategoryCnncGodRepository; 
		
	@Autowired
	DspBrndCtgryRepository dspBrndCtgryRepository; 
	
	@Autowired
	DisplayTemplateRepository displayTemplateRepository;
	
	@Autowired
	DspCnrTmplatInfoCnncRepository dspCnrTmplatInfoCnncRepository;
	
	@Autowired
	DspCtgryRelateRepository dspCtgryRelateRepository; 
	
	@Value("${ncp_web_bo.image.display.category.upload.path}")
	String categoryUploadPath;
	
	@Value("${ncp_web_bo.image.display.category.http.path}")
	String categoryHttpPath;
	
	@Value("${ncp_web_bo.image.display.contents.path}")
	String contentPath;
	
	@Value("${ncp_base.image.base.upload.temp.path}")
	String uploadTempPath;
	
	// BRAND 상품리스트 - 카테고리 타입 정의
	String[] specialCtgryTypesA = {"1", "2", "3", "4"};
	String[] specialCtgryTypesB = {"2", "3", "4"};
	String[] specialCtgryTypesC = {"1", "2", "3"};
	String[] specialCtgryTypesD = {"1", "3", "4"};
	
	String[] specialCtgryTypesDpth1 = {"1"};
	String[] specialCtgryTypesDpth2 = {"2"};
	String[] specialCtgryTypesDpth3 = {"3"};
	String[] specialCtgryTypesDpth4 = {"4"};

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
	public DspCtgryFoResult selectDisplayCategoryInfo(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception {
		DspCtgryFoResult result = displayRepository.selectDisplayCategoryInfo(pk, searchDTO);

		if (result != null && result.getDspCtgry() != null) {

			DspCtgry dspCtgry = result.getDspCtgry();
			// LANGUAGE Control
			String lang = pk.getLang();
			if (!lang.equals("KOR")) {
				DspCtgryLang dspCtgryLang = result.getDspCtgryLang();
				changeDspCtgryLnag(dspCtgry, dspCtgryLang);
			}

			if (searchDTO.getHasGodList().equals("Y")) {
				String dspCtgryNo = searchDTO.getDspCtgry().getDspCtgryNo();
				DspCtgrySearchFoDTO sDTO = new DspCtgrySearchFoDTO();
				if (dspCtgryNo.length() >= 9) {
					DspCtgry dC = new DspCtgry();
					dC.setDspCtgryNo(dspCtgryNo);
					sDTO.setDspCtgry(dC);
					sDTO.setSpecial(searchDTO.getSpecial());
					sDTO.setEmpYn(searchDTO.getEmpYn());
					sDTO.setSearchConditionBrandIds(searchDTO.getSearchConditionBrandIds());
					sDTO.setNewGodCondition(searchDTO.getNewGodCondition());
					sDTO.setSpcPrmTp(searchDTO.getSpcPrmTp());
					sDTO.setPrcSectCd(searchDTO.getPrcSectCd());

					// 카테고리연결 상품번호 설정(필터에 이용)
					List<String> godNoList = displayRepository.selectTestGodList(pk, sDTO);

					StringBuilder sb = new StringBuilder();
					for (int c = 0; c < godNoList.size(); c++) {
						String gNo = godNoList.get(c);
						if (c != 0) {
							sb.append(" ");
						}
						sb.append(gNo);
					}
					// 오픈 API 호출 값
					searchDTO.setGodNos(sb.toString());
				}
			}
		}

		return result;
	}
	
	public DspCtgryFoResult selectDisplayCategoryFoInfo(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception {

		DspCtgryFoResult result = displayRepository.selectDisplayCategoryFoInfo(pk, searchDTO);

		if (result != null && result.getDspCtgry() != null) {

			DspCtgry dspCtgry = result.getDspCtgry();
			// LANGUAGE Control
			String lang = pk.getLang();
			if (!lang.equals("KOR")) {
				DspCtgryLang dspCtgryLang = result.getDspCtgryLang();
				changeDspCtgryLnag(dspCtgry, dspCtgryLang);
			}
		}

		return result;
	}
	public DspCtgryFoResult selectDisplayCategoryGodSortInfo(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception {
		DspCtgryFoResult result = new DspCtgryFoResult();
		result = displayRepository.selectDspCtgryGodSortFo(pk, searchDTO);
		
		return result;
	}

		
		
	public List<DspCtgryFoResult> selectSubDisplayCategoryList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		List<DspCtgryFoResult> result = new ArrayList<DspCtgryFoResult>();

		List<DspCtgryFoResult> dspCtgryList = displayRepository.selectSubDisplayCategoryList(pk, searchDTO);
		
		// 상품이 존재하는지 비교는 통합몰 전시카테고리에서 만 수행한다.
		if(!searchDTO.getDspCtgryTreeRootDspCtgryNo().startsWith("DXM")){
			for(DspCtgryFoResult dspCtgryResult:dspCtgryList){	

				// LANGUAGE CONTROL
				String lang = pk.getLang();
				if(!lang.equals("KOR")){
					DspCtgryLang dspCtgryLang = dspCtgryResult.getDspCtgryLang();
					DspCtgry dspCtgry = dspCtgryResult.getDspCtgry();
					changeDspCtgryLnag(dspCtgry, dspCtgryLang);
				}
				result.add(dspCtgryResult);
			}
			return result;
		}
		
		List<DspCtgryFoResult> srcLeafCtgryList = displayRepository.selectGodExistDisplayCategroyList(pk, searchDTO);
		List<DspCtgryFoResult> leafCtgryList = new ArrayList<DspCtgryFoResult>();
		for(DspCtgryFoResult srcLeafCtgry:srcLeafCtgryList){
			for(DspCtgryFoResult dspCtgryResult:dspCtgryList){	
				if(dspCtgryResult.getDspCtgry().getLeafCtgryYn().equals("Y")){
					if(dspCtgryResult.getDspCtgry().getDspCtgryNo().equals(srcLeafCtgry.getDspCtgry().getDspCtgryNo())){
						leafCtgryList.add(srcLeafCtgry);
						break;
					}
				}
			}
		}
		
		for(DspCtgryFoResult dspCtgryResult:dspCtgryList){	
			// 구분자는 상품유무와 관계없이 추가시킨다.
			if(dspCtgryResult.getDspCtgry().getCtgryDivLneYn().equals("Y")){
				result.add(dspCtgryResult);
			}else{
				String dcNo = dspCtgryResult.getDspCtgry().getDspCtgryNo();
				for(DspCtgryFoResult leafCtgry:leafCtgryList){
					String leafCtgryNo = leafCtgry.getDspCtgry().getDspCtgryNo();
					if(leafCtgryNo.startsWith(dcNo)){
						// LANGUAGE CONTROL
						String lang = pk.getLang();
						if(!lang.equals("KOR")){
							DspCtgryLang dspCtgryLang = dspCtgryResult.getDspCtgryLang();
							DspCtgry dspCtgry = dspCtgryResult.getDspCtgry();
							changeDspCtgryLnag(dspCtgry, dspCtgryLang);
						}
						result.add(dspCtgryResult);
						break;
					}
				}
			}
		}
		return result;
	}
	
	public List<DspCtgryFoResult> selectSubDisplayCategoryNewList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		
		List<DspCtgryFoResult> resultList = new ArrayList<DspCtgryFoResult>();
		List<DspCtgryFoResult> result = displayRepository.selectSubDisplayCategoryList(pk, searchDTO);
		for(DspCtgryFoResult dspCtgryResult:result){

			DspCtgrySearchFoDTO dto = new DspCtgrySearchFoDTO();
			dto.setDspCtgry(dspCtgryResult.getDspCtgry());
//			dto.setNewGodCondition(searchDTO.getNewGodCondition());
//			if(dspCtgryResult.getDspCtgry().getCtgryDpthCd().equals("2")){
				if(displayRepository.selectDspCtgryConnGodTotCnt(pk, dto) > 0){
					// LANGUAGE CONTROL
					String lang = pk.getLang();
					if(!lang.equals("KOR")){
						DspCtgryLang dspCtgryLang = dspCtgryResult.getDspCtgryLang();
						DspCtgry dspCtgry = dspCtgryResult.getDspCtgry();
						changeDspCtgryLnag(dspCtgry, dspCtgryLang);
					}
					resultList.add(dspCtgryResult);
				}
//			}
		}
		
		return resultList;
	}
	
	public List<DspCtgryFoResult> selectSubDisplayCategoryNewList(List<DspCtgryFoResult> result, SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		
		List<DspCtgryFoResult> resultList = new ArrayList<DspCtgryFoResult>();

		for(DspCtgryFoResult dspCtgryResult:result){
			
			DspCtgrySearchFoDTO dto = new DspCtgrySearchFoDTO();
			dto.setDspCtgry(dspCtgryResult.getDspCtgry());
			dto.setNewGodCondition(searchDTO.getNewGodCondition());
			if(dspCtgryResult.getDspCtgry().getCtgryDpthCd().equals("2")){
				if(displayRepository.selectDspCtgryConnGodTotCnt(pk, dto) > 0){
					// LANGUAGE CONTROL
					String lang = pk.getLang();
					if(!lang.equals("KOR")){
						DspCtgryLang dspCtgryLang = dspCtgryResult.getDspCtgryLang();
						DspCtgry dspCtgry = dspCtgryResult.getDspCtgry();
						changeDspCtgryLnag(dspCtgry, dspCtgryLang);
					}
					resultList.add(dspCtgryResult);
				}
			}
		}
		
		return resultList;
	}
	
	public List<DspCtgryFoResult> selectSubDisplayCategorySpecialList(List<DspCtgryFoResult> result, SystemPK pk, DspCtgrySearchFoDTO searchDTO, String type) throws Exception{
		String parentDspCtgryNo = searchDTO.getDspCtgry().getDspCtgryNo();
		
//		   >. A타입(대+중+소) : A_TP_LAG_MID_SML
//		   >. B타입(대+중) : B_TP_LAG_MID
//		   >. C타입(대+소) : C_TP_LAG_SML
//		   >. D타입(중+소) : D_TP_MID_SML	
		String[] specialCtgryTypes = null;
		if(type.equals("A_TP_LAG_MID_SML_DTL")){	// 대-중-소-세
			specialCtgryTypes = specialCtgryTypesA;
		}else if(type.equals("B_TP_MID_SML_DTL")){	// 중-소-세
			specialCtgryTypes = specialCtgryTypesB;
		}else if(type.equals("C_TP_LAG_MID_SML")){ // 대-중-소
			specialCtgryTypes = specialCtgryTypesC;
		}else if(type.equals("D_TP_LAG_SML_DTL")){ // 대-소-세
			specialCtgryTypes = specialCtgryTypesD;
		}else if(type.equals("D1")){ // 대
			specialCtgryTypes = specialCtgryTypesDpth1;
		}else if(type.equals("D2")){ // 중
			specialCtgryTypes = specialCtgryTypesDpth2;
		}else if(type.equals("D3")){ // 소
			specialCtgryTypes = specialCtgryTypesDpth3;
		}else if(type.equals("D4")){ // 세
			specialCtgryTypes = specialCtgryTypesDpth4;
		}else{
			specialCtgryTypes = specialCtgryTypesA;
		}
		searchDTO.setSpecialCtgryTypes(specialCtgryTypes);
		
		List<DspCtgryFoResult> resultList = new ArrayList<DspCtgryFoResult>();

		List<DspCtgryFoResult> oldList = new ArrayList<DspCtgryFoResult>();
		oldList.addAll(result);
		
		for(int d = 0; d < specialCtgryTypes.length; d++){
			String dpth = specialCtgryTypes[d];
			List<DspCtgryFoResult> rList = new ArrayList<DspCtgryFoResult>();
			int oldListSize = oldList.size();
			for(int i = oldListSize - 1; i >= 0; i --){
				DspCtgryFoResult old = oldList.get(i);
				String oldDpth = old.getDspCtgry().getCtgryDpthCd();
				if(dpth.equals(oldDpth)){
					rList.add(0, oldList.remove(i));
				}
			}
			
			int rListSize = rList.size();
			for(int i = rListSize - 1; i >= 0; i --){
				DspCtgryFoResult dspCtgryResult = rList.get(i);
				// Special(OUTLET/BRAND)에 맞는 상품이 있는 카테고리만 필터링한다.
				if(d > 0){
						String upperNo = dspCtgryResult.getDspCtgry().getDspCtgryNo().substring(0, 3+((Integer.parseInt(specialCtgryTypes[d-1]) * 3)));
						dspCtgryResult.setBrandUpperDspCtgryNo(upperNo);
				}
			}
			resultList.addAll(rList);
		}
		searchDTO.getDspCtgry().setDspCtgryNo(parentDspCtgryNo);
		
		return resultList;
	}
	
	public List<DspCtgryFoResult> selectSubDisplayCategorySpecialNewList(List<DspCtgryFoResult> result, SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		
		List<DspCtgryFoResult> resultList = new ArrayList<DspCtgryFoResult>();

		for(DspCtgryFoResult dspCtgryResult:result){
			
			DspCtgrySearchFoDTO dto = new DspCtgrySearchFoDTO();
			dto.setDspCtgry(dspCtgryResult.getDspCtgry());
			dto.setNewGodCondition(searchDTO.getNewGodCondition());
			dto.setSpecial(searchDTO.getSpecial());
			String dpth = dspCtgryResult.getDspCtgry().getCtgryDpthCd();
			if(dpth.equals("1") || dpth.equals("2")){
				if(displayRepository.selectDspCtgryConnGodTotCnt(pk, dto) > 0){
					// LANGUAGE CONTROL
					String lang = pk.getLang();
					if(!lang.equals("KOR")){
						DspCtgryLang dspCtgryLang = dspCtgryResult.getDspCtgryLang();
						DspCtgry dspCtgry = dspCtgryResult.getDspCtgry();
						changeDspCtgryLnag(dspCtgry, dspCtgryLang);
					}
					resultList.add(dspCtgryResult);
				}
			}
		}
		
		return resultList;
	}
	
	public List<DspCtgryFoResult> selectSubDisplayCategoryGnbList(List<DspCtgryFoResult> result, SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		
		List<DspCtgryFoResult> resultList = new ArrayList<DspCtgryFoResult>();

		for(DspCtgryFoResult dspCtgryResult:result){
			
			DspCtgrySearchFoDTO dto = new DspCtgrySearchFoDTO();
			dto.setDspCtgry(dspCtgryResult.getDspCtgry());
			dto.setNewGodCondition(searchDTO.getNewGodCondition());
			String dpth = dspCtgryResult.getDspCtgry().getCtgryDpthCd();
			if(dpth.equals("1") || dpth.equals("2")){
				if(displayRepository.selectDspCtgryConnGodTotCnt(pk, dto) > 0){
					// LANGUAGE CONTROL
					String lang = pk.getLang();
					if(!lang.equals("KOR")){
						DspCtgryLang dspCtgryLang = dspCtgryResult.getDspCtgryLang();
						DspCtgry dspCtgry = dspCtgryResult.getDspCtgry();
						changeDspCtgryLnag(dspCtgry, dspCtgryLang);
					}
					resultList.add(dspCtgryResult);
				}
			}
		}
		
		return resultList;
	}
	/**
	 * 전시카테고리 Location Bar에서 사용될 전시카테고리 조회
	 * 
	 * @param pk			SystemPK
	 * @param searchDTO		검색조건
	 * @return				Location에 사용되는 전시카테고리 정보들.
	 * @throws Exception
	 */
	public List<DspCtgryFoResult> selectLocationDsplatyCategoryList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		
		List<DspCtgryFoResult> resultList = displayRepository.selectLocationDspCtgryList(pk, searchDTO);

		for(DspCtgryFoResult dspCtgryResult:resultList){
			// LANGUAGE CONTROL
			String lang = pk.getLang();
			if(!lang.equals("KOR")){
				DspCtgryLang dspCtgryLang = dspCtgryResult.getDspCtgryLang();
				DspCtgry dspCtgry = dspCtgryResult.getDspCtgry();
				changeDspCtgryLnag(dspCtgry, dspCtgryLang);
			}
		}

		return resultList;
	}
	
	/**
	 * 전신카테고리연결상품 전체수량
	 * @param pk			SystemPK
	 * @param searchDTO		검색조건
	 * @return				상품전체수량
	 */
	public int selectDisplayCategoryConnGoodTotCount(SystemPK pk,  DspCtgrySearchFoDTO searchDTO) throws Exception{
		// 전체 상풍 수
		return displayRepository.selectDspCtgryConnGodTotCnt(pk, searchDTO);
	}
	/**
	 * 전시카테고리연결상품 목록
	 * 
	 * @param pk			SystemPK
	 * @param searchDTO		검색조건
	 * @return				전시카테고리연결 상품 목록
	 * @throws Exception
	 */
	public List<DspCtgryGodFoResult> selectDisplayCategoryConnGoodList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{

	    /************************************
	     * SR : #22516(24208)
	     * 판매지수 (Sales Index) 전시/브랜드 카테고리 Best상품 신규로직반영 및 AB 테스트를 위한 RecommendService로 이동
	    ************************************/
	    // 베스트 목록 경우에만 신규로직 적용.
	    List<DspCtgryGodFoResult> godList = new ArrayList<>();        
        godList = displayRepository.selectDspCtgryConnGodList(pk, searchDTO);

		List<String> godNoList = new ArrayList<String>();

		if(godList != null && godList.size() > 0){
			for(int i = 0; i < godList.size(); i ++){
				DspCtgryGodFoResult godResult = godList.get(i);
				godNoList.add(godResult.getGod().getGodNo());

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

				// GRP_SESON_CD 체크 해당년도 제품의 SESON GRP 코드만 사용
//				String mDate = godResult.getMnfcturDate();
//				if(mDate != null){
//					String dd = mDate.substring(3,4);
//					String erpNo = godResult.getGod().getErpGodNo();
//					String sNo = erpNo.substring(2,3);
//					if(!dd.equals(sNo)){
//						godResult.getGod().setSesonGrpCd(null);
//					}
//				}
			}
			// 상품예약태그
			List<GodTagResveFoResult> godTagResveList = displayRepository.selectGodResve2(pk, godNoList);
			List<GodIconFoResult> godIconList = displayRepository.selectGodIconList2(godNoList);

			for(int i = 0; i < godList.size(); i ++){
				DspCtgryGodFoResult godResult = godList.get(i);
				God god = godResult.getGod();
				// LANGUAGE CONTROL
				String lang = pk.getLang();
				if(!lang.equals("KOR")){
					GodLangbyGodNm godLang = godResult.getGodLangbyGodNm();
					changeDspCtgryCnncGodLang(god, godLang);
				}

				for(GodTagResveFoResult tag:godTagResveList){
					String godNo = tag.getGodNo();
					if(god.getGodNo().equals(godNo)){
						GodTagResve godTagResve = tag.getGodTagResve();
						GodLangbyTagResve godLangbyTagResve = tag.getGodLangbyTagResve();
						if(!lang.equals("KOR")){
							changeDspCtgryCnncGodLang(god, godTagResve, godLangbyTagResve);
						}
						else if(godTagResve != null && godTagResve.getTagNm() != null){
							god.setTagNm(godTagResve.getTagNm());
						}
						break;
					}
				}

				for(GodIconFoResult godIcon:godIconList){
					String godNo = godIcon.getGodIconCnnc().getGodNo();
					if(god.getGodNo().equals(godNo)){
						List<GodIconFoResult> iconList = godResult.getGodIconList();
						if(iconList == null){
							iconList = new ArrayList<GodIconFoResult>();
							iconList.add(godIcon);
							godResult.setGodIconList(iconList);
						}else{
							iconList.add(godIcon);
						}
					}
				}
			}
		}
		return godList;
	}
	/**
	 * 전시카테고리연결상품 목록 모바일
	 *
	 * @param pk			SystemPK
	 * @param searchDTO		검색조건
	 * @return				전시카테고리연결 상품 목록
	 * @throws Exception
	 */
	public List<DspCtgryGodFoResult> selectDisplayCategoryConnGoodMobileAsList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		List<DspCtgryGodFoResult> godList = displayRepository.selectDspCtgryConnGodList(pk, searchDTO);
		List<String> godNoList = new ArrayList<String>();
		
		if(godList != null && godList.size() > 0){
			for(int i = 0; i < godList.size(); i ++){
				DspCtgryGodFoResult godResult = godList.get(i);
				godNoList.add(godResult.getGod().getGodNo());

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
				
				// GRP_SESON_CD 체크 해당년도 제품의 SESON GRP 코드만 사용
//				String mDate = godResult.getMnfcturDate();
//				if(mDate != null){
//					String dd = mDate.substring(3,4);
//					String erpNo = godResult.getGod().getErpGodNo();
//					String sNo = erpNo.substring(2,3);
//					if(!dd.equals(sNo)){
//						godResult.getGod().setSesonGrpCd(null);
//					}
//				}
			}
			// 상품예약태그
			List<GodTagResveFoResult> godTagResveList = displayRepository.selectGodResve2(pk, godNoList);
			List<GodIconFoResult> godIconList = displayRepository.selectGodIconList2(godNoList);
			
			for(int i = 0; i < godList.size(); i ++){
				DspCtgryGodFoResult godResult = godList.get(i);
				God god = godResult.getGod();
				// LANGUAGE CONTROL
				String lang = pk.getLang();
				if(!lang.equals("KOR")){
					GodLangbyGodNm godLang = godResult.getGodLangbyGodNm();
					changeDspCtgryCnncGodLang(god, godLang);
				}
				
				for(GodTagResveFoResult tag:godTagResveList){
					String godNo = tag.getGodNo();
					if(god.getGodNo().equals(godNo)){
						GodTagResve godTagResve = tag.getGodTagResve();
						GodLangbyTagResve godLangbyTagResve = tag.getGodLangbyTagResve();
						if(!lang.equals("KOR")){
							changeDspCtgryCnncGodLang(god, godTagResve, godLangbyTagResve);
						}
						else if(godTagResve != null && godTagResve.getTagNm() != null){
							god.setTagNm(godTagResve.getTagNm());
						}
						break;
					}
				}
				
				for(GodIconFoResult godIcon:godIconList){
					String godNo = godIcon.getGodIconCnnc().getGodNo();
					if(god.getGodNo().equals(godNo)){
						List<GodIconFoResult> iconList = godResult.getGodIconList();
						if(iconList == null){
							iconList = new ArrayList<GodIconFoResult>();
							iconList.add(godIcon);
							godResult.setGodIconList(iconList);
						}else{
							iconList.add(godIcon);
						}
					}
				}
			}
		}
		return godList;
	}
	
	public List<DspCtgryGodFoResult> selectDisplayCategoryConnGoodMobileNewBestList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		List<DspCtgryGodFoResult> godList = displayRepository.selectDspCtgryConnSaleIdxGodList(pk, searchDTO);
		List<String> godNoList = new ArrayList<String>();

		if(godList != null && godList.size() > 0){
			for(int i = 0; i < godList.size(); i ++){
				DspCtgryGodFoResult godResult = godList.get(i);
				godNoList.add(godResult.getGod().getGodNo());

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

				// GRP_SESON_CD 체크 해당년도 제품의 SESON GRP 코드만 사용
//				String mDate = godResult.getMnfcturDate();
//				if(mDate != null){
//					String dd = mDate.substring(3,4);
//					String erpNo = godResult.getGod().getErpGodNo();
//					String sNo = erpNo.substring(2,3);
//					if(!dd.equals(sNo)){
//						godResult.getGod().setSesonGrpCd(null);
//					}
//				}
			}
			// 상품예약태그
			List<GodTagResveFoResult> godTagResveList = displayRepository.selectGodResve2(pk, godNoList);
			List<GodIconFoResult> godIconList = displayRepository.selectGodIconList2(godNoList);

			for(int i = 0; i < godList.size(); i ++){
				DspCtgryGodFoResult godResult = godList.get(i);
				God god = godResult.getGod();
				// LANGUAGE CONTROL
				String lang = pk.getLang();
				if(!lang.equals("KOR")){
					GodLangbyGodNm godLang = godResult.getGodLangbyGodNm();
					changeDspCtgryCnncGodLang(god, godLang);
				}

				for(GodTagResveFoResult tag:godTagResveList){
					String godNo = tag.getGodNo();
					if(god.getGodNo().equals(godNo)){
						GodTagResve godTagResve = tag.getGodTagResve();
						GodLangbyTagResve godLangbyTagResve = tag.getGodLangbyTagResve();
						if(!lang.equals("KOR")){
							changeDspCtgryCnncGodLang(god, godTagResve, godLangbyTagResve);
						}
						else if(godTagResve != null && godTagResve.getTagNm() != null){
							god.setTagNm(godTagResve.getTagNm());
						}
						break;
					}
				}

				for(GodIconFoResult godIcon:godIconList){
					String godNo = godIcon.getGodIconCnnc().getGodNo();
					if(god.getGodNo().equals(godNo)){
						List<GodIconFoResult> iconList = godResult.getGodIconList();
						if(iconList == null){
							iconList = new ArrayList<GodIconFoResult>();
							iconList.add(godIcon);
							godResult.setGodIconList(iconList);
						}else{
							iconList.add(godIcon);
						}
					}
				}
			}
		}
		return godList;
	}

	public List<DspCtgryGodFoResult> selectDisplayCategoryConnSpecialLimitGoodList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		List<DspCtgryGodFoResult> returnList = new ArrayList<DspCtgryGodFoResult>();
		List<DspCtgryGodFoResult> godList = displayRepository.selectDspCtgryConnSpecialLimitGodList(pk, searchDTO);

		if(godList != null && godList.size() > 0){
			for(int i = 0; i < godList.size(); i ++){
				DspCtgryGodFoResult godResult = godList.get(i);

				// LANGUAGE CONTROL
				String lang = pk.getLang();
				if(!lang.equals("KOR")){
					God god = godResult.getGod();
					GodLangbyGodNm godLang = godResult.getGodLangbyGodNm();
					changeDspCtgryCnncGodLang(god, godLang);
				}
				
				List<GodImg> godImgList = displayRepository.selectGodImgList(godResult.getGod().getGodNo());
				godResult.setGodImgList(godImgList);
				List<GodIconFoResult> godIconList = displayRepository.selectGodIconList(godResult.getGod().getGodNo());
				godResult.setGodIconList(godIconList);
				
				// 예약태그
				GodTagResveFoResult godTagResveResult = displayRepository.selectGodResve(pk, godResult.getGod().getGodNo());
				if(!lang.equals("KOR")){
					God god = godResult.getGod();
					GodTagResve godTagResve = godTagResveResult.getGodTagResve();
					GodLangbyTagResve godLangbyTagResve = godTagResveResult.getGodLangbyTagResve();
					changeDspCtgryCnncGodLang(god, godTagResve, godLangbyTagResve);
				}
				returnList.add(godResult);
			}
		}
		
		return returnList;
	}
	
	public List<DspCtgryGodFoResult> selectBestNewGodList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		List<DspCtgryGodFoResult> returnList = new ArrayList<DspCtgryGodFoResult>();
		List<DspCtgryGodFoResult> godList = displayRepository.selectBestGodList(pk, searchDTO);
		if(godList != null && godList.size() > 0){
			for(int i = 0; i < godList.size(); i ++){
				DspCtgryGodFoResult godResult = godList.get(i);

				// LANGUAGE CONTROL
				String lang = pk.getLang();
				if(!lang.equals("KOR")){
					God god = godResult.getGod();
					GodLangbyGodNm godLang = godResult.getGodLangbyGodNm();
					changeDspCtgryCnncGodLang(god, godLang);
				}
				
//				List<GodImg> godImgList = displayRepository.selectGodImgList(godResult.getGod().getGodNo());
//				godResult.setGodImgList(godImgList);
				List<GodIconFoResult> godIconList = displayRepository.selectGodIconList(godResult.getGod().getGodNo());
				godResult.setGodIconList(godIconList);
				
				// 예약태그
				GodTagResveFoResult godTagResveResult = displayRepository.selectGodResve(pk, godResult.getGod().getGodNo());
				if(!lang.equals("KOR")){
					God god = godResult.getGod();
					GodTagResve godTagResve = godTagResveResult.getGodTagResve();
					GodLangbyTagResve godLangbyTagResve = godTagResveResult.getGodLangbyTagResve();
					changeDspCtgryCnncGodLang(god, godTagResve, godLangbyTagResve);
				}
				returnList.add(godResult);
			}
		}
		return returnList;
	}

	public List<SysPopupNoti> selectMainPopupList(HashMap<String,String> map) throws Exception 
	{
		return displayRepository.selectSysPopupNoti(map);
	}
	
	/**
	 * Select my brand.
	 *
	 * @param dspCtgryNos the dsp ctgry nos
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgry> selectMyBrand(List<String> dspCtgryNos) throws Exception 
	{
		return displayRepository.selectMyBrand(dspCtgryNos);
	}
	
	
	/**
	 * 전시 카테고리 조회.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return List 카테고리 트리 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 13
	 */
	public List<DspCtgryBoResult> selectCategoryTree(DspCtgryBoDTO dspCtgryBoDTO) throws Exception{
		return displayRepository.selectCategoryTree(dspCtgryBoDTO);
	}

	/**
	 * 전시 카테고리 검색어 조회.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return List 카테고리 검색어 조회 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public List<DspCtgryBoResult> selectCtgryList(DspCtgryBoDTO dspCtgryBoDTO, PageParam pageParam) throws Exception{
		return displayRepository.selectCtgryList(dspCtgryBoDTO, pageParam);
	}
	
	/**
	 * 전시 카테고리 검색어 조회 총 count.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public int selectCtgryListTotalCount(DspCtgryBoDTO dspCtgryBoDTO) throws Exception{
		return displayRepository.selectCtgryListTotalCount(dspCtgryBoDTO);
	}
	
	/**
	 * 전시 카테고리 검색어 조회. (카테고리번호로 조회
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return List 카테고리 검색어 조회 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public List<DspCtgryBoResult> selectCtgryListByCtgryNo(DspCtgryBoDTO dspCtgryBoDTO, PageParam pageParam) throws Exception{
		return displayRepository.selectCtgryListByCtgryNo(dspCtgryBoDTO, pageParam);
	}
	
	/**
	 * 전시 카테고리 검색어 조회 총 count. (카테고리번호로 조회)
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public int selectCtgryListByCtgryNoTotalCount(DspCtgryBoDTO dspCtgryBoDTO) throws Exception{
		return displayRepository.selectCtgryListByCtgryNoTotalCount(dspCtgryBoDTO);
	}
	
	public String selectDspCtgryNo(String upperDspCtgryNo) throws Exception {
		return displayRepository.selectDspCtgryNo(upperDspCtgryNo);
	}
	
	public void insertDspCtgry(DspCtgry dspCtgry) throws Exception {
		displayRepository.insertDspCtgry(dspCtgry);
	}
	
	public void saveDspCtgryLang(DspCtgryLang lang) throws Exception {
		displayRepository.saveDspCtgryLang(lang);
	}
	
	public int updateDspCtgryInfo(DspCtgry dspCtgry) throws Exception {
		return displayRepository.updateDspCtgryInfo(dspCtgry);
	}
	
	/**
	 * 카테고리 삭제.
	 * 
	 * <p/>
	 * 
	 * 삭제여부 컬럼을 "Y"로 update한다.
	 *
	 * @param dspCtgryBoDTO 카테고리 관리 DTO
	 * @return int 처리결과수
	 * @throws Exception the exception
	 * @since 2015. 4. 15
	 */
	public int deleteCategoryInfo(DspCtgryBoDTO dspCtgryBoDTO) throws Exception {
		DspCtgry dspCtgry = dspCtgryBoDTO.getDspCtgry();
		dspCtgry.setDeleteYn("Y");
		
//		if(getStringService().isNotEmpty(dspCtgry.getLeafCtgryYn())&&"Y".equals(dspCtgry.getLeafCtgryYn())) {
//			//연결상품 삭제
//			displayCategoryCnncGodRepository.deleteCtgryCnncGodInfo(dspCtgry.getDspCtgryNo());
//		}
		return displayRepository.deleteDspCtgryInfo(dspCtgryBoDTO.getDspCtgry());
	}
	
	/**
	 * 하위 카테고리 목록 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list 수정할 grid 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 21
	 */
	public void updateCategoryList(List<DspCtgryBoDTO> list) throws Exception {
		DspCtgry dspCtgry = null;
		String loginId = BOSecurityUtil.getLoginId();
		
		if (list != null) {
			for(DspCtgryBoDTO dspCtgryBoDTO: list) {
				dspCtgry =  dspCtgryBoDTO.getDspCtgry();
				
				DspCtgry ctg = new DspCtgry();
				
				//전시여부 수정 여부 체크 및 하위카테고리 전시여부 수정
				updateDspYnCtgry(dspCtgry);
				
				ctg.setDspYn(dspCtgry.getDspYn());
				ctg.setDspCtgryNo(dspCtgry.getDspCtgryNo());
				ctg.setSortSeq(dspCtgry.getSortSeq());
				ctg.setSpcifyUrlDspYn(dspCtgry.getSpcifyUrlDspYn());	
				ctg.setUdterId(loginId);
				
				displayRepository.updateDspCtgryForGrid(ctg);
			}
		}
	}
	
	public void updateDspYnCtgry (DspCtgry dspCtgry) {
		//1) 상위카테고리의 전시여부 조회
		String upperDspYn = displayRepository.selectDspYnCtgry(dspCtgry.getUpperDspCtgryNo());
		//2) 대상카테고리의 전시여부 조회
		String curDspYn = displayRepository.selectDspYnCtgry(dspCtgry.getDspCtgryNo());
		//3) 변경할 전시여부
		String toDspYn = dspCtgry.getDspYn();
		
		//전시여부 변경 시
		if(StringService.isNotEmpty(toDspYn) && !curDspYn.equals(toDspYn)) {
			//상위카테고리 전시여부가 "Y"일 경우
			if(StringService.isNotEmpty(upperDspYn) && upperDspYn.equals("Y")) {
				//전시여부를 "N"으로 변경할 경우 하위카테고리도 모두 "N"으로 수정한다.
				if("N".equals(toDspYn)) {
					//하위카테고리도 모두 "N"으로 수정
					displayRepository.updateDspYnCtgry(dspCtgry);
				}
			}
			else {
				throw new NotUpdatedDspYnException(null);
			}
		}
	}
	
	/**
	 * 하위 카테고리 목록 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list 삭제할 grid 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 21
	 */
	public void deleteCategoryList(List<DspCtgryBoDTO> list) throws Exception {
		DspCtgry dspCtgry = null;
		
		if (list != null) {
			for(DspCtgryBoDTO dspCtgryBoDTO: list) {
				dspCtgry =  dspCtgryBoDTO.getDspCtgry();
				dspCtgry.setDeleteYn("Y");
				displayRepository.deleteDspCtgryInfo(dspCtgry);
			}
		}
	}
	
	/**
	 * 카테고리 연결 상품 목록 등록.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list 등록 대상 상품 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 23
	 */
	public String[] insertCategoryGodList(List<DspCtgryCnncGod> list) throws Exception {
		
		String[] result = new String[3];
		
		if(list != null&&list.size()>0) {
			String targetCtgryNo = list.get(0).getDspCtgryNo();
			
			//int maxSortSeq = displayCategoryCnncGodRepository.selectCtgryCnncGodMaxSortSeq(targetCtgryNo);
			int dfSortSeq = Integer.parseInt(DisplayEnum.DF_SORT_SEQ.toString());
			String loginId = BOSecurityUtil.getLoginId();
			
			//기존 등록상품 중복 체크
			List<String> godNoList = displayCategoryCnncGodRepository.selectCnncGodList(targetCtgryNo); 
			String godNo = null;
			String dupGod = "";

			int insCnt = 0;
			List<String> moveGodNo = new ArrayList<String>();
			DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO = new DspCtgryCnncGodBoDTO();
			
			for(DspCtgryCnncGod dspCtgryCnncGod: list) {
				godNo = dspCtgryCnncGod.getGodNo();
				
				if(StringService.isNotEmpty(godNo)&&godNoList.contains(godNo)) {
					dupGod += godNo + ",";
				} 
				else {
					//maxSortSeq++;
					dspCtgryCnncGod.setRprstGodYn(DisplayEnum.NO.toString());
					dspCtgryCnncGod.setSortSeq(dfSortSeq); //999
					if(dspCtgryCnncGod.getDspYn() == null || "".equals(dspCtgryCnncGod.getDspYn())
							|| !(DisplayEnum.YES.toString().equals(dspCtgryCnncGod.getDspYn()) || DisplayEnum.NO.toString().equals(dspCtgryCnncGod.getDspYn()))) {
						dspCtgryCnncGod.setDspYn(DisplayEnum.NO.toString()); //미전시
					}
					dspCtgryCnncGod.setBrndCtgrySortSeq(dfSortSeq);
					dspCtgryCnncGod.setBrndCtgryDspYn(DisplayEnum.NO.toString()); //미전시
					dspCtgryCnncGod.setRegtrId(loginId);
					dspCtgryCnncGod.setUdterId(loginId);
					displayCategoryCnncGodRepository.insertDspCtgryCnncGod(dspCtgryCnncGod);
					insCnt++;
					
					moveGodNo.add(godNo);
					if(dspCtgryCnncGodBoDTO.getDspCtgryCnncGod() == null){
						dspCtgryCnncGodBoDTO.setDspCtgryCnncGod(dspCtgryCnncGod);  //업데이트 사용자 정보셋팅을 위해 최초 한번만 셋팅
					}
				}
			}
			
			if(moveGodNo.size() > 0){ //등록할 아이템이 있을 경우
				//상품등록 카테고리 아울렛 여부 체크
				DspCtgry pCtg = new DspCtgry();
				pCtg.setDspCtgryNo(targetCtgryNo);
				DspCtgry ctg = dspCtgryRepository.selectDspCtgry(pCtg);
				if(ctg != null && ctg.getCtgrySectCd().equals("OTLT_CTGRY")) {
					//포인트적립여부 및 포인트 적립율, 무료수선, 포장, 매장픽업 정책 업데이트
					dspCtgryCnncGodBoDTO.setMoveGodNo(moveGodNo);
					displayCategoryCnncGodRepository.updateGodFreeRepairPsbYn(dspCtgryCnncGodBoDTO);
				}
			}
						
			result[0] = dupGod ;
			result[1] = ""+insCnt ;
		}
		return result;
	}
	
	/**
	 * 카테고리 연결 상품 목록 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list 수정대상 연결상품 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 23
	 */
	public void updateCategoryGodList(List<DspCtgryCnncGodBoDTO> list) throws Exception {
		DspCtgryCnncGod dspCtgryCnncGod;
		String loginId = BOSecurityUtil.getLoginId();
		if (list != null) {
			for(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO: list) {
				dspCtgryCnncGod = dspCtgryCnncGodBoDTO.getDspCtgryCnncGod();
				dspCtgryCnncGod.setUdterId(loginId);
				if(dspCtgryCnncGodBoDTO.getScDspBrndId() != null && !"".equals(dspCtgryCnncGodBoDTO.getScDspBrndId())) {
					if(dspCtgryCnncGod.getBrndCtgrySortSeq() == null || dspCtgryCnncGod.getBrndCtgrySortSeq() == 0) {
						dspCtgryCnncGod.setBrndCtgrySortSeq(999);
					}
					dspCtgryCnncGod.setSortSeq(null);
					dspCtgryCnncGod.setDspYn(null);
				}
				else {
					if(dspCtgryCnncGod.getSortSeq() == null || dspCtgryCnncGod.getSortSeq() == 0) {
						dspCtgryCnncGod.setSortSeq(999);
					}
					dspCtgryCnncGod.setBrndCtgrySortSeq(null);
					dspCtgryCnncGod.setBrndCtgryDspYn(null);
				}
				
				
				displayCategoryCnncGodRepository.updateCtgryCnncGodInfo(dspCtgryCnncGod);
			}
		}
	}
	
	/**
	 * 카테고리 연결 상품 목록 삭제.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list 삭제 대상 연결 상품 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 23
	 */
	public int deleteCategoryGodList(List<DspCtgryCnncGodBoDTO> list) throws Exception {
		int delCnt = 0;
		if (list != null) {
			DspCtgryCnncGod dspCtgryCnncGod;
			for(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO: list) {
				dspCtgryCnncGod = dspCtgryCnncGodBoDTO.getDspCtgryCnncGod();
				delCnt += displayCategoryCnncGodRepository.deleteDspCtgryCnncGod(dspCtgryCnncGod);
			}
		}
		return delCnt;
	}

	/**
	 * 상품복사.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgryCnncGodBoDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public int copyCtgryCnncGod(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		int result = 0;

		//2017년 12월 12일 기존 무료수선 정책 및 멤버쉽 정책 신규 추가		
		//아울렛에서 일반을 복사(불러오기) 시 멤버쉽 no, 0% 조회조건 - 원본 카테고리가 아울렛인지 확인하여 정책적용
		//[제외] 일반에서 아울렛을 복사(불러오기) 시 멤버쉽 no, 0%  조회조건 - 복사(목적지) 카테고리	
		//           => 아울렛에 있는 상품을 불러오는 경우 상품의 무료배송/무료수선 멤버쉽사용여부가 "NO" 로 설정되어 있음
		DspCtgry pCtg = new DspCtgry();
		pCtg.setDspCtgryNo(dspCtgryCnncGodBoDTO.getDspCtgryCnncGod().getDspCtgryNo());  
		DspCtgry ctg = dspCtgryRepository.selectDspCtgry(pCtg);
		if(ctg != null && ctg.getCtgrySectCd().equals("OTLT_CTGRY")) {
			displayCategoryCnncGodRepository.updateGodCtgryCopy(dspCtgryCnncGodBoDTO);
		}
				
		result = displayCategoryCnncGodRepository.copyCtgryCnncGod(dspCtgryCnncGodBoDTO);		
		
		return result;
	}
	
	/**
	 * 카테고리 연결상품 전시순서 업로드.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 10
	 */
	public List<DspCtgryCnncGodExt> updateSortSeqGod(List<DspCtgryCnncGod> list, String dspCtgryNo) throws Exception {
		List<DspCtgryCnncGodExt> errList = null;
		if (list != null) {
			String loginId = BOSecurityUtil.getLoginId();
			
			int res = 0;
			errList = new ArrayList<DspCtgryCnncGodExt>();
			for(DspCtgryCnncGod dspCtgryCnncGod: list) {
				dspCtgryCnncGod.setUdterId(loginId);
				dspCtgryCnncGod.setDspCtgryNo(dspCtgryNo);
				
				res = displayCategoryCnncGodRepository.updateSortSeqGod(dspCtgryCnncGod);
				if(res < 1) {
					DspCtgryCnncGodExt dspCtgryCnncGodExt = new DspCtgryCnncGodExt();
					dspCtgryCnncGodExt.setGodNo(dspCtgryCnncGod.getGodNo());
					dspCtgryCnncGodExt.setSortSeq(dspCtgryCnncGod.getSortSeq());
					dspCtgryCnncGodExt.setErrMsg("등록되지 않은 온라인품번입니다.");
					errList.add(dspCtgryCnncGodExt);
				}
				
			}
		}
		return errList;
	}
	

	/**
	 * 연관카테고리 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgryRelate [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 24
	 */
	public void insertCtgryRelate (DspCtgryRelate dspCtgryRelate) throws Exception {
		dspCtgryRelateRepository.insertDspCtgryRelate(dspCtgryRelate);
	}
	
	/**
	 * 연관카테고리 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspBrndCtgryMoviCnnc [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 24
	 */
	public void deleteCtgryRelate (DspCtgryRelate dspCtgryRelate) throws Exception {
		dspCtgryRelateRepository.deleteDspCtgryRelate(dspCtgryRelate);
	}
	
	
	
	
	/**
	 *  브랜드 베스트 상품 조회
	 *  
	 * @param brndBstGod
	 * @return
	 * @throws Exception
	 */
	/*
	public List<God> selectBrndBestGod(DspBrndBstGod brndBstGod) throws Exception {
		return displayRepository.selectBrndBestGod(brndBstGod);
	}
	*/
	public String getDspCtgrygodNoList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		// 카테고리연결 상품번호 설정(필터에 이용)
		List<String> godNoList = displayRepository.selectTestGodList(pk, searchDTO) ;
		StringBuilder sb = new StringBuilder();
		for (int c = 0; c < godNoList.size(); c ++) {
			String gNo = godNoList.get(c);
			if (c != 0) {
				sb.append(" ");
			}
			sb.append(gNo);
		}

		return sb.toString();
	}
	
	public Map<String, Object> getMbDspCtgrygodNoList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		// 카테고리연결 상품번호 설정(필터에 이용)
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<DspCtgryResultMbExt> godInfoList = displayRepository.selectMbDspCtgryCnncGodNoList(pk, searchDTO) ;
		List<String> godNoList = new ArrayList<String>();
		List<String> brndIdList = new ArrayList<String>();
		List<String> uniqueBrndList = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		
		if (godInfoList != null && godInfoList.size() > 0) {
			for (DspCtgryResultMbExt dspCtgryResultMbExt : godInfoList) {
				godNoList.add(dspCtgryResultMbExt.getGodNo());
				brndIdList.add(dspCtgryResultMbExt.getBrndId());
            }
			if (godNoList != null && godNoList.size() > 0) {
				for (int c = 0; c < godNoList.size(); c ++) {
					String gNo = godNoList.get(c);
					if (c != 0) {
						sb.append(" ");
					}
					sb.append(gNo);
				}
			}
			
			uniqueBrndList = new ArrayList<String>(new HashSet<String>(brndIdList));
			resultMap.put("godNos", sb.toString());
			resultMap.put("brndIdList", uniqueBrndList);
		}
		
		return resultMap;
	}
	
	public List<GodImg> selectMbGodImgList(List<String> godNos) throws Exception {
		return displayRepository.selectMbGodImgList(godNos) ;
	}
	
	/**
	 * 모바일용 Left Menu 브랜드카테고리 목록 조회.
	 *
	 * @param pk the pk
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryFoResult> selectLeftMenuCtgry(SystemPK pk,DspCtgrySearchFoDTO dto) throws Exception {
		return displayRepository.selectLeftMenuCtgry(pk, dto);
	}
	
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	
	private void changeDspCtgryLnag(DspCtgry dspCtgry, DspCtgryLang dspCtgryLang){
		//대체변환 안함.
		if(dspCtgryLang != null){
			dspCtgry.setDspCtgryNm(dspCtgryLang.getDspCtgryNm());
			dspCtgry.setRprstImgFileUrl(dspCtgryLang.getRprstImgFileUrl());
			dspCtgry.setRprstImgAltrtvCont(dspCtgryLang.getRprstImgAltrtvCont());
			dspCtgry.setRprstImgFileNm(dspCtgryLang.getRprstImgFileNm());
		}else{
			dspCtgry.setDspCtgryNm("");
			dspCtgry.setRprstImgFileUrl("");
			dspCtgry.setRprstImgAltrtvCont("");
			dspCtgry.setRprstImgFileNm("");
		}
	}
	
	private void changeDspCtgryCnncGodLang(God god, GodLangbyGodNm godLang){
		//대체변환 안함.
		if(godLang != null){
			god.setGodNm(godLang.getGodNm());
			god.setTagNm(godLang.getTagNm());
			god.setThemaTagNm(godLang.getThemaTagNm());
		}else{
			god.setGodNm("");
			god.setTagNm("");
			god.setThemaTagNm("");
		}
	}
	
	private void changeDspCtgryCnncGodLang(God god,  GodTagResve godTagResve,GodLangbyTagResve godLangbyTagResve){
		//대체변환 안함.
		if(godTagResve != null){
			if(godLangbyTagResve != null && godLangbyTagResve.getTagNm() != null){
				god.setTagNm(godLangbyTagResve.getTagNm());
//			}else if(godTagResve != null && godTagResve.getTagNm() != null){
//				god.setTagNm(godTagResve.getTagNm());
			}else{
				god.setTagNm("");
			}
		}
	}
	
	private boolean isExistInList(String godNo,List<DspCtgryGodFoResult> list ){
		boolean isExist = false;
		for(DspCtgryGodFoResult god:list){
			String g = god.getGod().getGodNo();
			if(godNo.equals(g)){
				isExist = true;
				break;
			}
		}
		return isExist;
	}

	/**
	 * [BO] 상품이 존재하는 카테고리 목록 조회
	 * @param dspCtgryBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<DspCtgryBoResult> selectGodExistDspCtgryBoList(DspCtgryBoDTO dspCtgryBoDTO) throws Exception{
		return displayRepository.selectGodExistDspCtgryBoList(dspCtgryBoDTO);
	}

	/**
	 * V2 Location
	 * @param dspCtgryScFrDTO
	 * @return
	 * @throws Exception
	 */
	public List<DspCtgry> selectV2DspLocationList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{

		return displayRepository.selectLocationList(dspCtgryScFrDTO);
	}

	/**
	 * V2 MB Location
	 * @param dspCtgryScFrDTO
	 * @return
	 * @throws Exception
	 */
	public List<DspCtgry> selectV2DspMBLocationList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{

		return displayRepository.selectMBLocationList(dspCtgryScFrDTO);
	}

	/**
	 * V2 LNB 카테고리
	 * @param dspCtgryScFrDTO
	 * @return
	 * @throws Exception
	 */
	public List<DspCtgry> selectV2DspCtgryBrandAllList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{

		return displayRepository.selectBrandAllList(dspCtgryScFrDTO);
	}

	public List<DspCtgry> selectV2DspCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{

		return displayRepository.selectBrandCtgryList(dspCtgryScFrDTO);
	}

	public List<DspCtgryLNBResult> selectV2MBDspCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{

		return displayRepository.selectBrandMBCtgryList(dspCtgryScFrDTO);
	}

	public String selectBrandInfo(String brndDspCtgryNo) throws Exception{
		return displayRepository.selectBrandInfo(brndDspCtgryNo);
	}

	public DspCtgry selectCtgryInfo(String brndDspCtgryNo) throws Exception{
		return displayRepository.selectCtgryInfo(brndDspCtgryNo);
	}

	public List<DspCtgry> selectBeakerBrandList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{
		return displayRepository.selectBeakerBrandList(dspCtgryScFrDTO);
	}

	public CategoryChackResult selectCategoryChack(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{
		return displayRepository.selectCategoryChack(dspCtgryScFrDTO);
	}

	public List<DspCtgry> selectNewTopOnCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{
		return displayRepository.selectNewTopOnCtgryList(dspCtgryScFrDTO);
	}

	public List<DspCtgry> selectNewTopOnCtgryListOfInstore(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return displayRepository.selectNewTopOnCtgryListOfInstore(dspCtgryScFrDTO);
	}

	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	// 2016/07 (UX/UI) 
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 전시브랜드 카테고리 트리 조회.
	 * 판매중인 상품이 연결되어 있는 카테고리 목록만 조회
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return List 카테고리 트리 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 13
	 */
	public List<DspCtgryBoResult> selectBrndCtgryTree(DspCtgryBoDTO dspCtgryBoDTO) {
		return displayRepository.selectBrndCtgryTree(dspCtgryBoDTO);
	}
	
	/**
	 * 전시 브랜드카테고리 저장
	 * 전시 브랜드카테고리 테이블에 존재하면 수정하고, 존재하지 않으면 등록한다.
	 *
	 * @param dspCtgryBoDTO the dsp ctgry bo dto
	 * @throws Exception the exception
	 */
	public void updateDspBrndCtgryInfo(DspCtgryBoDTO dspCtgryBoDTO) throws Exception {
		DspBrndCtgry dspBrndCtgry = dspCtgryBoDTO.getDspBrndCtgry();
		String loginId = BOSecurityUtil.getLoginId();
		dspBrndCtgry.setRegtrId(loginId);
		dspBrndCtgry.setUdterId(loginId);
		
		int brndCnt = displayRepository.selectDspBrndCtgryCount(dspBrndCtgry);
		if(brndCnt == 0) {
			dspBrndCtgryRepository.insertDspBrndCtgry(dspBrndCtgry);
		}
		else {
			displayRepository.updateDspBrndCtgryInfo(dspBrndCtgry);
		}
	}
	
	/**
	 * Update dsp yn brnd ctgry.
	 *
	 * @param dspCtgry the dsp ctgry
	 * @throws Exception the exception
	 */
	public void updateDspYnBrndCtgry (DspCtgryBoDTO dspCtgryBoDTO)  {
		DspCtgry dspCtgry = dspCtgryBoDTO.getDspCtgry();
		DspBrndCtgry dspBrndCtgry = dspCtgryBoDTO.getDspBrndCtgry();
		dspBrndCtgry.setDspBrndId(dspCtgryBoDTO.getScDspBrndId());
		
		//1) 상위카테고리의 전시여부 조회
		dspBrndCtgry.setDspCtgryNo(dspCtgry.getUpperDspCtgryNo());
		String upperDspYn = displayRepository.selectDspYnBrndCtgry(dspBrndCtgry);
		if(StringService.isEmpty(upperDspYn)) {
			upperDspYn = displayRepository.selectDspYnCtgry(dspCtgry.getUpperDspCtgryNo());
		}
		
		//2) 대상카테고리의 전시여부 조회
		dspBrndCtgry.setDspCtgryNo(dspCtgry.getDspCtgryNo());
		String curDspYn = displayRepository.selectDspYnBrndCtgry(dspBrndCtgry);
		if(StringService.isEmpty(curDspYn)) {
			curDspYn = displayRepository.selectDspYnCtgry(dspCtgry.getDspCtgryNo());
		}
		//3) 변경할 전시여부
		String toDspYn = dspBrndCtgry.getDspYn();
		
		//전시여부 변경 시
		if(StringService.isNotEmpty(toDspYn) && !curDspYn.equals(toDspYn)) {
			//상위카테고리 전시여부가 "Y"일 경우
			if(StringService.isNotEmpty(upperDspYn) && upperDspYn.equals("Y")) {
				//전시여부를 "N"으로 변경할 경우 하위카테고리도 모두 "N"으로 수정한다.
				if("N".equals(toDspYn)) {
					//해당브랜드의 하위카테고리 조회
					DspCtgryBoDTO dto = new DspCtgryBoDTO();
					dto.setScDspCtgryNo(dspCtgry.getDspCtgryNo());
					dto.setScAplMallId(dspCtgry.getAplMallId());
					dto.setScDspBrndId(dspCtgryBoDTO.getScDspBrndId());
					dto.setLang("KOR");
					List<DspCtgryBoResult> list = selectBrndCtgryTree(dto);
					
					for(DspCtgryBoResult result:list) {
						//하위카테고리도 모두 "N"으로 수정
						DspCtgry child = result.getDspCtgry();
						
						if(child !=null && StringService.isNotEmpty(child.getDspCtgryNo()) && !(dspCtgry.getDspCtgryNo()).equals(child.getDspCtgryNo())) {
							DspBrndCtgry childBrnd = new DspBrndCtgry();
							
							childBrnd.setDspCtgryNo(child.getDspCtgryNo());
							childBrnd.setDspBrndId(dspCtgryBoDTO.getScDspBrndId());
							childBrnd.setDspYn("N");
							childBrnd.setUdterId(dspBrndCtgry.getUdterId());
							
							int brndCnt = displayRepository.selectDspBrndCtgryCount(childBrnd);
							if(brndCnt == 0) {
								childBrnd.setCtgryOutptTpCd(child.getCtgryOutptTpCd());
								childBrnd.setDspCtgryNm(child.getDspCtgryNm());
								childBrnd.setDspGodSortStdrCd(child.getDspGodSortStdrCd());
								childBrnd.setGodDspUnitCd(child.getGodDspUnitCd());
								childBrnd.setOutptLinkUrl(child.getOutptLinkUrl());
								childBrnd.setOutptSectCd(child.getOutptSectCd());
								childBrnd.setSortSeq(child.getSortSeq());
								childBrnd.setSpcifyUrlDspYn(child.getSpcifyUrlDspYn());
								
								//dspBrndCtgryRepository.insertDspBrndCtgry(childBrnd);
							}
							else {
								displayRepository.updateDspYnBrndCtgry(childBrnd);
							}
						}
					}
				}
			}
			else {
				throw new NotUpdatedDspYnException(null);
			}
		}
	}
	
	/**
	 * 하위 브랜드 카테고리 목록 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list 수정할 grid 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 21
	 */
	public void updateBrndCategoryList(List<DspCtgryBoDTO> list) throws Exception {
		DspBrndCtgry dspBrndCtgry = null; 
		String loginId = BOSecurityUtil.getLoginId();
		
		if (list != null) {
			for(DspCtgryBoDTO dspCtgryBoDTO: list) {
				dspBrndCtgry =  dspCtgryBoDTO.getDspBrndCtgry();
				dspCtgryBoDTO.setScDspBrndId(dspBrndCtgry.getDspBrndId());
				DspBrndCtgry bctg = new DspBrndCtgry();
				
				//전시여부 수정 여부 체크 및 하위카테고리 전시여부 수정
				updateDspYnBrndCtgry(dspCtgryBoDTO);
				
				bctg.setDspYn(dspBrndCtgry.getDspYn());
				bctg.setDspCtgryNo(dspBrndCtgry.getDspCtgryNo());
				bctg.setDspBrndId(dspBrndCtgry.getDspBrndId());
				bctg.setSortSeq(dspBrndCtgry.getSortSeq());
				bctg.setSpcifyUrlDspYn(dspBrndCtgry.getSpcifyUrlDspYn());	
				bctg.setUdterId(loginId);
				
				int brndCnt = displayRepository.selectDspBrndCtgryCount(dspBrndCtgry);
				if(brndCnt == 0) {
					DspCtgry dspCtgry = new DspCtgry();
					dspCtgry.setDspCtgryNo(dspBrndCtgry.getDspCtgryNo());
					DspCtgry ctg = dspCtgryRepository.selectDspCtgry(dspCtgry);
					ctg.setRegtrId(loginId);
					ctg.setUdterId(loginId);
					dspCtgryBoDTO.setDspCtgry(ctg);
					
					bctg.setCtgryOutptTpCd(ctg.getCtgryOutptTpCd());
					bctg.setDspCtgryNm(ctg.getDspCtgryNm());
					bctg.setDspGodSortStdrCd(ctg.getDspGodSortStdrCd());
					bctg.setGodDspUnitCd(ctg.getGodDspUnitCd());
//					bctg.setMobileTmplatSn(ctg.getMobileTmplatSn());
					bctg.setOutptLinkUrl(ctg.getOutptLinkUrl());
					bctg.setOutptSectCd(ctg.getOutptSectCd());
//					bctg.setPcMobileTmplatIndUseYn(ctg.getPcMobileTmplatIndUseYn());
//					bctg.setPcTmplatSn(ctg.getPcTmplatSn());
					bctg.setSpcifyUrlDspYn(ctg.getSpcifyUrlDspYn());
					bctg.setRegtrId(loginId);
					dspCtgryBoDTO.setDspBrndCtgry(bctg);
					
					dspBrndCtgryRepository.insertDspBrndCtgry(dspBrndCtgry);
				}
				else {
					displayRepository.updateDspBrndCtgryForGrid(bctg);
				}
			}
		}
	}
	
	/**
	 * 카테고리 연결상품 전시순서 업로드.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 10
	 */
	public List<DspCtgryCnncGodExt> updateBrndCtgrySortSeqGod(List<DspCtgryCnncGod> list, String dspCtgryNo, String scDspBrndId) throws Exception {
		List<DspCtgryCnncGodExt> errList = null;
		if (list != null) {
			String loginId = BOSecurityUtil.getLoginId();
			
			int res = 0;
			errList = new ArrayList<DspCtgryCnncGodExt>();
			for(DspCtgryCnncGod dspCtgryCnncGod: list) {
				dspCtgryCnncGod.setUdterId(loginId);
				dspCtgryCnncGod.setDspCtgryNo(dspCtgryNo);
				
				String brndGrpId = displayCategoryCnncGodRepository.selectBrndGrpIdCnncGod(dspCtgryCnncGod.getGodNo());
				if(brndGrpId == null || !brndGrpId.equals(scDspBrndId) ) {
					DspCtgryCnncGodExt dspCtgryCnncGodExt = new DspCtgryCnncGodExt();
					dspCtgryCnncGodExt.setGodNo(dspCtgryCnncGod.getGodNo());
					dspCtgryCnncGodExt.setBrndCtgrySortSeq(dspCtgryCnncGod.getBrndCtgrySortSeq());
					dspCtgryCnncGodExt.setErrMsg("브랜드코드가 일치하지 않습니다. brndGrpId="+brndGrpId+", scDspBrndId="+ scDspBrndId);
					errList.add(dspCtgryCnncGodExt);
				}
				else {
					res = displayCategoryCnncGodRepository.updateBrndCtgrySortSeqGod(dspCtgryCnncGod);
					if(res < 1) {
						DspCtgryCnncGodExt dspCtgryCnncGodExt = new DspCtgryCnncGodExt();
						dspCtgryCnncGodExt.setGodNo(dspCtgryCnncGod.getGodNo());
						dspCtgryCnncGodExt.setBrndCtgrySortSeq(dspCtgryCnncGod.getBrndCtgrySortSeq());
						dspCtgryCnncGodExt.setErrMsg("등록되지 않은 온라인품번입니다.");
						errList.add(dspCtgryCnncGodExt);
					}
				}
			}
		}
		return errList;
	}
	
	
	/**
	 * Move ctgry cnnc god.
	 *
	 * @param dspCtgryCnncGodBoDTO the dsp ctgry cnnc god bo dto
	 * @return the int
	 * @throws Exception the exception
	 */
	public int moveCtgryCnncGod(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		
		int result = 0;
		
		List<String> dupGodNo = displayCategoryCnncGodRepository.selectDupCnncGodList(dspCtgryCnncGodBoDTO);
		dspCtgryCnncGodBoDTO.setDupGodNo(dupGodNo);
		
		//moveGodNo 를 신규 카테고리(desCtgryNo) 번호로 insert - 중복 제거,
		//moveGodNo 를 기존 카테고리(srcCtgryNo) 번호 삭제
		result = displayCategoryCnncGodRepository.moveCtgryCnncGod(dspCtgryCnncGodBoDTO);
		displayCategoryCnncGodRepository.deleteMoveCtgryCnncGod(dspCtgryCnncGodBoDTO);
		
		//desCtgryNo가 아울렛카테고리일 경우 무료수선여부 N로 update
		DspCtgry pCtg = new DspCtgry();
		pCtg.setDspCtgryNo(dspCtgryCnncGodBoDTO.getDesCtgryNo());
		DspCtgry ctg = dspCtgryRepository.selectDspCtgry(pCtg);
		if(ctg != null) {
			if(ctg.getCtgrySectCd().equals("OTLT_CTGRY")) {  //이동하는 카테고리 아울렛일 때 
				displayCategoryCnncGodRepository.updateGodFreeRepairPsbYn(dspCtgryCnncGodBoDTO);
			}else{
				pCtg = null;
				pCtg = new DspCtgry();
				pCtg.setDspCtgryNo(dspCtgryCnncGodBoDTO.getSrcCtgryNo());
				ctg = null;
				ctg = dspCtgryRepository.selectDspCtgry(pCtg);
				if(ctg != null && ctg.getCtgrySectCd().equals("OTLT_CTGRY")) { //이동전 아울렛 카테고리면 SYS_BRND 멤버쉽 복구
					displayCategoryCnncGodRepository.updateGodPntAccml(dspCtgryCnncGodBoDTO);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Select outlet ctg for copy.
	 *
	 * @param dspCtgryCnncGodBoDTO the dsp ctgry cnnc god bo dto
	 * @return the integer
	 * @throws Exception the exception
	 */
	public Integer selectOutletCtgForCopy(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		return displayCategoryCnncGodRepository.selectOutletCtgForCopy(dspCtgryCnncGodBoDTO);
	}
	
	/**
	 * 카테고리 전시순서 변경
	 *
	 * @param list the list
	 * @throws Exception the exception
	 */
	public void updateCtgSortSeq(List<DspCtgryBoDTO> list) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		if(list != null) {
			for(int i=0; i<list.size(); i++) {
				DspCtgryBoDTO dto = list.get(i);
				DspCtgry dspCtgry = dto.getDspCtgry();
				dspCtgry.setSortSeq(i+1);
				dto.setLoginId(loginId);
				
				if(StringService.isNotEmpty(dto.getScDspBrndId())) {
					DspBrndCtgry dspBrndCtgry = new DspBrndCtgry();
					dspBrndCtgry.setDspCtgryNo(dspCtgry.getDspCtgryNo());
					dspBrndCtgry.setDspBrndId(dto.getScDspBrndId());
					
					int brndCnt = displayRepository.selectDspBrndCtgryCount(dspBrndCtgry);
					if(brndCnt == 0) {
						DspCtgry sctg = new DspCtgry();
						sctg.setDspCtgryNo(dspCtgry.getDspCtgryNo());
						DspCtgry ctg = dspCtgryRepository.selectDspCtgry(sctg);
						ctg.setRegtrId(loginId);
						ctg.setUdterId(loginId);
						//dspCtgryBoDTO.setDspCtgry(ctg);
						
						dspBrndCtgry.setDspYn(ctg.getDspYn());
						dspBrndCtgry.setSortSeq(i+1);
						dspBrndCtgry.setSpcifyUrlDspYn(ctg.getSpcifyUrlDspYn());	
						dspBrndCtgry.setUdterId(loginId);
						
						dspBrndCtgry.setCtgryOutptTpCd(ctg.getCtgryOutptTpCd());
						dspBrndCtgry.setDspCtgryNm(ctg.getDspCtgryNm());
						dspBrndCtgry.setDspGodSortStdrCd(ctg.getDspGodSortStdrCd());
						dspBrndCtgry.setGodDspUnitCd(ctg.getGodDspUnitCd());
//						dspBrndCtgry.setMobileTmplatSn(ctg.getMobileTmplatSn());
						dspBrndCtgry.setOutptLinkUrl(ctg.getOutptLinkUrl());
						dspBrndCtgry.setOutptSectCd(ctg.getOutptSectCd());
//						dspBrndCtgry.setPcMobileTmplatIndUseYn(ctg.getPcMobileTmplatIndUseYn());
//						dspBrndCtgry.setPcTmplatSn(ctg.getPcTmplatSn());
						dspBrndCtgry.setRegtrId(loginId);
						//dspCtgryBoDTO.setDspBrndCtgry(bctg);
						
						dspBrndCtgryRepository.insertDspBrndCtgry(dspBrndCtgry);
					}
					else {
						displayRepository.updateCtgSortSeq(dto);
					}
				}
				else {
					displayRepository.updateCtgSortSeq(dto);
				}
				
			}
		}
		
	}
	
	/**
	 * 카테고리 연결상품 전시순서 변경
	 *
	 * @param list the list
	 * @throws Exception the exception
	 */
	public void updateCtgGodSortSeq(List<DspCtgryCnncGodBoDTO> list) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		if(list != null) {
			for(int i=0; i<list.size(); i++) {
				DspCtgryCnncGodBoDTO dto = list.get(i);
				DspCtgryCnncGod dspCtgryCnncGod = dto.getDspCtgryCnncGod();
				dspCtgryCnncGod.setUdterId(loginId);
				
				if(StringService.isNotEmpty(dto.getScDspBrndId())) {
					dspCtgryCnncGod.setBrndCtgrySortSeq(i+1);
					
					displayCategoryCnncGodRepository.updateBrndCtgrySortSeqGod(dspCtgryCnncGod);
				}
				else {
					dspCtgryCnncGod.setSortSeq(i+1);
					
					displayCategoryCnncGodRepository.updateSortSeqGod(dspCtgryCnncGod);
				}
				
			}
		}
		
	}

	/**
	 * 전신카테고리연결상품 전체수량
	 * @param dspCtgrySearchFoDTO	검색조건
	 * @return				상품전체수량
	 */
	public int selectDspCtgryGodsListCnt(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception{
		DspCtgry ctg = dspCtgrySearchFoDTO.getDspCtgry();
		String scDspCtgryNo = "";
		if(ctg != null) {
			scDspCtgryNo = ctg.getDspCtgryNo();
		}
		//대카일경우 적재테이블 조회
		if(StringService.isNotEmpty(scDspCtgryNo) && (
				scDspCtgryNo.equals(DisplayEnum.WOMEN.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.MEN.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.KIDS.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.NEWCTG.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.BSTCTG.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.SALECTG.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.OTLTCTG.toString()))) {
			return displayRepository.selectDspCtgryDspGodListTotCnt(dspCtgrySearchFoDTO);
		}
		else {
			return displayRepository.selectDspCtgryGodsListCnt(dspCtgrySearchFoDTO);
		}
	}

	public List<DspCtgryGodFoResult> selectDspCtgryGodsList(SystemPK pk, DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		List<DspCtgryGodFoResult> godList = null;
		DspCtgry ctg = dspCtgrySearchFoDTO.getDspCtgry();
		String scDspCtgryNo = "";
		if(ctg != null) {
			scDspCtgryNo = ctg.getDspCtgryNo();
		}
		//대카일경우 적재테이블 조회
		if(StringService.isNotEmpty(scDspCtgryNo) && (
				scDspCtgryNo.equals(DisplayEnum.WOMEN.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.MEN.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.KIDS.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.NEWCTG.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.BSTCTG.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.SALECTG.toString())
			   ||scDspCtgryNo.equals(DisplayEnum.OTLTCTG.toString()))
				&&!"CORNER".equals(dspCtgrySearchFoDTO.getSelectSectCd())) {
			godList = displayRepository.selectDspCtgryDspGodList(dspCtgrySearchFoDTO);
		} 
		else {
			godList = displayRepository.selectDspCtgryGodList(dspCtgrySearchFoDTO);
		}
		
		List<DspCtgryGodFoResult> godListV2 = new ArrayList<DspCtgryGodFoResult> ();
		List<String> godNoList = new ArrayList<String>();

		if(godList != null && godList.size() > 0){
			for(DspCtgryGodFoResult godResult : godList){
				DspCtgryGodFoResult godResultV2 = new DspCtgryGodFoResult();
				BeanUtils.copyProperties(godResult, godResultV2);
				
				godNoList.add(godResult.getGod().getGodNo());

				String fImg = godResultV2.getImgFrontUrl();
				String bImg = godResultV2.getImgBackUrl();

				boolean isExistFront = false;
				boolean isExistBack = false;

				if(fImg != null && fImg.length() > 0){
					isExistFront = true;
				}
				if(bImg != null && bImg.length() > 0){
					isExistBack = true;
				}

				if(isExistFront == true && isExistBack == false) {
					godResultV2.setImgBackUrl(fImg);
				}
				if(isExistFront == false && isExistBack == true) {
					godResultV2.setImgFrontUrl(bImg);
				}
				
				godListV2.add(godResultV2);

			}
			// 상품예약태그
			List<GodTagResveFoResult> godTagResveList = displayRepository.selectGodResve2(pk, godNoList);
			List<GodIconFoResult> godIconList = displayRepository.selectGodIconList2(godNoList);

			for(int i = 0; i < godListV2.size(); i ++){
				DspCtgryGodFoResult godResult = godListV2.get(i);
				God god = godResult.getGod();
				// LANGUAGE CONTROL
				String lang = pk.getLang();
				if(!lang.equals("KOR")){
					GodLangbyGodNm godLang = godResult.getGodLangbyGodNm();
					changeDspCtgryCnncGodLang(god, godLang);
				}

				for(GodTagResveFoResult tag:godTagResveList){
					String godNo = tag.getGodNo();
					if(god.getGodNo().equals(godNo)){
						GodTagResve godTagResve = tag.getGodTagResve();
						GodLangbyTagResve godLangbyTagResve = tag.getGodLangbyTagResve();
						if(!lang.equals("KOR")){
							changeDspCtgryCnncGodLang(god, godTagResve, godLangbyTagResve);
						}
						else if(godTagResve != null && godTagResve.getTagNm() != null){
							god.setTagNm(godTagResve.getTagNm());
						}
						break;
					}
				}

				for(GodIconFoResult godIcon:godIconList){
					String godNo = godIcon.getGodIconCnnc().getGodNo();
					if(god.getGodNo().equals(godNo)){
						List<GodIconFoResult> iconList = godResult.getGodIconList();
						if(iconList == null){
							iconList = new ArrayList<GodIconFoResult>();
							iconList.add(godIcon);
							godResult.setGodIconList(iconList);
						}else{
							iconList.add(godIcon);
						}
					}
				}
			}
		}
		return godListV2;
	}

	public DspCtgryResultFoDTO selectV2DspCtgryNo(String dspCtgryNo, String ctgrySectCd) throws Exception {
		DspCtgrySearchFoDTO dspCtgrySearchFoDTO = new DspCtgrySearchFoDTO();
		DspCtgry dspCtgry = new DspCtgry();
		dspCtgry.setDspCtgryNo(dspCtgryNo);
		dspCtgry.setCtgrySectCd(ctgrySectCd);
		dspCtgrySearchFoDTO.setDspCtgry(dspCtgry);

		List<DspCtgryFoResult> ctgryResult = displayRepository.selectV2DspCtgryNo(dspCtgrySearchFoDTO);

		DspCtgryResultFoDTO result = new DspCtgryResultFoDTO();
		result.setDspCtgryResultList(ctgryResult);
		return result;
	}

	public List<String> selectCtgryCnncGodNo(DspCtgryScFrDTO dspCtgryFrDTO) throws Exception {
		return displayRepository.selectCtgryCnncGodNo(dspCtgryFrDTO);
	}

	public List<String> selectTopNewSaleGodNo(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return displayRepository.selectTopNewSaleGodNo(dspCtgryScFrDTO);
	}

	public List<String> selectCtgryNmListPC(DspCtgryScFrDTO dspCtgryFrDTO) throws Exception {
		return displayRepository.selectCtgryNmListPC(dspCtgryFrDTO);
	}

	public List<String> selectCtgryNmList(DspCtgryScFrDTO dspCtgryFrDTO) throws Exception {
		return displayRepository.selectCtgryNmList(dspCtgryFrDTO);
	}

	public List<DspCtgryGodFoResult> selectRecommendationList(DspCtgrySearchFoDTO dto) throws Exception {
		return displayRepository.selectRecommendationList(dto);
	}

	/**
	 * BEAKER 라이프 섹션 상품 갯수 조회
	 * @param dspCtgrySearchFoDTO	검색조건
	 * @return				상품전체수량
	 */
	public int selectNewSaleTopLifestyleCnt(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception{
		return displayRepository.selectNewSaleTopLifestyleCnt(dspCtgrySearchFoDTO);
	}

	/**
	 * * BEAKER 라이프 섹션 상품 리스트 조회
	 * @param pk
	 * @param dspCtgrySearchFoDTO
	 * @return
	 * @throws Exception
     */
	public List<DspCtgryGodFoResult> selectNewSaleTopLifestyleList(SystemPK pk, DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		List<DspCtgryGodFoResult> godList = displayRepository.selectNewSaleTopLifestyleList(dspCtgrySearchFoDTO);
		List<String> godNoList = new ArrayList<String>();

		if(godList != null && godList.size() > 0){
			for(DspCtgryGodFoResult godResult : godList){
				godNoList.add(godResult.getGod().getGodNo());

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

				if(isExistFront == true && isExistBack == false) {
					godResult.setImgBackUrl(fImg);
				}
				if(isExistFront == false && isExistBack == true) {
					godResult.setImgFrontUrl(bImg);
				}

			}
			// 상품예약태그
			List<GodTagResveFoResult> godTagResveList = displayRepository.selectGodResve2(pk, godNoList);
			List<GodIconFoResult> godIconList = displayRepository.selectGodIconList2(godNoList);

			for(int i = 0; i < godList.size(); i ++){
				DspCtgryGodFoResult godResult = godList.get(i);
				God god = godResult.getGod();
				// LANGUAGE CONTROL
				String lang = pk.getLang();
				if(!lang.equals("KOR")){
					GodLangbyGodNm godLang = godResult.getGodLangbyGodNm();
					changeDspCtgryCnncGodLang(god, godLang);
				}

				for(GodTagResveFoResult tag:godTagResveList){
					String godNo = tag.getGodNo();
					if(god.getGodNo().equals(godNo)){
						GodTagResve godTagResve = tag.getGodTagResve();
						GodLangbyTagResve godLangbyTagResve = tag.getGodLangbyTagResve();
						if(!lang.equals("KOR")){
							changeDspCtgryCnncGodLang(god, godTagResve, godLangbyTagResve);
						}
						else if(godTagResve != null && godTagResve.getTagNm() != null){
							god.setTagNm(godTagResve.getTagNm());
						}
						break;
					}
				}

				for(GodIconFoResult godIcon:godIconList){
					String godNo = godIcon.getGodIconCnnc().getGodNo();
					if(god.getGodNo().equals(godNo)){
						List<GodIconFoResult> iconList = godResult.getGodIconList();
						if(iconList == null){
							iconList = new ArrayList<GodIconFoResult>();
							iconList.add(godIcon);
							godResult.setGodIconList(iconList);
						}else{
							iconList.add(godIcon);
						}
					}
				}
			}
		}
		return godList;
	}

	public List<DspCtgryFoResult> selectLowerCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return displayRepository.selectLowerCtgryList(dspCtgryScFrDTO);
	}

	public Integer otltGodCheck(List<DspCtgryCnncGod> list) throws Exception {
		int otltCnt = 0;
		for(DspCtgryCnncGod god:list){
			if(otltCnt > 0){
				break;
			}else{
				int otltCheck = displayRepository.otltGodCheck(god.getGodNo());
				otltCnt += otltCheck;
			}
		}
		return otltCnt;
	}

	public int selectCtgryChk(String godNo) throws Exception {
		return displayRepository.selectCnncCtgryCnt(godNo);
	}

	public List<DspCtgryCnncGodExcelResult> selectGodOneCnncCtgry(String godNoList) throws Exception {
		List<DspCtgryCnncGodExcelResult> result = new ArrayList<>();
		String[] godNo = godNoList.split(",");

		for(String god : godNo){
			DspCtgryCnncGodExcelResult oneCtgryGod = displayRepository.selectDspCtgryCnncGodInfo(god);
			oneCtgryGod.setFailReson("전시카테고리는 1개 이상 필수 등록");
			result.add(oneCtgryGod);
		}
		return result;
	}

	public DspCtgryLang selectDspCtgryLang(DspCtgryLang dspCtgryLang) throws Exception {
		return dspCtgryLangRepository.selectDspCtgryLang(dspCtgryLang);
	}

	public void insertDspCtgryLangMetaTag(DspCtgryLang dspCtgryLang) throws Exception {
	    displayRepository.insertDspCtgryLangMetaTag(dspCtgryLang);
	}

	public void updateDspCtgryLangMetaTag(DspCtgryLang dspCtgryLang) throws Exception {
        displayRepository.updateDspCtgryLangMetaTag(dspCtgryLang);

	}

	/**
	 * 수트(상품)파인더 상품 전시 리스트 조회 Service
	 * @author Dave
	 * @since 2017.08.14
	 */
	public List<DspCtgryGodFoResult> selectGoodsFinderList(SystemPK pk, DspCtgrySearchFoDTO dspCtgrySearchFoDTO) {
		List<DspCtgryGodFoResult> godList = null;
		godList = displayRepository.selectGoodsFinderList(dspCtgrySearchFoDTO);

		List<DspCtgryGodFoResult> godListV2 = new ArrayList<DspCtgryGodFoResult> ();
		List<String> godNoList = new ArrayList<String>(); // 상품 리스트 godNoList   ex) SM0017081076808

		if(godList != null && godList.size() > 0){
			for(DspCtgryGodFoResult godResult : godList){
				godNoList.add(godResult.getGod().getGodNo()); // 상품 리스트에 품번 추가, 상품예약태그와 상품아이콘 리스트 조회하는데 사용

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

				if(isExistFront == true && isExistBack == false) {
					godResult.setImgBackUrl(fImg);
				}
				if(isExistFront == false && isExistBack == true) {
					godResult.setImgFrontUrl(bImg);
				}

				godListV2.add(godResult);

			}

			List<GodTagResveFoResult> godTagResveList = new ArrayList<GodTagResveFoResult>();
			List<GodIconFoResult> godIconList = new ArrayList<GodIconFoResult>();
			// 상품예약태그
			try {
				godTagResveList = displayRepository.selectGodResve2(pk, godNoList);
				godIconList = displayRepository.selectGodIconList2(godNoList);
			}catch(Exception e) {
				throw new RuntimeException();
			}

			for(int i = 0; i < godList.size(); i ++){
				DspCtgryGodFoResult godResult = godList.get(i);
				God god = godResult.getGod();
				// LANGUAGE CONTROL
				String lang = pk.getLang();
				if(!lang.equals("KOR")){
					GodLangbyGodNm godLang = godResult.getGodLangbyGodNm();
					changeDspCtgryCnncGodLang(god, godLang);
				}

				for(GodTagResveFoResult tag:godTagResveList){
					String godNo = tag.getGodNo();
					if(god.getGodNo().equals(godNo)){
						GodTagResve godTagResve = tag.getGodTagResve();
						GodLangbyTagResve godLangbyTagResve = tag.getGodLangbyTagResve();
						if(!lang.equals("KOR")){
							changeDspCtgryCnncGodLang(god, godTagResve, godLangbyTagResve);
						}
						else if(godTagResve != null && godTagResve.getTagNm() != null){
							god.setTagNm(godTagResve.getTagNm());
						}
						break;
					}
				}

				for(GodIconFoResult godIcon:godIconList){
					String godNo = godIcon.getGodIconCnnc().getGodNo();
					if(god.getGodNo().equals(godNo)){
						List<GodIconFoResult> iconList = godResult.getGodIconList();
						if(iconList == null){ // godIconList 가 null이면 새로운 GodIconFoResult 객체에 add 후 set
							iconList = new ArrayList<GodIconFoResult>();
							iconList.add(godIcon);
							godResult.setGodIconList(iconList);
						}else{
							iconList.add(godIcon);
						}
					}
				}
			}
		}
		return godListV2;
	}
	
	/**
	 * 전시브랜드 카테고리 트리 조회. (팝업)
	 * 판매중인 상품이 연결되어 있는 카테고리 목록만 조회
	 * 
	 *
	 * @param dspCtgryBoDTO the dsp ctgry bo DTO
	 * @return the list
	 */
	public List<DspCtgryBoResult> selectBrndCtgryTreePopup(DspCtgryBoDTO dspCtgryBoDTO) {
		return displayRepository.selectBrndCtgryTreePopup(dspCtgryBoDTO);
	}
	
	/**
	 * 전시 카테고리 검색어 조회.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return List 카테고리 검색어 조회 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public List<DspCtgryBoResult> selectBrndCtgryKwdPopup(DspCtgryBoDTO dspCtgryBoDTO, PageParam pageParam){
		return displayRepository.selectBrndCtgryKwdPopup(dspCtgryBoDTO, pageParam);
	}
	
	/**
	 * 전시 카테고리 검색어 조회 총 count.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public int selectBrndCtgryKwdPopupTotalCount(DspCtgryBoDTO dspCtgryBoDTO) {
		return displayRepository.selectBrndCtgryKwdPopupTotalCount(dspCtgryBoDTO);
	}
	
}
