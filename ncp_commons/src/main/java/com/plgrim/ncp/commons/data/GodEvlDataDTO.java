package com.plgrim.ncp.commons.data;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;


@Data
@EqualsAndHashCode(callSuper = false)
public class GodEvlDataDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private GodGodEvl godGodEvl; // 상품평
    private God god; // 상품
    private String godEvlTp; // 상품평구분
    private List<GodGodEvlAtchFile> godGodEvlAtchFileList; // 상품평파일(저장용)
	private List<GodGodEvlAtchFile> delGodGodEvlAtchFileList; // 상품평파일(삭제용)
    private List<String> imgFile; // 파일경로
	private List<String> imgSj; // 원본파일명
	private List<BigDecimal> imgSize; // 이미지사이즈
	private List<Integer> delImgSn; // 삭제할 이미지 번호
    private String mbrTpCd; // 회원유형
    private Integer no; // 일련번호
	private String orgBstGodEvlYn;	// org베스트상품평여부
	private Integer orgBstGodEvlSortSeq;   // 베스트상품평정렬순서 20160526_주동민_sr#20343 [상품평 관리 화면 조회값 및 컬럼값 추가 요청]
}
