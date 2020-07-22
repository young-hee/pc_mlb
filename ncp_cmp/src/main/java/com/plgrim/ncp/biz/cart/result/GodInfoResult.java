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
package com.plgrim.ncp.biz.cart.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
@EqualsAndHashCode(callSuper=false)
public class GodInfoResult extends AbstractResult{

	/**
	 * 
	 */
    private static final long serialVersionUID = 85161334626315038L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    /** The god. */
	BskGodExtend god;
    
    /** The cpst god list. */
    List<BskGodExtend> cpstGodList;
    
    /** The stplat list. */
    List<SysStplat> stplatList;
    
    /** The mobil nation no. */
    private String mobilNationNo;
    
    /** The mobil area no. */
    private String mobilAreaNo;
    
    /** The mobil tlof no. */
    private String mobilTlofNo;
    
    /** The mobiltlof wthn no. */
    private String mobiltlofWthnNo;
    
    /** The mobil no. */
    private String mobilNo;
    
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
