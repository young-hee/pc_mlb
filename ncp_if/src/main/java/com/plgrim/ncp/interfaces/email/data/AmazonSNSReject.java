package com.plgrim.ncp.interfaces.email.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmazonSNSReject {

    /**
     * 이메일이 거부된 이유입니다.
     * 유일하게 가능한 값은 Bad content로, Amazon SES가 바이러스 포함 이메일을 감지했다는 뜻입니다.
     * 메시지가 거부되면 Amazon SES는 메시지 처리를 중지하고 해당 메시지를 수신자의 메일 서버로 전송하지 않습니다.
     */
    @JsonProperty(value = "reason")
    private String reason;
}
