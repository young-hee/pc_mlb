package com.plgrim.ncp.biz.settlement.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class PrmFeeEventBasicFormDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 1L;
		
	private String feeEventNm;
	
	private String eventBegDt;
	
	private String eventEndDt;
	
	private String feeEventTpCd;
	
	private String aditFeeRt;
	
	private String useYn;

	private String eventAplTgtCd;
	
	private String feeEventSn;
	
	private String affAgncCom;
	
	/** The udter id. */
	private String udterId;
	
	/** 수수료행사관리 변경사항 목록 */
	List<PrmFeeEventBasicFormDTO> listPrmFeeEvent;
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
