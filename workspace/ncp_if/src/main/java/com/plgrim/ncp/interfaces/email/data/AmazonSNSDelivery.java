package com.plgrim.ncp.interfaces.email.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmazonSNSDelivery {
    /**
     * Amazon SES가 수신자의 메일 서버로 이메일을 전송한 날짜와 시간으로, ISO8601 형식(YYYY-MM-DDThh:mm:ss.sZ)으로 표시됩니다.
     */
    @JsonProperty(value = "timestamp")
    String timestamp;

    /**
     * Amazon SES가 발신자로부터 요청을 수락한 때로부터 Amazon SES가 수신자의 메일 서버로 메시지를 전송한 때까지의 시간(단위: 밀리초).
     */
    @JsonProperty(value = "processingTimeMillis")
    String processingTimeMillis;

    /**
     * 전송 이벤트가 적용되는 의도한 수신자의 목록.
     */
    @JsonProperty(value = "recipients")
    List<String> recipients;

    /**
     * Amazon SES로부터 이메일을 수락한 원격 ISP의 SMTP 응답 메시지. 이 메시지는 이메일, 수신 메일 서버, 수신 ISP마다 다릅니다.
     */
    @JsonProperty(value = "smtpResponse")
    String smtpResponse;

    /**
     * 메일을 전송한 Amazon SES 메일 서버의 호스트 이름.
     */
    @JsonProperty(value = "reportingMTA")
    String reportingMTA;

}

