package com.plgrim.ncp.biz.display.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtr;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspPromtSprtrBoDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	DspPromtSprtr dspPromtSprtr;
	
	String[] arSprtrNm;
	
	String[] arLangCdNm;
	
	String[] arRprstImgAltrtvCont;
	
	String[] arRprstImgFileNm;
	
	String[] arRprstImgFileUrl;
	
	List<String> tempFileList;

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
