package com.plgrim.ncp.biz.member.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefPymntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPntIntrlckHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.enums.WebPointEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum;
import com.plgrim.ncp.base.repository.mbr.MbrBnefPymntHistRepository;
import com.plgrim.ncp.base.repository.mbr.MbrPntIntrlckHistRepository;
import com.plgrim.ncp.biz.member.repository.MemberBenefitCommandRepository;
import com.plgrim.ncp.biz.member.repository.MemberBenefitSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberPersonalInfoCommandRepository;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원혜택정보 command service..
 */
@Service
@Slf4j
public class MemberBenefitCommandService extends AbstractService {
 
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
	
	/** IF 공통. */
	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	

	@Autowired
	private MemberBenefitSelectRepository memberBenefitSelectRepository;
	
	@Autowired
	private MemberBenefitCommandRepository memberBenefitCommandRepository;
	
	@Autowired
	private MbrPntIntrlckHistRepository mbrPntIntrlckHistRepository;
	
	/** 회원 혜택 지급 이력 Repository.	 */
	@Autowired
	private MbrBnefPymntHistRepository mbrBnefPymntHistRepository;

	@Autowired
	IdGenService idGenService;
	
	@Autowired
	MemberPersonalInfoCommandRepository memberPersonalInfoCommandRepository;
	


