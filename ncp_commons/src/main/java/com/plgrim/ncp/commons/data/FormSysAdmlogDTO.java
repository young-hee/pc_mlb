package com.plgrim.ncp.commons.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBoSite;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormSysAdmlogDTO  extends AbstractDTO {
	
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	
	/*
	 * 목록 검색 변수
	 */
	
	String searchField; // 1: 운영자명, 2:운영자ID
	String searchText;  //seearchField 조건에 따른 검색 문자
	List<String> searchListBoSite; // 시스템 구분
	
	String searchDtFrom; //접속시간 시작일자 조건
	String searchDtTo;//접속시간 끝일자 조건
	
	String searchMenuSn;// 개인이용정보, 메뉴  로그 조회시 사용됨
	
	String searchPsnlInfoUsefPgeYn; // Y 이면  개인이용정보 메뉴만 조회 
	/*
	 * 선택 변수
	 */
	String selAdminId;// 선택된 관리자 아이디
	long selMenuSn;//선택된 메뉴
	
	
	/*
	 * 메시지 관리
	 */
	/*String searchSmsY; //SMS 영역 검색 (Y)
	
	String searchLmsY; //LMS 영역 검색 (Y)
	
	String searchEmailY; //EMAIL 영역 검색 (Y)
	*/
	
	List<String> searchListGubun = new ArrayList<>(); //메시지 구분 SMS,LMS,MMS,EMAIL,NTALK(알림톡)

	List<String> searchListSendRstl = new ArrayList<>(); //전송상태 1:발송완료 2:발송실패 3:미발송 4:결과대기 5:대체발송성공 6:대체발송실패
	
	String searchAdsrNm; //발신자명
	
	String searchRcpnPhone; //수신자 번호
	
	String searchAdsrPhone; //발신자 번호

	String searchFromDt; //발송시작범위일자
	
	String searchToDt; //발송끝범위일자
	
	String sendType; //발송구분 CAHR:넷퍼시 NTALK:휴머스온

}
