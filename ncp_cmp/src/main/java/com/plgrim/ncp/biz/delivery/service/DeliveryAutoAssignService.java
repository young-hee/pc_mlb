/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      th86.yang
 * @since       2015. 11. 25       
 */
package com.plgrim.ncp.biz.delivery.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.enums.DeliveryEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum;
import com.plgrim.ncp.biz.delivery.data.DeliveryInfErpDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.exception.DeliveryStatusException;
import com.plgrim.ncp.biz.delivery.repository.DeliveryAssignRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryComptMsgResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.order.repository.OrderBoCommandRepository;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.enums.InterfaceAdapterEnum;
import com.plgrim.ncp.interfaces.util.AdapterHeader;

import lombok.extern.slf4j.Slf4j;

/**
 * [클래스 설명]
 * <p/>
 * <p/>
 * <p/>
 * <ul>
 * <li> [기능1]
 * <li> [기능2]
 * </ul>.
 *
 * @author 
 * @since 2015. 4. 13
 */
@Slf4j
@Service
public class DeliveryAutoAssignService extends AbstractService {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    @Autowired
    DeliveryCommandRepository deliveryCommandRepository;

    @Autowired
    DeliverySelectRepository deliverySelectRepository;
    
    @Autowired
    DeliveryAssignRepository deliveryAssignRepository;
    
    @Autowired
    OrderBoCommandRepository orderBoCommandRepository;
    
    @Autowired
	DeliveryAssignService deliveryAssignService;
    
    @Autowired
    DeliveryInfErpService deliveryInfErpService;

    @Autowired
    MemberPersonalInfoCommandService memberPersonalInfoCommandService;

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