	/**
	 * 웹포인트 적립/차감
	 *
	 * @param param
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 7
	 */
	public void insertWebPoint(MbrWebpntHist param)  {
		insertWebPoint(param, null);
	}
	public void insertWebPoint(MbrWebpntHist param, SystemPK systemPK)  {

		param.setWebpntAmt(param.getWebpntAmt().abs()); /* 절대값처리 */

		MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
		BeanUtils.copyProperties(param, mbrWebpntHist);

		mbrWebpntHist.setWebpntTpCd(WebPointEnum.WEBPNT.toString());

		String mbrNo = mbrWebpntHist.getMbrNo();
		String webPntResnCd = mbrWebpntHist.getWebpntResnCd();
		String webPntDtlResnCd = mbrWebpntHist.getWebpntDetailResnCd();
		BigDecimal webPntAmt = mbrWebpntHist.getWebpntAmt();
		
		//mbrWebpntHist.setMallId(mbrWebpntHist.getMallId());

		// 적립
		if (webPntResnCd.equals(WebPointEnum.WebPntResnCd.EVT.toString()) // 이벤트
																			// -
																			// 전체
				|| webPntResnCd.equals(WebPointEnum.WebPntResnCd.BNEF_PYMNT.toString()) // 혜택 지급
																			// -
																			// 전체
				|| webPntDtlResnCd.equals(WebPointEnum.WebPntAdminDtlResnCd.ADMIN_ADIT.toString()) // 관리자조정
																									// -
																									// 관리자추가
				|| webPntDtlResnCd.equals(WebPointEnum.WebPntPchDtlResnCd.WEBPNT_ACCML.toString())) { // 구매
																										// -
																										// 적립
			/*
			 * 적립예정으로 사용하는 경우가 없음.
			 * 일단은 받아온 상태값이 없으면 적립확정으로 세팅하고 받아온 상태값이 있으면 있는 것으로 사용하도록 수정.
			 */
			/*
			// 사용유효기간에 따라 적립예정/적립확정 구분
			if (mbrWebpntHist.getUseBegDt() != null) {
				Date sDate = DateService.createDate(DateService.parseString(mbrWebpntHist.getUseBegDt(), "yyyyMMdd"),
						"yyyyMMdd");
				Date eDate = DateService.createDate(DateService.parseString(new Date(), "yyyyMMdd"), "yyyyMMdd");

				if (DateService.getDurationDate(sDate, eDate) >= 0) {
					mbrWebpntHist.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_DCSN.toString());
				} else {
					mbrWebpntHist.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_PREARNGE.toString());
				}
			} else {
				mbrWebpntHist.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_PREARNGE.toString());
			}
			*/
			// 입력되어 있는 상태값이 없는 경우
			if(mbrWebpntHist.getWebpntStatCd() == null || "".equals(mbrWebpntHist.getWebpntStatCd())) {
				mbrWebpntHist.setWebpntStatCd(WebPointEnum.WebPntStatCd.ACCML_DCSN.toString());
			}

			if (mbrWebpntHist.getWebpntDetailResnCd()
					.equals(WebPointEnum.WebPntPchDtlResnCd.PCH_CANCL_ACCML.toString())) {
				mbrWebpntHist.setCnclDt(new Date()); // 취소일시 세팅 (구매취소의 경우)
			}

			// 차감해야하는 코인이 있으면 잔여 코인을 미리 차감해준다.
			List<MbrWebpntHistExtend> upperWebPntList = new ArrayList<MbrWebpntHistExtend>();
			upperWebPntList = memberBenefitSelectRepository.listWebPointDdctList(mbrWebpntHist);
			for (MbrWebpntHistExtend upperWebPnt : upperWebPntList) {
				upperWebPnt.setUdterId(mbrWebpntHist.getUdterId());
				if(systemPK == null){
					upperWebPnt.setMallId(BOSecurityUtil.getAuthMall().get("mallId"));
				}else{
					upperWebPnt.setMallId(systemPK.getMall());
				}

				BigDecimal ddctAmt = BigDecimal.ZERO;
				ddctAmt = upperWebPnt.getRemndrWebpntAmt(); // 차감해야할 코인

				if (ddctAmt.abs().compareTo(webPntAmt) >= 0) { // 차감코인이 같거나 더
																// 많을때

					upperWebPnt.setRemndrWebpntAmt(ddctAmt.add(webPntAmt)); // 잔여포인트
																			// 세팅
					memberBenefitCommandRepository.updateWebPoint(upperWebPnt); // 잔여 차감코인
																	// 업데이트
					webPntAmt = BigDecimal.ZERO;
					break;

				} else { // 차감코인이 더 적을떄

					upperWebPnt.setRemndrWebpntAmt(BigDecimal.ZERO); // 잔여포인트 세팅
					memberBenefitCommandRepository.updateWebPoint(upperWebPnt); // 잔여 차감코인
																	// 업데이트
					webPntAmt = webPntAmt.add(ddctAmt);
				}
			}

			Long webpntSn = Long.parseLong(
					getIdGenService().generateDBNumber(sqlSession1, "SQ_MBR_WEBPNT_HIST", "", DatabaseType.ORACLE));
			mbrWebpntHist.setWebpntSn(webpntSn);
			mbrWebpntHist.setRemndrWebpntAmt(webPntAmt);
			//mbrWebpntHist.setMallId(mbrWebpntHist.getMallId());
			memberBenefitCommandRepository.insertWebPoint(mbrWebpntHist);

			// 구매취소로인한 사용코인 재적립
		} else if (webPntDtlResnCd.equals(WebPointEnum.WebPntPchDtlResnCd.PCH_CANCL_ACCML.toString())) {

			HashMap oriOrdGodInfo = memberBenefitSelectRepository.selectOriginOrdGodInfo(mbrWebpntHist);
			
			if(oriOrdGodInfo != null){
				BigDecimal ordGodTurn = (BigDecimal)oriOrdGodInfo.get("ORDGODTURN")    == null ? new BigDecimal(mbrWebpntHist.getOrdGodTurn().intValue()) : (BigDecimal)oriOrdGodInfo.get("ORDGODTURN");
				BigDecimal oriQtyTurn = (BigDecimal)oriOrdGodInfo.get("ORIQTYTURN")    == null ? new BigDecimal(mbrWebpntHist.getQtyTurn().intValue()) : (BigDecimal)oriOrdGodInfo.get("ORIQTYTURN");
				BigDecimal oriOrdGodTurn = (BigDecimal)oriOrdGodInfo.get("ORIORDGODTURN") == null ? new BigDecimal(mbrWebpntHist.getOrdGodTurn().intValue()) : (BigDecimal)oriOrdGodInfo.get("ORIORDGODTURN");

				if(ordGodTurn.intValue() != oriOrdGodTurn.intValue()){
					mbrWebpntHist.setOrdGodTurn(oriOrdGodTurn.intValue());
					mbrWebpntHist.setQtyTurn(oriQtyTurn.intValue());
				}
			}

			// 사용코인 목록 조회
			List<MbrWebpntHistExtend> upperWebPntList = new ArrayList<MbrWebpntHistExtend>();
			mbrWebpntHist.setMallId(mbrWebpntHist.getMallId());
			upperWebPntList = memberBenefitSelectRepository.listWebPointUseList(mbrWebpntHist);
			Long tmpSnOrg = null;
			BigDecimal tmpRemndrWebpntSum = BigDecimal.ZERO;
			for (MbrWebpntHistExtend upperWebPnt : upperWebPntList) {
				Long tmpSnNew = upperWebPnt.getWebpntSn();
				Long webpntSn = Long.parseLong(
						getIdGenService().generateDBNumber(sqlSession1, "SQ_MBR_WEBPNT_HIST", "", DatabaseType.ORACLE));
				mbrWebpntHist.setWebpntSn(webpntSn);
				mbrWebpntHist.setOrdNo(mbrWebpntHist.getOrdNo());
				mbrWebpntHist.setOrdGodTurn(mbrWebpntHist.getOrdGodTurn());
				mbrWebpntHist.setClmNo(mbrWebpntHist.getClmNo());
				mbrWebpntHist.setClmWrhsGodTurn(mbrWebpntHist.getClmWrhsGodTurn());
				mbrWebpntHist.setUpperWebpntSn(upperWebPnt.getWebpntSn()); // 상위
																			// 일련번호
																			// 세팅
				mbrWebpntHist.setUseBegDt(upperWebPnt.getUseBegDt()); // 상위사용시작일시
																		// 세팅
				mbrWebpntHist.setUseEndDt(upperWebPnt.getUseEndDt()); // 상위사용종료일시
																		// 세팅
				mbrWebpntHist.setWebpntAmt(upperWebPnt.getWebpntAmt()); // 복원코인
				mbrWebpntHist.setRemndrWebpntAmt(BigDecimal.ZERO);
				mbrWebpntHist.setCnclDt(new Date()); // 취소일시 세팅 (구매취소의 경우)
				// 잔여포인트 업데이트
				if (tmpSnNew.equals(tmpSnOrg)) {
					tmpRemndrWebpntSum = tmpRemndrWebpntSum
							.add(upperWebPnt.getOrgRemndrWebpntAmt().add(upperWebPnt.getWebpntAmt()));
				} else {
					tmpRemndrWebpntSum = upperWebPnt.getOrgRemndrWebpntAmt().add(upperWebPnt.getWebpntAmt());
				}
				MbrWebpntHist orgWebpnt = new MbrWebpntHist();
				orgWebpnt.setWebpntSn(upperWebPnt.getUpperWebpntSn());
				orgWebpnt.setRemndrWebpntAmt(tmpRemndrWebpntSum);
				orgWebpnt.setMbrNo(mbrNo);
				orgWebpnt.setUdterId(mbrWebpntHist.getUdterId());
				
				memberBenefitCommandRepository.updateWebPoint(orgWebpnt); // 상위코인의 잔여코인 복원
				
				memberBenefitCommandRepository.updateWebPointExtension(orgWebpnt);	// 사용기간 만료시 기간 연장
				
				memberBenefitCommandRepository.insertWebPoint(mbrWebpntHist);

				tmpSnOrg = upperWebPnt.getWebpntSn();
			}

			// 구매취소로인한 적립코인 취소
		} else if (webPntDtlResnCd.equals(WebPointEnum.WebPntPchDtlResnCd.PCH_CANCL_DDCT.toString())) {

			HashMap oriOrdGodInfo = memberBenefitSelectRepository.selectOriginOrdGodInfo(mbrWebpntHist);
			
			if(oriOrdGodInfo != null){
				BigDecimal ordGodTurn = (BigDecimal)oriOrdGodInfo.get("ORDGODTURN")    == null ? new BigDecimal(mbrWebpntHist.getOrdGodTurn().intValue()) : (BigDecimal)oriOrdGodInfo.get("ORDGODTURN");
				BigDecimal oriQtyTurn = (BigDecimal)oriOrdGodInfo.get("ORIQTYTURN")    == null ? new BigDecimal(mbrWebpntHist.getQtyTurn().intValue()) : (BigDecimal)oriOrdGodInfo.get("ORIQTYTURN");
				BigDecimal oriOrdGodTurn = (BigDecimal)oriOrdGodInfo.get("ORIORDGODTURN") == null ? new BigDecimal(mbrWebpntHist.getOrdGodTurn().intValue()) : (BigDecimal)oriOrdGodInfo.get("ORIORDGODTURN");

				if(ordGodTurn.intValue() != oriOrdGodTurn.intValue()){
					mbrWebpntHist.setOrdGodTurn(oriOrdGodTurn.intValue());
					mbrWebpntHist.setQtyTurn(oriQtyTurn.intValue());
				}
			}
			
			// 적립코인 목록 조회
			List<MbrWebpntHistExtend> upperWebPntList = new ArrayList<MbrWebpntHistExtend>();
			upperWebPntList = memberBenefitSelectRepository.listWebPointAccmlList(mbrWebpntHist);
			for (MbrWebpntHistExtend upperWebPnt : upperWebPntList) {
				Long webpntSn = Long.parseLong(
						getIdGenService().generateDBNumber(sqlSession1, "SQ_MBR_WEBPNT_HIST", "", DatabaseType.ORACLE));
				mbrWebpntHist.setWebpntSn(webpntSn);
				mbrWebpntHist.setOrdNo(mbrWebpntHist.getOrdNo());
				mbrWebpntHist.setOrdGodTurn(mbrWebpntHist.getOrdGodTurn());
				mbrWebpntHist.setQtyTurn(mbrWebpntHist.getQtyTurn());
				mbrWebpntHist.setClmNo(mbrWebpntHist.getClmNo());
				mbrWebpntHist.setClmWrhsGodTurn(mbrWebpntHist.getClmWrhsGodTurn());
				mbrWebpntHist.setUpperWebpntSn(upperWebPnt.getWebpntSn()); // 상위
																			// 일련번호
																			// 세팅
				mbrWebpntHist.setUseBegDt(upperWebPnt.getUseBegDt()); // 상위사용시작일시
																		// 세팅
				mbrWebpntHist.setUseEndDt(upperWebPnt.getUseEndDt()); // 상위사용종료일시
																		// 세팅
				// mbrWebpntHist.setWebpntAmt(upperWebPnt.getWebpntAmt()); //
				// 취소코인
				mbrWebpntHist.setRemndrWebpntAmt(BigDecimal.ZERO); // 잔여코인=0
				mbrWebpntHist.setCnclDt(new Date()); // 취소일시 세팅 (구매취소의 경우)

				// 잔여포인트 업데이트
				upperWebPnt.setRemndrWebpntAmt(upperWebPnt.getRemndrWebpntAmt().subtract(mbrWebpntHist.getWebpntAmt())); // 적립(예정)코인
				upperWebPnt.setUdterId(mbrWebpntHist.getUdterId());
				memberBenefitCommandRepository.updateWebPoint(upperWebPnt);

				memberBenefitCommandRepository.insertWebPoint(mbrWebpntHist);
			}
			// 차감, 사용
		} else {
			if (StringService.isEmpty(mbrWebpntHist.getWebpntStatCd())) {
				mbrWebpntHist.setWebpntStatCd(WebPointEnum.WebPntStatCd.USE.toString()); // 사용
			}

			MbrWebpntHist tmp = new MbrWebpntHist();
			tmp.setMbrNo(mbrWebpntHist.getMbrNo());
			if(mbrWebpntHist.getMallId() != null && !"".equals(mbrWebpntHist.getMallId())) {
				tmp.setMallId(mbrWebpntHist.getMallId());
			} else {
				tmp.setMallId("DXM");
			}
			

			// 잔여포인트 목록 조회
			List<MbrWebpntHistExtend> remndrWebPntList = new ArrayList<MbrWebpntHistExtend>();
			if (webPntDtlResnCd.equals(WebPointEnum.WebPntPchDtlResnCd.PCH_CANCL_DDCT.toString())) {
				remndrWebPntList = memberBenefitSelectRepository.listWebPointRemndrList(mbrWebpntHist);
			} else {
				remndrWebPntList = memberBenefitSelectRepository.listWebPointRemndrList(tmp);
			}

			for (MbrWebpntHist upperWebPnt : remndrWebPntList) {
				upperWebPnt.setUdterId(mbrWebpntHist.getUdterId());

				Long webpntSn = Long.parseLong(
						getIdGenService().generateDBNumber(sqlSession1, "SQ_MBR_WEBPNT_HIST", "", DatabaseType.ORACLE));
				mbrWebpntHist.setWebpntSn(webpntSn);
				mbrWebpntHist.setOrdNo(mbrWebpntHist.getOrdNo());
				mbrWebpntHist.setOrdGodTurn(mbrWebpntHist.getOrdGodTurn());
				mbrWebpntHist.setQtyTurn(mbrWebpntHist.getQtyTurn());
				mbrWebpntHist.setClmNo(mbrWebpntHist.getClmNo());
				mbrWebpntHist.setClmWrhsGodTurn(mbrWebpntHist.getClmWrhsGodTurn());
				mbrWebpntHist.setEvtNo(mbrWebpntHist.getEvtNo());
				mbrWebpntHist.setGodNo(mbrWebpntHist.getGodNo());
				mbrWebpntHist.setGodEvlTurn(mbrWebpntHist.getGodEvlTurn());
				mbrWebpntHist.setUpperWebpntSn(upperWebPnt.getWebpntSn()); // 상위
																			// 일련번호
																			// 세팅
				// 목록조회시 group by로 인한 오정렬로 인해, 사용할때는 유효기간 넣지 않음..
				// mbrWebpntHist.setUseBegDt(upperWebPnt.getUseBegDt()); //
				// 상위사용시작일시 세팅
				// mbrWebpntHist.setUseEndDt(upperWebPnt.getUseEndDt()); //
				// 상위사용종료일시 세팅
				mbrWebpntHist.setRemndrWebpntAmt(BigDecimal.ZERO);
				if (mbrWebpntHist.getWebpntDetailResnCd()
						.equals(WebPointEnum.WebPntPchDtlResnCd.PCH_CANCL_DDCT.toString())) {
					mbrWebpntHist.setCnclDt(new Date()); // 취소일시 세팅 (구매취소의 경우)
				}

				BigDecimal remnderAmt = BigDecimal.ZERO;
				remnderAmt = upperWebPnt.getRemndrWebpntAmt();

				if (remnderAmt.compareTo(webPntAmt) >= 0) { // 잔여포인트가 같거나 더 많을때

					// 잔여포인트 업데이트
					upperWebPnt.setRemndrWebpntAmt(remnderAmt.subtract(webPntAmt)); // 잔여포인트
																					// 세팅
					memberBenefitCommandRepository.updateWebPoint(upperWebPnt);

					// 차감 내역 등록
					mbrWebpntHist.setWebpntAmt(webPntAmt); // 차감포인트 세팅
					memberBenefitCommandRepository.insertWebPoint(mbrWebpntHist);

					webPntAmt = BigDecimal.ZERO;

					break;

				} else { // 잔여포인트가 더 적을떄

					// 잔여포인트 업데이트
					upperWebPnt.setRemndrWebpntAmt(BigDecimal.ZERO); // 잔여포인트 세팅
					memberBenefitCommandRepository.updateWebPoint(upperWebPnt);

					// 차감 내역 등록
					mbrWebpntHist.setWebpntAmt(remnderAmt); // 차감포인트 세팅
					memberBenefitCommandRepository.insertWebPoint(mbrWebpntHist);

					webPntAmt = webPntAmt.subtract(remnderAmt);
				}
			}

			// 차감해야하는 코인이 더 남았을때
			if (webPntAmt.compareTo(BigDecimal.ZERO) > 0) {
				Long webpntSn = Long.parseLong(
						getIdGenService().generateDBNumber(sqlSession1, "SQ_MBR_WEBPNT_HIST", "", DatabaseType.ORACLE));
				mbrWebpntHist.setWebpntSn(webpntSn);
				mbrWebpntHist.setUpperWebpntSn(null);
				mbrWebpntHist.setWebpntAmt(webPntAmt);
				mbrWebpntHist.setRemndrWebpntAmt(webPntAmt.multiply(new BigDecimal(-1)));
				memberBenefitCommandRepository.insertWebPoint(mbrWebpntHist);
			}
		}
	}
	
