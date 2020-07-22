package com.plgrim.ncp.commons.data;

import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvHoldy;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QdlvHolidayDTO extends QdlvDTO {
	private static final long serialVersionUID = 5836627492662374731L;

	/**
	 * 퀵배송 미운영일
	 */
	private ComQdlvHoldy comQdlvHoldy;


	/**
	 * 검색 조건 시작년월
	 */
	private String searchQdlvHolidayFromYyyyMm;

	/**
	 * 검색 조건 종료년월
	 */
	private String searchQdlvHolidayToYyyyMm;
}
