package kr.co.introme.introme.global.config;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class Argon2HashGenerator {
    public static void main(String[] args) {
        // Argon2PasswordEncoder 인스턴스 생성
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 65536, 4);

        // 원문 비밀번호
        String rawPassword = "00000";

        // 비밀번호 인코딩
        String encodedPassword = encoder.encode(rawPassword);

        // 인코딩된 비밀번호 출력
        System.out.println("Argon2 Encoded Password: " + encodedPassword);
    }
}