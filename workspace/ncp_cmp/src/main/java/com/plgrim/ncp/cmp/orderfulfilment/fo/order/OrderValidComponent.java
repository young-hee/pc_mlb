package com.plgrim.ncp.cmp.orderfulfilment.fo.order;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.result.CartResult;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.framework.data.SystemPK;

@Component
public interface OrderValidComponent {

	/**
	 * 주문 사전 검증
	 * @param orderDTO
	 * @param cartResultList
	 * @param locale
	 * @throws Exception
	 */
	void checkOrderValid(OrderDTO orderDTO, CartSearchDTO cartSearchDTO, List<CartResult> cartResultList, SystemPK systemPK, Locale locale) throws Exception;

}