	/**
	 * 포인트 연동 이력 등록.
	 * 처리가 실패하여도 오류 발생하지 않음.
	 * 
	 * @param mpih [설명]
	 * @param loginId [설명]
	 * @since 2015. 4. 30
	 */
    public void insertMemberPointHistory(MbrPntIntrlckHist mpih, String loginId) {
    	try{
    		long pntSn = getIdGenService().generateDBSequence(sqlSession1, "sq_mbr_pnt_intrlck_hist", DatabaseType.ORACLE);
    		mpih.setPntSn(pntSn);
    		mpih.setUdterId(loginId);
    		mpih.setRegtrId(loginId);
    		
    		mbrPntIntrlckHistRepository.insertMbrPntIntrlckHist(mpih);

    	} catch(Exception e){
    		if(log.isWarnEnabled()) log.warn("> insertMemberPointHistory Exception : {}", e.getMessage());
    	}
	}
    
    /**
     * AdapterHeader 값 설정
     * @return
     */
    private AdapterHeader setAdapterHeader(){
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");
		
		return adapterHeader;
    }
    
    /**
     * AdapterHeader 값 설정
     * @return
     */
    private AdapterHeader setBOAdapterHeader(SystemPK systemPK){
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(systemPK.getMall());
		adapterHeader.setDvcCd(systemPK.getDevice());
		//adapterHeader.setMallId("DXM");
		//adapterHeader.setDvcCd("PC");
		adapterHeader.setLangCd("KOR");
		
		return adapterHeader;
    }
    
