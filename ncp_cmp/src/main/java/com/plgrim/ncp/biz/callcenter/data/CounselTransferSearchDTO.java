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
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnslt;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetail;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTrans;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoPromscl;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CounselTransferSearchDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 1921816460814783456L;
	
	private String cnsltSn;
	private String cnsltDetailTurn;
	private String transRequstDt;
	private String transStatNm;
	private String transTgtNm;
	private String transRequstTpNm;
	private String doiNm;
	private String transDelay;
	private String transComptDt;
	private String inqTpNm;
	private String cnsltReqstMbrNo;
	private String ordNo;
	private String godNo;
	private String partmalSectCd;
	private String cnsltStatNm;
	private String promsclDt;
	private String transRqesterNm;
	private String cnsltDt;
	private String cstmrClmNm;
	private String cnsltKndNm;
	private String chrgJobTpNm;
	private String cnsltTgtNm;
	private String dvcNm;
	private String langNm;
	private String mallNm;
	private String elapseDay;
	private String searchDateType;
	private String transStartDt;
	private String transEndDt;
	private String transTgtCd;
	private String regtrId;
	private String transComId;
	private String transRecrId;
	private String transRequstTpCd;
	private String chrgJobTpCd;
	private String mallId;
	private String langCd;

	CsoCnslt csoCnslt;

	CsoCnsltDetail csoCnsltDetail;

	CsoPromscl csoPromscl;

	CsoCnsltTrans csoCnsltTrans;

	CsoCnsltTransOrdGod csoCnsltTransOrdGod;
	
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
