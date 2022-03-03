package com.alerner.testBackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ConfigurationPropertiesScan("com.alerner.testBackEnd.configuration")
public class TestBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestBackEndApplication.class, args);
	}

}
