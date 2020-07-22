package com.plgrim.ncp.biz.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefPymntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGftExchg;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.repository.MemberBenefitApiSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberBenefitSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberOrderSelectRepository;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.result.MemberReserveResult;
import com.plgrim.ncp.biz.member.result.MypageCpnFoResult;
import com.plgrim.ncp.biz.member.result.PurpleCoinMemberBoResult;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원혜택정보 select service
 */
@Service
@Slf4j
public class MemberBenefitSelectService extends AbstractService {

	/** IF 공통. */
	@Autowired
	InterfaceApiCommon interfaceApiCommon;


	@Autowired
	MemberBenefitSelectRepository memberBenefitSelectRepository;

	@Autowired
	MemberOrderSelectRepository memberOrderSelectRepository;

	@Autowired
	MemberBenefitApiSelectRepository memberBenefitApiSelectRepository;


	private final static List<String> bpCbHistoryDisplayCbTypeList = new ArrayList<String>();
	{
		// 10 적립,11 사용,12 차감,20 조정,21 통합,22 삭제,23 휴면,24 이관,30 이벤트 ,40 전환,50
		// 수선,60 마일리지전환,90 초기조정,25 스크랩,26 패션피아 상품권,27 신규회원가입,28 재구매,29 매장오픈,31
		// 고객생일,32 감사의달
		// 위와같은 멤버십포인트이력(BpCbHistory) cbtype중 노출되어야 하는 코드 리스트만 ADD 해서 사용하도록 함.
		// TODO : erp에서 관리하는 코드로 정확한 코드가 확인이 된다면 하드코딩 된 부분 상수로 정리 필요.

		bpCbHistoryDisplayCbTypeList.add("20");
		bpCbHistoryDisplayCbTypeList.add("21");
		bpCbHistoryDisplayCbTypeList.add("22");
		bpCbHistoryDisplayCbTypeList.add("23");
		bpCbHistoryDisplayCbTypeList.add("26");
		bpCbHistoryDisplayCbTypeList.add("27");
		bpCbHistoryDisplayCbTypeList.add("30");
		bpCbHistoryDisplayCbTypeList.add("60");
	}

	/**
	 * 상품평으로 인한 적립정보 조회
	 */
	public List<MbrWebpntHistExtend> selectWebPointInfoByGodEvl(MbrWebpntHist mbrWebpntHist)  {
		return memberBenefitSelectRepository.selectWebPointInfoByGodEvl(mbrWebpntHist);
	}

	/**
	 * 상품평으로 인한 적립정보 조회 (MLB, SA)
	 */
	public List<MbrWebpntHistExtend> selectWebPointInfoByCriteria(MbrWebpntHist mbrWebpntHist)  {
		return memberBenefitSelectRepository.selectWebPointInfoByCriteria(mbrWebpntHist);
	}
	
	/**
	 * P포인트 현황 조회
	 */
	public MbrWebpntHistExtend selectWebPointInfo(MbrWebpntHist mbrWebpntHist)  {

		if(mbrWebpntHist.getMallId() == null || mbrWebpntHist.getMallId().isEmpty()){
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);

			mbrWebpntHist.setMallId(systemPK.getMall());
		}

