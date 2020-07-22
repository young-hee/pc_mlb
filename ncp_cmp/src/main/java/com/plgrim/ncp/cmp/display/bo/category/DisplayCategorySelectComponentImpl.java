package com.plgrim.ncp.cmp.display.bo.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;
import com.plgrim.ncp.biz.display.data.DspCtgryBoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryCnncGodBoDTO;
import com.plgrim.ncp.biz.display.data.DspImgUploadDTO;
import com.plgrim.ncp.biz.display.result.DspCtgryBoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryCnncGodExcelResult;
import com.plgrim.ncp.biz.display.result.DspImgUploadResult;
import com.plgrim.ncp.biz.display.service.DisplayCategoryService;
import com.plgrim.ncp.biz.display.service.DisplayCornerContentsService;
import com.plgrim.ncp.cmp.display.bo.DisplayCategorySelectComponent;
import com.plgrim.ncp.framework.page.PageParam;

@Component
public class DisplayCategorySelectComponentImpl implements DisplayCategorySelectComponent {

    @Autowired
    private DisplayCategoryService displayCategorysService;

    @Autowired
    DisplayCornerContentsService displayCornerContentsService;


    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectCategoryTree(com.plgrim.ncp.biz.display.data.DspCtgryBoDTO)
     */
    @Override
    public List<DspCtgryBoResult> selectCategoryTree(DspCtgryBoDTO dspCtgryBoDTO) throws Exception {
        return displayCategorysService.selectCategoryTree(dspCtgryBoDTO);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#searchCtgryList(com.plgrim.ncp.biz.display.data.DspCtgryBoDTO)
     */
    @Override
    public List<DspCtgryBoResult> selectCtgryList(DspCtgryBoDTO dspCtgryBoDTO, PageParam pageParam) throws Exception {
        String[] arCtgryNo = dspCtgryBoDTO.getArDspCtgryNo();
        if(arCtgryNo != null && arCtgryNo.length>0) {
            return displayCategorysService.selectCtgryListByCtgryNo(dspCtgryBoDTO, pageParam);
        }
        return displayCategorysService.selectCtgryList(dspCtgryBoDTO, pageParam);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectCtgryListTotalCount(com.plgrim.ncp.biz.display.data.DspCtgryBoDTO)
     */
    @Override
    public int selectCtgryListTotalCount(DspCtgryBoDTO dspCtgryBoDTO) throws Exception {
        String[] arCtgryNo = dspCtgryBoDTO.getArDspCtgryNo();
        if(arCtgryNo != null && arCtgryNo.length>0) {
            return displayCategorysService.selectCtgryListByCtgryNoTotalCount(dspCtgryBoDTO);
        }
        return displayCategorysService.selectCtgryListTotalCount(dspCtgryBoDTO);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.bo.DisplayCategorySelectComponent#selectImageUploadList(com.plgrim.ncp.biz.display.data.DspImgUploadDTO, com.plgrim.ncp.framework.page.PageParam)
     */
    @Override
    public Page<DspImgUploadResult> selectImageUploadList(DspImgUploadDTO dspImgUploadDTO, PageParam pageParam) throws Exception {
        Page<DspImgUploadResult> resultList = displayCornerContentsService.getImageUploadList(dspImgUploadDTO, pageParam);
        return resultList;
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.bo.DisplayCategorySelectComponent#detailImageUpload(com.plgrim.ncp.biz.display.data.DspImgUploadDTO)
     */
    @Override
    public DspImgUploadResult detailImageUpload(DspImgUploadDTO dspImgUploadDTO) throws Exception {
        return displayCornerContentsService.detailImageUpload(dspImgUploadDTO);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.bo.DisplayCategorySelectComponent#selectImageList(com.plgrim.ncp.biz.display.data.DspImgUploadDTO)
     */
    @Override
    public List<DspImgUploadResult> selectImageList(DspImgUploadDTO dspImgUploadDTO) throws Exception {
        return displayCornerContentsService.selectImageList(dspImgUploadDTO);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.bo.DisplayCategorySelectComponent#selectGodExistDspCtgryBoList(com.plgrim.ncp.biz.display.data.DspCtgryBoDTO)
     */
    @Override
    public List<DspCtgryBoResult> selectGodExistDspCtgryBoList(DspCtgryBoDTO dspCtgryBoDTO) throws Exception{
        return displayCategorysService.selectGodExistDspCtgryBoList(dspCtgryBoDTO);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectBrndCtgryTree(com.plgrim.ncp.biz.display.data.DspCtgryBoDTO)
     */
    @Override
    public List<DspCtgryBoResult> selectBrndCtgryTree(DspCtgryBoDTO dspCtgryBoDTO) throws Exception {
        return displayCategorysService.selectBrndCtgryTree(dspCtgryBoDTO);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.bo.DisplayCategorySelectComponent#otltGodCheck(java.util.List)
     */
    @Override
    public Integer otltGodCheck(List<DspCtgryCnncGod> list) throws Exception{
        return displayCategorysService.otltGodCheck(list);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.bo.DisplayCategorySelectComponent#selectCtgryChk(java.util.List)
     */
    @Override
    public String selectCtgryChk(List<DspCtgryCnncGodBoDTO> list) throws Exception{
        String failGod = "";
        for(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO : list){
            int ctgryCnt = displayCategorysService.selectCtgryChk(dspCtgryCnncGodBoDTO.getDspCtgryCnncGod().getGodNo());
            if(ctgryCnt < 2){
                failGod += dspCtgryCnncGodBoDTO.getDspCtgryCnncGod().getGodNo() + ",";
            }
        }
        return failGod;
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.bo.DisplayCategorySelectComponent#selectGodOneCnncCtgry(java.lang.String)
     */
    @Override
    public List<DspCtgryCnncGodExcelResult> selectGodOneCnncCtgry(String godNo) throws Exception {
        return displayCategorysService.selectGodOneCnncCtgry(godNo);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectBrndCtgryTreePopup(com.plgrim.ncp.biz.display.data.DspCtgryBoDTO)
     */
    @Override
    public List<DspCtgryBoResult> selectBrndCtgryTreePopup(DspCtgryBoDTO dspCtgryBoDTO)  {
        return displayCategorysService.selectBrndCtgryTreePopup(dspCtgryBoDTO);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#searchCtgryList(com.plgrim.ncp.biz.display.data.DspCtgryBoDTO)
     */
    @Override
    public List<DspCtgryBoResult> selectBrndCtgryKwdPopup(DspCtgryBoDTO dspCtgryBoDTO, PageParam pageParam) {
        return displayCategorysService.selectBrndCtgryKwdPopup(dspCtgryBoDTO, pageParam);
    }

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectCtgryListTotalCount(com.plgrim.ncp.biz.display.data.DspCtgryBoDTO)
     */
    @Override
    public int selectBrndCtgryKwdPopupTotalCount(DspCtgryBoDTO dspCtgryBoDTO) {
        return displayCategorysService.selectBrndCtgryKwdPopupTotalCount(dspCtgryBoDTO);
    }

}
