package com.plgrim.ncp.commons.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvGudTxt;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QdlvGuideResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    
    /**
     * 퀵배송 안내문구
     */
    private ComQdlvGudTxt comQdlvGudTxt;
    
    /**
     * 등록자명
     */
    private String regtrIdNm;

    /**
     * 수정자명
     */
    private String udterIdNm;

    /**
     * 엑셀업로드 실패 페이지구분
     */
    private String excelFailExpsrScrinSectCd;

    /**
     * 엑셀업로드 실패 운영시간여부
     */
    private String excelFailOperTimeSectCd;

    /**
     * 엑셀업로드 실패 시작 시분
     */
    private String excelFailBegHhmm;

    /**
     * 엑셀업로드 실패 종료 시분
     */
    private String excelFailEndHhmm;

    /**
     * 엑셀업로드 실패 안내문구(PC)
     */
    private String excelFailPcGudTxtCont;

    /**
     * 엑셀업로드 실패 안내문구(MB)
     */
    private String excelFailMobileGudTxtCont;

    /**
     * 엑셀업로드 실패 메시지
     */
    private String excelFailMsg;
}

