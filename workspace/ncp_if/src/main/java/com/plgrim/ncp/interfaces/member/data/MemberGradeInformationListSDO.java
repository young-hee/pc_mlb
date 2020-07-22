package com.plgrim.ncp.interfaces.member.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class MemberGradeInformationListSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 회원 등급 리스트
     */
    @JsonProperty("MEMBER_GRADE_LIST")
    private List<MemberGradeInformationSDO> memberGradeList;
}