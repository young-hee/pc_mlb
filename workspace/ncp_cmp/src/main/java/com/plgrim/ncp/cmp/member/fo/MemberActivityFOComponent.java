package com.plgrim.ncp.cmp.member.fo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.entities.datasource1.clm.ClmFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspFoExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;
import com.plgrim.ncp.biz.helpdesk.data.HistoryInfoFoDTO;
import com.plgrim.ncp.biz.helpdesk.result.HistoryInfoFoResult;
import com.plgrim.ncp.biz.member.data.MemberOrdGodFoDTO;
import com.plgrim.ncp.biz.member.data.MemberSizeClfcDTO;
import com.plgrim.ncp.biz.member.data.MyBrndFoDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.data.MypageQnaFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberOrdGodFoResult;
import com.plgrim.ncp.biz.member.result.MyBrndFoResult;
import com.plgrim.ncp.biz.member.result.MypageAlrmFoResult;
import com.plgrim.ncp.biz.member.result.MypageEvtFoResult;
import com.plgrim.ncp.biz.member.result.MypageMainFoResult;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.biz.member.result.MypageMysizeResult;
import com.plgrim.ncp.biz.member.result.MypageQnaFoResult;
import com.plgrim.ncp.biz.member.result.MypageTdGodFoResult;
import com.plgrim.ncp.biz.member.result.MypageWishFoResult;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.result.MypageClaimFoResult;
import com.plgrim.ncp.biz.order.result.MypageOrderFoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.order.data.OrderFormerlyPurchaseCfmInfoSDO;
import com.plgrim.ncp.interfaces.order.data.OrderFormerlyPurchaseSDO;
import com.plgrim.ncp.interfaces.order.data.OrderOfflinePurchaseSDO;

/**
 * 회원활동정보 select interface
 */
public interface MemberActivityFOComponent {

    /**
     * 1:1문의 리스트 조회
     *
     * @param mypageMtmFoDTO
     * @param pageParam
     * @return
     */
    public Page<MypageMtmFoResult> selectMyInquiryList(MypageMtmFoDTO mypageMtmFoDTO, PageParam pageParam);

    /**
     * 1:1문의 리스트 조회 (시스템정보 추가)
     *
     * @param mypageMtmFoDTO
     * @param pageParam
     * @return
     */
    public Page<MypageMtmFoResult> selectMyInquiryList(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO, PageParam pageParam);

