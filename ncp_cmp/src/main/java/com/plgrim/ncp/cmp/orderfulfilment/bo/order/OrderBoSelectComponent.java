package com.plgrim.ncp.cmp.orderfulfilment.bo.order;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZoneNationExtend;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.biz.callcenter.data.CsoCnsltMemoExtendDTO;
import com.plgrim.ncp.biz.claim.data.ClmCouponSearchDTO;
import com.plgrim.ncp.biz.claim.result.ClmCouponResult;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.result.ClmWrhsGodResult;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmDetailResult;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmResult;
import com.plgrim.ncp.biz.order.result.OrderBoResult;
import com.plgrim.ncp.biz.order.result.OrderRepairResult;
import com.plgrim.ncp.commons.result.SysShopResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

public interface OrderBoSelectComponent {

	/**
	 * 주문번호로 주문 엔티티를 반환한다.
	 * 
	 * @param ordNo
	 * @return
	 * @throws Exception
	 */
	public Ord selectOrdEntity(String ordNo) throws Exception;
	
	/**
	 * 주문목록 조회
	 * 
	 * @param systemPK
	 * @param orderDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<OrderBoResult> selectBoOrderList(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam) throws Exception;
	
	/**
	 * 걀제 목록 조회
	 * 
	 * @param systemPK
	 * @param orderDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<OrderBoResult> selectBoPayList(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam) throws Exception;
	
	/**
	 * 주문상품 리스트 조회
	 * 
	 * @param systemPK
	 * @param orderDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<OrderBoResult> selectBoOrdGodList(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam) throws Exception;
	
	/**
	 * 제휴 주문 리스트 조회
	 * 
	 * @param systemPK
	 * @param orderDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<OrderBoResult> selectAffOrdList(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam) throws Exception;
	
	/**
	 * 제휴주문 오류 리스트 조회
	 * 
	 * @param systemPK
	 * @param orderDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<OrderBoResult> selectAffOrdErrList(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam) throws Exception;
	
	/**
	 * 주문상품 목록 조회
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrderBoResult> selectBOGodList(OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 주문상세정보 조회
	 * 
	 * @param systemPK
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public OrderBoResult selectBOOrderDt(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 상담메모리스트 조회
	 * 
	 * @param csoCnsltMemo
	 * @return
	 * @throws Exception
	 */
	public List<CsoCnsltMemoExtendDTO> selectCsoCnsltMemoList(CsoCnsltMemo csoCnsltMemo) throws Exception;
	
	/**
	 * 상담메모 조회
	 * 
	 * @param csoCnsltMemo
	 * @return
	 * @throws Exception
	 */
	public CsoCnsltMemo getCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception;
	
	/**
	 * 주문클레임 리스트 조회
	 * 
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrderBoResult> selectBOOrdClmList(OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 주문상품단품 조회 (대량 주문 상품 일괄등록)
	 * @param ordGodExtend
	 * @return
	 * @throws Exception
	 */
	public GodExtend selectBoOrdGodItmHist(OrdGodExtend ordGodExtend) throws Exception;
	
	/**
	 * 배송상태 조회
	 * 
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<LgsDlivyDrctGodExtend> selectOrdDlivyDrct(OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 상품 단품 리스트 조회
	 * 
	 * @param orderDTO
	 * @param qty
	 * @return
	 * @throws Exception
	 */
	public List<GodExtend> selectBoItmHist(OrderBoDTO orderDTO, long qty) throws Exception;

	/**
	 * 주문배송 검증 (픽업주문 변경, 배송지변경)
	 * 
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> ordValidation(OrderBoDTO orderDTO) throws Exception;

	/**
	 * 주문 프로모션 상세 리스트 조회
	 * 
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdGodAplPrmExtend> selectOrdPrmDtlList(OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 메일템플릿을 위한 주문정보 조회
	 * 
	 * @param ordNo
	 * @return
	 * @throws Exception
	 */
	public OrderBoResult selectEmailOrderCompt(String ordNo) throws Exception;

	/**
	 * 메일템플릿을 위한 주문완료정보 조회
	 * 
	 * @param ordNo
	 * @return
	 * @throws Exception
	 */
	public OrderBoResult selectEmailOrderCompt(String ordNo, String dmstcWaybilNo) throws Exception;
	
	/**
	 * 메일템플릿을 위한 주문완료정보 조회 (글로벌)
	 * 
	 * @param ordNo
	 * @return
	 * @throws Exception
	 */
	public OrderBoResult selectEmailOrderComptGlobal(String ordNo, String dmstcWaybilNo) throws Exception;
    
