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
 * @since       2015. 8. 19       
 */
package com.plgrim.ncp.biz.display.repository;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspBrndCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryLang;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNoti;
import com.plgrim.ncp.base.repository.dsp.DspCtgryRepository;
import com.plgrim.ncp.biz.display.data.DspCtgryBoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryResultMbExt;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.DspCtgrySearchFoDTO;
import com.plgrim.ncp.biz.display.result.CategoryChackResult;
import com.plgrim.ncp.biz.display.result.DspCtgryBoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryCnncGodExcelResult;
import com.plgrim.ncp.biz.display.result.DspCtgryFoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryLNBResult;
import com.plgrim.ncp.biz.display.result.GodIconFoResult;
import com.plgrim.ncp.biz.display.result.GodTagResveFoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DisplayCategoryRepository extends DspCtgryRepository {


	@Cacheable(
			value="displayCategoryRepository.selectDisplayCategoryInfo",
			key="{ "
					+ "#p0.lang, "
					+ "#p0.mall, "
					+ "#p0.device, "
					+ "#p1.dspCtgry?.dspCtgryNo"
					+ "}")
	public DspCtgryFoResult selectDisplayCategoryInfo(SystemPK pk, DspCtgrySearchFoDTO search) throws Exception{
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>전시카테고리 기본정보 및 필터전송 상품번호 조회");
		search.setMallId(pk.getMall());
		search.setLang(pk.getLang());
		search.setDevice(pk.getDevice());
		
		DspCtgryFoResult result = getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCtgryFo", search);
		return result;
	}
	
	@Cacheable(
			value="displayCategoryRepository.selectDisplayCategoryFoInfo",
			key="{ "
					+ "#p0.lang, "
					+ "#p0.mall, "
					+ "#p0.device, "
					+ "#p1.dspCtgry?.dspCtgryNo"
					+ "}")
	public DspCtgryFoResult selectDisplayCategoryFoInfo(SystemPK pk, DspCtgrySearchFoDTO search) throws Exception{
		search.setMallId(pk.getMall());
		search.setLang(pk.getLang());
		search.setDevice(pk.getDevice());
		
		DspCtgryFoResult result = getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCtgryFoInfo", search);
		return result;
	}
	
	@Cacheable(
			value="displayCategoryRepository.selectDspCtgryGodSortFo",
			key="{ "
					+ "#p0.lang, "
					+ "#p0.mall, "
					+ "#p0.device, "
					+ "#p1.dspCtgry?.dspCtgryNo"
					+ "}")
	public DspCtgryFoResult selectDspCtgryGodSortFo(SystemPK pk, DspCtgrySearchFoDTO search) throws Exception{
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>전시카테고리 상품정렬코드 조회");
		search.setMallId(pk.getMall());
		search.setLang(pk.getLang());
		search.setDevice(pk.getDevice());
		
		DspCtgryFoResult result = getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCtgryGodSortFo", search);
		return result;
	}
	
	@Cacheable(
			value="displayCategoryRepository.dspCtgrySubDspCtgryList",
			key="{"
					+ "#p0.mall,"
					+ "#p0.lang,"
					+ "#p0.device,"
					+ "#p1.ctgryDpthCd,"
					+ "#p1.dspCtgryTreeRootDspCtgryNo,"
					+ "#p1.notIncBrndNo"
					+ "}")
	public List<DspCtgryFoResult> selectSubDisplayCategoryList(SystemPK pk, DspCtgrySearchFoDTO search) throws Exception{
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>전시카테고리 하부 카테고리 목록 조회");
		search.setMallId(pk.getMall());
		search.setLang(pk.getLang());
		search.setDevice(pk.getDevice());
		
		List<DspCtgryFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectSubDspCtgryFoList", search);
		return result;
	}
	
	@Cacheable(
			value="displayCategoryRepository.godExistDspCtgryList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.mall,"
					+ "#p0.device,"
					+ "#p1.empYn,"
					+ "#p1.grpBrndId,"
					+ "#p1.prcSectCd,"
					+ "#p1.searchConditionBrandIds,"
					+ "#p1.spcPrmTp,"
					+ "#p1.special,"
					+ "#p1.useNew"
					+ "}")
	public List<DspCtgryFoResult> selectGodExistDisplayCategroyList(SystemPK pk, DspCtgrySearchFoDTO search) throws Exception{
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>조건에 따른 상품이 존재하는 카테고리 목록 조회");
		search.setMallId(pk.getMall());
		search.setLang(pk.getLang());
		search.setDevice(pk.getDevice());
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectGodExistDspCtgryFoList", search);
	}
	public List<DspCtgryFoResult> selectLocationDspCtgryList(SystemPK pk, DspCtgrySearchFoDTO searchDTO){

		searchDTO.setMallId(pk.getMall());
		searchDTO.setLang(pk.getLang());
		searchDTO.setDevice(pk.getDevice());
		
		List<DspCtgryFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectLocationDspCtgryList", searchDTO);
		return result;
	}
	public int selectDspCtgryConnGodTotCnt(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception {

		searchDTO.setMallId(pk.getMall());
		searchDTO.setLang(pk.getLang());
		searchDTO.setDevice(pk.getDevice());

		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCtgryGodTotCnt", searchDTO);
	}
	
	public List<DspCtgryGodFoResult> selectDspCtgryConnGodList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		
		searchDTO.setMallId(pk.getMall());
		searchDTO.setLang(pk.getLang());
		searchDTO.setDevice(pk.getDevice());
		
		RepositoryHelper.makePageEntityIndex(searchDTO, searchDTO.getPageParam());

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCtgryGodFoList", searchDTO);
		return result;
	}


	/************************************
	 * SR : #22516
	 * 판매지수 (Sales Index)를 FO에 반영
	 * 김병철
	 * 2016.07.21
	************************************/
	public List<DspCtgryGodFoResult> selectDspCtgryConnSaleIdxGodList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{

		searchDTO.setMallId(pk.getMall());
		searchDTO.setLang(pk.getLang());
		searchDTO.setDevice(pk.getDevice());

		RepositoryHelper.makePageEntityIndex(searchDTO, searchDTO.getPageParam());

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCtgryConnSaleIdxGodList", searchDTO);
		return result;
	}

	public List<DspCtgryGodFoResult> selectDspCtgryConnSpecialLimitGodList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{

		searchDTO.setMallId(pk.getMall());
		searchDTO.setLang(pk.getLang());
		searchDTO.setDevice(pk.getDevice());
		
		RepositoryHelper.makePageEntityIndex(searchDTO, searchDTO.getPageParam());
		
		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCtgrySpecialLimitGodList", searchDTO);
		return result;
	}
	public List<GodImg> selectGodImgList(String godNo) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectGodImgList", godNo);
	}
	
	@Cacheable("displayCategoryRepository.selectGodIconList")
	public List<GodIconFoResult> selectGodIconList(String godNo) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectGodIconFoList", godNo);
	}
	
	@Cacheable(value="displayCategoryRepository.selectGodIconList2", key="#p0?.toString()")
	public List<GodIconFoResult> selectGodIconList2(List<String> godNoList) throws Exception{
		HashMap<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("godNoList", godNoList);
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectGodIconFoList2", pMap);
	}
	public GodTagResveFoResult selectGodResve(SystemPK pk,  String godNo) throws Exception {

		HashMap<String,String> pMap = new HashMap<String,String>();
		pMap.put("lang", pk.getLang());
		pMap.put("godNo", godNo);
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectGodTagResve", pMap);
	}
	
	@Cacheable(
			value="displayCategoryRepository.selectGodResve2", 
			key="{"
			+ "#p0.lang, "
			+ "#p1?.toString()"
			+ "}")
	public List<GodTagResveFoResult> selectGodResve2(SystemPK pk,  List<String> godNoList) throws Exception {

		HashMap<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("lang", pk.getLang());
		pMap.put("godNoList", godNoList);
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectGodTagResve2", pMap);
	}
		
