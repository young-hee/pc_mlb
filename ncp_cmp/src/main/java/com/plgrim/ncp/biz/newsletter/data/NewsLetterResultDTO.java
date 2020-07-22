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
package com.plgrim.ncp.biz.newsletter.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.biz.newsletter.result.NewsLetterResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * newsletter ResultDTO
 *
 * @author
 * @since 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NewsLetterResultDTO extends AbstractDTO {

    /** UID. */
    private static final long serialVersionUID = -1L;
    
    /* === List START =====================================================  */
    /** 목록. */
    private List<NewsLetterResult> lists;
   
    /** 목록 갯수. */
    private long listCount;
    /* === List END   =====================================================  */
    
    
}
