package com.hy.micro.service.dingding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * http://localhost:9200/swagger-ui.html
 * @author Weixuan
 *
 */
@Configuration
@EnableSwagger2
// @Component
public class SwaggerConfig {
	String scanedBasePackage = "com.hy.micro.service.dingding.web.controller";
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// Need to changed according to your package name.
				.apis(RequestHandlerSelectors.basePackage(scanedBasePackage)).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Blooming Rest API").description("Blooming Rest API.")
				.version("1.0")
				.contact(new Contact("Eddie.Wei", "https://github.com/weixuan2008", "www.weixuan@163.com"))
				.build();
	}
}
