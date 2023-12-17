package com.fengwenyi.erwin.sample.business.sys.admin.vo.user;

import com.fengwenyi.erwin.sample.business.sys.admin.vo.role.RoleVo;
import lombok.Data;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Data
public class UserSimpleVo {

    private String id;

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
