package com.plgrim.ncp.cmp.common.bo.system;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.commons.data.FormSysExchgDTO;
import com.plgrim.ncp.commons.data.SysExchgDTO;
import com.plgrim.ncp.commons.result.SysExchgResult;
import com.plgrim.ncp.framework.data.SystemPK;

public interface SystemCurrencyBOComponent {

    /**
     * Exchg 저장처리
     *
     * @param paramData
     * @throws Exception
     */
    public void updateExchg(List<SysExchgDTO> paramData) throws Exception;

    /**
     * 환율 등록.
     *
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public void insertExchg(SysExchgDTO paramData) throws Exception;


    /**
     * 시스템 환율 목록 조회
     *
     * @param systemPk
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<SysExchgResult> selectSysExchgList(SystemPK systemPk, FormSysExchgDTO paramData) throws Exception;

    /**
     * 환율 목록 엑셀.
     *
     * @param systemPK  [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public List<Map<String, Object>> selectSysExchgListExcel(SystemPK systemPK, FormSysExchgDTO paramData) throws Exception;

    /**
     * 환율 목록 개수.
     *
     * @param systemPK  [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public long selectSysExchgListCount(SystemPK systemPK, FormSysExchgDTO paramData) throws Exception;

    /**
     * 환율 중복 개수.
     *
     * @param systemPK  [설명]
     * @param paramData [설명]
     * @throws Exception the exception
     * @since 2015. 5. 29
     */
    public long selectSysExchgDupCount(SystemPK systemPK, SysExchgDTO paramData) throws Exception;

}
