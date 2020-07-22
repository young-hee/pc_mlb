package com.plgrim.ncp.biz.vendor.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.com.ComReqst;
import com.plgrim.ncp.base.entities.datasource1.com.ComReqstStplatAgr;

@Data
@EqualsAndHashCode(callSuper=false)
public class VendorReqstRegDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private ComReqst comReqst = new ComReqst();
	
	private ComReqstStplatAgr comReqstStplatAgr = new ComReqstStplatAgr();	
	
	private String applcntTel;
	
	private List<MultipartFile> upload;
	
	private String loginId;
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	/**
     * 등록자/수정자 각각의 ENTITY에 설정하기 위한 setter.
     *
     * @param loginId [설명]
     * @since 2015. 8. 7
     */
    @Override
    public void setLoginId(String loginId) {

        this.loginId = loginId;

        comReqst.setRegtrId(loginId);
        comReqst.setUdterId(loginId);
    }
}
