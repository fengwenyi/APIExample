package com.fengwenyi.erwin.sample.business.sys.admin.dto.role;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-10
 */
@Data
public class RoleUpdateDto {

    @Length(max = 64, message = "角色名称长度非法")
    private String roleName;

    private Boolean status;

    private Double sortNum;

    private List<Long> menuIds;
}
