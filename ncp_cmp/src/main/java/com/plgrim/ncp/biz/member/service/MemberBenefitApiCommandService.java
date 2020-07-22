package com.plgrim.ncp.biz.member.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.inf.InfMbrGrdInfoRecptn;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefAplTgt;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefPymntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefPymntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPntIntrlckHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.enums.MemberBenefitEnum.BnefDetailSectCd;
import com.plgrim.ncp.base.enums.MemberBenefitEnum.BnefPymntTpCd;
import com.plgrim.ncp.base.enums.MemberBenefitEnum.BnefTgtTpCd;
import com.plgrim.ncp.base.enums.MemberEnum.MemberTpCd;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.CouponIssueLimitReason;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.base.enums.WebPointEnum;
import com.plgrim.ncp.base.repository.mbr.MbrBnefPymntHistRepository;
import com.plgrim.ncp.biz.member.data.MbrBnefDetailExtend;
import com.plgrim.ncp.biz.member.data.MemberBenefitBoDTO;
import com.plgrim.ncp.biz.member.data.MemberBenefitDTO;
import com.plgrim.ncp.biz.member.repository.MemberBaseSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberBenefitApiCommandRepository;
import com.plgrim.ncp.biz.member.repository.MemberBenefitApiSelectRepository;
import com.plgrim.ncp.biz.member.result.MemberBenefitResult;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.repository.OnOffCouponRepository;
import com.plgrim.ncp.biz.promotion.repository.PromotionRepository;
import com.plgrim.ncp.biz.promotion.result.CouponPromotionResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;
import com.plgrim.ncp.biz.promotion.service.OnOffCouponService;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.cmp.promotion.fo.PromotionCouponFOComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.framework.utils.RemoteAddrUtil;
import com.plgrim.ncp.framework.utils.StringUtils;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원혜택API command service
 */
@Service
@Slf4j
public class MemberBenefitApiCommandService extends AbstractService {
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
	
	/** IF 공통. */
	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	
	@Autowired
	IdGenService idGenService;
	
	@Autowired
	private PromotionCouponFOComponent promotionCouponFOComponent;
	
	@Autowired
	private MemberBenefitCommandService memberBenefitCommandService;
	
	@Autowired
	private MemberBenefitApiSelectService memberBenefitApiSelectService;
	
	@Autowired
	private MemberBenefitApiSelectRepository memberBenefitApiSelectRepository;
	
	@Autowired
	private MbrBnefPymntHistRepository mbrBnefPymntHistRepository;
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
	private MemberBenefitApiCommandRepository memberBenefitApiCommandRepository;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private OnOffCouponService onOffCouponService;
	
	@Autowired
	private OnOffCouponRepository onOffCouponRepository;
	
	@Autowired
	private MemberBaseSelectRepository memberBaseSelectRepository;
	
