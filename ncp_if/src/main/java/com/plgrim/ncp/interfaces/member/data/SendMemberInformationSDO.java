package com.plgrim.ncp.interfaces.member.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class SendMemberInformationSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 고객 일련번호
     */
    @JsonProperty("CID")
    private String cid;
    /**
     * API 타입
     * R:온라인회원정보 송신, C:회원정보생성, U:회원정보수정
     */
    @JsonProperty("API_TYPE")
    private String apiType;
    /**
     * 웹로그인용 아이디
     */
    @JsonProperty("ID")
    private String id;
    /**
     * 이름 (실명)
     */
    @JsonProperty("NAME")
    private String name;
    /**
     * 웹로그인용 암호
     */
    @JsonProperty("PASSWD")
    private String passwd;
    /**
     * 상태
     * R: 활성 D: 탈퇴 S: 휴면 B:블랙리스트 L:사용제한(마일리지)
     */
    @JsonProperty("STATUS")
    private String status;
    /**
     * 성별 (M, F)
     * M : 남자 F : 여자
     */
    @JsonProperty("GENDER")
    private String gender;
    /**
     * 생년월일
     */
    @JsonProperty("BIRTH_DAY")
    private String birthDay;
    /**
     * 생일 양력, 음력 구분(S,L)
     * S : 양력 L : 음력
     */
    @JsonProperty("BIRTH_CAL")
    private String birthCal;
    /**
     * 이메일
     */
    @JsonProperty("EMAIL")
    private String email;
    /**
     * 우편번호
     */
    @JsonProperty("ZIPCODE")
    private String zipcode;
    /**
     * 주소 앞부분
     */
    @JsonProperty("ADDRESS1")
    private String address1;
    /**
     * 주소 뒷부분
     */
    @JsonProperty("ADDRESS2")
    private String address2;
    /**
     * 집전화
     */
    @JsonProperty("PHONE_HOME")
    private String phoneHome;
    /**
     * 휴대전화
     */
    @JsonProperty("PHONE_MOBILE")
    private String phoneMobile;
    /**
     * 문자메시지 수신동의
     */
    @JsonProperty("RECV_SMS")
    private String recvSms;
    /**
     * 이메일 수신동의
     */
    @JsonProperty("RECV_EMAIL")
    private String recvEmail;
    /**
     * 수신동의일시
     */
    @JsonProperty("RECV_DATE")
    private String recvDate;
    /**
     * 가입일
     */
    @JsonProperty("JOIN_DATE")
    private String joinDate;
    /**
     * 최근 로그인 날짜
     */
    @JsonProperty("LAST_LOGIN")
    private String lastLogin;
    /**
     * 최근 로그인 IP 주소
     */
    @JsonProperty("LAST_LOGIN_IP")
    private String lastLoginIp;
    /**
     * 최근구매일
     */
    @JsonProperty("BUY_LAST")
    private String buyLast;
    /**
     * 아이핀 CI값
     */
    @JsonProperty("IPIN_CI")
    private String ipinCi;
    /**
     * 아이핀 DI값
     */
    @JsonProperty("IPIN_DI")
    private String ipinDi;
    /**
     * 인증구분 (내외국인)
     * Y :14세미만, L:내국인, F:외국인
     */
    @JsonProperty("AUTH_TYPE")
    private String authType;
    /**
     * 실명인증방식
     * IPN : 아이핀 인증, UID : 주민번호 인증, CEL : 핸드폰 인증
     */
    @JsonProperty("AUTH_REALNAME")
    private String authRealname;
    /**
     * 자녀 목록
     */
    @JsonProperty("FAMILY_LIST")
    private List<FamilyInformationSDO> familyList;
    /**
     * 가입디바이스 코드
     * MOBILE_APP, MOBILE_WEB, PC
     */
    @JsonProperty("JOIN_DVC_CD")
    private String joinDvcCd;
    /**
     * 가입언어코드
     * KOR, ENG
     */
    @JsonProperty("JOIN_LANG_CD")
    private String joinLangCd;
    /**
     * OS코드
     * ANDROID, IOS, PC, UNKNOWN
     */
    @JsonProperty("OS_CD")
    private String osCd;
    /**
     * 모바일 어플리케이션 구분 코드
     * FnF_APP
     */
    @JsonProperty("MOBILE_APP_SECT_CD")
    private String mobileAppSectCd;
    /**
     * 유입경로
     * WEB, EMAIL 등 SYS_INLOW 테이블이 등록된 명으로 보냄
     */
    @JsonProperty("INFLOW")
    private String inflow;
    /**
     * 회원 등급 리스트
     */
    @JsonProperty("MEMBER_GRADE_LIST")
    private List<MemberGradeInformationSDO> memberGradeList;
    /**
     * 처리결과코드
     */
    @JsonProperty("RESULT_CD")
    private String resultCd;
    /**
     * 처리결과메시지
     */
    @JsonProperty("RESULT_MSG")
    private String resultMsg;
}