package com.plgrim.ncp.cmp.member.fo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.biz.member.data.Ipin;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.Pcc;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.member.data.MembershipSDO;

/**
 * 회원인증정보 command interface
 */
public interface MemberAuthFOComponent {

    /**
     * 멤버십 인증
     *
     * @param systemPK
     * @param dto
     * @return
     */
    boolean insertMemberCrtfc(SystemPK systemPK, MemberFoDTO dto);

    /**
     * 현재 세션정보 업데이트
     *
     * @param userDetail
     */
    public void updateAuthentication(SecurityUserDetail userDetail);

    /**
     * 휴대전화 인증 세팅
     *
     * @param reqPccNum
     * @param day
     * @param srv
     * @param device
     * @return
     */
    public Pcc setPCC(String reqPccNum, String day, String srv, String device);

    /**
     * 휴대전화 인증 완료 값 세팅
     *
     * @param reqPccNum
     * @param retInfo
     * @param successFlag
     * @return
     */
    public Pcc getPCC(String reqPccNum, String retInfo, boolean successFlag);

    /**
     * IPIN 인증 세팅
     *
     * @param reqIpinNum
     * @param srv
     * @return
     */
    public Ipin setIPIN(String reqIpinNum, String srv);

    /**
     * IPIN 인증 완료 값 세팅
     *
     * @param reqIpinNum
     * @param retInfo
     * @return
     */
    public Ipin getIPIN(String reqIpinNum, String retInfo);


    /**
     * 로그인시 멤버 인증 절차
     */
    public void mbrAuthentication();

    /**
     * 회원 가입 후에 로그인 처리하고 호출되는 함수
     * - 로그인 로그, ERP 로그인 성공 전송
     * - 장바구니 병합, 로그인 혜택 처리
     * 
     * @param request
     */
    public void processAfterAddAndLogin(HttpServletRequest request);
    
    /**
     * di값으로 가입 되어 있는지 체크
     *
     * @param di
     * @param joinMallId
     * @param maskingYn
     * @return MemberFoResult
     */
    public MemberFoResult checkMemberCertify(String di, String joinMallId, String maskingYn);

    /**
     * ERP로 DI, CI값을 보내 회원가입이 가능한지 체크
     *
     * @param systemPK
     * @param mbrCrtfc
     * @return MembershipSDO
     */
    public MembershipSDO checkMemberJoinErp(SystemPK systemPK, MbrCrtfc mbrCrtfc);
    
	/**
	 * 탈퇴한 회원 정보 조회
	 * 
	 * @param mbr
	 * @return MemberFoResult
	 * @throws Exception
	 */
	public MemberFoResult selectSecessionMbrInfo(Mbr mbr);
	
	/**
	 * 본인인증 후 세팅
	 * 
	 * @param model
	 * @param pk
	 * @param srv
	 * @param di
	 * @param ci
	 */
	public void setModelForCertificationAfter(Model model, SystemPK pk, String srv, String di, String ci);

	/**
     * ERP번호로 회원목록조회
     * 
     * @param erpCstmrNo
     * @return List<Mbr>
     */
    public List<Mbr> selectMemberByErpCstmrNo(String erpCstmrNo);
	
}
