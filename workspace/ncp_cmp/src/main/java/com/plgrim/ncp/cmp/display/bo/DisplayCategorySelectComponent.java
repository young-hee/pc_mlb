package com.plgrim.ncp.cmp.display.bo;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;
import com.plgrim.ncp.biz.display.data.DspCtgryBoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryCnncGodBoDTO;
import com.plgrim.ncp.biz.display.data.DspImgUploadDTO;
import com.plgrim.ncp.biz.display.result.DspCtgryBoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryCnncGodExcelResult;
import com.plgrim.ncp.biz.display.result.DspImgUploadResult;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DisplayCategorySelectComponent {

    /**
     * 전시카테고리 트리목록 조회
     * 
     * @param dspCtgryBoDTO
     * @return
     * @throws Exception
     */
    public List<DspCtgryBoResult> selectCategoryTree(DspCtgryBoDTO dspCtgryBoDTO) throws Exception;

    /**
     * 전시카테고리 검색어 조회 목록
     * 
     * @param dspCtgryBoDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public List<DspCtgryBoResult> selectCtgryList(DspCtgryBoDTO dspCtgryBoDTO, PageParam pageParam) throws Exception;

    /**
     * 전시카테고리 검색어 조회 목록 총count
     * 
     * @param dspCtgryBoDTO
     * @return
     * @throws Exception
     */
    public int selectCtgryListTotalCount(DspCtgryBoDTO dspCtgryBoDTO) throws Exception;

    /**
     * 전시 컨텐츠 이미지 목록 그리드 조회
     * 
     * @param dspImgUploadDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<DspImgUploadResult> selectImageUploadList(DspImgUploadDTO dspImgUploadDTO, PageParam pageParam) throws Exception;

    /**
     * 전시 컨텐츠 이미지 정보 상세 조회
     * 
     * @param dspImgUploadDTO
     * @return
     * @throws Exception
     */
    public DspImgUploadResult detailImageUpload(DspImgUploadDTO dspImgUploadDTO) throws Exception;

    /**
     * 전시 컨텐츠 이미지 목록 조회
     * 
     * @param dspImgUploadDTO
     * @return
     * @throws Exception
     */
    public List<DspImgUploadResult> selectImageList(DspImgUploadDTO dspImgUploadDTO) throws Exception;

    /**
     * 상품이 존재하는 카테고리 목록 조회
     * 
     * @param dspCtgryBoDTO
     * @return
     * @throws Exception
     */
    public List<DspCtgryBoResult> selectGodExistDspCtgryBoList(DspCtgryBoDTO dspCtgryBoDTO) throws Exception;

    /**
     * 전시브랜드카테고리 트리목록 조회
     * 
     * @param dspCtgryBoDTO
     * @return
     * @throws Exception
     */
    public List<DspCtgryBoResult> selectBrndCtgryTree(DspCtgryBoDTO dspCtgryBoDTO) throws Exception;

    /**
     * 아울렛 카테고리 상품 체크
     * 
     * @param list
     * @return
     * @throws Exception
     */
    public Integer otltGodCheck(List<DspCtgryCnncGod> list) throws Exception;

    /**
     * 전시 카테고리 연결 상품번호 조회
     * 
     * @param list
     * @return
     * @throws Exception
     */
    public String selectCtgryChk(List<DspCtgryCnncGodBoDTO> list) throws Exception;

    /**
     * 전시 카테고리 연결 상품정보 조회(엑셀용)
     * 
     * @param list
     * @return
     * @throws Exception
     */
    public List<DspCtgryCnncGodExcelResult> selectGodOneCnncCtgry(String list) throws Exception;

    /**
     * 전시브랜드카테고리 트리목록 조회. (브랜드하위카테고리)
     * 
     * @param dspCtgryBoDTO
     * @return
     */
    public List<DspCtgryBoResult> selectBrndCtgryTreePopup(DspCtgryBoDTO dspCtgryBoDTO) ;

    /**
     * 전시카테고리 검색어 조회 목록 (브랜드하위카테고리)
     * 
     * @param dspCtgryBoDTO
     * @param pageParam
     * @return
     */
    public List<DspCtgryBoResult> selectBrndCtgryKwdPopup(DspCtgryBoDTO dspCtgryBoDTO, PageParam pageParam) ;

    /**
     * 전시카테고리 검색어 조회 목록 총count.(브랜드하위카테고리)
     * 
     * @param dspCtgryBoDTO
     * @return
     */
    public int selectBrndCtgryKwdPopupTotalCount(DspCtgryBoDTO dspCtgryBoDTO) ;

}
