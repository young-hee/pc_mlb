/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      
 * @since       2015. 3. 30       
 */
package com.plgrim.ncp.interfaces.delivery.data;

import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * [우편택배 dto]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PostInterfaceSDO extends AbstractSDO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* 우체국 고객번호 */
	private String custNo;
	
	/* 신청구분 1:신규발송 / 2:반품 */
	private String reqType;
	
	/* 공급지(반품처) 코드/기 등록된 공급지코드 */
	private String officeSer;
	
	/* 무게(kg) 1~30kg, 정수 ※ 미입력 시 default값 : 2kg */
	private String weight;
	
	/* 부피(cm) cm (60,80,120,140,160) ※ 미입력 시 default값 : 60cm */
	private String volume;
	
	/* 고객주문처명 */
	private String ordCompNm;
	
	/* 주문자명 */
	private String ordNm;
	
	/* 주문자우편번호 */
	private String ordZip;
	
	/* 주문자우편번호주소 */
	private String ordAddr1;
	
	/* 주문자상세주소 */
	private String ordAddr2;
	
	/* 주문자전화번호 */
	private String ordTel;
	
	/* 주문자핸드폰번호 */
	private String ordMob;
	
	/* 수취인(반품인) 명 */
	private String recNm;
	
	/* 수취인(반품인) 우편번호 */
	private String recZip;
	
	/* 수취인(반품인) 주소 */
	private String recAddr1;
	
	/* 수취인(반품인) 상세주소 */
	private String recAddr2;
	
	/* 수취인(반품인) 전화번호 */
	private String recTel;
	
	/* 수취인(반품인) 이동통신 */
	private String recMob;
	
	/* 승인번호 */
	private String apprNo;
	
	/* 요금납부구분 1:일반(즉납or후납) / 2:수취인부담 */
	private String payType;
	
	/* 초소형택배구분(Y/N) 초소형계약 시만 사용 (무게 2kg미만) */
	private String microYn;
	
	/* 주요내용품코드
	 * 021 농/수/축산물(일반)
	 * 022 농/수/축산물(냉동/냉장)
	 * 023 전자제품
	 * 024 서적
	 * 025 의류/패션잡화
	 * 026 미용/화장품
	 * 027 의료/건강식품
	 * 028 생활용품
	 * 029 기타
	 *  */
	private String contCd;
	
	/* 상품명 */
	private String goodsNm;
	
	/* 상품코드 */
	private String goodsCd;
	
	/* 상품모델 */
	private String goodsMdl;
	
	/* 상품사이즈 */
	private String goodsSize;
	
	/* 색상 */
	private String goodsColor;
	
	/* 수량 */
	private String qty;
	
	/* 주문번호 */
	private String orderNo;
	
	/* 배송(반품)메세지 */
	private String delivMsg;
	
	/* 반품사유 (반품인 경우) */
	private String retReason;
	
	/* 반품희망방문일 (YYYYMMDD) (반품인 경우 필수) */
	private String retVisitYmd;
	
	/* 테스트 신청 여부 
	 * 오픈API 개발 및 테스트 용도로 호출 시 해당 파라미터 포함 하여 요청 데이터 생성
	 * (테스트 호출인 경우 : testYn=Y) 
	 * ※ 테스트 신청 건은 등기번호가 정상 채번 되지 않으며, 접수우체국에 신청정보 전송이 되지 않음*/
	private String testYn;
	
	
	private String ordNo;
	private String rtrvlDrctGodNo;
	private String dmstcWaybilNo;
	private String clmNo;
	private String dlvPcupspTurn;
	private String dlvTurn;
	private String callerId;
	
}
