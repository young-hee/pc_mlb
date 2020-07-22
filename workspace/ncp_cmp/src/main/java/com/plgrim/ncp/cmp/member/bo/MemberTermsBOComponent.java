package com.plgrim.ncp.cmp.member.bo;

import java.util.List;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;
import com.plgrim.ncp.biz.helpdesk.data.HistoryInfoFoDTO;
import com.plgrim.ncp.biz.member.data.MemberAgreementDTO;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.helpdesk.result.HistoryInfoFoResult;
import com.plgrim.ncp.biz.member.result.MemberPsnlInfoFoResult;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * 회원개인정보 select interface
 */
public interface MemberTermsBOComponent {


    /**
     * 회원 선택적동의 리스트
     *
     * @param mbrNo 회원
     * @return List<MemberAgreementDTO>
     */
    public List<MemberAgreementDTO> selectMemberOptionalAgreeList(String mbrNo);

}
