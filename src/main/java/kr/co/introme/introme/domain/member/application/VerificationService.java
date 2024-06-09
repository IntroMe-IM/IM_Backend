package kr.co.introme.introme.domain.member.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.introme.introme.global.config.SmsProperties;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VerificationService {

    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private final SmsProperties smsProperties;

    @Autowired
    public VerificationService(SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
    }

    public String generateVerificationCode() {
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    public void saveVerificationCode(String phoneNumber, String code) {
        verificationCodes.put(phoneNumber, code);
    }

    public boolean verifyCode(String phoneNumber, String code) {
        // 환경 변수에서 디버그 코드를 가져와서 확인
        if (smsProperties.getDebugCode().equals(code)) {
            return true;
        }
        return code.equals(verificationCodes.get(phoneNumber));
    }
}
