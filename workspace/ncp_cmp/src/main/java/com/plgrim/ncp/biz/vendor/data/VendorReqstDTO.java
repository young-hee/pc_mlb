package com.plgrim.ncp.biz.vendor.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.com.ComReqst;

@Data
@EqualsAndHashCode(callSuper=false)
public class VendorReqstDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private ComReqst	comReqst = new ComReqst();						// 입점 신청 엔티티
	private String startDt;					// 검색 시작일시
	private String endDt;						// 검색 종료일시
	private String searchNm;				// 이름, 대표자명
	private String searchDateType;	// 기간검색 유형
	private String serachNameType;//이름검색 유형
	private String applcntTel;				// 신청자 전화번호
	private String loginId;					// 로그인 ID
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
	 /**
     * 등록자/수정자 각각의 ENTITY에 설정하기 위한 setter.
     *
     * @param loginId [설명]
     * @since 2015. 8. 7
     */
    @Override
    public void setLoginId(String loginId) {

        this.loginId = loginId;

        comReqst.setRegtrId(loginId);
        comReqst.setUdterId(loginId);
    }
}
