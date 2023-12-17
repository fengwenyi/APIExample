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
@TableName("sys_user")
public class UserEntity extends BaseBizEntity {
    @Serial
    private static final long serialVersionUID = 7956245242779170013L;

    private String username;

    private String password;

    private String nickname;

    private Boolean locked;

    private String email;

    private String phone;

}
