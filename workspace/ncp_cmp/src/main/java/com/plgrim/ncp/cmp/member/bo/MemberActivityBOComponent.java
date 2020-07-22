package com.plgrim.ncp.cmp.member.bo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqOrdGod;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.biz.callcenter.result.CsoOthersMemberResult;
import com.plgrim.ncp.biz.goods.result.GoodsReviewResult;
import com.plgrim.ncp.biz.helpdesk.data.HistoryInfoFoDTO;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MysizeDTO;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.result.OrderBoResult;
import com.plgrim.ncp.biz.promotion.result.EventOthersMemberResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.order.data.OrderOfflinePurchaseSDO;

/**
 * 회원주문정보 command interface
 */
public interface MemberActivityBOComponent {

    /**
     * 현금영수증 재신청
     * TODO 현재사용안함
     *
     * @param request
     * @param pk
     * @param mypageOrderInfoDTO
     * @return
     */
    Map<String, String> issueReceipt(HttpServletRequest request, SystemPK pk, MypageOrderInfoDTO mypageOrderInfoDTO);


    /**
     * 대표 환불 계좌 변경.
     *
     * @param systemPK
     * @param listMrba
     * @param loginId
     * @
     */
    void modifyRprstRefundAccountBy(SystemPK systemPK, List<MbrRfdBnkAcct> listMrba, String loginId);


    /**
     * 회원 주문 배송지 변경.
     * FO
     *
     * @param systemPK
     * @param lgsDlvsp
     */
    public void updateDeliveryLocationChange(SystemPK systemPK, LgsDlvsp lgsDlvsp);

    /**
     * 대표배송지 변경.
     *
     * @param systemPK
     * @param listDTO
     * @param string
     */
    public void modifyBaseDeliveryLocationBy(SystemPK systemPK, List<MemberBoDTO> listDTO, String string);

    /**
     * 배송지 목록 저장.
     *
     * @param systemPK
     * @param listDTO  [설명]
     * @param string
     * @since 2015. 4. 9
     */
    public void saveDeliveryLocationListBy(SystemPK systemPK, List<MemberBoDTO> listDTO, String string);

    /**
     * 배송지 목록 삭제.
     *
     * @param systemPK
     * @param listDTO
     * @param loginId
     * @since 2015. 4. 9
     */
    public void removeDeliveryLocationListBy(SystemPK systemPK, List<MemberBoDTO> listDTO, String loginId);

    /**
     * 매장 구매 내역
     * BO
     *
     * @param orderOfflinePurchaseSDO
     * @param systemPK
     * @return OrderOfflinePurchaseSDO
     */
    public OrderOfflinePurchaseSDO getOfflinePurchaseList(OrderOfflinePurchaseSDO orderOfflinePurchaseSDO, SystemPK systemPK);

    /**
     * 환불계좌 인증
     * FO / BO
     *
     * @param dto
     * @return
     */
    Map<String, String> refundSendCheck(MemberFoDTO dto);

    /**
     * 회원 주문 목록 조회.
     *
     * @param systemPK  [설명]
     * @param orderDTO  [설명]
     * @param pageParam [설명]
     * @param loginId   [설명]
     * @return the order list for member
     * @ the exception
     * @since 2015. 5. 29
     */
    public Page<OrderBoResult> getOrderListForMember(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam, String loginId);

    /**
     * 회원 주문 엑셀 조회.
     *
     * @param systemPK [설명]
     * @param orderDTO [설명]
     * @param loginId  [설명]
     * @return the order excel for member
     * @since 2015. 5. 29
     */
    public List<Map<String, Object>> getOrderExcelForMember(SystemPK systemPK, OrderBoDTO orderDTO, String loginId);

    /**
     * 회원 환불 계좌 조회.
     *
     * @param systemPK      [설명]
     * @param mbrRfdBnkAcct [설명]
     * @return the refund account list
     * @ the exception
     * @since 2015. 4. 20
     */
    public List<MbrRfdBnkAcct> getRefundAccountList(SystemPK systemPK, MbrRfdBnkAcct mbrRfdBnkAcct, String loginId);


    /**
     * 회원 환불 목록 조회.
     *
     * @param systemPK  [설명]
     * @param mbr       [설명]
     * @param pageParam [설명]
     * @return the refund list for member
     * @ the exception
     * @since 2015. 5. 21
     */
    public Page<CsoOthersMemberResult> getRefundListForMember(SystemPK systemPK, Mbr mbr, PageParam pageParam);

    /**
     * 회원 환불 엑셀 조회.
     *
     * @param systemPK [설명]
     * @param mbr      [설명]
     * @param loginId  [설명]
     * @return the refund excel for member
     * @since 2015. 5. 21
     */
    public List<Map<String, Object>> getRefundExcelForMember(SystemPK systemPK, Mbr mbr, String loginId);

