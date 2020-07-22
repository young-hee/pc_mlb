package com.plgrim.ncp.commons.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNoti;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNotiAtchFile;


@Data
@EqualsAndHashCode(callSuper=false)
public class SysCmmnNotiDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private FormSysCmmnNotiDTO search = new FormSysCmmnNotiDTO(); // 조회조건 DTO
	
	private SysNoti sysNoti = new SysNoti(); // 시스템공지
	private List<SysNotiAtchFile> sysNotiAtchFileList = new ArrayList<SysNotiAtchFile>(); // 시스템공지파일(저장용)
	private List<SysNotiAtchFile> delSysNotiAtchFileList = new ArrayList<SysNotiAtchFile>(); // 시스템공지파일(삭제용)
	private List<String> adminTpCds = new ArrayList<String>(); // BO세부타겟
	private List<String> boSiteIds = new ArrayList<String>(); // BO노출타겟
	private List<String> mallIds = new ArrayList<String>(); // FO노출타겟, 몰
	private String popupBegDt; // 공지기간시작
	private String popupEndDt; // 공지기간끝	
	private String popupBegDtHour; 		// 공지기간 시작 시간
	private String popupBegDtMinute; 	// 공지기간 시작 분
	private String popupEndDtHour; 		// 공지기간 끝 시간	
	private String popupEndDtMinute; 	// 공지기간 끝 분	
	private String popupBegDt2; 		// 팝업 시작 일시	
	private String popupEndDt2; 		// 팝업 종료 일시
	private String targetTpCd;
    private String langCd;
    private String loginId; //로그인 아이디
	private List<SysCmmnNotiDataDTO> notiGridList; // 공지사항 그리드 
	private List<MultipartFile> uploadFiles; // 파일 
	
	private SysPopupNotiDataDTO formData; // 시스템팝업공지
	private List<SysGrpcoDataDTO> gridList; // 그리드

}
