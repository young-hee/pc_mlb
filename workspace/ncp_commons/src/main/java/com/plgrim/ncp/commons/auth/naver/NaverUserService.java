package com.plgrim.ncp.commons.auth.naver;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntcExtend;
import com.plgrim.ncp.framework.data.SystemPK;


public interface NaverUserService<MemberFoResult> {
	/**
	 * Naver user id에 해당하는 PLGRIMSHOP 회원의 mbr_id 를 반환한다.
	 */
	String findPlgrimshopLoginIdByNaverUserId(String NaverUserId, String mallId, boolean maskingYn, String idCntcTpCd);

	void updateMemberNaverUserId(String mbrNo, String toknId, String loginId);
	
	MbrIdCntc selectNaverUserInfo(MbrIdCntcExtend mbrIdCntcExtend);
	
	List<MemberFoResult> getMbrIdMbrIdCntc(String mbrEmail, String mallId);	
	
	long selectNaverConnectCount(String mbrNo, String idCntcTpCd);
	
	MbrIdCntc selectNaverUdterIdInfo(String mbrNo, String idCntcTpCd);
	
	void updateMemberNaverUserPwIdFirstLogin(MbrIdCntc mbrIdCntc);
	
	String naverAccessTokenValidator(SystemPK systemPK, String userAccessToken) throws Exception;
}


