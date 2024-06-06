package kr.co.introme.introme.domain.member.application;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VerificationService {

    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public String generateVerificationCode() {
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    public void saveVerificationCode(String phoneNumber, String code) {
        verificationCodes.put(phoneNumber, code);
    }

    public boolean verifyCode(String phoneNumber, String code) {
        return code.equals(verificationCodes.get(phoneNumber));
    }
}
