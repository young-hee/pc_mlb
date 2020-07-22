/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : jackie(jackie)
 * @date : 20150501
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.biz.callcenter.result;

import com.plgrim.ncp.base.abstracts.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 시스템 관리자
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysAdmin")
public class SysAdminResult extends AbstractEntity{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 관리자 ID
	 */
	private String adminId;
	/**
	 * 비밀번호
	 */
	private String pw;
	/**
	 * 관리자 유형 코드
ㅁ. 관리자유형 : ADMIN_TP
   >. 운영자 : OPRTR
   >. 입점사 : STPNT_COM
   >. 대행사 : VRSC_COM
   >. 상담원 : CNSLR
	 */
	private String adminTpCd;
	/**
	 * 관리자 상태 코드
ㅁ. 관리자 상태 : ADMIN_STAT
   >. 승인대기 : APRV_WAIT
   >. 승인완료 : APRV_COMPT
   >. 승인거부 : APRV_REJECT
   >. 휴직 : LAYOFF
   >. 휴면 : DRMC
   >. 일반탈퇴 : GNRL_SECSN
   >. 강제탈퇴 : ENFRC_SECSN


	 */
	private String adminStatCd;
	/**
	 * 승인 일시
	 */
	private java.util.Date aprvDt;
	/**
	 * 거부 일시
	 */
	private java.util.Date rejectDt;
	/**
	 * 휴직 일시
	 */
	private java.util.Date layoffDt;
	/**
	 * 휴면 일시
	 */
	private java.util.Date drmcDt;
	/**
	 * 탈퇴 일시
	 */
	private java.util.Date secsnDt;
	/**
	 * 관리자 명
	 */
	private String adminNm;
	/**
	 * 관리자 사원번호
	 */
	private String adminEmpNo;
	/**
	 * 부서 명
	 */
	private String deptNm;
	/**
	 * 업체 ID
	 */
	private String comId;
	/**
	 * 매장 ID
	 */
	private String shopId;
	/**
	 * 이메일
	 */
	private String email;

	/**
	 * PG 연동 노출 여부
	ㅁ. 고객센터 직원에만 해당 하는 컬럼
	ㅁ. MD가 설정
	 */
	private String pgIntrlckExpsrYn;

	/**
	 * 휴대전화 국가번호
	 */
	private String mobilNationNo;
	/**
	 * 휴대전화 지역번호
	 */
	private String mobilAreaNo;
	/**
	 * 휴대전화 국번호
	 */
	private String mobilTlofNo;
	/**
	 * 휴대전화 국내번호
	 */
	private String mobilTlofWthnNo;
	/**
	 * 전화 국가번호
	 */
	private String telNationNo;
	/**
	 * 전화 지역번호
	 */
	private String telAreaNo;
	/**
	 * 전화 국번호
	 */
	private String telTlofNo;
	/**
	 * 전화 국내번호
	 */
	private String telTlofWthnNo;
	/**
	 * 팩스 국가번호
	 */
	private String faxNationNo;
	/**
	 * 팩스 지역번호
	 */
	private String faxAreaNo;
	/**
	 * 팩스 국번호
	 */
	private String faxTlofNo;
	/**
	 * 팩스 국내번호
	 */
	private String faxTlofWthnNo;
	/**
	 * 최종 로그인 일시
	 */
	private java.util.Date lastLoginDt;
	/**
	 * 로그인 실패 횟수
	 */
	private Integer loginFailrCount;
	/**
	 * 비밀번호 초기화 여부
	 */
	private String pwIntzYn;
	/**
	 * 비밀번호 초기화 일시
	 */
	private java.util.Date pwIntzDt;


	/**
	 * 로그인 가능 IP 체크 여부
	 */
	private String loginPsbIpChkYn;

	/**
	 * 로그인 가능 IP
	 */
	private String loginPsbIp;

	/**
	 * CTI ID
	 */
	private String ctiId;
	/**
	 * CTI 내선번호
	 */
	private String ctiExno;
	/**
	 * CTI 녹취 ID
	 */
	private String ctiRecrdId;
	/**
	 * CTI Agent Party
	 */
	private String ctiGrpId;
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

	/**
	 * 고객센터 센터 구분코드
	 */
	private String ccCntrSectCd;

	/**
	 * 고객센터 팀 구분코드
	 */
	private String ccTeamSectCd;

	/**
	 * ID/사용자명 존재여부 체크
	 */
	private int idExistCnt;
	private int nmExistCnt;

	/**
	 * 사용자 권한 구룹 번호.
	 */
	private Integer authorGrpSn;
}
