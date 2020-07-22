package com.plgrim.ncp.interfaces.mpush.data;

import com.plgrim.ncp.base.abstracts.AbstractSDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = false)
@Data
public class MPushSmsMmsSDO extends AbstractSDO {

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
     * LMS / MMS 제목
     */
    private String subject;

    /**
     * SMS / LMS / MMS 내용
     */
    private String backupMessage;

    /**
     * 고객 수신 전화번호
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
     * 첨부이미지1
     */
    private String mmsImgPath1;

    /**
     * 첨부이미지2
     */
    private String mmsImgPath2;

    /**
     * 첨부이미지3
     */
    private String mmsImgPath3;

    /**
     * SMS 관리번호
     */
    private String msgKey;

    /**
     * ALIMTALK / SMS Message replace data
     */
    private ArrayList<String> params = new ArrayList<>();

}
