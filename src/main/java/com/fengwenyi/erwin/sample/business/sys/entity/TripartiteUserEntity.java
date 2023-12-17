package com.fengwenyi.erwin.sample.business.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fengwenyi.erwin.sample.base.BaseBizEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-27
 */
@Getter
@Setter
@ToString
@TableName("sys_tripartite_user")
public class TripartiteUserEntity extends BaseBizEntity {

    @Serial
    private static final long serialVersionUID = -7997539241726894459L;

    private String account;

    private String unionId;

    private String openId;

    private String registerType;

    private Long userId;

}
