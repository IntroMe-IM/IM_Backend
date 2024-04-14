package kr.co.introme.introme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class IntromeApplication {

	public static void main(String[] args) {
		// .env 파일 로드
		Dotenv dotenv = Dotenv.configure().directory("src/main/resources/.env").load();

		// 시스템 프로퍼티로 환경 변수 설정
		System.setProperty("DB_DRIVER", dotenv.get("DB_DRIVER"));
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		// 애플리케이션 실행
		SpringApplication.run(IntromeApplication.class, args);
	}
}