    /**
     * 배송매장 자동배정
     *
     * @param ordNo 주문번호
     * @param listDlivyDrctGodNo 물류출고지상품번호 리스트
     * @param asgnSectCd 배정구분코드, 자동 배정 : AUTO_ASGN 강제 배정 : ENFRC_ASGN 고객센터 강제 배정 : CC_ENFRC_ASGN 희망 배정 : HOPE_ASGN
     * @param batchYn 프로그램구분 (TRUE:배치/FALSE:배치외)
     * @return List<LgsAutoAsgnExtend> 기존 배송매장 정보와 신규 배송매장정보가 상이한 경우, 예약영수증 매장정보 변경위한 리스트
     * @throws Exception
     * @since 2016. 5. 2
     */
    /*
    public List<LgsAutoAsgnExtend> setAutoAssignDlvShop( String ordNo, List<String> paramListDlivyDrctGodNo,
                                      String asgnSectCd, boolean batchYn ) throws Exception {
        
         * 콤퍼넌트(DeliveryAutoAssignComponent.setAssignDlvShop)에서
         * 예약영수증 매장정보 변경 및 단품별 재고 수정을 위한 대상 리스트
         
        List<LgsAutoAsgnExtend> rtLgsAutoAsgnExt = new ArrayList<>();

        
         * 해당 '주문번호', '단품번호'를 이용해서 미배정 상품정보를 우선 최상위 브랜드로 그룹핑 처리
         * 조회된 '물류출고지시상품번호' 를 이용해서 그룹별로 '미배정 상품정보' 조회
         * 예)  RO201606274834826@RO201606274834827
         *      RO201606274834828@RO201606274834831@RO201606274834832@RO201606274834833
         *      RO201606274834829@RO201606274834830
         * - 해당 기능은 '배치'(batchYn='Y') 인 경우에만 사용
         
        List<List<String>> resultListByBrndGrp = new ArrayList<>();

        if ( batchYn ) {
            
             * 주문의 미배정 상품정보에 대해서 '브랜드그룹'으로 '물류출고지시번호'를 그룹핑
             
            List<String> listDlivyDrctNos = this.deliverySelectRepository.selectDlivyDrctGodNoByBrndGrp( ordNo );
            int idx = 0;
            for ( String dlivyDrctNos : listDlivyDrctNos ) {
                resultListByBrndGrp.add( Arrays.asList(dlivyDrctNos.split("[@]") ) );
                if (log.isInfoEnabled()) {
                    log.info("Auto assign. DlivyDrctGodNo by brand group[{}:{}/{}]", ++idx, ordNo, dlivyDrctNos);
                }
            }
        }
        else {
            
             * '강제배정' 등으로 '물류출고지시번호' 가 파라미터로 전달된 경우
             
            resultListByBrndGrp.add( paramListDlivyDrctGodNo );
        }

        for ( List<String> listDlivyDrctGodNoByGrp : resultListByBrndGrp ) {

            // 미배정 상품수량
            int notAssignGodCnt = 0;

            
             * 주문번호를 이용해서 해당 주문번호의 미배정 물류출고지시번호, ITM_NO 조회
             *  - 배치가 아닌 경우('batchYn' 으로 판단)에는 '물류출고지시상품번호'를 이용해서 조회
             
            LgsDlivyDrctGodExtend paramLgsDlivyDrctGod = new LgsDlivyDrctGodExtend();
            paramLgsDlivyDrctGod.setOrdNo(ordNo);
            paramLgsDlivyDrctGod.setDlivyDrctGodNoList(listDlivyDrctGodNoByGrp);
            paramLgsDlivyDrctGod.setBatchYn(batchYn);

            List<LgsDlivyDrctGodExtend> listLgsDlivyDrctGodExt = this.deliverySelectRepository.selectNotAssignGood(paramLgsDlivyDrctGod);

             
             * 미배정 상품이 존재하는 경우, '자동' 배정처리
             
            if (listLgsDlivyDrctGodExt != null && listLgsDlivyDrctGodExt.size() > 0) {

                
                 * '거절횟수'가 2회 이상인 경우에는 '결품처리'
                 *  - '거절횟수'가 2회 미만인 대상으로 처리할 수도 있지만,아래와 같은 경우를 대비해서 조회 후 거절회수 체크 및 '결품' 처리
                 *  - 1. '거절'2회->'결품' 이후
                 *    2. 물류센터로 강제배정 처리되어 '출고지시' 상태인 주문에 대해서
                 *    3. '물류결품처리'를 하는 경우에
                 *    4. '배송매장' 으로 재배정 처리되는 경우에 다시 '결품' 으로 변경
                 
                for (Iterator<LgsDlivyDrctGodExtend> itLgsDlivyDrctGod = listLgsDlivyDrctGodExt.iterator(); itLgsDlivyDrctGod.hasNext(); ) {
                    LgsDlivyDrctGodExtend lgsDlivyDrctGod = itLgsDlivyDrctGod.next();
                    
                    
                     * 2016.10.20 (shinkun)
                     * 강제배정시 배송매장 배정대상에 대해 송장정보 초기화 
                     
                    if( !batchYn ) {
                    	DeliveryOrderGoodDTO objDto = new DeliveryOrderGoodDTO();
                    	if ( null != lgsDlivyDrctGod.getDmstcWaybilNo() && !"".equals(lgsDlivyDrctGod.getDmstcWaybilNo()) ) {
                    		objDto.setOrdNo(lgsDlivyDrctGod.getOrdNo());
                    		objDto.setDlvPcupspTurn(lgsDlivyDrctGod.getDlvPcupspTurn());
                    		objDto.setDlvTurn(lgsDlivyDrctGod.getDlvTurn());
                    		objDto.setDmstcWaybilNo(lgsDlivyDrctGod.getDmstcWaybilNo());
                    		deliveryAssignService.initWayBillNo(objDto);
        				}
                    }

                    if (lgsDlivyDrctGod.getRejectCount() > 1) {
                        // 결품처리
                        LgsAutoAsgnExtend lgsAutoAsgnExtend = new LgsAutoAsgnExtend();
                        BeanUtils.copyProperties(lgsDlivyDrctGod, lgsAutoAsgnExtend);
                        lgsAutoAsgnExtend.setDlivyDrctQty(lgsDlivyDrctGod.getDlivyDrctQty().intValue());

                        rtLgsAutoAsgnExt.addAll(this.assignDlvShop(Collections.singletonList(lgsAutoAsgnExtend), asgnSectCd, true, batchYn));

                        if (log.isInfoEnabled()) {
                            log.info("Auto assign. Shortage processing.1[{}/{}]", ordNo, listDlivyDrctGodNoByGrp.toString());
                        }
                        // 조회 리스트에서 삭제
                        itLgsDlivyDrctGod.remove();
                    }
                }

                
                 * 위의 '거절횟수'로 인한 전체 결품 처리시 더이상 처리하지 않음
                 
                if (listLgsDlivyDrctGodExt.size() < 1) {
                    if (log.isInfoEnabled()) {
                        log.info("Auto assign. No non-assigned goods by 'Reject'.[{}/{}]", ordNo, listDlivyDrctGodNoByGrp.toString() );
                    }
                    continue;
                }

                
                 * 조회된 ITM_NO를 이용해 배정가능 배송매장 정보 조회
                 
                LgsDlivyDrctGodExtend param = listLgsDlivyDrctGodExt.get(0);

                
                 * 1. 세트상품의 '추가구성상품'으로 '동일품번'으로 주문(예, 정상세트 주문 시 동일 팬츠 추가 구입)
                 * 2. '동일품번'의 일반상품을 복수로 주문해서 배송지를 수량으로 분리
                 *
                 *  - '단품번호'(ITM_NO), '출고지시수량'(DLIVY_DRCT_QTY)에 대해서 중복제거 및 합계로 처리해야 함
                 *      1. '출고지시수량' 합계 - 'selectNotAssignGood' Query에서 DBMS 'SUM' 함수를 이용해서 조회
                 *      2. '단품번호' 중복 제거 - 'filterDuplLgsDlivyDrctGodExtend' 메소드 사용해서 제거
                 *  - '결품' 처리는 중복 제거 필터링 이전 리스트로 처리('물류출고지시번호' 기준)
                 *  - '배정'을 위한 조회('selectDlvShop4Assigned')는 중복제거 필터링 결과 리스트로 조회('단품번호' 기준)
                 *  - '배정' 시 이전에 필터링되어 제거된 중복리스트를 다시 추가해서 배정 처리('물류출고지시번호' 기준)
                 
                Map<String, List<LgsDlivyDrctGodExtend>> filteredMap = this.filterDuplLgsDlivyDrctGodExtend(listLgsDlivyDrctGodExt);

                
                 * '단품번호' 중복 제거된 리스트, '배정'을 위한 조회 시 사용
                 
                List<LgsDlivyDrctGodExtend> filteredNotAssignGoodList = filteredMap.get("FILTERED");
                
                 * 중복으로 제거된 리스트, 이후 '배정' 시 재추가해서 사용
                 
                List<LgsDlivyDrctGodExtend> removedLgsDlivyDrctGodExt = filteredMap.get("REMOVED");

                
                 * 해당 주문번호의 미배정 상품 수
                 
                notAssignGodCnt = filteredNotAssignGoodList.size();

                param.setLgsDlivyDrctGodExtendList(filteredNotAssignGoodList);
                param.setGodCnt(notAssignGodCnt);
                param.setBatchYn(batchYn);
                param.setAsgnSectCd(asgnSectCd);

                
                 * '주문번호', '단품번호' 를 이용해서 해당 상품의 배정가능 매장정보조회
                 * ordNo(주문번호), itmNoList(단품번호리스트), maxDlivyDrctQty(최대주문수량), gooCnt(미배정 단품수량)
                 
                List<LgsAutoAsgnExtend> listLgsAutoAsgn = this.deliverySelectRepository.selectDlvShop4Assigned(param);

                
                 * 배정가능 매장정보가 없는 경우, 결품처리 또는 단품별 재배정 처리
                 
                if (listLgsAutoAsgn == null || listLgsAutoAsgn.size() < 1) {

                    
                     * 단품인 경우 바로 결품처리
                     *  -  '동일품번'으로 인한 중복제거도 없는 경우
                     
                    if (notAssignGodCnt == 1) {
                        
                         * '단품번호' 중복제거 건 확인 및 복원 후 결품처리
                         
                        rtLgsAutoAsgnExt.addAll(this.assignDlvShop(this.restoreNotAssignGood4Shtg(param, removedLgsDlivyDrctGodExt)
                                , asgnSectCd, true, batchYn));

                        if (log.isInfoEnabled()) {
                            log.info("Auto assign. Shortage processing.2[{}/{}/{}/{}/{}]",
                                    ordNo, param.getDlivyDrctGodNo(), param.getItmNo(),
                                    param.getOrdGodTurn(), param.getDlivyDrctQty() );
                        }
                    }
                    else {
                        
                         * 1. 수정일자	: 2016-06-15
                         * 2. 수정자	    : 김재성 (jskim27)
                         * 3. 요청 SR NO	: #21726
                         * 4. 수정 내용	: 배송매장 자동배정 2차
                         *      - n개 충족매장 변경-->센터출고 제외한 나머지 주문을 브랜드 그룹으로 grouping
                         *          --> 브랜드 그룹 내 모든 상품 충족매장 우선검색 후 주문분리
                         

                        
                         * '브랜드그룹'으로 그룹핑한 결과 개수가 'notAssignGodCnt' 와 동일한 경우에는
                         * 그룹핑 결과가 없는 것으로 판단하고 각각 단품으로 재배정 처리
                         
                        if (!this.autoAssignByBrndGrp(param, filteredNotAssignGoodList, removedLgsDlivyDrctGodExt, rtLgsAutoAsgnExt)) {
                        
                         * 단품별 재배정 처리
                         
                            rtLgsAutoAsgnExt.addAll(this.assignDlvProc4Unit(ordNo, filteredNotAssignGoodList, removedLgsDlivyDrctGodExt, asgnSectCd, batchYn));
                        }
                    }
                }
                else {
                    
                     * 이전에 필터링되어 제거된 중복리스트가 존재하는 경우, 배정을 위해 재추가
                     
                    if (removedLgsDlivyDrctGodExt != null &&
                            removedLgsDlivyDrctGodExt.size() > 0) {
                        this.restoreDlvShop4Assigned(listLgsAutoAsgn, removedLgsDlivyDrctGodExt);

                    }

                    // 배정처리
                    rtLgsAutoAsgnExt.addAll(this.assignDlvShop(listLgsAutoAsgn, asgnSectCd, false, batchYn));
                }

            }
            else {
                StringBuffer dlivyDrctGodNos = new StringBuffer();
                if (log.isInfoEnabled()) {
                    log.info("Auto assign. No non-assigned goods.1[{}/{}]", ordNo, listDlivyDrctGodNoByGrp.toString());
                }

                DeliveryStatusException statusException = new DeliveryStatusException(null);
                statusException.setDirectMessage("Auto assign. No non-assigned goods. :: dlivyDrctGodNos : " + listDlivyDrctGodNoByGrp.toString());
                throw statusException;
            }
        }

        return rtLgsAutoAsgnExt;
    }
    */
    /*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

    /**
     * '브랜드그룹'으로 그룹핑한 결과 개수가 'notAssignGodCnt' 와 동일한 경우에는
     * 그룹핑 결과가 없는 것으로 판단하고 각각 단품으로 재배정 처리
     *
     * @param param 주문번호, 상품단품
     * @param listNotAssignGood 미재정상품정보, 출고지시수량 체크 위해 사용
     * @return 브랜드로 그룹핑이 되지 않는 경우 true
     * @throws Exception
     */
    /*
    private boolean autoAssignByBrndGrp(LgsDlivyDrctGodExtend param, List<LgsDlivyDrctGodExtend> listNotAssignGood,
                                        List<LgsDlivyDrctGodExtend> removedLgsDlivyDrctGodExt, List<LgsAutoAsgnExtend> rtLgsAutoAsgnExt )
            throws Exception {

        List<String> listItmNoResult = this.deliverySelectRepository.selectItmNosByBrndGrp(param );

        if (log.isInfoEnabled()) {
            List<String> itmNoList = new ArrayList<String>();
            for ( LgsDlivyDrctGodExtend ext : listNotAssignGood ) {
                itmNoList.add( ext.getItmNo() );
            }
            log.info("Auto assign. AutoAssignByBrndGrp [{}/{}/{}/{}]", param.getOrdNo(), itmNoList.toString(),
                    param.getGodCnt(), listItmNoResult != null ? listItmNoResult.size() : 0 );
        }

        
         * '브랜드그룹'으로 그룹핑한 결과 개수가 상품개수와 동일
         *  - 동일 브랜드그룹 없음, 단품으로 각각 재배정 처리 진행
         
        if ( listItmNoResult.size() == param.getGodCnt() ) {
            if (log.isInfoEnabled()) {
                log.info("Auto assign. AutoAssignByBrndGrp, 'Brand Group' as a result of the grouping number is the same as the number of items");
            }
            return false;
        }

        
         * 각각의 '브랜드그룹' 별로 배정처리
         *  - 예) IT201604051472983@IT201604141477875, IT201505070976012
         
        for ( String itmNos : listItmNoResult ) {
            if (log.isInfoEnabled()) {
                log.info("Auto assign. AutoAssignByBrndGrp itmNos :: [{}/{}]", param.getOrdNo(), itmNos );
            }

            List<String> listItmNo = Arrays.asList(itmNos.split("[@]"));

            
             * 주문번호(ordNo), 배치여부(batchYn) 정보는 파라미터로 전달됨
             
            List<LgsDlivyDrctGodExtend> notAssignGoodList = this.getNotAssignGoodList(listNotAssignGood, listItmNo);
            param.setLgsDlivyDrctGodExtendList(notAssignGoodList);

            
             * '브랜드그룹'으로 구분된 구성 상품에서 최대 출고지시수량
             
            param.setMaxDlivyDrctQty( this.getMaxDlivyDrctQty(listNotAssignGood, listItmNo) );
            
             * '브랜드그룹'으로 구분된 구성 상품 개수
             
            param.setGodCnt( listItmNo.size() );

            
             * '주문번호', '단품번호' 를 이용해서 해당 상품의 배정가능 매장정보조회
             * ordNo(주문번호), itmNoList(단품번호리스트), maxDlivyDrctQty(최대주문수량), godCnt(미배정 단품수량)
             
            List<LgsAutoAsgnExtend> listLgsAutoAsgn = this.deliverySelectRepository.selectDlvShop4Assigned(param);

            
             * 배정가능 매장정보가 없는 경우
             
            if (listLgsAutoAsgn == null || listLgsAutoAsgn.size() < 1) {

                
                 * 단품인 경우 바로 결품처리
                 
                if ( listItmNo.size() == 1 &&
                        ( removedLgsDlivyDrctGodExt == null || removedLgsDlivyDrctGodExt.size() == 0 ) ) {
                    
                     * 결품처리
                     
                    LgsAutoAsgnExtend lgsAutoAsgnExtend = new LgsAutoAsgnExtend();
                    BeanUtils.copyProperties(param, lgsAutoAsgnExtend);

                    
                     * 단품인 경우 '출고지시수량'은 '최대출고지시수량' 하고 동일함.
                     
                    lgsAutoAsgnExtend.setDlivyDrctQty(param.getMaxDlivyDrctQty());

                    
                     * '결품' 처리 시 조건으로 사용되는 물류출고지시번호, 주문상품순번 정보 세팅
                     
                    LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend = notAssignGoodList.get(0);

                    lgsAutoAsgnExtend.setDlivyDrctGodNo(lgsDlivyDrctGodExtend.getDlivyDrctGodNo());
                    lgsAutoAsgnExtend.setOrdGodTurn(lgsDlivyDrctGodExtend.getOrdGodTurn());
                    lgsAutoAsgnExtend.setItmNo(lgsDlivyDrctGodExtend.getItmNo());

                    rtLgsAutoAsgnExt.addAll(this.assignDlvShop(Collections.singletonList(lgsAutoAsgnExtend), param.getAsgnSectCd(),
                            true, param.isBatchYn()));

                    if (log.isInfoEnabled()) {
                        log.info("Auto assign. Shortage processing.3[{}::{}::{}]", param.getOrdNo(), param.getDlivyDrctGodNo(), listItmNo.toString());
                    }
                }
                else {
                    
                     * 단품별 재배정 처리
                     
                    rtLgsAutoAsgnExt.addAll(this.assignDlvProc4Unit(param.getOrdNo(), notAssignGoodList, removedLgsDlivyDrctGodExt, param.getAsgnSectCd(), param.isBatchYn()));
                }
            }
            else {
                
                 * 이전에 필터링되어 제거된 중복리스트가 존재하는 경우, 배정을 위해 재추가
                 
                if ( removedLgsDlivyDrctGodExt != null &&
                        removedLgsDlivyDrctGodExt.size() > 0 ) {
                    this.restoreDlvShop4Assigned(listLgsAutoAsgn, removedLgsDlivyDrctGodExt );

                }
                // 배정처리
                rtLgsAutoAsgnExt.addAll( this.assignDlvShop(listLgsAutoAsgn, param.getAsgnSectCd(), false, param.isBatchYn() ) );
            }
        }

        return true;
    }
    */
    /**
     * 단품별 재배정 처리
     * @param ordNo 주문번호
     * @param listLgsDlivyDrctGodExt 미배정상품정보 리스트
     * @param asgnSectCd
     * @param batchYn 배치여부
     * @throws Exception
     */
    /*
    private List<LgsAutoAsgnExtend> assignDlvProc4Unit( String ordNo, List<LgsDlivyDrctGodExtend> listNotAssignGood
            , List<LgsDlivyDrctGodExtend> removedLgsDlivyDrctGodExt, String asgnSectCd, boolean batchYn ) throws Exception {

        List<LgsAutoAsgnExtend> rtLgsAutoAsgnExt = new ArrayList<LgsAutoAsgnExtend>();

        // 미배정 패키지 구성품 리스트
        List<LgsDlivyDrctGodExtend> pckgList = new ArrayList<LgsDlivyDrctGodExtend>();

        for (LgsDlivyDrctGodExtend lgsDlivyDrctGod : listNotAssignGood) {
            if ( log.isInfoEnabled() ) {
                log.info("Auto assign. AssignDlvProc4Unit [{} :: {} :: {} :: {} :: {} :: {}]",
                        ordNo, lgsDlivyDrctGod.getDlivyDrctGodNo(), lgsDlivyDrctGod.getItmNo(), lgsDlivyDrctGod.getPckageGodTurn(), asgnSectCd, batchYn );
            }

            // 일반상품
            if (lgsDlivyDrctGod.getPckageGodTurn() == null ||
                    lgsDlivyDrctGod.getPckageGodTurn() == 0) {

                // 단품별 재배정 시도
                lgsDlivyDrctGod.setLgsDlivyDrctGodExtendList(Collections.singletonList(lgsDlivyDrctGod));
                lgsDlivyDrctGod.setGodCnt(1);
                lgsDlivyDrctGod.setBatchYn(batchYn);

                List<LgsAutoAsgnExtend> listLgsAutoAsgn4LgsItm = this.deliverySelectRepository.selectDlvShop4Assigned(lgsDlivyDrctGod);

                
                 * 단품별 배정가능 매장정보가 없는 경우
                 
                if (listLgsAutoAsgn4LgsItm == null || listLgsAutoAsgn4LgsItm.size() < 1) {

                    List<LgsAutoAsgnExtend> restoreLgsAutoAsgnExt = this.restoreNotAssignGood4Shtg(lgsDlivyDrctGod, removedLgsDlivyDrctGodExt);

                    
                     * '제휴주문'의 경우 [세트상품(A+B)+추가구성상품(B)] 주문등록 시 '추가구성상품(B)'이 일반상품으로 등록되는 경우
                     * 상품단품번호별 재고 체크를 하기 때문에 추가구성상품(B)이 재고부족으로 '결품' 처리되는 경우, 세트상품(B)도 결품처리되어,
                     * 세트상품(A)는 재고가 존재하면, 결과적으로 세트상품(A) 만 정상적으로 배정되는 문제발생
                     *
                     * 보유재고 체크를 위해 중복된 상품단품번호를 결품/배정 처리시 복원하는데, 이때 '패키지상품순번' 정보를 이용해서 세트 구성상품인 경우
                     * 별도로 관리해서 '세트상품' 을 별도로 결품/배정 처리
                     
                    for ( ListIterator<LgsAutoAsgnExtend> iterLgsAutoAsgnExt = restoreLgsAutoAsgnExt.listIterator(); iterLgsAutoAsgnExt.hasNext(); ) {
                        LgsAutoAsgnExtend asgnExt = iterLgsAutoAsgnExt.next();
                        if ( asgnExt.getPckageGodTurn() != null && asgnExt.getPckageGodTurn() > 0 ) {
                            lgsDlivyDrctGod.setPckageGodTurn(asgnExt.getPckageGodTurn());

                            
                             * 미배정 패키지 구성품을 위한 별도 처리하고 일반상품 결품처리에서 제외
                             
                            pckgList.add(lgsDlivyDrctGod);

                            iterLgsAutoAsgnExt.remove();
                        }
                    }

                    
                     * '단품번호' 중복제거 건 확인 및 복원 후 결품처리
                     
                    rtLgsAutoAsgnExt.addAll( this.assignDlvShop( restoreLgsAutoAsgnExt, asgnSectCd, true, batchYn));

                    if (log.isInfoEnabled()) {
                        log.info("Auto assign. Shortage processing.4[{}/{}/{}/{}/{}]",
                                ordNo, lgsDlivyDrctGod.getDlivyDrctGodNo(), lgsDlivyDrctGod.getItmNo(),
                                lgsDlivyDrctGod.getOrdGodTurn(), lgsDlivyDrctGod.getDlivyDrctQty() );
                    }
                }
                else {
                    
                     * 이전에 필터링되어 제거된 중복리스트가 존재하는 경우, 배정을 위해 재추가
                     
                    if ( removedLgsDlivyDrctGodExt != null &&
                            removedLgsDlivyDrctGodExt.size() > 0 ) {
                        this.restoreDlvShop4Assigned(listLgsAutoAsgn4LgsItm, removedLgsDlivyDrctGodExt );

                        
                         * '제휴주문'의 경우 [세트상품(A+B)+추가구성상품(B)] 주문등록 시 '추가구성상품(B)'이 일반상품으로 등록되는 경우
                         * 상품단품번호별 재고 체크를 하기 때문에 추가구성상품(B)이 재고부족으로 '결품' 처리되는 경우, 세트상품(B)도 결품처리되어,
                         * 세트상품(A)는 재고가 존재하면, 결과적으로 세트상품(A) 만 정상적으로 배정되는 문제발생
                         *
                         * 보유재고 체크를 위해 중복된 상품단품번호를 결품/배정 처리시 복원하는데, 이때 '패키지상품순번' 정보를 이용해서 세트 구성상품인 경우
                         * 별도로 관리해서 '세트상품' 을 별도로 결품/배정 처리
                         
                        for ( ListIterator<LgsAutoAsgnExtend> iterLgsAutoAsgnExt = listLgsAutoAsgn4LgsItm.listIterator(); iterLgsAutoAsgnExt.hasNext(); ) {
                            LgsAutoAsgnExtend asgnExt = iterLgsAutoAsgnExt.next();
                            if ( asgnExt.getPckageGodTurn() != null && asgnExt.getPckageGodTurn() > 0 ) {
                                lgsDlivyDrctGod.setPckageGodTurn(asgnExt.getPckageGodTurn());

                                
                                 * 미배정 패키지 구성품을 위한 별도 처리하고 일반상품 결풀처리에서 제외
                                 
                                pckgList.add(lgsDlivyDrctGod);

                                iterLgsAutoAsgnExt.remove();
                            }
                        }

                    }
                    // 배정처리
                    rtLgsAutoAsgnExt.addAll(this.assignDlvShop(listLgsAutoAsgn4LgsItm, asgnSectCd, false, batchYn));
                }
            }
            else {
                // 미배정 패키지 구성품을 위한 별도 처리
                pckgList.add(lgsDlivyDrctGod);
            }
        }

        // 미배정 패키지 구성품 재배정 시도
        List<LgsDlivyDrctGod> newPckgList = this.filterDuplPackageItem(pckgList);

        for (LgsDlivyDrctGod lgsDlivyDrctGod : newPckgList) {

            LgsDlivyDrctGodExtend param4Pckg = new LgsDlivyDrctGodExtend();
            param4Pckg.setOrdNo(lgsDlivyDrctGod.getOrdNo());
            param4Pckg.setPckageGodTurn(lgsDlivyDrctGod.getPckageGodTurn());
            param4Pckg.setBatchYn(batchYn);

            List<LgsDlivyDrctGodExtend> listLgsDlivyDrctGodExt4Pckg = this.deliverySelectRepository.selectNotAssignGood(param4Pckg);

            // 미배정 패키지 구성품이 상품이 존재하는 경우
            if (listLgsDlivyDrctGodExt4Pckg != null &&
                    listLgsDlivyDrctGodExt4Pckg.size() > 0) {

                LgsDlivyDrctGodExtend param4DlvShop = listLgsDlivyDrctGodExt4Pckg.get(0);

                
                 * 세트상품의 '추가구성상품'으로 '동일품번'으로 주문(예, 정상세트 주문 시 동일 팬츠 추가 구입)
                 *
                 *  - '단품번호'(ITM_NO), '출고지시수량'(DLIVY_DRCT_QTY)에 대해서 중복제거 및 합계로 처리해야 함
                 *      1. '출고지시수량' 합계 - 'selectNotAssignGood' Query에서 DBMS 'SUM' 함수를 이용해서 조회
                 *      2. '단품번호' 중복 제거 - 'filterDuplLgsDlivyDrctGodExtend' 메소드 사용해서 제거
                 *  - '결품' 처리는 중복 제거 필터링 이전 리스트로 처리('물류출고지시번호' 기준)
                 *  - '배정'을 위한 조회('selectDlvShop4Assigned')는 중복제거 필터링 결과 리스트로 조회('단품번호' 기준)
                 *  - '배정' 시 이전에 필터링되어 제거된 중복리스트를 다시 추가해서 배정 처리('물류출고지시번호' 기준)
                 
                Map<String, List<LgsDlivyDrctGodExtend>> filteredMap = this.filterDuplLgsDlivyDrctGodExtend(listLgsDlivyDrctGodExt4Pckg);

                
                 * '단품번호' 중복 제거된 리스트, '배정'을 위한 조회 시 사용
                 
                List<LgsDlivyDrctGodExtend> filteredNotAssignGoodList4Pckg = filteredMap.get("FILTERED");
                
                 * 중복으로 제거된 리스트, 이후 '배정' 시 재추가해서 사용
                 
                List<LgsDlivyDrctGodExtend> removedLgsDlivyDrctGodExt4Pckg = filteredMap.get("REMOVED");

                param4DlvShop.setLgsDlivyDrctGodExtendList(filteredNotAssignGoodList4Pckg);
                param4DlvShop.setGodCnt(filteredNotAssignGoodList4Pckg.size());
                param4DlvShop.setBatchYn(batchYn);

                
                 * '주문번호', '단품번호' 를 이용해서 세트상품의 배정가능 매장정보조회
                 
                List<LgsAutoAsgnExtend> listLgsAutoAsgn4Pckg = this.deliverySelectRepository.selectDlvShop4Assigned(param4DlvShop);

                
                 * 각각의 '세트상품'에 대해서 배정가능 매장정보가 없는 경우
                 
                if (listLgsAutoAsgn4Pckg == null || listLgsAutoAsgn4Pckg.size() < 1) {

                    
                     * '결품' 처리는 '단품번호' 중복 제거 필터링 이전의 리스트로 처리
                     
                    for (LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend : listLgsDlivyDrctGodExt4Pckg) {

                        rtLgsAutoAsgnExt.addAll( this.assignDlvShop( this.restoreNotAssignGood4Shtg(lgsDlivyDrctGodExtend, removedLgsDlivyDrctGodExt4Pckg)
                                , asgnSectCd, true, batchYn));

                        if (log.isInfoEnabled()) {
                            log.info("Auto assign. Shortage processing.5[{}/{}/{}/{}/{}]",
                                    ordNo, lgsDlivyDrctGodExtend.getDlivyDrctGodNo(), lgsDlivyDrctGodExtend.getItmNo(),
                                    lgsDlivyDrctGodExtend.getOrdGodTurn(), lgsDlivyDrctGodExtend.getDlivyDrctQty() );
                        }
                    }
                }
                else {
                    
                     * 이전에 필터링되어 제거된 중복리스트가 존재하는 경우, 배정을 위해 재추가
                     
                    if ( removedLgsDlivyDrctGodExt4Pckg != null &&
                            removedLgsDlivyDrctGodExt4Pckg.size() > 0 ) {
                        this.restoreDlvShop4Assigned(listLgsAutoAsgn4Pckg, removedLgsDlivyDrctGodExt4Pckg );
                    }

                    // 배정처리
                    rtLgsAutoAsgnExt.addAll(this.assignDlvShop(listLgsAutoAsgn4Pckg, asgnSectCd, false, batchYn));
                }

            }
            else {
                if (log.isInfoEnabled()) {
                    log.info("Auto assign. No non-assigned pckg goods.[{}/{}]", ordNo, lgsDlivyDrctGod.getPckageGodTurn());
                }

                DeliveryStatusException statusException = new DeliveryStatusException(null);
                statusException.setDirectMessage("Auto assign. No non-assigned goods. :: dlivyDrctGodNo : " + lgsDlivyDrctGod.getDlivyDrctGodNo());
                throw statusException;
            }
        }

        return rtLgsAutoAsgnExt;
    }
    */
    /**
     * 배송매장 자동 배정처리
     *
     * @param listLgsAutoAsgnExtend
     * @param asgnSectCd
     * @param isShtgRcept
     * @param batchYn
     * @throws Exception
     */
    /*
    private List<LgsAutoAsgnExtend> assignDlvShop( List<LgsAutoAsgnExtend> listLgsAutoAsgnExtend, String asgnSectCd,
                                boolean isShtgRcept, boolean batchYn ) throws Exception {

        List<LgsAutoAsgnExtend> rtLgsAutoAsgnExt = new ArrayList<LgsAutoAsgnExtend>();

        String dlivyDrctTgtYn   = "N";          // 출고지시대상 여부
        String dlivyDrctYn      = "N";          // 출고지시 여부
        String invAplYn         = "Y";          // 재고적용 여부
        String dlvLcId          = "FC01";       // 재고 저장위치
        String[] excptShop 		= { DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString(), "A345", "B031" }; //배송매장 운영변수 예외 매장
        String userId 			= batchYn ? "" : BOSecurityUtil.getLoginId();	// 세션ID

        // 배송상태코드 '결품접수' 여부에 따라 '출고지시' 또는 '결품접수'
        String dlvStatCd        = isShtgRcept ? "SHTG_RCEPT" : "DLIVY_DRCT";

        for ( LgsAutoAsgnExtend obj : listLgsAutoAsgnExtend ) {

            // '결품접수' 인 경우에는 '임시매장'(B031)로 처리
            if ( isShtgRcept ) {
                obj.setAsgnShopId("B031");
            }
            // #52785 - [모듈화-응답코드] 대응 상태 저장
            obj.setDlvStatCd(dlvStatCd);

            InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
            infOrdGodErpDstb.setOrdNo( obj.getOrdNo() );
            infOrdGodErpDstb.setOrdGodTurn( obj.getOrdGodTurn() );
            infOrdGodErpDstb.setDlivyDrctGodNo( obj.getDlivyDrctGodNo() );
            infOrdGodErpDstb.setDlivyDrctTgtYn( dlivyDrctTgtYn );
            infOrdGodErpDstb.setDlivyDrctYn( dlivyDrctYn );
            infOrdGodErpDstb.setMallId( obj.getMallId() );

            this.deliveryCommandRepository.updateInfDeliveryDirectTarget(infOrdGodErpDstb);

            LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
            lgsDdg.setDlvShopId( obj.getAsgnShopId() );         // 자동배정 '신규' 배송매장번호
            lgsDdg.setDlvShopDlivyLcId( dlvLcId );              // 배송매장출고위치ID
            lgsDdg.setDlvStatCd( dlvStatCd );                   // 배송상태코드
            lgsDdg.setDlivyDrctTgtYn( dlivyDrctTgtYn );         // 출고지시대상여부
            lgsDdg.setLgsItmYn("Y");                            // 단품여부
            if( ! batchYn ) {
            	lgsDdg.setUdterId(DeliveryEnum.ProcType4Hist.ENFRC_ASSIGN_SHOP.toString());
            }
            else {
                
                 * 'AUTO_ASSIGN_SHOP_BATCH' 값이 아래 'updateAssignShopInfo' 호출 시 조건으로 사용
                 *  따라서 변경 시 Query 구문도 변경 필요
                 
            	lgsDdg.setUdterId(DeliveryEnum.ProcType4Hist.AUTO_ASSIGN_SHOP_BATCH.toString());
            }
            lgsDdg.setDlivyDrctGodNo( obj.getDlivyDrctGodNo() );// 출고지시상품번호
            lgsDdg.setInvAplYn( invAplYn );                     // 재고적용여부
            lgsDdg.setAsgnSectCd( asgnSectCd );                 // 배정 구분 코드
            lgsDdg.setAsgnCount(1);                             // 배정횟수
            lgsDdg.setDlivyDrctQty( Long.valueOf(obj.getDlivyDrctQty()) );  // 출고지시수량

            
             * '배정', '결품' 처리 시 '배송상태코드'(DLV_STAT_CD)가 '배정대기'(DLV_WAIT)인 경우에만 UPDATE 처리
             *  - UPDATE 처리 개수가 1보다 작은 경우, 다른 프로세스에 의해 '배송상태코드'가 변경되는 상황으로 판단
             *  - 해당 주문건에 대해서는 Rollback 처리
             
            if ( this.deliveryCommandRepository.updateAssignShopInfo(lgsDdg) < 1 ) {
                DeliveryStatusException statusException = new DeliveryStatusException(null);
                statusException.setDirectMessage("Auto assign. The 'updateAssignShopInfo' failed.[" + obj.getOrdNo() + "]");
                throw statusException;
            }

            if ( log.isInfoEnabled() ) {
                log.info("Auto assign 1.[{} :: {} :: {} :: {} :: {} :: {} :: {} :: {} :: {} :: {} :: {}:: {}]",
                        obj.getOrdNo(), obj.getOrdGodTurn(), obj.getDlivyDrctGodNo(), obj.getItmNo(), obj.getDlvShopId(),
                        obj.getAsgnShopId(), obj.getDlivyDrctQty(), obj.getAsgnPrioRankSectCd(), dlvStatCd, asgnSectCd, obj.getMallId(), isShtgRcept );
            }


            // 배송매장 및 배송상태 정보 변경시 출고지시 변경이력 추가
            if ( ! StringService.equalsIgnoreCase( obj.getDlvShopId(), obj.getAsgnShopId() ) ||
                    ! dlvStatCd.equals( obj.getDlvStatCd() ) ) {
                //출고지시상품 이력 등록
                LgsDlivyDrctGodHist lgsDdgHist = new LgsDlivyDrctGodHist();
                if( ! batchYn ) {
                	lgsDdgHist.setRegtrId(userId);
                	lgsDdgHist.setUdterId(DeliveryEnum.ProcType4Hist.ENFRC_ASSIGN_SHOP.toString());
                } else {
                	lgsDdgHist.setRegtrId(DeliveryEnum.ProcType4Hist.AUTO_ASSIGN_SHOP_BATCH.toString());
                	lgsDdgHist.setUdterId(DeliveryEnum.ProcType4Hist.AUTO_ASSIGN_SHOP_BATCH.toString());
                }
                lgsDdgHist.setDlivyDrctGodNo(obj.getDlivyDrctGodNo());

                this.deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDdgHist);

                if ( log.isInfoEnabled() ) {
                    log.info("Auto assign 2.Creating lgs_dlivy_drct_god_hist data[{}/{}]", obj.getOrdNo(),obj.getDlivyDrctGodNo() );
                }
            }
            
            
             * 기존 배송매장 정보와 신규 배송매장정보가 상이한 경우
             *  - 예약영수증 매장정보 변경
             
            if ( ! StringService.equalsIgnoreCase( obj.getDlvShopId(), obj.getAsgnShopId() ) ) {

                
                 * 기존 배송매장 정보와 신규 배송매장정보가 상이한 경우
                 *  - 예약영수증 매장정보 변경하기 위해 리턴할 리스트 생성
                 *  - 트랜젝션관리를 위해 DeliveryAutoAssignComponentImpl.setAssignDlvShop 메소드에서
                 *    예약영수증변경 및 재고 처리
                 
                obj.setInvAplYn( lgsDdg.getInvAplYn() );
                rtLgsAutoAsgnExt.add( obj );
            }
        }

        return rtLgsAutoAsgnExt;
    }
    */
    /**
     * 패키지 묶음 중복건 단일 처리
     * @param list
     * @return
     * @throws Exception
     */    
    private List<LgsDlivyDrctGod> filterDuplPackageItem(List<LgsDlivyDrctGodExtend> list) throws Exception {
        List<LgsDlivyDrctGod> retList = new ArrayList<LgsDlivyDrctGod>();
        List<LgsDlivyDrctGod> tempList = new ArrayList<LgsDlivyDrctGod>();

        for (LgsDlivyDrctGodExtend obj : list) {
            LgsDlivyDrctGod lgsDlivyDrctGod = new LgsDlivyDrctGod();
            lgsDlivyDrctGod.setOrdNo(obj.getOrdNo());
            lgsDlivyDrctGod.setPckageGodTurn(obj.getPckageGodTurn());
            tempList.add(lgsDlivyDrctGod);
        }

        HashSet<LgsDlivyDrctGod> hs = new HashSet<LgsDlivyDrctGod>(tempList);
        Iterator<LgsDlivyDrctGod> it = hs.iterator();
        while(it.hasNext()) {
            retList.add(it.next());
        }

        return retList;
    }

