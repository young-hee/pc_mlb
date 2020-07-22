package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfig;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfigDetail;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysConfigDTO  extends AbstractDTO {
	private static final long serialVersionUID = 5836627492662374731L;
	
	//검색조건
	private String term;
	private String fromDate;
	private String toDate;
	private List<String> mallIds; //몰
	private List<String> searchListUseYn;	//사용여부
	private List<String> searchListConfigSectCd;	//설정구분
	private String searchField; //검색 필드 ( 1:설정명, 2:설정 key, 3:설정 value)
	private String searchText; // 검색 값
	
	private SysDynmcConfig sysDynmcConfig; //설정 master
	private SysDynmcConfigDetail sysDynmcConfigDetail; //설정 detail
	
	private SysDynmcConfig agoSysDynmcConfig;
	
	private List<SysDynmcConfigDetail> sysDynmcConfigDetailList; // 등록/수정 팝업그리드
	
	List<SysConfigDTO> gridList; //리스팅그리드
}
