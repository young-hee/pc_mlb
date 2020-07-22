/**
 * @package : com.plgrim.ncp.base.entities..inf
 * @author : jackie(jackie)
 * @date : 20150526
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.biz.callcenter.data;

import com.plgrim.ncp.base.abstracts.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 인터페이스 메일
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CsoCallback extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 콜백 일련번호
	 */
	private Long clbcSn;
	/**
	 * 콜백 등록 일시
	 */
	private java.util.Date clbcRegDt;
	/**
	 * 콜백 분배 일시
	 */
	private java.util.Date clbcDstbDt;
	/**
	 * 콜백 국가번호
	 */
	private String clbcNationNo;
	/**
	 * 콜백 지역번호
	 */
	private String clbcAreaNo;
	/**
	 * 콜백 국번호
	 */
	private String clbcTlofNo;
	/**
	 * 콜백 국내번호
	 */
	private String clbcTlofWthnNo;
	/**
	 * 발신 전화 국가번호
	 */
	private String dsptchTelNationNo;
	/**
	 * 발신 전화 지역번호
	 */
	private String dsptchTelAreaNo;
	/**
	 * 발신 전화 국번호
	 */
	private String dsptchTelTlofNo;
	/**
	 * 발신 전화 국내번호
	 */
	private String dsptchTelTlofWthnNo;
	/**
	 * 콜백 처리 상태 코드
	 ㅁ. 콜백 처리 상태 : CLBC_PROC_STAT
	 >. 등록 : REG
	 >. 고객연결중 : CSTMR_CNNC
	 >. 연결실패 : CNNC_FAILR
	 >. 상담진행중 : CNSLT_PROGRS
	 >. 완료-성공 : COMPT_SUCCES
	 >. 완료-실패 : COMPT_FAILR
	 >. 포기 : CLMP
	 */
	private String clbcPrcsStatCd;
	/**
	 * 콜백 완료 일시
	 */
	private java.util.Date clbcComptDt;
	/**
	 * 상담 관리자 ID
	 */
	private String cnsltAdminId;
	/**
	 * 등록자 ID
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
