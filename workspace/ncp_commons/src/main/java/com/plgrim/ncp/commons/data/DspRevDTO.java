package com.plgrim.ncp.commons.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspRevDTO extends AbstractDTO {

    private static final long serialVersionUID = 7722610666085332149L;

    String pgeSect;		//전시 페이지 구분값
    String dspBrndId;	//전시 브랜드 아이디
    String revSnSet;	//리비전 일련번호
    String dvcSectCd;   //PC/MOBILE 구분
    Long abTestSn;      //AB 테스트 순번
    Long tmplatSn;      //템플릿 순번

    String tmplatScKey;

    /** 브랜드몰 카테고리 템플릿 조회를 위한 Key 브랜드ID */
    String tmplatKeyBrndId;

    DspRevCpst dspRevCpst;
}
