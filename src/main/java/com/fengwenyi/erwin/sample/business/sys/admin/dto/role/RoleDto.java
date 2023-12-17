package com.fengwenyi.erwin.sample.business.sys.admin.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-10
 */
@Data
public class RoleDto {

    @NotBlank(message = "角色编码不能为空")
    @Length(max = 32, message = "角色编码长度非法")
    private String roleCode;

    @NotBlank(message = "角色名称不能为空")
    @Length(max = 64, message = "角色名称长度非法")
    private String roleName;

    private Boolean status;

    private Double sortNum;

    private List<Long> menuIds;

}
