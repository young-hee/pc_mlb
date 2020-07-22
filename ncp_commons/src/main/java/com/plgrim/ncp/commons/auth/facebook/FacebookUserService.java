package com.plgrim.ncp.commons.auth.facebook;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;

public interface FacebookUserService {
	/**
	 * facebook user id에 해당하는 PLGRIMSHOP 회원의 mbr_id 를 반환한다.
	 */
	String findPlgrimshopLoginIdByFacebookUserId(String facebookUserId, String mallId, boolean maskingYn);

	void updateMemberFacebookUserId(String mbrNo, String facebookUserId);
	
	MbrIdCntc selectFaceBookUserInfo(String mbrNo, String mbrId, String mallId);
}
