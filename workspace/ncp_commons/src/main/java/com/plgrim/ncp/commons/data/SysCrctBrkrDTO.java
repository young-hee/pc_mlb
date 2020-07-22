package com.plgrim.ncp.commons.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysCrctBrkrDTO  extends AbstractDTO {
	private static final long serialVersionUID = 5836627492662374731L;
	
	//검색조건
	private String term;
	private String fromDate;
	private String toDate;
	private List<String> mallIds; //몰
	private List<String> searchListUseYn;	//사용여부
	private List<String> searchListStatCd;	//서킷브래이커 스케줄 구분
	private String searchField; //검색 필드 ( 1:서킷브래이커명, 2:스케줄설명)
	private String searchText; // 검색 값
	private String cbBegDt;
}