    /**
     * '브랜드그룹'으로 구분된 구성 상품에서 최대 출고지시수량 조회
     * @param listNotAssignGood
     * @param listItmNo
     * @return int 그룹핑된 미배정 상품리스트의 '출고지시수량' 중 최대값
     * @throws Exception
     */
    private int getMaxDlivyDrctQty(List<LgsDlivyDrctGodExtend> listNotAssignGood, List<String> listItmNo )
            throws Exception {
        List<Integer> listMaxDlivyDrctQty = new ArrayList<Integer>();

        for ( LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend : listNotAssignGood ) {
            if ( listItmNo.contains( lgsDlivyDrctGodExtend.getItmNo() ) ) {
                /*
                 * 동일 단일품번의 '출고지시수량 합계'를 '출고지시수량'으로 세팅
                 */
                listMaxDlivyDrctQty.add( lgsDlivyDrctGodExtend.getSumDlivyDrctQty().intValue() );
            }
        }

        return Collections.max( listMaxDlivyDrctQty );
    }

    /**
     * '브랜드그룹'으로 구분된 미배정 상품 리스트 재조회
     * @param listNotAssignGood
     * @param listItmNo
     * @return
     * @throws Exception
     */
    private List<LgsDlivyDrctGodExtend> getNotAssignGoodList(List<LgsDlivyDrctGodExtend> listNotAssignGood, List<String> listItmNo )
            throws Exception {
        List<LgsDlivyDrctGodExtend> notAssignGoods = new ArrayList<LgsDlivyDrctGodExtend>();

        for ( LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend : listNotAssignGood ) {
            if ( listItmNo.contains( lgsDlivyDrctGodExtend.getItmNo() ) ) {
                notAssignGoods.add( lgsDlivyDrctGodExtend );
            }
        }

        return notAssignGoods;
    }

