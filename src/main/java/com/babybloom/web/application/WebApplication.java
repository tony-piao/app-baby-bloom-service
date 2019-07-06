package com.babybloom.web.application;

import com.babybloom.web.helper.SpringContentHelper;
import com.babybloom.web.utility.LogUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(basePackages = "com.babybloom.web")
public class WebApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(WebApplication.class, args);
		SpringContentHelper.initCtx(ctx);
		LogUtility.businessLog().info("Spring boot start succsess");
	}

}
