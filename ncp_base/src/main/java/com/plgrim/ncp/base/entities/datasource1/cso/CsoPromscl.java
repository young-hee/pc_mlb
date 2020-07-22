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
 * 고객서비스 약속콜
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("csoPromscl")
public class CsoPromscl extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 약속콜 일련번호
	 * ㅁ. 상담상세 이력에 대한 순번으로 상담 번호별 순번을 발급하도록 한다.
	 *    >. "고객 상담 상세 이력.상담 상세 순번"의 MAX값 + 1로 등록 되도록 한다.
	 *    >. 숫자로 최대 5자리 " 99999" 로 관리 한다.
	 *       (ORACLE 기준 Format)	 
	 */
	private java.lang.Long promsclSn;


	/**
	 * 약속콜 상태 코드
	 * ㅁ. 약속콜 상태 : PROMSCL_STAT
	 *    >. 약속 대기 : PROMS_WAIT
	 *    >. 약속 지연 : PROMS_DELAY
	 *    >. 약속 완료 : PROMS_COMPT
	 * 
	 * ㅁ. 약속 지연의 경우 약속콜 일시로 판단 해야 하는 부분임.	 
	 */
	private String promsclStatCd;


	/**
	 * 약속콜 완료 일시	 
	 */
	private java.util.Date promsclComptDt;


	/**
	 * 약속콜 취소 일시	 
	 */
	private java.util.Date promsclCnclDt;


	/**
	 * 약속콜 설정 여부
	 * ㅁ. 상담을 진행 하면서 고객에게 다시 전화를 걸기로 한 경우 설정 함
	 * 
	 * ㅁ. 약속 전화 설정 여부
	 *    >. 약속콜 : Y
	 *    >. 일반콜 : N
	 * 
	 * ㅁ. 예약콜이 설정되면 반드시 예약콜이 존재하도록 해야 한다.	 
	 */
	private String promsclConfigYn;


	/**
	 * 담당자 확인 여부	 
	 */
	private String chrgerCnfirmYn;


	/**
	 * 약속 대상 명	 
	 */
	private String promsTgtNm;


	/**
	 * 약속콜 국가번호
	 * ㅁ. 상담 고객의 전화번호를 의미 함
	 *    >. 주로 SMS를 통한 회신의 용도로 관리 됨.	 
	 */
	private String promsclNationNo;


	/**
	 * 약속콜 지역번호
	 * ㅁ. 상담 고객의 전화번호를 의미 함
	 *    >. 주로 SMS를 통한 회신의 용도로 관리 됨.	 
	 */
	private String promsclAreaNo;


	/**
	 * 약속콜 국번호
	 * ㅁ. 상담 고객의 전화번호를 의미 함
	 *    >. 주로 SMS를 통한 회신의 용도로 관리 됨.	 
	 */
	private String promsclTlofNo;


	/**
	 * 약속콜 국내번호
	 * ㅁ. 상담 고객의 전화번호를 의미 함
	 *    >. 주로 SMS를 통한 회신의 용도로 관리 됨.	 
	 */
	private String promsclTlofWthnNo;


	/**
	 * 약속콜 일시	 
	 */
	private java.util.Date promsclDt;


	/**
	 * 약속콜 메모 내용	 
	 */
	private String promsclMemoCont;


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
