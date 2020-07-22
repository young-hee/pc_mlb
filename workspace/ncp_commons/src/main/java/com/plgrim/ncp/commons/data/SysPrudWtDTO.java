package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstWt;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysPrudWtDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */
    SysPrdlstWt sysPrdlstWt;
    
    //검색조건
  	String searchField; //검색 필드
  	String searchText; // 권한그룹코드 , 권한그룹명
  	List<String> searchListUseYn;	//사용여부
  	
  	String searchMall; // Mall 구분. (I)ntegration, (C)irculation, (B)eaker
  	String searchLimit; //검색 목록 Limit 수
  	
  	//String prdlstCd;	// 검색 품목코드 
  	//String prdlstCdNm;  // 검색 품목코드네임
  	String searchPrudCode; // 검색 품목코드
  	String searchPrudSeason; //검색 시즌코드
  	
  	List<SysPrudDTO> listPrudCd; // SysPrud 목록
  	private List<SysPrudWtDTO> gridList; // 그리드
  	private String prdlstNm;
  	private String useYnNm;
}