/* Copyright (c) 2015 plgrim, Inc.
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
 * beyondj2ee			2015.02.09         
 */
package com.plgrim.ncp.biz.callcenter.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DetailChatInqResult extends AbstractResult {
	


	private String cnsltSn; //상담일련번호
	private String cnsltDetailTurn; //상담상세순번

	private String inqTpCd; //상담종류코드
	private String inqTpNm; //상담종류코드이름

	private String inqDetailTpCd;//상담종류 상세코드
	private String inqDetailTpNm; //상담종류 상세코드이름

	private String cnsltDt; //상담일시
	private String adminId; //상담자 아이디
	private String ansAdminNm; //상담담당자이름

	private String cnsltDetailStatCd; //처리상태
	private String cnsltDetailStatNm; //처리상태이름

	private String regtrId; //등록자id
	private String cstmrClmCd; //컴플레인여부 고객클레임 코드
	private String cnsltDetailCont; //상담내용

	private String cnsltKndNm; //상담종류 코드 이름
	private String cnsltKndCd; //상담종류 코드
	private String jobcdNm;

	private String chatInqSn; //채팅상담번호
	private String deleteYn; //삭제여부


}