package com.plgrim.ncp.commons.data;

import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvArea;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class QdlvAreaDTO extends QdlvDTO {
	private static final long serialVersionUID = 5836627492662374731L;

	/**
	 * 퀵배송 가능지역
	 */
	private ComQdlvArea comQdlvArea;

	/**
	 * 시도코드
	 */
	private String ctyCd;

	/**
	 * 검색 조건 시도코드
	 */
	private String searchCtyCd;
}
