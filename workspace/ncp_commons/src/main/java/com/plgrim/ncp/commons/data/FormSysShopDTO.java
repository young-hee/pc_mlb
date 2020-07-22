package com.plgrim.ncp.commons.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormSysShopDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private String shopId; // 매장ID
	private String shopType; // 조회구분
	private String shopValue; // 조회구분값
	private String shopTp; // 매장유형
	private List<String> saleShopYns; // 판매매장여부
	private List<String> drtstorDrdlShopYns; // 백화점직거래여부
	private List<String> wrhusYns; // 창고여부
	private List<String> foExpsrYn; // 프론트 노출여부
	private List<String> useYns; // 사용여부
	private List<String> virtlShopYn; // 가상매장여부
}
