package com.mstoy.restdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
public class RestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/cloudvendor/*"))
				.apis(RequestHandlerSelectors.basePackage("com.mstoy.restdemo"))//패키지를 그대로
				.build()
				.apiInfo(apiCustomData()); //인자로 ApiInfo를 주어야 함, 아래에서 하나 생성해주고 넣어줄것임
	}
	private ApiInfo apiCustomData(){//여기다가 costom 파라미터들을 설정해주면 됨
		return new ApiInfo(
				"Cloud Vendor API Application",
				"Cloud Vendor Documentation",
				"1.0",
				"Cloud Vendor Service Terms",
				new Contact("Minseo Kim", "https://github.com/minseo0102",
						"sk49058275@gmail.com"),
				"Minseo 0102 License",
				"https://github.com/minseo0102",
				Collections.emptyList()
		);

	}
}
