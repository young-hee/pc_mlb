package com.plgrim.ncp.biz.member.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGftExchg;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.result.MypageCpnFoResult;
import com.plgrim.ncp.biz.member.result.PurpleCoinMemberBoResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * 회원혜택정보 select repository..
 */
@Repository
public class MemberBenefitSelectRepository extends AbstractRepository  {

	/**
     * 상품평으로 인한 적립정보 조회
     */
	public List<MbrWebpntHistExtend> selectWebPointInfoByGodEvl(MbrWebpntHist mbrWebpntHist)  {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.webPnt.selectWebPointInfoByGodEvl", mbrWebpntHist);
    }

	/**
     * 상품평으로 인한 적립정보 조회 (MLB, SAM)
     */
	public List<MbrWebpntHistExtend> selectWebPointInfoByCriteria(MbrWebpntHist mbrWebpntHist)  {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.webPnt.selectWebPointInfoByCriteria", mbrWebpntHist);
    }
	
    /**
     * 차감해야하는 P포인트 목록 조회
     */
	public List<MbrWebpntHistExtend> listWebPointDdctList(MbrWebpntHist mbrWebpntHist)  {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.webPnt.listWebPointDdctList", mbrWebpntHist);
    }

    /**
     * 사용 P포인트 목록 조회
     */
    public List<MbrWebpntHistExtend> listWebPointUseList(MbrWebpntHist mbrWebpntHist)  {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.webPnt.listWebPointUseList", mbrWebpntHist);
    }

    /**
     * 적립 P포인트 목록 조회
     */
    public List<MbrWebpntHistExtend> listWebPointAccmlList(MbrWebpntHist mbrWebpntHist)  {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.webPnt.listWebPointAccmlList", mbrWebpntHist);
    }

    /**
     * 잔여 P포인트 목록 조회
     */
    public List<MbrWebpntHistExtend> listWebPointRemndrList(MbrWebpntHist mbrWebpntHist)  {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.webPnt.listWebPointRemndrList", mbrWebpntHist);
    }

    /**
     * P포인트 현황 조회
     */
    public MbrWebpntHistExtend selectWebPointInfo(MbrWebpntHist mbrWebpntHist)  {
    	//MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
		//mbrWebpntHist.setMbrNo(mbrNo);
        return getSession1().selectOne("com.plgrim.ncp.biz.mbr.webPnt.selectWebPointInfo", mbrWebpntHist);
    }

    /**
     * 마이페이지 - P포인트내역조회
     */
    public Page<MemberFoResult> selectMyPurpleCoinList(MbrWebpntHist mbrWebpntHist , PageParam pageParam) {
        RepositoryHelper.makePageEntityIndex(mbrWebpntHist, pageParam);
        List<MemberFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mbr.webPnt.selectMyPurpleCoinList", mbrWebpntHist);
        int totalRow = 0;
        if(results != null && results.size() > 0) {
        	totalRow = Integer.parseInt(String.valueOf(results.get(0).getMbrWebpntHistExt().getTotalRow()));
        }

        return new PageImpl<MemberFoResult>(results, pageParam.getPageable(), totalRow);
    }

    /**
     * 마이페이지 - P포인트내역조회
     */
    public Page<MemberFoResult> selectMyPurpleCoinListForMall(MbrWebpntHist mbrWebpntHist , PageParam pageParam) {
    	RepositoryHelper.makePageEntityIndex(mbrWebpntHist, pageParam);
    	List<MemberFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mbr.webPnt.selectMyPurpleCoinListForMall", mbrWebpntHist);
    	int totalRow = 0;
    	if(results != null && results.size() > 0) {
    		totalRow = Integer.parseInt(String.valueOf(results.get(0).getMbrWebpntHistExt().getTotalRow()));
    	}

    	return new PageImpl<MemberFoResult>(results, pageParam.getPageable(), totalRow);
    }

    /**
     * My 쿠폰 목록 조회.
     *
     * @param mbrIssuCpn [설명]
     * @return List [설명]
     * @ the exception
     * @since 2015. 5. 6
     */
    public Page<MypageCpnFoResult> selectMyCouponList(MypageFoDTO dto ,  PageParam pageParam)  {

        RepositoryHelper.makePageEntityIndex(dto, pageParam);
        List<MypageCpnFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.issu.cpn.selectMyCouponList", dto);
        Integer totalRow =  getSession1().selectOne("com.plgrim.ncp.biz.issu.cpn.selectMyCouponCnt", dto);
        return new PageImpl<MypageCpnFoResult>(results, pageParam.getPageable(), totalRow);

    }

