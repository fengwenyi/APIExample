package sample.api_result.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户响应VO
 * @author Erwin Feng
 * @since 2020/5/26
 */
@Data
@Accessors(chain = true)
public class UserResponseVo {

    /** 用户ID */
    private String uid;

    /** 用户真实姓名 */
    @JsonProperty("real_name")
    private String realName;

    /** 用户昵称 */
    private String nickname;

}