    /**
     * 세트상품의 동일품번 추가구성상품에 대한 처리로 동일 ITM_NO 제거
     * @param listLgsDlivyDrctGodExt
     * @return 중목 제거된 ITM_NO 리스트
     * @throws Exception
     */
    private List<String> filterDuplItemNo(List<LgsDlivyDrctGodExtend> listLgsDlivyDrctGodExt)
        throws Exception {
        List<String> listItmNo = new ArrayList<String>();

        for (LgsDlivyDrctGodExtend lgsDlivyDrctGod : listLgsDlivyDrctGodExt) {
            listItmNo.add(lgsDlivyDrctGod.getItmNo());
        }

        // remove dulplicate item_no
        Set<String> itemNoWithoutDuplicates = new LinkedHashSet<String>(listItmNo);

        listItmNo.clear();
        listItmNo.addAll( itemNoWithoutDuplicates );

        return listItmNo;
    }

    /**
     * 세트상품의 동일품번 추가구성상품에 대한 처리로 동일 LgsDlivyDrctGodExtend 제거
     * @param listLgsDlivyDrctGodExt 중복제거할 대상 리스트
     * @return 중목 제거된 LgsDlivyDrctGodExtend 리스트
     * @throws Exception
     */
    private Map<String, List<LgsDlivyDrctGodExtend>> filterDuplLgsDlivyDrctGodExtend(List<LgsDlivyDrctGodExtend> listLgsDlivyDrctGodExt)
            throws Exception {
        // 제거된 리스트, 이후 '배정' 처리지 재사용
        List<LgsDlivyDrctGodExtend> removedLgsDlivyDrctGodExt = new ArrayList<LgsDlivyDrctGodExtend>();

        List<String> listItmNo = new ArrayList<String>();

        for ( Iterator<LgsDlivyDrctGodExtend> itLgsDlivyDrctGod = listLgsDlivyDrctGodExt.iterator(); itLgsDlivyDrctGod.hasNext(); ) {
            LgsDlivyDrctGodExtend lgsDlivyDrctGod = itLgsDlivyDrctGod.next();
            /*
             * 동일 'ITM_NO'가 이미 존재하는 경우
             */
            if ( listItmNo.contains( lgsDlivyDrctGod.getItmNo() ) ) {
                /*
                 * 이후 '배정' 처리지 재사용하기 위해서 저장
                 */
                removedLgsDlivyDrctGodExt.add(lgsDlivyDrctGod);
                /*
                 * 조회 리스트에서 삭제
                 */
                itLgsDlivyDrctGod.remove();

                if (log.isInfoEnabled()) {
                    log.info("Auto assign. 'ITM_NO' deduplication.[{}/{}/{}]",
                            lgsDlivyDrctGod.getOrdNo(), lgsDlivyDrctGod.getDlivyDrctGodNo(),lgsDlivyDrctGod.getItmNo());
                }
            }
            else {
                listItmNo.add( lgsDlivyDrctGod.getItmNo() );
            }
        }

        Map<String, List<LgsDlivyDrctGodExtend>> rtMap = new HashMap();
        rtMap.put("FILTERED", listLgsDlivyDrctGodExt);
        rtMap.put("REMOVED", removedLgsDlivyDrctGodExt);

        return rtMap;
    }

