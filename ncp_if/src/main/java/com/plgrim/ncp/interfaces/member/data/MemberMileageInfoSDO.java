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
public class MemberMileageInfoSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 고객 일련번호
     */
    @JsonProperty("CID")
    private String cid;
    /**
     * 이력 요청 여부
     */
    @JsonProperty("HISTORY_YN")
    private String historyYn;
    /**
     * 검색 시작일자
     */
    @JsonProperty("START_DATE")
    private String startDate;
    /**
     * 검색 종료일자
     */
    @JsonProperty("END_DATE")
    private String endDate;
    /**
     * 페이지 번호 
     */
    @JsonProperty("PAGE_NUM")
    private String pageNum;
    /**
     * 페이지 사이즈
     */
    @JsonProperty("PAGE_SIZE")
    private String pageSize;
    /**
     * 몰 CODE
     */
    @JsonProperty("BRAND")
    private String brand;
    /**
     * 잔여 포인트
     */
    @JsonProperty("NOW_POINT")
    private String nowPoint;
    /**
     * 임시사용 마일리지
     */
    @JsonProperty("USE_TEMP_POINT")
    private String useTempPoint;
    /**
     * 소멸예상 마일리지
     */
    @JsonProperty("ELIMINATE_POINT")
    private String eliminatePoint;
    /**
     * 상태
     * R: 활성 D: 탈퇴 S: 휴면 B:블랙리스트 L:사용제한(마일리지)
     */
    @JsonProperty("STATUS")
    private String status;
    /**
     * 업데이트 date
     * (YYYY-MM-DD HH24:MI:SS)
     */
    @JsonProperty("UPDATE_DATE")
    private String updateDate;
    /**
     * 전체 건수
     */
    @JsonProperty("TOTAL_ROW")
    private java.lang.Long totalRow;
    
    
    /**
     * 마일리지 내역 리스트
     */
    @JsonProperty("MILEAGE_HIST_LIST")
    private List<MileageHistInofSDO> mileageHistList;
}