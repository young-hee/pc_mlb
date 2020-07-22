package com.plgrim.ncp.cmp.settlement;

import com.plgrim.ncp.biz.helpdesk.data.HistoryInfoFoDTO;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventSearchDTO;
import com.plgrim.ncp.biz.settlement.result.PrmFeeEventListResult;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;



public interface SettlementSelectComponent {

	/**
	 * 수수료행사관리 리스트
	 * @param systemPK
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public Page<PrmFeeEventListResult> getPrmFeeEventPageList(SystemPK systemPK, PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception ;
	
	/**
	 * 수수료행사관리 리스트 엑셀
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPrmFeeEventListExcel(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception;
	
	/**
	 * 수수료행사관리상세 리스트 엑셀 다운로드
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPrmFeeEventDetailListExcel(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception;



	/**
	 * 카카오 알림톡 발송내역 조회
	 * @param searchDTO
	 * @return
	 * @throws Exception
	 */
//	public HumusonHistListResult getKakaoAlimTalkHist(HistoryInfoFoDTO searchDTO) throws Exception;





}
