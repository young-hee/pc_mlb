package com.plgrim.ncp.commons.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormCsoOrdAffDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private String regBegDt; // 조회시작일
	private String regEndDt; // 조회종료일
	private List<String> ansStatCds; // 문의상태
	private List<String> orgztOrdAffInqTpCds; // 문의유형
	private Long orgztOrdAffInqSn; // 문의 일련번호
	private Integer pceodnlOrdAffInqAnsTurn; // 문의답변 순번
	private String maskingYn; // 마스킹 유무
}
