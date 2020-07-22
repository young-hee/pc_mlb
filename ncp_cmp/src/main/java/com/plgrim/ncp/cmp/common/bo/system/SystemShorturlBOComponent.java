package com.plgrim.ncp.cmp.common.bo.system;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.sys.SysMallCnvrsUrl;
import com.plgrim.ncp.commons.data.FormSysMallDTO;
import com.plgrim.ncp.commons.data.SysMallDTO;
import com.plgrim.ncp.commons.result.MallSiteResult;
import com.plgrim.ncp.framework.data.SystemPK;

public interface SystemShorturlBOComponent {

    /*
     * ---------------------------------------------------------------------
     * public & interface method.
     * ---------------------------------------------------------------------
     */


    /**
     * Site Redirct 목록 조회.
     *
     * @param systemPK  [설명]
     * @param paramData [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public List<MallSiteResult> selectSysMallList(SystemPK systemPK, FormSysMallDTO paramData) throws Exception;

    /**
     * Site Redirct 목록 조회 개수.
     *
     * @param systemPK  [설명]
     * @param paramData [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public long selectSysMallListCount(SystemPK systemPK, FormSysMallDTO paramData) throws Exception;

    /**
     * Site Redirct 목록 조회 엑셀.
     *
     * @param systemPK  [설명]
     * @param paramData [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public List<Map<String, Object>> selectSysMallListExcel(SystemPK systemPK, FormSysMallDTO paramData) throws Exception;


    /**
     * 일괄 Siter 저장처리
     *
     * @param paramList
     * @throws Exception
     */
    public void updateSiter(List<SysMallDTO> paramList) throws Exception;


    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
