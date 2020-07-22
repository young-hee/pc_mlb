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
public class MemberInformationSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 고객 일련번호
     */
    @JsonProperty("CID")
    private String cid;
    /**
     * 웹로그인용 아이디
     */
    @JsonProperty("ID")
    private String id;
    /**
     * 웹로그인용 암호
     */
    @JsonProperty("PASSWD")
    private String passwd;
    /**
     * 이름 (실명)
     */
    @JsonProperty("NAME")
    private String name;
    /**
     * 인증구분 (내외국인)
     * Y :14세미만, L:내국인, F:외국인
     */
    @JsonProperty("AUTH_TYPE")
    private String authType;
    /**
     * 실명인증 여부
     */
    @JsonProperty("REALNAME_YN")
    private String realnameYn;
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
     * 성별 (M, F)
     * M : 남자 F : 여자
     */
    @JsonProperty("GENDER")
    private String gender;
    /**
     * 이메일
     */
    @JsonProperty("EMAIL")
    private String email;
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
     * 전화 수신동의
     */
    @JsonProperty("RECV_PHONE")
    private String recvPhone;
    /**
     * 우편물 수신동의
     */
    @JsonProperty("RECV_POST")
    private String recvPost;
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
     * 가입경로
     * ATPOS, SMS.PHP, www.banilaco.com, POS, SIMPLE, SHOP, POS.FNF, POS.COLLECTED, N, SYRUP, WECHAT
     */
    @JsonProperty("JOIN_FROM")
    private String joinFrom;
    /**
     * 상태
     * R: 활성 D: 탈퇴 S: 휴면 B:블랙리스트 L:사용제한(마일리지)
     */
    @JsonProperty("STATUS")
    private String status;
    /**
     * 삭제일자
     */
    @JsonProperty("DROP_DATE")
    private String dropDate;
    /**
     * 전산실 기입용 필드
     */
    @JsonProperty("REMARK_IT")
    private String remarkIt;
    /**
     * 등록자 아이디
     */
    @JsonProperty("INPUT_USERID")
    private String inputUserid;
    /**
     * 등록자
     */
    @JsonProperty("INPUT_DATE")
    private String inputDate;
    /**
     * 수정자 아이디
     */
    @JsonProperty("UPDATE_USERID")
    private String updateUserid;
    /**
     * 수정일시
     */
    @JsonProperty("UPDATE_DATE")
    private String updateDate;
    /**
     * 실명인증방식
     * IPN : 아이핀 인증, UID : 주민번호 인증, CEL : 핸드폰 인증
     */
    @JsonProperty("AUTH_REALNAME")
    private String authRealname;
    /**
     * 신주소
     */
    @JsonProperty("ADDRESS_ROAD")
    private String addressRoad;
    /**
     * 레벨
     * 1 : Member, 2 : Foreigner, 4 : 직원, 5 : 계열사직원, 6 : MLB Story Maker, 7 : MLB Culture Project, 8 : MLB STYLE, 9 : MLBER Style, 13 : Discovery Team rood, 14 : Discovery People, 15 : Discovery Outdoor tip, 16 : Discovery Style
     */
    @JsonProperty("level")
    private String level;
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
     * 추천인 ID
     */
    @JsonProperty("RECOMMID")
    private String recommid;
    /**
     * 회원 상태값(정회원/블랙리스트 등등)
     * 1 : 정회원, 2 : 직원, 3 : 커뮤니티, 4 : 비회원, 5 : 블랙리스트, 6 : 탈퇴
     */
    @JsonProperty("STATUS_LEVEL")
    private String statusLevel;
    /**
     * 사번
     */
    @JsonProperty("EMP_NO")
    private String empNo;
    /**
     * 오프라인 가입일자
     */
    @JsonProperty("OFFLINE_JOIN_DATE")
    private String offlineJoinDate;
    /**
     * 오프라인 가입경로
     */
    @JsonProperty("OFFLINE_JOIN_FROM")
    private String offlineJoinFrom;
    /**
     * 회원 등급 리스트
     */
    @JsonProperty("MEMBER_GRADE_LIST")
    private List<MemberGradeInformationSDO> memberGradeList;
    /**
     * 자녀 목록
     */
    @JsonProperty("FAMILY_LIST")
    private List<FamilyInformationSDO> familyList;
}