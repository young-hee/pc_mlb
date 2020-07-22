package com.plgrim.ncp.biz.order.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class MbrDelvSearchDTO extends AbstractDTO {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 2657727893312958116L;

	/** 프론트 배송지 검색 추가 */
	private String searchId;

	private String searchNm;

	private String mbrNo;

	private String delvFlag;

	private String delvSeq;

	private String pageNo;
}
