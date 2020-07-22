package com.plgrim.ncp.biz.member.data;

import java.util.Date;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MemberAgreementDTO extends AbstractDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5974606481089437732L;

	private String stplatCd;
	
	private String stplatDscr;
	
	private String stplatIemAgrYn;
	
	private Date regDt;
	
	private Date udtDt;
}
