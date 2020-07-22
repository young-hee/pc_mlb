package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 유학생 게시판 첨부파일
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("ssaBbsAtchmnfl")
public class CssNoticeAtchmnfl extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 첨부파일 일련번호	 
	 */
	private java.lang.Long atchmnflSn;
	/**
	 * 게시글 일련번호	 
	 */
	private java.lang.Long bbcSn;
	/**
	 * POLL 일련번호	 
	 */
	private java.lang.Long pollSn;
	/**
	 * 첨부 구분 코드
ㅁ. 첨부 구분 코드
  > 이미지 : IMG
  > 파일     : FILE	 
	 */
	private String atchSectCd;
	/**
	 * 첨부파일 URL	 
	 */
	private String atchmnflUrl;
	/**
	 * 첨부 파일 명	 
	 */
	private String atchFileNm;
	/**
	 * 이미지 대체 내용
이미지 대체 텍스트	 
	 */
	private String imgAltrtvCont;
	/**
	 * 게시글 내용
게시글 등록 유형이 에디터인 경우

게시글 내용을 이곳에 입력	 
	 */
	private String bbcCont;
	/**
	 * 등록자 ID
등록한 관리자 번호	 
	 */
	private String regtrId;
	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;
	/**
	 * 수정자 ID
수정한 관리자 번호	 
	 */
	private String udterId;
	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

}