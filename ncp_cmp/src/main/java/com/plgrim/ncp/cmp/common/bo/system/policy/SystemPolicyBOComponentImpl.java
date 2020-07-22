package com.plgrim.ncp.cmp.common.bo.system.policy;

import com.plgrim.ncp.cmp.common.bo.system.SystemPolicyBOComponent;
import com.plgrim.ncp.commons.data.FormSysPolicyDTO;
import com.plgrim.ncp.commons.result.SysPolicyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.commons.data.SysPolicyDTO;
import com.plgrim.ncp.commons.service.SysPlcService;

import java.util.HashMap;
import java.util.List;

@Transactional(value = "transactionManager")
@Component
public class SystemPolicyBOComponentImpl extends AbstractComponent implements SystemPolicyBOComponent {
    @Autowired
    SysPlcService sysPlcService;

    /**
     * 정책 값 등록처리
     */
    public int insertSysPlcVal(SysPolicyDTO param) throws Exception {
        int rows = sysPlcService.insertSysPlcVal(param.getSysPlcVal());

        // 수정 값 MV 적용을 위해 refresh
        sysPlcService.refreshMviewPolicy();

        return rows;
    }

    /**
     * 정책 값 수정처리
     */
    public int updateSysPlcVal(SysPolicyDTO param) throws Exception {
        int rows = sysPlcService.updateSysPlcVal(param.getSysPlcVal());

        // 수정 값 MV 적용을 위해 refresh
        sysPlcService.refreshMviewPolicy();

        return rows;
    }

    public List<SysPolicyResult> selectListPlc(FormSysPolicyDTO paramData) throws Exception {

        return sysPlcService.selectListPlc(paramData);
    }

    public SysPolicyResult selectPlcOne(FormSysPolicyDTO paramData) throws Exception {
        return sysPlcService.selectPlcOne(paramData);
    }

    public List<SysPolicyResult> selectListPlcVal(FormSysPolicyDTO paramData) throws Exception {

        return sysPlcService.selectListPlcVal(paramData);
    }

    public SysPolicyResult selectPlcValOne(FormSysPolicyDTO paramData) throws Exception {
        return sysPlcService.selectPlcValOne(paramData);
    }

    public HashMap<String, Object> checkValidation(FormSysPolicyDTO paramData) throws Exception {

        return sysPlcService.checkValidation(paramData);
    }

    public boolean isPastPlcVal(FormSysPolicyDTO paramData) throws Exception {

        return sysPlcService.isPastPlcVal(paramData);
    }

}
