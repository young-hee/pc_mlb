package com.plgrim.ncp.biz.member.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPntIntrlckHist;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MemberOrdGodFoResult extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4519308777759767334L;

	private Mbr mbr;
	
	private Ord ord;
	
	private OrdGod ordGod;

	private GodGodEvl godGodEvl;

	private List<GodGodEvlAtchFile> godGodEvlAtchFiles;
	
	private SysBrnd sysBrnd;
	
	private MbrPntIntrlckHist mbrPntIntrlckHist;
	
	private String colorStyleCd;
	
	private List<String> deleteFileNm;
	
	private List<String> fileNames;
	
	private List<String> filePaths;

	private long cnt;

	private long realOrdQty;	/*주문상품수량 - 클레임수량 */

	private String pckageGodTpCd; /*패키지형 상품 유형코드*/

	private String dlvStatCd;  /* 배송 상태 코드 */
	
    /** 추천 성별명 */
    private String recomendSexNm;
    
    /** 텍스트 리뷰 */
    private String textYn;
    
    /** 포토 리뷰 */
    private String photoYn;
    
    /** 상품 URL */
    private String godUrl;
    
    /** 리뷰포인트 */
    private java.math.BigDecimal accmlAmt;
    
	//private List<MemberOrdGodReviewResult> godOptList; // 세트,패키지 상품 옵션 리스트


}
