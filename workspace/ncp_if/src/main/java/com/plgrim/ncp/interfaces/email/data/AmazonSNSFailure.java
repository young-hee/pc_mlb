package com.plgrim.ncp.interfaces.email.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmazonSNSFailure {

    /**
     * 이메일을 전송하는 데 사용하는 템플릿의 이름입니다.
     */
    @JsonProperty(value = "templateName")
    private String templateName;

    /**
     * 렌더링 오류에 관한 자세한 정보를 제공하는 메시지입니다.
     */
    @JsonProperty(value = "errorMessage")
    private String errorMessage;

}
