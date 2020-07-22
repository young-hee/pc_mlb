/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shhenry.choi
 * @since       2015. 3. 18       
 */
package com.plgrim.ncp.biz.callcenter.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetail;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailJobTp;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTrans;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransHist;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequst;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstAns;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstAnsAtchmnfl;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstAtchmnfl;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstOrdGod;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class CounselTransferBoardDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	
	CsoCnsltDetail csoCnsltDetail;
	
	CsoCnsltDetailOrdGod csoCnsltDetailOrdGod;
	
	CsoCnsltDetailJobTp csoCnsltDetailJobTp;

	
	CsoCnsltTrans csoCnsltTrans;

	CsoCnsltTransHist csoCnsltTransHist;
	
	CsoCnsltTransOrdGod csoCnsltTransOrdGod;

	CsoJobRequst csoJobRequst;
	
	CsoJobRequstAns csoJobRequstAns;
	
	CsoJobRequstAtchmnfl csoJobRequstAtchmnfl;
	
	CsoJobRequstOrdGod csoJobRequstOrdGod;
	
	CsoJobRequstAnsAtchmnfl csoJobRequstAnsAtchmnfl;
	
	
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
