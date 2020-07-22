package com.plgrim.ncp.biz.member.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsef;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsefJobDetail;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsefMbd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsefTgt;


@Data
@EqualsAndHashCode(callSuper=false)
public class MemberPsnlInfoFoResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4496921559930243150L;

	Mbr mbr;
	
	MbrPsnlInfoUsef mbrPsnlInfoUsef;
	
	MbrPsnlInfoUsefJobDetail mbrPsnlInfoUsefJobDetail;
	
	MbrPsnlInfoUsefMbd mbrPsnlInfoUsefMbd;
	
	List<MbrPsnlInfoUsefMbd> mbrPsnlInfoUsefMbds;
	
	MbrPsnlInfoUsefTgt mbrPsnlInfoUsefTgt;
	
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
