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
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.biz.member.data;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.result.PurpleCoinMemberBoResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * 회원 ResultDTO
 *
 * @author sy59.gim
 * @since 2015. 6. 19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberResultDTO extends AbstractDTO {

    /** UID. */
    private static final long serialVersionUID = -1801323128981888120L;
    
    /* === List START =====================================================  */
    /** 목록. */
    private List<MemberBoResult> lists;

    private List<MemberFoResult> foLists;
    /** 목록 갯수. */
    private long listCount;
    /* === List END   =====================================================  */
    
    /* === 회원 상세 START ================================================  */
    /** 조회결과. */
    private MemberBoResult mbrDetail;

    /** 해외회원 이메일 인증 정보. */
    private MbrCrtfc mbrCrtfcEmail;

    /** 통합회원 인증 정보. */
    private MbrCrtfc mbrCrtfcUnion;

    /** 임직원 인증 정보. */
    private MbrCrtfc mbrCrtfcEmp;

    /** 회원 P포인트 Entity */
    private MbrWebpntHistExtend mbrWebpntHist;

    /** 본인인증 휴대폰 전화번호. */
    private String crtfcMobilNo;
    /* === 회원 상세 END   ================================================  */

    /* === 적립금 START ===================================================  */
    /** 총액. */
    private long totalAmoust;

    /** 멥버십 포인트 (잔액). */
    private long reserveAmount;

    /** 가용 포인트. */
    private long usableAmount;

    /** 소멸예정 포인트. */
    private long expiringAmount;

    /** 멤버십 포인트 목록. */
    private List<Map<String, String>> reserveList;

    /**MyPage 멤버십 포인트 목록    . */
    private List<Map<String, String>> membershipPointList;

    /** 퍼플 코인 total 금액 */
    private MbrWebpntHistExtend mbrWebpntAmt;

    /* === 적립금 ENT   ===================================================  */

    /** P포인트 회원 목록. */
    private List<PurpleCoinMemberBoResult> purpleCoinMbrlists;
}
