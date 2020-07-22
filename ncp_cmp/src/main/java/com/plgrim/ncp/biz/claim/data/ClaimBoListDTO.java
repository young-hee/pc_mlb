/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author jhkhan.cha
 * @since  2015. 5. 20
 */
package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmExtend;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.biz.order.data.LgsDlvspDTO;

/**
 * 클레임 LIST DTO.
 *
 * @author jewellig.lee
 * @since 2017. 5.2
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimBoListDTO extends AbstractDTO {

	/**
	 *
	 */
	private static final long serialVersionUID = 1813292414186873278L;

	private List<ClaimBoDTO> claimBoDTOList;
}