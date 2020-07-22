package com.plgrim.ncp.biz.display.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.display.data.DspAbTestAnlSearchDTO;
import com.plgrim.ncp.biz.display.result.DspAbTestAnlResult;
import com.plgrim.ncp.biz.display.result.DspAbTestExpsrRtResult;

@Repository
public class DisplayAbTestAnlRepository extends AbstractRepository {
 
	/**
	 * AB-TEST 노출비율세팅정보 조회
	 * @param searchDTO
	 * @return List<DspAbTestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestExpsrRt(DspAbTestAnlSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectABTestExpsrRt", searchDTO);
	}
	
	/**
	 * AB-TEST 다른 세트 노출비율세팅정보 조회
	 * 세트 사용여부가 'Y'인 AB-TEST의 설정된 노출비율에 다른 세트 비율 합계를 보여주기 위해 조회함
	 * @param searchDTO
	 * @return List<DspAbTestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestExpsrRtForAnotherSet(DspAbTestAnlSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectABTestExpsrRtForAnotherSet", searchDTO);
	}
	
	/**
	 * AB-TEST 결과 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestAnl(DspAbTestAnlSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectABTestAnl", searchDTO);
	}

	/**
	 * AB-TEST 변경 이력 조회
	 * @param searchDTO
	 * @return List<DspAbTestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestModHistory(DspAbTestAnlSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectABTestModHistory", searchDTO);
	}

	public List<DspAbTestAnlResult> selectAbTestExpsrAnl(DspAbTestAnlSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectAbTestExpsrAnl", searchDTO);
	}
	
	/**
	 * AB-TEST 결과 일자별 추이 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestAnlTransitionByDate(DspAbTestAnlSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectABTestAnlTransitionByDate", searchDTO);
	}

	/**
	 * 고객 세그먼트 노출비율세팅정보 조회
	 * @param searchDTO
	 * @return List<DspAbTestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestSegmentExpsrRt(DspAbTestAnlSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectABTestSegmentExpsrRt", searchDTO);
	}

	/**
	 * 고객 세그먼트 결과 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestSegmentAnl(DspAbTestAnlSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectABTestSegmentAnl", searchDTO);
	}

	/**
	 * 고객 세그먼트 결과 일자별 추이 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestSegmentAnlTransitionByDate(DspAbTestAnlSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectABTestSegmentAnlTransitionByDate", searchDTO);
	}

	/**
	 * 고객 세그먼트 CTR상세 조회 (코너별 클릭수/CTR)
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestSegmentCTRDetailByCornerList(DspAbTestAnlSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectABTestSegmentCTRDetailByCornerList", searchDTO);
	}
}
