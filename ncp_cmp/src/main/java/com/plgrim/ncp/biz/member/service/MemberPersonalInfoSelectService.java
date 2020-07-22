package com.plgrim.ncp.biz.member.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsef;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatUse;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.biz.member.data.MemberAgreementDTO;
import com.plgrim.ncp.biz.member.repository.MemberBaseSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberPersonalInfoSelectRepository;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;

import lombok.extern.slf4j.Slf4j;
 
/**
 * 회원개인정보 select service
 */
@Service
@Slf4j
public class MemberPersonalInfoSelectService extends AbstractService {

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
		
	@Autowired
	MemberPersonalInfoSelectRepository memberPersonalInfoSelectRepository;
	
	@Autowired
	MemberBaseSelectRepository memberBaseSelectRepository;
	
	/**
	 * [회원 개인정보 이용 등록].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param usefSectCd [설명]
	 * @param usefJobCd [설명]
	 * @param inflowSn [설명]
	 * @param menuSn [설명]
	 * @param loginId [설명]
	 * @return Mbr psnl info usef [설명]
	 * @ the exception
	 * @since 2015. 6. 9
	 */
	public MbrPsnlInfoUsef setMbrPsnlInfoUsef(SystemPK systemPK, Long inflowSn, Long menuSn, String loginId) {
		long piuSn = getIdGenService().generateDBSequence(sqlSession1, "sq_mbr_psnl_info_usef", DatabaseType.ORACLE);
		Date usefDt = new Date();
		
		MbrPsnlInfoUsef mpiu = new MbrPsnlInfoUsef();
		mpiu.setPsnlInfoUsefSn(piuSn);			// 개인정보 이용 일련번호
		mpiu.setUsefDt(usefDt);  	   	    	// 이용 일자
		mpiu.setLangCd(systemPK.getLang()); 	// 언어 코드
		mpiu.setMallId(systemPK.getMall()); 	// 몰 ID
		mpiu.setDvcCd(systemPK.getDevice());	// 디바이스 코드
		mpiu.setBoSiteId(systemPK.getSite());   // BO 사이트 ID
		mpiu.setInflowSn(inflowSn);             // 유입 일련번호
		mpiu.setMenuSn(menuSn);					// 메뉴 일련번호
		mpiu.setRegtrId(loginId);           	// 등록자 ID
		mpiu.setUdterId(loginId);           	// 수정자 ID
		
		
		return mpiu;
	}
	
	/**
	 * 회원 개인정보 변경 목록 건수 조회.
	 *
	 * @param mpimh [회원 개인정보 변경 Entity]
	 * @return Long [설명]
	 * @ the exception
	 * @since 2015. 6. 9
	 */
	public long selectPersonalInfoModHistoryListCountForMember(MbrPsnlInfoModHist mpimh)  {
		return memberPersonalInfoSelectRepository.selectPersonalInfoModHistoryListCountForMember(mpimh);
	}
	
	/**
	 * 회원 개인정보 변경 목록 조회.
	 *
	 * @param mpimh [회원 개인정보 변경 Entity]
	 * @return List [설명]
	 * @ the exception
	 * @since 2015. 6. 9
	 */
	public List<MemberBoResult> selectPersonalInfoModHistoryListForMember(MbrPsnlInfoModHist mpimh)  {
		return memberPersonalInfoSelectRepository.selectPersonalInfoModHistoryListForMember(mpimh);
	}
	
	/**
	 * 회원 선택적동의 리스트
	 * @param mbrNo 회원
	 * @return List<MemberAgreementDTO>
	 */
	public List<MemberAgreementDTO> selectMemberOptionalAgreeList(String mbrNo) {
		return memberPersonalInfoSelectRepository.selectMemberOptionalAgreeList(mbrNo);
	}
	
	public MbrStplatAgr selectMbrStplatThpr(Mbr mbr)  {
		return memberPersonalInfoSelectRepository.selectMbrStplatThpr(mbr);
	}
	
	/**
	 * 언어별 시스템 약관 조회
	 * 
	 * @param sysStplatUse
	 * @return
	 * @
	 */
	public List<SysStplat> selectSysStplatLangKindList(SysStplatUse sysStplatUse)  {
        return memberPersonalInfoSelectRepository.selectSysStplatLangKindList(sysStplatUse);
    }

	/**
	 * 카테고리 별 회원 사이즈 구분 코드 조회
	 * @param dspCtgryNo
	 * @return
	 */
	public String selectMbrSizeClfcCd(String dspCtgryNo) {
		String mbrSizeClfcCd = memberPersonalInfoSelectRepository.selectMbrSizeClfcCd(dspCtgryNo);
		
		if (StringUtils.isEmpty(mbrSizeClfcCd)) {
			mbrSizeClfcCd = MemberEnum.MbrSizeClfc.ALL_BASE_SIZE.toString();
		}
		
		return mbrSizeClfcCd;
	}
	
	/**
	 * 회원 사이즈 정보 조회
	 * @param search
	 * @return
	 */
	public List<MbrSizeClfc> selectMbrSizeClfcInfo(MbrSizeClfc search) {
		return memberPersonalInfoSelectRepository.selectMbrSizeClfcInfo(search);
	}
}
