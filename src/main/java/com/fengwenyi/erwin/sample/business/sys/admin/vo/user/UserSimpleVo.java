package com.fengwenyi.erwin.sample.business.sys.admin.vo.user;

import com.fengwenyi.erwin.sample.business.sys.admin.vo.role.RoleVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Schema(description = "简单用户vo")
@Data
public class UserSimpleVo {

    @Schema(description = "ID")
    private String id;

    @Schema(description = "用户名")
    private String username;

    private String email;

    private String phone;

    private String nickname;

    private String registerTime;

    private List<RoleVo> roleVoList;

    private String roleNames;

    private Boolean status;

    private List<String> roleIds;

    private String organNames;

    private List<String> organCodes;

}
