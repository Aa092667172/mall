package com.kevin.mall.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {

        Contact contact = new Contact("kevin lin",
                "https://github.com/Aa092667172",
                "ttaehee520@gmail.com");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .description("mall商城API文件")
                .version("1.0")
                .contact(contact)
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kevin.mall.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo)
                ;
    }

}