    /**
     * 'ITM_NO' 중복으로 인해 제거된 리스트를 '배정' 대상 리스트로 복원
     *
     * @param listLgsAutoAsgn 배정대상 리스트
     * @param removedLgsDlivyDrctGodExt 'ITM_NO' 중복으로 인해 제거된 리스트
     * @throws Exception
     */
    /*
    private void restoreDlvShop4Assigned( List<LgsAutoAsgnExtend> listLgsAutoAsgn
            , List<LgsDlivyDrctGodExtend> removedLgsDlivyDrctGodExt) throws Exception {

        Set<LgsAutoAsgnExtend> restoreSet = new HashSet<>();

        for ( ListIterator<LgsDlivyDrctGodExtend> iterRemoved = removedLgsDlivyDrctGodExt.listIterator(); iterRemoved.hasNext();) {
            LgsDlivyDrctGodExtend lgsDlivyDrctGodExtend = iterRemoved.next();

            ListIterator<LgsAutoAsgnExtend> iter = listLgsAutoAsgn.listIterator();

            while ( iter.hasNext() ) {
                LgsAutoAsgnExtend dupLgsAutoAsgnExtend = iter.next();
                if ( StringService.equalsIgnoreCase( dupLgsAutoAsgnExtend.getItmNo(), lgsDlivyDrctGodExtend.getItmNo() ) ) {
                    
                     * 복원할 LgsAutoAsgnExtend 오브젝트
                     
                    LgsAutoAsgnExtend addLgsAutoAsgnExtend = new LgsAutoAsgnExtend();
                    BeanUtils.copyProperties(dupLgsAutoAsgnExtend, addLgsAutoAsgnExtend);

                    
                     * '물류출고지시번호', '출고지시수량', '주문상품순번' 정보 재설정
                     *  - 이외의 정보는 기존 동일 품번의 정보 사용
                     
                    addLgsAutoAsgnExtend.setDlivyDrctGodNo( lgsDlivyDrctGodExtend.getDlivyDrctGodNo());
                    addLgsAutoAsgnExtend.setDlivyDrctQty( lgsDlivyDrctGodExtend.getDlivyDrctQty().intValue() );
                    addLgsAutoAsgnExtend.setOrdGodTurn( lgsDlivyDrctGodExtend.getOrdGodTurn() );

                    restoreSet.add(addLgsAutoAsgnExtend);

                    if (log.isInfoEnabled()) {
                        log.info("Auto assign. Restore 'ITM_NO'.[{}/{}/{}/{}/{}/{}]",
                                lgsDlivyDrctGodExtend.getOrdNo(), lgsDlivyDrctGodExtend.getDlivyDrctGodNo(), lgsDlivyDrctGodExtend.getItmNo(),
                                lgsDlivyDrctGodExtend.getOrdGodTurn(), lgsDlivyDrctGodExtend.getPckageGodTurn(), lgsDlivyDrctGodExtend.getDlivyDrctQty());
                    }
                }
            }
        }
        
         * 복원된 LgsAutoAsgnExtend 추가
         
        listLgsAutoAsgn.addAll(restoreSet);
    }
    */

