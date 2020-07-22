package com.plgrim.ncp.biz.goods.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	:	상품 Excel DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsExcelDTO")
public class GoodsExcelDTO extends GoodsValidDTO{
	public interface GoodsInfoChecks {}					// 상품상세정보 일괄 업로드 Valid
	public interface GoodsStyleConnctionChecks {}		// 상품스타일 연결 일괄 업로드 Valid

    /**
 	 * 엑셀 그룹
	 */
    private String group;	
	
    /* 상품종류 */
    @NotEmpty(groups={GoodsInfoChecks.class}, message="상품유형은 필수입력항목입니다.")
    @NotNull(groups={GoodsInfoChecks.class}, message="상품유형은 필수입력항목입니다.")
    private String godTpCd;

    /* ERP품번 */
    @NotEmpty(groups={GoodsInfoChecks.class, GoodsStyleConnctionChecks.class}, message="ERP품번은 필수입력항목입니다.")
    @NotNull(groups={GoodsInfoChecks.class, GoodsStyleConnctionChecks.class}, message="ERP품번은 필수입력항목입니다.")
    private String erpGodNo;
    
    /* 매장 픽업 가능 여부 */
    private String shopPkupPsbYn;
    
    /* 상품명 */
    @NotEmpty(groups={GoodsInfoChecks.class},  message="상품명은 필수입력항목입니다.")
    @NotNull(groups={GoodsInfoChecks.class}, message="상품명은 필수입력항목입니다.")
    private String godNm;
    
    /* 모바일 상품명 */
    @NotEmpty(groups={GoodsInfoChecks.class}, message="모바일상품명은 필수입력항목입니다.")
    @NotNull(groups={GoodsInfoChecks.class}, message="모바일상품명은 필수입력항목입니다.")
    private String mobileGodNm;
    
    /* EP 상품명 */
    private String epGodNm;

    /* 전시 몰 */
    //@NotEmpty(groups={GoodsInfoChecks.class}, message="전시몰ID는 필수입력항목입니다.")
    //@NotNull(groups={GoodsInfoChecks.class}, message="전시몰ID는 필수입력항목입니다.")    
    private String mallId;

    /* 상품명(영문) */
    private String godNmEng;
    
    /* 모바일 상품명(영문) */
    private String mobileGodNmEng;
    
    /* 상품명(중문) */
    private String godNmChi;
    
    /* 모바일 상품명(중문) */
    private String mobileGodNmChi;
    
    /* 전시카테고리 */
    //@NotEmpty(groups={GoodsInfoChecks.class}, message="전시카테고리는 필수입력항목입니다.")
    //@NotNull(groups={GoodsInfoChecks.class}, message="전시카테고리는 필수입력항목입니다.")
    //@Size(groups={GoodsInfoChecks.class}, min=3, message="전시카테고리는 최소 3자리 이상 입니다.")    
    private String dspCtgryNo;
    
    /* 제조년월일 */
    //@NotEmpty(groups={GoodsInfoChecks.class}, message="제조연월(YYYYM, YYYYMM 형식으로 표기)은 필수입력항목입니다.")
    //@NotNull(groups={GoodsInfoChecks.class}, message="제조연월(YYYYM, YYYYMM 형식으로 표기)은 필수입력항목입니다.")
    //@Pattern(groups={GoodsInfoChecks.class}, regexp = "^[0-9]*$", message="제조연월(YYYYM, YYYYMM 형식으로 표기)은 형식으로 입력하세요.")
    //@Size(groups={GoodsInfoChecks.class}, min=5, max=6, message="제조연월(YYYYM, YYYYMM 형식으로 표기)은 5~6자리 입니다.")
    private String mnfcturDate;    
    
    /* 제조국 */
    private String mnfcturNationNm;

    /* 제조국(영문) */
    private String mnfcturNationNmEng;
    
    /* 제조국(중문) */
    private String mnfcturNationNmChi;
    
    /* 세탁코드 */
    private String lndrCd;

    /* 검색유의어 */
    @Size(groups={GoodsInfoChecks.class}, max=500, message="검색키워드는 최대 500자(한글 포함 150자) 입니다.")
    //@Pattern(groups={GoodsInfoChecks.class}, regexp = ".*[[;][=][<][>]\\'\\\"].*", message="특수문자 \" \' < > = ; 는 입력할 수 없습니다.")
    private String godSrchSnm;    
    
    /* 소재 */
    private String matrDscr;

    /* 소재(영문) */
    private String matrDscrEng;
    
    /* 소재(중문) */
    private String matrDscrChi;
    
    /* 상품 상세 설명 */
    private String godDetailDscr1;
    
    /* 상품 상세 설명(영문) */
    private String godDetailDscrEng1;
    
    /* 상품 상세 설명(중문) */
    private String godDetailDscrChi1;
    
    /* 상품 상세 설명 */
    private String godDetailDscr2;
    
    /* 상품 상세 설명(영문) */
    private String godDetailDscrEng2;
    
    /* 상품 상세 설명(중문) */
    private String godDetailDscrChi2;
    
    /* 사이즈 조견표 이미지 */
    private String sizeImg;
    
	/* 사이즈 조견표 HTML */
	private String sizeLktbHtml;

	/* 사이즈 조견표 HTML(영문) */
	private String sizeLktbHtmlEng;
	
	/* 사이즈 조견표 HTML(중문) */
	private String sizeLktbHtmlChi;
	
	/* 모바일 사이즈 조견표 HTML */
	private String mobileSizeLktbHtml;
	
	/* 모바일 사이즈 조견표 HTML(영문) */
	private String mobileSizeLktbHtmlEng;
	
	/* 모바일 사이즈 조견표 HTML(중문) */
	private String mobileSizeLktbHtmlChi;
}
