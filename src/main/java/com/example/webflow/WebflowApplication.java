package com.example.webflow;

import com.example.webflow.thymeleaf.FormDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.dialect.IProcessorDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@SpringBootApplication
@ComponentScan
public class WebflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebflowApplication.class, args);
	}

	@Configuration
	public class config {

		@Bean
		public FormDialect createformDialect() {
			return new FormDialect();
		}

	}



}