    /**
     * My 쿠폰 이력 목록 조회.
     *
     * @param mbrIssuCpn [설명]
     * @return List [설명]
     * @ the exception
     * @since 2015. 5. 6
     */
    public Page<MypageCpnFoResult> selectMyCouponHistList(MypageFoDTO dto ,  PageParam pageParam)  {

        RepositoryHelper.makePageEntityIndex(dto, pageParam);
        List<MypageCpnFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.issu.cpn.selectMyCouponHistList", dto);
        long totalRow =  getSession1().selectOne("com.plgrim.ncp.biz.issu.cpn.countMyCouponHistList", dto);
        return new PageImpl<MypageCpnFoResult>(results, pageParam.getPageable(), totalRow);

    }

    /**
     * My 쿠폰 적용가능 상품목록 조회.
     *
     * @param mbrIssuCpn [설명]
     * @return List [설명]
     * @ the exception
     * @since 2015. 5. 6
     */
    public Page<MypageCpnFoResult> selectMyCouponGoodList(MypageFoDTO dto ,  PageParam pageParam)  {

        RepositoryHelper.makePageEntityIndex(dto, pageParam);
        List<MypageCpnFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.issu.cpn.selectMyCouponGoodList", dto);
        long totalRow =  getSession1().selectOne("com.plgrim.ncp.biz.issu.cpn.countMyCouponGoodList", dto);
        return new PageImpl<MypageCpnFoResult>(results, pageParam.getPageable(), totalRow);

    }

    /**
     * My 쿠폰 목록 조회.
     *
     * @param mbrIssuCpn [설명]
     * @return List [설명]
     * @ the exception
     * @since 2015. 5. 6
     */
    public List<MypageCpnFoResult> selectMyCouponComboList(MypageFoDTO dto)  {
        return getSession1().selectList("com.plgrim.ncp.biz.issu.cpn.selectMyCouponComboList", dto);
    }

	/** 회원 보유쿠폰 카운트 */
	public long selectMyCouponsCount(MbrIssuCpn mbrIssuCpn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyCouponsCount", mbrIssuCpn);
	}

	/** 수신동의 유도 캠페인 쿠폰 발급 카운트 */
	public int selectMyCampaginCouponCount(MbrIssuCpn mbrIssuCpn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyCampaginCouponCount", mbrIssuCpn);
	}