    /**
	 * 포인트 연동 이력 등록.
	 * 처리가 실패하여도 오류 발생하지 않음.
	 *
	 * @param mbrNo [설명]
	 * @since 2015. 4. 30
	 */
	public void insertMbrBnefPymntHist(String mbrNo, String bnefPymntTpCd, String prmNo)  {
		try{

			// 앱 다운로드 멤버십 포인트 혜택 이력 저장
			Map<String, Object> conditions = Maps.newHashMap();
			conditions.put("MBR_NO", mbrNo);
			MbrBnefPymntHist mbrBnefPymntHist = new MbrBnefPymntHist();

			HttpServletRequest request = ContextService.getCurrentRequest();
			SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);
			BigDecimal pntPymntAmt = new BigDecimal(10000);

			long time = System.currentTimeMillis();
			Date pymntDt = new Date(time);

			mbrBnefPymntHist.setMbrNo(mbrNo);
			mbrBnefPymntHist.setBnefPymntTpCd(bnefPymntTpCd);
			mbrBnefPymntHist.setBnefPymntTurn(getIdGenService().generateDBOrder(sqlSession1, "MBR_BNEF_PYMNT_HIST", "BNEF_PYMNT_TURN", conditions, DatabaseType.ORACLE));
			//mbrBnefPymntHist.setBnefSectCd("MOBILE_APP_DWLD_BNEF");
			mbrBnefPymntHist.setDvcCd(systemPK.getDevice());
			mbrBnefPymntHist.setLangCd(systemPK.getLang());
			mbrBnefPymntHist.setMallId(systemPK.getMall());
			mbrBnefPymntHist.setMobileAppSectCd(systemPK.getApp());
			if("MBSH_PNT".equals(bnefPymntTpCd)){
				mbrBnefPymntHist.setPntPymntAmt(pntPymntAmt);
			}else if("CPN".equals(bnefPymntTpCd)){
				mbrBnefPymntHist.setPrmNo(prmNo);
			}

			mbrBnefPymntHist.setUdterId(mbrNo);
			mbrBnefPymntHist.setRegtrId(mbrNo);
			mbrBnefPymntHist.setPymntDt(pymntDt);

			mbrBnefPymntHistRepository.insertMbrBnefPymntHist(mbrBnefPymntHist);
		} catch(Exception e){
			if(log.isWarnEnabled()) log.warn("> insertMbrBnefPymntHist Exception : {}", e.getMessage());
		}
	}
	
	/**
	 * 쿠폰 연동 이력 등록.
	 * 처리가 실패하여도 오류 발생하지 않음.
	 *
	 * @param mbrNo [설명]
	 * @since 2017. 2. 9
	 */
	public void insertSecessionMbrBnefPymntHist(MbrBnefPymntHist mbrBnefPymntHist, String mbrNo)  {
		try{
			// 앱 다운로드 멤버십 포인트 혜택 이력 저장
			Map<String, Object> conditions = Maps.newHashMap();
			conditions.put("MBR_NO", mbrNo);
			MbrBnefPymntHist insertDto = new MbrBnefPymntHist();

			HttpServletRequest request = ContextService.getCurrentRequest();
			SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);

			insertDto.setMbrNo(mbrNo);
			insertDto.setBnefPymntTpCd("CPN");
			insertDto.setBnefPymntTurn(getIdGenService().generateDBOrder(sqlSession1, "MBR_BNEF_PYMNT_HIST", "BNEF_PYMNT_TURN", conditions, DatabaseType.ORACLE));
			//insertDto.setBnefSectCd("MOBILE_APP_DWLD_BNEF");
			insertDto.setDvcCd(systemPK.getDevice());
			insertDto.setLangCd(systemPK.getLang());
			insertDto.setMallId(systemPK.getMall());
			insertDto.setMobileAppSectCd(systemPK.getApp());
			insertDto.setPrmNo(mbrBnefPymntHist.getPrmNo());
			insertDto.setUdterId(mbrNo);
			insertDto.setRegtrId(mbrNo);
			insertDto.setBfPymntMbrNo(mbrBnefPymntHist.getMbrNo());
			insertDto.setPymntDt(mbrBnefPymntHist.getPymntDt());

			mbrBnefPymntHistRepository.insertMbrBnefPymntHist(insertDto);
		} catch(Exception e){
			if(log.isWarnEnabled()) log.warn("> insertSecessionMbrBnefPymntHist Exception : {}", e.getMessage());
		}
	}
	
	/**
	 * 불량상품평 지급코인 즉시회수
	 * @param mbrWebpntHist
	 * @
	 */
	public void webPntImdtlRtrvl(MbrWebpntHist mbrWebpntHist)  {
		// 잔여포인트 목록 조회
		List<MbrWebpntHistExtend> upperWebPntList = memberBenefitSelectRepository.selectWebPointInfoByGodEvl(mbrWebpntHist);

		for(MbrWebpntHistExtend upperWebPnt : upperWebPntList){

			BigDecimal remnderAmt  = BigDecimal.ZERO;

			remnderAmt = upperWebPnt.getRemndrWebpntAmt();

			if(remnderAmt.compareTo(upperWebPnt.getWebpntAmt()) >= 0){ 		//	잔여포인트가 같거나 더 많을때

				// 잔여포인트 업데이트
				upperWebPnt.setRemndrWebpntAmt(remnderAmt.subtract(upperWebPnt.getRemndrWebpntAmt()));	// 잔여포인트 세팅
				upperWebPnt.setUdterId(mbrWebpntHist.getLoginId());
				memberBenefitCommandRepository.updateWebPoint(upperWebPnt);

				// 차감 내역 등록
				Long webpntSn = Long.parseLong(getIdGenService().generateDBNumber(sqlSession1, "SQ_MBR_WEBPNT_HIST", "", DatabaseType.ORACLE));
				mbrWebpntHist.setWebpntTpCd(WebPointEnum.WEBPNT.toString());
				mbrWebpntHist.setWebpntSn(webpntSn);						// 일련번호 세팅
				mbrWebpntHist.setUpperWebpntSn(upperWebPnt.getWebpntSn());	// 상위일련번호 세팅
				mbrWebpntHist.setGodNo(upperWebPnt.getGodNo());				// 상품번호
				mbrWebpntHist.setGodEvlTurn(upperWebPnt.getGodEvlTurn());	// 상품평번호
				mbrWebpntHist.setUseEndDt(upperWebPnt.getUseEndDt());		// 상위사용종료일시 세팅
				mbrWebpntHist.setWebpntAmt(upperWebPnt.getWebpntAmt());		// 차감포인트 세팅
				mbrWebpntHist.setRemndrWebpntAmt(upperWebPnt.getRemndrWebpntAmt());	// 잔여포인트 세팅
				mbrWebpntHist.setResnDscr("Badness Goods Review.");
				memberBenefitCommandRepository.insertWebPoint(mbrWebpntHist);

			}
		}
	}
	
	public void insertMbrRfdAcc(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		memberBenefitCommandRepository.insertMbrRfdAcc(mbrRfdBnkAcct);
	}
	
	// point 임시삭감(table)
