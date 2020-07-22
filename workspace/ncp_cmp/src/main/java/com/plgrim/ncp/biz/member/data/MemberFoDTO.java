package com.plgrim.ncp.biz.member.data;


import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class MemberFoDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields...
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2160644988915724312L;

	/**
	 * 엔티티
	 */
	Mbr mbr;
	
	MbrCrtfc mbrCrtfc;
	
	MbrStplatAgr mbrStplatAgr;
	
	List<MbrStplatAgr> mbrStplatAgrs;
	
	/**
	 * 엔티티 끝
	 */
	
	String account_number;
	
	String account_holder;
	
	String account_bank;
	
	String mobileNumber;
	
	String telNumber;
	
	String otelNumber;
	
	/**
	 * 마이사이즈 몸무게
	 */
	String weight;
	
	/**
	 * 마이사이즈 키
	 */
	String height;
	
	/**
	 * 마이사이즈 체형
	 */
	String body;
	
	// 마이사이즈 리스트
	List<MemberSizeClfcDTO> mySizeList;
	
    /**
     * 약관동의 관련 필드
     */
	List<String> stplatCd;
	List<String> stplatIemAgrYn;
	
	//온라인사이트 이용약관
	String onlineSiteUsefStplat;
	//개인정보 보호를 위한 이용자 동의사항
	String psnlInfoPrtcUsefAgr;
	//개인정보 수집 이용에 대한 동의
	String psnlInfoColctUsefAgr;
	//개인정보의 취급위탁 동의
	String psnlInfoTrtmntCnsgnAgr;
	//홍보 마케팅 목적 개인정보 수집 및 이용 동의
	String marktPsnlInfoColctUsefAgr;
	//개인정보 제 3자 제공 안내
	String psnlInfoThprOfferDetl;
	//멤버쉽이용약관
	String mbshUsefStplat;
	//홍보 마케팅 목적 개인정보 수집 및 이용 동의
	String marktPsnlInfoColctUsefAgrYn;
	 /**
	  * 약관동의 관련 필드 끝
	  */
	
	/**
	 * 싱글 메일
	 */
	String singleEmail;
	String singleEmailDomain;
	
	/**
	 * 비밀번호 변경
	 */
	String newPassword;
	

	/**
	 * 기본 배송지 설정
	 */
	String baseDlvYn;
	
	String dateStart;
	
	String dateEnd;
	
	String srchMonth;
	
	String authOption;
	
	String recommandId;

	//오프라인 탈퇴
	String offSecesion;
	
	/** 기존 회원 이메일. */
	private String orgMbrEmail;

	/** 카페24 회원 정보 ID */
	private String orgMbrId;
	
	/** ID연계정보 (페이스북, 네이버 간편로그인, 삼성패스) */
	private MbrIdCntc mbrIdCntc;
	
	/** 페이스북 accessToken */
	private String fbAccessToken;
	
	private String mbrNo;
	
	private String mbrStplatCd;

	private String ageChk;

	private String srv;
	
	/**  
	 * 몰에서 멤버십 전환가입시
	 * 온라인몰 ERP 전달값 구분용(생일정보)
	 * Y:생일정보 전송
	 */
	private String BirthInfoYN;

	/**
	 *
	 ㅁ. 정보성 알림 수신 유형 : INFOR_NTCN_RECPTN_TP_CD
	 >. 알림톡 : NTCN_TAK
	 >. 문자 : CHAR
	 */
	private String inforNtcnRecptnTpCd;
	
	/**
	 * 글로벌몰 통합회원여부
	 */
	private String globalUnity;

	/**
	 * 회원가입 유형
	 * 멤버십 회원 : true
	 * 온라인 회원 : false
	 */

	boolean signupType;

	private String fbreq;

	private String mbrIdCntcCd;
	
	/**
	 * 자녀 목록
	 */
	private List<MemberChildDTO> childrenList;
	
	private String familyExceptionFlag;
	
	private String checkPasswordFlag;
	
    /**
     * 회원 등급
     * DISCOVERER:100, EXPLORER:200, ADVENTURERE:300, TRAVELER:400
     * ERP에서 받아온 회원의 등급
     */
	private String memberGrade;
	
	private String loginActionParam;
	
	private String secessMallId;
	
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
