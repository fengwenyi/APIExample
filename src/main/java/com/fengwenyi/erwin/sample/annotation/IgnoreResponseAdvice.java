package com.fengwenyi.erwin.sample.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略响应建议
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2022-05-04
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {
}
