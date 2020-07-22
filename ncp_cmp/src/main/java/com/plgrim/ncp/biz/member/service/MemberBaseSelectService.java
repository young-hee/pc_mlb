package com.plgrim.ncp.biz.member.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.inf.InfMbrGrdInfoRecptn;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefPymntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginLog;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPntIntrlckHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRecomendrRecomendeCnnc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthorGrp;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatUse;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.MemberModLcSectCd;
import com.plgrim.ncp.base.repository.mbr.MbrBnefPymntHistRepository;
import com.plgrim.ncp.base.repository.mbr.MbrPntIntrlckHistRepository;
import com.plgrim.ncp.base.repository.mbr.MbrRecomendrRecomendeCnncRepository;
import com.plgrim.ncp.base.repository.mbr.MbrRepository;
import com.plgrim.ncp.base.repository.mbr.MbrStplatAgrRepository;
import com.plgrim.ncp.biz.helpdesk.data.HistoryInfoFoDTO;
import com.plgrim.ncp.biz.helpdesk.result.HistoryInfoFoResult;
import com.plgrim.ncp.biz.member.data.MbrExtendDTO;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MemberSysGrpcoSearchDTO;
import com.plgrim.ncp.biz.member.data.MemberWebSelectResult;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.exception.MemberBoException;
import com.plgrim.ncp.biz.member.repository.MemberBaseSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberBenefitSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberPersonalInfoCommandRepository;
import com.plgrim.ncp.biz.member.result.MbrExtendResult;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.result.MemberSysGrpcoResult;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원기본정보 select service..
 */
@Service
@Slf4j
public class MemberBaseSelectService extends AbstractService {
 
	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	@Autowired
	private MemberBaseSelectRepository memberBaseSelectRepository;
	
	@Autowired
	private MemberPersonalInfoCommandRepository memberPersonalInfoCommandRepository;
	
	@Autowired
	private MemberBenefitSelectRepository memberBenefitSelectRepository;
	
	/** 회원 포인트 연동 이력 Repository. */
	@Autowired
	private MbrPntIntrlckHistRepository mbrPntIntrlckHistRepository;
	
	/** 회원 혜택 지급 이력 Repository.	 */
	@Autowired
	private MbrBnefPymntHistRepository mbrBnefPymntHistRepository;
	
	/** 회원 추천인 피추천인 연결 Repository */
	@Autowired
	private MbrRecomendrRecomendeCnncRepository mbrRecomendrRecomendeCnncRepository;

	@Autowired
	private MbrRepository mbrRepository;

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	@Autowired
	MbrStplatAgrRepository mbrStplatAgrRepository;

	public static final String WELCOME1_MEMBER_EVENT = "A";
	public static final String WELCOME2_MEMBER_EVENT = "B";
	public static final String BIRTH_MEMBER_EVENT = "C";
	public static final String APP_DOWNLOAD_EVENT = "D";
	public static final String ALL_BPOFFERINFO_EVENT = "";
	
	/**
     * 프런트 (PC, MOBILE) 회원들에게 부여되는 권한.
     */
    final static String FRONT_ROLE_NAME = "ROLE_USER";

	/**
	 * 로그인 인증.
	 *
	 * @param dto [설명]
	 * @return Security user detail [설명]
	 * @ the exception
	 * @since 2015. 4. 15
	 */
	public SecurityUserDetail selectSecuredMember(MemberFoDTO dto)  {

		//임시로 작성 실제 db 조회 로직으로 수정해야 함.
		SecurityUserDetail detail = new SecurityUserDetail();
		
		Mbr mbr = memberBaseSelectRepository.login(dto);
		detail.setMbr(mbr);

		return detail;
	}

	public Mbr selectMbrStatus(String mbrNo) {
		return memberBaseSelectRepository.selectMbrStatus(mbrNo);
	}
	
	public MemberFoResult selectSecessionCheck(MemberFoDTO dto) {
		return memberBaseSelectRepository.selectSecessionCheck(dto);
	}
	
	public Page<HistoryInfoFoResult> selectMyLoginHistory(HistoryInfoFoDTO historyInfoFoDTO, PageParam pageParam) {
	    return memberBaseSelectRepository.selectMyLoginHistory(historyInfoFoDTO, pageParam);
    }
	
	/**
	 * 회원 탈퇴 조건 조회.
	 *
	 * @param mbr [설명]
	 * @return Mbr extend result [설명]
	 * @since 2015. 5. 7
	 */
    public MbrExtendResult selectMemberForSecession(Mbr mbr) {
		return memberBaseSelectRepository.selectMemberForSecession(mbr);
	}
    
    /**
     * ERP번호로 회원목록조회
     * 
     * @param erpCstmrNo
     * @return List<Mbr>
     */
    public List<Mbr> selectMemberByErpCstmrNo(String erpCstmrNo) {
    	return memberBaseSelectRepository.selectMemberByErpCstmrNo(erpCstmrNo);
    }
    
