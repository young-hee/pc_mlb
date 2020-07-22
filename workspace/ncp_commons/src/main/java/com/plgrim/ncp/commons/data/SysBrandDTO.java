package com.plgrim.ncp.commons.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndExtend;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysBrandDTO  extends AbstractDTO {

	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	
	SysBrndExtend sysBrnd;
	
	List<String> scEmpOnlyYn;
	
	List<String> scBrndId;
	
	List<String> scPntAccmlYn;
	
	List<String> scPntUseYn;
	
	List<String> scImdtlDcYn;

	String taBrndId;
	
	public List<String> getScBrndId() {
		
		if(taBrndId==null||"".equals(taBrndId)) {
			this.scBrndId = null;
		}
		else {
			this.scBrndId = new ArrayList<String>();
			for(String s: taBrndId.split("\r\n")) {
				this.scBrndId.add(s);
			}		
		}
		return this.scBrndId;
	}

	/**
	 * NCP3차 추가
	 * since 2016. 01. 20
	 */
	List<String> scWebpntAccmlYn;

	List<String> scWebpntUseYn;

	String scPntTp;

	List<String> scAccmlYn;

	List<String> scUseYn;

	String upperBrndId;


}
