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
 * @since       2015. 12. 1       
 */
package com.plgrim.ncp.biz.display.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.biz.display.result.DspBrndImgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyTagResve;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResve;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndImg;
import com.plgrim.ncp.base.repository.sys.SysBrndImgRepository;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.repository.DisplayCategoryFrRepository;
import com.plgrim.ncp.biz.display.result.DspCommonGodFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryBrndFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryFrResult;
import com.plgrim.ncp.framework.data.SystemPK;

@Service
public class DisplayCategoryFrService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DisplayCategoryFrRepository displayCategoryFrRepository;
	
	@Autowired
	SysBrndImgRepository sysBrndImgRepository;

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
	 * 전시브랜드카테고리 전체 브랜드 목록 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 11
	 */
	public List<DspCtgryBrndFrResult> selectBrandAllList(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectBrandAllList(dto);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 12. 1
	 */
	public List<Map<String, Object>> selectBrandAllListCount(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectBrandAllListCount(dto);
	}
	
	/**
	 * 브랜드 LNB 카테고리 목록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dspCnrScFrDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 3
	 */
	public List<DspCtgryFrResult> selectBrandLnbCtgryList(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectBrandLnbCtgryList(dto);
	}
	
	/**
	 * 카테고리 상품목록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 6
	 */
	public List<DspCommonGodFrResult> selectCtgryGodFrList(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectCtgryGodFrList(dto);
	}
	
	/**
	 * 상품 목록 총 수.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dto [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 6
	 */
	public int selectCtgryGodFrTotCnt(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectCtgryGodFrTotCnt(dto);
	}
	
	
	/**
	 * 전시카테고리 기본정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Dsp ctgry fr result [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 13
	 */
	public DspCtgryFrResult selectDisplayCategoryInfo(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectDspCtgryFrInfo(dto);
	}
	
	/**
	 * 전시카테고리 로케이션 목록 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 16
	 */
	public List<DspCtgryFrResult> selectLocationList(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectLocationList(dto);
	}
	
	/**
	 * 카테고리 연결 상품번호 목록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 16
	 */
	public List<String> selectCnncGodNos(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectCnncGodNos(dto);
	}

	/**
	 * 브랜드샵의 카테고리별 연결상품 수 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Map [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 19
	 */
	public Map<String,Integer> selectBrndCtgGodCount(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectBrndCtgGodCount(dto);
	}
	
	/**
	 * TOP브랜드 목록조회.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryBrndFrResult> selectTopBrand(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectTopBrand(dto);
	}
	

	/**
	 * 브랜드이미지 조회
	 *
	 * @param brndId the brnd id
	 * @param brndImgSectCd the brnd img sect cd
	 * @return the sys brnd img
	 * @throws Exception the exception
	 */
	public SysBrndImg selectSysBrndImg(SystemPK pk, String brndId, String brndImgSectCd) throws Exception {
		SysBrndImg sysBrndImg = new SysBrndImg();
		sysBrndImg.setBrndId(brndId);
		sysBrndImg.setBrndImgSectCd(brndImgSectCd);
		sysBrndImg.setLangCd(pk.getLang());

		SysBrndImg sysBrndImgResult = sysBrndImgRepository.selectSysBrndImg(sysBrndImg);
		
		return sysBrndImgResult;
	}
	
	/**
	 * 브랜드카테고리목록조회-비이커서브브랜드메인화면용.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryFrResult> selectBrndCtgryList(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectBrndCtgryList(dto);
	}

	/**
	 * 브랜드스토리 이미지 조회
	 * @param brandId
	 * @return
	 * @throws Exception
     */
	public DspBrndImgResult selectBrandStory(String brandId) throws Exception {
		return displayCategoryFrRepository.selectBrandStory(brandId);
	}
	
	/**
	 * 브랜드스토리 이미지 조회 - 글로벌추가
	 *
	 * @param dto the dto
	 * @return the dsp brnd img result
	 * @throws Exception the exception
	 */
	public DspBrndImgResult selectBrandStoryInfo(DspCtgryScFrDTO dto) throws Exception{
		return displayCategoryFrRepository.selectBrandStoryInfo(dto);
	}

	/**
	 * All 브랜드조회.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryBrndFrResult> selectAllBrand(DspCtgryScFrDTO dto) throws Exception {
		return displayCategoryFrRepository.selectAllBrand(dto);
	}

	/**
	 * Select dsp brnd id list.
	 *
	 * @param bdmCtgNoList the bdm ctg no list
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<String> selectDspBrndIdList(List<String> bdmCtgNoList) throws Exception {

		return displayCategoryFrRepository.selectDspBrndIdList(bdmCtgNoList);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	
	
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
		if(godLang != null && godLang.getGodNm() != null){
			god.setGodNm(godLang.getGodNm());
			god.setMobileGodNm(godLang.getMobileGodNm());
			god.setTagNm(godLang.getTagNm());
			god.setColorTagNm(godLang.getColorTagNm());
			god.setThemaTagNm(godLang.getThemaTagNm());
			god.setColorNm(godLang.getColorNm());
		}
	}
	
	
	
	
	
	private void changeConttGodTagResveLang(God god,  GodTagResve godTagResve,GodLangbyTagResve godLangbyTagResve){
		if(godTagResve != null){
			if(godLangbyTagResve != null && godLangbyTagResve.getTagNm() != null){
				god.setTagNm(godLangbyTagResve.getTagNm());
			}else if(godTagResve != null && godTagResve.getTagNm() != null){
				god.setTagNm(godTagResve.getTagNm());
			}
		}
	}
}
