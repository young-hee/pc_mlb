package com.plgrim.ncp.biz.member.repository;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginLog;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.framework.data.SystemPK;

/**
 * 회원기본정보 command repository
 */
@Repository
public class MemberBaseCommandRepository extends AbstractRepository  {
	 
	/**
     * 회원 정보 변경.
     *
     * @param mbr [설명]
     * @return Int [설명]
     */
	public void updateFoMember(Mbr mbr)  {
    	getSession1().update("com.plgrim.ncp.biz.mbr.updateFoMember", mbr);
    }
    
    /**
	 * 회원 비밀번호 초기화.
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 */
	public int updateMemberPassword(Mbr mbr) {
		return getSession1().update("com.plgrim.ncp.biz.mbr.updateMemberPassword", mbr);
	}
    
    /**
	 *  회원 개인정보 삭제(휴면 처리) - UPDATE.
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2018. 07. 02
	 */
    public int updateMemberDormancy(Mbr mbr) {
		return getSession1().update("com.plgrim.ncp.biz.mbr.secession.updateMemberDormancy", mbr);
	}
    
    /**
	 *  회원 개인정보 삭제(탈퇴 처리) - UPDATE.
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2015. 5. 20
	 */
    public int updateMemberSecession(Mbr mbr) {
		return getSession1().update("com.plgrim.ncp.biz.mbr.secession.updateMemberSecession", mbr);
	}
    
    /** 회원 인증 삭제.*/
    public int deleteMbrCrtfc(String mbrNo) {

		/* 
		 * 회원 인증 삭제 전, 재가입여부 체크를 위한 데이터 적재
		 * 
		 * 2018.06.18
		 * ERP에 CI,DI 값이 있으므로 온라인은 관리하지 않는 것으로 변경.
		 * 
		 * */
    	/*
    	// Step 1.회원인증 데이터 조회 - DI/CI 값이 있는 경우에만
		List<MbrSecsnCrtfc> mbrSecsnCrtfcList = getSession1().selectList("com.plgrim.ncp.biz.mbr.secession.selectMbrCrtfc", mbrNo);

		// Step 2. 회원탈퇴인증 테이블에 데이터 적재
		if (mbrSecsnCrtfcList != null && mbrSecsnCrtfcList.size() > 0) {

			// 개인정보보호를 위해 암호화가 필요할 수 있어 FOR문으로 구현
			for (MbrSecsnCrtfc mbrSecsnCrtfc : mbrSecsnCrtfcList) {
				mbrSecsnCrtfc.setRegtrId(mbrNo);
				mbrSecsnCrtfc.setUdterId(mbrNo);

				//IdGenService.generateSHA256();
				getSession1().insert("com.plgrim.ncp.base.insertMbrSecsnCrtfc", mbrSecsnCrtfc);
			}
		}
		*/

		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrCrtfc", mbrNo);
	}
    
