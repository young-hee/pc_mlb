package com.plgrim.ncp.cmp.display.bo.abtest;

import java.util.List;

import com.plgrim.ncp.cmp.display.bo.DisplayAbTestAnlSelectComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.biz.display.data.DspAbTestAnlSearchDTO;
import com.plgrim.ncp.biz.display.result.DspAbTestAnlResult;
import com.plgrim.ncp.biz.display.result.DspAbTestExpsrRtResult;
import com.plgrim.ncp.biz.display.service.DisplayAbTestAnlService;

@Component
public class DisplayAbTestAnlSelectComponentImpl extends AbstractComponent implements DisplayAbTestAnlSelectComponent {
	 
	@Autowired
	DisplayAbTestAnlService displayAbTestAnlService;
	
	/**
	 * AB-TEST 노출비율세팅정보 조회
	 * @param searchDTO
	 * @return List<DspAbTestExpsrRtResult>
	 */
	@Override
	public List<DspAbTestExpsrRtResult> selectABTestExpsrRt(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlService.selectABTestExpsrRt(searchDTO);
	}
	
	/**
	 * AB-TEST 노출비율세팅정보 다른 세트 노출율 조회
	 * @param searchDTO
	 * @return List<DspAbTestExpsrRtResult>
	 */
	@Override
	public List<DspAbTestExpsrRtResult> selectABTestExpsrRtForAnotherSet(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlService.selectABTestExpsrRtForAnotherSet(searchDTO);
	}
	
	/**
	 * AB-TEST 결과 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	@Override
	public List<DspAbTestAnlResult> selectABTestAnl(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlService.selectABTestAnl(searchDTO);
	}
	
	/**
	 * AB-TEST 변경 이력 조회
	 * @param searchDTO
	 * @return List<DspAbTestExpsrRtResult>
	 */
	@Override
	public List<DspAbTestExpsrRtResult> selectABTestModHistory(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlService.selectABTestModHistory(searchDTO);
	}
	
	/**
	 * AB-TEST 결과 일자별 추이 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	@Override
	public List<DspAbTestAnlResult> selectABTestAnlTransitionByDate(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlService.selectABTestAnlTransitionByDate(searchDTO);
	}

	/**
	 * 고객 세그먼트 노출비율세팅정보 조회
	 * @param searchDTO
	 * @return List<DspAbTestExpsrRtResult>
	 */
	@Override
	public List<DspAbTestExpsrRtResult> selectABTestSegmentExpsrRt(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlService.selectABTestSegmentExpsrRt(searchDTO);
	}

	/**
	 * 고객 세그먼트 결과 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	@Override
	public List<DspAbTestAnlResult> selectABTestSegmentAnl(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlService.selectABTestSegmentAnl(searchDTO);
	}

	/**
	 * 고객 세그먼트 결과 일자별 추이 조회
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	@Override
	public List<DspAbTestAnlResult> selectABTestSegmentAnlTransitionByDate(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlService.selectABTestSegmentAnlTransitionByDate(searchDTO);
	}

	/**
	 * 고객 세그먼트 CTR상세 조회 (코너별 클릭수/CTR)
	 * @param searchDTO
	 * @return List<DspAbTestAnlResult>
	 */
	@Override
	public List<DspAbTestAnlResult> selectABTestSegmentCTRDetailByCornerList(DspAbTestAnlSearchDTO searchDTO) {
		return displayAbTestAnlService.selectABTestSegmentCTRDetailByCornerList(searchDTO);
	}
}