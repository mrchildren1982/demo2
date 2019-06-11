package com.example.demo;

import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;

//@SpringBootApplication
//@RestController
public class BatchApplication
implements CommandLineRunner {

	public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BatchApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        ApplicationContext context = application.run();
        SpringApplication.exit(context);
    }

    @Override
    public void run(String... args) throws Exception {
        CommandLineJobRunner.main(new String[] {
                "com.example.demo.domain.batch.SpringBatchSample1Configuration",
                "sampleBatch1Job"});
    }

	@RequestMapping("/")
	String hello() {
		return "Hello World!!!";
	}
}