	/**
	 * 회원 혜택 지급 처리
	 */
	public List<MemberBenefitResult> payMemberBenefit(List<MemberBenefitResult> validResultList, SystemPK systemPK, Mbr mbr, String bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList){
		List<MemberBenefitResult> resultList = new ArrayList<MemberBenefitResult>();

		for(int i= 0; i < validResultList.size(); i++) {
			MemberBenefitResult memberBenefitResult = validResultList.get(i);
			log.debug("==== MemberBenefitApiCommandService.payMemberBenefit 시작 ====" + memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd());
			try {
				//1. 회원혜택 지급 대상인 경우 지급 처리
				if(!memberBenefitResult.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())) {
					resultList.add(memberBenefitResult);
					continue;
				}				
								
				MemberBenefitResult result = new MemberBenefitResult();

				//1.1. 회원혜택별 지급로직 호출
				if(  memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd().equals(MemberBenefitEnum.BnefPymntTpCd.ONOFLNE_CPN.toString())) {	//오프라인 쿠폰
					//1.1.1. 쿠폰 지급처리 로직 호출
					result = validatePromotionCoupon(memberBenefitResult, systemPK, mbr, bnefSectCd, issuedOnOffCouponMapList);
					memberBenefitResult.setResultCd(result.getResultCd());
					memberBenefitResult.setResultMsg(result.getResultMsg());
					if(result.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())){
						payPromotionCoupon(memberBenefitResult, systemPK, mbr, bnefSectCd, issuedOnOffCouponMapList);
					}
				}else if(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd().equals(MemberBenefitEnum.BnefPymntTpCd.ONLNE_CPN.toString())) {	//온라인 쿠폰
					//1.1.1. 쿠폰 지급처리 로직 호출
					result = validatePromotionCoupon(memberBenefitResult, systemPK, mbr, bnefSectCd, null);
					memberBenefitResult.setResultCd(result.getResultCd());
					memberBenefitResult.setResultMsg(result.getResultMsg());
					if(result.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())){
						payPromotionCoupon(memberBenefitResult, systemPK, mbr, bnefSectCd, null);
					}
				} else if(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd().equals(MemberBenefitEnum.BnefPymntTpCd.WEBPNT.toString())) {	//P포인트
					//1.1.2. P포인트 지급처리 로직 호출
					result = validatePromotionWebPoint(memberBenefitResult, systemPK, mbr, bnefSectCd);
					memberBenefitResult.setResultCd(result.getResultCd());
					memberBenefitResult.setResultMsg(result.getResultMsg());
					if(result.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())){
						payPromotionWebPoint(memberBenefitResult, systemPK, mbr);
					}
				} else if(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd().equals(MemberBenefitEnum.BnefPymntTpCd.MBSH_PNT.toString())) {	//멤버쉽 포인트
					//1.1.3. 멤버쉽 포인트 지급처리 로직 호출
					result = validatePromotionMemberShipPoint(memberBenefitResult, systemPK, mbr, bnefSectCd);
					memberBenefitResult.setResultCd(result.getResultCd());
					memberBenefitResult.setResultMsg(result.getResultMsg());
					if(result.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())){
						payPromotionMemberShipPoint(memberBenefitResult, systemPK, mbr);
					}
				}
				
			} catch(Exception ex) {
				//2. 회원혜택 지급 실패한 경우 처리
				log.warn("", ex);
				memberBenefitResult.setResultCd(CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage());
				memberBenefitResult.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage()));
				memberBenefitResult.setResultException(ex);
			}
			resultList.add(memberBenefitResult);
			
			log.debug("==== MemberBenefitApiCommandService.payMemberBenefit 끝 ====" + memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd());
		}
		return resultList;
	}
	
	/**
	 * 회원 혜택 지급 처리
	 */
	public List<MemberBenefitResult> payMemberBenefit(List<MemberBenefitResult> validResultList, SystemPK systemPK, Mbr mbr, String bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList, Date mbrGrdModDt){
		List<MemberBenefitResult> resultList = new ArrayList<MemberBenefitResult>();

		for(int i= 0; i < validResultList.size(); i++) {
			MemberBenefitResult memberBenefitResult = validResultList.get(i);
			log.debug("==== MemberBenefitApiCommandService.payMemberBenefit 시작 ====" + memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd());
			try {
				//1. 회원혜택 지급 대상인 경우 지급 처리
				if(!memberBenefitResult.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())) {
					resultList.add(memberBenefitResult);
					continue;
				}				
								
				MemberBenefitResult result = new MemberBenefitResult();

				//1.1. 회원혜택별 지급로직 호출
				if(  memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd().equals(MemberBenefitEnum.BnefPymntTpCd.ONOFLNE_CPN.toString())) {	//오프라인 쿠폰
					//1.1.1. 쿠폰 지급처리 로직 호출
					result = validatePromotionCoupon(memberBenefitResult, systemPK, mbr, bnefSectCd, issuedOnOffCouponMapList);
					memberBenefitResult.setResultCd(result.getResultCd());
					memberBenefitResult.setResultMsg(result.getResultMsg());
					if(result.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())){
						payPromotionCoupon(memberBenefitResult, systemPK, mbr, bnefSectCd, issuedOnOffCouponMapList);
					}
				}else if(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd().equals(MemberBenefitEnum.BnefPymntTpCd.ONLNE_CPN.toString())) {	//온라인 쿠폰
					//1.1.1. 쿠폰 지급처리 로직 호출
					result = validatePromotionCoupon(memberBenefitResult, systemPK, mbr, bnefSectCd, null);
					memberBenefitResult.setResultCd(result.getResultCd());
					memberBenefitResult.setResultMsg(result.getResultMsg());
					if(result.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())){
						payPromotionCoupon(memberBenefitResult, systemPK, mbr, bnefSectCd, null, mbrGrdModDt);
					}
				} else if(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd().equals(MemberBenefitEnum.BnefPymntTpCd.WEBPNT.toString())) {	//P포인트
					//1.1.2. P포인트 지급처리 로직 호출
					result = validatePromotionWebPoint(memberBenefitResult, systemPK, mbr, bnefSectCd);
					memberBenefitResult.setResultCd(result.getResultCd());
					memberBenefitResult.setResultMsg(result.getResultMsg());
					if(result.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())){
						payPromotionWebPoint(memberBenefitResult, systemPK, mbr, mbrGrdModDt);
					}
				} else if(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd().equals(MemberBenefitEnum.BnefPymntTpCd.MBSH_PNT.toString())) {	//멤버쉽 포인트
					//1.1.3. 멤버쉽 포인트 지급처리 로직 호출
					result = validatePromotionMemberShipPoint(memberBenefitResult, systemPK, mbr, bnefSectCd);
					memberBenefitResult.setResultCd(result.getResultCd());
					memberBenefitResult.setResultMsg(result.getResultMsg());
					if(result.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())){
						payPromotionMemberShipPoint(memberBenefitResult, systemPK, mbr);
					}
				}
				
			} catch(Exception ex) {
				//2. 회원혜택 지급 실패한 경우 처리
				log.warn("", ex);
				memberBenefitResult.setResultCd(CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage());
				memberBenefitResult.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage()));
				memberBenefitResult.setResultException(ex);
			}
			resultList.add(memberBenefitResult);
			
			log.debug("==== MemberBenefitApiCommandService.payMemberBenefit 끝 ====" + memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd());
		}
		return resultList;
	}
	
	/**
	 * 회원 혜택 지급 이력 저장
	 */
	public List<MemberBenefitResult> setMemberBenefitHistory(List<MemberBenefitResult> payResultList, SystemPK systemPK, Mbr mbr){
		List<MemberBenefitResult> resultList = new ArrayList<MemberBenefitResult>();
		
		for(int i= 0; i < payResultList.size(); i++) {
			MemberBenefitResult memberBenefitResult = payResultList.get(i);
			//1. 회원혜택 API 처리 성공/실패의 경우 DB 이력 저장 
			if(memberBenefitResult.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage()) 
					|| memberBenefitResult.getResultCd().equals(CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage())) {
				try {
					//1.1. 회원 혜택 지급 이력 저장처리 로직 호출
					insertMemberBenefitHistory(memberBenefitResult, systemPK, mbr, null);
				} catch(Exception ex) {
					//2. 회원 혜택 지급 이력 저장 실패한 경우 처리
					log.warn("", ex);
					memberBenefitResult.setResultCd(CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage());
					memberBenefitResult.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage()));
					memberBenefitResult.setResultException(ex);
				}
			}
			resultList.add(memberBenefitResult);
		}
		return resultList;
	}
	
	/**
	 * 쿠폰 지급 처리
	 */
	public void payPromotionCoupon(MemberBenefitResult memberBenefitResult, SystemPK systemPK, Mbr mbr, String bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList) {
		try {
//			log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "START"));
			log.debug("==== MemberBenefitApiCommandService.payMemberBenefit ====");
			
			PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
			
			// * 기본 값 셋팅 (필수)
			promotionBoDTO.getMbrIssuCpn().setPrmNo(memberBenefitResult.getMbrBnefDetailExtend().getPrmNo());
			promotionBoDTO.getMbrIssuCpn().setMbrNo(mbr.getMbrNo());
			// * 적용 대상 값 셋팅 (필수)
			promotionBoDTO.setMallId(systemPK.getMall());         // 몰ID			
			promotionBoDTO.setLang(systemPK.getLang());           // 언어
			promotionBoDTO.setDevice(systemPK.getDevice());       // 적용디바이스
			promotionBoDTO.setAbsMbrTpCd(mbr.getMbrTpCd());       // 회원유형
			promotionBoDTO.setAbsMbrAtrbCd(mbr.getMbrAtrbCd());   // 회원속성
			promotionBoDTO.setAbsGepcoId(mbr.getPsitnGrpcoId());  // 그룹사ID
			
			if(MemberBenefitEnum.BnefPymntTpCd.ONOFLNE_CPN.toString().equals(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd())){
				log.debug("==== ONOFLNE_CPN");
				/* 온/오프라인 쿠폰 */
				boolean isSuccess = insertIssuedOnOffCoupon(systemPK, mbr, issuedOnOffCouponMapList, memberBenefitResult);
				if(!isSuccess){
					memberBenefitResult.setResultCd(CommonResponseCode.PM_00021_유효하지_않은_ERP_조건.toMessage());
					memberBenefitResult.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00021_유효하지_않은_ERP_조건.toMessage()));
					log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "ERROR") + " : " + CommonResponseCode.PM_00021_유효하지_않은_ERP_조건.toMessage());
				} else {
					log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "SUCCESS"));
				}
			} else {
				log.debug("==== ONLNE_CPN");
				// 임직원 혜택
				/*
				 * 임직원 쿠폰은 매월 1일 발행되어 해당 월에만 사용가능하도록 설정한다. 
				 */
				if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.EMP_BNEF.toString())) {
					promotionBoDTO.getMbrIssuCpn().setValidBegDate( DateService.getStringCurrentToday() );
					String year = DateService.getStringCurrentYear();
					String month = DateService.getStringCurrentMonth();
					String day = String.valueOf(DateService.daysOfMonth(Integer.parseInt(year), Integer.parseInt(month)));
					promotionBoDTO.getMbrIssuCpn().setValidEndDate(year + month + day);
				}

				else if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.ONLNE_GRD_MOD.toString())) {
					promotionBoDTO.getMbrIssuCpn().setValidBegDate(DateService.getStringCurrentToday());					

					//온라인 등급쿠폰은 분기마지막날까지 사용 (MLB, SA)									
					if(!"DXM".equals(memberBenefitResult.getMbrBnefDetailExtend().getMallId())) {
						promotionBoDTO.getMbrIssuCpn().setValidEndDate(DateService.getLastQuaterDayOfCurrent());
					}else{
						//오프라인 등급쿠폰 발급일 + 180일(DX) 
						InfMbrGrdInfoRecptn param = new InfMbrGrdInfoRecptn();								
						param.setBrndId("X");
						param.setErpCstmrNo(mbr.getErpCstmrNo());												
						List<InfMbrGrdInfoRecptn> listDxGrade = memberBaseSelectRepository.selectMemberGradeInfoCp(param);	
						
						if(!listDxGrade.isEmpty()){
							promotionBoDTO.getMbrIssuCpn().setValidEndDate(DateService.parseString(DateService.plusDate(listDxGrade.get(0).getMbrGrdModDt(), 180), "yyyyMMdd"));	
						}	
					}
				}
				/* 온라인 쿠폰(다운로드) */
				promotionBoDTO.setMallId(memberBenefitResult.getMbrBnefDetailExtend().getMallId());
				String rtnCd = promotionCouponFOComponent.addCouponDownMemberIssue(promotionBoDTO);
				if(StringService.isNotEmpty(rtnCd) && PromotionEnum.CouponIssueLimitReason.SUCCES.toString().equals(rtnCd)){
					//온라인쿠폰 발급 성공
					log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "SUCCESS"));
				} else {
					throw new Exception(rtnCd);
				}
			}
		} catch(Exception ex) {
			log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "ERROR") + " : " + ex);
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 쿠폰 지급 처리
	 */
	public void payPromotionCoupon(MemberBenefitResult memberBenefitResult, SystemPK systemPK, Mbr mbr, String bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList, Date mbrGrdModDt) {
		try {
//			log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "START"));
			log.debug("==== MemberBenefitApiCommandService.payMemberBenefit ====");
			
			PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
			
			// * 기본 값 셋팅 (필수)
			promotionBoDTO.getMbrIssuCpn().setPrmNo(memberBenefitResult.getMbrBnefDetailExtend().getPrmNo());
			promotionBoDTO.getMbrIssuCpn().setMbrNo(mbr.getMbrNo());
			// * 적용 대상 값 셋팅 (필수)
			promotionBoDTO.setMallId(systemPK.getMall());         // 몰ID			
			promotionBoDTO.setLang(systemPK.getLang());           // 언어
			promotionBoDTO.setDevice(systemPK.getDevice());       // 적용디바이스
			promotionBoDTO.setAbsMbrTpCd(mbr.getMbrTpCd());       // 회원유형
			promotionBoDTO.setAbsMbrAtrbCd(mbr.getMbrAtrbCd());   // 회원속성
			promotionBoDTO.setAbsGepcoId(mbr.getPsitnGrpcoId());  // 그룹사ID
			
			if(MemberBenefitEnum.BnefPymntTpCd.ONOFLNE_CPN.toString().equals(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd())){
				log.debug("==== ONOFLNE_CPN");
				/* 온/오프라인 쿠폰 */
				boolean isSuccess = insertIssuedOnOffCoupon(systemPK, mbr, issuedOnOffCouponMapList, memberBenefitResult);
				if(!isSuccess){
					memberBenefitResult.setResultCd(CommonResponseCode.PM_00021_유효하지_않은_ERP_조건.toMessage());
					memberBenefitResult.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00021_유효하지_않은_ERP_조건.toMessage()));
					log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "ERROR") + " : " + CommonResponseCode.PM_00021_유효하지_않은_ERP_조건.toMessage());
				} else {
					log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "SUCCESS"));
				}
			} else {
				log.debug("==== ONLNE_CPN");
				// 임직원 혜택
				/*
				 * 임직원 쿠폰은 매월 1일 발행되어 해당 월에만 사용가능하도록 설정한다. 
				 */
				if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.EMP_BNEF.toString())) {
					promotionBoDTO.getMbrIssuCpn().setValidBegDate( DateService.getStringCurrentToday() );
					String year = DateService.getStringCurrentYear();
					String month = DateService.getStringCurrentMonth();
					String day = String.valueOf(DateService.daysOfMonth(Integer.parseInt(year), Integer.parseInt(month)));
					promotionBoDTO.getMbrIssuCpn().setValidEndDate(year + month + day);
				}

				else if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.ONLNE_GRD_MOD.toString())) {
					promotionBoDTO.getMbrIssuCpn().setValidBegDate(DateService.getStringCurrentToday());					

					//온라인 등급쿠폰은 분기마지막날까지 사용 (MLB, SA)									
					if(!"DXM".equals(memberBenefitResult.getMbrBnefDetailExtend().getMallId())) {
						promotionBoDTO.getMbrIssuCpn().setValidEndDate(DateService.getLastQuaterDayOfCurrent());
					}else{
						//오프라인 등급쿠폰 발급일 + 180일(DX) 
						InfMbrGrdInfoRecptn param = new InfMbrGrdInfoRecptn();								
						param.setBrndId("X");
						param.setErpCstmrNo(mbr.getErpCstmrNo());												
						//List<InfMbrGrdInfoRecptn> listDxGrade = memberBaseSelectRepository.selectMemberGradeInfoCp(param);	
						
						if(null != mbrGrdModDt){
							promotionBoDTO.getMbrIssuCpn().setValidEndDate(DateService.parseString(DateService.plusDate(mbrGrdModDt, 180), "yyyyMMdd"));	
						}	
					}
				}
				/* 온라인 쿠폰(다운로드) */
				promotionBoDTO.setMallId(memberBenefitResult.getMbrBnefDetailExtend().getMallId());
				String rtnCd = promotionCouponFOComponent.addCouponDownMemberIssue(promotionBoDTO);
				if(StringService.isNotEmpty(rtnCd) && PromotionEnum.CouponIssueLimitReason.SUCCES.toString().equals(rtnCd)){
					//온라인쿠폰 발급 성공
					log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "SUCCESS"));
				} else {
					throw new Exception(rtnCd);
				}
			}
		} catch(Exception ex) {
			log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "ERROR") + " : " + ex);
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * P포인트 지급 처리
	 */
	public void payPromotionWebPoint(MemberBenefitResult memberBenefitResult, SystemPK systemPK, Mbr mbr) {
		try {
//			log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "START"));
			log.debug("==== MemberBenefitApiCommandService.payPromotionWebPoint ====");
			
			MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
			BigDecimal pntAmt = memberBenefitResult.getMbrBnefDetailExtend().getWebpntAmt();
			Integer validDayCnt = memberBenefitResult.getMbrBnefDetailExtend().getWebpntValidDaycnt() == null ? 30 : memberBenefitResult.getMbrBnefDetailExtend().getWebpntValidDaycnt();
			
			mbrWebpntHist.setMbrNo(mbr.getMbrNo());
			/*
			if( memberBenefitResult.getMbrBnefDetail().getBnefSectCd().equals(MemberBenefitEnum.BnefSectCd.ONLNE_NEW_JOIN.toString())
			 && memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd().equals(MemberBenefitEnum.BnefDetailSectCd.MBR_JOIN_RECOMENDE_BNEF.toString())){
				String recomendeMbrNo = memberBenefitApiSelectRepository.selectMbrBnefRecomende(mbr.getMbrNo()); //피추천인 회원 번호
				if(StringService.isNotEmpty(recomendeMbrNo)){
					mbrWebpntHist.setMbrNo(recomendeMbrNo);
				}
			}
			*/
			mbrWebpntHist.setWebpntResnCd(memberBenefitResult.getMbrBnefDetailExtend().getWebpntResnCd());
			mbrWebpntHist.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_DCSN.toString());
			mbrWebpntHist.setWebpntTpCd(MemberBenefitEnum.BnefPymntTpCd.WEBPNT.toString());
			mbrWebpntHist.setWebpntDetailResnCd(memberBenefitResult.getMbrBnefDetailExtend().getWebpntDetailResnCd());
			mbrWebpntHist.setResnDscr(memberBenefitResult.getMbrBnefDetailExtend().getPymntResnDscr());
			mbrWebpntHist.setWebpntAmt(pntAmt);
			
			 // TODO 등급 산정일 ~ 등급 산정일 + 2년
			mbrWebpntHist.setUseBegDt(new Date());
			mbrWebpntHist.setUseEndDt(DateService.minusDate(DateService.plusDate(new Date(), validDayCnt.intValue()), 1));	// 웹포인트 유효 일수
			
			//추가
			mbrWebpntHist.setMallId(systemPK.getMall());
			
			memberBenefitCommandService.insertWebPoint(mbrWebpntHist, systemPK);
			
			log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "SUCCESS"));
		} catch(Exception ex) {
			log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "ERROR") + " : " + ex);
			throw new RuntimeException(ex);
		}
		
	}
	
	/**
	 * P포인트 지급 처리
	 */
	public void payPromotionWebPoint(MemberBenefitResult memberBenefitResult, SystemPK systemPK, Mbr mbr, Date mbrGrdModDt) {
		try {
//			log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "START"));
			log.debug("==== MemberBenefitApiCommandService.payPromotionWebPoint ====");
			
			MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
			BigDecimal pntAmt = memberBenefitResult.getMbrBnefDetailExtend().getWebpntAmt();
			Integer validDayCnt = memberBenefitResult.getMbrBnefDetailExtend().getWebpntValidDaycnt() == null ? 30 : memberBenefitResult.getMbrBnefDetailExtend().getWebpntValidDaycnt();
			
			mbrWebpntHist.setMbrNo(mbr.getMbrNo());
			/*
			if( memberBenefitResult.getMbrBnefDetail().getBnefSectCd().equals(MemberBenefitEnum.BnefSectCd.ONLNE_NEW_JOIN.toString())
			 && memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd().equals(MemberBenefitEnum.BnefDetailSectCd.MBR_JOIN_RECOMENDE_BNEF.toString())){
				String recomendeMbrNo = memberBenefitApiSelectRepository.selectMbrBnefRecomende(mbr.getMbrNo()); //피추천인 회원 번호
				if(StringService.isNotEmpty(recomendeMbrNo)){
					mbrWebpntHist.setMbrNo(recomendeMbrNo);
				}
			}
			*/
			mbrWebpntHist.setWebpntResnCd(memberBenefitResult.getMbrBnefDetailExtend().getWebpntResnCd());
			mbrWebpntHist.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_DCSN.toString());
			mbrWebpntHist.setWebpntTpCd(MemberBenefitEnum.BnefPymntTpCd.WEBPNT.toString());
			mbrWebpntHist.setWebpntDetailResnCd(memberBenefitResult.getMbrBnefDetailExtend().getWebpntDetailResnCd());
			mbrWebpntHist.setResnDscr(memberBenefitResult.getMbrBnefDetailExtend().getPymntResnDscr());
			mbrWebpntHist.setWebpntAmt(pntAmt);
			
			 // TODO 등급 산정일 ~ 등급 산정일 + 2년
			mbrWebpntHist.setUseBegDt(new Date());
			mbrWebpntHist.setUseEndDt(DateService.minusDate(DateService.plusDate(mbrGrdModDt, validDayCnt.intValue()), 1));	// 웹포인트 유효 일수
			
			//추가
			mbrWebpntHist.setMallId(systemPK.getMall());
			
			memberBenefitCommandService.insertWebPoint(mbrWebpntHist, systemPK);
			
			log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "SUCCESS"));
		} catch(Exception ex) {
			log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "ERROR") + " : " + ex);
			throw new RuntimeException(ex);
		}
		
	}
	
	/**
	 * 멤버쉽 포인트 지급 처리
	 */
	public void payPromotionMemberShipPoint(MemberBenefitResult memberBenefitResult, SystemPK systemPK, Mbr mbr) {

	}
	
	/**
	 * 쿠폰 지급 validation 확인
	 */
	public MemberBenefitResult validatePromotionCoupon(MemberBenefitResult memberBenefitResult, SystemPK systemPK, Mbr mbr, String bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList){
		MemberBenefitResult result = new MemberBenefitResult();
		MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
		MbrBnefPymntHistExtend mbrBnefPymntHistEx = new MbrBnefPymntHistExtend();
		
		result.setResultCd(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage());
		result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage()));
		
		mbrIssuCpn.setMbrNo(mbr.getMbrNo());
		mbrIssuCpn.setPrmNo(memberBenefitResult.getMbrBnefDetailExtend().getPrmNo());
		
		mbrBnefPymntHistEx.setMbrNo(mbr.getMbrNo());
		//mbrBnefPymntHist.setMallId(systemPK.getMall());
		mbrBnefPymntHistEx.setMallId(null);
		mbrBnefPymntHistEx.setBnefSn(memberBenefitResult.getMbrBnefDetailExtend().getBnefSn());
		mbrBnefPymntHistEx.setBnefDetailTurn(memberBenefitResult.getMbrBnefDetailExtend().getBnefDetailTurn());
		//mbrBnefPymntHist.setBnefSectCd(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd());
		
		// 온/오프라인 쿠폰인데 쿠폰 목록이 없으면 오류
		if(MemberBenefitEnum.BnefPymntTpCd.ONOFLNE_CPN.toString().equals(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd())
				&& (issuedOnOffCouponMapList == null || issuedOnOffCouponMapList.isEmpty())
				) {
			result.setResultCd(CommonResponseCode.PM_00013_회원_혜택_없음.toMessage());
			result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00013_회원_혜택_없음.toMessage()));
		}
		
		// 임직원 혜택 검증
		if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.EMP_BNEF.toString())) {
			mbrIssuCpn.setValidBegDate(DateService.getStringCurrentToday());
			if(promotionRepository.selectCouponIssueCount(mbrIssuCpn) > 0){ //1. 회원 발급 쿠폰 확인
				result.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());
				result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage()));
			}
		}
		// 장바구니 쿠폰 혜택 검증
		else if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.BSK_CPN_BNEF.toString())) {
			// 주문서, 마이페이지, 마이페이지 쿠폰함 화면 진입시 발급하는 것.
			// 1. 사용하지 않은 쿠폰이 없으면 발급.
			// 2. 사용하지 않은 쿠폰이 있으면 발급하지 않음.
			mbrIssuCpn.setCpnStatCd("NO_USE");
			if(promotionRepository.selectCouponIssueCount(mbrIssuCpn) > 0){ //1. 회원 발급 쿠폰 확인
				result.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());
				result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage()));
			}
		}
		// 휴면 해제 회원 혜택 검증
		else if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.RELIS_DRMC_MBR_BNEF.toString())) {
			mbrBnefPymntHistEx.setPymntDtCondition("AFTER");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String today = DateService.parseString(DateService.getStringCurrentToday(), "yyyy-MM-dd") + " 00:00:00";
			Date pymntDt = null;
			try {
				pymntDt = format.parse(today);
				pymntDt = DateService.minusDate(DateService.plusYears(pymntDt, -1), 1);
			} catch (ParseException e) {
				log.warn("", e);
			}
			mbrBnefPymntHistEx.setPymntDt(pymntDt);
			if(memberBenefitApiSelectRepository.selectMbrBnefPymntPsbHistCnt(mbrBnefPymntHistEx) >= memberBenefitResult.getMbrBnefDetailExtend().getPymntPsbCount()){ //2. 회원 혜택 지급 이력 확인
				result.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());
				result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage()));
			}
		}
		// 온라인 등급 변경, 등급별 생일 혜택 이 아니면 검증
		// 등급이 변경될 때마다, 생일이 올때마다 쿠폰을 주므로 검증에서 제외함.
		else if( !bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.ONLNE_GRD_MOD.toString())
				&& !bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.GRDBY_BRTHDY_BNEF.toString())
				) {
			if(promotionRepository.selectCouponIssueCount(mbrIssuCpn) > 0){ //1. 회원 발급 쿠폰 확인
				result.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());
				result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage()));
			} else if(memberBenefitApiSelectRepository.selectMbrBnefPymntPsbHistCnt(mbrBnefPymntHistEx) >= memberBenefitResult.getMbrBnefDetailExtend().getPymntPsbCount()){ //2. 회원 혜택 지급 이력 확인
				result.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());
				result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage()));
			} else {
				if(MemberBenefitEnum.BnefPymntTpCd.ONOFLNE_CPN.toString().equals(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd())) {
					if( bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.NEW_MBR_JOIN.toString())) {
						if( MemberTpCd.UNITY_MBR.toString().equals(mbr.getMbrTpCd()) && StringService.isEmpty(mbr.getErpCstmrNo())){ //3. 회원 유형 및 ERP 고객번호 확인
							result.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
							result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage()));
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * P포인트 지급 validation 확인
	 */
	public MemberBenefitResult validatePromotionWebPoint(MemberBenefitResult memberBenefitResult, SystemPK systemPK, Mbr mbr, String bnefSectCd){
		MemberBenefitResult result = new MemberBenefitResult();
		MbrBnefPymntHistExtend mbrBnefPymntHistEx = new MbrBnefPymntHistExtend();
		MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
		
		result.setResultCd(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage());
		result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage()));
		
		mbrWebpntHist.setMbrNo(mbr.getMbrNo());
		mbrWebpntHist.setMallId(systemPK.getMall());
		mbrWebpntHist.setWebpntResnCd(memberBenefitResult.getMbrBnefDetailExtend().getWebpntResnCd());
		mbrWebpntHist.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_DCSN.toString());
		mbrWebpntHist.setWebpntDetailResnCd(memberBenefitResult.getMbrBnefDetailExtend().getWebpntDetailResnCd());
		
		mbrBnefPymntHistEx.setMbrNo(mbr.getMbrNo());
		mbrBnefPymntHistEx.setMallId(systemPK.getMall());
		mbrBnefPymntHistEx.setBnefSn(memberBenefitResult.getMbrBnefDetailExtend().getBnefSn());
		mbrBnefPymntHistEx.setBnefDetailTurn(memberBenefitResult.getMbrBnefDetailExtend().getBnefDetailTurn());
		//mbrBnefPymntHist.setBnefSectCd(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd());

		// 온라인 등급 변경이 아니면 검증
		// 등급이 변경될 때마다 포인트를 주므로 검증에서 제외함.
		if( !bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.ONLNE_GRD_MOD.toString())) {
			long agoPaymentCnt = 0;
			agoPaymentCnt = memberBenefitApiSelectRepository.selectMemberWebPointListCnt(mbrWebpntHist);
			long agoPaymentHistCnt = 0;
			agoPaymentHistCnt = memberBenefitApiSelectRepository.selectMbrBnefPymntPsbHistCnt(mbrBnefPymntHistEx);
			if(agoPaymentCnt >= memberBenefitResult.getMbrBnefDetailExtend().getPymntPsbCount()){ //1. 회원 웹포인트 이력 확인
				result.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());
				result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage()));
			}
			else if(agoPaymentHistCnt >= memberBenefitResult.getMbrBnefDetailExtend().getPymntPsbCount()) { //2. 회원 혜택 지급 이력 확인
				// 신규 회원 가입
				if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.NEW_MBR_JOIN.toString())) {
					result.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());
					result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage()));
				}
			}
		}
		return result;
	}
	
	/**
	 * 멤버쉽 포인트 지급 validation 확인
	 */
	public MemberBenefitResult validatePromotionMemberShipPoint(MemberBenefitResult memberBenefitResult, SystemPK systemPK, Mbr mbr, String bnefSectCd)  {
		MemberBenefitResult result = new MemberBenefitResult();
		String reasonMsg = memberBenefitResult.getMbrBnefDetailExtend().getPymntResnDscr();
		BigDecimal pntAmt = memberBenefitResult.getMbrBnefDetailExtend().getPntAmt();
		MbrBnefPymntHistExtend mbrBnefPymntHistEx = new MbrBnefPymntHistExtend();
		MbrPntIntrlckHist mbrPntIntrlckHist = new MbrPntIntrlckHist();
		
		result.setResultCd(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage());
		result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage()));
		
		mbrBnefPymntHistEx.setMbrNo(mbr.getMbrNo());
		mbrBnefPymntHistEx.setMallId(systemPK.getMall());
		mbrBnefPymntHistEx.setBnefSn(memberBenefitResult.getMbrBnefDetailExtend().getBnefSn());
		mbrBnefPymntHistEx.setBnefDetailTurn(memberBenefitResult.getMbrBnefDetailExtend().getBnefDetailTurn());
		//mbrBnefPymntHist.setBnefSectCd(memberBenefitResult.getMbrBnefDetailExtend().getBnefDetailSectCd());
		
		mbrPntIntrlckHist.setMbrNo(mbr.getMbrNo());
		mbrPntIntrlckHist.setPntAmt(pntAmt);
		mbrPntIntrlckHist.setPntAplResnCd(memberBenefitResult.getMbrBnefDetailExtend().getPntAplResnCd());
		mbrPntIntrlckHist.setPntResnDscr(reasonMsg);
		
		if( MemberTpCd.UNITY_MBR.toString().equals(mbr.getMbrTpCd()) && StringService.isNotEmpty(mbr.getErpCstmrNo())){ //1. 회원 유형 및 ERP 고객번호 확인
			MemberBenefitDTO memberBenefitDTO = new MemberBenefitDTO();
    		memberBenefitDTO.setMallId(systemPK.getMall());
    		memberBenefitDTO.setErpCstmrNo(mbr.getErpCstmrNo());
    		//memberBenefitDTO.setBnefSectCd(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd());
    		
    		//2. 재가입여부 확인 (DI) - 회원 정보 탈퇴 후 6개월 이후 삭제됨
			String SecessionMbrNo = memberBenefitApiSelectRepository.validCommonMbrCrtfcExistDi(memberBenefitDTO);
			
			if(SecessionMbrNo != null){
				result.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());
				result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage()));
				insertBeforeMemberBenefitHistory(systemPK, mbr, memberBenefitResult, SecessionMbrNo, bnefSectCd);
			} else {
				if(memberBenefitApiSelectRepository.selectMbrPntIntrlckHistCnt(mbrPntIntrlckHist) > 0){ //3. 회원 포인트 연동 이력 확인
					result.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());
					result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage()));
				} else if(memberBenefitApiSelectRepository.selectMbrBnefPymntPsbHistCnt(mbrBnefPymntHistEx) > 0){ // 4. 회원 혜택 지급 이력 확인
					result.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());
					result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage()));
				} else {
					//5. ERP 발급 이력 확인
					List<Map<String, String>> tmpList = getMemberShipPointHistory(systemPK, mbr, reasonMsg);
					if(tmpList.size() > 0){
						result.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());
						result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage()));
					}
				}
			}
		} else {
			result.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
			result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage()));
		}
		return result;
	}
	
	/**
	 * 쿠폰 프로모션 확인
	 */
	public boolean checkPromotionCoupon(MemberBenefitResult memberBenefitResult, SystemPK systemPK, Mbr mbr, String prmNo){
		String chkMsg = "";  //log MSG
		boolean isChkPrm = false;
		
		try {
			//1. promotion 정보 SELECT
			PrmCpn prmCpn = new PrmCpn();
			prmCpn.setPrmNo(prmNo);
			
			CouponPromotionResult cpnInfo = promotionService.selectFirstAppCpnInfo(prmCpn);
			
			PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
			
			if(cpnInfo != null){
				//2. promotion 유효성 검사
				//체크용 변수/객체 설정 : 발급대상 회원
		        List<String> listMbrNo = new ArrayList<String>();
		        listMbrNo.add(mbr.getMbrNo());
		        
		        promotionBoDTO.setListMbrNo(listMbrNo);
		        
		        promotionBoDTO.setPrm(cpnInfo.getPrm());
		        promotionBoDTO.setPrmCpn(cpnInfo.getPrmCpn());
		        promotionBoDTO.setMbrIssuCpn(cpnInfo.getMbrIssuCpn());
		        
				// * 적용 대상 값 셋팅 (필수)
				promotionBoDTO.setMallId(systemPK.getMall()); // - 몰ID
				promotionBoDTO.setLang(systemPK.getLang()); // - 언어
				promotionBoDTO.setDevice(systemPK.getDevice()); // - 적용 디바이스
				promotionBoDTO.setAbsMbrTpCd(mbr.getMbrTpCd()); // - 회원유형
				promotionBoDTO.setAbsMbrAtrbCd(mbr.getMbrAtrbCd()); // - 회원속성
				promotionBoDTO.setAbsGepcoId(mbr.getPsitnGrpcoId()); // - 그룹사ID
				
		        int issuAplTgtCnt = promotionService.selectCouponIssueTargetCheck(promotionBoDTO);
		        
		        if (issuAplTgtCnt < 1) {
		            chkMsg = CouponIssueLimitReason.NONISSU_TGT.toString();
		        } else {
		            //쿠폰 회원 발급 가능 체크
		            PromotionBoResult couponResult = promotionService.selectCouponIssueUseCheck(promotionBoDTO);
		            
		            if (CouponIssueLimitReason.SUCCES.toString().equals(couponResult.getChkMsg())) {
		            	
		            	/* 신규쿠폰의 경우 멤버쉽 전환 시 1번 발급되기 때문에 chkCoupon를 변수를 통해 값을 체크한다. */
		            	promotionBoDTO.getMbrIssuCpn().setMbrNo(mbr.getMbrNo());
		            	int chkCoupon = promotionService.selectCouponIssueCount(promotionBoDTO.getMbrIssuCpn());
		            	
		            	if(chkCoupon <= 0){
		            		isChkPrm = true;
		            	}else{ //발급 내역 있음
		            		chkMsg = CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString();
		            	}	
		            }else{
		            	chkMsg = couponResult.getChkMsg();
		            }
		        }
			} else {
				chkMsg = CouponIssueLimitReason.ADMIN_INQ.toString();
			}
			log.info("프로모션 유효성 확인 Mbr_No : {},  chkMsg : {}",mbr.getMbrNo(),chkMsg);
		} catch(Exception ex) {
			log.warn("", ex);
			log.info(memberBenefitApiSelectService.getLogMsg(memberBenefitResult, "ERROR") + " : " + ex);
		}
		return isChkPrm;
	}
	
	/**
	 * 회원 혜택 지급 이력 저장처리
	 */
	public void insertMemberBenefitHistory(MemberBenefitResult memberBenefitResult, SystemPK systemPK, Mbr mbr, MbrBnefPymntHist bfMbrBnefPymntHist)  {

		// 회원혜택 지급 이력 저장
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("MBR_NO", mbr.getMbrNo());
		MbrBnefPymntHist mbrBnefPymntHist = new MbrBnefPymntHist();

		HttpServletRequest request = ContextService.getCurrentRequest();
		BigDecimal pntPymntAmt = memberBenefitResult.getMbrBnefDetailExtend().getPntAmt();
		BigDecimal webPntPymntAmt = memberBenefitResult.getMbrBnefDetailExtend().getWebpntAmt();

		long time = System.currentTimeMillis();
		Date pymntDt = new Date(time);

		mbrBnefPymntHist.setMbrNo(mbr.getMbrNo());
		mbrBnefPymntHist.setUdterId(mbr.getMbrNo());
		mbrBnefPymntHist.setRegtrId(mbr.getMbrNo());
		/*
		if(memberBenefitResult.getMbrBnefDetail().getBnefSectCd().equals(MemberBenefitEnum.BnefSectCd.ONLNE_NEW_JOIN.toString())){
			String recomendeMbrNo = memberBenefitApiSelectRepository.selectMbrBnefRecomende(mbr.getMbrNo()); //피추천인 회원 번호
			if(StringService.isNotEmpty(recomendeMbrNo)){
				if(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd().equals(MemberBenefitEnum.BnefDetailSectCd.MBR_JOIN_RECOMENDE_BNEF.toString())){
					mbrBnefPymntHist.setMbrNo(recomendeMbrNo);
					conditions.put("MBR_NO", recomendeMbrNo);
					mbrBnefPymntHist.setRecomendrMbrNo(mbr.getMbrNo());
					mbrBnefPymntHist.setUdterId(recomendeMbrNo);
					mbrBnefPymntHist.setRegtrId(recomendeMbrNo);
				} else if(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd().equals(MemberBenefitEnum.BnefDetailSectCd.MBR_JOIN_RECOMENDR_BNEF.toString())) {
					mbrBnefPymntHist.setRecomendeMbrNo(recomendeMbrNo);
				}
			}
		}
		*/
		mbrBnefPymntHist.setBnefPymntTurn(getIdGenService().generateDBOrder(sqlSession1, "MBR_BNEF_PYMNT_HIST", "BNEF_PYMNT_TURN", conditions, DatabaseType.ORACLE));
		mbrBnefPymntHist.setBnefSn(memberBenefitResult.getMbrBnefDetailExtend().getBnefSn());
		mbrBnefPymntHist.setBnefDetailTurn(memberBenefitResult.getMbrBnefDetailExtend().getBnefDetailTurn());
		mbrBnefPymntHist.setDvcCd(systemPK.getDevice());
		mbrBnefPymntHist.setLangCd(systemPK.getLang());
		mbrBnefPymntHist.setMallId(systemPK.getMall());
		mbrBnefPymntHist.setMobileAppSectCd(systemPK.getApp());
		mbrBnefPymntHist.setTgtMbrTpCd(mbr.getMbrTpCd());
		mbrBnefPymntHist.setTgtMbrAtrbCd(mbr.getMbrAtrbCd());
		//mbrBnefPymntHist.setBnefSectCd(memberBenefitResult.getMbrBnefDetailExtend().getBnefDetailSectCd());
		
		if(bfMbrBnefPymntHist != null){
			mbrBnefPymntHist.setBfPymntMbrNo(bfMbrBnefPymntHist.getMbrNo());
		}
		
		mbrBnefPymntHist.setBnefPymntTpCd(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd());
		if(MemberBenefitEnum.BnefPymntTpCd.MBSH_PNT.toString().equals(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd())) {
			mbrBnefPymntHist.setPntPymntAmt(pntPymntAmt);
		} else if(MemberBenefitEnum.BnefPymntTpCd.WEBPNT.toString().equals(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd())) {
			mbrBnefPymntHist.setPntPymntAmt(webPntPymntAmt);
		} else if( MemberBenefitEnum.BnefPymntTpCd.ONOFLNE_CPN.toString().equals(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd())
			    || MemberBenefitEnum.BnefPymntTpCd.ONLNE_CPN.toString().equals(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd())) {
			if(bfMbrBnefPymntHist != null){
				mbrBnefPymntHist.setPrmNo(bfMbrBnefPymntHist.getPrmNo());
			} else {
				mbrBnefPymntHist.setPrmNo(memberBenefitResult.getMbrBnefDetailExtend().getPrmNo());
			}
		}

		if(memberBenefitResult.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())){
			mbrBnefPymntHist.setBnefPymntSuccesYn(MemberBenefitEnum.YES.toString());
		} else if(memberBenefitResult.getResultCd().equals(CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage())){
			StringWriter error = new StringWriter();
			Exception ex = memberBenefitResult.getResultException();
			String errMsg = "";
			
			ex.printStackTrace(new PrintWriter(error));
			
			errMsg = error.toString();
			
			mbrBnefPymntHist.setBnefPymntSuccesYn(MemberBenefitEnum.NO.toString());
			if(errMsg.length()>1000){
				mbrBnefPymntHist.setBnefPymntFailrResnCont(StringService.substring(errMsg,0,1000));
			}else{
				mbrBnefPymntHist.setBnefPymntFailrResnCont(errMsg);
			}
		}
		
		if(bfMbrBnefPymntHist != null){
			mbrBnefPymntHist.setPymntDt(bfMbrBnefPymntHist.getPymntDt());
		} else {
			mbrBnefPymntHist.setPymntDt(pymntDt);
		}
		
		if(request == null){
			try {
				mbrBnefPymntHist.setTgtMbrIp(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				throw new RuntimeException(e);
			}
		} else {
			mbrBnefPymntHist.setTgtMbrIp(RemoteAddrUtil.getRemoteAddr(request));
		}
		
		mbrBnefPymntHistRepository.insertMbrBnefPymntHist(mbrBnefPymntHist);
		
	}
	
	/**
	 * 이전 회원 혜택 지급 이력 저장
	 * @param mbr
	 * @
	 */
	public void insertBeforeMemberBenefitHistory(SystemPK systemPK, Mbr mbr, MemberBenefitResult memberBenefitResult, String SecessionMbrNo, String bnefSectCd)  {
		MbrBnefPymntHist PymntDto = new MbrBnefPymntHist();
		
		PymntDto.setMbrNo(SecessionMbrNo);
		PymntDto.setMallId(systemPK.getMall());
		//PymntDto.setBnefSectCd(bnefSectCd);
		
		// 이전 회원 지급 이력 조회
		MbrBnefPymntHist beforeResult = memberBenefitApiSelectRepository.selectMbrBnefPymntHist(PymntDto);
		
		MemberBenefitResult result = new MemberBenefitResult();
		MbrBnefDetailExtend mbrBnefDetailExtend = new MbrBnefDetailExtend();
		mbrBnefDetailExtend.setBnefSn(memberBenefitResult.getMbrBnefDetailExtend().getBnefSn());
		mbrBnefDetailExtend.setBnefDetailTurn(memberBenefitResult.getMbrBnefDetailExtend().getBnefDetailTurn());
		mbrBnefDetailExtend.setBnefTpCd(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd());
		mbrBnefDetailExtend.setPrmNo(memberBenefitResult.getMbrBnefDetailExtend().getPrmNo());
		mbrBnefDetailExtend.setPntAmt(memberBenefitResult.getMbrBnefDetailExtend().getPntAmt());
		mbrBnefDetailExtend.setWebpntAmt(memberBenefitResult.getMbrBnefDetailExtend().getWebpntAmt());
		mbrBnefDetailExtend.setPntAplResnCd(memberBenefitResult.getMbrBnefDetailExtend().getPntAplResnCd());
		mbrBnefDetailExtend.setPymntPsbCount(memberBenefitResult.getMbrBnefDetailExtend().getPymntPsbCount());
		mbrBnefDetailExtend.setPymntResnDscr(memberBenefitResult.getMbrBnefDetailExtend().getPymntResnDscr());
		mbrBnefDetailExtend.setWebpntResnCd(memberBenefitResult.getMbrBnefDetailExtend().getWebpntResnCd());
		mbrBnefDetailExtend.setWebpntDetailResnCd(memberBenefitResult.getMbrBnefDetailExtend().getWebpntDetailResnCd());
		mbrBnefDetailExtend.setWebpntValidDaycnt(memberBenefitResult.getMbrBnefDetailExtend().getWebpntValidDaycnt());
		mbrBnefDetailExtend.setBnefSectCd(memberBenefitResult.getMbrBnefDetailExtend().getBnefSectCd());
		mbrBnefDetailExtend.setBnefDetailSectCd(memberBenefitResult.getMbrBnefDetailExtend().getBnefDetailSectCd());

		result.setMbrBnefDetailExtend(mbrBnefDetailExtend);
		result.setResultCd(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage());
		result.setResultMsg(memberBenefitApiSelectService.getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage()));
		
		// 탈퇴회원 이력 저장
		if(beforeResult != null){
			insertMemberBenefitHistory(result, systemPK, mbr, beforeResult);
		} else {
			insertMemberBenefitHistory(result, systemPK, mbr, PymntDto);
		}
	}
	
	/**
	 * AdapterHeader 값 설정
	 * 
	 * @return
	 */
	AdapterHeader setAdapterHeader(SystemPK systemPK) {
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(systemPK.getMall());
		adapterHeader.setLangCd(systemPK.getLang());
		adapterHeader.setDvcCd(systemPK.getDevice());

		return adapterHeader;
	}
	
	/**
	 * 발급된 온오프라인 쿠폰을 온라인에 등록한다.
	 * prm-no 이용
	 * 
	 */
	public boolean insertIssuedOnOffCoupon(SystemPK systemPK, Mbr mbr, List<Map<String, String>> issuedOnOffCouponMapList, MemberBenefitResult memberBenefitResult) {
		boolean isSuccess = false;
		
		try {
			String issuedCouponType = "";
			String issuedCouponNo = "";
			String useStartDt = "";
			String useEndDt = "";
			String createDtStr = "";
			Date createDt = null;
			String useYn = "";
			String useDtStr = "";
			Date useDt = null;
			String mallId = "";
			boolean couponFlag = true;
			
			if(issuedOnOffCouponMapList == null) {
				return isSuccess;
			}
			
			for(Map<String, String> map : issuedOnOffCouponMapList) {
				issuedCouponType = map.get("ISSUED_COUPON_TYPE");
				issuedCouponNo = map.get("ISSUED_COUPON_NO");
				
				if(map.get("USE_START_DT") != null && !"".equals(map.get("USE_START_DT"))
						&& map.get("USE_END_DT") != null && !"".equals(map.get("USE_END_DT"))
						&& map.get("CREATE_DT") != null && !"".equals(map.get("CREATE_DT"))
						) {
					useStartDt = map.get("USE_START_DT");
					useEndDt = map.get("USE_END_DT");
					createDtStr = map.get("CREATE_DT");
					useYn = map.get("USE_YN");
					useDtStr = map.get("USE_DT");
					
					try {
						createDt = null;
						createDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createDtStr);
					}
					catch(Exception e) {
						createDt = null;
						log.warn("> insertIssuedOnOffCoupon Exception : createDt {}", e.getMessage());
					}
					
					try {
						useDt = null;
						if(StringService.isNotEmpty(useDtStr)) {
							useDt = new SimpleDateFormat("yyyy-MM-dd").parse(useDtStr);
						}
					}
					catch(Exception e) {
						useDt = null;
						log.warn("> insertIssuedOnOffCoupon Exception : useDt {}", e.getMessage());
					}
				}
				
				mallId = map.get("MALL_ID"); 
				
				List<PrmExtend> prmList = onOffCouponRepository.selectCouponPrmNo(issuedCouponType, mallId);
				
				if(prmList == null || prmList.isEmpty()) {
					log.error(">> ERP에서 넘어온 issuedCouponType이 우리쪽 DB에 없음. promotion 추가 해야함 : " + map);
				}
				else {
					couponFlag = false;	// 초기화
					for(PrmExtend prm : prmList) {
						if(prm.getPrmNo().equals(memberBenefitResult.getMbrBnefDetailExtend().getPrmNo())) {
							couponFlag = true;
						}
					}
					if(couponFlag) {
						onOffCouponService.insertOnOffCouponIssueByPrmNo(issuedCouponType, issuedCouponNo, mbr.getMbrNo(), mbr.getMbrNo(), useStartDt, useEndDt, createDt, useYn, useDt, mallId, memberBenefitResult.getMbrBnefDetailExtend().getPrmNo());
					}
				}
			}
			
			isSuccess = true;
		}
		catch(Exception e) {
			isSuccess = false;
		}
		return isSuccess;
	}
	
	/**
	 * 발급된 온오프라인 쿠폰을 온라인에 등록한다.
	 * 
	 */
	public boolean insertIssuedOnOffCoupon(SystemPK systemPK, Mbr mbr, List<Map<String, String>> issuedOnOffCouponMapList) {
		boolean isSuccess = false;
		
		try {
			String issuedCouponType = "";
			String issuedCouponNo = "";
			String useStartDt = "";
			String useEndDt = "";
			String createDtStr = "";
			Date createDt = null;
			String useYn = "";
			String useDtStr = "";
			Date useDt = null;
			String mallId = "";
			
			if(issuedOnOffCouponMapList == null) {
				return isSuccess;
			}
			
			for(Map<String, String> map : issuedOnOffCouponMapList) {
				issuedCouponType = map.get("ISSUED_COUPON_TYPE");
				issuedCouponNo = map.get("ISSUED_COUPON_NO");

				if(map.get("USE_START_DT") != null && !"".equals(map.get("USE_START_DT"))
						&& map.get("USE_END_DT") != null && !"".equals(map.get("USE_END_DT"))
						&& map.get("CREATE_DT") != null && !"".equals(map.get("CREATE_DT"))
						) {
					useStartDt = map.get("USE_START_DT");
					useEndDt = map.get("USE_END_DT");
					createDtStr = map.get("CREATE_DT");
					useYn = map.get("USE_YN");
					useDtStr = map.get("USE_DT");
					
					try {
						createDt = null;
						createDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createDtStr);
					}
					catch(Exception e) {
						createDt = null;
						log.warn("> insertIssuedOnOffCoupon Exception : createDt {}", e.getMessage());
					}
					
					try {
						useDt = null;
						if(StringService.isNotEmpty(useDtStr)) {
							useDt = new SimpleDateFormat("yyyy-MM-dd").parse(useDtStr);
						}
					}
					catch(Exception e) {
						useDt = null;
						log.warn("> insertIssuedOnOffCoupon Exception : useDt {}", e.getMessage());
					}
				}
				
				mallId = map.get("MALL_ID");
				
				PrmExtend prm = onOffCouponRepository.selectCouponPrmNoFromBenefit(issuedCouponType, mallId, mbr.getMbrNo());
				
				if(prm == null) {
					log.error(">> ERP에서 넘어온 issuedCouponType이 우리쪽 DB에 없음. promotion 추가 해야함 : " + map);
				}
				
				onOffCouponService.insertOnOffCouponIssueByPrmNo(issuedCouponType, issuedCouponNo, mbr.getMbrNo(), mbr.getMbrNo(), useStartDt, useEndDt, createDt, useYn, useDt, mallId, prm.getPrmNo());
			}
			
			isSuccess = true;
		}
		catch(Exception e) {
			isSuccess = false;
		}
		return isSuccess;
	}
	
    /**
	 * 멤버쉽 포인트 내역 조회
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getMemberShipPointHistory(SystemPK systemPK, Mbr mbr, String reasonMsg)  {
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();	
		return returnList;
	}
	
	/**
	 * 회원혜택 등록.
	 */
	public void insertMemberBenefit(MemberBenefitBoDTO dto) {
		try{
			memberBenefitApiCommandRepository.insertMemberBenefit(dto);
		} catch(Exception e){
			if(log.isWarnEnabled()) log.warn("> insertMemberBenefit Exception : {}", e.getMessage());
		}
	}
	
	/**
	 * 회원혜택 수정.
	 */
	public void updateMemberBenefit(MemberBenefitBoDTO dto) {
		try{
			memberBenefitApiCommandRepository.updateMemberBenefit(dto);
		} catch(Exception e){
			if(log.isWarnEnabled()) log.warn("> updateMemberBenefit Exception : {}", e.getMessage());
		}
	}
	
    /**
     * 회원혜택 적용대상 등록.
     */
    public void insertMemberBenefitApplyTarget(MemberBenefitBoDTO dto) {

        log.debug("MemberBenefitCommandService insertMemberBenefitApplyTarget :::::::::::::::::::: "+ dto.toString());
        MbrBnefAplTgt mbrBnefAplTgt = dto.getMbrBnefAplTgt();

        //몰 ID
        List<String> mallList = new ArrayList<String>();

        if (StringService.isNotEmpty(mbrBnefAplTgt.getMallId())) {
            mallList = Arrays.asList(mbrBnefAplTgt.getMallId().split(PromotionSeparator.DELIMITER.toString()));
            
            for (String mallId : mallList) {
            	MbrBnefAplTgt mbat = new MbrBnefAplTgt();
            	
            	mbat.setBnefSn(dto.getBnefSn());
            	mbat.setMallId(mallId);
            	mbat.setBnefTgtTpCd(BnefTgtTpCd.MALL_ID.toString());
            	mbat.setRegtrId(dto.getRegtrId());
            	mbat.setUdterId(dto.getUdterId());

            	memberBenefitApiCommandRepository.insertMemberBenefitApplyTarget(mbat);
            }            
        }

        //언어 코드
        List<String> langList = new ArrayList<String>();

        if (StringService.isNotEmpty(mbrBnefAplTgt.getLangCd())) {
            langList = Arrays.asList(mbrBnefAplTgt.getLangCd().split(PromotionSeparator.DELIMITER.toString()));
            
            for (String langCd : langList) {
            	MbrBnefAplTgt mbat = new MbrBnefAplTgt();

            	mbat.setBnefSn(dto.getBnefSn());
            	mbat.setLangCd(langCd);
            	mbat.setBnefTgtTpCd(BnefTgtTpCd.LANG.toString());

            	mbat.setRegtrId(dto.getRegtrId());
            	mbat.setUdterId(dto.getUdterId());

                memberBenefitApiCommandRepository.insertMemberBenefitApplyTarget(mbat);
            }            
        }
       
        //디바이스 코드
        List<String> dvcList = new ArrayList<String>();

        if (StringService.isNotEmpty(mbrBnefAplTgt.getDvcCd())) {
            dvcList = Arrays.asList(mbrBnefAplTgt.getDvcCd().split(PromotionSeparator.DELIMITER.toString()));
            
            for (String dvc : dvcList) {
            	MbrBnefAplTgt mbat = new MbrBnefAplTgt();

            	mbat.setDvcCd(dvc);
            	mbat.setBnefTgtTpCd(BnefTgtTpCd.DVC.toString());

            	mbat.setBnefSn(dto.getBnefSn());
            	mbat.setRegtrId(dto.getRegtrId());
            	mbat.setUdterId(dto.getUdterId());

            	memberBenefitApiCommandRepository.insertMemberBenefitApplyTarget(mbat);
            }            
        }
        
        //모바일 어플리케이션 구분 코드
        List<String> mobileAppList = new ArrayList<String>();

        if (StringService.isNotEmpty(mbrBnefAplTgt.getMobileAppSectCd())) {
        	mobileAppList = Arrays.asList(mbrBnefAplTgt.getMobileAppSectCd().split(PromotionSeparator.DELIMITER.toString()));

            for (String mobileApp : mobileAppList) {
            	MbrBnefAplTgt mbat = new MbrBnefAplTgt();

            	mbat.setMobileAppSectCd(mobileApp);
            	mbat.setBnefTgtTpCd(BnefTgtTpCd.MOBILE_APP_SECT.toString());

            	mbat.setBnefSn(dto.getBnefSn());
            	mbat.setRegtrId(dto.getRegtrId());
            	mbat.setUdterId(dto.getUdterId());

            	memberBenefitApiCommandRepository.insertMemberBenefitApplyTarget(mbat);
            } 
        }
        
        //적용 회원 유형
        List<String> tgtMbrTpList = new ArrayList<String>();

        if (StringService.isNotEmpty(mbrBnefAplTgt.getTgtMbrTpCd())) {
            tgtMbrTpList = Arrays.asList(mbrBnefAplTgt.getTgtMbrTpCd().split(PromotionSeparator.DELIMITER.toString()));

            for (String tgtMbrTp : tgtMbrTpList) {
            	MbrBnefAplTgt mbat = new MbrBnefAplTgt();

            	mbat.setTgtMbrTpCd(tgtMbrTp);
            	mbat.setBnefTgtTpCd(BnefTgtTpCd.TGT_MBR_TP.toString());
            	
            	mbat.setBnefSn(dto.getBnefSn());
            	mbat.setRegtrId(dto.getRegtrId());
            	mbat.setUdterId(dto.getUdterId());

            	memberBenefitApiCommandRepository.insertMemberBenefitApplyTarget(mbat);
            }
        }

        //적용 회원 속성
        List<String> tgtMbrAtrbList = new ArrayList<String>();

        if (StringService.isNotEmpty(mbrBnefAplTgt.getTgtMbrAtrbCd())) {
            tgtMbrAtrbList = Arrays.asList(mbrBnefAplTgt.getTgtMbrAtrbCd().split(PromotionSeparator.DELIMITER.toString()));
            
            for (String tgtMbrAtrb : tgtMbrAtrbList) {
            	MbrBnefAplTgt mbat = new MbrBnefAplTgt();

            	mbat.setTgtMbrAtrbCd(tgtMbrAtrb);
            	mbat.setBnefTgtTpCd(BnefTgtTpCd.TGT_MBR_ATRB.toString());

            	mbat.setBnefSn(dto.getBnefSn());
            	mbat.setRegtrId(dto.getRegtrId());
            	mbat.setUdterId(dto.getUdterId());

            	memberBenefitApiCommandRepository.insertMemberBenefitApplyTarget(mbat);
            }
        }

        //그룹사 ID
        if (StringService.isNotEmpty(mbrBnefAplTgt.getGrpcoId())) {
            List<String> grpcoIdList = Arrays.asList(mbrBnefAplTgt.getGrpcoId().split(PromotionSeparator.DELIMITER.toString()));

            for (String grpcoId : grpcoIdList) {
            	MbrBnefAplTgt mbat = new MbrBnefAplTgt();

            	mbat.setGrpcoId(grpcoId);
                mbat.setBnefTgtTpCd(BnefTgtTpCd.GRPCO_ID.toString());

            	mbat.setBnefSn(dto.getBnefSn());
	        	mbat.setRegtrId(dto.getRegtrId());
	        	mbat.setUdterId(dto.getUdterId());

	        	memberBenefitApiCommandRepository.insertMemberBenefitApplyTarget(mbat);
            }
        }

        //제휴업체 ID
        if (StringService.isNotEmpty(mbrBnefAplTgt.getAffComId())) {
            List<String> affComIdList = Arrays.asList(mbrBnefAplTgt.getAffComId().split(PromotionSeparator.DELIMITER.toString()));

            for (String affComId : affComIdList) {
            	MbrBnefAplTgt mbat = new MbrBnefAplTgt();

            	mbat.setAffComId(affComId);
            	mbat.setBnefTgtTpCd(BnefTgtTpCd.AFF_COM_ID.toString());

            	mbat.setBnefSn(dto.getBnefSn());
	        	mbat.setRegtrId(dto.getRegtrId());
	        	mbat.setUdterId(dto.getUdterId());

	        	memberBenefitApiCommandRepository.insertMemberBenefitApplyTarget(mbat);
            }
        }
        
        //채널 수신 동의 구분 코드
        List<String> chnnlRecptnAgrSectList = new ArrayList<String>();

        if (StringService.isNotEmpty(mbrBnefAplTgt.getChnnlRecptnAgrSectCd())) {
        	chnnlRecptnAgrSectList = Arrays.asList(mbrBnefAplTgt.getChnnlRecptnAgrSectCd().split(PromotionSeparator.DELIMITER.toString()));
        	
            for (String chnnlRecptnAgrSectCd : chnnlRecptnAgrSectList) {
            	MbrBnefAplTgt mbat = new MbrBnefAplTgt();

            	mbat.setBnefSn(dto.getBnefSn());
            	mbat.setChnnlRecptnAgrSectCd(chnnlRecptnAgrSectCd);
            	mbat.setBnefTgtTpCd(BnefTgtTpCd.CHNNL_RECPTN_AGR_SECT.toString());

            	mbat.setRegtrId(dto.getRegtrId());
            	mbat.setUdterId(dto.getUdterId());

                memberBenefitApiCommandRepository.insertMemberBenefitApplyTarget(mbat);
            }  
        }      
    }
    
	/**
	 * 회원혜택 지급혜택 등록.
	 */
	public void insertMemberBenefitDetail(MemberBenefitBoDTO dto) {
		
		log.debug("MemberBenefitCommandService insertMemberBenefitDetail :::::::::::::::::::: "+ dto.toString());
		
		memberBenefitApiCommandRepository.insertMemberBenefitDetail(dto);
	}
	
    /**
     * 회원혜택 적용대상 삭제.
     */
    public void deleteMemberBenefitApplyTarget(MemberBenefitBoDTO dto) {
    	
        log.debug("MemberBenefitCommandService deleteMemberBenefitApplyTarget :::::::::::::::::::: "+ dto.toString());

        memberBenefitApiCommandRepository.deleteMemberBenefitApplyTarget(dto);
    }
    
    /**
     * 회원혜택 지급혜택 삭제.
     */
    public void deleteMemberBenefitDetail(MemberBenefitBoDTO dto) {
    	
        log.debug("MemberBenefitCommandService deleteMemberBenefitDetail :::::::::::::::::::: "+ dto.toString());

        memberBenefitApiCommandRepository.deleteMemberBenefitDetail(dto);
    }
    
	/**
	 * 회원혜택 지급혜택 등록.
	 */
    public void insertMemberBenefitDetail(List<MemberBenefitBoDTO> list) {
    	
    	final String loginId = BOSecurityUtil.getLoginId();
    	SimpleDateFormat sdformat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
		try {
	    	if(list != null && !list.isEmpty()) {
	    		
	    		for(MemberBenefitBoDTO dto : list){
	                Map<String, Object> conditions = Maps.newHashMap();    
	                conditions.put("bnef_sn", dto.getDtlBnefSn());

	                int dtlBnefDetailTurn = idGenService.generateDBOrder(sqlSession1, "MBR_BNEF_DETAIL", "BNEF_DETAIL_TURN", conditions, DatabaseType.ORACLE);
	                dto.setDtlBnefDetailTurn(dtlBnefDetailTurn);
	                
					if(dto.getDtlStrBnefBegDt() != null && !"".equals(dto.getDtlStrBnefBegDt())) {
						
						dto.setDtlBnefBegDt(sdformat2.parse(dto.getDtlStrBnefBegDt()+":00"));				
					}
					if(dto.getDtlStrBnefEndDt() != null && !"".equals(dto.getDtlStrBnefEndDt())) {
						
						dto.setDtlBnefEndDt(sdformat2.parse(dto.getDtlStrBnefEndDt()+":59"));				
					}				

					if(BnefPymntTpCd.WEBPNT.toString().equals(dto.getDtlBnefTpCd())){
						dto.setDtlWebpntResnCd("EVT");
						if(BnefDetailSectCd.MOBILE_APP_DWLD_BNEF.toString().equals(dto.getDtlBnefDetailSectCd())){
							dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.MOBILE_APP_DWLD_BNEF.toString());							
						}else if(BnefDetailSectCd.NEW_MBR_JOIN.toString().equals(dto.getDtlBnefDetailSectCd())){
							dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.NEW_MBR_JOIN.toString());	
						}else if(BnefDetailSectCd.MARKT_RECPTN_AGR.toString().equals(dto.getDtlBnefDetailSectCd())){
							dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.MARKT_RECPTN_AGR.toString());	
						}else if(BnefDetailSectCd.NEW_UNITY_MBR_JOIN.toString().equals(dto.getDtlBnefDetailSectCd())){
							dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.NEW_UNITY_MBR_JOIN.toString());	
						}else if(BnefDetailSectCd.MBR_JOIN_RECOMENDE_BNEF.toString().equals(dto.getDtlBnefDetailSectCd())){
							dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.MBR_JOIN_RECOMENDE_BNEF.toString());	
						}else if(BnefDetailSectCd.MBR_JOIN_RECOMENDR_BNEF.toString().equals(dto.getDtlBnefDetailSectCd())){
							dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.MBR_JOIN_RECOMENDR_BNEF.toString());	
						}
					}
					if(BnefPymntTpCd.MBSH_PNT.toString().equals(dto.getDtlBnefTpCd())){
						if(BnefDetailSectCd.MOBILE_APP_DWLD_BNEF.toString().equals(dto.getDtlBnefDetailSectCd())){
							dto.setDtlPntAplResnCd(BnefDetailSectCd.MOBILE_APP_DWLD_BNEF.toString());							
						}else if(BnefDetailSectCd.NEW_MBR_JOIN.toString().equals(dto.getDtlBnefDetailSectCd())){
							dto.setDtlPntAplResnCd(BnefDetailSectCd.NEW_MBR_JOIN.toString());	
						}else if(BnefDetailSectCd.MARKT_RECPTN_AGR.toString().equals(dto.getDtlBnefDetailSectCd())){
							dto.setDtlPntAplResnCd(BnefDetailSectCd.MARKT_RECPTN_AGR.toString());	
						}else if(BnefDetailSectCd.NEW_UNITY_MBR_JOIN.toString().equals(dto.getDtlBnefDetailSectCd())){
							dto.setDtlPntAplResnCd(BnefDetailSectCd.NEW_UNITY_MBR_JOIN.toString());	
						}
					}
					dto.setDtlRegtrId(loginId);
					dto.setDtlUdterId(loginId);
					dto.setDtlAprvSectCd(dto.getDefaultMemberBenefitStatCd());		
					
					log.debug("MemberBenefitCommandService insertMemberBenefitDetail :::::::::::::::::::: "+ dto.toString());
					
					memberBenefitApiCommandRepository.insertMemberBenefitDetail(dto);
	    			
	    		}
	    	}  			

		} catch(Exception e) {
			throw new RuntimeException(e);
		}
    }
    
	public void updateMemberBenefitDetail(List<MemberBenefitBoDTO> list) {
		final String loginId = BOSecurityUtil.getLoginId();
		SimpleDateFormat sdformat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			if(list != null && !list.isEmpty()) {
				for(MemberBenefitBoDTO dto : list){
					log.debug("MemberBenefitCommandService updateMemberBenefitDetail :::::::::::::::::::: "+ dto.toString());
					
					if("APRV_WAIT".equals(dto.getDtlAprvSectCd())){
						if(dto.getDtlStrBnefBegDt() != null && !"".equals(dto.getDtlStrBnefBegDt())) {
							
							dto.setDtlBnefBegDt(sdformat2.parse(dto.getDtlStrBnefBegDt()+":00"));				
						}
						if(dto.getDtlStrBnefEndDt() != null && !"".equals(dto.getDtlStrBnefEndDt())) {
							
							dto.setDtlBnefEndDt(sdformat2.parse(dto.getDtlStrBnefEndDt()+":59"));				
						}
						
						if(BnefPymntTpCd.WEBPNT.toString().equals(dto.getDtlBnefTpCd())){
							dto.setDtlWebpntResnCd("EVT");
							if(BnefDetailSectCd.MOBILE_APP_DWLD_BNEF.toString().equals(dto.getDtlBnefDetailSectCd())){
								dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.MOBILE_APP_DWLD_BNEF.toString());							
							}else if(BnefDetailSectCd.NEW_MBR_JOIN.toString().equals(dto.getDtlBnefDetailSectCd())){
								dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.NEW_MBR_JOIN.toString());	
							}else if(BnefDetailSectCd.MARKT_RECPTN_AGR.toString().equals(dto.getDtlBnefDetailSectCd())){
								dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.MARKT_RECPTN_AGR.toString());	
							}else if(BnefDetailSectCd.NEW_UNITY_MBR_JOIN.toString().equals(dto.getDtlBnefDetailSectCd())){
								dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.NEW_UNITY_MBR_JOIN.toString());	
							}else if(BnefDetailSectCd.MBR_JOIN_RECOMENDE_BNEF.toString().equals(dto.getDtlBnefDetailSectCd())){
								dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.MBR_JOIN_RECOMENDE_BNEF.toString());	
							}else if(BnefDetailSectCd.MBR_JOIN_RECOMENDR_BNEF.toString().equals(dto.getDtlBnefDetailSectCd())){
								dto.setDtlWebpntDetailResnCd(BnefDetailSectCd.MBR_JOIN_RECOMENDR_BNEF.toString());	
							}
						}
						if(BnefPymntTpCd.MBSH_PNT.toString().equals(dto.getDtlBnefTpCd())){
							if(BnefDetailSectCd.MOBILE_APP_DWLD_BNEF.toString().equals(dto.getDtlBnefDetailSectCd())){
								dto.setDtlPntAplResnCd(BnefDetailSectCd.MOBILE_APP_DWLD_BNEF.toString());							
							}else if(BnefDetailSectCd.NEW_MBR_JOIN.toString().equals(dto.getDtlBnefDetailSectCd())){
								dto.setDtlPntAplResnCd(BnefDetailSectCd.NEW_MBR_JOIN.toString());	
							}else if(BnefDetailSectCd.MARKT_RECPTN_AGR.toString().equals(dto.getDtlBnefDetailSectCd())){
								dto.setDtlPntAplResnCd(BnefDetailSectCd.MARKT_RECPTN_AGR.toString());	
							}else if(BnefDetailSectCd.NEW_UNITY_MBR_JOIN.toString().equals(dto.getDtlBnefDetailSectCd())){
								dto.setDtlPntAplResnCd(BnefDetailSectCd.NEW_UNITY_MBR_JOIN.toString());	
							}
						}						

						dto.setDtlUdterId(loginId);
						log.debug("MemberBenefitCommandService updateMemberBenefitDetail 2 :::::::::::::::::::: "+ dto.toString());
						memberBenefitApiCommandRepository.updateMemberBenefitDetail(dto);
					}
				}
			}	
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}	
    
	public void deleteMemberBenefitDetail(List<MemberBenefitBoDTO> list) {

		if(list != null && !list.isEmpty()) {
			for(MemberBenefitBoDTO dto : list){
				log.debug("MemberBenefitCommandService deleteMemberBenefitDetail :::::::::::::::::::: "+ dto.toString());				
				if("APRV_WAIT".equals(dto.getDtlAprvSectCd())){
					memberBenefitApiCommandRepository.deleteMemberBenefitDetail(dto);
				}				
			}
		}		
	}
	
	/**
	 * 회원혜택 상태 수정.
	 */
	public void updateMemberBenefitStatus(MemberBenefitBoDTO dto) {
		
		MemberBenefitBoDTO innerDto = new MemberBenefitBoDTO();
		final String loginId = BOSecurityUtil.getLoginId();	
		boolean validCheck = false;
		
		log.debug("MemberBenefitCommandService updateMemberBenefitStatus dto :::::::::::::::::::: "+ dto.toString());
		
		if(dto.getRowBnefSn() != null){
			
			if(dto.getAprvSectCd() != null && dto.getAprvSectCd() != ""){
				validCheck = true;
			}
			if(loginId != null && loginId != ""){
				validCheck = true;
			}
			
			if(validCheck){
				for(int i=0 ; i < dto.getRowBnefSn().length ; i++){
					innerDto.setAprvSectCd(dto.getAprvSectCd());
					innerDto.setUdterId(loginId);
					innerDto.setBnefSn(dto.getRowBnefSn()[i]);
					if("STPGE".equals(dto.getAprvSectCd())){
						innerDto.setStpgeResnCont(dto.getStpgeResnCont());
						innerDto.setStpgeAdminId(loginId);
					}else if("APRV".equals(dto.getAprvSectCd())){
						innerDto.setAprvAdminId(loginId);
					}

					log.debug("MemberBenefitCommandService updateMemberBenefitStatus innerDto :::::::::::::::::::: "+ innerDto.toString());
					memberBenefitApiCommandRepository.updateMemberBenefitStatus(innerDto);
				}				
			}			
		}		
	}
	
	/**
	 * 회원혜택 지급혜택 상태 수정.
	 */
	public void updateMemberBenefitDtlStatus(MemberBenefitBoDTO dto) {
		
		MemberBenefitBoDTO innerDto = new MemberBenefitBoDTO();
		final String loginId = BOSecurityUtil.getLoginId();	
		boolean validCheck = false;
		
		log.debug("MemberBenefitCommandService updateMemberBenefitDtlStatus dto ::::::::::::::::::: "+ dto.toString());
		
		if(dto.getDtlRowBnefSn() != null && dto.getDtlRowBnefDetailTurn() != null){
			
			if(dto.getDtlAprvSectCd() != null && dto.getDtlAprvSectCd() != ""){
				validCheck = true;
			}
			if(loginId != null && loginId != ""){
				validCheck = true;
			}
			
			if(validCheck){
				for(int i=0 ; i < dto.getDtlRowBnefSn().length ; i++){
					innerDto.setDtlAprvSectCd(dto.getDtlAprvSectCd());
					innerDto.setDtlUdterId(loginId);
					innerDto.setDtlBnefSn(dto.getDtlRowBnefSn()[i]);
					innerDto.setDtlBnefDetailTurn(dto.getDtlRowBnefDetailTurn()[i]);
					if("STPGE".equals(dto.getDtlAprvSectCd())){
						innerDto.setDtlStpgeResnCont(dto.getDtlStpgeResnCont());
						innerDto.setDtlStpgeAdminId(loginId);
					}else if("APRV".equals(dto.getDtlAprvSectCd())){
						innerDto.setDtlAprvAdminId(loginId);
					}

					log.debug("MemberBenefitCommandService updateMemberBenefitDtlStatus innerDto :::::::::::::::::::: "+ innerDto.toString());
					memberBenefitApiCommandRepository.updateMemberBenefitDtlStatus(innerDto);
				}
			}
		}		
	}	
}