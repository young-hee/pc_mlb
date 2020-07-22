package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplTgt;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrmAplTgtExtend extends PrmAplTgt {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    /**
     * 제휴사 ID
     */
    private String comId;

    /**
     * 제휴사 명
     */
    private String comNm;
    
    /**
     * 그룹사 명
     */
    private String grpcoNm;

    private String comStatCd = "USE";

    private String deleteYn = "N";

    private String comTpCd = "AFF_SELR";

    private String mallNm;
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

}
