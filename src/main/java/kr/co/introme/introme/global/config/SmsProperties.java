package kr.co.introme.introme.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;

@Getter
@Configuration
public class SmsProperties {

    @Value("${sms.api-key}")
    private String apiKey;

    @Value("${sms.api-secret}")
    private String apiSecret;

    @Value("${sms.from-number}")
    private String fromNumber;
}
