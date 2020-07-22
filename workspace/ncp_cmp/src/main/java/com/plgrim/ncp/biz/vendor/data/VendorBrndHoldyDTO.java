package com.plgrim.ncp.biz.vendor.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

/**
 * VendorBrndHoldyDTO
 * @author lss
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VendorBrndHoldyDTO extends AbstractDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 매장 ID	 
	 */
	private String shopId;
	/**
	 * 브랜드 ID	 
	 */
	private String brndId;
	
	/**
	 * 구분(일별:DAY, 요일별:WEEK)
	 */
	private String gubun;
	
	/**
	 * 시스템일자
	 */
	private String sysDate;
	
	/**
	 * 수정시 key
	 */
	private String sysDateKey;
	
	/**
	 * sysDate 변경여부
	 */
	private String sysDateModifyYn;
	
	/**
	 * 수정자아이디
	 */
	private String udterId;
	
	/**
	 * 설명
	 */
	private String dscr;
	
	/**
	 * 요일코드 리스트
	 */
	private List<String> dowCd;
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
