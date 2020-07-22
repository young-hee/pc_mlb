package com.plgrim.ncp.biz.display.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;

@Data
@EqualsAndHashCode(callSuper = false)
public class InterestSearchFoDTO extends AbstractDTO {
	/**
	 * 
	 */
    private static final long serialVersionUID = -6726992616400596281L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    private String sesionId;
	private String mbrNo;
	private String mobileFlag;
	private String brandFlag;// 브랜드 그룹 구분
	private String mbrBrthYear;// 생년
	private String godNo;// 상품번호
	private Integer godsCount;// 조회 할 상품 수
	private List<String> exceptGodList;// 제외 상품 번호
	private String aplMbrTp;
	private String aplMbrAtrb;
	private String grpcoId;
	private String singleExist;
	private String bskTpCd;// 장바구니 유형 코드
	private List<String> favBrndList;// 즐겨찾기 전시 브랜드 카테고리 번호
	private List<String> godImgList;// 상품 이미지용 상품 번호
	private String recomendSexCd;// 상품 성별 코드
	private List<String> godNoList;// 상품 번호 목록
	private String cnrSn;// 코너 번호
	private String dspCtgryNo;// 전시카테고리번호
	private List<MbrTodayGod> skyScrapers;
	private String dspBrndId;	// 전시 카테고리와 전시 브랜드 맵핑
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
