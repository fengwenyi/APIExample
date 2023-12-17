package com.fengwenyi.erwin.sample.business.sys.admin.dto.user;

import com.fengwenyi.api.result.PageRequestVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Getter
@Setter
@ToString
public class UserGetPageDto extends PageRequestVo {
    @Serial
    private static final long serialVersionUID = 3445147074145065509L;

    private String username;

    private String nickname;
}
