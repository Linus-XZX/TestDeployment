package com.lxzx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lxzx.**"})
@EnableJpaRepositories(basePackages = "com.lxzx.**")
@EntityScan(basePackages = "com.lxzx.**")
public class MainApp extends SpringBootServletInitializer {
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainApp.class);
	}

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}