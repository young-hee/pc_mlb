package com.plgrim.ncp.commons.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopEvt;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopHoldy;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopPhoto;


@Data
@EqualsAndHashCode(callSuper=false)
public class SysShopDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private FormSysShopDTO search = new FormSysShopDTO(); // 조회조건 DTO
	
	private SysShopDataDTO formData; // 저장용
	
	private List<SysShopDataDTO> gridList; // 그리드
	
	private SysShopEvt sysShopEvt;	// 매장 이벤트 Entity
	
	private List<SysShopPhoto> sysShopPhotoList = new ArrayList<SysShopPhoto>(); // 매장 이미지

	private List<SysShopPhoto> sysShopPhotoDeleteList = new ArrayList<SysShopPhoto>(); // 매장 이미지 삭제
	
	/**
	 * 시스템 매장 휴일 Entity
	 */
	private SysShopHoldy sysShopHoldy;
	
	/**
	 * 	조회구분
	 */
	private String searchGubun;
	
   /**
	 * 	브랜드매장타입
	 */
	private List<String> searchBrndShopType;
	
	/**
    * 페이지번호
    */
	private String pageNo;
	
	/**
	 * 브랜드 매장 이벤트 상태
	 */
	private String evtStatus;

	/**
	 * 브랜드 매장 이벤트 상태 리스트 검색
	 */
	private List<String> evtStatusList;
	
	/**
	 * 브랜드 매장 이벤트 사용여부 리스트 검색
	 */
	private List<String> evtUseYnList;
	
	/**
	 * 브랜드 매장 휴일 검색 시작일
	 */
	private String searchHoldyBegDate;
	
	/**
	 * 브랜드 매장 휴일 검색 종료일
	 */
	private String searchHoldyEndDate;
}
