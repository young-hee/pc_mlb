package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstCd;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysPrudDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */
	private SysPrdlstCd sysPrdlstCd;
	private String prdlstNmEng; // 다국어코드명(영어)
	private String prdlstNmChi; // 다국어코드명(중국어)
	private Long engMlangSn; // 다국어일련번호(영어)
	private Long chiMlangSn; // 다국어일련번호(중국)
    private Long godWt; // 무게
    private String prdlstSectCd;
}
