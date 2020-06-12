package sample.api_result.controller;

import com.fengwenyi.api_result.model.ResultModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.api_result.util.ResponseUtils;
import sample.api_result.vo.response.UserResponseVo;

import java.util.Arrays;
import java.util.List;

/**
 * 用户接口示例
 * @author Erwin Feng
 * @since 2020/6/13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 查询所有用户
     * @return 返回数据：包含用户信息的列表
     */
    @GetMapping
    public ResultModel<List<UserResponseVo>> users() {
        List<UserResponseVo> responseVos = Arrays.asList(
                new UserResponseVo().setUid("x001").setRealName("关羽").setNickname("云长"),
                new UserResponseVo().setUid("x002").setRealName("张飞").setNickname("翼德"),
                new UserResponseVo().setUid("x003").setRealName("赵云").setNickname("子龙")
        );
        return ResponseUtils.success(responseVos);
    }
}