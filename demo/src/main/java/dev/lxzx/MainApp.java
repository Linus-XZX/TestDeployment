package dev.lxzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.lxzx.**"})
@EnableJpaRepositories(basePackages = "dev.lxzx.**")
@EntityScan(basePackages = "dev.lxzx.**")
public class MainApp extends SpringBootServletInitializer {
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainApp.class);
	}

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}