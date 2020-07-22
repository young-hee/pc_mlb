package com.plgrim.ncp.cmp.common.bo.history.message;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.cmp.common.bo.history.HistoryMessageBOComponent;
import com.plgrim.ncp.commons.data.FormSysAdmlogDTO;
import com.plgrim.ncp.commons.service.SysAdmlogService;
import com.plgrim.ncp.framework.commons.StringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@Transactional(value = "transactionManager")
public class HistoryMessageBOComponentIml extends AbstractComponent implements HistoryMessageBOComponent {

    @Autowired
    SysAdmlogService sysAdmlogService;
    /**
     * 회원 알림톡 발송 내역 조회. (회원관리>전체회원정보>발송내역)
     *
     * @param dto [설명]
     * @return List [설명]
     * @since 2015. 5. 4
     */
//    @Override
//    public NoticeListResult selectAlimTalkListForMember(MemberBoDTO dto) {
//        NoticeSDO noticeSDO = setNoticeSDO4Member(dto);
//        return sysAdmlogService.selectAlimTalkListForMember(noticeSDO);
//    }
//
//    /**
//     * 회원 알림톡 발송 내역 엑셀 조회. (회원정보>발송내역)
//     *
//     * @param dto [설명]
//     * @return List [설명]
//     * @since 2015. 5. 4
//     */
//    @Override
//    public List<Map<String, Object>> selectAlimTalkExcelListForMember(MemberBoDTO dto) {
//        NoticeSDO noticeSDO = setNoticeSDO4Member(dto);
//        return sysAdmlogService.selectAlimTalkExcelListForMember(noticeSDO);
//    }
//
//
//    /**
//     * 회원 알림톡 발송 내역 조회. (운영관리>메세지로그조회)
//     *
//     * @param dto [설명]
//     * @return List [설명]
//     */
//    public NoticeListResult selectAlimTalkListForMember(FormSysAdmlogDTO dto) {
//        NoticeSDO noticeSDO = setNoticeSDO4MsgLog(dto);
//
//        return sysAdmlogService.selectAlimTalkListForMember(noticeSDO);
//    }
//
//    /**
//     * 회원 넷퍼시 발송 내역 엑셀 조회. (운영관리>메세지로그조회>홍보성발송내역)
//     *
//     * @param dto [설명]
//     * @return List [설명]
//     * @since 2017. 8. 8
//     */
//    @Override
//    public List<Map<String, Object>> selectAlimTalkExcelListForMsgLog(FormSysAdmlogDTO dto) {
//        NoticeSDO noticeSDO = setNoticeSDO4MsgLog(dto);
//
//        return sysAdmlogService.selectAlimTalkExcelListForMsgLog(noticeSDO);
//    }
//
//    private NoticeSDO setNoticeSDO4Member(MemberBoDTO dto) {
//        String mobilNo = dto.getMobilNo();
//        if(StringService.isNotEmpty(mobilNo)){
//            mobilNo = dto.getMobilNo().replaceAll("-", "");
//        }
//
//        NoticeSDO noticeSDO = new NoticeSDO();
//        noticeSDO.setEtc1(dto.getMbr().getMbrNo());		// 회원번호
//        noticeSDO.setMobilNo(mobilNo);							// 수신자 번호
//        noticeSDO.setBeginIndex(dto.getBeginIndex());		//  페이징 시작
//        noticeSDO.setEndIndex(dto.getEndIndex());			// 페이징 끝
//        noticeSDO.setMaskingYn(dto.getMaskingYn());		// 마스킹 여부
//        noticeSDO.setSearchStartDate(dto.getSearchStartDate());	// 시작일시
//        noticeSDO.setSearchEndDate(dto.getSearchEndDate());		// 종료일시
//        return noticeSDO;
//    }
//
//    private NoticeSDO setNoticeSDO4MsgLog(FormSysAdmlogDTO dto) {
//        NoticeSDO noticeSDO = new NoticeSDO();
//        noticeSDO.setCallback(dto.getSearchAdsrPhone());		// 발신자 번호
//        noticeSDO.setMobilNo(dto.getSearchRcpnPhone());							// 수신자 번호
//        noticeSDO.setBeginIndex(dto.getBeginIndex());		//  페이징 시작
//        noticeSDO.setEndIndex(dto.getEndIndex());			// 페이징 끝
//        noticeSDO.setMaskingYn(dto.getMaskingYn());		// 마스킹 여부
//        noticeSDO.setSearchStartDate(dto.getSearchDtFrom());	// 시작일시
//        noticeSDO.setSearchEndDate(dto.getSearchDtTo());		// 종료일시
//        noticeSDO.setSearchListSendRstl(dto.getSearchListSendRstl());
//        noticeSDO.setSearchListGubun(dto.getSearchListGubun());
//        return noticeSDO;
//    }

}
