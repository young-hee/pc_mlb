package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstCd;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysPrudResult extends AbstractResult {

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

	/**
	 * 
	 */
    private static final long serialVersionUID = 2891203045485644739L;
    
	SysPrdlstCd sysPrdlstCd;
    private String prdlstBrndNm;
    private String prdlstBrndId;
	private String prdlstNmEng; // 다국어코드명(영어)
	private String prdlstNmChi; // 다국어코드명(중국어)
	private Long engMlangSn; // 다국어일련번호(영어)
	private Long chiMlangSn; // 다국어일련번호(중국)
    private Long godWt; // 무게
    private String prdlstSectCd;
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
