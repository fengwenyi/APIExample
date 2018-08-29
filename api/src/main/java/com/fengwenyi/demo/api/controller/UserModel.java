package com.fengwenyi.demo.api.controller;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * <类说明>
 * <p>
 * <功能详细描述>
 *
 * @author Wenyi Feng
 * @since 2018-08-28
 */
@Component
@Data
public class UserModel {

    @Size(max = 50)
    private String name;

    @Max(30)
    @Min(20)
    private Integer age;

}
