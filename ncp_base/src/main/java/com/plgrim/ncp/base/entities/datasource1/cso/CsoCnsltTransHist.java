/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.cso;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 고객서비스 상담 이관 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("csoCnsltTransHist")
public class CsoCnsltTransHist extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 상담 일련번호	 
	 */
	private java.lang.Long cnsltSn;


	/**
	 * 상담 상세 순번
	 * ㅁ. 상담상세 이력에 대한 순번으로 상담 번호별 순번을 발급하도록 한다.
	 *    >. "고객 상담 상세 이력.상담 상세 순번"의 MAX값 + 1로 등록 되도록 한다.
	 *    >. 숫자로 최대 5자리 " 99999" 로 관리 한다.
	 *       (ORACLE 기준 Format)	 
	 */
	private java.lang.Integer cnsltDetailTurn;


	/**
	 * 이관 요청 순번	 
	 */
	private java.lang.Integer transRequstTurn;


	/**
	 * 이관 변경 정보 코드
	 * ㅁ. 이관 변경 정보 : TRANS_MOD_INFO
	 *    >. 이관 접수자 ID : TRANS_RECR_ID
	 *    >. 이관 상태 코드 : TRANS_STAT_CD	 
	 */
	private String transModInfoCd;


	/**
	 * 이력 일시	 
	 */
	private java.util.Date histDt;


	/**
	 * 변경 유형 코드
	 * ㅁ. 변경 유형 : MOD_TP
	 *    >. 등록 : REG
	 *    >. 조회 : INQIRE
	 *    >. 수정 : UDT
	 *    >. 삭제 : DELETE	 
	 */
	private String modTpCd;


	/**
	 * 변경 내용	 
	 */
	private String modCont;


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
	 * 수정한 관리자 번호	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	
}
