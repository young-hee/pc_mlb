package com.plgrim.ncp.cmp.promotion.bo.coupon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnCrtfcCd;
import com.plgrim.ncp.base.enums.PromotionEnum.CouponCrtfcCodeTypeCode;
import com.plgrim.ncp.base.enums.PromotionEnum.CouponIssueLimitReason;
import com.plgrim.ncp.base.enums.PromotionEnum.CouponUsePeriodCode;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionStat;
import com.plgrim.ncp.biz.promotion.data.CouponPromotionDTO;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.exception.PromotionNotExistException;
import com.plgrim.ncp.biz.promotion.exception.PromotionStoppageException;
import com.plgrim.ncp.biz.promotion.result.CouponPromotionResult;
import com.plgrim.ncp.biz.promotion.result.MbrIssuCpnResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;
import com.plgrim.ncp.biz.promotion.service.PromotionDetailService;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.biz.promotion.service.PromotionTargetService;
import com.plgrim.ncp.cmp.promotion.bo.PromotionCouponBOComponent;
import com.plgrim.ncp.cmp.promotion.bo.PromotionBOComponentImpl;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class PromotionCouponBOComponentImpl extends PromotionBOComponentImpl implements PromotionCouponBOComponent{

	@Autowired
    private PromotionDetailService promotionDetailService;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
    private PromotionTargetService promotionTargetService;
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.CouponPromotionComponent#selectCouponPromotionList(com.plgrim.ncp.biz.promotion.data.CouponPromotionDTO, com.plgrim.ncp.framework.page.PageParam)
	 */
	@Override
    public Page<CouponPromotionResult> selectCouponPromotionList(CouponPromotionDTO searchDTO, PageParam pageParam) throws Exception {
        Page<CouponPromotionResult> resultList = promotionDetailService.selectCouponPromotionList(searchDTO, pageParam);
        return resultList;
    }

	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.CouponPromotionComponent#modifyCouponStopStatus(java.util.List)
	 */
    @Override
    public void modifyCouponStopStatus(List<PromotionBoDTO> updateList) throws Exception {
        promotionService.updateCouponStopStatus(updateList);
    }
    
    @Override
    public String addCouponMemberIssue(PromotionBoDTO promotionBoDTO) throws Exception {

        String chkMsg = "";

        String loginId = BOSecurityUtil.getLoginId();

        promotionBoDTO.getMbrIssuCpn().setPrmNo(promotionBoDTO.getPrm().getPrmNo());
        promotionBoDTO.getMbrIssuCpn().setIssuAdminId(loginId);	//DB CONSTRAINT R_534 확인
        promotionBoDTO.getMbrIssuCpn().setRegtrId(loginId);
        promotionBoDTO.getMbrIssuCpn().setUdterId(loginId);

        promotionBoDTO.getPrmCpn().setPrmNo(promotionBoDTO.getPrm().getPrmNo());
        promotionBoDTO.getPrmCpn().setUdterId(loginId);
        promotionBoDTO.getPrmCpn().setRegtrId(loginId);

        //쿠폰 회원 발급 가능 체크
        PromotionBoResult couponResult = promotionService.selectCouponIssueUseCheck(promotionBoDTO);

        if (CouponIssueLimitReason.SUCCES.toString().equals(couponResult.getChkMsg())) {
            //회원별 쿠폰 발급 처리 후 mbrIssuCpn결과 값 return
            List<MbrIssuCpnResult> couponIssueResult = promotionService.selectCouponIssueCheckResult(promotionBoDTO,
                    couponResult.getPrmCpn());

            for (MbrIssuCpnResult info : couponIssueResult) {
                if (CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString().equals(info.getRstCd())) {
                    chkMsg = CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString();
                }
            }

            if (CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString().equals(chkMsg)) {
                //발급 쿠폰 수 : n개
                if (couponIssueResult.size() > 1) {
                    chkMsg = CouponIssueLimitReason.SUCCES.toString() + "+" + chkMsg;
                }
            }
            else {
                //발급 쿠폰 수 : 1개
                chkMsg = CouponIssueLimitReason.SUCCES.toString();
            }
        }
        else {
            chkMsg = couponResult.getChkMsg();
        }
        return chkMsg;
    }
    
    @Override
    public List<MbrIssuCpnResult> addCouponMemberIssueExcel(List<MbrIssuCpn> list, String prmNo) throws Exception {

        List<MbrIssuCpnResult> errList = new ArrayList<MbrIssuCpnResult>();
        MbrIssuCpnResult result = null;

        if (list != null) {

            String loginId = BOSecurityUtil.getLoginId();

            PromotionBoDTO promotionBoDTO = new PromotionBoDTO();

            //체크용 변수/객체 설정 : 발급대상 회원 
            List<String> listMbrNo = new ArrayList<String>();
            for (MbrIssuCpn mbrIssuCpn : list) {
                listMbrNo.add(mbrIssuCpn.getMbrNo());
            }
            promotionBoDTO.setListMbrNo(listMbrNo);

            //체크용 변수 설정 : 프로모션 정보
            promotionBoDTO.getPrm().setPrmNo(prmNo);

            //체크용 변수 설정 : 회원 발급 쿠폰 정보
            promotionBoDTO.getMbrIssuCpn().setPrmNo(prmNo);
            promotionBoDTO.getMbrIssuCpn().setIssuAdminId(loginId);	//DB CONSTRAINT R_534 확인
            promotionBoDTO.getMbrIssuCpn().setRegtrId(loginId);
            promotionBoDTO.getMbrIssuCpn().setUdterId(loginId);

            //체크용 변수 설정 : 쿠폰 할인 정보
            promotionBoDTO.getPrmCpn().setPrmNo(prmNo);
            promotionBoDTO.getPrmCpn().setUdterId(loginId);
            promotionBoDTO.getPrmCpn().setRegtrId(loginId);

            //쿠폰 프로모션 전체 발급 가능 체크
            PromotionBoResult couponResult = promotionService.selectCouponIssueUseCheck(promotionBoDTO);

            String lang = "KOR";

            if (CouponIssueLimitReason.SUCCES.toString().equals(couponResult.getChkMsg())) {
                //회원별 쿠폰 발급 처리 후 mbrIssuCpn결과 값 return
                List<MbrIssuCpnResult> couponIssueResult = promotionService.selectCouponIssueCheckResult(promotionBoDTO,
                        couponResult.getPrmCpn());

                for (MbrIssuCpnResult info : couponIssueResult) {
                    if (!CouponIssueLimitReason.SUCCES.toString().equals(info.getRstCd())) {
                        info.setErrMsg(CodeUtil.getCode(lang, info.getRstCd()).getCdNm());
                        errList.add(info);
                    }
                }
            }
            else {
                /**
                 * 쿠폰 프로모션 발급 상태가 아닌 경우 (총개수, 발급기간, 프로모션상태 체크)
                 */
                for (MbrIssuCpn mbrIssuCpn : list) {
                    result = new MbrIssuCpnResult();

                    result.setMbrNo(mbrIssuCpn.getMbrNo());
                    result.setErrMsg(CodeUtil.getCode(lang, couponResult.getChkMsg()).getCdNm());
                    errList.add(result);
                }
            }
        }
        return errList;
    }
    
    @Override
    public void addCouponCrtfcCdIssue(PrmCpn prmCpn) throws Exception {

        String crtfcCdFirst = getCurrentDateTime().substring(2); //YYMMDD
        int issueCount = Integer.parseInt(prmCpn.getAllPubliQty() + "");

        List<String> crtfcCdList = IdGenService.generateCouponId(crtfcCdFirst, issueCount);

        PrmCpnCrtfcCd prmCpnCrtfcCd = new PrmCpnCrtfcCd();

        for (int i = 0; i < crtfcCdList.size(); i++) {

            prmCpnCrtfcCd.setPrmNo(prmCpn.getPrmNo());
            prmCpnCrtfcCd.setCpnCrtfcCd(crtfcCdList.get(i));
            prmCpnCrtfcCd.setRegtrId(prmCpn.getRegtrId());
            prmCpnCrtfcCd.setUdterId(prmCpn.getUdterId());

            promotionService.insertCouponCrtfcCdIssue(prmCpnCrtfcCd);
        }

        //쿠폰(인증코드) 발급 유형 코드 update
        prmCpn.setCpnCrtfcCdTpCd(CouponCrtfcCodeTypeCode.IND_CRTFC_CD.toString());
        promotionService.updateCouponCrtfcCdTp(prmCpn);
    }
    
    @Override
    public void addCouponSingleCrtfcCdIssue(PrmCpnCrtfcCd prmCpnCrtfcCd) throws Exception {

        promotionService.insertCouponCrtfcCdIssue(prmCpnCrtfcCd);

        PrmCpn prmCpn = new PrmCpn();
        prmCpn.setPrmNo(prmCpnCrtfcCd.getPrmNo());
        prmCpn.setCpnCrtfcCdTpCd(CouponCrtfcCodeTypeCode.RPRST_CRTFC_CD.toString());
        prmCpn.setUdterId(prmCpnCrtfcCd.getUdterId());

        //쿠폰(인증코드) 발급 유형 코드 update
        promotionService.updateCouponCrtfcCdTp(prmCpn);
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#mergeCouponNmMlang(com.plgrim.ncp.biz.promotion.data.PromotionBoDTO)
     */
    @Override
    public void mergeCouponNmMlang(PromotionBoDTO promotionBoDTO) throws Exception {
    	promotionService.mergeCouponNmMlang(promotionBoDTO);    	
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#insertOnOffCouponIssue(com.plgrim.ncp.biz.promotion.result.CouponPromotionResult)
     */
    @Override
	public String insertOnOffCouponIssue(CouponPromotionResult promotion) throws Exception {
    	log.info("InsertOnOffCouponIssue_Start. param : {}", promotion);
    	try {
    		String result = "";
    		// TODO 온오프 쿠폰발행
    		
    		log.info("InsertOnOffCouponIssue_Complete. result : {}", result);
    		return result;
    	} catch (Exception e) {
    		log.error("InsertOnOffCouponIssue_Fail. ", e);
    		throw e;
    	}
	}
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#updateOnOffPromotion(com.plgrim.ncp.biz.promotion.data.PromotionBoDTO)
     */
	@Override
	public int updateOnOffPromotion(PromotionBoDTO promotionBoDTO) throws Exception {
    	int result = 0;
    	
        PrmExtend validPrm = promotionService.selectValidPromotionInfo(promotionBoDTO.getPrm());

        if (validPrm.getPrmCount() == 0) {
            // 존재하지 않는 프로모션입니다.
            throw new PromotionNotExistException(null);
        }
        
        if (PromotionStat.APRV_WAIT.toString().equals(validPrm.getPrmStatCd())) {
        	// 프로모션 update
        	result = promotionService.updateOnOffPrm(promotionBoDTO.getPrm());
        	// 쿠폰 update
        	if(CouponUsePeriodCode.PD_APPN.toString().equals(promotionBoDTO.getPrmCpn().getCpnUsePdCd()) ){
        		// TODO : ERP정책에 따름을 선택한 경우 ERP 조회

        	}
        	
        	promotionService.updateOnOffPrmCpn(promotionBoDTO.getPrmCpn());
        	// 프로모션 적용대상 (전체 delete -> insert)
        	promotionTargetService.deletePromotionApplyTarget(promotionBoDTO);
        	promotionTargetService.insertPromotionApplyTarget(sqlSession1, promotionBoDTO);
        }else{
        	throw new PromotionStoppageException(null);
        }
        
    	return result;
    }
}
