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

import com.plgrim.ncp.base.entities.datasource1.cso.*;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminStplatAgr;
import com.plgrim.ncp.biz.callcenter.data.*;
import com.plgrim.ncp.biz.callcenter.result.DetailMemberGoodsQnaResult;
import com.plgrim.ncp.biz.callcenter.result.MailTemplateResult;
import com.plgrim.ncp.biz.callcenter.result.MemberGoodsQnaResult;
import com.plgrim.ncp.biz.callcenter.result.SelectCnsltDetailResult;
import com.plgrim.ncp.biz.member.result.MbrExtendResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CallcenterInquiryBOComponent {

    /**
     * 상품문의
     *
     * @param memberGoodsQnaDTO
     * @throws Exception
     */
    public void insertMemberGoodsQnaAns(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception;

    /**
     * 상품 Q&A 만족도평가 추가 답변 등록 / 수정
     *
     * @param memberGoodsQnaDTO
     * @throws Exception
     */
    public void insertQnaAnsEvlAdminAns(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception;

    /**
     * FAQ 수정(그리드)
     *
     * @param updateList
     * @throws Exception
     */
    void updateFaqGrid(List<FaqGridDTO> updateList) throws Exception;

    /**
     * FAQ 등록
     *
     * @param faqDTO
     * @param files
     * @throws Exception
     */
    void insertFaq(FaqDTO faqDTO, List<MultipartFile> files) throws Exception;

    /**
     * FAQ 수정
     *
     * @param faqDTO
     * @param files
     * @throws Exception
     */
    void updateFaq(FaqDTO faqDTO, List<MultipartFile> files) throws Exception;

    /**
     * FAQ 삭제(그리드)
     *
     * @param deleteList
     * @throws Exception
     */
    void deleteFaqGrid(List<FaqGridDTO> deleteList) throws Exception;

    /**
     * 1:1 문의 등록
     *
     * @param mtmAddDTO
     * @throws Exception
     */
    void insertMtmInquiryAns(MtmAddDTO mtmAddDTO) throws Exception;

    /**
     * 1:1 문의 만족도평가 추가 답변 등록
     *
     * @param mtmAddDTO
     * @throws Exception
     */
    void insertInquiryAnsEvlAdminAns(MtmAddDTO mtmAddDTO) throws Exception;

    /**
     * 1:1 문의 만족도 불만유형 수정
     *
     * @param csoMtmInq
     * @throws Exception
     */
    void updateAnsDscnttTp(CsoMtmInq csoMtmInq) throws Exception;

    /**
     * FAQ 첨부파일 삭제
     *
     * @param faqDTO
     * @throws Exception
     */
    void deleteFaqAtchmnfl(FaqDTO faqDTO) throws Exception;

    /**
     * 1:1 문의 수정
     *
     * @param mtmAddDTO
     * @throws Exception
     */
    void updateInquiry(MtmAddDTO mtmAddDTO) throws Exception;

    /**
     * QNA 수정
     *
     * @param memberGoodsQnaDTO
     * @throws Exception
     */
    void updateQna(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception;

    /**
     * 상품 QNA 수정
     *
     * @param memberGoodsQnaDTO
     * @throws Exception
     */
    void updateComptAns(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception;

    /**
     * 상품 QNA 조회
     *
     * @param memberGoodsQnaSearchDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    List<MemberGoodsQnaResult> selectMemberGoodsQnaList(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO, PageParam pageParam) throws Exception;

    /**
     * 상품 QNA 상세 조회(고객정보 포함)
     *
     * @param memberGoodsQnaSearchDTO
     * @return
     * @throws Exception
     */
    DetailMemberGoodsQnaResult detailMemberGoodsQnaUserInfo(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) throws Exception;

    /**
     * 상품 QNA 상세 조회
     *
     * @param memberGoodsQnaSearchDTO
     * @return
     * @throws Exception
     */
    DetailMemberGoodsQnaResult detailMemberGoodsQna(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) throws Exception;

    /**
     * 1:1 문의 상태 조회
     *
     * @param mtmInqSn
     * @return
     * @throws Exception
     */
    String mtmInqStatCheck(Long mtmInqSn) throws Exception;

    /**
     * 1:1 문의 / 답변 조회
     *
     * @param mtmInqSn
     * @return
     * @throws Exception
     */
    public MailTemplateResult selectInqryInfo(String mtmInqSn) throws Exception;

    /**
     * 상품 QNA 문의 / 답변 조회
     *
     * @param csoGodInq
     * @return
     * @throws Exception
     */
    public MailTemplateResult selectMemberGoodsQnaInfo(CsoGodInq csoGodInq) throws Exception;

    /**
     * 상품 QNA 상세 조회
     *
     * @param godInqSn
     * @return
     * @throws Exception
     */
    List<SelectCnsltDetailResult> selectCnsltDetail(String godInqSn) throws Exception;

}