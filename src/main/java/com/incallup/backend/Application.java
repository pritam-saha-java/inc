
package com.incallup.backend;



//import com.incallup.backend.configuration.WHMService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


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
	protected CommandLineRunner run(){
		return args -> {


			String url = "https://localhost";
			if(Desktop.isDesktopSupported()){
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI(url));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
//			else{
//				Runtime runtime = Runtime.getRuntime();
//				try {
//					runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}


		};
	}
}

