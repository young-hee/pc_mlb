package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;


@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimListSearchDTO extends AbstractDTO {
	/**
	 *
	 */
    private static final long serialVersionUID = 6594063466602041954L;



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

	//배송매장
	private String shopId;

	//제휴대행사
	private String affVrscComId;

	//판매제휴사
	private String affComId;

	//주문유형
	private String ordTpCd;

	//신청일시 시작일
	private String clmDtStart;

	//신청일시 종료일
	private String clmDtEnd;

	//완료일시 시작일
	private String clmCompDtStart;

	//완료일시 종료일
	private String clmCompDtEnd;

	//클레임구분
	private String clmTpCd;

	//클레임상태
	private String clmStatCd;

	//클레임사유
	private String clmResnCd;

	//판매제휴사
	private String saleAffComId;

    //클레임 번호
	private String clmNo;

	// 다중 클레임 번호
    private String[] clmNoArr;

	//구매자 아이디
	private String cstmrId;

	//구매자 이름
	private String cstmrNm;

	//수취인 이름
	private String addrseNm;

	//수취인연락처
	private String reciverTel;

	//브렌드코드 , 로 다중검색
	private String brndId;

	//브랜드코드 다중 검색
	private List<String> brndIdArr;

	//브랜드코드 다중 검색 henry
	private String[] brndIdNewArr;

	/** 브랜드 그룹 Id SR #21177 김병철*/
	private String upperBrndId;

	//주문번호
	private String ordNo;

	//주문번호 배열
	private String[] ordNos;

	//제휴사주문번호
	private String saleAffComOrdNo;

	//제휴사주문번호 배열
	private String[] saleAffComOrdNos;

	// 지연일수
	private String delayDate;

	// PG사 전송에러 코드
	private String result_msg;

	// 상품번호
	private String godNo;

	private String erpGodNo;

	// 다중 상품 번호
	private String[] godNoArr;

	private String nosGubun;

	/** 품번 검색어. */
	private String searchNos;

	/** 검색조건 리스트 */
	private List<String> searchNoList;

	/**k2 사유추가 **/
	//PG환불에러사유
	private String rfdErrCont;
	//디폴트상태여부
	private String defaultClmStatCd;

	/**페이징처리 YN **/
	private String pagingYn = "Y";

	/**무료수선관련**/
	private String dateGubun;
	private String repairComptCd;
	private String clmRceptMthdCd;
	private String rceptAdminId;
	private String regtrNm;
	private String mbrNo;
	private String freeRepairViewType;
	private String boRepairStatNm;
	private String boRepairStatCd;

	private String srchMonth;
	private String dateStart;    			/*  시작일 */
	private String dateEnd;    				/*  종료일 */

	/**무료수선관련 '17 6/13 오픈 후 추가**/
	private String searchCstmrMobile;       /*  구매자 연락처*/
	private String mbrId;                   /*  구매자 ID*/
	private String[] mbrIds;                /*  구매자 ID배열*/
	
	/** [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청 **/
	// 클레임 선진행 구분 코드
	private String clmPreprogrsSectCd;
	private String[] clmPreprogrsSectCdArr;
	// ERP입고확인일
	private String erpCnfirmDtStart;
	private String erpCnfirmDtEnd;
	// 자사 / 제휴사
	private String comall;
	// 디폴트상태여부
	private String defaultClmTpCd;
	// 집하 상태 코드
	private String gdgpStatCd;
	private String defaultGdgpStatCd;

	/**  회원 등급. */
	private String onlneGrdCd;
	
	// 배송방법
	private String dlvMnCd;
}
