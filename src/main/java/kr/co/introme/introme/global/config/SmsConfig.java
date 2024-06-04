package kr.co.introme.introme.global.config;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {

    private final SmsProperties smsProperties;

    public SmsConfig(SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
    }

    @Bean
    public DefaultMessageService defaultMessageService() {
        return NurigoApp.INSTANCE.initialize(smsProperties.getApiKey(), smsProperties.getApiSecret(), "https://api.coolsms.co.kr");
    }
}
