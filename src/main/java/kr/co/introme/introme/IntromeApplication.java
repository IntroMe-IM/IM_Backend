package kr.co.introme.introme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class IntromeApplication {

	public static void main(String[] args) {
		// 애플리케이션 실행
		SpringApplication.run(IntromeApplication.class, args);
	}
}