    /**
     * '결품처리'를 위한 '단품번호' 중복제거 건 확인 및 복원
     *
     * @param lgsDlivyDrctGod
     * @param removedLgsDlivyDrctGodExt
     * @return
     * @throws Exception
     */
    /*
    private List<LgsAutoAsgnExtend> restoreNotAssignGood4Shtg( LgsDlivyDrctGodExtend lgsDlivyDrctGod
            , List<LgsDlivyDrctGodExtend> removedLgsDlivyDrctGodExt) throws Exception {

        List<LgsAutoAsgnExtend> restoreNotAssignGood = new ArrayList<LgsAutoAsgnExtend>();
        // '결품' 처리 위한 기존 '단품번호'
        LgsAutoAsgnExtend lgsAutoAsgnExtend = new LgsAutoAsgnExtend();
        BeanUtils.copyProperties(lgsDlivyDrctGod, lgsAutoAsgnExtend);
        lgsAutoAsgnExtend.setDlivyDrctQty(lgsDlivyDrctGod.getDlivyDrctQty().intValue());

        restoreNotAssignGood.add( lgsAutoAsgnExtend );

        for ( ListIterator<LgsDlivyDrctGodExtend> iterRemoved = removedLgsDlivyDrctGodExt.listIterator(); iterRemoved.hasNext();) {
            LgsDlivyDrctGodExtend removedExt = iterRemoved.next();

            if ( StringService.equalsIgnoreCase( removedExt.getItmNo(), lgsDlivyDrctGod.getItmNo() ) ) {

                // 중복제거된 '단품번호' 복원
                lgsAutoAsgnExtend = new LgsAutoAsgnExtend();
                BeanUtils.copyProperties(removedExt, lgsAutoAsgnExtend);
                lgsAutoAsgnExtend.setDlivyDrctQty(removedExt.getDlivyDrctQty().intValue());

                restoreNotAssignGood.add( lgsAutoAsgnExtend );

                if (log.isInfoEnabled()) {
                    log.info("Auto assign. Restore not assign goods for shortage.[{}/{}/{}/{}/{}/{}]",
                            removedExt.getOrdNo(), removedExt.getDlivyDrctGodNo(), removedExt.getItmNo(),
                            removedExt.getOrdGodTurn(), removedExt.getPckageGodTurn(), removedExt.getDlivyDrctQty());
                }
            }
        }

        return restoreNotAssignGood;
    }
    */
    
/************************************ 퀵배송 [S] *********************************/
    
    /**
	 * 배정대상 주문번호 리스트 조회
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @return List<String>
	 * @throws Exception the exception
	 * @since 2017. 11. 7
	 */
    public List<String> selectAssignOrdNoList() throws Exception {
    	return deliveryAssignRepository.selectAssignOrdNoList();
    }
    
    
    /**
	 * 배정대상 출고정보 조회
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param ordNo [설명]
	 * @param assignType [설명]
	 * @param pckageGodTurn [설명]
	 * @return List<DeliveryOrderGoodResult> [설명]
	 * @throws Exception the exception
	 * @since 2017. 11. 7
	 */
	public List<DeliveryOrderGoodResult> selectAssignTargetList(String ordNo, String assignType, int pckageGodTurn) throws Exception {
		DeliveryOrderGoodDTO dto = new DeliveryOrderGoodDTO();
		dto.setOrdNo(ordNo);
		dto.setAssignType(assignType);
		if (pckageGodTurn > 0) {
			dto.setPckageGodTurn(pckageGodTurn);
		}
		
		return deliveryAssignRepository.selectAssignTargetList(dto);
	}
	
	
	/**
	 * 배정대상 아이템 그룹핑 정보 조회.
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param ordNo [설명]
	 * @param assignType [설명]
	 * @param pckageGodTurn [설명]
	 * @return List<DeliveryOrderGoodDTO> [설명]
	 * @throws Exception the exception
	 * @since 2017. 11. 7
	 */
	public List<DeliveryOrderGoodDTO> selectAssignTargetItemGrpList(String ordNo, String assignType, int pckageGodTurn) throws Exception {
		DeliveryOrderGoodDTO dto = new DeliveryOrderGoodDTO();
		dto.setOrdNo(ordNo);
		dto.setAssignType(assignType);
		if (pckageGodTurn > 0) {
			dto.setPckageGodTurn(pckageGodTurn);
		}
		return deliveryAssignRepository.selectAssignTargetItemGrpList(dto);
	}
	
	
	/**
	 * 배정 우선순위 배송매장 조회
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param DeliveryOrderGoodDTO dto [설명]
	 * @return LgsAutoAsgnExtend [설명]
	 * @throws Exception the exception
	 * @since 2017. 11. 9
	 */
	/*
	public LgsAutoAsgnExtend selectDlvShop4Assign(DeliveryOrderGoodDTO dto) throws Exception {
		return deliveryAssignRepository.selectDlvShop4Assign(dto);
	}
    */
	
