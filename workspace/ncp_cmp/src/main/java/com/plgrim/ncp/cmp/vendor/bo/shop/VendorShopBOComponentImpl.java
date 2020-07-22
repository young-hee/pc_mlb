package com.plgrim.ncp.cmp.vendor.bo.shop;

import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.biz.vendor.data.VendorBrndRegDTO;
import com.plgrim.ncp.biz.vendor.data.VendorBrndSearchDTO;
import com.plgrim.ncp.biz.vendor.data.VendorGridDTO;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.result.SysShopResult;
import com.plgrim.ncp.biz.vendor.result.VendorBrndListResult;
import com.plgrim.ncp.biz.vendor.service.SysShopService;
import com.plgrim.ncp.biz.vendor.service.VendorBrndService;
import com.plgrim.ncp.cmp.vendor.bo.common.VendorBOComponentImpl;
import com.plgrim.ncp.cmp.vendor.bo.VendorShopBOComponent;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [클래스 설명]
 *
 *
 *
 * <ul>
 * <li> [기능1]
 * <li> [기능2]
 * </ul>.
 *
 * @author yoon.eun
 * @since 2015. 11. 10
 */
@Component
@Transactional(value = "transactionManager")
public class VendorShopBOComponentImpl extends VendorBOComponentImpl implements VendorShopBOComponent {

    @Autowired
    private VendorBrndService vendorBrndService;

    @Autowired
    private SysShopService sysShopService;


    /**
     * 브랜드 매장 등록
     *
     * @param vendorBrndRegDTO
     * @throws Exception
     */
    @Override
    public void insertSysShopBrnd(VendorBrndRegDTO vendorBrndRegDTO) throws Exception {
        vendorBrndService.insertSysShopBrnd(vendorBrndRegDTO);
    }

    /**
     * 브랜드 매장 수정
     *
     * @param vendorBrndRegDTO
     * @throws Exception
     */
    @Override
    public void updateSysShopBrnd(VendorBrndRegDTO vendorBrndRegDTO) throws Exception {
        vendorBrndService.updateSysShopBrnd(vendorBrndRegDTO);
    }

    /**
     * 매장 브랜드별 수수료율 저장
     *
     * @param lists
     * @param loginId
     * @throws Exception
     */
    @Override
    public void updateSysShopBrndFee(List<VendorGridDTO> lists, String loginId) throws Exception {
        VendorGridDTO vgDTO = new VendorGridDTO();
        Iterator<VendorGridDTO> iterator = lists.iterator();

        while (iterator.hasNext()) {
            vgDTO = iterator.next();
            SysShopBrnd sysShopBrnd = new SysShopBrnd();
            sysShopBrnd.setShopId(vgDTO.getSysShopBrnd().getShopId());
            sysShopBrnd.setErpBrndId(vgDTO.getSysShopBrnd().getErpBrndId());
            sysShopBrnd.setDlvShopFeeSectCd(vgDTO.getSysShopBrnd().getDlvShopFeeSectCd());
            sysShopBrnd.setPkupShopFeeSectCd(vgDTO.getSysShopBrnd().getPkupShopFeeSectCd());
            sysShopBrnd.setPkupShopLgsFeeSectCd(vgDTO.getSysShopBrnd().getPkupShopLgsFeeSectCd());

            //배송 매장 수수료
            if ("FIXAMT".equals(sysShopBrnd.getDlvShopFeeSectCd())) {//정액
                sysShopBrnd.setDlvShopFeeAmt(vgDTO.getDlvShopFeeUse());
            } else if ("FIXRT".equals(sysShopBrnd.getDlvShopFeeSectCd())) {//정률
                sysShopBrnd.setDlvShopFeeRt(vgDTO.getDlvShopFeeUse());
            }
            //픽업 매장 수수료
            if ("FIXAMT".equals(sysShopBrnd.getPkupShopFeeSectCd())) {//정액
                sysShopBrnd.setPkupShopFeeAmt(vgDTO.getPkupShopFeeUse());
            } else if ("FIXRT".equals(sysShopBrnd.getPkupShopFeeSectCd())) {//정률
                sysShopBrnd.setPkupShopFeeRt(vgDTO.getPkupShopFeeUse());
            }
            //픽업 매장 물류 수수료
            if ("FIXAMT".equals(sysShopBrnd.getPkupShopLgsFeeSectCd())) {//정액
                sysShopBrnd.setPkupShopLgsFeeAmt(vgDTO.getPkupShopLgsFeeUse());
            } else if ("FIXRT".equals(sysShopBrnd.getPkupShopLgsFeeSectCd())) {//정률
                sysShopBrnd.setPkupShopLgsFeeRt(vgDTO.getPkupShopLgsFeeUse());
            }

            // 수수료 업데이트
            sysShopService.updateShopBrandForFee(sysShopBrnd, loginId);

            //배송 매장 수수료 이력 저장
            if (sysShopBrnd.getDlvShopFeeSectCd() != null && sysShopBrnd.getDlvShopFeeSectCd() != "") {
                sysShopService.updateShopBrandForFeeDlvHist(sysShopBrnd, loginId);
            }
            //픽업 매장 수수료  이력 저장
            if (sysShopBrnd.getPkupShopFeeSectCd() != null && sysShopBrnd.getPkupShopFeeSectCd() != "") {
                sysShopService.updateShopBrandForFeePkuHist(sysShopBrnd, loginId);
            }
            //픽업 매장 물류 수수료 이력 저장
            if (sysShopBrnd.getPkupShopLgsFeeSectCd() != null && sysShopBrnd.getPkupShopLgsFeeSectCd() != "") {
                sysShopService.updateShopBrandForFeePkuLgsHist(sysShopBrnd, loginId);
            }
        }

    }

