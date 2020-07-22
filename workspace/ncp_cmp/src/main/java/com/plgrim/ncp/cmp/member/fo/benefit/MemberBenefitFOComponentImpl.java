package com.plgrim.ncp.cmp.member.fo.benefit;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.inf.InfMbrGrdInfoRecptn;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefDetail;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefPymntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRecomendrRecomendeCnnc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGftExchg;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.SysInfoEnum;
import com.plgrim.ncp.base.enums.WebPointEnum;
import com.plgrim.ncp.biz.member.data.MbrBnefDetailExtend;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MemberResultDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.result.MemberBenefitApiResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.result.MemberReserveResult;
import com.plgrim.ncp.biz.member.result.MypageCpnFoResult;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitApiCommandService;
import com.plgrim.ncp.biz.member.service.MemberBenefitApiSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitCommandService;
import com.plgrim.ncp.biz.member.service.MemberBenefitSelectService;
import com.plgrim.ncp.biz.member.service.MemberInterfaceService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.service.EventService;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.cmp.promotion.fo.PromotionCouponFOComponent;
import com.plgrim.ncp.cmp.promotion.fo.PromotionEventFOComponent;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.interfaces.member.data.MemberMileageInfoSDO;
import com.plgrim.ncp.interfaces.member.data.MembershipCardSDO;

import lombok.extern.slf4j.Slf4j;

