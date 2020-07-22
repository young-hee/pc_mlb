package com.plgrim.ncp.cmp.vendor.bo;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.biz.vendor.data.VendorGridDTO;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.result.ComBrndHistResult;
import com.plgrim.ncp.biz.vendor.result.VendorListResult;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface VendorAffiliateBOComponent {


    /**
     * 브랜드별 수수료율 그리드 데이터 저장/수정
     *
     * @param dataList
     * @throws Exception
     */
    public void mergeBrndCommissionGridData(List<VendorGridDTO> dataList) throws Exception;

    /**
     * 브랜드별 수수료율 그리드 데이터 삭제
     *
     * @param dataList
     * @throws Exception
     */
    public void deleteBrndCommissionGridData(List<VendorGridDTO> dataList) throws Exception;

    /**
     * 제휴사별 상품속성 저장/수정
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param dataList [설명]
     * @since 2015. 4. 17
     */
    public void mergeComAffVrscComCnnc(List<VendorGridDTO> dataList) throws Exception;

    /**
     * 제휴사별 상품속성 삭제
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param dataList [설명]
     * @since 2015. 4. 17
     */
    public void deleteComAffVrscComCnnc(List<VendorGridDTO> dataList) throws Exception;

    /**
     * 판매 제휴사대행사 등록
     *
     * @param com the com
     * @return the string
     * @throws Exception the exception
     */
    public int insertAffCom(Com com) throws Exception;

    /**
     * 판매 제휴사 상세 조회
     *
     * @param com the com
     * @return the com
     * @throws Exception the exception
     */
    public List<Com> selectAffCom(Com com) throws Exception;


    /**
     * 제휴몰 상세정보 - 정산정보탭 - 브랜드별 수수료율 리스트.
     *
     * @param systemPK [설명]
     * @param vendorSearchDTO [설명]
     * @return the brand commission list
     * @throws Exception the exception
     * @since 2015. 4. 14
     */
    public Page<VendorListResult> getBrandCommissionList(SystemPK systemPK, VendorSearchDTO vendorSearchDTO) throws Exception ;

    /**
     * 제휴몰 상세정보 - 정산정보탭 - 브랜드별 수수료율 리스트 엑셀.
     *
     * @param systemPK [설명]
     * @param vendorSearchDTO [설명]
     * @return the brand commission list
     * @throws Exception the exception
     * @since 2015. 4. 14
     */
    public List<Map<String, Object>> getBrandCommissionListExcel(SystemPK systemPK, VendorSearchDTO vendorSearchDTO) throws Exception ;

    /**
     * 수수료율 이력 목록 조회.
     *
     * @param systemPK [설명]
     * @param vendorSearchDTO [설명]
     * @return the vendor list
     * @throws Exception the exception
     * @since 2015. 4. 14
     */
    public Page<ComBrndHistResult> getComBrndHistList(SystemPK systemPK, VendorSearchDTO vendorSearchDTO) throws Exception ;

    /**
     * 수수료율 이력 목록 조회 엑셀.
     *
     * @param systemPK [설명]
     * @param vendorSearchDTO [설명]
     * @return the vendor list
     * @throws Exception the exception
     * @since 2015. 4. 14
     */
    public List<Map<String, Object>> getComBrndHistListExcel(SystemPK systemPK, VendorSearchDTO vendorSearchDTO) throws Exception ;

    /**
     * 제휴사별 상품속성관리 조회.
     *
     * @param searchDTO [설명]
     * @return the ComDmstcDlvCstPlcResult
     * @throws Exception the exception
     * @since 2015. 5. 8
     */
    public Page<VendorListResult> selectComAffVrscComCnncList(VendorSearchDTO searchDTO) throws Exception;

    /**
     * 제휴사별 상품속성관리 조회 엑셀.
     *
     * @param searchDTO [설명]
     * @return the ComDmstcDlvCstPlcResult
     * @throws Exception the exception
     * @since 2015. 5. 8
     */
    public List<Map<String, Object>> selectComAffVrscComCnncListExcel(VendorSearchDTO searchDTO) throws Exception;


}
