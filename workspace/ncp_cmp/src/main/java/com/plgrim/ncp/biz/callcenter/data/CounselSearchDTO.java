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
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode(callSuper = false)
public class CounselSearchDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    private static final long serialVersionUID = 7062215063112244201L;

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

	private String searchDateType;

	private String startDt;

	private String endDt;

	private String inqTpCd;

	private String inqDetailTpCd;

	private String cstmrClmCd;

	private String cnsltKndCd;

	private String cnsltJobTpCd;

	private String cnsltTgtCd;

	private String promsclStatCd;

	private String cnsltStatCd;

	private String elapseDay;

	private String transStatCd;

	private String transTgtCd;
	
	private String chrgJobTpCd;

	private String transRequstTpCd;

	private String orderNo;
    
    private String godNo;
    
    private String cnsltSn;

	private String cnsltDetailTurn;
    
    private String mtmInqSn;

	private String regtrId;

	private String transComId;

	private String transRecrId;

	private String mbrNo;

	private String mobilNo;

	private String mbrNm;

	private String ordNo;

	private String ordGodTurn;

	private String cstmrPhone;
	
	private String langCd;

	private String mallId;
	
	//20160503_주동민_sr#18801 [CSO내 제휴사 고객정보 마스킹 처리요청]
	private String orgTelNo;
	private String orgMobilNo;
	private String orgMbrNm;
	private String orgMbrEmail;
	private String mbrTpCd;

	private String senarioChatYn;       // 시나리오 채팅여부

	/**  회원 등급. */
	private String onlneGrdCd;
}