//	@Cacheable(
//			value="displayCategoryRepository.selectTestGodList",
//			key="{"
//					+ "#a0.lang,"
//					+ "#a0.mall,"
//					+ "#a0.device,"
//					+ "#a1?.dspCtgry.dspCtgryNo,"
//					+ "#a1.empYn,"
//					+ "#a1.newGodCondition,"
//					+ "#a1.prcSectCd,"
//					+ "#a1.searchConditionBrandIds,"
//					+ "#a1.searchConditionBrandBests,"
//					+ "#a1.searchConditionBrandAtoZs,"
//					+ "#a1.searchConditionColors,"
//					+ "#a1.searchConditionColors2,"
//					+ "#a1.searchConditionMaterials,"
//					+ "#a1.searchConditionSizes,"
//					+ "#a1.searchConditionStyles,"
//					+ "#a1.spcPrmTp,"
//					+ "#a1.special"
//					+ "}")
	public List<String> selectTestGodList(SystemPK pk, DspCtgrySearchFoDTO search) throws Exception {
		
		search.setMallId(pk.getMall());
		search.setLang(pk.getLang());
		search.setDevice(pk.getDevice());

		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCtgryCnncGodNoList", search);
	}
	public List<DspCtgryResultMbExt> selectMbDspCtgryCnncGodNoList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception {
		
		searchDTO.setMallId(pk.getMall());
		searchDTO.setLang(pk.getLang());
		searchDTO.setDevice(pk.getDevice());
		
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectMbDspCtgryCnncGodNoList", searchDTO);
	}
	public List<DspCtgryGodFoResult> selectBestGodList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception {
		
		searchDTO.setMallId(pk.getMall());
		searchDTO.setLang(pk.getLang());
		searchDTO.setDevice(pk.getDevice());
		
		SqlSession sqlSession = getSession1();
		return sqlSession.selectList("com.plgrim.ncp.biz.display.selectDspCtgryBestGodFoList", searchDTO);
	}
	
	public List<SysPopupNoti> selectSysPopupNoti(HashMap<String,String> map) throws Exception{
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>메인류(통합몰메인,대카메인,브랜드메인 등등 팝업" );
		SqlSession sqlSession = getSession1();
		return sqlSession.selectList("com.plgrim.ncp.biz.display.selectMainPopup", map);
	}
	
	/**
	 * My Brand 브랜드카테고리명 조회
	 *
	 * @param dspCtgryNos the dsp ctgry nos
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgry> selectMyBrand(List<String> dspCtgryNos) throws Exception {
		HashMap<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("dspCtgryNos", dspCtgryNos);
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectMyBrand", pMap);
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
		dto.setMallId(pk.getMall());
		dto.setLang(pk.getLang());
		dto.setDevice(pk.getDevice());
		
		List<DspCtgryFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectLeftMenuCtgry", dto);
		return result;
	}
	
	/**
	 * 전시카테고리 트리 조회.
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
	public List<DspCtgryBoResult> selectCategoryTree (DspCtgryBoDTO dspCtgryBoDTO) throws Exception{
		
		List<DspCtgryBoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectCategoryTree", dspCtgryBoDTO);
		return result;
	}
	
	/**
	 * 전시카테고리 검색 조회.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return List 카테고리 검색어 조회 결과 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 13
	 */
	public List<DspCtgryBoResult> selectCtgryList(DspCtgryBoDTO dspCtgryBoDTO, PageParam pageParam) throws Exception{

		RepositoryHelper.makePageEntityIndex(dspCtgryBoDTO, pageParam);
		
		List<DspCtgryBoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectCtgryList", dspCtgryBoDTO);
		return result;
	}
	
	/**
	 * 카테고리 검색어 조회 총count.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return Int 카테고리 검색 결과 총count
	 * @since 2015. 4. 20
	 */
	public int selectCtgryListTotalCount(DspCtgryBoDTO dspCtgryBoDTO){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectCtgryListTotalCount", dspCtgryBoDTO);		
	}
	
	
	/**
	 * 전시카테고리 검색 조회.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return List 카테고리 검색어 조회 결과 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 13
	 */
	public List<DspCtgryBoResult> selectCtgryListByCtgryNo(DspCtgryBoDTO dspCtgryBoDTO, PageParam pageParam) throws Exception{

		RepositoryHelper.makePageEntityIndex(dspCtgryBoDTO, pageParam);
		
		List<DspCtgryBoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectCtgryListByCtgryNo", dspCtgryBoDTO);
		return result;
	}
	
	/**
	 * 카테고리 검색어 조회 총count.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return Int 카테고리 검색 결과 총count
	 * @since 2015. 4. 20
	 */
	public int selectCtgryListByCtgryNoTotalCount(DspCtgryBoDTO dspCtgryBoDTO){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectCtgryListByCtgryNoTotalCount", dspCtgryBoDTO);		
	}
	
	/**
	 * 카테고리 번호 생성.
	 * 
	 * <p/>
	 * 
	 * 생성규칙 - 상위 카테고리번호 + 해당 depth 의 max + 1
	 *
	 * @param upperDspCtgryNo [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 15
	 */
	public String selectDspCtgryNo (String upperDspCtgryNo) throws Exception{
		
		String result = getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCtgryNo", upperDspCtgryNo);
		return result;
	}
	
	/**
	 * 카테고리 정보 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgry 카테고리 Entity
	 * @return Int 처리결과수
	 * @throws Exception the exception
	 * @since 2015. 4. 15
	 */
	public int updateDspCtgryInfo(DspCtgry dspCtgry) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspCtgryInfo", dspCtgry);
	}
	
	/**
	 * 카테고리 수정 - 하위카테고리 그리드에서 사용.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgry [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 19
	 */
	public int updateDspCtgryForGrid(DspCtgry dspCtgry) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspCtgryForGrid", dspCtgry);
	}

	/**
	 * 카테고리 삭제.
	 * 
	 * <p/>
	 * 
	 * 삭제여부 컬럼을 "Y"로 update한다.
	 *
	 * @param dspCtgry 카테고리 Entity
	 * @return Int 처리결과수
	 * @throws Exception the exception
	 * @since 2015. 4. 15
	 */
	public int deleteDspCtgryInfo(DspCtgry dspCtgry) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDeleteYn", dspCtgry);
	}
	
	
	/**
	 * 카테고리 언어 테이블 저장.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgryLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 15
	 */
	public int saveDspCtgryLang(DspCtgryLang dspCtgryLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.saveDspCtgryLang", dspCtgryLang);
	}
