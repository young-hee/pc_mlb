/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 26
 */
package com.plgrim.ncp.cmp.callcenter.bo;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnslt;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetail;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTrans;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoPromscl;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.biz.callcenter.data.CallbackGridDTO;
import com.plgrim.ncp.biz.callcenter.data.CounselDTO;
import com.plgrim.ncp.biz.callcenter.data.CounselGridDTO;
import com.plgrim.ncp.biz.callcenter.data.CounselTransferBoardDTO;
import com.plgrim.ncp.biz.callcenter.data.CounselTransferDTO;
import com.plgrim.ncp.biz.callcenter.data.CsTemplateDTO;
import com.plgrim.ncp.biz.callcenter.data.CsTemplateGridDTO;
import com.plgrim.ncp.biz.callcenter.data.CsoCnsltClHistDTO;
import com.plgrim.ncp.biz.callcenter.data.EmailSmsTemplateDTO;
import com.plgrim.ncp.biz.callcenter.data.EmailSmsTemplateGridDTO;
import com.plgrim.ncp.biz.callcenter.data.FastUrlGridDTO;
import com.plgrim.ncp.biz.callcenter.data.InfCallBackDTO;
import com.plgrim.ncp.biz.callcenter.data.MemberBiasDTO;
import com.plgrim.ncp.biz.callcenter.data.MemberBiasGridDTO;
import com.plgrim.ncp.biz.callcenter.data.PersonalInfoDTO;
import com.plgrim.ncp.biz.member.result.MbrExtendResult;
import com.plgrim.ncp.framework.data.SystemPK;

public interface CallcenterCounselBOComponent {

    /**
     * 고객 상담 등록
     *
     * @param counselDTO
     * @param systemPK
     * @throws Exception
     */
    public void insertCounsel(CounselDTO counselDTO, SystemPK systemPK) throws Exception;

    /**
     * 업무 이관 등록
     *
     * @param counselTransferDTO
     * @throws Exception
     */
    public void insertCounselTransfer(CounselTransferDTO counselTransferDTO) throws Exception;


    /**
     * 고객서비스 바로가기 등록 / 수정
     *
     * @param createList
     * @param updateList
     * @throws Exception
     */
    public void insertFastUrl(List<FastUrlGridDTO> createList, List<FastUrlGridDTO> updateList) throws Exception;

    /**
     * 고객서비스 바로가기 삭제
     *
     * @param deleteList
     * @throws Exception
     */
    public void deleteFastUrl(List<FastUrlGridDTO> deleteList) throws Exception;

    /**
     * 업무 이관 게시판 등록
     *
     * @param counselTransferBoardDTO
     * @param files
     * @throws Exception
     */
    public void insertCounselTransferBoard(CounselTransferBoardDTO counselTransferBoardDTO, List<MultipartFile> files) throws Exception;

    public void insertCounselTransferBoardAns(CounselTransferBoardDTO counselTransferBoardDTO, List<MultipartFile> files) throws Exception;

    /**
     * 고객서비스 상담 메모 등록
     *
     * @param memberBiasDTO
     * @throws Exception
     */
    public void insertMemberBias(MemberBiasDTO memberBiasDTO) throws Exception;

    /**
     * 고객서비스 상담 메모 수정
     *
     * @param createList
     * @param updateList
     * @throws Exception
     */
    public void updateMemberBias(List<MemberBiasGridDTO> createList, List<MemberBiasGridDTO> updateList) throws Exception;

    /**
     * 업무 이관 게시판 답변 수정
     *
     * @param counselTransferBoardDTO
     * @param files
     * @throws Exception
     */
    public void updateCounselTransferBoardAns(CounselTransferBoardDTO counselTransferBoardDTO, List<MultipartFile> files) throws Exception;

    /**
     * 회원 조회 - FOR CS.
     *
     * @param loginId
     * @param memberId
     * @param b
     * @param mbrNo
     * @return
     * @throws Exception
     */
    public MbrExtendResult selectMemberInfo(String loginId, String memberId, boolean b, String mbrNo) throws Exception;

    /**
     * 멤버쉽 포인트 잔액 조회.
     *
     * @param erpNo
     * @return
     * @throws Exception
     */
    public long getMemberReserveRemainAmount(String erpNo) throws Exception;

    /**
     * 상담 템플릿 등록
     *
     * @param csTemplateDTO
     * @throws Exception
     */
    public void insertCsTemplate(CsTemplateDTO csTemplateDTO) throws Exception;

    /**
     * 상담 템플릿 수정(그리드)
     *
     * @param updateList
     * @throws Exception
     */
    public void updateCsTemplateGrid(List<CsTemplateGridDTO> updateList) throws Exception;

    /**
     * 상담 템플릿 수정
     *
     * @param csTemplateDTO
     * @throws Exception
     */
    public void updateCsTemplate(CsTemplateDTO csTemplateDTO) throws Exception;

