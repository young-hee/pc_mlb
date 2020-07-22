package com.plgrim.ncp.biz.member.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodIcon;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageMainFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    
	/**
	 * 
	 */
	private static final long serialVersionUID = -5612423994939267938L;

	private String alrmTitle;	   // 알람제목
	
	private String alrmType;	   // 알람구분 
	
	private String alrmCnt;		   // 건수
	
	private String dateStart;      // 시작일
	
	private String dateEnd;	       // 종료일
	
	private String alrmNo;	       // 알람번호
	
	private String pcUrl;	       // PC URL
	
	private String mobileUrl;	   // 모바일 URL
	
	
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
