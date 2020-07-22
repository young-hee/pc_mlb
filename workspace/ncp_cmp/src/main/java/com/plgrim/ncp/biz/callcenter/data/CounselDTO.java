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

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnslt;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetail;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailHist;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailJobTp;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTrans;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransHist;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequst;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoPromscl;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CounselDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	CsoCnslt csoCnslt;
	
	CsoCnsltDetail csoCnsltDetail;
	
	CsoPromscl csoPromscl;
	
	CsoCnsltTrans csoCnsltTrans;

	CsoCnsltTransOrdGod csoCnsltTransOrdGod;

	CsoCnsltTransHist csoCnsltTransHist;

	CsoJobRequst csoJobRequst;

	CsoJobRequstOrdGod csoJobRequstOrdGod;

	CsoCnsltDetailJobTp csoCnsltDetailJobTp;

	CsoCnsltDetailOrdGod csoCnsltDetailOrdGod;

	CsoCnsltDetailHist csoCnsltDetailHist;
	
	CsoCnsltOrdGod csoCnsltOrdGod;

	String phoneNumber;

	String csoPromsclTel;

	String csoPromsclDate;

	String[] cnsltJobTpCdArr;
	
	String selectChrgJobTpCd;
	
	String selectTransRequstTpCd;
	
	String selectTransRecrId;


	String mbrNo;

	String transSaveYn;

	String promiseCallSaveYn;

	String cnsltSn;

	String ordGodTurns;

	private List<CounselGridDTO> listCsoCnslt;

	String mallId;

	String langCd;

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
