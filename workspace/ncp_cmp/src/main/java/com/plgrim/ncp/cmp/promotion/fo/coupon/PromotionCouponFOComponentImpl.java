/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jw.vito.kim
 * @since       2015. 4. 30
 */
package com.plgrim.ncp.cmp.promotion.fo.coupon;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplTgt;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnCrtfcCd;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.CouponCrtfcCodeTypeCode;
import com.plgrim.ncp.base.enums.PromotionEnum.CouponIssueLimitReason;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionStat;
import com.plgrim.ncp.base.enums.SysInfoEnum.MallIdEnum;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.result.MbrIssuCpnResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;
import com.plgrim.ncp.biz.promotion.service.PromotionDetailService;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.biz.promotion.service.PromotionTargetService;
import com.plgrim.ncp.cmp.promotion.fo.PromotionCouponFOComponent;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponIssueResultDataSDO;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponIssueSDO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class PromotionCouponFOComponentImpl extends AbstractComponent implements PromotionCouponFOComponent {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
 
    @Autowired
    private PromotionService promotionService;
    
    @Autowired
    private PromotionDetailService promotionDetailService;

    @Autowired
    private PromotionTargetService promotionTargetService;
    
    @Autowired
    @Qualifier("sessionTemplate1")
    protected SqlSession sqlSession1;
    
    /*
     * ---------------------------------------------------------------------
     * Constructors.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * public & interface method.
     * ---------------------------------------------------------------------
     */

    /**
     * 사용 온라인 쿠폰 복원 (CS)
     *
     * @param mbrCpnNo
     * @throws Exception
     */
    @Override
    public boolean modifyCouponRevertStatus(String mbrCpnNo) throws Exception {

        boolean revert = true;

        if (!"".equals(mbrCpnNo) && mbrCpnNo != null) {

            MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();
            mbrIssuCpn.setMbrNo(mbrCpnNo);

            promotionService.updateCouponRevertStatus(mbrIssuCpn);
        }
        else {
            revert = false;
        }
        return revert;
    }

    

    

    /**
     * FO 사용 : 회원 가입 대상 온/오프 쿠폰 자동 발급 insert
     * 
     * 설명 : ERP에 온오프 쿠폰 발행되어 수신 등록 될 때 신온라인에 회원가입이 안되어 있거나 통합 회원이 아닌 사용자에게는 발급
     * 대기 상태로 남는다. 신오라인 통합 회원 전환 시 계정에 발급 해주는 로직. *
     * 
     * 사용자 값 셋팅 (필수) promotionBoDTO.getMbrIssuCpn().setMbrNo(value); - 회원번호
     * promotionBoDTO.setErpCstmrNo(value); - ERP 고객번호
     * 
     * 적용 대상 값 셋팅 (필수) promotionBoDTO.setMallId(value); - 몰ID
     * promotionBoDTO.setLang(value); - 언어 promotionBoDTO.setDevice(value); - 적용
     * 디바이스 promotionBoDTO.setAbsMbrTpCd(value); - 회원유형
     * promotionBoDTO.setAbsMbrAtrbCd(value); - 회원속성
     * promotionBoDTO.setAbsGepcoId(value); - 그룹사ID
     * 
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    @Override
    public void addOnoffCouponSignInMemberIssue(PromotionBoDTO promotionBoDTO) throws Exception {

        //회원 가입 대상 온/오프 쿠폰 보유 여부
        List<String> cpnList = promotionService.selectMemberOwnCouponCrtfcCd(promotionBoDTO);

        if (cpnList != null) {
            for (String cpnCrtfcCd : cpnList) {
                promotionBoDTO.getMbrIssuCpn().setCpnCrtfcCd(cpnCrtfcCd);

                //온오프 쿠폰 발급
                this.addCouponCrtfcCdMemberIssue(promotionBoDTO);
            }
        }
    }

    /**
     * FO 사용 : 일련번호(난수) 쿠폰 회원 발급 insert
     * 
     * 기본 값 셋팅 (필수)
     * 
     * - 일련번호로 발급 시 (입력한 개별난수[일련번호]로 발급)
     * promotionBoDTO.getMbrIssuCpn().setCpnCrtfcCd(value);
     * promotionBoDTO.getMbrIssuCpn().setMbrNo(value); or - 프로모션번호로 발급 시 (대표번호로
     * 발급) promotionBoDTO.getMbrIssuCpn().setPrmNo(value);
     * promotionBoDTO.getMbrIssuCpn().setMbrNo(value);
     * 
     * 적용 대상 값 셋팅 (필수) promotionBoDTO.setMallId(value); - 몰ID
     * promotionBoDTO.setLang(value); - 언어 promotionBoDTO.setDevice(value); - 적용
     * 디바이스 promotionBoDTO.setAbsMbrTpCd(value); - 회원유형
     * promotionBoDTO.setAbsMbrAtrbCd(value); - 회원속성
     * promotionBoDTO.setAbsGepcoId(value); - 그룹사ID
     * 
     * @param promotionBoDTO
     * @throws Exception
     */
    @Override
    public String addCouponCrtfcCdMemberIssue(PromotionBoDTO promotionBoDTO) throws Exception {

        String chkMsg = "";
        String cpnCrtfcCd = "";
        boolean onOffCouponFlag = false;	// 온오프라인 쿠폰 여부 true:온/오프라인 쿠폰, false:온라인 쿠폰

        //쿠폰 발급 방법이 인증(난수)코드 일때 - CRTFC_CD
        //온오프 쿠폰 : 원쿠폰 일때 인증(난수)코드 없이 프로모션 번호만 올 수도 있음
        if (StringService.isEmpty(promotionBoDTO.getMbrIssuCpn().getCpnCrtfcCd())) {
        	if(StringService.isEmpty(promotionBoDTO.getMbrIssuCpn().getPrmNo())){
                chkMsg = CouponIssueLimitReason.SN_NONINPUT.toString();
                return chkMsg;
            }
            else {
                cpnCrtfcCd = promotionService.selectCouponCrtfcCd(promotionBoDTO.getMbrIssuCpn());
                
                if(StringService.isNotEmpty(cpnCrtfcCd)){
                    promotionBoDTO.getMbrIssuCpn().setCpnCrtfcCd(cpnCrtfcCd);
                }
                else {
                    chkMsg = CouponIssueLimitReason.SN_EXST_NOT.toString();
                    return chkMsg;
                }
            }
        }
        else if(StringService.isEmpty(promotionBoDTO.getMbrIssuCpn().getMbrNo())){
            chkMsg = CouponIssueLimitReason.MBR_INFO_ABSNCE.toString();
            return chkMsg;
        }
        
        //#36494 대소문자 구분 안하도록 수정 BO 대문자/숫자 조합으로 수정됨
        promotionBoDTO.getMbrIssuCpn().setCpnCrtfcCd(promotionBoDTO.getMbrIssuCpn().getCpnCrtfcCd().toUpperCase());

        //쿠폰 사용 체크 : 일련번호(난수) 
        PromotionBoResult crtfcResult = promotionService.selectCouponCrtfcCdIssueUseCheck(promotionBoDTO.getMbrIssuCpn());

        //N:없는번호 인 경우 ERP에 발급이 가능한 쿠폰인지 확인
        if ("N".equals(crtfcResult.getMbrCpnUseChk())) {
        	OnOffCouponIssueSDO validSDO = null;
        	try {
        		// ERP 인터페이스 오류시 없는 쿠폰으로 처리.
        		validSDO = promotionService.validOnOffCouponForIssue(promotionBoDTO);
        	}
        	catch(Exception e) {
                StringWriter error = new StringWriter();
                e.printStackTrace(new PrintWriter(error));
                log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
        	}
        	
        	String validResult = "";
        	String resultMsg = "";
    		if (validSDO != null && validSDO.getResponseData() != null && !validSDO.getResponseData().isEmpty()) {
    			for (OnOffCouponIssueResultDataSDO sdo : validSDO.getResponseData()) {
  
    				if(sdo != null){
    					validResult = sdo.getResultCd();
    					resultMsg = sdo.getResultMsg();
    				}
    			}
    		}
    		if("10".equals(validResult) && StringService.isNotEmpty(resultMsg)) {
        		if(resultMsg.indexOf(promotionBoDTO.getMbr().getErpCstmrNo()) <1){
            		chkMsg = CouponIssueLimitReason.SN_EXST_NOT.toString();
                    return chkMsg;
        		}
        	}
    		
    		
        	// 00:발급완료, 01:발급가능, 10:기발급, 11:기사용, 20:미존재, 99:실패
    		if("11".equals(validResult)) {
        		chkMsg = CouponIssueLimitReason.PROSISSU_SN.toString();
                return chkMsg;
        	}
        	else if("20".equals(validResult) || "99".equals(validResult) || "".equals(validResult)) {
        		chkMsg = CouponIssueLimitReason.SN_EXST_NOT.toString();
                return chkMsg;
        	}
        	else  if("01".equals(validResult)|| "10".equals(validResult)) {
        		// 01. 온오프라인쿠폰 프로모션 정보 등록
            	try {
            		// 오류시 prmNo가 없음. prmNo가 없으면 PRM_CPN_CRTFC_CD 등록을 하지 않고 아래의 쿠폰 발급 대상 체크 검증에서 걸리게 됨.
            		// 해당 정보는 LIST로 오지만 1건만 들어있음.
            		// insertPromotionForOnOffCoupon 함수를 호출하기 전에 null 검증을 하므로 따로 하지 않음.
            		OnOffCouponIssueResultDataSDO validSdo = validSDO.getResponseData().get(0);
            		
                	// 01. 프로모션 정보 유무 확인
                	promotionBoDTO.getPrm().setErpCmpgId(validSdo.getCouponNo());
                	promotionBoDTO.getPrmAplTgt().setMallId(promotionBoDTO.getMallId());
                	String prmNo = promotionService.selectPrmNoByErpCmpgId(promotionBoDTO);
                	
                	// 02. 프로모션 정보 등록
                	if(prmNo == null || "".equals(prmNo)) {
                		// ERP에서 정보 조회
                    	OnOffCouponIssueSDO infoSDO = null;
                    	try {
                    		// ERP 인터페이스 오류시 관리자 문의로 처리.
                    		infoSDO = promotionService.getInfoForissueOnOffCoupon(promotionBoDTO);
                    	}
                    	catch(Exception e) {
                            StringWriter error = new StringWriter();
                            e.printStackTrace(new PrintWriter(error));
                            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
                    	}
                		
                		if (infoSDO != null && infoSDO.getResponseData() != null && !infoSDO.getResponseData().isEmpty()) {
                			// 해당 정보는 LIST로 오지만 1건만 들어있음.
                			OnOffCouponIssueResultDataSDO sdo = infoSDO.getResponseData().get(0);

                			String brand = "";
                			if(MallIdEnum.DXM_MALL_ID.toString().equals(promotionBoDTO.getMallId())) {
                				brand = "X";
                			}
                			else if(MallIdEnum.MLB_MALL_ID.toString().equals(promotionBoDTO.getMallId())) {
                				brand = "M";
                			}
                			else if(MallIdEnum.SA_MALL_ID.toString().equals(promotionBoDTO.getMallId())) {
                				brand = "A";
                			}
                			if(!brand.equals(sdo.getBrand())){
                        		chkMsg = CouponIssueLimitReason.SN_EXST_NOT.toString();
                                return chkMsg;
                			}
                			
                			
                			/** 프로모션 기본 저장 시작 **/
            	    		String prmNoGen = getIdGenService().generateDBNumber(sqlSession1, "SQ_PRM", "PR", DatabaseType.ORACLE);
            	    		Prm prm = promotionBoDTO.getPrm();
            	    		prm.setPrmNo(prmNoGen);
            	    		prm.setPrmTpCd(PromotionEnum.PromotionType.CPN.toString());
            	    		prm.setPrmDtlTpCd(PromotionEnum.PromotionCouponType.BSK_CPN.toString());	
            	    		prm.setRtlPrcAplYn(PromotionEnum.NO.toString());
            	    		prm.setPrmStatCd(PromotionStat.APRV_WAIT.toString());
            	    		prm.setPrmNm(sdo.getCpnNm());
            	    		prm.setErpCmpgId(sdo.getCouponNo());
            	    		prm.setErpCpnId(sdo.getCouponNo());
            	    		prm.setPrmBegDate(sdo.getUseStartDt().replaceAll("-", ""));
            	    		prm.setPrmEndDate(sdo.getUseEndDt().replaceAll("-", ""));
            	    		if("00".equals(sdo.getDcTpCd())) {
            	    			prm.setDcSectCd("FIXRT");
            	    			prm.setDcRt(new BigDecimal(sdo.getDcRt()));
            	    		}
            	    		else {
            	    			prm.setDcSectCd("FIXAMT");
            	    			prm.setDcAmt(new BigDecimal(sdo.getDcAmt()));
            	    		}
            	    		prm.setGodDcDplctCd("IMPS");
            	    		prm.setGodCpnDplctCd("PERM");
            	    		prm.setAplComSectCd("ALL");
            	    		prm.setRegtrId(promotionBoDTO.getMbrIssuCpn().getRegtrId());
            	    		prm.setUdterId(promotionBoDTO.getMbrIssuCpn().getUdterId());
            	    		promotionBoDTO.setPrm(prm);
            	    		promotionService.insertPromotion(promotionBoDTO);
            	    		/** 프로모션 기본 저장 종료 **/
            	
            	    		/** 프로모션 쿠폰 저장 시작 **/
            	    		PrmCpn prmCpn = promotionBoDTO.getPrmCpn();
            	    		prmCpn.setPrmNo(prm.getPrmNo());
            	    		prmCpn.setCpnKndCd("ONOFLNE_CPN");
            	    		if("FIXRT".equals(prm.getDcSectCd())) {
            	    			prmCpn.setOnoflneCpnOfrTpCd("DC_BIL");	
            	    		}
            	    		else {
            	    			prmCpn.setOnoflneCpnOfrTpCd("AMT_BIL");	
            	    		}
            	    		prmCpn.setCcRewardCpnYn(PromotionEnum.NO.toString());
            	    		if(sdo.getCpnUseMinPchAmt() == null) {
            	    			prmCpn.setCpnUseMinPchAmt(new BigDecimal(0));
            	    		}
            	    		else {
            	    			prmCpn.setCpnUseMinPchAmt(new BigDecimal(sdo.getCpnUseMinPchAmt()));
            	    		}
            	    		if(sdo.getCpnMaxDcPsbAmt() == null) {
            	    			prmCpn.setCpnMaxDcPsbAmt(new BigDecimal(0));
            	    		}
            	    		else {
            	    			prmCpn.setCpnMaxDcPsbAmt(new BigDecimal(sdo.getCpnMaxDcPsbAmt()));
            	    		}
            	    		prmCpn.setPubliQtyLmitCd(PromotionEnum.PromotionPubliQtyLmitCd.UNLMIT.toString());
            	    		prmCpn.setAllPubliQty(Long.parseLong("9999999999"));
            	    		prmCpn.setAllIssuInvQty(Long.parseLong("9999999999"));
            	    		prmCpn.setDplctIssuLmitCd(PromotionEnum.PromotionDplctIssuLmitCd.UNLMIT.toString());
            	    		prmCpn.setDplctIssuPsbQty(Long.parseLong("9999999999"));
            	    		prmCpn.setCpnIssuMthdCd(PromotionEnum.CouponIssueMethodCode.CRTFC_CD.toString());
            	    		prmCpn.setDwldCpnGodDetailExpsrYn(PromotionEnum.NO.toString());
            	    		prmCpn.setNaverEpCpnYn(PromotionEnum.NO.toString());
            	    		prmCpn.setCpnUsePdCd(PromotionEnum.CouponUsePeriodCode.PD_APPN.toString());
            	    		prmCpn.setCpnUsePdBegDate(sdo.getUseStartDt().replaceAll("-", ""));
            	    		prmCpn.setCpnUsePdEndDate(sdo.getUseEndDt().replaceAll("-", ""));
            	    		prmCpn.setCpnCrtfcCdTpCd(CouponCrtfcCodeTypeCode.IND_CRTFC_CD.toString());
            	    		prmCpn.setRegtrId(prm.getRegtrId());
            	    		prmCpn.setUdterId(prm.getUdterId());
            	    		promotionBoDTO.setPrmCpn(prmCpn);
            	    		promotionDetailService.insertPromotionCoupon(promotionBoDTO);
            	    		/** 프로모션 쿠폰 저장 종료 **/
            	    		
            	    		/** 프로모션 적용기간 저장 시작 **/
            	    		promotionBoDTO.setLoginId(prm.getUdterId());
            	    		promotionService.insertPromotionPeriod(promotionBoDTO);
            	    		/** 프로모션 적용기간 저장 종료 **/
            	    		
            	    		/** 프로모션 적용대상 저장 시작 **/
            	    		PrmAplTgt prmAplTgt = promotionBoDTO.getPrmAplTgt();
            	    		prmAplTgt.setMallId(promotionBoDTO.getMallId());
            	    		// DXM은 한국어, 영어만 사용
            	    		if(MallIdEnum.DXM_MALL_ID.toString().equals(promotionBoDTO.getMallId())) {
            	    			prmAplTgt.setLangCd("KOR"+PromotionEnum.PromotionSeparator.DELIMITER.toString()+"ENG");
            	    		}else{
            	    			prmAplTgt.setLangCd("KOR"+PromotionEnum.PromotionSeparator.DELIMITER.toString()+"ENG"+PromotionEnum.PromotionSeparator.DELIMITER.toString()+"CHI");
            	    		}
            	    		prmAplTgt.setTgtMbrAtrbCd("GNRL_MBR"+PromotionEnum.PromotionSeparator.DELIMITER.toString()+"EMP");
            	    		promotionBoDTO.setPrmAplTgt(prmAplTgt);
            	    		promotionTargetService.insertPromotionApplyTarget(sqlSession1, promotionBoDTO);
            	    		/** 프로모션 적용대상 저장 종료 **/
            	    		
            	    		/** 프로모션 적용상품 저장 시작 **/
            	    		promotionTargetService.insertOnOffPromotionApplyGoods(sqlSession1, prm, sdo);
            	    		/** 프로모션 적용상품 저장 종료 **/
            	    		
            	    		/** 프로모션 다국어 저장 시작 **/
            	    		// DXM은 한국어, 영어만 사용
            	    		promotionBoDTO.getPrmCpnEx().setCpnNmEng(prm.getPrmNm());
            	    		if(!MallIdEnum.DXM_MALL_ID.toString().equals(promotionBoDTO.getMallId())) {
            	    			promotionBoDTO.getPrmCpnEx().setCpnNmChi(prm.getPrmNm());
            	    		}
            	    		promotionService.insertPrmLang(promotionBoDTO);
            	    		/** 프로모션 다국어 저장 종료 **/
            	    		
            	    		/** 프로모션 승인 처리 시작 **/
            	    		promotionBoDTO.getPrm().setPrmStatCd(PromotionStat.APRV.toString());
            	    		promotionService.updatePromotionStatus(promotionBoDTO);
            	    		/** 프로모션 승인 처리 종료 **/
            	    		
            	    		crtfcResult.setPrmNo(prmNoGen);
            	    	}
                	}
                	else {
                		crtfcResult.setPrmNo(prmNo);
                	}
                	
                	crtfcResult.setMbrCpnUseChk("W");
 
            	}
            	catch(Exception e) {
                    StringWriter error = new StringWriter();
                    e.printStackTrace(new PrintWriter(error));
                    log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            	}
        		
        		// 02. 프로모션 쿠폰 인증 코드(PRM_CPN_CRTFC_CD) 등록
        		if(crtfcResult.getPrmNo() != null && !"".equals(crtfcResult.getPrmNo())) {
	            	PrmCpnCrtfcCd prmCpnCrtfcCd = new PrmCpnCrtfcCd();
	        		prmCpnCrtfcCd.setPrmNo(crtfcResult.getPrmNo());
	        		prmCpnCrtfcCd.setCpnCrtfcCd(promotionBoDTO.getMbrIssuCpn().getCpnCrtfcCd());
	        		prmCpnCrtfcCd.setChkNum(null);
	        		prmCpnCrtfcCd.setRegtrId(promotionBoDTO.getMbrIssuCpn().getRegtrId());
	        		prmCpnCrtfcCd.setUdterId(promotionBoDTO.getMbrIssuCpn().getUdterId());
	        		promotionService.insertCouponCrtfcCode(prmCpnCrtfcCd);
	        		
	        		onOffCouponFlag = true;
        		}
        	}
	    }

        //쿠폰 발급 대상 체크 - 몰, 디바이스, 언어, 회원유형, 회원속성, 그룹사ID
        promotionBoDTO.getMbrIssuCpn().setPrmNo(crtfcResult.getPrmNo());
        int issuAplTgtCnt = promotionService.selectCouponIssueTargetCheck(promotionBoDTO);

        if (issuAplTgtCnt < 1) { // 쿠폰 발급 대상이 아닌경우
        	//#38361,#38615 쿠폰 미발급 대상일경우 메세지 분기처리(디바이스)
        	//몰,언어,회원유형,회원속성(디바이스 조건에 우선함) - 등록할 수 없는 쿠폰번호 입니다. 확인 후 다시 입력해주세요.
            String cpnIssuTgtDvc = promotionService.selectCpnIssuTgtDvc(promotionBoDTO);
            
            if(StringService.isNotEmpty(cpnIssuTgtDvc)){
            	if("ETC".equals(cpnIssuTgtDvc)){							//디바이스 이외 조건인경우
            		chkMsg = CouponIssueLimitReason.SN_EXST_NOT.toString();
            	}else{
            		chkMsg = cpnIssuTgtDvc;									//디바이스 조건이 맞지 않는 경우
            	}
            	
            }else{
            	chkMsg = CouponIssueLimitReason.ADMIN_INQ.toString();
            }
            
            return chkMsg;
        }

        //N:없는번호, X:데이터오류, D:발급중복
        if ("N".equals(crtfcResult.getMbrCpnUseChk())) {
            chkMsg = CouponIssueLimitReason.SN_EXST_NOT.toString();
        }
        else if ("X".equals(crtfcResult.getMbrCpnUseChk())) {
            chkMsg = CouponIssueLimitReason.ADMIN_INQ.toString();
        }
        else {
            //체크용 변수/객체 설정 : 발급대상 회원 
            List<String> listMbrNo = new ArrayList<String>();
            listMbrNo.add(promotionBoDTO.getMbrIssuCpn().getMbrNo());

            promotionBoDTO.setListMbrNo(listMbrNo);

            //체크용 변수 설정 : 프로모션 정보
            promotionBoDTO.getPrm().setPrmNo(crtfcResult.getPrmNo());

            //체크용 변수 설정 : 회원 발급 쿠폰 정보
            promotionBoDTO.getMbrIssuCpn().setPrmNo(crtfcResult.getPrmNo());
            promotionBoDTO.getMbrIssuCpn().setCpnCrtfcCd(promotionBoDTO.getMbrIssuCpn().getCpnCrtfcCd());

            //체크용 변수 설정 : 쿠폰 할인 정보
            promotionBoDTO.getPrmCpn().setPrmNo(crtfcResult.getPrmNo());

            //쿠폰 회원 발급 가능 체크
            PromotionBoResult couponResult = promotionService.selectCouponIssueUseCheck(promotionBoDTO);

            //인증 코드 유형 : 개별인증코드
            //개별인증코드일 때 개별인증코드 중복 사용 체크 함.
            if (CouponCrtfcCodeTypeCode.IND_CRTFC_CD.toString().equals(couponResult.getPrmCpn().getCpnCrtfcCdTpCd())) {
                if ("D".equals(crtfcResult.getMbrCpnUseChk())) {
                    chkMsg = CouponIssueLimitReason.PROSISSU_SN.toString();
                    return chkMsg;
                }
            }

            // FO 에선 사용자가 일련번호 쿠폰 인증/등록 시
            // 쿠폰프로모션이 승인 상태에서만 발급 할 수 있음
            if (!PromotionStat.APRV.toString().equals(couponResult.getPrm().getPrmStatCd())) {
                chkMsg = CouponIssueLimitReason.ISMCBTW_OFF.toString();
                return chkMsg;
            }
            
            if (CouponIssueLimitReason.SUCCES.toString().equals(couponResult.getChkMsg())) {
                // 온오프라인 쿠폰이면 ERP에 발급 처리 요청.
                if(onOffCouponFlag == true) {
    	        	OnOffCouponIssueSDO issueSDO = null;
    	        	try {
    	        		// ERP 인터페이스 오류시 관리자 문의로 처리.
    	        		issueSDO = promotionService.issueOnOffCoupon(promotionBoDTO);
    	        	}
    	        	catch(Exception e) {
    	                StringWriter error = new StringWriter();
    	                e.printStackTrace(new PrintWriter(error));
    	                log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
    	        	}
    	        	String issueResult = "";
    	    		if (issueSDO != null && issueSDO.getResponseData() != null && !issueSDO.getResponseData().isEmpty()) {
    	    			for (OnOffCouponIssueResultDataSDO sdo : issueSDO.getResponseData()) {
    	    				issueResult = sdo.getResultCd();
    	    			}
    	    		}
    	        	// 00:발급완료, 01:발급가능, 10:기발급, 11:기사용, 20:미존재, 99:실패
    	    		if("11".equals(issueResult)) {
    	        		chkMsg = CouponIssueLimitReason.PROSISSU_SN.toString();
                        return chkMsg;
    	        	}
    	        	else if("20".equals(issueResult)) {
    	        		chkMsg = CouponIssueLimitReason.SN_EXST_NOT.toString();
                        return chkMsg;
    	        	}
    	        	else if("99".equals(issueResult) || "".equals(issueResult)) {
    	        		chkMsg = CouponIssueLimitReason.ADMIN_INQ.toString();
    	        		return chkMsg;
    	        	}
                }

                //회원별 쿠폰 발급 처리 후 mbrIssuCpn결과 값 return
                List<MbrIssuCpnResult> couponIssueResult = promotionService.selectCouponIssueCheckResult(promotionBoDTO,
                        couponResult.getPrmCpn());

                if (couponIssueResult != null) {
                    chkMsg = couponIssueResult.get(0).getRstCd();
                }
                else {
                    chkMsg = CouponIssueLimitReason.ADMIN_INQ.toString();
                }
            }
            else {
                chkMsg = couponResult.getChkMsg();
            }
        }

        return chkMsg;
    }

 
    /**
     * FO 사용 : (다운로드) 쿠폰 회원 발급 insert
     * 
     * 기본 값 셋팅 (필수) promotionBoDTO.getMbrIssuCpn().setPrmNo(value);
     * promotionBoDTO.getMbrIssuCpn().setMbrNo(value);
     * 
     * 적용 대상 값 셋팅 (필수) promotionBoDTO.setMallId(value); - 몰ID
     * promotionBoDTO.setLang(value); - 언어 promotionBoDTO.setDevice(value); - 적용
     * 디바이스 promotionBoDTO.setAbsMbrTpCd(value); - 회원유형
     * promotionBoDTO.setAbsMbrAtrbCd(value); - 회원속성
     * promotionBoDTO.setAbsGepcoId(value); - 그룹사ID
     * 
     * @param promotionBoDTO
     * @throws Exception
     */
    @Override
    public String addCouponDownMemberIssue(PromotionBoDTO promotionBoDTO) throws Exception {

        String chkMsg = "";
        //필수 입력 값 체크
        if (promotionBoDTO.getMbrIssuCpn().getPrmNo() == "" || promotionBoDTO.getMbrIssuCpn().getPrmNo() == null
                || promotionBoDTO.getMbrIssuCpn().getMbrNo() == "" || promotionBoDTO.getMbrIssuCpn().getMbrNo() == null) {
            chkMsg = CouponIssueLimitReason.MBR_INFO_ABSNCE.toString();
            return chkMsg;
        }

        //체크용 변수/객체 설정 : 발급대상 회원
        List<String> listMbrNo = new ArrayList<String>();
        listMbrNo.add(promotionBoDTO.getMbrIssuCpn().getMbrNo());

        promotionBoDTO.setListMbrNo(listMbrNo);

        //체크용 변수 설정 : 프로모션 정보
        promotionBoDTO.getPrm().setPrmNo(promotionBoDTO.getMbrIssuCpn().getPrmNo());

        //체크용 변수 설정 : 회원 발급 쿠폰 정보
        promotionBoDTO.getMbrIssuCpn().setPrmNo(promotionBoDTO.getMbrIssuCpn().getPrmNo());

        //체크용 변수 설정 : 쿠폰 할인 정보
        promotionBoDTO.getPrmCpn().setPrmNo(promotionBoDTO.getMbrIssuCpn().getPrmNo());

        //쿠폰 발급 대상 체크 - 몰, 디바이스, 언어, 회원유형, 회원속성, 그룹사ID
        promotionBoDTO.getMbrIssuCpn().setPrmNo(promotionBoDTO.getMbrIssuCpn().getPrmNo());
        int issuAplTgtCnt = promotionService.selectCouponIssueTargetCheck(promotionBoDTO);

        if (issuAplTgtCnt < 1) {
            //chkMsg = "쿠폰 발급 대상 아님.";
            chkMsg = CouponIssueLimitReason.NONISSU_TGT.toString();
            return chkMsg;
        }

        //쿠폰 회원 발급 가능 체크
        PromotionBoResult couponResult = promotionService.selectCouponIssueUseCheck(promotionBoDTO);

        if (CouponIssueLimitReason.SUCCES.toString().equals(couponResult.getChkMsg())) {
            //회원별 쿠폰 발급 처리 후 mbrIssuCpn결과 값 return
            List<MbrIssuCpnResult> couponIssueResult = promotionService.selectCouponIssueCheckResult(promotionBoDTO, couponResult.getPrmCpn());

            if (couponIssueResult != null) {
                chkMsg = couponIssueResult.get(0).getRstCd();
            }
            else {
                chkMsg = CouponIssueLimitReason.ADMIN_INQ.toString();
            }
        }
        else {
            chkMsg = couponResult.getChkMsg();
        }
        
        log.debug("addCouponDownMemberIssue 쿠폰발급결과 mbrNo: {} chkMsg: {}", promotionBoDTO.getMbrIssuCpn().getMbrNo() , chkMsg);
        return chkMsg;
    }

    @Override
    public PrmExtend selectValidPromtCpnDown(Prm prm) throws Exception {
        PrmExtend prmEx = promotionService.selectValidPromtCpnDown(prm);
        return prmEx;
    }

	/**
     * (회원) 쿠폰 발급 Count
     */
    @Override
	public int selectCouponIssueCount(MbrIssuCpn mbrIssuCpn) throws Exception {
        return promotionService.selectCouponIssueCount(mbrIssuCpn);
    }
	
}
