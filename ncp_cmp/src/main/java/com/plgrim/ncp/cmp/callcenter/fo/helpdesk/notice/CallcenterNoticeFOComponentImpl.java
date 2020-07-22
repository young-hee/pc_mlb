package com.plgrim.ncp.cmp.callcenter.fo.helpdesk.notice;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.biz.helpdesk.data.HelpdeskNoticeFoDTO;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskNoticeFoResult;
import com.plgrim.ncp.biz.helpdesk.service.HelpdeskService;
import com.plgrim.ncp.cmp.callcenter.fo.helpdesk.CallcenterNoticeFOComponent;
import com.plgrim.ncp.commons.result.CodeViewResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@Transactional(value = "transactionManager")
public class CallcenterNoticeFOComponentImpl extends AbstractComponent implements CallcenterNoticeFOComponent {

    @Autowired
    private HelpdeskService helpdeskService;

    /**
     * 공지 리스트
     *
     * @return
     */
    @Override
    public Page<HelpdeskNoticeFoResult> selectNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO, PageParam pageParam) throws Exception {
        return helpdeskService.selectNoticeList(helpdeskNoticeFoDTO, pageParam);
    }
    /**
     * 공지 리스트 for MobilePhone
     *
     * @return
     */
    @Override
    public List<HelpdeskNoticeFoResult> selectMpNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception {
        return helpdeskService.selectMpNoticeList(helpdeskNoticeFoDTO);
    }
    /**
     * 공지 리스트 for Popup
     *
     * @return
     */
    @Override
    public List<HelpdeskNoticeFoResult> selectPopupNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception {
    	return helpdeskService.selectPopupNoticeList(helpdeskNoticeFoDTO);
    }
    /**
     * 공지 타이틀 리스트
     */
    @Override
    public List<CodeViewResult> selectNotiTitleList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
    	return helpdeskService.selectNotiTitleList(helpdeskNoticeFoDTO);
    }
    /**
     * 공지사항 상세
     * */
    @Override
    public List<HelpdeskNoticeFoResult> selectNoticeDetail(SystemPK pk, HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
        return helpdeskService.selectNoticeDetail(helpdeskNoticeFoDTO);
    }

    /**
     * 공지사항 이벤트 상세
     * */
    @Override
    public List<HelpdeskNoticeFoResult> selectNoticeEvtDetail(SystemPK pk,HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
        return helpdeskService.selectNoticeEvtDetail(helpdeskNoticeFoDTO);
    }

    /**
     * 대표 공지 리스트
     */
    @Override
    public List<HelpdeskNoticeFoResult> selectNoticeRprstList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception {
        return helpdeskService.selectNoticeRprstList(helpdeskNoticeFoDTO);
    }

    /**
     * 고객센터 메인 공지 new5 리스트
     */
    @Override
    public List<HelpdeskNoticeFoResult> selectNoticeNew5List(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
        return helpdeskService.selectNoticeNew5List(helpdeskNoticeFoDTO);
    }
    /**
     * 고객센터 메인 공지 new3 리스트
     */
    @Override
    public List<HelpdeskNoticeFoResult> selectNoticeNew3List(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
    	return helpdeskService.selectNoticeNew3List(helpdeskNoticeFoDTO);
    }


    /** 이전글 보기 */
    @Override
    public List<HelpdeskNoticeFoResult> selectPreNotiSn(SystemPK pk,HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
        return helpdeskService.selectPreNotiSn(helpdeskNoticeFoDTO);
    }
    /** 다음글 보기 */
    @Override
    public List<HelpdeskNoticeFoResult> selectNextNotiSn(SystemPK pk,HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
        return helpdeskService.selectNextNotiSn(helpdeskNoticeFoDTO);
    }

    /** 이벤트 당첨 여부 */
    @Override
    public List<HelpdeskNoticeFoResult> selectEventCheck(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception {
        return helpdeskService.selectEventCheck(helpdeskNoticeFoDTO);
    }

}
