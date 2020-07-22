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
 * @since       2015. 3. 17
 */
package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;

/**
 * 클레임 조회 DTO.
 *
 * @author shhenry.choi
 * @since 2015. 3. 18
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimSearchDTO extends AbstractDTO {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    static final long serialVersionUID = 4403992496214821087L;


	private String clmNo;
	private String ordNo;	
	private String role;	
	private String pgExcept;
	private String clmResnCd;
	private String clmResnCont;
	private List<ClmDlvOrdGodInfoDTO> dlvspClmOrdGod;	// 화면에서 받을 주문지 별 상품정보
	
	private String memoCont;

    private String claimType;
    
    
    private String rfdBnkCd;
    private String rfdAcnthldrNm;
    private String rfdBnkAcctNo;
    
    private String adminTpCd;//관리자 유형
        
    private List<OrdGodExtend> ordGodExtends;

    /**
     * ncp2 - 업체별 반품/교환 배송비 위해 추가
     * 물류배송지
     * 배송지별 상품목록을 가져오기 위함
     * 패키지, 세트 상품인 경우 아래와 같은 구조임.
     * OrdGodExtend 	<패키지/일반상품>
     * 	   OrdGodExtend <패키지구성품>
     *     OrdGodExtend <패키지구성품>
     *     ...
     */
    private List<LgsDlvspExtend> lgsDlvspList;

	/** 상품번호 멀티 */
    private String[] gridGodNos;
    /** 다중검색 상품번호 구분. */	
	private String godNosGbn;
    /** 상품번호 */
	private String godNo;
	/** 상품번호 멀티 */
    private String[] godNos;
    /** 검색기간. */
	private String term;
	/** 검색기간 시작일. */
	private String startTermDt;
	/** 검색기간 종료일. */
	private String endTermDt;
	/** 반품입고 유형. */
	private String rtrvlGodDetailCd;
	/** 반품입고반영. */
	private String rtrvlStatCd;
	/** 메모. */
	private String memo;
	/** 페이징 여부*/
	private String pagingYn;
	/** 메시지 ID */
	private String msgId;
	/** 상품 상태 */
	private String restockType;
	/** 접수 admin ID */
	private String adminId;
	/** 등록/수정자 ID */
	private String udterId;
}
