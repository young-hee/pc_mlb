package com.plgrim.ncp.interfaces.member.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class DropMemberInformationListSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 탈퇴 회원 리스트
     */
    @JsonProperty("DROP_MEMBER_LIST")
    private List<DropMemberInformationSDO> dropMemberList;
}