    public List<SysStplat> selectJoinSysStplat(SysStplatUse sysStplatUse)  {
	    return memberBaseSelectRepository.selectJoinSysStplat(sysStplatUse);
    }

    public List<SysStplat> selectSysStplat(SysStplatUse sysStplatUse)  {
	    return memberBaseSelectRepository.selectSysStplat(sysStplatUse);
    }

	/**
	 * 회원 조회 - ERP 번호
	 */
	public String selectMemberErpNo(String mbrNo) {
		return memberBaseSelectRepository.selectMemberErpNo(mbrNo);
	}
    
    public List<MemberFoResult> getMbrId(MemberFoDTO dto) {
	    return memberBaseSelectRepository.getMbrId(dto);
    }
    
    public int checkAddMember(MemberFoDTO dto)  {
	    return memberBaseSelectRepository.checkAddMember(dto);
    }

    public boolean isCheckId(MemberFoDTO dto) {
		int count = memberBaseSelectRepository.isCheckId(dto);
		return count > 0;
    }



    public boolean checkMobilNoAsId(MemberFoDTO dto){
		int count = memberBaseSelectRepository.checkMobilNoAsId(dto);
		return count > 0;
	}

    public boolean isValidId(MemberFoDTO dto){
    	boolean result = false;
    	String mbrNo = memberBaseSelectRepository.isValidId(dto);
		if(StringService.isNotEmpty(mbrNo)){
			result = true;
		}
		return result;
	}

    /**
     * 추천인 회원NO 조회
     * 
     * @param dto
     * @return
     */
	public String selectRecommandMbrNo(MemberFoDTO dto) {
		return memberBaseSelectRepository.selectRecommandMbrNo(dto);
	}

	/**
	 * 추천인 회원ID 조회
	 * 
	 * @param dto
	 * @return
	 */
	public String selectRecommandMbrId(MemberBoDTO dto){
		return memberBaseSelectRepository.selectRecommandMbrId(dto);
	}

    public boolean isCheckEmail(MemberFoDTO dto) {
	    int count = memberBaseSelectRepository.isCheckEmail(dto);
		return count > 0;
    }
    
    public boolean isCheckEmailOthers(MemberFoDTO dto) {
	    int count = memberBaseSelectRepository.isCheckEmailOthers(dto);
		return count > 0;
    }
    
	/**
	 * 싱글 로그인 되어 있을시 가입이 되어 있는지 확인
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Member fo result [설명]
	 * @since 2015. 4. 15
	 */
	public MemberFoResult getSingleMbr(MemberFoDTO dto) {
		return memberBaseSelectRepository.getSingleMbr(dto);
	}
	
	public MemberFoResult getMbrPassword(MemberFoDTO dto) {
		return memberBaseSelectRepository.getMbrPassword(dto);
    }
	
	public MemberFoResult selectMemberForPassword(MemberFoDTO dto) {
	    return memberBaseSelectRepository.selectMemberForPassword(dto);
    }
	
	public List<MbrStplatAgr> selectMbrStplatAgrList(String mbrNo)  {
		return memberBaseSelectRepository.selectMbrStplatAgrList(mbrNo);
	}
	
	public MemberFoResult selectMemberErp(MemberFoDTO dto){
		return memberBaseSelectRepository.selectMemberForErp(dto);
	}
	
	public long selectCountHistory(HistoryInfoFoDTO historyInfoFoDTO) {
		return memberBaseSelectRepository.selectCountHistory(historyInfoFoDTO);
	}

	/**
	 * 회원 개인정보 변경 이력 등록 정보 설정.
	 *
	 * @param mbrNo 개인정보 변경 회원번호
	 * @param modResnDscr 개인정보 변경 사유
	 * @param loginId 시스템 접근자 ID (FRONT - mbr_no, BO/CS/PO - loginId(admin_id))
	 * @param isMember 변경주체의 회원 여부
	 * @return Mbr psnl info mod hist [설명]
	 * @ the exception
	 * @since 2015. 4. 16
	 */
	public MbrPsnlInfoModHist setMbrPsnlInfoModHist(String mbrNo, String modResnDscr, String loginId, boolean isMember) {
	
	    long psnlInfoModHistSn = getIdGenService().generateDBSequence(sqlSession1, "sq_mbr_psnl_info_mod_hist", DatabaseType.ORACLE);
	
	    MbrPsnlInfoModHist mpim = new MbrPsnlInfoModHist();
	    mpim.setPsnlInfoModHistSn(psnlInfoModHistSn);              // 개인정보 변경 이력 일련번호
	    mpim.setMbrNo(mbrNo);                                       // 회원번호
	    mpim.setModResnDscr(modResnDscr);                         // 변경 사유 설명
	    mpim.setErpTrnsmisYn("N");       // ERP 전송 여부
	    mpim.setRegtrId(loginId);
	    mpim.setUdterId(loginId);
	
	    if(isMember){
	        mpim.setModMbrNo(loginId);                       // 변경 회원 번호
	    } else {
	        mpim.setModAdminId(loginId);                    // 변경 관리자 ID
	    }
	
	    return mpim;
	}
	
