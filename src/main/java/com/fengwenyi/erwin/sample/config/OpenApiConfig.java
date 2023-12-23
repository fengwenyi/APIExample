package com.fengwenyi.erwin.sample.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-23
 */
@Configuration
@OpenAPI31
public class OpenApiConfig {

    private License license() {
        return new License()
                .name("Apache License, Version 2.0")
                .url("https://opensource.org/license/apache-2-0/");
    }

    private Info info(){
        return new Info()
                .title("ErwinSampleAPI")
                .description("Erwin Admin Sample Api")
                .version("v0.0.6")
                .license(license());
    }
    private ExternalDocumentation externalDocumentation() {
        return new ExternalDocumentation()
                .description("other 。")
                .url("https://www.yuque.com/fengwenyi/erwin-sample");
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(info())
                .externalDocs(externalDocumentation());
    }

}
