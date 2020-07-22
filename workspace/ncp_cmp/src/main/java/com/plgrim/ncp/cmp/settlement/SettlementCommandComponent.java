package com.plgrim.ncp.cmp.settlement;

import java.util.List;

import com.plgrim.ncp.biz.settlement.data.PrmFeeEventBasicFormDTO;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventExcelDTO;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventFormDTO;

public interface SettlementCommandComponent {

	/**
	 * 수수료행사 등록
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public void insertPrmFeeEvent(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception;
	
	/**
	 * 수수로행사 저장
	 * @param PrmFeeEventBasicFormDTO
	 * @throws Exception
	 */
	public void updatePrmFeeEventList(List<PrmFeeEventBasicFormDTO> updateList)throws Exception;
	
	/**
	 * 수수로행사 상세팝업 저장
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public void updatePrmFeeEvent(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception;
	
	/**
	 * 수수료행사 엑셀 등록
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public void addExcelPrmFeeEvent(PrmFeeEventExcelDTO settlementExcelDTO, String loginId) throws Exception;
	
	/**
	 * 수수료행사 (대용량)상품 엑셀 등록
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public void addExcelPrmFeeEventGoods(PrmFeeEventExcelDTO settlementExcelDTO, String loginId, Long feeEventSn) throws Exception;
	
}
