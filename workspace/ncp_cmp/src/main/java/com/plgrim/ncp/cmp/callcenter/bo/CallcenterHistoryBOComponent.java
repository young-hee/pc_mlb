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
package com.plgrim.ncp.cmp.callcenter.bo;

import com.plgrim.ncp.biz.callcenter.data.CsoCnsltClHistDTO;
import com.plgrim.ncp.biz.callcenter.data.OutCallGridDTO;

import java.util.List;

public interface CallcenterHistoryBOComponent {

    /**
     * 고객서비스 호전환 전화번호 내역 등록 / 수정
     *
     * @param createList
     * @param updateList
     * @throws Exception
     */
    public void insertOutCall(List<OutCallGridDTO> createList, List<OutCallGridDTO> updateList) throws Exception;

    /**
     * 고객서비스 호전환 전화번호 내역 삭제
     *
     * @param deleteList
     * @throws Exception
     */
    public void deleteOutCall(List<OutCallGridDTO> deleteList) throws Exception;

    /**
     * 상담전화 히스토리 저장 CS
     *
     * @param csoCnsltClHistDTO
     */
    void insertCtiCallInfo(CsoCnsltClHistDTO csoCnsltClHistDTO);


}