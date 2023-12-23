package com.fengwenyi.erwin.sample.business.sys.admin.dto.user;

import com.fengwenyi.api.result.PageRequestVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Schema(description = "用户分页查询dto")
@Getter
@Setter
@ToString
public class UserGetPageDto extends PageRequestVo {
    @Serial
    private static final long serialVersionUID = 3445147074145065509L;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;
}
