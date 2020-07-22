package com.plgrim.ncp.biz.member.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefAplTgt;
import com.plgrim.ncp.biz.member.data.MemberBenefitBoDTO;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;

/**
 * 회원혜택API command repository 
 */
@Repository
public class MemberBenefitApiCommandRepository extends AbstractRepository  {
	
    @Autowired
    private IdGenService idGenService;
    
	@Autowired
    @Qualifier("sessionTemplate1")
    private SqlSession sqlSession1;	
	
	/**
	 * 회원혜택 등록.
	 */
	public void insertMemberBenefit(MemberBenefitBoDTO dto) {
		try {
			long bnefSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_MBR_BNEF", DatabaseType.ORACLE);
			
			dto.setBnefSn(bnefSn);
			
			getSession1().insert("com.plgrim.ncp.biz.mbr.benefit.insertMemberBenefit", dto);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
    }
	
	/**
	 * 회원혜택 수정.
	 */
	public void updateMemberBenefit(MemberBenefitBoDTO dto) {
		getSession1().update("com.plgrim.ncp.biz.mbr.benefit.updateMemberBenefit", dto);
    }
	
    /**
     * 회원혜택 적용대상 등록.
     */
    public void insertMemberBenefitApplyTarget(MbrBnefAplTgt mbrBnefAplTgt) {
		try {
	        Map<String, Object> conditions = Maps.newHashMap();    
	        conditions.put("bnef_sn", mbrBnefAplTgt.getBnefSn());

	        int aplTurn = idGenService.generateDBOrder(sqlSession1, "MBR_BNEF_APL_TGT", "APL_TURN", conditions, DatabaseType.ORACLE);
	        mbrBnefAplTgt.setAplTurn(aplTurn);

	        getSession1().insert("com.plgrim.ncp.base.insertMbrBnefAplTgt", mbrBnefAplTgt);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    /**
     * 회원혜택 지급혜택 등록.
     */
	public void insertMemberBenefitDetail(MemberBenefitBoDTO dto) {
		getSession1().insert("com.plgrim.ncp.biz.mbr.benefit.insertMemberBenefitDetail", dto);
    }
	
    /**
     * 회원혜택 적용대상 삭제.
     */
    public void deleteMemberBenefitApplyTarget(MemberBenefitBoDTO dto) {
        getSession1().insert("com.plgrim.ncp.biz.mbr.benefit.deleteMemberBenefitApplyTarget", dto);
    }
    
	/**
	 * 회원혜택 지급혜택 수정.
	 */
	public void updateMemberBenefitDetail(MemberBenefitBoDTO dto) {
		getSession1().update("com.plgrim.ncp.biz.mbr.benefit.updateMemberBenefitDetail", dto);
    }    
    
    /**
     * 회원혜택 지급혜택 삭제.
     */
    public void deleteMemberBenefitDetail(MemberBenefitBoDTO dto) {

        getSession1().insert("com.plgrim.ncp.biz.mbr.benefit.deleteMemberBenefitDetail", dto);
    }

	/**
	 * 회원혜택 상태 수정.
	 */
	public void updateMemberBenefitStatus(MemberBenefitBoDTO dto) {
		getSession1().update("com.plgrim.ncp.biz.mbr.benefit.updateMemberBenefitStatus", dto);
    } 
	
	/**
	 * 회원혜택 지급혜택 상태 수정. 
	 */
	public void updateMemberBenefitDtlStatus(MemberBenefitBoDTO dto) {
		getSession1().update("com.plgrim.ncp.biz.mbr.benefit.updateMemberBenefitDtlStatus", dto);
    } 	
        
}