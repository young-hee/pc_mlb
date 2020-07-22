package com.plgrim.ncp.base.entities.datasource1.god;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godNotiExtend")
public class GodNotiExtend extends GodNoti {
	private static final long serialVersionUID = -5839313544630240833L;
	
	private String godNo;
	private String erpGodNo;
	private String brndId;
	private String godNm;	
	private String brndNm;
    private String godNotiSectNm;    
    private String mallNm;
    private String notiTpNm;
    private String langNm;
    private String udterNm;
    private String regtrNm;  
    private String sesonGrpCd;
    private String sesonCd;
	private String dspCtgryNo;    
	private String dspCtgryNm;    
}
