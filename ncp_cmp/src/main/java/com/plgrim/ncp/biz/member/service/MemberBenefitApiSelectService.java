package com.plgrim.ncp.biz.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnef;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefAplTgt;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefDetail;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.MemberEnum.MemberAtrbCd;
import com.plgrim.ncp.biz.member.data.MbrBnefDetailExtend;
import com.plgrim.ncp.biz.member.data.MemberBenefitBoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.repository.MemberBenefitApiSelectRepository;
import com.plgrim.ncp.biz.member.result.MemberBenefitAplTgtGrpCdResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitBoResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitPymntHistResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitResult;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.interfaces.order.data.OrderFormerlyPurchaseCfmInfoResultSDO;
import com.plgrim.ncp.interfaces.order.data.OrderFormerlyPurchaseCfmInfoSDO;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원혜택API select service 
 */
@Service 
@Slf4j
public class MemberBenefitApiSelectService extends AbstractService {
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
	
	/** IF 공통. */
	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	
	@Autowired
	MemberBenefitCommandService memberBenefitCommandService;
	
	@Autowired
	private MemberBenefitApiSelectRepository memberBenefitApiSelectRepository;
	
	@Autowired
	private MemberActivityFOComponent memberActivityFOComponent;
	
	/**
	 * 회원 혜택 로그인 여부 체크
	 */
	public MemberBenefitResult chkMemberBenefitLogin(SystemPK systemPK)  {
		MemberBenefitResult result = new MemberBenefitResult();
		
		//1. 로그인 여부 조회
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			result.setMbr(userDetail.getMbr());
		}
		return result;
	}
	
	/**
	 * 회원 혜택 조회
	 */
	public List<MbrBnefDetailExtend> getMemberBenefitList(MemberBenefitEnum.BnefSectCd bnefSectCd)  {	
		//회원 혜택정보 DB 조회
		return memberBenefitApiSelectRepository.selectMemberBenefitList(bnefSectCd.toString());
	}
	
	public List<MbrBnefDetailExtend> getMemberBenefitListAll()  {	
		//회원 혜택정보 DB 조회
		return memberBenefitApiSelectRepository.selectMemberBenefitListAll();
	}
	
	/**
	 * 회원 혜택 대상여부 validation 확인 처리
	 */
	public List<MemberBenefitResult> validateMemberBenefit(SystemPK systemPK, List<MbrBnefDetailExtend> mbrBnefDetailList, Mbr mbr, String bnefSectCd, MbrGrd mbrGrd)  {
		List<MemberBenefitResult> resultList = new ArrayList<MemberBenefitResult>();
		
		for(int i= 0; i < mbrBnefDetailList.size(); i++) {
 			MbrBnefDetailExtend mbrBnefDetailExtend = mbrBnefDetailList.get(i);
 			MemberBenefitResult memberBenefitResult = new MemberBenefitResult();
 			memberBenefitResult.setMbrBnefDetailExtend(mbrBnefDetailExtend);
 			Long bnefSn = memberBenefitResult.getMbrBnefDetailExtend().getBnefSn();
 			
 			try {
 				//1.systemPK 와 mbrBnefDetail 회원 혜택조건이 해당하는지 비교 후 해당하는 경우 memberBenefitResultList 에 추가
 				MemberBenefitResult returnResult = validateMemberBenefitAplTarget(systemPK, mbr, bnefSn, bnefSectCd, mbrBnefDetailExtend, mbrGrd);
 				
 				if(returnResult.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())) {
 					//1.1. 회원혜택 지급 대상인 경우
 					memberBenefitResult.setResultCd(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage());
 					memberBenefitResult.setResultMsg(getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage()));
 				} else {
 					//1.2. 회원혜택 지급 대상이 아닌 경우
 					if(returnResult.getResultCd().equals(CommonResponseCode.PM_00016_유효하지_않은_디바이스.toMessage())){
 						memberBenefitResult.setResultCd(CommonResponseCode.PM_00016_유효하지_않은_디바이스.toMessage());
 						memberBenefitResult.setResultMsg(getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00016_유효하지_않은_디바이스.toMessage()));
 					}else if(returnResult.getResultCd().equals(CommonResponseCode.PM_00018_유효하지_않은_몰.toMessage())){
 						memberBenefitResult.setResultCd(CommonResponseCode.PM_00018_유효하지_않은_몰.toMessage());
 						memberBenefitResult.setResultMsg(getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00018_유효하지_않은_몰.toMessage()));
 					}else if(returnResult.getResultCd().equals(CommonResponseCode.PM_00020_유효하지_않은_LANG.toMessage())){
 						memberBenefitResult.setResultMsg(getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00020_유효하지_않은_LANG.toMessage()));
 						memberBenefitResult.setResultCd(CommonResponseCode.PM_00020_유효하지_않은_LANG.toMessage());
 					}else if(returnResult.getResultCd().equals(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage())){
 						memberBenefitResult.setResultMsg(getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage()));
 						memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
 					}else if(returnResult.getResultCd().equals(CommonResponseCode.PM_00019_유효하지_않은_APP.toMessage())){
 						memberBenefitResult.setResultMsg(getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_00019_유효하지_않은_APP.toMessage()));
 						memberBenefitResult.setResultCd(CommonResponseCode.PM_00019_유효하지_않은_APP.toMessage());
 					}
 				}
 			} catch(Exception ex) {
 				//2. 회원혜택 지급 실패한 경우 처리
 				log.warn("", ex);
 				memberBenefitResult.setResultCd(CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage());
 				memberBenefitResult.setResultMsg(getResultMsg(systemPK, mbr, memberBenefitResult, CommonResponseCode.PM_40008_회원_혜택_지급_오류.toMessage()));
 				memberBenefitResult.setResultException(ex);
 			}
 			
 			resultList.add(memberBenefitResult);
 		}

		return resultList;
	}
	
	/**
	 * 회원 혜택 적용 대상 validation 확인
	 */
	public MemberBenefitResult validateMemberBenefitAplTarget(SystemPK systemPK, Mbr mbr, Long bnefSn, String bnefSectCd, MbrBnefDetailExtend mbrBnefDetailExtend, MbrGrd mbrGrd)  {
		MemberBenefitResult memberBenefitResult = new MemberBenefitResult();
		memberBenefitResult.setResultCd(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage());
		
		// 회원 혜택 대상 공통 validation
		MemberBenefitResult returnResult = validateMemberBenefitCommon(systemPK, mbr);
		
		if(!returnResult.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())){
			return returnResult;
		}
		
		MbrBnef mbrBnef = new MbrBnef();
			
		mbrBnef.setBnefSn(bnefSn);
		
		//1.회원 혜택 적용 대상 조회
		List<MbrBnefAplTgt> aplTgtList = memberBenefitApiSelectRepository.selectMemberBenefitTargetList(mbrBnef); 
		
		//2.회원 혜택 지급 대상 비교
		for(int j= 0; j < aplTgtList.size(); j++){
			MbrBnefAplTgt mbrBnefAplTgt = aplTgtList.get(j);
			MemberBenefitResult result = compareBnefAplTarget(systemPK, mbr, mbrBnefAplTgt);
			if(!result.getResultCd().equals(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage())){
				return result;
			}
		}
		
		//3.회원 혜택 구분별 대상 비교
		// 온라인 회원 등급 변경, 등급별 생일 혜택
		if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.ONLNE_GRD_MOD.toString())) {	

			if(mbrGrd == null) {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());				
				return memberBenefitResult;
			}
			else if(!(mbrGrd.getOnlneGrdCd()).equals(mbrBnefDetailExtend.getBnefDetailSectCd())) {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
				return memberBenefitResult;
			}
			
		}
		else if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.GRDBY_BRTHDY_BNEF.toString())){
			//등급확인은 DXM만 함
			if(systemPK.getMall().equals("DXM")){
				if(mbrGrd == null) {
					memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());				
					return memberBenefitResult;
				}
				else if(!(mbrGrd.getOnlneGrdCd()).equals(mbrBnefDetailExtend.getBnefDetailSectCd())) {
					memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());				
					return memberBenefitResult;
				}
			}else{
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage());
			}
		}
		// 임직원 혜택
		else if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.EMP_BNEF.toString())) {
			if(!mbr.getMbrAtrbCd().equals(MemberEnum.MemberAtrbCd.EMP.toString())) {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
				return memberBenefitResult;
			}
		}
		// 회원 첫구매 혜택
		else if(bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.MBR_BEG_PCH_BNEF.toString())) {
			OrderFormerlyPurchaseCfmInfoSDO sdo = new OrderFormerlyPurchaseCfmInfoSDO();
			sdo.setCid(mbr.getErpCstmrNo());
			sdo.setBrand("X");
			
			OrderFormerlyPurchaseCfmInfoSDO purchaseInfo = memberActivityFOComponent.formerlyPurchaseConfirmInfo(systemPK, sdo);
			
			if (purchaseInfo != null && purchaseInfo.getResponseData().isEmpty() == false) {
				for(OrderFormerlyPurchaseCfmInfoResultSDO resultSdo : purchaseInfo.getResponseData()) {
					if("Y".equals(resultSdo.getPurchaseConfirmFlag())) {
						memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
						return memberBenefitResult;	
					}
				}
			}
			else {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
				return memberBenefitResult;	
			}
		}
		
		/*
		 *   온라인 등급 변경 && 온오프라인쿠폰이 아닌 경우 중복 체크
		 *   회원혜택지급이력에 최근등급선정일(MLB,SA) or 최근등급변경일(DX) 이후 발급 이력이 있으면 해당 건 제외
		 *   온라인쿠폰, 포인트 둘다 중복 체크 되어야함
		 */
		if (bnefSectCd.equals(MemberBenefitEnum.BnefSectCd.ONLNE_GRD_MOD.toString())
				&& !mbrBnefDetailExtend.getBnefTpCd().equals(MemberBenefitEnum.BnefPymntTpCd.ONOFLNE_CPN.toString())) {
			MbrBnefDetailExtend bnef = new MbrBnefDetailExtend();
			bnef.setLoginId(mbr.getMbrNo());
			bnef.setMallId(systemPK.getMall());
			bnef.setPrmNo(mbrBnefDetailExtend.getPrmNo());
			bnef.setBnefTpCd(mbrBnefDetailExtend.getBnefTpCd());
			bnef.setBnefDetailSectCd(mbrBnefDetailExtend.getBnefDetailSectCd());
			
			int cnt = memberBenefitApiSelectRepository.selectMemberBenefitGrdCount(bnef);
			if (cnt > 0) {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00022_이전에_해당_혜택을_받음.toMessage());				
				return memberBenefitResult;
			}
		}
		
		return memberBenefitResult;
	}
	
	/**
	 * 회원혜택 지급 대상 유형 비교
	 */
	public MemberBenefitResult compareBnefAplTarget(SystemPK systemPK, Mbr mbr, MbrBnefAplTgt mbrBnefAplTgt)  {
		MemberBenefitResult memberBenefitResult = new MemberBenefitResult();
		memberBenefitResult.setResultCd(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage());
		String bnefTgtTpCd = mbrBnefAplTgt.getBnefTgtTpCd();
		
//		if(bnefTgtTpCd.equals(MemberBenefitEnum.BnefTgtTpCd.MALL_ID.toString())) {
//			memberBenefitResult = compareTarget(systemPK.getMall(), mbrBnefAplTgt.getMallId(), MemberBenefitEnum.BnefTgtTpCd.MALL_ID);
//		} else if( bnefTgtTpCd.equals(MemberBenefitEnum.BnefTgtTpCd.LANG.toString())) {
//			memberBenefitResult = compareTarget(systemPK.getLang(), mbrBnefAplTgt.getLangCd(), MemberBenefitEnum.BnefTgtTpCd.LANG);
//		} else if( bnefTgtTpCd.equals(MemberBenefitEnum.BnefTgtTpCd.DVC.toString())) {
//			memberBenefitResult = compareTarget(systemPK.getDevice(), mbrBnefAplTgt.getDvcCd(), MemberBenefitEnum.BnefTgtTpCd.DVC);
//		}
		
		//회원이 통합되어 운영되므로 mallID는 여기서 비교하지 않음
		if( bnefTgtTpCd.equals(MemberBenefitEnum.BnefTgtTpCd.LANG.toString())) {
			memberBenefitResult = compareTarget(systemPK.getLang(), mbrBnefAplTgt.getLangCd(), MemberBenefitEnum.BnefTgtTpCd.LANG);
		} else if( bnefTgtTpCd.equals(MemberBenefitEnum.BnefTgtTpCd.DVC.toString())) {
			memberBenefitResult = compareTarget(systemPK.getDevice(), mbrBnefAplTgt.getDvcCd(), MemberBenefitEnum.BnefTgtTpCd.DVC);
		}
		
		/* 2018.09.03
		 * userAgent에서 받는 값과 코드에서 사용하는 값이 달라서 APP의 종류는 가리지 않고 APP이 맞는지만 확인하는 것으로 수정.
		 * validateMemberBenefitCommon 이 함수에서 APP의 여부는 확인하므로 아래의 로직은 주석처리.
		 */
//		else if( bnefTgtTpCd.equals(MemberBenefitEnum.BnefTgtTpCd.MOBILE_APP_SECT.toString())) {
//			if(MemberBenefitEnum.DvcCd.MOBILE_APP.toString().equals(systemPK.getDevice())){
//				memberBenefitResult = compareTarget(systemPK.getApp(), mbrBnefAplTgt.getMobileAppSectCd(), MemberBenefitEnum.BnefTgtTpCd.MOBILE_APP_SECT);
//			}
//		}
		else if( bnefTgtTpCd.equals(MemberBenefitEnum.BnefTgtTpCd.TGT_MBR_TP.toString())) {
			memberBenefitResult = compareTarget(mbr.getMbrTpCd(), mbrBnefAplTgt.getTgtMbrTpCd(), MemberBenefitEnum.BnefTgtTpCd.TGT_MBR_TP);
		} else if( bnefTgtTpCd.equals(MemberBenefitEnum.BnefTgtTpCd.TGT_MBR_ATRB.toString())) {
			memberBenefitResult = compareTarget(mbr.getMbrAtrbCd(), mbrBnefAplTgt.getTgtMbrAtrbCd(), MemberBenefitEnum.BnefTgtTpCd.TGT_MBR_ATRB);
		} else if( bnefTgtTpCd.equals(MemberBenefitEnum.BnefTgtTpCd.GRPCO_ID.toString())) {
			if(MemberAtrbCd.EMP.toString().equals(mbr.getMbrAtrbCd())){
				memberBenefitResult = compareTarget(mbr.getPsitnGrpcoId(), mbrBnefAplTgt.getGrpcoId(), MemberBenefitEnum.BnefTgtTpCd.GRPCO_ID);
			}
		} else if( bnefTgtTpCd.equals(MemberBenefitEnum.BnefTgtTpCd.CHNNL_RECPTN_AGR_SECT.toString())) {
			memberBenefitResult = compareChnnlRecptnAgrTarget(mbr, mbrBnefAplTgt.getChnnlRecptnAgrSectCd());
		}
		return memberBenefitResult;
	}
	
	/**
	 * 채널 수신 동의 대상 비교
	 */
	public MemberBenefitResult compareChnnlRecptnAgrTarget(Mbr mbr, String targetStr)  {
		MemberBenefitResult memberBenefitResult = new MemberBenefitResult();
		memberBenefitResult.setResultCd(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage());
		boolean isTarget = false;
		
		if(StringService.isNotEmpty(targetStr)){
			String[] arrTarget = StringService.split(targetStr, "|");
			int agreeCnt = 0;
			if(arrTarget != null) {
				for(int i=0; i<arrTarget.length;i++){
					if(MemberBenefitEnum.ChnnlRecptnAgrSectCd.EMAIL_RECPTN_AGR.toString().equals(arrTarget[i])){
						if(MemberBenefitEnum.YES.toString().equals(mbr.getEmailRecptnAgrYn())){
							agreeCnt++;
						}
					} else if(MemberBenefitEnum.ChnnlRecptnAgrSectCd.SMS_RECPTN_AGR.toString().equals(arrTarget[i])) {
						if(MemberBenefitEnum.YES.toString().equals(mbr.getSmsRecptnAgrYn())){
							agreeCnt++;
						}
					} else if(MemberBenefitEnum.ChnnlRecptnAgrSectCd.DM_RECPTN_AGR.toString().equals(arrTarget[i])) {
						if(MemberBenefitEnum.YES.toString().equals(mbr.getDmRecptnAgrYn())){
							agreeCnt++;
						}
					} else if(MemberBenefitEnum.ChnnlRecptnAgrSectCd.TM_RECPTN_AGR.toString().equals(arrTarget[i])) {
						if(MemberBenefitEnum.YES.toString().equals(mbr.getTmRecptnAgrYn())){
							agreeCnt++;
						}
					}
				}
				
				if(agreeCnt == arrTarget.length) {
					isTarget = true;
				}
			}
		}
		if(!isTarget){
			memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
		}
		return memberBenefitResult;
	}
	
	/**
	 * 대상 비교
	 */
	public MemberBenefitResult compareTarget(String tmpStr, String targetStr, MemberBenefitEnum.BnefTgtTpCd bnefTgtTpCd)  {
		MemberBenefitResult memberBenefitResult = new MemberBenefitResult();
		memberBenefitResult.setResultCd(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage());
		boolean isTarget = false;
		
		if(StringService.isNotEmpty(targetStr)){
			String[] arrTarget = StringService.split(targetStr, "|");
			for(int i=0; i<arrTarget.length;i++){
				if(tmpStr.equals(arrTarget[i])){
					isTarget = true;
					break;
				}
			}
		}
		
		if(!isTarget){
			if(bnefTgtTpCd.toString().equals(MemberBenefitEnum.BnefTgtTpCd.MALL_ID.toString())) {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00018_유효하지_않은_몰.toMessage());
			} else if( bnefTgtTpCd.toString().equals(MemberBenefitEnum.BnefTgtTpCd.LANG.toString())) {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00020_유효하지_않은_LANG.toMessage());
			} else if( bnefTgtTpCd.toString().equals(MemberBenefitEnum.BnefTgtTpCd.DVC.toString())) {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00016_유효하지_않은_디바이스.toMessage());
			} else if( bnefTgtTpCd.toString().equals(MemberBenefitEnum.BnefTgtTpCd.MOBILE_APP_SECT.toString())) {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00019_유효하지_않은_APP.toMessage());
			} else if( bnefTgtTpCd.toString().equals(MemberBenefitEnum.BnefTgtTpCd.TGT_MBR_TP.toString())) {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
			} else if( bnefTgtTpCd.toString().equals(MemberBenefitEnum.BnefTgtTpCd.TGT_MBR_ATRB.toString())) {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
			} else if( bnefTgtTpCd.toString().equals(MemberBenefitEnum.BnefTgtTpCd.GRPCO_ID.toString())) {
				memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
			}
		}
		return memberBenefitResult;
	}
	
	/**
	 * 회원 혜택 대상 공통 validation 확인
	 */
	public MemberBenefitResult validateMemberBenefitCommon(SystemPK systemPK, Mbr mbr)  {
		MemberBenefitResult memberBenefitResult = new MemberBenefitResult();
		memberBenefitResult.setResultCd(CommonResponseCode.PM_00015_회원_혜택_지급_성공.toMessage());
		
		//시스템정보 부재
		if(systemPK == null){
			memberBenefitResult.setResultCd(CommonResponseCode.PM_00018_유효하지_않은_몰.toMessage());
			return memberBenefitResult;
		} else if(StringService.isEmpty(systemPK.getMall())) {
			memberBenefitResult.setResultCd(CommonResponseCode.PM_00018_유효하지_않은_몰.toMessage());
			return memberBenefitResult;
		} else if(StringService.isEmpty(systemPK.getLang())) {
			memberBenefitResult.setResultCd(CommonResponseCode.PM_00020_유효하지_않은_LANG.toMessage());
			return memberBenefitResult;
		} else if(StringService.isEmpty(systemPK.getDevice())) {
			memberBenefitResult.setResultCd(CommonResponseCode.PM_00016_유효하지_않은_디바이스.toMessage());
			return memberBenefitResult;
		} else if( MemberBenefitEnum.DvcCd.MOBILE_APP.toString().equals(systemPK.getDevice())
			&& StringService.isEmpty(systemPK.getApp())) {
			memberBenefitResult.setResultCd(CommonResponseCode.PM_00019_유효하지_않은_APP.toMessage());
			return memberBenefitResult;
		}
		
		//회원정보 부재
		if(mbr == null || StringService.isEmpty(mbr.getMbrNo())){
			memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
			return memberBenefitResult;
		} else if(StringService.isEmpty(mbr.getMbrTpCd())){
			memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
			return memberBenefitResult;
		} else if(StringService.isEmpty(mbr.getMbrAtrbCd())){
			memberBenefitResult.setResultCd(CommonResponseCode.PM_00017_유효하지_않은_회원.toMessage());
			return memberBenefitResult;
		} else if(StringService.isEmpty(mbr.getErpCstmrNo())){
			memberBenefitResult.setResultCd(CommonResponseCode.PM_00021_유효하지_않은_ERP_조건.toMessage());
			return memberBenefitResult;
		}
		
		return memberBenefitResult;
	}
	
	/**
	 * 회원혜택 API 결과 메시지
	 */
	public String getResultMsg(SystemPK systemPK, Mbr mbr, MemberBenefitResult memberBenefitResult, String commonMsg) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(commonMsg + " : ");
		
		if(memberBenefitResult != null && memberBenefitResult.getMbrBnefDetailExtend() != null){
			if(memberBenefitResult.getMbrBnefDetailExtend().getBnefSn() != null)	
				sb.append(memberBenefitResult.getMbrBnefDetailExtend().getBnefSn());
			/*
			if(memberBenefitResult.getMbrBnefDetail().getBnefSectCd() != null) 
				sb.append(",").append(memberBenefitResult.getMbrBnefDetail().getBnefSectCd());
			if(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd() != null)
				sb.append(",").append(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd());
			*/
			if(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd() != null)
				sb.append(",").append(memberBenefitResult.getMbrBnefDetailExtend().getBnefTpCd());
			if(memberBenefitResult.getMbrBnefDetailExtend().getPntAplResnCd() != null)
				sb.append(",").append(memberBenefitResult.getMbrBnefDetailExtend().getPntAplResnCd());
			if(memberBenefitResult.getMbrBnefDetailExtend().getPntAmt() != null)
				sb.append(",").append(memberBenefitResult.getMbrBnefDetailExtend().getPntAmt());
			if(memberBenefitResult.getMbrBnefDetailExtend().getPymntResnDscr() != null)
				sb.append(",").append(memberBenefitResult.getMbrBnefDetailExtend().getPymntResnDscr());
			if(memberBenefitResult.getMbrBnefDetailExtend().getWebpntResnCd() != null)
				sb.append(",").append(memberBenefitResult.getMbrBnefDetailExtend().getWebpntResnCd());
			if(memberBenefitResult.getMbrBnefDetailExtend().getWebpntDetailResnCd() != null)
				sb.append(",").append(memberBenefitResult.getMbrBnefDetailExtend().getWebpntDetailResnCd());
			if(memberBenefitResult.getMbrBnefDetailExtend().getWebpntAmt() != null)
				sb.append(",").append(memberBenefitResult.getMbrBnefDetailExtend().getWebpntAmt());
			if(memberBenefitResult.getMbrBnefDetailExtend().getWebpntValidDaycnt() != null)
				sb.append(",").append(memberBenefitResult.getMbrBnefDetailExtend().getWebpntValidDaycnt());
			if(memberBenefitResult.getMbrBnefDetailExtend().getPrmNo() != null)
				sb.append(",").append(memberBenefitResult.getMbrBnefDetailExtend().getPrmNo());
		}
		if(mbr != null){
			sb.append(",").append(mbr.getMbrNo());
		}
		return sb.toString();
	}
	
	/**
	 * 회원혜택 공통 로그 메시지
	 */
	public String getLogMsg(MemberBenefitResult memberBenefitResult, String flow) {
		String rtnStr = "";
		/*
		switch (flow) {
		case "START" :
			if(BnefDetailSectCd.MOBILE_APP_DWLD_BNEF.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_00002_모바일_앱다운로드쿠폰_혜택대상.toMessage();
			} else if(BnefDetailSectCd.NEW_MBR_JOIN.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_00004_온라인_신규가입쿠폰_혜택대상.toMessage();
			} else if(BnefDetailSectCd.MARKT_RECPTN_AGR.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_00006_마케팅_수신동의쿠폰_혜택대상.toMessage();
			} else if(BnefDetailSectCd.NEW_UNITY_MBR_JOIN.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_00008_멤버쉽_전환쿠폰_혜택대상.toMessage();
			} else if(BnefDetailSectCd.MBR_SIZE_REG.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
			    rtnStr = CommonResponseCode.PM_00027_사이즈등록혜택_혜택대상.toMessage();
            } else {
				rtnStr = memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd();
			}
			break;
		case "SUCCESS" :
			if(BnefDetailSectCd.MOBILE_APP_DWLD_BNEF.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_00003_모바일_앱다운로드쿠폰_지급완료.toMessage();
			} else if(BnefDetailSectCd.NEW_MBR_JOIN.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_00005_온라인_신규가입쿠폰_지급완료.toMessage();
			} else if(BnefDetailSectCd.MARKT_RECPTN_AGR.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_00007_마케팅_수신동의쿠폰_지급완료.toMessage();
			} else if(BnefDetailSectCd.NEW_UNITY_MBR_JOIN.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_00009_멤버쉽_전환쿠폰_지급완료.toMessage();
            } else if(BnefDetailSectCd.MBR_SIZE_REG.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
                rtnStr = CommonResponseCode.PM_00028_사이즈등록혜택_지급완료.toMessage();
            } else {
				rtnStr = memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd();
			}
			break;
		case "ERROR" :
			if(BnefDetailSectCd.MOBILE_APP_DWLD_BNEF.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_40002_모바일_앱다운로드쿠폰_미지급.toMessage();
			} else if(BnefDetailSectCd.NEW_MBR_JOIN.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_40003_온라인_신규가입쿠폰_미지급.toMessage();
			} else if(BnefDetailSectCd.MARKT_RECPTN_AGR.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_40004_마케팅_수신동의쿠폰_미지급.toMessage();
			} else if(BnefDetailSectCd.NEW_UNITY_MBR_JOIN.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
				rtnStr = CommonResponseCode.PM_40005_멤버쉽_전환쿠폰_미지급.toMessage();
            } else if(BnefDetailSectCd.MBR_SIZE_REG.toString().equals(memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd())){
                rtnStr = CommonResponseCode.PM_40011_사이즈등록혜택_미지급.toMessage();
            } else {
				rtnStr = memberBenefitResult.getMbrBnefDetail().getBnefDetailSectCd();
			}
			break;
		default:
			break;
		}
		*/
		return rtnStr;
	}
	
	/**
	 * 회원혜택 목록 건수 조회.
	 */
	public long selectMemberBenefitListCount(MemberBenefitBoDTO dto) {
		return memberBenefitApiSelectRepository.selectMemberBenefitListCount(dto);
	}

	/**
	 * 회원혜택 목록 조회.
	 */
	public List<MemberBenefitBoResult> selectMemberBenefitList(MemberBenefitBoDTO dto) {
		RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam());	// 페이지 설정
		return memberBenefitApiSelectRepository.selectMemberBenefitList(dto);
	}
	
	/**
	 * 회원혜택 상세조회.
	 */	
	public MemberBenefitBoResult selectMemberBenefitDetail(MemberBenefitBoDTO dto) {
		return memberBenefitApiSelectRepository.selectMemberBenefitDetail(dto);
	}
	
	/**
	 * 회원혜택 지급 혜택 목록 조회.
	 */	
	public Page<MemberBenefitBoResult> selectMemberBenefitDetailList(MemberBenefitBoDTO dto, PageParam pageParam) {
		return memberBenefitApiSelectRepository.selectMemberBenefitDetailList(dto, pageParam);
	}	
	
	/**
	 * 회원혜택 상세 수정이력 목록 조회.
	 */	
	public Page<MemberBenefitBoResult> selectMemberBenefitDetailHistList(MemberBenefitBoDTO dto, PageParam pageParam) {
		return memberBenefitApiSelectRepository.selectMemberBenefitDetailHistList(dto, pageParam);
	}		

	
	/**
	 * 회원혜택 상세 발급내역 목록 조회.
	 */	
	public Page<MemberBenefitPymntHistResult> selectMemberBenefitPymntHistList(MemberBenefitBoDTO dto, PageParam pageParam) {
		return memberBenefitApiSelectRepository.selectMemberBenefitPymntHistList(dto, pageParam);
	}
	
	/**
	 * 회원혜택 상세 그룹사 목록 조회.
	 */	
	public Page<MemberBenefitAplTgtGrpCdResult> selectMbrBnefAplTgtGrpCdList(MemberBenefitBoDTO dto, PageParam pageParam) {
		return memberBenefitApiSelectRepository.selectMbrBnefAplTgtGrpCdList(dto, pageParam);
	}	
	
	/**
	 * 회원혜택 중복체크.
	 */	
	public int checkMemberBenefitKey(MemberBenefitBoDTO dto) {
		return memberBenefitApiSelectRepository.checkMemberBenefitKey(dto);
	}
	
	/**
	 * 회원혜택 엑셀 목록 조회.
	 */	
	public List<Map<String, Object>> selectMemberBenefitExcelList(MemberBenefitBoDTO dto) {
		return memberBenefitApiSelectRepository.selectMemberBenefitExcelList(dto);
	}	
	
	/**
	 * 회원혜택 엑셀 수정이력 조회.
	 */	
	public List<Map<String, Object>> selectMemberBenefitDetailHistExcelList(MemberBenefitBoDTO dto) {
		return memberBenefitApiSelectRepository.selectMemberBenefitDetailHistExcelList(dto);
	}	
	
	/**
	 * 회원혜택 상세 엑셀 발급내역 조회.
	 */	
	public List<Map<String, Object>> selectMemberBenefitPymntHistExcelList(MemberBenefitBoDTO dto) {
		return memberBenefitApiSelectRepository.selectMemberBenefitPymntHistExcelList(dto);
	}
	
	/**
	 * 회원혜택 지급혜택 중복체크. 
	 */	
	public int checkMemberBenefitDetailDup(MemberBenefitBoDTO dto) {
		return memberBenefitApiSelectRepository.checkMemberBenefitDetailDup(dto);
	}	
	
	/**
	 * 회원 웹 적립금 건수 Count
	 */	
	public long selectMemberWebPointListCnt(MbrWebpntHist mbrWebpntHist) {
		return memberBenefitApiSelectRepository.selectMemberWebPointListCnt(mbrWebpntHist);
	}
	
	/**
     * 회원 혜택 단건 조회
     */
    public MbrBnefDetail selectMemberBenefitInfo(MbrBnefDetail mbrBnefDetail) {
        return memberBenefitApiSelectRepository.selectMemberBenefitInfo(mbrBnefDetail);
    }
}