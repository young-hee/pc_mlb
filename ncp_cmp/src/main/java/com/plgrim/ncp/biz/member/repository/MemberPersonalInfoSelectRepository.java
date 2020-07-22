package com.plgrim.ncp.biz.member.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatUse;
import com.plgrim.ncp.biz.member.data.MemberAgreementDTO;
import com.plgrim.ncp.biz.member.result.MemberBoResult;

/**
 * 회원개인정보 select repository
 */
@Repository
public class MemberPersonalInfoSelectRepository extends AbstractRepository  {
 
    /**
	 * 회원 선택적동의 리스트
	 * @param mbrNo 회원
	 * @return List<MemberAgreementDTO>
	 */
    public List<MemberAgreementDTO> selectMemberOptionalAgreeList(String mbrNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectMemberOptionalAgreeList", mbrNo);
	}
	
	/**
	 * 회원 개인정보 변경 이력 건수 조회
	 */
    public long selectPersonalInfoModHistoryListCountForMember(MbrPsnlInfoModHist mbrPsnlInfoModHist) {
		return getSession1().selectOne("com.plgrim.ncp.biz.psnl.info.selectPersonalInfoModHistoryListCountForMember", mbrPsnlInfoModHist);
	}
    
	/**
	 * 회원 개인정보 변경 이력 목록 조회
	 */
    public List<MemberBoResult> selectPersonalInfoModHistoryListForMember(MbrPsnlInfoModHist mbrPsnlInfoModHist) {
		return getSession1().selectList("com.plgrim.ncp.biz.psnl.info.selectPersonalInfoModHistoryListForMember", mbrPsnlInfoModHist);
	}
    
    public MbrStplatAgr selectMbrStplatThpr(Mbr mbr)  {
		// TODO Auto-generated method stub
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.agr.selectMbrStplatThpr", mbr);
	}
    
    /**
	 * 언어별 시스템 약관 조회
	 * 
	 * @param sysStplatUse
	 * @return
	 * @
	 */
    public List<SysStplat> selectSysStplatLangKindList(SysStplatUse sysStplatUse)  {
	    return getSession1().selectList("com.plgrim.ncp.biz.mbr.agr.selectSysStplatLangKindList", sysStplatUse);
	}

	/**
	 * 카테고리 별 회원 사이즈 구분 코드 조회
	 * 
	 * @param dspCtgryNo
	 * @return
	 */
	public String selectMbrSizeClfcCd(String dspCtgryNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.size.selectMbrSizeClfcCd", dspCtgryNo);
	}
    
    /**
     * 회원 사이즈 정보 조회
     * @param mbrSizeSearch
     * @return
     */
    public List<MbrSizeClfc> selectMbrSizeClfcInfo(MbrSizeClfc mbrSizeSearch) {
    	return getSession1().selectList("com.plgrim.ncp.biz.mbr.size.selectMbrSizeClfcInfo", mbrSizeSearch);
    }
    
}
