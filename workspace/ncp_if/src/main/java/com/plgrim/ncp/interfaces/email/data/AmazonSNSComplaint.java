package com.plgrim.ncp.interfaces.email.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmazonSNSComplaint {

    /**
     * 불만 제기를 제출했을 수 있는 수신자에 대한 정보를 포함하는 목록.
     */
    @JsonProperty(value = "complainedRecipients")
    ComplainedRecipients complainedRecipients;

    /**
     * ISP가 수신 거부 알림을 전송한 날짜와 시간으로, ISO8601 형식(YYYY-MM-DDThh:mm:ss.sZ)으로 표시됩니다.
     */
    @JsonProperty(value = "timestamp")
    String timestamp;

    /**
     * 불만 제기의 고유 ID.
     */
    @JsonProperty(value = "feedbackId")
    String feedbackId;

    /**
     * 피드백 보고서의 User-Agent 필드의 값입니다. 보고서를 생성한 시스템의 이름 및 버전을 나타냅니다.
     */
    @JsonProperty(value = "userAgent")
    String userAgent;

    /**
     * ISP로부터 수신된 피드백 보고서의 Feedback-Type 필드의 값. 이 값은 피드백의 유형을 포함합니다.
     */
    @JsonProperty(value = "complaintFeedbackType")
    String complaintFeedbackType;

    /**
     * 피드백 보고서의 Arrival-Date 또는 Received-Date 필드 값으로 ISO8601 형식(YYYY-MM-DDThh:mm:ss.sZ)입니다.
     * 이 필드가 보고서에는 없을 수 있습니다(따라서 JSON에도 없음).
     */
    @JsonProperty(value = "arrivalDate")
    String arrivalDate;

}

@Data
class ComplainedRecipients {
    private String emailAddress;
}