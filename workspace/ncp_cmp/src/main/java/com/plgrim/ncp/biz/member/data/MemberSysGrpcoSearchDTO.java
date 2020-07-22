package com.plgrim.ncp.biz.member.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
/**
 * MemberSysGrpcoSearchDTO
 * @author lss
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberSysGrpcoSearchDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * serialVersionUID
	 */
	 private static final long serialVersionUID = 1L;
	 
	 /**
	  * schGubn
	  */
	 private String schGubun;
	 
	 /**
	  * schTxt
	  */
	 private String schTxt;

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
