package com.plgrim.ncp.biz.member.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;

import org.springframework.stereotype.Repository;

/**
 * 회원혜택정보 command repository
 */
@Repository
public class MemberBenefitCommandRepository extends AbstractRepository  {
 

	/**
     * 포인트 수정
     *
     * @param mbrWebpntHist
     * @return
     */
	public int updateWebPoint(MbrWebpntHist mbrWebpntHist) {
        return getSession1().update("com.plgrim.ncp.biz.mbr.webPnt.updateWebPoint", mbrWebpntHist);
    }
    
    /**
     * 포인트 적립/차감
     * 
     * @param mbrWebpntHist
     * @return
     */
	public int insertWebPoint(MbrWebpntHist mbrWebpntHist)  {
        return getSession1().update("com.plgrim.ncp.biz.mbr.webPnt.insertWebPnt", mbrWebpntHist);
    }
    
    /** 회원 포인트 연동 이력 삭제.*/
    public int deleteMbrPntIntrlckHist(String mbrNo) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrPntIntrlckHist", mbrNo);
	}
    
    /** 회원 적립금 이력 삭제.*/
    public int deleteMbrSavMnyHist(String mbrNo) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrSavMnyHist", mbrNo);
	}
    
	/**
	 * 	회원 쿠폰을 등록한다.
	 * 	@param mbrIssuCnp [설명]
	 *  @return Int [설명]
	 *  @since 2016. 11. 16
	 */
    public int insertMemberIssueCpn( MbrIssuCpn mbrIssuCpn ) {
		return getSession1().update("com.plgrim.ncp.biz.mbr.insertMemberIssueCPN", mbrIssuCpn);
	}
	
	/**
	 * 클레임 환불계좌 등록
	 * @param mbrRfdBnkAcct
	 */
	public void insertMbrRfdAcc(MbrRfdBnkAcct mbrRfdBnkAcct) {
		getSession1().insert("com.plgrim.ncp.biz.mbr.rfd.bnk.acct.insertMbrRfdAcc", mbrRfdBnkAcct);
    }
	
	/**
     * 포인트 적립/차감(교환 클레임 완료 처리시)
     * 
     * @param mbrWebpntHist
     * @return
     */
	public int insertExchgWebPoint(MbrWebpntHist mbrWebpntHist) {
        return getSession1().update("com.plgrim.ncp.biz.mbr.webPnt.insertExchgWebPnt", mbrWebpntHist);
    }
	
	/**
	 * 포인트 사용기간 연장
	 * 
	 * @param mbrWebpntHist
	 * @return
	 */
	public int updateWebPointExtension(MbrWebpntHist mbrWebpntHist) {
        return getSession1().update("com.plgrim.ncp.biz.mbr.webPnt.updateWebPointExtension", mbrWebpntHist);
    }
}
