package com.plgrim.ncp.biz.order.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

@Data
@EqualsAndHashCode(callSuper = false)
public class CouponSearchDTO extends AbstractDTO {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -1017049483889031906L;

	Mbr mbr;
	
	String empYn;

	List<BskGod> bskGodList;
}
