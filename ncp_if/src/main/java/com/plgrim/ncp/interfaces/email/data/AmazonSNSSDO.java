package com.plgrim.ncp.interfaces.email.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;
import lombok.Data;

/**
 * Amazon Simple Notification Service Json Data
 */
@Data
public class AmazonSNSSDO extends AbstractSDO {
    /**
     * 메시지의 유형입니다.
     * 구독 확인의 경우 유형은 SubscriptionConfirmation입니다.
     * 알림의 경우 유형은 Notification입니다.
     * 구독 해지 확인의 경우 유형은 UnsubscribeConfirmation입니다.
     */
    @JsonProperty(value = "Type")
    String type;

    /**
     * 범용 고유 식별자(Universally Unique Identifier)로 게시되는 각 메시지마다 고유합니다.
     * 재시도 하는 동안 Amazon SNS가 재전송하는 메시지의 경우 원본 메시지의 메시지 ID가 사용됩니다.
     */
    @JsonProperty(value = "MessageId")
    String messageId;

    /**
     * 구독 확인을 위해 ConfirmSubscription 작업에 사용할 수 있는 값입니다.
     * 또는, 간단히 SubscribeURL을 방문하면 됩니다.
     */
    @JsonProperty(value = "Token")
    String token;

    /**
     * 이 메시지가 구독된 주제에 대한 Amazon Resource Name(ARN)입니다.
     */
    @JsonProperty(value = "TopicArn")
    String topicArn;

    /**
     * 알림이 주제에 게시되었을 때 제목 매개 변수이며, 옵션 매개 변수입니다.
     * 제목이 지정되지 않을 경우에는 이 이름/값 쌍은 본 JSON 문서에 표시되지 않습니다.
     */
    @JsonProperty(value = "Subject")
    String subject;

    /**
     * 메시지를 설명하는 문자열입니다.
     */
    @JsonProperty(value = "Message")
    String message;

    /**
     * 사용한 Amazon SNS 서명의 버전입니다.
     */
    @JsonProperty(value = "SignatureVersion")
    String signatureVersion;

    /**
     * Message, MessageId, Type, Timestamp, TopicArn 값을 가진 Base64 인코딩 “SHA1withRSA” 서명입니다.
     */
    @JsonProperty(value = "Signature")
    String signature;

    /**
     * 메시지에 서명하기 위해 사용된 인증서의 URL입니다.
     */
    @JsonProperty(value = "SigningCertURL")
    String signingCertURL;

    /**
     * 구독 확인을 위해 방문해야 하는 URL입니다.
     * 또는, 그 대신 Token을 ConfirmSubscription 작업으로 사용하여 구독을 확인하면 됩니다.
     */
    @JsonProperty(value = "SubscribeURL")
    String subscribeURL;

    /**
     * 이 주제에서 엔드포인트를 구독 해지하는데 사용하는 URL입니다.
     * 이 URL을 방문하면 Amazon SNS는 엔드포인트를 구독 해지하고 이 엔드포인트로 전송하는 알림을 중지합니다.
     */
    @JsonProperty(value = "UnsubscribeURL")
    String unsubscribeURL;

    /**
     * 전송된 시간(GMT)입니다.
     */
    @JsonProperty(value = "Timestamp")
    String timestamp;

}
