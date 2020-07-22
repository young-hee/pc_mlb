package com.plgrim.ncp.biz.member.repository;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;

/**
 * 회원개인정보 command repository
 */
@Repository
public class MemberPersonalInfoCommandRepository extends AbstractRepository  {
 
	/**
     * 회원 개인정보 변경 이력 수정
     */
	public void updatePersonalInfoErpTrnsmisYn(MbrPsnlInfoModHist mbrPsnlInfoModHist) {
    	getSession1().insert("com.plgrim.ncp.biz.psnl.info.updatePersonalInfoErpTrnsmisYn", mbrPsnlInfoModHist);
    }

    public void insertMergeMbrStplatAgr(MbrStplatAgr agr){
		getSession1().update("com.plgrim.ncp.biz.mbr.agr.insertMergeMbrStplatAgr", agr);
	}
    
    /** 회원 개인정보 변경 이력 삭제.*/
    public int deleteMbrPsnlInfoModHist(String mbrNo) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrPsnlInfoModHist", mbrNo);
	}
    
	/**
     * 회원 개인정보 변경 이력 등록
     */
    public int insertPersonalInfoModHistory(MbrPsnlInfoModHist mbrPsnlInfoModHist) {
    	return getSession1().insert("com.plgrim.ncp.biz.psnl.info.insertPersonalInfoModHistory", mbrPsnlInfoModHist);
    }
}
