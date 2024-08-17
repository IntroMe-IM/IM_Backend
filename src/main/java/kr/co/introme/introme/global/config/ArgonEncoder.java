package kr.co.introme.introme.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ArgonEncoder {
    @Value("${security.password.hash}")
    private String storedHash; // application.yml에서 로드한 비밀번호 해시

    private final Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 65536, 4);

    public boolean validateCode(String inputCode) {
        // 입력된 코드와 저장된 해시를 비교
        return passwordEncoder.matches(inputCode, storedHash);
    }
}
