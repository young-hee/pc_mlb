package com.plgrim.ncp.cmp.settlement;

import com.plgrim.ncp.biz.helpdesk.data.HistoryInfoFoDTO;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventSearchDTO;
import com.plgrim.ncp.biz.settlement.result.PrmFeeEventListResult;
import com.plgrim.ncp.biz.settlement.service.PrmFeeEventService;
import com.plgrim.ncp.biz.settlement.service.SettlementSelectService;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SettlementSelectComponentImpl implements SettlementSelectComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 수수료행사관리
	 */
	@Autowired
	PrmFeeEventService prmFeeEventService;

	@Autowired
	SettlementSelectService settlementSelectService;

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
	 * 수수료행사관리 리스트
	 */
	@Override
	public Page<PrmFeeEventListResult> getPrmFeeEventPageList(SystemPK systemPK, PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception{
		return prmFeeEventService.selectPrmFeeEventList(prmFeeEventSearchDTO);
	}
	
	/**
	 * 수수료행사관리 리스트 엑셀 다운로드
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> getPrmFeeEventListExcel(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception{
		return prmFeeEventService.selectPrmFeeEventListExcel(prmFeeEventSearchDTO);
	}
	
	/**
	 * 수수료행사관리상세 리스트 엑셀 다운로드
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> getPrmFeeEventDetailListExcel(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception{
		return prmFeeEventService.selectPrmFeeEventDetailListExcel(prmFeeEventSearchDTO);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 카카오 알림톡  발송내역
	 * @param searchDTO
	 * @return
	 * @throws Exception
	 */
//	@Override
//	public HumusonHistListResult getKakaoAlimTalkHist(HistoryInfoFoDTO searchDTO) throws Exception{
//
//		NoticeSDO noticeSDO = new NoticeSDO();
//		noticeSDO.setSearchStartDate(searchDTO.getDateStart().replace("-","")+"000000");	// 시작일시
//		noticeSDO.setSearchEndDate(searchDTO.getDateEnd().replace("-","")+"235959");		// 종료일시
//		noticeSDO.setMallId(searchDTO.getMallId());		// 몰 ID
//		noticeSDO.setGubun(searchDTO.getGubun());
//		noticeSDO.setBeginIndex(searchDTO.getBeginIndex());
//		noticeSDO.setEndIndex(searchDTO.getEndIndex());
//
//		return settlementSelectService.selectKakaoSendHist(noticeSDO);
//	}


}
