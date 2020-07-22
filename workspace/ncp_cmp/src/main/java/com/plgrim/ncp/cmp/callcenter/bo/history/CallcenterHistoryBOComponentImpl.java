/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 26
 */
package com.plgrim.ncp.cmp.callcenter.bo.history;

import com.plgrim.ncp.biz.callcenter.data.CsoCnsltClHistDTO;
import com.plgrim.ncp.biz.callcenter.data.OutCallGridDTO;
import com.plgrim.ncp.biz.callcenter.repository.MtmRepository;
import com.plgrim.ncp.biz.callcenter.service.AlarmService;
import com.plgrim.ncp.biz.callcenter.service.MainCounselService;
import com.plgrim.ncp.biz.callcenter.service.OutCallService;
import com.plgrim.ncp.cmp.callcenter.bo.CallcenterHistoryBOComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class CallcenterHistoryBOComponentImpl implements CallcenterHistoryBOComponent {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    @Autowired
    OutCallService outCallService;

    @Autowired
    MtmRepository mtmRepository;

    @Autowired
    MainCounselService mainCounselService;

    @Autowired
    AlarmService alarmService;

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
    public void insertOutCall(List<OutCallGridDTO> createList, List<OutCallGridDTO> updateList) throws Exception {

        outCallService.insertOutCall(createList);
        outCallService.updateOutCall(updateList);

    }

    @Override
    public void deleteOutCall(List<OutCallGridDTO> deleteList) throws Exception {

        outCallService.deleteOutCall(deleteList);
    }


    @Override
    public void insertCtiCallInfo(CsoCnsltClHistDTO csoCnsltClHistDTO) {
        mainCounselService.insertCtiCallInfo(csoCnsltClHistDTO);
    }
    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
