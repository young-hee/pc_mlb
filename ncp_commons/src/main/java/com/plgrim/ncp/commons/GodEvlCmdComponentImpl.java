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
 * @since       2015. 5. 29       
 */
package com.plgrim.ncp.commons;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.commons.data.GodEvlDTO;
import com.plgrim.ncp.commons.service.GodEvlService;
import com.plgrim.ncp.framework.data.SystemPK;

//@Slf4j
@Transactional(value = "transactionManager")
@Component
public class GodEvlCmdComponentImpl extends AbstractComponent implements GodEvlCmdComponent {

	@Autowired
	GodEvlService godEvlService; // 상품평관리 Service
	
	/**
	 * 상품평관리 등록.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateGodEvl( SystemPK systemPK, GodEvlDTO paramData) throws Exception {
		godEvlService.updateGodEvl(paramData); 
	}
}
