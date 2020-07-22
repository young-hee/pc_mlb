package com.plgrim.ncp.cmp.member.fo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.inf.InfMbrGrdInfoRecptn;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefDetail;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefPymntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGftExchg;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MemberResultDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.result.MemberBenefitApiResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.result.MemberReserveResult;
import com.plgrim.ncp.biz.member.result.MypageCpnFoResult;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.member.data.MemberMileageInfoSDO;
import com.plgrim.ncp.interfaces.member.data.MembershipCardSDO;

/**
 * 회원혜택정보 command interface..
 */
public interface MemberBenefitFOComponent {

    /**
     * 추천인 이벤트 제공
     *
     * @param promotionBoDTO
     * @param memberFoDTO
     * @param pk
     */
    void addBenefitRecommandEvent(PromotionBoDTO promotionBoDTO, MemberFoDTO memberFoDTO, SystemPK pk);

    /**
     * 회원 혜택 공통 API
     *
     * @param systemPK
     * @param bnefSectCd
     * @param issuedOnOffCouponMapList	[온오프라인 쿠폰 목록, 없으면 null]
     * @return
     */
    public MemberBenefitApiResult callMemberBenefit(SystemPK systemPK, MemberBenefitEnum.BnefSectCd bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList);

    /**
     * 회원 혜택 공통 API
     *
     * @param systemPK
     * @param bnefSectCd
     * @param issuedOnOffCouponMapList	[온오프라인 쿠폰 목록, 없으면 null]
     * @param mbr
     * @param mbrGrd [회원 등급, 없으면 null]
     * @return
     */
    public MemberBenefitApiResult callMemberBenefit(SystemPK systemPK, MemberBenefitEnum.BnefSectCd bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList, Mbr mbr, MbrGrd mbrGrd, String mallId);

    /**
     * 회원 혜택 공통 API
     * 
     * @param systemPK
     * @param bnefSectCd
     * @param issuedOnOffCouponMapList	[온오프라인 쿠폰 목록, 없으면 null]
     * @param mbr
     * @param mbrGrd [회원 등급, 없으면 null]
     * @param mallId
     * @param mbrGrdModDt [ERP 발급일]
     * @return
     */
    public MemberBenefitApiResult callMemberBenefit(SystemPK systemPK, MemberBenefitEnum.BnefSectCd bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList, Mbr mbr, MbrGrd mbrGrd, String mallId, Date mbrGrdModDt);

    /**
     * 회원 웹 적립금 건수 Count
     *
     * @param mbrWebpntHist
     * @return
     */
    public long selectMemberWebPointListCnt(MbrWebpntHist mbrWebpntHist);

    /**
     * 회원 로그인시 웹 포인트 적립
     * 1일 1회
     *
     * @param mbr
     */
    public void insertWebPointByLogin(Mbr mbr);

    /**
     * 회원 혜택 단건 조회
     *
     * @param mbrBnefDetail
     * @return
     */
    public MbrBnefDetail selectMemberBenefitInfo(MbrBnefDetail mbrBnefDetail);

    /**
     * MyPage 회원 발급 쿠폰 목록 조회.
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return the coupon list count for member
     * @ the exception
     * @since 2015. 5. 06
     */
    public Page<MypageCpnFoResult> getMyCouponList(SystemPK systemPK, MypageFoDTO dto, PageParam pageParam);

    /**
     * MyPage 회원 발급 쿠폰 목록 조회.(MLB,SA)
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return the coupon list count for member
     * @ the exception
     * @since 2015. 5. 06
     */
    public Page<MypageCpnFoResult> getMyCouponListWithMall(SystemPK systemPK, MypageFoDTO dto, PageParam pageParam);

    /**
     * MyPage 회원 발급 쿠폰 이력 목록 조회.
     *
     * @param systemPK
     * @param dto
     * @param pageParam
     * @return
     */
    public Page<MypageCpnFoResult> getMyCouponHistList(SystemPK systemPK, MypageFoDTO dto, PageParam pageParam);

    /**
     * MyPage 회원 발급 쿠폰 이력 목록 조회.
     *
     * @param systemPK
     * @param dto
     * @param pageParam
     * @return
     */
    public Page<MypageCpnFoResult> getMyCouponHistListWithMall(SystemPK systemPK, MypageFoDTO dto, PageParam pageParam);

