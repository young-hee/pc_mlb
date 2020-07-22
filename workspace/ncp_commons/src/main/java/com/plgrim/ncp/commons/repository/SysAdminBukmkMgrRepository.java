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
 * @since       2015. 6. 3       
 */
package com.plgrim.ncp.commons.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.commons.result.SysAdminBukmkResult;

/**
 * 즐겨찾기 Repository
 * @author ed
 *
 */
//@Slf4j
@Repository
public class SysAdminBukmkMgrRepository extends AbstractRepository {
	
	/**
	 * 사용자별 즐겨찾기 목록.
	 *
	 * @param adminId [설명]
	 * @return List [설명]
	 * @since 2015. 5. 29
	 */
	public List<SysAdminBukmkResult> selectAdminBookmarkList(String adminId, String boSiteId) {
		
		Map<String,String> paramData = new HashMap<String,String>();
		
		paramData.put("adminId", adminId);
		paramData.put("boSiteId", boSiteId);
		
	    return getSession1().selectList("com.plgrim.ncp.commons.sysadminbukmk.selectAdminBookmarkList", paramData);
    }
}





