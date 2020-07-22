package com.plgrim.ncp.biz.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.biz.member.repository.MemberCertifySelectRepository;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원인증정보 select service
 */
@Service
@Slf4j
public class MemberCertifySelectService extends AbstractService {
	 
	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	

	@Autowired
	MemberCertifySelectRepository memberCertifySelectRepository;
	

	public MemberFoResult checkMemberCertify(String di, String joinMallId, String maskingYn){
		MemberFoResult memberFoResult = memberCertifySelectRepository.checkMemberCertify(di, joinMallId);
		if(maskingYn != null && "Y".equals(maskingYn)) {
			if(memberFoResult != null && memberFoResult.getMbr() != null && memberFoResult.getMbr().getMbrId() != null) {
				Mbr mbr = memberFoResult.getMbr();
		    	String mbrId = mbr.getMbrId();
		    	mbrId = mbrId.substring(0, mbrId.length()-2) + "**";
				memberFoResult.getMbr().setMbrId(mbrId);
			}
		}
		return memberFoResult;
	}
	
	/**
	 * 회원 이메일 인증
	 * 유효한 tokenid 정보 조회.
	 * */
	public MbrCrtfc selectMbrEmailCrtfc(String mbrEmailToken) {
	    return memberCertifySelectRepository.selectMbrEmailCrtfc(mbrEmailToken);
    }
	
	/**
     * SSO Group Code 조회
     * @param mallId 몰ID
     * @return SSO Group Code
     */
    public String getSsoGrpCd(String mallId) {
    	return memberCertifySelectRepository.getSsoGrpCd(mallId);
    }
    
    /**
	 * 인증 정보 조회.
	 *
	 * @param mbrNo [설명]
	 * @param mbrCrtfcTpCd [설명]
	 * @return Mbr crtfc [설명]
	 * @since 2015. 5. 12
	 */
    public MemberBoResult selectMemberCertification(String mbrNo, String mbrCrtfcTpCd, String mbrCrtfcYn, String maskingYn) {
    	MbrCrtfc mc = new MbrCrtfc();
    	mc.setMbrNo(mbrNo);
    	mc.setMbrCrtfcTpCd(mbrCrtfcTpCd);
    	mc.setMbrCrtfcYn(mbrCrtfcYn);
    	mc.setMaskingYn(maskingYn);
		return memberCertifySelectRepository.selectMemberCertification(mc);
	}
    
	/**
	 * AdapterHeader 값 설정
	 * @return
	 */
	private AdapterHeader setAdapterHeader(){
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");

		return adapterHeader;
	}
	
	/**
     * AdapterHeader 값 설정
     * 
     * @param systemPK
     * @return
     */
    private AdapterHeader setAdapterHeader(SystemPK systemPK){
        AdapterHeader adapterHeader = new AdapterHeader();
        adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
        adapterHeader.setMallId(systemPK.getMall());
        adapterHeader.setDvcCd(systemPK.getDevice());
        adapterHeader.setLangCd(systemPK.getLang());
        return adapterHeader;
    }
}
