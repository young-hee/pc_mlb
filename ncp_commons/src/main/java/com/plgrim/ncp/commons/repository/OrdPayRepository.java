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
 * ------------------   --------------              ------------------
 * cannon			    20151222
 */
package com.plgrim.ncp.commons.repository;

import org.springframework.stereotype.Repository;
import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;

/**
 * The Class PayRepository.
 */
@Repository
public class OrdPayRepository extends AbstractRepository {
	
    /**
     * 주결제시 주결제수단 정보 조회
     * 
     * @param 주문번호
     * @return 주결제정보
     * @throws Exception
     */
	public Pay selectOrderMpayMnInfo(String ordNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.order.selectOrderMpayMnInfo", ordNo);
	}
	
}
