package com.plgrim.ncp.biz.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntcExtend;
import com.plgrim.ncp.biz.member.repository.MemberBaseCommandRepository;
import com.plgrim.ncp.biz.member.repository.MemberBaseSelectRepository;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.commons.auth.naver.NaverUserService;
import com.plgrim.ncp.framework.data.SystemPK;

@Component
public class NaverUserServiceImpl implements NaverUserService<MemberFoResult> {
	@Autowired
	MemberBaseSelectRepository memberBaseSelectRepository;
	
	@Autowired
	MemberBaseCommandRepository memberBaseCommandRepository;
	
	@Autowired
	MemberInterfaceService memberInterfaceService;

	@Override
	public String findPlgrimshopLoginIdByNaverUserId(String naverUserId, String mallId, boolean maskingYn, String idCntcTpCd) {
		return memberBaseSelectRepository.findPlgrimshopLoginIdByNaverUserId(naverUserId, mallId, maskingYn, idCntcTpCd);
	}

	@Override
	public void updateMemberNaverUserId(String mbrNo, String toknId, String loginId) {
		memberBaseCommandRepository.updateMemberNaverUserId(mbrNo, toknId, loginId);
	}


	@Override
	public MbrIdCntc selectNaverUserInfo(MbrIdCntcExtend mbrIdCntcExtend) {
		return memberBaseSelectRepository.selectNaverUserInfo(mbrIdCntcExtend);
	}
	
	@Override
	public List<MemberFoResult> getMbrIdMbrIdCntc(String mbrEmail, String mallId) {
		return memberBaseSelectRepository.getMbrIdMbrIdCntc(mbrEmail, mallId);
	}
	
	@Override
	public long selectNaverConnectCount(String mbrNo, String idCntcTpCd) {
		return memberBaseSelectRepository.selectNaverConnectCount(mbrNo, idCntcTpCd);
	}
	
	@Override
	public MbrIdCntc selectNaverUdterIdInfo(String mbrNo, String idCntcTpCd) {
		return memberBaseSelectRepository.selectNaverUdterIdInfo(mbrNo, idCntcTpCd);
	}
	
	@Override
	public void updateMemberNaverUserPwIdFirstLogin(MbrIdCntc mbrIdCntc){
		memberBaseCommandRepository.updateMemberNaverUserFirstLogin(mbrIdCntc);
	}
	
	@Override
	public String naverAccessTokenValidator(SystemPK systemPK, String userAccessToken) throws Exception {
		return memberInterfaceService.naverAccessTokenValidator(systemPK, userAccessToken);
	}
}


