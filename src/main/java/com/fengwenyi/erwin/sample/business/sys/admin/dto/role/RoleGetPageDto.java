package com.fengwenyi.erwin.sample.business.sys.admin.dto.role;

import com.fengwenyi.api.result.PageRequestVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-10
 */
@Getter
@Setter
@ToString
public class RoleGetPageDto extends PageRequestVo {
    @Serial
    private static final long serialVersionUID = 1489632572224680370L;

    private String roleName;
}
