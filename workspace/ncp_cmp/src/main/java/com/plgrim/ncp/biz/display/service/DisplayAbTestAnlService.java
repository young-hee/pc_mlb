package com.plgrim.ncp.biz.display.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.biz.display.data.DspAbTestAnlSearchDTO;
import com.plgrim.ncp.biz.display.repository.DisplayAbTestAnlRepository;
import com.plgrim.ncp.biz.display.result.DspAbTestAnlResult;
import com.plgrim.ncp.biz.display.result.DspAbTestExpsrRtResult;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Service
public class DisplayAbTestAnlService extends AbstractService {
	
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
			
	@Autowired
	DisplayAbTestAnlRepository displayAbTestAnlRepository;
	
	/**
	 * AB-TEST 노출비율세팅정보 조회
	 * @param testSearchDTO
	 * @return List<TestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestExpsrRt(DspAbTestAnlSearchDTO searchDTO) {
		return  displayAbTestAnlRepository.selectABTestExpsrRt(searchDTO);
	}
	
	/**
	 * AB-TEST 노출비율세팅정보 조회
	 * 세트사용여부가 'Y'인 AB-TEST의 노출비율 Chart에 현재 조회된 세트 외의 다른 세트 노출비율을 함께 보여주기 위해 조회
	 * @param testSearchDTO
	 * @return List<TestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestExpsrRtForAnotherSet(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlRepository.selectABTestExpsrRtForAnotherSet(searchDTO);
	}
	
	/**
	 * AB-TEST 결과 조회
	 * @param testSearchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestAnl(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlRepository.selectABTestAnl(searchDTO);
	}

	/**
	 * AB-TEST 변경 이력 조회
	 * @param testSearchDTO
	 * @return List<DspAbTestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestModHistory(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlRepository.selectABTestModHistory(searchDTO);
	}

	public List<DspAbTestAnlResult> selectAbTestExpsrAnl(DspAbTestAnlSearchDTO searchDTO){
		return displayAbTestAnlRepository.selectAbTestExpsrAnl(searchDTO);
	}
	
	/**
	 * AB-TEST 결과 일자별 추이 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestAnlTransitionByDate(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlRepository.selectABTestAnlTransitionByDate(searchDTO);
	}

	/**
	 * 고객 세그먼트 노출비율세팅정보 조회
	 * @param testSearchDTO
	 * @return List<TestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestSegmentExpsrRt(DspAbTestAnlSearchDTO searchDTO) {
		return  displayAbTestAnlRepository.selectABTestSegmentExpsrRt(searchDTO);
	}

	/**
	 * 고객 세그먼트 결과 조회
	 * @param testSearchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestSegmentAnl(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlRepository.selectABTestSegmentAnl(searchDTO);
	}

	/**
	 * 고객 세그먼트 결과 일자별 추이 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestSegmentAnlTransitionByDate(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlRepository.selectABTestSegmentAnlTransitionByDate(searchDTO);
	}

	/**
	 * 고객 세그먼트 CTR상세 조회 (코너별 클릭수/CTR)
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestSegmentCTRDetailByCornerList(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlRepository.selectABTestSegmentCTRDetailByCornerList(searchDTO);
	}
}
