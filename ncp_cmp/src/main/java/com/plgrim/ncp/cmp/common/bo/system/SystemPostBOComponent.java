package com.plgrim.ncp.cmp.common.bo.system;

import com.plgrim.ncp.base.entities.datasource1.sys.SysFileUpload;
import com.plgrim.ncp.biz.zipcode.data.SysFileUploadBoDTO;
import com.plgrim.ncp.biz.zipcode.data.SysFileUploadExtend;

public interface SystemPostBOComponent {


    /**
     * 우편번호방식 변경
     *
     * @param plcVal [설명]
     * @return the vendor list
     * @throws Exception the exception
     * @since 2016. 11. 29
     */
    public void mergePostNoIntrlckMenmthd(String plcVal) throws Exception;

    /**
     * 시스템 파일 업로드 이력 조회.
     *
     * @param sysFileUpload
     * @return SysFileUploadExtend
     * @throws Exception
     */
    public SysFileUploadExtend selectSysFileUploadInfo(SysFileUpload sysFileUpload) throws Exception;

    /**
     * 시스템 파일 업로드 등록.
     *
     * @param sysFileUpload
     * @return sysFileUpload
     * @throws Exception
     */
    public SysFileUpload insertSysFileUpload(SysFileUpload sysFileUpload) throws Exception;

    /**
     * 시스템 파일 업로드 진행 상태 변경
     *
     * @param sysFileUploadBoDTO
     * @return String result
     * @throws Exception the exception
     * @since 2017. 12. 11
     */
    public String updateProgressStatusCode(SysFileUploadBoDTO sysFileUploadBoDTO) throws Exception;

    /**
     * 변경 승인 처리
     *
     * @param sysFileUploadBoDTO
     * @return String result
     * @throws Exception the exception
     * @since 2017. 12. 11
     */
    public String addrChangeApprove(SysFileUploadBoDTO sysFileUploadBoDTO) throws Exception;
}
