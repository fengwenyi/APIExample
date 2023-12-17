package com.fengwenyi.erwin.sample.business.sys.admin.dto.menu;

import lombok.Data;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-10
 */
@Data
public class MenuUpdateDto {
    private String path;
    private String name;
    private String component;
    private String redirect;
    private Double sortNum;
    private Long parentId;
    private String icon;
    private String title;
    private Boolean affix;
    private Boolean hideMenu;

    private Boolean ignoreKeepAlive;

    private String currentActiveMenu;

    private String chineseName;
    private Boolean status;
}
