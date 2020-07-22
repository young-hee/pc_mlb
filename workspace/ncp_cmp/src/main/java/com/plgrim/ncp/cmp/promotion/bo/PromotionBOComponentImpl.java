package com.plgrim.ncp.cmp.promotion.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCrsDcAplGod;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionAplGoods;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionCouponType;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionDatePattern;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionOrderDiscountType;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionStat;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionType;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.data.PromotionExcelDTO;
import com.plgrim.ncp.biz.promotion.exception.PromotionNotExistException;
import com.plgrim.ncp.biz.promotion.exception.PromotionStoppageException;
import com.plgrim.ncp.biz.promotion.result.PromotionApplyGoodBoResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;
import com.plgrim.ncp.biz.promotion.service.PromotionDetailService;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.biz.promotion.service.PromotionTargetService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.ExcelResult;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.utils.JsonDateValueUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Transactional(value = "transactionManager")
@Component
public class PromotionBOComponentImpl extends AbstractComponent implements PromotionBOComponent {

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
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#insertPromotion(com.plgrim.ncp.biz.promotion.data.PromotionBoDTO)
     */
	@Override
    public void insertPromotion(PromotionBoDTO promotionBoDTO) throws Exception {

        String prmTpCd = promotionBoDTO.getPrm().getPrmTpCd();
        String prmDtlTpCd = promotionBoDTO.getPrm().getPrmDtlTpCd();
        String prmNoGen = getIdGenService().generateDBNumber(sqlSession1, "SQ_PRM", "PR", DatabaseType.ORACLE);

        // 프로모션 기본
        promotionBoDTO.getPrm().setPrmNo(prmNoGen);
        promotionService.insertPromotion(promotionBoDTO);
        // 프로모션 적용기간
        promotionService.insertPromotionPeriod(promotionBoDTO);

        if (PromotionType.CPN.toString().equals(prmTpCd)) {
            // 프로모션 상세(쿠폰정보)
            promotionBoDTO.getPrmCpn().setPrmNo(prmNoGen);
            int detailCnt = promotionDetailService.insertPromotionCoupon(promotionBoDTO);

            if (!(detailCnt > 0)) {
                //throw new Exception();
            }
        }
        else if (PromotionType.ORD_DC.toString().equals(prmTpCd)) {

            if (PromotionOrderDiscountType.BUNDLE_DC.toString().equals(prmDtlTpCd) && promotionBoDTO.getPrmBundleDcCndList().size() > 0) {
                // 프로모션 묶음 할인 조건 insert
                promotionDetailService.insertPrmBundleDcCnd(sqlSession1, promotionBoDTO);
            }
        }

        // 프로모션 적용대상
        promotionBoDTO.getPrmAplTgt().setPrmNo(prmNoGen);
        promotionTargetService.insertPromotionApplyTarget(sqlSession1, promotionBoDTO);

        // 프로모션 적용상품 (주문할인 제외)
        if (PromotionType.CPN.toString().equals(prmTpCd) && PromotionCouponType.DLV_CST_CPN.toString().equals(prmDtlTpCd)) {
            promotionBoDTO.getPrmAplGod().setPrmNo(prmNoGen);
            promotionBoDTO.getPrmAplGod().setAplGodSectCd(PromotionAplGoods.ALL.toString());
            promotionBoDTO.getPrmAplGod().setGodAplYn(PromotionEnum.YES.toString());

            promotionTargetService.insertPromotionApplyGoods(sqlSession1, promotionBoDTO.getPrmAplGod());
        }
        // 현재 BO에서는 오프라인 쿠폰에 대해서는 조회만 하고 등록, 수정을 하지 않으므로 주석 처리.
//        else if(PromotionType.CPN.toString().equals(prmTpCd) && PromotionCouponType.BSK_CPN.toString().equals(prmDtlTpCd)
//        		&& StringService.isNotEmpty(promotionBoDTO.getPrm().getErpCmpgId())	){
//        	//온오프라인 쿠폰 조회 및 적용
//        	promotionTargetService.insertOnOffPromotionApplyGoods(sqlSession1, promotionBoDTO.getPrm());
//        }
    }
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#updatePromotion(com.plgrim.ncp.biz.promotion.data.PromotionBoDTO)
	 */
    @Override
    public int updatePromotion(PromotionBoDTO promotionBoDTO) throws Exception {

        int result = 0;

        // 프로모션 수정
        // 1. 승인용은 별도처리(화면 조작 방지용)
        // 2. 데이터는 등록과 동일하게 전체 전달

        String prmNo = promotionBoDTO.getPrm().getPrmNo();
        String prmStatCd = promotionBoDTO.getPrm().getPrmStatCd();
        String prmTpCd = promotionBoDTO.getPrm().getPrmTpCd();
        String prmDtlTpCd = promotionBoDTO.getPrm().getPrmDtlTpCd();

        Prm prm = new Prm();
        prm.setPrmNo(prmNo);
        //prm.setB2eCrtfcKey(promotionBoDTO.getPrm().getB2eCrtfcKey());

        PrmExtend validPrm = promotionService.selectValidPromotionInfo(prm);

        if (validPrm.getPrmCount() == 0) {
            // 존재하지 않는 프로모션입니다.
            throw new PromotionNotExistException(null);
        }
        else {
            if (StringService.isNotEmpty(validPrm.getPrmStatCd())) {
                prmStatCd = validPrm.getPrmStatCd();
            }
        }

        // 프로모션 적용기간 DELETE -> INSERT
        promotionService.deletePromotionApplyPeriod(promotionBoDTO);
        promotionService.insertPromotionPeriod(promotionBoDTO);

        if (PromotionStat.APRV.toString().equals(prmStatCd)) {
            // 상태가 승인시
            result = promotionService.updatePromotionAprvKind(promotionBoDTO);
        }
        else if (PromotionStat.APRV_WAIT.toString().equals(prmStatCd)) {
            // 프로모션 업데이트
            result = promotionService.updatePromotion(promotionBoDTO);

            // 프로모션 적용대상 DELETE -> INSERT
            promotionBoDTO.getPrmAplTgt().setPrmNo(prmNo);
            promotionTargetService.deletePromotionApplyTarget(promotionBoDTO);
            promotionTargetService.insertPromotionApplyTarget(sqlSession1, promotionBoDTO);

            // 쿠폰인 경우 DETAIL 수정
            if (PromotionType.CPN.toString().equals(prmTpCd)) {
                promotionBoDTO.getPrmCpn().setPrmNo(prmNo);
                promotionDetailService.updatePromotionCoupon(promotionBoDTO);
                
                if (!prmDtlTpCd.equals(validPrm.getPrmDtlTpCd())) {
                	// 배송비 쿠폰으로 변경 시, 적용대상상품을 전체상품(기본값)으로 재설정 - 배송비 쿠폰은 상품 제한 없음.
                	if (PromotionCouponType.DLV_CST_CPN.toString().equals(prmDtlTpCd)) {
	                    // 적용대상 상품 DELETE -> INSERT (전체상품(default)
	                    PrmAplGod pag = new PrmAplGod();
	                    pag.setPrmNo(prmNo);
	                    promotionTargetService.deletePromotionTargetGoods(pag);
	
	                    pag.setAplGodSectCd(PromotionAplGoods.ALL.toString());
	                    pag.setGodAplYn(PromotionEnum.YES.toString());
	                    promotionTargetService.insertPromotionApplyGoods(sqlSession1, pag);
                	}
                
                	else if (validPrm.getGodAplSesonCount() > 0) {
                		// 적용대상상품에 시즌 값이 있을경우
                		PrmAplGod pag = new PrmAplGod();
 	                    pag.setPrmNo(prmNo);
 	                    pag.setAplGodSectCd(PromotionAplGoods.SESON.toString());
 	                    promotionTargetService.deletePromotionTargetGoods(pag);
                	}
                }
            }
            else if (PromotionType.ORD_DC.toString().equals(prmTpCd)) {

                if (PromotionOrderDiscountType.BUNDLE_DC.toString().equals(prmDtlTpCd)) {

                    if (promotionBoDTO.getPrmBundleDcCndList().size() > 0) {
                        // 프로모션 묶음 할인 조건 delete -> insert
                        promotionDetailService.deletePrmBundleDcCnd(promotionBoDTO);
                        promotionDetailService.insertPrmBundleDcCnd(sqlSession1, promotionBoDTO);
                    }

                    // 교차할인 적용 상품 제거
                    PrmCrsDcAplGod pcdagDel = new PrmCrsDcAplGod();
                    pcdagDel.setPrmNo(prmNo);
                    promotionTargetService.deletePrmCrsDcAplGod(pcdagDel);
                }
                else {
                    promotionDetailService.deletePrmBundleDcCnd(promotionBoDTO);

                    PrmAplGod pagDel = new PrmAplGod();
                    pagDel.setPrmNo(prmNo);
                    promotionTargetService.deletePromotionTargetGoods(pagDel);
                }
            }
        }
        else if (PromotionStat.STPGE.toString().equals(prmStatCd)) {
            throw new PromotionStoppageException(null);
        }

        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#updatePromotionStatus(com.plgrim.ncp.biz.promotion.data.PromotionBoDTO)
     */
    @Override
    public int updatePromotionStatus(PromotionBoDTO promotionBoDTO) throws Exception {
        int result = promotionService.updatePromotionStatus(promotionBoDTO);
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#updatePromotionGoodsPriceProcedure(com.plgrim.ncp.biz.promotion.data.PromotionBoDTO)
     */
    @Override
    public void updatePromotionGoodsPriceProcedure(PromotionBoDTO promotionBoDTO) throws Exception {
        promotionService.updatePromotionGoodsPriceProcedure(promotionBoDTO);
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#excelDownloadProcess(org.springframework.ui.Model, java.util.List, org.springframework.data.domain.Page)
     */
    @Override
    public void excelDownloadProcess(Model model, List<PromotionExcelDTO> excelList, Page<?> list) throws Exception {
        this.excelDownloadProcess(model, excelList, list, null);
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#excelDownloadProcess(org.springframework.ui.Model, java.util.List, org.springframework.data.domain.Page, java.lang.String)
     */
    @Override
    public void excelDownloadProcess(Model model, List<PromotionExcelDTO> excelList, Page<?> list, String fileName) throws Exception {

        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueUtil());

        JSONArray jsonArray = JSONArray.fromObject(list.getContent(), config);

        ExcelResult result = new ExcelResult();
        List<Map<String, String>> resultList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {

            Map<String, String> map = new LinkedHashMap<String, String>();
            JSONObject jso = jsonArray.getJSONObject(i); // row별 object

            for (int j = 0; j < excelList.size(); j++) {

                PromotionExcelDTO ped = excelList.get(j);

                String colId = ped.getColumnId();
                String colLabel = ped.getColumnLabel();
                String data = "";

                if (colId.indexOf(PromotionSeparator.DOT.toString()) > 0) {
                    String[] sptId = colId.split(Pattern.quote(PromotionSeparator.DOT.toString()));
                    JSONObject jsonObj = jso.getJSONObject(sptId[0]);
                    if(!jsonObj.isEmpty()) {
                    	data = jso.getJSONObject(sptId[0]).getString(sptId[1]); // 테이블
                    }
                }
                else {
                    data = jso.getString(colId);
                }

                map.put(colLabel, data);

                if (i < 1) {
                    result.addHeader(colLabel);
                }
            }
            resultList.add(map);
        }

        //엑셀파일 명
        result.setFileName(fileName);

        //컨텐트 데이터 생성
        result.parse(model, resultList);

    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#insertPromotionTempApplyGoods(com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod)
     */
    @Override
    public void insertPromotionTempApplyGoods(PrmAplGod pag) throws Exception {

        pag.setGodAplYn(PromotionEnum.YES.toString());
        pag.setAplGodSectCd(PromotionAplGoods.GOD.toString());
        promotionTargetService.insertPromotionApplyGoods(sqlSession1, pag);
    }
    
    /*
     * 서버 기준 현재 일자 리턴
     * 
     * @return
     */
    protected String getCurrentDateTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat defaultFormat = new SimpleDateFormat(PromotionDatePattern.SHORT.toString());
        return defaultFormat.format(cal.getTime());
    }

	@Override
	public Page<PromotionApplyGoodBoResult> selectPromotionApplyGoodList(PromotionApplyGoodBoDTO searchDTO, PageParam pageParam)
			throws Exception {
	        
		String prmTpCd = searchDTO.getPrmTpCd();
		String prmDtlTpCd = searchDTO.getPrmDtlTpCd();
	        
		Page<PromotionApplyGoodBoResult> resultList = null;
	        
		if (PromotionType.ORD_DC.toString().equals(prmTpCd) && PromotionOrderDiscountType.CRS_DC.toString().equals(prmDtlTpCd)) {
			resultList = promotionTargetService.selectPrmCrsDcAplGodList(searchDTO, pageParam);
		} else {
			resultList = promotionTargetService.selectPromotionApplyGoodList(searchDTO, pageParam);
		}
		
		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#selectPrmAplTgtGrpcdList(com.plgrim.ncp.biz.promotion.data.PromotionBoDTO, com.plgrim.ncp.framework.page.PageParam)
	 */
    @Override
    public Page<PromotionBoResult> selectPrmAplTgtGrpcdList(PromotionBoDTO searchDTO, PageParam pageParam) throws Exception {
        Page<PromotionBoResult> resultList = promotionTargetService.selectPrmAplTgtGrpcdList(searchDTO, pageParam);
        return resultList;
    }
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#selectPrmAplGodInfoForSearch(com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO)
	 */
	@Override
	public int selectPrmAplGodInfoForSearch(PromotionApplyGoodBoDTO searchDTO) throws Exception {
        return promotionTargetService.selectPrmAplGodInfoForSearch(searchDTO);
    }
    
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#selectValidPrmAplGodTargetAll(com.plgrim.ncp.biz.promotion.data.PromotionBoDTO)
	 */
	public int selectValidPrmAplGodTargetAll(PromotionBoDTO promotionBoDTO) throws Exception {
        
        int result = 0;
        for (String prmNo : promotionBoDTO.getPrm().getPrmNo().split(PromotionSeparator.DELIMITER.toString())) {
            PrmAplGod prmAplGod = new PrmAplGod();
            prmAplGod.setPrmNo(prmNo);
            result += promotionTargetService.selectValidPrmAplGodTargetAll(prmAplGod);
        }
            
        return result;
	}
	
	/**
     * 프로모션명 다국어 저장 merge
     *
     * @param promotionBoDTO
     * @throws Exception
     */
	@Override
    public void mergePrmNmMlang(PromotionBoDTO promotionBoDTO) throws Exception {
    	promotionService.mergePrmNmMlang(promotionBoDTO);
    }
	
}
