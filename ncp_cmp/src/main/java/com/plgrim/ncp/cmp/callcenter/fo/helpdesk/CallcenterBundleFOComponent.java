package com.plgrim.ncp.cmp.callcenter.fo.helpdesk;


import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.biz.member.data.BundleOrderFoDTO;
import com.plgrim.ncp.framework.data.SystemPK;

import java.util.List;

public interface CallcenterBundleFOComponent {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

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
     * 단체주문/제휴문의 등록
     *
     * @param pk
     * @param bundleOrderFoDTO
     * @return
     * @throws Exception
     */
    public void insertBundleOrder(SystemPK pk, BundleOrderFoDTO bundleOrderFoDTO) throws Exception;


    /**
     * 단체주문/제휴문의 개인정보 수집이용에 대한 동의
     *
     * @param sysStplat
     * @return
     * @throws Exception
     */
    public List<SysStplat> selectUserAgr(SysStplat sysStplat) throws Exception;



    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
