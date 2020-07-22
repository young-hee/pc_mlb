package com.plgrim.ncp.commons.data;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SysCmmnTempletDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private FormSysCmmnTempletDTO search = new FormSysCmmnTempletDTO(); // 조회조건 DTO
/*	
	private SysTemplet sysTemplet = new SysTemplet(); // 템플릿
	private List<SysTempletAtchFile> sysTempletAtchFileList = new ArrayList<SysTempletAtchFile>(); // 시스템공지파일(저장용)
	private List<SysTempletAtchFile> delSysTempletAtchFileList = new ArrayList<SysTempletAtchFile>(); // 시스템공지파일(삭제용)
*/	
	private List<SysCmmnTempletDataDTO> templetGridList; // 메뉴얼 그리드 
	private List<MultipartFile> uploadFiles; // 파일 
	
	private List<SysGrpcoDataDTO> gridList; // 그리드
	
	private String loginId; // 로그인아이디
}
