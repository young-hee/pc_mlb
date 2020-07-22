package com.plgrim.ncp.interfaces.email.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmazonSNSMail {

    /**
     * 메시지가 전송된 날짜와 시간으로, ISO8601 형식(YYYY-MM-DDThh:mm:ss.sZ)으로 표시됩니다.
     */
    @JsonProperty(value = "timestamp")
    String timestamp;

    /**
     * Amazon SES에서 메시지에 할당한 고유 ID입니다. Amazon SES는 메시지를 보낼 때 이 값을 반환합니다.
     * <p>
     * 참고
     * 이 메시지 ID는 Amazon SES에서 할당한 것입니다. mail 객체의 headers 및 commonHeaders 필드에서 원래 메시지의 메시지 ID를 찾을 수 있습니다.
     */
    @JsonProperty(value = "messageId")
    String messageId;

    /**
     * 메시지를 전송한 이메일 주소(엔벌로프 MAIL FROM 주소).
     */
    @JsonProperty(value = "source")
    String source;

    /**
     * 이메일을 전송하는 데 사용된 자격 증명의 Amazon 리소스 이름(ARN).
     * 권한 부여 전송의 경우 sourceArn은 자격 증명 소유자가 위임 발신자에게 메일 전송 시 사용하도록 권한을 부여한 자격 증명의 ARN입니다.
     * 권한 부여 전송에 대한 자세한 내용은 전송 권한 부여 사용 단원을 참조하십시오.
     */
    @JsonProperty(value = "sourceArn")
    String sourceArn;

    /**
     * 이메일을 전송하는 데 사용된 계정의 AWS 계정 ID.
     * 권한 부여 전송의 경우 sendingAccountId는 위임 발신자의 계정 ID입니다.
     */
    @JsonProperty(value = "sendingAccountId")
    String sendingAccountId;

    /**
     * 원래 메일의 수신자인 이메일 주소의 목록.
     */
    @JsonProperty(value = "destination")
    List<String> destination;

    /**
     * 알림에서 헤더가 잘렸는지 여부를 나타내는 문자열입니다.
     * 헤더의 용량이 10KB를 초과할 경우 헤더가 잘립니다.
     * 가능한 값은 true 및 false입니다.
     */
    @JsonProperty(value = "headersTruncated")
    boolean headersTruncated;

    /**
     * 이메일의 원래 헤더 목록입니다. 목록의 각 헤더에는 name 필드와 value 필드가 존재합니다.
     * <p>
     * 참고
     * headers 필드의 모든 메시지 ID는 Amazon SES에 전달한 원래 메시지에서 가져온 것입니다.
     * 이어서 Amazon SES가 메시지에 할당한 메시지 ID는 mail 객체의 messageId 필드에 들어 있습니다.
     */
    @JsonProperty(value = "headers")
    List<Headers> headers;

    /**
     * 자주 사용되는 원래 이메일 헤더 목록입니다. 목록의 각 헤더에는 name 필드와 value 필드가 존재합니다.
     * <p>
     * 참고
     * commonHeaders 필드의 모든 메시지 ID는 Amazon SES에 전달한 원래 메시지에서 가져온 것입니다.
     * 이어서 Amazon SES가 메시지에 할당한 메시지 ID는 mail 객체의 messageId 필드에 들어 있습니다.
     */
    @JsonProperty(value = "commonHeaders")
    CommonHeaders commonHeaders;

}

@Data
class Headers {
    private String name;
    private String value;
}

@Data
class CommonHeaders {
    private List<String> from;
    private List<String> to;
    private Date date;
    private String messageId;
    private String subject;
}