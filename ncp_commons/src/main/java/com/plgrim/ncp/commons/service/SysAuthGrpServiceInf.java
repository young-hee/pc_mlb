/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.commons.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthor;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;
import com.plgrim.ncp.commons.data.FormSysAuthGrpDTO;
import com.plgrim.ncp.commons.data.SysAuthDTO;
import com.plgrim.ncp.commons.data.SysAuthGrpDTO;
import com.plgrim.ncp.commons.data.SysAuthMenuDTO;
import com.plgrim.ncp.commons.repository.SysAuthRepository;
import com.plgrim.ncp.commons.result.AuthGrpResult;
import com.plgrim.ncp.commons.result.AuthResult;
import com.plgrim.ncp.commons.result.MenuResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * EditorService Implementation
 * @author Tam
 *
 */
@Service
public interface SysAuthGrpServiceInf  {

	public List<AuthGrpResult> selectListAuthGrp( FormSysAuthGrpDTO paramData) throws Exception ;

}
