package com.plgrim.ncp.biz.display.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspGoodsAddDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 5890646694257941504L;

	String min;
	String sec;
	//tempfile 리스트
	List<String> tempFileList;
	String hidgoodsImageDefault;
	String hidgoodsImageOver;
}
