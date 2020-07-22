package com.plgrim.ncp.biz.order.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZoneNationExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrd;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrndExtend;
import com.plgrim.ncp.biz.claim.data.ClaimBoDTO;
import com.plgrim.ncp.biz.claim.data.ClmCouponSearchDTO;
import com.plgrim.ncp.biz.claim.result.ClmCouponResult;
import com.plgrim.ncp.biz.claim.result.ClmGoodsCouponResult;
import com.plgrim.ncp.biz.order.data.OrdClmAplPrmDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.result.*;
import com.plgrim.ncp.commons.result.SysShopResult;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderBoSelectRepository extends AbstractRepository {

	
	public Page<OrderBoResult> selectBoPayList(OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		RepositoryHelper.makePageEntityIndex(orderDTO, pageParam);
		List<OrderBoResult> results = getSession1().selectList("com.plgrim.ncp.biz.order.selectBoPayList", orderDTO);

		// 전체 Row 수 구하기
		long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.order.selectBoPayListCount", orderDTO);
		return new PageImpl<OrderBoResult>(results, pageParam.getPageable(), totalRow);
	}
	
	
	public Page<OrderBoResult> selectBoOrderList(OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		RepositoryHelper.makePageEntityIndex(orderDTO, pageParam);
		List<OrderBoResult> results = getSession1().selectList("com.plgrim.ncp.biz.order.selectBoOrderList", orderDTO);
		
		// 전체 Row 수 구하기
		long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.order.selectBoOrderListCount", orderDTO);
		
		return new PageImpl<OrderBoResult>(results, pageParam.getPageable(), totalRow);
	}
	
	public int selectBoOrderListItemCount(OrderBoDTO orderDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectBoOrderListItemCount", orderDTO);
	}

	public Page<OrderBoResult> selectBoOrdGodList(OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		RepositoryHelper.makePageEntityIndex(orderDTO, pageParam);
		/*
		 * 1. 수정일자	: 2016-07-05
		 * 2. 수정자	    : 김재성 (jskim27)
		 * 3. 요청 SR NO	: #22623
		 * 4. 수정 내용	: BO 주문관리 > 상품별주문조회 오류 조치 요청
		 *      - 페이지 마지막 row 번호.
		 * 		- Query 속도저하 문제로 인해 '물자열'로 변경
		 */
		orderDTO.setEndIndexStr(String.valueOf(pageParam.getEndIndex()));

		List<OrderBoResult> results = getSession1().selectList("com.plgrim.ncp.biz.order.selectBoOrdGodList", orderDTO);

		// 전체 Row 수 구하기
		long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.order.selectBoOrdGodListCount", orderDTO);
		return new PageImpl<OrderBoResult>(results, pageParam.getPageable(), totalRow);
	}

	public Page<OrderBoResult> selectAffOrdList(OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		RepositoryHelper.makePageEntityIndex(orderDTO, pageParam);
		List<OrderBoResult> results = getSession1().selectList("com.plgrim.ncp.biz.order.selectAffOrdList", orderDTO);

		// 전체 Row 수 구하기
		long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.order.selectAffOrdCount", orderDTO);
		return new PageImpl<OrderBoResult>(results, pageParam.getPageable(), totalRow);
	}
	
	public Page<OrderBoResult> selectAffOrdErrList(OrderBoDTO orderDTO, PageParam pageParam) throws Exception {

		RepositoryHelper.makePageEntityIndex(orderDTO, pageParam);
		List<OrderBoResult> results = getSession1().selectList("com.plgrim.ncp.biz.order.selectAffOrdErrList", orderDTO);

		// 전체 Row 수 구하기
		long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.order.selectAffOrdErrCount", orderDTO);
		return new PageImpl<OrderBoResult>(results, pageParam.getPageable(), totalRow);
	}
	
	
	public List<OrderBoResult> selectBOGodList(OrderBoDTO orderDTO) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectBOGodList", orderDTO);
	}

	public List<OrderBoResult> selectBOOrdClmList(OrderBoDTO orderDTO) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectBOOrdClmList", orderDTO);
	}
	public List<OrdGodExtend> selectOrdGodList(OrderBoDTO orderDTO) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodList", orderDTO);
	}
	
	public List<OrdGodExtend> selectBoOrdGodGiftClmList(OrderBoDTO orderDTO) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectBoOrdGodGiftClmList", orderDTO);
	}

	public OrderBoResult selectBOOrderDt(OrderBoDTO orderDTO) throws Exception {


		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectBOOrderDt", orderDTO);

	}

	public GodExtend selectBoOrdGodItmHist(OrdGodExtend ordGodExtend) throws Exception {

		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectBoOrdGodItmHist", ordGodExtend);

	}

	public String getVirtlDlvComptYn(OrderBoDTO orderDTO) throws Exception {

		return getSession1().selectOne("com.plgrim.ncp.biz.order.getVirtlDlvComptYn", orderDTO);

	}

	public List<GodExtend> selectBoItmHist(OrderBoDTO orderDTO) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectBoItmHist", orderDTO);

	}

	public List<InfAffOrd> selectAffValidation(InfAffOrd infAffOrd) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectAffValidation", infAffOrd);

	}
	public List<OrdGodAplPrmExtend> selectOrdPrmDtlList(OrderBoDTO orderDTO) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdPrmDtlList", orderDTO);

	}

	public GodExtend selectBoOrdGodGift(OrdGodAplPrmExtend ordGodAplPrmExtend) throws Exception {

		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectBoOrdGodGift", ordGodAplPrmExtend);

	}
	public List<LgsDlvspExtend> selectDlvspList(OrderBoDTO orderDTO) throws Exception {


		return getSession1().selectList("com.plgrim.ncp.biz.order.selectDlvspList", orderDTO);

	}


	public List<OrderBoResult> selectAffTgtPrmList(OrderBoDTO orderDTO) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectAffTgtPrmList", orderDTO);

	}

	public List<OrderBoResult> selectAffGftList(OrderBoDTO orderDTO) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectAffGftList", orderDTO);

	}

	public List<OrderBoResult> selectAffGftGodList(OrderBoDTO orderDTO) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectAffGftGodList", orderDTO);

	}
	/*************************claim**********************************/
	public List<OrdClmAplPrmDTO> getOrdClmAplPrmByOrd(OrdClmAplPrmDTO ordClmAplPrmDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.getOrdClmAplPrmByOrd", ordClmAplPrmDTO);
	}

	public List<OrdGod> selectOrdGodTypeCheck(OrdClmAplPrmDTO ordClmAplPrmDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodTypeCheck", ordClmAplPrmDTO);
	}

	public List<OrdGodForClmResult> getOrdGodClmList(OrdClmAplPrmDTO ordClmAplPrmDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.getOrdGodClmList", ordClmAplPrmDTO);
	}

	public int getOrdGodClmListCount(OrdClmAplPrmDTO ordClmAplPrmDTO) {
		return Integer.parseInt(getSession1().selectOne("com.plgrim.ncp.biz.order.getOrdGodClmListCount", ordClmAplPrmDTO)+"");
	}

	public OrderBoResult selectOrdDCCount(OrderBoDTO orderDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectOrdDCCount", orderDTO);
	}

	
	
	/*
	 * 주문 - 부분취소/반품 클레임 접수화면 상단의 주문상품별 리스트 (상품사은품 포함)
	 */
	public List<OrdGodForRtnClmDetailResult> selectOrdGodInfoWithGft(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodInfoWithGft", orderDTO);
	}
	/*
	 * 주문 - 교환/맞교환 클레임 접수화면 상단의 주문상품별 리스트
	 */
	public List<OrdGodForRtnClmDetailResult> selectOrdGodInfo(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodInfo", orderDTO);
	}

	/*
	 * 주문 - 부분취소/반품 클레임 접수화면 하단의 배송지별 리스트 (상품사은품 포함)
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmWithGft(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodForRtnClmWithGft", orderDTO);
	}
	
	/*
	 * 주문 - 부분취소/반품 클레임 접수화면 하단의 배송지별 리스트 (상품사은품 포함)(FO에서 수선건제외 추가)
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmWithGftFo(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodForRtnClmWithGftFo", orderDTO);
	}
	
	/*
	 * 주문 - 교환/맞교환 클레임 접수화면 하단의 배송지별 리스트
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClm(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodForRtnClm", orderDTO);
	}
	
	/*
	 * 주문 - 교환/맞교환 클레임 접수화면 하단의 배송지별 리스트 - FO에서 수선수량 제거
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodForRtnClmFo(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodForRtnClmFo", orderDTO);
	}
	
	public List<OrdGodForRtnClmResult> selectOrdGodForCancelClm(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodForCancelClm", orderDTO);
	}



	/*
	 * 주문 - 반품접수 클레임접수기본정보조회[반품접수-비회원수거지정보]
	 */
	public List<LgsDlvspExtend> selectCoDlvspList(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectCoDlvspList", orderDTO);
	}

	/*
	 * 주문 - 교환접수
	 * 옵션변경 가능 상품정보 조회
	 */
	public List<GodExtend> selectBoItmHistForClm(OrderBoDTO orderDTO) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectBoItmHistForClm", orderDTO);

	}

	/*
	 * 주문 - 교환접수
	 * 상품정보 조회[SKU_NO]
	 */
	public GodExtend selectGodItmInfo(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectGodItmInfo", clmWrhsGodExtend);
	}
	
		
	/*
	 * 클레임관리 - 반품 수정 후 접수 [반품 - 물류배송지별]
	 */
	public List<ClmWrhsGodResult> selectClmWrhsGodForRtnClmWithGft(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectClmWrhsGodForRtnClmWithGft", orderDTO);
	}
	
	public List<ClmWrhsGodResult> selectClmWrhsGodForRtnClm(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectClmWrhsGodForRtnClm", orderDTO);		
	}
	
	
	/**
	 * 주문배송지리스트
	 * @param claimDTO
	 * @return
	 */
	public List<LgsDlvspExtend> selectDlvspListForOrd(OrderBoDTO orderDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.order.selectDlvspListForOrd", orderDTO);
	}


	public String selectOrdTpCd(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectOrdTpCd", ordNo);
    }

	/**
	 * 픽업주문 취소 시 관리자 핸드폰정보 조회
	 * @param claimDTO
	 * @return
	 */
	public List<Ord> selectSendSmsForShopMaster(String ordNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectSendSmsForShopMaster", ordNo);
    }
		
	
	/**
	 * 픽업주문 취소 시 관리자 핸드폰정보 조회
	 * @param claimDTO
	 * @return
	 */
	public List<Ord> selectShopMaster4Pkup( String dlvShopId ) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectShopMaster4Pkup", dlvShopId);
    }		
	
	/**
	 * 주문 - 반품접수
	 * 주문사은품 상세정보조회
	 */
	public OrdGodExtend selectOrdGodGftDtl(OrdGod ordGod) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectOrdGodGftDtl", ordGod);
	}
	
	/**
	 * 현재 주문상태 조회
	 *
	 */
	public String getOrdStatCd(Ord ord) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.getOrdStatCd", ord);
	}

	/**
	 * 상품옵션 정보에 '_' 존재하는 경우 우선 GOD_ITM 정보를 조회, 상품존재여부를 개수로 리턴
	 */
	public int getGodCountByItmNm(OrderBoDTO orderDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.getGodCountByItmNm", orderDTO);
	}

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * 	- 픽업가능 매장 리스트 조회
	 *
	 * @param lgsDlivyDrctGodExtend 주문번호, 최대출고지시수량, 단품개수, 기존 픽업매장ID 등
	 * @return List<SysShopResult> 픽업매장 리스트
	 * @throws Exception
	 */
	public List<SysShopResult> selectPkupShop(LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectPkupShop", lgsDlivyDrctGodExtend);
	}

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * 	- 해당 픽업주문의 단품갯수, 최대 출고지시 수량 조회
	 *
	 * @param ordNo 주문번호
	 * @return
	 * @throws Exception
	 */
	public LgsDlivyDrctGodExtend getMaxDlivyCount4Pkup(String ordNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.getMaxDlivyCount4Pkup", ordNo);
	}

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * 	- 변경하고자 하는 픽업매장의 재고 다시 체크
	 * @param lgsDlivyDrctGodExtend
	 * @return
	 * @throws Exception
	 */
	public String selectItmInv4Pkup(LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectItmInv4Pkup", lgsDlivyDrctGodExtend);
	}

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경 기능 추가
	 * @param sysShopBrndExtend
	 * @return 픽업매장ID
	 * @throws Exception
	 */
	public String selectPkupShopYn(SysShopBrndExtend sysShopBrndExtend) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectPkupShopYn", sysShopBrndExtend);
	}

	/**
	 * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경
	 * 	- 픽업가능 매장 재고 체크를 위해 품번별 출고지시수량
	 *
	 * @param ordNo 주문번호
	 * @return
	 * @throws Exception
	 */
	public List<LgsDlivyDrctGodExtend> getDlivyDrctQty4Pkup(String ordNo) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.order.getDlivyDrctQty4Pkup", ordNo);
	}

	/**
	 * #31669 [픽업주문] 배송매장 알림 문자 발송 제한 시간 설정 및 문구 수정
	 *	- 픽업매장의 '운영시간' 조회, 기본 '10:00:00@20:00:00'
	 *
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
	public String selectShopOperTime(String shopId) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectShopOperTime", shopId);
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
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectComOvseaDlvZoneNation", comOvseaDlvZoneNationExtend);
	}

	public String selectBaseDlvComCd(String mallId) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectBaseDlvComCd", mallId);
	}


	/**
	 * 무료수선서비스
	 *
	 * @param orderDTO the order dto
	 * @return the list
	 */
	public List<OrderRepairResult> selectOrdGodForRepair(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodForRepair", orderDTO);
	}
	
	/**
	 * 무료수선서비스 대상 상품 수 조회
	 *
	 * @param orderDTO the order dto
	 * @return the list
	 */
	public Integer selectOrdGodForRepairCount(OrderBoDTO orderDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectOrdGodForRepairCount", orderDTO);
	}
	
	/**
	 * 클레임 - 반품/교환 무료 배송비쿠폰 조회
	 */
	public List<ClmCouponResult> selectClmCouponList(ClmCouponSearchDTO clmCouponSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.selectClmCouponList", clmCouponSearchDTO);
	}
	
	/**
	 * 클레임 프로모션 정보조회
	 * @param clmCouponSearchDTO
	 */
	public ClmGoodsCouponResult selectClmCouponInfo(ClmCouponSearchDTO clmCouponSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectClmCouponInfo", clmCouponSearchDTO);
	}
	
	/**
	 * 클레임 프로모션(무료 반품/교환 배송비쿠폰) 사용된 상품갯수 조회
	 * @param clmCouponSearchDTO
	 */
	public long selectUsedClaimCouponGodCnt(ClmCouponSearchDTO clmCouponSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectUsedClaimCouponGodCnt", clmCouponSearchDTO);
	}
	
	/**
     * 클레임 사용 쿠폰 복원
     *
     * @param mbrIssuCpn
     * @throws Exception
     */
    public void updateCouponRevertStatus(MbrIssuCpn mbrIssuCpn) throws Exception {
    	getSession1().update("com.plgrim.ncp.biz.claim.manage.updateCouponRevertStatus", mbrIssuCpn);
    }
    
	public int selectClmWrhsGodNrmltCount(String clmNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmWrhsGodNrmltCount", clmNo);
    }
	
    public void updateLgsDlvPrmInfoInit(LgsDlv lgsDlv) throws Exception {
    	getSession1().update("com.plgrim.ncp.biz.claim.manage.updateLgsDlvPrmInfoInit", lgsDlv);
    }
    
	/**
	 * 가상주문번호 배송상품, 원주문 반품상품 동일 유무(수량포함) 체크
	 *
	 * @param orderDTO the order dto
	 * @return Integer
	 */
    public int selectDlivyDrctGodCompCount(ClaimBoDTO claimDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectDlivyDrctGodCompCount", claimDTO);
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
		return getSession1().selectOne("com.plgrim.ncp.biz.order.selectBoBulkOrdGodItmInfo", ordGodExtend);
	}
}
