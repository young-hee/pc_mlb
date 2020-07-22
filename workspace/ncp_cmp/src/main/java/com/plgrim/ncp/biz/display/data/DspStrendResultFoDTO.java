package com.plgrim.ncp.biz.display.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFoResult;
import com.plgrim.ncp.biz.display.result.DspStrendFoResult;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class DspStrendResultFoDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private List<DspStrendFoResult> brndList;
	
	private List<DspStrendFoResult> sesonList;
	
	private List<DspCnrFoResult> cornerList;
	
	private List<DspStrendFoResult> strendList;
	
	private List<List<DspStrendFoResult>> coordiThumbnailList;
	
	private DspStrendFoResult strendDetail;
	
	private List<DspStrendFoResult> recommendGoodsList;	
	
	private String trndNm;
	
	private int total;
	
	private String strndTpCd;
	
	private long nextStrnd;
	
	private String dspCtgryNo;
	
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
