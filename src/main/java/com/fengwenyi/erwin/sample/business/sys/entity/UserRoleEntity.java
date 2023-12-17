package com.fengwenyi.erwin.sample.business.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fengwenyi.erwin.sample.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
@Getter
@Setter
@ToString
@TableName("sys_user_role")
public class UserRoleEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 6135144069301106661L;

    private Long userId;

    private Long roleId;

}
