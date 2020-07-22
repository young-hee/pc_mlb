/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 5. 7       
 */
package com.plgrim.ncp.biz.display.data;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttDspTgt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspCnrConttScBoDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** The sc dvc sect cd. 디바이스 구분 */
	String scDvcSectCd;
	
	/** The sc dsp ctgry no. 카테고리 번호*/
	String scDspCtgryNo;
	
	/** The sc promt sn. 기획전 번호*/
	String scPromtSn;
	
	/** The sc evt no. 이벤트 번호 */
	String scEvtNo;
	
	/** The sc mgzn sn. 매거진 번호 */
	String scMgznSn;
	
	/** The sc strend sn. S-Trend 번호 */
	String scStrndSn;
	
	Long scCnrSn;
	
	String setNmChi;
	
	String setNmEng;
	
	Long scCnrSetSn;
	
	String scConttTpCd;
	
	DspCnrSet dspCnrSet;
	
	DspCnrContt dspCnrContt;
	
	DspCnrConttDspTgt dspCnrConttDspTgt;
	
	
	String[] arLangCd;
	
	String[] arRprstImgAltrtvCont;
	
	String[] arRprstImgFileNm;
	
	String[] arRprstImgFileUrl;
	
	String conttNmChi;
	
	String conttNmEng;

	String[] arImgOvAltrtvCont;
	
	String[] arImgOvFileNm;
	
	String[] arImgOvFileUrl;
	
	String scTmplatTp;

	String scDspTgtLang;

	String[] arHtmlCont;
	
	/** 20160512_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 컬럼 다국어 처리] **/
	String[] arImgMapCont;
	
	String[] arImgDscr;
	
	String scDspBrndId;
	
	//기획전 이미지 가져오기
	String imgPromtSn;
	
	String selectImg;
	
	String scBrndShopId;
	
	
	List<String> arTgtLangCd;
	
	//전시기간
	String inBegDt;
	
	String inEndDt;
	
	Long scRevSn;

	String scRevSectCd;

	String scAdminId;

	Long scSrcRevSn;

	Long scDesRevSn =1L;

	String tpGrpUpperYn;

	String cnrCopyYn;
	String revCpstCopyYn;

	String revOnlyYn;

	String scPrvwSn;

	String scTmplatSn;

	String scAbTestSn;

	String exclsCd;

	String scAbTestSetTurn;

	String scAbTestModTurn;

	String scModResnCont;

	String abTestSelectYn;


	String scBaseRevSn;
	String scBaseRevCpstTurn;

	String scGlobalConttYn;
	String scProgrsStatusYn;

	/** 리비전 복사 시 특정 코너를 조회할 경우 사용 */
	String scCpRevCnrSn;

	/** ab test 변경 end 날짜  */
	String abTestEndDt;

	String useYn; // 개정 구성 사용여부
	Long baseRevSn;
	int	 scRevCpstTurn;

	String prevLangCd;
	
	private List<MultipartFile> uploadFiles; // 파일
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
