package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
public class DemoApplication
 {

	public static void main(String[] args) {
//        SpringApplication application = new SpringApplication(DemoApplication.class);
        SpringApplication.run(DemoApplication.class, args);
//        application.setWebApplicationType(WebApplicationType.NONE);
//        ApplicationContext context = application.run();
//        SpringApplication.exit(context);
    }


	@RequestMapping("/")
	String hello() {
		return "Hello World!!!";
	}
}
