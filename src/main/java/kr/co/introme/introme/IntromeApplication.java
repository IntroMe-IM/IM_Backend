package kr.co.introme.introme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@ComponentScan(basePackages = {"kr.co.introme.introme.domain.member"})
public class IntromeApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntromeApplication.class, args);
	}

}
