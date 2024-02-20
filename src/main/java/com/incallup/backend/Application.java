
package com.incallup.backend;


import com.incallup.backend.domain.Admin;
import com.incallup.backend.repository.AdminRepository;
import com.incallup.backend.utility.IncallupConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CommandLineRunner run(AdminRepository adminRepository){
		return args -> {
//				var admin = Admin.builder()
//						.username(IncallupConstants.ADMIN)
//						.password(IncallupConstants.ADMIN_PASS)
//						.build();
//				adminRepository.save(admin);
		System.out.println("Hello world from command line runner");
		};
	}
}

