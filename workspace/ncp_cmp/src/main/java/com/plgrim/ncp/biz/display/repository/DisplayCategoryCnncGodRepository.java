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
 * @since       2015. 6. 10       
 */
package com.plgrim.ncp.biz.display.repository;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;
import com.plgrim.ncp.base.repository.dsp.DspCtgryCnncGodRepository;
import com.plgrim.ncp.biz.display.data.DspCtgryCnncGodBoDTO;


@Slf4j
@Repository
public class DisplayCategoryCnncGodRepository extends DspCtgryCnncGodRepository{

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
	 * 전시 여부 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgryCnncGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int updateCtgryCnncGodInfo(DspCtgryCnncGod dspCtgryCnncGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateCtgryCnncGodInfo", dspCtgryCnncGod);
	}
	
	/**
	 * 해당 카테고리의 연결 상품 전체 삭제
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryNo [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public int deleteCtgryCnncGodInfo(String dspCtgryNo) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteCtgryCnncGodInfo", dspCtgryNo);
	}
	
	/**
	 * 특정 카테고리에 연결된 상품의 max전시순서 조회.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryCnncGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 23
	 */
	public int selectCtgryCnncGodMaxSortSeq(String dspCtgryNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectCtgryCnncGodMaxSortSeq", dspCtgryNo);
	}

	/**
	 * 카테고리 연결 상품 복사.
	 * 
	 * <p/>
	 * 
	 * arDspCtgryNo 에서 중복 제거 후 Leaf카테고리의 연결 상품 목록을 select 하여
	 * 이미 등록되어 있는 상품 제외 후 
	 * dspCtgryCnncGod.dspCtgryNo 로 복사한다.
	 *
	 * @param dspCtgryCnncGodBoDTO [설명]
	 * @return Int 처리결과수
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public int copyCtgryCnncGod(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.display.copyCtgryCnncGod", dspCtgryCnncGodBoDTO);
	}
	
	/**
	 * 카테고리 연결 상품 등록 시 중복 체크를 위해 특정카테고리에 연결되어있는 상품번호 조회.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCtgryNo 카테고리 번호
	 * @return List 상품번호 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 29
	 */
	public List<String> selectCnncGodList(String dspCtgryNo) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectCnncGodList", dspCtgryNo);
	}
	
	/**
	 * 카테고리 연결 상품 전시순서 업로드.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgryCnncGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 10
	 */
	public int updateSortSeqGod(DspCtgryCnncGod dspCtgryCnncGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateSortSeqGod", dspCtgryCnncGod);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	// 2016/07 (UX/UI) 
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 카테고리 연결 상품 전시순서 업로드. - 브랜드카테고리
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgryCnncGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 10
	 */
	public int updateBrndCtgrySortSeqGod(DspCtgryCnncGod dspCtgryCnncGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateBrndCtgrySortSeqGod", dspCtgryCnncGod);
	}
	
	/**
	 * 브랜드카테고리 연결상품 전시브랜드ID 확인
	 *
	 * @param godNo the god no
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectBrndGrpIdCnncGod(String godNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectBrndGrpIdCnncGod", godNo);
	}
	
	/**
	 * 카테고리 연결상품 이동 시 신규 카테고리로 상품 등록.
	 *
	 * @param dspCtgryCnncGodBoDTO the dsp ctgry cnnc god bo dto
	 * @return the int
	 * @throws Exception the exception
	 */
	public int moveCtgryCnncGod(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.moveCtgryCnncGod", dspCtgryCnncGodBoDTO);
	}
	
	/**
	 * 카테고리 연결상품 이동 시 기존 카테고리의 상품 삭제.
	 *
	 * @param dspCtgryCnncGodBoDTO the dsp ctgry cnnc god bo dto
	 * @return the int
	 * @throws Exception the exception
	 */
	public int deleteMoveCtgryCnncGod(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.deleteMoveCtgryCnncGod", dspCtgryCnncGodBoDTO);
	}
	
	/**
	 * 아웃렛 카테고리로 상품 이동 시 해당 상품의 무료수선여부를 'Y'로 update
	 *
	 * @param dspCtgryCnncGodBoDTO the dsp ctgry cnnc god bo dto
	 * @return the int
	 * @throws Exception the exception
	 */
	public int updateGodFreeRepairPsbYn(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateGodFreeRepairPsbYn", dspCtgryCnncGodBoDTO);
	}
	
	/**
	 * 상품복사(불러오기)  아울렛에서 일반카테고리를 불러올 경우 :: 반대의 경우는 영향도 없음
	 *
	 * @param dspCtgryCnncGodBoDTO the dsp ctgry cnnc god bo dto
	 * @return the int
	 * @throws Exception the exception
	 */
	public int updateGodCtgryCopy(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateGodCtgryCopy", dspCtgryCnncGodBoDTO);
	}
	
	/**
	 * 아울렛 => 일반 카테고리로 상품 이동 시 해당 상품의 멤버쉽 정보 update
	 *
	 * @param dspCtgryCnncGodBoDTO the dsp ctgry cnnc god bo dto
	 * @return the int
	 * @throws Exception the exception
	 */
	public int updateGodPntAccml(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateGodPntAccml", dspCtgryCnncGodBoDTO);
	}
	
	/**
	 * 카테고리 연결상품 이동 시 중복상품 조회
	 *
	 * @param dspCtgryNo the dsp ctgry no
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<String> selectDupCnncGodList(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDupCnncGodList", dspCtgryCnncGodBoDTO);
	}
	
	/**
	 * 아웃렛 카테고리에 걸린 상품인지 확인. - 상품등록 시 사용
	 *
	 * @param godNo the god no
	 * @return the string
	 * @throws Exception the exception
	 */
	public Integer selectOutletCtgGod(String godNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectOutletCtgGod", godNo);
	}
	
	/**
	 * 아웃렛 카테고리인지 확인. - 상품복사 시 사용
	 *
	 * @param dspCtgryCnncGodBoDTO the dsp ctgry cnnc god bo dto
	 * @return the integer
	 * @throws Exception the exception
	 */
	public Integer selectOutletCtgForCopy(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectOutletCtgForCopy", dspCtgryCnncGodBoDTO);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
