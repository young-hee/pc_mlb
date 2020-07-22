package com.plgrim.ncp.cmp.display.bo;

import java.util.List;

import com.plgrim.ncp.biz.display.data.DspAbTestAnlSearchDTO;
import com.plgrim.ncp.biz.display.result.DspAbTestAnlResult;
import com.plgrim.ncp.biz.display.result.DspAbTestExpsrRtResult;

public interface DisplayAbTestAnlSelectComponent {
	/**
	 * AB-TEST 노출비율세팅정보 조회
	 * @param searchDTO
	 * @return List<TestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestExpsrRt(DspAbTestAnlSearchDTO searchDTO);
	
	/**
	 * AB-TEST 노출비율세팅정보 다른 세트 노출율 조회
	 * @param searchDTO
	 * @return List<TestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestExpsrRtForAnotherSet(DspAbTestAnlSearchDTO searchDTO);
	
	/**
	 * AB-TEST 결과 조회
	 * @param testSearchDTO
	 * @return List<TestExpsrRtResult>
	 */
	public List<DspAbTestAnlResult> selectABTestAnl(DspAbTestAnlSearchDTO searchDTO);

	/**
	 * AB-TEST 변경 이력 조회
	 * @param searchDTO
	 * @return List<DspAbTestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestModHistory(DspAbTestAnlSearchDTO searchDTO);
	
	/**
	 * AB-TEST 결과 일자별 추이 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestAnlTransitionByDate(DspAbTestAnlSearchDTO searchDTO);

	/**
	 * 고객 세그먼트 노출비율세팅정보 조회
	 * @param searchDTO
	 * @return List<TestExpsrRtResult>
	 */
	public List<DspAbTestExpsrRtResult> selectABTestSegmentExpsrRt(DspAbTestAnlSearchDTO searchDTO);

	/**
	 * 고객 세그먼트 결과 조회
	 * @param testSearchDTO
	 * @return List<TestExpsrRtResult>
	 */
	public List<DspAbTestAnlResult> selectABTestSegmentAnl(DspAbTestAnlSearchDTO searchDTO);

	/**
	 * 고객 세그먼트 결과 일자별 추이 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestSegmentAnlTransitionByDate(DspAbTestAnlSearchDTO searchDTO);

	/**
	 * 고객 세그먼트 CTR상세 조회 (코너별 클릭수/CTR)
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	public List<DspAbTestAnlResult> selectABTestSegmentCTRDetailByCornerList(DspAbTestAnlSearchDTO searchDTO);
}
