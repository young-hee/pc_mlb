package com.plgrim.ncp.cmp.orderfulfilment.bo.claim;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExcpCd;
import com.plgrim.ncp.biz.claim.data.ClaimBoDTO;
import com.plgrim.ncp.biz.claim.data.ClaimListSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClaimRefundListSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClaimSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClmOrdGodAplPrm;
import com.plgrim.ncp.biz.claim.result.ClaimListResult;
import com.plgrim.ncp.biz.claim.result.ClaimRefundListResult;
import com.plgrim.ncp.biz.claim.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.claim.result.OrdDetailClmInfoResult;
import com.plgrim.ncp.biz.delivery.data.LgsDlvspPkupDTO;
import com.plgrim.ncp.biz.order.data.OrdClmAplPrmDTO;
import com.plgrim.ncp.biz.order.data.SysExchgRtDTO;
import com.plgrim.ncp.biz.order.result.OrdGodForClmResult;
import com.plgrim.ncp.biz.pay.data.ClmRefundDTO;
import com.plgrim.ncp.biz.pay.data.ClmRefundSearchDTO;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

public interface ClaimSelectComponent {

	/**
	 * 주문상세에서 클레임 접수 팝업 호출시 화면상단의 상품리스트
	 * 
	 * @param ordClmAplPrmDTO
	 * @return
	 */
    List<OrdGodForClmResult> getOrdGodClmList(OrdClmAplPrmDTO ordClmAplPrmDTO);

    /**
     * 주문상세에서 클레임 접수 팝업 호출시 선택한 상품의 주문리스트 카운트
     * 
     * @param ordClmAplPrmDTO
     * @return
     */
    int getOrdGodClmListCount(OrdClmAplPrmDTO ordClmAplPrmDTO);

    /**
     * 주문 전체취소 / 부분취소 환불금 계산
     * @param clmRefundSearchDTO
     * @return
     * @throws Exception
     */
    public ClmRefundDTO selectOrdGodForClmRefund(ClmRefundSearchDTO clmRefundSearchDTO) throws Exception;
    
    /**
     * 주문 전체취소 / 부분취소 환불금 계산 (ordGodErp 기반으로 환불금액을 계산한다.)
     * 
     * @param clmRefundSearchDTO
     * @return
     * @throws Exception
     */
    public ClmRefundDTO selectOrdGodErpForClmRefund(ClmRefundSearchDTO clmRefundSearchDTO) throws Exception;
    
    /**
     * 결제리스트 조회
     * 
     * @param pay
     * @return
     */
    List<Pay> selectOrdClmPay(Pay pay);        
    
    /**
     * 주문번호와 배송지 순번으로 배송지와 픽업매장정보를 가져온다
     * 클레임 접수 화면 배송지 별 상품 리스트에서 사용
     * 
     * @param lgsDlvsp
     * @return
     */
    public LgsDlvspPkupDTO selectLgsDlvspPkup(LgsDlvspExtend lgsDlvsp);

    /**
     * 클레임 검증
     * 
     * @param claimSearchDTO
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> claimValidation(ClaimSearchDTO claimSearchDTO) throws Exception;

    /**
     * 주문상세 페이지 클레임 정보
     * 
     * @param claimSearchDTO
     * @return
     * @throws Exception
     */
    List<OrdDetailClmInfoResult> selectClaimInfo(ClaimSearchDTO claimSearchDTO) throws Exception;

    /**
     * 주문상세페이지 클레임정보
     *  
     * @param claimSearchDTO
     * @return
     */
    long selectClaimInfoCnt(ClaimSearchDTO claimSearchDTO);

    /**
     * 클레임으로 DB에 생성되어있는 환불정보를 가져오는 컴포넌트
     * 
     * @param clmNo
     * @return
     * @throws Exception
     */
	ClmRefundDTO getClmRefundInfoList(String clmNo)  throws Exception ;
	
	/**
	 * 클레임으로 DB에 생성되어있는 환불정보를 가져오는 컴포넌트
	 * 
	 * @param clmNo
	 * @return
	 * @throws Exception
	 */
	ClmRefundDTO getClmRefundInfoListEx(String clmNo)  throws Exception ;

	/**
	 * 지연클레임 리스트 조회
	 * 
	 * @param claimListSearchDTO
	 * @return
	 */
    List<ClaimListResult> selectClaimDelayList(ClaimListSearchDTO claimListSearchDTO);

    /**
     * 지연클레임 총 개수 조회
     * 
     * @param claimListSearchDTO
     * @return
     */
    long selectClaimDelayListTotal(ClaimListSearchDTO claimListSearchDTO);

    /**
     * 결품리스트 조회
     * 
     * @param claimListSearchDTO
     * @return
     */
    List<DeliveryOrderGoodResult> selectOrderGoodLackList(ClaimListSearchDTO claimListSearchDTO);
    
    /**
     * 결품리스트 조회 [엑셀]
     * 
     * @param claimListSearchDTO
     * @return
     */
    public List<ClaimListResult> selectOrderGoodLackListForExcel(ClaimListSearchDTO claimListSearchDTO);

    /**
     * 결품리스트 카운트
     * 
     * @param claimListSearchDTO
     * @return
     */
    long selectOrderGoodLackListTotal(ClaimListSearchDTO claimListSearchDTO);

