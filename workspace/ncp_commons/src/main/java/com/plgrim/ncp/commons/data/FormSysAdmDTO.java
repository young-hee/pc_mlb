package com.plgrim.ncp.commons.data;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.sys.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormSysAdmDTO  extends AbstractDTO {
	
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	
	/*
	 * 목록 검색 변수
	 */
	
	String searchField;
	String searchText;
	String selAuthorGrpSn;
	String searchComId;
	String searchShopId;
	String selAdminTpCd;
	//String selAdminStatCd;
	String searchRegDtFrom;
	String searchRegDtTo;
	
	List<String> searchListBoSite;
	
	String pageNo; //페이지 넘버
	
	List<String> searchListAdminStatCd;
	
	// 목록 검색 변수 정의 끝
	
	String selAdminId;// 선택된 관리자 아이디
	
	/**
	 * 운영자 등록/수정
	 */
	SysAdmin sysAdmin; 
	
	String regTrNm ; //등록자 명
	String udterNm ; //수정자 명
	
	/**
	 * 담당 브랜드
	 */
	List<SysAdminBrnd> sysAdminBrndList;
	
	/*
	 * 시스템관리자 담당업무
	 */
	List<SysAdminChrgJob> sysAdminChrgJobList;
	
	/*
	 * 시스템관리자 고객센터 업무유형
	 */
	List<SysCcJobTp> sysCcJobTpList;
	
	/**
	 * 접근 사이트 ID 설정
	 */
	List<SysBoSite> sysBoSiteList;
	
	/*권한그룹*/
	SysAuthorGrp sysAuthorGrp;

	List<SysAdminMall> sysMallList;
	
	/*
	 * 권한 
	 */
	SysAuthor sysAuthor;
	
}