    /** 회원 환불 계좌 삭제.*/
    public int deleteMbrRfdBnkAcct(String mbrNo) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrRfdBnkAcct", mbrNo);
	}
	
	public void updateMemberNaverUserId(String mdrNo, String toknId, String loginId) {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		String idCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER.toString();
		if(pk.getMall().equals("MBM")){
			idCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER_MBM.toString();
		}else if(pk.getMall().equals("SAM")){
			idCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER_SAM.toString();
		}
    	
    	MbrIdCntc mbrIdCntc = new MbrIdCntc();
    	
    	mbrIdCntc.setMbrNo(mdrNo);
    	mbrIdCntc.setToknId(toknId);
    	mbrIdCntc.setLoginId(loginId);
    	//mbrIdCntc.setIdCntcTpCd(MemberEnum.MemberIdCntcTpCd.NAVER.toString());
    	mbrIdCntc.setIdCntcTpCd(idCntcTpCd);
    	
    	updateMemberIdCntcInfo(mbrIdCntc);
    }
	
	public void updateMemberFacebookUserId(String mdrNo, String facebookUserId) {
    	
    	MbrIdCntc mbrIdCntc = new MbrIdCntc();
    	
    	mbrIdCntc.setMbrNo(mdrNo);
    	mbrIdCntc.setToknId(facebookUserId);
    	mbrIdCntc.setIdCntcTpCd(MemberEnum.MemberIdCntcTpCd.FACEBUK.toString());
    	
    	updateMemberIdCntcInfo(mbrIdCntc);
    }
	
	/**
	 * 	네이버ID로 로그인 처음 연동 하고 로그인 했을때 사용됨.
     *  @param mbrIdCntc
	 *  @since 2017. 4. 03
	 */    
	public void updateMemberNaverUserFirstLogin(MbrIdCntc mbrIdCntc){
    	getSession1().update("com.plgrim.ncp.biz.mbr.updateMbrIdCntcUdtDtTouch", mbrIdCntc);
    	getSession1().insert("com.plgrim.ncp.biz.mbr.insertMbrIdCntcHist", mbrIdCntc);    	
    }
	
	private void updateMemberIdCntcInfo(MbrIdCntc mbrIdCntc) {
		getSession1().update("com.plgrim.ncp.biz.mbr.updateMbrIdCntc", mbrIdCntc);
		getSession1().insert("com.plgrim.ncp.biz.mbr.insertMbrIdCntcHist", mbrIdCntc);
	}
	
	
    
    /** 회원 약관 동의 삭제.*/
	public int deleteMbrStplatAgr(String mbrNo) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrStplatAgr", mbrNo);
	}
    
	public int updateMbrGrd(Mbr mbr) {
		return getSession1().update("com.plgrim.ncp.biz.mbr.updateMbrGrd", mbr);
	}
	
	/**
	 * 회원정보수정 선택항목-주소수정
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2017. 3. 4
	 */
	public void updateFoMemberAddr(Mbr mbr)  {
		getSession1().update("com.plgrim.ncp.biz.mbr.updateFoMemberAddr", mbr);
	}
	
	public int insertMember(Mbr mbr) {
		return getSession1().insert("com.plgrim.ncp.biz.mbr.insertMember", mbr);
    }

    /**
     * 회원 정보 변경.
     *
     * @param mbr [설명]
     * @return Int [설명]
     */
	public int updateMemberCampagin(Map<String, String> map) {
    	return getSession1().update("com.plgrim.ncp.biz.mbr.updateMemberCampagin", map);
    }
    
    /**
	 * 로그인 실패 횟수 업데이트.
	 * 
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2015. 4. 1
	 */
	public int updateLoginFailCount(Mbr mbr) {
	    return getSession1().update("com.plgrim.ncp.biz.mbr.updateLoginFailCount", mbr);
    }
	
	/**
	 * 회원 로그인 로그 등록.
	 *
	 * @param mbrLoginLog the MbrLoginLog
	 * @throws SQLException the SQL exception
	 */
	public void insertMemberLoginLog(MbrLoginLog mbrLoginLog)  {
		getSession1().insert("com.plgrim.ncp.biz.mbr.login.log.insertMbrLoginLog", mbrLoginLog);
	}
	
	/**
     * 회원 정보 변경.
     *
     * @param mbr [설명]
     * @return Int [설명]
     * @since 2015. 3. 25
     */
    public int updateMember(Mbr mbr) {
    	return getSession1().update("com.plgrim.ncp.biz.mbr.updateMember", mbr);
    }
    
	/**
	 * 회원 상태 변경.
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2015. 3. 25
	 */
    public int updateMemberStatus(Mbr mbr) {
		return getSession1().update("com.plgrim.ncp.biz.mbr.updateMemberStatus", mbr);
	}
    
    /**
     * 회원 정보 선택 변경.
     *
     * @param mbr [설명]
     * @return Int [설명]
     * @since 2015. 3. 25
     */
    public void updateMemberChoiceInfo(Mbr mbr)  {
    	getSession1().update("com.plgrim.ncp.biz.mbr.updateMemberChoiceInfo", mbr);
    }

	/**
	 * @Method Name : updateOnlineGradeInfoBeforeInsert
	 * @작성일 : 2018. 08.03
	 * @작성자 :
	 * @Method 설명 : 온라인회원등급 변경
	 * 회원등급 등록 전 기존의 등급이 등급적용시작일자, 등급적용종료일자 가 중복되는 건에 대해서
	 * 등급적용일자를 새로 등록할 등급적용시작일자의 전일자로 변경.
	 * @param
	 * @return
	 */
	public void updateOnlineGradeInfoBeforeInsert(MbrGrd mbrGrd){
		getSession1().update("com.plgrim.ncp.biz.mbr.updateMbrGrdFo",mbrGrd);
	}
    
	/**
	 * @Method Name : insertOnlineGradeInfo
	 * @작성일 : 2017. 12.08
	 * @작성자 :
	 * @Method 설명 : 회원 가입시 온라인 등급 update
	 * @param
	 * @return
	 */
	public void insertOnlineGradeInfo(MbrGrd mbrGrd){
		getSession1().update("com.plgrim.ncp.biz.mbr.insertMbrGrdFo",mbrGrd);
	}
	
	public void insertOnlineGradeInfoLogin(MbrGrd mbrGrd){
		getSession1().update("com.plgrim.ncp.biz.mbr.insertMbrGrdFoLogin",mbrGrd);
	}
	
    /**
	 * 휴면 해제 처리
	 * 
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2018. 5. 29
	 */
	public int updateMemberForReleaseDrmncy(Mbr mbr) {
	    return getSession1().update("com.plgrim.ncp.biz.mbr.updateMemberForReleaseDrmncy", mbr);
    }
	
    /**
	 * ERP_CSTMR_NO 삭제 처리
	 * 
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2018. 7. 18
	 */
	public int updateMemberForErpCstmrNoInit(Mbr mbr) {
	    return getSession1().update("com.plgrim.ncp.biz.mbr.updateMemberForErpCstmrNoInit", mbr);
    }
	
    /**
	 * 임직원 변경 처리
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2017. 7. 27
	 */
    public int updateMbrForEmp(Mbr mbr) {
		return getSqlSession1().update("com.plgrim.ncp.biz.mbr.updateMbrForEmp", mbr);
	}
}
