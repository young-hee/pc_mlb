package com.plgrim.ncp.biz.display.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspPromtBoDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	String[] arPromtSn;
	
	String scPromtSn;
	
	String scDateType;
	
	String scStartDate;
	
	String scEndDate;
	
	String scSprtrTurn;
	
	String scAplMallId;
	
	String scAplMdBrndCd;

	String godEvlExpsrSectCd;

	List<String> scDspYn;
	
	DspPromt dspPromt;
	
	//기획전명
	String[] arPromtNm;

	//기획전 설명
	String[] arPromtDscr;

	
	//1단이미지//////////////
	//PC 대표이미지
	String[] arLangCdNm;
	
	String[] arPcImgAltrtvCont;
	
	String[] arPcImgFileNm;
	
	String[] arPcImgFileUrl;
	
	String[] arPcImgExpsrTxt1Cont;
	
	String[] arPcImgExpsrTxt2Cont;
	
	//Mobile 대표이미지
	String[] arLangCdI;
	
	String[] arMobileImgAltrtvCont;
	
	String[] arMobileImgFileNm;
	
	String[] arMobileImgFileUrl;
	
	String[] arMobileImgExpsrTxt1Cont;
	
	String[] arMobileImgExpsrTxt2Cont;
	
	//2단이미지////////////////
	String[] ar2pcePcImgAltrtvCont;
	
	String[] ar2pcePcImgFileNm;
	
	String[] ar2pcePcImgFileUrl;
	
	String[] ar2pcePcImgExpsrTxt1Cont;
	
	String[] ar2pcePcImgExpsrTxt2Cont;
	
	
	//Mobile 대표이미지
	String[] ar2pceMobileImgAltrtvCont;
	
	String[] ar2pceMobileImgFileNm;
	
	String[] ar2pceMobileImgFileUrl;
	
	String[] ar2pceMobileImgExpsrTxt1Cont;
	
	String[] ar2pceMobileImgExpsrTxt2Cont;
	
	
	//상품상세용 - PC 대표이미지
	String[] arLangCdGod;
	
	String[] arPcGodImgAltrtvCont;
	
	String[] arPcGodImgFileNm;
	
	String[] arPcGodImgFileUrl;
	
	//Mobile 대표이미지
	String[] arLangCdIGod;
	
	String[] arMobileGodImgAltrtvCont;
	
	String[] arMobileGodImgFileNm;
	
	String[] arMobileGodImgFileUrl;
	
	//전시기간
	String inBegDt;
	
	String inEndDt;
	
	//전시대상
	List<String> arTgtLangCd;
	
	List<String> arGodEvlExpsrYn;

	List<String> arDvcCd;
	
	List<String> arTgtMbrAtrbCd;
	
	List<String> arTgtMbrTpCd;
	
	String tgtGrpco;
	
	//브랜드
	List<String> arMdBrndCd;
	String mdBrndCd;
	
	
	//그룹사 그리드
	List<DspPromtDspTgtBoDTO> grpCoList;
	
	//tempfile 리스트
	List<String> tempFileList;
	
	String[] scDspTgtLang;
	
	String rprst1pceImgYn;
	String rprst2pceImgYn;
	String godImgYn;
	
	/**  기획전상품평 포토상품평 우선여부  0525 ash */
    private String imgGodEvlPrioExpsrYn;
	
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
	public String[] getArPromtSn() {
		
		if(scPromtSn==null||"".equals(scPromtSn)) {
			this.arPromtSn = null;
		}
		else {
			this.arPromtSn = scPromtSn.split("\r\n");			
		}
		return this.arPromtSn;
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
