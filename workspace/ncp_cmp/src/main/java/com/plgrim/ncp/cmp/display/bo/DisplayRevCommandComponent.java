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

import com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO;
import com.plgrim.ncp.biz.display.data.DspConttAbTestDTO;
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
public interface DisplayRevCommandComponent {
	
	
	/**
	 * AB 테스트 정보 수정
	 *
	 * @param dspRevDTO the dsp rev dto
	 * @return the int
	 * @ the exception
	 */
	//수정
	public int modifyAbTest(DspConttAbTestDTO dspConttAbTestDTO) ;
			
	/**
	 * AB 테스트 기간 수정
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @return the int
	 */
	public int modifyAbTestDate(DspConttAbTestDTO dspConttAbTestDTO) ;
	
	/**
	 * A/B 테스트 등록
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the long
	 * @ the exception
	 */
	public long addConttAbTest(DspConttAbTestDTO dspConttAbTestDTO) ; 
	
	/**
	 * A/B 테스트 삭제
	 *
	 * @param dspAbTestDTO the dsp ab test DTO
	 * @return the int
	 * @ the exception
	 */
	public void deleteConttAbTest(DspConttAbTestDTO dspConttAbTestDTO) ;
	
	/**
	 * A/B 테스트 대조군/실험군 등록
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @return the long
	 * @ the exception
	 */
	public void addConttAbTestRev(DspConttAbTestDTO dspConttAbTestDTO) ;
	
	/**
	 * A/B 테스트 대조군/실험군 노출설정 저장
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @ the exception
	 */
	public void saveConttAbTestExpsr(DspConttAbTestDTO dspConttAbTestDTO) ;
	
	/**
	 * AB 테스트 진행중인 컨텐츠를 수정할 경우 수정 이력 생성 저장
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @ the exception
	 */
	public void modCompleteAbTest(DspCnrConttScBoDTO dspCnrConttScBoDTO) ;
	
	/**
	 * AB 테스트 진행중인 컨텐츠를 수정 취소할 경우 수정 이력 생성 삭제
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @ the exception
	 */
	public void modCancelAbTest(DspCnrConttScBoDTO dspCnrConttScBoDTO) ;


	public void foApply(DspCnrConttScBoDTO dspCnrConttScBoDTO) ;
}