    /**
     * 1:1 문의 상세
     *
     * @param pk
     * @param mypageMtmFoDTO
     * @return
     */
    public List<MypageMtmFoResult> selectCsInquiryDetail(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 1:1 문의 리스트 카운트 조회(모바일)
     *
     * @param systemPk
     * @param mypageMtmFoDTO
     * @return
     */
    public int selectMyInquiryListMobileCnt(SystemPK systemPk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 1:1 문의 리스트 카운트 조회(모바일-MLB,SA)
     *
     * @param systemPk
     * @param mypageMtmFoDTO
     * @return
     */
    public int selectMyInquiryListMobileCntWithMall(SystemPK systemPk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 1:1 문의 리스트 조회(모바일)
     *
     * @param systemPk
     * @param mypageMtmFoDTO
     * @return
     */
    public List<MypageMtmFoResult> selectMyInquiryListMobile(SystemPK systemPk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 1:1 문의 리스트 조회(모바일-MLB,SA)
     *
     * @param systemPk
     * @param mypageMtmFoDTO
     * @return
     */
    public List<MypageMtmFoResult> selectMyInquiryListMobileWithMall(SystemPK systemPk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 1:1문의 파일 리스트 조회
     *
     * @param pk
     * @param mypageMtmFoDTO
     * @return
     */
    public List<MypageMtmFoResult> selectMyInquiryFileList(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 1:1문의 파일 리스트 조회(MLB,SA)
     *
     * @param pk
     * @param mypageMtmFoDTO
     * @return
     */
    public List<MypageMtmFoResult> selectMyInquiryFileListWithMall(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);
    
    /**
     * 1:1 문의 주문상품 리스트 조회
     *
     * @param pk
     * @param mypageMtmFoDTO
     * @return
     */
    public List<MypageMtmFoResult> selectMyInquiryOrdGodList(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 1:1문의 리스트 카운트
     *
     * @param mypageMtmFoDTO
     * @return
     */
    public long selectMyInquiryListCount(MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 1:1문의 리스트 카운트(MLB,SA 용)
     *
     * @param mypageMtmFoDTO
     * @return
     */
    public long selectMyInquiryListCount(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * QNA 리스트 조회
     *
     * @param mypageQnaFoDTO
     * @param pageParam
     * @return
     */
    public Page<MypageQnaFoResult> selectMyQnaList(MypageQnaFoDTO mypageQnaFoDTO, PageParam pageParam);

    /**
     * Qna 리스트 상세
     *
     * @param pk
     * @param mypageQnaFoDTO
     * @return
     */
    public List<MypageQnaFoResult> selectMyQnaListDetail(SystemPK pk, MypageQnaFoDTO mypageQnaFoDTO);

    /**
     * QNA 리스트 조회(모바일)
     *
     * @param pk
     * @param mypageQnaFoDTO
     * @return
     */
    public List<MypageQnaFoResult> selectMyQnaListMobile(SystemPK pk, MypageQnaFoDTO mypageQnaFoDTO);

    /**
     * 상품 리뷰 작성 가능 리스트 조회
     *
     * @param systemPK
     * @param dto
     * @param pageParam
     * @return
     */
    public Page<MemberOrdGodFoResult> selectMyGoodsNoReviewList(SystemPK systemPK, MemberOrdGodFoDTO dto, PageParam pageParam);

    /**
     * 작성 한 상품 리뷰 리스트 조회
     *
     * @param systemPK
     * @param dto
     * @param pageParam
     * @return
     */
    public Page<MemberOrdGodFoResult> selectMyGoodsReviewList(SystemPK systemPK, MemberOrdGodFoDTO dto, PageParam pageParam);

    /**
     * 상품평 작성 가능 리스트-매장구매내역
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public MemberOrdGodFoResult selectMyGoodsStoreNoReview(SystemPK systemPK, MemberOrdGodFoDTO dto);

    /**
     * 상품평 작성 가능 리스트
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public MemberOrdGodFoResult selectMyGoodsNoReview(SystemPK systemPK, MemberOrdGodFoDTO dto);


    /**
     * 상품 리뷰 조회
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public MemberOrdGodFoResult selectMyGoodsReview(SystemPK systemPK, MemberOrdGodFoDTO dto);

    /**
     * 나의 브랜드 리스트 조회
     *
     * @param myBrndFoDTO
     * @param pageParam
     * @return
     */
    public Page<MyBrndFoResult> selectMyBrndList(MyBrndFoDTO myBrndFoDTO, PageParam pageParam);

    /**
     * 로그인 히스토리 리스트 조회
     *
     * @param historyInfoFoDTO
     * @param pageParam
     * @return
     */
    public Page<HistoryInfoFoResult> selectMyLoginHistory(HistoryInfoFoDTO historyInfoFoDTO, PageParam pageParam);

    /**
     * 나의 사이즈 정보 리스트 조회
     *
     * @param mbrNo
     * @return
     */
    public List<MypageMysizeResult> selectMyBasicSizeList(String mbrNo);

    /**
     * MyPage 회원 WishList 조회
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return the coupon list count for member
     * @ the exception
     * @since 2015. 5. 06
     */
    public Page<MypageWishFoResult> getMyWishList(SystemPK systemPK, MypageFoDTO dto, PageParam pageParam);

    /**
     * MyPage 회원 WishList 조회
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return the coupon list count for member
     * @ the exception
     * @since 2015. 5. 06
     */
    public Page<MypageAlrmFoResult> getMyAlarmsForStockList(SystemPK systemPK, MypageFoDTO dto, PageParam pageParam);

    /**
     * MyPage 회원 Main 알람 조회
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return the coupon list count for member
     * @ the exception
     * @since 2015. 5. 06
     */
    public List<MypageMainFoResult> getMyPageAlramList(SystemPK systemPK, MypageFoDTO dto);

    /**
     * 위시리스트 조회(모바일)
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public List<MypageWishFoResult> getMyWishListMobile(SystemPK systemPK, MypageFoDTO dto);

    /**
     * 참여 이벤트 조회
     *
     * @param dto
     * @param pageParam
     * @return
     */
    public Page<MypageEvtFoResult> selectMyEventList(MypageFoDTO dto, PageParam pageParam);

    /**
     * 당첨 된 수 이벤트 리스트
     *
     * @param dto
     * @return
     */
    public long selectMyEventPrizeListCount(MypageFoDTO dto);

    /**
     * MyPage 회원 오늘본상품조회
     *
     * @param systemPK [설명]
     * @param dto      [설명]
     * @return the coupon list count for member
     * @ the exception
     * @since 2015. 5. 06
     */
    public Page<MypageTdGodFoResult> getMyTodayGodList(SystemPK systemPK, MypageFoDTO dto, PageParam pageParam);

    /**
     * Qna 리스트 카운트
     *
     * @param mypageQnaFoDTO
     * @return
     */
    long selectMyQnaListCount(MypageQnaFoDTO mypageQnaFoDTO);

    /**
     * Qna 리스트 카운트(모바일)
     *
     * @param pk
     * @param mypageQnaFoDTO
     * @return
     */
    public int selectMyQnaListMobileCnt(SystemPK pk, MypageQnaFoDTO mypageQnaFoDTO);

    /**
     * 상품평 작성 하지 않은 리스트 카운트
     *
     * @param dto
     * @return
     */
    public long selectMyGoodsNoReviewListCount(MemberOrdGodFoDTO dto);

    /**
     * 상품평 작성한 리스트 카운트
     *
     * @param dto
     * @return
     */
    public long selectMyGoodsReviewListCount(MemberOrdGodFoDTO dto);

    /**
     * 첫 로그인 체크
     *
     * @param mbr
     * @return
     */
    public long checkFirstLogin(Mbr mbr);

    /**
     * 첫 앱 다운로드 체크
     *
     * @param mbr
     * @return
     */
    public long checkFirstAppDownload(Mbr mbr);

    /**
     * 이벤트 응모가능여부
     *
     * @param evtNo
     * @return
     */
    public String selectCampaginEvtInfo(String evtNo);

    /**
     * MyPage Main Alarm 목록조회
     *
     * @param systemPK
     * @param dto
     * @return
     */
    long getMyPageAlramListCnt(SystemPK systemPK, MypageFoDTO dto);

    /**
     * MyPage Today Good 목록 Count 조회
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public long selectMyTodayGodListCount(SystemPK systemPK, MypageFoDTO dto);

    public List<MypageMainFoResult> getMyPageAlramListMb(SystemPK systemPK, MypageFoDTO dto);

    /**
     * 로그인기록 카운트
     *
     * @param historyInfoDTO
     * @return
     */
    public long selectCountHistory(HistoryInfoFoDTO historyInfoDTO);

    /**
     * 나의 사이즈 정보 1건 얻기
     *
     * @param memberSizeClfcDTO
     * @return
     */
    public MypageMysizeResult selectMysizeOne(MemberSizeClfcDTO memberSizeClfcDTO);

    /**
     * 나의 사이즈 정보 리스트 카운트
     *
     * @param mbrNo
     * @return
     */
    public int selectMysizeCount(String mbrNo);


    /**
     * 1:1 문의 수정
     *
     * @param pk
     * @param mypageMtmFoDTO
     * @param multipartFiles
     */
    void updateMyInquiry(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO, List<MultipartFile> multipartFiles);

    /**
     * 1:1 문의 수정 new
     * @param pk
     * @param mypageMtmFoDTO
     */
    public void updateMypageInquiry(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 1:1 문의 삭제
     *
     * @param pk
     * @param mypageMtmFoDTO
     */
    void deleteMyInquiry(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 1:1 문의 만족도 평가 수정
     *
     * @param pk
     * @param mypageMtmFoDTO
     */
    void updateMyInqAnsEvl(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * Qna 삭제
     *
     * @param pk
     * @param mypageQnaFoDTO
     */
    void deleteMyQna(SystemPK pk, MypageQnaFoDTO mypageQnaFoDTO);

    /**
     * Qna 변경
     *
     * @param pk
     * @param mypageQnaFoDTO
     */
    void updateMyQna(SystemPK pk, MypageQnaFoDTO mypageQnaFoDTO);

    /**
     * 나의 사이즈 저장
     *
     * @param mbrNo
     * @param mbrSizeNm
     * @param height
     * @param weight
     * @param waist
     * @param body
     * @return 포인트적립여부 true / false
     * @throws Exception
     */
    boolean saveMemberSize(String mbrNo, String mbrSizeNm, String height, String weight, String waist, String body) throws Exception;

    /**
     * 상품평 리뷰 작성
     *
     * @param pk
     * @param dto
     * @param multipartFiles
     */
    void insertMyGoodsReview(SystemPK pk, MemberOrdGodFoDTO dto, List<MultipartFile> multipartFiles);

    /**
     * 상품평 리뷰 수정
     *
     * @param pk
     * @param dto
     * @param multipartFiles
     */
    void updateMyGoodsReview(SystemPK pk, MemberOrdGodFoDTO dto, List<MultipartFile> multipartFiles);

    /**
     * 상품평 리뷰 수정(앱)
     *
     * @param pk
     * @param dto
     */
    void updateMyGoodsReviewByApp(SystemPK pk, MemberOrdGodFoDTO dto);

    /**
     * 위시리스트 삭제
     *
     * @param dto
     */
    void deleteMyWishList(MypageFoDTO dto);

    /**
     * 위시리스트 전체 삭제
     *
     * @param dto
     */
    void deleteAllMyWishList(MypageFoDTO dto);

    /**
     * 최근 본 상품 삭제
     *
     * @param dto
     */
    void deleteMyTodayGodList(MypageFoDTO dto);

    /**
     * 최근 본 상품 전체 삭제
     *
     * @param dto
     */
    void deleteAllTodayGoodList(MypageFoDTO dto);

    /**
     * 재입고 알림 삭제
     *
     * @param dto
     */
    void deleteMyAlarmsForStock(MypageFoDTO dto);

    /**
     * 1:1 상담 등록 및 파일 업로드
     *
     * @param pk
     * @param mypageMtmFoDTO
     * @param files
     */
    void createFile(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO, List files);

    /**
     * 1:1 상담 등록
     *
     * @param pk
     * @param mypageMtmFoDTO
     */
    void insertCsInquiry(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 나의 상품 리뷰 삭제
     *
     * @param pk
     * @param dto
     */
    void deleteMyGoodsReview(SystemPK pk, MemberOrdGodFoDTO dto);

    /**
     * 나의 상품 리뷰 삭제(앱)
     *
     * @param pk
     * @param dto
     */
    void insertMyGoodsReviewByApp(SystemPK pk, MemberOrdGodFoDTO dto);

    /**
     * 마이브랜드 삭제
     *
     * @param pk         the pk
     * @param userDetail the user detail
     * @param brndId     the brnd id
     * @return the int
     * @ the exception
     */
    public int deleteMyBrand(SystemPK pk, SecurityUserDetail userDetail, String brndId);

    /**
     * 마이브랜드 추가
     *
     * @param pk         the pk
     * @param userDetail the user detail
     * @param brndId     the brnd id
     * @return the int
     * @ the exception
     */
    public int addMyBrand(SystemPK pk, SecurityUserDetail userDetail, String brndId);

    /**
     * 나의 사이즈 등록/수정
     *
     * @param memberSizeClfcDTO
     */
    void mergeMbrSizeClfc(MemberSizeClfcDTO memberSizeClfcDTO);

    /**
     * 나의 사이즈 분류 삭제
     *
     * @param mbrNo
     * @param mbrSizeTurn
     */
    void deleteMbrSizeClfc(String mbrNo, String mbrSizeTurn);

    /**
     * 나의 사이즈 분류 상세 삭제
     *
     * @param mbrNo
     * @param mbrSizeTurn
     */
    void deleteMbrSizeClfcDetail(String mbrNo, String mbrSizeTurn);


    /**
     * 기본배송지 설정
     *
     * @param systemPK
     * @param mypageFoDTO
     */
    public void updateUserDeliveryBase(SystemPK systemPK, MypageFoDTO mypageFoDTO);

    /**
     * 배송지 목록 삭제.
     *
     * @param systemPK
     * @param mypageFoDTO
     */
    public void deleteUserDelivery(SystemPK systemPK, MypageFoDTO mypageFoDTO);

    /**
     * 배송지 변경시 회원배송지 테이블에 추가/수정
     *
     * @param systemPK
     * @param lgsDlvsp
     * @param defaultDelv
     * @param addMbrDlvspCheck
     */
    public void updateDeliveryLocationMbrDlvsp(SystemPK systemPK, LgsDlvsp lgsDlvsp, String defaultDelv, String addMbrDlvspCheck);

    /**
     * 기 배송목록 확인
     *
     * @param mbrDlvsp;
     * @return
     */
    public boolean hasDeliveryLocation(MbrDlvsp mbrDlvsp);

    /**
     * 회원 주문 상품 조회
     *
     * @param pk
     * @param mypageMtmFoDTO
     * @return
     */
    public List<MypageMtmFoResult> selectOrdGodList(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);

    /**
     * 회원 주문 배송지 조회(픽업주문 일반 배송 전환용).
     *
     * @param systemPK
     * @param mbrDlvsp
     * @return
     */
    public LgsDlvsp selectPkupOrderDeliveryLocationPop(SystemPK systemPK, MbrDlvsp mbrDlvsp);

    /**
     * 회원 주문 배송지 조회.
     *
     * @param systemPK
     * @param lgsDlvspFoExtend
     * @return
     */
    public LgsDlvsp selectOrderDeliveryLocationPop(SystemPK systemPK, LgsDlvspFoExtend lgsDlvspFoExtend);

    /**
     * 회원 주문 글로벌 배송지 조회.
     *
     * @param systemPK
     * @param lgsDlvspFoExtend
     * @return
     */
    public LgsDlvspFoExtend selectOrderDeliveryLocationPopGlobal(SystemPK systemPK, LgsDlvspFoExtend lgsDlvspFoExtend);

    /**
     * 회원 주문 조회 상세.
     *
     * @param systemPK
     * @param mypageOrderInfoDTO
     * @return
     */
    public MypageOrderInfoDTO selectFoOrderInfo(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO);

    /**
     * 취소 반품 교환시 환불계좌 여부
     *
     * @param systemPK
     * @param ordNo
     * @return
     */
    public MypageOrderInfoDTO selectPayFoMobilPonPayRfd(SystemPK systemPK, String ordNo);

    /**
     * 회원 주문 상세 클레임.
     *
     * @param systemPK
     * @param mypageOrderInfoDTO
     * @return
     */
    public List<ClmFoExtend> selectClmFoList(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO);

    /**
     * 회원 배송추적 정보 조회.
     *
     * @param systemPK
     * @param mypageFoDTO
     * @return
     */
    public List<String[]> selectHanjinTracking(SystemPK systemPK, MypageFoDTO mypageFoDTO);

    /**
     * 회원 영수증 조회.
     *
     * @param systemPK
     * @param dto
     * @param pageParam
     * @return
     */
    public Page<MypageOrderFoResult> selectFoReceiptList(SystemPK systemPK, MypageOrderInfoDTO dto, PageParam pageParam);

    /**
     * 회원 현금 영수증 조회 AS_IS
     *
     * @param systemPK
     * @param dto
     * @return
     */
    public List<MypageOrderFoResult> selectFoReceiptList(SystemPK systemPK, MypageOrderInfoDTO dto);

    /**
     * 다중배송지여부
     *
     * @param systemPK
     * @param ordNo
     * @return
     */
    public String selectMultiLgsDlvYn(SystemPK systemPK, String ordNo);

    /**
     * 회원 주문 조회 count.
     *
     * @param systemPK
     * @param mypageOrderFoResult
     * @return int
     */
    public int selectFoOrderListCount(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO);

    /**
     * 회원 주문 조회.
     *
     * @param systemPK
     * @param mypageOrderFoResult
     * @return
     */
    public Page<MypageOrderFoResult> selectFoOrderList(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO, PageParam pageParam);

    /**
     * 회원 클레임 내역 조회.
     *
     * @param systemPK
     * @param mypageOrderInfoDTO
     * @return
     */
    public Page<MypageClaimFoResult> selectFoClaimList(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO, PageParam pageParam);


    /**
     * 회원 결제수단 변경 조회.
     *
     * @param systemPK
     * @param mypageOrderInfoDTO
     * @return
     */
    public MypageOrderInfoDTO selectPayMethodChange(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO);

    /**
     * 픽업에서 일반배송으로 변경시 추가 배송비 조회
     *
     * @param systemPK
     * @param mypageOrderInfoDTO
     * @return
     */
    public List<MypageOrderInfoDTO> selectDlvCstSumAmt(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO);

    /**
     * 주문상품 옵션 조회
     *
     * @param systemPK
     * @param mypageOrderInfoDTO
     * @return
     */
    public List<MypageOrderFoResult> selectOrdGoodOption(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO);

    /**
     * 주문세트상품 옵션 조회
     *
     * @param systemPK
     * @param mypageOrderInfoDTO
     * @return
     */
    public List<MypageOrderFoResult> selectOrdSetOption(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO);

    /**
     * 픽업에서 일반배송으로 변경가능여부
     *
     * @param systemPK
     * @param mypageOrderInfoDTO
     * @return
     */
    public String selectDlvYn(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO);

    /**
     * 픽업에서 일반배송으로 변경가능여부 ( 옵션 판매상태 기준 : #47710 픽업판매중인 상품이 있는 경우 일반배송으로 전환 불가 )
     *
     * @param systemPK
     * @param mypageOrderInfoDTO
     * @return
     */
    public MypageOrderInfoDTO getPickupDeliveryChangeYnInfo(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO);


    /**
     * 회원 사이즈 정보 조회
     *
     * @param search
     * @return
     */
    public List<MbrSizeClfc> selectMbrSizeClfcInfo(MbrSizeClfc search);

    /**
     * 회원 배송지 조회.
     *
     * @param systemPK
     * @param mbrDlvsp
     * @return the delivery location list
     */
    public List<MemberBoResult> getDeliveryLocationList(SystemPK systemPK, MbrDlvsp mbrDlvsp, String loginId);

    /**
     * 회원 배송지 조회 Page.
     *
     * @param systemPK
     * @param mbrDlvsp
     * @param pageParam
     * @return the delivery location list
     */
    public Page<MemberBoResult> getDeliveryLocationPageList(SystemPK systemPK, MbrDlvsp mbrDlvsp, PageParam pageParam);

    /**
     * 회원 주문 배송지 변경.
     *
     * @param systemPK
     * @param lgsDlvsp
     */
    public void updateDeliveryLocationChange(SystemPK systemPK, LgsDlvsp lgsDlvsp);

    /**
     * 상품평 삭제
     *
     * @param dto
     * @return
     */
    public int deleteMyGoodsReviewNtceYn(MemberOrdGodFoDTO dto);

    public MypageWishFoResult selectGodWishList(MypageFoDTO dto);

    public MypageWishFoResult selectGodWishListCount(MypageFoDTO dto);

    public void deleteMainMyWishList(MypageFoDTO dto);

    /**
     * 이전구매내역 조회 - IF-ORD-05
     *
     * @param systemPK
     * @param sdo
     * @return
     */
    public OrderFormerlyPurchaseSDO getFormerlyPurchaseList(SystemPK systemPK, OrderFormerlyPurchaseSDO sdo);

    /**
     * 오프라인 구매내역 조회 - IF-ORD-05
     *
     * @param systemPK
     * @param sdo
     * @return
     */
    public OrderOfflinePurchaseSDO getOfflinePurchaseList(SystemPK systemPK, OrderOfflinePurchaseSDO sdo);

	/**
     * 이전주문 구매확정여부 조회 - IF-ORD-10
     *
     * @param systemPK
     * @param sdo
     * @return
     */
    public OrderFormerlyPurchaseCfmInfoSDO formerlyPurchaseConfirmInfo(SystemPK systemPK, OrderFormerlyPurchaseCfmInfoSDO sdo);

    /**
	 * 회원 등급 조회
	 *
	 * @param mbrGrd
	 * @return mbrGrd
	 */
	public MbrGrd selectMbrGrd(MbrGrd mbrGrd);

}
