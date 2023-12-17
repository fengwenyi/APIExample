package com.fengwenyi.erwin.sample.business.sys.admin.vo.menu;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-14
 */
@Data
public class MenuTreeItemVo {

    private String id;
    private String path;
    private String name;
    private String component;
    private String redirect;
    private Meta meta;
    private Double sortNum;
    private String parentId;
    private String chineseName;
    private Boolean status;
    private List<MenuTreeItemVo> children;

    @Data
    public static class Meta {

        private String icon;
        private String title;
        private Boolean affix;
        private Boolean hideMenu;
        private Boolean ignoreKeepAlive;
        private String currentActiveMenu;

    }

}