	public int selectMyCouponCnt(MypageFoDTO dto)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.issu.cpn.selectMyCouponCnt", dto);
    }

	public PrmCpnGftExchg selectEscPrmCpnGftExchg(PrmCpnGftExchg prmCpnGftExchg) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectEscPrmCpnGftExchg",prmCpnGftExchg);
	}

	public long selectMyCouponCount(MemberBoDTO dto)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.issu.cpn.selectMyCouponCount", dto);
    }

    public List<MypageCpnFoResult> selectMyOnOffCouponList(MypageFoDTO dto)  {
        return getSession1().selectList("com.plgrim.ncp.biz.issu.cpn.selectMyOnOffCouponList", dto);
    }

    /**
     * 앱다운로드 포인트 카운트.
     *
     * @param memberBoDTO [설명]
     * @return Long [설명]
     * @since 2015. 8. 15
     */
    public long selectAppDownloadPntListCount(MemberBoDTO memberBoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectAppDownloadPntListCount", memberBoDTO);
	}

	/**
	 * 앱다운로드 포인트 카운트목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 8. 15
	 */
    public List<MemberBoResult> selectAppDownloadPntList(MemberBoDTO memberBoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectAppDownloadPntList", memberBoDTO);
	}

    /**
	 * 앱다운로드 포인트 카운트목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 8. 15
	 */
    public List<Map<String, Object>> selectAppDownloadPntListExcel (MemberBoDTO memberBoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectAppDownloadPntListExcel", memberBoDTO);
	}

    /**
     * 회원 발행 쿠폰 목록 건수 조회.
     *
     * @param mbrIssuCpn [설명]
     * @return Long [설명]
     * @ the exception
     * @since 2015. 4. 21
     */
    public long selectCouponListCountForMember(MemberBoDTO dto)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.issu.cpn.selectCouponListCountForMember", dto);
    }

    /**
     * 회원 발행 쿠폰 목록 조회.
     *
     * @param mbrIssuCpn [설명]
     * @return List [설명]
     * @ the exception
     * @since 2015. 4. 21
     */
    public List<MemberBoResult> selectCouponListForMember(MemberBoDTO dto)  {
        return getSession1().selectList("com.plgrim.ncp.biz.issu.cpn.selectCouponListForMember", dto);
    }

    /**
     * 회원 발행 쿠폰 엑셀 조회.
     *
     * @param mbrIssuCpn [설명]
     * @return List [설명]
     * @ the exception
     * @since 2015. 4. 21
     */
    public List<Map<String, Object>> selectCouponListExcelForMember(MemberBoDTO dto)  {
        return getSession1().selectList("com.plgrim.ncp.biz.issu.cpn.selectCouponListExcelForMember", dto);
    }

	// ################################################################################################################
	// BackOffice End
	// ################################################################################################################



    /**
     * 주문번호 조회
     * @param params
     * @return
     */
    public String selectOrdNo(String rctNo){
        return getSession1().selectOne("com.plgrim.ncp.biz.mbr.reserve.selectOrdNo", rctNo );

    }

    /**
     * 회원 웹 적립금 건수 조회.
     */
    public long selectMemberPurpleCoinListCount(MbrWebpntHist mbrWebpntHist) {
        return getSession1().selectOne("com.plgrim.ncp.biz.mbr.webPnt.selectMemberPurpleCoinListCount", mbrWebpntHist);
    }

    /**
     * 회원 웹 적립금 목록 조회.
     */
    public List<MemberBoResult> selectMemberPurpleCoinList(MbrWebpntHist mbrWebpntHist) {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.webPnt.selectMemberPurpleCoinList", mbrWebpntHist);
    }


    /**
     * 회원 웹 적립금 엑셀 조회.
     */
    public List<Map<String, Object>> selectMemberPurpleCoinExcel(MbrWebpntHist mbrWebpntHist) {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.webPnt.selectMemberPurpleCoinExcel", mbrWebpntHist);
    }

    /**
     * 회원 웹 적립금 총액 조회.
     */
    public long selectMemberWebReserveTotalAmt(MbrWebpntHist mbrWebpntHist) {
        return getSession1().selectOne("com.plgrim.ncp.biz.mbr.webPnt.selectMemberPurpleCoinTotalAmt", mbrWebpntHist);
    }

    /**
	 * P포인트회원 목록 건수 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
	public long selectPurpleCoinMemberListCount(MemberBoDTO memberBoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.selectPurpleCoinMemberListCount", memberBoDTO);
	}

	/**
	 * P포인트회원 목록 조회.
	 *
	 * @param memberBoDTO [설명]
	 * @return List [설명]
	 * @since 2015. 3. 24
	 */
	public List<PurpleCoinMemberBoResult> selectPurpleCoinMemberList(MemberBoDTO memberBoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.selectPurpleCoinMemberList", memberBoDTO);
	}

	/** 회원 보유쿠폰 조회 리스트 */
	public List<MypageCpnFoResult> selectMyCouponsList(MbrIssuCpn mbrIssuCpn) {
		return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyCouponsList", mbrIssuCpn);
	}

    /**
     * 웹포인트 보유쿠폰 조회 리스트
     * @param mbrWebpntHist
     * @return
     */
    public int selectWebPntResnCdCount(MbrWebpntHist mbrWebpntHist) {
        return getSession1().selectOne("com.plgrim.ncp.biz.mbr.webPnt.selectWebPntResnCdCount", mbrWebpntHist);
    }

    /**
     * 원 주문상품정보 조회
     */

    public HashMap selectOriginOrdGodInfo(MbrWebpntHist mbrWebpntHist)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.mbr.webPnt.selectOriginOrdGodInfo", mbrWebpntHist);
    }

    /**
     * 신규 가입 감사 쿠폰 조회
     *
     * @param mbr
     * @return List<MypageCpnFoResult>
     */
    public List<MypageCpnFoResult> selectIssuedNewJoinCoupon(Mbr mbr)  {
    	return getSession1().selectList("com.plgrim.ncp.biz.issu.cpn.selectIssuedNewJoinCoupon", mbr);
    }

    /**
     * 가상계좌 결재대기 포인트
     * @param String mbrNo
     * @return
     */
    public int selectOrdPntUseAmtSum(String mbrNo){
        return getSession1().selectOne("com.plgrim.ncp.biz.order.select.selectOrdPntUseAmtSum", mbrNo );
    }
}
