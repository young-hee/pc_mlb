package com.plgrim.ncp.biz.interfaces.repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtend;
import com.plgrim.ncp.biz.interfaces.data.InfOrdGodErpDstbClmSearchDTO;
import com.plgrim.ncp.biz.interfaces.data.InfOrdGodErpDstbUpdateDTO;

@Repository
public class InfOrdGodErpDstbExtendRepository  extends AbstractRepository {

	
	public void updateInfOrdGodErpDstbByClm(InfOrdGodErpDstbUpdateDTO updateInfOrdGodErpDstbDTO) {
	    // TODO Auto-generated method stub
		getSession1().insert("com.plgrim.ncp.biz.interfaces.updateInfOrdGodErpDstbByClm", updateInfOrdGodErpDstbDTO);
    }

	public List<InfOrdGodErpDstb> selectInfOrdGodErpDstbListByOrdClm(
            InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO) {
	    // TODO Auto-generated method stub
	    return getSession1().selectList("com.plgrim.ncp.biz.interfaces.selectInfOrdGodErpDstbListByOrdClm", infOrdGodErpDstbClmSearchDTO);
    }
	
	public List<InfOrdGodErpDstbExtend> selectInfOrdGodErpDstbExtendListByOrdClm(
            InfOrdGodErpDstbClmSearchDTO infOrdGodErpDstbClmSearchDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.interfaces.selectInfOrdGodErpDstbExtendListByOrdClm", infOrdGodErpDstbClmSearchDTO);
    }

	public List<InfOrdGodErpDstbExtend> selectInfOrdGodErpDstbForRefund(InfOrdGodErpDstbExtend infOrdGodErpDstb) {
	    return getSession1().selectList("com.plgrim.ncp.biz.interfaces.selectInfOrdGodErpDstbForRefund", infOrdGodErpDstb);
    }

	
	
    /*
	 * 교환상품의 주문번호에 해당하는 수량만큼 ERP 분배테이블에서 조회
	 */
	public List<InfOrdGodErpDstb> selectInfOrdGodErpDstbForClm(InfOrdGodErpDstbExtend infOrdGodErpDstbExtend) {
	    return getSession1().selectList("com.plgrim.ncp.biz.interfaces.selectInfOrdGodErpDstbForClm", infOrdGodErpDstbExtend);
    }

	/*
	 * 인터페이스 주문 상품 ERP 분배 상품순번 채번.
	 */
	public InfOrdGodErpDstb selectInfOrdGodErpDstbQtyTurn(InfOrdGodErpDstb infOrdGodErpDstb) {
		return getSession1().selectOne("com.plgrim.ncp.biz.interfaces.selectInfOrdGodErpDstbQtyTurn", infOrdGodErpDstb);
	}

	/**
	 * 인터페이스 주문 상품 ERP 분배 등록.
	 * 주문 - 교환접수에서 선택한 변경수량 만큼 등록
	 * 교환상품의 sku_no 조회 후 set
	 *
	 * @param infOrdGodErpDstbExtend the InfOrdGodErpDstbExtend
	 * @throws SQLException the SQL exception
	 */
	public void insertInfOrdGodErpDstbForClm(InfOrdGodErpDstbExtend infOrdGodErpDstbExtend) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.interfaces.insertInfOrdGodErpDstbForClm", infOrdGodErpDstbExtend);
	}


	/**
	 * 맞교환시 holdStock 후 STO번호, 일련번호 저장
	 *
	 */
	public void updateInfOrdGodErpDstbByDrctEx(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.interfaces.updateInfOrdGodErpDstbByDrctEx", infOrdGodErpDstb);
	}

	public BigDecimal selectAllDcExForOrd(String ordNo) {
		String result = getSession1().selectOne("com.plgrim.ncp.biz.interfaces.selectAllDcExForOrd", ordNo);
		BigDecimal bd = BigDecimal.ZERO; 
		if(result != null && result != ""){
			bd = new BigDecimal(result);
		}
		return bd; 
	}

	public BigDecimal selectAllDcExForClm(String clmNo) {
		String result = getSession1().selectOne("com.plgrim.ncp.biz.interfaces.selectAllDcExForClm", clmNo);
		BigDecimal bd = BigDecimal.ZERO; 
		if(result != null && result != ""){
			bd = new BigDecimal(result);
		}
		return bd; 
	}

	
	/*
	 * Erp분배 수선 확장 테이블 insert를 위해 수선 신청 시 clm_no update 건 조회
	 */
	public List<InfOrdGodErpDstb> selectInfOrdGodErpDstbForRepair(InfOrdGodErpDstb infOrdGodErpDstb) {
	    return getSession1().selectList("com.plgrim.ncp.biz.interfaces.selectInfOrdGodErpDstbForRepair", infOrdGodErpDstb);
    }
	
	
}
