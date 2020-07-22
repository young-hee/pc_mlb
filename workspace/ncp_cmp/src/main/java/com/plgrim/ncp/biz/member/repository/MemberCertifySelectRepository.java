package com.plgrim.ncp.biz.member.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;

/**
 * 회원인증정보 select repository
 */
@Repository
public class MemberCertifySelectRepository extends AbstractRepository  {
 

	public MemberFoResult checkMemberCertify(String di, String joinMallId) {
		Map<String, String> param  = new HashMap<String, String>();
		param.put("di", di);
		param.put("joinMallId", joinMallId);
		List<MemberFoResult> mbrList = getSession1().selectList("com.plgrim.ncp.biz.mbr.crt.checkMemberCertify", param);
		if(mbrList != null && mbrList.size() > 0) {
			return mbrList.get(0);
		}
		else {
			return null;
		}
	}
	
	/**
     * 회원 이메일 인증 정보 조회.
     *
     * @param mbrEmailToken [설명]
     * @return Mbr crtfc [설명]
     * @since 2015. 5. 12
     */
	public MbrCrtfc selectMbrEmailCrtfc(String mbrEmailToken) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.crt.selectMbrEmailCrtfc", mbrEmailToken);
    }
    
    /**
     * SSO Group Code 조회
     * @param mallId 몰ID
     * @return SSO Group Code
     */
    public String getSsoGrpCd(String mallId) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.getSsoGrpCd", mallId);
    }
    
    /**
	 * 회원 인증 정보 조회.
	 *
	 * @param mbrCrtfc [설명]
	 * @return Mbr crtfc [설명]
	 * @since 2015. 5. 12
	 */
    public MemberBoResult selectMemberCertification(MbrCrtfc mbrCrtfc) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.crt.selectMemberCertification", mbrCrtfc);
	}
	
	/**
	 * 임시 토큰아이디 조회
	 * */
    public String selectEmailTokenId(String mbrNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.crt.selectEmailTokenId", mbrNo);
    }
}
