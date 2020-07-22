package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
@Data
@EqualsAndHashCode(callSuper = false)
public class EventExtendsFoDTO extends Evt{

	private String imgFileUrl;
	private String imgAltrtvCont;
	private String bcrnImgFileUrl;
	private String bcrnImgAltrtvCont;
	private int lastDate;
	private int firstWeek;
    private int todate;
	private String eventEndYn;
	private long evtApplcnCnt;
	
	private String imgFileUrl1;
	private String imgAltrtvCont1;
	private String imgExpsrTxt1Cont1;
	private String imgExpsrTxt2Cont1;
	
	private String imgFileUrl2;
	private String imgAltrtvCont2;
	private String imgExpsrTxt1Cont2;
	private String imgExpsrTxt2Cont2;

	private String lwprtEvtList;
	
    private String boAccess;       // 이동탭 구분
	
	/**
	 * 출석 일수	 
	 */
	private java.lang.Integer atendDaycnt;
	
	private java.lang.Integer lastDaycnt;
	/**
	 * 스탬프 횟수
	 * ㅁ. 스탬프 조건	 
	 */
	private java.lang.Integer stmpCount;
	
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
