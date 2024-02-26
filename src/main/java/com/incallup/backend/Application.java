
package com.incallup.backend;



import com.incallup.backend.domain.Admin;
import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Role;
import com.incallup.backend.repository.AdminRepository;
import com.incallup.backend.repository.RoleRepository;
import com.incallup.backend.service.AdminQueryService;
import com.incallup.backend.service.impl.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CommandLineRunner run(AdminService adminService, AdminRepository adminRepository, RoleRepository roleRepository, PasswordEncoder encoder){
		return args -> {


			var categories = adminService.listCategories();
			if(categories.isEmpty()){




				adminService.createCategory(Category.builder().title("Call Girl").build());
				adminService.createCategory(Category.builder().title("Adult Dating").build());
				adminService.createCategory(Category.builder().title("Male Escort").build());
				adminService.createCategory(Category.builder().title("Massage").build());

			}
			Optional<Admin> adminn = adminRepository.findUserByUsername("kunal");
            adminn.ifPresent(admin -> System.out.println("this is admin " + admin));
			if(roleRepository.findRoleByAuthority("ADMIN").isPresent()) {
				return;
			}

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

