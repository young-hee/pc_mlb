package com.plgrim.ncp.biz.member.service;

import com.plgrim.ncp.biz.member.repository.MemberBaseCommandRepository;
import com.plgrim.ncp.biz.member.repository.MemberBaseSelectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.commons.auth.facebook.FacebookUserService;

@Component
public class FacebookUserServiceImpl implements FacebookUserService {
	
	@Autowired
	MemberBaseSelectRepository memberBaseSelectRepository;
	
	@Autowired
	MemberBaseCommandRepository memberBaseCommandRepository;

	@Override
	public String findPlgrimshopLoginIdByFacebookUserId(String facebookUserId, String mallId, boolean maskingYn) {
		return memberBaseSelectRepository.findPlgrimshopLoginIdByFacebookUserId(facebookUserId, mallId, maskingYn);
	}

	@Override
	public void updateMemberFacebookUserId(String mbrNo, String facebookUserId) {
		memberBaseCommandRepository.updateMemberFacebookUserId(mbrNo, facebookUserId);
	}

	@Override
	public MbrIdCntc selectFaceBookUserInfo(String mbrNo, String mbrId, String mallId) {
		return memberBaseSelectRepository.selectFaceBookUserInfo(mbrNo, mbrId, mallId);
	}
	
}
