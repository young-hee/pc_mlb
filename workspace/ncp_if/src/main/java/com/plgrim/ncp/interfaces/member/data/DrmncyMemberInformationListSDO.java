package com.plgrim.ncp.interfaces.member.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class DrmncyMemberInformationListSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 휴면 회원 리스트
     */
    @JsonProperty("SLEEP_MEMBER_LIST")
    private List<DrmncyMemberInformationSDO> sleepMemberList;
    
    /**
     * 휴면 해제 회원 리스트
     */
    @JsonProperty("RELEASE_DRMNCY_MEMBER_LIST")
    private List<MemberInformationSDO> releaseDrmncyMemberList;
}