package com.plgrim.ncp.biz.interfaces.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbRepair;
import com.plgrim.ncp.base.repository.inf.InfOrdGodErpDstbRepairRepository;
import com.plgrim.ncp.base.repository.inf.InfOrdGodErpDstbRepository;
import com.plgrim.ncp.biz.interfaces.data.InfOrdGodErpDstbBoDTO;
import com.plgrim.ncp.biz.interfaces.data.InfOrdGodErpDstbClmSearchDTO;
import com.plgrim.ncp.biz.interfaces.data.InfOrdGodErpDstbUpdateDTO;
import com.plgrim.ncp.biz.interfaces.data.OrdGodErpDTO;
import com.plgrim.ncp.biz.interfaces.repository.InfOrdGodErpDstbExtendRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfOrdGodErpDstbService extends AbstractService {

	@Autowired
	InfOrdGodErpDstbRepository infOrdGodErpDstbRepository;// 주문 상품 ERP
	// 분배
	@Autowired
	InfOrdGodErpDstbExtendRepository infOrdGodErpDstbExtendRepository;

    @Autowired
	InfOrdGodErpDstbRepairRepository infOrdGodErpDstbRepairRepository;// REPAIR


	public void infOrdGodErpDstbProcessor(InfOrdGodErpDstbBoDTO infOrdGodErpDstbBoDTO, String ordNo) throws Exception {

		log.info("infOrdGodErpDstbProcessor");

		int count = 1;
		Map<Integer, Integer> map = Maps.newHashMap();
		for (InfOrdGodErpDstb infOrdGodErpDstb : infOrdGodErpDstbBoDTO.getInfOrdGodErpDstbList()) {

			infOrdGodErpDstb.setOrdNo(ordNo);
			infOrdGodErpDstb.setQtyTurn(count);

			if (map.containsKey(infOrdGodErpDstb.getOrdGodTurn())) {

				int ordGodQtyTurn = map.get(infOrdGodErpDstb.getOrdGodTurn()) + 1;
				infOrdGodErpDstb.setOrdGodQtyTurn(ordGodQtyTurn);
				map.put(infOrdGodErpDstb.getOrdGodTurn(), ordGodQtyTurn);
			}
			else {
				map.put(infOrdGodErpDstb.getOrdGodTurn(), 1);
				infOrdGodErpDstb.setOrdGodQtyTurn(1);
			}

			infOrdGodErpDstbRepository.insertInfOrdGodErpDstb(infOrdGodErpDstb);

			count++;
		}

	}

	/**
	 * InfOrdGodErpDstb 테이블을 update 한다.
	 * 전체취소일 경우 ordNo 만 필요
	 * @param infOrdGodErpDstb
	 * @throws Exception
	 */
	public void updateInfOrdGodErpDstbByClm(InfOrdGodErpDstbUpdateDTO updateInfOrdGodErpDstbDTO)  throws Exception {
		log.info("updateInfOrdGodErpDstbByClm");
		infOrdGodErpDstbExtendRepository.updateInfOrdGodErpDstbByClm(updateInfOrdGodErpDstbDTO);
	}

	/**
	 * InfOrdGodErpDstb 테이블을 update 한다.
	 * 부분취소일 경우 ordNo, ordGodTurn, qty 필요
	 * @param infOrdGodErpDstb
	 * @throws Exception
	 */
	public void updateInfOrdGodErpDstbByClmUnit(InfOrdGodErpDstbUpdateDTO updateInfOrdGodErpDstbDTO)  throws Exception {
		log.info("updateInfOrdGodErpDstbByClmUnit");
		
		List<OrdGodErpDTO> sortedList = updateInfOrdGodErpDstbDTO.getOrdGodTurnList();
		
		Collections.sort(sortedList, new OrderGodTurnAscCompare());
		
		for(OrdGodErpDTO dto : sortedList){
			updateInfOrdGodErpDstbDTO.setClmNo(dto.getClmNo());
			updateInfOrdGodErpDstbDTO.setOrdGodTurn(dto.getOrdGodTurn());
			updateInfOrdGodErpDstbDTO.setQty(dto.getQty());
			updateInfOrdGodErpDstbDTO.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
			updateInfOrdGodErpDstbDTO.setErpGodSnArry(dto.getErpGodSnArry());
			updateInfOrdGodErpDstbDTO.setClmResnCont(dto.getClmResnCont());

			infOrdGodErpDstbExtendRepository.updateInfOrdGodErpDstbByClm(updateInfOrdGodErpDstbDTO);
		}
	}
	
	public void updateInfOrdGodErpDstbRepairByRcpt(InfOrdGodErpDstb dto)  throws Exception {
		log.info("updateInfOrdGodErpDstbRepairByRcpt");

		//수선 clm_no update 한 건 조회
		List<InfOrdGodErpDstb> list = infOrdGodErpDstbExtendRepository.selectInfOrdGodErpDstbForRepair(dto);
		String regtrId = dto.getRegtrId();
		String udterId = dto.getUdterId();

		//수선 erp분배테이블 조회하여 insert or update
		for (InfOrdGodErpDstb infOrdGodErpDstb : list) {
			InfOrdGodErpDstbRepair param = new InfOrdGodErpDstbRepair();
			param.setOrdNo(infOrdGodErpDstb.getOrdNo());
			param.setOrdGodTurn(infOrdGodErpDstb.getOrdGodTurn());
			param.setQtyTurn(infOrdGodErpDstb.getQtyTurn());
			InfOrdGodErpDstbRepair res = infOrdGodErpDstbRepairRepository.selectInfOrdGodErpDstbRepair(param);

			InfOrdGodErpDstbRepair newParam = new InfOrdGodErpDstbRepair();
			newParam.setOrdNo(infOrdGodErpDstb.getOrdNo());
			newParam.setOrdGodTurn(infOrdGodErpDstb.getOrdGodTurn());
			newParam.setQtyTurn(infOrdGodErpDstb.getQtyTurn());
			newParam.setRepairComptYn("N");
			newParam.setClmNo(infOrdGodErpDstb.getClmNo());
			newParam.setClmWrhsGodTurn(infOrdGodErpDstb.getClmWrhsGodTurn());
			if(res != null) {
				newParam.setUdterId(udterId);
				infOrdGodErpDstbRepairRepository.updateInfOrdGodErpDstbRepair(newParam);
			}
			else {
				/*
				if("Y".equals(dto.getRepairRtgodYn())){ //불량수선접수이면
					newParam.setRepairRecrNm(dto.getRepairRecrNm());
					newParam.setRepairRecrPhon(dto.getRepairRecrPhon());
					newParam.setRepairNo(dto.getRepairNo());
					newParam.setRepairTpCd("BADN_REPAIR");
				}else{
					newParam.setRepairTpCd("GNRL_REPAIR");
				}
				*/
				newParam.setRepairTpCd("GNRL_REPAIR");
				newParam.setRegtrId(regtrId);
				newParam.setUdterId(udterId);
				infOrdGodErpDstbRepairRepository.insertInfOrdGodErpDstbRepair(newParam);
			}
		}
	}

	/**
	 * OrdGodErpDTO 형태의 list 를 OrdGodTurn 으로 asc 로 정렬
	 * @author user
	 *
	 */
	public class OrderGodTurnAscCompare implements Comparator<OrdGodErpDTO> {

		@Override
		public int compare(OrdGodErpDTO arg0, OrdGodErpDTO arg1) {
			return arg0.getOrdGodTurn().compareTo(arg1.getOrdGodTurn());
		}
	}
	
	
	

	public List<InfOrdGodErpDstb> selectInfOrdGodErpDstbListByOrdClm(
			InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO) {
		return infOrdGodErpDstbExtendRepository.selectInfOrdGodErpDstbListByOrdClm(infOrdGodErpDstbClmSearchDTO);
	}

	public List<InfOrdGodErpDstbExtend> selectInfOrdGodErpDstbExtendListByOrdClm(
			InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO) {
		return infOrdGodErpDstbExtendRepository.selectInfOrdGodErpDstbExtendListByOrdClm(infOrdGodErpDstbClmSearchDTO);
	}

	public List<InfOrdGodErpDstbExtend> selectInfOrdGodErpDstbForRefund(
			InfOrdGodErpDstbExtend infOrdGodErpDstb) {
		return infOrdGodErpDstbExtendRepository.selectInfOrdGodErpDstbForRefund(infOrdGodErpDstb);
	}

	/*
	 * 교환상품의 주문번호에 해당하는 수량만큼 ERP 분배테이블에서 조회
	 */
	public List<InfOrdGodErpDstb> selectInfOrdGodErpDstbForClm(
			InfOrdGodErpDstbExtend infOrdGodErpDstbExtend) {
		return infOrdGodErpDstbExtendRepository.selectInfOrdGodErpDstbForClm(infOrdGodErpDstbExtend);
	}

	/*
	 * 인터페이스 주문 상품 ERP 분배 상품순번 채번.
	 */
    public InfOrdGodErpDstb selectInfOrdGodErpDstbQtyTurn(InfOrdGodErpDstb infOrdGodErpDstb) {
        return infOrdGodErpDstbExtendRepository.selectInfOrdGodErpDstbQtyTurn(infOrdGodErpDstb);
    }
    	
	/**
	 * 인터페이스 주문 상품 ERP 분배 등록.
	 * 주문 - 교환접수에서 선택한 변경수량 만큼 등록
	 */
	public void insertInfOrdGodErpDstbForClm(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		infOrdGodErpDstbRepository.insertInfOrdGodErpDstb(infOrdGodErpDstb);
	}

	/**
	 * 맞교환시 holdStock 후 STO번호, 일련번호 저장
	 */
	public void updateInfOrdGodErpDstbByDrctEx(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		infOrdGodErpDstbExtendRepository.updateInfOrdGodErpDstbByDrctEx(infOrdGodErpDstb);
	}

	public BigDecimal selectAllDcExForOrd(String ordNo) {
		return infOrdGodErpDstbExtendRepository.selectAllDcExForOrd(ordNo);
	}

	public BigDecimal selectAllDcExForClm(String clmNo) {
		return infOrdGodErpDstbExtendRepository.selectAllDcExForClm(clmNo);
	}


}
