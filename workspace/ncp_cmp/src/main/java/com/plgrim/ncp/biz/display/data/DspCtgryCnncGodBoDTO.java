package com.plgrim.ncp.biz.display.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspCtgryCnncGodBoDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	DspCtgryCnncGod dspCtgryCnncGod;

	String[] cpDspCtgryNo;
	
	String scDspBrndId;
	
	//상품이동 기능을 위한 추가 param
	List<String> moveGodNo;
	List<String> dupGodNo;
	String srcCtgryNo;
	String desCtgryNo;
	
	String srcCtgrySectCd;
	
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
