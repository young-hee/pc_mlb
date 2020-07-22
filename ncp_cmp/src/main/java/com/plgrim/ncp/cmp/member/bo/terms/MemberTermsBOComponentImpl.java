package com.plgrim.ncp.cmp.member.bo.terms;

import java.util.List;

import com.plgrim.ncp.cmp.member.bo.MemberTermsBOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.biz.member.data.MemberAgreementDTO;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoSelectService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "transactionManager")
@Component
@Slf4j
public class MemberTermsBOComponentImpl extends AbstractComponent implements MemberTermsBOComponent {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    @Autowired
    MemberPersonalInfoSelectService memberPersonalInfoSelectService;


    /*
     * ---------------------------------------------------------------------
     * Constructors.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * public & interface method.
     * ---------------------------------------------------------------------
     */

    @Override
    public List<MemberAgreementDTO> selectMemberOptionalAgreeList(String mbrNo) {
        return memberPersonalInfoSelectService.selectMemberOptionalAgreeList(mbrNo);
    }

}
