package com.plgrim.ncp.cmp.promotion.bo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod;
import com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.data.PromotionExcelDTO;
import com.plgrim.ncp.biz.promotion.result.PromotionApplyGoodBoResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;
import com.plgrim.ncp.framework.page.PageParam;

public interface PromotionBOComponent {
	
	/**
	 * 프로모션 등록 insert
	 *
	 * @param promotionBoDTO
	 * @throws Exception
	 */
	public void insertPromotion(PromotionBoDTO promotionBoDTO) throws Exception;
	
	/**
	 * 프로모션 상태변경 update
	 *
	 * @param promotionBoDTO
	 * @return
	 * @throws Exception
	 */
	public int updatePromotionStatus(PromotionBoDTO promotionBoDTO) throws Exception;
	
	/**
	 * 프로모션 수정 update
	 *
	 * @param promotionBoDTO
	 * @return
	 * @throws Exception
	 */
	public int updatePromotion(PromotionBoDTO promotionBoDTO) throws Exception;
	
	/**
     * 프로모션 적용상품 등록
     * 
     * @param pag
     * @throws Exception
     */
    public void insertPromotionTempApplyGoods(PrmAplGod pag) throws Exception;
    
	/**
	 * 프로모션 적용상품 목록 조회
	 * 
	 * @param searchDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
    public Page<PromotionApplyGoodBoResult> selectPromotionApplyGoodList(PromotionApplyGoodBoDTO searchDTO, PageParam pageParam) throws Exception;
    
    /**
     * 프로모션 적용대상 그룹코드 목록 조회
     * 
     * @param searchDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<PromotionBoResult> selectPrmAplTgtGrpcdList(PromotionBoDTO searchDTO, PageParam pageParam) throws Exception;
    
    /**
     * 적용 대상 상품 검색
     * 
     * @param searchDTO
     * @return
     * @throws Exception
     */
    public int selectPrmAplGodInfoForSearch(PromotionApplyGoodBoDTO searchDTO) throws Exception;
    
    /**
     * 적용대상상품 전체대상 여부 체크
     *   - 쿠폰프로모션 > 배송비쿠폰의 경우 default(전체)로 쿼리에 not exists 로 구분
     *                   적용대상상품 추가시 쿼리에서 제거요망
     *                   
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    public int selectValidPrmAplGodTargetAll(PromotionBoDTO promotionBoDTO) throws Exception;
    
    
    /**
     * 프로모션 엑셀 다운로드
     * 
     * @param model
     * @param excelList
     * @param list
     * @throws Exception
     */
    public void excelDownloadProcess(Model model, List<PromotionExcelDTO> excelList, Page<?> list) throws Exception;
    
    /**
     * 프로모션 엑셀 다운로드
     * 
     * @param model
     * @param excelList
     * @param list
     * @param fileName
     * @throws Exception
     */
    public void excelDownloadProcess(Model model, List<PromotionExcelDTO> excelList, Page<?> list, String fileName) throws Exception;
    
    /**
     * 프로모션 적용 상품 가격 (배치)프로시져 수동 실행
     * 
     * @param promotionBoDTO
     * @throws Exception
     */
    public void updatePromotionGoodsPriceProcedure(PromotionBoDTO promotionBoDTO) throws Exception;
    
    /**
     * 프로모션명 다국어 저장 merge
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void mergePrmNmMlang(PromotionBoDTO promotionBoDTO) throws Exception;
}
