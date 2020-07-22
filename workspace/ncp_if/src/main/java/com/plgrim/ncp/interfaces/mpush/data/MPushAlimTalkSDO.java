package com.plgrim.ncp.interfaces.mpush.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.base.abstracts.AbstractSDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value = Include.NON_EMPTY)
public class MPushAlimTalkSDO extends AbstractSDO {

    private static final long serialVersionUID = 1L;

    /**
     * 회원 번호
     * 회원 : MBR_NO
     * 비회원 : NMBR
     */
    private String mbrNo;

    /**
     * Mall Id
     */
    private String mallId;

    /**
     * SMS/MMS 즉시 발송 Flag
     * Default 예약발송(true 즉시, false 예약발송)
     */
    private boolean sendDirectFlag = false;

    /**
     * 회원 수신 전화번호
     */
    private String receiveMobileNo;

    /**
     * Callback 번호 (고객센터)
     */
    private String callbackNo;

    /**
     * 예약 발송 시간
     * yyyyMMddHHmmss 형식
     */
    private String sendReserveDate;


    /**
     * ALIMTALK 관리번호
     */
    private String msgKey;

    /**
     * ALIMTALK / SMS Message replace data
     */
    private ArrayList<String> params;


}
