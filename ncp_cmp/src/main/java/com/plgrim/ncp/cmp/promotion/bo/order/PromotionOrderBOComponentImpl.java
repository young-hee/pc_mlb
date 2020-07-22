package com.plgrim.ncp.cmp.promotion.bo.order;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCrsDcAplGod;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionAplGoods;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionCouponType;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionCrsDcAplType;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionOrderDiscountType;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionType;
import com.plgrim.ncp.biz.promotion.data.PrmAplGodExtend;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO;
import com.plgrim.ncp.biz.promotion.exception.PromotionAlreadyExistOrdDcGodException;
import com.plgrim.ncp.biz.promotion.exception.PromotionApplyGoodsRequiredException;
import com.plgrim.ncp.biz.promotion.exception.PromotionGoodDuplicateException;
import com.plgrim.ncp.biz.promotion.result.PromotionApplyGoodBoResult;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.biz.promotion.service.PromotionTargetService;
import com.plgrim.ncp.cmp.promotion.bo.PromotionBOComponentImpl;
import com.plgrim.ncp.cmp.promotion.bo.PromotionOrderBOComponent;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.StringService;

@Transactional(value = "transactionManager")
@Component
public class PromotionOrderBOComponentImpl extends PromotionBOComponentImpl implements PromotionOrderBOComponent {

	@Autowired
    private PromotionService promotionService;

    @Autowired
    private PromotionTargetService promotionTargetService;

