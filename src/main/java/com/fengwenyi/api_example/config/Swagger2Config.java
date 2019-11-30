package com.fengwenyi.api_example.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Erwin Feng
 * @since 2019-06-05 09:20
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    private static final String DESCRIPTION = "我是冯文议(Erwin Feng)，Java工程师。我们如何建立一个API工程？怎么设置包比较合理？" +
            "以及与其他一些框架如何优雅的整合。这是一个实战示例，我希望通过这个实例，能够解决或者慢慢解决上面的问题，当然，我自己也会不断去探索" +
            "，并加以完善。更多，请关注我。我的个人网站：https://fengwenyi.com";

    @Bean
    public Docket api(){
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar
                .name("Content-Type")
                .description("请求类型")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("application/json; charset=UTF-8")
                .build();
        pars.add(tokenPar.build());
        //添加head参数end


        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.fengwenyi.api_example"))
                .paths(PathSelectors.regex("/api/.*"))
//                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API实例工程")
                .description(DESCRIPTION)
                .version("1.0.0.BUILD")
                .license("许可证")
                .licenseUrl("许可证URL")

                .termsOfServiceUrl("服务条款URL")

                // 联系人
                .contact(new Contact("Erwin Feng", "https://fengwenyi.com", "xfsy_2015@163.com"))

                .extensions(Arrays.asList(new StringVendorExtension("VE-Name", "VE-Value")))

                .build();
    }

    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(
                new ApiKey("Authorization", "Authorization", "header"));
    }

    private List<SecurityContext> securityContexts() {
        return Lists.newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }
}
