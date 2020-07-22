package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2016-11-16.
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class DspCtgryLNBResult extends AbstractResult {
    /**
     * 전시 카테고리 번호
     전시매장번호
     전시매장코드(3)+대카(3)+중카(3)+소카(3)+ 세카(3) +세세카(3)
     전시매장유형:Select Item, 브랜드매장
     */
    private String dspCtgryNo;
    /**
     * 상위 전시 카테고리 번호
     ㅁ. 재귀참조 구조를 가지기 위해 참조 함

     ㅁ. 1단계 레벨인 경우 적용 대상 스토어의 카테고리 순번으로써 참조 됨.
     */
    private String upperDspCtgryNo;
    /**
     * 상위 전시 카테고리 명
     ㅁ. 재귀참조 구조를 가지기 위해 참조 함

     ㅁ. 1단계 레벨인 경우 적용 대상 스토어의 카테고리 순번으로써 참조 됨.
     */
    private String upperDspCtgryNm;

    private String upperDepth2DspCtgryNo;

    /**
     * 전시 카테고리 명
     ㅁ. 카테고리에 적용된 언어에 따라서 카테고리명, 이미지를 적용하도록 함.
     */
    private String dspCtgryNm;
    /**
     * 카테고리 유형 코드
     ㅁ. 카테고리 유형 : CTGRY_TP
     >. 카테고리형 매장 : CTGRY_SHOP
     >. 비정형 매장 : ADHOC_SHOP

     */
    private String ctgryTpCd;
    /**
     * 상품 전시 단위 코드
     ㅁ. 상품 전시 단위 : GOD_DSP_UNIT
     >. 상품 : GOD
     >. 디자인 그룹 : DSGN_GRP
     */
    private String godDspUnitCd;
    /**
     * 카테고리 깊이 코드
     카테고리 LEVEL
     */
    private String ctgryDpthCd;
    /**
     * 전시 상품 정렬 기준 코드
     ㅁ. 전시 상품 정렬 순서 : DSP_GOD_SORT_STDR
     >. 신상품순 : NEW_GOD_SEQ
     >. 판매수량순 : SALE_QTY_SEQ
     >. 높은할인율순 : BEST_DC_SEQ
     >. 높은가격순 : BEST_PRC_SEQ
     >. 낮은가격순 : LWET_PRC_SEQ
     >. 구매후기순 : PCH_PS_SEQ
     */
    private String dspGodSortStdrCd;
    /**
     * 리프 카테고리 여부
     최하위 카테고리 여부
     Y이면 상품연결 가능함.
     */
    private String leafCtgryYn;
    /**
     * 전시 여부
     전시여부
     */
    private String dspYn;
    /**
     * 특정 URL 전시 여부
     ㅁ. 특정 URL 전시
     >. Y인 경우 일반 카테고리에서는 전시 되지 않음
     >. 특정 URL을 통해 들어 올 때만 전시
     >. 전시 여부에 종속적
     */
    private String spcifyUrlDspYn;
    /**
     * 카테고리 구분선 여부
     ㅁ. 해당 카테고리가 구분선일때 Y로 적용 하고 카테고리 구분선 CSS 코드를 설정
     */
    private String ctgryDivLneYn;
    /**
     * 카테고리 구분선 CSS 코드
     ㅁ. 카테고리 구분선 CSS : CTGRY_DIV_LNE_CSS
     >. Type A : CSS Class A
     >. Type B : CSS Class B

     ㅁ. 카테고리 구분선 CSS에 대한 정의가 없음
     ㅁ. 확인 되는데로 코드 테이블에 업데이트  필요
     */
    private String ctgryDivLneCssCd;
    /**
     * 삭제 여부
     */
    private String deleteYn;
    /**
     * 카테고리 출력 유형 코드
     ㅁ. 카테고리 출력 유형 : CTGRY_OUTPT_TP
     >. 본창 : TH_WIN
     >. 새창 : NEW_WIN
     */
    private String ctgryOutptTpCd;
    /**
     * 출력 구분 코드
     ㅁ. 출력 구분 : OUTPT_SECT
     >. 일반 : GNRL
     >. 링크 : LINK
     */
    private String outptSectCd;
    /**
     * 출력 링크 URL
     ㅁ. 출력이 링크일때 이동항 링크의 URL을 관리 함
     */
    private String outptLinkUrl;
    /**
     * 정렬 순서
     우선 순위
     */
    private java.lang.Integer sortSeq;
    /**
     * 적용 몰 ID
     */
    private String aplMallId;
    /**
     * 전시 브랜드 ID
     ㅁ. 온라인에서 사용하는 브랜드 ID
     */
    private String dspBrndId;
    /**
     * 노출 수준 코드
     ㅁ. 노출 수준 : EXPSR_LVL
     >. A타입(대+중+소+세) : A_TP_LAG_MID_SML_DTL
     >. B타입(중+소+세) : B_TP_MID_SML_DTL
     >. C타입(대+중+소) : C_TP_LAG_MID_SML
     >. D타입(대+소+세) : D_TP_LAG_SML_DTL
     */
    private String expsrLvlCd;
    /**
     * 노출 대상 코드
     ㅁ. 노출 대상 : EXPSR_TGT
     >. DXM 통합몰 : DXM
     >. 브랜드 카테고리 : BRND_CTGRY
     >. DXM 통합몰 + 브랜드 카테고리 : DXM_BRND_CTGRY
     */
    private String expsrTgtCd;
    /**
     * 대표 이미지 파일 명
     ㅁ. 저장된 이미지 파일의 명칭
     */
    private String rprstImgFileNm;
    /**
     * 대표 이미지 파일 URL
     ㅁ. 실제 저장된 이미지 파일의 위치화 파일 확장자까지를 포함

     */
    private String rprstImgFileUrl;
    /**
     * 대표 이미지 대체 내용
     이미지 대체 텍스트
     */
    private String rprstImgAltrtvCont;
    /**
     * 배경 스타일 클래스 ID
     */
    private String bcrnStyleClassId;
    /**
     * PC 모바일 템플릿 개별 사용 여부
     ㅁ. Y인 경우 PC와 모바일 템플릿을 개별적으로 사용
     >. PC와 모바일 템플릿 모두 사용

     ㅁ. N인 경우 PC와 모바일 템플릿을 함께 사용
     >. PC 템플릿 만 사용
     */
    private String pcMobileTmplatIndUseYn;
    /**
     * PC 템플릿 일련번호
     ㅁ. 템플릿을 관리하는 실질 식별자

     */
    private java.lang.Long pcTmplatSn;
    /**
     * 모바일 템플릿 일련번호
     ㅁ. 템플릿을 관리하는 실질 식별자

     */
    private java.lang.Long mobileTmplatSn;

    /**
     * 카테고리 구분 코드
     ㅁ. 카테고리 구분 : CTGRY_SECT
     >. 일반 카테고리 : GNRL_CTGRY
     >. 전략 카테고리 : STRTGY_CTGRY
     >. 신상품 카테고리 : NEW_GOD_CTGRY
     >. 베스트 카테고리 : BST_CTGRY
     */
    private String ctgrySectCd;

    /**
     * 전략 카테고리 속성 코드
     ㅁ. 전략 카테고리 속성 : STRTGY_CTGRY_ATRB
     >. Men : MEN
     >. Women : WOMEN
     >. Kids : KIDS
     */
    private String strtgyCtgryAtrbCd;

    private String metaSjNm;

    private String metaDscrCont;

    private String metaKwd;

    /**
     * 등록자 ID
     등록한 관리자 번호
     */
    private String regtrId;
    /**
     * 등록 일시
     */
    private java.util.Date regDt;
    /**
     * 수정자 ID
     수정한 관리자 번호
     */
    private String udterId;
    /**
     * 수정 일시
     */
    private java.util.Date udtDt;
}
