package com.plgrim.ncp.cmp.member.fo;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthorGrp;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatUse;
import com.plgrim.ncp.biz.callcenter.data.CsoCnsltMemoExtendDTO;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MemberResultDTO;
import com.plgrim.ncp.biz.member.data.MemberSysGrpcoSearchDTO;
import com.plgrim.ncp.biz.member.data.MemberWebSelectResult;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MbrExtendResult;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.result.MemberSysGrpcoResult;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.member.data.MemberInformationSDO;

/**
 * 회원기본정보 command interface
 */
public interface MemberJoinFOComponent {

    /**
     * 회원정보 변경 MypageMemberController.updateMemberAction 에 있던 처리로직을 트랜잭션 처리를 위해
     * 옮겨옴
     *
     * @param request
     * @param dto
     * @param pk
     * @param locale
     * @param mbr
     * @param loginId
     * @
     */
    public void updateMemberTransaction(HttpServletRequest request, MemberFoDTO dto, SystemPK pk, Locale locale, Mbr mbr) throws Exception;

    /**
     * 비밀번호 변경.
     *
     * @param systemPK
     * @param dto
     * @param onlyErpUpdateFlag
     * @return boolean
     */
    public boolean updatePassword(SystemPK systemPK, MemberFoDTO dto, boolean onlyErpUpdateFlag) throws Exception;

    /**
     * 회원 탈퇴
     *
     * @param systemPK
     * @param mbr
     */
    public void deleteMember(SystemPK systemPK, Mbr mbr);
    
    /**
     * 회원 탈퇴 메일/SMS 발송
     *
     * @param systemPK
     * @param mbr
     */
    public void sendDeleteMailSms(SystemPK systemPK, Mbr mbr);

    /**
     * 회원 가입.
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public Mbr addMember(SystemPK systemPK, MemberFoDTO dto);

    /**
     * 회원 가입 메일/SMS 발송
     *
     * @param systemPK
     * @param dto
     */
    public void sendJoinMailSms(SystemPK systemPK, MemberFoDTO dto);
    
