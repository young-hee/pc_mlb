/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 20
 */
package com.plgrim.ncp.cmp.vendor.bo;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcHist;
import com.plgrim.ncp.biz.vendor.data.VendorBrndSearchDTO;
import com.plgrim.ncp.biz.vendor.data.VendorGridDTO;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.result.*;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface VendorBOComponent {

    /**
     * 정산 정보 수정
     *
     * @param systemPK
     * @param com
     * @throws Exception
     */
    public void updateVendor(SystemPK systemPK, Com com) throws Exception;

    /**
     * AS 담당자정보 저장/수정
     *
     * @param vendorGridDTO
     * @throws Exception
     */
    public void updateAsAdmin(VendorGridDTO vendorGridDTO) throws Exception;


    /**
     * 배송비 정책 수정.
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param vendorGridDTO [설명]
     * @throws Exception the exception
     * @since 2015. 4. 28
     */
    public void updateComDmstcDlvCstPlc(VendorGridDTO vendorGridDTO) throws Exception;

    /**
     * 배송비정책 기본배송지 수정
     *
     * @param comDmstcDlvCstPlc
     * @throws Exception
     */
    public void updateComDmstcDlvCstPlcBaseDlv(ComDmstcDlvCstPlc comDmstcDlvCstPlc) throws Exception;


    /**
     * 배송비 정책 이력저장.
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param comDmstcDlvCstPlcHist [설명]
     * @since 2015. 4. 28
     */
    public void insertComDmstcDlvCstPlcHist(ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist) throws Exception;

    /**
     * 도서산간배송비 저장.
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param dataList [설명]
     * @since 2015. 4. 17
     */
    public void mergeDlvCstPlcExcp(List<VendorGridDTO> dataList) throws Exception;

    /**
     * 도서산간배송비 삭제.
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param dataList [설명]
     * @since 2015. 4. 17
     */
    public void deleteDlvCstPlcExcp(List<VendorGridDTO> dataList) throws Exception;

    /**
     * 업체 국내 배송비 정책 삭제
     *
     * @param dataList
     * @throws Exception
     */
    public void deleteComDmstcDlvCstPlc(List<VendorGridDTO> dataList) throws Exception;

    /**
     * 업체 브랜드 담당자 저장
     *
     * @param lists
     * @param loginId
     * @throws Exception
     */
    public void saveVendorBrandAdmin(List<ComChrger> lists, String loginId) throws Exception;


    /**
     * 업체 목록 조회.
     *
     * @param systemPK        [설명]
     * @param vendorSearchDTO [설명]
     * @return the vendor list
     * @throws Exception the exception
     * @since 2015. 4. 14
     */
    public Page<VendorListResult> getVendorPageList(SystemPK systemPK, VendorSearchDTO vendorSearchDTO) throws Exception;

    /**
     * 업체 목록 조회 엑셀.
     *
     * @param systemPK        [설명]
     * @param vendorSearchDTO [설명]
     * @return the vendor list excel
     * @throws Exception the exception
     * @since 2015. 5. 8
     */
    public List<Map<String, Object>> getVendorListExcel(SystemPK systemPK, VendorSearchDTO vendorSearchDTO) throws Exception;


    /**
     * 업체 상세 조회.
     *
     * @param systemPK        [설명]
     * @param vendorSearchDTO [설명]
     * @return the vendor
     * @throws Exception the exception
     * @since 2015. 4. 14
     */
    public VendorListResult getVendor(SystemPK systemPK, VendorSearchDTO vendorSearchDTO) throws Exception;

    /**
     * 배송비 정책 조회 콤보
     *
     * @param searchDTO
     * @return
     * @throws Exception
     */
    public List<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcCombo(VendorSearchDTO searchDTO) throws Exception;

    /**
     * 배송비 정책 리스트 조회
     *
     * @param searchDTO
     * @return
     * @throws Exception
     */
    public Page<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcList(VendorSearchDTO searchDTO) throws Exception;

    /**
     * 배송비 정책 조회.
     *
     * @param searchDTO [설명]
     * @return Page [설명]
     * @throws Exception the exception
     * @since 2015. 4. 28
     */
    public ComDmstcDlvCstPlcResult selectComDmstcDlvCstPlc(VendorSearchDTO searchDTO) throws Exception;

    /**
     * 배송비 정책 조회.(CS에서 업체의 기본 배송 정책에 관련 정보 출력용)
     *
     * @param searchDTO [설명]
     * @return Page [설명]
     * @throws Exception the exception
     * @since 2015. 4. 28
     */
    public List<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcFromCs(VendorSearchDTO searchDTO) throws Exception;

    /**
     * 도서산간배송비 조회.
     *
     * @param searchDTO [설명]
     * @return the ComDmstcDlvCstPlcResult
     * @throws Exception the exception
     * @since 2015. 5. 8
     */
    public Page<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcExcp(VendorSearchDTO searchDTO) throws Exception;

    /**
     * 도서산간배송비 조회 엑셀.
     *
     * @param searchDTO [설명]
     * @return the ComDmstcDlvCstPlcResult
     * @throws Exception the exception
     * @since 2015. 5. 8
     */
    public List<Map<String, Object>> selectComDmstcDlvCstPlcExcpExcel(VendorSearchDTO searchDTO) throws Exception;

    /**
     * 운영자 조회.
     *
     * @param searchDTO [설명]
     * @return the ComDmstcDlvCstPlcResult
     * @throws Exception the exception
     * @since 2015. 5. 8
     */
    public Page<SysAdminListResult> selectAdminList(VendorSearchDTO searchDTO) throws Exception;

    /**
     * 운영자 조회(페이징X)
     *
     * @param vendorSearchDTO
     * @return
     * @throws Exception
     */
    public List<SysAdminListResult> selectAdminInfoList(VendorSearchDTO vendorSearchDTO) throws Exception;


    /**
     * as 담당자 조회
     *
     * @param comChrger
     * @return
     * @throws Exception
     */
    public List<ComChrgerResult> selectComChrgerList(ComChrger comChrger) throws Exception;


    /**
     * [메서드 설명].
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param systemPK  [설명]
     * @param searchDTO [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 8
     */
    public List<VendorListResult> selectVendorAllList(SystemPK systemPK, VendorSearchDTO searchDTO) throws Exception;

    /**
     * 업체 브랜드 담당자 조회
     *
     * @param systemPK
     * @param comId
     * @param brndId
     * @return
     * @throws Exception
     */
    public List<ComChrger> selectComChargerList(SystemPK systemPK, String comId, String brndId) throws Exception;

    /**
     * 배송사 Url 구하기
     *
     * @param comDmstcDlvCstPlc
     * @return 선택된 배송업체의 url
     * @throws Exception
     */
    public String getDlvComUrl(ComDmstcDlvCstPlc comDmstcDlvCstPlc) throws Exception;


}
