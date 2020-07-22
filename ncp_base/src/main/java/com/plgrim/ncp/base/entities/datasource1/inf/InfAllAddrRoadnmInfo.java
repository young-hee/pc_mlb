/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.inf;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 인터페이스 전체 주소 도로명 정보
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("infAllAddrRoadnmInfo")
public class InfAllAddrRoadnmInfo extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 시군구 코드	 
	 */
	private String signguCd;


	/**
	 * 도로 명 번호	 
	 */
	private String roadNmNo;


	/**
	 * 읍면동 일련번호	 
	 */
	private String emdSn;


	/**
	 * 도로 명	 
	 */
	private String roadNm;


	/**
	 * 영어 도로 명	 
	 */
	private String engRoadNm;


	/**
	 * SIDO 명	 
	 */
	private String sidoNm;


	/**
	 * 시군구 명	 
	 */
	private String signguNm;


	/**
	 * 읍면동 구분
	 * 0: 읍면, 1:동, 2:미부여	 
	 */
	private String emdSect;


	/**
	 * 읍면동 코드
	 * 법정동 기준 읍면동 코드	 
	 */
	private String emdCd;


	/**
	 * 상위 도로 명 번호	 
	 */
	private String upperRoadNmNo;


	/**
	 * 상위 도로 명	 
	 */
	private String upperRoadNm;


	/**
	 * 사용 여부
	 * 0:사용, 1:미사용	 
	 */
	private String useYn;


	/**
	 * 변경 이력 사유
	 * 0:도로명변경, 1:도로명폐지, 2:시군구,
	 * 3:읍면동, 4:영문명변경	 
	 */
	private String modHistResn;


	/**
	 * 변경 이력 정보
	 * 도로명코드(12)+ 읍면동일련번호(2)
	 * 신규 정보일 경우 “신규”로 표시	 
	 */
	private String modHistInfo;


	/**
	 * 영어 SIDO 명	 
	 */
	private String engSidoNm;


	/**
	 * 영어 시군구 명	 
	 */
	private String engSignguNm;


	/**
	 * 영어 읍면동 명	 
	 */
	private String engEmdNm;


	/**
	 * 고시 일자
	 * 도로명 코드 고시일자(YYYYMMDD)	 
	 */
	private String ntfcDate;


	/**
	 * 말소 일자
	 * 도로명 코드 말소일자(YYYYMMDD)	 
	 */
	private String ersrDate;


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
