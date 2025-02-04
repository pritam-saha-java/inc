
package com.incallup.backend;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * @author incallup
 * */
@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class Website {

	public static void main(String[] args) {
		SpringApplication.run(Website.class, args);
	}


	@Bean
	protected CommandLineRunner run(){
		return args -> {




			log.info("incallup comment");
		};
	}
}

