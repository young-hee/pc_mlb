package com.plgrim.ncp.biz.member.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.inf.InfMbrGrdInfoRecptn;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntcExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginLog;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthorGrp;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatUse;
import com.plgrim.ncp.biz.helpdesk.data.HistoryInfoFoDTO;
import com.plgrim.ncp.biz.helpdesk.result.HistoryInfoFoResult;
import com.plgrim.ncp.biz.member.data.MbrExtendDTO;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MemberSysGrpcoSearchDTO;
import com.plgrim.ncp.biz.member.data.MemberWebSelectResult;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.result.MbrExtendResult;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.result.MemberSysGrpcoResult;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * 회원기본정보 select repository..
 */
@Repository
public class MemberBaseSelectRepository extends AbstractRepository  {
 
	/**
     * 로그인 회원 정보 조회.
     *
     * @param mbr [설명]
     * @return Mbr [설명]
     */
    public Mbr login(MemberFoDTO dto){
    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.login",dto);
    }
    
    /**
     * @param mbrNo
     * @return
     */
    public Mbr selectMbrStatus(String mbrNo ){
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectMbrStatus", mbrNo);
	}
    
    /**
     * @param dto
     * @return
     */
    public MemberFoResult selectSecessionCheck(MemberFoDTO dto){
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectSecessionCheck", dto);
	}
    
