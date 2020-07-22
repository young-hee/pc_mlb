/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      ystam.kwon
 * @since       2015. 4. 17
 */
package com.plgrim.ncp.cmp.common.bo.operation;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.sys.SysChckHist;
import com.plgrim.ncp.commons.result.SysBatchResult;

public interface OperationBatchBOComponent {

    /**
     * 배치 리스트 조회 후 SMS alarm 전송
     *
     * @param smsYn
     * @return
     * @throws Exception
     */
    public List<SysBatchResult> callSysBatchAlarmSMS(String smsYn) throws Exception;

    /**
     * IF 서버에 SMS 전송요청
     *
     * @param parameter
     */
    public void sendBatchAlarmSMS(String parameter);

    /**
     * Elasticsearch 호출하여 쿼리 결과 return
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<SysBatchResult> getElasticsearchList(String fromDate, String toDate);

    /**
     * Elasticsearch 호출하여 쿼리 결과 return(Batch)
     *
     * @param smsYn
     * @return
     */
    public List<SysBatchResult> getElasticsearchBatchList(String smsYn);

    /**
     * weekly 모니터링 쿼리 결과 return
     *
     * @param dayType
     * @param smsYn
     * @return
     */
    public List<SysChckHist> getWeeklyMonitor(String dayType, String smsYn);

    /**
     * Elasticsearch 호출하여 쿼리 결과 return(기간조건)
     *
     * @param smsYn
     * @param period
     * @return
     */
    public List<SysBatchResult> getElasticsearchPeriodList(String smsYn, String period);

    /**
     * Elasticsearch 호출하여 쿼리 속도결과 return
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<SysBatchResult> getElasticsearchSpeedList(String fromDate, String toDate);
}