    /**
     * 회원정보 업데이트
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public MemberFoResult updateMember(SystemPK systemPK, MemberFoDTO dto) throws Exception;

    /**
     * 회원 비밀번호 초기화.
     * BO
     *
     * @param systemPK
     * @param dto
     * @param loginId
     */
    public void modifyMemberPasswordBy(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 회원 메모 저장.
     * BO
     *
     * @param systemPK
     * @param mbrNo
     * @param memoCont
     * @param loginId
     */
    public void addMemberMemoBy(SystemPK systemPK, String mbrNo, String memoCont, String loginId);

    /**
     * 회원 정보 변경.
     * BO
     *
     * @param systemPK
     * @param dto
     * @param loginId
     */
    public void modifyMemberBy(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 배송지 목록 저장.
     *
     * @param systemPK
     * @param mypageFoDTO
     * @param loginId
     * @param locale
     */
    public void registerDeliveryLocationBy(SystemPK systemPK, MypageFoDTO mypageFoDTO, String loginId, Locale locale);

    /**
     * 신규이벤트 쿠폰의 종료
     */
    public static final String WELCOME1_OFFER_EVENT = "NEW_JOIN_1000_DC";
    public static final String WELCOME2_OFFER_EVENT = "BRTHDY_CGRT_DC";
    public static final String BIRTH_OFFFER_EVENT = "PCH_AF_1MM_5PT_DC";
    public static final String ALL_OFFFER_EVENT = "ALL";

    /**
     * 회원 정보 가져오기.
     *
     * @return the member info
     * @ the exception
     * @since 2015. 3. 23
     */
    public SecurityUserDetail getMemberInfo(MemberFoDTO dto);

    /**
     * 회원정보 조회(회원정보 수정 시 대조용)
     *
     * @param mbrNo
     * @return
     */
    public Mbr selectMbrStatus(String mbrNo);

    /**
     * 회원 탈퇴 대상 여부 조회
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public MemberFoResult selectSecessionCheck(SystemPK systemPK, MemberFoDTO dto);

    /**
     * 회원가입 시 약관 조회
     *
     * @param sysStplatUse
     * @return
     */
    public List<SysStplat> selectJoinSysStplat(SysStplatUse sysStplatUse);

    /**
     * 약관정보 조회
     *
     * @param sysStplatUse
     * @return
     */
    public List<SysStplat> selectSysStplat(SysStplatUse sysStplatUse);


    /**
     * @param userDetail
     * @param mbrInfo
     * @return
     * @Method Name : compareMbrData
     * @작성일 : 2017. 1. 20.
     * @작성자 : user
     * @Method 설명 : 회원수정시 세션,DB데이터 비교
     * @
     */
    public boolean compareMbrData(String langCd, SecurityUserDetail userDetail, Mbr mbrInfo);

    /**
     * 비밀번호 확인
     *
     * @param dto
     * @return
     */
    public boolean checkMemberPassword(MemberFoDTO dto);

    /**
     * 회원 약관동의 정보 조회
     *
     * @param dto
     * @return
     */
    public MemberFoResult selectMbrSysStplat(MemberFoDTO dto);

    /**
     * 회원정보 조회
     *
     * @param mbrNo
     * @return
     */
    public Mbr getMbrInfoByMbrNo(String mbrNo);

    /**
     * 아이디 찾기
     *
     * @param dto
     * @return
     */
    public List<MemberFoResult> getMbrId(MemberFoDTO dto);

    /**
     * 회원가입 가능 여부 조회
     * 이름 / 이메일 / 핸드폰 번호 / 생년월일 중복 여부
     * FO / BO
     *
     * @param dto
     * @return
     */
    public int checkAddMember(MemberFoDTO dto);

    /**
     * ID 중복 체크.
     *
     * @param dto
     * @return
     */
    public boolean isCheckId(MemberFoDTO dto);

    /**
     * ERP에서 ID 중복 체크.
     *
     * @param dto
     * @return
     */
    public boolean isCheckIdByErp(SystemPK pk, MemberFoDTO dto) throws Exception;
    
    /**
     * 추천인 ID 유효성 체크
     *
     * @param dto
     * @return
     */
    public boolean isValidId(MemberFoDTO dto);

    /**
     * 추천인 ID 회원번호 조회
     *
     * @param dto
     * @return
     */
    public String selectRecommandMbrNo(MemberFoDTO dto);

    /**
     * 추천인 ID 조회
     *
     * @param dto
     * @return
     */
    public String selectRecommandMbrId(MemberBoDTO dto);


    /**
     * 이메일 중복 체크
     *
     * @param dto
     * @return
     */
    public boolean isCheckEmail(MemberFoDTO dto);

    /**
     * 이메일 중복 체크(본인 회원번호를 제외한 회원 중에서 체크)
     *
     * @param dto
     * @return
     */
    public boolean isCheckEmailOthers(MemberFoDTO dto);
    
    /**
     * 비회원 주문 인증 절차
     */
    public void nonMbrAuthentication();

    /**
     * 회원정보 조회
     * BO
     * @param mbr
     * @return
     */
    public Mbr selectMbr(Mbr mbr);

    /**
     * 회원 목록 조회.
     * BO
     * @param systemPK [설명]
     * @param dto      [설명]
     * @param loginId  [설명]
     * @return the member list
     * @ the exception
     * @since 2015. 4. 10
     */
    public MemberResultDTO getMemberList(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 회원 엑셀 다운로드 목록 조회.
     * BO
     *
     *  @param systemPK
     * @param dto
     * @param loginId
     * @return
     */
    public List<Map<String, Object>> getMemberListExcel(SystemPK systemPK, MemberBoDTO dto, String loginId)
    ;

    /**
     * 회원 간략 정보 조회.
     * BO
     *
     *  @param systemPK
     * @param dto
     * @param loginId
     * @return
     */
    public MemberBoResult getMemberSimple(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 회원 상세 정보 조회.
     * BO
     *
     *  @param systemPK
     * @param dto
     * @param loginId
     * @return
     */
    public MemberResultDTO getMember(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 회원 개인정보 변경이력 목록 조회.
     * BO
     *
     * @param systemPK
     * @param mpimh
     * @param loginId
     * @return
     */
    public MemberResultDTO getPersonalInfoModHistoryListForMember(SystemPK systemPK, MbrPsnlInfoModHist mpimh, String loginId);

    /**
     * 회원 메모 조회.
     * BO
     *
     * @param systemPK
     * @param mbrNo
     * @param loginId
     * @return
     */
    public List<CsoCnsltMemoExtendDTO> getCsoCnsltMemoList(SystemPK systemPK, String mbrNo, String loginId);

    /**
     * 회원 탈퇴 조건 조회.
     * BO
     *
     * @param systemPK [설명]
     * @param mbrNo    [설명]
     * @return the member for terminate
     * @ the exception
     * @since 2015. 5. 7
     */
    public MbrExtendResult getMemberForTerminate(SystemPK systemPK, String mbrNo, String loginId);

    /**
     * 정지 회원 목록 조회.
     * BO
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @param loginId  [설명]
     * @return the suspend member list
     * @ the exception
     * @since 2015. 4. 14
     */
    public MemberResultDTO getSuspendMemberList(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 정지 회원 엑셀 다운로드 목록 조회.
     * BO
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @param loginId  [설명]
     * @return the suspend member list excel
     * @ the exception
     * @since 2015. 4. 29
     */
    public List<Map<String, Object>> getSuspendMemberListExcel(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 탈퇴 회원 목록 조회.
     * BO
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return the suspend member list
     * @ the exception
     * @since 2015. 4. 14
     */
    public MemberResultDTO getSecessionMemberList(SystemPK systemPK, MemberBoDTO dto);

    /**
     * 탈퇴 회원 엑셀 다운로드 목록 조회.
     * BO
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return the secession member list excel
     * @ the exception
     * @since 2015. 4. 29
     */
    public List<Map<String, Object>> getSecessionMemberListExcel(SystemPK systemPK, MemberBoDTO dto);

    /**
     * 온라인클럽 회원 목록 조회.
     * BO
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return the online club member list
     * @ the exception
     * @since 2015. 6. 4
     */
    public PageImpl<MemberBoResult> getOnlineClubMemberList(SystemPK systemPK, MemberBoDTO dto);

    /**
     * 온라인클럽 회원 엑셀 다운로드 목록 조회.
     * BO
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return the online club member list excel
     * @ the exception
     * @since 2015. 6. 4
     */
    public List<Map<String, Object>> getOnlineClubMemberExcel(SystemPK systemPK, MemberBoDTO dto);

    /**
     * 회원 정보
     *
     * @param pk
     * @param mypageMtmFoDTO
     * @return
     */
    public List<MypageMtmFoResult> selectMemberMtmInfo(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 그룹사 조회
     * BO
     *
     * @param systemPK
     * @param memberSysGrpcoSearchDTO
     * @return
     */
    public Page<MemberSysGrpcoResult> selectSysGrpcoList(SystemPK systemPK, MemberSysGrpcoSearchDTO memberSysGrpcoSearchDTO);

    /**
     * 권한 그룹 정보 가져오기
     * BO
     *
     * @param loginId
     * @return
     * @
     */
    public SysAuthorGrp selectAdminAuthorGrp(String loginId);

    /**
     * P포인트회원 목록 조회.
     * BO
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @param loginId  [설명]
     * @return the member list
     * @ the exception
     * @since 2015. 4. 10
     */
    public MemberResultDTO getPurpleCoinMemberList(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * P포인트회원 엑셀 다운로드 목록 조회.
     * BO
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return List [설명]
     * @ the exception
     * @since 2015. 4. 24
     */
    public List<Map<String, Object>> getPurpleCoinMemberListExcel(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 멤버십 등급을 조회한다.
     */
    public String selectErpGrdCd(SystemPK systemPK, String mbrNo);

    /**
     * 회원 SMS 발송 내역 건수 조회.
     *
     * @param dto [설명]
     * @return List [설명]
     * @since 2015. 5. 4
     */
    public long selectSmsListCountForMember(MemberBoDTO dto);

    /**
     * 회원 SMS 발송 내역 조회.
     *
     * @param dto [설명]
     * @return List [설명]
     * @since 2015. 5. 4
     */
    public List<MemberWebSelectResult> selectSmsListForMember(MemberBoDTO dto);

    /**
     * 회원 SMS 발송 내역 엑셀 조회.
     *
     * @param dto [설명]
     * @return List [설명]
     * @since 2015. 5. 4
     */
    public List<Map<String, Object>> selectSmsExcelForMember(MemberBoDTO dto);

    /**
     * 회원 Mail 발송 내역 건수 조회.
     *
     * @param dto [설명]
     * @return List [설명]
     * @since 2015. 5. 4
     */
    public long selectEMailListCountForMember(MemberBoDTO dto);

    /**
     * 회원 Mail 발송 내역 조회.
     *
     * @param dto [설명]
     * @return List [설명]
     * @since 2015. 5. 4
     */
    public List<MemberWebSelectResult> selectEMailListForMember(MemberBoDTO dto);

    /**
     * 회원 Mail 발송 내역 엑셀 조회.
     *
     * @param dto [설명]
     * @return List [설명]
     * @since 2015. 5. 4
     */
    public List<Map<String, Object>> selectEMailExcelForMember(MemberBoDTO dto);

    public boolean selectMysizeExistYn(String mbrNo, String mbrSizeClfcCd);

    public boolean checkMobilNoAsId(MemberFoDTO memberFoDTO);

    
    /**
     * 회원 인증 정보 조회(최근 1건)
     *
     * @param MbrNo [설명]
     * @return MbrCrtfc [설명]
     * @since 2018. 6. 12
     */
    public MbrCrtfc selectMemberCertification(String mbrNo);
    
    /**
     * ERP 통합회원정보 조회
     * 
     * @param systemPK
     * @param mbr
     * @return MemberInformationSDO
     */
    public MemberInformationSDO getMemberInformation(SystemPK systemPK, Mbr mbr) throws Exception;
    
	/**
	 * 유입 경로 조회
	 * 
	 * @param sysInflow
	 * @return SysInflow
	 */
	public SysInflow selectMbrSysInflow(SysInflow sysInflow);
	
	/**
	 * 로그인 실패횟수 UPDATE
	 * 
	 * @param mbr
	 * @return int
	 */
	public int updateLoginFailCount(Mbr mbr);
	
	/**
	 * 휴면 회원 해제 처리
	 * 
	 * @param request
	 * @param systemPK
	 * @param memberInformationSDO
	 * @return rtn	00:성공, 99:실패
	 */
    public String processReleaseDrmncyMember(HttpServletRequest request, SystemPK systemPK, MemberInformationSDO memberInformationSDO);
    
    /**
     * 본인인증 sms
     * - 외부 인터페이스 이용
     * 
     * @param birthday
     * @param gender
     * @param mbrNm
     * @param mobileCo
     * @param mobileNumber
     */
    public Map<String, String> sendCertSms(SystemPK systemPK, String birthday, String gender, String mbrNm, String mobileCo, String mobileNumber);

    /**
     * 본인인증 sms check
     * - 외부 인터페이스 이용
     * 
     * @param systemPK
     * @param REQ_SEQ
     * @param RES_SEQ
     * @param sAuthNo
     * @param birthday
     * @param gender
     * @param mbrNm
     * @param mobileCo
     * @param mobileNumber
     * @param frgnrYn
     * @return
     */
    public Map<String, String> sendCertSmsCheck(SystemPK systemPK, String REQ_SEQ, String RES_SEQ, String sAuthNo, String birthday, String gender, String mbrNm, String mobileCo, String mobileNumber, String frgnrYn);
    
    /**
     * request에서 requestURL을 가져와서 http://, https:// 및 파라미터 부분을 제외하고 반환.
     * - AWS 내부 도메인으로 나올 경우는 ncp_base에서 가져옴.
     * 
     * @param request
     * @param systemPK
     * @return
     */
    public String getRequestUrlForAddMember(HttpServletRequest request, SystemPK systemPK);
    
    /**
     * 통신사 약관 조회
     * 
     * @param model
     * @param systemPK
     * @param opt
     * @return
     */
    public Model getMccStplat(Model model, SystemPK systemPK, String opt) throws Exception;
}
