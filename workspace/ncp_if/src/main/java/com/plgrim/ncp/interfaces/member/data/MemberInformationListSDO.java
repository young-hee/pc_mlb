package com.plgrim.ncp.interfaces.member.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class MemberInformationListSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    @JsonProperty("MEMBER_LIST")
    private List<MemberInformationSDO> memberList;

}