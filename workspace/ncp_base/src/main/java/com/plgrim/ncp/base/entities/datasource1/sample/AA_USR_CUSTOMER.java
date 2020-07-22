package com.plgrim.ncp.base.entities.datasource1.sample;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * ASIS 회원 엔티티.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("customer")
public class AA_USR_CUSTOMER extends AbstractEntity {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

	/** 고객ID */
	String userId;

	/** ERP 고객번호 */
	String erpUserId;

	/** 고객명 */
	String userNm;

	/** 고객영문명 */
	String userengNm;

	/** 로그인ID */
	String loginId;

	/** 비밀번호 */
	String passwdTxt;

	/** 공통코드 10900 참고 */
	String custtypCd;

	/** 전사등급코드 */
	String gradeCd;

	/** 가입완료/탈퇴완료 */
	String stateCd;

	/** 1:국내, 2:국외 */
	String habiType;

	/** F:여자 M:남자 */
	String genderType;

	/** Y:양력, N:음력 */
	String solarYn;

	/** 생년월일 */
	String birthdayTxt;

	/** Y:수신, N:미수신 */
	String emailrepYn;

	/** 이메일 */
	String emailTxt;

	/** 전화번호 */
	String telTxt;

	/** Y:수신, N:미수신 */
	String smsrepYn;

	/** 휴대폰번호 */
	String mobilTxt;

	/** Y:기혼, N:미혼 */
	String marryYn;

	/** 결혼일자 */
	String marryYmd;

	/** 회사명 */
	String companynmTxt;

	/** 부서명 */
	String departTxt;

	/** 직위명 */
	String positionTxt;

	/** 회사전화번호 */
	String comptelTxt;

	/** Y:실명인증처리, N:실명인증미처리 */
	String nameauthYn;

	/** 실명인증일자 */
	Date nameauthDt;

	/** IPIN 인증여부 */
	String ipinConfirmYn;

	/** 가입일자 */
	Date joinDt;

	/** 탈퇴일시 */
	Date outDt;

	/** 탈퇴사유CD 공통코드 */
	String outCd;

	/** 탈퇴사유상세 */
	String outcommTxt;

	/** 추천인ID */
	String commId;

	/** SSO통합회원여부 */
	String ssomemberYn;

	/** 제3자정보제공동의여부 */
	String thirdcomYn;

	/** 공통코드에서 관리 */
	String jointypCd;

	/** 비밀번호문구 */
	String wordUpw;

	/** 회원아이피 */
	String userIp;

	/** 가입사이트 */
	String siteId;

	/** 부모동의여부 */
	String parentYn;

	/** 14세미만동의여부 */
	String agree14Yn;

	/** 임직원타입 */
	String memberType;

	/** 연령대구분_코드 */
	String birthGb;

	/** 우편번호구분 */
	String zipGb;

	/** 사이트통합여부 */
	String siteUnionYn;

	/** 사이트통합일자 */
	String siteUnionDay;

	/** 사이트통합구분 */
	String siteUnionMethod;

	/** 브랜드통합여부 */
	String brandUnionYn;

	/** 브랜드통합일자 */
	String brandUnionDay;

	/** 브랜드통합구분 */
	String brandUnionMethod;

	/** 후부SMS수신여부 */
	String fubuSmsYn;

	/** 후부메일수신여부 */
	String fubuMailYn;

	/** 엠비오SMS수신여부 */
	String mvioSmsYn;

	/** 엠비오메일수신여부 */
	String mvioMailYn;

	/** 가입동기 */
	String regWhy;

	/** 싱글인증고객여부 */
	String ssoMember;

	/** 싱글인증일 */
	String ssoUpdateDay;

	/** 실명인증결과 */
	String sciFlag;

	/** 실명인증중복 */
	String sciDup;

	/** 실명인증_CI */
	String sciCi;

	/** 실명인증버전 */
	String sciVer;

	/** 실명인증IPIN */
	String sciIpin;

	/** 싱글EPID */
	String ssoEpid;

	/** 회원메모 */
	String memoTxt;

	/** 우편번호 */
	String zipnumCd;

	/** 우편주소 */
	String zipaddrTxt;

	/** 상세주소 */
	String addressTxt;

	/** 주소타입 */
	String addType;

	/** 대표자 */
	String ownerNm;

	/** 사업자번호 */
	String biznumTxt;

	/** 비밀번호최종수정일 */
	Date lastpasswdDt;

	/** 강제탈퇴자 */
	String outstaffId;

	/** 재가입승인일시 */
	Date reenterDt;

	/** 탈퇴유형(1:일반탈퇴, 2:강제탈퇴) */
	String outType;

	/** 재가입승인자 */
	String reenterStaffId;

	/** 로그인실패횟수 */
	Integer loginFailCnt;

	/** 가입유형 */
	String joinType;

	/** 등록자 */
	String regstaffId;

	/** 등록일시 */
	Date regDt;

	/** 수정자 */
	String modstaffId;

	/** 수정일시 */
	Date modDt;

	/** 가입형태 1:웹 2:모바일 */
	String joinFlag;

	/** 탈퇴유형-마이그레이션 */
	String cutWhy;

	/** 홍보 마케팅 목적 개인정보 수집 및 이용 동의 */
	String marketingYn;

	/** 과거 패션피아통합회원여부(9월5이전) */
	String brandUnionYnAsis;

	/** 판매 제휴사ID */
	String affVendorId;

	/** 탈퇴아이피 */
	String outIp;

	/** 온라인사이트이용약관 */
	String onlinesiteYn;

	/** 멤버십카드이용약관 */
	String membercardYn;

	/** 개인정보보호를위한이용자동의사항 */
	String infomationYn;

	/** 개인정보의취급위탁동의 */
	String consignYn;

	/** 도로명주소 여부 */
	String roadaddrYn;

	/** 행정동 코드 */
	String dongCd;

	/**  */
	String lastUserIp;

	/**  */
	Date lastLoginDate;

	/**  */
	String lastSessionId;

	/** 페이스북회원 토큰 ID */
	String fbTokenId;

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