    /**
     * 환불관리 조회
     * @param claimRefundListSearchDTO
     * @return
     */
    List<ClaimRefundListResult> selectClaimRefundList(ClaimRefundListSearchDTO claimRefundListSearchDTO);

    /**
     * 환불관리 조회 카운트
     * @param claimRefundListSearchDTO
     * @return
     */
    long selectClaimRefundListTotal(ClaimRefundListSearchDTO claimRefundListSearchDTO);

    /**
     * 결제 데이터 조회
     * 
     * @param pay
     * @return
     */
	List<PayExtend> selectOrdClmPayExtend(PayExtend payExtend);

	/**
	 * 클레임 base 조회
	 * 
	 * @param clm
	 * @return
	 * @throws Exception
	 */
	Clm selectClm(Clm clm) throws Exception;

    /**
	 * 클레임 접수화면에서 취소배송비계산
	 * ncp2
	 */
    public List<LgsDlvFoExtend> selectCnclClmLgsDlvCstCal(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception;


	/**
	 * [클레임관리 - 반품배송비 계산]
	 * ncp2
	 */
	List<LgsDlvspExtend> selectRtgodDlvCstCal(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception;

    /**
     * [클레임관리 - 교환배송비 계산]
     * ncp2
     */
    List<LgsDlvspExtend> selectExchgDlvCstCal(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception;
    
    /**
     * 환율정보를 구한다.
     * @param crncyCd
     * @param exchgRtAplBegDtStr
     * @return
     */
	public SysExchgRtDTO selectExchangeRt(String crncyCd,String exchgRtAplBegDtStr);
	
	/**
	 * 환율정보를 구한다.
	 * 
	 * @param crncyCd
	 * @param exchgRtAplBegDt
	 * @return
	 */
	public SysExchgRtDTO selectExchangeRt(String crncyCd,Date exchgRtAplBegDt);

	/**
	 * 클레임 상세 페이지 배송비 팝업
	 * 
	 * @param clmNo
	 * @param clmTp
	 * @return
	 * @throws Exception
	 */
	ClmRefundDTO selectDlvFeeShow(String clmNo, String clmTp) throws Exception ;

    /**
     * 클레임 번호로 물류배송 테이블을 조회하여 엔티티 리스트로 리턴한다. (배송비 환불금액 추출위해서 작성)
     * 
     * @param clmNo
     * @return
     * @throws Exception
     */
    public List<LgsDlv> selectLgsDlvByClmNo(String clmNo) throws Exception ;
    
    /**
     * 수량단위의 할인금액을 구한 후 환율적용 : 판매금액 환율적용가 - 결제환율통화금액 = 상품별 할인금액
     * 
     * @param ordNo
     * @return
     * @throws Exception
     */
    public BigDecimal selectAllDcExForOrd(String ordNo) throws Exception ;
    
    /**
     * 수량단위의 할인금액을 구한 후 환율적용 : 판매금액 환율적용가 - 결제환율통화금액 = 상품별 할인금액
     * 
     * @param clmNo
     * @return
     * @throws Exception
     */
    public BigDecimal selectAllDcExForClm(String clmNo) throws Exception ;
    
    /**
     * 받은 환율 정보로 적용대상금액을 환율적용해준다.
     * 
     * @param sysExchgRtDTO
     * @return
     * @throws Exception
     */
    public String applyExchangeRt(SysExchgRtDTO sysExchgRtDTO) throws Exception ;

    /**
     * 주문 번호로 물류배송 테이블을 조회하여 엔티티 리스트로 리턴한다. (배송비 환불금액 추출위해서 작성)
     * 
     * @param ordNo
     * @return
     * @throws Exception
     */
    public List<LgsDlv> selectLgsDlvByOrdNo(String ordNo) throws Exception ;

    /**
     * 국내 기본 배송정책 가져오기
     * 
     * @return
     * @throws Exception
     */
    String selectBaseDlvComCd(String mallId) throws Exception;

    /**
     * 자사 기본 배송업체 조회
     * 
     * @param lang
     * @return
     * @throws Exception
     */
    Map<String, String> selectBaseDlvCom(String lang) throws Exception;

    /**
     * 몰별 자사 기본 배송업체 조회
     * 
     * @param lang
     * @return
     * @throws Exception
     */
    Map<String, String> selectBaseDlvCom(String lang, String mallId) throws Exception;

    /**
     * 무료수선신청목록
     *
     * @param searchDTO the search dto
     * @param pageParam the page param
     * @return the list
     * @throws Exception the exception
     */
    public List<ClaimListResult> selectRepairRegList(ClaimListSearchDTO searchDTO, PageParam pageParam) throws Exception ;

    /**
     * 수선일 경우 자사 기본 배송정책 조회
     * 
     * @param clmTpCd
     * @return
     * @throws Exception
     */
    public Map<Long, ComDmstcDlvCstPlcExtend> getDeliveryRuleMapForRepair (String clmTpCd) throws Exception;
    
    /** [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청 
     *  집하상태코드  
     */
    public List<SysExcpCd> selectSysExcpCdList(SysExcpCd sysExcpCd) throws Exception;
}
