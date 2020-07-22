package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.enums.DisplayEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper=false)
public class V2DspFoCtgryResult extends AbstractResult{

	//카테고리 정보
	DspCtgry ctgryInfo;

	//브랜드 리스트
	List<DspCtgry> brndList;

	//전체 전시 카테고리 트리 리스트
	List<DspCtgry> allCtgryList;

	//전시 카테고리 트리 리스트
	List<DspCtgry> ctgryList;

	//전략 카테고리 리스트
	List<DspCtgry> strtgyCtgryList;

	//Outlet 카테고리 리스트
	List<DspCtgry> outletCtgryList;

	//모바일 전시 카테고리 트리 리스트
	List<DspCtgryLNBResult> mbCtgryList;

	//전략 카테고리 리스트
	List<DspCtgryLNBResult> mbStrtgyCtgryList;

	//Outlet 카테고리 리스트
	List<DspCtgryLNBResult> mbOutletCtgryList;

	//기타 카테고리 리스트
	List<DspCtgryLNBResult> etcCtgryList;

	//신상품, 인기상품, 세일상품 카테고리 리스트
	List<DspCtgry> newTopOnCtgryList;

	List<DspCtgry> locationList;

	//상품상세여부
	String godDetailYN;

	String dspCtgryNo;

	String brandShopNo;

	String brndShopId;

	String mallGubun;

	String locationTp;

	//전략카테고리, Outlet카테고리 번호
	String etcCtgryNo;

	CategoryChackResult ctgyChack;


	//대카테고리
	String women = DisplayEnum.WOMEN.toString();

	String men = DisplayEnum.MEN.toString();

	String kids = DisplayEnum.KIDS.toString();

	String otlt= DisplayEnum.OTLTCTG.toString();

	String brandShopNm;
	
	List<DspCnrFrResult> lnbCnr;
}