    /**
     * 브랜드 매장내역 리스트 수정
     *
     * @param updateList
     * @throws Exception
     */
    @Override
    public void updateVendorShopBrndList(List<VendorBrndListResult> updateList) throws Exception {
        vendorBrndService.updateVendorShopBrndList(updateList);
    }


    /**
     * 매장 브랜드 수수료율 조회
     *
     * @param systemPK
     * @param searchDTO
     * @return
     * @throws Exception
     */
    @Override
    public Page<SysShopResult> getShopBrandListForFee(SystemPK systemPK, VendorSearchDTO searchDTO) throws Exception {
        return sysShopService.selectShopBrandListForFee(searchDTO);
    }

    /**
     * 매장 브랜드 수수료율 엑셀 조회
     *
     * @param systemPK
     * @param searchDTO
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getShopBrandExcelForFee(SystemPK systemPK, VendorSearchDTO searchDTO) throws Exception {
        return sysShopService.selectShopBrandExcelForFee(searchDTO);
    }

    /**
     * 브랜드 매장내역 조회
     *
     * @param systemPK
     * @param vendorBrndSearchDTO
     * @return
     * @throws Exception
     */
    @Override
    public Page<VendorBrndListResult> getVendorBrndList(SystemPK systemPK, VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception {
        return vendorBrndService.selectVendorBrndList(vendorBrndSearchDTO);
    }

    /**
     * 브랜드 목록 조회(콤보박스)
     *
     * @return List<SysBrnd>
     * @throws Exception
     */
    @Override
    public List<SysBrnd> getSysBrndCombo() throws Exception {
        return vendorBrndService.selectSysBrndCombo();
    }

    /**
     * 브랜드 매장내역 엑셀
     *
     * @param systemPK
     * @param vendorBrndSearchDTO
     * @return List<Map       <       String       ,               Object>>
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getVendorBrndListExcel(SystemPK systemPK, VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception {
        return vendorBrndService.selectVendorBrndListExcel(vendorBrndSearchDTO);
    }

    /**
     * 매장조회
     *
     * @param systemPK
     * @param vendorBrndSearchDTO
     * @return
     * @throws Exception
     */
    @Override
    public List<VendorBrndListResult> getSysShop(SystemPK systemPK, VendorBrndSearchDTO vendorBrndSearchDTO) throws Exception {
        return sysShopService.selectSysShop(vendorBrndSearchDTO);
    }

    /**
     * 브랜드 매장 상세조회
     *
     * @param sysShopBrnd
     * @return
     * @throws Exception
     */
    @Override
    public VendorBrndListResult getSysShopBrand(SysShopBrnd sysShopBrnd) throws Exception {
        return vendorBrndService.selectSysShopBrand(sysShopBrnd);
    }


}
