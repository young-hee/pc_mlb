package com.plgrim.ncp.biz.member.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MemberOrdGodFoDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -146264237760548175L;

	private Mbr mbr;
	
	private Ord ord;
	
	private OrdGod ordGod;
	
	private GodGodEvl godGodEvl;
	
	private GodGodEvlAtchFile godGodEvlAtchFile;
	
	private String dateStart;
	
	private String dateEnd;
	
	private String srchMonth;
	
	private List<String> deleteFileNm;
	
	private SysBrnd sysBrnd;
	
	private List<String> fileNames;
	
	private String filePath;
	
	private List<String> filePaths;
	
	private List<String> atchFileTurn;
	
	private List<String> plgrimshopMallIdList;

	private String godNo;

	private int promtReviewMore;

	private int fromNo;

	private int toNo;

	private String godEvlExpsrSectCd;
	
	/*
	 * 포토상품평 우선 조회 여부
	 * */
	private String imgGodEvlPrioExpsrYn;
	
	/** 이미지 사이즈 */
	private String imgSizeCd = "100X132";
	
	private String userTrackingId;
	
    /** 포토 리뷰 */
    private String photoYn;
}
