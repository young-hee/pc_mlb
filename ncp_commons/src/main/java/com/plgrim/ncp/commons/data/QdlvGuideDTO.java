package com.plgrim.ncp.commons.data;

import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvGudTxt;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QdlvGuideDTO extends QdlvDTO {
	private static final long serialVersionUID = 5836627492662374731L;

	/**
	 * 퀵배송 안내문구
	 */
	private ComQdlvGudTxt comQdlvGudTxt;

	/**
	 * 퀵배송 안내문구 등록/수정 구분
	 */
	private String cmdType;
}
