package com.plgrim.ncp.biz.order.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;

@Data
@EqualsAndHashCode(callSuper = false)
public class InfErpDTO extends AbstractDTO {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -2993189182129171595L;

	private InfOrdGodErpDstb infErp = null;

	private String godNo = null;
}
