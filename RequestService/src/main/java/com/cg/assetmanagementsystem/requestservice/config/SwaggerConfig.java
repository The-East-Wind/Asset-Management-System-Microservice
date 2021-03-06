package com.cg.assetmanagementsystem.requestservice.config;

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
    public Docket requestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cg.assetmanagementsystem.requestservice.controller"))
                .paths(PathSelectors.regex("/requests.*"))
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Request Service API")
                .description("A REST API which provides all Request Related Service in Asset Management System ")
                .contact(new Contact("Hari Mani","https://github.com/The-East-Wind","hari.a.mani@capgemini.com"))
                .version("1.0.0")
                .build();
    }
}
