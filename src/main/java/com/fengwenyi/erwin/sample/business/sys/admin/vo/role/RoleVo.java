package com.fengwenyi.erwin.sample.business.sys.admin.vo.role;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-10
 */
@Data
public class RoleVo {

    private String id;

    private String roleCode;

    private String roleName;

    private Boolean status;

    private Double sortNum;

    private List<String> menuIds;

}
