package com.plgrim.ncp.biz.display.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrnd;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspStrndBoDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */	
	/**
	 * 
	 */
    private static final long serialVersionUID = 5890646694257941504L;

	String[] arStrndSn;
	
	String scStrndSn;
	
	String scDateType;
	
	String scStartDate;
	
	String scEndDate;
	
	//브랜드배열
	String[] arBrndId;
	
	List<String> scDspYn;
	
	DspStrnd dspStrnd;
	
	//기획전명
	String[] arStrndNm;
	
	//기획전설명
	String[] arStrndDscr;
	
	//PC 대표이미지
	String[] arLangCdNm;
	
	String[] arRprstImgAltrtvCont;
	
	String[] arRprstImgFileNm;
	
	String[] arRprstImgFileUrl;
	
	//전시기간
	String inBegDt;
	
	String inEndDt;
	
	
	//tempfile 리스트
	List<String> tempFileList;
	
	String scAplMallId;
	//전시대상
	List<String> arTgtLangCd;
	
	List<String> arDvcCd;
	
	List<String> arTgtMbrAtrbCd;
	
	List<String> arTgtMbrTpCd;
	
	String tgtGrpco;
	
	//그룹사 그리드
	List<DspStrndDspTgtBoDTO> grpCoList;
	String[] arLangCd;

	//추가이미지
	String[] arRprstImg2AltrtvCont;
	
	String[] arRprstImg2FileNm;
	
	String[] arRprstImg2FileUrl;

	String scRevSn;

	DspRevCpst dspRevCpst;
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
	public String[] getArStrndSn() {
		
		if(scStrndSn==null||"".equals(scStrndSn)) {
			this.arStrndSn = null;
		}
		else {
			this.arStrndSn = scStrndSn.split("\r\n");			
		}
		return this.arStrndSn;
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
