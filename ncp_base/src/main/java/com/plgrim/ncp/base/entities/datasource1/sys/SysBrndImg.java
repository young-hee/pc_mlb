/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 시스템 브랜드 이미지
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysBrndImg")
public class SysBrndImg extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 브랜드 ID
	 * ㅁ. 온라인에서 사용하는 브랜드 ID	 
	 */
	private String brndId;


	/**
	 * 브랜드 이미지 구분 코드
	 * ㅁ. 브랜드 이미지 구분 : BRND_IMG_SECT
	 *    >. PC 대표 이미지 : PC_RPRST_IMG
	 *    >. 모바일 대표 이미지 : MOBILE_RPRST_IMG
	 *    >. PC 인덱스 이미지 : PC_IDX_IMG
	 *    >. 모바일 인덱스 이미지 : MOBILE_IDX_IMG
	 *    >. PC 인덱스 반전 이미지 : PC_IDX_REVSAL_IMG
	 *    >. 모바일 인덱스 반전 이미지 : MOBILE_IDX_REVSAL_IMG
	 *    >. PC 인덱스 확장 이미지 : PC_IDX_ESTN_IMG
	 *    >. 모바일 인덱스 확장 이미지 : MOBILE_IDX_ESTN_IMG
	 *    >. 브랜드 스토리 대표 이미지 1 : BRND_STORY_RPRST_IMG_1
	 *    >. 브랜드 스토리 대표 이미지 2 : BRND_STORY_RPRST_IMG_2
	 *    >. 브랜드 스토리 대표 이미지 3 : BRND_STORY_RPRST_IMG_3
	 *    >. 브랜드 스토리 대표 이미지 4 : BRND_STORY_RPRST_IMG_4	 
	 */
	private String brndImgSectCd;


	/**
	 * 언어 코드
	 * ㅁ. 코드 테이블 규칙에 따라 대문자를 사용한 ISO 639 표준에 따른다
	 * 
	 * ㅁ. 언어 : LANG
	 *    >. 한국어 : KOR
	 *    >. 중국어 : CHI
	 *    >. 영어 : ENG	 
	 */
	private String langCd;


	/**
	 * 브랜드 이미지 파일 명
	 * ㅁ. 저장된 이미지 파일의 명칭	 
	 */
	private String brndImgFileNm;


	/**
	 * 브랜드 이미지 파일 URL
	 * ㅁ. 실제 저장된 이미지 파일의 위치화 파일 확장자까지를 포함	 
	 */
	private String brndImgFileUrl;


	/**
	 * 브랜드 이미지 대체 내용
	 * 이미지 대체 텍스트	 
	 */
	private String brndImgAltrtvCont;


	/**
	 * 등록자 ID
	 * 등록한 관리자 번호	 
	 */
	private String regtrId;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;


	/**
	 * 수정자 ID
	 * 수정한 관리자 번호	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	
}
