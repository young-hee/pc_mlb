/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.com;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 업체 신청
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comReqst")
public class ComReqst extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 업체 신청 일련번호	 
	 */
	private java.lang.Long comReqstSn;


	/**
	 * 신청 제목	 
	 */
	private String reqstSj;


	/**
	 * 신청자 명	 
	 */
	private String applcntNm;


	/**
	 * 신청자 전화 국가번호	 
	 */
	private String applcntTelNationNo;


	/**
	 * 신청자 전화 지역번호	 
	 */
	private String applcntTelAreaNo;


	/**
	 * 신청자 전화 국번호	 
	 */
	private String applcntTelTlofNo;


	/**
	 * 신청자 전화 국내번호	 
	 */
	private String applcntTelTlofWthnNo;


	/**
	 * 신청자 이메일	 
	 */
	private String applcntEmail;


	/**
	 * 업체 명
	 * ㅁ. 화면의 사업자명	 
	 */
	private String comNm;


	/**
	 * 사업자등록번호	 
	 */
	private String bmanRegNo;


	/**
	 * 대표자 명	 
	 */
	private String rprstivNm;


	/**
	 * 브랜드 명	 
	 */
	private String brndNm;


	/**
	 * 카테고리 설명	 
	 */
	private String ctgryDscr;


	/**
	 * 브랜드 설명	 
	 */
	private String brndDscr;


	/**
	 * 기존 유통 채널 설명	 
	 */
	private String legacyDistbChnnlDscr;


	/**
	 * 메모 내용	 
	 */
	private String memoCont;


	/**
	 * 업체 신청 처리 상태 코드
	 * ㅁ. 업체 신청 처리 상태 : COM_REQST_PRCS_STAT
	 *    >. 처리 대기 : PRCS_WAIT
	 *    >. 처리 보류 : PRCS_RESRVE
	 *    >. 처리 완료 : PRCS_COMPT	 
	 */
	private String comReqstPrcsStatCd;


	/**
	 * 처리 완료 일시	 
	 */
	private java.util.Date prcsComptDt;


	/**
	 * 처리 완료 관리자 ID	 
	 */
	private String prcsComptAdminId;


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