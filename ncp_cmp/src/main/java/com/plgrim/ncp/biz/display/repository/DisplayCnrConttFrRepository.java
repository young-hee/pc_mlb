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
package com.plgrim.ncp.biz.display.repository;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.repository.dsp.DspCnrRepository;
import com.plgrim.ncp.biz.display.data.DspCnrScFrDTO;
import com.plgrim.ncp.biz.display.result.DspCnrConttFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspTmplatFrResult;


/**
 * 코너컨텐츠 목록 조회 Repository.
 *
 * @author shsunhee.kim
 * @since 2015. 3. 13
 */
@Repository
@Slf4j
public class DisplayCnrConttFrRepository extends DspCnrRepository {
	
	/**
	 * 코너 컨텐츠 Full Data 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 13
	 */
	public List<DspCnrFrResult> selectDspCnrConttList(DspCnrScFrDTO dto) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCnrConttFullList", dto);
	}
	
	/**
	 * 템플릿정보, 연결 코너목록 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dto [설명]
	 * @return Dsp tmplat fr result [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 15
	 */
	public List<DspTmplatFrResult> selectTmplatPageInfo(DspCnrScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectTmplatPageInfo", dto);
	}
	
	/**
	 * 카테고리 베스트.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectCtgryBstGodList(DspCnrScFrDTO dto) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectCtgryBstGodList", dto);
	}
	
	/**
	 * 카테고리 신상품.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectCtgryNewGodList(DspCnrScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectCtgryNewGodList", dto);
	}
	
	/**
	 * 브랜드 시즌 베스트.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectBrndSesonBstGodList(DspCnrScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrndSesonBstGodList", dto);
	}
	
	/**
	 * 브랜드 시즌 신상품.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectBrndSesonNewGodList(DspCnrScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrndSesonNewGodList", dto);
	}
	
	/**
	 * 브랜드 베스트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectBrndBstGodList(DspCnrScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrndBstGodList", dto);
	}
	
	/**
	 * 브랜드 신상품.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public List<DspCnrConttFrResult> selectBrndNewGodList(DspCnrScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrndNewGodList", dto);
	}
	
	/**
	 * 해당 브랜드에 등록된 최신 S-Trnd조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 23
	 */
	public Long selectStrndSnForBrnd(DspCnrScFrDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectStrndSnForBrnd", dto);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	// 2016/07 (UX/UI) 
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 코너 컨텐츠 Full Data 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 13
	 */
	@Cacheable(
			value="displayCnrConttFrRepository.selectDspCnrConttListV2", 
			condition="#p0.isPrevYn != 'Y'",
			key="{ "
					+ "#p0.lang, "
					+ "#p0.mallId, "
					+ "#p0.device, "
                    + "#p0.strndTpCd, "
                    + "#p0.cnrSn, "
                    + "#p0.tmplatTp, "
                    + "#p0.tmplatKeyBrndId, "
                    + "#p0.tmplatScKey, "
                    + "#p0.aplMbrAtrb, "
                    + "#p0.grpcoId, "
                    + "#p0.spcPrmTp, "
                    + "#p0.empYn, "
                    + "#p0.prcSectCd, "
                    + "#p0.imgSize, "
					+ "#p0.conttTpList?.toString(),"
                    + "#p0.filterBrndCd,"
                    + "#p0.revSn"
					+ "}")
	public List<DspCnrFrResult> selectDspCnrConttListV2(DspCnrScFrDTO dto) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCnrConttFullListV2", dto);
	}
	
	/**
	 * 템플릿정보, 연결 코너목록 조회. v2.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	@Cacheable(
			value="displayCnrConttFrRepository.selectTmplatPageInfoV2", 
            condition="#p0.isPrevYn != 'Y'",
			key="{"
					+ "#p0.tmplatTp,"
					+ "#p0.tmplatKeyBrndId,"
					+ "#p0.lang,"
					+ "#p0.device,"
					+ "#p0.tmplatScKey,"
					+ "#p0.mallId,"
					+ "#p0.strndTpCd,"
					+ "#p0.prev,"
                    + "#p0.revSn"
					+ "}")
	public List<DspTmplatFrResult> selectTmplatPageInfoV2(DspCnrScFrDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectTmplatPageInfoV2", dto);
	}
	
	/**
	 * 브랜드카테고리의 전시브랜드ID조회
	 *
	 * @param dspCtgryNo the dsp ctgry no
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectBrndCtgryBrndId(String dspCtgryNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectBrndCtgryBrndId", dspCtgryNo);
	}
	
	/**
	 * Select dsp cnr contt tp.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	@Cacheable(
			value="displayCnrConttFrRepository.selectDspCnrConttTp", 
            condition="#p0.isPrevYn != 'Y'",
			key="{"
					+ "#p0.tmplatTp,"
					+ "#p0.tmplatKeyBrndId,"
					+ "#p0.tmplatSn,"
					+ "#p0.tmplatScKey,"
                    + "#p0.revSn"
					+ "}")
	public List<String> selectDspCnrConttTp(DspCnrScFrDTO dto) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCnrConttTp", dto);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	
}