@Transactional(value = "transactionManager")
@Component
@Slf4j
public class MemberBenefitFOComponentImpl extends AbstractComponent implements MemberBenefitFOComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	MemberBenefitCommandService memberBenefitCommandService;

	@Autowired
	MemberBenefitSelectService memberBenefitSelectService;

	@Autowired
	MemberBaseSelectService memberBaseSelectService;

	@Autowired
	MemberPersonalInfoCommandService memberPersonalInfoCommandService;

	@Autowired
	PromotionCouponFOComponent promotionCouponFOComponent;

	@Autowired
	MemberBenefitApiSelectService memberBenefitApiSelectService;

	@Autowired
	MemberBenefitApiCommandService memberBenefitApiCommandService;

	@Autowired
    MemberInterfaceService memberInterfaceService;

	@Autowired
	PromotionEventFOComponent promotionEventFOComponent;

	@Autowired
	PromotionService promotionService;

	@Autowired
	EventService eventService;

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	@Override
	public void addBenefitRecommandEvent(PromotionBoDTO promotionBoDTO, MemberFoDTO memberFoDTO, SystemPK pk) {
		try{
			String recommandMbrNo = memberBaseSelectService.selectRecommandMbrNo(memberFoDTO);
			MbrBnefPymntHist mbrBnefPymntHist = new MbrBnefPymntHist();

			// 추천인 혜택 지급 이력 정보
			mbrBnefPymntHist.setMbrNo(promotionBoDTO.getMbrIssuCpn().getMbrNo());
			mbrBnefPymntHist.setBnefPymntTpCd("CPN");
			//mbrBnefPymntHist.setBnefSectCd("MBR_JOIN_RECOMENDR_BNEF");
			mbrBnefPymntHist.setPrmNo(promotionBoDTO.getMbrIssuCpn().getPrmNo());
			mbrBnefPymntHist.setRecomendeMbrNo(recommandMbrNo);

			promotionCouponFOComponent.addCouponDownMemberIssue(promotionBoDTO); // 추천인 쿠폰 지급
  			memberBaseSelectService.insertMbrBnefPymntHist(mbrBnefPymntHist); // 추천인 혜택 지급 이력 저장

			// 추천인 피추천인 연결테이블 저장
			MbrRecomendrRecomendeCnnc mbrRecomendrRecomendeCnnc = new MbrRecomendrRecomendeCnnc();
			mbrRecomendrRecomendeCnnc.setRecomendrMbrNo(promotionBoDTO.getMbrIssuCpn().getMbrNo());
			mbrRecomendrRecomendeCnnc.setRecomendeMbrNo(recommandMbrNo);
			mbrRecomendrRecomendeCnnc.setRegtrId(promotionBoDTO.getMbrIssuCpn().getMbrNo());
			mbrRecomendrRecomendeCnnc.setUdterId(promotionBoDTO.getMbrIssuCpn().getMbrNo());

			memberBaseSelectService.insertMbrRecomendrRecomendeCnnc(mbrRecomendrRecomendeCnnc);

			mbrBnefPymntHist = new MbrBnefPymntHist();

			mbrBnefPymntHist.setMbrNo(recommandMbrNo);
			//mbrBnefPymntHist.setBnefSectCd("MBR_JOIN_RECOMENDE_BNEF");
            mbrBnefPymntHist.setMallId(pk.getMall());
			if(memberBenefitSelectService.selectMbrBnefPymntHistCount(mbrBnefPymntHist) < 40){
				BigDecimal pntAmt = new BigDecimal(500);

				// 웹포인트 이력 정보
				MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
				mbrWebpntHist.setMbrNo(recommandMbrNo);
				mbrWebpntHist.setWebpntTpCd("WEBPNT");
				mbrWebpntHist.setWebpntResnCd("EVT");
				mbrWebpntHist.setWebpntDetailResnCd("MBR_JOIN_RECOMENDE_BNEF");
				mbrWebpntHist.setWebpntAmt(pntAmt);
				mbrWebpntHist.setWebpntStatCd("ACCML_DCSN");
				mbrWebpntHist.setUseBegDt(new Date());
				mbrWebpntHist.setUseEndDt(DateService.minusDate(DateService.plusYears(new Date(), 1), 1));	// 유효기간 1년
				mbrWebpntHist.setResnDscr("회원가입 추천 보상");

				// 혜택 지급 이력 정보
				mbrBnefPymntHist.setBnefPymntTpCd("WEB_PNT");
				mbrBnefPymntHist.setPntPymntAmt(pntAmt);
				mbrBnefPymntHist.setRecomendrMbrNo(promotionBoDTO.getMbrIssuCpn().getMbrNo());

				memberBenefitCommandService.insertWebPoint(mbrWebpntHist); // 피추천인 웹포인트 지급
				memberBaseSelectService.insertMbrBnefPymntHist(mbrBnefPymntHist); // 피추천인 혜택 지급 이력 저장
			}

		} catch(Exception e) {

		}
	}

	/**
	 * 회원 혜택 공통 API
	 */
	@Override
	public MemberBenefitApiResult callMemberBenefit(SystemPK systemPK, MemberBenefitEnum.BnefSectCd bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList) {
		MemberBenefitApiResult apiResult = new MemberBenefitApiResult();

		try {
			List<MemberBenefitResult> resultList = new ArrayList<MemberBenefitResult>();

			// 1. 로그인 여부 조회
			MemberBenefitResult result = memberBenefitApiSelectService.chkMemberBenefitLogin(systemPK);
			if (result == null || result.getMbr() == null) {
				apiResult.setResultCd(CommonResponseCode.PM_00012_회원_혜택_API_성공.toMessage());
				apiResult.setResultMsg(CommonResponseCode.PM_00014_비로그인_회원.toMessage() + " : " + bnefSectCd.toString());
				return apiResult;
			}

			// 2. 회원 혜택 조회
			List<MbrBnefDetailExtend> mbrBnefDetailList = memberBenefitApiSelectService.getMemberBenefitList(bnefSectCd);	
			//해당몰의 혜택만 적용
			mbrBnefDetailList = mbrBnefDetailList.stream()													
													.filter(t -> t.getMallId().equals(systemPK.getMall()))														
													.collect(Collectors.toList());
			if (mbrBnefDetailList == null || mbrBnefDetailList.size() == 0) {				
				apiResult.setResultCd(CommonResponseCode.PM_00012_회원_혜택_API_성공.toMessage());
				apiResult.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, result.getMbr(), null, CommonResponseCode.PM_00013_회원_혜택_없음.toMessage()) + " : " + bnefSectCd.toString());
				return apiResult;
			}
			
			// 3. 회원 혜택 대상여부 validation 체크
			List<MemberBenefitResult> validResultList = memberBenefitApiSelectService.validateMemberBenefit(systemPK, mbrBnefDetailList, result.getMbr(), bnefSectCd.toString(), null);

			// 4. 회원 혜택 지급 처리
			List<MemberBenefitResult> payResultList = memberBenefitApiCommandService.payMemberBenefit(validResultList, systemPK, result.getMbr(), bnefSectCd.toString(), issuedOnOffCouponMapList);

			// 5. 회원 혜택 지급 이력 저장
			resultList = memberBenefitApiCommandService.setMemberBenefitHistory(payResultList, systemPK, result.getMbr());

			// 6. 회원 혜택 API 결과 처리
			apiResult.setResultCd(CommonResponseCode.PM_00012_회원_혜택_API_성공.toMessage());
			apiResult.setResultMsg(CommonResponseCode.PM_00012_회원_혜택_API_성공.toMessage() + " : " + bnefSectCd.toString());
			apiResult.setMemberBenefitResultList(resultList);
			for(int i= 0; i < resultList.size(); i++) {
				MemberBenefitResult memberBenefitResult = resultList.get(i);
				if(memberBenefitResult.getResultCd().equals(CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage())) {
					apiResult.setResultCd(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage());
					apiResult.setResultMsg(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage() + " : " + bnefSectCd.toString());
					break;
				}
			}
		}
		catch (Exception ex) {
			// 7. 회원 혜택 API 실패한 경우 처리
			log.warn("", ex);
			apiResult.setResultCd(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage());
			apiResult.setResultMsg(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage() + " : " + bnefSectCd.toString() + ", ex : " + ex.getMessage());
		}

		return apiResult;
	}

	/**
	 * 회원 혜택 공통 API
	 */
	@Override
	public MemberBenefitApiResult callMemberBenefit(SystemPK systemPK, MemberBenefitEnum.BnefSectCd bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList, Mbr mbr, MbrGrd mbrGrd, String mallId) {
		MemberBenefitApiResult apiResult = new MemberBenefitApiResult();

		try {
			List<MemberBenefitResult> resultList = new ArrayList<MemberBenefitResult>();

			// 1. 회원 정보 세팅
			MemberBenefitResult result = new MemberBenefitResult();
			result.setMbr(mbr);

			// 2. 회원 혜택 조회
			List<MbrBnefDetailExtend> mbrBnefDetailList = memberBenefitApiSelectService.getMemberBenefitList(bnefSectCd);
			mbrBnefDetailList = mbrBnefDetailList
									.stream()
									.filter(t->t.getMallId().equals(mallId))
									.collect(Collectors.toList());

			if (mbrBnefDetailList == null || mbrBnefDetailList.size() == 0) {
				apiResult.setResultCd(CommonResponseCode.PM_00012_회원_혜택_API_성공.toMessage());
				apiResult.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, result.getMbr(), null, CommonResponseCode.PM_00013_회원_혜택_없음.toMessage()) + " : " + bnefSectCd.toString());
				return apiResult;
			}

			// 3. 회원 혜택 대상여부 validation 체크
			List<MemberBenefitResult> validResultList = memberBenefitApiSelectService.validateMemberBenefit(systemPK, mbrBnefDetailList, result.getMbr(), bnefSectCd.toString(), mbrGrd);

			// 4. 회원 혜택 지급 처리
			List<MemberBenefitResult> payResultList = memberBenefitApiCommandService.payMemberBenefit(validResultList, systemPK, result.getMbr(), bnefSectCd.toString(), issuedOnOffCouponMapList);

			// 5. 회원 혜택 지급 이력 저장
			resultList = memberBenefitApiCommandService.setMemberBenefitHistory(payResultList, systemPK, result.getMbr());

			// 6. 회원 혜택 API 결과 처리
			apiResult.setResultCd(CommonResponseCode.PM_00012_회원_혜택_API_성공.toMessage());
			apiResult.setResultMsg(CommonResponseCode.PM_00012_회원_혜택_API_성공.toMessage() + " : " + bnefSectCd.toString());
			apiResult.setMemberBenefitResultList(resultList);
			for(int i= 0; i < resultList.size(); i++) {
				MemberBenefitResult memberBenefitResult = resultList.get(i);
				if(memberBenefitResult.getResultCd().equals(CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage())) {
					apiResult.setResultCd(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage());
					apiResult.setResultMsg(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage() + " : " + bnefSectCd.toString());
					break;
				}
			}
		}
		catch (Exception ex) {
			// 7. 회원 혜택 API 실패한 경우 처리
			log.warn("", ex);
			apiResult.setResultCd(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage());
			apiResult.setResultMsg(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage() + " : " + bnefSectCd.toString() + ", ex : " + ex.getMessage());
		}

		return apiResult;
	}

	/**
	 * 회원 혜택 공통 API
	 */
	@Override
	public MemberBenefitApiResult callMemberBenefit(SystemPK systemPK, MemberBenefitEnum.BnefSectCd bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList, Mbr mbr, MbrGrd mbrGrd, String mallId, Date mbrGrdModDt) {
		MemberBenefitApiResult apiResult = new MemberBenefitApiResult();

		try {
			List<MemberBenefitResult> resultList = new ArrayList<MemberBenefitResult>();

			// 1. 회원 정보 세팅
			MemberBenefitResult result = new MemberBenefitResult();
			result.setMbr(mbr);

			// 2. 회원 혜택 조회
			List<MbrBnefDetailExtend> mbrBnefDetailList = memberBenefitApiSelectService.getMemberBenefitList(bnefSectCd);
			mbrBnefDetailList = mbrBnefDetailList
									.stream()
									.filter(t->t.getMallId().equals(mallId))
									.collect(Collectors.toList());

			if (mbrBnefDetailList == null || mbrBnefDetailList.size() == 0) {
				apiResult.setResultCd(CommonResponseCode.PM_00012_회원_혜택_API_성공.toMessage());
				apiResult.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, result.getMbr(), null, CommonResponseCode.PM_00013_회원_혜택_없음.toMessage()) + " : " + bnefSectCd.toString());
				return apiResult;
			}

			// 3. 회원 혜택 대상여부 validation 체크
			List<MemberBenefitResult> validResultList = memberBenefitApiSelectService.validateMemberBenefit(systemPK, mbrBnefDetailList, result.getMbr(), bnefSectCd.toString(), mbrGrd);

			// 4. 회원 혜택 지급 처리
			List<MemberBenefitResult> payResultList = memberBenefitApiCommandService.payMemberBenefit(validResultList, systemPK, result.getMbr(), bnefSectCd.toString(), issuedOnOffCouponMapList, mbrGrdModDt);

			// 5. 회원 혜택 지급 이력 저장
			resultList = memberBenefitApiCommandService.setMemberBenefitHistory(payResultList, systemPK, result.getMbr());

			// 6. 회원 혜택 API 결과 처리
			apiResult.setResultCd(CommonResponseCode.PM_00012_회원_혜택_API_성공.toMessage());
			apiResult.setResultMsg(CommonResponseCode.PM_00012_회원_혜택_API_성공.toMessage() + " : " + bnefSectCd.toString());
			apiResult.setMemberBenefitResultList(resultList);
			for(int i= 0; i < resultList.size(); i++) {
				MemberBenefitResult memberBenefitResult = resultList.get(i);
				if(memberBenefitResult.getResultCd().equals(CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage())) {
					apiResult.setResultCd(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage());
					apiResult.setResultMsg(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage() + " : " + bnefSectCd.toString());
					break;
				}
			}
		}
		catch (Exception ex) {
			// 7. 회원 혜택 API 실패한 경우 처리
			log.warn("", ex);
			apiResult.setResultCd(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage());
			apiResult.setResultMsg(CommonResponseCode.PM_40007_회원_혜택_API_오류.toMessage() + " : " + bnefSectCd.toString() + ", ex : " + ex.getMessage());
		}

		return apiResult;
	}

	/**
	 * 회원 웹 적립금 건수 Count
	 */
	@Override
	public long selectMemberWebPointListCnt(MbrWebpntHist mbrWebpntHist) {
		return memberBenefitApiSelectService.selectMemberWebPointListCnt(mbrWebpntHist);
	}

	@Override
    public void insertWebPointByLogin(Mbr mbr) {
		try{
			MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
			mbrWebpntHist.setMbrNo(mbr.getMbrNo());
			mbrWebpntHist.setWebpntResnCd(WebPointEnum.WebPntResnCd.BNEF_PYMNT.toString());
			mbrWebpntHist.setWebpntDetailResnCd(WebPointEnum.WebPntEvtDtlResnCd.LOGIN.toString());
			long loginCnt = this.selectMemberWebPointListCnt(mbrWebpntHist);
			if(loginCnt < 1){
				double pnt = 5;
				BigDecimal pntAmt = new BigDecimal(String.valueOf(pnt));
				Integer validDayCnt = 730;	// 2년

				// (ID - 확인필요)
				// 코드를 어느걸로 할지 필요.

				mbrWebpntHist.setMbrNo(mbr.getMbrNo());
				mbrWebpntHist.setWebpntResnCd(WebPointEnum.WebPntResnCd.BNEF_PYMNT.toString());
				mbrWebpntHist.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_DCSN.toString());
				mbrWebpntHist.setWebpntTpCd(MemberBenefitEnum.BnefPymntTpCd.WEBPNT.toString());
				mbrWebpntHist.setWebpntDetailResnCd(WebPointEnum.WebPntEvtDtlResnCd.LOGIN.toString());
				mbrWebpntHist.setWebpntAmt(pntAmt);
				mbrWebpntHist.setUseBegDt(new Date());
				mbrWebpntHist.setUseEndDt(DateService.minusDate(DateService.plusDate(new Date(), validDayCnt.intValue()), 1));	// 웹포인트 유효 일수

				memberBenefitCommandService.insertWebPoint(mbrWebpntHist);
			}
		}
		catch(Exception e) {
			log.warn("로그인 웹포인트 증정 실패");
		}
	}

	/**
	 * 회원 혜택 단건 조회
	 */
	@Override
	public MbrBnefDetail selectMemberBenefitInfo(MbrBnefDetail mbrBnefDetail) {
		return memberBenefitApiSelectService.selectMemberBenefitInfo(mbrBnefDetail);
	}

	/**
	 * My Coupon 조회
	 */
	@Override
	public Page<MypageCpnFoResult> getMyCouponList(SystemPK systemPK, MypageFoDTO dto , PageParam pageParam)  {
		dto.setLang(systemPK.getLang());
		dto.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
		return memberBenefitSelectService.selectMyCouponList(dto , pageParam);
	}

	/**
	 * My Coupon 조회
	 */
	@Override
	public Page<MypageCpnFoResult> getMyCouponListWithMall(SystemPK systemPK, MypageFoDTO dto , PageParam pageParam)  {
		dto.setLang(systemPK.getLang());
		dto.setPlgrimshopMallIdList(Arrays.asList(systemPK.getMall()));
		return memberBenefitSelectService.selectMyCouponList(dto , pageParam);
	}

	/**
	 * My Coupon 이력조회
	 */
	@Override
	public Page<MypageCpnFoResult> getMyCouponHistList(SystemPK systemPK, MypageFoDTO dto , PageParam pageParam)  {
		dto.setLang(systemPK.getLang());
		dto.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
		return memberBenefitSelectService.selectMyCouponHistList(dto , pageParam);
	}

	/**
	 * My Coupon 이력조회
	 */
	@Override
	public Page<MypageCpnFoResult> getMyCouponHistListWithMall(SystemPK systemPK, MypageFoDTO dto , PageParam pageParam)  {
		dto.setLang(systemPK.getLang());
		dto.setPlgrimshopMallIdList(Arrays.asList(systemPK.getMall()));
		return memberBenefitSelectService.selectMyCouponHistList(dto , pageParam);
	}

	/**
	 * 멤버쉽 포인트 조회.
	 */
	@Override
	public MemberResultDTO getMembershipPointList(SystemPK systemPK, MypageFoDTO myPageFoDTO)  {

		try{
			log.info(CommonResponseCode.MB_00047_통합회원_멤버십_포인트_조회_시도_멤버십_포인트_리스트.toMessage());

			// step 1. 페이지 인덱스 및 변수 설정
			MemberResultDTO resultDTO = new MemberResultDTO();

			// step 2. 회원의 ERP 정보 조회
			String erpNo = memberBaseSelectService.selectMemberErpNo(myPageFoDTO.getMbr().getMbrNo());

			// step 3. 포인트 잔액 조회
			MemberReserveResult pointInfo = memberBenefitSelectService.getMemberPointMilageRemainAmount(erpNo);
			// 멤버쉽포인트
			resultDTO.setReserveAmount(pointInfo.getReserve());

			// 임시삭감 포인트
			long tempDelAmount = memberBenefitSelectService.getMemberTempDelReserveRemainAmount(systemPK, erpNo);
//			resultDTO.setTempDelAmount(tempDelAmount);

			// 가용포인트 = 전체포인트 - 임시삭감포인트 By Gilbert
			resultDTO.setUsableAmount(resultDTO.getReserveAmount() - tempDelAmount);

			// step 4. 멤버쉽 포인트 내역 조회
			log.error("getMembershipPointHistory : start");
			List<Map<String, String>> membershipPointList = memberBenefitSelectService.getMembershipPointHistory(systemPK, erpNo , myPageFoDTO);
			resultDTO.setMembershipPointList(membershipPointList);
			log.error("getMembershipPointHistory : membershipPointList = " + membershipPointList.toString());

			log.info(CommonResponseCode.MB_00048_통합회원_멤버십_포인트_조회_성공_멤버십_포인트_리스트.toMessage());
			return resultDTO;
		}catch(Exception e){
			log.info(CommonResponseCode.MB_40019_통합회원_멤버십_포인트_조회_실패_멤버십_포인트_리스트.toMessage()+" : "+e);
			throw new RuntimeException(e);
		}

	}

	/**
	 * 마이페이지 - P포인트 내역 조회
	 */
	@Override
	public Page<MemberFoResult> getMyPurpleCoinList(SystemPK systemPK, MbrWebpntHist mbrWebpntHist, String loginId , PageParam pageParam) {
		mbrWebpntHist.setLang(systemPK.getLang());
		//long listCount = webPointService.selectMemberPurpleCoinListCount(mbrWebpntHist);
		Page<MemberFoResult> lists = memberBenefitSelectService.selectMyPurpleCoinList(mbrWebpntHist, pageParam);
		return lists;
	}

	/**
	 * 마이페이지 - P포인트 내역 조회
	 */
	@Override
	public Page<MemberFoResult> getMyPurpleCoinListForMall(SystemPK systemPK, MbrWebpntHist mbrWebpntHist, String loginId , PageParam pageParam) {
		mbrWebpntHist.setLang(systemPK.getLang());
		mbrWebpntHist.setMallId(systemPK.getMall());
		return memberBenefitSelectService.selectMyPurpleCoinListForMall(mbrWebpntHist, pageParam);
	}

	/**
	 * My Coupon 적용상품 조회
	 */
	@Override
	public Page<MypageCpnFoResult> getMyCouponGoodList(SystemPK systemPK, MypageFoDTO dto , PageParam pageParam)  {
		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());
		return memberBenefitSelectService.selectMyCouponGoodList(dto , pageParam);
	}

	/**
	 * My Coupon 조회
	 */
	@Override
	public List<MypageCpnFoResult> getMyCouponComboList(SystemPK systemPK, MypageFoDTO dto)  {
		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());
		return memberBenefitSelectService.selectMyCouponComboList(dto);
	}


	/**
	 *  통합몰에서 노출되어야 할 몰 ID리스트 수집
	 */
	private List<String> getPlgrimshopMallIdList() {

		List<String> plgrimshopMallIdList = new ArrayList<String>();
		for (SysInfoEnum.PlgrimshopMallEnum mallEnum : SysInfoEnum.PlgrimshopMallEnum.values()){
			plgrimshopMallIdList.add(mallEnum.toString());
		}
		return plgrimshopMallIdList;
	}



	@Override
	public int selectMyCampaginCouponCount(MbrIssuCpn mbrIssuCpn)  {
		return memberBenefitSelectService.selectMyCampaginCouponCount(mbrIssuCpn);
	}

	@Override
	public int getMyCouponCnt(SystemPK systemPK, MypageFoDTO dto)  {
		dto.setLang(systemPK.getLang());
		dto.setDevice(systemPK.getDevice());
		dto.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
		return memberBenefitSelectService.selectMyCouponCnt(dto);
	}

	@Override
	public int getMyCouponCntWithMall(SystemPK systemPK, MypageFoDTO dto)  {
		dto.setLang(systemPK.getLang());
		dto.setDevice(systemPK.getDevice());
		dto.setPlgrimshopMallIdList(Arrays.asList(systemPK.getMall()));
		return memberBenefitSelectService.selectMyCouponCnt(dto);
	}

	/**
	 * 멤버쉽 포인트/마일리지 조회updateAuthentication
	 */
	@Override
	public MemberReserveResult getMemberPointMilageRemainAmount(String erpNo)  {
		try{
			log.info(CommonResponseCode.MB_00055_통합회원_멤버십_포인트_조회_시도_포인트_마일리지_잔액.toMessage());
			MemberReserveResult mbrPointMileage = memberBenefitSelectService.getMemberPointMilageRemainAmount(erpNo);
			log.info(CommonResponseCode.MB_00056_통합회원_멤버십_포인트_조회_성공_포인트_마일리지_잔액.toMessage());
			return mbrPointMileage;
		}catch(Exception e){
			log.info(CommonResponseCode.MB_40023_통합회원_멤버십_포인트_조회_실패_포인트_마일리지_잔액.toMessage()+" : "+e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 혜택 지급 이력 Count
	 * @param mbrBnefPymntHist
	 * @return
	 * @
	 */
	@Override
	public int selectMbrBnefPymntHistCount (MbrBnefPymntHist mbrBnefPymntHist)  {
		return memberBenefitSelectService.selectMbrBnefPymntHistCount(mbrBnefPymntHist);
	}

	/** 회원 보유쿠폰 카운트 */
	@Override
	public long selectMyCouponsCount(MbrIssuCpn mbrIssuCpn)  {
		return memberBenefitSelectService.selectMyCouponsCount(mbrIssuCpn);
	}

	@Override
	public PrmCpnGftExchg selectEscPrmCpnGftExchg(PrmCpnGftExchg prmCpnGftExchg)  {
		return memberBenefitSelectService.selectEscPrmCpnGftExchg(prmCpnGftExchg);
	}

	/**
	 * P포인트 현황 조회
	 */
	@Override
	public MbrWebpntHistExtend selectWebPointInfo(MbrWebpntHist mbrWebpntHist)  {
		return memberBenefitSelectService.selectWebPointInfo(mbrWebpntHist);
	}

	/**
	 * ERP로 CID, HISTORY_YN, BRAND 값을 보내 마일리지 정보 조회
	 */
	@Override
    public MemberMileageInfoSDO selectMileageInfo(SystemPK systemPK, MypageFoDTO mypageFoDTO){
		MemberMileageInfoSDO memberMileageInfoSDO = null;
    	try {
    		memberMileageInfoSDO = memberInterfaceService.getMemberMileage(systemPK, mypageFoDTO);
		} catch (Exception e) {
			log.warn(">> getMemberMileage ", e);
		}

    	return memberMileageInfoSDO;
    }

	/**
	 * 멤버십 카드 등록
	 */
	@Override
    public MembershipCardSDO addMembershipCard(SystemPK systemPK, MypageFoDTO mypageFoDTO){

		MembershipCardSDO membershipCardSDO = new MembershipCardSDO();

		try {
    		membershipCardSDO.setCid(mypageFoDTO.getMbr().getErpCstmrNo());
    		membershipCardSDO.setMembershipCardNo(mypageFoDTO.getMembershipCardNo());

    		membershipCardSDO = memberInterfaceService.addMembershipCard(systemPK, membershipCardSDO);
		} catch (Exception e) {
			log.warn(">> getMemberMileage ", e);
		}

    	return membershipCardSDO;
    }

	/**
	 * 멤버십 카드 등록
	 */
	@Override
    public MembershipCardSDO addMembershipCardOnCertify(SystemPK systemPK, MypageFoDTO mypageFoDTO){

		MembershipCardSDO membershipCardSDO = new MembershipCardSDO();

		try {
    		membershipCardSDO.setCid(mypageFoDTO.getMbr().getErpCstmrNo());
    		membershipCardSDO.setMembershipCardNo(mypageFoDTO.getMembershipCardNo());
    		membershipCardSDO.setCertifyNo(mypageFoDTO.getCertifyNo());

    		membershipCardSDO = memberInterfaceService.addMembershipCard(systemPK, membershipCardSDO);
		} catch (Exception e) {
			log.warn(">> getMemberMileage ", e);
		}

    	return membershipCardSDO;
    }

	/**
     * 가상계좌 결재대기 포인트
     * @param  mbrNo
     * @return unityPntUseSumAmt
     */
    public int selectOrdPntUseAmtSum(String mbrNo)  {
		return memberBenefitSelectService.selectOrdPntUseAmtSum(mbrNo);
	}

    
	@Override
    public void memberGradeIssuCpn( InfMbrGrdInfoRecptn infMbrGrdInfoRecptn){

		List<InfMbrGrdInfoRecptn> listGrade = memberBaseSelectService.selectMemberGradeInfoCp(infMbrGrdInfoRecptn);
		for( InfMbrGrdInfoRecptn v : listGrade ) {
			
			/*
			 * 단위건별로 실행
			 */
			try {
				// 등급 변경 처리
				 processMemberGrade(v);
			}
			catch(Exception e) {
	 
			}
		}
		
		
    }

    public void processMemberGrade(InfMbrGrdInfoRecptn infMbrGrdInfoRecptn) throws Exception {
 
		// 회원 등급 변경 처리
        String erpCstmrNo = "";
        boolean result = true;
        
        /* ============================================================================== */
        /* =   01. 등급 변경 검증 및 실행                                               = */
        /* =   - ERP 번호로 조회하여 대상이 있고                                        = */
        /* =   - 회원 상태가 ACT, BLCLST_A, BLCLST_B이면 등급 변경 처리한다.            = */
        /* = -------------------------------------------------------------------------- = */
        
        erpCstmrNo = infMbrGrdInfoRecptn.getErpCstmrNo();
        
        List<Mbr> mbrList = memberBaseSelectService.selectMemberByErpCstmrNo(erpCstmrNo);
        
        if(mbrList != null && !mbrList.isEmpty()) {
        	for(Mbr mbr : mbrList) {
				if(MemberEnum.MemberStatCd.ACT.toString().equals(mbr.getMbrStatCd())
						|| MemberEnum.MemberStatCd.BLCLST_A.toString().equals(mbr.getMbrStatCd())
						|| MemberEnum.MemberStatCd.BLCLST_B.toString().equals(mbr.getMbrStatCd())
						|| MemberEnum.MemberStatCd.DRMNCY.toString().equals(mbr.getMbrStatCd())
						) {
					String brandId = infMbrGrdInfoRecptn.getBrndId();
					String mbrGrdCd = infMbrGrdInfoRecptn.getMbrGrdCd();
			        String prmTpCd = infMbrGrdInfoRecptn.getPrmTpCd();
			        String erpCpnId = infMbrGrdInfoRecptn.getErpCpnId();			        
			        String mallId = "";
					try {
						if("X".equals(brandId)) {
							mallId = "DXM";
						}else if("M".equals(brandId)) {
							mallId = "MBM";
						}else if("A".equals(brandId)) {
							mallId = "SAM";
						}else {
							throw new Exception("Not acceptable brand");
						}
						
						// 온라인 회원등급 등록
						MbrGrd mbrGrd = new MbrGrd();
						mbrGrd.setMallId(mallId);
						mbrGrd.setMbrNo(mbr.getMbrNo());
						mbrGrd.setOnlneGrdCd(mallId + "_" + mbrGrdCd);
						
				        /* ============================================================================== */
				        /* =   온라인 회원 등급 변경 혜택 처리                                          = */
						/* =   ERP에서 받은 쿠폰이 있는 경우만 혜택 처리                                = */
						/* =   ERP에서 받은 쿠폰이 없으면 포인트, 쿠폰도 주지 않음.                     = */
						/* = -------------------------------------------------------------------------- = */
						if(prmTpCd != null && !"".equals(prmTpCd)
							&& erpCpnId != null && !"".equals(erpCpnId)
							){
							// ERP에서 받은 회원 등급 승급시 발행된 온/오프라인 쿠폰 등록
							List<Map<String, String>> issuedCouponMapList = new ArrayList<Map<String, String>>();
							
							String[] prmTpCd_arr = StringUtils.split(prmTpCd, "|");
							String[] erpCpnId_arr = StringUtils.split(erpCpnId, "|");
							
							for(int k = 0 ; k < prmTpCd_arr.length ; k++){
								try{
									String s1 = prmTpCd_arr[k];
									String s2 = erpCpnId_arr[k];
																		
									Map<String, String> onOffCouponMap = new HashMap<String, String>();
									onOffCouponMap.put("ISSUED_COUPON_TYPE", s1);
									onOffCouponMap.put("ISSUED_COUPON_NO", s2);
									onOffCouponMap.put("MALL_ID", mallId);
									
									onOffCouponMap.put("CREATE_DT", DateService.parseString(new Date(), "yyyy-MM-dd HH:mm:ss") );
								    onOffCouponMap.put("USE_START_DT",DateService.parseString(new Date(), "yyyyMMdd") );
									
								    String USE_END_DT = "";								    
									if(mallId.equals("MBM") || mallId.equals("SAM")){				// MLB, SA는 분기 마지막날짜까지 유효								    
										USE_END_DT = DateService.getLastQuaterDayOfCurrent();								        							        
									}else{															// DX는 ERP 발급일로부터 180일											
										USE_END_DT = DateService.parseString(DateService.plusDate(infMbrGrdInfoRecptn.getMbrGrdModDt(), 180), "yyyyMMdd");
									}
									onOffCouponMap.put("USE_END_DT", USE_END_DT);
									//onOffCouponMap.put("USE_YN", "N");
									//todo online 전용 쿠폰 목록 추가 필요
									issuedCouponMapList.add(onOffCouponMap);
								}catch(Exception e){
									log.error("ISSUED_COUPON_TYPE과 ISSUED_COUPON_NO 짝이 맞지 않음");
								}
							}
		 
							// 회원 혜택을 사용하기 위한 임의 세팅.
							SystemPK pk = new SystemPK();
							pk.setMall(mallId);
							pk.setLang("KOR");
							pk.setDevice("PC");
							
							// 혜택API 온라인 회원 등급 변경 - 온라인 회원 등급 변경
							MemberBenefitApiResult apiResult =  callMemberBenefit(pk, MemberBenefitEnum.BnefSectCd.ONLNE_GRD_MOD, issuedCouponMapList, mbr, mbrGrd, mallId, infMbrGrdInfoRecptn.getMbrGrdModDt());
							
							log.info("MEMBER_BENEFIT_API : ResultCd({}),ResultMsg({})", apiResult.getResultCd(), apiResult.getResultMsg());

							if(apiResult != null && apiResult.getMemberBenefitResultList() != null) {
							    List<MemberBenefitResult> resultList = apiResult.getMemberBenefitResultList();

							    for(int i= 0; i < resultList.size(); i++) {
							        MemberBenefitResult memberBenefitResult = resultList.get(i);
							        log.info("MEMBER_BENEFIT_API_DETAIL : ResultCd({}),ResultMsg({})", memberBenefitResult.getResultCd(), memberBenefitResult.getResultMsg());
							    }
							}
						}
					}
					catch(Exception e) {
						StringWriter error = new StringWriter();
						e.printStackTrace(new PrintWriter(error));
						
						log.warn("> Failure message : {} : {}", this.getClass().getName(), error.toString());
						result = false;
					}
				}
				else {
					result = false;
				}
        	}
        }
        
        /* ============================================================================== */
	    /* =   02. 실행 결과에 따른 처리                                                = */
        /* = -------------------------------------------------------------------------- = */
        
    	if(result)
    	{
    		 memberBaseSelectService.deleteInfMbrGrdInfoRecptnCp( infMbrGrdInfoRecptn );
    	}
    }
	public  String getDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr = sdf.format(new Date());
		return dateStr;
	}
}
