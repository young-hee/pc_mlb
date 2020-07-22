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

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 클레임 Tag 시리얼 교환 DTO
 *
 * @author shhenry.choi
 * @since 2015. 3. 18
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmErpGodSnExchgDTO extends AbstractDTO {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    static final long serialVersionUID = 4403992496214821087L;

    /**
     * 주문 번호
     OD || YYYYMMDD || 7자리 Cycle Sequence
     */
    private String ordNo;

    /**
     * 주문 상품 순번
     */
    private java.lang.Integer ordGodTurn;

    /**
     * 수량 순번
     ㅁ. 주문별 상품 및 수량에 대한 순번
     ㅁ. 주문 번호에 유일성을 가짐
     */
    private java.lang.Integer qtyTurn;

    /**
     * ERP 상품 일련번호-기존 일련번호
     */
    private String erpGodSnOld;

    /**
     * ERP 상품 일련번호-신규 일련번호
     */
    private String erpGodSnNew;
    
}
