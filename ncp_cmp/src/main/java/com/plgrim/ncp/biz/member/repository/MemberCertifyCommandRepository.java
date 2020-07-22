package com.plgrim.ncp.biz.member.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;

import org.springframework.stereotype.Repository;

/**
 * 회원인증정보 command repository
 */
@Repository
public class MemberCertifyCommandRepository extends AbstractRepository  {
	 
	/**
	 * 임직원 인증 후 회원정보 업데이트
	 * @param mbr
	 * @
	 */
	public void updateEmpInfo(Mbr mbr) {
		getSession1().update("com.plgrim.ncp.biz.mbr.updateEmpInfo", mbr);
	}
	
	public int insertMemberCrtfc(MemberFoDTO dto) {
	    // TODO Auto-generated method stub
		MbrCrtfc mbrCrtfc = dto.getMbrCrtfc();
	    return getSession1().insert("com.plgrim.ncp.biz.mbr.crt.insertMbrCrtfc", mbrCrtfc);
    }
	
	/**
	 * 임직원 인증 후 인증 완료여부 업데이트
	 * @param mbr
	 * @
	 */
	public void updateEmpEnfrcCrtfcYn(Mbr mbr) {
		getSession1().update("com.plgrim.ncp.biz.mbr.crt.updateEmpEnfrcCrtfcYn", mbr);
	}
	
	/**
	 * 인증 완료한 회원의 사번과 같은 SSO그룹으로 인증코드 발송한 계정이 있을 경우 인증 이력 삭제
	 * @param mbr
	 * @
	 */
	public void deleteSsoEmpEnfrcCrtfc(Mbr mbr) {
		getSession1().delete("com.plgrim.ncp.biz.mbr.crt.deleteSsoEmpEnfrcCrtfc", mbr);
	}
	
	/**
	 * 회원 ID 연계 대상 탈퇴 회원 업데이트
	 * @param mbrNo
	 * @return
	 */
	public int updateMbrIdCntcDeleteYn(String mbrNo) {
		getSession1().update("com.plgrim.ncp.biz.mbr.crt.updateMbrIdCntcDeleteYn", mbrNo);
		return getSession1().insert("com.plgrim.ncp.biz.mbr.crt.insertMbrIdCntcHist", mbrNo);
	}

}
