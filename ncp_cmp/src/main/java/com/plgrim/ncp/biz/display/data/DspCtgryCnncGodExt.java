package com.plgrim.ncp.biz.display.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;

/**
 * 전시 카테고리 연결 상품 확장 엔티티
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("dspCtgryCnncGodExt")
public class DspCtgryCnncGodExt extends DspCtgryCnncGod{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errMsg;

}
