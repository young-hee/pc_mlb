package com.plgrim.ncp.biz.order.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZoneNationExtend;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrd;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrndExtend;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.repository.god.GodRepository;
import com.plgrim.ncp.base.repository.lgs.LgsDlvspRepository;
import com.plgrim.ncp.base.repository.ord.OrdGodRepository;
import com.plgrim.ncp.base.repository.ord.OrdRepository;
import com.plgrim.ncp.biz.claim.data.ClaimBoDTO;
import com.plgrim.ncp.biz.claim.data.ClmCouponSearchDTO;
import com.plgrim.ncp.biz.claim.result.ClmCouponResult;
import com.plgrim.ncp.biz.claim.result.ClmGoodsCouponResult;
import com.plgrim.ncp.biz.order.data.OrdClmAplPrmDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.repository.OrderBoEmailRepository;
import com.plgrim.ncp.biz.order.repository.OrderBoSelectRepository;
import com.plgrim.ncp.biz.order.result.*;
import com.plgrim.ncp.commons.result.SysShopResult;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBoSelectService extends AbstractService {

	@Autowired
	OrderBoSelectRepository orderBoSelectRepository;

	@Autowired
	OrdRepository ordRepository; // 주문

	@Autowired
	GodRepository godRepository;// 주문 상품

	@Autowired
	OrdGodRepository ordGodRepository;

	@Autowired
	OrderBoEmailRepository orderBoEmailRepository;

	@Autowired
	LgsDlvspRepository lgsDlvspRepository;

	public Page<OrderBoResult> selectBoOrderList(OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		Page<OrderBoResult> selectBOOrderList = orderBoSelectRepository.selectBoOrderList(orderDTO, pageParam);

		return selectBOOrderList;
	}

	public Page<OrderBoResult> selectBoPayList(OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		Page<OrderBoResult> selectBoPayList = orderBoSelectRepository.selectBoPayList(orderDTO, pageParam);

		return selectBoPayList;
	}


	public Page<OrderBoResult> selectBoOrdGodList(OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		Page<OrderBoResult> selectBoOrdGodList = orderBoSelectRepository.selectBoOrdGodList(orderDTO, pageParam);

		return selectBoOrdGodList;
	}

	public Page<OrderBoResult> selectAffOrdList(OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		Page<OrderBoResult> selectAffOrdList = orderBoSelectRepository.selectAffOrdList(orderDTO, pageParam);

		return selectAffOrdList;
	}

	public Page<OrderBoResult> selectAffOrdErrList(OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		Page<OrderBoResult> selectAffOrdErrList = orderBoSelectRepository.selectAffOrdErrList(orderDTO, pageParam);

		return selectAffOrdErrList;
	}

	public List<OrderBoResult> selectBOGodList(OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectRepository.selectBOGodList(orderDTO);
	}

	public OrderBoResult selectBOOrderDt(OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectRepository.selectBOOrderDt(orderDTO);
	}

	public List<OrderBoResult> selectBOOrdClmList(OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectRepository.selectBOOrdClmList(orderDTO);
	}

	public List<OrdGodExtend> selectBoOrdGodGiftClmList(OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectRepository.selectBoOrdGodGiftClmList(orderDTO);
	}

	public GodExtend selectBoOrdGodItmHist(OrdGodExtend ordGodExtend) throws Exception {

		return orderBoSelectRepository.selectBoOrdGodItmHist(ordGodExtend);

	}

	public List<InfAffOrd> selectAffValidation(InfAffOrd infAffOrd) throws Exception {

		return orderBoSelectRepository.selectAffValidation(infAffOrd);

	}

	public List<OrdGodExtend> selectOrdGodList(OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectRepository.selectOrdGodList(orderDTO);
	}

	public List<GodExtend> selectBoItmHist(OrderBoDTO orderDTO) throws Exception {
		God god = new God();
		god.setGodNo(orderDTO.getGodNo());
		god = godRepository.selectGod(god);
		orderDTO.setGodNm(god.getGodNm());
		orderBoSelectRepository.selectBoItmHist(orderDTO);
		return orderBoSelectRepository.selectBoItmHist(orderDTO);

	}

	public List<OrdGodAplPrmExtend> selectOrdPrmDtlList(OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectRepository.selectOrdPrmDtlList(orderDTO);

	}

	public String getVirtlDlvComptYn(OrderBoDTO orderDTO) throws Exception {

		return orderBoSelectRepository.getVirtlDlvComptYn(orderDTO);

	}

	/*
	 * '픽업주문 관련해서 메시지 발송을 위해 SM조회
	 */
	public List<Ord> selectShopMaster4Pkup(String dlvShopId) throws Exception {
		return orderBoSelectRepository.selectShopMaster4Pkup(dlvShopId);
	}

	/********************************
	 * Claim
	 *********************************************************************/

	//주문번호로 주문 엔티티를 반환한다.
	public List<Ord> selectSendSmsForShopMaster(String ordNo) throws Exception {
		return orderBoSelectRepository.selectSendSmsForShopMaster(ordNo);
	}

	public OrdGod selectOrdGodEntity(OrdGod ordGod) throws Exception {
		return ordGodRepository.selectOrdGod(ordGod);
	}

	public List<OrdClmAplPrmDTO> getOrdClmAplPrmByOrd(OrdClmAplPrmDTO ordClmAplPrmDTO) {
		return orderBoSelectRepository.getOrdClmAplPrmByOrd(ordClmAplPrmDTO);
	}

	public List<OrdGod> selectOrdGodTypeCheck(OrdClmAplPrmDTO ordClmAplPrmDTO) {
		return orderBoSelectRepository.selectOrdGodTypeCheck(ordClmAplPrmDTO);
	}

	public List<OrdGodForClmResult> getOrdGodClmList(OrdClmAplPrmDTO ordClmAplPrmDTO) {
		return orderBoSelectRepository.getOrdGodClmList(ordClmAplPrmDTO);
	}

	public int getOrdGodClmListCount(OrdClmAplPrmDTO ordClmAplPrmDTO) {
		return orderBoSelectRepository.getOrdGodClmListCount(ordClmAplPrmDTO);
	}

	public List<LgsDlvspExtend> selectDlvspList(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectDlvspList(orderDTO);
	}

	public OrderBoResult selectOrdDCCount(OrderBoDTO orderDTO) {
		return orderBoSelectRepository.selectOrdDCCount(orderDTO);
	}

	public OrderBoResult selectEmailOrderCompt(OrderBoDTO orderDTO) throws Exception {

		return orderBoEmailRepository.selectEmailOrderCompt(orderDTO);

	}
	
	public OrderBoResult selectEmailOrderComptGlobal(OrderBoDTO orderDTO) throws Exception {
		return orderBoEmailRepository.selectEmailOrderComptGlobal(orderDTO);
	}

	public void lgsDlvMailSnd(LgsDlv lgsDlv) throws Exception {

		orderBoEmailRepository.lgsDlvMailSnd(lgsDlv);
		orderBoEmailRepository.lgsDlvMailSndHist(lgsDlv);
	}


	/**
	 * 주문 - 부분취소/반품 접수
	 * 클레임접수기본정보조회[주문상품별]
	 */
	public List<OrdGodForRtnClmDetailResult> selectOrdGodInfoWithGft(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectOrdGodInfoWithGft(orderDTO);
	}

	/**
	 * 주문 - 교환접수
	 * 클레임접수기본정보조회[주문상품별]
	 */
	public List<OrdGodForRtnClmDetailResult> selectOrdGodInfo(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectOrdGodInfo(orderDTO);
	}

	/**
	 * 주문 - 부분취소/반품 접수
	 * 클레임접수기본정보조회[물류배송지별]
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmWithGft(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectOrdGodForRtnClmWithGft(orderDTO);
	}

	/**
	 * 주문 - 부분취소/반품 접수
	 * 클레임접수기본정보조회[물류배송지별]
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmWithGftFo(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectOrdGodForRtnClmWithGftFo(orderDTO);
	}
	
	/**
	 * 주문 - 교환접수
	 * 클레임접수기본정보조회[물류배송지별]
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClm(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectOrdGodForRtnClm(orderDTO);
	}
	
	/**
	 * 주문 - 교환접수
	 * 클레임접수기본정보조회[물류배송지별] - FO에서 수선수량 제거
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmFo(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectOrdGodForRtnClmFo(orderDTO);
	}
	
	/**
	 * 주문 - 반품접수
	 * 클레임접수기본정보조회[반품접수-비회원수거지정보]
	 */
	public List<LgsDlvspExtend> selectCoDlvspList(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectCoDlvspList(orderDTO);
	}

	/**
	 * 주문 - 반품접수
	 * 옵션변경 가능 상품정보 조회
	 */
	public List<GodExtend> selectBoItmHistForClm(OrderBoDTO orderDTO) throws Exception {
		God god = new God();
		god.setGodNo(orderDTO.getGodNo());
		god = godRepository.selectGod(god);
		orderDTO.setGodNm(god.getGodNm());
//		orderBoSelectRepository.selectBoItmHistForClm(orderDTO);
		return orderBoSelectRepository.selectBoItmHistForClm(orderDTO);
	}

	/**
	 * 주문 - 교환접수
	 * 상품정보 조회[SKU_NO]
	 */
	public GodExtend selectGodItmInfo(ClmWrhsGodExtend clmWrhsGodExtend) throws Exception {
		return orderBoSelectRepository.selectGodItmInfo(clmWrhsGodExtend);
	}


	/**
	 * 클레임관리 - 반품 수정후 팝업
	 * 클레임기본정보조회[반품 - 물류배송지별]
	 */
	public List<ClmWrhsGodResult> selectClmWrhsGodForRtnClmWithGft(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectClmWrhsGodForRtnClmWithGft(orderDTO);
	}

	public List<ClmWrhsGodResult> selectClmWrhsGodForRtnClm(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectClmWrhsGodForRtnClm(orderDTO);
	}

	/**
	 * 주문배송지리스트
	 *
	 * @param claimDTO
	 * @return
	 * @throws Exception
	 */
	public List<LgsDlvspExtend> selectDlvspListForOrd(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectDlvspListForOrd(orderDTO);
	}

	public String selectOrdTpCd(String ordNo) {
		return orderBoSelectRepository.selectOrdTpCd(ordNo);
	}


	/**
	 * 주문 - 반품접수
	 * 주문사은품 상세정보조회
	 */
	public OrdGodExtend selectOrdGodGftDtl(OrdGod ordGod) throws Exception {
		return orderBoSelectRepository.selectOrdGodGftDtl(ordGod);
	}

	/********************************Claim*********************************************************************/

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * - 픽업가능 매장 리스트 조회
	 *
	 * @param lgsDlivyDrctGodExtend 주문번호, 최대출고지시수량, 단품개수, 기존 픽업매장ID 등
	 * @return List<SysShopResult> 픽업매장 리스트
	 * @throws Exception
	 */
	public List<SysShopResult> selectPkupShop(LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend) throws Exception {
		return this.orderBoSelectRepository.selectPkupShop(lgsDlivyDrctGodExtend);
	}

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * - 해당 픽업주문의 단품갯수, 최대 출고지시 수량 조회
	 *
	 * @param ordNo 주문번호
	 * @return
	 * @throws Exception
	 */
	public LgsDlivyDrctGodExtend getMaxDlivyCount4Pkup(String ordNo) throws Exception {
		return this.orderBoSelectRepository.getMaxDlivyCount4Pkup(ordNo);
	}

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * - 변경하고자 하는 픽업매장의 재고 다시 체크
	 *
	 * @param lgsDlivyDrctGodExtend
	 * @return
	 * @throws Exception
	 */
	public String selectItmInv4Pkup(LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend) throws Exception {
		return this.orderBoSelectRepository.selectItmInv4Pkup(lgsDlivyDrctGodExtend);
	}

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경 기능 추가
	 *
	 * @param sysShopBrndExtend
	 * @return 픽업매장ID
	 * @throws Exception
	 */
	public String selectPkupShopYn(SysShopBrndExtend sysShopBrndExtend) throws Exception {
		return this.orderBoSelectRepository.selectPkupShopYn(sysShopBrndExtend);
	}

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * - 픽업가능 매장 재고 체크를 위해 품번별 출고지시수량
	 *
	 * @param ordNo 주문번호
	 * @return
	 * @throws Exception
	 */
	public List<LgsDlivyDrctGodExtend> getDlivyDrctQty4Pkup(String ordNo) throws Exception {
		return this.orderBoSelectRepository.getDlivyDrctQty4Pkup(ordNo);
	}

	/**
	 * #31669 [픽업주문] 배송매장 알림 문자 발송 제한 시간 설정 및 문구 수정
	 * - 픽업매장의 '운영시간' 조회, 기본 '10:00:00@20:00:00'
	 *
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
	public String selectShopOperTime(String shopId) throws Exception {
		return this.orderBoSelectRepository.selectShopOperTime(shopId);
	}

	/**
	 * #31669 [픽업주문] 배송매장 알림 문자 발송 제한 시간 설정 및 문구 수정
	 * - '배정' 이전에 취소되는 경우에는 픽업매장이 임시매장(B031) 이므로
	 * 		LGS_DLVSP.PKUP_SHOP_ID 정보를 조회해서 픽업매장 정보로 사용
	 *
	 * @param lgsDlvsp
	 * @return
	 * @throws Exception
	 */
	public LgsDlvsp selectLgsDlvsp(LgsDlvsp lgsDlvsp) throws Exception {
		return this.lgsDlvspRepository.selectLgsDlvsp(lgsDlvsp);
	}
	
	/**
	 * #31984 [글로벌] BO 주문조회 페이지 개선 요청
	 * - 배송국가 리스트 조회
	 *
	 * @param comOvseaDlvZoneNationExtend
	 * @return List<ComOvseaDlvZoneNationExtend>
	 * @throws Exception
	 */
	public List<ComOvseaDlvZoneNationExtend> selectComOvseaDlvZoneNation(ComOvseaDlvZoneNationExtend comOvseaDlvZoneNationExtend) throws Exception {
		return this.orderBoSelectRepository.selectComOvseaDlvZoneNation(comOvseaDlvZoneNationExtend);
	}

	/**
	 * 무료수선대상상품
	 */
	public List<OrderRepairResult> selectOrdGodForRepair(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectOrdGodForRepair(orderDTO);
	}
	
	/**
	 * 무료수선대상상품 수 조회
	 */
	public Integer selectOrdGodForRepairCount(OrderBoDTO orderDTO) throws Exception {
		return orderBoSelectRepository.selectOrdGodForRepairCount(orderDTO);
	}
	
	/**
	 * 클레임 - 반품/교환 무료 배송비쿠폰 조회
	 */
	public List<ClmCouponResult> selectClmCouponList(ClmCouponSearchDTO clmCouponSearchDTO) throws Exception {
		return orderBoSelectRepository.selectClmCouponList(clmCouponSearchDTO);
	}
	
	public ClmGoodsCouponResult selectClmCouponInfo(ClmCouponSearchDTO clmCouponSearchDTO) {
		return orderBoSelectRepository.selectClmCouponInfo(clmCouponSearchDTO);
	}
	
	public long selectUsedClaimCouponGodCnt(ClmCouponSearchDTO clmCouponSearchDTO) {
		return orderBoSelectRepository.selectUsedClaimCouponGodCnt(clmCouponSearchDTO);
	}
	
    public void updateCouponRevertStatus(MbrIssuCpn mbrIssuCpn) throws Exception {

        //클레임 step - 쿠폰상태값 : 사용중지
        mbrIssuCpn.setCpnStatCd(PromotionEnum.CouponUseStatusCd.USE_STPGE.toString());
        orderBoSelectRepository.updateCouponRevertStatus(mbrIssuCpn);

        //쿠폰 복원 step - 쿠폰상태값 : 미사용
        mbrIssuCpn.setCpnStatCd(PromotionEnum.CouponUseStatusCd.NO_USE.toString());
        mbrIssuCpn.setClmNo(null);
        mbrIssuCpn.setCpnUseDt(null);
        orderBoSelectRepository.updateCouponRevertStatus(mbrIssuCpn);
    }
    
    public int selectClmWrhsGodNrmltCount(String clmNo) {
		return orderBoSelectRepository.selectClmWrhsGodNrmltCount(clmNo);
	}

    public void updateLgsDlvPrmInfoInit(LgsDlv lgsDlv) throws Exception {
		orderBoSelectRepository.updateLgsDlvPrmInfoInit(lgsDlv);
	}
    
    /**
	 * 가상주문번호 배송상품, 원주문 반품상품 동일 유무(수량포함) 체크
	 *
	 */
    public int selectDlivyDrctGodCompCount(ClaimBoDTO claimDTO) throws Exception {
		return orderBoSelectRepository.selectDlivyDrctGodCompCount(claimDTO);
	}
    
    /**
     * ERP상품번호, 옵션으로 상품, 단품정보 조회
     * 대량주문 등록시 상품을 엑셀로 업로드할 때 사용.
     * 
     * @param ordGodExtend
     * @return
     * @throws Exception
     */
	public GodExtend selectBoBulkOrdGodItmInfo(OrdGodExtend ordGodExtend) throws Exception {
		return orderBoSelectRepository.selectBoBulkOrdGodItmInfo(ordGodExtend);
	}
}
