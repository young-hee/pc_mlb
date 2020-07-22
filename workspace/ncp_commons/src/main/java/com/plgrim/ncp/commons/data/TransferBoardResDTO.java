package com.plgrim.ncp.commons.data;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("transferBoardDTO")
public class TransferBoardResDTO extends AbstractEntity {
	/**
	 * 
	 */
    private static final long serialVersionUID = 9113553783478319970L;
	
	// ~ Instance fields. ~~~~~~~~~~~~~~
	
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	
	// ~ Getter and Setter. ~~~~~~~~~~~~
    
    private String requstSn;
    private String jobRequstAnsTurn;
    private String requstAnsAtchmnflTurn;
    private String requstAnsAtchFileNm;
    private String requstAnsAtchFileUrl;
    private String requstAnsAtchFileCpcty;
    private String regtrId;
    private String regDt;
    private String udterId;
    private String udtDt;
	
}