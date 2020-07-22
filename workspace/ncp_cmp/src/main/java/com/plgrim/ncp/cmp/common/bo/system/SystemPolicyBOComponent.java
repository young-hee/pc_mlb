package com.plgrim.ncp.cmp.common.bo.system;

import com.plgrim.ncp.commons.data.FormSysPolicyDTO;
import com.plgrim.ncp.commons.data.SysPolicyDTO;
import com.plgrim.ncp.commons.result.SysPolicyResult;

import java.util.HashMap;
import java.util.List;

public interface SystemPolicyBOComponent {
    /**
     * 정책 값 등록처리
     *
     * @param param
     * @return
     * @throws Exception
     */
    public int insertSysPlcVal(SysPolicyDTO param) throws Exception;

    /**
     * 정책 값 수정처리
     *
     * @param param
     * @return
     * @throws Exception
     */
    public int updateSysPlcVal(SysPolicyDTO param) throws Exception;


    /**
     * 정책 목록조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<SysPolicyResult> selectListPlc(FormSysPolicyDTO paramData) throws Exception;

    /**
     * 정책 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public SysPolicyResult selectPlcOne(FormSysPolicyDTO paramData) throws Exception;

    /**
     * 정책 값 목록 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public List<SysPolicyResult> selectListPlcVal(FormSysPolicyDTO paramData) throws Exception;

    /**
     * 정책 값 조회
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public SysPolicyResult selectPlcValOne(FormSysPolicyDTO paramData) throws Exception;

    /**
     * 정책값 유효성 CHECK
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> checkValidation(FormSysPolicyDTO paramData) throws Exception;

    /**
     * 과거 이력여부 체크
     *
     * @param paramData
     * @return
     * @throws Exception
     */
    public boolean isPastPlcVal(FormSysPolicyDTO paramData) throws Exception;
}
