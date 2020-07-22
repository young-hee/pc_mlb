package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

/**
 * @author Vito
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KaKaoToken extends AbstractDTO {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expires_in;
	private String scope;
	private String refresh_token_expires_in;
	
}
