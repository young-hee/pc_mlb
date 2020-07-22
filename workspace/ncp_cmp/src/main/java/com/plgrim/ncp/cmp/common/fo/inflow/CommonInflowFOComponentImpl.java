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
package com.plgrim.ncp.cmp.common.fo.inflow;

import com.plgrim.ncp.cmp.common.fo.CommonInflowFOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.commons.service.SysInflowService;

// @Slf4j
@Component
public class CommonInflowFOComponentImpl extends AbstractComponent implements CommonInflowFOComponent {

	@Autowired
	SysInflowService sysInflowService;

	@Override
    public SysInflow selectSysInflow(SysInflow sysInflow) throws Exception {
	    return sysInflowService.selectSysInflow(sysInflow);
    }
	
}
