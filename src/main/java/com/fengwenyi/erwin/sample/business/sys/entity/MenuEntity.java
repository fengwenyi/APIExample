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
@TableName("sys_menu")
public class MenuEntity extends BaseBizEntity {

    @Serial
    private static final long serialVersionUID = -5836776624764092294L;

    // 中文名字，用于展示
    private String chineseName;

    // 名字，不能重复
    private String menuName;

    // 路径，不能重复
    private String menuPath;

    private String component;

    private String title;

    private Boolean affix;

    private String icon;

    private String redirect;

    private Long parentId;

    private Double sortNum;

    private Boolean hideMenu;

    private Boolean ignoreKeepAlive;

    private String currentActiveMenu;
}
