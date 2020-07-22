package com.plgrim.ncp.commons.data;

import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvOper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class QdlvTimeDTO extends QdlvDTO {
	private static final long serialVersionUID = 5836627492662374731L;

	/**
	 * 퀵배송 운영시간
	 */
	private ComQdlvOper comQdlvOper;

	/**
	 * 운영일 구분 코드명
	 */
	private String operDaySectCdNm;

	/**
	 * 퀵배송 운영시간 리스트
	 */
	private List<QdlvTimeDTO> qdlvTimeDTOList;
}
