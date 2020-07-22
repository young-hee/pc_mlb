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
package com.plgrim.ncp.base.abstracts;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 최상위 추상 SDO 클래스.
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractSDO implements Serializable {
    // ~ Instance fields. ~~~~~~~~~~~~~~
    /**
     * Caller Id
     */
    private String callerId;

    // ~ Constructors. ~~~~~~~~~~~~~~~~~
    // ~ Implementation Method. ~~~~~~~~
    // ~ Public Methods. ~~~~~~~~~~~~~~~
    // ~ Private Methods. ~~~~~~~~~~~~~~
    // ~ Getter and Setter. ~~~~~~~~~~~~
}