    /**
     * Email / SMS 템플릿 등록
     *
     * @param emailSmsTemplateDTO
     * @throws Exception
     */
    public void insertEmailSmsTemplate(EmailSmsTemplateDTO emailSmsTemplateDTO) throws Exception;

    /**
     * Email / SMS 템플릿 수정
     *
     * @param emailSmsTemplateDTO
     * @throws Exception
     */
    public void updateEmailsmsTemplate(EmailSmsTemplateDTO emailSmsTemplateDTO) throws Exception;

    /**
     * Email / SMS 템플릿 수정(그리드)
     *
     * @param updateList
     * @throws Exception
     */
    public void updateEmailSmsTemplateGrid(List<EmailSmsTemplateGridDTO> updateList) throws Exception;

    /**
     * 개인정보 수정
     *
     * @param personalInfoDTO
     * @throws Exception
     */
    public void updatePersonalInfo(PersonalInfoDTO personalInfoDTO) throws Exception;

    public void updateCallbackGrid(List<CallbackGridDTO> updateList) throws Exception;

    /**
     * 고객서비스 상담 수정
     *
     * @param csoCnslt
     * @throws Exception
     */
    void updateCounselStatCompt(CsoCnslt csoCnslt) throws Exception;

    /**
     * 고객서비스 상담 이관 수정
     *
     * @param csoCnsltTrans
     * @throws Exception
     */
    void updateTransStatCompt(CsoCnsltTrans csoCnsltTrans) throws Exception;

    /**
     * 고객서비스 약속콜 수정
     *
     * @param csoPromscl
     * @throws Exception
     */
    void updatePromsclStatCompt(CsoPromscl csoPromscl) throws Exception;

    /**
     * 고객 상담 상세 등록
     *
     * @param counselDTO
     * @throws Exception
     */
    void insertCounselInDetail(CounselDTO counselDTO) throws Exception;

    /**
     * 고객 상담 삭제(그리드)
     *
     * @param deleteList
     * @throws Exception
     */
    void deleteCounselGrid(List<CounselGridDTO> deleteList) throws Exception;
    
    /**
     * 관리자 개인정보 동의 이력 조회
     *
     * @param systemPK
     * @param sasa
     * @throws Exception
     */
    public SysAdminStplatAgr selectSysAdminStplatAgr(SystemPK systemPK, SysAdminStplatAgr sasa) throws Exception;
    /**
     * 관리자 개인정보 동의 이력 등록
     *
     * @param systemPK
     * @param sasa
     * @throws Exception
     */
    public void insertSysAdminStplatAgr(SystemPK systemPK, SysAdminStplatAgr sasa) throws Exception;

    /**
     * 상담전화 히스토리 저장 CS 등록
     *
     * @param csoCnsltClHistDTO
     */
    void insertCtiCallInfo(CsoCnsltClHistDTO csoCnsltClHistDTO);

    /**
     * 고객상담 상세 미전시 처리
     *
     * @param csoCnsltDetail
     */
    void updateDisplayYn(CsoCnsltDetail csoCnsltDetail);

    /**
     * 고객상담 상세 삭제 처리
     *
     * @param csoCnsltDetail
     */
    void deleteTempMemo(CsoCnsltDetail csoCnsltDetail);

    /**
     * 고객상담 수정
     *
     * @param counselDTO
     * @throws Exception
     */
    void updateCounsel(CounselDTO counselDTO) throws Exception;

    void updateConfirmInPromiseCall(Map param) throws Exception;

    /**
     * 상담 이관 담당자/요청자 확인 처리
     *
     * @param param
     * @throws Exception
     */
    void updateConfirmInTransfer(Map param) throws Exception;

    void insertCallback(InfCallBackDTO infCallBackDTO) throws Exception;

    /**
     * 고객상담 상세 내용 수정
     *
     * @param csoCnsltDetail
     * @throws Exception
     */
    void updateCsoCnsltDetailCont(CsoCnsltDetail csoCnsltDetail) throws Exception;

    /**
     * P포인트 조회
     *
     * @param mbrNo
     * @return
     * @throws Exception
     */
    MbrWebpntHistExtend getPurpleCoin(MbrWebpntHist mbrWebpntHist) throws Exception;

    /**
     * 고객 메모 조회
     *
     * @param csoCnsltMemo
     * @return
     */
    public CsoCnsltMemo getCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo);

    /**
     * 고객 메모 수정
     *
     * @param csoCnsltMemo
     */
    public void updateCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo);

    /**
     * 고객 메모 등록
     *
     * @param csoCnsltMemo
     * @throws Exception
     */
    public void insertCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception;

    /**
     * 시스템 약관 조회
     *
     * @param systemPK
     * @param ss
     * @return
     * @throws Exception
     */
    public SysStplat selectSysStplat(SystemPK systemPK, SysStplat ss) throws Exception;

}