package com.plgrim.ncp.interfaces.email.data;

import com.plgrim.ncp.base.abstracts.AbstractSDO;
import lombok.Data;

@Data
public class EmailTemplateSDO extends AbstractSDO {

    private String callerId;

    /**
     * Mall Email
     * SenderName 지정 시 아래 형식 적용
     * SenderName <sample@example.com>
     */
    private String mallEmail;

    /**
     * 회원 Email
     */
    private String mbrEmail;

    /**
     * Aws SES TemplateName
     */
    private String templateName;

    /**
     * Aws SES Template replace data
     * JSON 형식
     */
    private String templateData;

}
