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

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetail;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailJobTp;


/**
 * 1:1 문의 게시판 리스트 조회 dto
 *
 * @author 
 * @since 2015. 3. 25
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class CsoCnsltDetailDTO extends CsoCnsltDetail {
	/**
	 * 
	 */
    private static final long serialVersionUID = 2989475890583474852L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    private List<CsoCnsltDetailJobTp> csoCnsltDetailJobTp;


}
