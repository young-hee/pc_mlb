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
 * @since       2015. 6. 20
 */
package com.plgrim.ncp.biz.cart.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Instantiates a new cart dto.
 *
 * @author mc009.park
 * @since 2015. 4. 2
 */

/**
 * Instantiates a new cart dto.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CartDTO extends AbstractDTO {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -8217966708585342932L;
    String lastSalePrc;

    /**
     * The bsk.
     */
    Bsk bsk = new Bsk();

    /**
     * The god.
     */
    BskGodExtend god;

    /**
     * The cpst god list.
     */
    List<CartGodDTO> cpstGodList;

    /**
     * The cpst cnt.
     */
    private int cpstCnt;

    /* 예약 상품 여부*/
    private String resveSaleGodYn = "N";

    private String popUpType ;
    
    private String callBakcUrl ;
    
    private String changeOptionYn = "N";
    
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
    public void setLastSalePrc(String lastSalePrc){
        this.lastSalePrc = lastSalePrc;
    }
    
    private String sourceGodTurn;
    
    private String naverPayYn = "N";

}
