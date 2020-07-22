package com.plgrim.ncp.cmp.callcenter.fo.helpdesk;

import com.plgrim.ncp.biz.helpdesk.data.HelpdeskNoticeFoDTO;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskNoticeFoResult;
import com.plgrim.ncp.commons.result.CodeViewResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CallcenterNoticeFOComponent {

    /**
     * 공지사항 리스트
     *
     * @param helpdeskNoticeFoDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<HelpdeskNoticeFoResult> selectNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO, PageParam pageParam) throws Exception;
    /**
     * 공지사항 리스트 forMobile
     *
     * @param helpdeskNoticeFoDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public List<HelpdeskNoticeFoResult> selectMpNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception;

    /**
     * 공지사항 리스트 Popup
     *
     * @param helpdeskNoticeFoDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public List<HelpdeskNoticeFoResult> selectPopupNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception;

    /**
     * 대표공지 리스트
     *
     * @param helpdeskNoticeFoDTO
     * @return
     * @throws Exception
     */
    public List<HelpdeskNoticeFoResult> selectNoticeRprstList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception;

    /**
     * 공지사항 타이틀 리스트
     *
     * @param helpdeskNoticeFoDTO
     * @return
     * @throws Exception
     */
    public List<CodeViewResult> selectNotiTitleList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception;

    /**
     * 공지사항 상세
     *
     * @param pk
     * @param helpdeskNoticeFoDTO
     * @return
     * @throws Exception
     */
    public List<HelpdeskNoticeFoResult> selectNoticeDetail(SystemPK pk, HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception;

    /**
     * 공지사항 이벤트 상세
     *
     * @param pk
     * @param helpdeskNoticeFoDTO
     * @return
     * @throws Exception
     */
    public List<HelpdeskNoticeFoResult> selectNoticeEvtDetail(SystemPK pk, HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception;

    /**
     * 고객센터 메인 공지 new5 리스트
     *
     * @param helpdeskNoticeFoDTO
     * @return
     * @throws Exception
     */
    public List<HelpdeskNoticeFoResult> selectNoticeNew5List(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception;

    /**
     * 고객센터 메인 공지 new3 리스트
     *
     * @param helpdeskNoticeFoDTO
     * @return
     * @throws Exception
     */
    public List<HelpdeskNoticeFoResult> selectNoticeNew3List(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception;


    /**
     * 이전글보기
     *
     * @param pk
     * @param helpdeskNoticeFoDTO
     * @return
     * @throws Exception
     */
    public List<HelpdeskNoticeFoResult> selectPreNotiSn(SystemPK pk, HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception;

    /**
     * 다음글보기
     *
     * @param pk
     * @param helpdeskNoticeFoDTO
     * @return
     * @throws Exception
     */
    public List<HelpdeskNoticeFoResult> selectNextNotiSn(SystemPK pk, HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception;

    /**
     * 이벤트 당첨 여부
     *
     * @param helpdeskNoticeFoDTO
     * @return
     * @throws Exception
     */
    public List<HelpdeskNoticeFoResult> selectEventCheck(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception;

}