    /**
     * 주문 - 클레임접수기본정보조회[주문상품별](교환)
     * @param systemPK
     * @param orderDTO
     * @return
     * @throws Exception
     */
	public List<OrdGodForRtnClmDetailResult> selectOrdGodInfo(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 주문 - 클레임접수기본정보조회[주문상품별](부분취소/반품)
	 *  
	 * @param systemPK
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdGodForRtnClmDetailResult> selectOrdGodInfoWithGft(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 주문 - 반품접수 클레임접수기본정보조회[반품접수-물류배송지별]
	 *  
	 * @param systemPK
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmWithGft(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 주문 - 반품접수 클레임접수기본정보조회[반품접수-물류배송지별]FO에서 주문건 제외
	 *  
	 * @param systemPK
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmWithGftFo(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 주문 - 반품접수 클레임접수기본정보조회[반품접수-물류배송지별]
	 *  
	 * @param systemPK
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClm(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 주문 - 반품접수 클레임접수기본정보조회[반품접수-물류배송지별] - FO에서 수선수량 제거
	 * @param systemPK
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmFo(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 주문 - 반품접수 옵션변경 가능 상품정보 조회
	 * 
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<GodExtend> selectBoItmHistForClm(OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 주문 - 반품접수 클레임접수기본정보조회[반품접수-물류배송지별]
	 * 
	 * @param systemPK
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<LgsDlvspExtend> selectCoDlvspList(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 클레임관리 - 반품 수정후 팝업 클레임기본정보조회[반품 - 물류배송지별]
	 * 
	 * @param systemPK
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<ClmWrhsGodResult> selectClmWrhsGodForRtnClmWithGft(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 배송지별 주문상품 내역 조회
	 * 
	 * @param systemPK
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<ClmWrhsGodResult> selectClmWrhsGodForRtnClm(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;

	/**
	 * 주문 배송지리스트 조회
	 * 
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<LgsDlvspExtend> selectDlvspListForOrd(OrderBoDTO orderDTO) throws Exception;

	/**
	 * 주문유형 조회
	 * 
	 * @param systemPK
	 * @param ordNo
	 * @return
	 */
	public String selectOrdTpCd(SystemPK systemPK, String ordNo);
	
	/*
	 * Claim
	 * ********************************************************************
	 * ****************************
	 */

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * 	- 픽업가능 매장 리스트 조회
	 *
	 * @param lgsDlivyDrctGodExtend 주문번호, 최대출고지시수량, 단품개수, 기존 픽업매장ID 등
	 * @return List<SysShopResult> 픽업매장 리스트
	 * @throws Exception
	 */
	public List<SysShopResult> selectPkupShop(LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend) throws Exception;

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * 	- 해당 픽업주문의 단품갯수, 최대 출고지시 수량 조회
	 *
	 * @param ordNo 주문번호
	 * @return
	 * @throws Exception
	 */
	public LgsDlivyDrctGodExtend getMaxDlivyCount4Pkup(String ordNo) throws Exception;
	
	/**
	 * #31984 [글로벌] BO 주문조회 페이지 개선 요청
	 * - 배송국가 리스트 조회
	 *
	 * @param comOvseaDlvZoneNationExtend
	 * @return List<ComOvseaDlvZoneNationExtend>
	 * @throws Exception
	 */
	public List<ComOvseaDlvZoneNationExtend> selectComOvseaDlvZoneNation(SystemPK systemPK, ComOvseaDlvZoneNationExtend comOvseaDlvZoneNationExtend) throws Exception;
	
	/**
	 * 무료수선가능대상상품 조회
	 * 
	 * @param systemPK
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrderRepairResult> selectOrdGodForRepair(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 무료수선가능대상상품 수 조회
	 *  
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public Integer selectOrdGodForRepairCount(OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 클레임 - 반품/교환 무료 배송비쿠폰 조회
	 * 
	 * @param systemPK
	 * @param clmCouponSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<ClmCouponResult> selectClmCouponList(SystemPK systemPK, ClmCouponSearchDTO clmCouponSearchDTO) throws Exception;
	
    /**
     * ERP상품번호, 옵션으로 상품, 단품정보 조회
     * 대량주문 등록시 상품을 엑셀로 업로드할 때 사용.
     * 
     * @param ordGodExtend
     * @return
     * @throws Exception
     */
	public GodExtend selectBoBulkOrdGodItmInfo(OrdGodExtend ordGodExtend) throws Exception;
	
	/**
	 * 단품갯수
	 * @param orderDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public int selectBoOrderListItemCount(OrderBoDTO orderDTO) throws Exception;
}
