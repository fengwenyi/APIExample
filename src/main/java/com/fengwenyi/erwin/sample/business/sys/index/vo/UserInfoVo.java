package com.fengwenyi.erwin.sample.business.sys.index.vo;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Data
public class UserInfoVo {

    private List<RoleInfoVo> roles;
    // 用户id
    private String userId;
    // 用户名
    private String username;
    // 真实名字
    private String realName;
    // 头像
    private String avatar;
    // 介绍
    private String desc;

}
