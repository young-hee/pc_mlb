package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnCrtfcCd;


/**
 * 쿠폰 유효성 체크 DTO
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PrmCpnCrtfcCdDTO extends AbstractDTO {

	
    /**
     * UID
     */
    private static final long serialVersionUID = 1L;
    
	/**
	 * 프로모션 번호
	 */
    private List<PrmCpnCrtfcCd> prmCpnCrtfcCdList;
    
    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */



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