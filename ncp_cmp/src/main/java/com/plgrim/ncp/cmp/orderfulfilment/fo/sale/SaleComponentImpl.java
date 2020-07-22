/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      mc009.park
 * @since       2015. 6. 19
 */
package com.plgrim.ncp.cmp.orderfulfilment.fo.sale;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.commons.data.order.KcpCommonReceiveDTO;
import com.plgrim.ncp.framework.commons.StringService;

import lombok.extern.slf4j.Slf4j;

/**
 * [클래스 설명]
 *
 * <p>
 *
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 */
@Slf4j
@Transactional(value = "transactionManager")
@Component
public class SaleComponentImpl extends AbstractComponent implements SaleComponent {

	@Autowired
	private DeliveryCommandRepository deliveryCommandRepository;

	@Override
    public String saleKcpConfirmReceive(KcpCommonReceiveDTO kcpCommonReceiveDTO) throws Exception {
		String resultCode = "0000";
		log.info("++++++++++++++++++ saleKcpConfirmReceive in {} ",kcpCommonReceiveDTO.toString());
		// tno 결제번호
		// order_no 주문번호
		// st_cd 구매 확인 코드 구매확인 : ‘Y’ 구매취소 : ‘N’ 시스템 구매확인 : ‘S’
		// tx_tm 통보된 업무에 대한 업무처리 완료 시간
		if(StringService.isEmpty(kcpCommonReceiveDTO.getOrder_no())
				|| StringService.isEmpty(kcpCommonReceiveDTO.getSt_cd())
				)
		{
			log.info("++++++++++++++++++ saleKcpConfirmReceive fail {} ",kcpCommonReceiveDTO.toString());
			return resultCode = "999";
		}
		deliveryCommandRepository.modifyEscrPchDcsn(kcpCommonReceiveDTO);
		return resultCode;
    }
}
