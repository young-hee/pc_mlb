/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      mc009.park
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.biz.cart.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author mc009.park
 * @since 2015. 6. 19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GodReWhsgNtcnDTO extends AbstractDTO{
	
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3139041120320228907L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    private String setGodNo = "";
    
    /** The bsk god list. */
	List<BskGod> bskGodList;
    
    /** The stplat list. */
    List<SysStplat> stplatList;
    
    /** The nation no. */
    private String nationNo;
    
    /** The area no. */
    private String areaNo;
    
    /** The tlof no1. */
    private String tlofNo1;
    
    /** The tlof no2. */
    private String tlofNo2;
    
    /** The mbr no. */
    private String mbrNo;
    
    /** 등록자 Id */
    private String regtrId;
    
    /** 수정자 Id */
    private String udterId;
    
    
    /**
     * ncp 3차
     * 알림 구분 코드
     * ㅁ. 알림 구분 : NTCN_SECT
     *    >. 휴대전화 알림 : MOBIL_NTCN
     *    >. 이메일 알림 : EMAIL_NTCN
     */
	private String ntcnSectCd;

	/**
     * ncp 3차
     * 회원 이메일
     * ㅁ. ID찾기 시 중요한 인증 기준 속성으로써 관리 됨
     *    >. 휴대전화인증 : 이름,휴대번호,인증번호(휴대전화로 전송된 인증번호)입력
     *    >.이메일 인증 : 이름,이메일, 인증번호(이메일로 전송된 인증번호) 입력
     */
	private String mbrEmail;

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