/*	
	public List<God> selectBrndBestGod(DspBrndBstGod brndBstGod) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrndBestGod", brndBstGod);
	}
*/	
	public List<GodImg> selectMbGodImgList(List<String> godNos) throws Exception {
		HashMap<String,List<String>> pMap = new HashMap<String,List<String>>();
		pMap.put("godNos", godNos);
		
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectMbGodImgList", pMap);
	}
	
	/**
	 * 카테고리의 전시여부 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgry [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 19
	 */
	public String selectDspYnCtgry(String dspCtgryNo)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspYnCtgry", dspCtgryNo);
	}
	
	
	/**
	 * 하위카테고리 전시여부 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgry [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 19
	 */
	public int updateDspYnCtgry(DspCtgry dspCtgry)  {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspYnCtgry", dspCtgry);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * [BO] 상품이 존재하는 카테고리 목록 조회
	 * @param dspCtgryBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<DspCtgryBoResult> selectGodExistDspCtgryBoList(DspCtgryBoDTO dspCtgryBoDTO) throws Exception{
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>[BO] 상품이 존재하는 카테고리 목록 조회");
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectGodExistDspCtgryBoList", dspCtgryBoDTO);
	}


	/*
	 * ---------------------------------------------------------------------
	 * V2 Location
	 * ---------------------------------------------------------------------
	 */

	@Cacheable(
			value="displayCategoryRepository.selectLocationList", 
			key="{"
					+ "#p0.brndId,"
					+ "#p0.lang,"
					+ "#p0.mallId,"
					+ "#p0.brandShopNo,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.ctgrySectCd,"
					+ "#p0.mallGubun"
					+ "}")
	public List<DspCtgry> selectLocationList(DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.v2.display.selectLocationList", dto);
	}

	public List<DspCtgry> selectMBLocationList(DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.v2.display.selectMBLocationList", dto);
	}


	/*
	 * ---------------------------------------------------------------------
	 * V2 LNB
	 * ---------------------------------------------------------------------
	 */

	@Cacheable(
			value="displayCategoryRepository.selectBrandAllList", 
			key="{"
					+ "#p0.prcSectCd,"
					+ "#p0.lang,"
					+ "#p0.mallId,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.mallGubun,"
                    + "#p0.prcSectCd"
					+ "}")
	public List<DspCtgry> selectBrandAllList(DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.v2.display.selectBrandLnbCtgryList", dto);
	}

	@Cacheable(
			value="displayCategoryRepository.selectBrandCtgryList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.brndId,"
					+ "#p0.prcSectCd,"
					+ "#p0.mallGubun,"
					+ "#p0.mallId,"
					+ "#p0.ctgrySectCd,"
					+ "#p0.dspCtgryNo,"
                    + "#p0.men,"
                    + "#p0.women,"
                    + "#p0.kids"
					+ "}")
	public List<DspCtgry> selectBrandCtgryList(DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.v2.display.selectLnbCtgryList", dto);
	}

	@Cacheable(
			value="displayCategoryRepository.selectBrandMBCtgryList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.brndId,"
					+ "#p0.prcSectCd,"
					+ "#p0.mallGubun,"
					+ "#p0.mallId,"
					+ "#p0.ctgrySectCd"
					+ "}")
	public List<DspCtgryLNBResult> selectBrandMBCtgryList(DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.v2.display.selectMBLnbCtgryList", dto);
	}

	@Cacheable("displayCategoryRepository.selectBrandInfo")
	public String selectBrandInfo(String brndDspCtgryNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.v2.display.selectBrandInfo", brndDspCtgryNo);
	}

	@Cacheable("displayCategoryRepository.selectCtgryInfo")
	public DspCtgry selectCtgryInfo(String brndDspCtgryNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.v2.display.selectCtgryInfo", brndDspCtgryNo);
	}

	public List<DspCtgry> selectBeakerBrandList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.v2.display.selectBeakerList", dspCtgryScFrDTO);
	}

	@Cacheable(
			value="displayCategoryRepository.selectCategoryChack", 
			key="{"
					+ "#p0.men,"
					+ "#p0.women,"
					+ "#p0.kids,"
					+ "#p0.brndId,"
					+ "#p0.prcSectCd,"
					+ "#p0.lang,"
					+ "#p0.mallId"
					+ "}")
	public CategoryChackResult selectCategoryChack(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.v2.display.selectCategoryChack", dspCtgryScFrDTO);
	}

	public List<DspCtgry> selectNewTopOnCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.v2.display.selectNewTopOnCtgryList", dspCtgryScFrDTO);
	}

	public List<DspCtgry> selectNewTopOnCtgryListOfInstore(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.v2.display.selectNewTopOnCtgryListOfInstore", dspCtgryScFrDTO);
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
	public List<DspCtgryBoResult> selectBrndCtgryTree (DspCtgryBoDTO dspCtgryBoDTO) {
		
		List<DspCtgryBoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectBrndCtgryTree", dspCtgryBoDTO);
		return result;
	}
	
	/**
	 * 전시 브랜드 카테고리가 존재하는지 체크
	 *
	 * @param dspBrndCtgry the dsp brnd ctgry
	 * @return the int
	 * @throws Exception the exception
	 */
	public int selectDspBrndCtgryCount(DspBrndCtgry dspBrndCtgry) {
		
		int result = getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspBrndCtgryCount", dspBrndCtgry);
		return result;
	}
	/**
	 * 카테고리 정보 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspBrndCtgry the dsp brnd ctgry
	 * @return Int 처리결과수
	 * @throws Exception the exception
	 * @since 2015. 4. 15
	 */
	public int updateDspBrndCtgryInfo(DspBrndCtgry dspBrndCtgry) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspBrndCtgryInfo", dspBrndCtgry);
	}
	
	/**
	 * Select dsp yn brnd ctgry.
	 *
	 * @param dspBrndCtgry the dsp brnd ctgry
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectDspYnBrndCtgry(DspBrndCtgry dspBrndCtgry)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspYnBrndCtgry", dspBrndCtgry);
	}
	
	/**
	 * 브랜드카테고리 전시여부 수정
	 *
	 * @param dspBrndCtgry the dsp brnd ctgry
	 * @return the int
	 * @throws Exception the exception
	 */
	public int updateDspYnBrndCtgry(DspBrndCtgry dspBrndCtgry)  {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspYnBrndCtgry", dspBrndCtgry);
	}
	
	/**
	 * 브랜드카테고리 수정 - 하위카테고리 그리드에서 사용.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspBrndCtgry the dsp brnd ctgry
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 19
	 */
	public int updateDspBrndCtgryForGrid(DspBrndCtgry dspBrndCtgry) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspBrndCtgryForGrid", dspBrndCtgry);
	}
	
	/**
	 * 카테고리 순서변경.
	 *
	 * @param dspCtgryBoDTO the dsp ctgry bo dto
	 * @throws Exception the exception
	 */
	public void updateCtgSortSeq(DspCtgryBoDTO dspCtgryBoDTO) throws Exception {
		 getSession1().update("com.plgrim.ncp.biz.display.updateCtgSortSeq", dspCtgryBoDTO);
	}

	@Cacheable(
			value="displayCategoryRepository.selectDspCtgryGodsListCnt", 
			key="{"
					+ "#p0.dspCtgry?.dspCtgryNo,"
                    + "#p0.mallGubun,"
					+ "#p0.lang,"
					+ "#p0.mallId,"
					+ "#p0.spcPrmTp,"
					+ "#p0.prcSectCd,"
					+ "#p0.empYn,"
					+ "#p0.searchConditionBrandId,"
					+ "#p0.searchConditionBrandBests?.toString(),"
					+ "#p0.searchConditionBrandAtoZs?.toString(),"
					+ "#p0.searchConditionColors?.toString(),"
					+ "#p0.searchConditionColors2?.toString(),"
					+ "#p0.searchConditionMaterials?.toString(),"
					+ "#p0.searchConditionSizes?.toString(),"
					+ "#p0.searchConditionStyles?.toString()"
					+ "}")
	public int selectDspCtgryGodsListCnt(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCtgryGodFoListTotCntNew", dspCtgrySearchFoDTO);
	}

	@Cacheable(value="displayCategoryRepository.selectDspCtgryGodList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgry?.dspCtgryNo,"
					+ "#p0.mallGubun,"
                    + "#p0.imgSizeCd,"
					+ "#p0.mallId,"
					+ "#p0.spcPrmTp,"
					+ "#p0.prcSectCd,"
					+ "#p0.empYn,"
					+ "#p0.searchConditionBrandId,"
					+ "#p0.searchConditionBrandBests?.toString(),"
					+ "#p0.searchConditionBrandAtoZs?.toString(),"
					+ "#p0.searchConditionColors?.toString(),"
					+ "#p0.searchConditionColors2?.toString(),"
					+ "#p0.searchConditionMaterials?.toString(),"
					+ "#p0.searchConditionSizes?.toString(),"
					+ "#p0.searchConditionStyles?.toString(),"
					+ "#p0.sortColumn,"
					+ "#p0.endIndex,"
					+ "#p0.beginIndex"
					+ "}")
	public List<DspCtgryGodFoResult> selectDspCtgryGodList(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCtgryGodFoListNew", dspCtgrySearchFoDTO);
		return result;
	}
	
	
	@Cacheable(
			value="displayCategoryRepository.selectDspCtgryDspGodListTotCnt", 
			key="{"
					+ "#p0.dspCtgry?.dspCtgryNo,"
                    + "#p0.mallGubun,"
					+ "#p0.lang,"
					+ "#p0.mallId,"
					+ "#p0.spcPrmTp,"
					+ "#p0.prcSectCd,"
					+ "#p0.empYn,"
					+ "#p0.searchConditionBrandId,"
					+ "#p0.searchConditionBrandBests?.toString(),"
					+ "#p0.searchConditionBrandAtoZs?.toString(),"
					+ "#p0.searchConditionColors?.toString(),"
					+ "#p0.searchConditionColors2?.toString(),"
					+ "#p0.searchConditionMaterials?.toString(),"
					+ "#p0.searchConditionSizes?.toString(),"
					+ "#p0.searchConditionStyles?.toString()"
					+ "}")	
	public int selectDspCtgryDspGodListTotCnt(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCtgryDspGodListTotCnt", dspCtgrySearchFoDTO);
	}

	@Cacheable(
			value="displayCategoryRepository.selectDspCtgryGodList", 
			key="{"
					+ "#p0.lang,"
					+ "#p0.device,"
		            + "#p0.mallGubun,"
		            + "#p0.dspCtgry?.dspCtgryNo,"
		            + "#p0.spcPrmTp,"
		            + "#p0.prcSectCd,"
		            + "#p0.empYn,"
		            + "#p0.searchConditionBrandId,"
		            + "#p0.searchConditionBrandBests?.toString(),"
		            + "#p0.searchConditionBrandAtoZs?.toString(),"
		            + "#p0.searchConditionColors?.toString(),"
		            + "#p0.searchConditionColors2?.toString(),"
		            + "#p0.searchConditionMaterials?.toString(),"
		            + "#p0.searchConditionSizes?.toString(),"
		            + "#p0.searchConditionStyles?.toString(),"
		            + "#p0.sortColumn,"
		            + "#p0.beginIndex,"
		            + "#p0.endIndex"
					+ "}")
	public List<DspCtgryGodFoResult> selectDspCtgryDspGodList(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCtgryDspGodList", dspCtgrySearchFoDTO);
		return result;
	}
	
	public int selectNewSaleTopLifestyleCnt(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectNewSaleTopLifestyleCnt", dspCtgrySearchFoDTO);
	}

	public List<DspCtgryGodFoResult> selectNewSaleTopLifestyleList(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectNewSaleTopLifestyleList", dspCtgrySearchFoDTO);

		return result;
	}


	@Cacheable(value="displayCategoryRepository.selectCtgryNo", key="{"
			+ "#p0.dspCtgry?.dspCtgryNo,"
			+ "#p0.dspCtgry?.ctgrySectCd"
			+ "}")
	public List<DspCtgryFoResult> selectV2DspCtgryNo(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectCtgryNo", dspCtgrySearchFoDTO);
	}

	public List<String> selectCtgryCnncGodNo(DspCtgryScFrDTO dspCtgryFrDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectCtgryCnncGodNo", dspCtgryFrDTO);
	}

	public List<String> selectTopNewSaleGodNo(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectTopNewSaleGodNo", dspCtgryScFrDTO);
	}

	public List<String> selectCtgryNmListPC (DspCtgryScFrDTO dspCtgryFrDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectUpperCtgryListPC", dspCtgryFrDTO);
	}

	public List<String> selectCtgryNmList(DspCtgryScFrDTO dspCtgryFrDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectUpperCtgryList", dspCtgryFrDTO);
	}

	public List<DspCtgryGodFoResult> selectRecommendationList(DspCtgrySearchFoDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectRecommendationList", dto);
	}

	public List<DspCtgryFoResult> selectLowerCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws  Exception {
	    return getSession1().selectList("com.plgrim.ncp.biz.display.selectLowerCtgryList", dspCtgryScFrDTO);
    }

	public int otltGodCheck(String godNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.otltGodCheck", godNo);
	}

	public int selectCnncCtgryCnt(String godNo) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectCnncCtgryCnt", godNo);
	}

	public DspCtgryCnncGodExcelResult selectDspCtgryCnncGodInfo(String godNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCtgryCnncGodInfo", godNo);
	}

	public void insertDspCtgryLangMetaTag(DspCtgryLang dspCtgryLang) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.display.insertDspCtgryLangMetaTag", dspCtgryLang);
	}

	public void updateDspCtgryLangMetaTag(DspCtgryLang dspCtgryLang) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspCtgryLangMetaTag", dspCtgryLang);
	}

	/**
	 * 수트(상품)파인더 상품 전시 리스트 조회 Repository
	 * @author Dave
	 * @since 2017.08.14
	 */
	public List<DspCtgryGodFoResult> selectGoodsFinderList(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) {
		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectGoodsFinderGodFoList", dspCtgrySearchFoDTO);
		return result;
	}

	public String selectBrandParam(String scDspBrndId) {
		String brndShopNo = getSession1().selectOne("com.plgrim.ncp.biz.display.selectBrandParam", scDspBrndId);
		return brndShopNo;
	}

	public String selectBrandId(String scDspCtgryNo) {
		String brndShopId = getSession1().selectOne("com.plgrim.ncp.biz.display.selectBrandId", scDspCtgryNo);
		return brndShopId;
	}
	
	/**
	 * 브랜드 카테고리 팝업
	 *
	 * @param dspCtgryBoDTO the dsp ctgry bo DTO
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryBoResult> selectBrndCtgryTreePopup (DspCtgryBoDTO dspCtgryBoDTO) {
		
		List<DspCtgryBoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectBrndCtgryTreePopup", dspCtgryBoDTO);
		return result;
	}
	
	/**
	 * 전시카테고리 검색 조회.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return List 카테고리 검색어 조회 결과 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 13
	 */
	public List<DspCtgryBoResult> selectBrndCtgryKwdPopup(DspCtgryBoDTO dspCtgryBoDTO, PageParam pageParam) {

		RepositoryHelper.makePageEntityIndex(dspCtgryBoDTO, pageParam);
		
		List<DspCtgryBoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectBrndCtgryKwdPopup", dspCtgryBoDTO);
		return result;
	}
	
	/**
	 * 카테고리 검색어 조회 총count.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryBoDTO BO카테고리 관리를 위한 DTO
	 * @return Int 카테고리 검색 결과 총count
	 * @since 2015. 4. 20
	 */
	public int selectBrndCtgryKwdPopupTotalCount(DspCtgryBoDTO dspCtgryBoDTO){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectBrndCtgryKwdPopupTotalCount", dspCtgryBoDTO);		
	}
}
