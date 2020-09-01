package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan

public class ProjekatIsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjekatIsaApplication.class, args);
	}
}