    /**
	 * 회원 탈퇴 조건 조회.
	 *
	 * @param mbr [설명]
	 * @return Mbr extend result [설명]
	 */
    public MbrExtendResult selectMemberForSecession(Mbr mbr) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.secession.selectMemberForSecession", mbr);
	}
    
    /**
     * ERP번호로 회원목록조회
     * 
     * @param erpCstmrNo
     * @return List<Mbr>
     */
    public List<Mbr> selectMemberByErpCstmrNo(String erpCstmrNo) {
    	return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectMemberByErpCstmrNo", erpCstmrNo);
    }
    
    /**
     * @param historyInfoFoDTO
     * @param pageParam
     * @return
     * @
     */
    public Page<HistoryInfoFoResult> selectMyLoginHistory(HistoryInfoFoDTO historyInfoFoDTO, PageParam pageParam) {
	    
		RepositoryHelper.makePageEntityIndex(historyInfoFoDTO, pageParam);
		List<HistoryInfoFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyLoginHistory", historyInfoFoDTO);
		
		long totalRow = selectCountHistory(historyInfoFoDTO);
		
		return new PageImpl<HistoryInfoFoResult>(results, pageParam.getPageable(), totalRow);
    }
    
    /**
     * @param historyInfoFoDTO
     * @return
     * @
     */
    public long selectCountHistory(HistoryInfoFoDTO historyInfoFoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectCountHistory",historyInfoFoDTO);
	}
    
    /**
     * @param sysStplatUse
     * @return
     * @
     */
    public List<SysStplat> selectJoinSysStplat(SysStplatUse sysStplatUse)  {
	    // TODO Auto-generated method stub
	    return getSession1().selectList("com.plgrim.ncp.biz.mbr.agr.selectJoinStplatList", sysStplatUse);
    }

    
    /**
	 * 회원 조회 - ERP 번호.
	 *
	 * @param mbrNo [설명]
	 * @return String [설명]
	 */
    public String selectMemberErpNo(String mbrNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectMemberErpNo", mbrNo);
	}
    
    /**
     * @param dto
     * @return
     */
    public MemberFoResult getSingleMbr(MemberFoDTO dto) {
	    // TODO Auto-generated method stub
	    return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectSingleMbr", dto);
    }
    
    /**
     * @param mbrNo
     * @param idCntcTpCd
     * @return
     */
    public MbrIdCntc selectNaverUdterIdInfo(String mbrNo, String idCntcTpCd) {

    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("mbrNo", mbrNo);
    	paramMap.put("idCntcTpCd", idCntcTpCd);

    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectNaverUdterIdInfo", paramMap);
    }

	/**
	 * @param mbrIdCntcExtend
	 * @return
	 */
	public MbrIdCntc selectNaverUserInfo(MbrIdCntcExtend mbrIdCntcExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectNaverUserInfo", mbrIdCntcExtend);
	}

    /**
     * @param mbrEmail
     * @param mallId
     * @return
     */
    public List<MemberFoResult> getMbrIdMbrIdCntc(String mbrEmail, String mallId) {
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("mbrEmail", mbrEmail);
    	paramMap.put("mallId", mallId);
    	
    	return getSession1().selectList("com.plgrim.ncp.biz.mbr.getMbrIdMbrIdCntc", paramMap);
    } 
    
    /**
     * @param toknId
     * @param mallId
     * @param maskingYn
     * @param idCntcTpCd
     * @return
     */
    public String findPlgrimshopLoginIdByNaverUserId(String toknId, String mallId, boolean maskingYn, String idCntcTpCd){
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("toknId", toknId);
    	paramMap.put("mallId", mallId);
    	paramMap.put("idCntcTpCd", idCntcTpCd);
    	
    	if (maskingYn) {
    		paramMap.put("maskingYn", "Y");
    	} else {
    		paramMap.put("maskingYn", "N");
    	}
    	
    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.findPlgrimshopLoginIdByNaverUserId", paramMap);
    }

	/**
	 * @param dto
	 * @return
	 */
    public int checkAddMember(MemberFoDTO dto) {
	    // TODO Auto-generated method stub
	    return getSession1().selectOne("com.plgrim.ncp.biz.mbr.checkAddMember", dto);
    }
	
	/**
	 * @param dto
	 * @return
	 */
    public int isCheckId(MemberFoDTO dto) {
	    // TODO Auto-generated method stub
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.isCheckId", dto);
    }

    public int checkMobilNoAsId(MemberFoDTO dto){
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.checkMobilNoAsId", dto);
	}

    public String isValidId(MemberFoDTO dto) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectMbrNo", dto);
	}

    /**
     * 추천인 회원NO 조회
     * 
     * @param dto
     * @return
     */
	public String selectRecommandMbrNo(MemberFoDTO dto){
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectRecommandMbrNo", dto);
	}

	/**
	 * 추천인 회원ID 조회
	 * 
	 * @param dto
	 * @return
	 */
	public String selectRecommandMbrId(MemberBoDTO dto){
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectRecommandMbrId", dto);
	}

	/**
	 * @param dto
	 * @return
	 */
	public int isCheckEmail(MemberFoDTO dto) {
	    // TODO Auto-generated method stub
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.isCheckEmail", dto);
    }
	
	/**
	 * @param dto
	 * @return
	 */
	public int isCheckEmailOthers(MemberFoDTO dto) {
	    // TODO Auto-generated method stub
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.isCheckEmailOthers", dto);
    }
	
	/**
	 * @param dto
	 * @return
	 */
	public MemberFoResult getMbrPassword(MemberFoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.getMbrPassword", dto);
    }
    
    /**
     * 회원 인증 정보 조회.
     *
     * @param dto [설명]
     * @return Mbr crtfc [설명]
     */
    public MemberFoResult selectMemberForPassword(MemberFoDTO dto) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.crt.selectMemberForPassword", dto);
    }
    
    /**
     * @param facebookUserId
     * @param mallId
     * @param maskingYn
     * @return
     */
    public String findPlgrimshopLoginIdByFacebookUserId(String facebookUserId, String mallId, boolean maskingYn){
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("facebookUserId", facebookUserId);
    	paramMap.put("mallId", mallId);
    	if (maskingYn) {
    		paramMap.put("maskingYn", "Y");
    	} else {
    		paramMap.put("maskingYn", "N");
    	}
    	
    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.findPlgrimshopLoginIdByFacebookUserId", paramMap);
    }
    
    /**
     * @param mbrNo
     * @return
     * @
     */
    public List<MbrStplatAgr> selectMbrStplatAgrList(String mbrNo)  {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.agr.selectMbrStplatAgrList", mbrNo);
	}
    
    /**
     * @param dto
     * @return
     */
    public MemberFoResult selectMemberForErp(MemberFoDTO dto){
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.erp.selectMemberForErp", dto);
	}
    
    /**
	 * 회원 간략 정보 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 3. 24
	 */
    public MemberBoResult selectMemberSimple(MemberBoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectMemberSimple", dto);
	}
    
    public List<SysStplat> selectSysStplat(SysStplatUse sysStplatUse)  {
	    // TODO Auto-generated method stub
	    return getSession1().selectList("com.plgrim.ncp.biz.mbr.agr.selectStplatList", sysStplatUse);
    }
    
    public List<MemberFoResult> getMbrId(MemberFoDTO dto){
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.getMbrId", dto);
	}
	
    public MbrIdCntc selectFaceBookUserInfo(String mbrNo, String mbrId, String mallId) {
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("mbrNo", mbrNo);
    	paramMap.put("mbrId", mbrId);
    	paramMap.put("mallId", mallId);
    	
    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectFaceBookUserInfo", paramMap);
    }
	
	public long selectNaverConnectCount(String mbrNo, String idCntcTpCd) {

    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("mbrNo", mbrNo);
    	paramMap.put("idCntcTpCd", idCntcTpCd);

    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectNaverConnectCount", paramMap);
    }
	
	public String checkMemberPassword(MemberFoDTO dto) {
	    // TODO Auto-generated method stub
	    return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectPassword", dto);
    }

	public MemberFoResult selectMbrSysStplat(MemberFoDTO dto)  {
	    // TODO Auto-generated method stub
	    return getSession1().selectOne("com.plgrim.ncp.biz.mbr.agr.selectMbrSysStplat", dto);
    }
	
	public Mbr getMbrInfoByMbrNo(String mbrNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectMbrInfoByMbrNo", mbrNo);
	}
	
	/**
	 * 회원 상세 조회.
	 *
	 * @param mbr the Mbr
	 * @return the Mbr
	 * @throws SQLException the SQL exception
	 */
	public Mbr selectMbr(Mbr mbr)  {
		Mbr result = getSession1().selectOne("com.plgrim.ncp.base.selectMbr", mbr);
		return result;
	}

    /**
     * 회원 목록 건수 조회.
     *
     * @param memberBoDTO [설명]
     * @return Long [설명]
     * @since 2015. 3. 24
     */
    public long selectMemberListCount(MemberBoDTO memberBoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectMemberListCount", memberBoDTO);
	}
	
	/**
	 * 회원 목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 3. 24
	 */
    public List<MemberBoResult> selectMemberList(MemberBoDTO memberBoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectMemberList", memberBoDTO);
	}
    
	/**
	 * 회원 엑셀 다운로드 목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 4. 24
	 */
    public List<Map<String, Object>> selectMemberListExcel(MemberBoDTO memberBoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectMemberListExcel", memberBoDTO);
	}
    
	/**
	 * 회원 상세 조회.
	 *
	 * @param dto [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
    public MemberBoResult selectMember(MemberBoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectMember", dto);
	}
    
	/**
	 * 회원 조회 - FOR CS.
	 *
	 * @param dto [설명]
	 * @return Mbr extend result [설명]
	 * @since 2015. 5. 7
	 */
    public MbrExtendResult selectMemberForCS(MbrExtendDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectMemberForCS", dto);
	}
    
    /**
	 * 정지 회원 건수 조회.
	 *
	 * @param dto [설명]
	 * @return Long [설명]
	 * @since 2015. 4. 13
	 */
    public long selectSuspendMemberListCount(MemberBoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectSuspendMemberListCount", dto);
	}
    
	/**
	 * 정지 회원 목록 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 4. 13
	 */
    public List<MemberBoResult> selectSuspendMemberList(MemberBoDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectSuspendMemberList", dto);
	}
    
	/**
	 * 정지회원 엑셀 다운로드 목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 4. 24
	 */
    public List<Map<String, Object>> selectSuspendMemberListExcel(MemberBoDTO memberBoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectSuspendMemberListExcel", memberBoDTO);
	}
    
	/**
	 * 탈퇴 회원 건수 조회.
	 *
	 * @param dto [설명]
	 * @return Long [설명]
	 * @since 2015. 4. 13
	 */
    public long selectSecessionMemberListCount(MemberBoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectSecessionMemberListCount", dto);
	}
    
	/**
	 * 탈퇴 회원 목록 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 4. 13
	 */
    public List<MemberBoResult> selectSecessionMemberList(MemberBoDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectSecessionMemberList", dto);
	}
    
	/**
	 * 탈퇴 회원 엑셀 다운로드 목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 4. 24
	 */
    public List<Map<String, Object>> selectSecessionMemberListExcel(MemberBoDTO memberBoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectSecessionMemberListExcel", memberBoDTO);
	}
    
	/**
	 * 온라인클럽 회원 건수 조회.
	 *
	 * @param dto [설명]
	 * @return Long [설명]
	 * @since 2015. 6. 4
	 */
    public long selectOnlineClubMemberListCount(MemberBoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectOnlineClubMemberListCount", dto);
	}
    
	/**
	 * 온라인클럽 회원 목록 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 6. 4
	 */
    public List<MemberBoResult> selectOnlineClubMemberList(MemberBoDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectOnlineClubMemberList", dto);
	}
    
	/**
	 * 온라인클럽 회원 엑셀 다운로드 목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 4
	 */
    public List<Map<String, Object>> selectOnlineClubMemberExcel(MemberBoDTO memberBoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectOnlineClubMemberExcel", memberBoDTO);
	}
    
    public String getErpGrdCd(String mbrNo ){
		
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("mbrNo", mbrNo);
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.getErpGrdCd", paramMap);
	}
	
	/**
     * 권한 그룹 정보 가져오기
     * 
     * @param loginId
     * @return
     * @
     */
    public SysAuthorGrp selectAdminAuthorGrp(String loginId) {
        return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectAdminAuthorGrp", loginId);
    }

	/**
	 * 회원 인증 여부 select
	 * @param mbr
	 * @return
	 * @
	 */
    public Mbr getMbr(Mbr mbr)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectMbr", mbr);
	}

	

	/**
	 * 회원 엑셀 다운로드 목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 4. 24
	 */
	public List<Map<String, Object>> selectPurpleCoinMemberListExcel(MemberBoDTO memberBoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectPurpleCoinMemberListExcel", memberBoDTO);
	}

	/**
	 * 그룹사 조회 건수
	 * @param memberSysGrpcoSearchDTO
	 * @return
	 */
	public long selectSysGrpcoListCount(MemberSysGrpcoSearchDTO memberSysGrpcoSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.sys.grpco.selectSysGrpcoListCount", memberSysGrpcoSearchDTO);
	}
	
	/**
	 * 그룹사 조회
	 * @param memberSysGrpcoSearchDTO
	 * @return
	 */
	public List<MemberSysGrpcoResult> selectSysGrpcoList(MemberSysGrpcoSearchDTO memberSysGrpcoSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.sys.grpco.selectSysGrpcoList", memberSysGrpcoSearchDTO);
	}
	
	/**회원 정보*/
	public List<MypageMtmFoResult> selectMemberMtmInfo(MypageMtmFoDTO mypageMtmFoDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMemberMtmInfo", mypageMtmFoDTO);
    }
	

	/**
	 * 회원 SMS 발송 내역 건수 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public long selectSmsListCountForMember(MemberBoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.member.others.selectSmsListCountForMember", dto);
	}
	
	/**
	 * 회원 SMS 발송 내역 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public List<MemberWebSelectResult> selectSmsListForMember(MemberBoDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.member.others.selectSmsListForMember", dto);
	}
    
	/**
	 * 회원 SMS 발송 내역 엑셀 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public List<Map<String, Object>> selectSmsExcelForMember(MemberBoDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.member.others.selectSmsExcelForMember", dto);
	}
    
	/**
	 * 회원 Mail 발송 내역 건수 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public long selectEMailListCountForMember(MemberBoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.member.others.selectEMailListCountForMember", dto);
	}
    
	/**
	 * 회원 Mail 발송 내역 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public List<MemberWebSelectResult> selectEMailListForMember(MemberBoDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.member.others.selectEMailListForMember", dto);
	}
    
	/**
	 * 회원 Mail 발송 내역 엑셀 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public List<Map<String, Object>> selectEMailExcelForMember(MemberBoDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.member.others.selectEMailExcelForMember", dto);
	}

    public int selectMysizeExistYn(MbrSizeClfc mbrSizeClfc) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.size.selectMysizeCnt", mbrSizeClfc);
	}

	/**
	 * 회원 사이즈 입력 정보 조회
	 * @param mbrNo
	 * @return
	 */
	public int selectMbrSizeAllInputCount(String mbrNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.size.selectMbrSizeAllInputCount", mbrNo);
	}

	/**
	 * 회원 등급 조회
	 *
	 * @param mbrGrd
	 * @return mbrGrd
	 */
	public MbrGrd selectMbrGrd(MbrGrd mbrGrd) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectMbrGrd", mbrGrd);
	}

	/**
	 * 회원 로그인 로그 최신 로그인 1건 조회.
	 * 
	 * @param mbrLoginLog
	 * @return
	 * @throws Exception
	 */
	public String selectMbrLoginLog(MbrLoginLog mbrLoginLog) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.login.log.selectMbrLoginLog", mbrLoginLog);
	}
	
	/**
	 * 탈퇴한 회원 정보 조회
	 * 
	 * @param mbr
	 * @return MemberFoResult
	 * @throws Exception
	 */
	public MemberFoResult selectSecessionMbrInfo(Mbr mbr) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectSecessionMbrInfo", mbr);
	}
	
	/**
	 * 유입 경로 조회
	 * 
	 * @param sysInflow
	 * @return SysInflow
	 */
	public SysInflow selectMbrSysInflow(SysInflow sysInflow) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectMbrSysInflow", sysInflow);
	}
	
	
	/**
	 * ERP에서 받은 회원 등급 변경정보 조회
	 * @return
	 */	
	public List<InfMbrGrdInfoRecptn> selectMemberGradeInfoCp(InfMbrGrdInfoRecptn infMbrGrdInfoRecptn) {		
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectMemberGradeInfoCp", infMbrGrdInfoRecptn);
	}
	
	/**
	 * 인터페이스 회원 등급 변경정보 수신 삭제.
	 *
	 * @param infMbrGrdInfoRecptn the InfMbrGrdInfoRecptn
	 * @return the InfMbrGrdInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfMbrGrdInfoRecptnCp(InfMbrGrdInfoRecptn infMbrGrdInfoRecptn) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.deleteInfMbrGrdInfoRecptnCp", infMbrGrdInfoRecptn);
	}
	
}
