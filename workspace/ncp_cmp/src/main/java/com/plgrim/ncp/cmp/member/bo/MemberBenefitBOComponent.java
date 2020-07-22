package com.plgrim.ncp.cmp.member.bo;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.biz.member.data.MemberBenefitBoDTO;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.data.MemberResultDTO;
import com.plgrim.ncp.biz.member.result.MemberBenefitAplTgtGrpCdResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitBoResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitPymntHistResult;
import com.plgrim.ncp.biz.member.result.MemberReserveResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface MemberBenefitBOComponent {

    /**
     * 회원혜택 등록.
     *
     * @param dto
     */
    public void insertMemberBenefit(MemberBenefitBoDTO dto);

    /**
     * 회원혜택 수정.
     *
     * @param dto
     */
    public void updateMemberBenefit(MemberBenefitBoDTO dto);

    /**
     * 회원혜택 지급 혜택 등록/수정/삭제
     *
     * @param insList
     * @param updList
     * @param delList
     */
    public void insertMemberBenefitDetail(List<MemberBenefitBoDTO> insList, List<MemberBenefitBoDTO> updList, List<MemberBenefitBoDTO> delList);

    /**
     * 회원혜택 상태 수정.
     *
     * @param dto
     */
    public void updateMemberBenefitStatus(MemberBenefitBoDTO dto);

    /**
     * 회원혜택 지급 혜택 상태 수정.
     *
     * @param dto
     */
    public void updateMemberBenefitDtlStatus(MemberBenefitBoDTO dto);


    /**
     * 회원혜택 목록 조회.
     *
     * @param systemPK
     * @param dto
     * @param loginId
     * @return
     */
    public MemberBenefitBoResult getMemberBenefitList(SystemPK systemPK, MemberBenefitBoDTO dto, String loginId);

    /**
     * 회원혜택 상세조회
     *
     * @param systemPK
     * @param dto
     * @param loginId
     * @return
     */
    public MemberBenefitBoResult getMemberBenefitDetail(SystemPK systemPK, MemberBenefitBoDTO dto, String loginId);

    /**
     * 회원혜택 지급 혜택 목록 조회.
     *
     * @param systemPK
     * @param dto
     * @param pageParam
     * @return
     */
    public Page<MemberBenefitBoResult> getMemberBenefitDetailList(SystemPK systemPK, MemberBenefitBoDTO dto, PageParam pageParam);

    /**
     * 회원혜택 상세 수정이력 목록 조회.
     *
     * @param systemPK
     * @param dto
     * @param pageParam
     * @return
     */
    public Page<MemberBenefitBoResult> getMemberBenefitDetailHistList(SystemPK systemPK, MemberBenefitBoDTO dto, PageParam pageParam);

    /**
     * 회원혜택 상세 발급내역 목록 조회.
     *
     * @param systemPK
     * @param dto
     * @param pageParam
     * @return
     */
    public Page<MemberBenefitPymntHistResult> getMemberBenefitPymntHistList(SystemPK systemPK, MemberBenefitBoDTO dto, PageParam pageParam);

    /**
     * 회원혜택 상세 그룹사 목록 조회.
     *
     * @param systemPK
     * @param dto
     * @param pageParam
     * @return
     */
    public Page<MemberBenefitAplTgtGrpCdResult> selectMbrBnefAplTgtGrpCdList(SystemPK systemPK, MemberBenefitBoDTO dto, PageParam pageParam);

    /**
     * 회원혜택 중복체크
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public int checkMemberBenefitKey(SystemPK systemPK, MemberBenefitBoDTO dto);

    /**
     * 회원혜택 엑셀 목록 조회.
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public List<Map<String, Object>> selectMemberBenefitExcelList(SystemPK systemPK, MemberBenefitBoDTO dto);

    /**
     * 회원혜택 엑셀 수정이력 조회.
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public List<Map<String, Object>> selectMemberBenefitDetailHistExcelList(SystemPK systemPK, MemberBenefitBoDTO dto);

    /**
     * 회원혜택 상세 엑셀 발급내역 조회.
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public List<Map<String, Object>> selectMemberBenefitPymntHistExcelList(SystemPK systemPK, MemberBenefitBoDTO dto);

    /**
     * 회원혜택 지급혜택 중복체크.
     *
     * @param gridList
     * @return
     */
    public int checkMemberBenefitDetailDup(List<MemberBenefitBoDTO> gridList);

    /**
     * 웹포인트 적립
     *
     * @param mbrWebpntHist
     * @return the object
     * @ the exception
     * @since 2015. 3. 23
     */
    public void insertWebPoint(MbrWebpntHist mbrWebpntHist);

    /**
     * 불량상품평 지급코인 즉시회수
     *
     * @param mbrWebpntHist
     * @
     */
    public void webPntImdtlRtrvl(MbrWebpntHist mbrWebpntHist);

    /**
     * P포인트 현황 조회
     *
     * @param mbrNo
     * @return
     */
    public MbrWebpntHistExtend selectWebPointInfo(MbrWebpntHist mbrWebpntHist);

    /**
     * 멤버쉽 포인트 조회
     *
     * @param erpNo
     * @return
     */
    public long getMemberPoint(String erpNo);

    /**
     * P포인트 현황 조회
     *
     * @param mbrNo
     * @return
     */
    public Map<String, String> selectWebPointInfoMap(String mbrNo);

    /**
     * 앱 다운로드 목록 조회.
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return List [설명]
     * @ the exception
     * @since 2015. 4. 24
     */
    public MemberResultDTO getAppdownloadPointList(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 액셀 앱 다운로드 목록 조회.
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return List [설명]
     * @ the exception
     * @since 2015. 4. 24
     */
    public List<Map<String, Object>> getAppdownloadPointListExcel(SystemPK systemPK, MemberBoDTO dto, String loginId)
    ;

    /**
     * 회원 발급 쿠폰 목록 조회.
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return the coupon list count for member
     * @ the exception
     * @since 2015. 4. 22
     */
    public MemberResultDTO getCouponListCountForMember(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 회원 발행 쿠폰 엑셀 조회.
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @param loginId  [설명]
     * @return the coupon list excel for member
     * @ the exception
     * @since 2015. 4. 30
     */
    public List<Map<String, Object>> getCouponListExcelForMember(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 멤버쉽 포인트 조회.
     *
     * @param systemPK [설명]
     * @param mbrNo    [설명]
     * @return the member reserve list
     * @ the exception
     * @since 2015. 4. 22
     */
    public MemberResultDTO getMemberReserveList(SystemPK systemPK, String mbrNo, String loginId, String startReserveDt, String endReserveDt);

    /**
     * 멤버쉽 포인트 잔액 조회.
     *
     * @param systemPK [설명]
     * @param mbrNo    [설명]
     * @return the member reserve balance
     * @ the exception
     * @since 2015. 4. 30
     */
    public MemberResultDTO getMemberReserve(SystemPK systemPK, String mbrNo, String loginId);

    /**
     * 임시삭감 포인트 내역 조회.
     *
     * @param systemPK [설명]
     * @param mbrNo    [설명]
     * @param loginId  [설명]
     * @return the member temp del reserve list
     * @ the exception
     * @since 2015. 5. 28
     */
    public MemberResultDTO getMemberTempDelReserveList(SystemPK systemPK, String mbrNo, String loginId);


    /**
     * 멤버쉽 포인트 내역 엑셀 조회.
     *
     * @param systemPK [설명]
     * @param mbrNo    [설명]
     * @return the member reserve excel
     * @ the exception
     * @since 2015. 5. 12
     */
    public List<Map<String, Object>> getMemberReserveExcel(SystemPK systemPK, String mbrNo, String loginId, String startReserveDt, String endReserveDt);


    /**
     * 회원 웹적립금 조회.
     *
     * @param systemPK
     * @param mbrWebpntHist
     * @param loginId
     * @return
     */
    public MemberResultDTO getMemberPurpleCoinList(SystemPK systemPK, MbrWebpntHist mbrWebpntHist, String loginId);

    /**
     * 회원 웹적립금 엑셀 조회.
     *
     * @param systemPK
     * @param mbrWebpntHist
     * @param loginId
     * @return
     */
    public List<Map<String, Object>> getMemberPurpleCoinExcel(SystemPK systemPK, MbrWebpntHist mbrWebpntHist, String loginId);

}
