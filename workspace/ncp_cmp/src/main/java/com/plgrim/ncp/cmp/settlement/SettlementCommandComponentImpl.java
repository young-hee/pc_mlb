package com.plgrim.ncp.cmp.settlement;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.biz.settlement.data.PrmFeeEventBasicFormDTO;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventExcelDTO;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventFormDTO;
import com.plgrim.ncp.biz.settlement.service.PrmFeeEventService;

@Transactional(value = "transactionManager")
@Component
public class SettlementCommandComponentImpl implements
        SettlementCommandComponent {

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
	 * 수수료행사 등록
	 */
	@Override
	public void insertPrmFeeEvent(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception{
		prmFeeEventService.insertPrmFeeEvent(prmFeeEventFormDTO);
	}
	
	/**
	 * 수수료행사 저장
	 * @param PrmFeeEventBasicFormDTO
	 * @throws Exception
	 */
	public void updatePrmFeeEventList(List<PrmFeeEventBasicFormDTO> updateList)throws Exception{
		Iterator<PrmFeeEventBasicFormDTO> iterator = updateList.iterator();
		while(iterator.hasNext()) {
			//수수료 행사관리  사용여부 수정
			prmFeeEventService.updatePrmFeeEventList(iterator.next());
		}
	}
	
	/**
	 * 수수료행사 상세팝업 저장
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	@Override
	public void updatePrmFeeEvent(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception{
		prmFeeEventService.updatePrmFeeEvent(prmFeeEventFormDTO);
	}
	
	/**
	 * 수수료행사 엑셀 등록
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	@Override
	public void addExcelPrmFeeEvent(PrmFeeEventExcelDTO settlementExcelDTO, String loginId) throws Exception {
		prmFeeEventService.insertExcelPrmFeeEvent(settlementExcelDTO, loginId);
	}
	
	/**
	 * 수수료행사 (대용량)상품 엑셀 등록
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	@Override
	public void addExcelPrmFeeEventGoods(PrmFeeEventExcelDTO settlementExcelDTO, String loginId, Long feeEventSn) throws Exception {
		prmFeeEventService.insertExcelPrmFeeEventGoods(settlementExcelDTO, loginId, feeEventSn);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
