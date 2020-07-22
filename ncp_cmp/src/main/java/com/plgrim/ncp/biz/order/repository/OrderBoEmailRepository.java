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
package com.plgrim.ncp.biz.order.repository;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.result.OrderBoResult;

/**
 * @author Park
 *
 */
@Repository
public class OrderBoEmailRepository extends AbstractRepository {
	
	
	public OrderBoResult selectEmailOrderCompt(OrderBoDTO orderDTO) throws Exception {


		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectEmailOrderCompt", orderDTO);

	}
	
	public OrderBoResult selectEmailOrderComptGlobal(OrderBoDTO orderDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectEmailOrderComptGlobal", orderDTO);
	}
	
    public void lgsDlvMailSnd(LgsDlv lgsDlv) throws Exception {

        getSession1().update("com.plgrim.ncp.biz.order.lgsDlvMailSnd", lgsDlv);
        
    }
    
    public void lgsDlvMailSndHist(LgsDlv lgsDlv) throws Exception {

        getSession1().insert("com.plgrim.ncp.biz.order.lgsDlvMailSndHist", lgsDlv);
        
    }
	
}