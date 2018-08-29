package com.fengwenyi.demo.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * @author Wenyi Feng
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigure {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("base-host")
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.fengwenyi.demo.api.controller"))
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.fengwenyi"))

                .paths(PathSelectors.regex("/**"))

                .paths(PathSelectors.ant("/error, /ops/**"))

                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Doc 标题")
                .description("简要描述")
                .version("版本")
                .license("许可证")
                .licenseUrl("许可证URL")

                .termsOfServiceUrl("服务条款URL")

                // 联系人
                .contact(new Contact("Wenyi Feng", "https://fengwenyi.com", "xfsy2014@gmail.com"))

                .extensions(Arrays.asList(new StringVendorExtension("VE-Name", "VE-Value")))

                .build();
    }

}