//	public BpCbTemDelTableSDO processBpCbTemDelTable(MbrPntIntrlckHist mpih, List<BpCbTemDelListSDO> list, String mbrId) {
//
//		BpCbTemDelTableSDO result = new BpCbTemDelTableSDO();
//
//		return result;
//	}

	/**
	 * 회원 개인정보 변경 이력 등록 정보 설정.
	 *
	 * @param mbrNo       개인정보 변경 회원번호
	 * @param modResnDscr 개인정보 변경 사유
	 * @param loginId     시스템 접근자 ID (FRONT - mbr_no, BO/CS/PO - loginId(admin_id))
	 * @param isMember    변경주체의 회원 여부
	 * @return Mbr psnl info mod hist [설명]
	 * @ the exception
	 * @since 2015. 4. 16
	 */
	public MbrPsnlInfoModHist setMbrPsnlInfoModHist(String mbrNo, String modResnDscr, String loginId, boolean isMember) {

		long psnlInfoModHistSn = getIdGenService().generateDBSequence(sqlSession1, "sq_mbr_psnl_info_mod_hist", DatabaseType.ORACLE);

		MbrPsnlInfoModHist mpim = new MbrPsnlInfoModHist();
		mpim.setPsnlInfoModHistSn(psnlInfoModHistSn);              // 개인정보 변경 이력 일련번호
		mpim.setMbrNo(mbrNo);                                       // 회원번호
		mpim.setModResnDscr(modResnDscr);                         // 변경 사유 설명
		mpim.setErpTrnsmisYn("N");       // ERP 전송 여부
		mpim.setRegtrId(loginId);
		mpim.setUdterId(loginId);

		if (isMember) {
			mpim.setModMbrNo(loginId);                       // 변경 회원 번호
		} else {
			mpim.setModAdminId(loginId);                    // 변경 관리자 ID
		}

		return mpim;
	}

	/**
	 * 회원 상세 정보 변경 이력 등록.
	 *
	 * @param afterMbr [설명]
	 * @param mpim     [설명]
	 * @param codeArr  [설명]
	 * @param isReg    [설명]
	 * @ the exception
	 * @since 2015. 4. 16
	 */
	public void insertPersonalInfoForMbr(MbrPsnlInfoModHist mpim, String[] codeArr, Mbr beforeMbr, Mbr afterMbr) {
		try {
			// AFTER
			Map<String, Object> afterMap = makeAfterMap(afterMbr);

			// BEFORE
			Map<String, Object> beforeMap = makeAfterMap(beforeMbr);

			insertPersonalInfo(afterMap, beforeMap, mpim, codeArr);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 회원 정보 변경 이력 등록.
	 *
	 * @param afterMap  [설명]
	 * @param beforeMap [설명]
	 * @param mpim      [설명]
	 * @param codeArr   [설명]
	 * @ the exception
	 * @since 2015. 4. 16
	 */
	private void insertPersonalInfo(Map<String, Object> afterMap, Map<String, Object> beforeMap, MbrPsnlInfoModHist mpim, String[] codeArr) {
		String modBfVal = "";
		String modAfVal = "";

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("psnl_info_mod_hist_sn", mpim.getPsnlInfoModHistSn());
		int modTurn = 0;

		for (String codeName : codeArr) {
			modBfVal = this.makeCheckString(codeName, beforeMap);
			modAfVal = this.makeCheckString(codeName, afterMap);

			if (StringService.contains(codeName, "_NO") || StringService.contains(codeName, "_DATE") || StringService.contains(codeName, "MBR_BRTHDY")) {
				modBfVal = StringService.removeHyphen(modBfVal);
				modAfVal = StringService.removeHyphen(modAfVal);

			}

			if (modBfVal.equals(modAfVal)) {
				continue;
			}

			// 변경이력 값 설정
			modTurn = getIdGenService().generateDBOrder(sqlSession1, "mbr_psnl_info_mod_hist", "psnl_info_mod_hist_turn", conditions, DatabaseType.ORACLE);
			mpim.setPsnlInfoModHistTurn(modTurn);    // 개인정보 변경 이력 순번

			mpim.setPsnlInfoUdtSectCd(codeName);    // 개인정보 수정 구분 코드
			mpim.setModBfVal(modBfVal);            // 변경 이전 값
			mpim.setModAfVal(modAfVal);                // 변경 이후 값

			memberPersonalInfoCommandRepository.insertPersonalInfoModHistory(mpim);
		}
	}

	private String makeCheckString(String codeName, Map<String, Object> paramMap) {
		String returnStr = "";

		String[] colunms = StringService.split(MemberPersonalInfoEnum.valueOf(codeName).toString(), "|");

		for (String colunm : colunms) {
			returnStr += StringService.trimToEmpty((String) paramMap.get(colunm));
		}

		return returnStr;
	}
}
