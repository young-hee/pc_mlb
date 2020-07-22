package com.plgrim.ncp.biz.display.data;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("olapicDTO")
public class OlapicDTO extends AbstractDTO {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 화면출력 유형, com.plgrim.ncp.base.enums.OlapicEnum
	 */
	private String dspType;
	
	/**
	 * ERP 상품품번
	 */
	private String erpGodNo;
	
	/**
	 * ERP 상품품번
	 */
	private String[] erpGodNos;
}
