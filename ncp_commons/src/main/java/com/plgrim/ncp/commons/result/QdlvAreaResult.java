package com.plgrim.ncp.commons.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvArea;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QdlvAreaResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    
    /**
     * 퀵배송 가능지역
     */
    private ComQdlvArea comQdlvArea;
    
    /**
     * 등록자명
     */
    private String regtrIdNm;

    /**
     * 시/도 코드
     */
    private String ctyCd;
}

