package com.fengwenyi.erwin.sample.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-23
 */
@Data
@Configuration
@ConfigurationProperties("erwin")
public class ErwinConfigProperties {

    private Api api;

    @Data
    public static class Api {

        private Exclude exclude;

    }

    @Data
    private static class Exclude {

        private List<String> packages;

    }

    public List<String> getApiExcludePackages() {
        return api.exclude.packages;
    }

}