	/**
	 * 배정 처리
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param target [설명]
	 * @param dlvShopId [설명]
	 * @param dlvStatCd [설명]
	 * @return 
	 * @throws Exception the exception
	 * @since 2017. 11. 9
	 */
	public void assingDlvShop(SystemPK systemPk, DeliveryOrderGoodResult target, String dlvShopId, String dlvStatCd) throws Exception {
		String[] excptShop = { "B031", DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString(), "A345", "A500", "B04E", "A351", "A505", "A506"
				, DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString(), DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString(), DeliveryEnum.DLV_ONLINE_SHOP_SA.toString()}; // 배정이력 예외 매장
		
		// 배정처리 대상 출고정보 조회
		DeliverySearchDTO dto = new DeliverySearchDTO();
		dto.setOrdNo(target.getOrd().getOrdNo());
		dto.setDlivyDrctGodNo(target.getLgsDdg().getDlivyDrctGodNo());
		DeliveryOrderGoodResult result = deliveryCommandRepository.selectReAssignDeliveryInfo(dto);
		
		if (result != null) {
			// 배송상태 체크
			if ( !result.getLgsDdg().getDlvStatCd().equals( DeliveryEnum.DLV_STAT_DLV_WAIT.toString() )) {
				log.info("배정처리 대상이 아닙니다. {} :: {} :: {}", result.getOrd().getOrdNo()
						, result.getLgsDdg().getDlivyDrctGodNo(), result.getLgsDdg().getDlvStatCd());
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("배정처리 대상이 아닙니다.\n확인 후 처리하세요.");
				throw statusException;
			} 
			// 이전 품절처리된 상품중 ERP출고취소가 미처리된 상품은 재배정 처리 안함
			else if ("Y".equals( result.getLgsDdg().getDlivyDrctYn() )) {
				log.info("ERP출고취소가 되지 않은 주문건. {} :: {} :: {}", result.getOrd().getOrdNo()
						, result.getLgsDdg().getDlivyDrctGodNo(), result.getLgsDdg().getDlvStatCd());
				DeliveryStatusException statusException = new DeliveryStatusException(null);
				statusException.setDirectMessage("ERP출고취소가 되지 않은 주문건이 존재합니다.\n확인 후 처리하세요.");
				throw statusException;

			}
			else {
				
				DeliveryOrderGoodDTO checkDto = new DeliveryOrderGoodDTO();
				checkDto.setItmNo(result.getOrdGod().getItmNo()); 							// 단품번호
				checkDto.setDlivyDrctQty(result.getLgsDdg().getDlivyDrctQty().intValue());	// 출고지시수량
				
				// 한정재고 체크
				if ("Y".equals( result.getLmttInvYn() )) {

					// 한정재고 수량 조회
					GodItm godItm = deliveryCommandRepository.selectLmttInvQty4ItemStock(checkDto);

					// 제휴주문
					if (DeliveryEnum.OrdTpCd.AFF_ORD.toString().equals( result.getOrd().getOrdTpCd() )) {
						// 제휴업체 한정재고 체크
						if ( godItm.getAffComLmttInvQty() < 0 ) {
							dlvShopId = DeliveryEnum.B031.toString();
							dlvStatCd = DeliveryEnum.DLV_STAT_SHTG_RCEPT.toString();
						}

					// 제휴주문 외
					} else {
						// 온라인 한정재고 체크
						if ( godItm.getOnlneLmttInvQty() < 0 ) {
							dlvShopId = DeliveryEnum.B031.toString();
							dlvStatCd = DeliveryEnum.DLV_STAT_SHTG_RCEPT.toString();
						}
					}
				}
				
				// 매장거절 2회 이상인 출고건에 대한 결품 처리
				if (result.getRejectCount() > 1) {
					dlvShopId = DeliveryEnum.B031.toString();
					dlvStatCd = DeliveryEnum.DLV_STAT_SHTG_RCEPT.toString();
				}
				
				String dlvShopDlivyLcId = DeliveryEnum.FC01.toString();	//출고 로케이션
				String dlivyDrctTgtYn = "N";							//물류센터 출고지시여부
				// 물류센터 출고건 출고 로케이션(FC08/FC01) 확인.
				checkDto.setDlvShopId(dlvShopId);	// 배정매장
				if (DeliveryEnum.DLV_ONLINE_SHOP_DISCOVERY.toString().equals( dlvShopId ) || DeliveryEnum.DLV_ONLINE_SHOP_MLB.toString().equals( dlvShopId )
						|| DeliveryEnum.DLV_ONLINE_SHOP_MLB_KIDZ.toString().equals( dlvShopId ) || DeliveryEnum.DLV_ONLINE_SHOP_SA.toString().equals( dlvShopId )) {
					dlvShopDlivyLcId = StringService.nvl(deliveryCommandRepository.selectDlvLcId4ItemStock(checkDto), DeliveryEnum.FC01.toString());
					dlivyDrctTgtYn = "Y";
				}
				
				// ERP 인터페이스 출고지시대상 정보 수정
				InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
				infOrdGodErpDstb.setOrdNo(result.getOrd().getOrdNo());
				infOrdGodErpDstb.setOrdGodTurn(result.getOrdGod().getOrdGodTurn());
				infOrdGodErpDstb.setDlivyDrctGodNo(result.getLgsDdg().getDlivyDrctGodNo());
				infOrdGodErpDstb.setDlivyDrctTgtYn(dlivyDrctTgtYn);
				infOrdGodErpDstb.setDlivyDrctYn("N");
				deliveryCommandRepository.updateInfDeliveryDirectTarget(infOrdGodErpDstb);
				
				// 배정정보 수정
				LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
				lgsDdg.setDlivyDrctGodNo(result.getLgsDdg().getDlivyDrctGodNo());
				lgsDdg.setUdterId(DeliveryEnum.ProcType4Hist.AUTO_ASSIGN_SHOP_BATCH.toString());
				lgsDdg.setDlvShopId(dlvShopId); 						// 배송매장
				lgsDdg.setDlvStatCd(dlvStatCd); 						// 배송상태
				lgsDdg.setDlvShopDlivyLcId(dlvShopDlivyLcId); 			// 배송매장출고위치ID
				lgsDdg.setDlivyDrctTgtYn(dlivyDrctTgtYn); 				// 출고지시대상여부
				if ( !StringService.equalsIn(dlvShopId, excptShop) ) {
					lgsDdg.setAsgnSectCd("ENFRC_ASGN");					// 배정구분코드 : 강제배정
				}
				lgsDdg.setAsgnCount(1);									// 배정횟수
				lgsDdg.setLgsItmYn("Y"); 								// 단품여부
				deliveryCommandRepository.updateAssignShopInfo(lgsDdg);

				// 물류 출고지시 상품 이력 등록
				LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
				lgsDlivyDrctGodHist.setRegtrId(DeliveryEnum.ProcType4Hist.QUICK_ASSIGN_BATCH.toString());
				lgsDlivyDrctGodHist.setUdterId(DeliveryEnum.ProcType4Hist.QUICK_ASSIGN_BATCH.toString());
				lgsDlivyDrctGodHist.setDlivyDrctGodNo(result.getLgsDdg().getDlivyDrctGodNo());
				deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

				log.info("Quick Assign Result Info : {} :: {} :: {} :: {} :: {} :: {} :: {} "
						, result.getOrd().getOrdNo(), result.getLgsDdg().getDlivyDrctGodNo(), dlvShopId, dlvStatCd
						, dlvShopDlivyLcId, result.getLgsDdg().getDlivyDrctQty(), DeliveryEnum.ProcType4Hist.QUICK_ASSIGN_BATCH.toString());
				
				// 주문상태 수정
				if(StringService.isEmpty(result.getOrd().getOrdStatCd()) 
						|| DeliveryEnum.OrdStatCd.PAY_COMPT.toString().equals( result.getOrd().getOrdStatCd() )) {
					Ord ord = new Ord();
					ord.setOrdNo(result.getOrd().getOrdNo());
					ord.setUdterId(DeliveryEnum.ProcType4Hist.QUICK_ASSIGN_BATCH.toString());
					ord.setOrdStatCd(DeliveryEnum.OrdStatCd.DLV_PRPARE.toString());    //배송준비
					orderBoCommandRepository.updateOrdStatCd(ord);
				}
				
				// ERP 예약영수증 변경 대상 조회
				DeliverySearchDTO sdto = new DeliverySearchDTO();
				sdto.setOrdNo(result.getOrd().getOrdNo());
				sdto.setDlivyDrctGodNo(result.getLgsDdg().getDlivyDrctGodNo());
				List<DeliveryInfErpDTO> rlist = deliveryInfErpService.selectErpResveRcptfrCancelInfoList(sdto);

				if (rlist.size() > 0) {
					// ERP 예약영수증 정보 변경 처리
					deliveryInfErpService.modifyPreSalesDlvShopChangeNew(rlist, DeliveryEnum.ProcType4Hist.QUICK_ASSIGN_BATCH.toString(), dlvShopDlivyLcId);
				}
				
				// #52785 - [모듈화-응답코드] 배정 및 예약영수증 생성/취소
				if (StringService.equalsIgnoreCase( dlvStatCd, DeliveryEnum.DLV_STAT_SHTG_RCEPT.toString() )) {
					//this.setResponseCode(null, result, "DL_40001", "QUICK");	// 배정실패_결품접수
				} else if( StringService.equalsIgnoreCase( dlvStatCd, DeliveryEnum.DLV_STAT_DLIVY_DRCT.toString() )) {
					//this.setResponseCode(null, result, "DL_00001", "QUICK");	// 배정성공
				}
			}
		} 
		else {
			log.info("[Quick] 배정처리 대상이 존재하지 않습니다. {} :: {} :: {} :: {}", target.getOrd().getOrdNo()
					, target.getLgsDdg().getDlivyDrctGodNo(), dlvShopId, dlvStatCd);
			DeliveryStatusException statusException = new DeliveryStatusException(null);
			statusException.setDirectMessage("[Quick] 배정처리 대상이 존재하지 않습니다.\n확인 후 처리하세요.");
			throw statusException;
		}
	}
	
	
	/**
	 * 가상배송 배정 처리
	 * <p/>
	 * <p/>
	 * [사용 방법 설명].
	 *
	 * @param systemPk [설명]
	 * @param target [설명]
	 * @param dlvShopId [설명]
	 * @param dlvStatCd [설명]
	 * @return 
	 * @throws Exception the exception
	 * @since 2017. 11. 29
	 */
    public void assignDlvShop4Vitual(SystemPK systemPk, DeliveryOrderGoodResult target, String dlvShopId, String dlvStatCd) throws Exception {

        //ERP 인터페이스 출고지시대상 정보 수정
        InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
        infOrdGodErpDstb.setOrdNo(target.getOrd().getOrdNo());
        infOrdGodErpDstb.setOrdGodTurn(target.getOrdGod().getOrdGodTurn());
        infOrdGodErpDstb.setDlivyDrctGodNo(target.getLgsDdg().getDlivyDrctGodNo());
        infOrdGodErpDstb.setDlivyDrctTgtYn("N");
        infOrdGodErpDstb.setDlivyDrctYn("N");
        deliveryCommandRepository.updateInfDeliveryDirectTarget(infOrdGodErpDstb);
        
        
        // 배정정보 수정
		LgsDlivyDrctGod lgsDdg = new LgsDlivyDrctGod();
		lgsDdg.setDlivyDrctGodNo(target.getLgsDdg().getDlivyDrctGodNo());
		lgsDdg.setUdterId(DeliveryEnum.ProcType4Hist.AUTO_ASSIGN_SHOP_BATCH.toString());
		lgsDdg.setDlvShopId(dlvShopId); 							// 배송매장
		lgsDdg.setDlvStatCd(dlvStatCd); 							// 배송상태
		lgsDdg.setDlvShopDlivyLcId(DeliveryEnum.FC01.toString());	// 배송매장출고위치ID
		lgsDdg.setDlivyDrctTgtYn("N"); 								// 출고지시대상여부
		lgsDdg.setDlivyComptQty(target.getLgsDdg().getDlivyDrctQty());    //출고완료수량
		lgsDdg.setAsgnCount(1);										// 배정횟수
		lgsDdg.setLgsItmYn("Y"); 									// 단품여부
		deliveryCommandRepository.updateAssignShopInfo(lgsDdg);
        

		// 물류 출고지시 상품 이력 등록
		LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
		lgsDlivyDrctGodHist.setRegtrId(DeliveryEnum.ProcType4Hist.QUICK_ASSIGN_BATCH.toString());
		lgsDlivyDrctGodHist.setUdterId(DeliveryEnum.ProcType4Hist.QUICK_ASSIGN_BATCH.toString());
		lgsDlivyDrctGodHist.setDlivyDrctGodNo(target.getLgsDdg().getDlivyDrctGodNo());
		deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);