    /**
     * MyPage 멤버쉽 포인트 조회.
     *
     * @param systemPK
     * @param myPageFoDTO
     * @return
     */
    public MemberResultDTO getMembershipPointList(SystemPK systemPK, MypageFoDTO myPageFoDTO);

    /**
     * 마이페이지 - P포인트 내역 조회.
     *
     * @param systemPK
     * @param mbrWebpntHist
     * @param loginId
     * @param pageParam
     * @return
     */
    public Page<MemberFoResult> getMyPurpleCoinList(SystemPK systemPK, MbrWebpntHist mbrWebpntHist, String loginId,
                                                    PageParam pageParam);

    /**
     * 마이페이지 - P포인트 내역 조회.
     *
     * @param systemPK
     * @param mbrWebpntHist
     * @param loginId
     * @param pageParam
     * @return
     */
    public Page<MemberFoResult> getMyPurpleCoinListForMall(SystemPK systemPK, MbrWebpntHist mbrWebpntHist, String loginId,
    		PageParam pageParam);

    /**
     * MyPage 쿠폰 적용대상 상품조회.
     *
     * @param systemPK
     * @param dto
     * @param pageParam
     * @return
     */
    public Page<MypageCpnFoResult> getMyCouponGoodList(SystemPK systemPK, MypageFoDTO dto, PageParam pageParam);

    /**
     * MyPage 회원 발급 쿠폰 목록 조회.
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public List<MypageCpnFoResult> getMyCouponComboList(SystemPK systemPK, MypageFoDTO dto);

    /**
     * 수신동의 유도 캠페인쿠폰 카운트
     *
     * @param mbrIssuCpn
     * @return
     */
    public int selectMyCampaginCouponCount(MbrIssuCpn mbrIssuCpn);

    /**
     * 나의 쿠폰 카운드
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public int getMyCouponCnt(SystemPK systemPK, MypageFoDTO dto);

    /**
     * 나의 쿠폰 카운드(MLB,SA)
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public int getMyCouponCntWithMall(SystemPK systemPK, MypageFoDTO dto);


    /**
     * 멤버쉽 포인트/마일리지 조회
     */
    public MemberReserveResult getMemberPointMilageRemainAmount(String erpNo);

    /**
     * 회원 혜택 지급 이력 Count
     *
     * @param mbrBnefPymntHist
     * @return
     * @
     */
    int selectMbrBnefPymntHistCount(MbrBnefPymntHist mbrBnefPymntHist);

    /**
     * 회원 보유쿠폰 카운트
     *
     * @param mbrIssuCpn
     * @return
     */
    public long selectMyCouponsCount(MbrIssuCpn mbrIssuCpn);

    /**
     * @param prmCpnGftExchg
     * @return
     */
    public PrmCpnGftExchg selectEscPrmCpnGftExchg(PrmCpnGftExchg prmCpnGftExchg);

	/**
	 * P포인트 현황 조회
	 */
	public MbrWebpntHistExtend selectWebPointInfo(MbrWebpntHist mbrWebpntHist) ;

	/**
     * ERP로 CID, HISTORY_YN, BRAND 값을 보내 마일리지 정보 조회
     *
     * @param systemPK
     * @param mypageFoDTO
     * @return MemberMileageInfoSDO
     */
    public MemberMileageInfoSDO selectMileageInfo(SystemPK systemPK, MypageFoDTO mypageFoDTO);

	/**
     * ERP로 CID, HISTORY_YN, BRAND 값을 보내 마일리지 정보 조회
     *
     * @param systemPK
     * @param mypageFoDTO
     * @return MembershipCardSDO
     */
    public MembershipCardSDO addMembershipCard(SystemPK systemPK, MypageFoDTO mypageFoDTO);

    /**
     * 멤버쉽카드 등록 (인증번호 추가)
     *
     * @param systemPK
     * @param mypageFoDTO
     * @return MembershipCardSDO
     */
    public MembershipCardSDO addMembershipCardOnCertify(SystemPK systemPK, MypageFoDTO mypageFoDTO);

    /**
     * 가상계좌 결재대기 포인트
     * @param  mbrNo
     * @return unityPntUseSumAmt
     */
    public int selectOrdPntUseAmtSum(String mbrNo) ;

    public void memberGradeIssuCpn(InfMbrGrdInfoRecptn infMbrGrdInfoRecptn);
 

}
