package com.plgrim.ncp.cmp.member.bo;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.framework.data.SystemPK;

public interface MemberCustomerBOComponent {

    /**
     * 회원 상태 변경.
     *
     * @param systemPK
     * @param dto
     * @param loginId
     */
    public void modifyMemberStatusBy(SystemPK systemPK, MemberBoDTO dto, String loginId);

    /**
     * 회원 탈퇴.
     *
     * @param systemPK
     * @param mbr
     * @param loginId
     */
    public void modifyMemberTerminateBy(SystemPK systemPK, Mbr mbr, String loginId);


}