		// ERP 예약영수증 변경 대상 조회
		DeliverySearchDTO sdto = new DeliverySearchDTO();
		sdto.setOrdNo(target.getOrd().getOrdNo());
		sdto.setDlivyDrctGodNo(target.getLgsDdg().getDlivyDrctGodNo());
		List<DeliveryInfErpDTO> rlist = deliveryInfErpService.selectErpResveRcptfrCancelInfoList(sdto);

		if (rlist.size() > 0) {
			// ERP 예약영수증 정보 변경 처리
			deliveryInfErpService.modifyPreSalesDlvShopChangeNew(rlist, DeliveryEnum.ProcType4Hist.QUICK_ASSIGN_BATCH.toString(), DeliveryEnum.FC01.toString());
		}
		
		// 주문상태 수정
		if(StringService.isEmpty(target.getOrd().getOrdStatCd()) 
				|| DeliveryEnum.OrdStatCd.PAY_COMPT.toString().equals( target.getOrd().getOrdStatCd() )) {
			Ord ord = new Ord();
			ord.setOrdNo(target.getOrd().getOrdNo());
			ord.setUdterId(DeliveryEnum.ProcType4Hist.QUICK_ASSIGN_BATCH.toString());
			ord.setOrdStatCd(DeliveryEnum.OrdStatCd.DLV_COMPT.toString());    //배송완료
			orderBoCommandRepository.updateOrdStatCd(ord);
		}
        
        // #52785 - [모듈화-응답코드] 배정 및 예약영수증 생성/취소
		//this.setResponseCode(null, target, "DL_00001", "QUICK");	// 배정성공
    }
	
	
    /**
	 * #52785 - [모듈화-응답코드] 배정 및 예약영수증 생성/취소
	 *
	 * @param LgsAutoAsgnExtend lgsAutoAsgnExt
	 * @param DeliveryOrderGoodResult result
	 * @param String rCode
	 * @param String Type
	 */
    /*
	public void setResponseCode(LgsAutoAsgnExtend lgsAutoAsgnExt, DeliveryOrderGoodResult result, String rCode, String Type) {
		try {
			StringBuffer dlivyKey = new StringBuffer("");
			if (lgsAutoAsgnExt != null) {
				dlivyKey.append(lgsAutoAsgnExt.getOrdNo()).append("@")
				.append(lgsAutoAsgnExt.getOrdGodTurn()).append("@")
				.append(lgsAutoAsgnExt.getDlivyDrctGodNo());
			}
			if (result != null) {
				dlivyKey.append(result.getOrd().getOrdNo()).append("@")
				.append(result.getOrdGod().getOrdGodTurn()).append("@")
				.append(result.getLgsDdg().getDlivyDrctGodNo());
			}

            MDC.put("dlivyKey", dlivyKey.toString());
            if (StringService.equalsIgnoreCase("DL_00001", rCode)) {
                log.info(CommonResponseCode.DL_00001_X0_배정_성공.toMessage(Type));
            }
            else if (StringService.equalsIgnoreCase("DL_00002", rCode)){
                log.info(CommonResponseCode.DL_00002_X0_배정_성공_배정대기.toMessage(Type));
            }
            else if (StringService.equalsIgnoreCase("DL_40001", rCode)){
                log.info(CommonResponseCode.DL_40001_X0_배정_실패_결품접수.toMessage(Type));
            }
            else if (StringService.equalsIgnoreCase("DL_50001", rCode)){
                log.warn(CommonResponseCode.DL_50001_X0_배정_수행_실패.toMessage(Type));
            }
            MDC.remove("dlivyKey");
		}
		catch(Exception e){
			log.error("> Failure message : {}", this.getClass().getName() + " setResponseCode : " + e);
		}
	}
	*/
	
	/**
	 * 결품 알림 메시지 발송
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param ordNo [설명]
	 * @return [설명]
	 * @throws Exception the exception
	 * @since 2017. 11. 15
	 */
	public void sendDeliveryComptMsg(SystemPK systemPK, String ordNo) throws Exception {

		//결품 알림 메시지 발송정보 조회
		DeliveryComptMsgResult msgInfo = this.deliveryAssignRepository.selectQuickShtgMsgInfo(ordNo);
		
		if (msgInfo != null) {
			if ( StringService.isNotEmpty(msgInfo.getMobile()) || StringService.isNotEmpty(msgInfo.getAddrseMobileNo())
					|| StringService.isNotEmpty(msgInfo.getOrdmanMobileNo()) || StringService.isNotEmpty(msgInfo.getAddrseMobileNo()) ) {
				
//				try {
//
//					String tranId = StringService.isEmpty(msgInfo.getMbrNo()) ? msgInfo.getMbrNo() : ordNo;
//					String msgKey = "DXM_LGS_SHTG_01";
//					String mobilNo = msgInfo.getMobile();
//					String mallId = msgInfo.getMallId();
//
//					ArrayList<String> params = new ArrayList<>();
//					params.add(0, msgInfo.getOrdNo());
//					params.add(1, msgInfo.getGodSumry());
//
					// TODO 문자 솔루션 적용
//					String callerId = InterfaceAdapterEnum.INTERFACE_ADAPTER_DLIVY_COMPT_GNRL_SMS.toString();
//
//					if(StringService.isNotEmpty(mobilNo) && StringService.isNotEmpty(msgKey)){
//						HumusonSDO humusonSDO = humusonCommonService.getAlimTalkData(tranId, mobilNo, mallId, callerId, msgKey, params);
//
//						AdapterHeader adapterHeader = new AdapterHeader();
//						adapterHeader.setRequestId( UUID.randomUUID().toString().replace("-", "") );
//						adapterHeader.setMallId(mallId);
//						adapterHeader.setDvcCd(systemPK.getDevice());
//						adapterHeader.setLangCd(systemPK.getLang());
//
//						try {
//							humusonCommonService.sendAlimTalk(humusonSDO, adapterHeader);
//							/*
//							 * 개인정보이력 생성-SMS
//							 */
//							if (StringService.isNotEmpty(tranId) && tranId.contains("MB")) {
//								// tranID 가 회원일 경우
//								// 구분, 업무, 업무상세
//								String[][] usefCdInfo = {
//										{
//											MemberPersonalInfoEnum.UsefSectCd.PSNL_INFO_THPR_OFFER_DETL.toString(), // 개인정보 취급위탁 내역
//											MemberPersonalInfoEnum.UsefJobCd.SND_KKO_NTCN_TAK.toString(), // SMS, MMS발송
//											MemberPersonalInfoEnum.UsefJobDetail.STPLAT_APL.toString() // 약관에 따름
//										}
//								};
//								String[] target = { tranId };
//								memberPersonalInfoCommandService.insertMemberPersonalInfo( systemPK
//										, usefCdInfo			    // 개인정보 코드 정보(구분, 업무, 업무상세)
//										, target					// 이용대상 : 회원
//										, null				// 유입 일련번호
//										, null              // 메뉴 일련번호
//										, tranId	                // 로그인 ID
//										);
//							}
//
//							//결품 알림 메시지 발송대상 확인 처리
//							this.deliveryAssignRepository.updateShtgMsgTransYn(ordNo);
//
//						} catch (Exception e) {
//							StringWriter error = new StringWriter();
//							e.printStackTrace(new PrintWriter(error));
//							log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
//						}
//					}
//
//				} catch (Exception e) {
//					StringWriter error = new StringWriter();
//					e.printStackTrace(new PrintWriter(error));
//					log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
//				}
			}
		}
			
	}	
    
    /************************************ 퀵배송 [E] *********************************/
    
    
    
}