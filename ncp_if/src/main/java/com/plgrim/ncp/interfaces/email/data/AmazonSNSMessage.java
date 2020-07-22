package com.plgrim.ncp.interfaces.email.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;
import lombok.Data;

@Data
public class AmazonSNSMessage extends AbstractSDO {

    /**
     * 이벤트 유형을 설명하는 문자열입니다.
     * 가능한 값: Delivery, Send, Reject, Open, Click, Bounce, Complaint 또는 Rendering Failure.
     */
    @JsonProperty(value = "eventType")
    String eventType;

    /**
     * 이벤트를 생성하는 이메일 관련 정보를 포함하는 JSON 객체입니다.
     */
    @JsonProperty(value = "mail")
    AmazonSNSMail mail;

    /**
     * 이 필드는 eventType이 Bounce인 경우에만 존재합니다.
     * 이 파일에는 반송 관련 정보가 포함되어 있습니다.
     */
    @JsonProperty(value = "bounce")
    AmazonSNSBounce bounce;

    /**
     * 이 필드는 eventType이 Complaint인 경우에만 존재합니다.
     * 이 파일에는 수신 거부 관련 정보가 포함되어 있습니다.
     */
    AmazonSNSComplaint complaint;

    /**
     * 이 필드는 eventType이 Delivery인 경우에만 존재합니다.
     * 이 파일에는 전송 관련 정보가 포함되어 있습니다.
     */
    AmazonSNSDelivery delivery;

    /**
     * 이 필드는 eventType이 Send인 경우에만 존재합니다.
     */
    AmazonSNSSend send;

    /**
     * 이 필드는 eventType이 Reject인 경우에만 존재합니다.
     * 이 파일에는 거부 관련 정보가 포함되어 있습니다.
     */
    AmazonSNSReject reject;

    /**
     * 이 필드는 eventType이 Open인 경우에만 존재합니다.
     * 이 파일에는 열기 이벤트 관련 정보가 포함되어 있습니다.
     */
    AmazonSNSOpen open;

    /**
     * 이 필드는 eventType이 Rendering Failure인 경우에만 존재합니다.
     * 이 파일에는 렌더링 오류 이벤트 관련 정보가 포함되어 있습니다.
     */
    AmazonSNSFailure failure;

}
