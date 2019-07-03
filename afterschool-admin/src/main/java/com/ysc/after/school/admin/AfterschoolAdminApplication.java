package com.ysc.after.school.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AfterschoolAdminApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AfterschoolAdminApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AfterschoolAdminApplication.class, args);
	}

}
