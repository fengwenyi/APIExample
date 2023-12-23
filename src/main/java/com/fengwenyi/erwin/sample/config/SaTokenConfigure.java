package com.fengwenyi.erwin.sample.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-26
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(
                new SaInterceptor(
                        handle -> {
                            StpUtil.checkLogin();
                            // SaRouter.match("/admin/sys/permission/list", r -> StpUtil.checkRoleOr("ADMIN", "SUPER_ADMIN")); // todo 没生效
                            SaRouter.match("/admin/sys/**", r -> StpUtil.checkRole("SUPER_ADMIN"));
                            SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("ADMIN", "SUPER_ADMIN"));

                        })
                )
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/auth/login",
                        "/swagger**/**",
                        "/webjars/**",
                        "/v3/api-docs/**",
                        "/doc.html"
                )
        ;
    }
}
