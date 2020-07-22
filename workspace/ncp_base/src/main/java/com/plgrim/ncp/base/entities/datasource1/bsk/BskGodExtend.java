/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      mc009.park
 * @since       2015. 6. 20       
 */
package com.plgrim.ncp.base.entities.datasource1.bsk;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author mc009.park
 * @since 2015. 6. 20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("bskGodExtend")
public class BskGodExtend extends BskGod{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1392954534668211854L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
	/** The parent god turn. */
	private int parentGodTurn;
	
	/** The pckage god tp cd. */
	private String pckageGodTpCd;
	
	/** The cpst god qty. */
	private int cpstGodQty;
	
	/** The brnd id. */
	private String brndId;
	
	/** The god cnt. */
	private int godCnt;
	
	/** The cart seq. */
	private int cartSeq;
	
	/** The color nm. */
	private String colorNm;
	
	/** The itm nm. */
	private String itmNm;
	
	/** The brnd nm. */
	private String brndNm;
	
	/** The god nm. */
	private String godNm;
	
	/** The img url. */
	private String imgUrl;
	
	/** The prm no. */
	private String prmNo;

	/** The sell yn. */
	private String sellYn;
	
	/** The inv yn. */
	private String invYn;
	
	private String colorCd;
	
	private String clorChipImgUrl;
	
	private String availMinOrdCnt = "Y";
    
    private String availMaxOrdCnt = "Y";
    
    private String erpGodNo;
    
    private String skuNo;
    
    private Long dmstcDlvCstPlcSn;
    
    private String partmalSectCd;
    
    private String lastSalePrc;
    
    private String godSaleSectCd;
    
    private String itmStatCd;
    
    private Integer newGodTurn;
    
    private java.math.BigDecimal csmrPrc;
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
