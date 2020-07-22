package com.plgrim.ncp.biz.member.result;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageEvtFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3913955347363953701L;

	Evt evt;
	
	private String  prsYn;                // 진행여부
	
	private String  winYn;                // 당첨여부
	
	private String  evtBegDt;             // 진행시작일여부
	
	private String  evtEndDt;             // 진행종료일여부
	
	private String  prizePresnatnDate;    // 당첨일
	
	
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
