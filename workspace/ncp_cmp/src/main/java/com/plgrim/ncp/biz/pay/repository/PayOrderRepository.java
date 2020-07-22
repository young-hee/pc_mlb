/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * ()			20150414                      
 */
package com.plgrim.ncp.biz.pay.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.PayRfd;
import com.plgrim.ncp.biz.pay.data.ClmRefundResultDTO;
import com.plgrim.ncp.biz.pay.data.ClmRefundSearchDTO;

/**
 * The Class PayRepository.
 */
@Repository
public class PayOrderRepository extends AbstractRepository {
	

	/**
	 * 주문번호로 결제정보 가져온다.
	 * @param ordNo
	 * @return
	 */
	public Pay getOrdPayInfo(String ordNo) {	    
	    return getSession1().selectOne("com.plgrim.ncp.pay.getOrdPayInfo", ordNo);
    }

	/** 입금확정완료 */
	public int confirmDepositPay(Pay pay) throws Exception {
		return getSession1().update("com.plgrim.ncp.pay.confirmDepositPay", pay);
	}

	public List<ClmRefundResultDTO> selectInfOrdGodErpDstbForClmRefund(ClmRefundSearchDTO clmRefundSearchDTO) {
	    // TODO Auto-generated method stub
		return getSession1().selectList("com.plgrim.ncp.pay.selectInfOrdGodErpDstbForClmRefund", clmRefundSearchDTO);
    }
	
	public List<Pay> selectOrdClmPay(Pay pay) {
	    // TODO Auto-generated method stub
		return getSession1().selectList("com.plgrim.ncp.pay.selectOrdClmPay", pay);
    }
	
	public List<PayExtend> selectOrdClmPayForErp(Pay pay) {
	    // TODO Auto-generated method stub
		return getSession1().selectList("com.plgrim.ncp.pay.selectOrdClmPayForClm", pay);
    }
	
	public List<PayExtend> selectOrdClmPayExtend(PayExtend payExtend) {
	    // TODO Auto-generated method stub
		return getSession1().selectList("com.plgrim.ncp.pay.selectOrdClmPayExtend", payExtend);
    }
	
	public List<PayExtend> selectOrdClmRefundPayExtend(Pay pay) {
	    // TODO Auto-generated method stub
		return getSession1().selectList("com.plgrim.ncp.pay.selectOrdClmRefundPayExtend", pay);
    }
	
	/**
	 * 주문번호로 결제번호를 가져온다.
	 * @param ordNo
	 * @return
	 */
	public String selectUpperPayNo(Pay pay) {	    
	    return getSession1().selectOne("com.plgrim.ncp.pay.selectUpperPayNo", pay);
    }

	/**
	 * 클레임 번호로 환불정보를 조회한다.
	 * @param clmNo
	 * @return
	 */
	public List<ClmRefundResultDTO> getClmRefundInfoList(String clmNo) {
		return getSession1().selectList("com.plgrim.ncp.pay.getClmRefundInfoList", clmNo);
    }

	public void updatePayForCancel(Pay updateCnclPay) {
		getSession1().update("com.plgrim.ncp.pay.updatePayForCancel", updateCnclPay);
    }

	public void updatePayForClmWthdrawDlvFee(Pay searchPay) {
		getSession1().update("com.plgrim.ncp.pay.updatePayForClmWthdrawDlvFee", searchPay);
    }

	public void insertPayForClmWthdrawDlvFee(Pay searchPay) {
		getSession1().insert("com.plgrim.ncp.pay.insertPayForClmWthdrawDlvFee", searchPay);
    }

	public int selectbeforePartCancelCnt(Pay beforeSearchPay) {
		return getSession1().selectOne("com.plgrim.ncp.pay.selectbeforePartCancelCnt", beforeSearchPay);
    }
	
	public int selectbeforePartCancelCnt2(Pay beforeSearchPay) {
		return getSession1().selectOne("com.plgrim.ncp.pay.selectbeforePartCancelCnt2", beforeSearchPay);
    }
	
	
}
