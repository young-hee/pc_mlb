/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 17       
 */
package com.plgrim.ncp.base.enums;

/**
 * [회원 Enum].
 *
 * @author sy59.gim
 * @since 2015. 6. 17
 */
public enum SessionEnum {
	
	//Attribute(SessionEnum.SESSION_ID.toString())
	// CONSTANTS
	YES("Y"), NO("N"),
	SINGLE_DTO("SINGLE_DTO")     		//싱글 정보
	,SRVNO("SRV_NO")					//본인인증 서비스 번호
	,SINGLE_MSG("SINGLE_MESSAGE")		//싱글 메시지 ex)마이페이지에서 싱글인증을 받으세요.
	,FAIL_MSG("failMsg")			//로그인 실패 메시지
	,MEMBER_FO_DTO("memberFoDTO")		//가입 할때 사용
	,SESSION_ID("SESSION_ID")			//로그인전 세션아이디
	,IPIN("IPIN")						//아이핀
	,PCC("PCC")							//휴대전화 인증
	,IS_CHECK_PWD("isCheckPwd") //isCheckPwd
	,INFLOW_SN("INFLOW_SN") 		//유입경로
	,ADVT_AFF_COM_ID("ADVT_AFF_COM_ID") // 광과제휴업체ID
	,ID_CNTC_TP_CD("ID_CNTC_TP_CD") 		//ID 연계 유형 코드(sns 네이버)
	,FIRST_APP_LOGIN("firstApplogin")   // 앱첫로그인
	,MEMBER_INFORMATION_SDO("memberInformationSDO")	// ERP에서 조회한 통합회원정보
	,ONLINE_INSERT_FLAG("onlineInsertFlag")	// ERP에는 회원정보가 있는데 온라인에는 없는 경우 온라인에 회원정보를 등록하기 위한 flag
	,CHECK_PASSWORD_FLAG("checkPasswordFlag")	// 회원정보 수정 전 패스워드 검증 인증 랜덤값
	,FIND_ID_PW_TYPE("findIdPwType")	// 아이디/비밀번호 찾기에서 현재 화면이 아이디찾기인지 비밀번호찾기인지 구분하기 위해 모바일에서 사용.
	;
	
	
	
	/**
	 * 값
	 */
	private final String value;

	/**
	 * Instantiates a new member enum.
	 *
	 * @param value [설명]
	 */
	private SessionEnum(final String value) {
		this.value = value;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return value;
	}
}

