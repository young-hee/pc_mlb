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
package com.plgrim.ncp.biz.display.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.biz.display.result.DspBrndImgResult;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.repository.dsp.DspCnrRepository;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.result.DspCommonGodFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryBrndFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryFrResult;


/**
 * 브랜드 카테고리 Repository.
 *
 * @author shsunhee.kim
 * @since 2015. 3. 13
 */
@Repository
@Slf4j
public class DisplayCategoryFrRepository extends DspCnrRepository {

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
	 * 전시브랜드카테고리 전체 브랜드 목록 조회
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
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrandAllList", dto);
	}
	
	/**
	 * 전시브랜드카테고리별 브랜드 수 조회
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
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrandAllListCount", dto);
	}

	/**
	 * 브랜드 LNB 카테고리 목록 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 5
	 */
	public List<DspCtgryFrResult> selectBrandLnbCtgryList(DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrandLnbCtgryList", dto);
	}
	
	/**
	 * 카테고리 상품목록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 6
	 */
	public List<DspCommonGodFrResult> selectCtgryGodFrList(DspCtgryScFrDTO dto) throws Exception {
		
		RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam());
		
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectCtgryGodFrList", dto);
	}
	
	/**
	 * 카테고리 상품목록 총 Count.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 6
	 */
	public int selectCtgryGodFrTotCnt (DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectCtgryGodFrTotCnt", dto);
	}
	
	/**
	 * 전시카테고리 기본정보.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Dsp ctgry fr result [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 9
	 */
	@Cacheable(
			value="displayCategoryFrRepository.selectDspCtgryFrInfo", 
			key="{"
					+ "#p0.lang,"
					+ "#p0.mallId,"
					+ "#p0.dspCtgry?.dspCtgryNo"
					+ "}")
	public DspCtgryFrResult selectDspCtgryFrInfo (DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCtgryFrInfo", dto);
	}
	
	/**
	 * 전시카테고리 로케이션목록 조회.
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
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectLocationList", dto);
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
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectCnncGodNos", dto);
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
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectBrndCtgGodCount", dto);
	}
	
	/**
	 * TOP브랜드 목록 조회
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryBrndFrResult> selectTopBrand(DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectTopBrand", dto);
	}
	
	/**
	 * 브랜드카테고리목록조회-비이커서브브랜드메인화면용.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryFrResult> selectBrndCtgryList(DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrndCtgryList", dto);
	}

	/**
	 * 브랜드스토리 이미지 조회
	 * @param brandId
	 * @return
	 * @throws Exception
     */
	public DspBrndImgResult selectBrandStory(String brandId) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectBrndStory", brandId);
	}
	
	/**
	 * 브랜드스토리 이미지 조회 - 글로벌추가
	 * @param brandId
	 * @return
	 * @throws Exception
     */
	public DspBrndImgResult selectBrandStoryInfo(DspCtgryScFrDTO dto) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectBrandStoryInfo", dto);
	}

	/**
	 * ALL브랜드 목록 조회
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryBrndFrResult> selectAllBrand(DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectAllBrand", dto);
	}

	/**
	 * 브랜드카테고리번호에 해당하는 전시브랜드ID조회
	 *
	 * @param bdmCtgNoList the bdm ctg no list
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<String> selectDspBrndIdList(List<String> bdmCtgNoList) throws Exception {
		Map<String, List<String>> map = new HashMap<String, List<String>> ();
		map.put("bdmCtgNoList", bdmCtgNoList);
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspBrndIdList", map);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	
}