	/**
     * 회원 상세 정보 변경 이력 등록.
     *
     * @param afterMbr [설명]
     * @param mpim [설명]
     * @param codeArr [설명]
     * @param isReg [설명]
     * @ the exception
     * @since 2015. 4. 16
     */
    public void insertPersonalInfoForMbr(MbrPsnlInfoModHist mpim, String[] codeArr, Mbr afterMbr)  {
    	try{
	    	// 기존 정보 조회
	    	Mbr beforeMbr = mbrRepository.selectMbr(afterMbr);
	    	
	    	// AFTER
	        Map<String, Object> afterMap = makeAfterMap(afterMbr);
	
	        // BEFORE
	        Map<String, Object> beforeMap = makeAfterMap(beforeMbr);
	        
	
	        insertPersonalInfo(afterMap, beforeMap, mpim, codeArr);
	    } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    /**
     * 약관 동의여부 조회
     * 
     * @param mbrStplatAgr
     * @return
     */
    public MbrStplatAgr selectMbrStplatAgr(MbrStplatAgr mbrStplatAgr)  {
    	
    	MbrStplatAgr msa = new MbrStplatAgr();
		
    	try {
			msa = mbrStplatAgrRepository.selectMbrStplatAgr(mbrStplatAgr);
		}
		catch (Exception e) {
		
			throw new RuntimeException(e);
		}
    	
    	return msa;
    }
    
    /**
     * 약관 동의여부 이력 insert
     * 
     * @param mbrStplatAgr
     * @return
     */
    public void insertMbrStplatAgrPsnlmodHist(MbrPsnlInfoModHist mpim)  {
    	
    	long psnlInfoModHistSn = getIdGenService().generateDBSequence(sqlSession1, "sq_mbr_psnl_info_mod_hist", DatabaseType.ORACLE);
    	mpim.setPsnlInfoModHistSn(psnlInfoModHistSn); // 개인정보 변경 이력 순번

    	Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("psnl_info_mod_hist_sn", mpim .getPsnlInfoModHistSn());
		int modTurn = getIdGenService().generateDBOrder(sqlSession1, "mbr_psnl_info_mod_hist", "psnl_info_mod_hist_turn", conditions, DatabaseType.ORACLE);
		mpim.setPsnlInfoModHistTurn(modTurn);	// 개인정보 변경 이력 순번
		mpim.setModLcSectCd(MemberModLcSectCd.ONLNE_MALL.toString()); //수정매장
		
		memberPersonalInfoCommandRepository.insertPersonalInfoModHistory(mpim);
		
    }
    
    /**
	 * 회원 정보 변경 이력 등록.
	 *
	 * @param afterMap [설명]
	 * @param beforeMap [설명]
	 * @param mpim [설명]
	 * @param codeArr [설명]
	 * @ the exception
	 * @since 2015. 4. 16
	 */
	private void insertPersonalInfo(Map<String, Object> afterMap, Map<String, Object> beforeMap, MbrPsnlInfoModHist mpim, String[] codeArr)  {
		String modBfVal = "";
		String modAfVal = "";
		
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("psnl_info_mod_hist_sn", mpim .getPsnlInfoModHistSn());
		int modTurn = 0;
		
		for(String codeName : codeArr){
			modBfVal = this.makeCheckString(codeName, beforeMap);
			modAfVal = this.makeCheckString(codeName, afterMap);
			
			if(StringService.contains(codeName, "_NO") || StringService.contains(codeName, "_DATE") || StringService.contains(codeName, "MBR_BRTHDY")){
				modBfVal = StringService.removeHyphen(modBfVal);
				modAfVal = StringService.removeHyphen(modAfVal);
				
			}
												
			if(modBfVal.equals(modAfVal)){continue; } 
			
			// 변경이력 값 설정
			modTurn = getIdGenService().generateDBOrder(sqlSession1, "mbr_psnl_info_mod_hist", "psnl_info_mod_hist_turn", conditions, DatabaseType.ORACLE);
			mpim.setPsnlInfoModHistTurn(modTurn);	// 개인정보 변경 이력 순번
			
			mpim.setPsnlInfoUdtSectCd(codeName); 	// 개인정보 수정 구분 코드
			mpim.setModBfVal(modBfVal);  			// 변경 이전 값
			mpim.setModAfVal(modAfVal);				// 변경 이후 값
			
			memberPersonalInfoCommandRepository.insertPersonalInfoModHistory(mpim);
		}
	}

    
    private String makeCheckString(String codeName, Map<String, Object> paramMap){
        String returnStr = "";

        String[] colunms =  StringService.split(MemberPersonalInfoEnum.valueOf(codeName).toString(), "|");

        for(String colunm : colunms){
            returnStr += StringService.trimToEmpty((String) paramMap.get(colunm));
        }

        return returnStr;
    }


	
	public String checkMemberPassword(MemberFoDTO dto) {
	    return memberBaseSelectRepository.checkMemberPassword(dto);
	    
    }
	
	/**
	  * @Method Name : compareMbrData
	  * @작성일 : 2017. 1. 20.
	  * @작성자 : user
	  * @Method 설명 : 회원 데이터 비교
	  * @param userDetail
	  * @param mbrInfo
	  * @return
	  * @
	  */
	public boolean compareMbrData(String langCd,SecurityUserDetail userDetail, Mbr mbrInfo){


		boolean mbrStatus = true ;


		if("KOR".equalsIgnoreCase(langCd)){ //글로벌정책 : 멤버쉽회원->온라인 회원으로 바뀌는 정책( 글로벌은 온라인 회원만 존재)때문에 DB값과유형 비교 불가
			/* 회원 유형비교 */
			boolean mbrTpData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getMbrTpCd()), String.valueOf(mbrInfo.getMbrTpCd()));
			if(!mbrTpData){
				return false;
			}

			/* 회원 속성비교 */
			boolean mbrAtrbData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getMbrAtrbCd()), String.valueOf(mbrInfo.getMbrAtrbCd()));
			if(!mbrAtrbData){
				return false;
			}

		}

		
		/* 회원 이름 비교 */
		boolean mbrNameData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getMbrNm()), String.valueOf(mbrInfo.getMbrNm()));
		if(!mbrNameData){
			return false;
		}
		
		/* 회원 국가번호 비교 */
		boolean mbrNationNoData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getMobilNationNo()) , String.valueOf(mbrInfo.getMobilNationNo()));
		if(!mbrNationNoData){
			return false;
		}
		
		/* 회원 지역번호 비교 */
		boolean mbrAreaNoData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getMobilAreaNo()), String.valueOf(mbrInfo.getMobilAreaNo()));
		if(!mbrAreaNoData){
			return false;
		}
		
		/* 회원 국번호 비교 */
		boolean mbrTlofNoData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getMobilTlofNo()), String.valueOf(mbrInfo.getMobilTlofNo()));
		if(!mbrTlofNoData){
			return false;
		}
		
		/* 회원 지역번호 비교 */
		boolean mbrTlofWthnNoData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getMobilTlofWthnNo()), String.valueOf(mbrInfo.getMobilTlofWthnNo()));
		if(!mbrTlofWthnNoData){
			return false;
		}
  	
		/* 회원 이메일 비교 */
		boolean mbrEmailData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getMbrEmail()), String.valueOf(mbrInfo.getMbrEmail()));
		if(!mbrEmailData){
			return false;
		}
		
		/* 회원 이메일 수신여부 비교 */
		boolean mbrEmailYnData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getEmailRecptnAgrYn()), String.valueOf(mbrInfo.getEmailRecptnAgrYn()));
		if(!mbrEmailYnData){
			return false;
		}
		
		/* 회원 SMS 수신여부  비교 */
		boolean mbrSmsYnData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getSmsRecptnAgrYn()), String.valueOf(mbrInfo.getSmsRecptnAgrYn()));
		if(!mbrSmsYnData){
			return false;
		}
		
		/* 회원 DM 수신여부 비교 */
		boolean mbrDmYnData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getDmRecptnAgrYn()), String.valueOf(mbrInfo.getDmRecptnAgrYn()));
		if(!mbrDmYnData){
			return false;
		}

		/* 회원 TM 수신여부 비교 */
		boolean mbrTmYnData = comapreMbrDetailData(String.valueOf(userDetail.getMbr().getTmRecptnAgrYn()), String.valueOf(mbrInfo.getTmRecptnAgrYn()));
		if(!mbrTmYnData){
			return false;
		}
		return mbrStatus;
		
		
	}
	
	/**
	  * @Method Name : comapreMbrDetailData
	  * @작성일 : 2017. 1. 20.
	  * @작성자 : user
	  * @Method 설명 : 세션, DB데이터 비교
	  * @param sessionData
	  * @param dbData
	  * @return
	  * @
	  */
	boolean comapreMbrDetailData(String sessionData , String dbData){
		
		boolean mbrStatus = true;
		/* 세션데이터와 DB 데이터가 다를 시 false 처리 */
		if(!sessionData.equals(dbData)){
			mbrStatus = false;
		}
		
		return mbrStatus;	
	}
	
	public MemberFoResult selectMbrSysStplat(MemberFoDTO dto)  {
	    return memberBaseSelectRepository.selectMbrSysStplat(dto);
    }
	
	public Mbr getMbrInfoByMbrNo(String mbrNo) {
		return memberBaseSelectRepository.getMbrInfoByMbrNo(mbrNo);
	}
	
    public Mbr selectMbr(Mbr mbr) {
		return memberBaseSelectRepository.selectMbr(mbr);
	}
    
    /**
	 * 포인트 연동 이력 등록.
	 * 처리가 실패하여도 오류 발생하지 않음.
	 * 
	 * @param mpih [설명]
	 * @param loginId [설명]
	 * @since 2015. 4. 30
	 */
    public void insertMemberPointHistory(MbrPntIntrlckHist mpih, String loginId) {
    	try{
    		long pntSn = getIdGenService().generateDBSequence(sqlSession1, "sq_mbr_pnt_intrlck_hist", DatabaseType.ORACLE);
    		mpih.setPntSn(pntSn);
    		mpih.setUdterId(loginId);
    		mpih.setRegtrId(loginId);
    		
    		mbrPntIntrlckHistRepository.insertMbrPntIntrlckHist(mpih);

    	} catch(Exception e){
    		if(log.isWarnEnabled()) log.warn("> insertMemberPointHistory Exception : {}", e.getMessage());
    	}
	}


    
    /**
	 * 포인트 연동 이력 등록.
	 * 처리가 실패하여도 오류 발생하지 않음.
	 *
	 * @param mbrNo [설명]
	 * @since 2015. 4. 30
	 */
	public void insertMbrBnefPymntHist(MbrBnefPymntHist mbrBnefPymntHist)  {
		try{

			// 앱 다운로드 멤버십 포인트 혜택 이력 저장
			Map<String, Object> conditions = Maps.newHashMap();
			conditions.put("MBR_NO", mbrBnefPymntHist.getMbrNo());

			HttpServletRequest request = ContextService.getCurrentRequest();
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);

			long time = System.currentTimeMillis();
			Date pymntDt = new Date(time);

			mbrBnefPymntHist.setBnefPymntTurn(getIdGenService().generateDBOrder(sqlSession1, "MBR_BNEF_PYMNT_HIST", "BNEF_PYMNT_TURN", conditions, DatabaseType.ORACLE));
			mbrBnefPymntHist.setDvcCd(systemPK.getDevice());
			mbrBnefPymntHist.setLangCd(systemPK.getLang());
			mbrBnefPymntHist.setMallId(systemPK.getMall());
			mbrBnefPymntHist.setMobileAppSectCd(systemPK.getApp());
			mbrBnefPymntHist.setUdterId(mbrBnefPymntHist.getMbrNo());
			mbrBnefPymntHist.setRegtrId(mbrBnefPymntHist.getMbrNo());
			mbrBnefPymntHist.setPymntDt(pymntDt);

			mbrBnefPymntHistRepository.insertMbrBnefPymntHist(mbrBnefPymntHist);
		} catch(Exception e){
			if(log.isWarnEnabled()) log.warn("> insertMbrBnefPymntHist Exception : {}", e.getMessage());
		}
	}
	
	public void insertMbrRecomendrRecomendeCnnc(MbrRecomendrRecomendeCnnc mbrRecomendrRecomendeCnnc){
		try{
			mbrRecomendrRecomendeCnncRepository.insertMbrRecomendrRecomendeCnnc(mbrRecomendrRecomendeCnnc);
		}catch(Exception e){
			if(log.isWarnEnabled()) log.warn("> insertMbrRecomendrRecomendeCnnc Exception : {}", e.getMessage());
		}
	}

	/**
     * 로그인.
     * 
     *
     * @param mbr [설명]
     * @return Mbr [설명]
     * @since 2015. 3. 31
     */
    public Mbr login(MemberFoDTO dto){
    	return memberBaseSelectRepository.login(dto);
    }
    
	/**
     * 회원 목록 건수 조회.
     *
     * @param dto [설명]
     * @return Long [설명]
     * @ the exception
     * @since 2015. 4. 17
     */
	public long selectMemberListCount(MemberBoDTO dto)  {
		return memberBaseSelectRepository.selectMemberListCount(dto);
	}
	
	/**
	 * 회원 목록 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @ the exception
	 * @since 2015. 4. 7
	 */
	public List<MemberBoResult> selectMemberList(MemberBoDTO dto)  {
		RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam());	// 페이지 설정
		return memberBaseSelectRepository.selectMemberList(dto);		
	}

	/**
	 * 회원 엑셀 다운로드 목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 4. 24
	 */
    public List<Map<String, Object>> selectMemberListExcel(MemberBoDTO dto) {
		return memberBaseSelectRepository.selectMemberListExcel(dto);
	}
    
    /**
	 * 회원 간략 정보 조회.
	 */
	public MemberBoResult selectMemberSimple(MemberBoDTO dto)  {
		return memberBaseSelectRepository.selectMemberSimple(dto);
	}

	/**
	 * 회원 상세 정보 조회.
	 */
	public MemberBoResult selectMember(MemberBoDTO dto)  {
		return memberBaseSelectRepository.selectMember(dto);
	}

	/**
	 * 회원 조회 - FOR CS.
	 *
	 * @param mbrNo [회원 번호]
	 * @param isMasking [개인정보 마스킹 설정 여부]
	 * @return Mbr extend result [설명]
	 * @since 2015. 5. 7
	 */
	public MbrExtendResult selectMemberForCS(String searchType, String searchValue, boolean isMasking, String mbrNo) {
		MbrExtendDTO dto = new MbrExtendDTO();
		if(StringService.equalsIgnoreCase(searchType, "TEL")){
			dto.setTelNo(searchValue);
		} else if(StringService.equalsIgnoreCase(searchType, "ID")){
			dto.setMbrId(searchValue);
		} else {
			throw new MemberBoException(null);
		}
		dto.setMaskingYn(isMasking ? MemberEnum.YES.toString() : MemberEnum.NO.toString());
		dto.setMbrNo(mbrNo);
		return memberBaseSelectRepository.selectMemberForCS(dto);
	}

	/**
	 * 정지 회원 목록 건수 조회.
	 */
	public long selectSuspendMemberListCount(MemberBoDTO dto)  {
		return memberBaseSelectRepository.selectSuspendMemberListCount(dto);
	}
	
	/**
	 * 정지 회원 목록 조회.
	 *
	 * @param dto [설명]
	 * @return Page [설명]
	 * @ the exception
	 * @since 2015. 4. 7
	 */
	public List<MemberBoResult> selectSuspendMemberList(MemberBoDTO dto)  {
		RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam());	// 페이지 설정
		return memberBaseSelectRepository.selectSuspendMemberList(dto);
	}
	
	/**
	 * 정지 회원 엑셀 다운로드 목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 4. 24
	 */
    public List<Map<String, Object>> selectSuspendMemberListExcel(MemberBoDTO dto) {
		return memberBaseSelectRepository.selectSuspendMemberListExcel(dto);
	}
	
	/**
	 * 탈퇴 회원 목록 건수 조회.
	 */
	public long selectSecessionMemberListCount(MemberBoDTO dto)  {
		return memberBaseSelectRepository.selectSecessionMemberListCount(dto);
	}
	
	/**
	 * 탈퇴 회원 목록 조회.
	 *
	 * @param dto [설명]
	 * @return Page [설명]
	 * @ the exception
	 * @since 2015. 4. 7
	 */
	public List<MemberBoResult> selectSecessionMemberList(MemberBoDTO dto)  {
		RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam());	// 페이지 설정
		return memberBaseSelectRepository.selectSecessionMemberList(dto);
	}
	
	/**
	 * 탈퇴 회원 엑셀 다운로드 목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 4. 24
	 */
    public List<Map<String, Object>> selectSecessionMemberListExcel(MemberBoDTO dto) {
		return memberBaseSelectRepository.selectSecessionMemberListExcel(dto);
	}
    
	
	/**
	 * 온라인클럽 회원 목록 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @ the exception
	 * @since 2015. 6. 4
	 */
	public PageImpl<MemberBoResult> selectOnlineClubMemberList(MemberBoDTO dto)  {
    	// 페이지 인덱스 셋팅
    	RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam());
    	// 온라인클럽 회원 유지 기준
    	dto.setOnlineClubLoginCnt(Integer.parseInt(MemberEnum.ONLINE_CLUB_LOGIN_CNT.toString()));
    	dto.setOnlineClubGodEvlCnt(Integer.parseInt(MemberEnum.ONLINE_CLUB_GOD_EVL_CNT.toString()));
    	dto.setOnlineClubSnsCnt(Integer.parseInt(MemberEnum.ONLINE_CLUB_SNS_CNT.toString()));
    	
    	// step 2. 목록 건수 조회
    	long listCount = memberBaseSelectRepository.selectOnlineClubMemberListCount(dto);
    	
    	// 목록 조회
		List<MemberBoResult> lists = new ArrayList<MemberBoResult>();
		if(listCount > 0){
			lists = memberBaseSelectRepository.selectOnlineClubMemberList(dto);
		}
    	
    	return new PageImpl<MemberBoResult>(lists, dto.getPageParam().getPageable(), listCount);
	}
	
	/**
	 * 온라인클럽 회원 엑셀 다운로드 목록 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 6. 4
	 */
    public List<Map<String, Object>> selectOnlineClubMemberExcel(MemberBoDTO dto) {
    	// 온라인클럽 회원 유지 기준
    	dto.setOnlineClubLoginCnt(Integer.parseInt(MemberEnum.ONLINE_CLUB_LOGIN_CNT.toString()));
    	dto.setOnlineClubGodEvlCnt(Integer.parseInt(MemberEnum.ONLINE_CLUB_GOD_EVL_CNT.toString()));
    	dto.setOnlineClubSnsCnt(Integer.parseInt(MemberEnum.ONLINE_CLUB_SNS_CNT.toString()));

		return memberBaseSelectRepository.selectOnlineClubMemberExcel(dto);
	}
    
	/**
     * 앱다운로드 포인트 적립 목록 건수 조회.
     *
     * @param dto [설명]
     * @return Long [설명]
     * @ the exception
     * @since 2015. 4. 17
     */
	public long selectAppDownloadPntListCount(MemberBoDTO dto)  {
		return memberBenefitSelectRepository.selectAppDownloadPntListCount(dto);
	}
	
	/**
	 * 앱다운로드 포인트 적립  목록 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @ the exception
	 * @since 2015. 4. 7
	 */
	public List<MemberBoResult> selectAppDownloadPntList(MemberBoDTO dto)  {
		RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam());	// 페이지 설정
		return memberBenefitSelectRepository.selectAppDownloadPntList(dto);		
	}
	
	/**
	 *앱다운로드 포인트 적립 목록 액셀조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @ the exception
	 * @since 2015. 4. 7
	 */
	public List<Map<String, Object>> selectAppDownloadPntListExcel(MemberBoDTO dto)  {
		//RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam());	// 페이지 설정
		return memberBenefitSelectRepository.selectAppDownloadPntListExcel(dto);		
	}
	
	/**
     * 권한 그룹 정보 가져오기
     * 
     * @param loginId
     * @return
     * @
     */
    public SysAuthorGrp selectAdminAuthorGrp(String loginId) {
        return memberBaseSelectRepository.selectAdminAuthorGrp(loginId);
    }
    
    /**
	 * 회원 인증 여부 select
	 * @param mbr
	 * @return
	 * @
	 */
	public Mbr getMbr(Mbr mbr)  {
		return memberBaseSelectRepository.getMbr(mbr);
	}
	
	/**
	 * 회원 엑셀 다운로드 목록 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 4. 24
	 */
	public List<Map<String, Object>> selectPurpleCoinMemberListExcel(MemberBoDTO dto) {
		return memberBaseSelectRepository.selectPurpleCoinMemberListExcel(dto);
	}

	public String selectErpGrdcd( String  mbrNo )
	 {
		 return memberBaseSelectRepository.getErpGrdCd(mbrNo);
	 }
	
	/**
	 * 그룹사 조회
	 * @param memberSysGrpcoSearchDTO
	 * @return
	 * @
	 */
	public Page<MemberSysGrpcoResult> selectSysGrpcoList(MemberSysGrpcoSearchDTO memberSysGrpcoSearchDTO)  {

		RepositoryHelper.makePageEntityIndex(memberSysGrpcoSearchDTO, memberSysGrpcoSearchDTO.getPageParam());	// 페이지 설정
		
		// 목록 건수 조회
		long totalRow = memberBaseSelectRepository.selectSysGrpcoListCount(memberSysGrpcoSearchDTO);
		
		// 목록 조회
		List<MemberSysGrpcoResult> results = new ArrayList<MemberSysGrpcoResult>();
		if(totalRow > 0){
			results = memberBaseSelectRepository.selectSysGrpcoList(memberSysGrpcoSearchDTO);
		}
		
		return new PageImpl<MemberSysGrpcoResult>(results, memberSysGrpcoSearchDTO.getPageParam().getPageable(), totalRow);
	}
	
	/** 회원 정보*/
	public List<MypageMtmFoResult> selectMemberMtmInfo(MypageMtmFoDTO mypageMtmFoDTO) {
	    return memberBaseSelectRepository.selectMemberMtmInfo(mypageMtmFoDTO);
    }
	
	/**
	 * 회원 SMS 발송 내역 건수 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public long selectSmsListCountForMember(MemberBoDTO dto) {
		return memberBaseSelectRepository.selectSmsListCountForMember(dto);
	}
	
	/**
	 * 회원 SMS 발송 내역 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public List<MemberWebSelectResult> selectSmsListForMember(MemberBoDTO dto) {
    	return memberBaseSelectRepository.selectSmsListForMember(dto);
	}
    
	/**
	 * 회원 SMS 발송 내역 엑셀 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public List<Map<String, Object>> selectSmsExcelForMember(MemberBoDTO dto) {
    	return memberBaseSelectRepository.selectSmsExcelForMember(dto);
	}
    
	/**
	 * 회원 Mail 발송 내역 건수 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public long selectEMailListCountForMember(MemberBoDTO dto) {
    	return memberBaseSelectRepository.selectEMailListCountForMember(dto);
	}
    
    /**
	 * 회원 Mail 발송 내역 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public List<MemberWebSelectResult> selectEMailListForMember(MemberBoDTO dto) {
    	return memberBaseSelectRepository.selectEMailListForMember(dto);
	}
    
	/**
	 * 회원 Mail 발송 내역 엑셀 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
    public List<Map<String, Object>> selectEMailExcelForMember(MemberBoDTO dto) {
    	return memberBaseSelectRepository.selectEMailExcelForMember(dto);
	}

	public int selectMysizeExistYn(MbrSizeClfc mbrSizeClfc) {
		return memberBaseSelectRepository.selectMysizeExistYn(mbrSizeClfc);
	}

	public int selectMbrSizeAllInputCount(String mbrNo) {
		return memberBaseSelectRepository.selectMbrSizeAllInputCount(mbrNo);
	}

	/**
	 * 회원 등급 조회
	 *
	 * @param mbrGrd
	 * @return mbrGrd
	 */
	public MbrGrd selectMbrGrd(MbrGrd mbrGrd) {
		return memberBaseSelectRepository.selectMbrGrd(mbrGrd);
	}
	
	/**
	 * 회원 로그인 로그 최신 로그인 1건의 시간 조회(YYYY-MM-DD HH24:MI:SS).
	 * 
	 * @param mbrLoginLog
	 * @return
	 * @throws Exception
	 */
	public String selectMbrLoginLog(MbrLoginLog mbrLoginLog) {
		return memberBaseSelectRepository.selectMbrLoginLog(mbrLoginLog);
	}

	/**
	 * 탈퇴한 회원 정보 조회
	 * 
	 * @param mbr
	 * @return MemberFoResult
	 * @throws Exception
	 */
	public MemberFoResult selectSecessionMbrInfo(Mbr mbr) {
		return memberBaseSelectRepository.selectSecessionMbrInfo(mbr);
	}

	/**
	 * 유입 경로 조회
	 * 
	 * @param sysInflow
	 * @return SysInflow
	 */
	public SysInflow selectMbrSysInflow(SysInflow sysInflow) {
		return memberBaseSelectRepository.selectMbrSysInflow(sysInflow);
	}
	
    /**
     * request에서 requestURL을 가져와서 http://, https:// 및 파라미터 부분을 제외하고 반환.
     * - AWS 내부 도메인으로 나올 경우는 ncp_base에서 가져옴.
     * 
     * @param request
     * @param systemPK
     * @return String
     */
	public String getRequestUrlForAddMember(HttpServletRequest request, SystemPK systemPK) {
		String requestUrl = "";
		try {
			requestUrl = request.getRequestURL().toString();
			requestUrl = requestUrl.replace("http://", "").replace("https://", "");
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("/"));
			
			if(requestUrl.contains("internal-prd")) {
				String baseUrl = "";
				if("PC".equals(systemPK.getDevice())) {
					if("SAM".equals(systemPK.getMall())){
						baseUrl = getConfigService().getProperty("ncp.url.pc_SA.root.secure");
					}else if("MBM".equals(systemPK.getMall())){
						baseUrl = getConfigService().getProperty("ncp.url.pc_MLB.root.secure");
					}else{
						baseUrl = getConfigService().getProperty("ncp.url.pc_DX.root.secure");
					}
					
				}
				else {
					if("SAM".equals(systemPK.getMall())){
						baseUrl = getConfigService().getProperty("ncp.url.mb_SA.root.secure");
					}else if("MBM".equals(systemPK.getMall())){
						baseUrl = getConfigService().getProperty("ncp.url.mb_MLB.root.secure");
					}else{
						baseUrl = getConfigService().getProperty("ncp.url.mb_DX.root.secure");
					}						
				}
				
				requestUrl = baseUrl.replace("http://", "").replace("https://", "");
			}
		}
		catch(Exception e) {
			if(log.isWarnEnabled()) log.warn("> getRequestUrlForAddMember Exception : {}", e.getMessage());
		}

		return requestUrl;
	}
	
	private HashMap<String, String> getValue(String sText) {
		sText = sText.replaceAll(" ", "");
		String strTmp = sText.replaceAll("[0-9,\\-]+", "");

		if(StringService.isNotEmpty(strTmp)){
			int lastStrIndex = sText.lastIndexOf(strTmp.substring(strTmp.length() - 1, strTmp.length()));
			sText = sText.substring(0, lastStrIndex + 1) + " " + sText.substring(lastStrIndex + 1, sText.length());
		}

		sText = sText.replaceAll("-", " ").replaceAll("^ +| +$|( )+", "$1");

		HashMap<String, String> ret = new HashMap<String, String>();
		StringTokenizer mainTk = new StringTokenizer(sText, " ");
		int i = 1;

		while (mainTk != null && mainTk.hasMoreTokens()) {

			String mainKeyword = mainTk.nextToken();

			ret.put("keyword" +i, mainKeyword);
			i++;
		}

		return ret;
	}
	
	/**
	 * ERP에서 받은 회원 등급 변경정보 조회
	 * @return
	 */	
	public List<InfMbrGrdInfoRecptn> selectMemberGradeInfoCp(InfMbrGrdInfoRecptn infMbrGrdInfoRecptn) {		
		return memberBaseSelectRepository.selectMemberGradeInfoCp(infMbrGrdInfoRecptn);
	}
	
	/**
	 * 인터페이스 회원 등급 변경정보 수신 삭제.
	 *
	 * @param infMbrGrdInfoRecptn the InfMbrGrdInfoRecptn
	 * @return the InfMbrGrdInfoRecptn
	 * @throws SQLException the SQL exception
	 */
	public int deleteInfMbrGrdInfoRecptnCp(InfMbrGrdInfoRecptn infMbrGrdInfoRecptn) {
		return memberBaseSelectRepository.deleteInfMbrGrdInfoRecptnCp(infMbrGrdInfoRecptn);
	}
	
}
