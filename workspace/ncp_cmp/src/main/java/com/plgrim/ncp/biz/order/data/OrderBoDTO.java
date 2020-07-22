package com.plgrim.ncp.biz.order.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrd;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.commons.data.order.KcpParamDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;


@Data
@EqualsAndHashCode(callSuper = false)
public class OrderBoDTO extends AbstractDTO {
	/**
	 *
	 */
	private static final long serialVersionUID = 5836627492662374731L;

	private Ord ord; // 주문
	private Mbr mbr ;
	SysInflow sysInflow;

	private OrdExtend ordExtend;
	private List<LgsDlvspExtend> lgsDlvspList;// 물류배송지

	private List<LgsDlivyDrctGod> lgsDlivyDrctGods;
	
	private List<InfAffOrd> infAffOrds;
	
	private List<Pay> payList;// 결제

	private List<OrdGodExtend> ordGodList;

	private List<InfOrdGodErpDstb> infOrdGodErpDstbList;

	private Map<String, Integer> drctGodMap;

	private long godTotQty;

	private long qty;
	/**
	 * 제휴 대행사 ID
	 */
	private String affVrscComId;
	private java.lang.Long inflowSn;
	private String inflowLpcd;
	/* 자사/제휴사 */
	private String mcomOrAff;

	private String mcom;

	private String aff;

	private String memoCont;

	private String mbrNo;
	
	private String langCd;
	private String[] langCds;
	
	/* 업체 ID */
	private String comId;

	/* 브랜드 ID */
	private String brndId;

	private String[] brndIds;

	/* 배송상태 */
	private String dlvStat;

	/* 상품유형 */
	private String godTp;

	/* 몰 아이디 */
	private String mallNm;

	/* 언어명 */
	private String langNm;

	/* 주문번호 */
	private String ordNo;
	
	private String[] ordNos;

	private String[] payNos;
	
	private String[] devices;

	private String godNo;

	private String itmNo;

	private String oriItmNo;
	private String skuNo;

	private String[] godNos;

	private String[] erpGodNos;

	/* 제휴사 주문번호 */
	private String affComOrdNo;

	private String[] affComOrdNos;

	/* 주문 시작일 */
	private String startOrdDt;

	/* 주문 종료일 */
	private String endOrdDt;

	/* 주문구분 */
	private String ordTp;

	/* 주문 상태 */
	private String ordStat;

	/* 판매제휴사 */
	private String saleAff;

	/* 광고채널 */
	private String advrtsCh;

	/* 결제수단 */
	private String payMn;

	private String[] payMns;

	/* 회원유형 */
	private String mbrTp;

	/* 회원속성 */
	private String mbrAtrb;

	/* 구매자ID */
	private String pchId;

	/* 구매자 이름 */
	private String pchNm;

	/* 구매자 연락처 전화번호 */
	private String pchTelNo;

	/* 구매자 연락처 휴대전화번호 */
	private String pchMoNo;

	/* 수취인이름 */
	private String rcverNm;

	/* 수취인이름 */
	private String rcverTelNo;

	/* email */
	private String email;

	/* 주문일시 */
	private Date ordDt;
	
	/* 상품명 */
	private String godNm;

	/* 주문금액 */
	private Double ordAmt;

	/* 할인금액 */
	private Double dcAmt;

	/* 주 결제금액 */
	private Double mainPayAmt;
	
	
	private Double payCrncyPayAmt;
	

	/* 멤버쉽포인트결제액 */
	private Double memPntAmt;

	/* 이벤트포인트결제액 */
	private Double evtPntAmt;

	/* 적립 멤버쉽 포인트 */
	private Double savMemPnt;

	/* 적립웹 포인트 */
	private Double savWebPnt;

	private String affOrdStatCd;


	private int dlvPcupspTurn;

	private int ordGodTurn;
	private int pkGodTurn;
	private String regtrId;

	private String udterId;

	private String lagQtyOrdDcsnYn;

	private String maskingYn = "Y";

	private String pckageshapeGodNo;

	private boolean godSelect = true;

	private long affOrdSn;

	private String tgtMbrTpCd;

	private String tgtMbrAtrbCd;

	private String affComId;

	private String prmNo;

	private String affComAplCd;

	private String prmDtlTpCd;

	private String excelType;

	private String cntcSectCd;
	
	private java.lang.Long dmstcDlvCstPlcSn;
	/* claim *************************************************************************/

	/***************************************************************************
	 * 주문 반품접수
	 ****************************************************************************/
	/**
	 * 주문상품순번
	 * 클레임접수시 주문상세에서 선택한 주문상품순번
	 */
	private String ordGodTurnStr;

	/**
	 * 클레임번호
	 */
	private String clmNo;

	/**
	 * 클레임유형코드
	 */
	private String clmTpCd;

	/**
	 * 실소가
	 */
	private String csmrPrc;
	
	/**
	 * 클레임상태코드
	 */
	private String clmStatCd;
	/**
	 * 클레임상태코드명
	 */
	private String clmStatNm;

	/**
	 * 배송순번
	 */
	private String dlvTurn;

	private List<String> ordGodTurnArr;

	private String validationType;
	
	/**
	 * 배송 매장 ID	 
	 */
	private String dlvShopId;
	

	/* 로그인 유형 */
	private String adminTpCd;
	/* 로그인된 매장ID */
	private String regtrShopId;

	/* claim *************************************************************************/

	/* 호출구분 */
	private String callTp;

	private String shopId;

    /**
     * Role
     * FO/BO 구분
     * FO : F, BO : B
     * 반품접수/교환접수 에서 사용
     */	
	private String role;
	
	private String prmTpCd;
	
	private String idx;

	/**
	 * upper Brnd Id
	 * */
	private String upperBrndId;
	
	/* 제휴주문 가상배송완료여부 2016.01.20 by Cannon */
	private String affOrdVirtlDlvComptYn;

	/* 국내운송장번호 */
	private String dmstcWaybilNo;

	/**
	 * 1. 수정일자	: 2016-07-05
	 * 2. 수정자	    : 김재성 (jskim27)
	 * 3. 요청 SR NO	: #22623
	 * 4. 수정 내용	: BO 주문관리 > 상품별주문조회 오류 조치 요청
	 *      - 페이지 마지막 row 번호.
	 * 		- Query 속도저하 문제로 인해 '물자열'로 변경
	 */
	private String endIndexStr = "0";
	
	/* 배송국가코드 */
	private String dlvNationCd;

	private String talkYn;                          /* 시나리오 채팅 호출 여부*/
	
    /**
     * 환불은행
     */
    private String rfdBnkCd;
    
    /**
     * 환불예금주
     */
    private String rfdAcnthldrNm;
    
    /**
     * 환불계좌
     */
    private String rfdBnkAcctNo;

    private String autoRfdSectCd;
    
	/* 주문배송구분 */
	private String ordDlvTp;
	
	/* 에스크로 상태 */
	private String escrStatus;
	
	/** 고객구매확정여부 */
	private String cstmrPchDcsnYn;
	
	//kcp 페이
	private KcpParamDTO kcpDTO;
	
	public static <T> T fromJSON(String jsonString, Class<T> type){
        ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
        	return objectMapper.readValue(jsonString, type);
        }catch(IOException ie) {
        	throw new RuntimeException(ie.getMessage());
        }
    }
	
	public String toJSON() {
		ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString;
        try {
            jsonInString = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            //log.warn("error occured during parse to json:" + e.getMessage(), e);
            return "{"+ToStringBuilder.reflectionToString(this)+"}";
        }

        return jsonInString;
	}
}

