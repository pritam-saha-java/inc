
package com.incallup.backend;


//import com.incallup.backend.domain.*;
//import com.incallup.backend.repository.AdminRepository;
//import com.incallup.backend.repository.SellerRepository;
//import com.incallup.backend.service.impl.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import java.util.ArrayList;
//import java.util.Optional;

/**
 * @author incallup
 * */
@SpringBootApplication
@EnableJpaRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
//

	@Bean
	CommandLineRunner run(
//			AdminService adminService
//			, AdminRepository adminRepository
//			, SellerRepository sellerRepository
	){
		return args -> {


//
//
//			var sellers = adminService.listSellers();
//			if(sellers.isEmpty())
//			{
//
//				sellerRepository.save(Seller.builder()
////								.posts(new ArrayList<Post>())
//						.email("sample@email.com")
//								.id(7800)
//						.username("seller")
//						.password("123456")
//						.build());
//			}
//
//			var categories = adminService.listCategories();
//			if(categories.isEmpty()){
//
//
//
//
//				adminService.createCategory(Category.builder().title("Call Girl").build());
//				adminService.createCategory(Category.builder().title("Verified Call Girl").build());
//				adminService.createCategory(Category.builder().title("Spa And Massage").build());
//				adminService.createCategory(Category.builder().title("Dating").build());
//
//			}
//
//
//			var locations = adminService.listLocations();
//			if(locations.isEmpty()){
//				adminService.createLocation(Location.builder().district("kolkata").state("West Bangal").build());
//				adminService.createLocation(Location.builder().district("digha").state("West Bangal").build());
//				adminService.createLocation(Location.builder().district("purulia").state("West Bangal").build());
//				adminService.createLocation(Location.builder().district("mandarmani").state("West Bangal").build());
//				adminService.createLocation(Location.builder().district("thane").state("MAHARASHTRA").build());
//				adminService.createLocation(Location.builder().district("pune").state("MAHARASHTRA").build());
//				adminService.createLocation(Location.builder().district("solapur").state("MAHARASHTRA").build());
//				adminService.createLocation(Location.builder().district("mumbai").state("MAHARASHTRA").build());
//				adminService.createLocation(Location.builder().district("bangalore").state("Karnataka").build());
//				adminService.createLocation(Location.builder().district("bidar").state("Karnataka").build());
//				adminService.createLocation(Location.builder().district("ahemdabad").state("gujrat").build());
//				adminService.createLocation(Location.builder().district("hyderabad").state("tamilnadu").build());
//			}
//			Optional<Admin> adminn = adminRepository.findUserByUsername("kunal");
//            adminn.ifPresent(admin -> System.out.println("this is admin " + admin));
////			if(roleRepository.findRoleByAuthority("ADMIN").isPresent()) {
////				return;
////			}
//
////			var adminRole = roleRepository.save(Role.builder()
////							.authority("ADMIN")
////					.build());
////			var roles = new HashSet<Role>();
////			roles.add(adminRole);
//			if(adminRepository.findUserByUsername("kunal").isPresent())
//				return;
//			Admin admin = Admin.builder()
////					.authorities(roles)
//					.username("kunal")
//					.password("kunal123")
//					.build();
//			adminRepository.save(admin);
		System.out.println("Hello world from command line runner");

		};
	}
}

