package com.fengwenyi.api.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户实体
 * @author Erwin Feng
 * @since 2020/4/11 22:43
 */
@Data
@Accessors(chain = true)
public class UserEntity {

    private int uid;

    private String name;

    private String gender;

    private int age;

}
