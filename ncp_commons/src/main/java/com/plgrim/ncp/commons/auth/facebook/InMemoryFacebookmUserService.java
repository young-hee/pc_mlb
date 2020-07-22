package com.plgrim.ncp.commons.auth.facebook;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;

public class InMemoryFacebookmUserService implements FacebookUserService{
	@Override
	public String findPlgrimshopLoginIdByFacebookUserId(String facebookUserId, String mallId, boolean maskingYn) {
		return "narusas77";
	}

	@Override
	public void updateMemberFacebookUserId(String mdrNo, String facebookUserId) {
		
	}
	
	@Override
	public MbrIdCntc selectFaceBookUserInfo(String mbrNo, String mbrId, String mallId) {
		return null;
	}
}
