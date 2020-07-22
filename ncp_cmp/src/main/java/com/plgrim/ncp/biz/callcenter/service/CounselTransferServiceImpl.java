/* Copyright (c) 2013 plgrim, Inc.
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
 *                       
 */
package com.plgrim.ncp.biz.callcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.biz.callcenter.data.CounselTransferDTO;
import com.plgrim.ncp.biz.callcenter.repository.CsoCnsltTransAtchmnflEntityRepository;
import com.plgrim.ncp.biz.callcenter.repository.CsoCnsltTransEntityRepository;
import com.plgrim.ncp.biz.callcenter.repository.CsoCnsltTransOrdGodEntityRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CounselTransferServiceImpl{

	@Autowired
	private CsoCnsltTransEntityRepository csoCnsltTransEntityRepository;

	@Autowired
	private CsoCnsltTransOrdGodEntityRepository csoCnsltTransOrdGodEntityRepository;

	@Autowired
	private CsoCnsltTransAtchmnflEntityRepository csoCnsltTransAtchmnflEntityRepository;
	
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public void addCounselTransfer(CounselTransferDTO counselTransferDTO) throws Exception {

		// CSO_CNSLT_TRANS 저장
		counselTransferDTO.getCsoCnsltTrans().setCnsltSn(Long.valueOf("7"));
		counselTransferDTO.getCsoCnsltTrans().setCnsltDetailTurn(1);
		counselTransferDTO.getCsoCnsltTrans().setTransRequstTurn(1);
		counselTransferDTO.getCsoCnsltTrans().setRegtrId("MB201504060000003"); // tester3
		csoCnsltTransEntityRepository.insertCsoCnsltTrans(counselTransferDTO.getCsoCnsltTrans());


		Long cnsltSn = counselTransferDTO.getCsoCnsltTrans().getCnsltSn();
		int cnsltDetailTurn = counselTransferDTO.getCsoCnsltTrans().getCnsltDetailTurn();
		int cnsltTransRequstTurn = counselTransferDTO.getCsoCnsltTrans().getTransRequstTurn();


		// CSO_CNSLT_TRANS_ORD_GOD 저장
		counselTransferDTO.getCsoCnsltTransOrdGod().setCnsltSn(cnsltSn);
		counselTransferDTO.getCsoCnsltTransOrdGod().setCnsltDetailTurn(cnsltDetailTurn);
		counselTransferDTO.getCsoCnsltTransOrdGod().setTransRequstTurn(cnsltTransRequstTurn);
		counselTransferDTO.getCsoCnsltTransOrdGod().setTransOrdGodTurn(1);
		csoCnsltTransOrdGodEntityRepository.insertCsoCnsltTransOrdGod(counselTransferDTO.getCsoCnsltTransOrdGod());

	}


}
