package com.plgrim.ncp.interfaces.email.data;

import com.plgrim.ncp.base.abstracts.AbstractSDO;
import lombok.Data;

@Data
public class EmailHtmlSDO extends AbstractSDO {

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
     * Email Subject
     */
    private String subject;

    /**
     * Email HTML Body
     * (Mail server HTML 형식 지원 시 적용)
     */
    private String htmlBody;

    /**
     * Email Text Body
     * (Mail server HTML 형식 미지원 시 적용)
     */
    private String textBody;

}
