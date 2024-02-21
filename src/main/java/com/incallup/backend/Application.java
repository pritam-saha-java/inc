
package com.incallup.backend;


import com.incallup.backend.domain.Admin;
import com.incallup.backend.domain.Role;
import com.incallup.backend.repository.AdminRepository;
import com.incallup.backend.repository.RoleRepository;
import com.incallup.backend.utility.IncallupConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CommandLineRunner run(AdminRepository adminRepository, RoleRepository roleRepository, PasswordEncoder encoder){
		return args -> {

			if(roleRepository.findRoleByAuthority("ADMIN").isPresent())
				return;

			var adminRole = roleRepository.save(Role.builder()
							.authority("ADMIN")
					.build());
			var roles = new HashSet<Role>();
			roles.add(adminRole);
			Admin admin = Admin.builder()
					.authorities(roles)
					.username("kunal")
					.password(encoder.encode("kunal123"))
					.build();
			adminRepository.save(admin);
//				var admin = Admin.builder()
//						.username(IncallupConstants.ADMIN)
//						.password(IncallupConstants.ADMIN_PASS)
//						.build();
//				adminRepository.save(admin);
		System.out.println("Hello world from command line runner");
		};
	}
}

