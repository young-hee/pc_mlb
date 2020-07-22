package com.plgrim.ncp.interfaces.email.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmazonSNSOpen {

    /**
     * 수신자의 IP 주소입니다.
     */
    @JsonProperty(value = "ipAddress")
    private String ipAddress;

    /**
     * 열기 이벤트가 발생한 날짜와 시간으로, ISO8601 형식(YYYY-MM-DDThh:mm:ss.sZ)으로 표시됩니다.
     */
    @JsonProperty(value = "timestamp")
    private String timestamp;

    /**
     * 수신자가 이메일을 여는 데 사용한 이메일 클라이언트 또는 디바이스의 사용자 에이전트입니다.
     */
    @JsonProperty(value = "userAgent")
    private String userAgent;

}
