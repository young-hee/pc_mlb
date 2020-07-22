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
package com.plgrim.ncp.cmp.display.bo;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRev;
import com.plgrim.ncp.biz.display.data.DspAbTestAnlSearchDTO;
import com.plgrim.ncp.biz.display.data.DspAbTestBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO;
import com.plgrim.ncp.biz.display.data.DspConttAbTestDTO;
import com.plgrim.ncp.biz.display.result.DspAbTestResult;
import com.plgrim.ncp.biz.display.result.DspConttAbTestResult;
import com.plgrim.ncp.biz.display.result.DspRevCpstResult;
import com.plgrim.ncp.framework.page.PageParam;
/**
 * 전시 리비전 Component
 * 
 * <p>
 * 
 * <ul>
 *   <li> 리비전 관리 기능 </li>
 *   <li> AB Test 관리 기능 </li>
 * </ul>.
 *
 * @author shsunhee.kim
 * @since 2017. 7. 13
 */
public interface DisplayRevSelectComponent {
	
	// * abtest
	/**
	 * AB 테스트 목록 조회
	 *
	 * @param dspAbTestDTO the dsp cnr dto
	 * @param pageParam the page param
	 * @return the list
	 * @ the exception
	 */
	public List<DspAbTestResult> selectAbTestList(DspAbTestBoDTO dspAbTestDTO, PageParam pageParam) ;
	
	/**
	 * AB 테스트 total count.
	 *
	 * @param dspAbTestDTO the dsp ab test dto
	 * @return the integer
	 * @ the exception
	 */
	public Integer selectAbTestTotalCount(DspAbTestBoDTO dspAbTestDTO) ;
	
	
	/**
	 * AB 테스트 상세정보 조회
	 *
	 * @param abTestSn the rev sn
	 * @return the dsp rev result
	 * @ the exception
	 */
	//상세조회
	public DspAbTestResult selectAbTestDetail(int abTestSn) ;
	
	
	/**
	 * 특정 페이지의 리비전 번호 조회.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @return the long
	 * @ the exception
	 */
	public Long selectPageRevByAdmin(DspCnrConttScBoDTO dspCnrConttScBoDTO)  ;

	/**
	 * 특정 페이지 (KEY)에 존재하는 ABTEST 존재여부 조회
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the string
	 * @ the exception
	 */
	public String selectPageAbTestCount(DspCnrConttScBoDTO dspCnrConttScBoDTO) ;
	
	/**
	 * 특정 KEY에 존재하는 템플릿 리비전 목록 조회
	 *
	 * @param dspCnrConttScBoDTO
	 * @return
	 * @
	 */
	public List<DspRevCpstResult> selectRevCpstList(DspCnrConttScBoDTO dspCnrConttScBoDTO) ;

	/**
	 * 리비전 정보 조회
	 *
	 * @param dspRev
	 * @return
	 * @
	 */
	public DspRev selectDspRev(DspRev dspRev) ;

	/**
	 * 컨텐츠 미리보기 URL
	 *
	 * @param dspCnrConttScBoDTO
	 * @return
	 * @
	 */
	public String getPreviewUrl(DspCnrConttScBoDTO dspCnrConttScBoDTO) ;
	
	/**
	 * 컨텐츠 관리의 AB-TEST 리비전 목록
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the list
	 * @ the exception
	 */
	public DspConttAbTestResult selectConttAbTestRevList(DspCnrConttScBoDTO dspCnrConttScBoDTO) ;
	
	/**
	 * A/B테스트 분석결과 데이터 존재여부 체크
	 *
	 * @param dspAbTest the dsp ab test
	 * @return the integer
	 */
	public Integer selectAbTestAnlCount(DspAbTest dspAbTest); 
	
	/**
	 * AB-TEST 조회 - 컨텐츠관리
	 *
	 * @param dspAbTest the dsp ab test
	 * @return the dsp ab test
	 * @ the exception
	 */
	public DspAbTest selectAbTestConttDetail(DspAbTest dspAbTest) ;
	
	public int selectCstmrSgmtPercent(DspConttAbTestDTO dspConttAbTestDTO) ;

	public DspConttAbTestResult selectAbTestExpsrList(DspAbTestAnlSearchDTO searchDTO, DspCnrConttScBoDTO dspCnrConttScBoDTO) ;

	public String selectMallId(DspCnrConttScBoDTO dspCnrConttScBoDTO) ;
	
//	/**
//	 * BASE 리비전번호 조회
//	 *
//	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
//	 * @return the base rev sn
//	 * @ the exception
//	 */
//	public Long getBaseRevSn (DspCnrConttScBoDTO dspCnrConttScBoDTO) ;
	
	/**
	 * BASE 리비전 조회
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the dsp rev cpst result
	 */
	public DspRevCpstResult selectBaseRev(DspCnrConttScBoDTO dspCnrConttScBoDTO);
}
