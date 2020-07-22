package com.plgrim.ncp.cmp.common.bo.history;

import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.commons.data.FormSysAdmlogDTO;

import java.util.List;
import java.util.Map;

public interface HistoryMessageBOComponent {

    /**
     * 회원 알림톡 발송 내역 조회.
     * BO
     *
     * @param dto [설명]
     * @return List [설명]
     */
//    public NoticeListResult selectAlimTalkListForMember(MemberBoDTO dto);
//
//    /**
//     * 회원 알림톡 발송 내역 조회.
//     * BO
//     *
//     * @param dto [설명]
//     * @return List [설명]
//     */
//    public NoticeListResult selectAlimTalkListForMember(FormSysAdmlogDTO dto);

    /**
     * 회원 알림톡 발송 내역 엑셀 조회. (회원정보>발송내역)
     * BO
     *
     * @param dto
     * @return
     */
//    public List<Map<String, Object>> selectAlimTalkExcelListForMember(MemberBoDTO dto);

    /**
     * 회원 넷퍼시 발송 내역 엑셀 조회. (운영관리>메세지로그조회>홍보성발송내역)
     * BO
     *
     * @param dto
     * @return
     */
//    public List<Map<String, Object>> selectAlimTalkExcelListForMsgLog(FormSysAdmlogDTO dto);

}
