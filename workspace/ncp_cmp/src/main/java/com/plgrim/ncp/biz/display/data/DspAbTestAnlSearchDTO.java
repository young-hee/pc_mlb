package com.plgrim.ncp.biz.display.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TestSearchDTO
 * 
 * @author eunseo1.park
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DspAbTestAnlSearchDTO extends AbstractDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 조회시작일자
	 */
	private String searchStartDate;

	/**
	 * 조회끝일자
	 */
	private String searchEndDate;
	
	/*
	 * AB테스트 일련번호
	 */
	private int abTestSn;
	
	/*
	 * 세트순번
	 */
	private int setTurn=1;
	
	/*
	 * 변경순번
	 */
	private int modTurn=1;
	
	/*
	 * 개정일련번호
	 */
	private int revSn;
	
	/*
	 * 개정구성순번
	 */
	private int revCpstTurn;
	
	/*
	 * 단말구분(PC,MOBILE,PC_MOBILE)
	 */
	private String deviceType;
	
	/*
	 * 언어코드
	 */
	private String langCd;
	
	/*
	 * ABTEST "제외" 사용여부
	 */
	private String exclsYn;
	
}
