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


/**
 * Instantiates a new faq vo.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReservationSearchDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = 3399637603566616951L;
    
    ReservationDTO reservationDTO;

    // 기간 검색 Type
    private String searchDateType;

    // 검색 시작 일자
    private String startDt;

    // 검색 종료 일자
    private String endDt;

    // 문의 유형 코드
    private String inqTpCd;

    // 문의 유형 상세 코드
    private String inqDetailTpCd;

    // 약속 상태 코드
    private String selectPromsclStatCd;

    //상담사 ID
    private String inputRegId;

    // 상담번호
    private String cnsltSn;

    // 선물포장
    private String svcAplTpCd;

}
