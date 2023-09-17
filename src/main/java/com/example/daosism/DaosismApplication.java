package com.example.daosism;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@SpringBootApplication(scanBasePackages = "com.example.daosism")
@EnableTransactionManagement
@EntityScan
public class DaosismApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaosismApplication.class, args);
	}
}
