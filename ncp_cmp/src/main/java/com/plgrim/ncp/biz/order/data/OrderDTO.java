package com.plgrim.ncp.biz.order.data;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodSvcDetailCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.result.CartResult;
import com.plgrim.ncp.biz.order.result.OrderCouponResult;
import com.plgrim.ncp.commons.data.order.KcpParamDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@JsonInclude(value=Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = false)
public class OrderDTO extends AbstractDTO {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 5974721296347971555L;

	// 1. 상품
	List<BskGod> bskGodList;

	// 2. 배송
	private List<LgsDlvspDTO> lgsDlvspDTOList;

	// 3. 결제
	private Pay pay;

	// 4. 주문
	private Ord ord;

	private Mbr mbr;
	
	//네이버 페이
	private NaverPayApproveDTO naverPayApprove;
	
	//kcp 페이
	private KcpParamDTO kcpDTO; 
	
	
	// 5. 쿠폰
	private List<CouponDTO> couponDTOList;
	// - 포인트
	// - 본인인증관련
	
	// 6. 사은품
	private List<OrderGiftDTO> orderGiftDTOList;

	// 7. 기타
	private String stplatIemAgrYn; // 비회원주문약관동의
	
	private Long inflowSn = 0L; // 유입경로
	
	private String godSummary;

	/**
	 * 등록자 ID 등록한 관리자 번호
	 */
	private String regtrId;

	/**
	 * 등록 일시
	 */
	private java.util.Date regDt;
	/**
	 * mypage 결제 여부
	 */	
	private String type;
	/**
	 * 배송비 결제 클레임NO
	 */	
	private String clmNo;
	
	/**
	 * 카카오페이 승인결과 TID 저장
	 */
	private String tid;
	
	private java.lang.Integer dlvPcupspTurn;

	private String payDtStr;


	private String crncyCd;


	/**
	 * 해외배송정보
	 */
	private String ovseaDlvFee;
	private String ovseaDlvCstPlcSn;
	private String nationCd;
	private String godWtSum;
	private String zoneTurn;


	private String exchgRtAplBegDtStr;
	private java.util.Date exchgRtAplBegDt;
	
	/**
	 * 주문.외국인 신분증 번호
	 */
	private String frgnrIdtCardNo;
	
	/**
	 * ㅁ. 외국인 신분증 구분 : FRGNR_IDT_CARD_SECT
   >. 신분증 번호 : IDT_CARD_NO
   >. 여권 번호 : PASPORT_NO
	 */
	private String frgnrIdtCardSectCd;
	
	private String tempPayNo;

	private String connectPayYn = "N";
	
	
	private List<OrdGodExtends> ordGodExtendsList;


	/**
	 * ncp 3차 결제로직 변경으로 인터페이스 서버에서 리퀘스트가 들어오기때문에 세션유지안되서 추가
	 */
	
	private CartSearchDTO cartSearchDTO;
	private List<CartResult> cartResultList;
	private List<OrderCouponResult> memberCouponList;
	
	private String mbrPreferPayMn;	//회원선호결제수단 : UXUI개선
	
	private boolean isEmp = false;
	private boolean isVipCsmtr = false;


	// 주문 상품 상세 연결 테이블
	private List<OrdGodSvcDetailCnncExtend> ordGodSvcDetailCnncExtend;

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
            log.warn("error occured during parse to json:" + e.getMessage(), e);
            return "{"+ToStringBuilder.reflectionToString(this)+"}";
        }

        return jsonInString;
	}

}
