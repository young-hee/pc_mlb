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
package com.plgrim.ncp.commons.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminChrgJob;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthorGrp;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBoSite;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysCcJobTp;

/**
 * @author Tam
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdmResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    
    
    // SysAdminBoSiteAuthor aa;
    
    /*
     * 접근가능 BO 사이트 
     */
    List<SysBoSite> sysBoSiteList;
    
    /*
     * 담당 브랜드 목록
     */
    
    List<SysBrndExtend> brndList;
    
    /*
     * 시스템 담당업무 
     */
    List<SysAdminChrgJob>  chrgJobList;
    
    /**
     * 고객센터 업무유형
     */
    List<SysCcJobTp>  ccJobList;
    
    /*
     * 부여된 권한그룹
     */
    SysAuthorGrp sysAuthorGrp;
    
    /*
     * 시스템 관리자 정보
     */
    SysAdmin sysAdmin;
    
    String adminTpNm; //운영자 유형 코드명
    
    String adminStatNm; //운영자 상태 코드명
    
    String shopNm; //매장코드 명
    
    String comNm; //업체코드 명
    
    String boSiteIds; // Bo 사이트접근 아이디  여러개일경우 콤마(,)로 구분한다.
    
    String authorGrpNms; //권한그룹명  권한그룹이 여러개일경우 콤마(,)로 구분한다.
    
    String mobil; //휴대폰 번호 구분자 (-)
    
    String tel; //전화번호 구분자(-)
    
    String regtrNm; //등록자명
    String udterNm; //수정자명
    
    String sysBoSiteListJson; // sysBoSiteList marshalling string
    
    String chrgJobListJson; // chrgJobList marshalling string
    
    String ccJobListJson; // ccJobList marshalling string
   
    java.util.Date stplatAgrDt; //cs 개인정보취급 동의일시
    
    
    String makAdminId; //마스킹된 admin_id ( key 값)
    
    String makEmail; //마스킹된 email 
    
    String mobilNo; // 휴대폰번호
    
    String telNo; // 전화번호 
    
    java.util.Date lastLoginDt;
}

