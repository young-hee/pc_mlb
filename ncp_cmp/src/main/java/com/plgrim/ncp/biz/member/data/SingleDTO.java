package com.plgrim.ncp.biz.member.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class SingleDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7444067757023952468L;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	String epReturncode;	     	//1:정상  ,  0:error
	String epLoginid;	       	//로그인ID
	String epCompid;						//회사코드
	String epSocialid;					//주민번호
	String epDeptid;						//부서코드
	String epGrdid;						//직급코드
	String epSecid;						//보안등급
	String epGrplst;						//가상그룹목록
	String epPassword;					//비밀번호  (사용하지않음)
	String epMailhost;					//메일서버
	String epSabun;						//사번
	String epLang;							//사용언어  ex)  K
	String epLocale;						//언어정보  ex)  koKR.EUC-KR  
	String epUserdn;						//사용자DN
	String epUserlevel;				//사용자등급
	String epUserstatus;				//임직원상태
	String epComptel;					//회사전화번호
	String epDm;							//Dm서버정보
	String epSorgid;						//총괄코드
	String epBusid;						//사업장코드
	String epRegid;						//지역코드
	String epMail;							//메일어드레스
	String epUserid;						//Unique  ID
	String epPrivlst;					//권한목록
	String epStrhttp;					//STRHTTP  ????
	String epDcomp;						//원소속/파견소속  구분
	String epUserlocation;			//사용자  지역
	String epIcode;						//인터넷  로그인  여부
	String epRoleid;						//역할  코드
	String epServicecode;			//서비스  코드
	String epDcompcode;				//원/파견소속  정보
	String epTimezone;					//TIMEZONE
	String epEpftp;						//Ftp  사용여부
	String epListcount;				//출력  목록수
	String epMailrecptcnt;			//메일수신처숫자
	String epSummertimeyn;			//써머타임  여부
	String epReloginyn;				//MIS  재로그인유무
	String epOminuseyn;				//omin  사용여부
	String epPassworduseyn;		//패스워드사용여부
	String epLevelaware;				//메신져  사용레벨
	String epMessagecode;			//값  없음
	String epUsername;					//사용자명
	String epCompname;					//회사명
	String epDeptname;					//부서명
	String epGrdname;					//직급명
	String epSorgname;					//총괄명
	String epBusname;					//사업장명
	String epRegname;					//지역명
	String epUsernameEn;			//영문  사용자명
	String epNickname;					//닉네임
	String epCompnameEn;			//영문  회사명
	String epDeptnameEn;			//영문  부서명
	String epGrdnameEn;				//영문  직급명
	String epSorgnameEn;			//영문  총괄명
	String epPreferredlanguage;//	사용자기본언어
	String epMcode;						//메타프레임사용여부
	String epScreensize;				//본문입력창크기
	String epMobile;						//핸드폰  번호
	String epBase64Yn;					//Base64인코딩여부
	String epCompressyn;				//압축여부
	String epGlobalposition;		//거점분산정보
	String epDomain;						//접속도메인
	String epLcdm;							//로컬저장함사용유무
	String epLighttrayyn;			//LIGHT버젼  구분
	String epLoginip;					//로그인  IP
	String EpLogintimeformis;	//로그인시간(GMT)
	String EpWebeditor;				//웹에디터  사용여부
	String EpReturnurl;				//로그아웃URL
	String EpChpwd;						//패스워드변경
	String EpServiceleve;			//서비스별  권한
	String EpAttachsize;				//첨부파일크기
	String EpNativeyn;					//현채인여부

	String plgrimUserId;				// 싱글 EPID에 대한 PLGRIM USER ID

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
