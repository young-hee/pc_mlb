/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 22       
 */
package com.plgrim.ncp.biz.delivery.repository;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInvExtend;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;

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
 * @author jwcale.kim
 * @since 2015. 4. 22
 */
@Slf4j
@Repository
public class DeliveryGlobalAffExternalRepository extends AbstractRepository {


	public List<DlvOrdGodInfoDTO> selectLgsDlivyDrctGodInfoList(DlvOrdGodInfoDTO dlvOrdGodInfoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.globalAff.selectLgsDlivyDrctGodInfoList", dlvOrdGodInfoDTO);
    }

	public void updateRtrvlDrctGftForTmall(Clm clm) {
		getSession1().update("com.plgrim.ncp.biz.delivery.external.globalAff.updateRtrvlDrctGftForTmall", clm);
    }


}
