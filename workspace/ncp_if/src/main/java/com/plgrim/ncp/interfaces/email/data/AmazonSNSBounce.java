package com.plgrim.ncp.interfaces.email.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmazonSNSBounce {
    /**
     * Amazon SES가 결정한 반송 메일의 유형.
     */
    @JsonProperty(value = "bounceType")
    String bounceType;

    /**
     * Amazon SES가 결정한 반송 메일의 하위 유형.
     */
    @JsonProperty(value = "bounceSubType")
    String bounceSubType;

    /**
     * 반송된 원래 메일의 수신자 정보를 포함하는 목록.
     */
    @JsonProperty(value = "bouncedRecipients")
    List<BouncedRecipients> bouncedRecipients;

    /**
     *
     * @return
     */
    public List<String> getBouncedRecipientEmail() {
        List<String> list = new ArrayList<>();

        for (BouncedRecipients recipient : bouncedRecipients) {
            list.add(recipient.getEmailAddress());
        }

        return list;
    }

    /**
     * ISP가 반송 메일 알림을 전송한 날짜와 시간으로, ISO8601 형식(YYYY-MM-DDThh:mm:ss.sZ)으로 표시됩니다.
     */
    @JsonProperty(value = "timestamp")
    String timestamp;

    /**
     * 반송 메일의 고유 ID.
     */
    @JsonProperty(value = "feedbackId")
    String feedbackId;

    /**
     * DSN의 Reporting-MTA 필드의 값. DSN에서 설명하는 전송, 중계 또는 게이트웨이 작업을 시도하는 메시지 전송 권한(MTA)의 값입니다.
     */
    @JsonProperty(value = "reportingMTA")
    String reportingMTA;

}

@Data
class BouncedRecipients {
    private String emailAddress;
    private String action;
    private String status;
    private String diagnosticCode;
}
