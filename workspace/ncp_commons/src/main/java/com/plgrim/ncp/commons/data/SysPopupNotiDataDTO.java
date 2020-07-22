package com.plgrim.ncp.commons.data;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNoti;


@Data
@EqualsAndHashCode(callSuper = false)
public class SysPopupNotiDataDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private SysPopupNoti sysPopupNoti; // 시스템공지 
	private List<String> mallIds; // 몰
	private List<String> dvcCds; // 디바이스 유형
	private List<String> langCds; // 언어 유형
	private List<String> tgtMbrTps; // 회원유형
	private List<String> tgtMbrAtrbs; // 회원속성
	private List<String> mainDsps; // Main 전시
	private List<String> ctgryDsps; // Category 전시
	private List<String> brndDsps; // Brand 전시
	private String grpco; // 회원속성상세
	private String dspBegDtDay; // 전시시작일시
	private String dspBegDtHour; // 전시시작일시
	private String dspBegDtMinute; // 전시시작일시
	private String dspEndDtDay; // 전시종료일시
	private String dspEndDtHour; // 전시종료일시
	private String dspEndDtMinute; // 전시종료일시
	private String dspTimeBegHour; // 반복시작일시
	private String dspTimeBegMinute; // 반복시작일시
	private String dspTimeEndHour; // 반복종료일시
	private String dspTimeEndMinute; // 반복종료일시
	private String chngImgYn; // 이미지변경여부
	private List<String> imgFile; // 파일경로
	private List<String> imgSj; // 원본파일명
	private List<BigDecimal> imgSize; // 이미지사이즈
}
