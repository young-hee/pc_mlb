package com.plgrim.ncp.biz.claim.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimRefundListSearchDTO extends AbstractDTO {
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    

	//자사/제휴사 코드
	private String partmalSectCd;

	//자사/제휴사 코드
	private String[] partmalSectCdArr;

	//몰
	private String mallId;

	//언어
	private String langCd;

	//입점사
	private String comId;

	//주문유형
	private String ordTpCd;

	//제휴대행사
	private String affVrscComId;

	//판매제휴사
	private String affComId;

	//환불접수 시작일
	private String clmDtStart;

	//환불접수 종료일
	private String clmDtEnd;

	//환불 상태
	private String rfdStatCd;

	//클레임 구분
	private String clmTpCd;

	//클레임 상태
	private String clmStatCd;

	//결제수단
	
	private String payMnCd;
	
	private String[] payMnCdArr;

	//입금은행
	private String bnkNm;

	//예금주
	private String acnthldrNm;

	//전화번호
	private String tel;

	//수취인이름
	private String addrseNm;

	//구매자이름
	private String cstmrNm;

	// 주문 번호
	private String ordNo;

	// 다중 주문 번호
	private String[] ordNoArr;
	
	/** [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청 **/
	// 클레임 선진행 구분 코드
	private String clmPreprogrsSectCd;
	private String[] clmPreprogrsSectCdArr;

}
