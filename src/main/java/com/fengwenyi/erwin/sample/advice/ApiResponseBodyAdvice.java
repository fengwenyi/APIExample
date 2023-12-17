package com.fengwenyi.erwin.sample.advice;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.erwin.sample.annotation.IgnoreResponseAdvice;
import com.fengwenyi.javalib.convert.JsonUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2022-05-04
 */
@RestControllerAdvice
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        if (returnType.getDeclaringClass()
                .isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        Method method = returnType.getMethod();
        if (Objects.isNull(method)) {
            return true;
        }

        if (method.isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        ResultTemplate<Object> responseTemplate = ResultTemplate.success();

        if (Objects.isNull(body)) {
            return responseTemplate;
        } else if (body instanceof ResultTemplate) {
            return body;
        } else if (body instanceof String) {
            return JsonUtils.convertString(ResultTemplate.success(body));
        } else {
            return responseTemplate.setBody(body);
        }
    }
}
