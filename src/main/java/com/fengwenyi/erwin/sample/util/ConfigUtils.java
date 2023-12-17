package com.fengwenyi.erwin.sample.util;

import com.fengwenyi.javalib.convert.JsonUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-19
 */
public class ConfigUtils {


    public static <T> T getConfig(String configJson, Class<T> clazz) {
        if (!StringUtils.hasText(configJson)) {
            return null;
        }
        return JsonUtils.convertObject(configJson, clazz);
    }

    public static <T> List<T> getListByConfigJson(String configJson, Class<T> clazz) {
        if (!StringUtils.hasText(configJson)) {
            return null;
        }
        return JsonUtils.convertCollection(configJson, List.class, clazz);
    }

}
