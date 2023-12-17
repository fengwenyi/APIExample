package com.fengwenyi.erwin.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {

        //创建CorsConfiguration对象后添加配置
        CorsConfiguration config = new CorsConfiguration();
        //设置放行哪些原始域
//        config.addAllowedOrigin("*");
        config.addAllowedOriginPattern("*");
        //放行哪些原始请求头部信息
        config.addAllowedHeader("*");
        //暴露哪些头部信息
        config.addExposedHeader("*");
        //放行哪些请求方式
//        config.addAllowedMethod("GET");     //get
//        config.addAllowedMethod("PUT");     //put
//        config.addAllowedMethod("POST");    //post
//        config.addAllowedMethod("DELETE");  //delete
        config.addAllowedMethod("*");     //放行全部请求

        //是否发送Cookie
        config.setAllowCredentials(true);

        //2. 添加映射路径
        UrlBasedCorsConfigurationSource corsConfigurationSource =
                new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        //返回CorsFilter
        return new CorsFilter(corsConfigurationSource);
    }

}