    /**
     * 회원 배송지 조회.
     * FO / BO
     *
     * @param systemPK
     * @param mbrDlvsp
     * @return the delivery location list
     * @since 2015. 4. 10
     */
    public List<MemberBoResult> getDeliveryLocationList(SystemPK systemPK, MbrDlvsp mbrDlvsp, String loginId);


    /**
     * 상품평 노출 여부 변경 처리.
     *
     * @param systemPK [설명]
     * @param ggeList  [설명]
     * @param loginId  [설명]
     * @ the exception
     * @since 2015. 6. 17
     */
    void modifyGoodsReviewForNtceYn(SystemPK systemPK, List<GodGodEvl> ggeList, String loginId);

    /**
     * 상품문의 삭제 여부 변경 처리.
     *
     * @param systemPK [설명]
     * @param cgiList  [설명]
     * @param loginId  [설명]
     * @ the exception
     * @since 2015. 6. 17
     */
    void modifyGoodsInquiryForDeleteYn(SystemPK systemPK, List<CsoGodInq> cgiList, String loginId);

    /**
     * 나의 사이즈 조회
     *
     * @param mbrNo
     * @return
     */
    public List<MysizeDTO> selectMyBasicSize(String mbrNo);

    /**
     * 회원 이벤트 응모 내역 조회.
     *
     * @param systemPK
     * @param ea
     * @param pageParam
     * @param loginId
     * @return
     */
    public Page<EventOthersMemberResult> getEventApplyListForMember(SystemPK systemPK, EvtApplcn ea, PageParam pageParam, String loginId);

    /**
     * 회원 이벤트 응모내역 엑셀 조회.
     *
     * @param systemPK [설명]
     * @param ea       [설명]
     * @param loginId  [설명]
     * @return the event apply excel for member
     * @since 2015. 5. 21
     */
    public List<Map<String, Object>> getEventApplyExcelForMember(SystemPK systemPK, EvtApplcn ea, String loginId);

    /**
     * 회원 상품평 목록 조회.
     *
     * @param systemPK  [설명]
     * @param gge        [설명]
     * @param pageParam [설명]
     * @param loginId   [설명]
     * @return List [설명]
     * @ the exception
     * @since 2015. 5. 4
     */
    public Page<GoodsReviewResult> getGoodsReviewListForMember(SystemPK systemPK, GodGodEvl gge, PageParam pageParam, String loginId);


    /**
     * 회원 상품평 엑셀 조회.
     *
     * @param systemPK [설명]
     * @param gge      [설명]
     * @param loginId  [설명]
     * @return the goods review excel for member
     * @since 2015. 5. 21
     */
    public List<Map<String, Object>> getGoodsReviewExcelForMember(SystemPK systemPK, GodGodEvl gge, String loginId);


    /**
     * 회원 상품문의 목록 조회.
     *
     * @param systemPK  [설명]
     * @param cgi       [설명]
     * @param pageParam [설명]
     * @param loginId   [설명]
     * @return the goods inquiry list for member
     * @ the exception
     * @since 2015. 5. 15
     */
    public Page<CsoOthersMemberResult> getGoodsInquiryListForMember(SystemPK systemPK, CsoGodInq cgi, PageParam pageParam, String loginId);

    /**
     * 회원 상품문의 엑셀 조회.
     *
     * @param systemPK [설명]
     * @param cgi      [설명]
     * @param loginId  [설명]
     * @return the goods inquiry excel for member
     * @since 2015. 5. 21
     */
    public List<Map<String, Object>> getGoodsInquiryExcelForMember(SystemPK systemPK, CsoGodInq cgi, String loginId);

    /**
     * 회원 1:1문의 목록 조회.
     *
     * @param systemPK  [설명]
     * @param cmi       [설명]
     * @param pageParam [설명]
     * @param loginId   [설명]
     * @return the mtm inquiry list for member
     * @ the exception
     * @since 2015. 5. 15
     */
    public Page<CsoOthersMemberResult> getMtmInquiryListForMember(SystemPK systemPK, CsoMtmInq cmi, PageParam pageParam, String loginId);

    /**
     * 회원 1:1문의 엑셀 조회.
     *
     * @param systemPK [설명]
     * @param cmi      [설명]
     * @param loginId  [설명]
     * @return the mtm inquiry excel for member
     * @since 2015. 5. 21
     */
    public List<Map<String, Object>> getMtmInquiryExcelForMember(SystemPK systemPK, CsoMtmInq cmi, String loginId);


    /**
     * 회원 일대일문의 주문상품 목록 조회.
     *
     * @param cmiog [설명]
     * @return the mtm inquiry ord god list
     * @since 2015. 5. 26
     */
    public List<OrdGod> getMtmInquiryOrdGodList(CsoMtmInqOrdGod cmiog);

}
