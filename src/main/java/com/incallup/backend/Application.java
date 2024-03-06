
package com.incallup.backend;



import com.incallup.backend.configuration.WHMService;
import com.incallup.backend.utility.FileUtility;
import com.incallup.backend.utility.FileUtility2;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


/**
 * @author incallup
 * */
@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
//

	@Bean
	CommandLineRunner run(
//			AdminService adminService
//			, AdminRepository adminRepository
//			, SellerRepository sellerRepository,
//			LocationRepository locationRepository,
			WHMService service

	){
		return args -> {
//			/*
//			*
//			*
//			* code for adding locations to database
//			*
//			*
//			**/
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cvirms","root","hello");
//			var statement = con.createStatement();
//			var queryResult = statement.executeQuery("SELECT stateName,district FROM state s join district d on d.state_id=s.stateId;");
//			List<Location> list = new ArrayList<>();
//			while(queryResult.next()){
//				var districtName = queryResult.getString("district");
//				var stateName = queryResult.getString("stateName");
//				var location = Location.builder()
//						.district(districtName)
//						.state(stateName)
//						.meta("call girls in (location), call girl in (location), call girls service in (location), female escort in (location), escort in (location), call girl number in (location), girl whatsapp number in (location), girl sex in (location)".replace("(location)",districtName))
//						.description("Are you looking for CALL GIRL in (location) and escort service? incallup.com adult classifieds ads, independent (location) call girl. the largest call girls ads selection in (location). Browse in our call girl category for get 100% genuine and beautiful call girls (location)".replace("(location)",districtName))
//								.build();
//				list.add(location);
//
//			}
//			System.out.println("result of this entire transfer is "+list.size());
//			/*
//			 *following code is for adding custom locations programmatically
//			 * */
//			List<Location> list = new ArrayList<>();
//
//			Location location = Location.builder().district("digha").state("West Bangal").build();
//			list.add(location);
//			location = Location.builder().district("kolkata").state("West Bangal").build();
//			list.add(location);
//			location = Location.builder().district("purulia").state("West Bangal").build();
//			list.add(location);
//			location = Location.builder().district("mandarmani").state("West Bangal").build();
//			list.add(location);
//			location = Location.builder().district("thane").state("MAHARASHTRA").build();
//			list.add(location);
//			location = Location.builder().district("pune").state("MAHARASHTRA").build();
//			list.add(location);
//			location = Location.builder().district("solapur").state("MAHARASHTRA").build();
//			list.add(location);
//			location = Location.builder().district("mumbai").state("MAHARASHTRA").build();
//			list.add(location);
//			location = Location.builder().district("bangalore").state("Karnataka").build();
//			list.add(location);
//			location = Location.builder().district("bidar").state("Karnataka").build();
//			list.add(location);
//			location = Location.builder().district("ahemdabad").state("gujrat").build();
//			list.add(location);
//			location = Location.builder().district("hyderabad").state("tamilnadu").build();
//			list.add(location);
//			list.forEach(locationObj->{
//				String meta = "call girls in (location), call girl in (location), call girls service in (location), female escort in (location), escort in (location), call girl number in (location), girl whatsapp number in (location), girl sex in (location)".replace("(location)",locationObj.getDistrict());
//				String desc = "Are you looking for CALL GIRL in (location) and escort service? incallup.com adult classifieds ads, independent (location) call girl. the largest call girls ads selection in (location). Browse in our call girl category for get 100% genuine and beautiful call girls (location)".replace("(location)", locationObj.getDistrict());
//				locationObj.setMeta(meta);
//				locationObj.setDescription(desc);
//				log.info("this is location object "+locationObj);
//			});
//			list.forEach(locationObj->{
//				try {
//					adminService.createLocation(locationObj);
//				}catch (Exception e){
//
//					log.info(e.getMessage());
//				}
//			});
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
//			Optional<Admin> adminn = adminRepository.findUserByUsername("kunal");
//            adminn.ifPresent(admin -> System.out.println("this is admin " + admin));
//			if(roleRepository.findRoleByAuthority("ADMIN").isPresent()) {
//				return;
//			}
//			var adminRole = roleRepository.save(Role.builder()
//							.authority("ADMIN")
//					.build());
//			var roles = new HashSet<Role>();
//			roles.add(adminRole);
//			if(adminRepository.findUserByUsername("kunal").isPresent())
//				return;
//			Admin admin = Admin.builder()
//					.authorities(roles)
//					.username("kunal")
//					.password("kunal123")
//					.build();
//			adminRepository.save(admin);
//		System.out.println("Hello world from command line runner");





//			sftpClient.get("inctest/index.html",  "src/main/resources/"+"index.html");

//			File outputFile = new File(path);
//			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();







//			FileOutputStream outputStream = new FileOutputStream(outputFile);
//			FileUtils.writeByteArrayToFile(outputFile,bytes);







//			outputStream.close();

//			outputStream.write(bytes);
//			OutputStream outputStream = Files.newOutputStream(path);
//			LocalDestFile localDestFile =

//			sftpClient.get("inctest/index.html");

//			File file = new File("src/main/resources/schema.sql");
//
//			log.error(String.valueOf(file.isFile()));
//			MultipartFile multipartFile = new MockM

//		log.info("download started");
//		boolean isDownloaded = service.downloadFile("src/main/resources/index.html","/inctest/index.html");
//		log.info("download result"+isDownloaded);


		};
	}
}

