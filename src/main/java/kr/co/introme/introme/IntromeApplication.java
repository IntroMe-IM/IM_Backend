package kr.co.introme.introme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = {"kr.co.introme.introme.domain"})
public class IntromeApplication {

	public static void main(String[] args) {
//		System.out.println(IntromeApplication.class.getPackageName());
		SpringApplication.run(IntromeApplication.class, args);
	}

}