    @Autowired
    @Qualifier("sessionTemplate1")
    protected SqlSession sqlSession1;
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#insertPromotionApplyGoods(com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO)
     */
    @Override
    public int insertPromotionApplyGoods(PromotionApplyGoodBoDTO promotionApplyGoodBoDTO) throws Exception {

        int result = 0;

        String prmNo = promotionApplyGoodBoDTO.getPrmAplGod().getPrmNo();

        String godAplYn = promotionApplyGoodBoDTO.getPrmAplGod().getGodAplYn();
        String aplGodSectCd = promotionApplyGoodBoDTO.getPrmAplGod().getAplGodSectCd();

        String prmTpCd = promotionApplyGoodBoDTO.getPrmTpCd();
        String prmDtlTpCd = promotionApplyGoodBoDTO.getPrmDtlTpCd();

        Prm prm = new Prm();
        prm.setPrmNo(prmNo);

        PrmExtend validPrm = promotionService.selectValidPromotionInfo(prm);

        if (validPrm.getPrmCount() < 1) {
            // 프로모션 번호가 없습니다.
        }

        // 이전 적용상품구분코드
        String beforeAplGodSectCd = promotionApplyGoodBoDTO.getBeforeAplGodSectCd();
        // 이전 제외상품구분코드
        String beforeExcGodSectCd = promotionApplyGoodBoDTO.getBeforeExcGodSectCd();

        // 선택된 적용상품구분코드
        String checkedAplGodSectCd = promotionApplyGoodBoDTO.getCheckedAplGodSectCd();
        // 선택된 제외상품구분코드
        String checkedExcGodSectCd = promotionApplyGoodBoDTO.getCheckedExcGodSectCd();

        String regtrId = promotionApplyGoodBoDTO.getPrmAplGod().getRegtrId();
        String udterId = promotionApplyGoodBoDTO.getPrmAplGod().getUdterId();

        if (PromotionType.ORD_DC.toString().equals(prmTpCd) && PromotionOrderDiscountType.CRS_DC.toString().equals(prmDtlTpCd)) {

            // 교차할인 상품을 등록하면 (prm_crs_dc_apl_god)
            // 묶음할인 상품을 등록할 수 없다.(prm_apl_god)

            // 중복 insert 방지
            // 주문할인간 상품 생성 불가.

            // 적용상품값 제거
            PrmAplGod pagDel = new PrmAplGod();
            pagDel.setPrmNo(prmNo);
            promotionTargetService.deletePromotionTargetGoods(pagDel);

            // 교차할인 적용대상그룹 insert
            PrmCrsDcAplGod prmCrsDcAplGod = new PrmCrsDcAplGod();
            prmCrsDcAplGod.setPrmNo(prmNo);

            if (PromotionEnum.YES.toString().equals(godAplYn)) {
                prmCrsDcAplGod.setAplTgtGodGrpCd(PromotionCrsDcAplType.APL_TGT_GOD_GRP_1.toString());
            }
            else if (PromotionEnum.NO.toString().equals(godAplYn)) {
                prmCrsDcAplGod.setAplTgtGodGrpCd(PromotionCrsDcAplType.APL_TGT_GOD_GRP_2.toString());
            }

            for (String godNo : promotionApplyGoodBoDTO.getPrmAplGod().getGodNo().split(PromotionSeparator.DELIMITER.toString())) {
                PrmAplGodExtend validPagx = new PrmAplGodExtend();

                validPagx.setPrmNo(prmNo);
                validPagx.setGodNo(godNo);
                PromotionApplyGoodBoResult pagResult = promotionTargetService.selectValidOrderPromotionApplyGoodInfo(validPagx);

                if (pagResult.getOrdDcGodCount() > 0) {
                    // 기 등록된 상품이 있는 경우.
                    throw new PromotionAlreadyExistOrdDcGodException(null);
                }
                else if (StringService.isNotEmpty(pagResult.getAplTgtGodGrpCd())) {
                    throw new PromotionAlreadyExistOrdDcGodException(null);
                }
                else {
                    prmCrsDcAplGod.setGodNo(godNo);
                    prmCrsDcAplGod.setRegtrId(regtrId);
                    prmCrsDcAplGod.setUdterId(udterId);

                    result += promotionTargetService.insertPrmCrsDcAplGod(prmCrsDcAplGod);
                }
            }
        }
        else {

            if (PromotionType.ORD_DC.toString().equals(prmTpCd)) {
                // 교차할인 적용상품 제거
                PrmCrsDcAplGod pcdagDel = new PrmCrsDcAplGod();
                pcdagDel.setPrmNo(prmNo);
                promotionTargetService.deletePrmCrsDcAplGod(pcdagDel);
            }
            else {
                if (PromotionEnum.NO.toString().equals(godAplYn) && validPrm.getGodAplCount() < 1) {
                    throw new PromotionApplyGoodsRequiredException(null);
                }
            }

            // 시즌 등록 (2017.05.24) : 모두 삭제 후, 등록
            if (PromotionAplGoods.SESON.toString().equals(aplGodSectCd)) {
            	
            	if (validPrm.getGodAplCount() < 1) {
                     throw new PromotionApplyGoodsRequiredException(null);
                }
            	 
            	// 이전 시즌 전체 삭제 (2017.05.24)
                if (validPrm.getGodAplSesonCount() > 0) {
                	PrmAplGod pagDel = new PrmAplGod();
   	             	pagDel.setPrmNo(prmNo);
   	             	pagDel.setGodAplYn(PromotionEnum.YES.toString());
                	pagDel.setAplGodSectCd(PromotionAplGoods.SESON.toString());
                	promotionTargetService.deletePromotionTargetGoods(pagDel);
                }
            	 
            	for (String sesonCd : promotionApplyGoodBoDTO.getPrmAplGod().getSesonCd().split(PromotionSeparator.DELIMITER.toString())) {
                    PrmAplGod pag = new PrmAplGod();

                    pag.setSesonCd(sesonCd);

                    pag.setPrmNo(prmNo);
                    pag.setGodAplYn(godAplYn);
                    pag.setAplGodSectCd(aplGodSectCd);
                    pag.setRegtrId(regtrId);
                    pag.setUdterId(udterId);

                    result += promotionTargetService.insertPromotionApplyGoods(sqlSession1, pag);
                }
            	 
            } else {
	            // 적용대상 상품이 변경된 경우
	            if (!checkedAplGodSectCd.equals(beforeAplGodSectCd)) {
	
	                PrmAplGod pagDel = new PrmAplGod();
	
	                pagDel.setPrmNo(prmNo);
	
	                if (!PromotionAplGoods.ALL.toString().equals(checkedAplGodSectCd)) {
	                    pagDel.setAplGodSectCd(beforeAplGodSectCd);
	                    pagDel.setGodAplYn(PromotionEnum.YES.toString());
	                }
	
	                // 이전 적용상품값 제거
	                promotionTargetService.deletePromotionTargetGoods(pagDel);
	                
	                // 이전 적용상품 시즌 제거 (2017.05.24) : ALL은 이미 위에서 전체 삭제 되어 제외
	                if (validPrm.getGodAplSesonCount() > 0 && !PromotionAplGoods.ALL.toString().equals(checkedAplGodSectCd)) {
	                	pagDel.setAplGodSectCd(PromotionAplGoods.SESON.toString());
	                	promotionTargetService.deletePromotionTargetGoods(pagDel);
	                }
	
	                // 전체대상일 경우 구분값 insert
	                if (PromotionAplGoods.ALL.toString().equals(checkedAplGodSectCd)) {
	
	                    PrmAplGod pagAdd = new PrmAplGod();
	
	                    pagAdd.setPrmNo(prmNo);
	                    pagAdd.setGodAplYn(PromotionEnum.YES.toString());
	                    pagAdd.setAplGodSectCd(checkedAplGodSectCd);
	                    pagAdd.setRegtrId(regtrId);
	                    pagAdd.setUdterId(udterId);
	
	                    result = promotionTargetService.insertPromotionApplyGoods(sqlSession1, pagAdd);
	                }
	                else {
	                    pagDel.setPrmNo(prmNo);
	                    pagDel.setAplGodSectCd(null);
	                    pagDel.setGodAplYn(PromotionEnum.NO.toString());
	
	                    // 이전 적용상품값 제거
	                    promotionTargetService.deletePromotionTargetGoods(pagDel);
	                }
	                
	            }
	            // 제외대상 상품이 변경된 경우
	            if (StringService.isNotEmpty(beforeExcGodSectCd) && !checkedExcGodSectCd.equals(beforeExcGodSectCd)) {
	
	                PrmAplGod pagDel = new PrmAplGod();
	
	                pagDel.setPrmNo(prmNo);
	                pagDel.setAplGodSectCd(beforeExcGodSectCd);
	                pagDel.setGodAplYn(PromotionEnum.NO.toString());
	
	                // 이전 제외상품값 제거
	                promotionTargetService.deletePromotionTargetGoods(pagDel);
	            }
	
	            if (PromotionAplGoods.BRND.toString().equals(aplGodSectCd)) {
	
	                for (String brndId : promotionApplyGoodBoDTO.getPrmAplGod().getBrndId().split(PromotionSeparator.DELIMITER.toString())) {
	                    PrmAplGod pag = new PrmAplGod();
	
	                    pag.setBrndId(brndId);
	
	                    pag.setPrmNo(prmNo);
	                    pag.setGodAplYn(godAplYn);
	                    pag.setAplGodSectCd(aplGodSectCd);
	                    pag.setRegtrId(regtrId);
	                    pag.setUdterId(udterId);
	
	                    result += promotionTargetService.insertPromotionApplyGoods(sqlSession1, pag);
	                }
	            }
	            else if (PromotionAplGoods.DSP_CTGRY.toString().equals(aplGodSectCd)) {
	
	                for (String dspCtgryNo : promotionApplyGoodBoDTO.getPrmAplGod().getDspCtgryNo()
	                        .split(PromotionSeparator.DELIMITER.toString())) {
	                    PrmAplGod pag = new PrmAplGod();
	
	                    pag.setDspCtgryNo(dspCtgryNo);
	
	                    pag.setPrmNo(prmNo);
	                    pag.setGodAplYn(godAplYn);
	                    pag.setAplGodSectCd(aplGodSectCd);
	                    pag.setRegtrId(regtrId);
	                    pag.setUdterId(udterId);
	
	                    result += promotionTargetService.insertPromotionApplyGoods(sqlSession1, pag);
	                }
	            }
	            else if (PromotionAplGoods.GOD.toString().equals(aplGodSectCd)) {
	
	                for (String godNo : promotionApplyGoodBoDTO.getPrmAplGod().getGodNo().split(PromotionSeparator.DELIMITER.toString())) {
	
	                    if (PromotionType.ORD_DC.toString().equals(prmTpCd)) {
	                        PrmAplGodExtend validPagx = new PrmAplGodExtend();
	
	                        validPagx.setPrmNo(prmNo);
	                        validPagx.setGodNo(godNo);
	                        PromotionApplyGoodBoResult pagResult = promotionTargetService.selectValidOrderPromotionApplyGoodInfo(validPagx);
	
	                        if (pagResult.getOrdDcGodCount() > 0) {
	                            // 기 등록된 상품이 있는 경우.
	                            throw new PromotionAlreadyExistOrdDcGodException(null);
	                        }
	                        else if (StringService.isNotEmpty(pagResult.getGodAplYn())) {
	                            throw new PromotionGoodDuplicateException(null);
	                        }
	                    }
	
	                    PrmAplGod pag = new PrmAplGod();
	
	                    pag.setGodNo(godNo);
	
	                    pag.setPrmNo(prmNo);
	                    pag.setGodAplYn(godAplYn);
	                    pag.setAplGodSectCd(aplGodSectCd);
	                    pag.setRegtrId(regtrId);
	                    pag.setUdterId(udterId);
	
	                    result += promotionTargetService.insertPromotionApplyGoods(sqlSession1, pag);
	                }
	            }
	            else {
	                // 값이 없습니다.
	            }
	        }
	        
	        //	삼성라이온즈 쿠폰 적용 제외 상품 설정(4월 말까지 진행) - 2016.03.18
	        Date saleDay = DateService.createDate("20160430235959", "yyyyMMddHHmmss");
	        Date toDay = new Date();        
			if (PromotionType.CPN.toString().equals(prmTpCd) && (DateService.getDurationDate(toDay, saleDay) > 0)
			        && PromotionEnum.YES.toString().equals(godAplYn)) {
				PrmAplGod pag = new PrmAplGod();
				pag.setPrmNo(prmNo);
				pag.setAplGodSectCd(PromotionAplGoods.GOD.toString());
				pag.setGodAplYn(PromotionEnum.NO.toString());
				pag.setGodNo("SM0016031087845");			
				promotionTargetService.insertPromotionApplyGoods(sqlSession1, pag);
			}
        }
        
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#deletePromotionTargetGoods(com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO)
     */
    @Override
    public int deletePromotionTargetGoods(PromotionApplyGoodBoDTO promotionApplyGoodBoDTO) throws Exception {

        int result = 0;
        String prmNo = promotionApplyGoodBoDTO.getPrmAplGod().getPrmNo();
        String aplTurns = promotionApplyGoodBoDTO.getAplTurns();
        String prmTpCd = promotionApplyGoodBoDTO.getPrmTpCd();
        String prmDtlTpCd = promotionApplyGoodBoDTO.getPrmDtlTpCd();

        if (StringService.isNotEmpty(aplTurns)) {

            for (String aplTurn : aplTurns.split(PromotionSeparator.DELIMITER.toString())) {
                PrmAplGod pag = new PrmAplGod();

                pag.setPrmNo(prmNo);
                pag.setAplTurn(Integer.parseInt(aplTurn));

                result += promotionTargetService.deletePromotionTargetGoods(pag);
            }
        }
        
        return result;
    }
    
    @Override
    public int deletePrmCrsDcAplGod(PromotionApplyGoodBoDTO promotionApplyGoodBoDTO) throws Exception {

        int result = 0;
        String prmNo = promotionApplyGoodBoDTO.getPrmCrsDcAplGod().getPrmNo();
        String aplTgtGodGrpCd = promotionApplyGoodBoDTO.getPrmCrsDcAplGod().getAplTgtGodGrpCd();
        String godNos = promotionApplyGoodBoDTO.getGodNos();

        if (StringService.isNotEmpty(godNos)) {

            for (String godNo : godNos.split(PromotionSeparator.DELIMITER.toString())) {
                PrmCrsDcAplGod pcdag = new PrmCrsDcAplGod();

                pcdag.setPrmNo(prmNo);
                pcdag.setAplTgtGodGrpCd(aplTgtGodGrpCd);
                pcdag.setGodNo(godNo);

                result += promotionTargetService.deletePrmCrsDcAplGod(pcdag);
            }
        }
        return result;
    }

}
