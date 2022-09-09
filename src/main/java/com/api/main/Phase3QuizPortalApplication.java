package com.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.api")
@EntityScan("com.api.bean")
@EnableJpaRepositories("com.api.repository")
public class Phase3QuizPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(Phase3QuizPortalApplication.class, args);
	}

}
