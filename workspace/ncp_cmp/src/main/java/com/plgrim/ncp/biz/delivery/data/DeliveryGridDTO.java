package com.plgrim.ncp.biz.delivery.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryClaimGoodResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryGridDTO extends AbstractDTO {
	/**
	 * 
	 */
    private static final long serialVersionUID = -3066928731694807268L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    private List<DeliveryOrderGoodResult> gridParams;
    
    private List<DeliveryClaimGoodResult> gridClaimParams;
    
    private DeliverySearchDTO deliverySearchDTO;
    
    private DeliveryInvoiceDTO deliveryInvoiceDTO;
    
    private DeliveryOrderGoodDTO formParams;
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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
