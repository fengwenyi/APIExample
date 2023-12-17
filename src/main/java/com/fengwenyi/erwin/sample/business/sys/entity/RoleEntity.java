package com.fengwenyi.erwin.sample.business.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fengwenyi.erwin.sample.base.BaseBizEntity;
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
@TableName("sys_role")
public class RoleEntity extends BaseBizEntity {

    @Serial
    private static final long serialVersionUID = 2458122357260711246L;

    private String roleName;

    private String roleCode;

    private Double sortNum;
}
