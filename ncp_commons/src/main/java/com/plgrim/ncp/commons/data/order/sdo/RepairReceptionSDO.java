package com.plgrim.ncp.commons.data.order.sdo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RepairReceptionSDO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 대표 온라인 수선번호
	 * 온라인에서 제공한 대표 수선번호
	 * 필수 여부 : Y
	 */
	private String onRepairNo;
	/**
	 * 대표 온라인 수선번호 순번
	 * 온라인에서 제공한 대표 수선번호 순번
	 * 필수 여부 : Y
	 */
	private String onRepairNoSeq;
	/**
	 * 판매매장코드
	 * "A345"
	 * 필수 여부 : Y
	 */
	private String werks;
	/**
	 * 판매구분
	 * 'A02'
	 * 필수 여부 : Y
	 */
	private String salfr;
	/**
	 * 고객명
	 * <p>
	 * 필수 여부 : Y
	 */
	private String bpName;
	/**
	 * 전화번호
	 * <p>
	 * 필수 여부 : Y
	 */
	private String telNo;
	/**
	 * 생년월일
	 * <p>
	 * 필수 여부 : N
	 */
	private String date;
	/**
	 * 남녀구분
	 * 남녀구분(M:남자, F:여자)
	 * 필수 여부 : Y
	 */
	private String gender;
	/**
	 * 품번
	 * SKU_NO(디자인코드)=> 품번사이즈포함
	 * 필수 여부 : Y
	 */
	private String matnr;
	/**
	 * 차수
	 * 차수
	 * 필수 여부 : Y
	 */
	private String chasu;
	/**
	 * 수량
	 * 고정: 1
	 * 필수 여부 : Y
	 */
	private String anzfehler;
	/**
	 * 실판매가액
	 * <p>
	 * 필수 여부 : Y
	 */
	private String retAppPrc;
	/**
	 * 매장요청
	 * 수선: 'A01'
	 * 필수 여부 : N
	 */
	private String otgrp;
	/**
	 * 매장요청사항
	 * 고객요청사항
	 * 필수 여부 : Y
	 */
	private String otgrpt;
	/**
	 * 완료희망일
	 * <p>
	 * 필수 여부 : Y
	 */
	private String ausbs;
	/**
	 * 고객번호
	 * <p>
	 * 필수 여부 : N
	 */
	private String bpNo;
	/**
	 * 예약영수증번호
	 * 예약영수증번호
	 * 필수 여부 : Y
	 */
	private String rctno;
	/**
	 * 예약영수증 일련번호
	 * 일련번호 (순번) 개별순번
	 * 필수 여부 : Y
	 */
	private String seqno;
	/**
	 * Tag Serial Number
	 * Tag Serial Number: 공란
	 * 필수 여부 : N
	 */
	private String tagSn;
	/**
	 * 접수타입
	 * "B": 본사접수 "S": 매장접수
	 * 필수 여부 : Y
	 */
	private String receptTp;
	/**
	 * 수선매장수수료율
	 * 수선수수료율 (매장인경우만등록)
	 * 필수 여부 : Y
	 */
	private String shopFee;
	/**
	 * 수선매장수수료금액
	 * 수선수수료금액  (매장인경우만등록)
	 * 필수 여부 : Y
	 */
	private String shopPrc;
	/**
	 * 회수받은 송장번호
	 * 회수받을 송장번호 (본사접수시에만 등록)
	 * 필수 여부 : N
	 */
	private String delmngNo;
	/**
	 * 회수받을 우편번호
	 * 회수받을시 우편번호  (본사접수시에만 등록)
	 * 필수 여부 : N
	 */
	private String postNo;
	/**
	 * 회수받을시 주소
	 * 회수받을시 주소  (본사접수시에만 등록)
	 * 필수 여부 : n
	 */
	private String address;
	/**
	 * 수령 고객명
	 * <p>
	 * 필수 여부 : N
	 */
	private String bpName2;
	/**
	 * 수령고객 전화번호
	 * <p>
	 * 필수 여부 : N
	 */
	private String telNo2;
	/**
	 * 송장번호, 우편번호, 주소 갱신 flag
	 * "X" 입력시 송장번호, 우편번호, 주소 갱신
	 * 필수 여부 : N
	 */
	private String update;


	/**
	 * 결과메시지
	 */
	private String message;

	/**
	 * 결과코드
	 * 성공: S o r공란 에러: E
	 */
	private String returnCd;


	/**
	 * 개별 수선번호
	 * 본사 상담실 접수 건만 수선번호 생성
	 */
	private String qmnum;
	/**
	 * 수선진행 상태
	 * 진행상태 코드 시트 참조
	 */
	private String astxt;
	/**
	 * 수선진행 상태 코드명
	 */
	private String astxtTx;
	/**
	 * 수선후 고객 전달 방법
	 * 수령방문,택배배송
	 */
	private String dliMethod;
	/**
	 * 택배사코드
	 * 수선완료후 택배배송인경우 온라인에서 제공하는 택배사코드
	 */
	private String dliMngCode;
	/**
	 * 택배배송
	 * 수선완료후 택배배송인경우 송장번호
	 */
	private String dliMngNo;
	/**
	 * 취소여부
	 * 취소: Y
	 */
	private String cancal;
	/**
	 * 수선완료일
	 */
	private String enddat;

	/**
	 * A/S 접수 담당자
	 */
	private String hlpusrNm;
	/**
	 * A/S 접수 담당자 전화번호
	 */
	private String hlpusrTelf1;
	/**
	 * 접수매장 전화번호
	 */
	private String werksTelf1;

	private String udterId;
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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}