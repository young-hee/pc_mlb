package com.plgrim.ncp.biz.device.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class AppVerMbDTO extends AbstractDTO {
	/**
	 * 
	 */
    private static final long serialVersionUID = -6726992616400596281L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
     private String appSectCd;
     private String appVer;
     private String opstmNm;
     private String pckageNm;
     private String appUdtAplDt;
     private String appEnfrcUdtYn;
     private String appDscr;
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