		return memberBenefitSelectRepository.selectWebPointInfo(mbrWebpntHist);
	}

	/**
	 * My 쿠폰 목록 조회.
	 *
	 * @param mbrIssuCpn
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public Page<MypageCpnFoResult> selectMyCouponList(MypageFoDTO dto, PageParam pageParam)  {
		return memberBenefitSelectRepository.selectMyCouponList(dto, pageParam);
	}

	/**
	 * My 쿠폰 이력 목록 조회.
	 *
	 * @param mbrIssuCpn
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public Page<MypageCpnFoResult> selectMyCouponHistList(MypageFoDTO dto, PageParam pageParam)  {
		return memberBenefitSelectRepository.selectMyCouponHistList(dto, pageParam);
	}

	/**
	 * 마이페이지 - P포인트 내역 조회
	 *
	 * @param mbrWebpntHist
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public Page<MemberFoResult> selectMyPurpleCoinList(MbrWebpntHist mbrWebpntHist, PageParam pageParam)
	{
		return memberBenefitSelectRepository.selectMyPurpleCoinList(mbrWebpntHist, pageParam);
	}

	/**
	 * 마이페이지 - P포인트 내역 조회
	 *
	 * @param mbrWebpntHist
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public Page<MemberFoResult> selectMyPurpleCoinListForMall(MbrWebpntHist mbrWebpntHist, PageParam pageParam)
			 {
		return memberBenefitSelectRepository.selectMyPurpleCoinListForMall(mbrWebpntHist, pageParam);
	}

	/**
	 * My 쿠폰 적용가능 상품목록 조회.
	 *
	 * @param mbrIssuCpn
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public Page<MypageCpnFoResult> selectMyCouponGoodList(MypageFoDTO dto, PageParam pageParam)  {
		return memberBenefitSelectRepository.selectMyCouponGoodList(dto, pageParam);
	}

	/**
	 * My 쿠폰 목록 조회.
	 *
	 * @param mbrIssuCpn
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public List<MypageCpnFoResult> selectMyCouponComboList(MypageFoDTO dto)  {
		return memberBenefitSelectRepository.selectMyCouponComboList(dto);
	}

	/** 수신동의 유도 캠페인 쿠폰 발급 카운트 */
	public int selectMyCampaginCouponCount(MbrIssuCpn mbrIssuCpn)  {
		return memberBenefitSelectRepository.selectMyCampaginCouponCount(mbrIssuCpn);
	}

	/**
	 * 멤버십포인트/마일리지 잔액 조회 RFC 통합으로 인해 포인트와 마일리지를 한 RFC를 사용하여 조회하도록 변경됨. split
	 * 1차때는 회원만 범위에 들어가므로 테스트 영향범위로 인해 회원쪽만 수정하며, 이후 주문쪽도 수정하도록 하자.
	 *
	 * @param erpNo
	 * @return
	 * @
	 */
	public MemberReserveResult getMemberPointMilageRemainAmount(String erpNo)  {



		MemberReserveResult memberReserveResult = new MemberReserveResult();
		memberReserveResult.setReserve(0L);

		// step 3. 포인트 잔액 값 설정

		String remainPoint = "0";

		if (StringService.isNumeric(remainPoint)) {
			memberReserveResult.setReserve(Long.parseLong(remainPoint));
		}

		String remainMilage = "0";

		return memberReserveResult;
	}


	/**
	 * 임식삭감 포인트 잔액 조회.
	 *
	 * @param systemPK
	 *            [설명]
	 * @param erpNo
	 *            [설명]
	 * @return Long [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public long getMemberTempDelReserveRemainAmount(SystemPK systemPK, String erpNo)  {
		// step 1. ERP 연동 - 임시삭감 포인트는 잔액조회 IF 없어서 내역을 조회하여 처리.
		List<Map<String, String>> reserveList = this.getMemberTempDelReserveHistory(systemPK, erpNo);

		// step 2. 포인트 잔액 값 설정
		long returnAmount = 0L;
		for (Map<String, String> map : reserveList) {
			String reserveRemainAmount = map.get("cbBalAmt");
			returnAmount += Long.parseLong(reserveRemainAmount);
		}

		return returnAmount;
	}

	/**
	 * 임시/삭감 포인트 내역 조회.
	 *
	 * @param systemPK
	 *            [설명]
	 * @param erpNo
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public List<Map<String, String>> getMemberTempDelReserveHistory(SystemPK systemPK, String erpNo)  {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

		return resultList;
	}

	/**
	 * 멤버쉽 포인트 내역 조회.
	 *
	 * @param systemPK
	 *            [설명]
	 * @param erpNo
	 *            [설명]
	 * @return List [멤버쉽포인트 목록]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public List<Map<String, String>> getMembershipPointHistory(SystemPK systemPK, String erpNo, MypageFoDTO myPageFoDTO)
			 {
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		return returnList;
	}

	/**
	 * 상품명 조회
	 *
	 * @param mbrSavMnyHist
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public String selectErpGoodNm(String matNo)  {
		MypageFoDTO dto = new MypageFoDTO();
		dto.setMatNo(matNo);
		return memberOrderSelectRepository.selectErpGoodNm(dto);
	}

	/**
	 * 멤버쉽 포인트 잔액 조회.
	 *
	 * @param erpNo
	 *            [ERP 번호]
	 * @return Long [멤버쉽포인트 잔액]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public long getMemberReserveRemainAmount(String erpNo)  {
		long returnAmount = 0L;

		try {
			log.info(CommonResponseCode.MB_00051_통합회원_멤버십_포인트_조회_시도_포인트.toMessage());

			// step 3. 포인트 잔액 값 설정
			String reserveRemainAmount = "0";
			if (StringService.isNumeric(reserveRemainAmount)) {
				returnAmount = Long.parseLong(reserveRemainAmount);
			}

			log.info(CommonResponseCode.MB_00052_통합회원_멤버십_포인트_조회_성공_포인트.toMessage());
		} catch (Exception e) {
			log.info(CommonResponseCode.MB_40021_통합회원_멤버십_포인트_조회_실패_포인트.toMessage() + " : " + e);
			throw new RuntimeException(e);
		}

		return returnAmount;
	}

	public int selectMyCouponCnt(MypageFoDTO dto)  {
		return memberBenefitSelectRepository.selectMyCouponCnt(dto);
	}

	public Map<String, String> selectWebPointInfoMap(String mbrNo)  {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);

		Map<String, String> rtnMap = new HashMap<String, String>();
		MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
		mbrWebpntHist.setMbrNo(mbrNo);
		mbrWebpntHist.setMallId(systemPK.getMall());

		MbrWebpntHistExtend rtnDto = memberBenefitSelectRepository.selectWebPointInfo(mbrWebpntHist);
		rtnMap.put("use", String.valueOf(rtnDto.getUsefulAmt())); /* 가용금액 */
		rtnMap.put("exp", String.valueOf(rtnDto.getAccmlPrearngeAmt())); /* 적립예정 */
		rtnMap.put("ext", String.valueOf(rtnDto.getExtshPrearngeAmt())); /* 소멸예정 */
		return rtnMap;
	}

	/**
	 * 회원 혜택 지급 이력 Count
	 *
	 * @param mbrBnefPymntHist
	 * @return
	 * @
	 */
	public int selectMbrBnefPymntHistCount(MbrBnefPymntHist mbrBnefPymntHist)  {
		return memberBenefitApiSelectRepository.selectMbrBnefPymntHistCnt(mbrBnefPymntHist);
	}

	public MbrBnefPymntHist selectMbrBnefPymntHist(MbrBnefPymntHist mbrBnefPymntHist)  {
		return memberBenefitApiSelectRepository.selectMbrBnefPymntHist(mbrBnefPymntHist);
	}

	/** 회원 보유쿠폰 카운트 */
	public long selectMyCouponsCount(MbrIssuCpn mbrIssuCpn)  {
		return memberBenefitSelectRepository.selectMyCouponsCount(mbrIssuCpn);
	}

	public PrmCpnGftExchg selectEscPrmCpnGftExchg(PrmCpnGftExchg prmCpnGftExchg)  {
		return memberBenefitSelectRepository.selectEscPrmCpnGftExchg(prmCpnGftExchg);
	}

	/**
	 * AdapterHeader 값 설정
	 *
	 * @return
	 */
	private AdapterHeader setAdapterHeader() {
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");

		return adapterHeader;
	}

	public long selectMyCouponCount(MemberBoDTO dto)  {
		return memberBenefitSelectRepository.selectMyCouponCount(dto);
	}

	public List<MypageCpnFoResult> selectMyOnOffCouponList(MypageFoDTO dto)  {
		return memberBenefitSelectRepository.selectMyOnOffCouponList(dto);
	}

	/**
	 * 회원 발행 쿠폰 목록 건수 조회.
	 *
	 * @param mbrIssuCpn
	 *            [설명]
	 * @return Long [설명]
	 * @
	 *             the exception
	 * @since 2015. 4. 21
	 */
	public long selectCouponListCountForMember(MemberBoDTO dto)  {
		return memberBenefitSelectRepository.selectCouponListCountForMember(dto);
	}

	/**
	 * 회원 발행 쿠폰 목록 조회.
	 *
	 * @param mbrIssuCpn
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 4. 21
	 */
	public List<MemberBoResult> selectCouponListForMember(MemberBoDTO dto)  {
		return memberBenefitSelectRepository.selectCouponListForMember(dto);
	}

	/**
	 * 회원 발행 쿠폰 엑셀 조회.
	 *
	 * @param mbrIssuCpn
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 4. 21
	 */
	public List<Map<String, Object>> selectCouponListExcelForMember(MemberBoDTO dto)  {
		return memberBenefitSelectRepository.selectCouponListExcelForMember(dto);
	}

	/**
	 * 멤버쉽 포인트 내역 조회.
	 *
	 * @param systemPK
	 *            [설명]
	 * @param erpNo
	 *            [설명]
	 * @return List [멤버쉽포인트 목록]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public List<Map<String, String>> getMemberReserveHistory(SystemPK systemPK, String erpNo)  {
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		return returnList;
	}

	/**
	 * 멤버쉽 포인트 내역 엑셀 조회 - 엑셀 출력 순서.
	 *
	 * @param systemPK
	 *            [설명]
	 * @param erpNo
	 *            [설명]
	 * @return List [멤버쉽포인트 목록]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public List<Map<String, Object>> getMemberReserveExcel(SystemPK systemPK, String erpNo)  {
		List<Map<String, String>> excelList = getMemberReserveHistory(systemPK, erpNo);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		for (Map<String, String> map : excelList) {
			Map<String, Object> newMap = this.setMemberReserveData(map);
			returnList.add(newMap);
		}
		return returnList;
	}

	/**
	 * 멤버쉽 포인트 내역 엑셀 조회 - Map에 데이타 담기.
	 *
	 * @param map
	 * @return
	 */
	private Map<String, Object> setMemberReserveData(Map<String, String> map) {
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		newMap.put("pointTypeNm", map.get("pointTypeNm")); // 포인트 유형
		newMap.put("name1", map.get("name1")); // 매장명
		newMap.put("regDt", map.get("regDt")); // 적립일
		newMap.put("cbTypeNm", map.get("cbTypeNm")); // 작업유형내역
		newMap.put("ordNo", map.get("ordNo")); // 주문번호
		newMap.put("amtPur", map.get("amtPur")); // 구매금액
		newMap.put("cbA", map.get("cbA")); // 적립금액
		newMap.put("cbU", map.get("cbU")); // 사용금액
		newMap.put("rcbBalAmt", map.get("rcbBalAmt")); // 포인트잔액

		return newMap;
	}

	/**
	 * 이벤트 포인트 내역 조회 - 현재 협의된 내용 없음.
	 *
	 * @param systemPK
	 *            [설명]
	 * @param erpNo
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public List<Map<String, String>> getMemberEventReserveHistory(SystemPK systemPK, String erpNo)  {
		return new ArrayList<Map<String, String>>();
	}

	/**
	 * P포인트회원 목록 건수 조회.
	 *
	 * @param dto
	 *            [설명]
	 * @return Long [설명]
	 * @
	 *             the exception
	 * @since 2015. 4. 17
	 */
	public long selectPurpleCoinMemberListCount(MemberBoDTO dto)  {
		return memberBenefitSelectRepository.selectPurpleCoinMemberListCount(dto);
	}

	/**
	 * P포인트회원 목록 조회.
	 *
	 * @param dto
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 4. 7
	 */
	public List<PurpleCoinMemberBoResult> selectPurpleCoinMemberList(MemberBoDTO dto)  {
		RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam()); // 페이지 설정
		return memberBenefitSelectRepository.selectPurpleCoinMemberList(dto);
	}

	/** 회원 보유쿠폰 조회 리스트 */
	public List<MypageCpnFoResult> selectMyCouponsList(MbrIssuCpn mbrIssuCpn)  {
		return memberBenefitSelectRepository.selectMyCouponsList(mbrIssuCpn);
	}

	/**
	 * 회원 P포인트 목록 건수 조회.
	 *
	 * @param mbrWebpntHist
	 *            [설명]
	 * @return Long [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public long selectMemberPurpleCoinListCount(MbrWebpntHist mbrWebpntHist)  {
		return memberBenefitSelectRepository.selectMemberPurpleCoinListCount(mbrWebpntHist);
	}

	/**
	 * 회원 P포인트 목록 조회.
	 *
	 * @param mbrWebpntHist
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public List<MemberBoResult> selectMemberPurpleCoinList(MbrWebpntHist mbrWebpntHist)  {
		return memberBenefitSelectRepository.selectMemberPurpleCoinList(mbrWebpntHist);
	}

	/**
	 * 회원 P포인트 엑셀 조회.
	 */
	public List<Map<String, Object>> selectMemberPurpleCoinExcel(MbrWebpntHist mbrWebpntHist) {
		return memberBenefitSelectRepository.selectMemberPurpleCoinExcel(mbrWebpntHist);
	}

	/**
	 * 회원 P포인트 지급 이력 조회
	 */
	public int selectWebPntResnCdCount(MbrWebpntHist mbrWebpntHist) {
		return memberBenefitSelectRepository.selectWebPntResnCdCount(mbrWebpntHist);
	}

    /**
     * 신규 가입 감사 쿠폰 조회
     *
     * @param mbr
     * @return List<MypageCpnFoResult>
     */
    public List<MypageCpnFoResult> selectIssuedNewJoinCoupon(Mbr mbr)  {
    	return memberBenefitSelectRepository.selectIssuedNewJoinCoupon(mbr);
    }

    /**
     * 가상계좌 결재대기 포인트
     *
     * @param  mbrNo
     * @return unityPntUseSumAmt
     */
    public int selectOrdPntUseAmtSum(String mbrNo)  {
		return memberBenefitSelectRepository.selectOrdPntUseAmtSum(mbrNo);
	}
}
