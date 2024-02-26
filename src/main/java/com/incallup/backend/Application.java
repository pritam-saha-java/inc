
package com.incallup.backend;



import com.incallup.backend.domain.Admin;
import com.incallup.backend.domain.Role;
import com.incallup.backend.repository.AdminRepository;
import com.incallup.backend.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CommandLineRunner run(AdminRepository adminRepository, RoleRepository roleRepository, PasswordEncoder encoder){
		return args -> {

			Optional<Admin> adminn = adminRepository.findUserByUsername("kunal");
			System.out.println("this is admin "+adminn.get());
			if(roleRepository.findRoleByAuthority("ADMIN").isPresent())
				return;

			var adminRole = roleRepository.save(Role.builder()
							.authority("ADMIN")
					.build());
			var roles = new HashSet<Role>();
			roles.add(adminRole);
			if(adminRepository.findUserByUsername("kunal").isPresent())
				return;
			Admin admin = Admin.builder()
					.authorities(roles)
					.username("kunal")
					.password(encoder.encode("kunal123"))
					.build();
			adminRepository.save(admin);
		System.out.println("Hello world from command line runner");

		};
	}
}

