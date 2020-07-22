package com.plgrim.ncp.biz.member.data;




import java.util.List;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.biz.member.result.MypageOnlineMemberGradeInfoResult;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mypageFoDTO")
public class MypageFoDTO extends AbstractDTO {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    private MbrDlvsp mbrDlvsp = new MbrDlvsp();						// 회원 배송지

    private String member;                                          // 회원 기본정보 변경여부

    private MbrIssuCpn mbrIssuCpn;                    				// 회원 발급 쿠폰

    private Mbr mbr;                    							// 회원 발급 쿠폰

    private MypageOnlineMemberGradeInfoResult vipGrade;				// 온라인회원 등급정보

    private String prmNo;                    						//  프로모션 번호

    private String aplGodSecCd; 									// 쿠폰 상품 Type

    private String srchType; 										// Search Type

    private String srchKeyword; 								    // Search Keyword

	private String dateStart;    							    	//  시작일

	private String dateEnd;    										//  종료일

	private String srchMonth;    									//  Search Month

    private String waybillNumber;       							// 국내 송장번호
    private String dlvComCd;       									// 택배사 코드

    private String ordNo;                    						//  주분 번호

    private String erpSeq;                    						//  ERP 순번
    private String mobilNo;         								// 휴대폰번호
    private String telNo;           								// 전화번호

    private String wishlstSn;	   									// 위시리스트 일련번호

    private String godTurn;		   									// 상품순번

	private String prcSectCd;		   								// 가격구분코드

	private String ivCbAmt;		   									// 마일리지 포인트

	private String todayGodSn;                                       // 최근본상품 일련번호

	private String mbrCpnNo;									    // 쿠폰번호

	private String language;									    // 언어

	private List<String> godNoList;									// 상품번호 리스트

	private String matNo;								  	        // 상품Item No

    private PageParam pageParam;

    private List<String> plgrimshopMallIdList;							// 통합몰에서 노출되어야 할 Mall Id List

    private MbrIdCntc mbrIdCntc;

    //재입고 알림 삭제 추가
    private String reWhsgNtcnSn;

    private String godNo;

    private String setGodNo;

    private String cpnCrtfcCd; // 오프라인쿠폰번호

    // ERP 마일리지 조회
    private String historyYn;

    private String brand;

    private String pageSize;

    private String pageNum;

    // 맴버십 카드등록
    private String membershipCardNo;

    // 인증번호
    private String certifyNo;

	private String mbrNo;

	// 쿠폰 유형 코드
	private String cpnTpCd;

	// 페이징 처리 여부
	private String pagingFlagYn;

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
