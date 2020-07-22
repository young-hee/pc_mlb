package com.plgrim.ncp.biz.order.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrdGodDTO extends AbstractDTO {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -1445664540343887682L;

	private String bskNo = null;

	private Integer godTurn;

	private OrdGod ordGod = null;
	
	private String recomendComCd = null;
	
	private BigDecimal price; //(실소가,임직원)

}
