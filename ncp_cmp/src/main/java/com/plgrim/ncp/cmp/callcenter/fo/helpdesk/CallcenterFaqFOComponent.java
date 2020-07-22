package com.plgrim.ncp.cmp.callcenter.fo.helpdesk;

import com.plgrim.ncp.biz.helpdesk.data.HelpdeskFaqFoDTO;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskFaqFoResult;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.commons.result.CodeViewResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CallcenterFaqFOComponent {

    /**
     * Faq 리스트
     *
     * @param helpdeskFaqFoDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<HelpdeskFaqFoResult> selectFaqList(HelpdeskFaqFoDTO helpdeskFaqFoDTO, PageParam pageParam) throws Exception;

    /**
     * 고객센터 메인 FAQ top10 리스트
     *
     * @param helpdeskFaqFoDTO
     * @return
     * @throws Exception
     */
    public List<HelpdeskFaqFoResult> selectFaqTop10List(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception;
    /**
     * 고객센터 메인 FAQ top5 리스트
     *
     * @param helpdeskFaqFoDTO
     * @return
     * @throws Exception
     */
    public List<HelpdeskFaqFoResult> selectFaqTop5List(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception;


    /**
     * 조회수 증가
     *
     * @param helpdeskFaqFoDTO
     * @throws Exception
     */
    public void updateFaqInqireCount(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception;

    /**
     * faq 타이틀 조회
     *
     * @param helpdeskFaqFoDTO
     * @return
     * @throws Exception
     */
    public List<CodeViewResult> selectFaqTitleList(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception;
    
    /**
     * faq 리스트 카운트
     * @param helpdeskFaqFoDTO
     * @return
     * @throws Exception
     */
    public long selectCountFaq(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception;

    /**
     * 1:1문의 등록
     * @param pk
     * @param mypageMtmFoDTO
     */
    public void insertCsInquiry(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO);
  
    
    /** 1:1문의 주문리스트	*/
    public List<MypageMtmFoResult> selectOrdGodList(SystemPK pk,MypageMtmFoDTO mypageMtmFoDTO) throws Exception; 
    
    /** 1:1문의 주문리스트	 페이징*/
    public Page<MypageMtmFoResult> selectInquiryOrdGodList(MypageMtmFoDTO mypageMtmFoDTO,PageParam pageParam) throws Exception; 
    /** 1:1문의 주문리스트 MO	 페이징*/
    public Page<MypageMtmFoResult> selectInquiryOrdGodMOList(MypageMtmFoDTO mypageMtmFoDTO,PageParam pageParam)throws Exception; 
    /**
   	 * 1:1주문상품 리스트 8월20일 이전 내용 확인
   	 */
     public boolean selectInquiryOrdMOListBefor0822(MypageMtmFoDTO mypageMtmFoDTO)throws Exception;
}

